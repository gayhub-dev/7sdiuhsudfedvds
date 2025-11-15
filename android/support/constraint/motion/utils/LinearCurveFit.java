package android.support.constraint.motion.utils;

/* loaded from: classes.dex */
public class LinearCurveFit extends CurveFit {
    private static final String TAG = "LinearCurveFit";

    /* renamed from: mT */
    private double[] f129mT;
    private double mTotalLength;

    /* renamed from: mY */
    private double[][] f130mY;

    public LinearCurveFit(double[] dArr, double[][] dArr2) {
        this.mTotalLength = Double.NaN;
        int length = dArr.length;
        int length2 = dArr2[0].length;
        this.f129mT = dArr;
        this.f130mY = dArr2;
        if (length2 > 2) {
            double d7 = 0.0d;
            double d8 = 0.0d;
            int i7 = 0;
            while (i7 < dArr.length) {
                double d9 = dArr2[i7][0];
                double d10 = dArr2[i7][0];
                if (i7 > 0) {
                    Math.hypot(d9 - d7, d10 - d8);
                }
                i7++;
                d7 = d9;
                d8 = d10;
            }
            this.mTotalLength = 0.0d;
        }
    }

    private double getLength2D(double d7) {
        if (Double.isNaN(this.mTotalLength)) {
            return 0.0d;
        }
        double[] dArr = this.f129mT;
        int length = dArr.length;
        if (d7 <= dArr[0]) {
            return 0.0d;
        }
        int i7 = length - 1;
        if (d7 >= dArr[i7]) {
            return this.mTotalLength;
        }
        double dHypot = 0.0d;
        double d8 = 0.0d;
        double d9 = 0.0d;
        int i8 = 0;
        while (i8 < i7) {
            double[][] dArr2 = this.f130mY;
            double d10 = dArr2[i8][0];
            double d11 = dArr2[i8][1];
            if (i8 > 0) {
                dHypot += Math.hypot(d10 - d8, d11 - d9);
            }
            double[] dArr3 = this.f129mT;
            if (d7 == dArr3[i8]) {
                return dHypot;
            }
            int i9 = i8 + 1;
            if (d7 < dArr3[i9]) {
                double d12 = (d7 - dArr3[i8]) / (dArr3[i9] - dArr3[i8]);
                double[][] dArr4 = this.f130mY;
                double d13 = dArr4[i8][0];
                double d14 = dArr4[i9][0];
                double d15 = 1.0d - d12;
                return Math.hypot(d11 - ((dArr4[i9][1] * d12) + (dArr4[i8][1] * d15)), d10 - ((d14 * d12) + (d13 * d15))) + dHypot;
            }
            i8 = i9;
            d8 = d10;
            d9 = d11;
        }
        return 0.0d;
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getPos(double d7, double[] dArr) {
        double[] dArr2 = this.f129mT;
        int length = dArr2.length;
        int i7 = 0;
        int length2 = this.f130mY[0].length;
        if (d7 <= dArr2[0]) {
            for (int i8 = 0; i8 < length2; i8++) {
                dArr[i8] = this.f130mY[0][i8];
            }
            return;
        }
        int i9 = length - 1;
        if (d7 >= dArr2[i9]) {
            while (i7 < length2) {
                dArr[i7] = this.f130mY[i9][i7];
                i7++;
            }
            return;
        }
        int i10 = 0;
        while (i10 < i9) {
            if (d7 == this.f129mT[i10]) {
                for (int i11 = 0; i11 < length2; i11++) {
                    dArr[i11] = this.f130mY[i10][i11];
                }
            }
            double[] dArr3 = this.f129mT;
            int i12 = i10 + 1;
            if (d7 < dArr3[i12]) {
                double d8 = (d7 - dArr3[i10]) / (dArr3[i12] - dArr3[i10]);
                while (i7 < length2) {
                    double[][] dArr4 = this.f130mY;
                    dArr[i7] = (dArr4[i12][i7] * d8) + ((1.0d - d8) * dArr4[i10][i7]);
                    i7++;
                }
                return;
            }
            i10 = i12;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getSlope(double d7, double[] dArr) {
        double[] dArr2 = this.f129mT;
        int length = dArr2.length;
        int length2 = this.f130mY[0].length;
        if (d7 <= dArr2[0]) {
            d7 = dArr2[0];
        } else {
            int i7 = length - 1;
            if (d7 >= dArr2[i7]) {
                d7 = dArr2[i7];
            }
        }
        int i8 = 0;
        while (i8 < length - 1) {
            double[] dArr3 = this.f129mT;
            int i9 = i8 + 1;
            if (d7 <= dArr3[i9]) {
                double d8 = dArr3[i9] - dArr3[i8];
                double d9 = dArr3[i8];
                for (int i10 = 0; i10 < length2; i10++) {
                    double[][] dArr4 = this.f130mY;
                    dArr[i10] = (dArr4[i9][i10] - dArr4[i8][i10]) / d8;
                }
                return;
            }
            i8 = i9;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.f129mT;
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double getSlope(double d7, int i7) {
        double[] dArr = this.f129mT;
        int length = dArr.length;
        int i8 = 0;
        if (d7 < dArr[0]) {
            d7 = dArr[0];
        } else {
            int i9 = length - 1;
            if (d7 >= dArr[i9]) {
                d7 = dArr[i9];
            }
        }
        while (i8 < length - 1) {
            double[] dArr2 = this.f129mT;
            int i10 = i8 + 1;
            if (d7 <= dArr2[i10]) {
                double d8 = dArr2[i10] - dArr2[i8];
                double d9 = dArr2[i8];
                double[][] dArr3 = this.f130mY;
                return (dArr3[i10][i7] - dArr3[i8][i7]) / d8;
            }
            i8 = i10;
        }
        return 0.0d;
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getPos(double d7, float[] fArr) {
        double[] dArr = this.f129mT;
        int length = dArr.length;
        int i7 = 0;
        int length2 = this.f130mY[0].length;
        if (d7 <= dArr[0]) {
            for (int i8 = 0; i8 < length2; i8++) {
                fArr[i8] = (float) this.f130mY[0][i8];
            }
            return;
        }
        int i9 = length - 1;
        if (d7 >= dArr[i9]) {
            while (i7 < length2) {
                fArr[i7] = (float) this.f130mY[i9][i7];
                i7++;
            }
            return;
        }
        int i10 = 0;
        while (i10 < i9) {
            if (d7 == this.f129mT[i10]) {
                for (int i11 = 0; i11 < length2; i11++) {
                    fArr[i11] = (float) this.f130mY[i10][i11];
                }
            }
            double[] dArr2 = this.f129mT;
            int i12 = i10 + 1;
            if (d7 < dArr2[i12]) {
                double d8 = (d7 - dArr2[i10]) / (dArr2[i12] - dArr2[i10]);
                while (i7 < length2) {
                    double[][] dArr3 = this.f130mY;
                    fArr[i7] = (float) ((dArr3[i12][i7] * d8) + ((1.0d - d8) * dArr3[i10][i7]));
                    i7++;
                }
                return;
            }
            i10 = i12;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double getPos(double d7, int i7) {
        double[] dArr = this.f129mT;
        int length = dArr.length;
        int i8 = 0;
        if (d7 <= dArr[0]) {
            return this.f130mY[0][i7];
        }
        int i9 = length - 1;
        if (d7 >= dArr[i9]) {
            return this.f130mY[i9][i7];
        }
        while (i8 < i9) {
            double[] dArr2 = this.f129mT;
            if (d7 == dArr2[i8]) {
                return this.f130mY[i8][i7];
            }
            int i10 = i8 + 1;
            if (d7 < dArr2[i10]) {
                double d8 = (d7 - dArr2[i8]) / (dArr2[i10] - dArr2[i8]);
                double[][] dArr3 = this.f130mY;
                return (dArr3[i10][i7] * d8) + ((1.0d - d8) * dArr3[i8][i7]);
            }
            i8 = i10;
        }
        return 0.0d;
    }
}
