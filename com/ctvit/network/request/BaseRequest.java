package com.ctvit.network.request;

import android.arch.lifecycle.AbstractC0052c;
import android.content.Context;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import android.text.TextUtils;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.api.ApiService;
import com.ctvit.network.cache.RxCache;
import com.ctvit.network.cache.converter.IDiskConverter;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.https.HttpsUtils;
import com.ctvit.network.interceptor.BaseDynamicInterceptor;
import com.ctvit.network.interceptor.CacheInterceptor;
import com.ctvit.network.interceptor.CacheInterceptorOffline;
import com.ctvit.network.interceptor.HeadersInterceptor;
import com.ctvit.network.interceptor.NoCacheInterceptor;
import com.ctvit.network.model.HttpHeaders;
import com.ctvit.network.model.HttpParams;
import com.ctvit.network.request.BaseRequest;
import com.ctvit.network.utils.RxUtils;
import com.ctvit.network.utils.Utils;
import java.io.File;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import p014b4.InterfaceC0446f;
import p086k2.C1231b;
import p186x2.C2073a;
import p194y3.AbstractC2120l;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public abstract class BaseRequest<R extends BaseRequest> {
    public ApiService apiManager;
    public String baseUrl;
    public Cache cache;
    public String cacheKey;
    public CacheMode cacheMode;
    public long cacheTime;
    public long connectTimeout;
    public Context context;
    public IDiskConverter diskConverter;
    public HostnameVerifier hostnameVerifier;
    public HttpUrl httpUrl;
    public boolean isSyncRequest;
    private AbstractC0052c lifecycle;
    public OkHttpClient okHttpClient;
    public Proxy proxy;
    public long readTimeOut;
    public Retrofit retrofit;
    public int retryCount;
    public int retryDelay;
    public int retryIncreaseDelay;
    public RxCache rxCache;
    public HttpsUtils.SSLParams sslParams;
    public String url;
    public long writeTimeOut;
    public final List<Interceptor> networkInterceptors = new ArrayList();
    public final List<Interceptor> interceptors = new ArrayList();
    public List<Cookie> cookies = new ArrayList();
    public HttpHeaders headers = new HttpHeaders();
    public HttpParams params = new HttpParams();
    public List<Converter.Factory> converterFactories = new ArrayList();
    public List<CallAdapter.Factory> adapterFactories = new ArrayList();
    private boolean sign = false;
    private boolean timeStamp = false;
    private boolean accessToken = false;
    private AbstractC0052c.a autoDisposeEvent = AbstractC0052c.a.ON_DESTROY;

    /* renamed from: com.ctvit.network.request.BaseRequest$1 */
    public class C06551 implements InterfaceC0446f<Boolean> {
        public C06551() {
        }

        @Override // p014b4.InterfaceC0446f
        public void accept(Boolean bool) {
            C2073a.m2459d("缓存删除成功!!!");
        }
    }

    /* renamed from: com.ctvit.network.request.BaseRequest$2 */
    public class C06562 implements InterfaceC0446f<Throwable> {
        public C06562() {
        }

        @Override // p014b4.InterfaceC0446f
        public void accept(Throwable th) {
            C2073a.m2456a("缓存删除失败!!!" + th);
        }
    }

    /* renamed from: com.ctvit.network.request.BaseRequest$3 */
    public static /* synthetic */ class C06573 {
        public static final /* synthetic */ int[] $SwitchMap$com$ctvit$network$cache$model$CacheMode;

        static {
            int[] iArr = new int[CacheMode.values().length];
            $SwitchMap$com$ctvit$network$cache$model$CacheMode = iArr;
            try {
                iArr[CacheMode.NO_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ctvit$network$cache$model$CacheMode[CacheMode.DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ctvit$network$cache$model$CacheMode[CacheMode.FIRSTREMOTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ctvit$network$cache$model$CacheMode[CacheMode.FIRSTCACHE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ctvit$network$cache$model$CacheMode[CacheMode.ONLYREMOTE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ctvit$network$cache$model$CacheMode[CacheMode.ONLYCACHE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$ctvit$network$cache$model$CacheMode[CacheMode.CACHEANDREMOTE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$ctvit$network$cache$model$CacheMode[CacheMode.CACHEANDREMOTEDISTINCT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public BaseRequest(String str) {
        this.cache = null;
        this.cacheMode = CacheMode.NO_CACHE;
        this.cacheTime = -1L;
        this.url = str;
        CtvitHttp ctvitHttp = CtvitHttp.getInstance();
        String baseUrl = CtvitHttp.getBaseUrl();
        this.baseUrl = baseUrl;
        if (!TextUtils.isEmpty(baseUrl)) {
            this.httpUrl = HttpUrl.parse(this.baseUrl);
        } else if (str != null && (str.startsWith("http://") || str.startsWith("https://"))) {
            this.httpUrl = HttpUrl.parse(str);
            this.baseUrl = this.httpUrl.url().getProtocol() + "://" + this.httpUrl.url().getHost();
        }
        this.cacheMode = CtvitHttp.getCacheMode();
        this.cacheTime = CtvitHttp.getCacheTime();
        this.retryCount = CtvitHttp.getRetryCount();
        this.retryDelay = CtvitHttp.getRetryDelay();
        this.retryIncreaseDelay = CtvitHttp.getRetryIncreaseDelay();
        this.cache = CtvitHttp.getHttpCache();
        String acceptLanguage = HttpHeaders.getAcceptLanguage();
        if (!TextUtils.isEmpty(acceptLanguage)) {
            headers(HttpHeaders.HEAD_KEY_ACCEPT_LANGUAGE, acceptLanguage);
        }
        String userAgent = HttpHeaders.getUserAgent();
        if (!TextUtils.isEmpty(userAgent)) {
            headers("User-Agent", userAgent);
        }
        if (ctvitHttp.getCommonParams() != null) {
            this.params.put(ctvitHttp.getCommonParams());
        }
        if (ctvitHttp.getCommonHeaders() != null) {
            this.headers.put(ctvitHttp.getCommonHeaders());
        }
    }

    private OkHttpClient.Builder generateOkClient() {
        if (this.readTimeOut <= 0 && this.writeTimeOut <= 0 && this.connectTimeout <= 0 && this.sslParams == null && this.cookies.size() == 0 && this.hostnameVerifier == null && this.proxy == null && this.headers.isEmpty()) {
            OkHttpClient.Builder okHttpClientBuilder = CtvitHttp.getOkHttpClientBuilder();
            for (Interceptor interceptor : okHttpClientBuilder.interceptors()) {
                if (interceptor instanceof BaseDynamicInterceptor) {
                    ((BaseDynamicInterceptor) interceptor).sign(this.sign).timeStamp(this.timeStamp).accessToken(this.accessToken);
                }
            }
            return okHttpClientBuilder;
        }
        OkHttpClient.Builder builderNewBuilder = CtvitHttp.getOkHttpClient().newBuilder();
        long j7 = this.readTimeOut;
        if (j7 > 0) {
            builderNewBuilder.readTimeout(j7, TimeUnit.MILLISECONDS);
        }
        long j8 = this.writeTimeOut;
        if (j8 > 0) {
            builderNewBuilder.writeTimeout(j8, TimeUnit.MILLISECONDS);
        }
        long j9 = this.connectTimeout;
        if (j9 > 0) {
            builderNewBuilder.connectTimeout(j9, TimeUnit.MILLISECONDS);
        }
        HostnameVerifier hostnameVerifier = this.hostnameVerifier;
        if (hostnameVerifier != null) {
            builderNewBuilder.hostnameVerifier(hostnameVerifier);
        }
        HttpsUtils.SSLParams sSLParams = this.sslParams;
        if (sSLParams != null) {
            builderNewBuilder.sslSocketFactory(sSLParams.sSLSocketFactory, sSLParams.trustManager);
        }
        Proxy proxy = this.proxy;
        if (proxy != null) {
            builderNewBuilder.proxy(proxy);
        }
        if (this.cookies.size() > 0) {
            CtvitHttp.getCookieJar().addCookies(this.cookies);
        }
        builderNewBuilder.addInterceptor(new HeadersInterceptor(this.headers));
        for (Interceptor interceptor2 : this.interceptors) {
            if (interceptor2 instanceof BaseDynamicInterceptor) {
                ((BaseDynamicInterceptor) interceptor2).sign(this.sign).timeStamp(this.timeStamp).accessToken(this.accessToken);
            }
            builderNewBuilder.addInterceptor(interceptor2);
        }
        if (this.networkInterceptors.size() > 0) {
            Iterator<Interceptor> it = this.networkInterceptors.iterator();
            while (it.hasNext()) {
                builderNewBuilder.addNetworkInterceptor(it.next());
            }
        }
        if (C1231b.f2762d && CtvitHttp.getLoggingInterceptor() != null) {
            builderNewBuilder.addInterceptor(CtvitHttp.getLoggingInterceptor());
        }
        return builderNewBuilder;
    }

    private Retrofit.Builder generateRetrofit() {
        if (this.converterFactories.isEmpty() && this.adapterFactories.isEmpty()) {
            Retrofit.Builder retrofitBuilder = CtvitHttp.getRetrofitBuilder();
            if (!TextUtils.isEmpty(this.baseUrl)) {
                retrofitBuilder.baseUrl(this.baseUrl);
            }
            return retrofitBuilder;
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        if (!TextUtils.isEmpty(this.baseUrl)) {
            builder.baseUrl(this.baseUrl);
        }
        if (this.converterFactories.isEmpty()) {
            Retrofit.Builder retrofitBuilder2 = CtvitHttp.getRetrofitBuilder();
            if (!TextUtils.isEmpty(this.baseUrl)) {
                retrofitBuilder2.baseUrl(this.baseUrl);
            }
            Iterator<Converter.Factory> it = retrofitBuilder2.build().converterFactories().iterator();
            while (it.hasNext()) {
                builder.addConverterFactory(it.next());
            }
        } else {
            Iterator<Converter.Factory> it2 = this.converterFactories.iterator();
            while (it2.hasNext()) {
                builder.addConverterFactory(it2.next());
            }
        }
        if (this.adapterFactories.isEmpty()) {
            Iterator<CallAdapter.Factory> it3 = CtvitHttp.getRetrofitBuilder().baseUrl(this.baseUrl).build().callAdapterFactories().iterator();
            while (it3.hasNext()) {
                builder.addCallAdapterFactory(it3.next());
            }
        } else {
            Iterator<CallAdapter.Factory> it4 = this.adapterFactories.iterator();
            while (it4.hasNext()) {
                builder.addCallAdapterFactory(it4.next());
            }
        }
        return builder;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private RxCache.Builder generateRxCache() {
        RxCache.Builder rxCacheBuilder = CtvitHttp.getRxCacheBuilder();
        switch (C06573.$SwitchMap$com$ctvit$network$cache$model$CacheMode[this.cacheMode.ordinal()]) {
            case 1:
                NoCacheInterceptor noCacheInterceptor = new NoCacheInterceptor();
                this.interceptors.add(noCacheInterceptor);
                this.networkInterceptors.add(noCacheInterceptor);
                return rxCacheBuilder;
            case 2:
                if (this.cache == null) {
                    File cacheDirectory = CtvitHttp.getCacheDirectory();
                    if (cacheDirectory == null) {
                        cacheDirectory = new File(CtvitHttp.getContext().getCacheDir(), "okhttp-cache");
                    } else if (cacheDirectory.isDirectory() && !cacheDirectory.exists()) {
                        cacheDirectory.mkdirs();
                    }
                    this.cache = new Cache(cacheDirectory, Math.max(5242880L, CtvitHttp.getCacheMaxSize()));
                }
                String str = String.format("max-age=%d", Long.valueOf(Math.max(-1L, this.cacheTime)));
                CacheInterceptor cacheInterceptor = new CacheInterceptor(CtvitHttp.getContext(), str);
                CacheInterceptorOffline cacheInterceptorOffline = new CacheInterceptorOffline(CtvitHttp.getContext(), str);
                this.networkInterceptors.add(cacheInterceptor);
                this.networkInterceptors.add(cacheInterceptorOffline);
                this.interceptors.add(cacheInterceptorOffline);
                return rxCacheBuilder;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                if (TextUtils.isEmpty(this.cacheKey)) {
                    this.cacheKey = getUrl() + getParams().urlParamsMap.toString();
                }
                this.interceptors.add(new NoCacheInterceptor());
                if (this.diskConverter == null) {
                    rxCacheBuilder.cachekey((String) Utils.checkNotNull(this.cacheKey, "cacheKey == null")).cacheTime(this.cacheTime);
                    return rxCacheBuilder;
                }
                RxCache.Builder builderNewBuilder = CtvitHttp.getRxCache().newBuilder();
                builderNewBuilder.diskConverter(this.diskConverter).cachekey((String) Utils.checkNotNull(this.cacheKey, "cacheKey == null")).cacheTime(this.cacheTime);
                return builderNewBuilder;
            default:
                return rxCacheBuilder;
        }
    }

    public R addCallAdapterFactory(CallAdapter.Factory factory) {
        this.adapterFactories.add(factory);
        return this;
    }

    public R addConverterFactory(Converter.Factory factory) {
        this.converterFactories.add(factory);
        return this;
    }

    public R addCookie(String str, String str2) {
        this.cookies.add(new Cookie.Builder().name(str).value(str2).domain(this.httpUrl.host()).build());
        return this;
    }

    public R addCookies(List<Cookie> list) {
        this.cookies.addAll(list);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public R addInterceptor(Interceptor interceptor) {
        this.interceptors.add(Utils.checkNotNull(interceptor, "interceptor == null"));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public R addNetworkInterceptor(Interceptor interceptor) {
        this.networkInterceptors.add(Utils.checkNotNull(interceptor, "interceptor == null"));
        return this;
    }

    public R baseUrl(String str) {
        this.baseUrl = str;
        if (!TextUtils.isEmpty(str)) {
            this.httpUrl = HttpUrl.parse(str);
        }
        return this;
    }

    public R bindAutoDisposeEvent(AbstractC0052c.a aVar) {
        this.autoDisposeEvent = aVar;
        return this;
    }

    public R bindLifecycle(AbstractC0052c abstractC0052c) {
        this.lifecycle = abstractC0052c;
        return this;
    }

    public R build() {
        RxCache.Builder builderGenerateRxCache = generateRxCache();
        OkHttpClient.Builder builderGenerateOkClient = generateOkClient();
        if (this.cacheMode == CacheMode.DEFAULT) {
            builderGenerateOkClient.cache(this.cache);
        }
        Retrofit.Builder builderGenerateRetrofit = generateRetrofit();
        OkHttpClient okHttpClientBuild = builderGenerateOkClient.build();
        this.okHttpClient = okHttpClientBuild;
        builderGenerateRetrofit.client(okHttpClientBuild);
        this.retrofit = builderGenerateRetrofit.build();
        this.rxCache = builderGenerateRxCache.build();
        this.apiManager = (ApiService) this.retrofit.create(ApiService.class);
        return this;
    }

    public R cacheDiskConverter(IDiskConverter iDiskConverter) {
        this.diskConverter = (IDiskConverter) Utils.checkNotNull(iDiskConverter, "converter == null");
        return this;
    }

    public R cacheKey(String str) {
        this.cacheKey = str;
        return this;
    }

    public R cacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
        return this;
    }

    public R cacheTime(long j7) {
        if (j7 <= -1) {
            j7 = -1;
        }
        this.cacheTime = j7;
        return this;
    }

    public R certificates(InputStream... inputStreamArr) {
        this.sslParams = HttpsUtils.getSslSocketFactory(null, null, inputStreamArr);
        return this;
    }

    public R connectTimeout(long j7) {
        this.connectTimeout = j7;
        return this;
    }

    public R context(Context context) {
        this.context = context;
        return this;
    }

    public abstract AbstractC2120l<ResponseBody> generateRequest();

    public AbstractC0052c.a getAutoDisposeEvent() {
        return this.autoDisposeEvent;
    }

    public AbstractC0052c getLifecycle() {
        return this.lifecycle;
    }

    public HttpParams getParams() {
        return this.params;
    }

    public String getUrl() {
        if (TextUtils.isEmpty(this.url)) {
            return "";
        }
        if (this.url.startsWith("http")) {
            return this.url;
        }
        return this.baseUrl + this.url;
    }

    public R headers(HttpHeaders httpHeaders) {
        this.headers.put(httpHeaders);
        return this;
    }

    public R hostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public R okCache(Cache cache) {
        this.cache = cache;
        return this;
    }

    public R okproxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public R params(HttpParams httpParams) {
        this.params.put(httpParams);
        return this;
    }

    public R readTimeOut(long j7) {
        this.readTimeOut = j7;
        return this;
    }

    public R removeAllHeaders() {
        this.headers.clear();
        return this;
    }

    public R removeAllParams() {
        this.params.clear();
        return this;
    }

    public void removeCache(String str) {
        CtvitHttp.getRxCache().remove(str).compose(RxUtils.io_main()).subscribe(new InterfaceC0446f<Boolean>() { // from class: com.ctvit.network.request.BaseRequest.1
            public C06551() {
            }

            @Override // p014b4.InterfaceC0446f
            public void accept(Boolean bool) {
                C2073a.m2459d("缓存删除成功!!!");
            }
        }, new InterfaceC0446f<Throwable>() { // from class: com.ctvit.network.request.BaseRequest.2
            public C06562() {
            }

            @Override // p014b4.InterfaceC0446f
            public void accept(Throwable th) {
                C2073a.m2456a("缓存删除失败!!!" + th);
            }
        });
    }

    public R removeCommonHeaders() {
        LinkedHashMap<String, String> linkedHashMap;
        HttpHeaders commonHeaders = CtvitHttp.getInstance().getCommonHeaders();
        if (commonHeaders != null && (linkedHashMap = commonHeaders.headersMap) != null && !linkedHashMap.isEmpty()) {
            Iterator<String> it = commonHeaders.headersMap.keySet().iterator();
            String strM97a = "";
            while (it.hasNext()) {
                strM97a = C0096a.m97a(strM97a, it.next(), ",");
            }
            if (!TextUtils.isEmpty(strM97a)) {
                for (String str : strM97a.split(",")) {
                    this.headers.remove(str);
                }
            }
        }
        return this;
    }

    public R removeCommonParams() {
        HttpParams commonParams = CtvitHttp.getInstance().getCommonParams();
        if (commonParams != null) {
            LinkedHashMap<String, String> linkedHashMap = commonParams.urlParamsMap;
            String strM97a = "";
            if (linkedHashMap != null && !linkedHashMap.isEmpty()) {
                Iterator<String> it = commonParams.urlParamsMap.keySet().iterator();
                String strM97a2 = "";
                while (it.hasNext()) {
                    strM97a2 = C0096a.m97a(strM97a2, it.next(), ",");
                }
                if (!TextUtils.isEmpty(strM97a2)) {
                    for (String str : strM97a2.split(",")) {
                        this.params.removeUrl(str);
                    }
                }
            }
            LinkedHashMap<String, List<HttpParams.FileWrapper>> linkedHashMap2 = commonParams.fileParamsMap;
            if (linkedHashMap2 != null && !linkedHashMap2.isEmpty()) {
                Iterator<String> it2 = commonParams.fileParamsMap.keySet().iterator();
                while (it2.hasNext()) {
                    strM97a = C0096a.m97a(strM97a, it2.next(), ",");
                }
                if (!TextUtils.isEmpty(strM97a)) {
                    for (String str2 : strM97a.split(",")) {
                        this.params.removeFile(str2);
                    }
                }
            }
        }
        return this;
    }

    public R removeHeader(String str) {
        this.headers.remove(str);
        return this;
    }

    public R removeParam(String str) {
        this.params.remove(str);
        return this;
    }

    public R retryCount(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("retryCount must > 0");
        }
        this.retryCount = i7;
        return this;
    }

    public R retryDelay(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("retryDelay must > 0");
        }
        this.retryDelay = i7;
        return this;
    }

    public R retryIncreaseDelay(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("retryIncreaseDelay must > 0");
        }
        this.retryIncreaseDelay = i7;
        return this;
    }

    public R sign(boolean z6) {
        this.sign = z6;
        return this;
    }

    public R syncRequest(boolean z6) {
        this.isSyncRequest = z6;
        return this;
    }

    public R writeTimeOut(long j7) {
        this.writeTimeOut = j7;
        return this;
    }

    public R certificates(InputStream inputStream, String str, InputStream... inputStreamArr) {
        this.sslParams = HttpsUtils.getSslSocketFactory(inputStream, str, inputStreamArr);
        return this;
    }

    public R headers(String str, String str2) {
        this.headers.put(str, str2);
        return this;
    }

    public R params(String str, String str2) {
        this.params.put(str, str2);
        return this;
    }

    public R params(Map<String, String> map) {
        this.params.put(map);
        return this;
    }

    public R addCookie(Cookie cookie) {
        this.cookies.add(cookie);
        return this;
    }
}
