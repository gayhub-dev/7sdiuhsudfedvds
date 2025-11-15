package com.alibaba.fastjson.util;

import android.support.constraint.motion.C0079a;
import java.lang.reflect.Array;
import java.math.BigInteger;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes.dex */
public final class RyuDouble {
    private static final int[][] POW5_SPLIT = (int[][]) Array.newInstance((Class<?>) int.class, 326, 4);
    private static final int[][] POW5_INV_SPLIT = (int[][]) Array.newInstance((Class<?>) int.class, 291, 4);

    static {
        BigInteger bigInteger = BigInteger.ONE;
        BigInteger bigIntegerSubtract = bigInteger.shiftLeft(31).subtract(bigInteger);
        BigInteger bigIntegerSubtract2 = bigInteger.shiftLeft(31).subtract(bigInteger);
        int i7 = 0;
        while (i7 < 326) {
            BigInteger bigIntegerPow = BigInteger.valueOf(5L).pow(i7);
            int iBitLength = bigIntegerPow.bitLength();
            int i8 = i7 == 0 ? 1 : (int) ((((i7 * 23219280) + 10000000) - 1) / 10000000);
            if (i8 != iBitLength) {
                throw new IllegalStateException(iBitLength + " != " + i8);
            }
            if (i7 < POW5_SPLIT.length) {
                for (int i9 = 0; i9 < 4; i9++) {
                    POW5_SPLIT[i7][i9] = bigIntegerPow.shiftRight(((3 - i9) * 31) + (iBitLength - 121)).and(bigIntegerSubtract).intValue();
                }
            }
            if (i7 < POW5_INV_SPLIT.length) {
                BigInteger bigInteger2 = BigInteger.ONE;
                BigInteger bigIntegerAdd = bigInteger2.shiftLeft(iBitLength + 121).divide(bigIntegerPow).add(bigInteger2);
                for (int i10 = 0; i10 < 4; i10++) {
                    if (i10 == 0) {
                        POW5_INV_SPLIT[i7][i10] = bigIntegerAdd.shiftRight((3 - i10) * 31).intValue();
                    } else {
                        POW5_INV_SPLIT[i7][i10] = bigIntegerAdd.shiftRight((3 - i10) * 31).and(bigIntegerSubtract2).intValue();
                    }
                }
            }
            i7++;
        }
    }

    public static String toString(double d7) {
        char[] cArr = new char[24];
        return new String(cArr, 0, toString(d7, cArr, 0));
    }

