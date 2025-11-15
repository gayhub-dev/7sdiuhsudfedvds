package android.support.v4.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ViewDragHelper {
    private static final int BASE_SETTLE_DURATION = 256;
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    private static final int EDGE_SIZE = 20;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "ViewDragHelper";
    private static final Interpolator sInterpolator = new Interpolator() { // from class: android.support.v4.widget.ViewDragHelper.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            float f8 = f7 - 1.0f;
            return (f8 * f8 * f8 * f8 * f8) + 1.0f;
        }
    };
    private final Callback mCallback;
    private View mCapturedView;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;
    private int mActivePointerId = -1;
    private final Runnable mSetIdleRunnable = new Runnable() { // from class: android.support.v4.widget.ViewDragHelper.2
        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper.this.setDragState(0);
        }
    };

    public static abstract class Callback {
        public int clampViewPositionHorizontal(@NonNull View view, int i7, int i8) {
            return 0;
        }

        public int clampViewPositionVertical(@NonNull View view, int i7, int i8) {
            return 0;
        }

        public int getOrderedChildIndex(int i7) {
            return i7;
        }

        public int getViewHorizontalDragRange(@NonNull View view) {
            return 0;
        }

        public int getViewVerticalDragRange(@NonNull View view) {
            return 0;
        }

        public void onEdgeDragStarted(int i7, int i8) {
        }

        public boolean onEdgeLock(int i7) {
            return false;
        }

        public void onEdgeTouched(int i7, int i8) {
        }

        public void onViewCaptured(@NonNull View view, int i7) {
        }

        public void onViewDragStateChanged(int i7) {
        }

        public void onViewPositionChanged(@NonNull View view, int i7, int i8, @Px int i9, @Px int i10) {
        }

        public void onViewReleased(@NonNull View view, float f7, float f8) {
        }

        public abstract boolean tryCaptureView(@NonNull View view, int i7);
    }

    private ViewDragHelper(@NonNull Context context, @NonNull ViewGroup viewGroup, @NonNull Callback callback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.mParentView = viewGroup;
        this.mCallback = callback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mEdgeSize = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMaxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mScroller = new OverScroller(context, sInterpolator);
    }

    private boolean checkNewEdgeDrag(float f7, float f8, int i7, int i8) {
        float fAbs = Math.abs(f7);
        float fAbs2 = Math.abs(f8);
        if ((this.mInitialEdgesTouched[i7] & i8) != i8 || (this.mTrackingEdges & i8) == 0 || (this.mEdgeDragsLocked[i7] & i8) == i8 || (this.mEdgeDragsInProgress[i7] & i8) == i8) {
            return false;
        }
        int i9 = this.mTouchSlop;
        if (fAbs <= i9 && fAbs2 <= i9) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.mCallback.onEdgeLock(i8)) {
            return (this.mEdgeDragsInProgress[i7] & i8) == 0 && fAbs > ((float) this.mTouchSlop);
        }
        int[] iArr = this.mEdgeDragsLocked;
        iArr[i7] = iArr[i7] | i8;
        return false;
    }

    private boolean checkTouchSlop(View view, float f7, float f8) {
        if (view == null) {
            return false;
        }
        boolean z6 = this.mCallback.getViewHorizontalDragRange(view) > 0;
        boolean z7 = this.mCallback.getViewVerticalDragRange(view) > 0;
        if (!z6 || !z7) {
            return z6 ? Math.abs(f7) > ((float) this.mTouchSlop) : z7 && Math.abs(f8) > ((float) this.mTouchSlop);
        }
        float f9 = (f8 * f8) + (f7 * f7);
        int i7 = this.mTouchSlop;
        return f9 > ((float) (i7 * i7));
    }

    private int clampMag(int i7, int i8, int i9) {
        int iAbs = Math.abs(i7);
        if (iAbs < i8) {
            return 0;
        }
        return iAbs > i9 ? i7 > 0 ? i9 : -i9 : i7;
    }

    private void clearMotionHistory() {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.mInitialMotionY, 0.0f);
        Arrays.fill(this.mLastMotionX, 0.0f);
        Arrays.fill(this.mLastMotionY, 0.0f);
        Arrays.fill(this.mInitialEdgesTouched, 0);
        Arrays.fill(this.mEdgeDragsInProgress, 0);
        Arrays.fill(this.mEdgeDragsLocked, 0);
        this.mPointersDown = 0;
    }

    private int computeAxisDuration(int i7, int i8, int i9) {
        if (i7 == 0) {
            return 0;
        }
        int width = this.mParentView.getWidth();
        float f7 = width / 2;
        float fDistanceInfluenceForSnapDuration = (distanceInfluenceForSnapDuration(Math.min(1.0f, Math.abs(i7) / width)) * f7) + f7;
        int iAbs = Math.abs(i8);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fDistanceInfluenceForSnapDuration / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i7) / i9) + 1.0f) * 256.0f), 600);
    }

    private int computeSettleDuration(View view, int i7, int i8, int i9, int i10) {
        float f7;
        float f8;
        float f9;
        float f10;
        int iClampMag = clampMag(i9, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int iClampMag2 = clampMag(i10, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int iAbs = Math.abs(i7);
        int iAbs2 = Math.abs(i8);
        int iAbs3 = Math.abs(iClampMag);
        int iAbs4 = Math.abs(iClampMag2);
        int i11 = iAbs3 + iAbs4;
        int i12 = iAbs + iAbs2;
        if (iClampMag != 0) {
            f7 = iAbs3;
            f8 = i11;
        } else {
            f7 = iAbs;
            f8 = i12;
        }
        float f11 = f7 / f8;
        if (iClampMag2 != 0) {
            f9 = iAbs4;
            f10 = i11;
        } else {
            f9 = iAbs2;
            f10 = i12;
        }
        return (int) ((computeAxisDuration(i8, iClampMag2, this.mCallback.getViewVerticalDragRange(view)) * (f9 / f10)) + (computeAxisDuration(i7, iClampMag, this.mCallback.getViewHorizontalDragRange(view)) * f11));
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup, @NonNull Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    private void dispatchViewReleased(float f7, float f8) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f7, f8);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    private float distanceInfluenceForSnapDuration(float f7) {
        return (float) Math.sin((f7 - 0.5f) * 0.47123894f);
    }

    private void dragTo(int i7, int i8, int i9, int i10) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        if (i9 != 0) {
            i7 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, i7, i9);
            ViewCompat.offsetLeftAndRight(this.mCapturedView, i7 - left);
        }
        int i11 = i7;
        if (i10 != 0) {
            i8 = this.mCallback.clampViewPositionVertical(this.mCapturedView, i8, i10);
            ViewCompat.offsetTopAndBottom(this.mCapturedView, i8 - top);
        }
        int i12 = i8;
        if (i9 == 0 && i10 == 0) {
            return;
        }
        this.mCallback.onViewPositionChanged(this.mCapturedView, i11, i12, i11 - left, i12 - top);
    }

    private void ensureMotionHistorySizeForId(int i7) {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null || fArr.length <= i7) {
            int i8 = i7 + 1;
            float[] fArr2 = new float[i8];
            float[] fArr3 = new float[i8];
            float[] fArr4 = new float[i8];
            float[] fArr5 = new float[i8];
            int[] iArr = new int[i8];
            int[] iArr2 = new int[i8];
            int[] iArr3 = new int[i8];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.mInitialMotionY;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.mLastMotionX;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.mLastMotionY;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.mInitialEdgesTouched;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.mEdgeDragsInProgress;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.mEdgeDragsLocked;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.mInitialMotionX = fArr2;
            this.mInitialMotionY = fArr3;
            this.mLastMotionX = fArr4;
            this.mLastMotionY = fArr5;
            this.mInitialEdgesTouched = iArr;
            this.mEdgeDragsInProgress = iArr2;
            this.mEdgeDragsLocked = iArr3;
        }
    }

    private boolean forceSettleCapturedViewAt(int i7, int i8, int i9, int i10) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        int i11 = i7 - left;
        int i12 = i8 - top;
        if (i11 == 0 && i12 == 0) {
            this.mScroller.abortAnimation();
            setDragState(0);
            return false;
        }
        this.mScroller.startScroll(left, top, i11, i12, computeSettleDuration(this.mCapturedView, i11, i12, i9, i10));
        setDragState(2);
        return true;
    }

    private int getEdgesTouched(int i7, int i8) {
        int i9 = i7 < this.mParentView.getLeft() + this.mEdgeSize ? 1 : 0;
        if (i8 < this.mParentView.getTop() + this.mEdgeSize) {
            i9 |= 4;
        }
        if (i7 > this.mParentView.getRight() - this.mEdgeSize) {
            i9 |= 2;
        }
        return i8 > this.mParentView.getBottom() - this.mEdgeSize ? i9 | 8 : i9;
    }

    private boolean isValidPointerForActionMove(int i7) {
        return isPointerDown(i7);
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r3v3, types: [android.support.v4.widget.ViewDragHelper$Callback] */
    private void reportNewEdgeDrags(float f7, float f8, int i7) {
        boolean zCheckNewEdgeDrag = checkNewEdgeDrag(f7, f8, i7, 1);
        boolean z6 = zCheckNewEdgeDrag;
        if (checkNewEdgeDrag(f8, f7, i7, 4)) {
            z6 = (zCheckNewEdgeDrag ? 1 : 0) | 4;
        }
        boolean z7 = z6;
        if (checkNewEdgeDrag(f7, f8, i7, 2)) {
            z7 = (z6 ? 1 : 0) | 2;
        }
        ?? r02 = z7;
        if (checkNewEdgeDrag(f8, f7, i7, 8)) {
            r02 = (z7 ? 1 : 0) | 8;
        }
        if (r02 != 0) {
            int[] iArr = this.mEdgeDragsInProgress;
            iArr[i7] = iArr[i7] | r02;
            this.mCallback.onEdgeDragStarted(r02, i7);
        }
    }

    private void saveInitialMotion(float f7, float f8, int i7) {
        ensureMotionHistorySizeForId(i7);
        float[] fArr = this.mInitialMotionX;
        this.mLastMotionX[i7] = f7;
        fArr[i7] = f7;
        float[] fArr2 = this.mInitialMotionY;
        this.mLastMotionY[i7] = f8;
        fArr2[i7] = f8;
        this.mInitialEdgesTouched[i7] = getEdgesTouched((int) f7, (int) f8);
        this.mPointersDown |= 1 << i7;
    }

    private void saveLastMotion(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i7 = 0; i7 < pointerCount; i7++) {
            int pointerId = motionEvent.getPointerId(i7);
            if (isValidPointerForActionMove(pointerId)) {
                float x6 = motionEvent.getX(i7);
                float y6 = motionEvent.getY(i7);
                this.mLastMotionX[pointerId] = x6;
                this.mLastMotionY[pointerId] = y6;
            }
        }
    }

    public void abort() {
        cancel();
        if (this.mDragState == 2) {
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int currX2 = this.mScroller.getCurrX();
            int currY2 = this.mScroller.getCurrY();
            this.mCallback.onViewPositionChanged(this.mCapturedView, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        setDragState(0);
    }

    public boolean canScroll(@NonNull View view, boolean z6, int i7, int i8, int i9, int i10) {
        int i11;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i12 = i9 + scrollX;
                if (i12 >= childAt.getLeft() && i12 < childAt.getRight() && (i11 = i10 + scrollY) >= childAt.getTop() && i11 < childAt.getBottom() && canScroll(childAt, true, i7, i8, i12 - childAt.getLeft(), i11 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z6 && (view.canScrollHorizontally(-i7) || view.canScrollVertically(-i8));
    }

    public void cancel() {
        this.mActivePointerId = -1;
        clearMotionHistory();
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void captureChildView(@NonNull View view, int i7) {
        if (view.getParent() != this.mParentView) {
            StringBuilder sbM112a = C0413b.m112a("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (");
            sbM112a.append(this.mParentView);
            sbM112a.append(")");
            throw new IllegalArgumentException(sbM112a.toString());
        }
        this.mCapturedView = view;
        this.mActivePointerId = i7;
        this.mCallback.onViewCaptured(view, i7);
        setDragState(1);
    }

    public boolean continueSettling(boolean z6) {
        if (this.mDragState == 2) {
            boolean zComputeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            int left = currX - this.mCapturedView.getLeft();
            int top = currY - this.mCapturedView.getTop();
            if (left != 0) {
                ViewCompat.offsetLeftAndRight(this.mCapturedView, left);
            }
            if (top != 0) {
                ViewCompat.offsetTopAndBottom(this.mCapturedView, top);
            }
            if (left != 0 || top != 0) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, currX, currY, left, top);
            }
            if (zComputeScrollOffset && currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                if (z6) {
                    this.mParentView.post(this.mSetIdleRunnable);
                } else {
                    setDragState(0);
                }
            }
        }
        return this.mDragState == 2;
    }

    @Nullable
    public View findTopChildUnder(int i7, int i8) {
        for (int childCount = this.mParentView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(childCount));
            if (i7 >= childAt.getLeft() && i7 < childAt.getRight() && i8 >= childAt.getTop() && i8 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public void flingCapturedView(int i7, int i8, int i9, int i10) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
        this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId), i7, i9, i8, i10);
        setDragState(2);
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    @Nullable
    public View getCapturedView() {
        return this.mCapturedView;
    }

    @Px
    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    @Px
    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public boolean isCapturedViewUnder(int i7, int i8) {
        return isViewUnder(this.mCapturedView, i7, i8);
    }

    public boolean isEdgeTouched(int i7) {
        int length = this.mInitialEdgesTouched.length;
        for (int i8 = 0; i8 < length; i8++) {
            if (isEdgeTouched(i7, i8)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPointerDown(int i7) {
        return ((1 << i7) & this.mPointersDown) != 0;
    }

    public boolean isViewUnder(@Nullable View view, int i7, int i8) {
        return view != null && i7 >= view.getLeft() && i7 < view.getRight() && i8 >= view.getTop() && i8 < view.getBottom();
    }

    public void processTouchEvent(@NonNull MotionEvent motionEvent) {
        int i7;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int i8 = 0;
        if (actionMasked == 0) {
            float x6 = motionEvent.getX();
            float y6 = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewFindTopChildUnder = findTopChildUnder((int) x6, (int) y6);
            saveInitialMotion(x6, y6, pointerId);
            tryCaptureViewForDrag(viewFindTopChildUnder, pointerId);
            int i9 = this.mInitialEdgesTouched[pointerId];
            int i10 = this.mTrackingEdges;
            if ((i9 & i10) != 0) {
                this.mCallback.onEdgeTouched(i9 & i10, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.mDragState == 1) {
                releaseViewForPointerUp();
            }
            cancel();
            return;
        }
        if (actionMasked == 2) {
            if (this.mDragState == 1) {
                if (isValidPointerForActionMove(this.mActivePointerId)) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    float x7 = motionEvent.getX(iFindPointerIndex);
                    float y7 = motionEvent.getY(iFindPointerIndex);
                    float[] fArr = this.mLastMotionX;
                    int i11 = this.mActivePointerId;
                    int i12 = (int) (x7 - fArr[i11]);
                    int i13 = (int) (y7 - this.mLastMotionY[i11]);
                    dragTo(this.mCapturedView.getLeft() + i12, this.mCapturedView.getTop() + i13, i12, i13);
                    saveLastMotion(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i8 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i8);
                if (isValidPointerForActionMove(pointerId2)) {
                    float x8 = motionEvent.getX(i8);
                    float y8 = motionEvent.getY(i8);
                    float f7 = x8 - this.mInitialMotionX[pointerId2];
                    float f8 = y8 - this.mInitialMotionY[pointerId2];
                    reportNewEdgeDrags(f7, f8, pointerId2);
                    if (this.mDragState != 1) {
                        View viewFindTopChildUnder2 = findTopChildUnder((int) x8, (int) y8);
                        if (checkTouchSlop(viewFindTopChildUnder2, f7, f8) && tryCaptureViewForDrag(viewFindTopChildUnder2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i8++;
            }
            saveLastMotion(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.mDragState == 1) {
                dispatchViewReleased(0.0f, 0.0f);
            }
            cancel();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x9 = motionEvent.getX(actionIndex);
            float y9 = motionEvent.getY(actionIndex);
            saveInitialMotion(x9, y9, pointerId3);
            if (this.mDragState != 0) {
                if (isCapturedViewUnder((int) x9, (int) y9)) {
                    tryCaptureViewForDrag(this.mCapturedView, pointerId3);
                    return;
                }
                return;
            } else {
                tryCaptureViewForDrag(findTopChildUnder((int) x9, (int) y9), pointerId3);
                int i14 = this.mInitialEdgesTouched[pointerId3];
                int i15 = this.mTrackingEdges;
                if ((i14 & i15) != 0) {
                    this.mCallback.onEdgeTouched(i14 & i15, pointerId3);
                    return;
                }
                return;
            }
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.mDragState == 1 && pointerId4 == this.mActivePointerId) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i8 >= pointerCount2) {
                    i7 = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i8);
                if (pointerId5 != this.mActivePointerId) {
                    View viewFindTopChildUnder3 = findTopChildUnder((int) motionEvent.getX(i8), (int) motionEvent.getY(i8));
                    View view = this.mCapturedView;
                    if (viewFindTopChildUnder3 == view && tryCaptureViewForDrag(view, pointerId5)) {
                        i7 = this.mActivePointerId;
                        break;
                    }
                }
                i8++;
            }
            if (i7 == -1) {
                releaseViewForPointerUp();
            }
        }
        clearMotionHistory(pointerId4);
    }

    public void setDragState(int i7) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != i7) {
            this.mDragState = i7;
            this.mCallback.onViewDragStateChanged(i7);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    public void setEdgeTrackingEnabled(int i7) {
        this.mTrackingEdges = i7;
    }

    public void setMinVelocity(float f7) {
        this.mMinVelocity = f7;
    }

    public boolean settleCapturedViewAt(int i7, int i8) {
        if (this.mReleaseInProgress) {
            return forceSettleCapturedViewAt(i7, i8, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean shouldInterceptTouchEvent(@android.support.annotation.NonNull android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.ViewDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean smoothSlideViewTo(@NonNull View view, int i7, int i8) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        boolean zForceSettleCapturedViewAt = forceSettleCapturedViewAt(i7, i8, 0, 0);
        if (!zForceSettleCapturedViewAt && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
        }
        return zForceSettleCapturedViewAt;
    }

    public boolean tryCaptureViewForDrag(View view, int i7) {
        if (view == this.mCapturedView && this.mActivePointerId == i7) {
            return true;
        }
        if (view == null || !this.mCallback.tryCaptureView(view, i7)) {
            return false;
        }
        this.mActivePointerId = i7;
        captureChildView(view, i7);
        return true;
    }

    private float clampMag(float f7, float f8, float f9) {
        float fAbs = Math.abs(f7);
        if (fAbs < f8) {
            return 0.0f;
        }
        return fAbs > f9 ? f7 > 0.0f ? f9 : -f9 : f7;
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup, float f7, @NonNull Callback callback) {
        ViewDragHelper viewDragHelperCreate = create(viewGroup, callback);
        viewDragHelperCreate.mTouchSlop = (int) ((1.0f / f7) * viewDragHelperCreate.mTouchSlop);
        return viewDragHelperCreate;
    }

    public boolean isEdgeTouched(int i7, int i8) {
        return isPointerDown(i8) && (i7 & this.mInitialEdgesTouched[i8]) != 0;
    }

    public boolean checkTouchSlop(int i7) {
        int length = this.mInitialMotionX.length;
        for (int i8 = 0; i8 < length; i8++) {
            if (checkTouchSlop(i7, i8)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTouchSlop(int i7, int i8) {
        if (!isPointerDown(i8)) {
            return false;
        }
        boolean z6 = (i7 & 1) == 1;
        boolean z7 = (i7 & 2) == 2;
        float f7 = this.mLastMotionX[i8] - this.mInitialMotionX[i8];
        float f8 = this.mLastMotionY[i8] - this.mInitialMotionY[i8];
        if (!z6 || !z7) {
            return z6 ? Math.abs(f7) > ((float) this.mTouchSlop) : z7 && Math.abs(f8) > ((float) this.mTouchSlop);
        }
        float f9 = (f8 * f8) + (f7 * f7);
        int i9 = this.mTouchSlop;
        return f9 > ((float) (i9 * i9));
    }

    private void clearMotionHistory(int i7) {
        if (this.mInitialMotionX == null || !isPointerDown(i7)) {
            return;
        }
        this.mInitialMotionX[i7] = 0.0f;
        this.mInitialMotionY[i7] = 0.0f;
        this.mLastMotionX[i7] = 0.0f;
        this.mLastMotionY[i7] = 0.0f;
        this.mInitialEdgesTouched[i7] = 0;
        this.mEdgeDragsInProgress[i7] = 0;
        this.mEdgeDragsLocked[i7] = 0;
        this.mPointersDown = (~(1 << i7)) & this.mPointersDown;
    }
}
