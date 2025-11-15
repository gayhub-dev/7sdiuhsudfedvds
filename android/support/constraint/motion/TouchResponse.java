package android.support.constraint.motion;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.support.constraint.C0071R;
import android.support.constraint.motion.MotionLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
class TouchResponse {
    private static final boolean DEBUG = false;
    public static final int FLAG_DISABLE_POST_SCROLL = 1;
    public static final int FLAG_DISABLE_SCROLL = 2;
    private static final int SIDE_BOTTOM = 3;
    private static final int SIDE_END = 6;
    private static final int SIDE_LEFT = 1;
    private static final int SIDE_MIDDLE = 4;
    private static final int SIDE_RIGHT = 2;
    private static final int SIDE_START = 5;
    private static final int SIDE_TOP = 0;
    private static final String TAG = "TouchResponse";
    private static final int TOUCH_DOWN = 1;
    private static final int TOUCH_END = 5;
    private static final int TOUCH_LEFT = 2;
    private static final int TOUCH_RIGHT = 3;
    private static final int TOUCH_START = 4;
    private static final int TOUCH_UP = 0;
    private float mLastTouchX;
    private float mLastTouchY;
    private final MotionLayout mMotionLayout;
    private static final float[][] TOUCH_SIDES = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    private static final float[][] TOUCH_DIRECTION = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    private int mTouchAnchorSide = 0;
    private int mTouchSide = 0;
    private int mOnTouchUp = 0;
    private int mTouchAnchorId = -1;
    private int mTouchRegionId = -1;
    private int mLimitBoundsTo = -1;
    private float mTouchAnchorY = 0.5f;
    private float mTouchAnchorX = 0.5f;
    private float mTouchDirectionX = 0.0f;
    private float mTouchDirectionY = 1.0f;
    private boolean mDragStarted = false;
    private float[] mAnchorDpDt = new float[2];
    private float mMaxVelocity = 4.0f;
    private float mMaxAcceleration = 1.2f;
    private boolean mMoveWhenScrollAtTop = true;
    private float mDragScale = 1.0f;
    private int mFlags = 0;
    private float mDragThreshold = 10.0f;

    public TouchResponse(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.mMotionLayout = motionLayout;
        fillFromAttributeList(context, Xml.asAttributeSet(xmlPullParser));
    }

