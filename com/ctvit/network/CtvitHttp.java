package com.ctvit.network;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import com.ctvit.network.cache.RxCache;
import com.ctvit.network.cache.converter.GsonDiskConverter;
import com.ctvit.network.cache.converter.IDiskConverter;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.cookie.CookieManger;
import com.ctvit.network.https.HttpsUtils;
import com.ctvit.network.interceptor.HttpLoggingInterceptor;
import com.ctvit.network.model.HttpHeaders;
import com.ctvit.network.model.HttpParams;
import com.ctvit.network.request.CustomRequest;
import com.ctvit.network.request.DeleteRequest;
import com.ctvit.network.request.DownloadRequest;
import com.ctvit.network.request.GetRequest;
import com.ctvit.network.request.PostRequest;
import com.ctvit.network.request.PutRequest;
import com.ctvit.network.utils.RxUtils;
import com.ctvit.network.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import p014b4.InterfaceC0446f;
import p086k2.C1231b;
import p186x2.C2073a;
import p201z3.InterfaceC2153b;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@SuppressLint({"CheckResult"})
/* loaded from: classes.dex */
public final class CtvitHttp {
    public static final int DEFAULT_CACHE_NEVER_EXPIRE = -1;
    public static final int DEFAULT_MILLISECONDS = 60000;
    private static final int DEFAULT_RETRY_COUNT = 3;
    private static final int DEFAULT_RETRY_DELAY = 500;
    private static final int DEFAULT_RETRY_INCREASEDELAY = 0;
    private static Application sContext;
    private static volatile CtvitHttp singleton;
    private CookieManger cookieJar;
    private HttpLoggingInterceptor loggingInterceptor;
    private String mBaseUrl;
    private File mCacheDirectory;
    private long mCacheMaxSize;
    private HttpHeaders mCommonHeaders;
    private HttpParams mCommonParams;
    private OkHttpClient.Builder okHttpClientBuilder;
    private Retrofit.Builder retrofitBuilder;
    private RxCache.Builder rxCacheBuilder;
    private Cache mCache = null;
    private CacheMode mCacheMode = CacheMode.NO_CACHE;
    private long mCacheTime = -1;
    private int mRetryCount = 3;
    private int mRetryDelay = DEFAULT_RETRY_DELAY;
    private int mRetryIncreaseDelay = 0;

    /* renamed from: com.ctvit.network.CtvitHttp$1 */
    public static class C06281 implements InterfaceC0446f<Boolean> {
        @Override // p014b4.InterfaceC0446f
        public void accept(Boolean bool) {
            C2073a.m2459d("clearCache success!!!");
        }
    }

    /* renamed from: com.ctvit.network.CtvitHttp$2 */
    public static class C06292 implements InterfaceC0446f<Throwable> {
        @Override // p014b4.InterfaceC0446f
        public void accept(Throwable th) {
            C2073a.m2459d("clearCache err!!!");
        }
    }

    /* renamed from: com.ctvit.network.CtvitHttp$3 */
    public static class C06303 implements InterfaceC0446f<Boolean> {
        @Override // p014b4.InterfaceC0446f
        public void accept(Boolean bool) {
            C2073a.m2459d("removeCache success!!!");
        }
    }

    /* renamed from: com.ctvit.network.CtvitHttp$4 */
    public static class C06314 implements InterfaceC0446f<Throwable> {
        @Override // p014b4.InterfaceC0446f
        public void accept(Throwable th) {
            C2073a.m2459d("removeCache err!!!");
        }
    }

    public class DefaultHostnameVerifier implements HostnameVerifier {
        public DefaultHostnameVerifier() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    private CtvitHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        this.okHttpClientBuilder = builder;
        builder.hostnameVerifier(new DefaultHostnameVerifier());
        OkHttpClient.Builder builder2 = this.okHttpClientBuilder;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        builder2.connectTimeout(60000L, timeUnit);
        this.okHttpClientBuilder.readTimeout(60000L, timeUnit);
        this.okHttpClientBuilder.writeTimeout(60000L, timeUnit);
        Retrofit.Builder builder3 = new Retrofit.Builder();
        this.retrofitBuilder = builder3;
        builder3.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        this.rxCacheBuilder = new RxCache.Builder().init(sContext).diskConverter(new GsonDiskConverter());
        setHttpLoggingInterceptor();
    }

    public static void cancelSubscription(InterfaceC2153b interfaceC2153b) {
        if (interfaceC2153b == null || interfaceC2153b.isDisposed()) {
            return;
        }
        interfaceC2153b.dispose();
    }

