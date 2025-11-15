package p161t5;

import java.io.ByteArrayOutputStream;

/* compiled from: B64Code.java */
/* renamed from: t5.c */
/* loaded from: classes.dex */
public class C1911c {

    /* renamed from: a */
    public static final char[] f5613a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* renamed from: b */
    public static final byte[] f5614b = new byte[256];

    static {
        for (int i7 = 0; i7 < 256; i7++) {
            f5614b[i7] = -1;
        }
        for (byte b7 = 0; b7 < 64; b7 = (byte) (b7 + 1)) {
            f5614b[(byte) f5613a[b7]] = b7;
        }
        f5614b[61] = 0;
    }

    /* renamed from: a */
    public static void m2207a(String str, ByteArrayOutputStream byteArrayOutputStream) {
        if (str == null) {
            return;
        }
        byte[] bArr = new byte[4];
        int i7 = 0;
        int i8 = 0;
        while (i7 < str.length()) {
            int i9 = i7 + 1;
            char cCharAt = str.charAt(i7);
            if (cCharAt == '=') {
                return;
            }
            if (!Character.isWhitespace(cCharAt)) {
                byte[] bArr2 = f5614b;
                if (bArr2[cCharAt] < 0) {
                    throw new IllegalArgumentException("Not B64 encoded");
                }
                int i10 = i8 + 1;
                bArr[i8] = bArr2[cCharAt];
                if (i10 == 2) {
                    byteArrayOutputStream.write((bArr[0] << 2) | (bArr[1] >>> 4));
                } else if (i10 == 3) {
                    byteArrayOutputStream.write((bArr[2] >>> 2) | (bArr[1] << 4));
                } else if (i10 == 4) {
                    byteArrayOutputStream.write((bArr[2] << 6) | bArr[3]);
                    i8 = 0;
                }
                i8 = i10;
            }
            i7 = i9;
        }
    }

    /* renamed from: b */
    public static char[] m2208b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        char[] cArr = new char[((length + 2) / 3) * 4];
        int i7 = (length / 3) * 3;
        int i8 = 0;
        int i9 = 0;
        while (i8 < i7) {
            int i10 = i8 + 1;
            byte b7 = bArr[i8];
            int i11 = i10 + 1;
            byte b8 = bArr[i10];
            int i12 = i11 + 1;
            byte b9 = bArr[i11];
            int i13 = i9 + 1;
            char[] cArr2 = f5613a;
            cArr[i9] = cArr2[(b7 >>> 2) & 63];
            int i14 = i13 + 1;
            cArr[i13] = cArr2[((b7 << 4) & 63) | ((b8 >>> 4) & 15)];
            int i15 = i14 + 1;
            cArr[i14] = cArr2[((b8 << 2) & 63) | ((b9 >>> 6) & 3)];
            i9 = i15 + 1;
            cArr[i15] = cArr2[b9 & 63];
            i8 = i12;
        }
        if (length != i8) {
            int i16 = length % 3;
            if (i16 == 1) {
                byte b10 = bArr[i8];
                int i17 = i9 + 1;
                char[] cArr3 = f5613a;
                cArr[i9] = cArr3[(b10 >>> 2) & 63];
                int i18 = i17 + 1;
                cArr[i17] = cArr3[(b10 << 4) & 63];
                cArr[i18] = '=';
                cArr[i18 + 1] = '=';
            } else if (i16 == 2) {
                int i19 = i8 + 1;
                byte b11 = bArr[i8];
                byte b12 = bArr[i19];
                int i20 = i9 + 1;
                char[] cArr4 = f5613a;
                cArr[i9] = cArr4[(b11 >>> 2) & 63];
                int i21 = i20 + 1;
                cArr[i20] = cArr4[((b11 << 4) & 63) | ((b12 >>> 4) & 15)];
                cArr[i21] = cArr4[(b12 << 2) & 63];
                cArr[i21 + 1] = '=';
            }
        }
        return cArr;
    }
}
