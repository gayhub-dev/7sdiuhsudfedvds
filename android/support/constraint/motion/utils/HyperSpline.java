package android.support.constraint.motion.utils;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class HyperSpline {
    public double[][] mCtl;
    public Cubic[][] mCurve;
    public double[] mCurveLength;
    public int mDimensionality;
    public int mPoints;
    public double mTotalLength;

    public static class Cubic {
        public static final double HALF = 0.5d;
        public static final double THIRD = 0.3333333333333333d;

        /* renamed from: mA */
        public double f125mA;

        /* renamed from: mB */
        public double f126mB;

        /* renamed from: mC */
        public double f127mC;

        /* renamed from: mD */
        public double f128mD;

        public Cubic(double d7, double d8, double d9, double d10) {
            this.f125mA = d7;
            this.f126mB = d8;
            this.f127mC = d9;
            this.f128mD = d10;
        }

        public double eval(double d7) {
            return (((((this.f128mD * d7) + this.f127mC) * d7) + this.f126mB) * d7) + this.f125mA;
        }

        public double vel(double d7) {
            return (((this.f127mC * 0.5d) + (this.f128mD * 0.3333333333333333d * d7)) * d7) + this.f126mB;
        }
    }

    public HyperSpline(double[][] dArr) {
        setup(dArr);
    }

    public static Cubic[] calcNaturalCubic(int i7, double[] dArr) {
        double[] dArr2 = new double[i7];
        double[] dArr3 = new double[i7];
        double[] dArr4 = new double[i7];
        int i8 = i7 - 1;
        int i9 = 0;
        dArr2[0] = 0.5d;
        int i10 = 1;
        for (int i11 = 1; i11 < i8; i11++) {
            dArr2[i11] = 1.0d / (4.0d - dArr2[i11 - 1]);
        }
        int i12 = i8 - 1;
        dArr2[i8] = 1.0d / (2.0d - dArr2[i12]);
        dArr3[0] = (dArr[1] - dArr[0]) * 3.0d * dArr2[0];
        while (i10 < i8) {
            int i13 = i10 + 1;
            int i14 = i10 - 1;
            dArr3[i10] = (((dArr[i13] - dArr[i14]) * 3.0d) - dArr3[i14]) * dArr2[i10];
            i10 = i13;
        }
        dArr3[i8] = (((dArr[i8] - dArr[i12]) * 3.0d) - dArr3[i12]) * dArr2[i8];
        dArr4[i8] = dArr3[i8];
        while (i12 >= 0) {
            dArr4[i12] = dArr3[i12] - (dArr2[i12] * dArr4[i12 + 1]);
            i12--;
        }
        Cubic[] cubicArr = new Cubic[i8];
        while (i9 < i8) {
            int i15 = i9 + 1;
            cubicArr[i9] = new Cubic((float) dArr[i9], dArr4[i9], (((dArr[i15] - dArr[i9]) * 3.0d) - (dArr4[i9] * 2.0d)) - dArr4[i15], ((dArr[i9] - dArr[i15]) * 2.0d) + dArr4[i9] + dArr4[i15]);
            i9 = i15;
        }
        return cubicArr;
    }

    public double approxLength(Cubic[] cubicArr) {
        int i7;
        int length = cubicArr.length;
        double[] dArr = new double[cubicArr.length];
        double d7 = 0.0d;
        double d8 = 0.0d;
        double dSqrt = 0.0d;
        while (true) {
            i7 = 0;
            if (d8 >= 1.0d) {
                break;
            }
            double d9 = 0.0d;
            while (i7 < cubicArr.length) {
                double d10 = dArr[i7];
                double dEval = cubicArr[i7].eval(d8);
                dArr[i7] = dEval;
                double d11 = d10 - dEval;
                d9 += d11 * d11;
                i7++;
            }
            if (d8 > 0.0d) {
                dSqrt += Math.sqrt(d9);
            }
            d8 += 0.1d;
        }
        while (i7 < cubicArr.length) {
            double d12 = dArr[i7];
            double dEval2 = cubicArr[i7].eval(1.0d);
            dArr[i7] = dEval2;
            double d13 = d12 - dEval2;
            d7 += d13 * d13;
            i7++;
        }
        return Math.sqrt(d7) + dSqrt;
    }

    public void getPos(double d7, double[] dArr) {
        double d8 = d7 * this.mTotalLength;
        int i7 = 0;
        while (true) {
            double[] dArr2 = this.mCurveLength;
            if (i7 >= dArr2.length - 1 || dArr2[i7] >= d8) {
                break;
            }
            d8 -= dArr2[i7];
            i7++;
        }
        for (int i8 = 0; i8 < dArr.length; i8++) {
            dArr[i8] = this.mCurve[i8][i7].eval(d8 / this.mCurveLength[i7]);
        }
    }

    public void getVelocity(double d7, double[] dArr) {
        double d8 = d7 * this.mTotalLength;
        int i7 = 0;
        while (true) {
            double[] dArr2 = this.mCurveLength;
            if (i7 >= dArr2.length - 1 || dArr2[i7] >= d8) {
                break;
            }
            d8 -= dArr2[i7];
            i7++;
        }
        for (int i8 = 0; i8 < dArr.length; i8++) {
            dArr[i8] = this.mCurve[i8][i7].vel(d8 / this.mCurveLength[i7]);
        }
    }

    public void setup(double[][] dArr) {
        int i7;
        int length = dArr[0].length;
        this.mDimensionality = length;
        int length2 = dArr.length;
        this.mPoints = length2;
        this.mCtl = (double[][]) Array.newInstance((Class<?>) double.class, length, length2);
        this.mCurve = new Cubic[this.mDimensionality][];
        for (int i8 = 0; i8 < this.mDimensionality; i8++) {
            for (int i9 = 0; i9 < this.mPoints; i9++) {
                this.mCtl[i8][i9] = dArr[i9][i8];
            }
        }
        int i10 = 0;
        while (true) {
            i7 = this.mDimensionality;
            if (i10 >= i7) {
                break;
            }
            Cubic[][] cubicArr = this.mCurve;
            double[][] dArr2 = this.mCtl;
            cubicArr[i10] = calcNaturalCubic(dArr2[i10].length, dArr2[i10]);
            i10++;
        }
        this.mCurveLength = new double[this.mPoints - 1];
        this.mTotalLength = 0.0d;
        Cubic[] cubicArr2 = new Cubic[i7];
        for (int i11 = 0; i11 < this.mCurveLength.length; i11++) {
            for (int i12 = 0; i12 < this.mDimensionality; i12++) {
                cubicArr2[i12] = this.mCurve[i12][i11];
            }
            double d7 = this.mTotalLength;
            double[] dArr3 = this.mCurveLength;
            double dApproxLength = approxLength(cubicArr2);
            dArr3[i11] = dApproxLength;
            this.mTotalLength = d7 + dApproxLength;
        }
    }

    public HyperSpline() {
    }

    public void getPos(double d7, float[] fArr) {
        double d8 = d7 * this.mTotalLength;
        int i7 = 0;
        while (true) {
            double[] dArr = this.mCurveLength;
            if (i7 >= dArr.length - 1 || dArr[i7] >= d8) {
                break;
            }
            d8 -= dArr[i7];
            i7++;
        }
        for (int i8 = 0; i8 < fArr.length; i8++) {
            fArr[i8] = (float) this.mCurve[i8][i7].eval(d8 / this.mCurveLength[i7]);
        }
    }

    public double getPos(double d7, int i7) {
        double[] dArr;
        double d8 = d7 * this.mTotalLength;
        int i8 = 0;
        while (true) {
            dArr = this.mCurveLength;
            if (i8 >= dArr.length - 1 || dArr[i8] >= d8) {
                break;
            }
            d8 -= dArr[i8];
            i8++;
        }
        return this.mCurve[i7][i8].eval(d8 / dArr[i8]);
    }
}