    public static int toString(double d7, char[] cArr, int i7) {
        int i8;
        boolean z6;
        boolean z7;
        long j7;
        int i9;
        boolean z8;
        long j8;
        long j9;
        boolean z9;
        long j10;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        if (!Double.isNaN(d7)) {
            if (d7 == Double.POSITIVE_INFINITY) {
                int i19 = i7 + 1;
                cArr[i7] = 'I';
                int i20 = i19 + 1;
                cArr[i19] = 'n';
                int i21 = i20 + 1;
                cArr[i20] = 'f';
                int i22 = i21 + 1;
                cArr[i21] = 'i';
                int i23 = i22 + 1;
                cArr[i22] = 'n';
                int i24 = i23 + 1;
                cArr[i23] = 'i';
                int i25 = i24 + 1;
                cArr[i24] = 't';
                i14 = i25 + 1;
                cArr[i25] = 'y';
            } else if (d7 == Double.NEGATIVE_INFINITY) {
                int i26 = i7 + 1;
                cArr[i7] = '-';
                int i27 = i26 + 1;
                cArr[i26] = 'I';
                int i28 = i27 + 1;
                cArr[i27] = 'n';
                int i29 = i28 + 1;
                cArr[i28] = 'f';
                int i30 = i29 + 1;
                cArr[i29] = 'i';
                int i31 = i30 + 1;
                cArr[i30] = 'n';
                int i32 = i31 + 1;
                cArr[i31] = 'i';
                int i33 = i32 + 1;
                cArr[i32] = 't';
                i18 = i33 + 1;
                cArr[i33] = 'y';
            } else {
                long jDoubleToLongBits = Double.doubleToLongBits(d7);
                if (jDoubleToLongBits == 0) {
                    int i34 = i7 + 1;
                    cArr[i7] = '0';
                    int i35 = i34 + 1;
                    cArr[i34] = '.';
                    i18 = i35 + 1;
                    cArr[i35] = '0';
                } else if (jDoubleToLongBits == Long.MIN_VALUE) {
                    int i36 = i7 + 1;
                    cArr[i7] = '-';
                    int i37 = i36 + 1;
                    cArr[i36] = '0';
                    int i38 = i37 + 1;
                    cArr[i37] = '.';
                    i14 = i38 + 1;
                    cArr[i38] = '0';
                } else {
                    int i39 = (int) ((jDoubleToLongBits >>> 52) & 2047);
                    long j11 = jDoubleToLongBits & 4503599627370495L;
                    if (i39 == 0) {
                        i8 = -1074;
                    } else {
                        i8 = (i39 - 1023) - 52;
                        j11 |= 4503599627370496L;
                    }
                    boolean z10 = jDoubleToLongBits < 0;
                    boolean z11 = (j11 & 1) == 0;
                    long j12 = 4 * j11;
                    long j13 = 2 + j12;
                    int i40 = (j11 != 4503599627370496L || i39 <= 1) ? 1 : 0;
                    long j14 = (j12 - 1) - i40;
                    int i41 = i8 - 2;
                    if (i41 >= 0) {
                        int iMax = Math.max(0, ((int) ((i41 * 3010299) / 10000000)) - 1);
                        int i42 = ((((-i41) + iMax) + (((iMax == 0 ? 1 : (int) ((((iMax * 23219280) + 10000000) - 1) / 10000000)) + IjkMediaMeta.FF_PROFILE_H264_HIGH_422) - 1)) - 93) - 21;
                        if (i42 >= 0) {
                            int[] iArr = POW5_INV_SPLIT[iMax];
                            long j15 = j12 >>> 31;
                            long j16 = j12 & 2147483647L;
                            z7 = z10;
                            long j17 = ((((((((((((j16 * iArr[3]) >>> 31) + (iArr[2] * j16)) + (j15 * iArr[3])) >>> 31) + (iArr[1] * j16)) + (iArr[2] * j15)) >>> 31) + (iArr[0] * j16)) + (iArr[1] * j15)) >>> 21) + ((iArr[0] * j15) << 10)) >>> i42;
                            long j18 = j13 >>> 31;
                            long j19 = 2147483647L & j13;
                            long j20 = ((((((((((((iArr[3] * j19) >>> 31) + (iArr[2] * j19)) + (j18 * iArr[3])) >>> 31) + (iArr[1] * j19)) + (iArr[2] * j18)) >>> 31) + (iArr[0] * j19)) + (iArr[1] * j18)) >>> 21) + ((iArr[0] * j18) << 10)) >>> i42;
                            long j21 = j14 >>> 31;
                            long j22 = 2147483647L & j14;
                            j9 = j20;
                            z6 = z11;
                            j8 = ((((((((((((j22 * iArr[3]) >>> 31) + (iArr[2] * j22)) + (j21 * iArr[3])) >>> 31) + (iArr[1] * j22)) + (iArr[2] * j21)) >>> 31) + (iArr[0] * j22)) + (iArr[1] * j21)) >>> 21) + ((iArr[0] * j21) << 10)) >>> i42;
                            i9 = iMax;
                            if (i9 <= 21) {
                                long j23 = j12 % 5;
                                if (j23 == 0) {
                                    if (j23 != 0) {
                                        i17 = 0;
                                    } else if (j12 % 25 != 0) {
                                        i17 = 1;
                                    } else if (j12 % 125 != 0) {
                                        i17 = 2;
                                    } else if (j12 % 625 != 0) {
                                        i17 = 3;
                                    } else {
                                        long j24 = j12 / 625;
                                        i17 = 4;
                                        for (long j25 = 0; j24 > j25 && j24 % 5 == j25; j25 = 0) {
                                            j24 /= 5;
                                            i17++;
                                        }
                                    }
                                    z9 = i17 >= i9;
                                    z8 = false;
                                    j7 = j17;
                                } else {
                                    if (z6) {
                                        if (j14 % 5 != 0) {
                                            i16 = 0;
                                        } else if (j14 % 25 != 0) {
                                            i16 = 1;
                                        } else if (j14 % 125 != 0) {
                                            i16 = 2;
                                        } else if (j14 % 625 != 0) {
                                            i16 = 3;
                                        } else {
                                            long j26 = j14 / 625;
                                            i16 = 4;
                                            for (long j27 = 0; j26 > j27 && j26 % 5 == j27; j27 = 0) {
                                                j26 /= 5;
                                                i16++;
                                            }
                                        }
                                        z8 = i16 >= i9;
                                        z9 = false;
                                        j7 = j17;
                                    } else {
                                        if (j13 % 5 != 0) {
                                            i15 = 0;
                                        } else if (j13 % 25 != 0) {
                                            i15 = 1;
                                        } else if (j13 % 125 != 0) {
                                            i15 = 2;
                                        } else if (j13 % 625 != 0) {
                                            i15 = 3;
                                        } else {
                                            long j28 = j13 / 625;
                                            i15 = 4;
                                            for (long j29 = 0; j28 > j29 && j28 % 5 == j29; j29 = 0) {
                                                j28 /= 5;
                                                i15++;
                                            }
                                        }
                                        if (i15 >= i9) {
                                            j9--;
                                        }
                                    }
                                    z9 = false;
                                    j7 = j17;
                                }
                            } else {
                                z9 = false;
                                j7 = j17;
                            }
                        } else {
                            throw new IllegalArgumentException(C0079a.m93a("", i42));
                        }
                    } else {
                        z6 = z11;
                        z7 = z10;
                        int iMax2 = Math.max(0, ((int) ((r1 * 6989700) / 10000000)) - 1);
                        int i43 = (-i41) - iMax2;
                        int i44 = ((iMax2 - ((i43 == 0 ? 1 : (int) ((((i43 * 23219280) + 10000000) - 1) / 10000000)) - 121)) - 93) - 21;
                        if (i44 >= 0) {
                            int[] iArr2 = POW5_SPLIT[i43];
                            long j30 = j12 >>> 31;
                            long j31 = j12 & 2147483647L;
                            long j32 = ((((((((((((j31 * iArr2[3]) >>> 31) + (iArr2[2] * j31)) + (j30 * iArr2[3])) >>> 31) + (iArr2[1] * j31)) + (iArr2[2] * j30)) >>> 31) + (iArr2[0] * j31)) + (iArr2[1] * j30)) >>> 21) + ((iArr2[0] * j30) << 10)) >>> i44;
                            long j33 = j13 >>> 31;
                            long j34 = j13 & 2147483647L;
                            j7 = j32;
                            long j35 = ((((((((((((j34 * iArr2[3]) >>> 31) + (iArr2[2] * j34)) + (j33 * iArr2[3])) >>> 31) + (iArr2[1] * j34)) + (iArr2[2] * j33)) >>> 31) + (iArr2[0] * j34)) + (iArr2[1] * j33)) >>> 21) + ((iArr2[0] * j33) << 10)) >>> i44;
                            long j36 = j14 >>> 31;
                            long j37 = 2147483647L & j14;
                            long j38 = ((((((((((((j37 * iArr2[3]) >>> 31) + (iArr2[2] * j37)) + (j36 * iArr2[3])) >>> 31) + (iArr2[1] * j37)) + (iArr2[2] * j36)) >>> 31) + (iArr2[0] * j37)) + (iArr2[1] * j36)) >>> 21) + ((iArr2[0] * j36) << 10)) >>> i44;
                            i9 = iMax2 + i41;
                            if (iMax2 <= 1) {
                                if (z6) {
                                    z8 = i40 == 1;
                                    j9 = j35;
                                } else {
                                    j9 = j35 - 1;
                                    z8 = false;
                                }
                                j8 = j38;
                                z9 = true;
                            } else if (iMax2 < 63) {
                                z8 = false;
                                j9 = j35;
                                z9 = (j12 & ((1 << (iMax2 + (-1))) - 1)) == 0;
                                j8 = j38;
                            } else {
                                z8 = false;
                                j8 = j38;
                                j9 = j35;
                                z9 = false;
                            }
                        } else {
                            throw new IllegalArgumentException(C0079a.m93a("", i44));
                        }
                    }
                    int i45 = j9 >= 1000000000000000000L ? 19 : j9 >= 100000000000000000L ? 18 : j9 >= 10000000000000000L ? 17 : j9 >= 1000000000000000L ? 16 : j9 >= 100000000000000L ? 15 : j9 >= 10000000000000L ? 14 : j9 >= 1000000000000L ? 13 : j9 >= 100000000000L ? 12 : j9 >= 10000000000L ? 11 : j9 >= 1000000000 ? 10 : j9 >= 100000000 ? 9 : j9 >= 10000000 ? 8 : j9 >= 1000000 ? 7 : j9 >= 100000 ? 6 : j9 >= 10000 ? 5 : j9 >= 1000 ? 4 : j9 >= 100 ? 3 : j9 >= 10 ? 2 : 1;
                    int i46 = (i9 + i45) - 1;
                    boolean z12 = i46 < -3 || i46 >= 7;
                    if (z8 || z9) {
                        int i47 = 0;
                        int i48 = 0;
                        while (true) {
                            long j39 = j9 / 10;
                            long j40 = j8 / 10;
                            if (j39 <= j40 || (j9 < 100 && z12)) {
                                break;
                            }
                            z8 &= j8 % 10 == 0;
                            z9 &= i47 == 0;
                            i47 = (int) (j7 % 10);
                            j7 /= 10;
                            i48++;
                            j9 = j39;
                            j8 = j40;
                        }
                        if (z8 && z6) {
                            while (j8 % 10 == 0 && (j9 >= 100 || !z12)) {
                                z9 &= i47 == 0;
                                i47 = (int) (j7 % 10);
                                j9 /= 10;
                                j7 /= 10;
                                j8 /= 10;
                                i48++;
                            }
                        }
                        if (z9 && i47 == 5 && j7 % 2 == 0) {
                            i47 = 4;
                        }
                        j10 = j7 + (((j7 != j8 || (z8 && z6)) && i47 < 5) ? 0 : 1);
                        i10 = i48;
                    } else {
                        i10 = 0;
                        int i49 = 0;
                        while (true) {
                            long j41 = j9 / 10;
                            long j42 = j8 / 10;
                            if (j41 <= j42 || (j9 < 100 && z12)) {
                                break;
                            }
                            i49 = (int) (j7 % 10);
                            j7 /= 10;
                            i10++;
                            j9 = j41;
                            j8 = j42;
                        }
                        j10 = j7 + ((j7 == j8 || i49 >= 5) ? 1 : 0);
                    }
                    int i50 = i45 - i10;
                    if (z7) {
                        i11 = i7 + 1;
                        cArr[i7] = '-';
                    } else {
                        i11 = i7;
                    }
                    if (!z12) {
                        char c7 = '0';
                        if (i46 < 0) {
                            int i51 = i11 + 1;
                            cArr[i11] = '0';
                            int i52 = i51 + 1;
                            cArr[i51] = '.';
                            int i53 = -1;
                            while (i53 > i46) {
                                cArr[i52] = c7;
                                i53--;
                                c7 = '0';
                                i52++;
                            }
                            i12 = i52;
                            for (int i54 = 0; i54 < i50; i54++) {
                                cArr[((i52 + i50) - i54) - 1] = (char) ((j10 % 10) + 48);
                                j10 /= 10;
                                i12++;
                            }
                        } else {
                            int i55 = i46 + 1;
                            if (i55 >= i50) {
                                for (int i56 = 0; i56 < i50; i56++) {
                                    cArr[((i11 + i50) - i56) - 1] = (char) ((j10 % 10) + 48);
                                    j10 /= 10;
                                }
                                int i57 = i11 + i50;
                                while (i50 < i55) {
                                    cArr[i57] = '0';
                                    i50++;
                                    i57++;
                                }
                                int i58 = i57 + 1;
                                cArr[i57] = '.';
                                cArr[i58] = '0';
                                i12 = i58 + 1;
                            } else {
                                int i59 = i11 + 1;
                                for (int i60 = 0; i60 < i50; i60++) {
                                    if ((i50 - i60) - 1 == i46) {
                                        cArr[((i59 + i50) - i60) - 1] = '.';
                                        i59--;
                                    }
                                    cArr[((i59 + i50) - i60) - 1] = (char) ((j10 % 10) + 48);
                                    j10 /= 10;
                                }
                                i12 = i50 + 1 + i11;
                            }
                        }
                        return i12 - i7;
                    }
                    for (int i61 = 0; i61 < i50 - 1; i61++) {
                        int i62 = (int) (j10 % 10);
                        j10 /= 10;
                        cArr[(i11 + i50) - i61] = (char) (i62 + 48);
                    }
                    cArr[i11] = (char) ((j10 % 10) + 48);
                    cArr[i11 + 1] = '.';
                    int i63 = i50 + 1 + i11;
                    if (i50 == 1) {
                        cArr[i63] = '0';
                        i63++;
                    }
                    int i64 = i63 + 1;
                    cArr[i63] = 'E';
                    if (i46 < 0) {
                        cArr[i64] = '-';
                        i46 = -i46;
                        i64++;
                    }
                    if (i46 >= 100) {
                        int i65 = i64 + 1;
                        i13 = 48;
                        cArr[i64] = (char) ((i46 / 100) + 48);
                        i46 %= 100;
                        i64 = i65 + 1;
                        cArr[i65] = (char) ((i46 / 10) + 48);
                    } else {
                        i13 = 48;
                        if (i46 >= 10) {
                            cArr[i64] = (char) ((i46 / 10) + 48);
                            i64++;
                        }
                    }
                    i14 = i64 + 1;
                    cArr[i64] = (char) ((i46 % 10) + i13);
                }
            }
            return i14 - i7;
        }
        int i66 = i7 + 1;
        cArr[i7] = 'N';
        int i67 = i66 + 1;
        cArr[i66] = 'a';
        i18 = i67 + 1;
        cArr[i67] = 'N';
        return i18 - i7;
    }
}
