package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.support.constraint.C0071R;
import android.support.constraint.motion.utils.Easing;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes.dex */
public class KeyPosition extends KeyPositionBase {
    public static final int KEY_TYPE = 2;
    public static final String NAME = "KeyPosition";
    private static final String PERCENT_X = "percentX";
    private static final String PERCENT_Y = "percentY";
    private static final String TAG = "KeyPosition";
    public static final int TYPE_CARTESIAN = 0;
    public static final int TYPE_PATH = 1;
    public static final int TYPE_SCREEN = 2;
    public String mTransitionEasing = null;
    public int mPathMotionArc = Key.UNSET;
    public int mDrawPath = 0;
    public float mPercentWidth = Float.NaN;
    public float mPercentHeight = Float.NaN;
    public float mPercentX = Float.NaN;
    public float mPercentY = Float.NaN;
    public float mAltPercentX = Float.NaN;
    public float mAltPercentY = Float.NaN;
    public int mPositionType = 0;
    private float mCalculatedPositionX = Float.NaN;
    private float mCalculatedPositionY = Float.NaN;

    public static class Loader {
        private static final int CURVE_FIT = 4;
        private static final int DRAW_PATH = 5;
        private static final int FRAME_POSITION = 2;
        private static final int PATH_MOTION_ARC = 10;
        private static final int PERCENT_HEIGHT = 12;
        private static final int PERCENT_WIDTH = 11;
        private static final int PERCENT_X = 6;
        private static final int PERCENT_Y = 7;
        private static final int SIZE_PERCENT = 8;
        private static final int TARGET_ID = 1;
        private static final int TRANSITION_EASING = 3;
        private static final int TYPE = 9;
        private static SparseIntArray mAttrMap;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(C0071R.styleable.KeyPosition_motionTarget, 1);
            mAttrMap.append(C0071R.styleable.KeyPosition_framePosition, 2);
            mAttrMap.append(C0071R.styleable.KeyPosition_transitionEasing, 3);
            mAttrMap.append(C0071R.styleable.KeyPosition_curveFit, 4);
            mAttrMap.append(C0071R.styleable.KeyPosition_drawPath, 5);
            mAttrMap.append(C0071R.styleable.KeyPosition_percentX, 6);
            mAttrMap.append(C0071R.styleable.KeyPosition_percentY, 7);
            mAttrMap.append(C0071R.styleable.KeyPosition_keyPositionType, 9);
            mAttrMap.append(C0071R.styleable.KeyPosition_sizePercent, 8);
            mAttrMap.append(C0071R.styleable.KeyPosition_percentWidth, 11);
            mAttrMap.append(C0071R.styleable.KeyPosition_percentHeight, 12);
            mAttrMap.append(C0071R.styleable.KeyPosition_pathMotionArc, 10);
        }

