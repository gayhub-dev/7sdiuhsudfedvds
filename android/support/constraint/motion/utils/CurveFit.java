package android.support.constraint.motion.utils;

/* loaded from: classes.dex */
public abstract class CurveFit {
    public static final int CONSTANT = 2;
    public static final int LINEAR = 1;
    public static final int SPLINE = 0;

    public static class Constant extends CurveFit {
        public double mTime;
        public double[] mValue;

        public Constant(double d7, double[] dArr) {
            this.mTime = d7;
            this.mValue = dArr;
        }

        @Override // android.support.constraint.motion.utils.CurveFit
        public void getPos(double d7, double[] dArr) {
            double[] dArr2 = this.mValue;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // android.support.constraint.motion.utils.CurveFit
        public double getSlope(double d7, int i7) {
            return 0.0d;
        }

        @Override // android.support.constraint.motion.utils.CurveFit
        public void getSlope(double d7, double[] dArr) {
            for (int i7 = 0; i7 < this.mValue.length; i7++) {
                dArr[i7] = 0.0d;
            }
        }

        @Override // android.support.constraint.motion.utils.CurveFit
        public double[] getTimePoints() {
            return new double[]{this.mTime};
        }

        @Override // android.support.constraint.motion.utils.CurveFit
        public void getPos(double d7, float[] fArr) {
            int i7 = 0;
            while (true) {
                double[] dArr = this.mValue;
                if (i7 >= dArr.length) {
                    return;
                }
                fArr[i7] = (float) dArr[i7];
                i7++;
            }
        }

        @Override // android.support.constraint.motion.utils.CurveFit
        public double getPos(double d7, int i7) {
            return this.mValue[i7];
        }
    }

    public static CurveFit get(int i7, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i7 = 2;
        }
        return i7 != 0 ? i7 != 2 ? new LinearCurveFit(dArr, dArr2) : new Constant(dArr[0], dArr2[0]) : new MonotonicCurveFit(dArr, dArr2);
    }

    public static CurveFit getArc(int[] iArr, double[] dArr, double[][] dArr2) {
        return new ArcCurveFit(iArr, dArr, dArr2);
    }

    public abstract double getPos(double d7, int i7);

    public abstract void getPos(double d7, double[] dArr);

    public abstract void getPos(double d7, float[] fArr);

    public abstract double getSlope(double d7, int i7);

    public abstract void getSlope(double d7, double[] dArr);

    public abstract double[] getTimePoints();
}
