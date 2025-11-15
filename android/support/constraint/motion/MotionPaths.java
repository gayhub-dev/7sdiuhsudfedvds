package android.support.constraint.motion;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintSet;
import android.support.constraint.motion.utils.Easing;
import android.view.View;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
class MotionPaths implements Comparable<MotionPaths> {
    public static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    public static final int OFF_HEIGHT = 4;
    public static final int OFF_PATH_ROTATE = 5;
    public static final int OFF_POSITION = 0;
    public static final int OFF_WIDTH = 3;
    public static final int OFF_X = 1;
    public static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    public static final int PERPENDICULAR = 1;
    public static final int SCREEN = 3;
    public static final String TAG = "MotionPaths";
    public static String[] names = {RequestParameters.POSITION, "x", "y", "width", "height", "pathRotate"};
    public LinkedHashMap<String, ConstraintAttribute> attributes;
    public float height;
    public int mDrawPath;
    public Easing mKeyFrameEasing;
    public int mMode;
    public int mPathMotionArc;
    public float mPathRotate;
    public float mProgress;
    public double[] mTempDelta;
    public double[] mTempValue;
    public float position;
    public float time;
    public float width;

    /* renamed from: x */
    public float f119x;

    /* renamed from: y */
    public float f120y;

    public MotionPaths() {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    private boolean diff(float f7, float f8) {
        return (Float.isNaN(f7) || Float.isNaN(f8)) ? Float.isNaN(f7) != Float.isNaN(f8) : Math.abs(f7 - f8) > 1.0E-6f;
    }

    private static final float xRotate(float f7, float f8, float f9, float f10, float f11, float f12) {
        return (((f11 - f9) * f8) - ((f12 - f10) * f7)) + f9;
    }

    private static final float yRotate(float f7, float f8, float f9, float f10, float f11, float f12) {
        return ((f12 - f10) * f8) + ((f11 - f9) * f7) + f10;
    }

    public void applyParameters(ConstraintSet.Constraint constraint) {
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.mPathMotionArc = motion.mPathMotionArc;
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

    public void different(MotionPaths motionPaths, boolean[] zArr, String[] strArr, boolean z6) {
        zArr[0] = zArr[0] | diff(this.position, motionPaths.position);
        zArr[1] = zArr[1] | diff(this.f119x, motionPaths.f119x) | z6;
        zArr[2] = z6 | diff(this.f120y, motionPaths.f120y) | zArr[2];
        zArr[3] = zArr[3] | diff(this.width, motionPaths.width);
        zArr[4] = diff(this.height, motionPaths.height) | zArr[4];
    }

    public void fillStandard(double[] dArr, int[] iArr) {
        float[] fArr = {this.position, this.f119x, this.f120y, this.width, this.height, this.mPathRotate};
        int i7 = 0;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            if (iArr[i8] < 6) {
                dArr[i7] = fArr[iArr[i8]];
                i7++;
            }
        }
    }

    public void getBounds(int[] iArr, double[] dArr, float[] fArr, int i7) {
        float f7 = this.width;
        float f8 = this.height;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            float f9 = (float) dArr[i8];
            int i9 = iArr[i8];
            if (i9 == 3) {
                f7 = f9;
            } else if (i9 == 4) {
                f8 = f9;
            }
        }
        fArr[i7] = f7;
        fArr[i7 + 1] = f8;
    }

    public void getCenter(int[] iArr, double[] dArr, float[] fArr, int i7) {
        float f7 = this.f119x;
        float f8 = this.f120y;
        float f9 = this.width;
        float f10 = this.height;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            float f11 = (float) dArr[i8];
            int i9 = iArr[i8];
            if (i9 == 1) {
                f7 = f11;
            } else if (i9 == 2) {
                f8 = f11;
            } else if (i9 == 3) {
                f9 = f11;
            } else if (i9 == 4) {
                f10 = f11;
            }
        }
        fArr[i7] = (f9 / 2.0f) + f7 + 0.0f;
        fArr[i7 + 1] = (f10 / 2.0f) + f8 + 0.0f;
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

