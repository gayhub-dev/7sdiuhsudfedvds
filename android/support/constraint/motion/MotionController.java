package android.support.constraint.motion;

import android.graphics.RectF;
import android.support.constraint.C0072a;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.motion.KeyCycleOscillator;
import android.support.constraint.motion.SplineSet;
import android.support.constraint.motion.TimeCycleSplineSet;
import android.support.constraint.motion.utils.CurveFit;
import android.support.constraint.motion.utils.Easing;
import android.support.constraint.motion.utils.VelocityMatrix;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import p009b.C0413b;

/* loaded from: classes.dex */
public class MotionController {
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    public String[] attributeTable;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpCount;
    private String[] mAttributeNames;
    private HashMap<String, SplineSet> mAttributesMap;
    public String mConstraintTag;
    private HashMap<String, KeyCycleOscillator> mCycleMap;
    public int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private KeyTrigger[] mKeyTriggers;
    private CurveFit[] mSpline;
    private HashMap<String, TimeCycleSplineSet> mTimeCycleAttributesMap;
    public View mView;
    private int mCurveFitType = -1;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    public float mMotionStagger = Float.NaN;
    public float mStaggerOffset = 0.0f;
    public float mStaggerScale = 1.0f;
    private int MAX_DIMENSION = 4;
    private float[] mValuesBuff = new float[4];
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    private float[] mVelocity = new float[1];
    private ArrayList<Key> mKeyList = new ArrayList<>();
    private int mPathMotionArc = Key.UNSET;

    public MotionController(View view) {
        setView(view);
    }