    private void fill(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArray.getIndex(i7);
            if (index == C0071R.styleable.OnSwipe_touchAnchorId) {
                this.mTouchAnchorId = typedArray.getResourceId(index, this.mTouchAnchorId);
            } else if (index == C0071R.styleable.OnSwipe_touchAnchorSide) {
                int i8 = typedArray.getInt(index, this.mTouchAnchorSide);
                this.mTouchAnchorSide = i8;
                float[][] fArr = TOUCH_SIDES;
                this.mTouchAnchorX = fArr[i8][0];
                this.mTouchAnchorY = fArr[i8][1];
            } else if (index == C0071R.styleable.OnSwipe_dragDirection) {
                int i9 = typedArray.getInt(index, this.mTouchSide);
                this.mTouchSide = i9;
                float[][] fArr2 = TOUCH_DIRECTION;
                this.mTouchDirectionX = fArr2[i9][0];
                this.mTouchDirectionY = fArr2[i9][1];
            } else if (index == C0071R.styleable.OnSwipe_maxVelocity) {
                this.mMaxVelocity = typedArray.getFloat(index, this.mMaxVelocity);
            } else if (index == C0071R.styleable.OnSwipe_maxAcceleration) {
                this.mMaxAcceleration = typedArray.getFloat(index, this.mMaxAcceleration);
            } else if (index == C0071R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.mMoveWhenScrollAtTop = typedArray.getBoolean(index, this.mMoveWhenScrollAtTop);
            } else if (index == C0071R.styleable.OnSwipe_dragScale) {
                this.mDragScale = typedArray.getFloat(index, this.mDragScale);
            } else if (index == C0071R.styleable.OnSwipe_dragThreshold) {
                this.mDragThreshold = typedArray.getFloat(index, this.mDragThreshold);
            } else if (index == C0071R.styleable.OnSwipe_touchRegionId) {
                this.mTouchRegionId = typedArray.getResourceId(index, this.mTouchRegionId);
            } else if (index == C0071R.styleable.OnSwipe_onTouchUp) {
                this.mOnTouchUp = typedArray.getInt(index, this.mOnTouchUp);
            } else if (index == C0071R.styleable.OnSwipe_nestedScrollFlags) {
                this.mFlags = typedArray.getInteger(index, 0);
            } else if (index == C0071R.styleable.OnSwipe_limitBoundsTo) {
                this.mLimitBoundsTo = typedArray.getResourceId(index, 0);
            }
        }
    }

    private void fillFromAttributeList(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0071R.styleable.OnSwipe);
        fill(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public float dot(float f7, float f8) {
        return (f8 * this.mTouchDirectionY) + (f7 * this.mTouchDirectionX);
    }

    public int getAnchorId() {
        return this.mTouchAnchorId;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public RectF getLimitBoundsTo(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i7 = this.mLimitBoundsTo;
        if (i7 == -1 || (viewFindViewById = viewGroup.findViewById(i7)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public int getLimitBoundsToId() {
        return this.mLimitBoundsTo;
    }

    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    public boolean getMoveWhenScrollAtTop() {
        return this.mMoveWhenScrollAtTop;
    }

    public float getProgressDirection(float f7, float f8) throws Resources.NotFoundException {
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, this.mMotionLayout.getProgress(), this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f9 = this.mTouchDirectionX;
        if (f9 != 0.0f) {
            float[] fArr = this.mAnchorDpDt;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f7 * f9) / fArr[0];
        }
        float[] fArr2 = this.mAnchorDpDt;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f8 * this.mTouchDirectionY) / fArr2[1];
    }

    public RectF getTouchRegion(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i7 = this.mTouchRegionId;
        if (i7 == -1 || (viewFindViewById = viewGroup.findViewById(i7)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public int getTouchRegionId() {
        return this.mTouchRegionId;
    }

    public void processTouchEvent(MotionEvent motionEvent, MotionLayout.MotionTracker motionTracker, int i7, MotionScene motionScene) throws Resources.NotFoundException {
        int i8;
        motionTracker.addMovement(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mLastTouchX = motionEvent.getRawX();
            this.mLastTouchY = motionEvent.getRawY();
            this.mDragStarted = false;
            return;
        }
        if (action == 1) {
            this.mDragStarted = false;
            motionTracker.computeCurrentVelocity(1000);
            float xVelocity = motionTracker.getXVelocity();
            float yVelocity = motionTracker.getYVelocity();
            float progress = this.mMotionLayout.getProgress();
            int i9 = this.mTouchAnchorId;
            if (i9 != -1) {
                this.mMotionLayout.getAnchorDpDt(i9, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
            } else {
                float fMin = Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                float[] fArr = this.mAnchorDpDt;
                fArr[1] = this.mTouchDirectionY * fMin;
                fArr[0] = fMin * this.mTouchDirectionX;
            }
            float f7 = this.mTouchDirectionX;
            float[] fArr2 = this.mAnchorDpDt;
            float f8 = fArr2[0];
            float f9 = fArr2[1];
            float f10 = f7 != 0.0f ? xVelocity / fArr2[0] : yVelocity / fArr2[1];
            float f11 = !Float.isNaN(f10) ? (f10 / 3.0f) + progress : progress;
            if (f11 == 0.0f || f11 == 1.0f || (i8 = this.mOnTouchUp) == 3) {
                if (0.0f >= f11 || 1.0f <= f11) {
                    this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    return;
                }
                return;
            }
            this.mMotionLayout.touchAnimateTo(i8, ((double) f11) < 0.5d ? 0.0f : 1.0f, f10);
            if (0.0f >= progress || 1.0f <= progress) {
                this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
                return;
            }
            return;
        }
        if (action != 2) {
            return;
        }
        float rawY = motionEvent.getRawY() - this.mLastTouchY;
        float rawX = motionEvent.getRawX() - this.mLastTouchX;
        if (Math.abs((this.mTouchDirectionY * rawY) + (this.mTouchDirectionX * rawX)) > this.mDragThreshold || this.mDragStarted) {
            float progress2 = this.mMotionLayout.getProgress();
            if (!this.mDragStarted) {
                this.mDragStarted = true;
                this.mMotionLayout.setProgress(progress2);
            }
            int i10 = this.mTouchAnchorId;
            if (i10 != -1) {
                this.mMotionLayout.getAnchorDpDt(i10, progress2, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
            } else {
                float fMin2 = Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                float[] fArr3 = this.mAnchorDpDt;
                fArr3[1] = this.mTouchDirectionY * fMin2;
                fArr3[0] = fMin2 * this.mTouchDirectionX;
            }
            float f12 = this.mTouchDirectionX;
            float[] fArr4 = this.mAnchorDpDt;
            if (Math.abs(((this.mTouchDirectionY * fArr4[1]) + (f12 * fArr4[0])) * this.mDragScale) < 0.01d) {
                float[] fArr5 = this.mAnchorDpDt;
                fArr5[0] = 0.01f;
                fArr5[1] = 0.01f;
            }
            float fMax = Math.max(Math.min(progress2 + (this.mTouchDirectionX != 0.0f ? rawX / this.mAnchorDpDt[0] : rawY / this.mAnchorDpDt[1]), 1.0f), 0.0f);
            if (fMax != this.mMotionLayout.getProgress()) {
                this.mMotionLayout.setProgress(fMax);
                motionTracker.computeCurrentVelocity(1000);
                this.mMotionLayout.mLastVelocity = this.mTouchDirectionX != 0.0f ? motionTracker.getXVelocity() / this.mAnchorDpDt[0] : motionTracker.getYVelocity() / this.mAnchorDpDt[1];
            } else {
                this.mMotionLayout.mLastVelocity = 0.0f;
            }
            this.mLastTouchX = motionEvent.getRawX();
            this.mLastTouchY = motionEvent.getRawY();
        }
    }

    public void scrollMove(float f7, float f8) throws Resources.NotFoundException {
        float progress = this.mMotionLayout.getProgress();
        if (!this.mDragStarted) {
            this.mDragStarted = true;
            this.mMotionLayout.setProgress(progress);
        }
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f9 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        if (Math.abs((this.mTouchDirectionY * fArr[1]) + (f9 * fArr[0])) < 0.01d) {
            float[] fArr2 = this.mAnchorDpDt;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f10 = this.mTouchDirectionX;
        float fMax = Math.max(Math.min(progress + (f10 != 0.0f ? (f7 * f10) / this.mAnchorDpDt[0] : (f8 * this.mTouchDirectionY) / this.mAnchorDpDt[1]), 1.0f), 0.0f);
        if (fMax != this.mMotionLayout.getProgress()) {
            this.mMotionLayout.setProgress(fMax);
        }
    }

    public void scrollUp(float f7, float f8) throws Resources.NotFoundException {
        this.mDragStarted = false;
        float progress = this.mMotionLayout.getProgress();
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f9 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        float f10 = fArr[0];
        float f11 = this.mTouchDirectionY;
        float f12 = fArr[1];
        float f13 = f9 != 0.0f ? (f7 * f9) / fArr[0] : (f8 * f11) / fArr[1];
        if (!Float.isNaN(f13)) {
            progress += f13 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z6 = progress != 1.0f;
            int i7 = this.mOnTouchUp;
            if ((i7 != 3) && z6) {
                this.mMotionLayout.touchAnimateTo(i7, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f13);
            }
        }
    }

    public void setAnchorId(int i7) {
        this.mTouchAnchorId = i7;
    }

    public void setDown(float f7, float f8) {
        this.mLastTouchX = f7;
        this.mLastTouchY = f8;
    }

    public void setMaxAcceleration(float f7) {
        this.mMaxAcceleration = f7;
    }

    public void setMaxVelocity(float f7) {
        this.mMaxVelocity = f7;
    }

    public void setRTL(boolean z6) {
        if (z6) {
            float[][] fArr = TOUCH_DIRECTION;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = TOUCH_SIDES;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = TOUCH_DIRECTION;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = TOUCH_SIDES;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = TOUCH_SIDES;
        int i7 = this.mTouchAnchorSide;
        this.mTouchAnchorX = fArr5[i7][0];
        this.mTouchAnchorY = fArr5[i7][1];
        float[][] fArr6 = TOUCH_DIRECTION;
        int i8 = this.mTouchSide;
        this.mTouchDirectionX = fArr6[i8][0];
        this.mTouchDirectionY = fArr6[i8][1];
    }

    public void setTouchAnchorLocation(float f7, float f8) {
        this.mTouchAnchorX = f7;
        this.mTouchAnchorY = f8;
    }

    public void setUpTouchEvent(float f7, float f8) {
        this.mLastTouchX = f7;
        this.mLastTouchY = f8;
        this.mDragStarted = false;
    }

    public void setupTouch() {
        View viewFindViewById;
        int i7 = this.mTouchAnchorId;
        if (i7 != -1) {
            viewFindViewById = this.mMotionLayout.findViewById(i7);
            if (viewFindViewById == null) {
                Debug.getName(this.mMotionLayout.getContext(), this.mTouchAnchorId);
            }
        } else {
            viewFindViewById = null;
        }
        if (viewFindViewById instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) viewFindViewById;
            nestedScrollView.setOnTouchListener(new View.OnTouchListener() { // from class: android.support.constraint.motion.TouchResponse.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: android.support.constraint.motion.TouchResponse.2
                @Override // android.support.v4.widget.NestedScrollView.OnScrollChangeListener
                public void onScrollChange(NestedScrollView nestedScrollView2, int i8, int i9, int i10, int i11) {
                }
            });
        }
    }

    public String toString() {
        return this.mTouchDirectionX + " , " + this.mTouchDirectionY;
    }
}