    public void getRect(int[] iArr, double[] dArr, float[] fArr, int i7) {
        float f7 = this.f119x;
        float f8 = this.f120y;
        float f9 = this.width;
        float f10 = this.height;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            float f11 = (float) dArr[i8];
            int i9 = iArr[i8];
            if (i9 == 1) {
                f7 = f11;
            } else if (i9 == 2) {
                f8 = f11;
            } else if (i9 == 3) {
                f9 = f11;
            } else if (i9 == 4) {
                f10 = f11;
            }
        }
        float f12 = f9 + f7;
        float f13 = f10 + f8;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i10 = i7 + 1;
        fArr[i7] = f7 + 0.0f;
        int i11 = i10 + 1;
        fArr[i10] = f8 + 0.0f;
        int i12 = i11 + 1;
        fArr[i11] = f12 + 0.0f;
        int i13 = i12 + 1;
        fArr[i12] = f8 + 0.0f;
        int i14 = i13 + 1;
        fArr[i13] = f12 + 0.0f;
        int i15 = i14 + 1;
        fArr[i14] = f13 + 0.0f;
        fArr[i15] = f7 + 0.0f;
        fArr[i15 + 1] = f13 + 0.0f;
    }

    public boolean hasCustomData(String str) {
        return this.attributes.containsKey(str);
    }

    public void initCartesian(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f7 = keyPosition.mFramePosition / 100.0f;
        this.time = f7;
        this.mDrawPath = keyPosition.mDrawPath;
        float f8 = Float.isNaN(keyPosition.mPercentWidth) ? f7 : keyPosition.mPercentWidth;
        float f9 = Float.isNaN(keyPosition.mPercentHeight) ? f7 : keyPosition.mPercentHeight;
        float f10 = motionPaths2.width;
        float f11 = motionPaths.width;
        float f12 = f10 - f11;
        float f13 = motionPaths2.height;
        float f14 = motionPaths.height;
        float f15 = f13 - f14;
        this.position = this.time;
        float f16 = motionPaths.f119x;
        float f17 = motionPaths.f120y;
        float f18 = ((f10 / 2.0f) + motionPaths2.f119x) - ((f11 / 2.0f) + f16);
        float f19 = ((f13 / 2.0f) + motionPaths2.f120y) - ((f14 / 2.0f) + f17);
        float f20 = (f12 * f8) / 2.0f;
        this.f119x = (int) (((f18 * f7) + f16) - f20);
        float f21 = (f19 * f7) + f17;
        float f22 = (f15 * f9) / 2.0f;
        this.f120y = (int) (f21 - f22);
        this.width = (int) (f11 + r9);
        this.height = (int) (f14 + r12);
        float f23 = Float.isNaN(keyPosition.mPercentX) ? f7 : keyPosition.mPercentX;
        float f24 = Float.isNaN(keyPosition.mAltPercentY) ? 0.0f : keyPosition.mAltPercentY;
        if (!Float.isNaN(keyPosition.mPercentY)) {
            f7 = keyPosition.mPercentY;
        }
        float f25 = Float.isNaN(keyPosition.mAltPercentX) ? 0.0f : keyPosition.mAltPercentX;
        this.mMode = 2;
        this.f119x = (int) (((f25 * f19) + ((f23 * f18) + motionPaths.f119x)) - f20);
        this.f120y = (int) (((f19 * f7) + ((f18 * f24) + motionPaths.f120y)) - f22);
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void initPath(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f7 = keyPosition.mFramePosition / 100.0f;
        this.time = f7;
        this.mDrawPath = keyPosition.mDrawPath;
        float f8 = Float.isNaN(keyPosition.mPercentWidth) ? f7 : keyPosition.mPercentWidth;
        float f9 = Float.isNaN(keyPosition.mPercentHeight) ? f7 : keyPosition.mPercentHeight;
        float f10 = motionPaths2.width - motionPaths.width;
        float f11 = motionPaths2.height - motionPaths.height;
        this.position = this.time;
        if (!Float.isNaN(keyPosition.mPercentX)) {
            f7 = keyPosition.mPercentX;
        }
        float f12 = motionPaths.f119x;
        float f13 = motionPaths.width;
        float f14 = motionPaths.f120y;
        float f15 = motionPaths.height;
        float f16 = ((motionPaths2.width / 2.0f) + motionPaths2.f119x) - ((f13 / 2.0f) + f12);
        float f17 = ((motionPaths2.height / 2.0f) + motionPaths2.f120y) - ((f15 / 2.0f) + f14);
        float f18 = f16 * f7;
        float f19 = (f10 * f8) / 2.0f;
        this.f119x = (int) ((f12 + f18) - f19);
        float f20 = f7 * f17;
        float f21 = (f11 * f9) / 2.0f;
        this.f120y = (int) ((f14 + f20) - f21);
        this.width = (int) (f13 + r7);
        this.height = (int) (f15 + r8);
        float f22 = Float.isNaN(keyPosition.mPercentY) ? 0.0f : keyPosition.mPercentY;
        this.mMode = 1;
        float f23 = (int) ((motionPaths.f119x + f18) - f19);
        this.f119x = f23;
        float f24 = (int) ((motionPaths.f120y + f20) - f21);
        this.f120y = f24;
        this.f119x = f23 + ((-f17) * f22);
        this.f120y = f24 + (f16 * f22);
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void initScreen(int i7, int i8, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f7 = keyPosition.mFramePosition / 100.0f;
        this.time = f7;
        this.mDrawPath = keyPosition.mDrawPath;
        float f8 = Float.isNaN(keyPosition.mPercentWidth) ? f7 : keyPosition.mPercentWidth;
        float f9 = Float.isNaN(keyPosition.mPercentHeight) ? f7 : keyPosition.mPercentHeight;
        float f10 = motionPaths2.width;
        float f11 = f10 - motionPaths.width;
        float f12 = motionPaths2.height;
        float f13 = f12 - motionPaths.height;
        this.position = this.time;
        float f14 = motionPaths.f119x;
        float f15 = motionPaths.f120y;
        float f16 = (f10 / 2.0f) + motionPaths2.f119x;
        float f17 = (f12 / 2.0f) + motionPaths2.f120y;
        float f18 = f11 * f8;
        this.f119x = (int) ((((f16 - ((r8 / 2.0f) + f14)) * f7) + f14) - (f18 / 2.0f));
        float f19 = f13 * f9;
        this.f120y = (int) ((((f17 - ((r11 / 2.0f) + f15)) * f7) + f15) - (f19 / 2.0f));
        this.width = (int) (r8 + f18);
        this.height = (int) (r11 + f19);
        this.mMode = 3;
        if (!Float.isNaN(keyPosition.mPercentX)) {
            this.f119x = (int) (keyPosition.mPercentX * ((int) (i7 - this.width)));
        }
        if (!Float.isNaN(keyPosition.mPercentY)) {
            this.f120y = (int) (keyPosition.mPercentY * ((int) (i8 - this.height)));
        }
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void setBounds(float f7, float f8, float f9, float f10) {
        this.f119x = f7;
        this.f120y = f8;
        this.width = f9;
        this.height = f10;
    }

    public void setDpDt(float f7, float f8, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        for (int i7 = 0; i7 < iArr.length; i7++) {
            float f13 = (float) dArr[i7];
            double d7 = dArr2[i7];
            int i8 = iArr[i7];
            if (i8 == 1) {
                f9 = f13;
            } else if (i8 == 2) {
                f11 = f13;
            } else if (i8 == 3) {
                f10 = f13;
            } else if (i8 == 4) {
                f12 = f13;
            }
        }
        float f14 = f9 - ((0.0f * f10) / 2.0f);
        float f15 = f11 - ((0.0f * f12) / 2.0f);
        fArr[0] = (((f10 * 1.0f) + f14) * f7) + ((1.0f - f7) * f14) + 0.0f;
        fArr[1] = (((f12 * 1.0f) + f15) * f8) + ((1.0f - f8) * f15) + 0.0f;
    }

    public void setView(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f7;
        float f8 = this.f119x;
        float f9 = this.f120y;
        float f10 = this.width;
        float f11 = this.height;
        if (iArr.length != 0 && this.mTempValue.length <= iArr[iArr.length - 1]) {
            int i7 = iArr[iArr.length - 1] + 1;
            this.mTempValue = new double[i7];
            this.mTempDelta = new double[i7];
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i8 = 0; i8 < iArr.length; i8++) {
            this.mTempValue[iArr[i8]] = dArr[i8];
            this.mTempDelta[iArr[i8]] = dArr2[i8];
        }
        int i9 = 0;
        float f12 = Float.NaN;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        float f16 = 0.0f;
        while (true) {
            double[] dArr4 = this.mTempValue;
            if (i9 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i9]) && (dArr3 == null || dArr3[i9] == 0.0d)) {
                f7 = f8;
            } else {
                double d7 = dArr3 != null ? dArr3[i9] : 0.0d;
                if (!Double.isNaN(this.mTempValue[i9])) {
                    d7 = this.mTempValue[i9] + d7;
                }
                f7 = f8;
                float f17 = (float) d7;
                float f18 = (float) this.mTempDelta[i9];
                if (i9 == 1) {
                    f13 = f18;
                    f8 = f17;
                } else if (i9 == 2) {
                    f9 = f17;
                    f15 = f18;
                } else if (i9 == 3) {
                    f10 = f17;
                    f14 = f18;
                } else if (i9 == 4) {
                    f11 = f17;
                    f16 = f18;
                } else if (i9 == 5) {
                    f8 = f7;
                    f12 = f17;
                }
                i9++;
            }
            f8 = f7;
            i9++;
        }
        float f19 = f8;
        if (!Float.isNaN(f12)) {
            view.setRotation((float) (Math.toDegrees(Math.atan2((f16 / 2.0f) + f15, (f14 / 2.0f) + f13)) + f12 + (Float.isNaN(Float.NaN) ? 0.0f : Float.NaN)));
        } else if (!Float.isNaN(Float.NaN)) {
            view.setRotation(Float.NaN);
        }
        float f20 = f19 + 0.5f;
        int i10 = (int) f20;
        float f21 = f9 + 0.5f;
        int i11 = (int) f21;
        int i12 = (int) (f20 + f10);
        int i13 = (int) (f21 + f11);
        int i14 = i12 - i10;
        int i15 = i13 - i11;
        if ((i14 == view.getMeasuredWidth() && i15 == view.getMeasuredHeight()) ? false : true) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i14, 1073741824), View.MeasureSpec.makeMeasureSpec(i15, 1073741824));
        }
        view.layout(i10, i11, i12, i13);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull MotionPaths motionPaths) {
        return Float.compare(this.position, motionPaths.position);
    }

    public MotionPaths(int i7, int i8, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        int i9 = keyPosition.mPositionType;
        if (i9 == 1) {
            initPath(keyPosition, motionPaths, motionPaths2);
        } else if (i9 != 2) {
            initCartesian(keyPosition, motionPaths, motionPaths2);
        } else {
            initScreen(i7, i8, keyPosition, motionPaths, motionPaths2);
        }
    }
}
