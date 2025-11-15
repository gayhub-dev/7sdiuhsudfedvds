package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import p009b.C0413b;

/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    public static final boolean DEBUG = false;

    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "StaggeredGridLManager";
    public static final int VERTICAL = 1;
    private int mFullSizeSpec;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;

    @NonNull
    private final LayoutState mLayoutState;
    private int mOrientation;
    private SavedState mPendingSavedState;
    private int[] mPrefetchDistances;

    @NonNull
    public OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;

    @NonNull
    public OrientationHelper mSecondaryOrientation;
    private int mSizePerSpan;
    public Span[] mSpans;
    private int mSpanCount = -1;
    public boolean mReverseLayout = false;
    public boolean mShouldReverseLayout = false;
    public int mPendingScrollPosition = -1;
    public int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    public LazySpanLookup mLazySpanLookup = new LazySpanLookup();
    private int mGapStrategy = 2;
    private final Rect mTmpRect = new Rect();
    private final AnchorInfo mAnchorInfo = new AnchorInfo();
    private boolean mLaidOutInvalidFullSpan = false;
    private boolean mSmoothScrollbarEnabled = true;
    private final Runnable mCheckForGapsRunnable = new Runnable() { // from class: android.support.v7.widget.StaggeredGridLayoutManager.1
        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.checkForGaps();
        }
    };

    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        public boolean mFullSpan;
        public Span mSpan;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int getSpanIndex() {
            Span span = this.mSpan;
            if (span == null) {
                return -1;
            }
            return span.mIndex;
        }

        public boolean isFullSpan() {
            return this.mFullSpan;
        }

        public void setFullSpan(boolean z6) {
            this.mFullSpan = z6;
        }

        public LayoutParams(int i7, int i8) {
            super(i7, i8);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v7.widget.StaggeredGridLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        };
        public boolean mAnchorLayoutFromEnd;
        public int mAnchorPosition;
        public List<LazySpanLookup.FullSpanItem> mFullSpanItems;
        public boolean mLastLayoutRTL;
        public boolean mReverseLayout;
        public int[] mSpanLookup;
        public int mSpanLookupSize;
        public int[] mSpanOffsets;
        public int mSpanOffsetsSize;
        public int mVisibleAnchorPosition;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }

        public void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mVisibleAnchorPosition);
            parcel.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                parcel.writeIntArray(this.mSpanOffsets);
            }
            parcel.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                parcel.writeIntArray(this.mSpanLookup);
            }
            parcel.writeInt(this.mReverseLayout ? 1 : 0);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
            parcel.writeInt(this.mLastLayoutRTL ? 1 : 0);
            parcel.writeList(this.mFullSpanItems);
        }

        public SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mVisibleAnchorPosition = parcel.readInt();
            int i7 = parcel.readInt();
            this.mSpanOffsetsSize = i7;
            if (i7 > 0) {
                int[] iArr = new int[i7];
                this.mSpanOffsets = iArr;
                parcel.readIntArray(iArr);
            }
            int i8 = parcel.readInt();
            this.mSpanLookupSize = i8;
            if (i8 > 0) {
                int[] iArr2 = new int[i8];
                this.mSpanLookup = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.mReverseLayout = parcel.readInt() == 1;
            this.mAnchorLayoutFromEnd = parcel.readInt() == 1;
            this.mLastLayoutRTL = parcel.readInt() == 1;
            this.mFullSpanItems = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.mSpanOffsetsSize = savedState.mSpanOffsetsSize;
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mVisibleAnchorPosition = savedState.mVisibleAnchorPosition;
            this.mSpanOffsets = savedState.mSpanOffsets;
            this.mSpanLookupSize = savedState.mSpanLookupSize;
            this.mSpanLookup = savedState.mSpanLookup;
            this.mReverseLayout = savedState.mReverseLayout;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = savedState.mLastLayoutRTL;
            this.mFullSpanItems = savedState.mFullSpanItems;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i7, int i8) {
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i7, i8);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    private void appendViewToAllSpans(View view) {
        for (int i7 = this.mSpanCount - 1; i7 >= 0; i7--) {
            this.mSpans[i7].appendToSpan(view);
        }
    }

    private void applyPendingSavedState(AnchorInfo anchorInfo) {
        SavedState savedState = this.mPendingSavedState;
        int i7 = savedState.mSpanOffsetsSize;
        if (i7 > 0) {
            if (i7 == this.mSpanCount) {
                for (int i8 = 0; i8 < this.mSpanCount; i8++) {
                    this.mSpans[i8].clear();
                    SavedState savedState2 = this.mPendingSavedState;
                    int endAfterPadding = savedState2.mSpanOffsets[i8];
                    if (endAfterPadding != Integer.MIN_VALUE) {
                        endAfterPadding += savedState2.mAnchorLayoutFromEnd ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding();
                    }
                    this.mSpans[i8].setLine(endAfterPadding);
                }
            } else {
                savedState.invalidateSpanInfo();
                SavedState savedState3 = this.mPendingSavedState;
                savedState3.mAnchorPosition = savedState3.mVisibleAnchorPosition;
            }
        }
        SavedState savedState4 = this.mPendingSavedState;
        this.mLastLayoutRTL = savedState4.mLastLayoutRTL;
        setReverseLayout(savedState4.mReverseLayout);
        resolveShouldLayoutReverse();
        SavedState savedState5 = this.mPendingSavedState;
        int i9 = savedState5.mAnchorPosition;
        if (i9 != -1) {
            this.mPendingScrollPosition = i9;
            anchorInfo.mLayoutFromEnd = savedState5.mAnchorLayoutFromEnd;
        } else {
            anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        if (savedState5.mSpanLookupSize > 1) {
            LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
            lazySpanLookup.mData = savedState5.mSpanLookup;
            lazySpanLookup.mFullSpanItems = savedState5.mFullSpanItems;
        }
    }

    private void attachViewToSpans(View view, LayoutParams layoutParams, LayoutState layoutState) {
        if (layoutState.mLayoutDirection == 1) {
            if (layoutParams.mFullSpan) {
                appendViewToAllSpans(view);
                return;
            } else {
                layoutParams.mSpan.appendToSpan(view);
                return;
            }
        }
        if (layoutParams.mFullSpan) {
            prependViewToAllSpans(view);
        } else {
            layoutParams.mSpan.prependToSpan(view);
        }
    }

    private int calculateScrollDirectionForPosition(int i7) {
        if (getChildCount() == 0) {
            return this.mShouldReverseLayout ? 1 : -1;
        }
        return (i7 < getFirstChildPosition()) != this.mShouldReverseLayout ? -1 : 1;
    }

    private boolean checkSpanForGap(Span span) {
        if (this.mShouldReverseLayout) {
            if (span.getEndLine() < this.mPrimaryOrientation.getEndAfterPadding()) {
                ArrayList<View> arrayList = span.mViews;
                return !span.getLayoutParams(arrayList.get(arrayList.size() - 1)).mFullSpan;
            }
        } else if (span.getStartLine() > this.mPrimaryOrientation.getStartAfterPadding()) {
            return !span.getLayoutParams(span.mViews.get(0)).mFullSpan;
        }
        return false;
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollExtent(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollOffset(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollRange(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int convertFocusDirectionToLayoutDirection(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 17 ? i7 != 33 ? i7 != 66 ? (i7 == 130 && this.mOrientation == 1) ? 1 : Integer.MIN_VALUE : this.mOrientation == 0 ? 1 : Integer.MIN_VALUE : this.mOrientation == 1 ? -1 : Integer.MIN_VALUE : this.mOrientation == 0 ? -1 : Integer.MIN_VALUE : (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1 : (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int i7) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i8 = 0; i8 < this.mSpanCount; i8++) {
            fullSpanItem.mGapPerSpan[i8] = i7 - this.mSpans[i8].getEndLine(i7);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int i7) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i8 = 0; i8 < this.mSpanCount; i8++) {
            fullSpanItem.mGapPerSpan[i8] = this.mSpans[i8].getStartLine(i7) - i7;
        }
        return fullSpanItem;
    }

    private void createOrientationHelpers() {
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    private int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state) {
        int i7;
        Span nextSpan;
        int decoratedMeasurement;
        int i8;
        int decoratedMeasurement2;
        int decoratedMeasurement3;
        ?? r9 = 0;
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        if (this.mLayoutState.mInfinite) {
            i7 = layoutState.mLayoutDirection == 1 ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : Integer.MIN_VALUE;
        } else {
            i7 = layoutState.mLayoutDirection == 1 ? layoutState.mEndLine + layoutState.mAvailable : layoutState.mStartLine - layoutState.mAvailable;
        }
        updateAllRemainingSpans(layoutState.mLayoutDirection, i7);
        int endAfterPadding = this.mShouldReverseLayout ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding();
        boolean z6 = false;
        while (layoutState.hasMore(state) && (this.mLayoutState.mInfinite || !this.mRemainingSpans.isEmpty())) {
            View next = layoutState.next(recycler);
            LayoutParams layoutParams = (LayoutParams) next.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            int span = this.mLazySpanLookup.getSpan(viewLayoutPosition);
            boolean z7 = span == -1;
            if (z7) {
                nextSpan = layoutParams.mFullSpan ? this.mSpans[r9] : getNextSpan(layoutState);
                this.mLazySpanLookup.setSpan(viewLayoutPosition, nextSpan);
            } else {
                nextSpan = this.mSpans[span];
            }
            Span span2 = nextSpan;
            layoutParams.mSpan = span2;
            if (layoutState.mLayoutDirection == 1) {
                addView(next);
            } else {
                addView(next, r9);
            }
            measureChildWithDecorationsAndMargin(next, layoutParams, r9);
            if (layoutState.mLayoutDirection == 1) {
                int maxEnd = layoutParams.mFullSpan ? getMaxEnd(endAfterPadding) : span2.getEndLine(endAfterPadding);
                int decoratedMeasurement4 = this.mPrimaryOrientation.getDecoratedMeasurement(next) + maxEnd;
                if (z7 && layoutParams.mFullSpan) {
                    LazySpanLookup.FullSpanItem fullSpanItemCreateFullSpanItemFromEnd = createFullSpanItemFromEnd(maxEnd);
                    fullSpanItemCreateFullSpanItemFromEnd.mGapDir = -1;
                    fullSpanItemCreateFullSpanItemFromEnd.mPosition = viewLayoutPosition;
                    this.mLazySpanLookup.addFullSpanItem(fullSpanItemCreateFullSpanItemFromEnd);
                }
                i8 = decoratedMeasurement4;
                decoratedMeasurement = maxEnd;
            } else {
                int minStart = layoutParams.mFullSpan ? getMinStart(endAfterPadding) : span2.getStartLine(endAfterPadding);
                decoratedMeasurement = minStart - this.mPrimaryOrientation.getDecoratedMeasurement(next);
                if (z7 && layoutParams.mFullSpan) {
                    LazySpanLookup.FullSpanItem fullSpanItemCreateFullSpanItemFromStart = createFullSpanItemFromStart(minStart);
                    fullSpanItemCreateFullSpanItemFromStart.mGapDir = 1;
                    fullSpanItemCreateFullSpanItemFromStart.mPosition = viewLayoutPosition;
                    this.mLazySpanLookup.addFullSpanItem(fullSpanItemCreateFullSpanItemFromStart);
                }
                i8 = minStart;
            }
            if (layoutParams.mFullSpan && layoutState.mItemDirection == -1) {
                if (z7) {
                    this.mLaidOutInvalidFullSpan = true;
                } else {
                    if (!(layoutState.mLayoutDirection == 1 ? areAllEndsEqual() : areAllStartsEqual())) {
                        LazySpanLookup.FullSpanItem fullSpanItem = this.mLazySpanLookup.getFullSpanItem(viewLayoutPosition);
                        if (fullSpanItem != null) {
                            fullSpanItem.mHasUnwantedGapAfter = true;
                        }
                        this.mLaidOutInvalidFullSpan = true;
                    }
                }
            }
            attachViewToSpans(next, layoutParams, layoutState);
            if (isLayoutRTL() && this.mOrientation == 1) {
                int endAfterPadding2 = layoutParams.mFullSpan ? this.mSecondaryOrientation.getEndAfterPadding() : this.mSecondaryOrientation.getEndAfterPadding() - (((this.mSpanCount - 1) - span2.mIndex) * this.mSizePerSpan);
                decoratedMeasurement3 = endAfterPadding2;
                decoratedMeasurement2 = endAfterPadding2 - this.mSecondaryOrientation.getDecoratedMeasurement(next);
            } else {
                int startAfterPadding = layoutParams.mFullSpan ? this.mSecondaryOrientation.getStartAfterPadding() : (span2.mIndex * this.mSizePerSpan) + this.mSecondaryOrientation.getStartAfterPadding();
                decoratedMeasurement2 = startAfterPadding;
                decoratedMeasurement3 = this.mSecondaryOrientation.getDecoratedMeasurement(next) + startAfterPadding;
            }
            if (this.mOrientation == 1) {
                layoutDecoratedWithMargins(next, decoratedMeasurement2, decoratedMeasurement, decoratedMeasurement3, i8);
            } else {
                layoutDecoratedWithMargins(next, decoratedMeasurement, decoratedMeasurement2, i8, decoratedMeasurement3);
            }
            if (layoutParams.mFullSpan) {
                updateAllRemainingSpans(this.mLayoutState.mLayoutDirection, i7);
            } else {
                updateRemainingSpans(span2, this.mLayoutState.mLayoutDirection, i7);
            }
            recycle(recycler, this.mLayoutState);
            if (this.mLayoutState.mStopInFocusable && next.hasFocusable()) {
                if (layoutParams.mFullSpan) {
                    this.mRemainingSpans.clear();
                } else {
                    this.mRemainingSpans.set(span2.mIndex, false);
                }
            }
            z6 = true;
            r9 = 0;
        }
        if (!z6) {
            recycle(recycler, this.mLayoutState);
        }
        int startAfterPadding2 = this.mLayoutState.mLayoutDirection == -1 ? this.mPrimaryOrientation.getStartAfterPadding() - getMinStart(this.mPrimaryOrientation.getStartAfterPadding()) : getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        if (startAfterPadding2 > 0) {
            return Math.min(layoutState.mAvailable, startAfterPadding2);
        }
        return 0;
    }

    private int findFirstReferenceChildPosition(int i7) {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            int position = getPosition(getChildAt(i8));
            if (position >= 0 && position < i7) {
                return position;
            }
        }
        return 0;
    }

    private int findLastReferenceChildPosition(int i7) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i7) {
                return position;
            }
        }
        return 0;
    }

    private void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z6) {
        int endAfterPadding;
        int maxEnd = getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE && (endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - maxEnd) > 0) {
            int i7 = endAfterPadding - (-scrollBy(-endAfterPadding, recycler, state));
            if (!z6 || i7 <= 0) {
                return;
            }
            this.mPrimaryOrientation.offsetChildren(i7);
        }
    }

    private void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z6) {
        int startAfterPadding;
        int minStart = getMinStart(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (minStart != Integer.MAX_VALUE && (startAfterPadding = minStart - this.mPrimaryOrientation.getStartAfterPadding()) > 0) {
            int iScrollBy = startAfterPadding - scrollBy(startAfterPadding, recycler, state);
            if (!z6 || iScrollBy <= 0) {
                return;
            }
            this.mPrimaryOrientation.offsetChildren(-iScrollBy);
        }
    }

    private int getMaxEnd(int i7) {
        int endLine = this.mSpans[0].getEndLine(i7);
        for (int i8 = 1; i8 < this.mSpanCount; i8++) {
            int endLine2 = this.mSpans[i8].getEndLine(i7);
            if (endLine2 > endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    private int getMaxStart(int i7) {
        int startLine = this.mSpans[0].getStartLine(i7);
        for (int i8 = 1; i8 < this.mSpanCount; i8++) {
            int startLine2 = this.mSpans[i8].getStartLine(i7);
            if (startLine2 > startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    private int getMinEnd(int i7) {
        int endLine = this.mSpans[0].getEndLine(i7);
        for (int i8 = 1; i8 < this.mSpanCount; i8++) {
            int endLine2 = this.mSpans[i8].getEndLine(i7);
            if (endLine2 < endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    private int getMinStart(int i7) {
        int startLine = this.mSpans[0].getStartLine(i7);
        for (int i8 = 1; i8 < this.mSpanCount; i8++) {
            int startLine2 = this.mSpans[i8].getStartLine(i7);
            if (startLine2 < startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    private Span getNextSpan(LayoutState layoutState) {
        int i7;
        int i8;
        int i9 = -1;
        if (preferLastSpan(layoutState.mLayoutDirection)) {
            i7 = this.mSpanCount - 1;
            i8 = -1;
        } else {
            i7 = 0;
            i9 = this.mSpanCount;
            i8 = 1;
        }
        Span span = null;
        if (layoutState.mLayoutDirection == 1) {
            int i10 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
            while (i7 != i9) {
                Span span2 = this.mSpans[i7];
                int endLine = span2.getEndLine(startAfterPadding);
                if (endLine < i10) {
                    span = span2;
                    i10 = endLine;
                }
                i7 += i8;
            }
            return span;
        }
        int i11 = Integer.MIN_VALUE;
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        while (i7 != i9) {
            Span span3 = this.mSpans[i7];
            int startLine = span3.getStartLine(endAfterPadding);
            if (startLine > i11) {
                span = span3;
                i11 = startLine;
            }
            i7 += i8;
        }
        return span;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleUpdate(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.mShouldReverseLayout
            if (r0 == 0) goto L9
            int r0 = r6.getLastChildPosition()
            goto Ld
        L9:
            int r0 = r6.getFirstChildPosition()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1a
            if (r7 >= r8) goto L16
            int r2 = r8 + 1
            goto L1c
        L16:
            int r2 = r7 + 1
            r3 = r8
            goto L1d
        L1a:
            int r2 = r7 + r8
        L1c:
            r3 = r7
        L1d:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.mLazySpanLookup
            r4.invalidateAfter(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.offsetForRemoval(r7, r4)
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.mLazySpanLookup
            r7.offsetForAddition(r8, r4)
            goto L41
        L36:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.offsetForRemoval(r7, r8)
            goto L41
        L3c:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.offsetForAddition(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.mShouldReverseLayout
            if (r7 == 0) goto L4d
            int r7 = r6.getFirstChildPosition()
            goto L51
        L4d:
            int r7 = r6.getLastChildPosition()
        L51:
            if (r3 > r7) goto L56
            r6.requestLayout()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.handleUpdate(int, int, int):void");
    }

    private void measureChildWithDecorationsAndMargin(View view, LayoutParams layoutParams, boolean z6) {
        if (layoutParams.mFullSpan) {
            if (this.mOrientation != 1) {
                measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), this.mFullSizeSpec, z6);
                return;
            }
            measureChildWithDecorationsAndMargin(view, this.mFullSizeSpec, RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true), z6);
            return;
        }
        if (this.mOrientation != 1) {
            measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getHeightMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).height, false), z6);
            return;
        }
        measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getWidthMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).width, false), RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true), z6);
    }

    private boolean preferLastSpan(int i7) {
        if (this.mOrientation == 0) {
            return (i7 == -1) != this.mShouldReverseLayout;
        }
        return ((i7 == -1) == this.mShouldReverseLayout) == isLayoutRTL();
    }

    private void prependViewToAllSpans(View view) {
        for (int i7 = this.mSpanCount - 1; i7 >= 0; i7--) {
            this.mSpans[i7].prependToSpan(view);
        }
    }

    private void recycle(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (!layoutState.mRecycle || layoutState.mInfinite) {
            return;
        }
        if (layoutState.mAvailable == 0) {
            if (layoutState.mLayoutDirection == -1) {
                recycleFromEnd(recycler, layoutState.mEndLine);
                return;
            } else {
                recycleFromStart(recycler, layoutState.mStartLine);
                return;
            }
        }
        if (layoutState.mLayoutDirection != -1) {
            int minEnd = getMinEnd(layoutState.mEndLine) - layoutState.mEndLine;
            recycleFromStart(recycler, minEnd < 0 ? layoutState.mStartLine : Math.min(minEnd, layoutState.mAvailable) + layoutState.mStartLine);
        } else {
            int i7 = layoutState.mStartLine;
            int maxStart = i7 - getMaxStart(i7);
            recycleFromEnd(recycler, maxStart < 0 ? layoutState.mEndLine : layoutState.mEndLine - Math.min(maxStart, layoutState.mAvailable));
        }
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int i7) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.mPrimaryOrientation.getDecoratedStart(childAt) < i7 || this.mPrimaryOrientation.getTransformedStartWithDecoration(childAt) < i7) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.mFullSpan) {
                for (int i8 = 0; i8 < this.mSpanCount; i8++) {
                    if (this.mSpans[i8].mViews.size() == 1) {
                        return;
                    }
                }
                for (int i9 = 0; i9 < this.mSpanCount; i9++) {
                    this.mSpans[i9].popEnd();
                }
            } else if (layoutParams.mSpan.mViews.size() == 1) {
                return;
            } else {
                layoutParams.mSpan.popEnd();
            }
            removeAndRecycleView(childAt, recycler);
        }
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int i7) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > i7 || this.mPrimaryOrientation.getTransformedEndWithDecoration(childAt) > i7) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.mFullSpan) {
                for (int i8 = 0; i8 < this.mSpanCount; i8++) {
                    if (this.mSpans[i8].mViews.size() == 1) {
                        return;
                    }
                }
                for (int i9 = 0; i9 < this.mSpanCount; i9++) {
                    this.mSpans[i9].popStart();
                }
            } else if (layoutParams.mSpan.mViews.size() == 1) {
                return;
            } else {
                layoutParams.mSpan.popStart();
            }
            removeAndRecycleView(childAt, recycler);
        }
    }

    private void repositionToWrapContentIfNecessary() {
        if (this.mSecondaryOrientation.getMode() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            float decoratedMeasurement = this.mSecondaryOrientation.getDecoratedMeasurement(childAt);
            if (decoratedMeasurement >= fMax) {
                if (((LayoutParams) childAt.getLayoutParams()).isFullSpan()) {
                    decoratedMeasurement = (decoratedMeasurement * 1.0f) / this.mSpanCount;
                }
                fMax = Math.max(fMax, decoratedMeasurement);
            }
        }
        int i8 = this.mSizePerSpan;
        int iRound = Math.round(fMax * this.mSpanCount);
        if (this.mSecondaryOrientation.getMode() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.mSecondaryOrientation.getTotalSpace());
        }
        updateMeasureSpecs(iRound);
        if (this.mSizePerSpan == i8) {
            return;
        }
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt2 = getChildAt(i9);
            LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
            if (!layoutParams.mFullSpan) {
                if (isLayoutRTL() && this.mOrientation == 1) {
                    int i10 = this.mSpanCount;
                    int i11 = layoutParams.mSpan.mIndex;
                    childAt2.offsetLeftAndRight(((-((i10 - 1) - i11)) * this.mSizePerSpan) - ((-((i10 - 1) - i11)) * i8));
                } else {
                    int i12 = layoutParams.mSpan.mIndex;
                    int i13 = this.mSizePerSpan * i12;
                    int i14 = i12 * i8;
                    if (this.mOrientation == 1) {
                        childAt2.offsetLeftAndRight(i13 - i14);
                    } else {
                        childAt2.offsetTopAndBottom(i13 - i14);
                    }
                }
            }
        }
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    private void setLayoutStateDirection(int i7) {
        LayoutState layoutState = this.mLayoutState;
        layoutState.mLayoutDirection = i7;
        layoutState.mItemDirection = this.mShouldReverseLayout != (i7 == -1) ? -1 : 1;
    }

    private void updateAllRemainingSpans(int i7, int i8) {
        for (int i9 = 0; i9 < this.mSpanCount; i9++) {
            if (!this.mSpans[i9].mViews.isEmpty()) {
                updateRemainingSpans(this.mSpans[i9], i7, i8);
            }
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, AnchorInfo anchorInfo) {
        anchorInfo.mPosition = this.mLastLayoutFromEnd ? findLastReferenceChildPosition(state.getItemCount()) : findFirstReferenceChildPosition(state.getItemCount());
        anchorInfo.mOffset = Integer.MIN_VALUE;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateLayoutState(int r5, android.support.v7.widget.RecyclerView.State r6) {
        /*
            r4 = this;
            android.support.v7.widget.LayoutState r0 = r4.mLayoutState
            r1 = 0
            r0.mAvailable = r1
            r0.mCurrentPosition = r5
            boolean r0 = r4.isSmoothScrolling()
            r2 = 1
            if (r0 == 0) goto L2e
            int r6 = r6.getTargetScrollPosition()
            r0 = -1
            if (r6 == r0) goto L2e
            boolean r0 = r4.mShouldReverseLayout
            if (r6 >= r5) goto L1b
            r5 = 1
            goto L1c
        L1b:
            r5 = 0
        L1c:
            if (r0 != r5) goto L25
            android.support.v7.widget.OrientationHelper r5 = r4.mPrimaryOrientation
            int r5 = r5.getTotalSpace()
            goto L2f
        L25:
            android.support.v7.widget.OrientationHelper r5 = r4.mPrimaryOrientation
            int r5 = r5.getTotalSpace()
            r6 = r5
            r5 = 0
            goto L30
        L2e:
            r5 = 0
        L2f:
            r6 = 0
        L30:
            boolean r0 = r4.getClipToPadding()
            if (r0 == 0) goto L4d
            android.support.v7.widget.LayoutState r0 = r4.mLayoutState
            android.support.v7.widget.OrientationHelper r3 = r4.mPrimaryOrientation
            int r3 = r3.getStartAfterPadding()
            int r3 = r3 - r6
            r0.mStartLine = r3
            android.support.v7.widget.LayoutState r6 = r4.mLayoutState
            android.support.v7.widget.OrientationHelper r0 = r4.mPrimaryOrientation
            int r0 = r0.getEndAfterPadding()
            int r0 = r0 + r5
            r6.mEndLine = r0
            goto L5d
        L4d:
            android.support.v7.widget.LayoutState r0 = r4.mLayoutState
            android.support.v7.widget.OrientationHelper r3 = r4.mPrimaryOrientation
            int r3 = r3.getEnd()
            int r3 = r3 + r5
            r0.mEndLine = r3
            android.support.v7.widget.LayoutState r5 = r4.mLayoutState
            int r6 = -r6
            r5.mStartLine = r6
        L5d:
            android.support.v7.widget.LayoutState r5 = r4.mLayoutState
            r5.mStopInFocusable = r1
            r5.mRecycle = r2
            android.support.v7.widget.OrientationHelper r6 = r4.mPrimaryOrientation
            int r6 = r6.getMode()
            if (r6 != 0) goto L74
            android.support.v7.widget.OrientationHelper r6 = r4.mPrimaryOrientation
            int r6 = r6.getEnd()
            if (r6 != 0) goto L74
            r1 = 1
        L74:
            r5.mInfinite = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.updateLayoutState(int, android.support.v7.widget.RecyclerView$State):void");
    }

    private void updateRemainingSpans(Span span, int i7, int i8) {
        int deletedSize = span.getDeletedSize();
        if (i7 == -1) {
            if (span.getStartLine() + deletedSize <= i8) {
                this.mRemainingSpans.set(span.mIndex, false);
            }
        } else if (span.getEndLine() - deletedSize >= i8) {
            this.mRemainingSpans.set(span.mIndex, false);
        }
    }

    private int updateSpecWithExtra(int i7, int i8, int i9) {
        if (i8 == 0 && i9 == 0) {
            return i7;
        }
        int mode = View.MeasureSpec.getMode(i7);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i7) - i8) - i9), mode) : i7;
    }

    public boolean areAllEndsEqual() {
        int endLine = this.mSpans[0].getEndLine(Integer.MIN_VALUE);
        for (int i7 = 1; i7 < this.mSpanCount; i7++) {
            if (this.mSpans[i7].getEndLine(Integer.MIN_VALUE) != endLine) {
                return false;
            }
        }
        return true;
    }

    public boolean areAllStartsEqual() {
        int startLine = this.mSpans[0].getStartLine(Integer.MIN_VALUE);
        for (int i7 = 1; i7 < this.mSpanCount; i7++) {
            if (this.mSpans[i7].getStartLine(Integer.MIN_VALUE) != startLine) {
                return false;
            }
        }
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public boolean checkForGaps() {
        int firstChildPosition;
        int lastChildPosition;
        if (getChildCount() == 0 || this.mGapStrategy == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.mShouldReverseLayout) {
            firstChildPosition = getLastChildPosition();
            lastChildPosition = getFirstChildPosition();
        } else {
            firstChildPosition = getFirstChildPosition();
            lastChildPosition = getLastChildPosition();
        }
        if (firstChildPosition == 0 && hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
        if (!this.mLaidOutInvalidFullSpan) {
            return false;
        }
        int i7 = this.mShouldReverseLayout ? -1 : 1;
        int i8 = lastChildPosition + 1;
        LazySpanLookup.FullSpanItem firstFullSpanItemInRange = this.mLazySpanLookup.getFirstFullSpanItemInRange(firstChildPosition, i8, i7, true);
        if (firstFullSpanItemInRange == null) {
            this.mLaidOutInvalidFullSpan = false;
            this.mLazySpanLookup.forceInvalidateAfter(i8);
            return false;
        }
        LazySpanLookup.FullSpanItem firstFullSpanItemInRange2 = this.mLazySpanLookup.getFirstFullSpanItemInRange(firstChildPosition, firstFullSpanItemInRange.mPosition, i7 * (-1), true);
        if (firstFullSpanItemInRange2 == null) {
            this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange.mPosition);
        } else {
            this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange2.mPosition + 1);
        }
        requestSimpleAnimationsInNextLayout();
        requestLayout();
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void collectAdjacentPrefetchPositions(int i7, int i8, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int endLine;
        int startLine;
        if (this.mOrientation != 0) {
            i7 = i8;
        }
        if (getChildCount() == 0 || i7 == 0) {
            return;
        }
        prepareLayoutStateForDelta(i7, state);
        int[] iArr = this.mPrefetchDistances;
        if (iArr == null || iArr.length < this.mSpanCount) {
            this.mPrefetchDistances = new int[this.mSpanCount];
        }
        int i9 = 0;
        for (int i10 = 0; i10 < this.mSpanCount; i10++) {
            LayoutState layoutState = this.mLayoutState;
            if (layoutState.mItemDirection == -1) {
                endLine = layoutState.mStartLine;
                startLine = this.mSpans[i10].getStartLine(endLine);
            } else {
                endLine = this.mSpans[i10].getEndLine(layoutState.mEndLine);
                startLine = this.mLayoutState.mEndLine;
            }
            int i11 = endLine - startLine;
            if (i11 >= 0) {
                this.mPrefetchDistances[i9] = i11;
                i9++;
            }
        }
        Arrays.sort(this.mPrefetchDistances, 0, i9);
        for (int i12 = 0; i12 < i9 && this.mLayoutState.hasMore(state); i12++) {
            layoutPrefetchRegistry.addPosition(this.mLayoutState.mCurrentPosition, this.mPrefetchDistances[i12]);
            LayoutState layoutState2 = this.mLayoutState;
            layoutState2.mCurrentPosition += layoutState2.mItemDirection;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i7) {
        int iCalculateScrollDirectionForPosition = calculateScrollDirectionForPosition(i7);
        PointF pointF = new PointF();
        if (iCalculateScrollDirectionForPosition == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = iCalculateScrollDirectionForPosition;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iCalculateScrollDirectionForPosition;
        }
        return pointF;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public int[] findFirstCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            StringBuilder sbM112a = C0413b.m112a("Provided int[]'s size must be more than or equal to span count. Expected:");
            sbM112a.append(this.mSpanCount);
            sbM112a.append(", array size:");
            sbM112a.append(iArr.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
        for (int i7 = 0; i7 < this.mSpanCount; i7++) {
            iArr[i7] = this.mSpans[i7].findFirstCompletelyVisibleItemPosition();
        }
        return iArr;
    }

    public View findFirstVisibleItemClosestToEnd(boolean z6) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z6) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public View findFirstVisibleItemClosestToStart(boolean z6) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z6) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public int findFirstVisibleItemPositionInt() {
        View viewFindFirstVisibleItemClosestToEnd = this.mShouldReverseLayout ? findFirstVisibleItemClosestToEnd(true) : findFirstVisibleItemClosestToStart(true);
        if (viewFindFirstVisibleItemClosestToEnd == null) {
            return -1;
        }
        return getPosition(viewFindFirstVisibleItemClosestToEnd);
    }

    public int[] findFirstVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            StringBuilder sbM112a = C0413b.m112a("Provided int[]'s size must be more than or equal to span count. Expected:");
            sbM112a.append(this.mSpanCount);
            sbM112a.append(", array size:");
            sbM112a.append(iArr.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
        for (int i7 = 0; i7 < this.mSpanCount; i7++) {
            iArr[i7] = this.mSpans[i7].findFirstVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            StringBuilder sbM112a = C0413b.m112a("Provided int[]'s size must be more than or equal to span count. Expected:");
            sbM112a.append(this.mSpanCount);
            sbM112a.append(", array size:");
            sbM112a.append(iArr.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
        for (int i7 = 0; i7 < this.mSpanCount; i7++) {
            iArr[i7] = this.mSpans[i7].findLastCompletelyVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findLastVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            StringBuilder sbM112a = C0413b.m112a("Provided int[]'s size must be more than or equal to span count. Expected:");
            sbM112a.append(this.mSpanCount);
            sbM112a.append(", array size:");
            sbM112a.append(iArr.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
        for (int i7 = 0; i7 < this.mSpanCount; i7++) {
            iArr[i7] = this.mSpans[i7].findLastVisibleItemPosition();
        }
        return iArr;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.mOrientation == 1 ? this.mSpanCount : super.getColumnCountForAccessibility(recycler, state);
    }

    public int getFirstChildPosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    public int getGapStrategy() {
        return this.mGapStrategy;
    }

    public int getLastChildPosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.mOrientation == 0 ? this.mSpanCount : super.getRowCountForAccessibility(recycler, state);
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View hasGapsToFix() {
        /*
            r12 = this;
            int r0 = r12.getChildCount()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.mSpanCount
            r2.<init>(r3)
            int r3 = r12.mSpanCount
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.mOrientation
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.isLayoutRTL()
            if (r3 == 0) goto L20
            r3 = 1
            goto L21
        L20:
            r3 = -1
        L21:
            boolean r6 = r12.mShouldReverseLayout
            if (r6 == 0) goto L27
            r6 = -1
            goto L2b
        L27:
            int r0 = r0 + 1
            r6 = r0
            r0 = 0
        L2b:
            if (r0 >= r6) goto L2e
            r5 = 1
        L2e:
            if (r0 == r6) goto Lab
            android.view.View r7 = r12.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r8 = (android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams) r8
            android.support.v7.widget.StaggeredGridLayoutManager$Span r9 = r8.mSpan
            int r9 = r9.mIndex
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            android.support.v7.widget.StaggeredGridLayoutManager$Span r9 = r8.mSpan
            boolean r9 = r12.checkSpanForGap(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            android.support.v7.widget.StaggeredGridLayoutManager$Span r9 = r8.mSpan
            int r9 = r9.mIndex
            r2.clear(r9)
        L54:
            boolean r9 = r8.mFullSpan
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.getChildAt(r9)
            boolean r10 = r12.mShouldReverseLayout
            if (r10 == 0) goto L77
            android.support.v7.widget.OrientationHelper r10 = r12.mPrimaryOrientation
            int r10 = r10.getDecoratedEnd(r7)
            android.support.v7.widget.OrientationHelper r11 = r12.mPrimaryOrientation
            int r11 = r11.getDecoratedEnd(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            android.support.v7.widget.OrientationHelper r10 = r12.mPrimaryOrientation
            int r10 = r10.getDecoratedStart(r7)
            android.support.v7.widget.OrientationHelper r11 = r12.mPrimaryOrientation
            int r11 = r11.getDecoratedStart(r9)
            if (r10 <= r11) goto L86
            return r7
        L86:
            if (r10 != r11) goto L8a
        L88:
            r10 = 1
            goto L8b
        L8a:
            r10 = 0
        L8b:
            if (r10 == 0) goto La9
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r9 = (android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams) r9
            android.support.v7.widget.StaggeredGridLayoutManager$Span r8 = r8.mSpan
            int r8 = r8.mIndex
            android.support.v7.widget.StaggeredGridLayoutManager$Span r9 = r9.mSpan
            int r9 = r9.mIndex
            int r8 = r8 - r9
            if (r8 >= 0) goto La0
            r8 = 1
            goto La1
        La0:
            r8 = 0
        La1:
            if (r3 >= 0) goto La5
            r9 = 1
            goto La6
        La5:
            r9 = 0
        La6:
            if (r8 == r9) goto La9
            return r7
        La9:
            int r0 = r0 + r5
            goto L2e
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.hasGapsToFix():android.view.View");
    }

    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void offsetChildrenHorizontal(int i7) {
        super.offsetChildrenHorizontal(i7);
        for (int i8 = 0; i8 < this.mSpanCount; i8++) {
            this.mSpans[i8].onOffset(i7);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void offsetChildrenVertical(int i7) {
        super.offsetChildrenVertical(i7);
        for (int i8 = 0; i8 < this.mSpanCount; i8++) {
            this.mSpans[i8].onOffset(i7);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        removeCallbacks(this.mCheckForGapsRunnable);
        for (int i7 = 0; i7 < this.mSpanCount; i7++) {
            this.mSpans[i7].clear();
        }
        recyclerView.requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    @Nullable
    public View onFocusSearchFailed(View view, int i7, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View viewFindContainingItemView;
        View focusableViewAfter;
        if (getChildCount() == 0 || (viewFindContainingItemView = findContainingItemView(view)) == null) {
            return null;
        }
        resolveShouldLayoutReverse();
        int iConvertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i7);
        if (iConvertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) viewFindContainingItemView.getLayoutParams();
        boolean z6 = layoutParams.mFullSpan;
        Span span = layoutParams.mSpan;
        int lastChildPosition = iConvertFocusDirectionToLayoutDirection == 1 ? getLastChildPosition() : getFirstChildPosition();
        updateLayoutState(lastChildPosition, state);
        setLayoutStateDirection(iConvertFocusDirectionToLayoutDirection);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = layoutState.mItemDirection + lastChildPosition;
        layoutState.mAvailable = (int) (this.mPrimaryOrientation.getTotalSpace() * MAX_SCROLL_FACTOR);
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.mStopInFocusable = true;
        layoutState2.mRecycle = false;
        fill(recycler, layoutState2, state);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        if (!z6 && (focusableViewAfter = span.getFocusableViewAfter(lastChildPosition, iConvertFocusDirectionToLayoutDirection)) != null && focusableViewAfter != viewFindContainingItemView) {
            return focusableViewAfter;
        }
        if (preferLastSpan(iConvertFocusDirectionToLayoutDirection)) {
            for (int i8 = this.mSpanCount - 1; i8 >= 0; i8--) {
                View focusableViewAfter2 = this.mSpans[i8].getFocusableViewAfter(lastChildPosition, iConvertFocusDirectionToLayoutDirection);
                if (focusableViewAfter2 != null && focusableViewAfter2 != viewFindContainingItemView) {
                    return focusableViewAfter2;
                }
            }
        } else {
            for (int i9 = 0; i9 < this.mSpanCount; i9++) {
                View focusableViewAfter3 = this.mSpans[i9].getFocusableViewAfter(lastChildPosition, iConvertFocusDirectionToLayoutDirection);
                if (focusableViewAfter3 != null && focusableViewAfter3 != viewFindContainingItemView) {
                    return focusableViewAfter3;
                }
            }
        }
        boolean z7 = (this.mReverseLayout ^ true) == (iConvertFocusDirectionToLayoutDirection == -1);
        if (!z6) {
            View viewFindViewByPosition = findViewByPosition(z7 ? span.findFirstPartiallyVisibleItemPosition() : span.findLastPartiallyVisibleItemPosition());
            if (viewFindViewByPosition != null && viewFindViewByPosition != viewFindContainingItemView) {
                return viewFindViewByPosition;
            }
        }
        if (preferLastSpan(iConvertFocusDirectionToLayoutDirection)) {
            for (int i10 = this.mSpanCount - 1; i10 >= 0; i10--) {
                if (i10 != span.mIndex) {
                    View viewFindViewByPosition2 = findViewByPosition(z7 ? this.mSpans[i10].findFirstPartiallyVisibleItemPosition() : this.mSpans[i10].findLastPartiallyVisibleItemPosition());
                    if (viewFindViewByPosition2 != null && viewFindViewByPosition2 != viewFindContainingItemView) {
                        return viewFindViewByPosition2;
                    }
                }
            }
        } else {
            for (int i11 = 0; i11 < this.mSpanCount; i11++) {
                View viewFindViewByPosition3 = findViewByPosition(z7 ? this.mSpans[i11].findFirstPartiallyVisibleItemPosition() : this.mSpans[i11].findLastPartiallyVisibleItemPosition());
                if (viewFindViewByPosition3 != null && viewFindViewByPosition3 != viewFindContainingItemView) {
                    return viewFindViewByPosition3;
                }
            }
        }
        return null;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View viewFindFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(false);
            View viewFindFirstVisibleItemClosestToEnd = findFirstVisibleItemClosestToEnd(false);
            if (viewFindFirstVisibleItemClosestToStart == null || viewFindFirstVisibleItemClosestToEnd == null) {
                return;
            }
            int position = getPosition(viewFindFirstVisibleItemClosestToStart);
            int position2 = getPosition(viewFindFirstVisibleItemClosestToEnd);
            if (position < position2) {
                accessibilityEvent.setFromIndex(position);
                accessibilityEvent.setToIndex(position2);
            } else {
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.mOrientation == 0) {
            int spanIndex = layoutParams2.getSpanIndex();
            boolean z6 = layoutParams2.mFullSpan;
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanIndex, z6 ? this.mSpanCount : 1, -1, -1, z6, false));
        } else {
            int spanIndex2 = layoutParams2.getSpanIndex();
            boolean z7 = layoutParams2.mFullSpan;
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, spanIndex2, z7 ? this.mSpanCount : 1, z7, false));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i7, int i8) {
        handleUpdate(i7, i8, 1);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i7, int i8, int i9) {
        handleUpdate(i7, i8, 8);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i7, int i8) {
        handleUpdate(i7, i8, 2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i7, int i8, Object obj) {
        handleUpdate(i7, i8, 4);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        int startLine;
        int startAfterPadding;
        int[] iArr;
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        savedState.mReverseLayout = this.mReverseLayout;
        savedState.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        savedState.mLastLayoutRTL = this.mLastLayoutRTL;
        LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.mData) == null) {
            savedState.mSpanLookupSize = 0;
        } else {
            savedState.mSpanLookup = iArr;
            savedState.mSpanLookupSize = iArr.length;
            savedState.mFullSpanItems = lazySpanLookup.mFullSpanItems;
        }
        if (getChildCount() > 0) {
            savedState.mAnchorPosition = this.mLastLayoutFromEnd ? getLastChildPosition() : getFirstChildPosition();
            savedState.mVisibleAnchorPosition = findFirstVisibleItemPositionInt();
            int i7 = this.mSpanCount;
            savedState.mSpanOffsetsSize = i7;
            savedState.mSpanOffsets = new int[i7];
            for (int i8 = 0; i8 < this.mSpanCount; i8++) {
                if (this.mLastLayoutFromEnd) {
                    startLine = this.mSpans[i8].getEndLine(Integer.MIN_VALUE);
                    if (startLine != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
                        startLine -= startAfterPadding;
                    }
                } else {
                    startLine = this.mSpans[i8].getStartLine(Integer.MIN_VALUE);
                    if (startLine != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
                        startLine -= startAfterPadding;
                    }
                }
                savedState.mSpanOffsets[i8] = startLine;
            }
        } else {
            savedState.mAnchorPosition = -1;
            savedState.mVisibleAnchorPosition = -1;
            savedState.mSpanOffsetsSize = 0;
        }
        return savedState;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i7) {
        if (i7 == 0) {
            checkForGaps();
        }
    }

    public void prepareLayoutStateForDelta(int i7, RecyclerView.State state) {
        int firstChildPosition;
        int i8;
        if (i7 > 0) {
            firstChildPosition = getLastChildPosition();
            i8 = 1;
        } else {
            firstChildPosition = getFirstChildPosition();
            i8 = -1;
        }
        this.mLayoutState.mRecycle = true;
        updateLayoutState(firstChildPosition, state);
        setLayoutStateDirection(i8);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = firstChildPosition + layoutState.mItemDirection;
        layoutState.mAvailable = Math.abs(i7);
    }

    public int scrollBy(int i7, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i7 == 0) {
            return 0;
        }
        prepareLayoutStateForDelta(i7, state);
        int iFill = fill(recycler, this.mLayoutState, state);
        if (this.mLayoutState.mAvailable >= iFill) {
            i7 = i7 < 0 ? -iFill : iFill;
        }
        this.mPrimaryOrientation.offsetChildren(-i7);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        LayoutState layoutState = this.mLayoutState;
        layoutState.mAvailable = 0;
        recycle(recycler, layoutState);
        return i7;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i7, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i7, recycler, state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i7) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.mAnchorPosition != i7) {
            savedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = i7;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i7, int i8) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = i7;
        this.mPendingScrollPositionOffset = i8;
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i7, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i7, recycler, state);
    }

    public void setGapStrategy(int i7) {
        assertNotInLayoutOrScroll(null);
        if (i7 == this.mGapStrategy) {
            return;
        }
        if (i7 != 0 && i7 != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        this.mGapStrategy = i7;
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void setMeasuredDimension(Rect rect, int i7, int i8) {
        int iChooseSize;
        int iChooseSize2;
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            iChooseSize2 = RecyclerView.LayoutManager.chooseSize(i8, rect.height() + paddingBottom, getMinimumHeight());
            iChooseSize = RecyclerView.LayoutManager.chooseSize(i7, (this.mSizePerSpan * this.mSpanCount) + paddingRight, getMinimumWidth());
        } else {
            iChooseSize = RecyclerView.LayoutManager.chooseSize(i7, rect.width() + paddingRight, getMinimumWidth());
            iChooseSize2 = RecyclerView.LayoutManager.chooseSize(i8, (this.mSizePerSpan * this.mSpanCount) + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(iChooseSize, iChooseSize2);
    }

    public void setOrientation(int i7) {
        if (i7 != 0 && i7 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i7 == this.mOrientation) {
            return;
        }
        this.mOrientation = i7;
        OrientationHelper orientationHelper = this.mPrimaryOrientation;
        this.mPrimaryOrientation = this.mSecondaryOrientation;
        this.mSecondaryOrientation = orientationHelper;
        requestLayout();
    }

    public void setReverseLayout(boolean z6) {
        assertNotInLayoutOrScroll(null);
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.mReverseLayout != z6) {
            savedState.mReverseLayout = z6;
        }
        this.mReverseLayout = z6;
        requestLayout();
    }

    public void setSpanCount(int i7) {
        assertNotInLayoutOrScroll(null);
        if (i7 != this.mSpanCount) {
            invalidateSpanAssignments();
            this.mSpanCount = i7;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new Span[this.mSpanCount];
            for (int i8 = 0; i8 < this.mSpanCount; i8++) {
                this.mSpans[i8] = new Span(i8);
            }
            requestLayout();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i7) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i7);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    public boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i7;
        if (!state.isPreLayout() && (i7 = this.mPendingScrollPosition) != -1) {
            if (i7 >= 0 && i7 < state.getItemCount()) {
                SavedState savedState = this.mPendingSavedState;
                if (savedState == null || savedState.mAnchorPosition == -1 || savedState.mSpanOffsetsSize < 1) {
                    View viewFindViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                    if (viewFindViewByPosition != null) {
                        anchorInfo.mPosition = this.mShouldReverseLayout ? getLastChildPosition() : getFirstChildPosition();
                        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                            if (anchorInfo.mLayoutFromEnd) {
                                anchorInfo.mOffset = (this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedEnd(viewFindViewByPosition);
                            } else {
                                anchorInfo.mOffset = (this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedStart(viewFindViewByPosition);
                            }
                            return true;
                        }
                        if (this.mPrimaryOrientation.getDecoratedMeasurement(viewFindViewByPosition) > this.mPrimaryOrientation.getTotalSpace()) {
                            anchorInfo.mOffset = anchorInfo.mLayoutFromEnd ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding();
                            return true;
                        }
                        int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(viewFindViewByPosition) - this.mPrimaryOrientation.getStartAfterPadding();
                        if (decoratedStart < 0) {
                            anchorInfo.mOffset = -decoratedStart;
                            return true;
                        }
                        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(viewFindViewByPosition);
                        if (endAfterPadding < 0) {
                            anchorInfo.mOffset = endAfterPadding;
                            return true;
                        }
                        anchorInfo.mOffset = Integer.MIN_VALUE;
                    } else {
                        int i8 = this.mPendingScrollPosition;
                        anchorInfo.mPosition = i8;
                        int i9 = this.mPendingScrollPositionOffset;
                        if (i9 == Integer.MIN_VALUE) {
                            anchorInfo.mLayoutFromEnd = calculateScrollDirectionForPosition(i8) == 1;
                            anchorInfo.assignCoordinateFromPadding();
                        } else {
                            anchorInfo.assignCoordinateFromPadding(i9);
                        }
                        anchorInfo.mInvalidateOffsets = true;
                    }
                } else {
                    anchorInfo.mOffset = Integer.MIN_VALUE;
                    anchorInfo.mPosition = this.mPendingScrollPosition;
                }
                return true;
            }
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        }
        return false;
    }

    public void updateAnchorInfoForLayout(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (updateAnchorFromPendingData(state, anchorInfo) || updateAnchorFromChildren(state, anchorInfo)) {
            return;
        }
        anchorInfo.assignCoordinateFromPadding();
        anchorInfo.mPosition = 0;
    }

    public void updateMeasureSpecs(int i7) {
        this.mSizePerSpan = i7 / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(i7, this.mSecondaryOrientation.getMode());
    }

    public class AnchorInfo {
        public boolean mInvalidateOffsets;
        public boolean mLayoutFromEnd;
        public int mOffset;
        public int mPosition;
        public int[] mSpanReferenceLines;
        public boolean mValid;

        public AnchorInfo() {
            reset();
        }

        public void assignCoordinateFromPadding() {
            this.mOffset = this.mLayoutFromEnd ? StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() : StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
        }

        public void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
            this.mValid = false;
            int[] iArr = this.mSpanReferenceLines;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        public void saveSpanReferenceLines(Span[] spanArr) {
            int length = spanArr.length;
            int[] iArr = this.mSpanReferenceLines;
            if (iArr == null || iArr.length < length) {
                this.mSpanReferenceLines = new int[StaggeredGridLayoutManager.this.mSpans.length];
            }
            for (int i7 = 0; i7 < length; i7++) {
                this.mSpanReferenceLines[i7] = spanArr[i7].getStartLine(Integer.MIN_VALUE);
            }
        }

        public void assignCoordinateFromPadding(int i7) {
            if (this.mLayoutFromEnd) {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - i7;
            } else {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding() + i7;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void onLayoutChildren(android.support.v7.widget.RecyclerView.Recycler r9, android.support.v7.widget.RecyclerView.State r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.onLayoutChildren(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, boolean):void");
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public class Span {
        public static final int INVALID_LINE = Integer.MIN_VALUE;
        public final int mIndex;
        public ArrayList<View> mViews = new ArrayList<>();
        public int mCachedStart = Integer.MIN_VALUE;
        public int mCachedEnd = Integer.MIN_VALUE;
        public int mDeletedSize = 0;

        public Span(int i7) {
            this.mIndex = i7;
        }

        public void appendToSpan(View view) {
            LayoutParams layoutParams = getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(view);
            this.mCachedEnd = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view) + this.mDeletedSize;
            }
        }

        public void cacheReferenceLineAndClear(boolean z6, int i7) {
            int endLine = z6 ? getEndLine(Integer.MIN_VALUE) : getStartLine(Integer.MIN_VALUE);
            clear();
            if (endLine == Integer.MIN_VALUE) {
                return;
            }
            if (!z6 || endLine >= StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding()) {
                if (z6 || endLine <= StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding()) {
                    if (i7 != Integer.MIN_VALUE) {
                        endLine += i7;
                    }
                    this.mCachedEnd = endLine;
                    this.mCachedStart = endLine;
                }
            }
        }

        public void calculateCachedEnd() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            ArrayList<View> arrayList = this.mViews;
            View view = arrayList.get(arrayList.size() - 1);
            LayoutParams layoutParams = getLayoutParams(view);
            this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
            if (layoutParams.mFullSpan && (fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition())) != null && fullSpanItem.mGapDir == 1) {
                this.mCachedEnd += fullSpanItem.getGapForSpan(this.mIndex);
            }
        }

        public void calculateCachedStart() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            View view = this.mViews.get(0);
            LayoutParams layoutParams = getLayoutParams(view);
            this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            if (layoutParams.mFullSpan && (fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition())) != null && fullSpanItem.mGapDir == -1) {
                this.mCachedStart -= fullSpanItem.getGapForSpan(this.mIndex);
            }
        }

        public void clear() {
            this.mViews.clear();
            invalidateCache();
            this.mDeletedSize = 0;
        }

        public int findFirstCompletelyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? findOneVisibleChild(this.mViews.size() - 1, -1, true) : findOneVisibleChild(0, this.mViews.size(), true);
        }

        public int findFirstPartiallyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true) : findOnePartiallyVisibleChild(0, this.mViews.size(), true);
        }

        public int findFirstVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? findOneVisibleChild(this.mViews.size() - 1, -1, false) : findOneVisibleChild(0, this.mViews.size(), false);
        }

        public int findLastCompletelyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? findOneVisibleChild(0, this.mViews.size(), true) : findOneVisibleChild(this.mViews.size() - 1, -1, true);
        }

        public int findLastPartiallyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? findOnePartiallyVisibleChild(0, this.mViews.size(), true) : findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
        }

        public int findLastVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? findOneVisibleChild(0, this.mViews.size(), false) : findOneVisibleChild(this.mViews.size() - 1, -1, false);
        }

        public int findOnePartiallyOrCompletelyVisibleChild(int i7, int i8, boolean z6, boolean z7, boolean z8) {
            int startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            int endAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            int i9 = i8 > i7 ? 1 : -1;
            while (i7 != i8) {
                View view = this.mViews.get(i7);
                int decoratedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
                int decoratedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
                boolean z9 = false;
                boolean z10 = !z8 ? decoratedStart >= endAfterPadding : decoratedStart > endAfterPadding;
                if (!z8 ? decoratedEnd > startAfterPadding : decoratedEnd >= startAfterPadding) {
                    z9 = true;
                }
                if (z10 && z9) {
                    if (z6 && z7) {
                        if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    } else {
                        if (z7) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                        if (decoratedStart < startAfterPadding || decoratedEnd > endAfterPadding) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    }
                }
                i7 += i9;
            }
            return -1;
        }

        public int findOnePartiallyVisibleChild(int i7, int i8, boolean z6) {
            return findOnePartiallyOrCompletelyVisibleChild(i7, i8, false, false, z6);
        }

        public int findOneVisibleChild(int i7, int i8, boolean z6) {
            return findOnePartiallyOrCompletelyVisibleChild(i7, i8, z6, true, false);
        }

        public int getDeletedSize() {
            return this.mDeletedSize;
        }

        public int getEndLine(int i7) {
            int i8 = this.mCachedEnd;
            if (i8 != Integer.MIN_VALUE) {
                return i8;
            }
            if (this.mViews.size() == 0) {
                return i7;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        public View getFocusableViewAfter(int i7, int i8) {
            View view = null;
            if (i8 != -1) {
                int size = this.mViews.size() - 1;
                while (size >= 0) {
                    View view2 = this.mViews.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.mReverseLayout && staggeredGridLayoutManager.getPosition(view2) >= i7) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.mReverseLayout && staggeredGridLayoutManager2.getPosition(view2) <= i7) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.mViews.size();
                int i9 = 0;
                while (i9 < size2) {
                    View view3 = this.mViews.get(i9);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.mReverseLayout && staggeredGridLayoutManager3.getPosition(view3) <= i7) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.mReverseLayout && staggeredGridLayoutManager4.getPosition(view3) >= i7) || !view3.hasFocusable()) {
                        break;
                    }
                    i9++;
                    view = view3;
                }
            }
            return view;
        }

        public LayoutParams getLayoutParams(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        public int getStartLine(int i7) {
            int i8 = this.mCachedStart;
            if (i8 != Integer.MIN_VALUE) {
                return i8;
            }
            if (this.mViews.size() == 0) {
                return i7;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        public void invalidateCache() {
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        public void onOffset(int i7) {
            int i8 = this.mCachedStart;
            if (i8 != Integer.MIN_VALUE) {
                this.mCachedStart = i8 + i7;
            }
            int i9 = this.mCachedEnd;
            if (i9 != Integer.MIN_VALUE) {
                this.mCachedEnd = i9 + i7;
            }
        }

        public void popEnd() {
            int size = this.mViews.size();
            View viewRemove = this.mViews.remove(size - 1);
            LayoutParams layoutParams = getLayoutParams(viewRemove);
            layoutParams.mSpan = null;
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(viewRemove);
            }
            if (size == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        public void popStart() {
            View viewRemove = this.mViews.remove(0);
            LayoutParams layoutParams = getLayoutParams(viewRemove);
            layoutParams.mSpan = null;
            if (this.mViews.size() == 0) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(viewRemove);
            }
            this.mCachedStart = Integer.MIN_VALUE;
        }

        public void prependToSpan(View view) {
            LayoutParams layoutParams = getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(0, view);
            this.mCachedStart = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view) + this.mDeletedSize;
            }
        }

        public void setLine(int i7) {
            this.mCachedStart = i7;
            this.mCachedEnd = i7;
        }

        public int getEndLine() {
            int i7 = this.mCachedEnd;
            if (i7 != Integer.MIN_VALUE) {
                return i7;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        public int getStartLine() {
            int i7 = this.mCachedStart;
            if (i7 != Integer.MIN_VALUE) {
                return i7;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }
    }

    public static class LazySpanLookup {
        private static final int MIN_SIZE = 10;
        public int[] mData;
        public List<FullSpanItem> mFullSpanItems;

        private int invalidateFullSpansAfter(int i7) {
            if (this.mFullSpanItems == null) {
                return -1;
            }
            FullSpanItem fullSpanItem = getFullSpanItem(i7);
            if (fullSpanItem != null) {
                this.mFullSpanItems.remove(fullSpanItem);
            }
            int size = this.mFullSpanItems.size();
            int i8 = 0;
            while (true) {
                if (i8 >= size) {
                    i8 = -1;
                    break;
                }
                if (this.mFullSpanItems.get(i8).mPosition >= i7) {
                    break;
                }
                i8++;
            }
            if (i8 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(i8);
            this.mFullSpanItems.remove(i8);
            return fullSpanItem2.mPosition;
        }

        private void offsetFullSpansForAddition(int i7, int i8) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                int i9 = fullSpanItem.mPosition;
                if (i9 >= i7) {
                    fullSpanItem.mPosition = i9 + i8;
                }
            }
        }

        private void offsetFullSpansForRemoval(int i7, int i8) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list == null) {
                return;
            }
            int i9 = i7 + i8;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                int i10 = fullSpanItem.mPosition;
                if (i10 >= i7) {
                    if (i10 < i9) {
                        this.mFullSpanItems.remove(size);
                    } else {
                        fullSpanItem.mPosition = i10 - i8;
                    }
                }
            }
        }

        public void addFullSpanItem(FullSpanItem fullSpanItem) {
            if (this.mFullSpanItems == null) {
                this.mFullSpanItems = new ArrayList();
            }
            int size = this.mFullSpanItems.size();
            for (int i7 = 0; i7 < size; i7++) {
                FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(i7);
                if (fullSpanItem2.mPosition == fullSpanItem.mPosition) {
                    this.mFullSpanItems.remove(i7);
                }
                if (fullSpanItem2.mPosition >= fullSpanItem.mPosition) {
                    this.mFullSpanItems.add(i7, fullSpanItem);
                    return;
                }
            }
            this.mFullSpanItems.add(fullSpanItem);
        }

        public void clear() {
            int[] iArr = this.mData;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.mFullSpanItems = null;
        }

        public void ensureSize(int i7) {
            int[] iArr = this.mData;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i7, 10) + 1];
                this.mData = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i7 >= iArr.length) {
                int[] iArr3 = new int[sizeForPosition(i7)];
                this.mData = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.mData;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int forceInvalidateAfter(int i7) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.mFullSpanItems.get(size).mPosition >= i7) {
                        this.mFullSpanItems.remove(size);
                    }
                }
            }
            return invalidateAfter(i7);
        }

        public FullSpanItem getFirstFullSpanItemInRange(int i7, int i8, int i9, boolean z6) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(i10);
                int i11 = fullSpanItem.mPosition;
                if (i11 >= i8) {
                    return null;
                }
                if (i11 >= i7 && (i9 == 0 || fullSpanItem.mGapDir == i9 || (z6 && fullSpanItem.mHasUnwantedGapAfter))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem getFullSpanItem(int i7) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                if (fullSpanItem.mPosition == i7) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public int getSpan(int i7) {
            int[] iArr = this.mData;
            if (iArr == null || i7 >= iArr.length) {
                return -1;
            }
            return iArr[i7];
        }

        public int invalidateAfter(int i7) {
            int[] iArr = this.mData;
            if (iArr == null || i7 >= iArr.length) {
                return -1;
            }
            int iInvalidateFullSpansAfter = invalidateFullSpansAfter(i7);
            if (iInvalidateFullSpansAfter == -1) {
                int[] iArr2 = this.mData;
                Arrays.fill(iArr2, i7, iArr2.length, -1);
                return this.mData.length;
            }
            int i8 = iInvalidateFullSpansAfter + 1;
            Arrays.fill(this.mData, i7, i8, -1);
            return i8;
        }

        public void offsetForAddition(int i7, int i8) {
            int[] iArr = this.mData;
            if (iArr == null || i7 >= iArr.length) {
                return;
            }
            int i9 = i7 + i8;
            ensureSize(i9);
            int[] iArr2 = this.mData;
            System.arraycopy(iArr2, i7, iArr2, i9, (iArr2.length - i7) - i8);
            Arrays.fill(this.mData, i7, i9, -1);
            offsetFullSpansForAddition(i7, i8);
        }

        public void offsetForRemoval(int i7, int i8) {
            int[] iArr = this.mData;
            if (iArr == null || i7 >= iArr.length) {
                return;
            }
            int i9 = i7 + i8;
            ensureSize(i9);
            int[] iArr2 = this.mData;
            System.arraycopy(iArr2, i9, iArr2, i7, (iArr2.length - i7) - i8);
            int[] iArr3 = this.mData;
            Arrays.fill(iArr3, iArr3.length - i8, iArr3.length, -1);
            offsetFullSpansForRemoval(i7, i8);
        }

        public void setSpan(int i7, Span span) {
            ensureSize(i7);
            this.mData[i7] = span.mIndex;
        }

        public int sizeForPosition(int i7) {
            int length = this.mData.length;
            while (length <= i7) {
                length *= 2;
            }
            return length;
        }

        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new Parcelable.Creator<FullSpanItem>() { // from class: android.support.v7.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FullSpanItem[] newArray(int i7) {
                    return new FullSpanItem[i7];
                }
            };
            public int mGapDir;
            public int[] mGapPerSpan;
            public boolean mHasUnwantedGapAfter;
            public int mPosition;

            public FullSpanItem(Parcel parcel) {
                this.mPosition = parcel.readInt();
                this.mGapDir = parcel.readInt();
                this.mHasUnwantedGapAfter = parcel.readInt() == 1;
                int i7 = parcel.readInt();
                if (i7 > 0) {
                    int[] iArr = new int[i7];
                    this.mGapPerSpan = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public int getGapForSpan(int i7) {
                int[] iArr = this.mGapPerSpan;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i7];
            }

            public String toString() {
                StringBuilder sbM112a = C0413b.m112a("FullSpanItem{mPosition=");
                sbM112a.append(this.mPosition);
                sbM112a.append(", mGapDir=");
                sbM112a.append(this.mGapDir);
                sbM112a.append(", mHasUnwantedGapAfter=");
                sbM112a.append(this.mHasUnwantedGapAfter);
                sbM112a.append(", mGapPerSpan=");
                sbM112a.append(Arrays.toString(this.mGapPerSpan));
                sbM112a.append('}');
                return sbM112a.toString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i7) {
                parcel.writeInt(this.mPosition);
                parcel.writeInt(this.mGapDir);
                parcel.writeInt(this.mHasUnwantedGapAfter ? 1 : 0);
                int[] iArr = this.mGapPerSpan;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.mGapPerSpan);
                }
            }

            public FullSpanItem() {
            }
        }
    }

    public StaggeredGridLayoutManager(int i7, int i8) {
        this.mOrientation = i8;
        setSpanCount(i7);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    private void measureChildWithDecorationsAndMargin(View view, int i7, int i8, boolean z6) {
        boolean zShouldMeasureChild;
        calculateItemDecorationsForChild(view, this.mTmpRect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i9 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        Rect rect = this.mTmpRect;
        int iUpdateSpecWithExtra = updateSpecWithExtra(i7, i9 + rect.left, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + rect.right);
        int i10 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        Rect rect2 = this.mTmpRect;
        int iUpdateSpecWithExtra2 = updateSpecWithExtra(i8, i10 + rect2.top, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + rect2.bottom);
        if (z6) {
            zShouldMeasureChild = shouldReMeasureChild(view, iUpdateSpecWithExtra, iUpdateSpecWithExtra2, layoutParams);
        } else {
            zShouldMeasureChild = shouldMeasureChild(view, iUpdateSpecWithExtra, iUpdateSpecWithExtra2, layoutParams);
        }
        if (zShouldMeasureChild) {
            view.measure(iUpdateSpecWithExtra, iUpdateSpecWithExtra2);
        }
    }
}
