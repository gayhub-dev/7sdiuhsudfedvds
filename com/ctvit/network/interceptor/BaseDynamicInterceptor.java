package com.ctvit.network.interceptor;

import com.ctvit.network.interceptor.BaseDynamicInterceptor;
import com.ctvit.network.utils.HttpUtils;
import com.ctvit.network.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;
import p186x2.C2073a;

/* loaded from: classes.dex */
public abstract class BaseDynamicInterceptor<R extends BaseDynamicInterceptor> implements Interceptor {
    private HttpUrl httpUrl;
    private boolean isSign = false;
    private boolean timeStamp = false;
    private boolean accessToken = false;

    /* JADX WARN: Multi-variable type inference failed */
    private Request addGetParamsSign(Request request) {
        HttpUrl httpUrlUrl = request.url();
        HttpUrl.Builder builderNewBuilder = httpUrlUrl.newBuilder();
        Set<String> setQueryParameterNames = httpUrlUrl.queryParameterNames();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(setQueryParameterNames);
        TreeMap treeMap = new TreeMap();
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            treeMap.put(arrayList.get(i7), (httpUrlUrl.queryParameterValues((String) arrayList.get(i7)) == null || httpUrlUrl.queryParameterValues((String) arrayList.get(i7)).size() <= 0) ? "" : httpUrlUrl.queryParameterValues((String) arrayList.get(i7)).get(0));
        }
        String string = Collections.singletonList(arrayList).toString();
        TreeMap<String, String> treeMapDynamic = dynamic(treeMap);
        Utils.checkNotNull(treeMapDynamic, "newParams==null");
        for (Map.Entry<String, String> entry : treeMapDynamic.entrySet()) {
            if (!string.contains(entry.getKey())) {
                builderNewBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        return request.newBuilder().url(builderNewBuilder.build()).build();
    }

    private Request addPostParamsSign(Request request) {
        if (request.body() instanceof FormBody) {
            FormBody.Builder builder = new FormBody.Builder();
            FormBody formBody = (FormBody) request.body();
            TreeMap<String, String> treeMap = new TreeMap<>();
            for (int i7 = 0; i7 < formBody.size(); i7++) {
                treeMap.put(formBody.name(i7), formBody.value(i7));
            }
            TreeMap<String, String> treeMapDynamic = dynamic(treeMap);
            Utils.checkNotNull(treeMapDynamic, "newParams==null");
            for (Map.Entry<String, String> entry : treeMapDynamic.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            C2073a.m2459d(HttpUtils.createUrlFromParams(this.httpUrl.url().toString(), treeMapDynamic));
            return request.newBuilder().post(builder.build()).build();
        }
        if (!(request.body() instanceof MultipartBody)) {
            return request;
        }
        MultipartBody multipartBody = (MultipartBody) request.body();
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        List<MultipartBody.Part> listParts = multipartBody.parts();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(listParts);
        for (Map.Entry<String, String> entry2 : dynamic(new TreeMap<>()).entrySet()) {
            arrayList.add(MultipartBody.Part.createFormData(entry2.getKey(), entry2.getValue()));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            type.addPart((MultipartBody.Part) it.next());
        }
        return request.newBuilder().post(type.build()).build();
    }

    private String parseUrl(String str) {
        return ("".equals(str) || !str.contains("?")) ? str : str.substring(0, str.indexOf(63));
    }

    public R accessToken(boolean z6) {
        this.accessToken = z6;
        return this;
    }

    public abstract TreeMap<String, String> dynamic(TreeMap<String, String> treeMap);

    public HttpUrl getHttpUrl() {
        return this.httpUrl;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        if (request.method().equals("GET")) {
            this.httpUrl = HttpUrl.parse(parseUrl(request.url().url().toString()));
            request = addGetParamsSign(request);
        } else if (request.method().equals("POST")) {
            this.httpUrl = request.url();
            request = addPostParamsSign(request);
        }
        return chain.proceed(request);
    }

    public boolean isAccessToken() {
        return this.accessToken;
    }

    public boolean isSign() {
        return this.isSign;
    }

    public boolean isTimeStamp() {
        return this.timeStamp;
    }

    public R sign(boolean z6) {
        this.isSign = z6;
        return this;
    }

    public R timeStamp(boolean z6) {
        this.timeStamp = z6;
        return this;
    }
}
