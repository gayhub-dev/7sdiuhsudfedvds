package android.support.constraint.motion;

import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintSet;
import android.support.constraint.motion.SplineSet;
import android.support.constraint.motion.utils.Easing;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.view.View;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Objects;

/* loaded from: classes.dex */
class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {
    public static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    public static final int PERPENDICULAR = 1;
    public static final String TAG = "MotionPaths";
    public static String[] names = {RequestParameters.POSITION, "x", "y", "width", "height", "pathRotate"};
    private float height;
    private Easing mKeyFrameEasing;
    private float position;
    public int visibility;
    private float width;

    /* renamed from: x */
    private float f115x;

    /* renamed from: y */
    private float f116y;
    private float alpha = 1.0f;
    public int mVisibilityMode = 0;
    private boolean applyElevation = false;
    private float elevation = 0.0f;
    private float rotation = 0.0f;
    private float rotationX = 0.0f;
    public float rotationY = 0.0f;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float translationX = 0.0f;
    private float translationY = 0.0f;
    private float translationZ = 0.0f;
    private int mDrawPath = 0;
    private float mPathRotate = Float.NaN;
    private float mProgress = Float.NaN;
    public LinkedHashMap<String, ConstraintAttribute> attributes = new LinkedHashMap<>();
    public int mMode = 0;
    public double[] mTempValue = new double[18];
    public double[] mTempDelta = new double[18];

