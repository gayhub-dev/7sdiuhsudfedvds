package com.ctvit.network.interceptor;

import okhttp3.Interceptor;
import okhttp3.Response;

/* loaded from: classes.dex */
public class NoCacheInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        return chain.proceed(chain.request().newBuilder().header("Cache-Control", "no-cache").build()).newBuilder().header("Cache-Control", "no-cache").build();
    }
}
