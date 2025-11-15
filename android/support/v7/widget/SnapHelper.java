package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

/* loaded from: classes.dex */
public abstract class SnapHelper extends RecyclerView.OnFlingListener {
    public static final float MILLISECONDS_PER_INCH = 100.0f;
    private Scroller mGravityScroller;
    public RecyclerView mRecyclerView;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() { // from class: android.support.v7.widget.SnapHelper.1
        public boolean mScrolled = false;

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i7) {
            super.onScrollStateChanged(recyclerView, i7);
            if (i7 == 0 && this.mScrolled) {
                this.mScrolled = false;
                SnapHelper.this.snapToTargetExistingView();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i7, int i8) {
            if (i7 == 0 && i8 == 0) {
                return;
            }
            this.mScrolled = true;
        }
    };

    private void destroyCallbacks() {
        this.mRecyclerView.removeOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(null);
    }

    private void setupCallbacks() {
        if (this.mRecyclerView.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(this);
    }

    private boolean snapFromFling(@NonNull RecyclerView.LayoutManager layoutManager, int i7, int i8) {
        RecyclerView.SmoothScroller smoothScrollerCreateScroller;
        int iFindTargetSnapPosition;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (smoothScrollerCreateScroller = createScroller(layoutManager)) == null || (iFindTargetSnapPosition = findTargetSnapPosition(layoutManager, i7, i8)) == -1) {
            return false;
        }
        smoothScrollerCreateScroller.setTargetPosition(iFindTargetSnapPosition);
        layoutManager.startSmoothScroll(smoothScrollerCreateScroller);
        return true;
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
            this.mGravityScroller = new Scroller(this.mRecyclerView.getContext(), new DecelerateInterpolator());
            snapToTargetExistingView();
        }
    }

    @Nullable
    public abstract int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view);

    public int[] calculateScrollDistance(int i7, int i8) {
        this.mGravityScroller.fling(0, 0, i7, i8, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        return new int[]{this.mGravityScroller.getFinalX(), this.mGravityScroller.getFinalY()};
    }

    @Nullable
    public RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        return createSnapScroller(layoutManager);
    }

    @Nullable
    @Deprecated
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new LinearSmoothScroller(this.mRecyclerView.getContext()) { // from class: android.support.v7.widget.SnapHelper.2
                @Override // android.support.v7.widget.LinearSmoothScroller
                public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return 100.0f / displayMetrics.densityDpi;
                }

                @Override // android.support.v7.widget.LinearSmoothScroller, android.support.v7.widget.RecyclerView.SmoothScroller
                public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                    SnapHelper snapHelper = SnapHelper.this;
                    RecyclerView recyclerView = snapHelper.mRecyclerView;
                    if (recyclerView == null) {
                        return;
                    }
                    int[] iArrCalculateDistanceToFinalSnap = snapHelper.calculateDistanceToFinalSnap(recyclerView.getLayoutManager(), view);
                    int i7 = iArrCalculateDistanceToFinalSnap[0];
                    int i8 = iArrCalculateDistanceToFinalSnap[1];
                    int iCalculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i7), Math.abs(i8)));
                    if (iCalculateTimeForDeceleration > 0) {
                        action.update(i7, i8, iCalculateTimeForDeceleration, this.mDecelerateInterpolator);
                    }
                }
            };
        }
        return null;
    }

    @Nullable
    public abstract View findSnapView(RecyclerView.LayoutManager layoutManager);

    public abstract int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i7, int i8);

    @Override // android.support.v7.widget.RecyclerView.OnFlingListener
    public boolean onFling(int i7, int i8) {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null || this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.mRecyclerView.getMinFlingVelocity();
        return (Math.abs(i8) > minFlingVelocity || Math.abs(i7) > minFlingVelocity) && snapFromFling(layoutManager, i7, i8);
    }

    public void snapToTargetExistingView() {
        RecyclerView.LayoutManager layoutManager;
        View viewFindSnapView;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewFindSnapView = findSnapView(layoutManager)) == null) {
            return;
        }
        int[] iArrCalculateDistanceToFinalSnap = calculateDistanceToFinalSnap(layoutManager, viewFindSnapView);
        if (iArrCalculateDistanceToFinalSnap[0] == 0 && iArrCalculateDistanceToFinalSnap[1] == 0) {
            return;
        }
        this.mRecyclerView.smoothScrollBy(iArrCalculateDistanceToFinalSnap[0], iArrCalculateDistanceToFinalSnap[1]);
    }
}
