package retrofit2;

import android.support.constraint.motion.C0079a;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes.dex */
public final class Response<T> {

    @Nullable
    private final T body;

    @Nullable
    private final ResponseBody errorBody;
    private final okhttp3.Response rawResponse;

    private Response(okhttp3.Response response, @Nullable T t6, @Nullable ResponseBody responseBody) {
        this.rawResponse = response;
        this.body = t6;
        this.errorBody = responseBody;
    }

    public static <T> Response<T> error(int i7, ResponseBody responseBody) {
        if (i7 >= 400) {
            return error(responseBody, new Response.Builder().code(i7).message("Response.error()").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
        }
        throw new IllegalArgumentException(C0079a.m93a("code < 400: ", i7));
    }

    public static <T> Response<T> success(@Nullable T t6) {
        return success(t6, new Response.Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
    }

    @Nullable
    public T body() {
        return this.body;
    }

    public int code() {
        return this.rawResponse.code();
    }

    @Nullable
    public ResponseBody errorBody() {
        return this.errorBody;
    }

    public Headers headers() {
        return this.rawResponse.headers();
    }

    public boolean isSuccessful() {
        return this.rawResponse.isSuccessful();
    }

    public String message() {
        return this.rawResponse.message();
    }

    public okhttp3.Response raw() {
        return this.rawResponse;
    }

    public String toString() {
        return this.rawResponse.toString();
    }

    public static <T> Response<T> success(int i7, @Nullable T t6) {
        if (i7 >= 200 && i7 < 300) {
            return success(t6, new Response.Builder().code(i7).message("Response.success()").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
        }
        throw new IllegalArgumentException(C0079a.m93a("code < 200 or >= 300: ", i7));
    }

    public static <T> Response<T> error(ResponseBody responseBody, okhttp3.Response response) {
        Utils.checkNotNull(responseBody, "body == null");
        Utils.checkNotNull(response, "rawResponse == null");
        if (!response.isSuccessful()) {
            return new Response<>(response, null, responseBody);
        }
        throw new IllegalArgumentException("rawResponse should not be successful response");
    }

    public static <T> Response<T> success(@Nullable T t6, Headers headers) {
        Utils.checkNotNull(headers, "headers == null");
        return success(t6, new Response.Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).headers(headers).request(new Request.Builder().url("http://localhost/").build()).build());
    }

    public static <T> Response<T> success(@Nullable T t6, okhttp3.Response response) {
        Utils.checkNotNull(response, "rawResponse == null");
        if (response.isSuccessful()) {
            return new Response<>(response, t6, null);
        }
        throw new IllegalArgumentException("rawResponse must be successful response");
    }
}
