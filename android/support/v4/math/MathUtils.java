package android.support.v4.math;

/* loaded from: classes.dex */
public class MathUtils {
    private MathUtils() {
    }

    public static double clamp(double d7, double d8, double d9) {
        return d7 < d8 ? d8 : d7 > d9 ? d9 : d7;
    }

    public static float clamp(float f7, float f8, float f9) {
        return f7 < f8 ? f8 : f7 > f9 ? f9 : f7;
    }

    public static int clamp(int i7, int i8, int i9) {
        return i7 < i8 ? i8 : i7 > i9 ? i9 : i7;
    }
}
