package android.support.v7.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

@VisibleForTesting
/* loaded from: classes.dex */
class FastScroller extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
    private static final int ANIMATION_STATE_FADING_IN = 1;
    private static final int ANIMATION_STATE_FADING_OUT = 3;
    private static final int ANIMATION_STATE_IN = 2;
    private static final int ANIMATION_STATE_OUT = 0;
    private static final int DRAG_NONE = 0;
    private static final int DRAG_X = 1;
    private static final int DRAG_Y = 2;
    private static final int HIDE_DELAY_AFTER_DRAGGING_MS = 1200;
    private static final int HIDE_DELAY_AFTER_VISIBLE_MS = 1500;
    private static final int HIDE_DURATION_MS = 500;
    private static final int SCROLLBAR_FULL_OPAQUE = 255;
    private static final int SHOW_DURATION_MS = 500;
    private static final int STATE_DRAGGING = 2;
    private static final int STATE_HIDDEN = 0;
    private static final int STATE_VISIBLE = 1;
    public int mAnimationState;
    private final Runnable mHideRunnable;

    @VisibleForTesting
    public float mHorizontalDragX;

    @VisibleForTesting
    public int mHorizontalThumbCenterX;
    private final StateListDrawable mHorizontalThumbDrawable;
    private final int mHorizontalThumbHeight;

    @VisibleForTesting
    public int mHorizontalThumbWidth;
    private final Drawable mHorizontalTrackDrawable;
    private final int mHorizontalTrackHeight;
    private final int mMargin;
    private final RecyclerView.OnScrollListener mOnScrollListener;
    private RecyclerView mRecyclerView;
    private final int mScrollbarMinimumRange;
    public final ValueAnimator mShowHideAnimator;

    @VisibleForTesting
    public float mVerticalDragY;

    @VisibleForTesting
    public int mVerticalThumbCenterY;
    public final StateListDrawable mVerticalThumbDrawable;

    @VisibleForTesting
    public int mVerticalThumbHeight;
    private final int mVerticalThumbWidth;
    public final Drawable mVerticalTrackDrawable;
    private final int mVerticalTrackWidth;
    private static final int[] PRESSED_STATE_SET = {R.attr.state_pressed};
    private static final int[] EMPTY_STATE_SET = new int[0];
    private int mRecyclerViewWidth = 0;
    private int mRecyclerViewHeight = 0;
    private boolean mNeedVerticalScrollbar = false;
    private boolean mNeedHorizontalScrollbar = false;
    private int mState = 0;
    private int mDragState = 0;
    private final int[] mVerticalRange = new int[2];
    private final int[] mHorizontalRange = new int[2];

    public class AnimatorListener extends AnimatorListenerAdapter {
        private boolean mCanceled = false;

        public AnimatorListener() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.mCanceled) {
                this.mCanceled = false;
                return;
            }
            if (((Float) FastScroller.this.mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0f) {
                FastScroller fastScroller = FastScroller.this;
                fastScroller.mAnimationState = 0;
                fastScroller.setState(0);
            } else {
                FastScroller fastScroller2 = FastScroller.this;
                fastScroller2.mAnimationState = 2;
                fastScroller2.requestRedraw();
            }
        }
    }

    public class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        public AnimatorUpdater() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            FastScroller.this.mVerticalThumbDrawable.setAlpha(iFloatValue);
            FastScroller.this.mVerticalTrackDrawable.setAlpha(iFloatValue);
            FastScroller.this.requestRedraw();
        }
    }

    public FastScroller(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i7, int i8, int i9) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mShowHideAnimator = valueAnimatorOfFloat;
        this.mAnimationState = 0;
        this.mHideRunnable = new Runnable() { // from class: android.support.v7.widget.FastScroller.1
            @Override // java.lang.Runnable
            public void run() {
                FastScroller.this.hide(500);
            }
        };
        this.mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: android.support.v7.widget.FastScroller.2
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i10, int i11) {
                FastScroller.this.updateScrollPosition(recyclerView2.computeHorizontalScrollOffset(), recyclerView2.computeVerticalScrollOffset());
            }
        };
        this.mVerticalThumbDrawable = stateListDrawable;
        this.mVerticalTrackDrawable = drawable;
        this.mHorizontalThumbDrawable = stateListDrawable2;
        this.mHorizontalTrackDrawable = drawable2;
        this.mVerticalThumbWidth = Math.max(i7, stateListDrawable.getIntrinsicWidth());
        this.mVerticalTrackWidth = Math.max(i7, drawable.getIntrinsicWidth());
        this.mHorizontalThumbHeight = Math.max(i7, stateListDrawable2.getIntrinsicWidth());
        this.mHorizontalTrackHeight = Math.max(i7, drawable2.getIntrinsicWidth());
        this.mScrollbarMinimumRange = i8;
        this.mMargin = i9;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new AnimatorListener());
        valueAnimatorOfFloat.addUpdateListener(new AnimatorUpdater());
        attachToRecyclerView(recyclerView);
    }

    private void cancelHide() {
        this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this);
        this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
        cancelHide();
    }

    private void drawHorizontalScrollbar(Canvas canvas) {
        int i7 = this.mRecyclerViewHeight;
        int i8 = this.mHorizontalThumbHeight;
        int i9 = this.mHorizontalThumbCenterX;
        int i10 = this.mHorizontalThumbWidth;
        this.mHorizontalThumbDrawable.setBounds(0, 0, i10, i8);
        this.mHorizontalTrackDrawable.setBounds(0, 0, this.mRecyclerViewWidth, this.mHorizontalTrackHeight);
        canvas.translate(0.0f, i7 - i8);
        this.mHorizontalTrackDrawable.draw(canvas);
        canvas.translate(i9 - (i10 / 2), 0.0f);
        this.mHorizontalThumbDrawable.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    private void drawVerticalScrollbar(Canvas canvas) {
        int i7 = this.mRecyclerViewWidth;
        int i8 = this.mVerticalThumbWidth;
        int i9 = i7 - i8;
        int i10 = this.mVerticalThumbCenterY;
        int i11 = this.mVerticalThumbHeight;
        int i12 = i10 - (i11 / 2);
        this.mVerticalThumbDrawable.setBounds(0, 0, i8, i11);
        this.mVerticalTrackDrawable.setBounds(0, 0, this.mVerticalTrackWidth, this.mRecyclerViewHeight);
        if (!isLayoutRTL()) {
            canvas.translate(i9, 0.0f);
            this.mVerticalTrackDrawable.draw(canvas);
            canvas.translate(0.0f, i12);
            this.mVerticalThumbDrawable.draw(canvas);
            canvas.translate(-i9, -i12);
            return;
        }
        this.mVerticalTrackDrawable.draw(canvas);
        canvas.translate(this.mVerticalThumbWidth, i12);
        canvas.scale(-1.0f, 1.0f);
        this.mVerticalThumbDrawable.draw(canvas);
        canvas.scale(1.0f, 1.0f);
        canvas.translate(-this.mVerticalThumbWidth, -i12);
    }

    private int[] getHorizontalRange() {
        int[] iArr = this.mHorizontalRange;
        int i7 = this.mMargin;
        iArr[0] = i7;
        iArr[1] = this.mRecyclerViewWidth - i7;
        return iArr;
    }

    private int[] getVerticalRange() {
        int[] iArr = this.mVerticalRange;
        int i7 = this.mMargin;
        iArr[0] = i7;
        iArr[1] = this.mRecyclerViewHeight - i7;
        return iArr;
    }

    private void horizontalScrollTo(float f7) {
        int[] horizontalRange = getHorizontalRange();
        float fMax = Math.max(horizontalRange[0], Math.min(horizontalRange[1], f7));
        if (Math.abs(this.mHorizontalThumbCenterX - fMax) < 2.0f) {
            return;
        }
        int iScrollTo = scrollTo(this.mHorizontalDragX, fMax, horizontalRange, this.mRecyclerView.computeHorizontalScrollRange(), this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerViewWidth);
        if (iScrollTo != 0) {
            this.mRecyclerView.scrollBy(iScrollTo, 0);
        }
        this.mHorizontalDragX = fMax;
    }

    private boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this.mRecyclerView) == 1;
    }

    private void resetHideDelay(int i7) {
        cancelHide();
        this.mRecyclerView.postDelayed(this.mHideRunnable, i7);
    }

    private int scrollTo(float f7, float f8, int[] iArr, int i7, int i8, int i9) {
        int i10 = iArr[1] - iArr[0];
        if (i10 == 0) {
            return 0;
        }
        int i11 = i7 - i9;
        int i12 = (int) (((f8 - f7) / i10) * i11);
        int i13 = i8 + i12;
        if (i13 >= i11 || i13 < 0) {
            return 0;
        }
        return i12;
    }

    private void setupCallbacks() {
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this);
        this.mRecyclerView.addOnScrollListener(this.mOnScrollListener);
    }

    private void verticalScrollTo(float f7) {
        int[] verticalRange = getVerticalRange();
        float fMax = Math.max(verticalRange[0], Math.min(verticalRange[1], f7));
        if (Math.abs(this.mVerticalThumbCenterY - fMax) < 2.0f) {
            return;
        }
        int iScrollTo = scrollTo(this.mVerticalDragY, fMax, verticalRange, this.mRecyclerView.computeVerticalScrollRange(), this.mRecyclerView.computeVerticalScrollOffset(), this.mRecyclerViewHeight);
        if (iScrollTo != 0) {
            this.mRecyclerView.scrollBy(0, iScrollTo);
        }
        this.mVerticalDragY = fMax;
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            destroyCallbacks();
        }
        this.mRecyclerView = recyclerView;
        if (recyclerView != null) {
            setupCallbacks();
        }
    }

    @VisibleForTesting
    public Drawable getHorizontalThumbDrawable() {
        return this.mHorizontalThumbDrawable;
    }

    @VisibleForTesting
    public Drawable getHorizontalTrackDrawable() {
        return this.mHorizontalTrackDrawable;
    }

    @VisibleForTesting
    public Drawable getVerticalThumbDrawable() {
        return this.mVerticalThumbDrawable;
    }

    @VisibleForTesting
    public Drawable getVerticalTrackDrawable() {
        return this.mVerticalTrackDrawable;
    }

    public void hide() {
        hide(0);
    }

    public boolean isDragging() {
        return this.mState == 2;
    }

    @VisibleForTesting
    public boolean isHidden() {
        return this.mState == 0;
    }

    @VisibleForTesting
    public boolean isPointInsideHorizontalThumb(float f7, float f8) {
        if (f8 >= this.mRecyclerViewHeight - this.mHorizontalThumbHeight) {
            int i7 = this.mHorizontalThumbCenterX;
            int i8 = this.mHorizontalThumbWidth;
            if (f7 >= i7 - (i8 / 2) && f7 <= (i8 / 2) + i7) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    public boolean isPointInsideVerticalThumb(float f7, float f8) {
        if (!isLayoutRTL() ? f7 >= this.mRecyclerViewWidth - this.mVerticalThumbWidth : f7 <= this.mVerticalThumbWidth / 2) {
            int i7 = this.mVerticalThumbCenterY;
            int i8 = this.mVerticalThumbHeight;
            if (f8 >= i7 - (i8 / 2) && f8 <= (i8 / 2) + i7) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    public boolean isVisible() {
        return this.mState == 1;
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.mRecyclerViewWidth != this.mRecyclerView.getWidth() || this.mRecyclerViewHeight != this.mRecyclerView.getHeight()) {
            this.mRecyclerViewWidth = this.mRecyclerView.getWidth();
            this.mRecyclerViewHeight = this.mRecyclerView.getHeight();
            setState(0);
        } else if (this.mAnimationState != 0) {
            if (this.mNeedVerticalScrollbar) {
                drawVerticalScrollbar(canvas);
            }
            if (this.mNeedHorizontalScrollbar) {
                drawHorizontalScrollbar(canvas);
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        int i7 = this.mState;
        if (i7 == 1) {
            boolean zIsPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
            boolean zIsPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!zIsPointInsideVerticalThumb && !zIsPointInsideHorizontalThumb) {
                return false;
            }
            if (zIsPointInsideHorizontalThumb) {
                this.mDragState = 1;
                this.mHorizontalDragX = (int) motionEvent.getX();
            } else if (zIsPointInsideVerticalThumb) {
                this.mDragState = 2;
                this.mVerticalDragY = (int) motionEvent.getY();
            }
            setState(2);
        } else if (i7 != 2) {
            return false;
        }
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z6) {
    }

    @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        if (this.mState == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean zIsPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
            boolean zIsPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
            if (zIsPointInsideVerticalThumb || zIsPointInsideHorizontalThumb) {
                if (zIsPointInsideHorizontalThumb) {
                    this.mDragState = 1;
                    this.mHorizontalDragX = (int) motionEvent.getX();
                } else if (zIsPointInsideVerticalThumb) {
                    this.mDragState = 2;
                    this.mVerticalDragY = (int) motionEvent.getY();
                }
                setState(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.mState == 2) {
            this.mVerticalDragY = 0.0f;
            this.mHorizontalDragX = 0.0f;
            setState(1);
            this.mDragState = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.mState == 2) {
            show();
            if (this.mDragState == 1) {
                horizontalScrollTo(motionEvent.getX());
            }
            if (this.mDragState == 2) {
                verticalScrollTo(motionEvent.getY());
            }
        }
    }

    public void requestRedraw() {
        this.mRecyclerView.invalidate();
    }

    public void setState(int i7) {
        if (i7 == 2 && this.mState != 2) {
            this.mVerticalThumbDrawable.setState(PRESSED_STATE_SET);
            cancelHide();
        }
        if (i7 == 0) {
            requestRedraw();
        } else {
            show();
        }
        if (this.mState == 2 && i7 != 2) {
            this.mVerticalThumbDrawable.setState(EMPTY_STATE_SET);
            resetHideDelay(HIDE_DELAY_AFTER_DRAGGING_MS);
        } else if (i7 == 1) {
            resetHideDelay(HIDE_DELAY_AFTER_VISIBLE_MS);
        }
        this.mState = i7;
    }

    public void show() {
        int i7 = this.mAnimationState;
        if (i7 != 0) {
            if (i7 != 3) {
                return;
            } else {
                this.mShowHideAnimator.cancel();
            }
        }
        this.mAnimationState = 1;
        ValueAnimator valueAnimator = this.mShowHideAnimator;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.mShowHideAnimator.setDuration(500L);
        this.mShowHideAnimator.setStartDelay(0L);
        this.mShowHideAnimator.start();
    }

    public void updateScrollPosition(int i7, int i8) {
        int iComputeVerticalScrollRange = this.mRecyclerView.computeVerticalScrollRange();
        int i9 = this.mRecyclerViewHeight;
        this.mNeedVerticalScrollbar = iComputeVerticalScrollRange - i9 > 0 && i9 >= this.mScrollbarMinimumRange;
        int iComputeHorizontalScrollRange = this.mRecyclerView.computeHorizontalScrollRange();
        int i10 = this.mRecyclerViewWidth;
        boolean z6 = iComputeHorizontalScrollRange - i10 > 0 && i10 >= this.mScrollbarMinimumRange;
        this.mNeedHorizontalScrollbar = z6;
        boolean z7 = this.mNeedVerticalScrollbar;
        if (!z7 && !z6) {
            if (this.mState != 0) {
                setState(0);
                return;
            }
            return;
        }
        if (z7) {
            float f7 = i9;
            this.mVerticalThumbCenterY = (int) ((((f7 / 2.0f) + i8) * f7) / iComputeVerticalScrollRange);
            this.mVerticalThumbHeight = Math.min(i9, (i9 * i9) / iComputeVerticalScrollRange);
        }
        if (this.mNeedHorizontalScrollbar) {
            float f8 = i10;
            this.mHorizontalThumbCenterX = (int) ((((f8 / 2.0f) + i7) * f8) / iComputeHorizontalScrollRange);
            this.mHorizontalThumbWidth = Math.min(i10, (i10 * i10) / iComputeHorizontalScrollRange);
        }
        int i11 = this.mState;
        if (i11 == 0 || i11 == 1) {
            setState(1);
        }
    }

    @VisibleForTesting
    public void hide(int i7) {
        int i8 = this.mAnimationState;
        if (i8 == 1) {
            this.mShowHideAnimator.cancel();
        } else if (i8 != 2) {
            return;
        }
        this.mAnimationState = 3;
        ValueAnimator valueAnimator = this.mShowHideAnimator;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.mShowHideAnimator.setDuration(i7);
        this.mShowHideAnimator.start();
    }
}
