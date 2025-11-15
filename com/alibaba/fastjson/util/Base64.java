package com.alibaba.fastjson.util;

import java.util.Arrays;

/* loaded from: classes.dex */
public class Base64 {

    /* renamed from: CA */
    public static final char[] f421CA;

    /* renamed from: IA */
    public static final int[] f422IA;

    static {
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        f421CA = charArray;
        int[] iArr = new int[256];
        f422IA = iArr;
        Arrays.fill(iArr, -1);
        int length = charArray.length;
        for (int i7 = 0; i7 < length; i7++) {
            f422IA[f421CA[i7]] = i7;
        }
        f422IA[61] = 0;
    }

    public static byte[] decodeFast(char[] cArr, int i7, int i8) {
        int i9;
        int i10 = 0;
        if (i8 == 0) {
            return new byte[0];
        }
        int i11 = (i7 + i8) - 1;
        while (i7 < i11 && f422IA[cArr[i7]] < 0) {
            i7++;
        }
        while (i11 > 0 && f422IA[cArr[i11]] < 0) {
            i11--;
        }
        int i12 = cArr[i11] == '=' ? cArr[i11 + (-1)] == '=' ? 2 : 1 : 0;
        int i13 = (i11 - i7) + 1;
        if (i8 > 76) {
            i9 = (cArr[76] == '\r' ? i13 / 78 : 0) << 1;
        } else {
            i9 = 0;
        }
        int i14 = (((i13 - i9) * 6) >> 3) - i12;
        byte[] bArr = new byte[i14];
        int i15 = (i14 / 3) * 3;
        int i16 = 0;
        int i17 = 0;
        while (i16 < i15) {
            int[] iArr = f422IA;
            int i18 = i7 + 1;
            int i19 = i18 + 1;
            int i20 = (iArr[cArr[i7]] << 18) | (iArr[cArr[i18]] << 12);
            int i21 = i19 + 1;
            int i22 = i20 | (iArr[cArr[i19]] << 6);
            int i23 = i21 + 1;
            int i24 = i22 | iArr[cArr[i21]];
            int i25 = i16 + 1;
            bArr[i16] = (byte) (i24 >> 16);
            int i26 = i25 + 1;
            bArr[i25] = (byte) (i24 >> 8);
            int i27 = i26 + 1;
            bArr[i26] = (byte) i24;
            if (i9 <= 0 || (i17 = i17 + 1) != 19) {
                i7 = i23;
            } else {
                i7 = i23 + 2;
                i17 = 0;
            }
            i16 = i27;
        }
        if (i16 < i14) {
            int i28 = 0;
            while (i7 <= i11 - i12) {
                i10 |= f422IA[cArr[i7]] << (18 - (i28 * 6));
                i28++;
                i7++;
            }
            int i29 = 16;
            while (i16 < i14) {
                bArr[i16] = (byte) (i10 >> i29);
                i29 -= 8;
                i16++;
            }
        }
        return bArr;
    }

    public static byte[] decodeFast(String str, int i7, int i8) {
        int i9;
        int i10 = 0;
        if (i8 == 0) {
            return new byte[0];
        }
        int i11 = (i7 + i8) - 1;
        while (i7 < i11 && f422IA[str.charAt(i7)] < 0) {
            i7++;
        }
        while (i11 > 0 && f422IA[str.charAt(i11)] < 0) {
            i11--;
        }
        int i12 = str.charAt(i11) == '=' ? str.charAt(i11 + (-1)) == '=' ? 2 : 1 : 0;
        int i13 = (i11 - i7) + 1;
        if (i8 > 76) {
            i9 = (str.charAt(76) == '\r' ? i13 / 78 : 0) << 1;
        } else {
            i9 = 0;
        }
        int i14 = (((i13 - i9) * 6) >> 3) - i12;
        byte[] bArr = new byte[i14];
        int i15 = (i14 / 3) * 3;
        int i16 = 0;
        int i17 = 0;
        while (i16 < i15) {
            int[] iArr = f422IA;
            int i18 = i7 + 1;
            int i19 = i18 + 1;
            int i20 = (iArr[str.charAt(i7)] << 18) | (iArr[str.charAt(i18)] << 12);
            int i21 = i19 + 1;
            int i22 = i20 | (iArr[str.charAt(i19)] << 6);
            int i23 = i21 + 1;
            int i24 = i22 | iArr[str.charAt(i21)];
            int i25 = i16 + 1;
            bArr[i16] = (byte) (i24 >> 16);
            int i26 = i25 + 1;
            bArr[i25] = (byte) (i24 >> 8);
            int i27 = i26 + 1;
            bArr[i26] = (byte) i24;
            if (i9 <= 0 || (i17 = i17 + 1) != 19) {
                i7 = i23;
            } else {
                i7 = i23 + 2;
                i17 = 0;
            }
            i16 = i27;
        }
        if (i16 < i14) {
            int i28 = 0;
            while (i7 <= i11 - i12) {
                i10 |= f422IA[str.charAt(i7)] << (18 - (i28 * 6));
                i28++;
                i7++;
            }
            int i29 = 16;
            while (i16 < i14) {
                bArr[i16] = (byte) (i10 >> i29);
                i29 -= 8;
                i16++;
            }
        }
        return bArr;
    }

    public static byte[] decodeFast(String str) {
        int i7;
        int length = str.length();
        int i8 = 0;
        if (length == 0) {
            return new byte[0];
        }
        int i9 = length - 1;
        int i10 = 0;
        while (i10 < i9 && f422IA[str.charAt(i10) & 255] < 0) {
            i10++;
        }
        while (i9 > 0 && f422IA[str.charAt(i9) & 255] < 0) {
            i9--;
        }
        int i11 = str.charAt(i9) == '=' ? str.charAt(i9 + (-1)) == '=' ? 2 : 1 : 0;
        int i12 = (i9 - i10) + 1;
        if (length > 76) {
            i7 = (str.charAt(76) == '\r' ? i12 / 78 : 0) << 1;
        } else {
            i7 = 0;
        }
        int i13 = (((i12 - i7) * 6) >> 3) - i11;
        byte[] bArr = new byte[i13];
        int i14 = (i13 / 3) * 3;
        int i15 = 0;
        int i16 = 0;
        while (i15 < i14) {
            int[] iArr = f422IA;
            int i17 = i10 + 1;
            int i18 = i17 + 1;
            int i19 = (iArr[str.charAt(i10)] << 18) | (iArr[str.charAt(i17)] << 12);
            int i20 = i18 + 1;
            int i21 = i19 | (iArr[str.charAt(i18)] << 6);
            int i22 = i20 + 1;
            int i23 = i21 | iArr[str.charAt(i20)];
            int i24 = i15 + 1;
            bArr[i15] = (byte) (i23 >> 16);
            int i25 = i24 + 1;
            bArr[i24] = (byte) (i23 >> 8);
            int i26 = i25 + 1;
            bArr[i25] = (byte) i23;
            if (i7 <= 0 || (i16 = i16 + 1) != 19) {
                i10 = i22;
            } else {
                i10 = i22 + 2;
                i16 = 0;
            }
            i15 = i26;
        }
        if (i15 < i13) {
            int i27 = 0;
            while (i10 <= i9 - i11) {
                i8 |= f422IA[str.charAt(i10)] << (18 - (i27 * 6));
                i27++;
                i10++;
            }
            int i28 = 16;
            while (i15 < i13) {
                bArr[i15] = (byte) (i8 >> i28);
                i28 -= 8;
                i15++;
            }
        }
        return bArr;
    }
}
