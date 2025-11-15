package retrofit2;

import p009b.C0413b;

/* loaded from: classes.dex */
public class HttpException extends RuntimeException {
    private final int code;
    private final String message;
    private final transient Response<?> response;

    public HttpException(Response<?> response) {
        super(getMessage(response));
        this.code = response.code();
        this.message = response.message();
        this.response = response;
    }

    private static String getMessage(Response<?> response) {
        Utils.checkNotNull(response, "response == null");
        StringBuilder sbM112a = C0413b.m112a("HTTP ");
        sbM112a.append(response.code());
        sbM112a.append(" ");
        sbM112a.append(response.message());
        return sbM112a.toString();
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public Response<?> response() {
        return this.response;
    }
}
