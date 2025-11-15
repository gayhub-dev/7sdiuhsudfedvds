package android.support.constraint.motion.utils;

import java.util.Arrays;

/* loaded from: classes.dex */
public class Easing {
    private static final String ACCELERATE = "cubic(0.4, 0.05, 0.8, 0.7)";
    private static final String DECELERATE = "cubic(0.0, 0.0, 0.2, 0.95)";
    private static final String LINEAR = "cubic(1, 1, 0, 0)";
    private static final String STANDARD = "cubic(0.4, 0.0, 0.2, 1)";
    public String str = "identity";
    public static Easing sDefault = new Easing();
    private static final String STANDARD_NAME = "standard";
    private static final String ACCELERATE_NAME = "accelerate";
    private static final String DECELERATE_NAME = "decelerate";
    private static final String LINEAR_NAME = "linear";
    public static String[] NAMED_EASING = {STANDARD_NAME, ACCELERATE_NAME, DECELERATE_NAME, LINEAR_NAME};

    public static Easing getInterpolator(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new CubicEasing(str);
        }
        switch (str) {
            case "accelerate":
                return new CubicEasing(ACCELERATE);
            case "decelerate":
                return new CubicEasing(DECELERATE);
            case "linear":
                return new CubicEasing(LINEAR);
            case "standard":
                return new CubicEasing(STANDARD);
            default:
                Arrays.toString(NAMED_EASING);
                return sDefault;
        }
    }

    public double get(double d7) {
        return d7;
    }

    public double getDiff(double d7) {
        return 1.0d;
    }

    public String toString() {
        return this.str;
    }

    public static class CubicEasing extends Easing {
        private static double d_error = 1.0E-4d;
        private static double error = 0.01d;

        /* renamed from: x1 */
        public double f121x1;

        /* renamed from: x2 */
        public double f122x2;

        /* renamed from: y1 */
        public double f123y1;

        /* renamed from: y2 */
        public double f124y2;

        public CubicEasing(String str) {
            this.str = str;
            int iIndexOf = str.indexOf(40);
            int iIndexOf2 = str.indexOf(44, iIndexOf);
            this.f121x1 = Double.parseDouble(str.substring(iIndexOf + 1, iIndexOf2).trim());
            int i7 = iIndexOf2 + 1;
            int iIndexOf3 = str.indexOf(44, i7);
            this.f123y1 = Double.parseDouble(str.substring(i7, iIndexOf3).trim());
            int i8 = iIndexOf3 + 1;
            int iIndexOf4 = str.indexOf(44, i8);
            this.f122x2 = Double.parseDouble(str.substring(i8, iIndexOf4).trim());
            int i9 = iIndexOf4 + 1;
            this.f124y2 = Double.parseDouble(str.substring(i9, str.indexOf(41, i9)).trim());
        }

        private double getDiffX(double d7) {
            double d8 = 1.0d - d7;
            double d9 = this.f121x1;
            double d10 = d8 * 3.0d * d8 * d9;
            double d11 = this.f122x2;
            return ((1.0d - d11) * 3.0d * d7 * d7) + ((d11 - d9) * d8 * 6.0d * d7) + d10;
        }

        private double getDiffY(double d7) {
            double d8 = 1.0d - d7;
            double d9 = this.f123y1;
            double d10 = d8 * 3.0d * d8 * d9;
            double d11 = this.f124y2;
            return ((1.0d - d11) * 3.0d * d7 * d7) + ((d11 - d9) * d8 * 6.0d * d7) + d10;
        }

        private double getX(double d7) {
            double d8 = 1.0d - d7;
            double d9 = 3.0d * d8;
            double d10 = d8 * d9 * d7;
            double d11 = d9 * d7 * d7;
            return (this.f122x2 * d11) + (this.f121x1 * d10) + (d7 * d7 * d7);
        }

        private double getY(double d7) {
            double d8 = 1.0d - d7;
            double d9 = 3.0d * d8;
            double d10 = d8 * d9 * d7;
            double d11 = d9 * d7 * d7;
            return (this.f124y2 * d11) + (this.f123y1 * d10) + (d7 * d7 * d7);
        }

        @Override // android.support.constraint.motion.utils.Easing
        public double get(double d7) {
            if (d7 <= 0.0d) {
                return 0.0d;
            }
            if (d7 >= 1.0d) {
                return 1.0d;
            }
            double d8 = 0.5d;
            double d9 = 0.5d;
            while (d8 > error) {
                d8 *= 0.5d;
                d9 = getX(d9) < d7 ? d9 + d8 : d9 - d8;
            }
            double d10 = d9 - d8;
            double x6 = getX(d10);
            double d11 = d9 + d8;
            double x7 = getX(d11);
            double y6 = getY(d10);
            return (((d7 - x6) * (getY(d11) - y6)) / (x7 - x6)) + y6;
        }

        @Override // android.support.constraint.motion.utils.Easing
        public double getDiff(double d7) {
            double d8 = 0.5d;
            double d9 = 0.5d;
            while (d8 > d_error) {
                d8 *= 0.5d;
                d9 = getX(d9) < d7 ? d9 + d8 : d9 - d8;
            }
            double d10 = d9 - d8;
            double d11 = d9 + d8;
            return (getY(d11) - getY(d10)) / (getX(d11) - getX(d10));
        }

        public void setup(double d7, double d8, double d9, double d10) {
            this.f121x1 = d7;
            this.f123y1 = d8;
            this.f122x2 = d9;
            this.f124y2 = d10;
        }

        public CubicEasing(double d7, double d8, double d9, double d10) {
            setup(d7, d8, d9, d10);
        }
    }
}
