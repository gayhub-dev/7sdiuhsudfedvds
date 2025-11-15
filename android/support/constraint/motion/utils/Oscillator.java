package android.support.constraint.motion.utils;

import java.util.Arrays;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;
    public double[] mArea;
    public int mType;
    public float[] mPeriod = new float[0];
    public double[] mPosition = new double[0];
    public double PI2 = 6.283185307179586d;
    private boolean mNormalized = false;

    public void addPoint(double d7, float f7) {
        int length = this.mPeriod.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.mPosition, d7);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.mPosition = Arrays.copyOf(this.mPosition, length);
        this.mPeriod = Arrays.copyOf(this.mPeriod, length);
        this.mArea = new double[length];
        double[] dArr = this.mPosition;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.mPosition[iBinarySearch] = d7;
        this.mPeriod[iBinarySearch] = f7;
        this.mNormalized = false;
    }

    public double getDP(double d7) {
        if (d7 <= 0.0d) {
            d7 = 1.0E-5d;
        } else if (d7 >= 1.0d) {
            d7 = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.mPosition, d7);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i7 = (-iBinarySearch) - 1;
        float[] fArr = this.mPeriod;
        int i8 = i7 - 1;
        double d8 = fArr[i7] - fArr[i8];
        double[] dArr = this.mPosition;
        double d9 = d8 / (dArr[i7] - dArr[i8]);
        return (fArr[i8] - (d9 * dArr[i8])) + (d7 * d9);
    }

    public double getP(double d7) {
        if (d7 < 0.0d) {
            d7 = 0.0d;
        } else if (d7 > 1.0d) {
            d7 = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.mPosition, d7);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i7 = (-iBinarySearch) - 1;
        float[] fArr = this.mPeriod;
        int i8 = i7 - 1;
        double d8 = fArr[i7] - fArr[i8];
        double[] dArr = this.mPosition;
        double d9 = d8 / (dArr[i7] - dArr[i8]);
        return ((((d7 * d7) - (dArr[i8] * dArr[i8])) * d9) / 2.0d) + ((d7 - dArr[i8]) * (fArr[i8] - (dArr[i8] * d9))) + this.mArea[i8];
    }

    public double getSlope(double d7) {
        switch (this.mType) {
            case 1:
                return 0.0d;
            case 2:
                return Math.signum((((getP(d7) * 4.0d) + 3.0d) % 4.0d) - 2.0d) * getDP(d7) * 4.0d;
            case 3:
                return getDP(d7) * 2.0d;
            case 4:
                return (-getDP(d7)) * 2.0d;
            case 5:
                return Math.sin(getP(d7) * this.PI2) * getDP(d7) * (-this.PI2);
            case 6:
                return ((((getP(d7) * 4.0d) + 2.0d) % 4.0d) - 2.0d) * getDP(d7) * 4.0d;
            default:
                return Math.cos(getP(d7) * this.PI2) * getDP(d7) * this.PI2;
        }
    }

    public double getValue(double d7) {
        double dAbs;
        switch (this.mType) {
            case 1:
                return Math.signum(0.5d - (getP(d7) % 1.0d));
            case 2:
                dAbs = Math.abs((((getP(d7) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((getP(d7) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((getP(d7) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(getP(d7) * this.PI2);
            case 6:
                double dAbs2 = 1.0d - Math.abs(((getP(d7) * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            default:
                return Math.sin(getP(d7) * this.PI2);
        }
        return 1.0d - dAbs;
    }

    public void normalize() {
        double d7 = 0.0d;
        int i7 = 0;
        while (true) {
            if (i7 >= this.mPeriod.length) {
                break;
            }
            d7 += r7[i7];
            i7++;
        }
        double d8 = 0.0d;
        int i8 = 1;
        while (true) {
            float[] fArr = this.mPeriod;
            if (i8 >= fArr.length) {
                break;
            }
            int i9 = i8 - 1;
            float f7 = (fArr[i9] + fArr[i8]) / 2.0f;
            double[] dArr = this.mPosition;
            d8 += (dArr[i8] - dArr[i9]) * f7;
            i8++;
        }
        int i10 = 0;
        while (true) {
            float[] fArr2 = this.mPeriod;
            if (i10 >= fArr2.length) {
                break;
            }
            fArr2[i10] = (float) (fArr2[i10] * (d7 / d8));
            i10++;
        }
        this.mArea[0] = 0.0d;
        int i11 = 1;
        while (true) {
            float[] fArr3 = this.mPeriod;
            if (i11 >= fArr3.length) {
                this.mNormalized = true;
                return;
            }
            int i12 = i11 - 1;
            float f8 = (fArr3[i12] + fArr3[i11]) / 2.0f;
            double[] dArr2 = this.mPosition;
            double d9 = dArr2[i11] - dArr2[i12];
            double[] dArr3 = this.mArea;
            dArr3[i11] = (d9 * f8) + dArr3[i12];
            i11++;
        }
    }

    public void setType(int i7) {
        this.mType = i7;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("pos =");
        sbM112a.append(Arrays.toString(this.mPosition));
        sbM112a.append(" period=");
        sbM112a.append(Arrays.toString(this.mPeriod));
        return sbM112a.toString();
    }
}
