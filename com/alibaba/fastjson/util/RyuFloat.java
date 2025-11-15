package com.alibaba.fastjson.util;

/* loaded from: classes.dex */
public final class RyuFloat {
    private static final int[][] POW5_SPLIT = {new int[]{536870912, 0}, new int[]{671088640, 0}, new int[]{838860800, 0}, new int[]{1048576000, 0}, new int[]{655360000, 0}, new int[]{819200000, 0}, new int[]{1024000000, 0}, new int[]{640000000, 0}, new int[]{800000000, 0}, new int[]{1000000000, 0}, new int[]{625000000, 0}, new int[]{781250000, 0}, new int[]{976562500, 0}, new int[]{610351562, 1073741824}, new int[]{762939453, 268435456}, new int[]{953674316, 872415232}, new int[]{596046447, 1619001344}, new int[]{745058059, 1486880768}, new int[]{931322574, 1321730048}, new int[]{582076609, 289210368}, new int[]{727595761, 898383872}, new int[]{909494701, 1659850752}, new int[]{568434188, 1305842176}, new int[]{710542735, 1632302720}, new int[]{888178419, 1503507488}, new int[]{555111512, 671256724}, new int[]{693889390, 839070905}, new int[]{867361737, 2122580455}, new int[]{542101086, 521306416}, new int[]{677626357, 1725374844}, new int[]{847032947, 546105819}, new int[]{1058791184, 145761362}, new int[]{661744490, 91100851}, new int[]{827180612, 1187617888}, new int[]{1033975765, 1484522360}, new int[]{646234853, 1196261931}, new int[]{807793566, 2032198326}, new int[]{1009741958, 1466506084}, new int[]{631088724, 379695390}, new int[]{788860905, 474619238}, new int[]{986076131, 1130144959}, new int[]{616297582, 437905143}, new int[]{770371977, 1621123253}, new int[]{962964972, 415791331}, new int[]{601853107, 1333611405}, new int[]{752316384, 1130143345}, new int[]{940395480, 1412679181}};
    private static final int[][] POW5_INV_SPLIT = {new int[]{268435456, 1}, new int[]{214748364, 1717986919}, new int[]{171798691, 1803886265}, new int[]{137438953, 1013612282}, new int[]{219902325, 1192282922}, new int[]{175921860, 953826338}, new int[]{140737488, 763061070}, new int[]{225179981, 791400982}, new int[]{180143985, 203624056}, new int[]{144115188, 162899245}, new int[]{230584300, 1978625710}, new int[]{184467440, 1582900568}, new int[]{147573952, 1266320455}, new int[]{236118324, 308125809}, new int[]{188894659, 675997377}, new int[]{151115727, 970294631}, new int[]{241785163, 1981968139}, new int[]{193428131, 297084323}, new int[]{154742504, 1955654377}, new int[]{247588007, 1840556814}, new int[]{198070406, 613451992}, new int[]{158456325, 61264864}, new int[]{253530120, 98023782}, new int[]{202824096, 78419026}, new int[]{162259276, 1780722139}, new int[]{259614842, 1990161963}, new int[]{207691874, 733136111}, new int[]{166153499, 1016005619}, new int[]{265845599, 337118801}, new int[]{212676479, 699191770}, new int[]{170141183, 988850146}};

    public static String toString(float f7) {
        char[] cArr = new char[15];
        return new String(cArr, 0, toString(f7, cArr, 0));
    }

