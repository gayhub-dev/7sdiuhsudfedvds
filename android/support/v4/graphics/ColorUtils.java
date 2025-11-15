package android.support.v4.graphics;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.view.ViewCompat;
import java.util.Objects;
import p009b.C0413b;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes.dex */
public final class ColorUtils {
    private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
    private static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal<>();
    private static final double XYZ_EPSILON = 0.008856d;
    private static final double XYZ_KAPPA = 903.3d;
    private static final double XYZ_WHITE_REFERENCE_X = 95.047d;
    private static final double XYZ_WHITE_REFERENCE_Y = 100.0d;
    private static final double XYZ_WHITE_REFERENCE_Z = 108.883d;

    private ColorUtils() {
    }

    @ColorInt
    public static int HSLToColor(@NonNull float[] fArr) {
        int iRound;
        int iRound2;
        int iRound3;
        float f7 = fArr[0];
        float f8 = fArr[1];
        float f9 = fArr[2];
        float fAbs = (1.0f - Math.abs((f9 * 2.0f) - 1.0f)) * f8;
        float f10 = f9 - (0.5f * fAbs);
        float fAbs2 = (1.0f - Math.abs(((f7 / 60.0f) % 2.0f) - 1.0f)) * fAbs;
        switch (((int) f7) / 60) {
            case 0:
                iRound = Math.round((fAbs + f10) * 255.0f);
                iRound2 = Math.round((fAbs2 + f10) * 255.0f);
                iRound3 = Math.round(f10 * 255.0f);
                break;
            case 1:
                iRound = Math.round((fAbs2 + f10) * 255.0f);
                iRound2 = Math.round((fAbs + f10) * 255.0f);
                iRound3 = Math.round(f10 * 255.0f);
                break;
            case 2:
                iRound = Math.round(f10 * 255.0f);
                iRound2 = Math.round((fAbs + f10) * 255.0f);
                iRound3 = Math.round((fAbs2 + f10) * 255.0f);
                break;
            case 3:
                iRound = Math.round(f10 * 255.0f);
                iRound2 = Math.round((fAbs2 + f10) * 255.0f);
                iRound3 = Math.round((fAbs + f10) * 255.0f);
                break;
            case 4:
                iRound = Math.round((fAbs2 + f10) * 255.0f);
                iRound2 = Math.round(f10 * 255.0f);
                iRound3 = Math.round((fAbs + f10) * 255.0f);
                break;
            case 5:
            case 6:
                iRound = Math.round((fAbs + f10) * 255.0f);
                iRound2 = Math.round(f10 * 255.0f);
                iRound3 = Math.round((fAbs2 + f10) * 255.0f);
                break;
            default:
                iRound3 = 0;
                iRound = 0;
                iRound2 = 0;
                break;
        }
        return Color.rgb(constrain(iRound, 0, 255), constrain(iRound2, 0, 255), constrain(iRound3, 0, 255));
    }

    @ColorInt
    public static int LABToColor(@FloatRange(from = 0.0d, m90to = XYZ_WHITE_REFERENCE_Y) double d7, @FloatRange(from = -128.0d, m90to = 127.0d) double d8, @FloatRange(from = -128.0d, m90to = 127.0d) double d9) {
        double[] tempDouble3Array = getTempDouble3Array();
        LABToXYZ(d7, d8, d9, tempDouble3Array);
        return XYZToColor(tempDouble3Array[0], tempDouble3Array[1], tempDouble3Array[2]);
    }

    public static void LABToXYZ(@FloatRange(from = 0.0d, m90to = XYZ_WHITE_REFERENCE_Y) double d7, @FloatRange(from = -128.0d, m90to = 127.0d) double d8, @FloatRange(from = -128.0d, m90to = 127.0d) double d9, @NonNull double[] dArr) {
        double d10 = (d7 + 16.0d) / 116.0d;
        double d11 = (d8 / 500.0d) + d10;
        double d12 = d10 - (d9 / 200.0d);
        double dPow = Math.pow(d11, 3.0d);
        if (dPow <= XYZ_EPSILON) {
            dPow = ((d11 * 116.0d) - 16.0d) / XYZ_KAPPA;
        }
        double dPow2 = d7 > 7.9996247999999985d ? Math.pow(d10, 3.0d) : d7 / XYZ_KAPPA;
        double dPow3 = Math.pow(d12, 3.0d);
        if (dPow3 <= XYZ_EPSILON) {
            dPow3 = ((d12 * 116.0d) - 16.0d) / XYZ_KAPPA;
        }
        dArr[0] = dPow * XYZ_WHITE_REFERENCE_X;
        dArr[1] = dPow2 * XYZ_WHITE_REFERENCE_Y;
        dArr[2] = dPow3 * XYZ_WHITE_REFERENCE_Z;
    }

