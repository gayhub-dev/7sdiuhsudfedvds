package android.support.v7.widget;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/* loaded from: classes.dex */
public class PagerSnapHelper extends SnapHelper {
    private static final int MAX_SCROLL_ON_FLING_DURATION = 100;

    @Nullable
    private OrientationHelper mHorizontalHelper;

    @Nullable
    private OrientationHelper mVerticalHelper;

    private int distanceToCenter(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view, OrientationHelper orientationHelper) {
        int end;
        int decoratedMeasurement = (orientationHelper.getDecoratedMeasurement(view) / 2) + orientationHelper.getDecoratedStart(view);
        if (layoutManager.getClipToPadding()) {
            end = (orientationHelper.getTotalSpace() / 2) + orientationHelper.getStartAfterPadding();
        } else {
            end = orientationHelper.getEnd() / 2;
        }
        return decoratedMeasurement - end;
    }

    @Nullable
    private View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int totalSpace = layoutManager.getClipToPadding() ? (orientationHelper.getTotalSpace() / 2) + orientationHelper.getStartAfterPadding() : orientationHelper.getEnd() / 2;
        int i7 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = layoutManager.getChildAt(i8);
            int iAbs = Math.abs(((orientationHelper.getDecoratedMeasurement(childAt) / 2) + orientationHelper.getDecoratedStart(childAt)) - totalSpace);
            if (iAbs < i7) {
                view = childAt;
                i7 = iAbs;
            }
        }
        return view;
    }

    @Nullable
    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int i7 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = layoutManager.getChildAt(i8);
            int decoratedStart = orientationHelper.getDecoratedStart(childAt);
            if (decoratedStart < i7) {
                view = childAt;
                i7 = decoratedStart;
            }
        }
        return view;
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mHorizontalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }

    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mVerticalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }

    @Override // android.support.v7.widget.SnapHelper
    @Nullable
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = distanceToCenter(layoutManager, view, getHorizontalHelper(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = distanceToCenter(layoutManager, view, getVerticalHelper(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // android.support.v7.widget.SnapHelper
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new LinearSmoothScroller(this.mRecyclerView.getContext()) { // from class: android.support.v7.widget.PagerSnapHelper.1
                @Override // android.support.v7.widget.LinearSmoothScroller
                public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return 100.0f / displayMetrics.densityDpi;
                }

                @Override // android.support.v7.widget.LinearSmoothScroller
                public int calculateTimeForScrolling(int i7) {
                    return Math.min(100, super.calculateTimeForScrolling(i7));
                }

                @Override // android.support.v7.widget.LinearSmoothScroller, android.support.v7.widget.RecyclerView.SmoothScroller
                public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                    PagerSnapHelper pagerSnapHelper = PagerSnapHelper.this;
                    int[] iArrCalculateDistanceToFinalSnap = pagerSnapHelper.calculateDistanceToFinalSnap(pagerSnapHelper.mRecyclerView.getLayoutManager(), view);
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

    @Override // android.support.v7.widget.SnapHelper
    @Nullable
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findCenterView(layoutManager, getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return findCenterView(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i7, int i8) {
        int position;
        PointF pointFComputeScrollVectorForPosition;
        int itemCount = layoutManager.getItemCount();
        if (itemCount == 0) {
            return -1;
        }
        View viewFindStartView = null;
        if (layoutManager.canScrollVertically()) {
            viewFindStartView = findStartView(layoutManager, getVerticalHelper(layoutManager));
        } else if (layoutManager.canScrollHorizontally()) {
            viewFindStartView = findStartView(layoutManager, getHorizontalHelper(layoutManager));
        }
        if (viewFindStartView == null || (position = layoutManager.getPosition(viewFindStartView)) == -1) {
            return -1;
        }
        boolean z6 = false;
        boolean z7 = !layoutManager.canScrollHorizontally() ? i8 <= 0 : i7 <= 0;
        if ((layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) && (pointFComputeScrollVectorForPosition = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1)) != null && (pointFComputeScrollVectorForPosition.x < 0.0f || pointFComputeScrollVectorForPosition.y < 0.0f)) {
            z6 = true;
        }
        return z6 ? z7 ? position - 1 : position : z7 ? position + 1 : position;
    }
}