    public static int toString(float f7, char[] cArr, int i7) {
        int i8;
        boolean z6;
        int i9;
        int i10;
        boolean z7;
        int i11;
        boolean z8;
        int i12;
        int i13;
        int i14;
        boolean z9;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        if (Float.isNaN(f7)) {
            int i22 = i7 + 1;
            cArr[i7] = 'N';
            int i23 = i22 + 1;
            cArr[i22] = 'a';
            i21 = i23 + 1;
            cArr[i23] = 'N';
        } else {
            if (f7 == Float.POSITIVE_INFINITY) {
                int i24 = i7 + 1;
                cArr[i7] = 'I';
                int i25 = i24 + 1;
                cArr[i24] = 'n';
                int i26 = i25 + 1;
                cArr[i25] = 'f';
                int i27 = i26 + 1;
                cArr[i26] = 'i';
                int i28 = i27 + 1;
                cArr[i27] = 'n';
                int i29 = i28 + 1;
                cArr[i28] = 'i';
                int i30 = i29 + 1;
                cArr[i29] = 't';
                cArr[i30] = 'y';
                return (i30 + 1) - i7;
            }
            if (f7 == Float.NEGATIVE_INFINITY) {
                int i31 = i7 + 1;
                cArr[i7] = '-';
                int i32 = i31 + 1;
                cArr[i31] = 'I';
                int i33 = i32 + 1;
                cArr[i32] = 'n';
                int i34 = i33 + 1;
                cArr[i33] = 'f';
                int i35 = i34 + 1;
                cArr[i34] = 'i';
                int i36 = i35 + 1;
                cArr[i35] = 'n';
                int i37 = i36 + 1;
                cArr[i36] = 'i';
                int i38 = i37 + 1;
                cArr[i37] = 't';
                i21 = i38 + 1;
                cArr[i38] = 'y';
            } else {
                int iFloatToIntBits = Float.floatToIntBits(f7);
                if (iFloatToIntBits != 0) {
                    if (iFloatToIntBits == Integer.MIN_VALUE) {
                        int i39 = i7 + 1;
                        cArr[i7] = '-';
                        int i40 = i39 + 1;
                        cArr[i39] = '0';
                        int i41 = i40 + 1;
                        cArr[i40] = '.';
                        cArr[i41] = '0';
                        return (i41 + 1) - i7;
                    }
                    int i42 = (iFloatToIntBits >> 23) & 255;
                    int i43 = 8388607 & iFloatToIntBits;
                    if (i42 == 0) {
                        i8 = -149;
                    } else {
                        i8 = (i42 - 127) - 23;
                        i43 |= 8388608;
                    }
                    boolean z10 = iFloatToIntBits < 0;
                    boolean z11 = (i43 & 1) == 0;
                    int i44 = i43 * 4;
                    int i45 = i44 + 2;
                    int i46 = i44 - ((((long) i43) != 8388608 || i42 <= 1) ? 2 : 1);
                    int i47 = i8 - 2;
                    if (i47 >= 0) {
                        i12 = (int) ((i47 * 3010299) / 10000000);
                        if (i12 == 0) {
                            i18 = i47;
                            i19 = 1;
                        } else {
                            i18 = i47;
                            i19 = (int) ((((i12 * 23219280) + 10000000) - 1) / 10000000);
                        }
                        int[][] iArr = POW5_INV_SPLIT;
                        long j7 = iArr[i12][0];
                        long j8 = iArr[i12][1];
                        long j9 = i44;
                        int i48 = (((i19 + 59) - 1) + ((-i18) + i12)) - 31;
                        z6 = z11;
                        int i49 = (int) (((j9 * j7) + ((j9 * j8) >> 31)) >> i48);
                        long j10 = i45;
                        i14 = (int) (((j10 * j7) + ((j10 * j8) >> 31)) >> i48);
                        int i50 = i45;
                        long j11 = i46;
                        int i51 = (int) (((j7 * j11) + ((j11 * j8) >> 31)) >> i48);
                        if (i12 == 0 || (i14 - 1) / 10 > i51 / 10) {
                            i20 = i51;
                            i13 = 0;
                        } else {
                            i20 = i51;
                            i13 = (int) ((((iArr[r6][0] * j9) + ((iArr[r6][1] * j9) >> 31)) >> (((r4 - 1) + (((i12 - 1 == 0 ? 1 : (int) ((((r6 * 23219280) + 10000000) - 1) / 10000000)) + 59) - 1)) - 31)) % 10);
                        }
                        int i52 = 0;
                        while (i50 > 0 && i50 % 5 == 0) {
                            i50 /= 5;
                            i52++;
                        }
                        int i53 = 0;
                        int i54 = i44;
                        while (i54 > 0 && i54 % 5 == 0) {
                            i54 /= 5;
                            i53++;
                        }
                        int i55 = 0;
                        while (i46 > 0 && i46 % 5 == 0) {
                            i46 /= 5;
                            i55++;
                        }
                        z8 = i52 >= i12;
                        z9 = i53 >= i12;
                        z7 = i55 >= i12;
                        i11 = i20;
                        i9 = i49;
                    } else {
                        z6 = z11;
                        int i56 = -i47;
                        int i57 = (int) ((i56 * 6989700) / 10000000);
                        int i58 = i56 - i57;
                        int i59 = i58 == 0 ? 1 : (int) ((((i58 * 23219280) + 10000000) - 1) / 10000000);
                        int[][] iArr2 = POW5_SPLIT;
                        long j12 = iArr2[i58][0];
                        long j13 = iArr2[i58][1];
                        int i60 = (i57 - (i59 - 61)) - 31;
                        long j14 = i44;
                        i9 = (int) (((j14 * j12) + ((j14 * j13) >> 31)) >> i60);
                        long j15 = i45;
                        int i61 = (int) (((j15 * j12) + ((j15 * j13) >> 31)) >> i60);
                        long j16 = i46;
                        int i62 = (int) (((j12 * j16) + ((j16 * j13) >> 31)) >> i60);
                        if (i57 == 0 || (i61 - 1) / 10 > i62 / 10) {
                            i10 = 0;
                        } else {
                            i10 = (int) ((((iArr2[r3][0] * j14) + ((iArr2[r3][1] * j14) >> 31)) >> (((i57 - 1) - ((i58 + 1 == 0 ? 1 : (int) ((((r3 * 23219280) + 10000000) - 1) / 10000000)) - 61)) - 31)) % 10);
                        }
                        int i63 = i47 + i57;
                        boolean z12 = 1 >= i57;
                        boolean z13 = i57 < 23 && (((1 << (i57 + (-1))) - 1) & i44) == 0;
                        z7 = (i46 % 2 == 1 ? 0 : 1) >= i57;
                        i11 = i62;
                        z8 = z12;
                        i12 = i63;
                        i13 = i10;
                        i14 = i61;
                        z9 = z13;
                    }
                    int i64 = 1000000000;
                    int i65 = 10;
                    while (i65 > 0 && i14 < i64) {
                        i64 /= 10;
                        i65--;
                    }
                    int i66 = (i12 + i65) - 1;
                    boolean z14 = i66 < -3 || i66 >= 7;
                    if (z8 && !z6) {
                        i14--;
                    }
                    int i67 = 0;
                    while (true) {
                        int i68 = i14 / 10;
                        int i69 = i11 / 10;
                        if (i68 <= i69 || (i14 < 100 && z14)) {
                            break;
                        }
                        z7 &= i11 % 10 == 0;
                        i13 = i9 % 10;
                        i9 /= 10;
                        i67++;
                        i14 = i68;
                        i11 = i69;
                    }
                    if (z7 && z6) {
                        while (i11 % 10 == 0 && (i14 >= 100 || !z14)) {
                            i14 /= 10;
                            i13 = i9 % 10;
                            i9 /= 10;
                            i11 /= 10;
                            i67++;
                        }
                    }
                    if (z9 && i13 == 5 && i9 % 2 == 0) {
                        i13 = 4;
                    }
                    int i70 = i9 + (((i9 != i11 || (z7 && z6)) && i13 < 5) ? 0 : 1);
                    int i71 = i65 - i67;
                    if (z10) {
                        i15 = i7 + 1;
                        cArr[i7] = '-';
                    } else {
                        i15 = i7;
                    }
                    if (z14) {
                        for (int i72 = 0; i72 < i71 - 1; i72++) {
                            int i73 = i70 % 10;
                            i70 /= 10;
                            cArr[(i15 + i71) - i72] = (char) (i73 + 48);
                        }
                        cArr[i15] = (char) ((i70 % 10) + 48);
                        cArr[i15 + 1] = '.';
                        int i74 = i71 + 1 + i15;
                        if (i71 == 1) {
                            cArr[i74] = '0';
                            i74++;
                        }
                        int i75 = i74 + 1;
                        cArr[i74] = 'E';
                        if (i66 < 0) {
                            cArr[i75] = '-';
                            i66 = -i66;
                            i75++;
                        }
                        if (i66 >= 10) {
                            i17 = 48;
                            cArr[i75] = (char) ((i66 / 10) + 48);
                            i75++;
                        } else {
                            i17 = 48;
                        }
                        i16 = i75 + 1;
                        cArr[i75] = (char) ((i66 % 10) + i17);
                    } else {
                        int i76 = 48;
                        if (i66 < 0) {
                            int i77 = i15 + 1;
                            cArr[i15] = '0';
                            int i78 = i77 + 1;
                            cArr[i77] = '.';
                            int i79 = -1;
                            while (i79 > i66) {
                                cArr[i78] = '0';
                                i79--;
                                i78++;
                            }
                            int i80 = i78;
                            int i81 = 0;
                            while (i81 < i71) {
                                cArr[((i78 + i71) - i81) - 1] = (char) ((i70 % 10) + i76);
                                i70 /= 10;
                                i80++;
                                i81++;
                                i76 = 48;
                            }
                            i16 = i80;
                        } else {
                            int i82 = i66 + 1;
                            if (i82 >= i71) {
                                for (int i83 = 0; i83 < i71; i83++) {
                                    cArr[((i15 + i71) - i83) - 1] = (char) ((i70 % 10) + 48);
                                    i70 /= 10;
                                }
                                int i84 = i15 + i71;
                                while (i71 < i82) {
                                    cArr[i84] = '0';
                                    i71++;
                                    i84++;
                                }
                                int i85 = i84 + 1;
                                cArr[i84] = '.';
                                cArr[i85] = '0';
                                i16 = i85 + 1;
                            } else {
                                int i86 = i15 + 1;
                                for (int i87 = 0; i87 < i71; i87++) {
                                    if ((i71 - i87) - 1 == i66) {
                                        cArr[((i86 + i71) - i87) - 1] = '.';
                                        i86--;
                                    }
                                    cArr[((i86 + i71) - i87) - 1] = (char) ((i70 % 10) + 48);
                                    i70 /= 10;
                                }
                                i16 = i71 + 1 + i15;
                            }
                        }
                    }
                    return i16 - i7;
                }
                int i88 = i7 + 1;
                cArr[i7] = '0';
                int i89 = i88 + 1;
                cArr[i88] = '.';
                i21 = i89 + 1;
                cArr[i89] = '0';
            }
        }
        return i21 - i7;
    }
}