        private Loader() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void read(KeyPosition keyPosition, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArray.getIndex(i7);
                switch (mAttrMap.get(index)) {
                    case 1:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyPosition.mTargetId);
                            keyPosition.mTargetId = resourceId;
                            if (resourceId == -1) {
                                keyPosition.mTargetString = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyPosition.mTargetString = typedArray.getString(index);
                            break;
                        } else {
                            keyPosition.mTargetId = typedArray.getResourceId(index, keyPosition.mTargetId);
                            break;
                        }
                    case 2:
                        keyPosition.mFramePosition = typedArray.getInt(index, keyPosition.mFramePosition);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            keyPosition.mTransitionEasing = typedArray.getString(index);
                            break;
                        } else {
                            keyPosition.mTransitionEasing = Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                            break;
                        }
                    case 4:
                        keyPosition.mCurveFit = typedArray.getInteger(index, keyPosition.mCurveFit);
                        break;
                    case 5:
                        keyPosition.mDrawPath = typedArray.getInt(index, keyPosition.mDrawPath);
                        break;
                    case 6:
                        keyPosition.mPercentX = typedArray.getFloat(index, keyPosition.mPercentX);
                        break;
                    case 7:
                        keyPosition.mPercentY = typedArray.getFloat(index, keyPosition.mPercentY);
                        break;
                    case 8:
                        float f7 = typedArray.getFloat(index, keyPosition.mPercentHeight);
                        keyPosition.mPercentWidth = f7;
                        keyPosition.mPercentHeight = f7;
                        break;
                    case 9:
                        keyPosition.mPositionType = typedArray.getInt(index, keyPosition.mPositionType);
                        break;
                    case 10:
                        keyPosition.mPathMotionArc = typedArray.getInt(index, keyPosition.mPathMotionArc);
                        break;
                    case 11:
                        keyPosition.mPercentWidth = typedArray.getFloat(index, keyPosition.mPercentWidth);
                        break;
                    case 12:
                        keyPosition.mPercentHeight = typedArray.getFloat(index, keyPosition.mPercentHeight);
                        break;
                    default:
                        Integer.toHexString(index);
                        mAttrMap.get(index);
                        break;
                }
            }
            int i8 = keyPosition.mFramePosition;
        }
    }

    public KeyPosition() {
        this.mType = 2;
    }

    private void calcCartesianPosition(float f7, float f8, float f9, float f10) {
        float f11 = f9 - f7;
        float f12 = f10 - f8;
        float f13 = Float.isNaN(this.mPercentX) ? 0.0f : this.mPercentX;
        float f14 = Float.isNaN(this.mAltPercentY) ? 0.0f : this.mAltPercentY;
        float f15 = Float.isNaN(this.mPercentY) ? 0.0f : this.mPercentY;
        this.mCalculatedPositionX = (int) (((Float.isNaN(this.mAltPercentX) ? 0.0f : this.mAltPercentX) * f12) + (f13 * f11) + f7);
        this.mCalculatedPositionY = (int) ((f12 * f15) + (f11 * f14) + f8);
    }

    private void calcPathPosition(float f7, float f8, float f9, float f10) {
        float f11 = f9 - f7;
        float f12 = f10 - f8;
        float f13 = this.mPercentX;
        float f14 = (f11 * f13) + f7;
        float f15 = this.mPercentY;
        this.mCalculatedPositionX = ((-f12) * f15) + f14;
        this.mCalculatedPositionY = (f11 * f15) + (f12 * f13) + f8;
    }

    private void calcScreenPosition(int i7, int i8) {
        float f7 = this.mPercentX;
        float f8 = 0;
        this.mCalculatedPositionX = ((i7 - 0) * f7) + f8;
        this.mCalculatedPositionY = ((i8 - 0) * f7) + f8;
    }

    @Override // android.support.constraint.motion.Key
    public void addValues(HashMap<String, SplineSet> map) {
    }

    @Override // android.support.constraint.motion.KeyPositionBase
    public void calcPosition(int i7, int i8, float f7, float f8, float f9, float f10) {
        int i9 = this.mPositionType;
        if (i9 == 1) {
            calcPathPosition(f7, f8, f9, f10);
        } else if (i9 != 2) {
            calcCartesianPosition(f7, f8, f9, f10);
        } else {
            calcScreenPosition(i7, i8);
        }
    }

    @Override // android.support.constraint.motion.KeyPositionBase
    public float getPositionX() {
        return this.mCalculatedPositionX;
    }

    @Override // android.support.constraint.motion.KeyPositionBase
    public float getPositionY() {
        return this.mCalculatedPositionY;
    }

    @Override // android.support.constraint.motion.KeyPositionBase
    public boolean intersects(int i7, int i8, RectF rectF, RectF rectF2, float f7, float f8) {
        calcPosition(i7, i8, rectF.centerX(), rectF.centerY(), rectF2.centerX(), rectF2.centerY());
        return Math.abs(f7 - this.mCalculatedPositionX) < 20.0f && Math.abs(f8 - this.mCalculatedPositionY) < 20.0f;
    }

    @Override // android.support.constraint.motion.Key
    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, C0071R.styleable.KeyPosition));
    }

    @Override // android.support.constraint.motion.KeyPositionBase
    public void positionAttributes(View view, RectF rectF, RectF rectF2, float f7, float f8, String[] strArr, float[] fArr) {
        int i7 = this.mPositionType;
        if (i7 == 1) {
            positionPathAttributes(rectF, rectF2, f7, f8, strArr, fArr);
        } else if (i7 != 2) {
            positionCartAttributes(rectF, rectF2, f7, f8, strArr, fArr);
        } else {
            positionScreenAttributes(view, rectF, rectF2, f7, f8, strArr, fArr);
        }
    }

    public void positionCartAttributes(RectF rectF, RectF rectF2, float f7, float f8, String[] strArr, float[] fArr) {
        float fCenterX = rectF.centerX();
        float fCenterY = rectF.centerY();
        float fCenterX2 = rectF2.centerX() - fCenterX;
        float fCenterY2 = rectF2.centerY() - fCenterY;
        if (strArr[0] == null) {
            strArr[0] = PERCENT_X;
            fArr[0] = (f7 - fCenterX) / fCenterX2;
            strArr[1] = PERCENT_Y;
            fArr[1] = (f8 - fCenterY) / fCenterY2;
            return;
        }
        if (PERCENT_X.equals(strArr[0])) {
            fArr[0] = (f7 - fCenterX) / fCenterX2;
            fArr[1] = (f8 - fCenterY) / fCenterY2;
        } else {
            fArr[1] = (f7 - fCenterX) / fCenterX2;
            fArr[0] = (f8 - fCenterY) / fCenterY2;
        }
    }

    public void positionPathAttributes(RectF rectF, RectF rectF2, float f7, float f8, String[] strArr, float[] fArr) {
        float fCenterX = rectF.centerX();
        float fCenterY = rectF.centerY();
        float fCenterX2 = rectF2.centerX() - fCenterX;
        float fCenterY2 = rectF2.centerY() - fCenterY;
        float fHypot = (float) Math.hypot(fCenterX2, fCenterY2);
        if (fHypot < 1.0E-4d) {
            System.out.println("distance ~ 0");
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            return;
        }
        float f9 = fCenterX2 / fHypot;
        float f10 = fCenterY2 / fHypot;
        float f11 = f8 - fCenterY;
        float f12 = f7 - fCenterX;
        float f13 = ((f9 * f11) - (f12 * f10)) / fHypot;
        float f14 = ((f10 * f11) + (f9 * f12)) / fHypot;
        if (strArr[0] != null) {
            if (PERCENT_X.equals(strArr[0])) {
                fArr[0] = f14;
                fArr[1] = f13;
                return;
            }
            return;
        }
        strArr[0] = PERCENT_X;
        strArr[1] = PERCENT_Y;
        fArr[0] = f14;
        fArr[1] = f13;
    }

    public void positionScreenAttributes(View view, RectF rectF, RectF rectF2, float f7, float f8, String[] strArr, float[] fArr) {
        rectF.centerX();
        rectF.centerY();
        rectF2.centerX();
        rectF2.centerY();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        if (strArr[0] == null) {
            strArr[0] = PERCENT_X;
            fArr[0] = f7 / width;
            strArr[1] = PERCENT_Y;
            fArr[1] = f8 / height;
            return;
        }
        if (PERCENT_X.equals(strArr[0])) {
            fArr[0] = f7 / width;
            fArr[1] = f8 / height;
        } else {
            fArr[1] = f7 / width;
            fArr[0] = f8 / height;
        }
    }

    @Override // android.support.constraint.motion.Key
    public void setValue(String str, Object obj) {
        Objects.requireNonNull(str);
        switch (str) {
            case "transitionEasing":
                this.mTransitionEasing = obj.toString();
                break;
            case "percentWidth":
                this.mPercentWidth = toFloat(obj);
                break;
            case "percentHeight":
                this.mPercentHeight = toFloat(obj);
                break;
            case "drawPath":
                this.mDrawPath = toInt(obj);
                break;
            case "sizePercent":
                float f7 = toFloat(obj);
                this.mPercentWidth = f7;
                this.mPercentHeight = f7;
                break;
            case "percentX":
                this.mPercentX = toFloat(obj);
                break;
            case "percentY":
                this.mPercentY = toFloat(obj);
                break;
        }
    }
}