    public static void clearCache() {
        getRxCache().clear().compose(RxUtils.io_main()).subscribe(new InterfaceC0446f<Boolean>() { // from class: com.ctvit.network.CtvitHttp.1
            @Override // p014b4.InterfaceC0446f
            public void accept(Boolean bool) {
                C2073a.m2459d("clearCache success!!!");
            }
        }, new InterfaceC0446f<Throwable>() { // from class: com.ctvit.network.CtvitHttp.2
            @Override // p014b4.InterfaceC0446f
            public void accept(Throwable th) {
                C2073a.m2459d("clearCache err!!!");
            }
        });
    }

    public static CustomRequest custom() {
        return new CustomRequest();
    }

    public static DeleteRequest delete(String str) {
        return new DeleteRequest(str);
    }

    public static DownloadRequest downLoad(String str) {
        return new DownloadRequest(str);
    }

    public static GetRequest get(String str) {
        return new GetRequest(str);
    }

    public static String getBaseUrl() {
        return getInstance().mBaseUrl;
    }

    public static File getCacheDirectory() {
        return getInstance().mCacheDirectory;
    }

    public static long getCacheMaxSize() {
        return getInstance().mCacheMaxSize;
    }

    public static CacheMode getCacheMode() {
        return getInstance().mCacheMode;
    }

    public static long getCacheSize() {
        return getRxCache().getSize();
    }

    public static long getCacheTime() {
        return getInstance().mCacheTime;
    }

    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    public static CookieManger getCookieJar() {
        return getInstance().cookieJar;
    }

    public static Cache getHttpCache() {
        return getInstance().mCache;
    }

