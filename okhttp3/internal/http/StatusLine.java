package okhttp3.internal.http;

import android.arch.lifecycle.C0063n;
import java.net.ProtocolException;
import okhttp3.Protocol;
import okhttp3.Response;

/* loaded from: classes.dex */
public final class StatusLine {
    public static final int HTTP_CONTINUE = 100;
    public static final int HTTP_PERM_REDIRECT = 308;
    public static final int HTTP_TEMP_REDIRECT = 307;
    public final int code;
    public final String message;
    public final Protocol protocol;

    public StatusLine(Protocol protocol, int i7, String str) {
        this.protocol = protocol;
        this.code = i7;
        this.message = str;
    }

    public static StatusLine get(Response response) {
        return new StatusLine(response.protocol(), response.code(), response.message());
    }

    public static StatusLine parse(String str) throws ProtocolException, NumberFormatException {
        Protocol protocol;
        String strSubstring;
        int i7 = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException(C0063n.m88a("Unexpected status line: ", str));
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt == 0) {
                protocol = Protocol.HTTP_1_0;
            } else {
                if (iCharAt != 1) {
                    throw new ProtocolException(C0063n.m88a("Unexpected status line: ", str));
                }
                protocol = Protocol.HTTP_1_1;
            }
        } else {
            if (!str.startsWith("ICY ")) {
                throw new ProtocolException(C0063n.m88a("Unexpected status line: ", str));
            }
            protocol = Protocol.HTTP_1_0;
            i7 = 4;
        }
        int i8 = i7 + 3;
        if (str.length() < i8) {
            throw new ProtocolException(C0063n.m88a("Unexpected status line: ", str));
        }
        try {
            int i9 = Integer.parseInt(str.substring(i7, i8));
            if (str.length() <= i8) {
                strSubstring = "";
            } else {
                if (str.charAt(i8) != ' ') {
                    throw new ProtocolException(C0063n.m88a("Unexpected status line: ", str));
                }
                strSubstring = str.substring(i7 + 4);
            }
            return new StatusLine(protocol, i9, strSubstring);
        } catch (NumberFormatException unused) {
            throw new ProtocolException(C0063n.m88a("Unexpected status line: ", str));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.code);
        if (this.message != null) {
            sb.append(' ');
            sb.append(this.message);
        }
        return sb.toString();
    }
}