    private boolean diff(float f7, float f8) {
        return (Float.isNaN(f7) || Float.isNaN(f8)) ? Float.isNaN(f7) != Float.isNaN(f8) : Math.abs(f7 - f8) > 1.0E-6f;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void addValues(HashMap<String, SplineSet> map, int i7) {
        for (String str : map.keySet()) {
            SplineSet splineSet = map.get(str);
            Objects.requireNonNull(str);
            char c7 = 65535;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals(Key.ROTATION_X)) {
                        c7 = 0;
                        break;
                    }
                    break;
                case -1249320805:
                    if (str.equals(Key.ROTATION_Y)) {
                        c7 = 1;
                        break;
                    }
                    break;
                case -1225497657:
                    if (str.equals(Key.TRANSLATION_X)) {
                        c7 = 2;
                        break;
                    }
                    break;
                case -1225497656:
                    if (str.equals(Key.TRANSLATION_Y)) {
                        c7 = 3;
                        break;
                    }
                    break;
                case -1225497655:
                    if (str.equals(Key.TRANSLATION_Z)) {
                        c7 = 4;
                        break;
                    }
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        c7 = 5;
                        break;
                    }
                    break;
                case -908189618:
                    if (str.equals(Key.SCALE_X)) {
                        c7 = 6;
                        break;
                    }
                    break;
                case -908189617:
                    if (str.equals(Key.SCALE_Y)) {
                        c7 = 7;
                        break;
                    }
                    break;
                case -760884510:
                    if (str.equals(Key.PIVOT_X)) {
                        c7 = '\b';
                        break;
                    }
                    break;
                case -760884509:
                    if (str.equals(Key.PIVOT_Y)) {
                        c7 = '\t';
                        break;
                    }
                    break;
                case -40300674:
                    if (str.equals(Key.ROTATION)) {
                        c7 = '\n';
                        break;
                    }
                    break;
                case -4379043:
                    if (str.equals(Key.ELEVATION)) {
                        c7 = 11;
                        break;
                    }
                    break;
                case 37232917:
                    if (str.equals(Key.TRANSITION_PATH_ROTATE)) {
                        c7 = '\f';
                        break;
                    }
                    break;
                case 92909918:
                    if (str.equals(Key.ALPHA)) {
                        c7 = '\r';
                        break;
                    }
                    break;
            }
            switch (c7) {
                case 0:
                    splineSet.setPoint(i7, Float.isNaN(this.rotationX) ? 0.0f : this.rotationX);
                    break;
                case 1:
                    splineSet.setPoint(i7, Float.isNaN(this.rotationY) ? 0.0f : this.rotationY);
                    break;
                case 2:
                    splineSet.setPoint(i7, Float.isNaN(this.translationX) ? 0.0f : this.translationX);
                    break;
                case 3:
                    splineSet.setPoint(i7, Float.isNaN(this.translationY) ? 0.0f : this.translationY);
                    break;
                case 4:
                    splineSet.setPoint(i7, Float.isNaN(this.translationZ) ? 0.0f : this.translationZ);
                    break;
                case 5:
                    splineSet.setPoint(i7, Float.isNaN(this.mProgress) ? 0.0f : this.mProgress);
                    break;
                case 6:
                    splineSet.setPoint(i7, Float.isNaN(this.scaleX) ? 1.0f : this.scaleX);
                    break;
                case 7:
                    splineSet.setPoint(i7, Float.isNaN(this.scaleY) ? 1.0f : this.scaleY);
                    break;
                case '\b':
                    splineSet.setPoint(i7, Float.isNaN(this.mPivotX) ? 0.0f : this.mPivotX);
                    break;
                case '\t':
                    splineSet.setPoint(i7, Float.isNaN(this.mPivotY) ? 0.0f : this.mPivotY);
                    break;
                case '\n':
                    splineSet.setPoint(i7, Float.isNaN(this.rotation) ? 0.0f : this.rotation);
                    break;
                case 11:
                    splineSet.setPoint(i7, Float.isNaN(this.elevation) ? 0.0f : this.elevation);
                    break;
                case '\f':
                    splineSet.setPoint(i7, Float.isNaN(this.mPathRotate) ? 0.0f : this.mPathRotate);
                    break;
                case '\r':
                    splineSet.setPoint(i7, Float.isNaN(this.alpha) ? 1.0f : this.alpha);
                    break;
                default:
                    if (str.startsWith(Key.CUSTOM)) {
                        String str2 = str.split(",")[1];
                        if (this.attributes.containsKey(str2)) {
                            ConstraintAttribute constraintAttribute = this.attributes.get(str2);
                            if (splineSet instanceof SplineSet.CustomSet) {
                                ((SplineSet.CustomSet) splineSet).setPoint(i7, constraintAttribute);
                                break;
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append(str);
                                sb.append(" splineSet not a CustomSet frame = ");
                                sb.append(i7);
                                sb.append(", value");
                                sb.append(constraintAttribute.getValueToInterpolate());
                                sb.append(splineSet);
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
            }
        }
    }

    public void applyParameters(View view) {
        this.visibility = view.getVisibility();
        this.alpha = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.applyElevation = false;
        this.elevation = view.getElevation();
        this.rotation = view.getRotation();
        this.rotationX = view.getRotationX();
        this.rotationY = view.getRotationY();
        this.scaleX = view.getScaleX();
        this.scaleY = view.getScaleY();
        this.mPivotX = view.getPivotX();
        this.mPivotY = view.getPivotY();
        this.translationX = view.getTranslationX();
        this.translationY = view.getTranslationY();
        this.translationZ = view.getTranslationZ();
    }

    public void different(MotionConstrainedPoint motionConstrainedPoint, HashSet<String> hashSet) {
        if (diff(this.alpha, motionConstrainedPoint.alpha)) {
            hashSet.add(Key.ALPHA);
        }
        if (diff(this.elevation, motionConstrainedPoint.elevation)) {
            hashSet.add(Key.ELEVATION);
        }
        int i7 = this.visibility;
        int i8 = motionConstrainedPoint.visibility;
        if (i7 != i8 && this.mVisibilityMode == 0 && (i7 == 0 || i8 == 0)) {
            hashSet.add(Key.ALPHA);
        }
        if (diff(this.rotation, motionConstrainedPoint.rotation)) {
            hashSet.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.mPathRotate) || !Float.isNaN(motionConstrainedPoint.mPathRotate)) {
            hashSet.add(Key.TRANSITION_PATH_ROTATE);
        }
        if (!Float.isNaN(this.mProgress) || !Float.isNaN(motionConstrainedPoint.mProgress)) {
            hashSet.add("progress");
        }
        if (diff(this.rotationX, motionConstrainedPoint.rotationX)) {
            hashSet.add(Key.ROTATION_X);
        }
        if (diff(this.rotationY, motionConstrainedPoint.rotationY)) {
            hashSet.add(Key.ROTATION_Y);
        }
        if (diff(this.mPivotX, motionConstrainedPoint.mPivotX)) {
            hashSet.add(Key.PIVOT_X);
        }
        if (diff(this.mPivotY, motionConstrainedPoint.mPivotY)) {
            hashSet.add(Key.PIVOT_Y);
        }
        if (diff(this.scaleX, motionConstrainedPoint.scaleX)) {
            hashSet.add(Key.SCALE_X);
        }
        if (diff(this.scaleY, motionConstrainedPoint.scaleY)) {
            hashSet.add(Key.SCALE_Y);
        }
        if (diff(this.translationX, motionConstrainedPoint.translationX)) {
            hashSet.add(Key.TRANSLATION_X);
        }
        if (diff(this.translationY, motionConstrainedPoint.translationY)) {
            hashSet.add(Key.TRANSLATION_Y);
        }
        if (diff(this.translationZ, motionConstrainedPoint.translationZ)) {
            hashSet.add(Key.TRANSLATION_Z);
        }
    }

