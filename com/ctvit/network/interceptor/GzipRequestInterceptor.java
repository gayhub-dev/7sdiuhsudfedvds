package com.ctvit.network.interceptor;

import com.ctvit.network.model.HttpHeaders;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/* loaded from: classes.dex */
public class GzipRequestInterceptor implements Interceptor {
    private RequestBody gzip(final RequestBody requestBody) {
        return new RequestBody() { // from class: com.ctvit.network.interceptor.GzipRequestInterceptor.1
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return -1L;
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return requestBody.contentType();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) {
                BufferedSink bufferedSinkBuffer = Okio.buffer(new GzipSink(bufferedSink));
                requestBody.writeTo(bufferedSinkBuffer);
                bufferedSinkBuffer.close();
            }
        };
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        return (request.body() == null || request.header("Content-Encoding") != null) ? chain.proceed(request) : chain.proceed(request.newBuilder().header(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip").method(request.method(), gzip(request.body())).build());
    }
}
