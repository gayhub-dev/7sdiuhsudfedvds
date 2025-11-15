package com.ctvit.network.interceptor;

import com.ctvit.network.model.HttpHeaders;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class HeadersInterceptor implements Interceptor {
    private HttpHeaders headers;

    public HeadersInterceptor(HttpHeaders httpHeaders) {
        this.headers = httpHeaders;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request.Builder builderNewBuilder = chain.request().newBuilder();
        if (this.headers.headersMap.isEmpty()) {
            return chain.proceed(builderNewBuilder.build());
        }
        try {
            for (Map.Entry<String, String> entry : this.headers.headersMap.entrySet()) {
                builderNewBuilder.header(entry.getKey(), entry.getValue()).build();
            }
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
        return chain.proceed(builderNewBuilder.build());
    }
}
