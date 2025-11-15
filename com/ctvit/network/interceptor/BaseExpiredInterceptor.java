package com.ctvit.network.interceptor;

import android.support.constraint.motion.C0081c;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import com.ctvit.network.utils.HttpUtils;
import java.nio.charset.Charset;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import p186x2.C2073a;

/* loaded from: classes.dex */
public abstract class BaseExpiredInterceptor implements Interceptor {
    private boolean isText(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (mediaType.type() == null || !mediaType.type().equals(NotificationCompat.MessagingStyle.Message.KEY_TEXT)) {
            return mediaType.subtype() != null && mediaType.subtype().equals("json");
        }
        return true;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        Response responseProceed = chain.proceed(request);
        ResponseBody responseBodyBody = responseProceed.body();
        BufferedSource bufferedSourceSource = responseBodyBody.source();
        bufferedSourceSource.request(RecyclerView.FOREVER_NS);
        Buffer buffer = bufferedSourceSource.buffer();
        Charset charset = HttpUtils.UTF8;
        MediaType mediaTypeContentType = responseBodyBody.contentType();
        if (mediaTypeContentType != null) {
            charset = mediaTypeContentType.charset(charset);
        }
        String string = buffer.clone().readString(charset);
        StringBuilder sbM95a = C0081c.m95a("网络拦截器:", string, " host:");
        sbM95a.append(request.url().toString());
        C2073a.m2459d(sbM95a.toString());
        return (isText(mediaTypeContentType) && isResponseExpired(responseProceed, string)) ? responseExpired(chain, string) : responseProceed;
    }

    public abstract boolean isResponseExpired(Response response, String str);

    public abstract Response responseExpired(Interceptor.Chain chain, String str);
}
