package okhttp3;

import android.arch.lifecycle.C0063n;
import java.io.Closeable;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import p009b.C0413b;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* loaded from: classes.dex */
public final class Response implements Closeable {

    @Nullable
    public final ResponseBody body;

    @Nullable
    private volatile CacheControl cacheControl;

    @Nullable
    public final Response cacheResponse;
    public final int code;

    @Nullable
    public final Handshake handshake;
    public final Headers headers;
    public final String message;

    @Nullable
    public final Response networkResponse;

    @Nullable
    public final Response priorResponse;
    public final Protocol protocol;
    public final long receivedResponseAtMillis;
    public final Request request;
    public final long sentRequestAtMillis;

    public Response(Builder builder) {
        this.request = builder.request;
        this.protocol = builder.protocol;
        this.code = builder.code;
        this.message = builder.message;
        this.handshake = builder.handshake;
        this.headers = builder.headers.build();
        this.body = builder.body;
        this.networkResponse = builder.networkResponse;
        this.cacheResponse = builder.cacheResponse;
        this.priorResponse = builder.priorResponse;
        this.sentRequestAtMillis = builder.sentRequestAtMillis;
        this.receivedResponseAtMillis = builder.receivedResponseAtMillis;
    }

    @Nullable
    public ResponseBody body() {
        return this.body;
    }

    public CacheControl cacheControl() {
        CacheControl cacheControl = this.cacheControl;
        if (cacheControl != null) {
            return cacheControl;
        }
        CacheControl cacheControl2 = CacheControl.parse(this.headers);
        this.cacheControl = cacheControl2;
        return cacheControl2;
    }

    @Nullable
    public Response cacheResponse() {
        return this.cacheResponse;
    }

    public List<Challenge> challenges() {
        String str;
        int i7 = this.code;
        if (i7 == 401) {
            str = "WWW-Authenticate";
        } else {
            if (i7 != 407) {
                return Collections.emptyList();
            }
            str = "Proxy-Authenticate";
        }
        return HttpHeaders.parseChallenges(headers(), str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ResponseBody responseBody = this.body;
        if (responseBody == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        responseBody.close();
    }

    public int code() {
        return this.code;
    }

    @Nullable
    public Handshake handshake() {
        return this.handshake;
    }

    @Nullable
    public String header(String str) {
        return header(str, null);
    }

    public List<String> headers(String str) {
        return this.headers.values(str);
    }

    public boolean isRedirect() {
        int i7 = this.code;
        if (i7 == 307 || i7 == 308) {
            return true;
        }
        switch (i7) {
            case IjkMediaCodecInfo.RANK_SECURE /* 300 */:
            case 301:
            case 302:
            case 303:
                return true;
            default:
                return false;
        }
    }

    public boolean isSuccessful() {
        int i7 = this.code;
        return i7 >= 200 && i7 < 300;
    }

    public String message() {
        return this.message;
    }

    @Nullable
    public Response networkResponse() {
        return this.networkResponse;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public ResponseBody peekBody(long j7) {
        BufferedSource bufferedSourceSource = this.body.source();
        bufferedSourceSource.request(j7);
        Buffer bufferClone = bufferedSourceSource.buffer().clone();
        if (bufferClone.size() > j7) {
            Buffer buffer = new Buffer();
            buffer.write(bufferClone, j7);
            bufferClone.clear();
            bufferClone = buffer;
        }
        return ResponseBody.create(this.body.contentType(), bufferClone.size(), bufferClone);
    }

    @Nullable
    public Response priorResponse() {
        return this.priorResponse;
    }

    public Protocol protocol() {
        return this.protocol;
    }

    public long receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    public Request request() {
        return this.request;
    }

    public long sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Response{protocol=");
        sbM112a.append(this.protocol);
        sbM112a.append(", code=");
        sbM112a.append(this.code);
        sbM112a.append(", message=");
        sbM112a.append(this.message);
        sbM112a.append(", url=");
        sbM112a.append(this.request.url());
        sbM112a.append('}');
        return sbM112a.toString();
    }

    @Nullable
    public String header(String str, @Nullable String str2) {
        String str3 = this.headers.get(str);
        return str3 != null ? str3 : str2;
    }

    public Headers headers() {
        return this.headers;
    }

    public static class Builder {

        @Nullable
        public ResponseBody body;

        @Nullable
        public Response cacheResponse;
        public int code;

        @Nullable
        public Handshake handshake;
        public Headers.Builder headers;
        public String message;

        @Nullable
        public Response networkResponse;

        @Nullable
        public Response priorResponse;

        @Nullable
        public Protocol protocol;
        public long receivedResponseAtMillis;

        @Nullable
        public Request request;
        public long sentRequestAtMillis;

        public Builder() {
            this.code = -1;
            this.headers = new Headers.Builder();
        }

        private void checkPriorResponse(Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        private void checkSupportResponse(String str, Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException(C0063n.m88a(str, ".body != null"));
            }
            if (response.networkResponse != null) {
                throw new IllegalArgumentException(C0063n.m88a(str, ".networkResponse != null"));
            }
            if (response.cacheResponse != null) {
                throw new IllegalArgumentException(C0063n.m88a(str, ".cacheResponse != null"));
            }
            if (response.priorResponse != null) {
                throw new IllegalArgumentException(C0063n.m88a(str, ".priorResponse != null"));
            }
        }

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Builder body(@Nullable ResponseBody responseBody) {
            this.body = responseBody;
            return this;
        }

        public Response build() {
            if (this.request == null) {
                throw new IllegalStateException("request == null");
            }
            if (this.protocol == null) {
                throw new IllegalStateException("protocol == null");
            }
            if (this.code >= 0) {
                if (this.message != null) {
                    return new Response(this);
                }
                throw new IllegalStateException("message == null");
            }
            StringBuilder sbM112a = C0413b.m112a("code < 0: ");
            sbM112a.append(this.code);
            throw new IllegalStateException(sbM112a.toString());
        }

        public Builder cacheResponse(@Nullable Response response) {
            if (response != null) {
                checkSupportResponse("cacheResponse", response);
            }
            this.cacheResponse = response;
            return this;
        }

        public Builder code(int i7) {
            this.code = i7;
            return this;
        }

        public Builder handshake(@Nullable Handshake handshake) {
            this.handshake = handshake;
            return this;
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Builder headers(Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }

        public Builder message(String str) {
            this.message = str;
            return this;
        }

        public Builder networkResponse(@Nullable Response response) {
            if (response != null) {
                checkSupportResponse("networkResponse", response);
            }
            this.networkResponse = response;
            return this;
        }

        public Builder priorResponse(@Nullable Response response) {
            if (response != null) {
                checkPriorResponse(response);
            }
            this.priorResponse = response;
            return this;
        }

        public Builder protocol(Protocol protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder receivedResponseAtMillis(long j7) {
            this.receivedResponseAtMillis = j7;
            return this;
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder request(Request request) {
            this.request = request;
            return this;
        }

        public Builder sentRequestAtMillis(long j7) {
            this.sentRequestAtMillis = j7;
            return this;
        }

        public Builder(Response response) {
            this.code = -1;
            this.request = response.request;
            this.protocol = response.protocol;
            this.code = response.code;
            this.message = response.message;
            this.handshake = response.handshake;
            this.headers = response.headers.newBuilder();
            this.body = response.body;
            this.networkResponse = response.networkResponse;
            this.cacheResponse = response.cacheResponse;
            this.priorResponse = response.priorResponse;
            this.sentRequestAtMillis = response.sentRequestAtMillis;
            this.receivedResponseAtMillis = response.receivedResponseAtMillis;
        }
    }
}
