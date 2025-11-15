package com.ctvit.network.exception;

import android.net.ParseException;
import android.support.constraint.motion.C0080b;
import android.support.constraint.solver.C0084a;
import android.text.TextUtils;
import com.ctvit.network.model.ApiResult;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import p009b.C0413b;
import p186x2.C2073a;
import retrofit2.HttpException;

/* loaded from: classes.dex */
public class ApiException extends Exception {
    private final int code;
    private String displayMessage;
    private String message;

    public static class ERROR {
        public static final int CAST_ERROR = 1007;
        public static final int HTTP_ERROR = 1003;
        public static final int INVOKE_ERROR = 1006;
        public static final int NETWORD_ERROR = 1002;
        public static final int NULLPOINTER_EXCEPTION = 1010;
        public static final int PARSE_ERROR = 1001;
        public static final int REQUEST_CANCEL = 1008;
        public static final int SSL_ERROR = 1004;
        public static final int TIMEOUT_ERROR = 1005;
        public static final int UNKNOWN = 1000;
        public static final int UNKNOWNHOST_ERROR = 1009;
    }

    public ApiException(Throwable th, int i7) {
        super(th);
        this.code = i7;
        this.message = th.getMessage();
    }

    public static ApiException handleException(Throwable th) {
        String message;
        if (th instanceof HttpException) {
            HttpException httpException = (HttpException) th;
            try {
                message = httpException.response().errorBody().string();
            } catch (Exception e7) {
                C2073a.m2458c(e7);
                message = "";
            }
            ApiException apiException = new ApiException(httpException, httpException.code());
            if (TextUtils.isEmpty(message)) {
                message = httpException.getMessage();
            }
            apiException.message = message;
            return apiException;
        }
        if (th instanceof ServerException) {
            ServerException serverException = (ServerException) th;
            ApiException apiException2 = new ApiException(serverException, serverException.getErrCode());
            apiException2.message = serverException.getMessage();
            return apiException2;
        }
        if ((th instanceof JsonParseException) || (th instanceof JSONException) || (th instanceof JsonSyntaxException) || (th instanceof JsonSerializer) || (th instanceof NotSerializableException) || (th instanceof ParseException)) {
            ApiException apiException3 = new ApiException(th, 1001);
            apiException3.message = "解析错误";
            return apiException3;
        }
        if (th instanceof ClassCastException) {
            ApiException apiException4 = new ApiException(th, 1007);
            apiException4.message = "类型转换错误";
            return apiException4;
        }
        if (th instanceof ConnectException) {
            ApiException apiException5 = new ApiException(th, 1002);
            apiException5.message = "连接失败";
            return apiException5;
        }
        if (th instanceof SSLHandshakeException) {
            ApiException apiException6 = new ApiException(th, 1004);
            apiException6.message = "证书验证失败";
            return apiException6;
        }
        if (th instanceof ConnectTimeoutException) {
            ApiException apiException7 = new ApiException(th, 1005);
            apiException7.message = "连接超时";
            return apiException7;
        }
        if (th instanceof SocketTimeoutException) {
            ApiException apiException8 = new ApiException(th, 1005);
            apiException8.message = "连接超时";
            return apiException8;
        }
        if (th instanceof UnknownHostException) {
            ApiException apiException9 = new ApiException(th, 1009);
            apiException9.message = "无法解析该域名";
            return apiException9;
        }
        if (th instanceof NullPointerException) {
            ApiException apiException10 = new ApiException(th, 1010);
            apiException10.message = "NullPointerException";
            return apiException10;
        }
        ApiException apiException11 = new ApiException(th, 1000);
        apiException11.message = "未知错误";
        return apiException11;
    }

    public static boolean isOk(ApiResult apiResult) {
        return apiResult != null && apiResult.isOk();
    }

    public int getCode() {
        return this.code;
    }

    public String getDisplayMessage() {
        return this.displayMessage;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public void setDisplayMessage(String str) {
        this.displayMessage = C0084a.m96a(C0080b.m94a(str, "(code:"), this.code, ")");
    }

    public void setMessage(String str) {
        this.message = str;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("code=");
        sbM112a.append(getCode());
        sbM112a.append(" message");
        sbM112a.append(getMessage());
        sbM112a.append(" displayMessage");
        sbM112a.append(getDisplayMessage());
        return sbM112a.toString();
    }
}
