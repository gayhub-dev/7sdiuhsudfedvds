package okio;

import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
final class Base64 {
    private static final byte[] MAP = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] URL_MAP = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    private Base64() {
    }

    public static byte[] decode(String str) {
        int i7;
        char cCharAt;
        int length = str.length();
        while (length > 0 && ((cCharAt = str.charAt(length - 1)) == '=' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ' || cCharAt == '\t')) {
            length--;
        }
        int i8 = (int) ((length * 6) / 8);
        byte[] bArr = new byte[i8];
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < length; i12++) {
            char cCharAt2 = str.charAt(i12);
            if (cCharAt2 >= 'A' && cCharAt2 <= 'Z') {
                i7 = cCharAt2 - 'A';
            } else if (cCharAt2 >= 'a' && cCharAt2 <= 'z') {
                i7 = cCharAt2 - 'G';
            } else if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                i7 = cCharAt2 + 4;
            } else if (cCharAt2 == '+' || cCharAt2 == '-') {
                i7 = 62;
            } else if (cCharAt2 == '/' || cCharAt2 == '_') {
                i7 = 63;
            } else {
                if (cCharAt2 != '\n' && cCharAt2 != '\r' && cCharAt2 != ' ' && cCharAt2 != '\t') {
                    return null;
                }
            }
            i10 = (i10 << 6) | ((byte) i7);
            i9++;
            if (i9 % 4 == 0) {
                int i13 = i11 + 1;
                bArr[i11] = (byte) (i10 >> 16);
                int i14 = i13 + 1;
                bArr[i13] = (byte) (i10 >> 8);
                bArr[i14] = (byte) i10;
                i11 = i14 + 1;
            }
        }
        int i15 = i9 % 4;
        if (i15 == 1) {
            return null;
        }
        if (i15 == 2) {
            bArr[i11] = (byte) ((i10 << 12) >> 16);
            i11++;
        } else if (i15 == 3) {
            int i16 = i10 << 6;
            int i17 = i11 + 1;
            bArr[i11] = (byte) (i16 >> 16);
            i11 = i17 + 1;
            bArr[i17] = (byte) (i16 >> 8);
        }
        if (i11 == i8) {
            return bArr;
        }
        byte[] bArr2 = new byte[i11];
        System.arraycopy(bArr, 0, bArr2, 0, i11);
        return bArr2;
    }

    public static String encode(byte[] bArr) {
        return encode(bArr, MAP);
    }

    public static String encodeUrl(byte[] bArr) {
        return encode(bArr, URL_MAP);
    }

    private static String encode(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8 += 3) {
            int i9 = i7 + 1;
            bArr3[i7] = bArr2[(bArr[i8] & 255) >> 2];
            int i10 = i9 + 1;
            int i11 = i8 + 1;
            bArr3[i9] = bArr2[((bArr[i8] & 3) << 4) | ((bArr[i11] & 255) >> 4)];
            int i12 = i10 + 1;
            int i13 = (bArr[i11] & 15) << 2;
            int i14 = i8 + 2;
            bArr3[i10] = bArr2[i13 | ((bArr[i14] & 255) >> 6)];
            i7 = i12 + 1;
            bArr3[i12] = bArr2[bArr[i14] & 63];
        }
        int length2 = bArr.length % 3;
        if (length2 == 1) {
            int i15 = i7 + 1;
            bArr3[i7] = bArr2[(bArr[length] & 255) >> 2];
            int i16 = i15 + 1;
            bArr3[i15] = bArr2[(bArr[length] & 3) << 4];
            bArr3[i16] = 61;
            bArr3[i16 + 1] = 61;
        } else if (length2 == 2) {
            int i17 = i7 + 1;
            bArr3[i7] = bArr2[(bArr[length] & 255) >> 2];
            int i18 = i17 + 1;
            int i19 = (bArr[length] & 3) << 4;
            int i20 = length + 1;
            bArr3[i17] = bArr2[((bArr[i20] & 255) >> 4) | i19];
            bArr3[i18] = bArr2[(bArr[i20] & 15) << 2];
            bArr3[i18 + 1] = 61;
        }
        try {
            return new String(bArr3, "US-ASCII");
        } catch (UnsupportedEncodingException e7) {
            throw new AssertionError(e7);
        }
    }
}
