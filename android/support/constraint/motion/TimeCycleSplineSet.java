package android.support.constraint.motion;

import android.support.constraint.ConstraintAttribute;
import android.support.constraint.motion.utils.CurveFit;
import android.util.SparseArray;
import android.view.View;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class TimeCycleSplineSet {
    private static final int CURVE_OFFSET = 2;
    private static final int CURVE_PERIOD = 1;
    private static final int CURVE_VALUE = 0;
    private static final String TAG = "SplineSet";
    private static float VAL_2PI = 6.2831855f;
    private int count;
    public long last_time;
    public CurveFit mCurveFit;
    private String mType;
    public int mWaveShape = 0;
    public int[] mTimePoints = new int[10];
    public float[][] mValues = (float[][]) Array.newInstance((Class<?>) float.class, 10, 3);
    private float[] mCache = new float[3];
    public boolean mContinue = false;
    public float last_cycle = Float.NaN;

    public static class AlphaSet extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setAlpha(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static class CustomSet extends TimeCycleSplineSet {
        public String mAttributeName;
        public float[] mCache;
        public SparseArray<ConstraintAttribute> mConstraintAttributeList;
        public float[] mTempValues;
        public SparseArray<float[]> mWaveProperties = new SparseArray<>();

        public CustomSet(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.mAttributeName = str.split(",")[1];
            this.mConstraintAttributeList = sparseArray;
        }

        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public void setPoint(int i7, float f7, float f8, int i8, float f9) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            this.mCurveFit.getPos(f7, this.mTempValues);
            float[] fArr = this.mTempValues;
            float f8 = fArr[fArr.length - 2];
            float f9 = fArr[fArr.length - 1];
            long j8 = j7 - this.last_time;
            if (Float.isNaN(this.last_cycle)) {
                float floatValue = keyCache.getFloatValue(view, this.mAttributeName, 0);
                this.last_cycle = floatValue;
                if (Float.isNaN(floatValue)) {
                    this.last_cycle = 0.0f;
                }
            }
            float f10 = (float) ((((j8 * 1.0E-9d) * f8) + this.last_cycle) % 1.0d);
            this.last_cycle = f10;
            this.last_time = j7;
            float fCalcWave = calcWave(f10);
            this.mContinue = false;
            int i7 = 0;
            while (true) {
                float[] fArr2 = this.mCache;
                if (i7 >= fArr2.length) {
                    break;
                }
                boolean z6 = this.mContinue;
                float[] fArr3 = this.mTempValues;
                this.mContinue = z6 | (((double) fArr3[i7]) != 0.0d);
                fArr2[i7] = (fArr3[i7] * fCalcWave) + f9;
                i7++;
            }
            this.mConstraintAttributeList.valueAt(0).setInterpolatedValue(view, this.mCache);
            if (f8 != 0.0f) {
                this.mContinue = true;
            }
            return this.mContinue;
        }

        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public void setup(int i7) {
            int size = this.mConstraintAttributeList.size();
            int iNoOfInterpValues = this.mConstraintAttributeList.valueAt(0).noOfInterpValues();
            double[] dArr = new double[size];
            int i8 = iNoOfInterpValues + 2;
            this.mTempValues = new float[i8];
            this.mCache = new float[iNoOfInterpValues];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, i8);
            for (int i9 = 0; i9 < size; i9++) {
                int iKeyAt = this.mConstraintAttributeList.keyAt(i9);
                ConstraintAttribute constraintAttributeValueAt = this.mConstraintAttributeList.valueAt(i9);
                float[] fArrValueAt = this.mWaveProperties.valueAt(i9);
                dArr[i9] = iKeyAt * 0.01d;
                constraintAttributeValueAt.getValuesToInterpolate(this.mTempValues);
                int i10 = 0;
                while (true) {
                    if (i10 < this.mTempValues.length) {
                        dArr2[i9][i10] = r8[i10];
                        i10++;
                    }
                }
                dArr2[i9][iNoOfInterpValues] = fArrValueAt[0];
                dArr2[i9][iNoOfInterpValues + 1] = fArrValueAt[1];
            }
            this.mCurveFit = CurveFit.get(i7, dArr, dArr2);
        }

        public void setPoint(int i7, ConstraintAttribute constraintAttribute, float f7, int i8, float f8) {
            this.mConstraintAttributeList.append(i7, constraintAttribute);
            this.mWaveProperties.append(i7, new float[]{f7, f8});
            this.mWaveShape = Math.max(this.mWaveShape, i8);
        }
    }

    public static class ElevationSet extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setElevation(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static class PathRotate extends TimeCycleSplineSet {
        public boolean setPathRotate(View view, KeyCache keyCache, float f7, long j7, double d7, double d8) {
            view.setRotation(get(f7, j7, view, keyCache) + ((float) Math.toDegrees(Math.atan2(d8, d7))));
            return this.mContinue;
        }

        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            return this.mContinue;
        }
    }

    public static class ProgressSet extends TimeCycleSplineSet {
        public boolean mNoMethod = false;

        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f7, j7, view, keyCache));
            } else {
                if (this.mNoMethod) {
                    return false;
                }
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.mNoMethod = true;
                }
                if (method != null) {
                    try {
                        method.invoke(view, Float.valueOf(get(f7, j7, view, keyCache)));
                    } catch (IllegalAccessException | InvocationTargetException unused2) {
                    }
                }
            }
            return this.mContinue;
        }
    }

    public static class RotationSet extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setRotation(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static class RotationXset extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setRotationX(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static class RotationYset extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setRotationY(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static class ScaleXset extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setScaleX(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static class ScaleYset extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setScaleY(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static class Sort {
        private Sort() {
        }

        public static void doubleQuickSort(int[] iArr, float[][] fArr, int i7, int i8) {
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

        private static int partition(int[] iArr, float[][] fArr, int i7, int i8) {
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

        private static void swap(int[] iArr, float[][] fArr, int i7, int i8) {
            int i9 = iArr[i7];
            iArr[i7] = iArr[i8];
            iArr[i8] = i9;
            float[] fArr2 = fArr[i7];
            fArr[i7] = fArr[i8];
            fArr[i8] = fArr2;
        }
    }

    public static class TranslationXset extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setTranslationX(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static class TranslationYset extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setTranslationY(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static class TranslationZset extends TimeCycleSplineSet {
        @Override // android.support.constraint.motion.TimeCycleSplineSet
        public boolean setProperty(View view, float f7, long j7, KeyCache keyCache) {
            view.setTranslationZ(get(f7, j7, view, keyCache));
            return this.mContinue;
        }
    }

    public static TimeCycleSplineSet makeCustomSpline(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new CustomSet(str, sparseArray);
    }

    public static TimeCycleSplineSet makeSpline(String str, long j7) {
        TimeCycleSplineSet rotationXset;
        Objects.requireNonNull(str);
        switch (str) {
            case "rotationX":
                rotationXset = new RotationXset();
                break;
            case "rotationY":
                rotationXset = new RotationYset();
                break;
            case "translationX":
                rotationXset = new TranslationXset();
                break;
            case "translationY":
                rotationXset = new TranslationYset();
                break;
            case "translationZ":
                rotationXset = new TranslationZset();
                break;
            case "progress":
                rotationXset = new ProgressSet();
                break;
            case "scaleX":
                rotationXset = new ScaleXset();
                break;
            case "scaleY":
                rotationXset = new ScaleYset();
                break;
            case "rotation":
                rotationXset = new RotationSet();
                break;
            case "elevation":
                rotationXset = new ElevationSet();
                break;
            case "transitionPathRotate":
                rotationXset = new PathRotate();
                break;
            case "alpha":
                rotationXset = new AlphaSet();
                break;
            default:
                return null;
        }
        rotationXset.setStartTime(j7);
        return rotationXset;
    }

    public float calcWave(float f7) {
        float fAbs;
        switch (this.mWaveShape) {
            case 1:
                return Math.signum(f7 * VAL_2PI);
            case 2:
                fAbs = Math.abs(f7);
                break;
            case 3:
                return (((f7 * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                fAbs = ((f7 * 2.0f) + 1.0f) % 2.0f;
                break;
            case 5:
                return (float) Math.cos(f7 * VAL_2PI);
            case 6:
                float fAbs2 = 1.0f - Math.abs(((f7 * 4.0f) % 4.0f) - 2.0f);
                fAbs = fAbs2 * fAbs2;
                break;
            default:
                return (float) Math.sin(f7 * VAL_2PI);
        }
        return 1.0f - fAbs;
    }

    public float get(float f7, long j7, View view, KeyCache keyCache) {
        this.mCurveFit.getPos(f7, this.mCache);
        float[] fArr = this.mCache;
        float f8 = fArr[1];
        if (f8 == 0.0f) {
            this.mContinue = false;
            return fArr[2];
        }
        if (Float.isNaN(this.last_cycle)) {
            float floatValue = keyCache.getFloatValue(view, this.mType, 0);
            this.last_cycle = floatValue;
            if (Float.isNaN(floatValue)) {
                this.last_cycle = 0.0f;
            }
        }
        float f9 = (float) (((((j7 - this.last_time) * 1.0E-9d) * f8) + this.last_cycle) % 1.0d);
        this.last_cycle = f9;
        keyCache.setFloatValue(view, this.mType, 0, f9);
        this.last_time = j7;
        float f10 = this.mCache[0];
        float fCalcWave = (calcWave(this.last_cycle) * f10) + this.mCache[2];
        this.mContinue = (f10 == 0.0f && f8 == 0.0f) ? false : true;
        return fCalcWave;
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    public void setPoint(int i7, float f7, float f8, int i8, float f9) {
        int[] iArr = this.mTimePoints;
        int i9 = this.count;
        iArr[i9] = i7;
        float[][] fArr = this.mValues;
        fArr[i9][0] = f7;
        fArr[i9][1] = f8;
        fArr[i9][2] = f9;
        this.mWaveShape = Math.max(this.mWaveShape, i8);
        this.count++;
    }

    public abstract boolean setProperty(View view, float f7, long j7, KeyCache keyCache);

    public void setStartTime(long j7) {
        this.last_time = j7;
    }

    public void setType(String str) {
        this.mType = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setup(int r12) {
        /*
            r11 = this;
            int r0 = r11.count
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r11.mTimePoints
            float[][] r2 = r11.mValues
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            android.support.constraint.motion.TimeCycleSplineSet.Sort.doubleQuickSort(r1, r2, r4, r0)
            r0 = 1
            r1 = 0
        L11:
            int[] r2 = r11.mTimePoints
            int r5 = r2.length
            if (r0 >= r5) goto L23
            r5 = r2[r0]
            int r6 = r0 + (-1)
            r2 = r2[r6]
            if (r5 == r2) goto L20
            int r1 = r1 + 1
        L20:
            int r0 = r0 + 1
            goto L11
        L23:
            if (r1 != 0) goto L26
            r1 = 1
        L26:
            double[] r0 = new double[r1]
            r2 = 3
            r5 = 2
            int[] r6 = new int[r5]
            r6[r3] = r2
            r6[r4] = r1
            java.lang.Class<double> r1 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r6)
            double[][] r1 = (double[][]) r1
            r2 = 0
            r6 = 0
        L3a:
            int r7 = r11.count
            if (r2 >= r7) goto L7b
            if (r2 <= 0) goto L4b
            int[] r7 = r11.mTimePoints
            r8 = r7[r2]
            int r9 = r2 + (-1)
            r7 = r7[r9]
            if (r8 != r7) goto L4b
            goto L78
        L4b:
            int[] r7 = r11.mTimePoints
            r7 = r7[r2]
            double r7 = (double) r7
            r9 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r7 = r7 * r9
            r0[r6] = r7
            r7 = r1[r6]
            float[][] r8 = r11.mValues
            r9 = r8[r2]
            r9 = r9[r4]
            double r9 = (double) r9
            r7[r4] = r9
            r7 = r1[r6]
            r9 = r8[r2]
            r9 = r9[r3]
            double r9 = (double) r9
            r7[r3] = r9
            r7 = r1[r6]
            r8 = r8[r2]
            r8 = r8[r5]
            double r8 = (double) r8
            r7[r5] = r8
            int r6 = r6 + 1
        L78:
            int r2 = r2 + 1
            goto L3a
        L7b:
            android.support.constraint.motion.utils.CurveFit r12 = android.support.constraint.motion.utils.CurveFit.get(r12, r0, r1)
            r11.mCurveFit = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.TimeCycleSplineSet.setup(int):void");
    }

    public String toString() {
        String string = this.mType;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i7 = 0; i7 < this.count; i7++) {
            StringBuilder sbM94a = C0080b.m94a(string, "[");
            sbM94a.append(this.mTimePoints[i7]);
            sbM94a.append(" , ");
            sbM94a.append(decimalFormat.format(this.mValues[i7]));
            sbM94a.append("] ");
            string = sbM94a.toString();
        }
        return string;
    }
}