    public static CtvitHttp getInstance() {
        testInitialize();
        if (singleton == null) {
            synchronized (CtvitHttp.class) {
                if (singleton == null) {
                    singleton = new CtvitHttp();
                }
            }
        }
        return singleton;
    }

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        return getInstance().loggingInterceptor;
    }

    public static OkHttpClient getOkHttpClient() {
        return getInstance().okHttpClientBuilder.build();
    }

    public static OkHttpClient.Builder getOkHttpClientBuilder() {
        return getInstance().okHttpClientBuilder;
    }

    public static Retrofit getRetrofit() {
        return getInstance().retrofitBuilder.build();
    }

    public static Retrofit.Builder getRetrofitBuilder() {
        return getInstance().retrofitBuilder;
    }

    public static int getRetryCount() {
        return getInstance().mRetryCount;
    }

    public static int getRetryDelay() {
        return getInstance().mRetryDelay;
    }

    public static int getRetryIncreaseDelay() {
        return getInstance().mRetryIncreaseDelay;
    }

    public static RxCache getRxCache() {
        return getInstance().rxCacheBuilder.build();
    }

    public static RxCache.Builder getRxCacheBuilder() {
        return getInstance().rxCacheBuilder;
    }

    public static void init(Application application) {
        sContext = application;
    }

    public static PostRequest post(String str) {
        return new PostRequest(str);
    }

    public static PutRequest put(String str) {
        return new PutRequest(str);
    }

    public static void removeCache(String str) {
        getRxCache().remove(str).compose(RxUtils.io_main()).subscribe(new InterfaceC0446f<Boolean>() { // from class: com.ctvit.network.CtvitHttp.3
            @Override // p014b4.InterfaceC0446f
            public void accept(Boolean bool) {
                C2073a.m2459d("removeCache success!!!");
            }
        }, new InterfaceC0446f<Throwable>() { // from class: com.ctvit.network.CtvitHttp.4
            @Override // p014b4.InterfaceC0446f
            public void accept(Throwable th) {
                C2073a.m2459d("removeCache err!!!");
            }
        });
    }

    private void setHttpLoggingInterceptor() {
        if (C1231b.f2762d) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("", true);
            this.loggingInterceptor = httpLoggingInterceptor;
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
    }

    private static void testInitialize() {
    }

    public CtvitHttp addCallAdapterFactory(CallAdapter.Factory factory) {
        this.retrofitBuilder.addCallAdapterFactory((CallAdapter.Factory) Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    public CtvitHttp addCommonHeaders(HttpHeaders httpHeaders) {
        if (this.mCommonHeaders == null) {
            this.mCommonHeaders = new HttpHeaders();
        }
        this.mCommonHeaders.put(httpHeaders);
        return this;
    }

    public CtvitHttp addCommonParams(HttpParams httpParams) {
        if (this.mCommonParams == null) {
            this.mCommonParams = new HttpParams();
        }
        this.mCommonParams.put(httpParams);
        return this;
    }

    public CtvitHttp addConverterFactory(Converter.Factory factory) {
        this.retrofitBuilder.addConverterFactory((Converter.Factory) Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    public CtvitHttp addInterceptor(Interceptor interceptor) {
        this.okHttpClientBuilder.addInterceptor((Interceptor) Utils.checkNotNull(interceptor, "interceptor == null"));
        return this;
    }

    public CtvitHttp addNetworkInterceptor(Interceptor interceptor) {
        this.okHttpClientBuilder.addNetworkInterceptor((Interceptor) Utils.checkNotNull(interceptor, "interceptor == null"));
        return this;
    }

    public HttpHeaders getCommonHeaders() {
        return this.mCommonHeaders;
    }

    public HttpParams getCommonParams() {
        return this.mCommonParams;
    }

    public CtvitHttp setBaseUrl(String str) {
        this.mBaseUrl = (String) Utils.checkNotNull(str, "baseUrl == null");
        return this;
    }

    public CtvitHttp setCacheDirectory(File file) {
        this.mCacheDirectory = (File) Utils.checkNotNull(file, "directory == null");
        this.rxCacheBuilder.diskDir(file);
        return this;
    }

    public CtvitHttp setCacheDiskConverter(IDiskConverter iDiskConverter) {
        this.rxCacheBuilder.diskConverter((IDiskConverter) Utils.checkNotNull(iDiskConverter, "converter == null"));
        return this;
    }

    public CtvitHttp setCacheMaxSize(long j7) {
        this.mCacheMaxSize = j7;
        return this;
    }

    public CtvitHttp setCacheMode(CacheMode cacheMode) {
        this.mCacheMode = cacheMode;
        return this;
    }

    public CtvitHttp setCacheTime(long j7) {
        if (j7 <= -1) {
            j7 = -1;
        }
        this.mCacheTime = j7;
        return this;
    }

    public CtvitHttp setCacheVersion(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("cacheersion must > 0");
        }
        this.rxCacheBuilder.appVersion(i7);
        return this;
    }

    public CtvitHttp setCallFactory(Call.Factory factory) {
        this.retrofitBuilder.callFactory((Call.Factory) Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    public CtvitHttp setCallbackExecutor(Executor executor) {
        this.retrofitBuilder.callbackExecutor((Executor) Utils.checkNotNull(executor, "executor == null"));
        return this;
    }

    public CtvitHttp setCertificates(InputStream... inputStreamArr) throws UnrecoverableKeyException, NoSuchAlgorithmException, IOException, CertificateException, KeyManagementException {
        HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, inputStreamArr);
        this.okHttpClientBuilder.sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager);
        return this;
    }

    public CtvitHttp setConnectTimeout(long j7) {
        this.okHttpClientBuilder.connectTimeout(j7, TimeUnit.MILLISECONDS);
        return this;
    }

    public CtvitHttp setCookieStore(CookieManger cookieManger) {
        this.cookieJar = cookieManger;
        this.okHttpClientBuilder.cookieJar(cookieManger);
        return this;
    }

    public CtvitHttp setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.okHttpClientBuilder.hostnameVerifier(hostnameVerifier);
        return this;
    }

    public CtvitHttp setHttpCache(Cache cache) {
        this.mCache = cache;
        return this;
    }

    public CtvitHttp setOkclient(OkHttpClient okHttpClient) {
        this.retrofitBuilder.client((OkHttpClient) Utils.checkNotNull(okHttpClient, "client == null"));
        return this;
    }

    public CtvitHttp setOkconnectionPool(ConnectionPool connectionPool) {
        this.okHttpClientBuilder.connectionPool((ConnectionPool) Utils.checkNotNull(connectionPool, "connectionPool == null"));
        return this;
    }

    public CtvitHttp setOkproxy(Proxy proxy) {
        this.okHttpClientBuilder.proxy((Proxy) Utils.checkNotNull(proxy, "proxy == null"));
        return this;
    }

    public CtvitHttp setReadTimeOut(long j7) {
        this.okHttpClientBuilder.readTimeout(j7, TimeUnit.MILLISECONDS);
        return this;
    }

    public CtvitHttp setRetryCount(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("retryCount must > 0");
        }
        this.mRetryCount = i7;
        return this;
    }

    public CtvitHttp setRetryDelay(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("retryDelay must > 0");
        }
        this.mRetryDelay = i7;
        return this;
    }

    public CtvitHttp setRetryIncreaseDelay(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("retryIncreaseDelay must > 0");
        }
        this.mRetryIncreaseDelay = i7;
        return this;
    }

    public CtvitHttp setWriteTimeOut(long j7) {
        this.okHttpClientBuilder.writeTimeout(j7, TimeUnit.MILLISECONDS);
        return this;
    }

    public CtvitHttp setCertificates(InputStream inputStream, String str, InputStream... inputStreamArr) throws UnrecoverableKeyException, NoSuchAlgorithmException, IOException, CertificateException, KeyManagementException {
        HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(inputStream, str, inputStreamArr);
        this.okHttpClientBuilder.sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager);
        return this;
    }
}
