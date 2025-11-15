package okhttp3.internal.http2;

import android.support.constraint.C0072a;
import java.io.IOException;
import okhttp3.internal.Util;
import okio.ByteString;

/* loaded from: classes.dex */
public final class Http2 {
    public static final byte FLAG_ACK = 1;
    public static final byte FLAG_COMPRESSED = 32;
    public static final byte FLAG_END_HEADERS = 4;
    public static final byte FLAG_END_PUSH_PROMISE = 4;
    public static final byte FLAG_END_STREAM = 1;
    public static final byte FLAG_NONE = 0;
    public static final byte FLAG_PADDED = 8;
    public static final byte FLAG_PRIORITY = 32;
    public static final int INITIAL_MAX_FRAME_SIZE = 16384;
    public static final byte TYPE_CONTINUATION = 9;
    public static final byte TYPE_DATA = 0;
    public static final byte TYPE_GOAWAY = 7;
    public static final byte TYPE_HEADERS = 1;
    public static final byte TYPE_PING = 6;
    public static final byte TYPE_PRIORITY = 2;
    public static final byte TYPE_PUSH_PROMISE = 5;
    public static final byte TYPE_RST_STREAM = 3;
    public static final byte TYPE_SETTINGS = 4;
    public static final byte TYPE_WINDOW_UPDATE = 8;
    public static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    private static final String[] FRAME_NAMES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    public static final String[] FLAGS = new String[64];
    public static final String[] BINARY = new String[256];

    static {
        int i7 = 0;
        int i8 = 0;
        while (true) {
            String[] strArr = BINARY;
            if (i8 >= strArr.length) {
                break;
            }
            strArr[i8] = Util.format("%8s", Integer.toBinaryString(i8)).replace(' ', '0');
            i8++;
        }
        String[] strArr2 = FLAGS;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i9 = 0; i9 < 1; i9++) {
            int i10 = iArr[i9];
            String[] strArr3 = FLAGS;
            strArr3[i10 | 8] = C0072a.m92a(new StringBuilder(), strArr3[i10], "|PADDED");
        }
        String[] strArr4 = FLAGS;
        strArr4[4] = "END_HEADERS";
        strArr4[32] = "PRIORITY";
        strArr4[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i11 = 0; i11 < 3; i11++) {
            int i12 = iArr2[i11];
            for (int i13 = 0; i13 < 1; i13++) {
                int i14 = iArr[i13];
                String[] strArr5 = FLAGS;
                int i15 = i14 | i12;
                strArr5[i15] = strArr5[i14] + '|' + strArr5[i12];
                StringBuilder sb = new StringBuilder();
                sb.append(strArr5[i14]);
                sb.append('|');
                strArr5[i15 | 8] = C0072a.m92a(sb, strArr5[i12], "|PADDED");
            }
        }
        while (true) {
            String[] strArr6 = FLAGS;
            if (i7 >= strArr6.length) {
                return;
            }
            if (strArr6[i7] == null) {
                strArr6[i7] = BINARY[i7];
            }
            i7++;
        }
    }

    private Http2() {
    }

    public static String formatFlags(byte b7, byte b8) {
        if (b8 == 0) {
            return "";
        }
        if (b7 != 2 && b7 != 3) {
            if (b7 == 4 || b7 == 6) {
                return b8 == 1 ? "ACK" : BINARY[b8];
            }
            if (b7 != 7 && b7 != 8) {
                String[] strArr = FLAGS;
                String str = b8 < strArr.length ? strArr[b8] : BINARY[b8];
                return (b7 != 5 || (b8 & 4) == 0) ? (b7 != 0 || (b8 & 32) == 0) ? str : str.replace("PRIORITY", "COMPRESSED") : str.replace("HEADERS", "PUSH_PROMISE");
            }
        }
        return BINARY[b8];
    }

    public static String frameLog(boolean z6, int i7, int i8, byte b7, byte b8) {
        String[] strArr = FRAME_NAMES;
        String str = b7 < strArr.length ? strArr[b7] : Util.format("0x%02x", Byte.valueOf(b7));
        String flags = formatFlags(b7, b8);
        Object[] objArr = new Object[5];
        objArr[0] = z6 ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i7);
        objArr[2] = Integer.valueOf(i8);
        objArr[3] = str;
        objArr[4] = flags;
        return Util.format("%s 0x%08x %5d %-13s %s", objArr);
    }

    public static IllegalArgumentException illegalArgument(String str, Object... objArr) {
        throw new IllegalArgumentException(Util.format(str, objArr));
    }

    public static IOException ioException(String str, Object... objArr) throws IOException {
        throw new IOException(Util.format(str, objArr));
    }
}
