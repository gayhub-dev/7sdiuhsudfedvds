package android.support.constraint.motion;

import android.support.constraint.ConstraintAttribute;
import android.support.constraint.motion.utils.CurveFit;
import android.util.SparseArray;
import android.view.View;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class SplineSet {
    private static final String TAG = "SplineSet";
    private int count;
    public CurveFit mCurveFit;
    private String mType;
    public int[] mTimePoints = new int[10];
    public float[] mValues = new float[10];

    public static class AlphaSet extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setAlpha(get(f7));
        }
    }

    public static class CustomSet extends SplineSet {
        public String mAttributeName;
        public SparseArray<ConstraintAttribute> mConstraintAttributeList;
        public float[] mTempValues;

        public CustomSet(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.mAttributeName = str.split(",")[1];
            this.mConstraintAttributeList = sparseArray;
        }

        @Override // android.support.constraint.motion.SplineSet
        public void setPoint(int i7, float f7) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            this.mCurveFit.getPos(f7, this.mTempValues);
            this.mConstraintAttributeList.valueAt(0).setInterpolatedValue(view, this.mTempValues);
        }

        @Override // android.support.constraint.motion.SplineSet
        public void setup(int i7) {
            int size = this.mConstraintAttributeList.size();
            int iNoOfInterpValues = this.mConstraintAttributeList.valueAt(0).noOfInterpValues();
            double[] dArr = new double[size];
            this.mTempValues = new float[iNoOfInterpValues];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, iNoOfInterpValues);
            for (int i8 = 0; i8 < size; i8++) {
                int iKeyAt = this.mConstraintAttributeList.keyAt(i8);
                ConstraintAttribute constraintAttributeValueAt = this.mConstraintAttributeList.valueAt(i8);
                dArr[i8] = iKeyAt * 0.01d;
                constraintAttributeValueAt.getValuesToInterpolate(this.mTempValues);
                int i9 = 0;
                while (true) {
                    if (i9 < this.mTempValues.length) {
                        dArr2[i8][i9] = r6[i9];
                        i9++;
                    }
                }
            }
            this.mCurveFit = CurveFit.get(i7, dArr, dArr2);
        }

        public void setPoint(int i7, ConstraintAttribute constraintAttribute) {
            this.mConstraintAttributeList.append(i7, constraintAttribute);
        }
    }

    public static class ElevationSet extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setElevation(get(f7));
        }
    }

    public static class PathRotate extends SplineSet {
        public void setPathRotate(View view, float f7, double d7, double d8) {
            view.setRotation(get(f7) + ((float) Math.toDegrees(Math.atan2(d8, d7))));
        }

        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
        }
    }

    public static class PivotXset extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setPivotX(get(f7));
        }
    }

    public static class PivotYset extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setPivotY(get(f7));
        }
    }

    public static class ProgressSet extends SplineSet {
        public boolean mNoMethod = false;

        @Override // android.support.constraint.motion.SplineSet
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

    public static class RotationSet extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setRotation(get(f7));
        }
    }

    public static class RotationXset extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setRotationX(get(f7));
        }
    }

    public static class RotationYset extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setRotationY(get(f7));
        }
    }

    public static class ScaleXset extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setScaleX(get(f7));
        }
    }

    public static class ScaleYset extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setScaleY(get(f7));
        }
    }

    public static class Sort {
        private Sort() {
        }

        public static void doubleQuickSort(int[] iArr, float[] fArr, int i7, int i8) {
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

        private static void swap(int[] iArr, float[] fArr, int i7, int i8) {
            int i9 = iArr[i7];
            iArr[i7] = iArr[i8];
            iArr[i8] = i9;
            float f7 = fArr[i7];
            fArr[i7] = fArr[i8];
            fArr[i8] = f7;
        }
    }

    public static class TranslationXset extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setTranslationX(get(f7));
        }
    }

    public static class TranslationYset extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setTranslationY(get(f7));
        }
    }

    public static class TranslationZset extends SplineSet {
        @Override // android.support.constraint.motion.SplineSet
        public void setProperty(View view, float f7) {
            view.setTranslationZ(get(f7));
        }
    }

    public static SplineSet makeCustomSpline(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new CustomSet(str, sparseArray);
    }

    public static SplineSet makeSpline(String str) {
        Objects.requireNonNull(str);
        switch (str) {
        }
        return new AlphaSet();
    }

    public float get(float f7) {
        return (float) this.mCurveFit.getPos(f7, 0);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    public float getSlope(float f7) {
        return (float) this.mCurveFit.getSlope(f7, 0);
    }

    public void setPoint(int i7, float f7) {
        int[] iArr = this.mTimePoints;
        if (iArr.length < this.count + 1) {
            this.mTimePoints = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.mValues;
            this.mValues = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.mTimePoints;
        int i8 = this.count;
        iArr2[i8] = i7;
        this.mValues[i8] = f7;
        this.count = i8 + 1;
    }

    public abstract void setProperty(View view, float f7);

    public void setType(String str) {
        this.mType = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setup(int r10) {
        /*
            r9 = this;
            int r0 = r9.count
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r9.mTimePoints
            float[] r2 = r9.mValues
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            android.support.constraint.motion.SplineSet.Sort.doubleQuickSort(r1, r2, r4, r0)
            r0 = 1
            r1 = 1
        L11:
            int r2 = r9.count
            if (r0 >= r2) goto L24
            int[] r2 = r9.mTimePoints
            int r5 = r0 + (-1)
            r5 = r2[r5]
            r2 = r2[r0]
            if (r5 == r2) goto L21
            int r1 = r1 + 1
        L21:
            int r0 = r0 + 1
            goto L11
        L24:
            double[] r0 = new double[r1]
            r2 = 2
            int[] r2 = new int[r2]
            r2[r3] = r3
            r2[r4] = r1
            java.lang.Class<double> r1 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r2)
            double[][] r1 = (double[][]) r1
            r2 = 0
            r3 = 0
        L37:
            int r5 = r9.count
            if (r2 >= r5) goto L64
            if (r2 <= 0) goto L48
            int[] r5 = r9.mTimePoints
            r6 = r5[r2]
            int r7 = r2 + (-1)
            r5 = r5[r7]
            if (r6 != r5) goto L48
            goto L61
        L48:
            int[] r5 = r9.mTimePoints
            r5 = r5[r2]
            double r5 = (double) r5
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r7
            r0[r3] = r5
            r5 = r1[r3]
            float[] r6 = r9.mValues
            r6 = r6[r2]
            double r6 = (double) r6
            r5[r4] = r6
            int r3 = r3 + 1
        L61:
            int r2 = r2 + 1
            goto L37
        L64:
            android.support.constraint.motion.utils.CurveFit r10 = android.support.constraint.motion.utils.CurveFit.get(r10, r0, r1)
            r9.mCurveFit = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.SplineSet.setup(int):void");
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