    public void fillStandard(double[] dArr, int[] iArr) {
        float[] fArr = {this.position, this.f115x, this.f116y, this.width, this.height, this.alpha, this.elevation, this.rotation, this.rotationX, this.rotationY, this.scaleX, this.scaleY, this.mPivotX, this.mPivotY, this.translationX, this.translationY, this.translationZ, this.mPathRotate};
        int i7 = 0;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            if (iArr[i8] < 18) {
                dArr[i7] = fArr[iArr[i8]];
                i7++;
            }
        }
    }

    public int getCustomData(String str, double[] dArr, int i7) {
        ConstraintAttribute constraintAttribute = this.attributes.get(str);
        if (constraintAttribute.noOfInterpValues() == 1) {
            dArr[i7] = constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int iNoOfInterpValues = constraintAttribute.noOfInterpValues();
        constraintAttribute.getValuesToInterpolate(new float[iNoOfInterpValues]);
        int i8 = 0;
        while (i8 < iNoOfInterpValues) {
            dArr[i7] = r1[i8];
            i8++;
            i7++;
        }
        return iNoOfInterpValues;
    }

    public int getCustomDataCount(String str) {
        return this.attributes.get(str).noOfInterpValues();
    }

    public boolean hasCustomData(String str) {
        return this.attributes.containsKey(str);
    }

    public void setBounds(float f7, float f8, float f9, float f10) {
        this.f115x = f7;
        this.f116y = f8;
        this.width = f9;
        this.height = f10;
    }

    public void setState(View view) {
        setBounds(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        applyParameters(view);
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionConstrainedPoint motionConstrainedPoint) {
        return Float.compare(this.position, motionConstrainedPoint.position);
    }

    public void setState(ConstraintWidget constraintWidget, ConstraintSet constraintSet, int i7) {
        setBounds(constraintWidget.getX(), constraintWidget.getY(), constraintWidget.getWidth(), constraintWidget.getHeight());
        applyParameters(constraintSet.getParameters(i7));
    }

    public void applyParameters(ConstraintSet.Constraint constraint) {
        ConstraintSet.PropertySet propertySet = constraint.propertySet;
        int i7 = propertySet.mVisibilityMode;
        this.mVisibilityMode = i7;
        int i8 = propertySet.visibility;
        this.visibility = i8;
        this.alpha = (i8 == 0 || i7 != 0) ? propertySet.alpha : 0.0f;
        ConstraintSet.Transform transform = constraint.transform;
        this.applyElevation = transform.applyElevation;
        this.elevation = transform.elevation;
        this.rotation = transform.rotation;
        this.rotationX = transform.rotationX;
        this.rotationY = transform.rotationY;
        this.scaleX = transform.scaleX;
        this.scaleY = transform.scaleY;
        this.mPivotX = transform.transformPivotX;
        this.mPivotY = transform.transformPivotY;
        this.translationX = transform.translationX;
        this.translationY = transform.translationY;
        this.translationZ = transform.translationZ;
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.mPathRotate = motion.mPathRotate;
        this.mDrawPath = motion.mDrawPath;
        this.mProgress = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute.getType() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.attributes.put(str, constraintAttribute);
            }
        }
    }

    public void different(MotionConstrainedPoint motionConstrainedPoint, boolean[] zArr, String[] strArr) {
        zArr[0] = zArr[0] | diff(this.position, motionConstrainedPoint.position);
        zArr[1] = zArr[1] | diff(this.f115x, motionConstrainedPoint.f115x);
        zArr[2] = zArr[2] | diff(this.f116y, motionConstrainedPoint.f116y);
        zArr[3] = zArr[3] | diff(this.width, motionConstrainedPoint.width);
        zArr[4] = diff(this.height, motionConstrainedPoint.height) | zArr[4];
    }
}