    private float getAdjustedPosition(float f7, float[] fArr) {
        float f8 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f9 = this.mStaggerScale;
            if (f9 != 1.0d) {
                float f10 = this.mStaggerOffset;
                if (f7 < f10) {
                    f7 = 0.0f;
                }
                if (f7 > f10 && f7 < 1.0d) {
                    f7 = (f7 - f10) * f9;
                }
            }
        }
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        float f11 = Float.NaN;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths next = it.next();
            Easing easing2 = next.mKeyFrameEasing;
            if (easing2 != null) {
                float f12 = next.time;
                if (f12 < f7) {
                    easing = easing2;
                    f8 = f12;
                } else if (Float.isNaN(f11)) {
                    f11 = next.time;
                }
            }
        }
        if (easing != null) {
            float f13 = (Float.isNaN(f11) ? 1.0f : f11) - f8;
            double d7 = (f7 - f8) / f13;
            f7 = (((float) easing.get(d7)) * f13) + f8;
            if (fArr != null) {
                fArr[0] = (float) easing.getDiff(d7);
            }
        }
        return f7;
    }

    private float getPreCycleDistance() {
        float[] fArr = new float[2];
        float f7 = 1.0f / 99;
        double d7 = 0.0d;
        double d8 = 0.0d;
        int i7 = 0;
        float fHypot = 0.0f;
        while (i7 < 100) {
            float f8 = i7 * f7;
            double d9 = f8;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            float f9 = Float.NaN;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f10 = 0.0f;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                Easing easing2 = next.mKeyFrameEasing;
                float f11 = f7;
                if (easing2 != null) {
                    float f12 = next.time;
                    if (f12 < f8) {
                        f10 = f12;
                        easing = easing2;
                    } else if (Float.isNaN(f9)) {
                        f9 = next.time;
                    }
                }
                f7 = f11;
            }
            float f13 = f7;
            if (easing != null) {
                if (Float.isNaN(f9)) {
                    f9 = 1.0f;
                }
                d9 = (((float) easing.get((f8 - f10) / r16)) * (f9 - f10)) + f10;
            }
            this.mSpline[0].getPos(d9, this.mInterpolateData);
            this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
            if (i7 > 0) {
                fHypot = (float) (Math.hypot(d8 - fArr[1], d7 - fArr[0]) + fHypot);
            }
            d7 = fArr[0];
            d8 = fArr[1];
            i7++;
            f7 = f13;
        }
        return fHypot;
    }

    private void insertKey(MotionPaths motionPaths) {
        if (Collections.binarySearch(this.mMotionPaths, motionPaths) == 0) {
            float f7 = motionPaths.position;
        }
        this.mMotionPaths.add((-r0) - 1, motionPaths);
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((int) this.mView.getX(), (int) this.mView.getY(), this.mView.getWidth(), this.mView.getHeight());
    }

    public void addKey(Key key) {
        this.mKeyList.add(key);
    }

    public void addKeys(ArrayList<Key> arrayList) {
        this.mKeyList.addAll(arrayList);
    }

    public void buildBounds(float[] fArr, int i7) {
        float f7 = 1.0f / (i7 - 1);
        HashMap<String, SplineSet> map = this.mAttributesMap;
        if (map != null) {
            map.get(Key.TRANSLATION_X);
        }
        HashMap<String, SplineSet> map2 = this.mAttributesMap;
        if (map2 != null) {
            map2.get(Key.TRANSLATION_Y);
        }
        HashMap<String, KeyCycleOscillator> map3 = this.mCycleMap;
        if (map3 != null) {
            map3.get(Key.TRANSLATION_X);
        }
        HashMap<String, KeyCycleOscillator> map4 = this.mCycleMap;
        if (map4 != null) {
            map4.get(Key.TRANSLATION_Y);
        }
        for (int i8 = 0; i8 < i7; i8++) {
            float f8 = i8 * f7;
            float f9 = this.mStaggerScale;
            float f10 = 0.0f;
            if (f9 != 1.0f) {
                float f11 = this.mStaggerOffset;
                if (f8 < f11) {
                    f8 = 0.0f;
                }
                if (f8 > f11 && f8 < 1.0d) {
                    f8 = (f8 - f11) * f9;
                }
            }
            double d7 = f8;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            float f12 = Float.NaN;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            while (it.hasNext()) {
                MotionPaths next = it.next();
                Easing easing2 = next.mKeyFrameEasing;
                if (easing2 != null) {
                    float f13 = next.time;
                    if (f13 < f8) {
                        easing = easing2;
                        f10 = f13;
                    } else if (Float.isNaN(f12)) {
                        f12 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f12)) {
                    f12 = 1.0f;
                }
                d7 = (((float) easing.get((f8 - f10) / r11)) * (f12 - f10)) + f10;
            }
            this.mSpline[0].getPos(d7, this.mInterpolateData);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d7, dArr);
                }
            }
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, fArr, i8 * 2);
        }
    }

    public int buildKeyBounds(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            int i7 = 0;
            while (it.hasNext()) {
                iArr[i7] = it.next().mMode;
                i7++;
            }
        }
        int i8 = 0;
        for (double d7 : timePoints) {
            this.mSpline[0].getPos(d7, this.mInterpolateData);
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, fArr, i8);
            i8 += 2;
        }
        return i8 / 2;
    }

    public int buildKeyFrames(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            int i7 = 0;
            while (it.hasNext()) {
                iArr[i7] = it.next().mMode;
                i7++;
            }
        }
        int i8 = 0;
        for (double d7 : timePoints) {
            this.mSpline[0].getPos(d7, this.mInterpolateData);
            this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, fArr, i8);
            i8 += 2;
        }
        return i8 / 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0065 A[PHI: r11
      0x0065: PHI (r11v2 float) = (r11v1 float), (r11v4 float) binds: [B:21:0x004b, B:26:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0103  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void buildPath(float[] r22, int r23) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.MotionController.buildPath(float[], int):void");
    }

    public void buildRect(float f7, float[] fArr, int i7) {
        this.mSpline[0].getPos(getAdjustedPosition(f7, null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i7);
    }

    public void buildRectangles(float[] fArr, int i7) {
        float f7 = 1.0f / (i7 - 1);
        for (int i8 = 0; i8 < i7; i8++) {
            this.mSpline[0].getPos(getAdjustedPosition(i8 * f7, null), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i8 * 8);
        }
    }

    public int getAttributeValues(String str, float[] fArr, int i7) {
        SplineSet splineSet = this.mAttributesMap.get(str);
        if (splineSet == null) {
            return -1;
        }
        for (int i8 = 0; i8 < fArr.length; i8++) {
            fArr[i8] = splineSet.get(i8 / (fArr.length - 1));
        }
        return fArr.length;
    }

    public void getDpDt(float f7, float f8, float f9, float[] fArr) {
        double[] dArr;
        float adjustedPosition = getAdjustedPosition(f7, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        int i7 = 0;
        if (curveFitArr == null) {
            MotionPaths motionPaths = this.mEndMotionPath;
            float f10 = motionPaths.f119x;
            MotionPaths motionPaths2 = this.mStartMotionPath;
            float f11 = f10 - motionPaths2.f119x;
            float f12 = motionPaths.f120y - motionPaths2.f120y;
            float f13 = motionPaths.width - motionPaths2.width;
            float f14 = (motionPaths.height - motionPaths2.height) + f12;
            fArr[0] = ((f13 + f11) * f8) + ((1.0f - f8) * f11);
            fArr[1] = (f14 * f9) + ((1.0f - f9) * f12);
            return;
        }
        double d7 = adjustedPosition;
        curveFitArr[0].getSlope(d7, this.mInterpolateVelocity);
        this.mSpline[0].getPos(d7, this.mInterpolateData);
        float f15 = this.mVelocity[0];
        while (true) {
            dArr = this.mInterpolateVelocity;
            if (i7 >= dArr.length) {
                break;
            }
            dArr[i7] = dArr[i7] * f15;
            i7++;
        }
        CurveFit curveFit = this.mArcSpline;
        if (curveFit == null) {
            this.mStartMotionPath.setDpDt(f8, f9, fArr, this.mInterpolateVariables, dArr, this.mInterpolateData);
            return;
        }
        double[] dArr2 = this.mInterpolateData;
        if (dArr2.length > 0) {
            curveFit.getPos(d7, dArr2);
            this.mArcSpline.getSlope(d7, this.mInterpolateVelocity);
            this.mStartMotionPath.setDpDt(f8, f9, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
        }
    }

    public int getDrawPath() {
        int iMax = this.mStartMotionPath.mDrawPath;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().mDrawPath);
        }
        return Math.max(iMax, this.mEndMotionPath.mDrawPath);
    }

    public float getFinalX() {
        return this.mEndMotionPath.f119x;
    }

    public float getFinalY() {
        return this.mEndMotionPath.f120y;
    }

    public MotionPaths getKeyFrame(int i7) {
        return this.mMotionPaths.get(i7);
    }

    public int getKeyFrameInfo(int i7, int[] iArr) {
        float[] fArr = new float[2];
        Iterator<Key> it = this.mKeyList.iterator();
        int i8 = 0;
        int i9 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            int i10 = next.mType;
            if (i10 == i7 || i7 != -1) {
                iArr[i9] = 0;
                int i11 = i9 + 1;
                iArr[i11] = i10;
                int i12 = i11 + 1;
                iArr[i12] = next.mFramePosition;
                this.mSpline[0].getPos(r8 / 100.0f, this.mInterpolateData);
                this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
                int i13 = i12 + 1;
                iArr[i13] = Float.floatToIntBits(fArr[0]);
                int i14 = i13 + 1;
                iArr[i14] = Float.floatToIntBits(fArr[1]);
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    int i15 = i14 + 1;
                    iArr[i15] = keyPosition.mPositionType;
                    int i16 = i15 + 1;
                    iArr[i16] = Float.floatToIntBits(keyPosition.mPercentX);
                    i14 = i16 + 1;
                    iArr[i14] = Float.floatToIntBits(keyPosition.mPercentY);
                }
                int i17 = i14 + 1;
                iArr[i9] = i17 - i9;
                i8++;
                i9 = i17;
            }
        }
        return i8;
    }

    public float getKeyFrameParameter(int i7, float f7, float f8) {
        MotionPaths motionPaths = this.mEndMotionPath;
        float f9 = motionPaths.f119x;
        MotionPaths motionPaths2 = this.mStartMotionPath;
        float f10 = motionPaths2.f119x;
        float f11 = f9 - f10;
        float f12 = motionPaths.f120y;
        float f13 = motionPaths2.f120y;
        float f14 = f12 - f13;
        float f15 = (motionPaths2.width / 2.0f) + f10;
        float f16 = (motionPaths2.height / 2.0f) + f13;
        float fHypot = (float) Math.hypot(f11, f14);
        if (fHypot < 1.0E-7d) {
            return Float.NaN;
        }
        float f17 = f7 - f15;
        float f18 = f8 - f16;
        if (((float) Math.hypot(f17, f18)) == 0.0f) {
            return 0.0f;
        }
        float f19 = (f18 * f14) + (f17 * f11);
        if (i7 == 0) {
            return f19 / fHypot;
        }
        if (i7 == 1) {
            return (float) Math.sqrt((fHypot * fHypot) - (f19 * f19));
        }
        if (i7 == 2) {
            return f17 / f11;
        }
        if (i7 == 3) {
            return f18 / f11;
        }
        if (i7 == 4) {
            return f17 / f14;
        }
        if (i7 != 5) {
            return 0.0f;
        }
        return f18 / f14;
    }

    public KeyPositionBase getPositionKeyframe(int i7, int i8, float f7, float f8) {
        RectF rectF = new RectF();
        MotionPaths motionPaths = this.mStartMotionPath;
        float f9 = motionPaths.f119x;
        rectF.left = f9;
        float f10 = motionPaths.f120y;
        rectF.top = f10;
        rectF.right = f9 + motionPaths.width;
        rectF.bottom = f10 + motionPaths.height;
        RectF rectF2 = new RectF();
        MotionPaths motionPaths2 = this.mEndMotionPath;
        float f11 = motionPaths2.f119x;
        rectF2.left = f11;
        float f12 = motionPaths2.f120y;
        rectF2.top = f12;
        rectF2.right = f11 + motionPaths2.width;
        rectF2.bottom = f12 + motionPaths2.height;
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key next = it.next();
            if (next instanceof KeyPositionBase) {
                KeyPositionBase keyPositionBase = (KeyPositionBase) next;
                if (keyPositionBase.intersects(i7, i8, rectF, rectF2, f7, f8)) {
                    return keyPositionBase;
                }
            }
        }
        return null;
    }

    public void getPostLayoutDvDp(float f7, int i7, int i8, float f8, float f9, float[] fArr) {
        float adjustedPosition = getAdjustedPosition(f7, this.mVelocity);
        HashMap<String, SplineSet> map = this.mAttributesMap;
        SplineSet splineSet = map == null ? null : map.get(Key.TRANSLATION_X);
        HashMap<String, SplineSet> map2 = this.mAttributesMap;
        SplineSet splineSet2 = map2 == null ? null : map2.get(Key.TRANSLATION_Y);
        HashMap<String, SplineSet> map3 = this.mAttributesMap;
        SplineSet splineSet3 = map3 == null ? null : map3.get(Key.ROTATION);
        HashMap<String, SplineSet> map4 = this.mAttributesMap;
        SplineSet splineSet4 = map4 == null ? null : map4.get(Key.SCALE_X);
        HashMap<String, SplineSet> map5 = this.mAttributesMap;
        SplineSet splineSet5 = map5 == null ? null : map5.get(Key.SCALE_Y);
        HashMap<String, KeyCycleOscillator> map6 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator = map6 == null ? null : map6.get(Key.TRANSLATION_X);
        HashMap<String, KeyCycleOscillator> map7 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator2 = map7 == null ? null : map7.get(Key.TRANSLATION_Y);
        HashMap<String, KeyCycleOscillator> map8 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator3 = map8 == null ? null : map8.get(Key.ROTATION);
        HashMap<String, KeyCycleOscillator> map9 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator4 = map9 == null ? null : map9.get(Key.SCALE_X);
        HashMap<String, KeyCycleOscillator> map10 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator5 = map10 != null ? map10.get(Key.SCALE_Y) : null;
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
        velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
        velocityMatrix.setRotationVelocity(keyCycleOscillator3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(keyCycleOscillator, keyCycleOscillator2, adjustedPosition);
        velocityMatrix.setScaleVelocity(keyCycleOscillator4, keyCycleOscillator5, adjustedPosition);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                double d7 = adjustedPosition;
                curveFit.getPos(d7, dArr);
                this.mArcSpline.getSlope(d7, this.mInterpolateVelocity);
                this.mStartMotionPath.setDpDt(f8, f9, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            }
            velocityMatrix.applyTransform(f8, f9, i7, i8, fArr);
            return;
        }
        int i9 = 0;
        if (this.mSpline == null) {
            MotionPaths motionPaths = this.mEndMotionPath;
            float f10 = motionPaths.f119x;
            MotionPaths motionPaths2 = this.mStartMotionPath;
            float f11 = f10 - motionPaths2.f119x;
            KeyCycleOscillator keyCycleOscillator6 = keyCycleOscillator5;
            float f12 = motionPaths.f120y - motionPaths2.f120y;
            KeyCycleOscillator keyCycleOscillator7 = keyCycleOscillator4;
            float f13 = motionPaths.width - motionPaths2.width;
            float f14 = (motionPaths.height - motionPaths2.height) + f12;
            fArr[0] = ((f13 + f11) * f8) + ((1.0f - f8) * f11);
            fArr[1] = (f14 * f9) + ((1.0f - f9) * f12);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
            velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
            velocityMatrix.setRotationVelocity(keyCycleOscillator3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(keyCycleOscillator, keyCycleOscillator2, adjustedPosition);
            velocityMatrix.setScaleVelocity(keyCycleOscillator7, keyCycleOscillator6, adjustedPosition);
            velocityMatrix.applyTransform(f8, f9, i7, i8, fArr);
            return;
        }
        double adjustedPosition2 = getAdjustedPosition(adjustedPosition, this.mVelocity);
        this.mSpline[0].getSlope(adjustedPosition2, this.mInterpolateVelocity);
        this.mSpline[0].getPos(adjustedPosition2, this.mInterpolateData);
        float f15 = this.mVelocity[0];
        while (true) {
            double[] dArr2 = this.mInterpolateVelocity;
            if (i9 >= dArr2.length) {
                this.mStartMotionPath.setDpDt(f8, f9, fArr, this.mInterpolateVariables, dArr2, this.mInterpolateData);
                velocityMatrix.applyTransform(f8, f9, i7, i8, fArr);
                return;
            } else {
                dArr2[i9] = dArr2[i9] * f15;
                i9++;
            }
        }
    }

    public float getStartX() {
        return this.mStartMotionPath.f119x;
    }

    public float getStartY() {
        return this.mStartMotionPath.f120y;
    }

    public int getkeyFramePositions(int[] iArr, float[] fArr) {
        Iterator<Key> it = this.mKeyList.iterator();
        int i7 = 0;
        int i8 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            iArr[i7] = (next.mType * 1000) + next.mFramePosition;
            this.mSpline[0].getPos(r6 / 100.0f, this.mInterpolateData);
            this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, fArr, i8);
            i8 += 2;
            i7++;
        }
        return i7;
    }

    public boolean interpolate(View view, float f7, long j7, KeyCache keyCache) {
        TimeCycleSplineSet.PathRotate pathRotate;
        boolean pathRotate2;
        double d7;
        float adjustedPosition = getAdjustedPosition(f7, null);
        HashMap<String, SplineSet> map = this.mAttributesMap;
        if (map != null) {
            Iterator<SplineSet> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().setProperty(view, adjustedPosition);
            }
        }
        HashMap<String, TimeCycleSplineSet> map2 = this.mTimeCycleAttributesMap;
        if (map2 != null) {
            pathRotate = null;
            boolean property = false;
            for (TimeCycleSplineSet timeCycleSplineSet : map2.values()) {
                if (timeCycleSplineSet instanceof TimeCycleSplineSet.PathRotate) {
                    pathRotate = (TimeCycleSplineSet.PathRotate) timeCycleSplineSet;
                } else {
                    property |= timeCycleSplineSet.setProperty(view, adjustedPosition, j7, keyCache);
                }
            }
            pathRotate2 = property;
        } else {
            pathRotate = null;
            pathRotate2 = false;
        }
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            double d8 = adjustedPosition;
            curveFitArr[0].getPos(d8, this.mInterpolateData);
            this.mSpline[0].getSlope(d8, this.mInterpolateVelocity);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d8, dArr);
                    this.mArcSpline.getSlope(d8, this.mInterpolateVelocity);
                }
            }
            this.mStartMotionPath.setView(view, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, null);
            HashMap<String, SplineSet> map3 = this.mAttributesMap;
            if (map3 != null) {
                for (SplineSet splineSet : map3.values()) {
                    if (splineSet instanceof SplineSet.PathRotate) {
                        double[] dArr2 = this.mInterpolateVelocity;
                        ((SplineSet.PathRotate) splineSet).setPathRotate(view, adjustedPosition, dArr2[0], dArr2[1]);
                    }
                }
            }
            if (pathRotate != null) {
                double[] dArr3 = this.mInterpolateVelocity;
                d7 = d8;
                pathRotate2 = pathRotate.setPathRotate(view, keyCache, adjustedPosition, j7, dArr3[0], dArr3[1]) | pathRotate2;
            } else {
                d7 = d8;
            }
            int i7 = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.mSpline;
                if (i7 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i7].getPos(d7, this.mValuesBuff);
                this.mStartMotionPath.attributes.get(this.mAttributeNames[i7 - 1]).setInterpolatedValue(view, this.mValuesBuff);
                i7++;
            }
            MotionConstrainedPoint motionConstrainedPoint = this.mStartPoint;
            if (motionConstrainedPoint.mVisibilityMode == 0) {
                if (adjustedPosition <= 0.0f) {
                    view.setVisibility(motionConstrainedPoint.visibility);
                } else if (adjustedPosition >= 1.0f) {
                    view.setVisibility(this.mEndPoint.visibility);
                } else if (this.mEndPoint.visibility != motionConstrainedPoint.visibility) {
                    view.setVisibility(0);
                }
            }
            if (this.mKeyTriggers != null) {
                int i8 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
                    if (i8 >= keyTriggerArr.length) {
                        break;
                    }
                    keyTriggerArr[i8].conditionallyFire(adjustedPosition, view);
                    i8++;
                }
            }
        } else {
            MotionPaths motionPaths = this.mStartMotionPath;
            float f8 = motionPaths.f119x;
            MotionPaths motionPaths2 = this.mEndMotionPath;
            float f9 = ((motionPaths2.f119x - f8) * adjustedPosition) + f8;
            float f10 = motionPaths.f120y;
            float f11 = ((motionPaths2.f120y - f10) * adjustedPosition) + f10;
            float f12 = motionPaths.width;
            float f13 = motionPaths2.width;
            float f14 = motionPaths.height;
            float f15 = motionPaths2.height;
            float f16 = f9 + 0.5f;
            int i9 = (int) f16;
            float f17 = f11 + 0.5f;
            int i10 = (int) f17;
            int i11 = (int) (f16 + ((f13 - f12) * adjustedPosition) + f12);
            int i12 = (int) (f17 + ((f15 - f14) * adjustedPosition) + f14);
            int i13 = i11 - i9;
            int i14 = i12 - i10;
            if (f13 != f12 || f15 != f14) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i13, 1073741824), View.MeasureSpec.makeMeasureSpec(i14, 1073741824));
            }
            view.layout(i9, i10, i11, i12);
        }
        HashMap<String, KeyCycleOscillator> map4 = this.mCycleMap;
        if (map4 != null) {
            for (KeyCycleOscillator keyCycleOscillator : map4.values()) {
                if (keyCycleOscillator instanceof KeyCycleOscillator.PathRotateSet) {
                    double[] dArr4 = this.mInterpolateVelocity;
                    ((KeyCycleOscillator.PathRotateSet) keyCycleOscillator).setPathRotate(view, adjustedPosition, dArr4[0], dArr4[1]);
                } else {
                    keyCycleOscillator.setProperty(view, adjustedPosition);
                }
            }
        }
        return pathRotate2;
    }

    public String name() {
        return this.mView.getContext().getResources().getResourceEntryName(this.mView.getId());
    }

    public void positionKeyframe(View view, KeyPositionBase keyPositionBase, float f7, float f8, String[] strArr, float[] fArr) {
        RectF rectF = new RectF();
        MotionPaths motionPaths = this.mStartMotionPath;
        float f9 = motionPaths.f119x;
        rectF.left = f9;
        float f10 = motionPaths.f120y;
        rectF.top = f10;
        rectF.right = f9 + motionPaths.width;
        rectF.bottom = f10 + motionPaths.height;
        RectF rectF2 = new RectF();
        MotionPaths motionPaths2 = this.mEndMotionPath;
        float f11 = motionPaths2.f119x;
        rectF2.left = f11;
        float f12 = motionPaths2.f120y;
        rectF2.top = f12;
        rectF2.right = f11 + motionPaths2.width;
        rectF2.bottom = f12 + motionPaths2.height;
        keyPositionBase.positionAttributes(view, rectF, rectF2, f7, f8, strArr, fArr);
    }

    public void setDrawPath(int i7) {
        this.mStartMotionPath.mDrawPath = i7;
    }

    public void setEndState(ConstraintWidget constraintWidget, ConstraintSet constraintSet) {
        MotionPaths motionPaths = this.mEndMotionPath;
        motionPaths.time = 1.0f;
        motionPaths.position = 1.0f;
        readView(motionPaths);
        this.mEndMotionPath.setBounds(constraintWidget.getX(), constraintWidget.getY(), constraintWidget.getWidth(), constraintWidget.getHeight());
        this.mEndMotionPath.applyParameters(constraintSet.getParameters(this.mId));
        this.mEndPoint.setState(constraintWidget, constraintSet, this.mId);
    }

    public void setPathMotionArc(int i7) {
        this.mPathMotionArc = i7;
    }

    public void setStartCurrentState(View view) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.time = 0.0f;
        motionPaths.position = 0.0f;
        motionPaths.setBounds(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.mStartPoint.setState(view);
    }

    public void setStartState(ConstraintWidget constraintWidget, ConstraintSet constraintSet) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.time = 0.0f;
        motionPaths.position = 0.0f;
        readView(motionPaths);
        this.mStartMotionPath.setBounds(constraintWidget.getX(), constraintWidget.getY(), constraintWidget.getWidth(), constraintWidget.getHeight());
        ConstraintSet.Constraint parameters = constraintSet.getParameters(this.mId);
        this.mStartMotionPath.applyParameters(parameters);
        this.mMotionStagger = parameters.motion.mMotionStagger;
        this.mStartPoint.setState(constraintWidget, constraintSet, this.mId);
    }

    public void setView(View view) {
        this.mView = view;
        this.mId = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            this.mConstraintTag = ((ConstraintLayout.LayoutParams) layoutParams).getConstraintTag();
        }
    }

    public void setup(int i7, int i8, float f7, long j7) {
        ArrayList arrayList;
        String[] strArr;
        TimeCycleSplineSet timeCycleSplineSetMakeSpline;
        ConstraintAttribute constraintAttribute;
        SplineSet splineSetMakeSpline;
        ConstraintAttribute constraintAttribute2;
        new HashSet();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        int i9 = this.mPathMotionArc;
        if (i9 != Key.UNSET) {
            this.mStartMotionPath.mPathMotionArc = i9;
        }
        this.mStartPoint.different(this.mEndPoint, hashSet2);
        ArrayList<Key> arrayList2 = this.mKeyList;
        if (arrayList2 != null) {
            Iterator<Key> it = arrayList2.iterator();
            arrayList = null;
            while (it.hasNext()) {
                Key next = it.next();
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    insertKey(new MotionPaths(i7, i8, keyPosition, this.mStartMotionPath, this.mEndMotionPath));
                    int i10 = keyPosition.mCurveFit;
                    if (i10 != Key.UNSET) {
                        this.mCurveFitType = i10;
                    }
                } else if (next instanceof KeyCycle) {
                    next.getAttributeNames(hashSet3);
                } else if (next instanceof KeyTimeCycle) {
                    next.getAttributeNames(hashSet);
                } else if (next instanceof KeyTrigger) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((KeyTrigger) next);
                } else {
                    next.setInterpolation(map);
                    next.getAttributeNames(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        char c7 = 0;
        if (arrayList != null) {
            this.mKeyTriggers = (KeyTrigger[]) arrayList.toArray(new KeyTrigger[0]);
        }
        char c8 = 1;
        if (!hashSet2.isEmpty()) {
            this.mAttributesMap = new HashMap<>();
            Iterator<String> it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                String next2 = it2.next();
                if (next2.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str = next2.split(",")[c8];
                    Iterator<Key> it3 = this.mKeyList.iterator();
                    while (it3.hasNext()) {
                        Key next3 = it3.next();
                        HashMap<String, ConstraintAttribute> map2 = next3.mCustomConstraints;
                        if (map2 != null && (constraintAttribute2 = map2.get(str)) != null) {
                            sparseArray.append(next3.mFramePosition, constraintAttribute2);
                        }
                    }
                    splineSetMakeSpline = SplineSet.makeCustomSpline(next2, sparseArray);
                } else {
                    splineSetMakeSpline = SplineSet.makeSpline(next2);
                }
                if (splineSetMakeSpline != null) {
                    splineSetMakeSpline.setType(next2);
                    this.mAttributesMap.put(next2, splineSetMakeSpline);
                }
                c8 = 1;
            }
            ArrayList<Key> arrayList3 = this.mKeyList;
            if (arrayList3 != null) {
                Iterator<Key> it4 = arrayList3.iterator();
                while (it4.hasNext()) {
                    Key next4 = it4.next();
                    if (next4 instanceof KeyAttributes) {
                        next4.addValues(this.mAttributesMap);
                    }
                }
            }
            this.mStartPoint.addValues(this.mAttributesMap, 0);
            this.mEndPoint.addValues(this.mAttributesMap, 100);
            for (String str2 : this.mAttributesMap.keySet()) {
                this.mAttributesMap.get(str2).setup(map.containsKey(str2) ? map.get(str2).intValue() : 0);
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.mTimeCycleAttributesMap == null) {
                this.mTimeCycleAttributesMap = new HashMap<>();
            }
            Iterator<String> it5 = hashSet.iterator();
            while (it5.hasNext()) {
                String next5 = it5.next();
                if (!this.mTimeCycleAttributesMap.containsKey(next5)) {
                    if (next5.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str3 = next5.split(",")[1];
                        Iterator<Key> it6 = this.mKeyList.iterator();
                        while (it6.hasNext()) {
                            Key next6 = it6.next();
                            HashMap<String, ConstraintAttribute> map3 = next6.mCustomConstraints;
                            if (map3 != null && (constraintAttribute = map3.get(str3)) != null) {
                                sparseArray2.append(next6.mFramePosition, constraintAttribute);
                            }
                        }
                        timeCycleSplineSetMakeSpline = TimeCycleSplineSet.makeCustomSpline(next5, sparseArray2);
                    } else {
                        timeCycleSplineSetMakeSpline = TimeCycleSplineSet.makeSpline(next5, j7);
                    }
                    if (timeCycleSplineSetMakeSpline != null) {
                        timeCycleSplineSetMakeSpline.setType(next5);
                        this.mTimeCycleAttributesMap.put(next5, timeCycleSplineSetMakeSpline);
                    }
                }
            }
            ArrayList<Key> arrayList4 = this.mKeyList;
            if (arrayList4 != null) {
                Iterator<Key> it7 = arrayList4.iterator();
                while (it7.hasNext()) {
                    Key next7 = it7.next();
                    if (next7 instanceof KeyTimeCycle) {
                        ((KeyTimeCycle) next7).addTimeValues(this.mTimeCycleAttributesMap);
                    }
                }
            }
            for (String str4 : this.mTimeCycleAttributesMap.keySet()) {
                this.mTimeCycleAttributesMap.get(str4).setup(map.containsKey(str4) ? map.get(str4).intValue() : 0);
            }
        }
        int i11 = 2;
        int size = this.mMotionPaths.size() + 2;
        MotionPaths[] motionPathsArr = new MotionPaths[size];
        motionPathsArr[0] = this.mStartMotionPath;
        motionPathsArr[size - 1] = this.mEndMotionPath;
        if (this.mMotionPaths.size() > 0 && this.mCurveFitType == -1) {
            this.mCurveFitType = 0;
        }
        Iterator<MotionPaths> it8 = this.mMotionPaths.iterator();
        int i12 = 1;
        while (it8.hasNext()) {
            motionPathsArr[i12] = it8.next();
            i12++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str5 : this.mEndMotionPath.attributes.keySet()) {
            if (this.mStartMotionPath.attributes.containsKey(str5)) {
                if (!hashSet2.contains("CUSTOM," + str5)) {
                    hashSet4.add(str5);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.mAttributeNames = strArr2;
        this.mAttributeInterpCount = new int[strArr2.length];
        int i13 = 0;
        while (true) {
            strArr = this.mAttributeNames;
            if (i13 >= strArr.length) {
                break;
            }
            String str6 = strArr[i13];
            this.mAttributeInterpCount[i13] = 0;
            int i14 = 0;
            while (true) {
                if (i14 >= size) {
                    break;
                }
                if (motionPathsArr[i14].attributes.containsKey(str6)) {
                    int[] iArr = this.mAttributeInterpCount;
                    iArr[i13] = motionPathsArr[i14].attributes.get(str6).noOfInterpValues() + iArr[i13];
                    break;
                }
                i14++;
            }
            i13++;
        }
        boolean z6 = motionPathsArr[0].mPathMotionArc != Key.UNSET;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i15 = 1; i15 < size; i15++) {
            motionPathsArr[i15].different(motionPathsArr[i15 - 1], zArr, this.mAttributeNames, z6);
        }
        int i16 = 0;
        for (int i17 = 1; i17 < length; i17++) {
            if (zArr[i17]) {
                i16++;
            }
        }
        int[] iArr2 = new int[i16];
        this.mInterpolateVariables = iArr2;
        this.mInterpolateData = new double[iArr2.length];
        this.mInterpolateVelocity = new double[iArr2.length];
        int i18 = 0;
        for (int i19 = 1; i19 < length; i19++) {
            if (zArr[i19]) {
                this.mInterpolateVariables[i18] = i19;
                i18++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, size, this.mInterpolateVariables.length);
        double[] dArr2 = new double[size];
        for (int i20 = 0; i20 < size; i20++) {
            motionPathsArr[i20].fillStandard(dArr[i20], this.mInterpolateVariables);
            dArr2[i20] = motionPathsArr[i20].time;
        }
        int i21 = 0;
        while (true) {
            int[] iArr3 = this.mInterpolateVariables;
            if (i21 >= iArr3.length) {
                break;
            }
            if (iArr3[i21] < MotionPaths.names.length) {
                String strM92a = C0072a.m92a(new StringBuilder(), MotionPaths.names[this.mInterpolateVariables[i21]], " [");
                for (int i22 = 0; i22 < size; i22++) {
                    StringBuilder sbM112a = C0413b.m112a(strM92a);
                    sbM112a.append(dArr[i22][i21]);
                    strM92a = sbM112a.toString();
                }
            }
            i21++;
        }
        this.mSpline = new CurveFit[this.mAttributeNames.length + 1];
        int i23 = 0;
        while (true) {
            String[] strArr3 = this.mAttributeNames;
            if (i23 >= strArr3.length) {
                break;
            }
            String str7 = strArr3[i23];
            int i24 = 0;
            double[] dArr3 = null;
            int i25 = 0;
            double[][] dArr4 = null;
            while (i24 < size) {
                if (motionPathsArr[i24].hasCustomData(str7)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr4 = new int[i11];
                        iArr4[1] = motionPathsArr[i24].getCustomDataCount(str7);
                        iArr4[c7] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr4);
                    }
                    dArr3[i25] = motionPathsArr[i24].time;
                    motionPathsArr[i24].getCustomData(str7, dArr4[i25], 0);
                    i25++;
                }
                i24++;
                i11 = 2;
                c7 = 0;
            }
            i23++;
            this.mSpline[i23] = CurveFit.get(this.mCurveFitType, Arrays.copyOf(dArr3, i25), (double[][]) Arrays.copyOf(dArr4, i25));
            i11 = 2;
            c7 = 0;
        }
        this.mSpline[0] = CurveFit.get(this.mCurveFitType, dArr2, dArr);
        if (motionPathsArr[0].mPathMotionArc != Key.UNSET) {
            int[] iArr5 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i26 = 0; i26 < size; i26++) {
                iArr5[i26] = motionPathsArr[i26].mPathMotionArc;
                dArr5[i26] = motionPathsArr[i26].time;
                dArr6[i26][0] = motionPathsArr[i26].f119x;
                dArr6[i26][1] = motionPathsArr[i26].f120y;
            }
            this.mArcSpline = CurveFit.getArc(iArr5, dArr5, dArr6);
        }
        float preCycleDistance = Float.NaN;
        this.mCycleMap = new HashMap<>();
        if (this.mKeyList != null) {
            Iterator<String> it9 = hashSet3.iterator();
            while (it9.hasNext()) {
                String next8 = it9.next();
                KeyCycleOscillator keyCycleOscillatorMakeSpline = KeyCycleOscillator.makeSpline(next8);
                if (keyCycleOscillatorMakeSpline != null) {
                    if (keyCycleOscillatorMakeSpline.variesByPath() && Float.isNaN(preCycleDistance)) {
                        preCycleDistance = getPreCycleDistance();
                    }
                    keyCycleOscillatorMakeSpline.setType(next8);
                    this.mCycleMap.put(next8, keyCycleOscillatorMakeSpline);
                }
            }
            Iterator<Key> it10 = this.mKeyList.iterator();
            while (it10.hasNext()) {
                Key next9 = it10.next();
                if (next9 instanceof KeyCycle) {
                    ((KeyCycle) next9).addCycleValues(this.mCycleMap);
                }
            }
            Iterator<KeyCycleOscillator> it11 = this.mCycleMap.values().iterator();
            while (it11.hasNext()) {
                it11.next().setup(preCycleDistance);
            }
        }
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a(" start: x: ");
        sbM112a.append(this.mStartMotionPath.f119x);
        sbM112a.append(" y: ");
        sbM112a.append(this.mStartMotionPath.f120y);
        sbM112a.append(" end: x: ");
        sbM112a.append(this.mEndMotionPath.f119x);
        sbM112a.append(" y: ");
        sbM112a.append(this.mEndMotionPath.f120y);
        return sbM112a.toString();
    }
}