    public static void RGBToHSL(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i7, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i8, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i9, @NonNull float[] fArr) {
        float f7;
        float fAbs;
        float f8 = i7 / 255.0f;
        float f9 = i8 / 255.0f;
        float f10 = i9 / 255.0f;
        float fMax = Math.max(f8, Math.max(f9, f10));
        float fMin = Math.min(f8, Math.min(f9, f10));
        float f11 = fMax - fMin;
        float f12 = (fMax + fMin) / 2.0f;
        if (fMax == fMin) {
            f7 = 0.0f;
            fAbs = 0.0f;
        } else {
            f7 = fMax == f8 ? ((f9 - f10) / f11) % 6.0f : fMax == f9 ? ((f10 - f8) / f11) + 2.0f : 4.0f + ((f8 - f9) / f11);
            fAbs = f11 / (1.0f - Math.abs((2.0f * f12) - 1.0f));
        }
        float f13 = (f7 * 60.0f) % 360.0f;
        if (f13 < 0.0f) {
            f13 += 360.0f;
        }
        fArr[0] = constrain(f13, 0.0f, 360.0f);
        fArr[1] = constrain(fAbs, 0.0f, 1.0f);
        fArr[2] = constrain(f12, 0.0f, 1.0f);
    }

    public static void RGBToLAB(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i7, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i8, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i9, @NonNull double[] dArr) {
        RGBToXYZ(i7, i8, i9, dArr);
        XYZToLAB(dArr[0], dArr[1], dArr[2], dArr);
    }

    public static void RGBToXYZ(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i7, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i8, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i9, @NonNull double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d7 = i7 / 255.0d;
        double dPow = d7 < 0.04045d ? d7 / 12.92d : Math.pow((d7 + 0.055d) / 1.055d, 2.4d);
        double d8 = i8 / 255.0d;
        double dPow2 = d8 < 0.04045d ? d8 / 12.92d : Math.pow((d8 + 0.055d) / 1.055d, 2.4d);
        double d9 = i9 / 255.0d;
        double dPow3 = d9 < 0.04045d ? d9 / 12.92d : Math.pow((d9 + 0.055d) / 1.055d, 2.4d);
        dArr[0] = ((0.1805d * dPow3) + (0.3576d * dPow2) + (0.4124d * dPow)) * XYZ_WHITE_REFERENCE_Y;
        dArr[1] = ((0.0722d * dPow3) + (0.7152d * dPow2) + (0.2126d * dPow)) * XYZ_WHITE_REFERENCE_Y;
        dArr[2] = ((dPow3 * 0.9505d) + (dPow2 * 0.1192d) + (dPow * 0.0193d)) * XYZ_WHITE_REFERENCE_Y;
    }

    @ColorInt
    public static int XYZToColor(@FloatRange(from = 0.0d, m90to = XYZ_WHITE_REFERENCE_X) double d7, @FloatRange(from = 0.0d, m90to = XYZ_WHITE_REFERENCE_Y) double d8, @FloatRange(from = 0.0d, m90to = XYZ_WHITE_REFERENCE_Z) double d9) {
        double d10 = (((-0.4986d) * d9) + (((-1.5372d) * d8) + (3.2406d * d7))) / XYZ_WHITE_REFERENCE_Y;
        double d11 = ((0.0415d * d9) + ((1.8758d * d8) + ((-0.9689d) * d7))) / XYZ_WHITE_REFERENCE_Y;
        double d12 = ((1.057d * d9) + (((-0.204d) * d8) + (0.0557d * d7))) / XYZ_WHITE_REFERENCE_Y;
        return Color.rgb(constrain((int) Math.round((d10 > 0.0031308d ? (Math.pow(d10, 0.4166666666666667d) * 1.055d) - 0.055d : d10 * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d11 > 0.0031308d ? (Math.pow(d11, 0.4166666666666667d) * 1.055d) - 0.055d : d11 * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d12 > 0.0031308d ? (Math.pow(d12, 0.4166666666666667d) * 1.055d) - 0.055d : d12 * 12.92d) * 255.0d), 0, 255));
    }

