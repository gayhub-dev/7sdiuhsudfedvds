package android.support.v7.widget;

import android.support.annotation.Nullable;
import android.support.v4.os.TraceCompat;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class GapWorker implements Runnable {
    public static final ThreadLocal<GapWorker> sGapWorker = new ThreadLocal<>();
    public static Comparator<Task> sTaskComparator = new Comparator<Task>() { // from class: android.support.v7.widget.GapWorker.1
        @Override // java.util.Comparator
        public int compare(Task task, Task task2) {
            RecyclerView recyclerView = task.view;
            if ((recyclerView == null) != (task2.view == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z6 = task.immediate;
            if (z6 != task2.immediate) {
                return z6 ? -1 : 1;
            }
            int i7 = task2.viewVelocity - task.viewVelocity;
            if (i7 != 0) {
                return i7;
            }
            int i8 = task.distanceToItem - task2.distanceToItem;
            if (i8 != 0) {
                return i8;
            }
            return 0;
        }
    };
    public long mFrameIntervalNs;
    public long mPostTimeNs;
    public ArrayList<RecyclerView> mRecyclerViews = new ArrayList<>();
    private ArrayList<Task> mTasks = new ArrayList<>();

    public static class LayoutPrefetchRegistryImpl implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {
        public int mCount;
        public int[] mPrefetchArray;
        public int mPrefetchDx;
        public int mPrefetchDy;

        @Override // android.support.v7.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry
        public void addPosition(int i7, int i8) {
            if (i7 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i8 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i9 = this.mCount * 2;
            int[] iArr = this.mPrefetchArray;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.mPrefetchArray = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i9 >= iArr.length) {
                int[] iArr3 = new int[i9 * 2];
                this.mPrefetchArray = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.mPrefetchArray;
            iArr4[i9] = i7;
            iArr4[i9 + 1] = i8;
            this.mCount++;
        }

        public void clearPrefetchPositions() {
            int[] iArr = this.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.mCount = 0;
        }

        public void collectPrefetchPositionsFromView(RecyclerView recyclerView, boolean z6) {
            this.mCount = 0;
            int[] iArr = this.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.mLayout;
            if (recyclerView.mAdapter == null || layoutManager == null || !layoutManager.isItemPrefetchEnabled()) {
                return;
            }
            if (z6) {
                if (!recyclerView.mAdapterHelper.hasPendingUpdates()) {
                    layoutManager.collectInitialPrefetchPositions(recyclerView.mAdapter.getItemCount(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                layoutManager.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, recyclerView.mState, this);
            }
            int i7 = this.mCount;
            if (i7 > layoutManager.mPrefetchMaxCountObserved) {
                layoutManager.mPrefetchMaxCountObserved = i7;
                layoutManager.mPrefetchMaxObservedInInitialPrefetch = z6;
                recyclerView.mRecycler.updateViewCacheSize();
            }
        }

        public boolean lastPrefetchIncludedPosition(int i7) {
            if (this.mPrefetchArray != null) {
                int i8 = this.mCount * 2;
                for (int i9 = 0; i9 < i8; i9 += 2) {
                    if (this.mPrefetchArray[i9] == i7) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void setPrefetchVector(int i7, int i8) {
            this.mPrefetchDx = i7;
            this.mPrefetchDy = i8;
        }
    }

    public static class Task {
        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        public void clear() {
            this.immediate = false;
            this.viewVelocity = 0;
            this.distanceToItem = 0;
            this.view = null;
            this.position = 0;
        }
    }

    private void buildTaskList() {
        Task task;
        int size = this.mRecyclerViews.size();
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            RecyclerView recyclerView = this.mRecyclerViews.get(i8);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerView, false);
                i7 += recyclerView.mPrefetchRegistry.mCount;
            }
        }
        this.mTasks.ensureCapacity(i7);
        int i9 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            RecyclerView recyclerView2 = this.mRecyclerViews.get(i10);
            if (recyclerView2.getWindowVisibility() == 0) {
                LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView2.mPrefetchRegistry;
                int iAbs = Math.abs(layoutPrefetchRegistryImpl.mPrefetchDy) + Math.abs(layoutPrefetchRegistryImpl.mPrefetchDx);
                for (int i11 = 0; i11 < layoutPrefetchRegistryImpl.mCount * 2; i11 += 2) {
                    if (i9 >= this.mTasks.size()) {
                        task = new Task();
                        this.mTasks.add(task);
                    } else {
                        task = this.mTasks.get(i9);
                    }
                    int[] iArr = layoutPrefetchRegistryImpl.mPrefetchArray;
                    int i12 = iArr[i11 + 1];
                    task.immediate = i12 <= iAbs;
                    task.viewVelocity = iAbs;
                    task.distanceToItem = i12;
                    task.view = recyclerView2;
                    task.position = iArr[i11];
                    i9++;
                }
            }
        }
        Collections.sort(this.mTasks, sTaskComparator);
    }

    private void flushTaskWithDeadline(Task task, long j7) {
        RecyclerView.ViewHolder viewHolderPrefetchPositionWithDeadline = prefetchPositionWithDeadline(task.view, task.position, task.immediate ? RecyclerView.FOREVER_NS : j7);
        if (viewHolderPrefetchPositionWithDeadline == null || viewHolderPrefetchPositionWithDeadline.mNestedRecyclerView == null || !viewHolderPrefetchPositionWithDeadline.isBound() || viewHolderPrefetchPositionWithDeadline.isInvalid()) {
            return;
        }
        prefetchInnerRecyclerViewWithDeadline(viewHolderPrefetchPositionWithDeadline.mNestedRecyclerView.get(), j7);
    }

    private void flushTasksWithDeadline(long j7) {
        for (int i7 = 0; i7 < this.mTasks.size(); i7++) {
            Task task = this.mTasks.get(i7);
            if (task.view == null) {
                return;
            }
            flushTaskWithDeadline(task, j7);
            task.clear();
        }
    }

    public static boolean isPrefetchPositionAttached(RecyclerView recyclerView, int i7) {
        int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
        for (int i8 = 0; i8 < unfilteredChildCount; i8++) {
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i8));
            if (childViewHolderInt.mPosition == i7 && !childViewHolderInt.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    private void prefetchInnerRecyclerViewWithDeadline(@Nullable RecyclerView recyclerView, long j7) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.getUnfilteredChildCount() != 0) {
            recyclerView.removeAndRecycleViews();
        }
        LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView.mPrefetchRegistry;
        layoutPrefetchRegistryImpl.collectPrefetchPositionsFromView(recyclerView, true);
        if (layoutPrefetchRegistryImpl.mCount != 0) {
            try {
                TraceCompat.beginSection(RecyclerView.TRACE_NESTED_PREFETCH_TAG);
                recyclerView.mState.prepareForNestedPrefetch(recyclerView.mAdapter);
                for (int i7 = 0; i7 < layoutPrefetchRegistryImpl.mCount * 2; i7 += 2) {
                    prefetchPositionWithDeadline(recyclerView, layoutPrefetchRegistryImpl.mPrefetchArray[i7], j7);
                }
            } finally {
                TraceCompat.endSection();
            }
        }
    }

    private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerView, int i7, long j7) {
        if (isPrefetchPositionAttached(recyclerView, i7)) {
            return null;
        }
        RecyclerView.Recycler recycler = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            RecyclerView.ViewHolder viewHolderTryGetViewHolderForPositionByDeadline = recycler.tryGetViewHolderForPositionByDeadline(i7, false, j7);
            if (viewHolderTryGetViewHolderForPositionByDeadline != null) {
                if (!viewHolderTryGetViewHolderForPositionByDeadline.isBound() || viewHolderTryGetViewHolderForPositionByDeadline.isInvalid()) {
                    recycler.addViewHolderToRecycledViewPool(viewHolderTryGetViewHolderForPositionByDeadline, false);
                } else {
                    recycler.recycleView(viewHolderTryGetViewHolderForPositionByDeadline.itemView);
                }
            }
            return viewHolderTryGetViewHolderForPositionByDeadline;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    public void add(RecyclerView recyclerView) {
        this.mRecyclerViews.add(recyclerView);
    }

    public void postFromTraversal(RecyclerView recyclerView, int i7, int i8) {
        if (recyclerView.isAttachedToWindow() && this.mPostTimeNs == 0) {
            this.mPostTimeNs = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.mPrefetchRegistry.setPrefetchVector(i7, i8);
    }

    public void prefetch(long j7) {
        buildTaskList();
        flushTasksWithDeadline(j7);
    }

    public void remove(RecyclerView recyclerView) {
        this.mRecyclerViews.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            TraceCompat.beginSection(RecyclerView.TRACE_PREFETCH_TAG);
            if (!this.mRecyclerViews.isEmpty()) {
                int size = this.mRecyclerViews.size();
                long jMax = 0;
                for (int i7 = 0; i7 < size; i7++) {
                    RecyclerView recyclerView = this.mRecyclerViews.get(i7);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    prefetch(TimeUnit.MILLISECONDS.toNanos(jMax) + this.mFrameIntervalNs);
                }
            }
        } finally {
            this.mPostTimeNs = 0L;
            TraceCompat.endSection();
        }
    }
}
