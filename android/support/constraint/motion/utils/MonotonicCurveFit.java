package android.support.constraint.motion.utils;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class MonotonicCurveFit extends CurveFit {
    private static final String TAG = "MonotonicCurveFit";

    /* renamed from: mT */
    private double[] f131mT;
    private double[][] mTangent;

    /* renamed from: mY */
    private double[][] f132mY;

    public MonotonicCurveFit(double[] dArr, double[][] dArr2) {
        int length = dArr.length;
        int length2 = dArr2[0].length;
        int i7 = length - 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) double.class, i7, length2);
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, length, length2);
        for (int i8 = 0; i8 < length2; i8++) {
            int i9 = 0;
            while (i9 < i7) {
                int i10 = i9 + 1;
                dArr3[i9][i8] = (dArr2[i10][i8] - dArr2[i9][i8]) / (dArr[i10] - dArr[i9]);
                if (i9 == 0) {
                    dArr4[i9][i8] = dArr3[i9][i8];
                } else {
                    dArr4[i9][i8] = (dArr3[i9 - 1][i8] + dArr3[i9][i8]) * 0.5d;
                }
                i9 = i10;
            }
            dArr4[i7][i8] = dArr3[length - 2][i8];
        }
        for (int i11 = 0; i11 < i7; i11++) {
            for (int i12 = 0; i12 < length2; i12++) {
                if (dArr3[i11][i12] == 0.0d) {
                    dArr4[i11][i12] = 0.0d;
                    dArr4[i11 + 1][i12] = 0.0d;
                } else {
                    double d7 = dArr4[i11][i12] / dArr3[i11][i12];
                    int i13 = i11 + 1;
                    double d8 = dArr4[i13][i12] / dArr3[i11][i12];
                    double dHypot = Math.hypot(d7, d8);
                    if (dHypot > 9.0d) {
                        double d9 = 3.0d / dHypot;
                        dArr4[i11][i12] = d7 * d9 * dArr3[i11][i12];
                        dArr4[i13][i12] = d9 * d8 * dArr3[i11][i12];
                    }
                }
            }
        }
        this.f131mT = dArr;
        this.f132mY = dArr2;
        this.mTangent = dArr4;
    }

    private static double diff(double d7, double d8, double d9, double d10, double d11, double d12) {
        double d13 = d8 * d8;
        double d14 = d8 * 6.0d;
        double d15 = 6.0d * d13 * d9;
        double d16 = 3.0d * d7;
        return (d7 * d11) + (((((d16 * d11) * d13) + (((d16 * d12) * d13) + ((d15 + ((d14 * d10) + (((-6.0d) * d13) * d10))) - (d14 * d9)))) - (((2.0d * d7) * d12) * d8)) - (((4.0d * d7) * d11) * d8));
    }

    private static double interpolate(double d7, double d8, double d9, double d10, double d11, double d12) {
        double d13 = d8 * d8;
        double d14 = d13 * d8;
        double d15 = 3.0d * d13;
        double d16 = d14 * 2.0d * d9;
        double d17 = ((d16 + ((d15 * d10) + (((-2.0d) * d14) * d10))) - (d15 * d9)) + d9;
        double d18 = d7 * d12;
        double d19 = (d18 * d14) + d17;
        double d20 = d7 * d11;
        return (d20 * d8) + ((((d14 * d20) + d19) - (d18 * d13)) - (((2.0d * d7) * d11) * d13));
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getPos(double d7, double[] dArr) {
        double[] dArr2 = this.f131mT;
        int length = dArr2.length;
        int i7 = 0;
        int length2 = this.f132mY[0].length;
        if (d7 <= dArr2[0]) {
            for (int i8 = 0; i8 < length2; i8++) {
                dArr[i8] = this.f132mY[0][i8];
            }
            return;
        }
        int i9 = length - 1;
        if (d7 >= dArr2[i9]) {
            while (i7 < length2) {
                dArr[i7] = this.f132mY[i9][i7];
                i7++;
            }
            return;
        }
        int i10 = 0;
        while (i10 < i9) {
            if (d7 == this.f131mT[i10]) {
                for (int i11 = 0; i11 < length2; i11++) {
                    dArr[i11] = this.f132mY[i10][i11];
                }
            }
            double[] dArr3 = this.f131mT;
            int i12 = i10 + 1;
            if (d7 < dArr3[i12]) {
                double d8 = dArr3[i12] - dArr3[i10];
                double d9 = (d7 - dArr3[i10]) / d8;
                while (i7 < length2) {
                    double[][] dArr4 = this.f132mY;
                    double d10 = dArr4[i10][i7];
                    double d11 = dArr4[i12][i7];
                    double[][] dArr5 = this.mTangent;
                    dArr[i7] = interpolate(d8, d9, d10, d11, dArr5[i10][i7], dArr5[i12][i7]);
                    i7++;
                }
                return;
            }
            i10 = i12;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getSlope(double d7, double[] dArr) {
        double d8;
        double[] dArr2 = this.f131mT;
        int length = dArr2.length;
        int length2 = this.f132mY[0].length;
        if (d7 <= dArr2[0]) {
            d8 = dArr2[0];
        } else {
            int i7 = length - 1;
            d8 = d7 >= dArr2[i7] ? dArr2[i7] : d7;
        }
        int i8 = 0;
        while (i8 < length - 1) {
            double[] dArr3 = this.f131mT;
            int i9 = i8 + 1;
            if (d8 <= dArr3[i9]) {
                double d9 = dArr3[i9] - dArr3[i8];
                double d10 = (d8 - dArr3[i8]) / d9;
                for (int i10 = 0; i10 < length2; i10++) {
                    double[][] dArr4 = this.f132mY;
                    double d11 = dArr4[i8][i10];
                    double d12 = dArr4[i9][i10];
                    double[][] dArr5 = this.mTangent;
                    dArr[i10] = diff(d9, d10, d11, d12, dArr5[i8][i10], dArr5[i9][i10]) / d9;
                }
                return;
            }
            i8 = i9;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.f131mT;
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double getSlope(double d7, int i7) {
        double d8;
        double[] dArr = this.f131mT;
        int length = dArr.length;
        int i8 = 0;
        if (d7 < dArr[0]) {
            d8 = dArr[0];
        } else {
            int i9 = length - 1;
            d8 = d7 >= dArr[i9] ? dArr[i9] : d7;
        }
        while (i8 < length - 1) {
            double[] dArr2 = this.f131mT;
            int i10 = i8 + 1;
            if (d8 <= dArr2[i10]) {
                double d9 = dArr2[i10] - dArr2[i8];
                double d10 = (d8 - dArr2[i8]) / d9;
                double[][] dArr3 = this.f132mY;
                double d11 = dArr3[i8][i7];
                double d12 = dArr3[i10][i7];
                double[][] dArr4 = this.mTangent;
                return diff(d9, d10, d11, d12, dArr4[i8][i7], dArr4[i10][i7]) / d9;
            }
            i8 = i10;
        }
        return 0.0d;
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getPos(double d7, float[] fArr) {
        double[] dArr = this.f131mT;
        int length = dArr.length;
        int i7 = 0;
        int length2 = this.f132mY[0].length;
        if (d7 <= dArr[0]) {
            for (int i8 = 0; i8 < length2; i8++) {
                fArr[i8] = (float) this.f132mY[0][i8];
            }
            return;
        }
        int i9 = length - 1;
        if (d7 >= dArr[i9]) {
            while (i7 < length2) {
                fArr[i7] = (float) this.f132mY[i9][i7];
                i7++;
            }
            return;
        }
        int i10 = 0;
        while (i10 < i9) {
            if (d7 == this.f131mT[i10]) {
                for (int i11 = 0; i11 < length2; i11++) {
                    fArr[i11] = (float) this.f132mY[i10][i11];
                }
            }
            double[] dArr2 = this.f131mT;
            int i12 = i10 + 1;
            if (d7 < dArr2[i12]) {
                double d8 = dArr2[i12] - dArr2[i10];
                double d9 = (d7 - dArr2[i10]) / d8;
                while (i7 < length2) {
                    double[][] dArr3 = this.f132mY;
                    double d10 = dArr3[i10][i7];
                    double d11 = dArr3[i12][i7];
                    double[][] dArr4 = this.mTangent;
                    fArr[i7] = (float) interpolate(d8, d9, d10, d11, dArr4[i10][i7], dArr4[i12][i7]);
                    i7++;
                }
                return;
            }
            i10 = i12;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double getPos(double d7, int i7) {
        double[] dArr = this.f131mT;
        int length = dArr.length;
        int i8 = 0;
        if (d7 <= dArr[0]) {
            return this.f132mY[0][i7];
        }
        int i9 = length - 1;
        if (d7 >= dArr[i9]) {
            return this.f132mY[i9][i7];
        }
        while (i8 < i9) {
            double[] dArr2 = this.f131mT;
            if (d7 == dArr2[i8]) {
                return this.f132mY[i8][i7];
            }
            int i10 = i8 + 1;
            if (d7 < dArr2[i10]) {
                double d8 = dArr2[i10] - dArr2[i8];
                double d9 = (d7 - dArr2[i8]) / d8;
                double[][] dArr3 = this.f132mY;
                double d10 = dArr3[i8][i7];
                double d11 = dArr3[i10][i7];
                double[][] dArr4 = this.mTangent;
                return interpolate(d8, d9, d10, d11, dArr4[i8][i7], dArr4[i10][i7]);
            }
            i8 = i10;
        }
        return 0.0d;
    }
}