    public static void XYZToLAB(@FloatRange(from = 0.0d, m90to = XYZ_WHITE_REFERENCE_X) double d7, @FloatRange(from = 0.0d, m90to = XYZ_WHITE_REFERENCE_Y) double d8, @FloatRange(from = 0.0d, m90to = XYZ_WHITE_REFERENCE_Z) double d9, @NonNull double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outLab must have a length of 3.");
        }
        double dPivotXyzComponent = pivotXyzComponent(d7 / XYZ_WHITE_REFERENCE_X);
        double dPivotXyzComponent2 = pivotXyzComponent(d8 / XYZ_WHITE_REFERENCE_Y);
        double dPivotXyzComponent3 = pivotXyzComponent(d9 / XYZ_WHITE_REFERENCE_Z);
        dArr[0] = Math.max(0.0d, (116.0d * dPivotXyzComponent2) - 16.0d);
        dArr[1] = (dPivotXyzComponent - dPivotXyzComponent2) * 500.0d;
        dArr[2] = (dPivotXyzComponent2 - dPivotXyzComponent3) * 200.0d;
    }

    @ColorInt
    public static int blendARGB(@ColorInt int i7, @ColorInt int i8, @FloatRange(from = 0.0d, m90to = 1.0d) float f7) {
        float f8 = 1.0f - f7;
        return Color.argb((int) ((Color.alpha(i8) * f7) + (Color.alpha(i7) * f8)), (int) ((Color.red(i8) * f7) + (Color.red(i7) * f8)), (int) ((Color.green(i8) * f7) + (Color.green(i7) * f8)), (int) ((Color.blue(i8) * f7) + (Color.blue(i7) * f8)));
    }

    public static void blendHSL(@NonNull float[] fArr, @NonNull float[] fArr2, @FloatRange(from = 0.0d, m90to = 1.0d) float f7, @NonNull float[] fArr3) {
        if (fArr3.length != 3) {
            throw new IllegalArgumentException("result must have a length of 3.");
        }
        float f8 = 1.0f - f7;
        fArr3[0] = circularInterpolate(fArr[0], fArr2[0], f7);
        fArr3[1] = (fArr2[1] * f7) + (fArr[1] * f8);
        fArr3[2] = (fArr2[2] * f7) + (fArr[2] * f8);
    }

    public static void blendLAB(@NonNull double[] dArr, @NonNull double[] dArr2, @FloatRange(from = 0.0d, m90to = 1.0d) double d7, @NonNull double[] dArr3) {
        if (dArr3.length != 3) {
            throw new IllegalArgumentException("outResult must have a length of 3.");
        }
        double d8 = 1.0d - d7;
        dArr3[0] = (dArr2[0] * d7) + (dArr[0] * d8);
        dArr3[1] = (dArr2[1] * d7) + (dArr[1] * d8);
        dArr3[2] = (dArr2[2] * d7) + (dArr[2] * d8);
    }

    public static double calculateContrast(@ColorInt int i7, @ColorInt int i8) {
        if (Color.alpha(i8) != 255) {
            StringBuilder sbM112a = C0413b.m112a("background can not be translucent: #");
            sbM112a.append(Integer.toHexString(i8));
            throw new IllegalArgumentException(sbM112a.toString());
        }
        if (Color.alpha(i7) < 255) {
            i7 = compositeColors(i7, i8);
        }
        double dCalculateLuminance = calculateLuminance(i7) + 0.05d;
        double dCalculateLuminance2 = calculateLuminance(i8) + 0.05d;
        return Math.max(dCalculateLuminance, dCalculateLuminance2) / Math.min(dCalculateLuminance, dCalculateLuminance2);
    }

    @FloatRange(from = 0.0d, m90to = 1.0d)
    public static double calculateLuminance(@ColorInt int i7) {
        double[] tempDouble3Array = getTempDouble3Array();
        colorToXYZ(i7, tempDouble3Array);
        return tempDouble3Array[1] / XYZ_WHITE_REFERENCE_Y;
    }

    public static int calculateMinimumAlpha(@ColorInt int i7, @ColorInt int i8, float f7) {
        int i9 = 255;
        if (Color.alpha(i8) != 255) {
            StringBuilder sbM112a = C0413b.m112a("background can not be translucent: #");
            sbM112a.append(Integer.toHexString(i8));
            throw new IllegalArgumentException(sbM112a.toString());
        }
        double d7 = f7;
        if (calculateContrast(setAlphaComponent(i7, 255), i8) < d7) {
            return -1;
        }
        int i10 = 0;
        for (int i11 = 0; i11 <= 10 && i9 - i10 > 1; i11++) {
            int i12 = (i10 + i9) / 2;
            if (calculateContrast(setAlphaComponent(i7, i12), i8) < d7) {
                i10 = i12;
            } else {
                i9 = i12;
            }
        }
        return i9;
    }

    @VisibleForTesting
    public static float circularInterpolate(float f7, float f8, float f9) {
        if (Math.abs(f8 - f7) > 180.0f) {
            if (f8 > f7) {
                f7 += 360.0f;
            } else {
                f8 += 360.0f;
            }
        }
        return (((f8 - f7) * f9) + f7) % 360.0f;
    }

    public static void colorToHSL(@ColorInt int i7, @NonNull float[] fArr) {
        RGBToHSL(Color.red(i7), Color.green(i7), Color.blue(i7), fArr);
    }

    public static void colorToLAB(@ColorInt int i7, @NonNull double[] dArr) {
        RGBToLAB(Color.red(i7), Color.green(i7), Color.blue(i7), dArr);
    }

    public static void colorToXYZ(@ColorInt int i7, @NonNull double[] dArr) {
        RGBToXYZ(Color.red(i7), Color.green(i7), Color.blue(i7), dArr);
    }

    private static int compositeAlpha(int i7, int i8) {
        return 255 - (((255 - i7) * (255 - i8)) / 255);
    }

    public static int compositeColors(@ColorInt int i7, @ColorInt int i8) {
        int iAlpha = Color.alpha(i8);
        int iAlpha2 = Color.alpha(i7);
        int iCompositeAlpha = compositeAlpha(iAlpha2, iAlpha);
        return Color.argb(iCompositeAlpha, compositeComponent(Color.red(i7), iAlpha2, Color.red(i8), iAlpha, iCompositeAlpha), compositeComponent(Color.green(i7), iAlpha2, Color.green(i8), iAlpha, iCompositeAlpha), compositeComponent(Color.blue(i7), iAlpha2, Color.blue(i8), iAlpha, iCompositeAlpha));
    }

    private static int compositeComponent(int i7, int i8, int i9, int i10, int i11) {
        if (i11 == 0) {
            return 0;
        }
        return (((255 - i8) * (i9 * i10)) + ((i7 * 255) * i8)) / (i11 * 255);
    }

    private static float constrain(float f7, float f8, float f9) {
        return f7 < f8 ? f8 : f7 > f9 ? f9 : f7;
    }

    private static int constrain(int i7, int i8, int i9) {
        return i7 < i8 ? i8 : i7 > i9 ? i9 : i7;
    }

    public static double distanceEuclidean(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return Math.sqrt(Math.pow(dArr[2] - dArr2[2], 2.0d) + Math.pow(dArr[1] - dArr2[1], 2.0d) + Math.pow(dArr[0] - dArr2[0], 2.0d));
    }

    private static double[] getTempDouble3Array() {
        ThreadLocal<double[]> threadLocal = TEMP_ARRAY;
        double[] dArr = threadLocal.get();
        if (dArr != null) {
            return dArr;
        }
        double[] dArr2 = new double[3];
        threadLocal.set(dArr2);
        return dArr2;
    }

    private static double pivotXyzComponent(double d7) {
        return d7 > XYZ_EPSILON ? Math.pow(d7, 0.3333333333333333d) : ((d7 * XYZ_KAPPA) + 16.0d) / 116.0d;
    }

    @ColorInt
    public static int setAlphaComponent(@ColorInt int i7, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = IjkMediaMeta.AV_CH_LAYOUT_7POINT1_WIDE_BACK) int i8) {
        if (i8 < 0 || i8 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i7 & ViewCompat.MEASURED_SIZE_MASK) | (i8 << 24);
    }

    @NonNull
    @RequiresApi(26)
    public static Color compositeColors(@NonNull Color color, @NonNull Color color2) {
        if (Objects.equals(color.getModel(), color2.getModel())) {
            if (!Objects.equals(color2.getColorSpace(), color.getColorSpace())) {
                color = color.convert(color2.getColorSpace());
            }
            float[] components = color.getComponents();
            float[] components2 = color2.getComponents();
            float fAlpha = color.alpha();
            float fAlpha2 = (1.0f - fAlpha) * color2.alpha();
            int componentCount = color2.getComponentCount() - 1;
            components2[componentCount] = fAlpha + fAlpha2;
            if (components2[componentCount] > 0.0f) {
                fAlpha /= components2[componentCount];
                fAlpha2 /= components2[componentCount];
            }
            for (int i7 = 0; i7 < componentCount; i7++) {
                components2[i7] = (components2[i7] * fAlpha2) + (components[i7] * fAlpha);
            }
            return Color.valueOf(components2, color2.getColorSpace());
        }
        StringBuilder sbM112a = C0413b.m112a("Color models must match (");
        sbM112a.append(color.getModel());
        sbM112a.append(" vs. ");
        sbM112a.append(color2.getModel());
        sbM112a.append(")");
        throw new IllegalArgumentException(sbM112a.toString());
    }
}
