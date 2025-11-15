package okhttp3.internal.cache;

import com.ctvit.network.model.HttpHeaders;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* loaded from: classes.dex */
public final class CacheInterceptor implements Interceptor {
    public final InternalCache cache;

    /* renamed from: okhttp3.internal.cache.CacheInterceptor$1 */
    public class C16151 implements Source {
        public boolean cacheRequestClosed;
        public final /* synthetic */ BufferedSink val$cacheBody;
        public final /* synthetic */ CacheRequest val$cacheRequest;
        public final /* synthetic */ BufferedSource val$source;

        public C16151(BufferedSource bufferedSource, CacheRequest cacheRequest, BufferedSink bufferedSink) {
            bufferedSource = bufferedSource;
            cacheRequest = cacheRequest;
            bufferedSink = bufferedSink;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                this.cacheRequestClosed = true;
                cacheRequest.abort();
            }
            bufferedSource.close();
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j7) throws IOException {
            try {
                long j8 = bufferedSource.read(buffer, j7);
                if (j8 != -1) {
                    buffer.copyTo(bufferedSink.buffer(), buffer.size() - j8, j8);
                    bufferedSink.emitCompleteSegments();
                    return j8;
                }
                if (!this.cacheRequestClosed) {
                    this.cacheRequestClosed = true;
                    bufferedSink.close();
                }
                return -1L;
            } catch (IOException e7) {
                if (!this.cacheRequestClosed) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                throw e7;
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return bufferedSource.timeout();
        }
    }

    public CacheInterceptor(InternalCache internalCache) {
        this.cache = internalCache;
    }

    private Response cacheWritingResponse(CacheRequest cacheRequest, Response response) {
        Sink sinkBody;
        if (cacheRequest == null || (sinkBody = cacheRequest.body()) == null) {
            return response;
        }
        return response.newBuilder().body(new RealResponseBody(response.header("Content-Type"), response.body().contentLength(), Okio.buffer(new Source() { // from class: okhttp3.internal.cache.CacheInterceptor.1
            public boolean cacheRequestClosed;
            public final /* synthetic */ BufferedSink val$cacheBody;
            public final /* synthetic */ CacheRequest val$cacheRequest;
            public final /* synthetic */ BufferedSource val$source;

            public C16151(BufferedSource bufferedSource, CacheRequest cacheRequest2, BufferedSink bufferedSink) {
                bufferedSource = bufferedSource;
                cacheRequest = cacheRequest2;
                bufferedSink = bufferedSink;
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                bufferedSource.close();
            }

            @Override // okio.Source
            public long read(Buffer buffer, long j7) throws IOException {
                try {
                    long j8 = bufferedSource.read(buffer, j7);
                    if (j8 != -1) {
                        buffer.copyTo(bufferedSink.buffer(), buffer.size() - j8, j8);
                        bufferedSink.emitCompleteSegments();
                        return j8;
                    }
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        bufferedSink.close();
                    }
                    return -1L;
                } catch (IOException e7) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e7;
                }
            }

            @Override // okio.Source
            public Timeout timeout() {
                return bufferedSource.timeout();
            }
        }))).build();
    }

    private static Headers combine(Headers headers, Headers headers2) {
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i7 = 0; i7 < size; i7++) {
            String strName = headers.name(i7);
            String strValue = headers.value(i7);
            if ((!"Warning".equalsIgnoreCase(strName) || !strValue.startsWith(DiskLruCache.VERSION_1)) && (isContentSpecificHeader(strName) || !isEndToEnd(strName) || headers2.get(strName) == null)) {
                Internal.instance.addLenient(builder, strName, strValue);
            }
        }
        int size2 = headers2.size();
        for (int i8 = 0; i8 < size2; i8++) {
            String strName2 = headers2.name(i8);
            if (!isContentSpecificHeader(strName2) && isEndToEnd(strName2)) {
                Internal.instance.addLenient(builder, strName2, headers2.value(i8));
            }
        }
        return builder.build();
    }

    public static boolean isContentSpecificHeader(String str) {
        return "Content-Length".equalsIgnoreCase(str) || "Content-Encoding".equalsIgnoreCase(str) || "Content-Type".equalsIgnoreCase(str);
    }

    public static boolean isEndToEnd(String str) {
        return (HttpHeaders.HEAD_KEY_CONNECTION.equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    private static Response stripBody(Response response) {
        return (response == null || response.body() == null) ? response : response.newBuilder().body(null).build();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        InternalCache internalCache = this.cache;
        Response response = internalCache != null ? internalCache.get(chain.request()) : null;
        CacheStrategy cacheStrategy = new CacheStrategy.Factory(System.currentTimeMillis(), chain.request(), response).get();
        Request request = cacheStrategy.networkRequest;
        Response response2 = cacheStrategy.cacheResponse;
        InternalCache internalCache2 = this.cache;
        if (internalCache2 != null) {
            internalCache2.trackResponse(cacheStrategy);
        }
        if (response != null && response2 == null) {
            Util.closeQuietly(response.body());
        }
        if (request == null && response2 == null) {
            return new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
        }
        if (request == null) {
            return response2.newBuilder().cacheResponse(stripBody(response2)).build();
        }
        try {
            Response responseProceed = chain.proceed(request);
            if (responseProceed == null && response != null) {
            }
            if (response2 != null) {
                if (responseProceed.code() == 304) {
                    Response responseBuild = response2.newBuilder().headers(combine(response2.headers(), responseProceed.headers())).sentRequestAtMillis(responseProceed.sentRequestAtMillis()).receivedResponseAtMillis(responseProceed.receivedResponseAtMillis()).cacheResponse(stripBody(response2)).networkResponse(stripBody(responseProceed)).build();
                    responseProceed.body().close();
                    this.cache.trackConditionalCacheHit();
                    this.cache.update(response2, responseBuild);
                    return responseBuild;
                }
                Util.closeQuietly(response2.body());
            }
            Response responseBuild2 = responseProceed.newBuilder().cacheResponse(stripBody(response2)).networkResponse(stripBody(responseProceed)).build();
            if (this.cache != null) {
                if (okhttp3.internal.http.HttpHeaders.hasBody(responseBuild2) && CacheStrategy.isCacheable(responseBuild2, request)) {
                    return cacheWritingResponse(this.cache.put(responseBuild2), responseBuild2);
                }
                if (HttpMethod.invalidatesCache(request.method())) {
                    try {
                        this.cache.remove(request);
                    } catch (IOException unused) {
                    }
                }
            }
            return responseBuild2;
        } finally {
            if (response != null) {
                Util.closeQuietly(response.body());
            }
        }
    }
}
