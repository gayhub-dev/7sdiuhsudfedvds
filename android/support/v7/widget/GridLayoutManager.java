package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.constraint.motion.C0079a;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    public int[] mCachedBorders;
    public final Rect mDecorInsets;
    public boolean mPendingSpanCountChange;
    public final SparseIntArray mPreLayoutSpanIndexCache;
    public final SparseIntArray mPreLayoutSpanSizeCache;
    public View[] mSet;
    public int mSpanCount;
    public SpanSizeLookup mSpanSizeLookup;

    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanIndex(int i7, int i8) {
            return i7 % i8;
        }

        @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i7) {
            return 1;
        }
    }

    public static abstract class SpanSizeLookup {
        public final SparseIntArray mSpanIndexCache = new SparseIntArray();
        private boolean mCacheSpanIndices = false;

        public int findReferenceIndexFromCache(int i7) {
            int size = this.mSpanIndexCache.size() - 1;
            int i8 = 0;
            while (i8 <= size) {
                int i9 = (i8 + size) >>> 1;
                if (this.mSpanIndexCache.keyAt(i9) < i7) {
                    i8 = i9 + 1;
                } else {
                    size = i9 - 1;
                }
            }
            int i10 = i8 - 1;
            if (i10 < 0 || i10 >= this.mSpanIndexCache.size()) {
                return -1;
            }
            return this.mSpanIndexCache.keyAt(i10);
        }

        public int getCachedSpanIndex(int i7, int i8) {
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(i7, i8);
            }
            int i9 = this.mSpanIndexCache.get(i7, -1);
            if (i9 != -1) {
                return i9;
            }
            int spanIndex = getSpanIndex(i7, i8);
            this.mSpanIndexCache.put(i7, spanIndex);
            return spanIndex;
        }

        public int getSpanGroupIndex(int i7, int i8) {
            int spanSize = getSpanSize(i7);
            int i9 = 0;
            int i10 = 0;
            for (int i11 = 0; i11 < i7; i11++) {
                int spanSize2 = getSpanSize(i11);
                i9 += spanSize2;
                if (i9 == i8) {
                    i10++;
                    i9 = 0;
                } else if (i9 > i8) {
                    i10++;
                    i9 = spanSize2;
                }
            }
            return i9 + spanSize > i8 ? i10 + 1 : i10;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x002a  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0039  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0031 -> B:19:0x0036). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0033 -> B:19:0x0036). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0035 -> B:19:0x0036). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int getSpanIndex(int r6, int r7) {
            /*
                r5 = this;
                int r0 = r5.getSpanSize(r6)
                r1 = 0
                if (r0 != r7) goto L8
                return r1
            L8:
                boolean r2 = r5.mCacheSpanIndices
                if (r2 == 0) goto L26
                android.util.SparseIntArray r2 = r5.mSpanIndexCache
                int r2 = r2.size()
                if (r2 <= 0) goto L26
                int r2 = r5.findReferenceIndexFromCache(r6)
                if (r2 < 0) goto L26
                android.util.SparseIntArray r3 = r5.mSpanIndexCache
                int r3 = r3.get(r2)
                int r4 = r5.getSpanSize(r2)
                int r4 = r4 + r3
                goto L36
            L26:
                r2 = 0
                r4 = 0
            L28:
                if (r2 >= r6) goto L39
                int r3 = r5.getSpanSize(r2)
                int r4 = r4 + r3
                if (r4 != r7) goto L33
                r4 = 0
                goto L36
            L33:
                if (r4 <= r7) goto L36
                r4 = r3
            L36:
                int r2 = r2 + 1
                goto L28
            L39:
                int r0 = r0 + r4
                if (r0 > r7) goto L3d
                return r4
            L3d:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.SpanSizeLookup.getSpanIndex(int, int):int");
        }

        public abstract int getSpanSize(int i7);

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        public void setSpanIndexCacheEnabled(boolean z6) {
            this.mCacheSpanIndices = z6;
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i7, int i8) {
        super(context, attributeSet, i7, i8);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        setSpanCount(RecyclerView.LayoutManager.getProperties(context, attributeSet, i7, i8).spanCount);
    }

    private void assignSpans(RecyclerView.Recycler recycler, RecyclerView.State state, int i7, int i8, boolean z6) {
        int i9;
        int i10;
        int i11 = 0;
        int i12 = -1;
        if (z6) {
            i12 = i7;
            i9 = 0;
            i10 = 1;
        } else {
            i9 = i7 - 1;
            i10 = -1;
        }
        while (i9 != i12) {
            View view = this.mSet[i9];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int spanSize = getSpanSize(recycler, state, getPosition(view));
            layoutParams.mSpanSize = spanSize;
            layoutParams.mSpanIndex = i11;
            i11 += spanSize;
            i9 += i10;
        }
    }

    private void cachePreLayoutSpanMapping() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i7).getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, layoutParams.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, layoutParams.getSpanIndex());
        }
    }

    private void calculateItemBorders(int i7) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, i7);
    }

    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i7) {
        boolean z6 = i7 == 1;
        int spanIndex = getSpanIndex(recycler, state, anchorInfo.mPosition);
        if (z6) {
            while (spanIndex > 0) {
                int i8 = anchorInfo.mPosition;
                if (i8 <= 0) {
                    return;
                }
                int i9 = i8 - 1;
                anchorInfo.mPosition = i9;
                spanIndex = getSpanIndex(recycler, state, i9);
            }
            return;
        }
        int itemCount = state.getItemCount() - 1;
        int i10 = anchorInfo.mPosition;
        while (i10 < itemCount) {
            int i11 = i10 + 1;
            int spanIndex2 = getSpanIndex(recycler, state, i11);
            if (spanIndex2 <= spanIndex) {
                break;
            }
            i10 = i11;
            spanIndex = spanIndex2;
        }
        anchorInfo.mPosition = i10;
    }

    private void ensureViewSet() {
        View[] viewArr = this.mSet;
        if (viewArr == null || viewArr.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }

    private int getSpanGroupIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i7) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanGroupIndex(i7, this.mSpanCount);
        }
        int iConvertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i7);
        if (iConvertPreLayoutPositionToPostLayout == -1) {
            return 0;
        }
        return this.mSpanSizeLookup.getSpanGroupIndex(iConvertPreLayoutPositionToPostLayout, this.mSpanCount);
    }

    private int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i7) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanIndex(i7, this.mSpanCount);
        }
        int i8 = this.mPreLayoutSpanIndexCache.get(i7, -1);
        if (i8 != -1) {
            return i8;
        }
        int iConvertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i7);
        if (iConvertPreLayoutPositionToPostLayout == -1) {
            return 0;
        }
        return this.mSpanSizeLookup.getCachedSpanIndex(iConvertPreLayoutPositionToPostLayout, this.mSpanCount);
    }

    private int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int i7) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanSize(i7);
        }
        int i8 = this.mPreLayoutSpanSizeCache.get(i7, -1);
        if (i8 != -1) {
            return i8;
        }
        int iConvertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i7);
        if (iConvertPreLayoutPositionToPostLayout == -1) {
            return 1;
        }
        return this.mSpanSizeLookup.getSpanSize(iConvertPreLayoutPositionToPostLayout);
    }

    private void guessMeasurement(float f7, int i7) {
        calculateItemBorders(Math.max(Math.round(f7 * this.mSpanCount), i7));
    }

    private void measureChild(View view, int i7, boolean z6) {
        int childMeasureSpec;
        int childMeasureSpec2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.mDecorInsets;
        int i8 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        int i9 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        int spaceForSpanRange = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
        if (this.mOrientation == 1) {
            childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, i7, i9, ((ViewGroup.MarginLayoutParams) layoutParams).width, false);
            childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), i8, ((ViewGroup.MarginLayoutParams) layoutParams).height, true);
        } else {
            int childMeasureSpec3 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, i7, i8, ((ViewGroup.MarginLayoutParams) layoutParams).height, false);
            int childMeasureSpec4 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), i9, ((ViewGroup.MarginLayoutParams) layoutParams).width, true);
            childMeasureSpec = childMeasureSpec3;
            childMeasureSpec2 = childMeasureSpec4;
        }
        measureChildWithDecorationsAndMargin(view, childMeasureSpec2, childMeasureSpec, z6);
    }

    private void measureChildWithDecorationsAndMargin(View view, int i7, int i8, boolean z6) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z6 ? shouldReMeasureChild(view, i7, i8, layoutParams) : shouldMeasureChild(view, i7, i8, layoutParams)) {
            view.measure(i7, i8);
        }
    }

    private void updateMeasurements() {
        int height;
        int paddingTop;
        if (getOrientation() == 1) {
            height = getWidth() - getPaddingRight();
            paddingTop = getPaddingLeft();
        } else {
            height = getHeight() - getPaddingBottom();
            paddingTop = getPaddingTop();
        }
        calculateItemBorders(height - paddingTop);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int spanSize = this.mSpanCount;
        for (int i7 = 0; i7 < this.mSpanCount && layoutState.hasMore(state) && spanSize > 0; i7++) {
            int i8 = layoutState.mCurrentPosition;
            layoutPrefetchRegistry.addPosition(i8, Math.max(0, layoutState.mScrollingOffset));
            spanSize -= this.mSpanSizeLookup.getSpanSize(i8);
            layoutState.mCurrentPosition += layoutState.mItemDirection;
        }
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, int i7, int i8, int i9) {
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i10 = i8 > i7 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i7 != i8) {
            View childAt = getChildAt(i7);
            int position = getPosition(childAt);
            if (position >= 0 && position < i9 && getSpanIndex(recycler, state, position) == 0) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.getDecoratedStart(childAt) < endAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) >= startAfterPadding) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i7 += i10;
        }
        return view != null ? view : view2;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    public int getSpaceForSpanRange(int i7, int i8) {
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            int[] iArr = this.mCachedBorders;
            return iArr[i8 + i7] - iArr[i7];
        }
        int[] iArr2 = this.mCachedBorders;
        int i9 = this.mSpanCount;
        return iArr2[i9 - i7] - iArr2[(i9 - i7) - i8];
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    /* JADX WARN: Removed duplicated region for block: B:88:0x01b2  */
    @Override // android.support.v7.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void layoutChunk(android.support.v7.widget.RecyclerView.Recycler r19, android.support.v7.widget.RecyclerView.State r20, android.support.v7.widget.LinearLayoutManager.LayoutState r21, android.support.v7.widget.LinearLayoutManager.LayoutChunkResult r22) {
        /*
            Method dump skipped, instructions count: 575
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.layoutChunk(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, android.support.v7.widget.LinearLayoutManager$LayoutState, android.support.v7.widget.LinearLayoutManager$LayoutChunkResult):void");
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i7) {
        super.onAnchorReady(recycler, state, anchorInfo, i7);
        updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            ensureAnchorIsInCorrectSpan(recycler, state, anchorInfo, i7);
        }
        ensureViewSet();
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x00d6, code lost:
    
        if (r13 == (r2 > r15)) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0107  */
    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View onFocusSearchFailed(android.view.View r24, int r25, android.support.v7.widget.RecyclerView.Recycler r26, android.support.v7.widget.RecyclerView.State r27) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.onFocusSearchFailed(android.view.View, int, android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):android.view.View");
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int spanGroupIndex = getSpanGroupIndex(recycler, state, layoutParams2.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
        } else {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanGroupIndex, 1, layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i7, int i8) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i7, int i8, int i9) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i7, int i8) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i7, int i8, Object obj) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) {
            cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        clearPreLayoutSpanMappingCache();
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i7, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(i7, recycler, state);
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i7, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(i7, recycler, state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void setMeasuredDimension(Rect rect, int i7, int i8) {
        int iChooseSize;
        int iChooseSize2;
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(rect, i7, i8);
        }
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            iChooseSize2 = RecyclerView.LayoutManager.chooseSize(i8, rect.height() + paddingBottom, getMinimumHeight());
            int[] iArr = this.mCachedBorders;
            iChooseSize = RecyclerView.LayoutManager.chooseSize(i7, iArr[iArr.length - 1] + paddingRight, getMinimumWidth());
        } else {
            iChooseSize = RecyclerView.LayoutManager.chooseSize(i7, rect.width() + paddingRight, getMinimumWidth());
            int[] iArr2 = this.mCachedBorders;
            iChooseSize2 = RecyclerView.LayoutManager.chooseSize(i8, iArr2[iArr2.length - 1] + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(iChooseSize, iChooseSize2);
    }

    public void setSpanCount(int i7) {
        if (i7 == this.mSpanCount) {
            return;
        }
        this.mPendingSpanCountChange = true;
        if (i7 < 1) {
            throw new IllegalArgumentException(C0079a.m93a("Span count should be at least 1. Provided ", i7));
        }
        this.mSpanCount = i7;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        requestLayout();
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public void setStackFromEnd(boolean z6) {
        if (z6) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }

    public static int[] calculateItemBorders(int[] iArr, int i7, int i8) {
        int i9;
        if (iArr == null || iArr.length != i7 + 1 || iArr[iArr.length - 1] != i8) {
            iArr = new int[i7 + 1];
        }
        int i10 = 0;
        iArr[0] = 0;
        int i11 = i8 / i7;
        int i12 = i8 % i7;
        int i13 = 0;
        for (int i14 = 1; i14 <= i7; i14++) {
            i10 += i12;
            if (i10 <= 0 || i7 - i10 >= i12) {
                i9 = i11;
            } else {
                i9 = i11 + 1;
                i10 -= i7;
            }
            i13 += i9;
            iArr[i14] = i13;
        }
        return iArr;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        public int mSpanIndex;
        public int mSpanSize;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public int getSpanIndex() {
            return this.mSpanIndex;
        }

        public int getSpanSize() {
            return this.mSpanSize;
        }

        public LayoutParams(int i7, int i8) {
            super(i7, i8);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
    }

    public GridLayoutManager(Context context, int i7) {
        super(context);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        setSpanCount(i7);
    }

    public GridLayoutManager(Context context, int i7, int i8, boolean z6) {
        super(context, i8, z6);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        setSpanCount(i7);
    }
}
