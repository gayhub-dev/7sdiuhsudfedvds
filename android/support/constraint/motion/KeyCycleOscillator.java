package android.support.constraint.motion;

import android.annotation.TargetApi;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.motion.utils.CurveFit;
import android.support.constraint.motion.utils.Oscillator;
import android.view.View;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class KeyCycleOscillator {
    private static final String TAG = "KeyCycleOscillator";
    private CurveFit mCurveFit;
    public ConstraintAttribute mCustom;
    private CycleOscillator mCycleOscillator;
    private String mType;
    private int mWaveShape = 0;
    public int mVariesBy = 0;
    public ArrayList<WavePoint> mWavePoints = new ArrayList<>();

    public static class AlphaSet extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setAlpha(get(f7));
        }
    }

    public static class CustomSet extends KeyCycleOscillator {
        public float[] value = new float[1];

        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            this.value[0] = get(f7);
            this.mCustom.setInterpolatedValue(view, this.value);
        }
    }

    public static class CycleOscillator {
        private static final String TAG = "CycleOscillator";
        public static final int UNSET = -1;
        public CurveFit mCurveFit;
        public float[] mOffset;
        public float mPathLength;
        public float[] mPeriod;
        public double[] mPosition;
        public float[] mScale;
        public double[] mSplineSlopeCache;
        public double[] mSplineValueCache;
        public float[] mValues;
        private final int mVariesBy;
        public int mWaveShape;
        public Oscillator mOscillator = new Oscillator();
        public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap<>();

        public CycleOscillator(int i7, int i8, int i9) {
            this.mWaveShape = i7;
            this.mVariesBy = i8;
            this.mOscillator.setType(i7);
            this.mValues = new float[i9];
            this.mPosition = new double[i9];
            this.mPeriod = new float[i9];
            this.mOffset = new float[i9];
            this.mScale = new float[i9];
        }

        private ConstraintAttribute get(String str, ConstraintAttribute.AttributeType attributeType) {
            if (!this.mCustomConstraints.containsKey(str)) {
                ConstraintAttribute constraintAttribute = new ConstraintAttribute(str, attributeType);
                this.mCustomConstraints.put(str, constraintAttribute);
                return constraintAttribute;
            }
            ConstraintAttribute constraintAttribute2 = this.mCustomConstraints.get(str);
            if (constraintAttribute2.getType() == attributeType) {
                return constraintAttribute2;
            }
            StringBuilder sbM112a = C0413b.m112a("ConstraintAttribute is already a ");
            sbM112a.append(constraintAttribute2.getType().name());
            throw new IllegalArgumentException(sbM112a.toString());
        }

        public double getSlope(float f7) {
            CurveFit curveFit = this.mCurveFit;
            if (curveFit != null) {
                double d7 = f7;
                curveFit.getSlope(d7, this.mSplineSlopeCache);
                this.mCurveFit.getPos(d7, this.mSplineValueCache);
            } else {
                double[] dArr = this.mSplineSlopeCache;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d8 = f7;
            double value = this.mOscillator.getValue(d8);
            double slope = this.mOscillator.getSlope(d8);
            double[] dArr2 = this.mSplineSlopeCache;
            return (slope * this.mSplineValueCache[1]) + (value * dArr2[1]) + dArr2[0];
        }

        public double getValues(float f7) {
            CurveFit curveFit = this.mCurveFit;
            if (curveFit != null) {
                curveFit.getPos(f7, this.mSplineValueCache);
            } else {
                double[] dArr = this.mSplineValueCache;
                dArr[0] = this.mOffset[0];
                dArr[1] = this.mValues[0];
            }
            return (this.mOscillator.getValue(f7) * this.mSplineValueCache[1]) + this.mSplineValueCache[0];
        }

        public void setPoint(int i7, int i8, float f7, float f8, float f9) {
            this.mPosition[i7] = i8 / 100.0d;
            this.mPeriod[i7] = f7;
            this.mOffset[i7] = f8;
            this.mValues[i7] = f9;
        }

        public void setup(float f7) {
            this.mPathLength = f7;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.mPosition.length, 2);
            float[] fArr = this.mValues;
            this.mSplineValueCache = new double[fArr.length + 1];
            this.mSplineSlopeCache = new double[fArr.length + 1];
            if (this.mPosition[0] > 0.0d) {
                this.mOscillator.addPoint(0.0d, this.mPeriod[0]);
            }
            double[] dArr2 = this.mPosition;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.mOscillator.addPoint(1.0d, this.mPeriod[length]);
            }
            for (int i7 = 0; i7 < dArr.length; i7++) {
                dArr[i7][0] = this.mOffset[i7];
                int i8 = 0;
                while (true) {
                    if (i8 < this.mValues.length) {
                        dArr[i8][1] = r4[i8];
                        i8++;
                    }
                }
                this.mOscillator.addPoint(this.mPosition[i7], this.mPeriod[i7]);
            }
            this.mOscillator.normalize();
            double[] dArr3 = this.mPosition;
            if (dArr3.length > 1) {
                this.mCurveFit = CurveFit.get(0, dArr3, dArr);
            } else {
                this.mCurveFit = null;
            }
        }
    }

    public static class ElevationSet extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setElevation(get(f7));
        }
    }

    public static class IntDoubleSort {
        private IntDoubleSort() {
        }

        private static int partition(int[] iArr, float[] fArr, int i7, int i8) {
            int i9 = iArr[i8];
            int i10 = i7;
            while (i7 < i8) {
                if (iArr[i7] <= i9) {
                    swap(iArr, fArr, i10, i7);
                    i10++;
                }
                i7++;
            }
            swap(iArr, fArr, i10, i8);
            return i10;
        }

        public static void sort(int[] iArr, float[] fArr, int i7, int i8) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i8;
            iArr2[1] = i7;
            int i9 = 2;
            while (i9 > 0) {
                int i10 = i9 - 1;
                int i11 = iArr2[i10];
                i9 = i10 - 1;
                int i12 = iArr2[i9];
                if (i11 < i12) {
                    int iPartition = partition(iArr, fArr, i11, i12);
                    int i13 = i9 + 1;
                    iArr2[i9] = iPartition - 1;
                    int i14 = i13 + 1;
                    iArr2[i13] = i11;
                    int i15 = i14 + 1;
                    iArr2[i14] = i12;
                    i9 = i15 + 1;
                    iArr2[i15] = iPartition + 1;
                }
            }
        }

        private static void swap(int[] iArr, float[] fArr, int i7, int i8) {
            int i9 = iArr[i7];
            iArr[i7] = iArr[i8];
            iArr[i8] = i9;
            float f7 = fArr[i7];
            fArr[i7] = fArr[i8];
            fArr[i8] = f7;
        }
    }

    public static class IntFloatFloatSort {
        private IntFloatFloatSort() {
        }

        private static int partition(int[] iArr, float[] fArr, float[] fArr2, int i7, int i8) {
            int i9 = iArr[i8];
            int i10 = i7;
            while (i7 < i8) {
                if (iArr[i7] <= i9) {
                    swap(iArr, fArr, fArr2, i10, i7);
                    i10++;
                }
                i7++;
            }
            swap(iArr, fArr, fArr2, i10, i8);
            return i10;
        }

        public static void sort(int[] iArr, float[] fArr, float[] fArr2, int i7, int i8) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i8;
            iArr2[1] = i7;
            int i9 = 2;
            while (i9 > 0) {
                int i10 = i9 - 1;
                int i11 = iArr2[i10];
                i9 = i10 - 1;
                int i12 = iArr2[i9];
                if (i11 < i12) {
                    int iPartition = partition(iArr, fArr, fArr2, i11, i12);
                    int i13 = i9 + 1;
                    iArr2[i9] = iPartition - 1;
                    int i14 = i13 + 1;
                    iArr2[i13] = i11;
                    int i15 = i14 + 1;
                    iArr2[i14] = i12;
                    i9 = i15 + 1;
                    iArr2[i15] = iPartition + 1;
                }
            }
        }

        private static void swap(int[] iArr, float[] fArr, float[] fArr2, int i7, int i8) {
            int i9 = iArr[i7];
            iArr[i7] = iArr[i8];
            iArr[i8] = i9;
            float f7 = fArr[i7];
            fArr[i7] = fArr[i8];
            fArr[i8] = f7;
            float f8 = fArr2[i7];
            fArr2[i7] = fArr2[i8];
            fArr2[i8] = f8;
        }
    }

    public static class PathRotateSet extends KeyCycleOscillator {
        public void setPathRotate(View view, float f7, double d7, double d8) {
            view.setRotation(get(f7) + ((float) Math.toDegrees(Math.atan2(d8, d7))));
        }

        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
        }
    }

    public static class ProgressSet extends KeyCycleOscillator {
        public boolean mNoMethod = false;

        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f7));
                return;
            }
            if (this.mNoMethod) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.mNoMethod = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(get(f7)));
                } catch (IllegalAccessException | InvocationTargetException unused2) {
                }
            }
        }
    }

    public static class RotationSet extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setRotation(get(f7));
        }
    }

    public static class RotationXset extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setRotationX(get(f7));
        }
    }

    public static class RotationYset extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setRotationY(get(f7));
        }
    }

    public static class ScaleXset extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setScaleX(get(f7));
        }
    }

    public static class ScaleYset extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setScaleY(get(f7));
        }
    }

    public static class TranslationXset extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setTranslationX(get(f7));
        }
    }

    public static class TranslationYset extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setTranslationY(get(f7));
        }
    }

    public static class TranslationZset extends KeyCycleOscillator {
        @Override // android.support.constraint.motion.KeyCycleOscillator
        public void setProperty(View view, float f7) {
            view.setTranslationZ(get(f7));
        }
    }

    public static class WavePoint {
        public float mOffset;
        public float mPeriod;
        public int mPosition;
        public float mValue;

        public WavePoint(int i7, float f7, float f8, float f9) {
            this.mPosition = i7;
            this.mValue = f9;
            this.mOffset = f8;
            this.mPeriod = f7;
        }
    }

    public static KeyCycleOscillator makeSpline(String str) {
        if (str.startsWith(Key.CUSTOM)) {
            return new CustomSet();
        }
        switch (str) {
            case "rotationX":
                return new RotationXset();
            case "rotationY":
                return new RotationYset();
            case "translationX":
                return new TranslationXset();
            case "translationY":
                return new TranslationYset();
            case "translationZ":
                return new TranslationZset();
            case "progress":
                return new ProgressSet();
            case "scaleX":
                return new ScaleXset();
            case "scaleY":
                return new ScaleYset();
            case "waveVariesBy":
                return new AlphaSet();
            case "rotation":
                return new RotationSet();
            case "elevation":
                return new ElevationSet();
            case "transitionPathRotate":
                return new PathRotateSet();
            case "alpha":
                return new AlphaSet();
            case "waveOffset":
                return new AlphaSet();
            default:
                return null;
        }
    }

    public float get(float f7) {
        return (float) this.mCycleOscillator.getValues(f7);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    public float getSlope(float f7) {
        return (float) this.mCycleOscillator.getSlope(f7);
    }

    public void setPoint(int i7, int i8, int i9, float f7, float f8, float f9, ConstraintAttribute constraintAttribute) {
        this.mWavePoints.add(new WavePoint(i7, f7, f8, f9));
        if (i9 != -1) {
            this.mVariesBy = i9;
        }
        this.mWaveShape = i8;
        this.mCustom = constraintAttribute;
    }

    public abstract void setProperty(View view, float f7);

    public void setType(String str) {
        this.mType = str;
    }

    @TargetApi(19)
    public void setup(float f7) {
        int size = this.mWavePoints.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.mWavePoints, new Comparator<WavePoint>() { // from class: android.support.constraint.motion.KeyCycleOscillator.1
            @Override // java.util.Comparator
            public int compare(WavePoint wavePoint, WavePoint wavePoint2) {
                return Integer.compare(wavePoint.mPosition, wavePoint2.mPosition);
            }
        });
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
        this.mCycleOscillator = new CycleOscillator(this.mWaveShape, this.mVariesBy, size);
        Iterator<WavePoint> it = this.mWavePoints.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            WavePoint next = it.next();
            float f8 = next.mPeriod;
            dArr[i7] = f8 * 0.01d;
            double[] dArr3 = dArr2[i7];
            float f9 = next.mValue;
            dArr3[0] = f9;
            double[] dArr4 = dArr2[i7];
            float f10 = next.mOffset;
            dArr4[1] = f10;
            this.mCycleOscillator.setPoint(i7, next.mPosition, f8, f10, f9);
            i7++;
        }
        this.mCycleOscillator.setup(f7);
        this.mCurveFit = CurveFit.get(0, dArr, dArr2);
    }

    public String toString() {
        String string = this.mType;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<WavePoint> it = this.mWavePoints.iterator();
        while (it.hasNext()) {
            WavePoint next = it.next();
            StringBuilder sbM94a = C0080b.m94a(string, "[");
            sbM94a.append(next.mPosition);
            sbM94a.append(" , ");
            sbM94a.append(decimalFormat.format(next.mValue));
            sbM94a.append("] ");
            string = sbM94a.toString();
        }
        return string;
    }

    public boolean variesByPath() {
        return this.mVariesBy == 1;
    }

    public void setPoint(int i7, int i8, int i9, float f7, float f8, float f9) {
        this.mWavePoints.add(new WavePoint(i7, f7, f8, f9));
        if (i9 != -1) {
            this.mVariesBy = i9;
        }
        this.mWaveShape = i8;
    }
}
