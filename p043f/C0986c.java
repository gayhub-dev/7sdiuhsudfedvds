package p043f;

import okhttp3.internal.http.StatusLine;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* renamed from: f.c */
/* loaded from: classes.dex */
public /* synthetic */ class C0986c {

    /* renamed from: a */
    public static /* synthetic */ int[] f1818a;

    /* renamed from: a */
    public static synchronized /* synthetic */ int[] m948a() {
        if (f1818a == null) {
            f1818a = m951d(47);
        }
        return f1818a;
    }

    /* renamed from: b */
    public static /* synthetic */ boolean m949b(int i7, int i8) {
        if (i7 != 0) {
            return i7 == i8;
        }
        throw null;
    }

    /* renamed from: c */
    public static /* synthetic */ int m950c(int i7) {
        if (i7 != 0) {
            return i7 - 1;
        }
        throw null;
    }

    /* renamed from: d */
    public static /* synthetic */ int[] m951d(int i7) {
        int[] iArr = new int[i7];
        int i8 = 0;
        while (i8 < i7) {
            int i9 = i8 + 1;
            iArr[i8] = i9;
            i8 = i9;
        }
        return iArr;
    }

    /* renamed from: e */
    public static /* synthetic */ String m952e(int i7) {
        if (i7 == 1) {
            return "FORWARD";
        }
        if (i7 == 2) {
            return "INCLUDE";
        }
        if (i7 == 3) {
            return "REQUEST";
        }
        if (i7 == 4) {
            return "ASYNC";
        }
        if (i7 == 5) {
            return "ERROR";
        }
        throw null;
    }

    /* renamed from: f */
    public static /* synthetic */ int m953f(int i7) {
        if (i7 == 1) {
            return 100;
        }
        if (i7 == 2) {
            return 101;
        }
        if (i7 == 3) {
            return 102;
        }
        if (i7 == 4) {
            return 200;
        }
        if (i7 == 5) {
            return 201;
        }
        if (i7 == 6) {
            return 202;
        }
        if (i7 == 7) {
            return 203;
        }
        if (i7 == 8) {
            return 204;
        }
        if (i7 == 9) {
            return 205;
        }
        if (i7 == 10) {
            return 206;
        }
        if (i7 == 11) {
            return 207;
        }
        if (i7 == 12) {
            return IjkMediaCodecInfo.RANK_SECURE;
        }
        if (i7 == 13) {
            return 301;
        }
        if (i7 == 14 || i7 == 15) {
            return 302;
        }
        if (i7 == 16) {
            return 303;
        }
        if (i7 == 17) {
            return 304;
        }
        if (i7 == 18) {
            return 305;
        }
        if (i7 == 19) {
            return StatusLine.HTTP_TEMP_REDIRECT;
        }
        if (i7 == 20) {
            return 400;
        }
        if (i7 == 21) {
            return 401;
        }
        if (i7 == 22) {
            return 402;
        }
        if (i7 == 23) {
            return 403;
        }
        if (i7 == 24) {
            return 404;
        }
        if (i7 == 25) {
            return 405;
        }
        if (i7 == 26) {
            return 406;
        }
        if (i7 == 27) {
            return 407;
        }
        if (i7 == 28) {
            return 408;
        }
        if (i7 == 29) {
            return 409;
        }
        if (i7 == 30) {
            return 410;
        }
        if (i7 == 31) {
            return 411;
        }
        if (i7 == 32) {
            return 412;
        }
        if (i7 == 33) {
            return 413;
        }
        if (i7 == 34) {
            return 414;
        }
        if (i7 == 35) {
            return 415;
        }
        if (i7 == 36) {
            return 416;
        }
        if (i7 == 37) {
            return 417;
        }
        if (i7 == 38) {
            return 422;
        }
        if (i7 == 39) {
            return 423;
        }
        if (i7 == 40) {
            return 424;
        }
        if (i7 == 41) {
            return 500;
        }
        if (i7 == 42) {
            return 501;
        }
        if (i7 == 43) {
            return 502;
        }
        if (i7 == 44) {
            return 503;
        }
        if (i7 == 45) {
            return 504;
        }
        if (i7 == 46) {
            return 505;
        }
        if (i7 == 47) {
            return 507;
        }
        throw null;
    }

    /* renamed from: g */
    public static /* synthetic */ String m954g(int i7) {
        if (i7 == 1) {
            return "Continue";
        }
        if (i7 == 2) {
            return "Switching Protocols";
        }
        if (i7 == 3) {
            return "Processing";
        }
        if (i7 == 4) {
            return "OK";
        }
        if (i7 == 5) {
            return "Created";
        }
        if (i7 == 6) {
            return "Accepted";
        }
        if (i7 == 7) {
            return "Non Authoritative Information";
        }
        if (i7 == 8) {
            return "No Content";
        }
        if (i7 == 9) {
            return "Reset Content";
        }
        if (i7 == 10) {
            return "Partial Content";
        }
        if (i7 == 11) {
            return "Multi-Status";
        }
        if (i7 == 12) {
            return "Multiple Choices";
        }
        if (i7 == 13) {
            return "Moved Permanently";
        }
        if (i7 == 14) {
            return "Moved Temporarily";
        }
        if (i7 == 15) {
            return "Found";
        }
        if (i7 == 16) {
            return "See Other";
        }
        if (i7 == 17) {
            return "Not Modified";
        }
        if (i7 == 18) {
            return "Use Proxy";
        }
        if (i7 == 19) {
            return "Temporary Redirect";
        }
        if (i7 == 20) {
            return "Bad Request";
        }
        if (i7 == 21) {
            return "Unauthorized";
        }
        if (i7 == 22) {
            return "Payment Required";
        }
        if (i7 == 23) {
            return "Forbidden";
        }
        if (i7 == 24) {
            return "Not Found";
        }
        if (i7 == 25) {
            return "Method Not Allowed";
        }
        if (i7 == 26) {
            return "Not Acceptable";
        }
        if (i7 == 27) {
            return "Proxy Authentication Required";
        }
        if (i7 == 28) {
            return "Request Timeout";
        }
        if (i7 == 29) {
            return "Conflict";
        }
        if (i7 == 30) {
            return "Gone";
        }
        if (i7 == 31) {
            return "Length Required";
        }
        if (i7 == 32) {
            return "Precondition Failed";
        }
        if (i7 == 33) {
            return "Request Entity Too Large";
        }
        if (i7 == 34) {
            return "Request-URI Too Long";
        }
        if (i7 == 35) {
            return "Unsupported Media Type";
        }
        if (i7 == 36) {
            return "Requested Range Not Satisfiable";
        }
        if (i7 == 37) {
            return "Expectation Failed";
        }
        if (i7 == 38) {
            return "Unprocessable Entity";
        }
        if (i7 == 39) {
            return "Locked";
        }
        if (i7 == 40) {
            return "Failed Dependency";
        }
        if (i7 == 41) {
            return "Server Error";
        }
        if (i7 == 42) {
            return "Not Implemented";
        }
        if (i7 == 43) {
            return "Bad Gateway";
        }
        if (i7 == 44) {
            return "Service Unavailable";
        }
        if (i7 == 45) {
            return "Gateway Timeout";
        }
        if (i7 == 46) {
            return "HTTP Version Not Supported";
        }
        if (i7 == 47) {
            return "Insufficient Storage";
        }
        throw null;
    }

    public static int[] org$eclipse$jetty$http$HttpStatus$Code$s$values() {
        return (int[]) m948a().clone();
    }
}
