package okhttp3;

import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class Request {

    @Nullable
    public final RequestBody body;

    @Nullable
    private volatile CacheControl cacheControl;
    public final Headers headers;
    public final String method;
    public final Map<Class<?>, Object> tags;
    public final HttpUrl url;

    public static class Builder {

        @Nullable
        public RequestBody body;
        public Headers.Builder headers;
        public String method;
        public Map<Class<?>, Object> tags;

        @Nullable
        public HttpUrl url;

        public Builder() {
            this.tags = Collections.emptyMap();
            this.method = "GET";
            this.headers = new Headers.Builder();
        }

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Request build() {
            if (this.url != null) {
                return new Request(this);
            }
            throw new IllegalStateException("url == null");
        }

        public Builder cacheControl(CacheControl cacheControl) {
            String string = cacheControl.toString();
            return string.isEmpty() ? removeHeader("Cache-Control") : header("Cache-Control", string);
        }

        public Builder delete(@Nullable RequestBody requestBody) {
            return method("DELETE", requestBody);
        }

        public Builder get() {
            return method("GET", null);
        }

        public Builder head() {
            return method("HEAD", null);
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Builder headers(Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }

        public Builder method(String str, @Nullable RequestBody requestBody) {
            Objects.requireNonNull(str, "method == null");
            if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            }
            if (requestBody != null && !HttpMethod.permitsRequestBody(str)) {
                throw new IllegalArgumentException(C0096a.m97a("method ", str, " must not have a request body."));
            }
            if (requestBody == null && HttpMethod.requiresRequestBody(str)) {
                throw new IllegalArgumentException(C0096a.m97a("method ", str, " must have a request body."));
            }
            this.method = str;
            this.body = requestBody;
            return this;
        }

        public Builder patch(RequestBody requestBody) {
            return method("PATCH", requestBody);
        }

        public Builder post(RequestBody requestBody) {
            return method("POST", requestBody);
        }

        public Builder put(RequestBody requestBody) {
            return method("PUT", requestBody);
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder tag(@Nullable Object obj) {
            return tag(Object.class, obj);
        }

        public Builder url(HttpUrl httpUrl) {
            Objects.requireNonNull(httpUrl, "url == null");
            this.url = httpUrl;
            return this;
        }

        public Builder delete() {
            return delete(Util.EMPTY_REQUEST);
        }

        public <T> Builder tag(Class<? super T> cls, @Nullable T t6) {
            Objects.requireNonNull(cls, "type == null");
            if (t6 == null) {
                this.tags.remove(cls);
            } else {
                if (this.tags.isEmpty()) {
                    this.tags = new LinkedHashMap();
                }
                this.tags.put(cls, cls.cast(t6));
            }
            return this;
        }

        public Builder url(String str) {
            Objects.requireNonNull(str, "url == null");
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                StringBuilder sbM112a = C0413b.m112a("http:");
                sbM112a.append(str.substring(3));
                str = sbM112a.toString();
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                StringBuilder sbM112a2 = C0413b.m112a("https:");
                sbM112a2.append(str.substring(4));
                str = sbM112a2.toString();
            }
            return url(HttpUrl.get(str));
        }

        public Builder(Request request) {
            Map<Class<?>, Object> linkedHashMap;
            this.tags = Collections.emptyMap();
            this.url = request.url;
            this.method = request.method;
            this.body = request.body;
            if (request.tags.isEmpty()) {
                linkedHashMap = Collections.emptyMap();
            } else {
                linkedHashMap = new LinkedHashMap<>(request.tags);
            }
            this.tags = linkedHashMap;
            this.headers = request.headers.newBuilder();
        }

        public Builder url(URL url) {
            Objects.requireNonNull(url, "url == null");
            return url(HttpUrl.get(url.toString()));
        }
    }

    public Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers.build();
        this.body = builder.body;
        this.tags = Util.immutableMap(builder.tags);
    }

    @Nullable
    public RequestBody body() {
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
    public String header(String str) {
        return this.headers.get(str);
    }

    public Headers headers() {
        return this.headers;
    }

    public boolean isHttps() {
        return this.url.isHttps();
    }

    public String method() {
        return this.method;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    @Nullable
    public Object tag() {
        return tag(Object.class);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Request{method=");
        sbM112a.append(this.method);
        sbM112a.append(", url=");
        sbM112a.append(this.url);
        sbM112a.append(", tags=");
        sbM112a.append(this.tags);
        sbM112a.append('}');
        return sbM112a.toString();
    }

    public HttpUrl url() {
        return this.url;
    }

    public List<String> headers(String str) {
        return this.headers.values(str);
    }

    @Nullable
    public <T> T tag(Class<? extends T> cls) {
        return cls.cast(this.tags.get(cls));
    }
}
