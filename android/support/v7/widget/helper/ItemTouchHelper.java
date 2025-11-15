package android.support.v7.widget.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.C0311R;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
    public static final int ACTION_MODE_DRAG_MASK = 16711680;
    private static final int ACTION_MODE_IDLE_MASK = 255;
    public static final int ACTION_MODE_SWIPE_MASK = 65280;
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    private static final int ACTIVE_POINTER_ID_NONE = -1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    private static final boolean DEBUG = false;
    public static final int DIRECTION_FLAG_COUNT = 8;
    public static final int DOWN = 2;
    public static final int END = 32;
    public static final int LEFT = 4;
    private static final int PIXELS_PER_SECOND = 1000;
    public static final int RIGHT = 8;
    public static final int START = 16;
    private static final String TAG = "ItemTouchHelper";

    /* renamed from: UP */
    public static final int f172UP = 1;

    @NonNull
    public Callback mCallback;
    private List<Integer> mDistances;
    private long mDragScrollStartTimeInMs;
    public float mDx;
    public float mDy;
    public GestureDetectorCompat mGestureDetector;
    public float mInitialTouchX;
    public float mInitialTouchY;
    private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
    private float mMaxSwipeVelocity;
    public RecyclerView mRecyclerView;
    public int mSelectedFlags;
    private float mSelectedStartX;
    private float mSelectedStartY;
    private int mSlop;
    private List<RecyclerView.ViewHolder> mSwapTargets;
    private float mSwipeEscapeVelocity;
    private Rect mTmpRect;
    public VelocityTracker mVelocityTracker;
    public final List<View> mPendingCleanup = new ArrayList();
    private final float[] mTmpPosition = new float[2];
    public RecyclerView.ViewHolder mSelected = null;
    public int mActivePointerId = -1;
    private int mActionState = 0;
    public List<RecoverAnimation> mRecoverAnimations = new ArrayList();
    public final Runnable mScrollRunnable = new Runnable() { // from class: android.support.v7.widget.helper.ItemTouchHelper.1
        @Override // java.lang.Runnable
        public void run() {
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if (itemTouchHelper.mSelected == null || !itemTouchHelper.scrollIfNecessary()) {
                return;
            }
            ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
            RecyclerView.ViewHolder viewHolder = itemTouchHelper2.mSelected;
            if (viewHolder != null) {
                itemTouchHelper2.moveIfNecessary(viewHolder);
            }
            ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
            itemTouchHelper3.mRecyclerView.removeCallbacks(itemTouchHelper3.mScrollRunnable);
            ViewCompat.postOnAnimation(ItemTouchHelper.this.mRecyclerView, this);
        }
    };
    private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback = null;
    public View mOverdrawChild = null;
    public int mOverdrawChildPosition = -1;
    private final RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener() { // from class: android.support.v7.widget.helper.ItemTouchHelper.2
        @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            int iFindPointerIndex;
            RecoverAnimation recoverAnimationFindAnimation;
            ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                ItemTouchHelper.this.mActivePointerId = motionEvent.getPointerId(0);
                ItemTouchHelper.this.mInitialTouchX = motionEvent.getX();
                ItemTouchHelper.this.mInitialTouchY = motionEvent.getY();
                ItemTouchHelper.this.obtainVelocityTracker();
                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                if (itemTouchHelper.mSelected == null && (recoverAnimationFindAnimation = itemTouchHelper.findAnimation(motionEvent)) != null) {
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.mInitialTouchX -= recoverAnimationFindAnimation.f173mX;
                    itemTouchHelper2.mInitialTouchY -= recoverAnimationFindAnimation.f174mY;
                    itemTouchHelper2.endRecoverAnimation(recoverAnimationFindAnimation.mViewHolder, true);
                    if (ItemTouchHelper.this.mPendingCleanup.remove(recoverAnimationFindAnimation.mViewHolder.itemView)) {
                        ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                        itemTouchHelper3.mCallback.clearView(itemTouchHelper3.mRecyclerView, recoverAnimationFindAnimation.mViewHolder);
                    }
                    ItemTouchHelper.this.select(recoverAnimationFindAnimation.mViewHolder, recoverAnimationFindAnimation.mActionState);
                    ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                    itemTouchHelper4.updateDxDy(motionEvent, itemTouchHelper4.mSelectedFlags, 0);
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                ItemTouchHelper itemTouchHelper5 = ItemTouchHelper.this;
                itemTouchHelper5.mActivePointerId = -1;
                itemTouchHelper5.select(null, 0);
            } else {
                int i7 = ItemTouchHelper.this.mActivePointerId;
                if (i7 != -1 && (iFindPointerIndex = motionEvent.findPointerIndex(i7)) >= 0) {
                    ItemTouchHelper.this.checkSelectForSwipe(actionMasked, motionEvent, iFindPointerIndex);
                }
            }
            VelocityTracker velocityTracker = ItemTouchHelper.this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            return ItemTouchHelper.this.mSelected != null;
        }

        @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z6) {
            if (z6) {
                ItemTouchHelper.this.select(null, 0);
            }
        }

        @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
            VelocityTracker velocityTracker = ItemTouchHelper.this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (ItemTouchHelper.this.mActivePointerId == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int iFindPointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
            if (iFindPointerIndex >= 0) {
                ItemTouchHelper.this.checkSelectForSwipe(actionMasked, motionEvent, iFindPointerIndex);
            }
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            RecyclerView.ViewHolder viewHolder = itemTouchHelper.mSelected;
            if (viewHolder == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (iFindPointerIndex >= 0) {
                        itemTouchHelper.updateDxDy(motionEvent, itemTouchHelper.mSelectedFlags, iFindPointerIndex);
                        ItemTouchHelper.this.moveIfNecessary(viewHolder);
                        ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                        itemTouchHelper2.mRecyclerView.removeCallbacks(itemTouchHelper2.mScrollRunnable);
                        ItemTouchHelper.this.mScrollRunnable.run();
                        ItemTouchHelper.this.mRecyclerView.invalidate();
                        return;
                    }
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    int pointerId = motionEvent.getPointerId(actionIndex);
                    ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                    if (pointerId == itemTouchHelper3.mActivePointerId) {
                        itemTouchHelper3.mActivePointerId = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                        itemTouchHelper4.updateDxDy(motionEvent, itemTouchHelper4.mSelectedFlags, actionIndex);
                        return;
                    }
                    return;
                }
                VelocityTracker velocityTracker2 = itemTouchHelper.mVelocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
            ItemTouchHelper.this.select(null, 0);
            ItemTouchHelper.this.mActivePointerId = -1;
        }
    };

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$5 */
    public class C04075 implements RecyclerView.ChildDrawingOrderCallback {
        public C04075() {
        }

        @Override // android.support.v7.widget.RecyclerView.ChildDrawingOrderCallback
        public int onGetChildDrawingOrder(int i7, int i8) {
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            View view = itemTouchHelper.mOverdrawChild;
            if (view == null) {
                return i8;
            }
            int iIndexOfChild = itemTouchHelper.mOverdrawChildPosition;
            if (iIndexOfChild == -1) {
                iIndexOfChild = itemTouchHelper.mRecyclerView.indexOfChild(view);
                ItemTouchHelper.this.mOverdrawChildPosition = iIndexOfChild;
            }
            return i8 == i7 + (-1) ? iIndexOfChild : i8 < iIndexOfChild ? i8 : i8 + 1;
        }
    }

    public static abstract class Callback {
        private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000;
        public static final int RELATIVE_DIR_FLAGS = 3158064;
        private static final Interpolator sDragScrollInterpolator = new Interpolator() { // from class: android.support.v7.widget.helper.ItemTouchHelper.Callback.1
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f7) {
                return f7 * f7 * f7 * f7 * f7;
            }
        };
        private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator() { // from class: android.support.v7.widget.helper.ItemTouchHelper.Callback.2
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f7) {
                float f8 = f7 - 1.0f;
                return (f8 * f8 * f8 * f8 * f8) + 1.0f;
            }
        };
        private int mCachedMaxScrollSpeed = -1;

        public static int convertToRelativeDirection(int i7, int i8) {
            int i9;
            int i10 = i7 & ABS_HORIZONTAL_DIR_FLAGS;
            if (i10 == 0) {
                return i7;
            }
            int i11 = i7 & (~i10);
            if (i8 == 0) {
                i9 = i10 << 2;
            } else {
                int i12 = i10 << 1;
                i11 |= (-789517) & i12;
                i9 = (i12 & ABS_HORIZONTAL_DIR_FLAGS) << 2;
            }
            return i11 | i9;
        }

        @NonNull
        public static ItemTouchUIUtil getDefaultUIUtil() {
            return ItemTouchUIUtilImpl.INSTANCE;
        }

        private int getMaxDragScroll(RecyclerView recyclerView) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(C0311R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }

        public static int makeFlag(int i7, int i8) {
            return i8 << (i7 * 8);
        }

        public static int makeMovementFlags(int i7, int i8) {
            return makeFlag(2, i7) | makeFlag(1, i8) | makeFlag(0, i8 | i7);
        }

        public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
            return true;
        }

        public RecyclerView.ViewHolder chooseDropTarget(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<RecyclerView.ViewHolder> list, int i7, int i8) {
            int bottom;
            int iAbs;
            int top;
            int iAbs2;
            int left;
            int iAbs3;
            int right;
            int iAbs4;
            int width = viewHolder.itemView.getWidth() + i7;
            int height = viewHolder.itemView.getHeight() + i8;
            int left2 = i7 - viewHolder.itemView.getLeft();
            int top2 = i8 - viewHolder.itemView.getTop();
            int size = list.size();
            RecyclerView.ViewHolder viewHolder2 = null;
            int i9 = -1;
            for (int i10 = 0; i10 < size; i10++) {
                RecyclerView.ViewHolder viewHolder3 = list.get(i10);
                if (left2 > 0 && (right = viewHolder3.itemView.getRight() - width) < 0 && viewHolder3.itemView.getRight() > viewHolder.itemView.getRight() && (iAbs4 = Math.abs(right)) > i9) {
                    viewHolder2 = viewHolder3;
                    i9 = iAbs4;
                }
                if (left2 < 0 && (left = viewHolder3.itemView.getLeft() - i7) > 0 && viewHolder3.itemView.getLeft() < viewHolder.itemView.getLeft() && (iAbs3 = Math.abs(left)) > i9) {
                    viewHolder2 = viewHolder3;
                    i9 = iAbs3;
                }
                if (top2 < 0 && (top = viewHolder3.itemView.getTop() - i8) > 0 && viewHolder3.itemView.getTop() < viewHolder.itemView.getTop() && (iAbs2 = Math.abs(top)) > i9) {
                    viewHolder2 = viewHolder3;
                    i9 = iAbs2;
                }
                if (top2 > 0 && (bottom = viewHolder3.itemView.getBottom() - height) < 0 && viewHolder3.itemView.getBottom() > viewHolder.itemView.getBottom() && (iAbs = Math.abs(bottom)) > i9) {
                    viewHolder2 = viewHolder3;
                    i9 = iAbs;
                }
            }
            return viewHolder2;
        }

        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            ItemTouchUIUtilImpl.INSTANCE.clearView(viewHolder.itemView);
        }

        public int convertToAbsoluteDirection(int i7, int i8) {
            int i9;
            int i10 = i7 & RELATIVE_DIR_FLAGS;
            if (i10 == 0) {
                return i7;
            }
            int i11 = i7 & (~i10);
            if (i8 == 0) {
                i9 = i10 >> 2;
            } else {
                int i12 = i10 >> 1;
                i11 |= (-3158065) & i12;
                i9 = (i12 & RELATIVE_DIR_FLAGS) >> 2;
            }
            return i11 | i9;
        }

        public final int getAbsoluteMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection(recyclerView));
        }

        public long getAnimationDuration(@NonNull RecyclerView recyclerView, int i7, float f7, float f8) {
            RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            return itemAnimator == null ? i7 == 8 ? 200L : 250L : i7 == 8 ? itemAnimator.getMoveDuration() : itemAnimator.getRemoveDuration();
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getMoveThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public abstract int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder);

        public float getSwipeEscapeVelocity(float f7) {
            return f7;
        }

        public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public float getSwipeVelocityThreshold(float f7) {
            return f7;
        }

        public boolean hasDragFlag(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (getAbsoluteMovementFlags(recyclerView, viewHolder) & ItemTouchHelper.ACTION_MODE_DRAG_MASK) != 0;
        }

        public boolean hasSwipeFlag(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (getAbsoluteMovementFlags(recyclerView, viewHolder) & 65280) != 0;
        }

        public int interpolateOutOfBoundsScroll(@NonNull RecyclerView recyclerView, int i7, int i8, int i9, long j7) {
            int interpolation = (int) (sDragScrollInterpolator.getInterpolation(j7 <= DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS ? j7 / 2000.0f : 1.0f) * ((int) (sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (Math.abs(i8) * 1.0f) / i7)) * ((int) Math.signum(i8)) * getMaxDragScroll(recyclerView))));
            return interpolation == 0 ? i8 > 0 ? 1 : -1 : interpolation;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float f7, float f8, int i7, boolean z6) {
            ItemTouchUIUtilImpl.INSTANCE.onDraw(canvas, recyclerView, viewHolder.itemView, f7, f8, i7, z6);
        }

        public void onChildDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f7, float f8, int i7, boolean z6) {
            ItemTouchUIUtilImpl.INSTANCE.onDrawOver(canvas, recyclerView, viewHolder.itemView, f7, f8, i7, z6);
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<RecoverAnimation> list, int i7, float f7, float f8) {
            int size = list.size();
            for (int i8 = 0; i8 < size; i8++) {
                RecoverAnimation recoverAnimation = list.get(i8);
                recoverAnimation.update();
                int iSave = canvas.save();
                onChildDraw(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.f173mX, recoverAnimation.f174mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(iSave);
            }
            if (viewHolder != null) {
                int iSave2 = canvas.save();
                onChildDraw(canvas, recyclerView, viewHolder, f7, f8, i7, true);
                canvas.restoreToCount(iSave2);
            }
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<RecoverAnimation> list, int i7, float f7, float f8) {
            int size = list.size();
            boolean z6 = false;
            for (int i8 = 0; i8 < size; i8++) {
                RecoverAnimation recoverAnimation = list.get(i8);
                int iSave = canvas.save();
                onChildDrawOver(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.f173mX, recoverAnimation.f174mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(iSave);
            }
            if (viewHolder != null) {
                int iSave2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, viewHolder, f7, f8, i7, true);
                canvas.restoreToCount(iSave2);
            }
            for (int i9 = size - 1; i9 >= 0; i9--) {
                RecoverAnimation recoverAnimation2 = list.get(i9);
                boolean z7 = recoverAnimation2.mEnded;
                if (z7 && !recoverAnimation2.mIsPendingCleanup) {
                    list.remove(i9);
                } else if (!z7) {
                    z6 = true;
                }
            }
            if (z6) {
                recyclerView.invalidate();
            }
        }

        public abstract boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2);

        /* JADX WARN: Multi-variable type inference failed */
        public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int i7, @NonNull RecyclerView.ViewHolder viewHolder2, int i8, int i9, int i10) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof ViewDropHandler) {
                ((ViewDropHandler) layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, i9, i10);
                return;
            }
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(i8);
                }
                if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(i8);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(i8);
                }
                if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(i8);
                }
            }
        }

        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i7) {
            if (viewHolder != null) {
                ItemTouchUIUtilImpl.INSTANCE.onSelected(viewHolder.itemView);
            }
        }

        public abstract void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i7);
    }

    public class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mShouldReactToLongPress = true;

        public ItemTouchHelperGestureListener() {
        }

        public void doNotReactToLongPress() {
            this.mShouldReactToLongPress = false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View viewFindChildView;
            RecyclerView.ViewHolder childViewHolder;
            if (!this.mShouldReactToLongPress || (viewFindChildView = ItemTouchHelper.this.findChildView(motionEvent)) == null || (childViewHolder = ItemTouchHelper.this.mRecyclerView.getChildViewHolder(viewFindChildView)) == null) {
                return;
            }
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if (itemTouchHelper.mCallback.hasDragFlag(itemTouchHelper.mRecyclerView, childViewHolder)) {
                int pointerId = motionEvent.getPointerId(0);
                int i7 = ItemTouchHelper.this.mActivePointerId;
                if (pointerId == i7) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(i7);
                    float x6 = motionEvent.getX(iFindPointerIndex);
                    float y6 = motionEvent.getY(iFindPointerIndex);
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.mInitialTouchX = x6;
                    itemTouchHelper2.mInitialTouchY = y6;
                    itemTouchHelper2.mDy = 0.0f;
                    itemTouchHelper2.mDx = 0.0f;
                    if (itemTouchHelper2.mCallback.isLongPressDragEnabled()) {
                        ItemTouchHelper.this.select(childViewHolder, 2);
                    }
                }
            }
        }
    }

    public static class RecoverAnimation implements Animator.AnimatorListener {
        public final int mActionState;
        public final int mAnimationType;
        private float mFraction;
        public boolean mIsPendingCleanup;
        public final float mStartDx;
        public final float mStartDy;
        public final float mTargetX;
        public final float mTargetY;
        private final ValueAnimator mValueAnimator;
        public final RecyclerView.ViewHolder mViewHolder;

        /* renamed from: mX */
        public float f173mX;

        /* renamed from: mY */
        public float f174mY;
        public boolean mOverridden = false;
        public boolean mEnded = false;

        public RecoverAnimation(RecyclerView.ViewHolder viewHolder, int i7, int i8, float f7, float f8, float f9, float f10) {
            this.mActionState = i8;
            this.mAnimationType = i7;
            this.mViewHolder = viewHolder;
            this.mStartDx = f7;
            this.mStartDy = f8;
            this.mTargetX = f9;
            this.mTargetY = f10;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.mValueAnimator = valueAnimatorOfFloat;
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v7.widget.helper.ItemTouchHelper.RecoverAnimation.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    RecoverAnimation.this.setFraction(valueAnimator.getAnimatedFraction());
                }
            });
            valueAnimatorOfFloat.setTarget(viewHolder.itemView);
            valueAnimatorOfFloat.addListener(this);
            setFraction(0.0f);
        }

        public void cancel() {
            this.mValueAnimator.cancel();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            setFraction(1.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.mEnded) {
                this.mViewHolder.setIsRecyclable(true);
            }
            this.mEnded = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        public void setDuration(long j7) {
            this.mValueAnimator.setDuration(j7);
        }

        public void setFraction(float f7) {
            this.mFraction = f7;
        }

        public void start() {
            this.mViewHolder.setIsRecyclable(false);
            this.mValueAnimator.start();
        }

        public void update() {
            float f7 = this.mStartDx;
            float f8 = this.mTargetX;
            if (f7 == f8) {
                this.f173mX = this.mViewHolder.itemView.getTranslationX();
            } else {
                this.f173mX = ((f8 - f7) * this.mFraction) + f7;
            }
            float f9 = this.mStartDy;
            float f10 = this.mTargetY;
            if (f9 == f10) {
                this.f174mY = this.mViewHolder.itemView.getTranslationY();
            } else {
                this.f174mY = ((f10 - f9) * this.mFraction) + f9;
            }
        }
    }

    public static abstract class SimpleCallback extends Callback {
        private int mDefaultDragDirs;
        private int mDefaultSwipeDirs;

        public SimpleCallback(int i7, int i8) {
            this.mDefaultSwipeDirs = i8;
            this.mDefaultDragDirs = i7;
        }

        public int getDragDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return this.mDefaultDragDirs;
        }

        @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return Callback.makeMovementFlags(getDragDirs(recyclerView, viewHolder), getSwipeDirs(recyclerView, viewHolder));
        }

        public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return this.mDefaultSwipeDirs;
        }

        public void setDefaultDragDirs(int i7) {
            this.mDefaultDragDirs = i7;
        }

        public void setDefaultSwipeDirs(int i7) {
            this.mDefaultSwipeDirs = i7;
        }
    }

    public interface ViewDropHandler {
        void prepareForDrop(@NonNull View view, @NonNull View view2, int i7, int i8);
    }

    public ItemTouchHelper(@NonNull Callback callback) {
        this.mCallback = callback;
    }

    private void addChildDrawingOrderCallback() {
    }

    private int checkHorizontalSwipe(RecyclerView.ViewHolder viewHolder, int i7) {
        if ((i7 & 12) == 0) {
            return 0;
        }
        int i8 = this.mDx > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null && this.mActivePointerId > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
            float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            int i9 = xVelocity <= 0.0f ? 4 : 8;
            float fAbs = Math.abs(xVelocity);
            if ((i9 & i7) != 0 && i8 == i9 && fAbs >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && fAbs > Math.abs(yVelocity)) {
                return i9;
            }
        }
        float swipeThreshold = this.mCallback.getSwipeThreshold(viewHolder) * this.mRecyclerView.getWidth();
        if ((i7 & i8) == 0 || Math.abs(this.mDx) <= swipeThreshold) {
            return 0;
        }
        return i8;
    }

    private int checkVerticalSwipe(RecyclerView.ViewHolder viewHolder, int i7) {
        if ((i7 & 3) == 0) {
            return 0;
        }
        int i8 = this.mDy > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null && this.mActivePointerId > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
            float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            int i9 = yVelocity <= 0.0f ? 1 : 2;
            float fAbs = Math.abs(yVelocity);
            if ((i9 & i7) != 0 && i9 == i8 && fAbs >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && fAbs > Math.abs(xVelocity)) {
                return i9;
            }
        }
        float swipeThreshold = this.mCallback.getSwipeThreshold(viewHolder) * this.mRecyclerView.getHeight();
        if ((i7 & i8) == 0 || Math.abs(this.mDy) <= swipeThreshold) {
            return 0;
        }
        return i8;
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.removeOnChildAttachStateChangeListener(this);
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            this.mCallback.clearView(this.mRecyclerView, this.mRecoverAnimations.get(0).mViewHolder);
        }
        this.mRecoverAnimations.clear();
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        releaseVelocityTracker();
        stopGestureDetection();
    }

    private List<RecyclerView.ViewHolder> findSwapTargets(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        List<RecyclerView.ViewHolder> list = this.mSwapTargets;
        if (list == null) {
            this.mSwapTargets = new ArrayList();
            this.mDistances = new ArrayList();
        } else {
            list.clear();
            this.mDistances.clear();
        }
        int boundingBoxMargin = this.mCallback.getBoundingBoxMargin();
        int iRound = Math.round(this.mSelectedStartX + this.mDx) - boundingBoxMargin;
        int iRound2 = Math.round(this.mSelectedStartY + this.mDy) - boundingBoxMargin;
        int i7 = boundingBoxMargin * 2;
        int width = viewHolder2.itemView.getWidth() + iRound + i7;
        int height = viewHolder2.itemView.getHeight() + iRound2 + i7;
        int i8 = (iRound + width) / 2;
        int i9 = (iRound2 + height) / 2;
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        int i10 = 0;
        while (i10 < childCount) {
            View childAt = layoutManager.getChildAt(i10);
            if (childAt != viewHolder2.itemView && childAt.getBottom() >= iRound2 && childAt.getTop() <= height && childAt.getRight() >= iRound && childAt.getLeft() <= width) {
                RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(childAt);
                if (this.mCallback.canDropOver(this.mRecyclerView, this.mSelected, childViewHolder)) {
                    int iAbs = Math.abs(i8 - ((childAt.getRight() + childAt.getLeft()) / 2));
                    int iAbs2 = Math.abs(i9 - ((childAt.getBottom() + childAt.getTop()) / 2));
                    int i11 = (iAbs2 * iAbs2) + (iAbs * iAbs);
                    int size = this.mSwapTargets.size();
                    int i12 = 0;
                    for (int i13 = 0; i13 < size && i11 > this.mDistances.get(i13).intValue(); i13++) {
                        i12++;
                    }
                    this.mSwapTargets.add(i12, childViewHolder);
                    this.mDistances.add(i12, Integer.valueOf(i11));
                }
            }
            i10++;
            viewHolder2 = viewHolder;
        }
        return this.mSwapTargets;
    }

    private RecyclerView.ViewHolder findSwipedView(MotionEvent motionEvent) {
        View viewFindChildView;
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        int i7 = this.mActivePointerId;
        if (i7 == -1) {
            return null;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(i7);
        float x6 = motionEvent.getX(iFindPointerIndex) - this.mInitialTouchX;
        float y6 = motionEvent.getY(iFindPointerIndex) - this.mInitialTouchY;
        float fAbs = Math.abs(x6);
        float fAbs2 = Math.abs(y6);
        int i8 = this.mSlop;
        if (fAbs < i8 && fAbs2 < i8) {
            return null;
        }
        if (fAbs > fAbs2 && layoutManager.canScrollHorizontally()) {
            return null;
        }
        if ((fAbs2 <= fAbs || !layoutManager.canScrollVertically()) && (viewFindChildView = findChildView(motionEvent)) != null) {
            return this.mRecyclerView.getChildViewHolder(viewFindChildView);
        }
        return null;
    }

    private void getSelectedDxDy(float[] fArr) {
        if ((this.mSelectedFlags & 12) != 0) {
            fArr[0] = (this.mSelectedStartX + this.mDx) - this.mSelected.itemView.getLeft();
        } else {
            fArr[0] = this.mSelected.itemView.getTranslationX();
        }
        if ((this.mSelectedFlags & 3) != 0) {
            fArr[1] = (this.mSelectedStartY + this.mDy) - this.mSelected.itemView.getTop();
        } else {
            fArr[1] = this.mSelected.itemView.getTranslationY();
        }
    }

    private static boolean hitTest(View view, float f7, float f8, float f9, float f10) {
        return f7 >= f9 && f7 <= f9 + ((float) view.getWidth()) && f8 >= f10 && f8 <= f10 + ((float) view.getHeight());
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void setupCallbacks() {
        this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.addOnChildAttachStateChangeListener(this);
        startGestureDetection();
    }

    private void startGestureDetection() {
        this.mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener();
        this.mGestureDetector = new GestureDetectorCompat(this.mRecyclerView.getContext(), this.mItemTouchHelperGestureListener);
    }

    private void stopGestureDetection() {
        ItemTouchHelperGestureListener itemTouchHelperGestureListener = this.mItemTouchHelperGestureListener;
        if (itemTouchHelperGestureListener != null) {
            itemTouchHelperGestureListener.doNotReactToLongPress();
            this.mItemTouchHelperGestureListener = null;
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector = null;
        }
    }

    private int swipeIfNecessary(RecyclerView.ViewHolder viewHolder) {
        if (this.mActionState == 2) {
            return 0;
        }
        int movementFlags = this.mCallback.getMovementFlags(this.mRecyclerView, viewHolder);
        int iConvertToAbsoluteDirection = (this.mCallback.convertToAbsoluteDirection(movementFlags, ViewCompat.getLayoutDirection(this.mRecyclerView)) & 65280) >> 8;
        if (iConvertToAbsoluteDirection == 0) {
            return 0;
        }
        int i7 = (movementFlags & 65280) >> 8;
        if (Math.abs(this.mDx) > Math.abs(this.mDy)) {
            int iCheckHorizontalSwipe = checkHorizontalSwipe(viewHolder, iConvertToAbsoluteDirection);
            if (iCheckHorizontalSwipe > 0) {
                return (i7 & iCheckHorizontalSwipe) == 0 ? Callback.convertToRelativeDirection(iCheckHorizontalSwipe, ViewCompat.getLayoutDirection(this.mRecyclerView)) : iCheckHorizontalSwipe;
            }
            int iCheckVerticalSwipe = checkVerticalSwipe(viewHolder, iConvertToAbsoluteDirection);
            if (iCheckVerticalSwipe > 0) {
                return iCheckVerticalSwipe;
            }
        } else {
            int iCheckVerticalSwipe2 = checkVerticalSwipe(viewHolder, iConvertToAbsoluteDirection);
            if (iCheckVerticalSwipe2 > 0) {
                return iCheckVerticalSwipe2;
            }
            int iCheckHorizontalSwipe2 = checkHorizontalSwipe(viewHolder, iConvertToAbsoluteDirection);
            if (iCheckHorizontalSwipe2 > 0) {
                return (i7 & iCheckHorizontalSwipe2) == 0 ? Callback.convertToRelativeDirection(iCheckHorizontalSwipe2, ViewCompat.getLayoutDirection(this.mRecyclerView)) : iCheckHorizontalSwipe2;
            }
        }
        return 0;
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
            Resources resources = recyclerView.getResources();
            this.mSwipeEscapeVelocity = resources.getDimension(C0311R.dimen.item_touch_helper_swipe_escape_velocity);
            this.mMaxSwipeVelocity = resources.getDimension(C0311R.dimen.item_touch_helper_swipe_escape_max_velocity);
            setupCallbacks();
        }
    }

    public void checkSelectForSwipe(int i7, MotionEvent motionEvent, int i8) {
        RecyclerView.ViewHolder viewHolderFindSwipedView;
        int absoluteMovementFlags;
        if (this.mSelected != null || i7 != 2 || this.mActionState == 2 || !this.mCallback.isItemViewSwipeEnabled() || this.mRecyclerView.getScrollState() == 1 || (viewHolderFindSwipedView = findSwipedView(motionEvent)) == null || (absoluteMovementFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, viewHolderFindSwipedView) & 65280) >> 8) == 0) {
            return;
        }
        float x6 = motionEvent.getX(i8);
        float y6 = motionEvent.getY(i8);
        float f7 = x6 - this.mInitialTouchX;
        float f8 = y6 - this.mInitialTouchY;
        float fAbs = Math.abs(f7);
        float fAbs2 = Math.abs(f8);
        int i9 = this.mSlop;
        if (fAbs >= i9 || fAbs2 >= i9) {
            if (fAbs > fAbs2) {
                if (f7 < 0.0f && (absoluteMovementFlags & 4) == 0) {
                    return;
                }
                if (f7 > 0.0f && (absoluteMovementFlags & 8) == 0) {
                    return;
                }
            } else {
                if (f8 < 0.0f && (absoluteMovementFlags & 1) == 0) {
                    return;
                }
                if (f8 > 0.0f && (absoluteMovementFlags & 2) == 0) {
                    return;
                }
            }
            this.mDy = 0.0f;
            this.mDx = 0.0f;
            this.mActivePointerId = motionEvent.getPointerId(0);
            select(viewHolderFindSwipedView, 1);
        }
    }

    public void endRecoverAnimation(RecyclerView.ViewHolder viewHolder, boolean z6) {
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(size);
            if (recoverAnimation.mViewHolder == viewHolder) {
                recoverAnimation.mOverridden |= z6;
                if (!recoverAnimation.mEnded) {
                    recoverAnimation.cancel();
                }
                this.mRecoverAnimations.remove(size);
                return;
            }
        }
    }

    public RecoverAnimation findAnimation(MotionEvent motionEvent) {
        if (this.mRecoverAnimations.isEmpty()) {
            return null;
        }
        View viewFindChildView = findChildView(motionEvent);
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(size);
            if (recoverAnimation.mViewHolder.itemView == viewFindChildView) {
                return recoverAnimation;
            }
        }
        return null;
    }

    public View findChildView(MotionEvent motionEvent) {
        float x6 = motionEvent.getX();
        float y6 = motionEvent.getY();
        RecyclerView.ViewHolder viewHolder = this.mSelected;
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            if (hitTest(view, x6, y6, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy)) {
                return view;
            }
        }
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(size);
            View view2 = recoverAnimation.mViewHolder.itemView;
            if (hitTest(view2, x6, y6, recoverAnimation.f173mX, recoverAnimation.f174mY)) {
                return view2;
            }
        }
        return this.mRecyclerView.findChildViewUnder(x6, y6);
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.setEmpty();
    }

    public boolean hasRunningRecoverAnim() {
        int size = this.mRecoverAnimations.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (!this.mRecoverAnimations.get(i7).mEnded) {
                return true;
            }
        }
        return false;
    }

    public void moveIfNecessary(RecyclerView.ViewHolder viewHolder) {
        if (!this.mRecyclerView.isLayoutRequested() && this.mActionState == 2) {
            float moveThreshold = this.mCallback.getMoveThreshold(viewHolder);
            int i7 = (int) (this.mSelectedStartX + this.mDx);
            int i8 = (int) (this.mSelectedStartY + this.mDy);
            if (Math.abs(i8 - viewHolder.itemView.getTop()) >= viewHolder.itemView.getHeight() * moveThreshold || Math.abs(i7 - viewHolder.itemView.getLeft()) >= viewHolder.itemView.getWidth() * moveThreshold) {
                List<RecyclerView.ViewHolder> listFindSwapTargets = findSwapTargets(viewHolder);
                if (listFindSwapTargets.size() == 0) {
                    return;
                }
                RecyclerView.ViewHolder viewHolderChooseDropTarget = this.mCallback.chooseDropTarget(viewHolder, listFindSwapTargets, i7, i8);
                if (viewHolderChooseDropTarget == null) {
                    this.mSwapTargets.clear();
                    this.mDistances.clear();
                    return;
                }
                int adapterPosition = viewHolderChooseDropTarget.getAdapterPosition();
                int adapterPosition2 = viewHolder.getAdapterPosition();
                if (this.mCallback.onMove(this.mRecyclerView, viewHolder, viewHolderChooseDropTarget)) {
                    this.mCallback.onMoved(this.mRecyclerView, viewHolder, adapterPosition2, viewHolderChooseDropTarget, adapterPosition, i7, i8);
                }
            }
        }
    }

    public void obtainVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.mVelocityTracker = VelocityTracker.obtain();
    }

    @Override // android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewAttachedToWindow(@NonNull View view) {
    }

    @Override // android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        removeChildDrawingOrderCallbackIfNecessary(view);
        RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(view);
        if (childViewHolder == null) {
            return;
        }
        RecyclerView.ViewHolder viewHolder = this.mSelected;
        if (viewHolder != null && childViewHolder == viewHolder) {
            select(null, 0);
            return;
        }
        endRecoverAnimation(childViewHolder, false);
        if (this.mPendingCleanup.remove(childViewHolder.itemView)) {
            this.mCallback.clearView(this.mRecyclerView, childViewHolder);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f7;
        float f8;
        this.mOverdrawChildPosition = -1;
        if (this.mSelected != null) {
            getSelectedDxDy(this.mTmpPosition);
            float[] fArr = this.mTmpPosition;
            float f9 = fArr[0];
            f8 = fArr[1];
            f7 = f9;
        } else {
            f7 = 0.0f;
            f8 = 0.0f;
        }
        this.mCallback.onDraw(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f7, f8);
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f7;
        float f8;
        if (this.mSelected != null) {
            getSelectedDxDy(this.mTmpPosition);
            float[] fArr = this.mTmpPosition;
            float f9 = fArr[0];
            f8 = fArr[1];
            f7 = f9;
        } else {
            f7 = 0.0f;
            f8 = 0.0f;
        }
        this.mCallback.onDrawOver(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f7, f8);
    }

    public void postDispatchSwipe(final RecoverAnimation recoverAnimation, final int i7) {
        this.mRecyclerView.post(new Runnable() { // from class: android.support.v7.widget.helper.ItemTouchHelper.4
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView = ItemTouchHelper.this.mRecyclerView;
                if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
                    return;
                }
                RecoverAnimation recoverAnimation2 = recoverAnimation;
                if (recoverAnimation2.mOverridden || recoverAnimation2.mViewHolder.getAdapterPosition() == -1) {
                    return;
                }
                RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.this.mRecyclerView.getItemAnimator();
                if ((itemAnimator == null || !itemAnimator.isRunning(null)) && !ItemTouchHelper.this.hasRunningRecoverAnim()) {
                    ItemTouchHelper.this.mCallback.onSwiped(recoverAnimation.mViewHolder, i7);
                } else {
                    ItemTouchHelper.this.mRecyclerView.post(this);
                }
            }
        });
    }

    public void removeChildDrawingOrderCallbackIfNecessary(View view) {
        if (view == this.mOverdrawChild) {
            this.mOverdrawChild = null;
            if (this.mChildDrawingOrderCallback != null) {
                this.mRecyclerView.setChildDrawingOrderCallback(null);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean scrollIfNecessary() {
        /*
            Method dump skipped, instructions count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.helper.ItemTouchHelper.scrollIfNecessary():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void select(@android.support.annotation.Nullable android.support.v7.widget.RecyclerView.ViewHolder r24, int r25) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.helper.ItemTouchHelper.select(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
    }

    public void startDrag(@NonNull RecyclerView.ViewHolder viewHolder) {
        if (this.mCallback.hasDragFlag(this.mRecyclerView, viewHolder) && viewHolder.itemView.getParent() == this.mRecyclerView) {
            obtainVelocityTracker();
            this.mDy = 0.0f;
            this.mDx = 0.0f;
            select(viewHolder, 2);
        }
    }

    public void startSwipe(@NonNull RecyclerView.ViewHolder viewHolder) {
        if (this.mCallback.hasSwipeFlag(this.mRecyclerView, viewHolder) && viewHolder.itemView.getParent() == this.mRecyclerView) {
            obtainVelocityTracker();
            this.mDy = 0.0f;
            this.mDx = 0.0f;
            select(viewHolder, 1);
        }
    }

    public void updateDxDy(MotionEvent motionEvent, int i7, int i8) {
        float x6 = motionEvent.getX(i8);
        float y6 = motionEvent.getY(i8);
        float f7 = x6 - this.mInitialTouchX;
        this.mDx = f7;
        this.mDy = y6 - this.mInitialTouchY;
        if ((i7 & 4) == 0) {
            this.mDx = Math.max(0.0f, f7);
        }
        if ((i7 & 8) == 0) {
            this.mDx = Math.min(0.0f, this.mDx);
        }
        if ((i7 & 1) == 0) {
            this.mDy = Math.max(0.0f, this.mDy);
        }
        if ((i7 & 2) == 0) {
            this.mDy = Math.min(0.0f, this.mDy);
        }
    }
}
