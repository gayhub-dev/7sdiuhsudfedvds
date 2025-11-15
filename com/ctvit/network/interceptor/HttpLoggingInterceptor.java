package com.ctvit.network.interceptor;

import android.support.v4.app.NotificationCompat;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import p009b.C0413b;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private boolean isLogEnable;
    private volatile Level level;
    private String tag;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public HttpLoggingInterceptor(String str) {
        this.level = Level.NONE;
        this.isLogEnable = false;
        this.tag = str;
    }

    private void bodyToString(Request request) {
        try {
            Request requestBuild = request.newBuilder().build();
            Buffer buffer = new Buffer();
            requestBuild.body().writeTo(buffer);
            Charset charset = UTF8;
            MediaType mediaTypeContentType = requestBuild.body().contentType();
            C2073a.m2459d("\tbody:" + URLDecoder.decode(replacer(buffer.readString(mediaTypeContentType != null ? mediaTypeContentType.charset(charset) : charset)), charset.name()));
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    public static boolean isPlaintext(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (mediaType.type() != null && mediaType.type().equals(NotificationCompat.MessagingStyle.Message.KEY_TEXT)) {
            return true;
        }
        String strSubtype = mediaType.subtype();
        if (strSubtype != null) {
            String lowerCase = strSubtype.toLowerCase();
            if (lowerCase.contains("x-www-form-urlencoded") || lowerCase.contains("json") || lowerCase.contains("xml") || lowerCase.contains("html")) {
                return true;
            }
        }
        return false;
    }

    private void logForRequest(Request request, Connection connection) {
        StringBuilder sb;
        C2073a.m2459d("-------------------------------request-------------------------------");
        Level level = this.level;
        Level level2 = Level.BODY;
        boolean z6 = level == level2;
        boolean z7 = this.level == level2 || this.level == Level.HEADERS;
        RequestBody requestBodyBody = request.body();
        boolean z8 = requestBodyBody != null;
        try {
            try {
                C2073a.m2459d("--> " + request.method() + ' ' + URLDecoder.decode(request.url().url().toString(), UTF8.name()) + ' ' + (connection != null ? connection.protocol() : Protocol.HTTP_1_1));
                if (z7) {
                    Headers headers = request.headers();
                    int size = headers.size();
                    for (int i7 = 0; i7 < size; i7++) {
                        C2073a.m2459d("\t" + headers.name(i7) + ": " + headers.value(i7));
                    }
                    if (z6 && z8) {
                        if (isPlaintext(requestBodyBody.contentType())) {
                            bodyToString(request);
                        } else {
                            C2073a.m2459d("body: maybe [file part] , too large too print , ignored!");
                        }
                    }
                }
                sb = new StringBuilder();
            } catch (Exception e7) {
                m527e(e7);
                sb = new StringBuilder();
            }
            sb.append("--> END ");
            sb.append(request.method());
            C2073a.m2459d(sb.toString());
        } catch (Throwable th) {
            StringBuilder sbM112a = C0413b.m112a("--> END ");
            sbM112a.append(request.method());
            C2073a.m2459d(sbM112a.toString());
            throw th;
        }
    }

    private Response logForResponse(Response response, long j7) {
        C2073a.m2459d("-------------------------------response-------------------------------");
        Response responseBuild = response.newBuilder().build();
        ResponseBody responseBodyBody = responseBuild.body();
        Level level = this.level;
        Level level2 = Level.BODY;
        boolean z6 = true;
        boolean z7 = level == level2;
        if (this.level != level2 && this.level != Level.HEADERS) {
            z6 = false;
        }
        try {
            try {
                C2073a.m2459d("<-- " + responseBuild.code() + ' ' + responseBuild.message() + ' ' + URLDecoder.decode(responseBuild.request().url().url().toString(), UTF8.name()) + " (" + j7 + "ms）");
                if (z6) {
                    Headers headers = responseBuild.headers();
                    int size = headers.size();
                    for (int i7 = 0; i7 < size; i7++) {
                        C2073a.m2459d("\t" + headers.name(i7) + ": " + headers.value(i7));
                    }
                    if (z7 && HttpHeaders.hasBody(responseBuild)) {
                        if (isPlaintext(responseBodyBody.contentType())) {
                            String strString = responseBodyBody.string();
                            C2073a.m2459d("\tbody:" + strString);
                            return response.newBuilder().body(ResponseBody.create(responseBodyBody.contentType(), strString)).build();
                        }
                        C2073a.m2459d("body: maybe [file part] , too large too print , ignored!");
                    }
                }
            } catch (Exception e7) {
                m527e(e7);
            }
            return response;
        } finally {
            C2073a.m2459d("<-- END HTTP");
        }
    }

    private String replacer(String str) {
        try {
            return URLDecoder.decode(str.replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B"), "utf-8");
        } catch (Exception e7) {
            C2073a.m2458c(e7);
            return str;
        }
    }

    /* renamed from: e */
    public void m527e(Throwable th) {
        C2073a.m2458c(th);
    }

    public Level getLevel() {
        return this.level;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        Request request = chain.request();
        if (this.level == Level.NONE) {
            return chain.proceed(request);
        }
        logForRequest(request, chain.connection());
        long jNanoTime = System.nanoTime();
        try {
            return logForResponse(chain.proceed(request), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime));
        } catch (Exception e7) {
            C2073a.m2458c(e7);
            throw e7;
        }
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        if (level == null) {
            this.level = Level.NONE;
        } else {
            this.level = level;
        }
        return this;
    }

    public HttpLoggingInterceptor(String str, boolean z6) {
        this.level = Level.NONE;
        this.isLogEnable = false;
        this.tag = str;
        this.isLogEnable = z6;
    }
}
