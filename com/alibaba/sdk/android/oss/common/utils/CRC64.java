package com.alibaba.sdk.android.oss.common.utils;

import java.lang.reflect.Array;
import java.util.zip.Checksum;

/* loaded from: classes.dex */
public class CRC64 implements Checksum {
    private static final int GF2_DIM = 64;
    private static final long POLY = -3932672073523589310L;
    private static final long[][] table = (long[][]) Array.newInstance((Class<?>) long.class, 8, 256);
    private long value = 0;

    static {
        for (int i7 = 0; i7 < 256; i7++) {
            long j7 = i7;
            for (int i8 = 0; i8 < 8; i8++) {
                j7 = (j7 & 1) == 1 ? (j7 >>> 1) ^ POLY : j7 >>> 1;
            }
            table[0][i7] = j7;
        }
        for (int i9 = 0; i9 < 256; i9++) {
            long j8 = table[0][i9];
            for (int i10 = 1; i10 < 8; i10++) {
                long[][] jArr = table;
                j8 = (j8 >>> 8) ^ jArr[0][(int) (255 & j8)];
                jArr[i10][i9] = j8;
            }
        }
    }

    public static long combine(long j7, long j8, long j9) {
        if (j9 == 0) {
            return j7;
        }
        long[] jArr = new long[64];
        long[] jArr2 = new long[64];
        jArr2[0] = -3932672073523589310L;
        long j10 = 1;
        for (int i7 = 1; i7 < 64; i7++) {
            jArr2[i7] = j10;
            j10 <<= 1;
        }
        gf2MatrixSquare(jArr, jArr2);
        gf2MatrixSquare(jArr2, jArr);
        long jGf2MatrixTimes = j7;
        long j11 = j9;
        do {
            gf2MatrixSquare(jArr, jArr2);
            if ((j11 & 1) == 1) {
                jGf2MatrixTimes = gf2MatrixTimes(jArr, jGf2MatrixTimes);
            }
            long j12 = j11 >>> 1;
            if (j12 == 0) {
                break;
            }
            gf2MatrixSquare(jArr2, jArr);
            if ((j12 & 1) == 1) {
                jGf2MatrixTimes = gf2MatrixTimes(jArr2, jGf2MatrixTimes);
            }
            j11 = j12 >>> 1;
        } while (j11 != 0);
        return jGf2MatrixTimes ^ j8;
    }

    private static void gf2MatrixSquare(long[] jArr, long[] jArr2) {
        for (int i7 = 0; i7 < 64; i7++) {
            jArr[i7] = gf2MatrixTimes(jArr2, jArr2[i7]);
        }
    }

    private static long gf2MatrixTimes(long[] jArr, long j7) {
        int i7 = 0;
        long j8 = 0;
        while (j7 != 0) {
            if ((j7 & 1) == 1) {
                j8 ^= jArr[i7];
            }
            j7 >>>= 1;
            i7++;
        }
        return j8;
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        return this.value;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.value = 0L;
    }

    @Override // java.util.zip.Checksum
    public void update(int i7) {
        update(new byte[]{(byte) (i7 & 255)}, 1);
    }

    public void update(byte[] bArr, int i7) {
        update(bArr, 0, i7);
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i7, int i8) {
        this.value = ~this.value;
        int i9 = i7;
        int i10 = i8;
        while (i10 >= 8) {
            long[][] jArr = table;
            long[] jArr2 = jArr[7];
            long j7 = this.value;
            this.value = ((((((jArr[6][(int) ((bArr[i9 + 1] & 255) ^ ((j7 >>> 8) & 255))] ^ jArr2[(int) ((j7 & 255) ^ (bArr[i9] & 255))]) ^ jArr[5][(int) (((j7 >>> 16) & 255) ^ (bArr[i9 + 2] & 255))]) ^ jArr[4][(int) (((j7 >>> 24) & 255) ^ (bArr[i9 + 3] & 255))]) ^ jArr[3][(int) (((j7 >>> 32) & 255) ^ (bArr[i9 + 4] & 255))]) ^ jArr[2][(int) (((j7 >>> 40) & 255) ^ (bArr[i9 + 5] & 255))]) ^ jArr[1][(int) ((255 & (j7 >>> 48)) ^ (bArr[i9 + 6] & 255))]) ^ jArr[0][(int) ((j7 >>> 56) ^ (bArr[i9 + 7] & 255))];
            i9 += 8;
            i10 -= 8;
        }
        while (i10 > 0) {
            long[] jArr3 = table[0];
            long j8 = this.value;
            this.value = (j8 >>> 8) ^ jArr3[(int) ((bArr[i9] ^ j8) & 255)];
            i9++;
            i10--;
        }
        this.value = ~this.value;
    }
}
