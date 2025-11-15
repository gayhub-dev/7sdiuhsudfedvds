package com.ctvit.network.interceptor;

import android.content.Context;
import com.ctvit.network.model.HttpHeaders;
import com.ctvit.network.utils.Utils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import p009b.C0413b;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class CacheInterceptorOffline extends CacheInterceptor {
    public CacheInterceptorOffline(Context context) {
        super(context);
    }

    @Override // com.ctvit.network.interceptor.CacheInterceptor, okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        if (Utils.isNetworkAvailable(this.context)) {
            return chain.proceed(request);
        }
        StringBuilder sbM112a = C0413b.m112a(" no network load cache:");
        sbM112a.append(request.cacheControl().toString());
        C2073a.m2459d(sbM112a.toString());
        Response.Builder builderRemoveHeader = chain.proceed(request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()).newBuilder().removeHeader(HttpHeaders.HEAD_KEY_PRAGMA).removeHeader("Cache-Control");
        StringBuilder sbM112a2 = C0413b.m112a("public, only-if-cached, ");
        sbM112a2.append(this.cacheControlValue_Offline);
        return builderRemoveHeader.header("Cache-Control", sbM112a2.toString()).build();
    }

    public CacheInterceptorOffline(Context context, String str) {
        super(context, str);
    }

    public CacheInterceptorOffline(Context context, String str, String str2) {
        super(context, str, str2);
    }
}
