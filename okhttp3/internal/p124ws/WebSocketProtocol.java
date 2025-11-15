package okhttp3.internal.p124ws;

import android.support.constraint.motion.C0079a;
import okio.Buffer;
import okio.ByteString;

/* loaded from: classes.dex */
public final class WebSocketProtocol {
    public static final String ACCEPT_MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    public static final int B0_FLAG_FIN = 128;
    public static final int B0_FLAG_RSV1 = 64;
    public static final int B0_FLAG_RSV2 = 32;
    public static final int B0_FLAG_RSV3 = 16;
    public static final int B0_MASK_OPCODE = 15;
    public static final int B1_FLAG_MASK = 128;
    public static final int B1_MASK_LENGTH = 127;
    public static final int CLOSE_CLIENT_GOING_AWAY = 1001;
    public static final long CLOSE_MESSAGE_MAX = 123;
    public static final int CLOSE_NO_STATUS_CODE = 1005;
    public static final int OPCODE_BINARY = 2;
    public static final int OPCODE_CONTINUATION = 0;
    public static final int OPCODE_CONTROL_CLOSE = 8;
    public static final int OPCODE_CONTROL_PING = 9;
    public static final int OPCODE_CONTROL_PONG = 10;
    public static final int OPCODE_FLAG_CONTROL = 8;
    public static final int OPCODE_TEXT = 1;
    public static final long PAYLOAD_BYTE_MAX = 125;
    public static final int PAYLOAD_LONG = 127;
    public static final int PAYLOAD_SHORT = 126;
    public static final long PAYLOAD_SHORT_MAX = 65535;

    private WebSocketProtocol() {
        throw new AssertionError("No instances.");
    }

    public static String acceptHeader(String str) {
        return ByteString.encodeUtf8(str + ACCEPT_MAGIC).sha1().base64();
    }

    public static String closeCodeExceptionMessage(int i7) {
        if (i7 < 1000 || i7 >= 5000) {
            return C0079a.m93a("Code must be in range [1000,5000): ", i7);
        }
        if ((i7 < 1004 || i7 > 1006) && (i7 < 1012 || i7 > 2999)) {
            return null;
        }
        return "Code " + i7 + " is reserved and may not be used.";
    }

    public static void toggleMask(Buffer.UnsafeCursor unsafeCursor, byte[] bArr) {
        int length = bArr.length;
        int i7 = 0;
        do {
            byte[] bArr2 = unsafeCursor.data;
            int i8 = unsafeCursor.start;
            int i9 = unsafeCursor.end;
            while (i8 < i9) {
                int i10 = i7 % length;
                bArr2[i8] = (byte) (bArr2[i8] ^ bArr[i10]);
                i8++;
                i7 = i10 + 1;
            }
        } while (unsafeCursor.next() != -1);
    }

    public static void validateCloseCode(int i7) {
        String strCloseCodeExceptionMessage = closeCodeExceptionMessage(i7);
        if (strCloseCodeExceptionMessage != null) {
            throw new IllegalArgumentException(strCloseCodeExceptionMessage);
        }
    }
}
