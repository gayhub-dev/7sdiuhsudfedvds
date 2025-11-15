package android.support.v4.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ListView;

/* loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingParent, NestedScrollingChild {
    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final int CIRCLE_BG_LIGHT = -328966;

    @VisibleForTesting
    public static final int CIRCLE_DIAMETER = 40;

    @VisibleForTesting
    public static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    public static final int DEFAULT_SLINGSHOT_DISTANCE = -1;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int MAX_ALPHA = 255;
    private static final float MAX_PROGRESS_ANGLE = 0.8f;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private OnChildScrollUpCallback mChildScrollUpCallback;
    private int mCircleDiameter;
    public CircleImageView mCircleView;
    private int mCircleViewIndex;
    public int mCurrentTargetOffsetTop;
    public int mCustomSlingshotDistance;
    private final DecelerateInterpolator mDecelerateInterpolator;
    public int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    public OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private boolean mNestedScrollInProgress;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    public boolean mNotify;
    public int mOriginalOffsetTop;
    private final int[] mParentOffsetInWindow;
    private final int[] mParentScrollConsumed;
    public CircularProgressDrawable mProgress;
    private Animation.AnimationListener mRefreshListener;
    public boolean mRefreshing;
    private boolean mReturningToStart;
    public boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    public int mSpinnerOffsetEnd;
    public float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private float mTotalUnconsumed;
    private int mTouchSlop;
    public boolean mUsingCustomStart;
    private static final String LOG_TAG = "SwipeRefreshLayout";
    private static final int[] LAYOUT_ATTRS = {R.attr.enabled};

    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(@NonNull SwipeRefreshLayout swipeRefreshLayout, @Nullable View view);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public SwipeRefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    private void animateOffsetToCorrectPosition(int i7, Animation.AnimationListener animationListener) {
        this.mFrom = i7;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200L);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int i7, Animation.AnimationListener animationListener) {
        if (this.mScale) {
            startScaleDownReturnToStartAnimation(i7, animationListener);
            return;
        }
        this.mFrom = i7;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200L);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToStartPosition);
    }

    private void createProgressView() {
        this.mCircleView = new CircleImageView(getContext(), CIRCLE_BG_LIGHT);
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getContext());
        this.mProgress = circularProgressDrawable;
        circularProgressDrawable.setStyle(1);
        this.mCircleView.setImageDrawable(this.mProgress);
        this.mCircleView.setVisibility(8);
        addView(this.mCircleView);
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            for (int i7 = 0; i7 < getChildCount(); i7++) {
                View childAt = getChildAt(i7);
                if (!childAt.equals(this.mCircleView)) {
                    this.mTarget = childAt;
                    return;
                }
            }
        }
    }

    private void finishSpinner(float f7) {
        if (f7 > this.mTotalDragDistance) {
            setRefreshing(true, true);
            return;
        }
        this.mRefreshing = false;
        this.mProgress.setStartEndTrim(0.0f, 0.0f);
        animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, this.mScale ? null : new Animation.AnimationListener() { // from class: android.support.v4.widget.SwipeRefreshLayout.5
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
                if (swipeRefreshLayout.mScale) {
                    return;
                }
                swipeRefreshLayout.startScaleDownAnimation(null);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.mProgress.setArrowEnabled(false);
    }

    private boolean isAnimationRunning(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    private void moveSpinner(float f7) {
        this.mProgress.setArrowEnabled(true);
        float fMin = Math.min(1.0f, Math.abs(f7 / this.mTotalDragDistance));
        float fMax = (((float) Math.max(fMin - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f7) - this.mTotalDragDistance;
        int i7 = this.mCustomSlingshotDistance;
        if (i7 <= 0) {
            i7 = this.mUsingCustomStart ? this.mSpinnerOffsetEnd - this.mOriginalOffsetTop : this.mSpinnerOffsetEnd;
        }
        float f8 = i7;
        double dMax = Math.max(0.0f, Math.min(fAbs, f8 * DECELERATE_INTERPOLATION_FACTOR) / f8) / 4.0f;
        float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * DECELERATE_INTERPOLATION_FACTOR;
        int i8 = this.mOriginalOffsetTop + ((int) ((f8 * fMin) + (f8 * fPow * DECELERATE_INTERPOLATION_FACTOR)));
        if (this.mCircleView.getVisibility() != 0) {
            this.mCircleView.setVisibility(0);
        }
        if (!this.mScale) {
            this.mCircleView.setScaleX(1.0f);
            this.mCircleView.setScaleY(1.0f);
        }
        if (this.mScale) {
            setAnimationProgress(Math.min(1.0f, f7 / this.mTotalDragDistance));
        }
        if (f7 < this.mTotalDragDistance) {
            if (this.mProgress.getAlpha() > 76 && !isAnimationRunning(this.mAlphaStartAnimation)) {
                startProgressAlphaStartAnimation();
            }
        } else if (this.mProgress.getAlpha() < 255 && !isAnimationRunning(this.mAlphaMaxAnimation)) {
            startProgressAlphaMaxAnimation();
        }
        this.mProgress.setStartEndTrim(0.0f, Math.min(MAX_PROGRESS_ANGLE, fMax * MAX_PROGRESS_ANGLE));
        this.mProgress.setArrowScale(Math.min(1.0f, fMax));
        this.mProgress.setProgressRotation(((fPow * DECELERATE_INTERPOLATION_FACTOR) + ((fMax * 0.4f) - 0.25f)) * 0.5f);
        setTargetOffsetTopAndBottom(i8 - this.mCurrentTargetOffsetTop);
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    private void setColorViewAlpha(int i7) {
        this.mCircleView.getBackground().setAlpha(i7);
        this.mProgress.setAlpha(i7);
    }

    private Animation startAlphaAnimation(final int i7, final int i8) {
        Animation animation = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.4
            @Override // android.view.animation.Animation
            public void applyTransformation(float f7, Transformation transformation) {
                SwipeRefreshLayout.this.mProgress.setAlpha((int) (((i8 - r0) * f7) + i7));
            }
        };
        animation.setDuration(300L);
        this.mCircleView.setAnimationListener(null);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(animation);
        return animation;
    }

    private void startDragging(float f7) {
        float f8 = this.mInitialDownY;
        float f9 = f7 - f8;
        int i7 = this.mTouchSlop;
        if (f9 <= i7 || this.mIsBeingDragged) {
            return;
        }
        this.mInitialMotionY = f8 + i7;
        this.mIsBeingDragged = true;
        this.mProgress.setAlpha(76);
    }

    private void startProgressAlphaMaxAnimation() {
        this.mAlphaMaxAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 255);
    }

    private void startProgressAlphaStartAnimation() {
        this.mAlphaStartAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 76);
    }

    private void startScaleDownReturnToStartAnimation(int i7, Animation.AnimationListener animationListener) {
        this.mFrom = i7;
        this.mStartingScale = this.mCircleView.getScaleX();
        Animation animation = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.8
            @Override // android.view.animation.Animation
            public void applyTransformation(float f7, Transformation transformation) {
                SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
                float f8 = swipeRefreshLayout.mStartingScale;
                swipeRefreshLayout.setAnimationProgress(((-f8) * f7) + f8);
                SwipeRefreshLayout.this.moveToStart(f7);
            }
        };
        this.mScaleDownToStartAnimation = animation;
        animation.setDuration(150L);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
    }

    private void startScaleUpAnimation(Animation.AnimationListener animationListener) {
        this.mCircleView.setVisibility(0);
        this.mProgress.setAlpha(255);
        Animation animation = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.2
            @Override // android.view.animation.Animation
            public void applyTransformation(float f7, Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(f7);
            }
        };
        this.mScaleAnimation = animation;
        animation.setDuration(this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleAnimation);
    }

    public boolean canChildScrollUp() {
        OnChildScrollUpCallback onChildScrollUpCallback = this.mChildScrollUpCallback;
        if (onChildScrollUpCallback != null) {
            return onChildScrollUpCallback.canChildScrollUp(this, this.mTarget);
        }
        View view = this.mTarget;
        return view instanceof ListView ? ListViewCompat.canScrollList((ListView) view, -1) : view.canScrollVertically(-1);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f7, float f8, boolean z6) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(f7, f8, z6);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f7, float f8) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(f7, f8);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i7, int i8, int[] iArr, int[] iArr2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(i7, i8, iArr, iArr2);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, int[] iArr) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(i7, i8, i9, i10, iArr);
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i7, int i8) {
        int i9 = this.mCircleViewIndex;
        return i9 < 0 ? i8 : i8 == i7 + (-1) ? i9 : i8 >= i9 ? i8 + 1 : i8;
    }

    @Override // android.view.ViewGroup, android.support.v4.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    public int getProgressCircleDiameter() {
        return this.mCircleDiameter;
    }

    public int getProgressViewEndOffset() {
        return this.mSpinnerOffsetEnd;
    }

    public int getProgressViewStartOffset() {
        return this.mOriginalOffsetTop;
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    public void moveToStart(float f7) {
        setTargetOffsetTopAndBottom((this.mFrom + ((int) ((this.mOriginalOffsetTop - r0) * f7))) - this.mCircleView.getTop());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        reset();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0051  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            r4.ensureTarget()
            int r0 = r5.getActionMasked()
            boolean r1 = r4.mReturningToStart
            r2 = 0
            if (r1 == 0) goto L10
            if (r0 != 0) goto L10
            r4.mReturningToStart = r2
        L10:
            boolean r1 = r4.isEnabled()
            if (r1 == 0) goto L7a
            boolean r1 = r4.mReturningToStart
            if (r1 != 0) goto L7a
            boolean r1 = r4.canChildScrollUp()
            if (r1 != 0) goto L7a
            boolean r1 = r4.mRefreshing
            if (r1 != 0) goto L7a
            boolean r1 = r4.mNestedScrollInProgress
            if (r1 == 0) goto L29
            goto L7a
        L29:
            if (r0 == 0) goto L56
            r1 = 1
            r3 = -1
            if (r0 == r1) goto L51
            r1 = 2
            if (r0 == r1) goto L3d
            r1 = 3
            if (r0 == r1) goto L51
            r1 = 6
            if (r0 == r1) goto L39
            goto L77
        L39:
            r4.onSecondaryPointerUp(r5)
            goto L77
        L3d:
            int r0 = r4.mActivePointerId
            if (r0 != r3) goto L42
            return r2
        L42:
            int r0 = r5.findPointerIndex(r0)
            if (r0 >= 0) goto L49
            return r2
        L49:
            float r5 = r5.getY(r0)
            r4.startDragging(r5)
            goto L77
        L51:
            r4.mIsBeingDragged = r2
            r4.mActivePointerId = r3
            goto L77
        L56:
            int r0 = r4.mOriginalOffsetTop
            android.support.v4.widget.CircleImageView r1 = r4.mCircleView
            int r1 = r1.getTop()
            int r0 = r0 - r1
            r4.setTargetOffsetTopAndBottom(r0)
            int r0 = r5.getPointerId(r2)
            r4.mActivePointerId = r0
            r4.mIsBeingDragged = r2
            int r0 = r5.findPointerIndex(r0)
            if (r0 >= 0) goto L71
            return r2
        L71:
            float r5 = r5.getY(r0)
            r4.mInitialDownY = r5
        L77:
            boolean r5 = r4.mIsBeingDragged
            return r5
        L7a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SwipeRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.mTarget == null) {
            ensureTarget();
        }
        View view = this.mTarget;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.mCircleView.getMeasuredWidth();
        int measuredHeight2 = this.mCircleView.getMeasuredHeight();
        int i11 = measuredWidth / 2;
        int i12 = measuredWidth2 / 2;
        int i13 = this.mCurrentTargetOffsetTop;
        this.mCircleView.layout(i11 - i12, i13, i11 + i12, measuredHeight2 + i13);
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (this.mTarget == null) {
            ensureTarget();
        }
        View view = this.mTarget;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824));
        this.mCircleViewIndex = -1;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            if (getChildAt(i9) == this.mCircleView) {
                this.mCircleViewIndex = i9;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        return dispatchNestedFling(f7, f8, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f7, float f8) {
        return dispatchNestedPreFling(f7, f8);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.NestedScrollingParent
    public void onNestedPreScroll(View view, int i7, int i8, int[] iArr) {
        if (i8 > 0) {
            float f7 = this.mTotalUnconsumed;
            if (f7 > 0.0f) {
                float f8 = i8;
                if (f8 > f7) {
                    iArr[1] = i8 - ((int) f7);
                    this.mTotalUnconsumed = 0.0f;
                } else {
                    this.mTotalUnconsumed = f7 - f8;
                    iArr[1] = i8;
                }
                moveSpinner(this.mTotalUnconsumed);
            }
        }
        if (this.mUsingCustomStart && i8 > 0 && this.mTotalUnconsumed == 0.0f && Math.abs(i8 - iArr[1]) > 0) {
            this.mCircleView.setVisibility(8);
        }
        int[] iArr2 = this.mParentScrollConsumed;
        if (dispatchNestedPreScroll(i7 - iArr[0], i8 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.NestedScrollingParent
    public void onNestedScroll(View view, int i7, int i8, int i9, int i10) {
        dispatchNestedScroll(i7, i8, i9, i10, this.mParentOffsetInWindow);
        if (i10 + this.mParentOffsetInWindow[1] >= 0 || canChildScrollUp()) {
            return;
        }
        float fAbs = this.mTotalUnconsumed + Math.abs(r11);
        this.mTotalUnconsumed = fAbs;
        moveSpinner(fAbs);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i7) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view, view2, i7);
        startNestedScroll(i7 & 2);
        this.mTotalUnconsumed = 0.0f;
        this.mNestedScrollInProgress = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i7) {
        return (!isEnabled() || this.mReturningToStart || this.mRefreshing || (i7 & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view);
        this.mNestedScrollInProgress = false;
        float f7 = this.mTotalUnconsumed;
        if (f7 > 0.0f) {
            finishSpinner(f7);
            this.mTotalUnconsumed = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp() || this.mRefreshing || this.mNestedScrollInProgress) {
            return false;
        }
        if (actionMasked == 0) {
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mIsBeingDragged = false;
        } else {
            if (actionMasked == 1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                if (iFindPointerIndex < 0) {
                    return false;
                }
                if (this.mIsBeingDragged) {
                    float y6 = (motionEvent.getY(iFindPointerIndex) - this.mInitialMotionY) * 0.5f;
                    this.mIsBeingDragged = false;
                    finishSpinner(y6);
                }
                this.mActivePointerId = -1;
                return false;
            }
            if (actionMasked == 2) {
                int iFindPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
                if (iFindPointerIndex2 < 0) {
                    return false;
                }
                float y7 = motionEvent.getY(iFindPointerIndex2);
                startDragging(y7);
                if (this.mIsBeingDragged) {
                    float f7 = (y7 - this.mInitialMotionY) * 0.5f;
                    if (f7 <= 0.0f) {
                        return false;
                    }
                    moveSpinner(f7);
                }
            } else {
                if (actionMasked == 3) {
                    return false;
                }
                if (actionMasked == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    if (actionIndex < 0) {
                        return false;
                    }
                    this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                } else if (actionMasked == 6) {
                    onSecondaryPointerUp(motionEvent);
                }
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z6) {
        View view = this.mTarget;
        if (view == null || ViewCompat.isNestedScrollingEnabled(view)) {
            super.requestDisallowInterceptTouchEvent(z6);
        }
    }

    public void reset() {
        this.mCircleView.clearAnimation();
        this.mProgress.stop();
        this.mCircleView.setVisibility(8);
        setColorViewAlpha(255);
        if (this.mScale) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCurrentTargetOffsetTop);
        }
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    public void setAnimationProgress(float f7) {
        this.mCircleView.setScaleX(f7);
        this.mCircleView.setScaleY(f7);
    }

    @Deprecated
    public void setColorScheme(@ColorRes int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(@ColorInt int... iArr) {
        ensureTarget();
        this.mProgress.setColorSchemeColors(iArr);
    }

    public void setColorSchemeResources(@ColorRes int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i7 = 0; i7 < iArr.length; i7++) {
            iArr2[i7] = ContextCompat.getColor(context, iArr[i7]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i7) {
        this.mTotalDragDistance = i7;
    }

    @Override // android.view.View
    public void setEnabled(boolean z6) {
        super.setEnabled(z6);
        if (z6) {
            return;
        }
        reset();
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z6) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(z6);
    }

    public void setOnChildScrollUpCallback(@Nullable OnChildScrollUpCallback onChildScrollUpCallback) {
        this.mChildScrollUpCallback = onChildScrollUpCallback;
    }

    public void setOnRefreshListener(@Nullable OnRefreshListener onRefreshListener) {
        this.mListener = onRefreshListener;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i7) {
        setProgressBackgroundColorSchemeResource(i7);
    }

    public void setProgressBackgroundColorSchemeColor(@ColorInt int i7) {
        this.mCircleView.setBackgroundColor(i7);
    }

    public void setProgressBackgroundColorSchemeResource(@ColorRes int i7) {
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), i7));
    }

    public void setProgressViewEndTarget(boolean z6, int i7) {
        this.mSpinnerOffsetEnd = i7;
        this.mScale = z6;
        this.mCircleView.invalidate();
    }

    public void setProgressViewOffset(boolean z6, int i7, int i8) {
        this.mScale = z6;
        this.mOriginalOffsetTop = i7;
        this.mSpinnerOffsetEnd = i8;
        this.mUsingCustomStart = true;
        reset();
        this.mRefreshing = false;
    }

    public void setRefreshing(boolean z6) {
        if (!z6 || this.mRefreshing == z6) {
            setRefreshing(z6, false);
            return;
        }
        this.mRefreshing = z6;
        setTargetOffsetTopAndBottom((!this.mUsingCustomStart ? this.mSpinnerOffsetEnd + this.mOriginalOffsetTop : this.mSpinnerOffsetEnd) - this.mCurrentTargetOffsetTop);
        this.mNotify = false;
        startScaleUpAnimation(this.mRefreshListener);
    }

    public void setSize(int i7) {
        if (i7 == 0 || i7 == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i7 == 0) {
                this.mCircleDiameter = (int) (displayMetrics.density * 56.0f);
            } else {
                this.mCircleDiameter = (int) (displayMetrics.density * 40.0f);
            }
            this.mCircleView.setImageDrawable(null);
            this.mProgress.setStyle(i7);
            this.mCircleView.setImageDrawable(this.mProgress);
        }
    }

    public void setSlingshotDistance(@Px int i7) {
        this.mCustomSlingshotDistance = i7;
    }

    public void setTargetOffsetTopAndBottom(int i7) {
        this.mCircleView.bringToFront();
        ViewCompat.offsetTopAndBottom(this.mCircleView, i7);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean startNestedScroll(int i7) {
        return this.mNestedScrollingChildHelper.startNestedScroll(i7);
    }

    public void startScaleDownAnimation(Animation.AnimationListener animationListener) {
        Animation animation = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.3
            @Override // android.view.animation.Animation
            public void applyTransformation(float f7, Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(1.0f - f7);
            }
        };
        this.mScaleDownAnimation = animation;
        animation.setDuration(150L);
        this.mCircleView.setAnimationListener(animationListener);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }

    public SwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRefreshing = false;
        this.mTotalDragDistance = -1.0f;
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mActivePointerId = -1;
        this.mCircleViewIndex = -1;
        this.mRefreshListener = new Animation.AnimationListener() { // from class: android.support.v4.widget.SwipeRefreshLayout.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                OnRefreshListener onRefreshListener;
                SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
                if (!swipeRefreshLayout.mRefreshing) {
                    swipeRefreshLayout.reset();
                    return;
                }
                swipeRefreshLayout.mProgress.setAlpha(255);
                SwipeRefreshLayout.this.mProgress.start();
                SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
                if (swipeRefreshLayout2.mNotify && (onRefreshListener = swipeRefreshLayout2.mListener) != null) {
                    onRefreshListener.onRefresh();
                }
                SwipeRefreshLayout swipeRefreshLayout3 = SwipeRefreshLayout.this;
                swipeRefreshLayout3.mCurrentTargetOffsetTop = swipeRefreshLayout3.mCircleView.getTop();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
        this.mAnimateToCorrectPosition = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.6
            @Override // android.view.animation.Animation
            public void applyTransformation(float f7, Transformation transformation) {
                SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
                int iAbs = !swipeRefreshLayout.mUsingCustomStart ? swipeRefreshLayout.mSpinnerOffsetEnd - Math.abs(swipeRefreshLayout.mOriginalOffsetTop) : swipeRefreshLayout.mSpinnerOffsetEnd;
                SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
                SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((swipeRefreshLayout2.mFrom + ((int) ((iAbs - r1) * f7))) - swipeRefreshLayout2.mCircleView.getTop());
                SwipeRefreshLayout.this.mProgress.setArrowScale(1.0f - f7);
            }
        };
        this.mAnimateToStartPosition = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.7
            @Override // android.view.animation.Animation
            public void applyTransformation(float f7, Transformation transformation) {
                SwipeRefreshLayout.this.moveToStart(f7);
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mCircleDiameter = (int) (displayMetrics.density * 40.0f);
        createProgressView();
        setChildrenDrawingOrderEnabled(true);
        int i7 = (int) (displayMetrics.density * 64.0f);
        this.mSpinnerOffsetEnd = i7;
        this.mTotalDragDistance = i7;
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        int i8 = -this.mCircleDiameter;
        this.mCurrentTargetOffsetTop = i8;
        this.mOriginalOffsetTop = i8;
        moveToStart(1.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        setEnabled(typedArrayObtainStyledAttributes.getBoolean(0, true));
        typedArrayObtainStyledAttributes.recycle();
    }

    private void setRefreshing(boolean z6, boolean z7) {
        if (this.mRefreshing != z6) {
            this.mNotify = z7;
            ensureTarget();
            this.mRefreshing = z6;
            if (z6) {
                animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            } else {
                startScaleDownAnimation(this.mRefreshListener);
            }
        }
    }
}
