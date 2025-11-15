package com.ctvit.network.interceptor;

import android.content.Context;
import android.text.TextUtils;
import com.ctvit.network.model.HttpHeaders;
import okhttp3.Interceptor;
import okhttp3.Response;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class CacheInterceptor implements Interceptor {
    public static final int maxStale = 259200;
    public static final int maxStaleOnline = 60;
    public String cacheControlValue_Offline;
    public String cacheControlValue_Online;
    public Context context;

    public CacheInterceptor(Context context) {
        this(context, String.format("max-age=%d", 60));
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Response responseProceed = chain.proceed(chain.request());
        String strHeader = responseProceed.header("Cache-Control");
        C2073a.m2456a("60s load cache:" + strHeader);
        return (TextUtils.isEmpty(strHeader) || strHeader.contains("no-store") || strHeader.contains("no-cache") || strHeader.contains("must-revalidate") || strHeader.contains("max-age") || strHeader.contains("max-stale")) ? responseProceed.newBuilder().removeHeader(HttpHeaders.HEAD_KEY_PRAGMA).removeHeader("Cache-Control").header("Cache-Control", "public, max-age=259200").build() : responseProceed;
    }

    public CacheInterceptor(Context context, String str) {
        this(context, str, String.format("max-age=%d", Integer.valueOf(maxStale)));
    }

    public CacheInterceptor(Context context, String str, String str2) {
        this.context = context;
        this.cacheControlValue_Offline = str;
        this.cacheControlValue_Online = str2;
    }
}
