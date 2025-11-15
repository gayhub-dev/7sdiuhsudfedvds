package android.support.v7.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.Preconditions;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.recyclerview.C0311R;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.AdapterHelper;
import android.support.v7.widget.ChildHelper;
import android.support.v7.widget.GapWorker;
import android.support.v7.widget.ViewBoundsCheck;
import android.support.v7.widget.ViewInfoStore;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p009b.C0413b;

/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2 {
    public static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    public static final boolean ALLOW_THREAD_GAP_WORK;
    public static final boolean DEBUG = false;
    public static final int DEFAULT_ORIENTATION = 1;
    public static final boolean DISPATCH_TEMP_DETACH = false;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
    public static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    public static final long FOREVER_NS = Long.MAX_VALUE;
    public static final int HORIZONTAL = 0;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    public static final int MAX_SCROLL_DURATION = 2000;
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    public static final boolean POST_UPDATES_ON_ANIMATION;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    public static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    public static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    public static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    public static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    public static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    public static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    public static final Interpolator sQuinticInterpolator;
    public RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private OnItemTouchListener mActiveOnItemTouchListener;
    public Adapter mAdapter;
    public AdapterHelper mAdapterHelper;
    public boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    public ChildHelper mChildHelper;
    public boolean mClipToPadding;
    public boolean mDataSetHasChangedAfterLayout;
    public boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;

    @NonNull
    private EdgeEffectFactory mEdgeEffectFactory;
    public boolean mEnableFastScroller;

    @VisibleForTesting
    public boolean mFirstLayoutComplete;
    public GapWorker mGapWorker;
    public boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    public boolean mIsAttached;
    public ItemAnimator mItemAnimator;
    private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    public final ArrayList<ItemDecoration> mItemDecorations;
    public boolean mItemsAddedOrRemoved;
    public boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;

    @VisibleForTesting
    public LayoutManager mLayout;
    public boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    public boolean mLayoutWasDefered;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;

    @VisibleForTesting
    public final List<ViewHolder> mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    public boolean mPostedAnimatorRunner;
    public GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    public final Recycler mRecycler;
    public RecyclerListener mRecyclerListener;
    private EdgeEffect mRightGlow;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    public final int[] mScrollConsumed;
    private OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    public final int[] mScrollStepConsumed;
    private NestedScrollingChildHelper mScrollingChildHelper;
    public final State mState;
    public final Rect mTempRect;
    private final Rect mTempRect2;
    public final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    public final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    public final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    public final ViewInfoStore mViewInfoStore;
    private static final int[] NESTED_SCROLLING_ATTRS = {R.attr.nestedScrollingEnabled};
    private static final int[] CLIP_TO_PADDING_ATTR = {R.attr.clipToPadding};

    public static abstract class Adapter<VH extends ViewHolder> {
        private final AdapterDataObservable mObservable = new AdapterDataObservable();
        private boolean mHasStableIds = false;

        public final void bindViewHolder(@NonNull VH vh, int i7) {
            vh.mPosition = i7;
            if (hasStableIds()) {
                vh.mItemId = getItemId(i7);
            }
            vh.setFlags(1, 519);
            TraceCompat.beginSection(RecyclerView.TRACE_BIND_VIEW_TAG);
            onBindViewHolder(vh, i7, vh.getUnmodifiedPayloads());
            vh.clearPayload();
            ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                ((LayoutParams) layoutParams).mInsetsDirty = true;
            }
            TraceCompat.endSection();
        }

        @NonNull
        public final VH createViewHolder(@NonNull ViewGroup viewGroup, int i7) {
            try {
                TraceCompat.beginSection(RecyclerView.TRACE_CREATE_VIEW_TAG);
                VH vh = (VH) onCreateViewHolder(viewGroup, i7);
                if (vh.itemView.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.mItemViewType = i7;
                return vh;
            } finally {
                TraceCompat.endSection();
            }
        }

        public abstract int getItemCount();

        public long getItemId(int i7) {
            return -1L;
        }

        public int getItemViewType(int i7) {
            return 0;
        }

        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int i7) {
            this.mObservable.notifyItemRangeChanged(i7, 1);
        }

        public final void notifyItemInserted(int i7) {
            this.mObservable.notifyItemRangeInserted(i7, 1);
        }

        public final void notifyItemMoved(int i7, int i8) {
            this.mObservable.notifyItemMoved(i7, i8);
        }

        public final void notifyItemRangeChanged(int i7, int i8) {
            this.mObservable.notifyItemRangeChanged(i7, i8);
        }

        public final void notifyItemRangeInserted(int i7, int i8) {
            this.mObservable.notifyItemRangeInserted(i7, i8);
        }

        public final void notifyItemRangeRemoved(int i7, int i8) {
            this.mObservable.notifyItemRangeRemoved(i7, i8);
        }

        public final void notifyItemRemoved(int i7) {
            this.mObservable.notifyItemRangeRemoved(i7, 1);
        }

        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public abstract void onBindViewHolder(@NonNull VH vh, int i7);

        public void onBindViewHolder(@NonNull VH vh, int i7, @NonNull List<Object> list) {
            onBindViewHolder(vh, i7);
        }

        @NonNull
        public abstract VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i7);

        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public boolean onFailedToRecycleView(@NonNull VH vh) {
            return false;
        }

        public void onViewAttachedToWindow(@NonNull VH vh) {
        }

        public void onViewDetachedFromWindow(@NonNull VH vh) {
        }

        public void onViewRecycled(@NonNull VH vh) {
        }

        public void registerAdapterDataObserver(@NonNull AdapterDataObserver adapterDataObserver) {
            this.mObservable.registerObserver(adapterDataObserver);
        }

        public void setHasStableIds(boolean z6) {
            if (hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.mHasStableIds = z6;
        }

        public void unregisterAdapterDataObserver(@NonNull AdapterDataObserver adapterDataObserver) {
            this.mObservable.unregisterObserver(adapterDataObserver);
        }

        public final void notifyItemChanged(int i7, @Nullable Object obj) {
            this.mObservable.notifyItemRangeChanged(i7, 1, obj);
        }

        public final void notifyItemRangeChanged(int i7, int i8, @Nullable Object obj) {
            this.mObservable.notifyItemRangeChanged(i7, i8, obj);
        }
    }

    public static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        public boolean hasObservers() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void notifyChanged() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onChanged();
            }
        }

        public void notifyItemMoved(int i7, int i8) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeMoved(i7, i8, 1);
            }
        }

        public void notifyItemRangeChanged(int i7, int i8) {
            notifyItemRangeChanged(i7, i8, null);
        }

        public void notifyItemRangeInserted(int i7, int i8) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeInserted(i7, i8);
            }
        }

        public void notifyItemRangeRemoved(int i7, int i8) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeRemoved(i7, i8);
            }
        }

        public void notifyItemRangeChanged(int i7, int i8, @Nullable Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeChanged(i7, i8, obj);
            }
        }
    }

    public static abstract class AdapterDataObserver {
        public void onChanged() {
        }

        public void onItemRangeChanged(int i7, int i8) {
        }

        public void onItemRangeChanged(int i7, int i8, @Nullable Object obj) {
            onItemRangeChanged(i7, i8);
        }

        public void onItemRangeInserted(int i7, int i8) {
        }

        public void onItemRangeMoved(int i7, int i8, int i9) {
        }

        public void onItemRangeRemoved(int i7, int i8) {
        }
    }

    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int i7, int i8);
    }

    public static class EdgeEffectFactory {
        public static final int DIRECTION_BOTTOM = 3;
        public static final int DIRECTION_LEFT = 0;
        public static final int DIRECTION_RIGHT = 2;
        public static final int DIRECTION_TOP = 1;

        @Retention(RetentionPolicy.SOURCE)
        public @interface EdgeDirection {
        }

        @NonNull
        public EdgeEffect createEdgeEffect(@NonNull RecyclerView recyclerView, int i7) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class ItemAnimator {
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private ItemAnimatorListener mListener = null;
        private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList<>();
        private long mAddDuration = 120;
        private long mRemoveDuration = 120;
        private long mMoveDuration = 250;
        private long mChangeDuration = 250;

        @Retention(RetentionPolicy.SOURCE)
        public @interface AdapterChanges {
        }

        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        public interface ItemAnimatorListener {
            void onAnimationFinished(@NonNull ViewHolder viewHolder);
        }

        public static class ItemHolderInfo {
            public int bottom;
            public int changeFlags;
            public int left;
            public int right;
            public int top;

            @NonNull
            public ItemHolderInfo setFrom(@NonNull ViewHolder viewHolder) {
                return setFrom(viewHolder, 0);
            }

            @NonNull
            public ItemHolderInfo setFrom(@NonNull ViewHolder viewHolder, int i7) {
                View view = viewHolder.itemView;
                this.left = view.getLeft();
                this.top = view.getTop();
                this.right = view.getRight();
                this.bottom = view.getBottom();
                return this;
            }
        }

        public static int buildAdapterChangeFlagsForAnimations(ViewHolder viewHolder) {
            int i7 = viewHolder.mFlags & 14;
            if (viewHolder.isInvalid()) {
                return 4;
            }
            if ((i7 & 4) != 0) {
                return i7;
            }
            int oldPosition = viewHolder.getOldPosition();
            int adapterPosition = viewHolder.getAdapterPosition();
            return (oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition) ? i7 : i7 | 2048;
        }

        public abstract boolean animateAppearance(@NonNull ViewHolder viewHolder, @Nullable ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateChange(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateDisappearance(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @Nullable ItemHolderInfo itemHolderInfo2);

        public abstract boolean animatePersistence(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder) {
            return true;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder, @NonNull List<Object> list) {
            return canReuseUpdatedViewHolder(viewHolder);
        }

        public final void dispatchAnimationFinished(@NonNull ViewHolder viewHolder) {
            onAnimationFinished(viewHolder);
            ItemAnimatorListener itemAnimatorListener = this.mListener;
            if (itemAnimatorListener != null) {
                itemAnimatorListener.onAnimationFinished(viewHolder);
            }
        }

        public final void dispatchAnimationStarted(@NonNull ViewHolder viewHolder) {
            onAnimationStarted(viewHolder);
        }

        public final void dispatchAnimationsFinished() {
            int size = this.mFinishedListeners.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.mFinishedListeners.get(i7).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }

        public abstract void endAnimation(@NonNull ViewHolder viewHolder);

        public abstract void endAnimations();

        public long getAddDuration() {
            return this.mAddDuration;
        }

        public long getChangeDuration() {
            return this.mChangeDuration;
        }

        public long getMoveDuration() {
            return this.mMoveDuration;
        }

        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }

        public abstract boolean isRunning();

        public final boolean isRunning(@Nullable ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            boolean zIsRunning = isRunning();
            if (itemAnimatorFinishedListener != null) {
                if (zIsRunning) {
                    this.mFinishedListeners.add(itemAnimatorFinishedListener);
                } else {
                    itemAnimatorFinishedListener.onAnimationsFinished();
                }
            }
            return zIsRunning;
        }

        @NonNull
        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }

        public void onAnimationFinished(@NonNull ViewHolder viewHolder) {
        }

        public void onAnimationStarted(@NonNull ViewHolder viewHolder) {
        }

        @NonNull
        public ItemHolderInfo recordPostLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        @NonNull
        public ItemHolderInfo recordPreLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder, int i7, @NonNull List<Object> list) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        public abstract void runPendingAnimations();

        public void setAddDuration(long j7) {
            this.mAddDuration = j7;
        }

        public void setChangeDuration(long j7) {
            this.mChangeDuration = j7;
        }

        public void setListener(ItemAnimatorListener itemAnimatorListener) {
            this.mListener = itemAnimatorListener;
        }

        public void setMoveDuration(long j7) {
            this.mMoveDuration = j7;
        }

        public void setRemoveDuration(long j7) {
            this.mRemoveDuration = j7;
        }
    }

    public class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
        public ItemAnimatorRestoreListener() {
        }

        @Override // android.support.v7.widget.RecyclerView.ItemAnimator.ItemAnimatorListener
        public void onAnimationFinished(ViewHolder viewHolder) {
            viewHolder.setIsRecyclable(true);
            if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
                viewHolder.mShadowedHolder = null;
            }
            viewHolder.mShadowingHolder = null;
            if (viewHolder.shouldBeKeptAsChild() || RecyclerView.this.removeAnimatingView(viewHolder.itemView) || !viewHolder.isTmpDetached()) {
                return;
            }
            RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
        }
    }

    public static abstract class ItemDecoration {
        @Deprecated
        public void getItemOffsets(@NonNull Rect rect, int i7, @NonNull RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        @Deprecated
        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull State state) {
            onDraw(canvas, recyclerView);
        }

        @Deprecated
        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull State state) {
            onDrawOver(canvas, recyclerView);
        }

        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull State state) {
            getItemOffsets(rect, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
        }
    }

    public static abstract class LayoutManager {
        public boolean mAutoMeasure;
        public ChildHelper mChildHelper;
        private int mHeight;
        private int mHeightMode;
        public ViewBoundsCheck mHorizontalBoundCheck;
        private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback;
        public boolean mIsAttachedToWindow;
        private boolean mItemPrefetchEnabled;
        private boolean mMeasurementCacheEnabled;
        public int mPrefetchMaxCountObserved;
        public boolean mPrefetchMaxObservedInInitialPrefetch;
        public RecyclerView mRecyclerView;
        public boolean mRequestedSimpleAnimations;

        @Nullable
        public SmoothScroller mSmoothScroller;
        public ViewBoundsCheck mVerticalBoundCheck;
        private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback;
        private int mWidth;
        private int mWidthMode;

        public interface LayoutPrefetchRegistry {
            void addPosition(int i7, int i8);
        }

        public static class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }

        public LayoutManager() {
            ViewBoundsCheck.Callback callback = new ViewBoundsCheck.Callback() { // from class: android.support.v7.widget.RecyclerView.LayoutManager.1
                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public View getChildAt(int i7) {
                    return LayoutManager.this.getChildAt(i7);
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getChildCount() {
                    return LayoutManager.this.getChildCount();
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getChildEnd(View view) {
                    return LayoutManager.this.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).rightMargin;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getChildStart(View view) {
                    return LayoutManager.this.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).leftMargin;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public View getParent() {
                    return LayoutManager.this.mRecyclerView;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getParentEnd() {
                    return LayoutManager.this.getWidth() - LayoutManager.this.getPaddingRight();
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getParentStart() {
                    return LayoutManager.this.getPaddingLeft();
                }
            };
            this.mHorizontalBoundCheckCallback = callback;
            ViewBoundsCheck.Callback callback2 = new ViewBoundsCheck.Callback() { // from class: android.support.v7.widget.RecyclerView.LayoutManager.2
                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public View getChildAt(int i7) {
                    return LayoutManager.this.getChildAt(i7);
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getChildCount() {
                    return LayoutManager.this.getChildCount();
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getChildEnd(View view) {
                    return LayoutManager.this.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getChildStart(View view) {
                    return LayoutManager.this.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).topMargin;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public View getParent() {
                    return LayoutManager.this.mRecyclerView;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getParentEnd() {
                    return LayoutManager.this.getHeight() - LayoutManager.this.getPaddingBottom();
                }

                @Override // android.support.v7.widget.ViewBoundsCheck.Callback
                public int getParentStart() {
                    return LayoutManager.this.getPaddingTop();
                }
            };
            this.mVerticalBoundCheckCallback = callback2;
            this.mHorizontalBoundCheck = new ViewBoundsCheck(callback);
            this.mVerticalBoundCheck = new ViewBoundsCheck(callback2);
            this.mRequestedSimpleAnimations = false;
            this.mIsAttachedToWindow = false;
            this.mAutoMeasure = false;
            this.mMeasurementCacheEnabled = true;
            this.mItemPrefetchEnabled = true;
        }

        private void addViewInt(View view, int i7, boolean z6) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (z6 || childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.unScrap();
                } else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.mChildHelper.attachViewToParent(view, i7, view.getLayoutParams(), false);
            } else if (view.getParent() == this.mRecyclerView) {
                int iIndexOfChild = this.mChildHelper.indexOfChild(view);
                if (i7 == -1) {
                    i7 = this.mChildHelper.getChildCount();
                }
                if (iIndexOfChild == -1) {
                    StringBuilder sbM112a = C0413b.m112a("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                    sbM112a.append(this.mRecyclerView.indexOfChild(view));
                    throw new IllegalStateException(C0402a.m108a(this.mRecyclerView, sbM112a));
                }
                if (iIndexOfChild != i7) {
                    this.mRecyclerView.mLayout.moveView(iIndexOfChild, i7);
                }
            } else {
                this.mChildHelper.addView(view, i7, false);
                layoutParams.mInsetsDirty = true;
                SmoothScroller smoothScroller = this.mSmoothScroller;
                if (smoothScroller != null && smoothScroller.isRunning()) {
                    this.mSmoothScroller.onChildAttachedToWindow(view);
                }
            }
            if (layoutParams.mPendingInvalidate) {
                childViewHolderInt.itemView.invalidate();
                layoutParams.mPendingInvalidate = false;
            }
        }

        public static int chooseSize(int i7, int i8, int i9) {
            int mode = View.MeasureSpec.getMode(i7);
            int size = View.MeasureSpec.getSize(i7);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i8, i9) : size : Math.min(size, Math.max(i8, i9));
        }

        private void detachViewInternal(int i7, @NonNull View view) {
            this.mChildHelper.detachViewFromParent(i7);
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0011  */
        @java.lang.Deprecated
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int getChildMeasureSpec(int r1, int r2, int r3, boolean r4) {
            /*
                int r1 = r1 - r2
                r2 = 0
                int r1 = java.lang.Math.max(r2, r1)
                r0 = 1073741824(0x40000000, float:2.0)
                if (r4 == 0) goto Lf
                if (r3 < 0) goto Ld
                goto L11
            Ld:
                r3 = 0
                goto L21
            Lf:
                if (r3 < 0) goto L14
            L11:
                r2 = 1073741824(0x40000000, float:2.0)
                goto L21
            L14:
                r4 = -1
                if (r3 != r4) goto L1b
                r2 = 1073741824(0x40000000, float:2.0)
            L19:
                r3 = r1
                goto L21
            L1b:
                r4 = -2
                if (r3 != r4) goto Ld
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                goto L19
            L21:
                int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.LayoutManager.getChildMeasureSpec(int, int, int, boolean):int");
        }

        private int[] getChildRectangleOnScreenScrollAmount(RecyclerView recyclerView, View view, Rect rect, boolean z6) {
            int[] iArr = new int[2];
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int iWidth = rect.width() + left;
            int iHeight = rect.height() + top;
            int i7 = left - paddingLeft;
            int iMin = Math.min(0, i7);
            int i8 = top - paddingTop;
            int iMin2 = Math.min(0, i8);
            int i9 = iWidth - width;
            int iMax = Math.max(0, i9);
            int iMax2 = Math.max(0, iHeight - height);
            if (getLayoutDirection() != 1) {
                if (iMin == 0) {
                    iMin = Math.min(i7, iMax);
                }
                iMax = iMin;
            } else if (iMax == 0) {
                iMax = Math.max(iMin, i9);
            }
            if (iMin2 == 0) {
                iMin2 = Math.min(i8, iMax2);
            }
            iArr[0] = iMax;
            iArr[1] = iMin2;
            return iArr;
        }

        public static Properties getProperties(@NonNull Context context, @Nullable AttributeSet attributeSet, int i7, int i8) {
            Properties properties = new Properties();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0311R.styleable.RecyclerView, i7, i8);
            properties.orientation = typedArrayObtainStyledAttributes.getInt(C0311R.styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = typedArrayObtainStyledAttributes.getInt(C0311R.styleable.RecyclerView_spanCount, 1);
            properties.reverseLayout = typedArrayObtainStyledAttributes.getBoolean(C0311R.styleable.RecyclerView_reverseLayout, false);
            properties.stackFromEnd = typedArrayObtainStyledAttributes.getBoolean(C0311R.styleable.RecyclerView_stackFromEnd, false);
            typedArrayObtainStyledAttributes.recycle();
            return properties;
        }

        private boolean isFocusedChildVisibleAfterScrolling(RecyclerView recyclerView, int i7, int i8) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            Rect rect = this.mRecyclerView.mTempRect;
            getDecoratedBoundsWithMargins(focusedChild, rect);
            return rect.left - i7 < width && rect.right - i7 > paddingLeft && rect.top - i8 < height && rect.bottom - i8 > paddingTop;
        }

        private static boolean isMeasurementUpToDate(int i7, int i8, int i9) {
            int mode = View.MeasureSpec.getMode(i8);
            int size = View.MeasureSpec.getSize(i8);
            if (i9 > 0 && i7 != i9) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i7;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i7;
            }
            return true;
        }

        private void scrapOrRecycleView(Recycler recycler, int i7, View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.shouldIgnore()) {
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
                removeViewAt(i7);
                recycler.recycleViewHolderInternal(childViewHolderInt);
            } else {
                detachViewAt(i7);
                recycler.scrapView(view);
                this.mRecyclerView.mViewInfoStore.onViewDetached(childViewHolderInt);
            }
        }

        public void addDisappearingView(View view) {
            addDisappearingView(view, -1);
        }

        public void addView(View view) {
            addView(view, -1);
        }

        public void assertInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.assertInLayoutOrScroll(str);
            }
        }

        public void assertNotInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.assertNotInLayoutOrScroll(str);
            }
        }

        public void attachView(@NonNull View view, int i7, LayoutParams layoutParams) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            this.mChildHelper.attachViewToParent(view, i7, layoutParams, childViewHolderInt.isRemoved());
        }

        public void calculateItemDecorationsForChild(@NonNull View view, @NonNull Rect rect) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.getItemDecorInsetsForChild(view));
            }
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public boolean checkLayoutParams(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public void collectAdjacentPrefetchPositions(int i7, int i8, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void collectInitialPrefetchPositions(int i7, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public int computeHorizontalScrollExtent(@NonNull State state) {
            return 0;
        }

        public int computeHorizontalScrollOffset(@NonNull State state) {
            return 0;
        }

        public int computeHorizontalScrollRange(@NonNull State state) {
            return 0;
        }

        public int computeVerticalScrollExtent(@NonNull State state) {
            return 0;
        }

        public int computeVerticalScrollOffset(@NonNull State state) {
            return 0;
        }

        public int computeVerticalScrollRange(@NonNull State state) {
            return 0;
        }

        public void detachAndScrapAttachedViews(@NonNull Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                scrapOrRecycleView(recycler, childCount, getChildAt(childCount));
            }
        }

        public void detachAndScrapView(@NonNull View view, @NonNull Recycler recycler) {
            scrapOrRecycleView(recycler, this.mChildHelper.indexOfChild(view), view);
        }

        public void detachAndScrapViewAt(int i7, @NonNull Recycler recycler) {
            scrapOrRecycleView(recycler, i7, getChildAt(i7));
        }

        public void detachView(@NonNull View view) {
            int iIndexOfChild = this.mChildHelper.indexOfChild(view);
            if (iIndexOfChild >= 0) {
                detachViewInternal(iIndexOfChild, view);
            }
        }

        public void detachViewAt(int i7) {
            detachViewInternal(i7, getChildAt(i7));
        }

        public void dispatchAttachedToWindow(RecyclerView recyclerView) {
            this.mIsAttachedToWindow = true;
            onAttachedToWindow(recyclerView);
        }

        public void dispatchDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            this.mIsAttachedToWindow = false;
            onDetachedFromWindow(recyclerView, recycler);
        }

        public void endAnimation(View view) {
            ItemAnimator itemAnimator = this.mRecyclerView.mItemAnimator;
            if (itemAnimator != null) {
                itemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(view));
            }
        }

        @Nullable
        public View findContainingItemView(@NonNull View view) {
            View viewFindContainingItemView;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (viewFindContainingItemView = recyclerView.findContainingItemView(view)) == null || this.mChildHelper.isHidden(viewFindContainingItemView)) {
                return null;
            }
            return viewFindContainingItemView;
        }

        @Nullable
        public View findViewByPosition(int i7) {
            int childCount = getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i7 && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !childViewHolderInt.isRemoved())) {
                    return childAt;
                }
            }
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
        }

        public int getBaseline() {
            return -1;
        }

        public int getBottomDecorationHeight(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.bottom;
        }

        @Nullable
        public View getChildAt(int i7) {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildAt(i7);
            }
            return null;
        }

        public int getChildCount() {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildCount();
            }
            return 0;
        }

        public boolean getClipToPadding() {
            RecyclerView recyclerView = this.mRecyclerView;
            return recyclerView != null && recyclerView.mClipToPadding;
        }

        public int getColumnCountForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || recyclerView.mAdapter == null || !canScrollHorizontally()) {
                return 1;
            }
            return this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getDecoratedBottom(@NonNull View view) {
            return getBottomDecorationHeight(view) + view.getBottom();
        }

        public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect rect) {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
        }

        public int getDecoratedLeft(@NonNull View view) {
            return view.getLeft() - getLeftDecorationWidth(view);
        }

        public int getDecoratedMeasuredHeight(@NonNull View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int getDecoratedMeasuredWidth(@NonNull View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public int getDecoratedRight(@NonNull View view) {
            return getRightDecorationWidth(view) + view.getRight();
        }

        public int getDecoratedTop(@NonNull View view) {
            return view.getTop() - getTopDecorationHeight(view);
        }

        @Nullable
        public View getFocusedChild() {
            View focusedChild;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.mChildHelper.isHidden(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        @Px
        public int getHeight() {
            return this.mHeight;
        }

        public int getHeightMode() {
            return this.mHeightMode;
        }

        public int getItemCount() {
            RecyclerView recyclerView = this.mRecyclerView;
            Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.getItemCount();
            }
            return 0;
        }

        public int getItemViewType(@NonNull View view) {
            return RecyclerView.getChildViewHolderInt(view).getItemViewType();
        }

        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection(this.mRecyclerView);
        }

        public int getLeftDecorationWidth(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.left;
        }

        @Px
        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.mRecyclerView);
        }

        @Px
        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.mRecyclerView);
        }

        @Px
        public int getPaddingBottom() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        @Px
        public int getPaddingEnd() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return ViewCompat.getPaddingEnd(recyclerView);
            }
            return 0;
        }

        @Px
        public int getPaddingLeft() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        @Px
        public int getPaddingRight() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        @Px
        public int getPaddingStart() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return ViewCompat.getPaddingStart(recyclerView);
            }
            return 0;
        }

        @Px
        public int getPaddingTop() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int getPosition(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getRightDecorationWidth(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.right;
        }

        public int getRowCountForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || recyclerView.mAdapter == null || !canScrollVertically()) {
                return 1;
            }
            return this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getSelectionModeForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            return 0;
        }

        public int getTopDecorationHeight(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.top;
        }

        public void getTransformedBoundingBox(@NonNull View view, boolean z6, @NonNull Rect rect) {
            Matrix matrix;
            if (z6) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.mRecyclerView != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.mRecyclerView.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        @Px
        public int getWidth() {
            return this.mWidth;
        }

        public int getWidthMode() {
            return this.mWidthMode;
        }

        public boolean hasFlexibleChildInBothOrientations() {
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                ViewGroup.LayoutParams layoutParams = getChildAt(i7).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean hasFocus() {
            RecyclerView recyclerView = this.mRecyclerView;
            return recyclerView != null && recyclerView.hasFocus();
        }

        public void ignoreView(@NonNull View view) {
            ViewParent parent = view.getParent();
            RecyclerView recyclerView = this.mRecyclerView;
            if (parent != recyclerView || recyclerView.indexOfChild(view) == -1) {
                throw new IllegalArgumentException(C0402a.m108a(this.mRecyclerView, C0413b.m112a("View should be fully attached to be ignored")));
            }
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.addFlags(128);
            this.mRecyclerView.mViewInfoStore.removeViewHolder(childViewHolderInt);
        }

        public boolean isAttachedToWindow() {
            return this.mIsAttachedToWindow;
        }

        public boolean isAutoMeasureEnabled() {
            return this.mAutoMeasure;
        }

        public boolean isFocused() {
            RecyclerView recyclerView = this.mRecyclerView;
            return recyclerView != null && recyclerView.isFocused();
        }

        public final boolean isItemPrefetchEnabled() {
            return this.mItemPrefetchEnabled;
        }

        public boolean isLayoutHierarchical(@NonNull Recycler recycler, @NonNull State state) {
            return false;
        }

        public boolean isMeasurementCacheEnabled() {
            return this.mMeasurementCacheEnabled;
        }

        public boolean isSmoothScrolling() {
            SmoothScroller smoothScroller = this.mSmoothScroller;
            return smoothScroller != null && smoothScroller.isRunning();
        }

        public boolean isViewPartiallyVisible(@NonNull View view, boolean z6, boolean z7) {
            boolean z8 = this.mHorizontalBoundCheck.isViewWithinBoundFlags(view, 24579) && this.mVerticalBoundCheck.isViewWithinBoundFlags(view, 24579);
            return z6 ? z8 : !z8;
        }

        public void layoutDecorated(@NonNull View view, int i7, int i8, int i9, int i10) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            view.layout(i7 + rect.left, i8 + rect.top, i9 - rect.right, i10 - rect.bottom);
        }

        public void layoutDecoratedWithMargins(@NonNull View view, int i7, int i8, int i9, int i10) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.mDecorInsets;
            view.layout(i7 + rect.left + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i8 + rect.top + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (i9 - rect.right) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, (i10 - rect.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        }

        public void measureChild(@NonNull View view, int i7, int i8) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
            int i9 = itemDecorInsetsForChild.left + itemDecorInsetsForChild.right + i7;
            int i10 = itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom + i8;
            int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft() + i9, ((ViewGroup.MarginLayoutParams) layoutParams).width, canScrollHorizontally());
            int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop() + i10, ((ViewGroup.MarginLayoutParams) layoutParams).height, canScrollVertically());
            if (shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }

        public void measureChildWithMargins(@NonNull View view, int i7, int i8) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
            int i9 = itemDecorInsetsForChild.left + itemDecorInsetsForChild.right + i7;
            int i10 = itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom + i8;
            int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + i9, ((ViewGroup.MarginLayoutParams) layoutParams).width, canScrollHorizontally());
            int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + i10, ((ViewGroup.MarginLayoutParams) layoutParams).height, canScrollVertically());
            if (shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }

        public void moveView(int i7, int i8) {
            View childAt = getChildAt(i7);
            if (childAt != null) {
                detachViewAt(i7);
                attachView(childAt, i8);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i7 + this.mRecyclerView.toString());
            }
        }

        public void offsetChildrenHorizontal(@Px int i7) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.offsetChildrenHorizontal(i7);
            }
        }

        public void offsetChildrenVertical(@Px int i7) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.offsetChildrenVertical(i7);
            }
        }

        public void onAdapterChanged(@Nullable Adapter adapter, @Nullable Adapter adapter2) {
        }

        public boolean onAddFocusables(@NonNull RecyclerView recyclerView, @NonNull ArrayList<View> arrayList, int i7, int i8) {
            return false;
        }

        @CallSuper
        public void onAttachedToWindow(RecyclerView recyclerView) {
        }

        @Deprecated
        public void onDetachedFromWindow(RecyclerView recyclerView) {
        }

        @CallSuper
        public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            onDetachedFromWindow(recyclerView);
        }

        @Nullable
        public View onFocusSearchFailed(@NonNull View view, int i7, @NonNull Recycler recycler, @NonNull State state) {
            return null;
        }

        public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            onInitializeAccessibilityEvent(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            RecyclerView recyclerView = this.mRecyclerView;
            onInitializeAccessibilityNodeInfo(recyclerView.mRecycler, recyclerView.mState, accessibilityNodeInfoCompat);
        }

        public void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt == null || childViewHolderInt.isRemoved() || this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                return;
            }
            RecyclerView recyclerView = this.mRecyclerView;
            onInitializeAccessibilityNodeInfoForItem(recyclerView.mRecycler, recyclerView.mState, view, accessibilityNodeInfoCompat);
        }

        @Nullable
        public View onInterceptFocusSearch(@NonNull View view, int i7) {
            return null;
        }

        public void onItemsAdded(@NonNull RecyclerView recyclerView, int i7, int i8) {
        }

        public void onItemsChanged(@NonNull RecyclerView recyclerView) {
        }

        public void onItemsMoved(@NonNull RecyclerView recyclerView, int i7, int i8, int i9) {
        }

        public void onItemsRemoved(@NonNull RecyclerView recyclerView, int i7, int i8) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i7, int i8) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i7, int i8, @Nullable Object obj) {
            onItemsUpdated(recyclerView, i7, i8);
        }

        public void onLayoutChildren(Recycler recycler, State state) {
        }

        public void onLayoutCompleted(State state) {
        }

        public void onMeasure(@NonNull Recycler recycler, @NonNull State state, int i7, int i8) {
            this.mRecyclerView.defaultOnMeasure(i7, i8);
        }

        @Deprecated
        public boolean onRequestChildFocus(@NonNull RecyclerView recyclerView, @NonNull View view, @Nullable View view2) {
            return isSmoothScrolling() || recyclerView.isComputingLayout();
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        @Nullable
        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onScrollStateChanged(int i7) {
        }

        public void onSmoothScrollerStopped(SmoothScroller smoothScroller) {
            if (this.mSmoothScroller == smoothScroller) {
                this.mSmoothScroller = null;
            }
        }

        public boolean performAccessibilityAction(int i7, @Nullable Bundle bundle) {
            RecyclerView recyclerView = this.mRecyclerView;
            return performAccessibilityAction(recyclerView.mRecycler, recyclerView.mState, i7, bundle);
        }

        public boolean performAccessibilityActionForItem(@NonNull Recycler recycler, @NonNull State state, @NonNull View view, int i7, @Nullable Bundle bundle) {
            return false;
        }

        public boolean performAccessibilityActionForItem(@NonNull View view, int i7, @Nullable Bundle bundle) {
            RecyclerView recyclerView = this.mRecyclerView;
            return performAccessibilityActionForItem(recyclerView.mRecycler, recyclerView.mState, view, i7, bundle);
        }

        public void postOnAnimation(Runnable runnable) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                ViewCompat.postOnAnimation(recyclerView, runnable);
            }
        }

        public void removeAllViews() {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                this.mChildHelper.removeViewAt(childCount);
            }
        }

        public void removeAndRecycleAllViews(@NonNull Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (!RecyclerView.getChildViewHolderInt(getChildAt(childCount)).shouldIgnore()) {
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }

        public void removeAndRecycleScrapInt(Recycler recycler) {
            int scrapCount = recycler.getScrapCount();
            for (int i7 = scrapCount - 1; i7 >= 0; i7--) {
                View scrapViewAt = recycler.getScrapViewAt(i7);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(scrapViewAt);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(scrapViewAt, false);
                    }
                    ItemAnimator itemAnimator = this.mRecyclerView.mItemAnimator;
                    if (itemAnimator != null) {
                        itemAnimator.endAnimation(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    recycler.quickRecycleScrapView(scrapViewAt);
                }
            }
            recycler.clearScrap();
            if (scrapCount > 0) {
                this.mRecyclerView.invalidate();
            }
        }

        public void removeAndRecycleView(@NonNull View view, @NonNull Recycler recycler) {
            removeView(view);
            recycler.recycleView(view);
        }

        public void removeAndRecycleViewAt(int i7, @NonNull Recycler recycler) {
            View childAt = getChildAt(i7);
            removeViewAt(i7);
            recycler.recycleView(childAt);
        }

        public boolean removeCallbacks(Runnable runnable) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void removeDetachedView(@NonNull View view) {
            this.mRecyclerView.removeDetachedView(view, false);
        }

        public void removeView(View view) {
            this.mChildHelper.removeView(view);
        }

        public void removeViewAt(int i7) {
            if (getChildAt(i7) != null) {
                this.mChildHelper.removeViewAt(i7);
            }
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z6) {
            return requestChildRectangleOnScreen(recyclerView, view, rect, z6, false);
        }

        public void requestLayout() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }

        public int scrollHorizontallyBy(int i7, Recycler recycler, State state) {
            return 0;
        }

        public void scrollToPosition(int i7) {
        }

        public int scrollVerticallyBy(int i7, Recycler recycler, State state) {
            return 0;
        }

        @Deprecated
        public void setAutoMeasureEnabled(boolean z6) {
            this.mAutoMeasure = z6;
        }

        public void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
            setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public final void setItemPrefetchEnabled(boolean z6) {
            if (z6 != this.mItemPrefetchEnabled) {
                this.mItemPrefetchEnabled = z6;
                this.mPrefetchMaxCountObserved = 0;
                RecyclerView recyclerView = this.mRecyclerView;
                if (recyclerView != null) {
                    recyclerView.mRecycler.updateViewCacheSize();
                }
            }
        }

        public void setMeasureSpecs(int i7, int i8) {
            this.mWidth = View.MeasureSpec.getSize(i7);
            int mode = View.MeasureSpec.getMode(i7);
            this.mWidthMode = mode;
            if (mode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }
            this.mHeight = View.MeasureSpec.getSize(i8);
            int mode2 = View.MeasureSpec.getMode(i8);
            this.mHeightMode = mode2;
            if (mode2 != 0 || RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                return;
            }
            this.mHeight = 0;
        }

        public void setMeasuredDimension(Rect rect, int i7, int i8) {
            setMeasuredDimension(chooseSize(i7, getPaddingRight() + getPaddingLeft() + rect.width(), getMinimumWidth()), chooseSize(i8, getPaddingBottom() + getPaddingTop() + rect.height(), getMinimumHeight()));
        }

        public void setMeasuredDimensionFromChildren(int i7, int i8) {
            int childCount = getChildCount();
            if (childCount == 0) {
                this.mRecyclerView.defaultOnMeasure(i7, i8);
                return;
            }
            int i9 = Integer.MIN_VALUE;
            int i10 = Integer.MIN_VALUE;
            int i11 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            int i12 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            for (int i13 = 0; i13 < childCount; i13++) {
                View childAt = getChildAt(i13);
                Rect rect = this.mRecyclerView.mTempRect;
                getDecoratedBoundsWithMargins(childAt, rect);
                int i14 = rect.left;
                if (i14 < i11) {
                    i11 = i14;
                }
                int i15 = rect.right;
                if (i15 > i9) {
                    i9 = i15;
                }
                int i16 = rect.top;
                if (i16 < i12) {
                    i12 = i16;
                }
                int i17 = rect.bottom;
                if (i17 > i10) {
                    i10 = i17;
                }
            }
            this.mRecyclerView.mTempRect.set(i11, i12, i9, i10);
            setMeasuredDimension(this.mRecyclerView.mTempRect, i7, i8);
        }

        public void setMeasurementCacheEnabled(boolean z6) {
            this.mMeasurementCacheEnabled = z6;
        }

        public void setRecyclerView(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            } else {
                this.mRecyclerView = recyclerView;
                this.mChildHelper = recyclerView.mChildHelper;
                this.mWidth = recyclerView.getWidth();
                this.mHeight = recyclerView.getHeight();
            }
            this.mWidthMode = 1073741824;
            this.mHeightMode = 1073741824;
        }

        public boolean shouldMeasureChild(View view, int i7, int i8, LayoutParams layoutParams) {
            return (!view.isLayoutRequested() && this.mMeasurementCacheEnabled && isMeasurementUpToDate(view.getWidth(), i7, ((ViewGroup.MarginLayoutParams) layoutParams).width) && isMeasurementUpToDate(view.getHeight(), i8, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public boolean shouldMeasureTwice() {
            return false;
        }

        public boolean shouldReMeasureChild(View view, int i7, int i8, LayoutParams layoutParams) {
            return (this.mMeasurementCacheEnabled && isMeasurementUpToDate(view.getMeasuredWidth(), i7, ((ViewGroup.MarginLayoutParams) layoutParams).width) && isMeasurementUpToDate(view.getMeasuredHeight(), i8, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i7) {
        }

        public void startSmoothScroll(SmoothScroller smoothScroller) {
            SmoothScroller smoothScroller2 = this.mSmoothScroller;
            if (smoothScroller2 != null && smoothScroller != smoothScroller2 && smoothScroller2.isRunning()) {
                this.mSmoothScroller.stop();
            }
            this.mSmoothScroller = smoothScroller;
            smoothScroller.start(this.mRecyclerView, this);
        }

        public void stopIgnoringView(@NonNull View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.stopIgnoring();
            childViewHolderInt.resetInternal();
            childViewHolderInt.addFlags(4);
        }

        public void stopSmoothScroller() {
            SmoothScroller smoothScroller = this.mSmoothScroller;
            if (smoothScroller != null) {
                smoothScroller.stop();
            }
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public void addDisappearingView(View view, int i7) {
            addViewInt(view, i7, true);
        }

        public void addView(View view, int i7) {
            addViewInt(view, i7, false);
        }

        public void onInitializeAccessibilityEvent(@NonNull Recycler recycler, @NonNull State state, @NonNull AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z6 = true;
            if (!recyclerView.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
                z6 = false;
            }
            accessibilityEvent.setScrollable(z6);
            Adapter adapter = this.mRecyclerView.mAdapter;
            if (adapter != null) {
                accessibilityEvent.setItemCount(adapter.getItemCount());
            }
        }

        public void onInitializeAccessibilityNodeInfo(@NonNull Recycler recycler, @NonNull State state, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        }

        public boolean onRequestChildFocus(@NonNull RecyclerView recyclerView, @NonNull State state, @NonNull View view, @Nullable View view2) {
            return onRequestChildFocus(recyclerView, view, view2);
        }

        public boolean performAccessibilityAction(@NonNull Recycler recycler, @NonNull State state, int i7, @Nullable Bundle bundle) {
            int height;
            int width;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null) {
                return false;
            }
            if (i7 != 4096) {
                if (i7 != 8192) {
                    height = 0;
                } else {
                    height = recyclerView.canScrollVertically(-1) ? -((getHeight() - getPaddingTop()) - getPaddingBottom()) : 0;
                    if (this.mRecyclerView.canScrollHorizontally(-1)) {
                        width = -((getWidth() - getPaddingLeft()) - getPaddingRight());
                    }
                }
            } else {
                height = recyclerView.canScrollVertically(1) ? (getHeight() - getPaddingTop()) - getPaddingBottom() : 0;
                width = this.mRecyclerView.canScrollHorizontally(1) ? (getWidth() - getPaddingLeft()) - getPaddingRight() : 0;
            }
            if (height == 0 && width == 0) {
                return false;
            }
            this.mRecyclerView.smoothScrollBy(width, height);
            return true;
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z6, boolean z7) {
            int[] childRectangleOnScreenScrollAmount = getChildRectangleOnScreenScrollAmount(recyclerView, view, rect, z6);
            int i7 = childRectangleOnScreenScrollAmount[0];
            int i8 = childRectangleOnScreenScrollAmount[1];
            if ((z7 && !isFocusedChildVisibleAfterScrolling(recyclerView, i7, i8)) || (i7 == 0 && i8 == 0)) {
                return false;
            }
            if (z6) {
                recyclerView.scrollBy(i7, i8);
            } else {
                recyclerView.smoothScrollBy(i7, i8);
            }
            return true;
        }

        public static int getChildMeasureSpec(int i7, int i8, int i9, int i10, boolean z6) {
            int iMax = Math.max(0, i7 - i9);
            if (z6) {
                if (i10 < 0) {
                    if (i10 != -1 || (i8 != Integer.MIN_VALUE && (i8 == 0 || i8 != 1073741824))) {
                        i8 = 0;
                        i10 = 0;
                    } else {
                        i10 = iMax;
                    }
                }
                i8 = 1073741824;
            } else {
                if (i10 < 0) {
                    if (i10 != -1) {
                        if (i10 == -2) {
                            i8 = (i8 == Integer.MIN_VALUE || i8 == 1073741824) ? Integer.MIN_VALUE : 0;
                        }
                        i8 = 0;
                        i10 = 0;
                    }
                    i10 = iMax;
                }
                i8 = 1073741824;
            }
            return View.MeasureSpec.makeMeasureSpec(i10, i8);
        }

        public void onInitializeAccessibilityNodeInfoForItem(@NonNull Recycler recycler, @NonNull State state, @NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(canScrollVertically() ? getPosition(view) : 0, 1, canScrollHorizontally() ? getPosition(view) : 0, 1, false, false));
        }

        public void attachView(@NonNull View view, int i7) {
            attachView(view, i7, (LayoutParams) view.getLayoutParams());
        }

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public void setMeasuredDimension(int i7, int i8) {
            this.mRecyclerView.setMeasuredDimension(i7, i8);
        }

        public void attachView(@NonNull View view) {
            attachView(view, -1);
        }
    }

    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(@NonNull View view);

        void onChildViewDetachedFromWindow(@NonNull View view);
    }

    public static abstract class OnFlingListener {
        public abstract boolean onFling(int i7, int i8);
    }

    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z6);

        void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);
    }

    public static abstract class OnScrollListener {
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i7) {
        }

        public void onScrolled(@NonNull RecyclerView recyclerView, int i7, int i8) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface Orientation {
    }

    public static class RecycledViewPool {
        private static final int DEFAULT_MAX_SCRAP = 5;
        public SparseArray<ScrapData> mScrap = new SparseArray<>();
        private int mAttachCount = 0;

        public static class ScrapData {
            public final ArrayList<ViewHolder> mScrapHeap = new ArrayList<>();
            public int mMaxScrap = 5;
            public long mCreateRunningAverageNs = 0;
            public long mBindRunningAverageNs = 0;
        }

        private ScrapData getScrapDataForType(int i7) {
            ScrapData scrapData = this.mScrap.get(i7);
            if (scrapData != null) {
                return scrapData;
            }
            ScrapData scrapData2 = new ScrapData();
            this.mScrap.put(i7, scrapData2);
            return scrapData2;
        }

        public void attach() {
            this.mAttachCount++;
        }

        public void clear() {
            for (int i7 = 0; i7 < this.mScrap.size(); i7++) {
                this.mScrap.valueAt(i7).mScrapHeap.clear();
            }
        }

        public void detach() {
            this.mAttachCount--;
        }

        public void factorInBindTime(int i7, long j7) {
            ScrapData scrapDataForType = getScrapDataForType(i7);
            scrapDataForType.mBindRunningAverageNs = runningAverage(scrapDataForType.mBindRunningAverageNs, j7);
        }

        public void factorInCreateTime(int i7, long j7) {
            ScrapData scrapDataForType = getScrapDataForType(i7);
            scrapDataForType.mCreateRunningAverageNs = runningAverage(scrapDataForType.mCreateRunningAverageNs, j7);
        }

        @Nullable
        public ViewHolder getRecycledView(int i7) {
            ScrapData scrapData = this.mScrap.get(i7);
            if (scrapData == null || scrapData.mScrapHeap.isEmpty()) {
                return null;
            }
            return scrapData.mScrapHeap.remove(r2.size() - 1);
        }

        public int getRecycledViewCount(int i7) {
            return getScrapDataForType(i7).mScrapHeap.size();
        }

        public void onAdapterChanged(Adapter adapter, Adapter adapter2, boolean z6) {
            if (adapter != null) {
                detach();
            }
            if (!z6 && this.mAttachCount == 0) {
                clear();
            }
            if (adapter2 != null) {
                attach();
            }
        }

        public void putRecycledView(ViewHolder viewHolder) {
            int itemViewType = viewHolder.getItemViewType();
            ArrayList<ViewHolder> arrayList = getScrapDataForType(itemViewType).mScrapHeap;
            if (this.mScrap.get(itemViewType).mMaxScrap <= arrayList.size()) {
                return;
            }
            viewHolder.resetInternal();
            arrayList.add(viewHolder);
        }

        public long runningAverage(long j7, long j8) {
            if (j7 == 0) {
                return j8;
            }
            return (j8 / 4) + ((j7 / 4) * 3);
        }

        public void setMaxRecycledViews(int i7, int i8) {
            ScrapData scrapDataForType = getScrapDataForType(i7);
            scrapDataForType.mMaxScrap = i8;
            ArrayList<ViewHolder> arrayList = scrapDataForType.mScrapHeap;
            while (arrayList.size() > i8) {
                arrayList.remove(arrayList.size() - 1);
            }
        }

        public int size() {
            int size = 0;
            for (int i7 = 0; i7 < this.mScrap.size(); i7++) {
                ArrayList<ViewHolder> arrayList = this.mScrap.valueAt(i7).mScrapHeap;
                if (arrayList != null) {
                    size = arrayList.size() + size;
                }
            }
            return size;
        }

        public boolean willBindInTime(int i7, long j7, long j8) {
            long j9 = getScrapDataForType(i7).mBindRunningAverageNs;
            return j9 == 0 || j7 + j9 < j8;
        }

        public boolean willCreateInTime(int i7, long j7, long j8) {
            long j9 = getScrapDataForType(i7).mCreateRunningAverageNs;
            return j9 == 0 || j7 + j9 < j8;
        }
    }

    public final class Recycler {
        public static final int DEFAULT_CACHE_SIZE = 2;
        public final ArrayList<ViewHolder> mAttachedScrap;
        public final ArrayList<ViewHolder> mCachedViews;
        public ArrayList<ViewHolder> mChangedScrap;
        public RecycledViewPool mRecyclerPool;
        private int mRequestedCacheMax;
        private final List<ViewHolder> mUnmodifiableAttachedScrap;
        private ViewCacheExtension mViewCacheExtension;
        public int mViewCacheMax;

        public Recycler() {
            ArrayList<ViewHolder> arrayList = new ArrayList<>();
            this.mAttachedScrap = arrayList;
            this.mChangedScrap = null;
            this.mCachedViews = new ArrayList<>();
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(arrayList);
            this.mRequestedCacheMax = 2;
            this.mViewCacheMax = 2;
        }

        private void attachAccessibilityDelegateOnBind(ViewHolder viewHolder) {
            if (RecyclerView.this.isAccessibilityEnabled()) {
                View view = viewHolder.itemView;
                if (ViewCompat.getImportantForAccessibility(view) == 0) {
                    ViewCompat.setImportantForAccessibility(view, 1);
                }
                if (ViewCompat.hasAccessibilityDelegate(view)) {
                    return;
                }
                viewHolder.addFlags(16384);
                ViewCompat.setAccessibilityDelegate(view, RecyclerView.this.mAccessibilityDelegate.getItemDelegate());
            }
        }

        private void invalidateDisplayListInt(ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (view instanceof ViewGroup) {
                invalidateDisplayListInt((ViewGroup) view, false);
            }
        }

        private boolean tryBindViewHolderByDeadline(@NonNull ViewHolder viewHolder, int i7, int i8, long j7) {
            viewHolder.mOwnerRecyclerView = RecyclerView.this;
            int itemViewType = viewHolder.getItemViewType();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j7 != RecyclerView.FOREVER_NS && !this.mRecyclerPool.willBindInTime(itemViewType, nanoTime, j7)) {
                return false;
            }
            RecyclerView.this.mAdapter.bindViewHolder(viewHolder, i7);
            this.mRecyclerPool.factorInBindTime(viewHolder.getItemViewType(), RecyclerView.this.getNanoTime() - nanoTime);
            attachAccessibilityDelegateOnBind(viewHolder);
            if (!RecyclerView.this.mState.isPreLayout()) {
                return true;
            }
            viewHolder.mPreLayoutPosition = i8;
            return true;
        }

        public void addViewHolderToRecycledViewPool(@NonNull ViewHolder viewHolder, boolean z6) {
            RecyclerView.clearNestedRecyclerViewIfNotNested(viewHolder);
            if (viewHolder.hasAnyOfTheFlags(16384)) {
                viewHolder.setFlags(0, 16384);
                ViewCompat.setAccessibilityDelegate(viewHolder.itemView, null);
            }
            if (z6) {
                dispatchViewRecycled(viewHolder);
            }
            viewHolder.mOwnerRecyclerView = null;
            getRecycledViewPool().putRecycledView(viewHolder);
        }

        public void bindViewToPosition(@NonNull View view, int i7) {
            LayoutParams layoutParams;
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt == null) {
                throw new IllegalArgumentException(C0402a.m108a(RecyclerView.this, C0413b.m112a("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter")));
            }
            int iFindPositionOffset = RecyclerView.this.mAdapterHelper.findPositionOffset(i7);
            if (iFindPositionOffset < 0 || iFindPositionOffset >= RecyclerView.this.mAdapter.getItemCount()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Inconsistency detected. Invalid item position ");
                sb.append(i7);
                sb.append("(offset:");
                sb.append(iFindPositionOffset);
                sb.append(").");
                sb.append("state:");
                sb.append(RecyclerView.this.mState.getItemCount());
                throw new IndexOutOfBoundsException(C0402a.m108a(RecyclerView.this, sb));
            }
            tryBindViewHolderByDeadline(childViewHolderInt, iFindPositionOffset, i7, RecyclerView.FOREVER_NS);
            ViewGroup.LayoutParams layoutParams2 = childViewHolderInt.itemView.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams = (LayoutParams) RecyclerView.this.generateDefaultLayoutParams();
                childViewHolderInt.itemView.setLayoutParams(layoutParams);
            } else if (RecyclerView.this.checkLayoutParams(layoutParams2)) {
                layoutParams = (LayoutParams) layoutParams2;
            } else {
                layoutParams = (LayoutParams) RecyclerView.this.generateLayoutParams(layoutParams2);
                childViewHolderInt.itemView.setLayoutParams(layoutParams);
            }
            layoutParams.mInsetsDirty = true;
            layoutParams.mViewHolder = childViewHolderInt;
            layoutParams.mPendingInvalidate = childViewHolderInt.itemView.getParent() == null;
        }

        public void clear() {
            this.mAttachedScrap.clear();
            recycleAndClearCachedViews();
        }

        public void clearOldPositions() {
            int size = this.mCachedViews.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.mCachedViews.get(i7).clearOldPosition();
            }
            int size2 = this.mAttachedScrap.size();
            for (int i8 = 0; i8 < size2; i8++) {
                this.mAttachedScrap.get(i8).clearOldPosition();
            }
            ArrayList<ViewHolder> arrayList = this.mChangedScrap;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i9 = 0; i9 < size3; i9++) {
                    this.mChangedScrap.get(i9).clearOldPosition();
                }
            }
        }

        public void clearScrap() {
            this.mAttachedScrap.clear();
            ArrayList<ViewHolder> arrayList = this.mChangedScrap;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int convertPreLayoutPositionToPostLayout(int i7) {
            if (i7 >= 0 && i7 < RecyclerView.this.mState.getItemCount()) {
                return !RecyclerView.this.mState.isPreLayout() ? i7 : RecyclerView.this.mAdapterHelper.findPositionOffset(i7);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("invalid position ");
            sb.append(i7);
            sb.append(". State ");
            sb.append("item count is ");
            sb.append(RecyclerView.this.mState.getItemCount());
            throw new IndexOutOfBoundsException(C0402a.m108a(RecyclerView.this, sb));
        }

        public void dispatchViewRecycled(@NonNull ViewHolder viewHolder) {
            RecyclerListener recyclerListener = RecyclerView.this.mRecyclerListener;
            if (recyclerListener != null) {
                recyclerListener.onViewRecycled(viewHolder);
            }
            Adapter adapter = RecyclerView.this.mAdapter;
            if (adapter != null) {
                adapter.onViewRecycled(viewHolder);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mState != null) {
                recyclerView.mViewInfoStore.removeViewHolder(viewHolder);
            }
        }

        public ViewHolder getChangedScrapViewForPosition(int i7) {
            int size;
            int iFindPositionOffset;
            ArrayList<ViewHolder> arrayList = this.mChangedScrap;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i8 = 0; i8 < size; i8++) {
                    ViewHolder viewHolder = this.mChangedScrap.get(i8);
                    if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == i7) {
                        viewHolder.addFlags(32);
                        return viewHolder;
                    }
                }
                if (RecyclerView.this.mAdapter.hasStableIds() && (iFindPositionOffset = RecyclerView.this.mAdapterHelper.findPositionOffset(i7)) > 0 && iFindPositionOffset < RecyclerView.this.mAdapter.getItemCount()) {
                    long itemId = RecyclerView.this.mAdapter.getItemId(iFindPositionOffset);
                    for (int i9 = 0; i9 < size; i9++) {
                        ViewHolder viewHolder2 = this.mChangedScrap.get(i9);
                        if (!viewHolder2.wasReturnedFromScrap() && viewHolder2.getItemId() == itemId) {
                            viewHolder2.addFlags(32);
                            return viewHolder2;
                        }
                    }
                }
            }
            return null;
        }

        public RecycledViewPool getRecycledViewPool() {
            if (this.mRecyclerPool == null) {
                this.mRecyclerPool = new RecycledViewPool();
            }
            return this.mRecyclerPool;
        }

        public int getScrapCount() {
            return this.mAttachedScrap.size();
        }

        @NonNull
        public List<ViewHolder> getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }

        public ViewHolder getScrapOrCachedViewForId(long j7, int i7, boolean z6) {
            for (int size = this.mAttachedScrap.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = this.mAttachedScrap.get(size);
                if (viewHolder.getItemId() == j7 && !viewHolder.wasReturnedFromScrap()) {
                    if (i7 == viewHolder.getItemViewType()) {
                        viewHolder.addFlags(32);
                        if (viewHolder.isRemoved() && !RecyclerView.this.mState.isPreLayout()) {
                            viewHolder.setFlags(2, 14);
                        }
                        return viewHolder;
                    }
                    if (!z6) {
                        this.mAttachedScrap.remove(size);
                        RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
                        quickRecycleScrapView(viewHolder.itemView);
                    }
                }
            }
            int size2 = this.mCachedViews.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                ViewHolder viewHolder2 = this.mCachedViews.get(size2);
                if (viewHolder2.getItemId() == j7) {
                    if (i7 == viewHolder2.getItemViewType()) {
                        if (!z6) {
                            this.mCachedViews.remove(size2);
                        }
                        return viewHolder2;
                    }
                    if (!z6) {
                        recycleCachedViewAt(size2);
                        return null;
                    }
                }
            }
        }

        public ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int i7, boolean z6) {
            View viewFindHiddenNonRemovedView;
            int size = this.mAttachedScrap.size();
            for (int i8 = 0; i8 < size; i8++) {
                ViewHolder viewHolder = this.mAttachedScrap.get(i8);
                if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == i7 && !viewHolder.isInvalid() && (RecyclerView.this.mState.mInPreLayout || !viewHolder.isRemoved())) {
                    viewHolder.addFlags(32);
                    return viewHolder;
                }
            }
            if (z6 || (viewFindHiddenNonRemovedView = RecyclerView.this.mChildHelper.findHiddenNonRemovedView(i7)) == null) {
                int size2 = this.mCachedViews.size();
                for (int i9 = 0; i9 < size2; i9++) {
                    ViewHolder viewHolder2 = this.mCachedViews.get(i9);
                    if (!viewHolder2.isInvalid() && viewHolder2.getLayoutPosition() == i7) {
                        if (!z6) {
                            this.mCachedViews.remove(i9);
                        }
                        return viewHolder2;
                    }
                }
                return null;
            }
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(viewFindHiddenNonRemovedView);
            RecyclerView.this.mChildHelper.unhide(viewFindHiddenNonRemovedView);
            int iIndexOfChild = RecyclerView.this.mChildHelper.indexOfChild(viewFindHiddenNonRemovedView);
            if (iIndexOfChild != -1) {
                RecyclerView.this.mChildHelper.detachViewFromParent(iIndexOfChild);
                scrapView(viewFindHiddenNonRemovedView);
                childViewHolderInt.addFlags(8224);
                return childViewHolderInt;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("layout index should not be -1 after unhiding a view:");
            sb.append(childViewHolderInt);
            throw new IllegalStateException(C0402a.m108a(RecyclerView.this, sb));
        }

        public View getScrapViewAt(int i7) {
            return this.mAttachedScrap.get(i7).itemView;
        }

        @NonNull
        public View getViewForPosition(int i7) {
            return getViewForPosition(i7, false);
        }

        public void markItemDecorInsetsDirty() {
            int size = this.mCachedViews.size();
            for (int i7 = 0; i7 < size; i7++) {
                LayoutParams layoutParams = (LayoutParams) this.mCachedViews.get(i7).itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.mInsetsDirty = true;
                }
            }
        }

        public void markKnownViewsInvalid() {
            int size = this.mCachedViews.size();
            for (int i7 = 0; i7 < size; i7++) {
                ViewHolder viewHolder = this.mCachedViews.get(i7);
                if (viewHolder != null) {
                    viewHolder.addFlags(6);
                    viewHolder.addChangePayload(null);
                }
            }
            Adapter adapter = RecyclerView.this.mAdapter;
            if (adapter == null || !adapter.hasStableIds()) {
                recycleAndClearCachedViews();
            }
        }

        public void offsetPositionRecordsForInsert(int i7, int i8) {
            int size = this.mCachedViews.size();
            for (int i9 = 0; i9 < size; i9++) {
                ViewHolder viewHolder = this.mCachedViews.get(i9);
                if (viewHolder != null && viewHolder.mPosition >= i7) {
                    viewHolder.offsetPosition(i8, true);
                }
            }
        }

        public void offsetPositionRecordsForMove(int i7, int i8) {
            int i9;
            int i10;
            int i11;
            int i12;
            if (i7 < i8) {
                i9 = -1;
                i11 = i7;
                i10 = i8;
            } else {
                i9 = 1;
                i10 = i7;
                i11 = i8;
            }
            int size = this.mCachedViews.size();
            for (int i13 = 0; i13 < size; i13++) {
                ViewHolder viewHolder = this.mCachedViews.get(i13);
                if (viewHolder != null && (i12 = viewHolder.mPosition) >= i11 && i12 <= i10) {
                    if (i12 == i7) {
                        viewHolder.offsetPosition(i8 - i7, false);
                    } else {
                        viewHolder.offsetPosition(i9, false);
                    }
                }
            }
        }

        public void offsetPositionRecordsForRemove(int i7, int i8, boolean z6) {
            int i9 = i7 + i8;
            for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = this.mCachedViews.get(size);
                if (viewHolder != null) {
                    int i10 = viewHolder.mPosition;
                    if (i10 >= i9) {
                        viewHolder.offsetPosition(-i8, z6);
                    } else if (i10 >= i7) {
                        viewHolder.addFlags(8);
                        recycleCachedViewAt(size);
                    }
                }
            }
        }

        public void onAdapterChanged(Adapter adapter, Adapter adapter2, boolean z6) {
            clear();
            getRecycledViewPool().onAdapterChanged(adapter, adapter2, z6);
        }

        public void quickRecycleScrapView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.mScrapContainer = null;
            childViewHolderInt.mInChangeScrap = false;
            childViewHolderInt.clearReturnedFromScrapFlag();
            recycleViewHolderInternal(childViewHolderInt);
        }

        public void recycleAndClearCachedViews() {
            for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
                recycleCachedViewAt(size);
            }
            this.mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions();
            }
        }

        public void recycleCachedViewAt(int i7) {
            addViewHolderToRecycledViewPool(this.mCachedViews.get(i7), true);
            this.mCachedViews.remove(i7);
        }

        public void recycleView(@NonNull View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            recycleViewHolderInternal(childViewHolderInt);
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x0091 A[PHI: r1
          0x0091: PHI (r1v19 boolean) = (r1v6 boolean), (r1v0 boolean) binds: [B:41:0x008b, B:20:0x0039] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void recycleViewHolderInternal(android.support.v7.widget.RecyclerView.ViewHolder r6) {
            /*
                Method dump skipped, instructions count: 248
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.Recycler.recycleViewHolderInternal(android.support.v7.widget.RecyclerView$ViewHolder):void");
        }

        public void recycleViewInternal(View view) {
            recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(view));
        }

        public void scrapView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.hasAnyOfTheFlags(12) && childViewHolderInt.isUpdated() && !RecyclerView.this.canReuseUpdatedViewHolder(childViewHolderInt)) {
                if (this.mChangedScrap == null) {
                    this.mChangedScrap = new ArrayList<>();
                }
                childViewHolderInt.setScrapContainer(this, true);
                this.mChangedScrap.add(childViewHolderInt);
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !RecyclerView.this.mAdapter.hasStableIds()) {
                throw new IllegalArgumentException(C0402a.m108a(RecyclerView.this, C0413b.m112a("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.")));
            }
            childViewHolderInt.setScrapContainer(this, false);
            this.mAttachedScrap.add(childViewHolderInt);
        }

        public void setRecycledViewPool(RecycledViewPool recycledViewPool) {
            RecycledViewPool recycledViewPool2 = this.mRecyclerPool;
            if (recycledViewPool2 != null) {
                recycledViewPool2.detach();
            }
            this.mRecyclerPool = recycledViewPool;
            if (recycledViewPool == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.mRecyclerPool.attach();
        }

        public void setViewCacheExtension(ViewCacheExtension viewCacheExtension) {
            this.mViewCacheExtension = viewCacheExtension;
        }

        public void setViewCacheSize(int i7) {
            this.mRequestedCacheMax = i7;
            updateViewCacheSize();
        }

        /* JADX WARN: Removed duplicated region for block: B:100:0x01f5  */
        /* JADX WARN: Removed duplicated region for block: B:106:0x0211 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:108:0x0214  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x016a A[PHI: r1 r5
          0x016a: PHI (r1v12 android.support.v7.widget.RecyclerView$ViewHolder) = (r1v11 android.support.v7.widget.RecyclerView$ViewHolder), (r1v31 android.support.v7.widget.RecyclerView$ViewHolder) binds: [B:28:0x005d, B:59:0x00ed] A[DONT_GENERATE, DONT_INLINE]
          0x016a: PHI (r5v3 boolean) = (r5v2 boolean), (r5v5 boolean) binds: [B:28:0x005d, B:59:0x00ed] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x018b  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x01b7  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01e7  */
        @android.support.annotation.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.support.v7.widget.RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instructions count: 585
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.Recycler.tryGetViewHolderForPositionByDeadline(int, boolean, long):android.support.v7.widget.RecyclerView$ViewHolder");
        }

        public void unscrapView(ViewHolder viewHolder) {
            if (viewHolder.mInChangeScrap) {
                this.mChangedScrap.remove(viewHolder);
            } else {
                this.mAttachedScrap.remove(viewHolder);
            }
            viewHolder.mScrapContainer = null;
            viewHolder.mInChangeScrap = false;
            viewHolder.clearReturnedFromScrapFlag();
        }

        public void updateViewCacheSize() {
            LayoutManager layoutManager = RecyclerView.this.mLayout;
            this.mViewCacheMax = this.mRequestedCacheMax + (layoutManager != null ? layoutManager.mPrefetchMaxCountObserved : 0);
            for (int size = this.mCachedViews.size() - 1; size >= 0 && this.mCachedViews.size() > this.mViewCacheMax; size--) {
                recycleCachedViewAt(size);
            }
        }

        public boolean validateViewHolderForOffsetPosition(ViewHolder viewHolder) {
            if (viewHolder.isRemoved()) {
                return RecyclerView.this.mState.isPreLayout();
            }
            int i7 = viewHolder.mPosition;
            if (i7 < 0 || i7 >= RecyclerView.this.mAdapter.getItemCount()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Inconsistency detected. Invalid view holder adapter position");
                sb.append(viewHolder);
                throw new IndexOutOfBoundsException(C0402a.m108a(RecyclerView.this, sb));
            }
            if (RecyclerView.this.mState.isPreLayout() || RecyclerView.this.mAdapter.getItemViewType(viewHolder.mPosition) == viewHolder.getItemViewType()) {
                return !RecyclerView.this.mAdapter.hasStableIds() || viewHolder.getItemId() == RecyclerView.this.mAdapter.getItemId(viewHolder.mPosition);
            }
            return false;
        }

        public void viewRangeUpdate(int i7, int i8) {
            int i9;
            int i10 = i8 + i7;
            for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = this.mCachedViews.get(size);
                if (viewHolder != null && (i9 = viewHolder.mPosition) >= i7 && i9 < i10) {
                    viewHolder.addFlags(2);
                    recycleCachedViewAt(size);
                }
            }
        }

        public View getViewForPosition(int i7, boolean z6) {
            return tryGetViewHolderForPositionByDeadline(i7, z6, RecyclerView.FOREVER_NS).itemView;
        }

        private void invalidateDisplayListInt(ViewGroup viewGroup, boolean z6) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    invalidateDisplayListInt((ViewGroup) childAt, true);
                }
            }
            if (z6) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                } else {
                    int visibility = viewGroup.getVisibility();
                    viewGroup.setVisibility(4);
                    viewGroup.setVisibility(visibility);
                }
            }
        }
    }

    public interface RecyclerListener {
        void onViewRecycled(@NonNull ViewHolder viewHolder);
    }

    public class RecyclerViewDataObserver extends AdapterDataObserver {
        public RecyclerViewDataObserver() {
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mState.mStructureChanged = true;
            recyclerView.processDataSetCompletelyChanged(true);
            if (RecyclerView.this.mAdapterHelper.hasPendingUpdates()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i7, int i8, Object obj) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeChanged(i7, i8, obj)) {
                triggerUpdateProcessor();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i7, int i8) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeInserted(i7, i8)) {
                triggerUpdateProcessor();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i7, int i8, int i9) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeMoved(i7, i8, i9)) {
                triggerUpdateProcessor();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i7, int i8) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeRemoved(i7, i8)) {
                triggerUpdateProcessor();
            }
        }

        public void triggerUpdateProcessor() {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mHasFixedSize && recyclerView.mIsAttached) {
                    ViewCompat.postOnAnimation(recyclerView, recyclerView.mUpdateChildViewsRunnable);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.mAdapterUpdateDuringMeasure = true;
            recyclerView2.requestLayout();
        }
    }

    public static class SimpleOnItemTouchListener implements OnItemTouchListener {
        @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z6) {
        }

        @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        }
    }

    public static abstract class SmoothScroller {
        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private boolean mRunning;
        private boolean mStarted;
        private View mTargetView;
        private int mTargetPosition = -1;
        private final Action mRecyclingAction = new Action(0, 0);

        public static class Action {
            public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
            private boolean mChanged;
            private int mConsecutiveUpdates;
            private int mDuration;
            private int mDx;
            private int mDy;
            private Interpolator mInterpolator;
            private int mJumpToPosition;

            public Action(@Px int i7, @Px int i8) {
                this(i7, i8, Integer.MIN_VALUE, null);
            }

            private void validate() {
                if (this.mInterpolator != null && this.mDuration < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.mDuration < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public int getDuration() {
                return this.mDuration;
            }

            @Px
            public int getDx() {
                return this.mDx;
            }

            @Px
            public int getDy() {
                return this.mDy;
            }

            @Nullable
            public Interpolator getInterpolator() {
                return this.mInterpolator;
            }

            public boolean hasJumpTarget() {
                return this.mJumpToPosition >= 0;
            }

            public void jumpTo(int i7) {
                this.mJumpToPosition = i7;
            }

            public void runIfNecessary(RecyclerView recyclerView) {
                int i7 = this.mJumpToPosition;
                if (i7 >= 0) {
                    this.mJumpToPosition = -1;
                    recyclerView.jumpToPositionForSmoothScroller(i7);
                    this.mChanged = false;
                } else {
                    if (!this.mChanged) {
                        this.mConsecutiveUpdates = 0;
                        return;
                    }
                    validate();
                    Interpolator interpolator = this.mInterpolator;
                    if (interpolator == null) {
                        int i8 = this.mDuration;
                        if (i8 == Integer.MIN_VALUE) {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
                        } else {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, i8);
                        }
                    } else {
                        recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, interpolator);
                    }
                    this.mConsecutiveUpdates++;
                    this.mChanged = false;
                }
            }

            public void setDuration(int i7) {
                this.mChanged = true;
                this.mDuration = i7;
            }

            public void setDx(@Px int i7) {
                this.mChanged = true;
                this.mDx = i7;
            }

            public void setDy(@Px int i7) {
                this.mChanged = true;
                this.mDy = i7;
            }

            public void setInterpolator(@Nullable Interpolator interpolator) {
                this.mChanged = true;
                this.mInterpolator = interpolator;
            }

            public void update(@Px int i7, @Px int i8, int i9, @Nullable Interpolator interpolator) {
                this.mDx = i7;
                this.mDy = i8;
                this.mDuration = i9;
                this.mInterpolator = interpolator;
                this.mChanged = true;
            }

            public Action(@Px int i7, @Px int i8, int i9) {
                this(i7, i8, i9, null);
            }

            public Action(@Px int i7, @Px int i8, int i9, @Nullable Interpolator interpolator) {
                this.mJumpToPosition = -1;
                this.mChanged = false;
                this.mConsecutiveUpdates = 0;
                this.mDx = i7;
                this.mDy = i8;
                this.mDuration = i9;
                this.mInterpolator = interpolator;
            }
        }

        public interface ScrollVectorProvider {
            @Nullable
            PointF computeScrollVectorForPosition(int i7);
        }

        @Nullable
        public PointF computeScrollVectorForPosition(int i7) {
            Object layoutManager = getLayoutManager();
            if (layoutManager instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(i7);
            }
            return null;
        }

        public View findViewByPosition(int i7) {
            return this.mRecyclerView.mLayout.findViewByPosition(i7);
        }

        public int getChildCount() {
            return this.mRecyclerView.mLayout.getChildCount();
        }

        public int getChildPosition(View view) {
            return this.mRecyclerView.getChildLayoutPosition(view);
        }

        @Nullable
        public LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }

        public int getTargetPosition() {
            return this.mTargetPosition;
        }

        @Deprecated
        public void instantScrollToPosition(int i7) {
            this.mRecyclerView.scrollToPosition(i7);
        }

        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        public void normalize(@NonNull PointF pointF) {
            float f7 = pointF.x;
            float f8 = pointF.y;
            float fSqrt = (float) Math.sqrt((f8 * f8) + (f7 * f7));
            pointF.x /= fSqrt;
            pointF.y /= fSqrt;
        }

        public void onAnimation(int i7, int i8) {
            PointF pointFComputeScrollVectorForPosition;
            RecyclerView recyclerView = this.mRecyclerView;
            if (!this.mRunning || this.mTargetPosition == -1 || recyclerView == null) {
                stop();
            }
            if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null && (pointFComputeScrollVectorForPosition = computeScrollVectorForPosition(this.mTargetPosition)) != null) {
                float f7 = pointFComputeScrollVectorForPosition.x;
                if (f7 != 0.0f || pointFComputeScrollVectorForPosition.y != 0.0f) {
                    recyclerView.scrollStep((int) Math.signum(f7), (int) Math.signum(pointFComputeScrollVectorForPosition.y), null);
                }
            }
            this.mPendingInitialRun = false;
            View view = this.mTargetView;
            if (view != null) {
                if (getChildPosition(view) == this.mTargetPosition) {
                    onTargetFound(this.mTargetView, recyclerView.mState, this.mRecyclingAction);
                    this.mRecyclingAction.runIfNecessary(recyclerView);
                    stop();
                } else {
                    this.mTargetView = null;
                }
            }
            if (this.mRunning) {
                onSeekTargetStep(i7, i8, recyclerView.mState, this.mRecyclingAction);
                boolean zHasJumpTarget = this.mRecyclingAction.hasJumpTarget();
                this.mRecyclingAction.runIfNecessary(recyclerView);
                if (zHasJumpTarget) {
                    if (!this.mRunning) {
                        stop();
                    } else {
                        this.mPendingInitialRun = true;
                        recyclerView.mViewFlinger.postOnAnimation();
                    }
                }
            }
        }

        public void onChildAttachedToWindow(View view) {
            if (getChildPosition(view) == getTargetPosition()) {
                this.mTargetView = view;
            }
        }

        public abstract void onSeekTargetStep(@Px int i7, @Px int i8, @NonNull State state, @NonNull Action action);

        public abstract void onStart();

        public abstract void onStop();

        public abstract void onTargetFound(@NonNull View view, @NonNull State state, @NonNull Action action);

        public void setTargetPosition(int i7) {
            this.mTargetPosition = i7;
        }

        public void start(RecyclerView recyclerView, LayoutManager layoutManager) {
            this.mRecyclerView = recyclerView;
            this.mLayoutManager = layoutManager;
            int i7 = this.mTargetPosition;
            if (i7 == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.mState.mTargetPosition = i7;
            this.mRunning = true;
            this.mPendingInitialRun = true;
            this.mTargetView = findViewByPosition(getTargetPosition());
            onStart();
            this.mRecyclerView.mViewFlinger.postOnAnimation();
            this.mStarted = true;
        }

        public final void stop() {
            if (this.mRunning) {
                this.mRunning = false;
                onStop();
                this.mRecyclerView.mState.mTargetPosition = -1;
                this.mTargetView = null;
                this.mTargetPosition = -1;
                this.mPendingInitialRun = false;
                this.mLayoutManager.onSmoothScrollerStopped(this);
                this.mLayoutManager = null;
                this.mRecyclerView = null;
            }
        }
    }

    public static class State {
        public static final int STEP_ANIMATIONS = 4;
        public static final int STEP_LAYOUT = 2;
        public static final int STEP_START = 1;
        private SparseArray<Object> mData;
        public long mFocusedItemId;
        public int mFocusedItemPosition;
        public int mFocusedSubChildId;
        public int mRemainingScrollHorizontal;
        public int mRemainingScrollVertical;
        public int mTargetPosition = -1;
        public int mPreviousLayoutItemCount = 0;
        public int mDeletedInvisibleItemCountSincePreviousLayout = 0;
        public int mLayoutStep = 1;
        public int mItemCount = 0;
        public boolean mStructureChanged = false;
        public boolean mInPreLayout = false;
        public boolean mTrackOldChangeHolders = false;
        public boolean mIsMeasuring = false;
        public boolean mRunSimpleAnimations = false;
        public boolean mRunPredictiveAnimations = false;

        public void assertLayoutStep(int i7) {
            if ((this.mLayoutStep & i7) != 0) {
                return;
            }
            StringBuilder sbM112a = C0413b.m112a("Layout state should be one of ");
            sbM112a.append(Integer.toBinaryString(i7));
            sbM112a.append(" but it is ");
            sbM112a.append(Integer.toBinaryString(this.mLayoutStep));
            throw new IllegalStateException(sbM112a.toString());
        }

        public boolean didStructureChange() {
            return this.mStructureChanged;
        }

        public <T> T get(int i7) {
            SparseArray<Object> sparseArray = this.mData;
            if (sparseArray == null) {
                return null;
            }
            return (T) sparseArray.get(i7);
        }

        public int getItemCount() {
            return this.mInPreLayout ? this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout : this.mItemCount;
        }

        public int getRemainingScrollHorizontal() {
            return this.mRemainingScrollHorizontal;
        }

        public int getRemainingScrollVertical() {
            return this.mRemainingScrollVertical;
        }

        public int getTargetScrollPosition() {
            return this.mTargetPosition;
        }

        public boolean hasTargetScrollPosition() {
            return this.mTargetPosition != -1;
        }

        public boolean isMeasuring() {
            return this.mIsMeasuring;
        }

        public boolean isPreLayout() {
            return this.mInPreLayout;
        }

        public void prepareForNestedPrefetch(Adapter adapter) {
            this.mLayoutStep = 1;
            this.mItemCount = adapter.getItemCount();
            this.mInPreLayout = false;
            this.mTrackOldChangeHolders = false;
            this.mIsMeasuring = false;
        }

        public void put(int i7, Object obj) {
            if (this.mData == null) {
                this.mData = new SparseArray<>();
            }
            this.mData.put(i7, obj);
        }

        public void remove(int i7) {
            SparseArray<Object> sparseArray = this.mData;
            if (sparseArray == null) {
                return;
            }
            sparseArray.remove(i7);
        }

        public State reset() {
            this.mTargetPosition = -1;
            SparseArray<Object> sparseArray = this.mData;
            if (sparseArray != null) {
                sparseArray.clear();
            }
            this.mItemCount = 0;
            this.mStructureChanged = false;
            this.mIsMeasuring = false;
            return this;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("State{mTargetPosition=");
            sbM112a.append(this.mTargetPosition);
            sbM112a.append(", mData=");
            sbM112a.append(this.mData);
            sbM112a.append(", mItemCount=");
            sbM112a.append(this.mItemCount);
            sbM112a.append(", mIsMeasuring=");
            sbM112a.append(this.mIsMeasuring);
            sbM112a.append(", mPreviousLayoutItemCount=");
            sbM112a.append(this.mPreviousLayoutItemCount);
            sbM112a.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
            sbM112a.append(this.mDeletedInvisibleItemCountSincePreviousLayout);
            sbM112a.append(", mStructureChanged=");
            sbM112a.append(this.mStructureChanged);
            sbM112a.append(", mInPreLayout=");
            sbM112a.append(this.mInPreLayout);
            sbM112a.append(", mRunSimpleAnimations=");
            sbM112a.append(this.mRunSimpleAnimations);
            sbM112a.append(", mRunPredictiveAnimations=");
            sbM112a.append(this.mRunPredictiveAnimations);
            sbM112a.append('}');
            return sbM112a.toString();
        }

        public boolean willRunPredictiveAnimations() {
            return this.mRunPredictiveAnimations;
        }

        public boolean willRunSimpleAnimations() {
            return this.mRunSimpleAnimations;
        }
    }

    public static abstract class ViewCacheExtension {
        @Nullable
        public abstract View getViewForPositionAndType(@NonNull Recycler recycler, int i7, int i8);
    }

    public class ViewFlinger implements Runnable {
        private boolean mEatRunOnAnimationRequest;
        public Interpolator mInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        private boolean mReSchedulePostAnimationCallback;
        public OverScroller mScroller;

        public ViewFlinger() {
            Interpolator interpolator = RecyclerView.sQuinticInterpolator;
            this.mInterpolator = interpolator;
            this.mEatRunOnAnimationRequest = false;
            this.mReSchedulePostAnimationCallback = false;
            this.mScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        private int computeScrollDuration(int i7, int i8, int i9, int i10) {
            int iRound;
            int iAbs = Math.abs(i7);
            int iAbs2 = Math.abs(i8);
            boolean z6 = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt((i10 * i10) + (i9 * i9));
            int iSqrt2 = (int) Math.sqrt((i8 * i8) + (i7 * i7));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z6 ? recyclerView.getWidth() : recyclerView.getHeight();
            int i11 = width / 2;
            float f7 = width;
            float f8 = i11;
            float fDistanceInfluenceForSnapDuration = (distanceInfluenceForSnapDuration(Math.min(1.0f, (iSqrt2 * 1.0f) / f7)) * f8) + f8;
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fDistanceInfluenceForSnapDuration / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z6) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f7) + 1.0f) * 300.0f);
            }
            return Math.min(iRound, RecyclerView.MAX_SCROLL_DURATION);
        }

        private void disableRunOnAnimationRequests() {
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
        }

        private float distanceInfluenceForSnapDuration(float f7) {
            return (float) Math.sin((f7 - 0.5f) * 0.47123894f);
        }

        private void enableRunOnAnimationRequests() {
            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback) {
                postOnAnimation();
            }
        }

        public void fling(int i7, int i8) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.fling(0, 0, i7, i8, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            postOnAnimation();
        }

        public void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
            } else {
                RecyclerView.this.removeCallbacks(this);
                ViewCompat.postOnAnimation(RecyclerView.this, this);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x00e4  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00e7  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00ee  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00f7  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 418
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.ViewFlinger.run():void");
        }

        public void smoothScrollBy(int i7, int i8) {
            smoothScrollBy(i7, i8, 0, 0);
        }

        public void stop() {
            RecyclerView.this.removeCallbacks(this);
            this.mScroller.abortAnimation();
        }

        public void smoothScrollBy(int i7, int i8, int i9, int i10) {
            smoothScrollBy(i7, i8, computeScrollDuration(i7, i8, i9, i10));
        }

        public void smoothScrollBy(int i7, int i8, int i9) {
            smoothScrollBy(i7, i8, i9, RecyclerView.sQuinticInterpolator);
        }

        public void smoothScrollBy(int i7, int i8, Interpolator interpolator) {
            int iComputeScrollDuration = computeScrollDuration(i7, i8, 0, 0);
            if (interpolator == null) {
                interpolator = RecyclerView.sQuinticInterpolator;
            }
            smoothScrollBy(i7, i8, iComputeScrollDuration, interpolator);
        }

        public void smoothScrollBy(int i7, int i8, int i9, Interpolator interpolator) {
            if (this.mInterpolator != interpolator) {
                this.mInterpolator = interpolator;
                this.mScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.startScroll(0, 0, i7, i8, i9);
            if (Build.VERSION.SDK_INT < 23) {
                this.mScroller.computeScrollOffset();
            }
            postOnAnimation();
        }
    }

    public static abstract class ViewHolder {
        public static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        public static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        public static final int FLAG_BOUND = 1;
        public static final int FLAG_IGNORE = 128;
        public static final int FLAG_INVALID = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_NOT_RECYCLABLE = 16;
        public static final int FLAG_REMOVED = 8;
        public static final int FLAG_RETURNED_FROM_SCRAP = 32;
        public static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
        public static final int FLAG_TMP_DETACHED = 256;
        public static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        public static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;

        @NonNull
        public final View itemView;
        public int mFlags;
        public WeakReference<RecyclerView> mNestedRecyclerView;
        public RecyclerView mOwnerRecyclerView;
        public int mPosition = -1;
        public int mOldPosition = -1;
        public long mItemId = -1;
        public int mItemViewType = -1;
        public int mPreLayoutPosition = -1;
        public ViewHolder mShadowedHolder = null;
        public ViewHolder mShadowingHolder = null;
        public List<Object> mPayloads = null;
        public List<Object> mUnmodifiedPayloads = null;
        private int mIsRecyclableCount = 0;
        public Recycler mScrapContainer = null;
        public boolean mInChangeScrap = false;
        private int mWasImportantForAccessibilityBeforeHidden = 0;

        @VisibleForTesting
        public int mPendingAccessibilityState = -1;

        public ViewHolder(@NonNull View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = view;
        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                ArrayList arrayList = new ArrayList();
                this.mPayloads = arrayList;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
            }
        }

        public void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
            } else if ((1024 & this.mFlags) == 0) {
                createPayloadsIfNeeded();
                this.mPayloads.add(obj);
            }
        }

        public void addFlags(int i7) {
            this.mFlags = i7 | this.mFlags;
        }

        public void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        public void clearPayload() {
            List<Object> list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
        }

        public void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        public void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        public boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
        }

        public void flagRemovedAndOffsetPosition(int i7, int i8, boolean z6) {
            addFlags(8);
            offsetPosition(i8, z6);
            this.mPosition = i7;
        }

        public final int getAdapterPosition() {
            RecyclerView recyclerView = this.mOwnerRecyclerView;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.getAdapterPositionFor(this);
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            int i7 = this.mPreLayoutPosition;
            return i7 == -1 ? this.mPosition : i7;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        @Deprecated
        public final int getPosition() {
            int i7 = this.mPreLayoutPosition;
            return i7 == -1 ? this.mPosition : i7;
        }

        public List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) != 0) {
                return FULLUPDATE_PAYLOADS;
            }
            List<Object> list = this.mPayloads;
            return (list == null || list.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
        }

        public boolean hasAnyOfTheFlags(int i7) {
            return (i7 & this.mFlags) != 0;
        }

        public boolean isAdapterPositionUnknown() {
            return (this.mFlags & 512) != 0 || isInvalid();
        }

        public boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
        }

        public boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        public boolean isScrap() {
            return this.mScrapContainer != null;
        }

        public boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        public boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }

        public boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        public void offsetPosition(int i7, boolean z6) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z6) {
                this.mPreLayoutPosition += i7;
            }
            this.mPosition += i7;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        public void onEnteredHiddenState(RecyclerView recyclerView) {
            int i7 = this.mPendingAccessibilityState;
            if (i7 != -1) {
                this.mWasImportantForAccessibilityBeforeHidden = i7;
            } else {
                this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            }
            recyclerView.setChildImportantForAccessibilityInternal(this, 4);
        }

        public void onLeftHiddenState(RecyclerView recyclerView) {
            recyclerView.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        public void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        public void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        public void setFlags(int i7, int i8) {
            this.mFlags = (i7 & i8) | (this.mFlags & (~i8));
        }

        public final void setIsRecyclable(boolean z6) {
            int i7 = this.mIsRecyclableCount;
            int i8 = z6 ? i7 - 1 : i7 + 1;
            this.mIsRecyclableCount = i8;
            if (i8 < 0) {
                this.mIsRecyclableCount = 0;
                StringBuilder sb = new StringBuilder();
                sb.append("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ");
                sb.append(this);
                return;
            }
            if (!z6 && i8 == 1) {
                this.mFlags |= 16;
            } else if (z6 && i8 == 0) {
                this.mFlags &= -17;
            }
        }

        public void setScrapContainer(Recycler recycler, boolean z6) {
            this.mScrapContainer = recycler;
            this.mInChangeScrap = z6;
        }

        public boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0;
        }

        public boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        public void stopIgnoring() {
            this.mFlags &= -129;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("ViewHolder{");
            sbM112a.append(Integer.toHexString(hashCode()));
            sbM112a.append(" position=");
            sbM112a.append(this.mPosition);
            sbM112a.append(" id=");
            sbM112a.append(this.mItemId);
            sbM112a.append(", oldPos=");
            sbM112a.append(this.mOldPosition);
            sbM112a.append(", pLpos:");
            sbM112a.append(this.mPreLayoutPosition);
            StringBuilder sb = new StringBuilder(sbM112a.toString());
            if (isScrap()) {
                sb.append(" scrap ");
                sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            if (needsUpdate()) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                StringBuilder sbM112a2 = C0413b.m112a(" not recyclable(");
                sbM112a2.append(this.mIsRecyclableCount);
                sbM112a2.append(")");
                sb.append(sbM112a2.toString());
            }
            if (isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }

        public boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }
    }

    static {
        int i7 = Build.VERSION.SDK_INT;
        FORCE_INVALIDATE_DISPLAY_LIST = false;
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = i7 >= 23;
        POST_UPDATES_ON_ANIMATION = true;
        ALLOW_THREAD_GAP_WORK = true;
        FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
        IGNORE_DETACHED_FOCUSED_CHILD = false;
        Class<?> cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
        sQuinticInterpolator = new Interpolator() { // from class: android.support.v7.widget.RecyclerView.3
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f7) {
                float f8 = f7 - 1.0f;
                return (f8 * f8 * f8 * f8 * f8) + 1.0f;
            }
        };
    }

    public RecyclerView(@NonNull Context context) {
        this(context, null);
    }

    private void addAnimatingView(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        boolean z6 = view.getParent() == this;
        this.mRecycler.unscrapView(getChildViewHolder(view));
        if (viewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
        } else if (z6) {
            this.mChildHelper.hide(view);
        } else {
            this.mChildHelper.addView(view, true);
        }
    }

    private void animateChange(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2, boolean z6, boolean z7) {
        viewHolder.setIsRecyclable(false);
        if (z6) {
            addAnimatingView(viewHolder);
        }
        if (viewHolder != viewHolder2) {
            if (z7) {
                addAnimatingView(viewHolder2);
            }
            viewHolder.mShadowedHolder = viewHolder2;
            addAnimatingView(viewHolder);
            this.mRecycler.unscrapView(viewHolder);
            viewHolder2.setIsRecyclable(false);
            viewHolder2.mShadowingHolder = viewHolder;
        }
        if (this.mItemAnimator.animateChange(viewHolder, viewHolder2, itemHolderInfo, itemHolderInfo2)) {
            postAnimationRunner();
        }
    }

    private void cancelTouch() {
        resetTouch();
        setScrollState(0);
    }

    public static void clearNestedRecyclerViewIfNotNested(@NonNull ViewHolder viewHolder) {
        WeakReference<RecyclerView> weakReference = viewHolder.mNestedRecyclerView;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == viewHolder.itemView) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            viewHolder.mNestedRecyclerView = null;
        }
    }

    private void createLayoutManager(Context context, String str, AttributeSet attributeSet, int i7, int i8) throws NoSuchMethodException, SecurityException {
        Constructor constructor;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            String fullClassName = getFullClassName(context, strTrim);
            try {
                Class<? extends U> clsAsSubclass = (isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).loadClass(fullClassName).asSubclass(LayoutManager.class);
                Object[] objArr = null;
                try {
                    constructor = clsAsSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i7), Integer.valueOf(i8)};
                } catch (NoSuchMethodException e7) {
                    try {
                        constructor = clsAsSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e8) {
                        e8.initCause(e7);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, e8);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((LayoutManager) constructor.newInstance(objArr));
            } catch (ClassCastException e9) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, e9);
            } catch (ClassNotFoundException e10) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, e10);
            } catch (IllegalAccessException e11) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, e11);
            } catch (InstantiationException e12) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e12);
            } catch (InvocationTargetException e13) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e13);
            }
        }
    }

    private boolean didChildRangeChange(int i7, int i8) {
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        int[] iArr = this.mMinMaxLayoutPositions;
        return (iArr[0] == i7 && iArr[1] == i8) ? false : true;
    }

    private void dispatchContentChangedIfNecessary() {
        int i7 = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (i7 == 0 || !isAccessibilityEnabled()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        AccessibilityEventCompat.setContentChangeTypes(accessibilityEventObtain, i7);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    private void dispatchLayoutStep1() {
        this.mState.assertLayoutStep(1);
        fillRemainingScrollValues(this.mState);
        this.mState.mIsMeasuring = false;
        startInterceptRequestLayout();
        this.mViewInfoStore.clear();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        State state = this.mState;
        state.mTrackOldChangeHolders = state.mRunSimpleAnimations && this.mItemsChanged;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        state.mInPreLayout = state.mRunPredictiveAnimations;
        state.mItemCount = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.mRunSimpleAnimations) {
            int childCount = this.mChildHelper.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i7));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    this.mViewInfoStore.addToPreLayout(childViewHolderInt, this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt, ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt), childViewHolderInt.getUnmodifiedPayloads()));
                    if (this.mState.mTrackOldChangeHolders && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.addToOldChangeHolders(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.mRunPredictiveAnimations) {
            saveOldPositions();
            State state2 = this.mState;
            boolean z6 = state2.mStructureChanged;
            state2.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, state2);
            this.mState.mStructureChanged = z6;
            for (int i8 = 0; i8 < this.mChildHelper.getChildCount(); i8++) {
                ViewHolder childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getChildAt(i8));
                if (!childViewHolderInt2.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(childViewHolderInt2)) {
                    int iBuildAdapterChangeFlagsForAnimations = ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt2);
                    boolean zHasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(8192);
                    if (!zHasAnyOfTheFlags) {
                        iBuildAdapterChangeFlagsForAnimations |= 4096;
                    }
                    ItemAnimator.ItemHolderInfo itemHolderInfoRecordPreLayoutInformation = this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt2, iBuildAdapterChangeFlagsForAnimations, childViewHolderInt2.getUnmodifiedPayloads());
                    if (zHasAnyOfTheFlags) {
                        recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, itemHolderInfoRecordPreLayoutInformation);
                    } else {
                        this.mViewInfoStore.addToAppearedInPreLayoutHolders(childViewHolderInt2, itemHolderInfoRecordPreLayoutInformation);
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }

    private void dispatchLayoutStep2() {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        State state = this.mState;
        state.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        state.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, state);
        State state2 = this.mState;
        state2.mStructureChanged = false;
        this.mPendingSavedState = null;
        state2.mRunSimpleAnimations = state2.mRunSimpleAnimations && this.mItemAnimator != null;
        state2.mLayoutStep = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
    }

    private void dispatchLayoutStep3() {
        this.mState.assertLayoutStep(4);
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        State state = this.mState;
        state.mLayoutStep = 1;
        if (state.mRunSimpleAnimations) {
            for (int childCount = this.mChildHelper.getChildCount() - 1; childCount >= 0; childCount--) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(childCount));
                if (!childViewHolderInt.shouldIgnore()) {
                    long changedHolderKey = getChangedHolderKey(childViewHolderInt);
                    ItemAnimator.ItemHolderInfo itemHolderInfoRecordPostLayoutInformation = this.mItemAnimator.recordPostLayoutInformation(this.mState, childViewHolderInt);
                    ViewHolder fromOldChangeHolders = this.mViewInfoStore.getFromOldChangeHolders(changedHolderKey);
                    if (fromOldChangeHolders == null || fromOldChangeHolders.shouldIgnore()) {
                        this.mViewInfoStore.addToPostLayout(childViewHolderInt, itemHolderInfoRecordPostLayoutInformation);
                    } else {
                        boolean zIsDisappearing = this.mViewInfoStore.isDisappearing(fromOldChangeHolders);
                        boolean zIsDisappearing2 = this.mViewInfoStore.isDisappearing(childViewHolderInt);
                        if (zIsDisappearing && fromOldChangeHolders == childViewHolderInt) {
                            this.mViewInfoStore.addToPostLayout(childViewHolderInt, itemHolderInfoRecordPostLayoutInformation);
                        } else {
                            ItemAnimator.ItemHolderInfo itemHolderInfoPopFromPreLayout = this.mViewInfoStore.popFromPreLayout(fromOldChangeHolders);
                            this.mViewInfoStore.addToPostLayout(childViewHolderInt, itemHolderInfoRecordPostLayoutInformation);
                            ItemAnimator.ItemHolderInfo itemHolderInfoPopFromPostLayout = this.mViewInfoStore.popFromPostLayout(childViewHolderInt);
                            if (itemHolderInfoPopFromPreLayout == null) {
                                handleMissingPreInfoForChangeError(changedHolderKey, childViewHolderInt, fromOldChangeHolders);
                            } else {
                                animateChange(fromOldChangeHolders, childViewHolderInt, itemHolderInfoPopFromPreLayout, itemHolderInfoPopFromPostLayout, zIsDisappearing, zIsDisappearing2);
                            }
                        }
                    }
                }
            }
            this.mViewInfoStore.process(this.mViewInfoProcessCallback);
        }
        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        State state2 = this.mState;
        state2.mPreviousLayoutItemCount = state2.mItemCount;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        state2.mRunSimpleAnimations = false;
        state2.mRunPredictiveAnimations = false;
        this.mLayout.mRequestedSimpleAnimations = false;
        ArrayList<ViewHolder> arrayList = this.mRecycler.mChangedScrap;
        if (arrayList != null) {
            arrayList.clear();
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager.mPrefetchMaxObservedInInitialPrefetch) {
            layoutManager.mPrefetchMaxCountObserved = 0;
            layoutManager.mPrefetchMaxObservedInInitialPrefetch = false;
            this.mRecycler.updateViewCacheSize();
        }
        this.mLayout.onLayoutCompleted(this.mState);
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mViewInfoStore.clear();
        int[] iArr = this.mMinMaxLayoutPositions;
        if (didChildRangeChange(iArr[0], iArr[1])) {
            dispatchOnScrolled(0, 0);
        }
        recoverFocusFromState();
        resetFocusInfo();
    }

    private boolean dispatchOnItemTouch(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        OnItemTouchListener onItemTouchListener = this.mActiveOnItemTouchListener;
        if (onItemTouchListener != null) {
            if (action != 0) {
                onItemTouchListener.onTouchEvent(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.mActiveOnItemTouchListener = null;
                }
                return true;
            }
            this.mActiveOnItemTouchListener = null;
        }
        if (action != 0) {
            int size = this.mOnItemTouchListeners.size();
            for (int i7 = 0; i7 < size; i7++) {
                OnItemTouchListener onItemTouchListener2 = this.mOnItemTouchListeners.get(i7);
                if (onItemTouchListener2.onInterceptTouchEvent(this, motionEvent)) {
                    this.mActiveOnItemTouchListener = onItemTouchListener2;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dispatchOnItemTouchIntercept(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.mActiveOnItemTouchListener = null;
        }
        int size = this.mOnItemTouchListeners.size();
        for (int i7 = 0; i7 < size; i7++) {
            OnItemTouchListener onItemTouchListener = this.mOnItemTouchListeners.get(i7);
            if (onItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mActiveOnItemTouchListener = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int childCount = this.mChildHelper.getChildCount();
        if (childCount == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i7 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i8 = Integer.MIN_VALUE;
        for (int i9 = 0; i9 < childCount; i9++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i9));
            if (!childViewHolderInt.shouldIgnore()) {
                int layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i7) {
                    i7 = layoutPosition;
                }
                if (layoutPosition > i8) {
                    i8 = layoutPosition;
                }
            }
        }
        iArr[0] = i7;
        iArr[1] = i8;
    }

    @Nullable
    public static RecyclerView findNestedRecyclerView(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            RecyclerView recyclerViewFindNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i7));
            if (recyclerViewFindNestedRecyclerView != null) {
                return recyclerViewFindNestedRecyclerView;
            }
        }
        return null;
    }

    @Nullable
    private View findNextViewToFocus() {
        ViewHolder viewHolderFindViewHolderForAdapterPosition;
        State state = this.mState;
        int i7 = state.mFocusedItemPosition;
        if (i7 == -1) {
            i7 = 0;
        }
        int itemCount = state.getItemCount();
        for (int i8 = i7; i8 < itemCount; i8++) {
            ViewHolder viewHolderFindViewHolderForAdapterPosition2 = findViewHolderForAdapterPosition(i8);
            if (viewHolderFindViewHolderForAdapterPosition2 == null) {
                break;
            }
            if (viewHolderFindViewHolderForAdapterPosition2.itemView.hasFocusable()) {
                return viewHolderFindViewHolderForAdapterPosition2.itemView;
            }
        }
        int iMin = Math.min(itemCount, i7);
        do {
            iMin--;
            if (iMin < 0 || (viewHolderFindViewHolderForAdapterPosition = findViewHolderForAdapterPosition(iMin)) == null) {
                return null;
            }
        } while (!viewHolderFindViewHolderForAdapterPosition.itemView.hasFocusable());
        return viewHolderFindViewHolderForAdapterPosition.itemView;
    }

    public static ViewHolder getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).mViewHolder;
    }

    public static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.mDecorInsets;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    private String getFullClassName(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    private void handleMissingPreInfoForChangeError(long j7, ViewHolder viewHolder, ViewHolder viewHolder2) {
        int childCount = this.mChildHelper.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i7));
            if (childViewHolderInt != viewHolder && getChangedHolderKey(childViewHolderInt) == j7) {
                Adapter adapter = this.mAdapter;
                if (adapter == null || !adapter.hasStableIds()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:");
                    sb.append(childViewHolderInt);
                    sb.append(" \n View Holder 2:");
                    sb.append(viewHolder);
                    throw new IllegalStateException(C0402a.m108a(this, sb));
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:");
                sb2.append(childViewHolderInt);
                sb2.append(" \n View Holder 2:");
                sb2.append(viewHolder);
                throw new IllegalStateException(C0402a.m108a(this, sb2));
            }
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ");
        sb3.append(viewHolder2);
        sb3.append(" cannot be found but it is necessary for ");
        sb3.append(viewHolder);
        sb3.append(exceptionLabel());
    }

    private boolean hasUpdatedView() {
        int childCount = this.mChildHelper.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i7));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"InlinedApi"})
    private void initAutofill() {
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            ViewCompat.setImportantForAutofill(this, 8);
        }
    }

    private void initChildrenHelper() {
        this.mChildHelper = new ChildHelper(new ChildHelper.Callback() { // from class: android.support.v7.widget.RecyclerView.5
            @Override // android.support.v7.widget.ChildHelper.Callback
            public void addView(View view, int i7) {
                RecyclerView.this.addView(view, i7);
                RecyclerView.this.dispatchChildAttached(view);
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public void attachViewToParent(View view, int i7, ViewGroup.LayoutParams layoutParams) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    if (!childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Called attach on a child which is not detached: ");
                        sb.append(childViewHolderInt);
                        throw new IllegalArgumentException(C0402a.m108a(RecyclerView.this, sb));
                    }
                    childViewHolderInt.clearTmpDetachFlag();
                }
                RecyclerView.this.attachViewToParent(view, i7, layoutParams);
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public void detachViewFromParent(int i7) {
                ViewHolder childViewHolderInt;
                View childAt = getChildAt(i7);
                if (childAt != null && (childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt)) != null) {
                    if (childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("called detach on an already detached child ");
                        sb.append(childViewHolderInt);
                        throw new IllegalArgumentException(C0402a.m108a(RecyclerView.this, sb));
                    }
                    childViewHolderInt.addFlags(256);
                }
                RecyclerView.this.detachViewFromParent(i7);
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public View getChildAt(int i7) {
                return RecyclerView.this.getChildAt(i7);
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public int getChildCount() {
                return RecyclerView.this.getChildCount();
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public ViewHolder getChildViewHolder(View view) {
                return RecyclerView.getChildViewHolderInt(view);
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public int indexOfChild(View view) {
                return RecyclerView.this.indexOfChild(view);
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public void onEnteredHiddenState(View view) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    childViewHolderInt.onEnteredHiddenState(RecyclerView.this);
                }
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public void onLeftHiddenState(View view) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    childViewHolderInt.onLeftHiddenState(RecyclerView.this);
                }
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public void removeAllViews() {
                int childCount = getChildCount();
                for (int i7 = 0; i7 < childCount; i7++) {
                    View childAt = getChildAt(i7);
                    RecyclerView.this.dispatchChildDetached(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeAllViews();
            }

            @Override // android.support.v7.widget.ChildHelper.Callback
            public void removeViewAt(int i7) {
                View childAt = RecyclerView.this.getChildAt(i7);
                if (childAt != null) {
                    RecyclerView.this.dispatchChildDetached(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeViewAt(i7);
            }
        });
    }

    private boolean isPreferredNextFocus(View view, View view2, int i7) {
        int i8;
        if (view2 == null || view2 == this || findContainingItemView(view2) == null) {
            return false;
        }
        if (view == null || findContainingItemView(view) == null) {
            return true;
        }
        this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        this.mTempRect2.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        offsetDescendantRectToMyCoords(view2, this.mTempRect2);
        char c7 = 65535;
        int i9 = this.mLayout.getLayoutDirection() == 1 ? -1 : 1;
        Rect rect = this.mTempRect;
        int i10 = rect.left;
        Rect rect2 = this.mTempRect2;
        int i11 = rect2.left;
        if ((i10 < i11 || rect.right <= i11) && rect.right < rect2.right) {
            i8 = 1;
        } else {
            int i12 = rect.right;
            int i13 = rect2.right;
            i8 = ((i12 > i13 || i10 >= i13) && i10 > i11) ? -1 : 0;
        }
        int i14 = rect.top;
        int i15 = rect2.top;
        if ((i14 < i15 || rect.bottom <= i15) && rect.bottom < rect2.bottom) {
            c7 = 1;
        } else {
            int i16 = rect.bottom;
            int i17 = rect2.bottom;
            if ((i16 <= i17 && i14 < i17) || i14 <= i15) {
                c7 = 0;
            }
        }
        if (i7 == 1) {
            return c7 < 0 || (c7 == 0 && i8 * i9 <= 0);
        }
        if (i7 == 2) {
            return c7 > 0 || (c7 == 0 && i8 * i9 >= 0);
        }
        if (i7 == 17) {
            return i8 < 0;
        }
        if (i7 == 33) {
            return c7 < 0;
        }
        if (i7 == 66) {
            return i8 > 0;
        }
        if (i7 == 130) {
            return c7 > 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid direction: ");
        sb.append(i7);
        throw new IllegalArgumentException(C0402a.m108a(this, sb));
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i7 = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i7);
            int x6 = (int) (motionEvent.getX(i7) + 0.5f);
            this.mLastTouchX = x6;
            this.mInitialTouchX = x6;
            int y6 = (int) (motionEvent.getY(i7) + 0.5f);
            this.mLastTouchY = y6;
            this.mInitialTouchY = y6;
        }
    }

    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations();
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        boolean z6;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.reset();
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.onItemsChanged(this);
            }
        }
        if (predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.preProcess();
        } else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }
        boolean z7 = false;
        boolean z8 = this.mItemsAddedOrRemoved || this.mItemsChanged;
        this.mState.mRunSimpleAnimations = this.mFirstLayoutComplete && this.mItemAnimator != null && ((z6 = this.mDataSetHasChangedAfterLayout) || z8 || this.mLayout.mRequestedSimpleAnimations) && (!z6 || this.mAdapter.hasStableIds());
        State state = this.mState;
        if (state.mRunSimpleAnimations && z8 && !this.mDataSetHasChangedAfterLayout && predictiveItemAnimationsEnabled()) {
            z7 = true;
        }
        state.mRunPredictiveAnimations = z7;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void pullGlows(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            r1 = 1
            r2 = 0
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 >= 0) goto L21
            r6.ensureLeftGlow()
            android.widget.EdgeEffect r3 = r6.mLeftGlow
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r0 - r9
            android.support.v4.widget.EdgeEffectCompat.onPull(r3, r4, r9)
        L1f:
            r9 = 1
            goto L3c
        L21:
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 <= 0) goto L3b
            r6.ensureRightGlow()
            android.widget.EdgeEffect r3 = r6.mRightGlow
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            android.support.v4.widget.EdgeEffectCompat.onPull(r3, r4, r9)
            goto L1f
        L3b:
            r9 = 0
        L3c:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 >= 0) goto L56
            r6.ensureTopGlow()
            android.widget.EdgeEffect r9 = r6.mTopGlow
            float r0 = -r10
            int r3 = r6.getHeight()
            float r3 = (float) r3
            float r0 = r0 / r3
            int r3 = r6.getWidth()
            float r3 = (float) r3
            float r7 = r7 / r3
            android.support.v4.widget.EdgeEffectCompat.onPull(r9, r0, r7)
            goto L72
        L56:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 <= 0) goto L71
            r6.ensureBottomGlow()
            android.widget.EdgeEffect r9 = r6.mBottomGlow
            int r3 = r6.getHeight()
            float r3 = (float) r3
            float r3 = r10 / r3
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r0 = r0 - r7
            android.support.v4.widget.EdgeEffectCompat.onPull(r9, r3, r0)
            goto L72
        L71:
            r1 = r9
        L72:
            if (r1 != 0) goto L7c
            int r7 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r7 != 0) goto L7c
            int r7 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r7 == 0) goto L7f
        L7c:
            android.support.v4.view.ViewCompat.postInvalidateOnAnimation(r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.pullGlows(float, float, float, float):void");
    }

    private void recoverFocusFromState() {
        View viewFindViewById;
        if (!this.mPreserveFocusAfterLayout || this.mAdapter == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!IGNORE_DETACHED_FOCUSED_CHILD || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.mChildHelper.isHidden(focusedChild)) {
                    return;
                }
            } else if (this.mChildHelper.getChildCount() == 0) {
                requestFocus();
                return;
            }
        }
        View viewFindNextViewToFocus = null;
        ViewHolder viewHolderFindViewHolderForItemId = (this.mState.mFocusedItemId == -1 || !this.mAdapter.hasStableIds()) ? null : findViewHolderForItemId(this.mState.mFocusedItemId);
        if (viewHolderFindViewHolderForItemId != null && !this.mChildHelper.isHidden(viewHolderFindViewHolderForItemId.itemView) && viewHolderFindViewHolderForItemId.itemView.hasFocusable()) {
            viewFindNextViewToFocus = viewHolderFindViewHolderForItemId.itemView;
        } else if (this.mChildHelper.getChildCount() > 0) {
            viewFindNextViewToFocus = findNextViewToFocus();
        }
        if (viewFindNextViewToFocus != null) {
            int i7 = this.mState.mFocusedSubChildId;
            if (i7 != -1 && (viewFindViewById = viewFindNextViewToFocus.findViewById(i7)) != null && viewFindViewById.isFocusable()) {
                viewFindNextViewToFocus = viewFindViewById;
            }
            viewFindNextViewToFocus.requestFocus();
        }
    }

    private void releaseGlows() {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.mLeftGlow.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.mBottomGlow.isFinished();
        }
        if (zIsFinished) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void requestChildOnScreen(@NonNull View view, @Nullable View view2) {
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.mInsetsDirty) {
                Rect rect = layoutParams2.mDecorInsets;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        this.mLayout.requestChildRectangleOnScreen(this, view, this.mTempRect, !this.mFirstLayoutComplete, view2 == null);
    }

    private void resetFocusInfo() {
        State state = this.mState;
        state.mFocusedItemId = -1L;
        state.mFocusedItemPosition = -1;
        state.mFocusedSubChildId = -1;
    }

    private void resetTouch() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        releaseGlows();
    }

    private void saveFocusInfo() {
        View focusedChild = (this.mPreserveFocusAfterLayout && hasFocus() && this.mAdapter != null) ? getFocusedChild() : null;
        ViewHolder viewHolderFindContainingViewHolder = focusedChild != null ? findContainingViewHolder(focusedChild) : null;
        if (viewHolderFindContainingViewHolder == null) {
            resetFocusInfo();
            return;
        }
        this.mState.mFocusedItemId = this.mAdapter.hasStableIds() ? viewHolderFindContainingViewHolder.getItemId() : -1L;
        this.mState.mFocusedItemPosition = this.mDataSetHasChangedAfterLayout ? -1 : viewHolderFindContainingViewHolder.isRemoved() ? viewHolderFindContainingViewHolder.mOldPosition : viewHolderFindContainingViewHolder.getAdapterPosition();
        this.mState.mFocusedSubChildId = getDeepestFocusedViewWithId(viewHolderFindContainingViewHolder.itemView);
    }

    private void setAdapterInternal(@Nullable Adapter adapter, boolean z6, boolean z7) {
        Adapter adapter2 = this.mAdapter;
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!z6 || z7) {
            removeAndRecycleViews();
        }
        this.mAdapterHelper.reset();
        Adapter adapter3 = this.mAdapter;
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mObserver);
            adapter.onAttachedToRecyclerView(this);
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.onAdapterChanged(adapter3, this.mAdapter);
        }
        this.mRecycler.onAdapterChanged(adapter3, this.mAdapter, z6);
        this.mState.mStructureChanged = true;
    }

    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.stopSmoothScroller();
        }
    }

    public void absorbGlows(int i7, int i8) {
        if (i7 < 0) {
            ensureLeftGlow();
            this.mLeftGlow.onAbsorb(-i7);
        } else if (i7 > 0) {
            ensureRightGlow();
            this.mRightGlow.onAbsorb(i7);
        }
        if (i8 < 0) {
            ensureTopGlow();
            this.mTopGlow.onAbsorb(-i8);
        } else if (i8 > 0) {
            ensureBottomGlow();
            this.mBottomGlow.onAbsorb(i8);
        }
        if (i7 == 0 && i8 == 0) {
            return;
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i7, int i8) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || !layoutManager.onAddFocusables(this, arrayList, i7, i8)) {
            super.addFocusables(arrayList, i7, i8);
        }
    }

    public void addItemDecoration(@NonNull ItemDecoration itemDecoration, int i7) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i7 < 0) {
            this.mItemDecorations.add(itemDecoration);
        } else {
            this.mItemDecorations.add(i7, itemDecoration);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(onChildAttachStateChangeListener);
    }

    public void addOnItemTouchListener(@NonNull OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.add(onItemTouchListener);
    }

    public void addOnScrollListener(@NonNull OnScrollListener onScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(onScrollListener);
    }

    public void animateAppearance(@NonNull ViewHolder viewHolder, @Nullable ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            postAnimationRunner();
        }
    }

    public void animateDisappearance(@NonNull ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        addAnimatingView(viewHolder);
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            postAnimationRunner();
        }
    }

    public void assertInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            return;
        }
        if (str != null) {
            throw new IllegalStateException(C0402a.m108a(this, C0413b.m112a(str)));
        }
        throw new IllegalStateException(C0402a.m108a(this, C0413b.m112a("Cannot call this method unless RecyclerView is computing a layout or scrolling")));
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException(C0402a.m108a(this, C0413b.m112a("Cannot call this method while RecyclerView is computing a layout or scrolling")));
        }
        if (this.mDispatchScrollCounter > 0) {
            new IllegalStateException(C0402a.m108a(this, C0413b.m112a("")));
        }
    }

    public boolean canReuseUpdatedViewHolder(ViewHolder viewHolder) {
        ItemAnimator itemAnimator = this.mItemAnimator;
        return itemAnimator == null || itemAnimator.canReuseUpdatedViewHolder(viewHolder, viewHolder.getUnmodifiedPayloads());
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.mLayout.checkLayoutParams((LayoutParams) layoutParams);
    }

    public void clearOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i7 = 0; i7 < unfilteredChildCount; i7++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i7));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        this.mRecycler.clearOldPositions();
    }

    public void clearOnChildAttachStateChangeListeners() {
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void clearOnScrollListeners() {
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View, android.support.v4.view.ScrollingView
    public int computeHorizontalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.ScrollingView
    public int computeHorizontalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.ScrollingView
    public int computeHorizontalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.ScrollingView
    public int computeVerticalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.ScrollingView
    public int computeVerticalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.ScrollingView
    public int computeVerticalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }

    public void considerReleasingGlowsOnScroll(int i7, int i8) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i7 <= 0) {
            zIsFinished = false;
        } else {
            this.mLeftGlow.onRelease();
            zIsFinished = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i7 < 0) {
            this.mRightGlow.onRelease();
            zIsFinished |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i8 > 0) {
            this.mTopGlow.onRelease();
            zIsFinished |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i8 < 0) {
            this.mBottomGlow.onRelease();
            zIsFinished |= this.mBottomGlow.isFinished();
        }
        if (zIsFinished) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
            dispatchLayout();
            TraceCompat.endSection();
            return;
        }
        if (this.mAdapterHelper.hasPendingUpdates()) {
            if (!this.mAdapterHelper.hasAnyUpdateTypes(4) || this.mAdapterHelper.hasAnyUpdateTypes(11)) {
                if (this.mAdapterHelper.hasPendingUpdates()) {
                    TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
                    dispatchLayout();
                    TraceCompat.endSection();
                    return;
                }
                return;
            }
            TraceCompat.beginSection(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            this.mAdapterHelper.preProcess();
            if (!this.mLayoutWasDefered) {
                if (hasUpdatedView()) {
                    dispatchLayout();
                } else {
                    this.mAdapterHelper.consumePostponedUpdates();
                }
            }
            stopInterceptRequestLayout(true);
            onExitLayoutOrScroll();
            TraceCompat.endSection();
        }
    }

    public void defaultOnMeasure(int i7, int i8) {
        setMeasuredDimension(LayoutManager.chooseSize(i7, getPaddingRight() + getPaddingLeft(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(i8, getPaddingBottom() + getPaddingTop(), ViewCompat.getMinimumHeight(this)));
    }

    public void dispatchChildAttached(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        Adapter adapter = this.mAdapter;
        if (adapter != null && childViewHolderInt != null) {
            adapter.onViewAttachedToWindow(childViewHolderInt);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).onChildViewAttachedToWindow(view);
            }
        }
    }

    public void dispatchChildDetached(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        Adapter adapter = this.mAdapter;
        if (adapter != null && childViewHolderInt != null) {
            adapter.onViewDetachedFromWindow(childViewHolderInt);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).onChildViewDetachedFromWindow(view);
            }
        }
    }

    public void dispatchLayout() {
        if (this.mAdapter == null || this.mLayout == null) {
            return;
        }
        State state = this.mState;
        state.mIsMeasuring = false;
        if (state.mLayoutStep == 1) {
            dispatchLayoutStep1();
            this.mLayout.setExactMeasureSpecsFrom(this);
            dispatchLayoutStep2();
        } else if (!this.mAdapterHelper.hasUpdates() && this.mLayout.getWidth() == getWidth() && this.mLayout.getHeight() == getHeight()) {
            this.mLayout.setExactMeasureSpecsFrom(this);
        } else {
            this.mLayout.setExactMeasureSpecsFrom(this);
            dispatchLayoutStep2();
        }
        dispatchLayoutStep3();
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f7, float f8, boolean z6) {
        return getScrollingChildHelper().dispatchNestedFling(f7, f8, z6);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f7, float f8) {
        return getScrollingChildHelper().dispatchNestedPreFling(f7, f8);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i7, int i8, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i7, i8, iArr, iArr2);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i7, i8, i9, i10, iArr);
    }

    public void dispatchOnScrollStateChanged(int i7) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.onScrollStateChanged(i7);
        }
        onScrollStateChanged(i7);
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(this, i7);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrollStateChanged(this, i7);
            }
        }
    }

    public void dispatchOnScrolled(int i7, int i8) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        onScrolled(i7, i8);
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrolled(this, i7, i8);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrolled(this, i7, i8);
            }
        }
        this.mDispatchScrollCounter--;
    }

    public void dispatchPendingImportantForAccessibilityChanges() {
        int i7;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = this.mPendingAccessibilityImportanceChange.get(size);
            if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore() && (i7 = viewHolder.mPendingAccessibilityState) != -1) {
                ViewCompat.setImportantForAccessibility(viewHolder.itemView, i7);
                viewHolder.mPendingAccessibilityState = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z6;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z7 = false;
        for (int i7 = 0; i7 < size; i7++) {
            this.mItemDecorations.get(i7).onDrawOver(canvas, this, this.mState);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z6 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            z6 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            z6 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.mClipToPadding ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.mRightGlow;
            z6 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z7 = true;
            }
            z6 |= z7;
            canvas.restoreToCount(iSave4);
        }
        if ((z6 || this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.isRunning()) ? z6 : true) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j7) {
        return super.drawChild(canvas, view, j7);
    }

    public void ensureBottomGlow() {
        if (this.mBottomGlow != null) {
            return;
        }
        EdgeEffect edgeEffectCreateEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this, 3);
        this.mBottomGlow = edgeEffectCreateEdgeEffect;
        if (this.mClipToPadding) {
            edgeEffectCreateEdgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectCreateEdgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void ensureLeftGlow() {
        if (this.mLeftGlow != null) {
            return;
        }
        EdgeEffect edgeEffectCreateEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this, 0);
        this.mLeftGlow = edgeEffectCreateEdgeEffect;
        if (this.mClipToPadding) {
            edgeEffectCreateEdgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectCreateEdgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void ensureRightGlow() {
        if (this.mRightGlow != null) {
            return;
        }
        EdgeEffect edgeEffectCreateEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this, 2);
        this.mRightGlow = edgeEffectCreateEdgeEffect;
        if (this.mClipToPadding) {
            edgeEffectCreateEdgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectCreateEdgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void ensureTopGlow() {
        if (this.mTopGlow != null) {
            return;
        }
        EdgeEffect edgeEffectCreateEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this, 1);
        this.mTopGlow = edgeEffectCreateEdgeEffect;
        if (this.mClipToPadding) {
            edgeEffectCreateEdgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectCreateEdgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public String exceptionLabel() {
        StringBuilder sbM112a = C0413b.m112a(" ");
        sbM112a.append(super.toString());
        sbM112a.append(", adapter:");
        sbM112a.append(this.mAdapter);
        sbM112a.append(", layout:");
        sbM112a.append(this.mLayout);
        sbM112a.append(", context:");
        sbM112a.append(getContext());
        return sbM112a.toString();
    }

    public final void fillRemainingScrollValues(State state) {
        if (getScrollState() != 2) {
            state.mRemainingScrollHorizontal = 0;
            state.mRemainingScrollVertical = 0;
        } else {
            OverScroller overScroller = this.mViewFlinger.mScroller;
            state.mRemainingScrollHorizontal = overScroller.getFinalX() - overScroller.getCurrX();
            state.mRemainingScrollVertical = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    @Nullable
    public View findChildViewUnder(float f7, float f8) {
        for (int childCount = this.mChildHelper.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mChildHelper.getChildAt(childCount);
            float translationX = childAt.getTranslationX();
            float translationY = childAt.getTranslationY();
            if (f7 >= childAt.getLeft() + translationX && f7 <= childAt.getRight() + translationX && f8 >= childAt.getTop() + translationY && f8 <= childAt.getBottom() + translationY) {
                return childAt;
            }
        }
        return null;
    }

    @Nullable
    public View findContainingItemView(@NonNull View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    @Nullable
    public ViewHolder findContainingViewHolder(@NonNull View view) {
        View viewFindContainingItemView = findContainingItemView(view);
        if (viewFindContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(viewFindContainingItemView);
    }

    @Nullable
    public ViewHolder findViewHolderForAdapterPosition(int i7) {
        ViewHolder viewHolder = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i8 = 0; i8 < unfilteredChildCount; i8++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i8));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionFor(childViewHolderInt) == i7) {
                if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                viewHolder = childViewHolderInt;
            }
        }
        return viewHolder;
    }

    public ViewHolder findViewHolderForItemId(long j7) {
        Adapter adapter = this.mAdapter;
        ViewHolder viewHolder = null;
        if (adapter != null && adapter.hasStableIds()) {
            int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
            for (int i7 = 0; i7 < unfilteredChildCount; i7++) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i7));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.getItemId() == j7) {
                    if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    viewHolder = childViewHolderInt;
                }
            }
        }
        return viewHolder;
    }

    @Nullable
    public ViewHolder findViewHolderForLayoutPosition(int i7) {
        return findViewHolderForPosition(i7, false);
    }

    @Nullable
    @Deprecated
    public ViewHolder findViewHolderForPosition(int i7) {
        return findViewHolderForPosition(i7, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v6 */
    public boolean fling(int i7, int i8) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || this.mLayoutFrozen) {
            return false;
        }
        int iCanScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean zCanScrollVertically = this.mLayout.canScrollVertically();
        if (iCanScrollHorizontally == 0 || Math.abs(i7) < this.mMinFlingVelocity) {
            i7 = 0;
        }
        if (!zCanScrollVertically || Math.abs(i8) < this.mMinFlingVelocity) {
            i8 = 0;
        }
        if (i7 == 0 && i8 == 0) {
            return false;
        }
        float f7 = i7;
        float f8 = i8;
        if (!dispatchNestedPreFling(f7, f8)) {
            boolean z6 = iCanScrollHorizontally != 0 || zCanScrollVertically;
            dispatchNestedFling(f7, f8, z6);
            OnFlingListener onFlingListener = this.mOnFlingListener;
            if (onFlingListener != null && onFlingListener.onFling(i7, i8)) {
                return true;
            }
            if (z6) {
                if (zCanScrollVertically) {
                    iCanScrollHorizontally = (iCanScrollHorizontally == true ? 1 : 0) | 2;
                }
                startNestedScroll(iCanScrollHorizontally, 1);
                int i9 = this.mMaxFlingVelocity;
                int iMax = Math.max(-i9, Math.min(i7, i9));
                int i10 = this.mMaxFlingVelocity;
                this.mViewFlinger.fling(iMax, Math.max(-i10, Math.min(i8, i10)));
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i7) {
        View viewOnFocusSearchFailed;
        boolean z6;
        View viewOnInterceptFocusSearch = this.mLayout.onInterceptFocusSearch(view, i7);
        if (viewOnInterceptFocusSearch != null) {
            return viewOnInterceptFocusSearch;
        }
        boolean z7 = (this.mAdapter == null || this.mLayout == null || isComputingLayout() || this.mLayoutFrozen) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z7 && (i7 == 2 || i7 == 1)) {
            if (this.mLayout.canScrollVertically()) {
                int i8 = i7 == 2 ? 130 : 33;
                z6 = focusFinder.findNextFocus(this, view, i8) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i7 = i8;
                }
            } else {
                z6 = false;
            }
            if (!z6 && this.mLayout.canScrollHorizontally()) {
                int i9 = (this.mLayout.getLayoutDirection() == 1) ^ (i7 == 2) ? 66 : 17;
                boolean z8 = focusFinder.findNextFocus(this, view, i9) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i7 = i9;
                }
                z6 = z8;
            }
            if (z6) {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                this.mLayout.onFocusSearchFailed(view, i7, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            }
            viewOnFocusSearchFailed = focusFinder.findNextFocus(this, view, i7);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i7);
            if (viewFindNextFocus == null && z7) {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                viewOnFocusSearchFailed = this.mLayout.onFocusSearchFailed(view, i7, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            } else {
                viewOnFocusSearchFailed = viewFindNextFocus;
            }
        }
        if (viewOnFocusSearchFailed == null || viewOnFocusSearchFailed.hasFocusable()) {
            return isPreferredNextFocus(view, viewOnFocusSearchFailed, i7) ? viewOnFocusSearchFailed : super.focusSearch(view, i7);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i7);
        }
        requestChildOnScreen(viewOnFocusSearchFailed, null);
        return view;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateDefaultLayoutParams();
        }
        throw new IllegalStateException(C0402a.m108a(this, C0413b.m112a("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException(C0402a.m108a(this, C0413b.m112a("RecyclerView has no LayoutManager")));
    }

    @Nullable
    public Adapter getAdapter() {
        return this.mAdapter;
    }

    public int getAdapterPositionFor(ViewHolder viewHolder) {
        if (viewHolder.hasAnyOfTheFlags(524) || !viewHolder.isBound()) {
            return -1;
        }
        return this.mAdapterHelper.applyPendingUpdatesToPosition(viewHolder.mPosition);
    }

    @Override // android.view.View
    public int getBaseline() {
        LayoutManager layoutManager = this.mLayout;
        return layoutManager != null ? layoutManager.getBaseline() : super.getBaseline();
    }

    public long getChangedHolderKey(ViewHolder viewHolder) {
        return this.mAdapter.hasStableIds() ? viewHolder.getItemId() : viewHolder.mPosition;
    }

    public int getChildAdapterPosition(@NonNull View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getAdapterPosition();
        }
        return -1;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i7, int i8) {
        ChildDrawingOrderCallback childDrawingOrderCallback = this.mChildDrawingOrderCallback;
        return childDrawingOrderCallback == null ? super.getChildDrawingOrder(i7, i8) : childDrawingOrderCallback.onGetChildDrawingOrder(i7, i8);
    }

    public long getChildItemId(@NonNull View view) {
        ViewHolder childViewHolderInt;
        Adapter adapter = this.mAdapter;
        if (adapter == null || !adapter.hasStableIds() || (childViewHolderInt = getChildViewHolderInt(view)) == null) {
            return -1L;
        }
        return childViewHolderInt.getItemId();
    }

    public int getChildLayoutPosition(@NonNull View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }

    @Deprecated
    public int getChildPosition(@NonNull View view) {
        return getChildAdapterPosition(view);
    }

    public ViewHolder getChildViewHolder(@NonNull View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    @Nullable
    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect rect) {
        getDecoratedBoundsWithMarginsInt(view, rect);
    }

    @NonNull
    public EdgeEffectFactory getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    @Nullable
    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }

    public Rect getItemDecorInsetsForChild(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.mInsetsDirty) {
            return layoutParams.mDecorInsets;
        }
        if (this.mState.isPreLayout() && (layoutParams.isItemChanged() || layoutParams.isViewInvalid())) {
            return layoutParams.mDecorInsets;
        }
        Rect rect = layoutParams.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i7).getItemOffsets(this.mTempRect, view, this, this.mState);
            int i8 = rect.left;
            Rect rect2 = this.mTempRect;
            rect.left = i8 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        layoutParams.mInsetsDirty = false;
        return rect;
    }

    @NonNull
    public ItemDecoration getItemDecorationAt(int i7) {
        int itemDecorationCount = getItemDecorationCount();
        if (i7 >= 0 && i7 < itemDecorationCount) {
            return this.mItemDecorations.get(i7);
        }
        throw new IndexOutOfBoundsException(i7 + " is an invalid index for size " + itemDecorationCount);
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    @Nullable
    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0L;
    }

    @Nullable
    public OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    @NonNull
    public RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }

    public void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback() { // from class: android.support.v7.widget.RecyclerView.6
            public void dispatchUpdate(AdapterHelper.UpdateOp updateOp) {
                int i7 = updateOp.cmd;
                if (i7 == 1) {
                    RecyclerView recyclerView = RecyclerView.this;
                    recyclerView.mLayout.onItemsAdded(recyclerView, updateOp.positionStart, updateOp.itemCount);
                    return;
                }
                if (i7 == 2) {
                    RecyclerView recyclerView2 = RecyclerView.this;
                    recyclerView2.mLayout.onItemsRemoved(recyclerView2, updateOp.positionStart, updateOp.itemCount);
                } else if (i7 == 4) {
                    RecyclerView recyclerView3 = RecyclerView.this;
                    recyclerView3.mLayout.onItemsUpdated(recyclerView3, updateOp.positionStart, updateOp.itemCount, updateOp.payload);
                } else {
                    if (i7 != 8) {
                        return;
                    }
                    RecyclerView recyclerView4 = RecyclerView.this;
                    recyclerView4.mLayout.onItemsMoved(recyclerView4, updateOp.positionStart, updateOp.itemCount, 1);
                }
            }

            @Override // android.support.v7.widget.AdapterHelper.Callback
            public ViewHolder findViewHolder(int i7) {
                ViewHolder viewHolderFindViewHolderForPosition = RecyclerView.this.findViewHolderForPosition(i7, true);
                if (viewHolderFindViewHolderForPosition == null || RecyclerView.this.mChildHelper.isHidden(viewHolderFindViewHolderForPosition.itemView)) {
                    return null;
                }
                return viewHolderFindViewHolderForPosition;
            }

            @Override // android.support.v7.widget.AdapterHelper.Callback
            public void markViewHoldersUpdated(int i7, int i8, Object obj) {
                RecyclerView.this.viewRangeUpdate(i7, i8, obj);
                RecyclerView.this.mItemsChanged = true;
            }

            @Override // android.support.v7.widget.AdapterHelper.Callback
            public void offsetPositionsForAdd(int i7, int i8) {
                RecyclerView.this.offsetPositionRecordsForInsert(i7, i8);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            @Override // android.support.v7.widget.AdapterHelper.Callback
            public void offsetPositionsForMove(int i7, int i8) {
                RecyclerView.this.offsetPositionRecordsForMove(i7, i8);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            @Override // android.support.v7.widget.AdapterHelper.Callback
            public void offsetPositionsForRemovingInvisible(int i7, int i8) {
                RecyclerView.this.offsetPositionRecordsForRemove(i7, i8, true);
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mItemsAddedOrRemoved = true;
                recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i8;
            }

            @Override // android.support.v7.widget.AdapterHelper.Callback
            public void offsetPositionsForRemovingLaidOutOrNewView(int i7, int i8) {
                RecyclerView.this.offsetPositionRecordsForRemove(i7, i8, false);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            @Override // android.support.v7.widget.AdapterHelper.Callback
            public void onDispatchFirstPass(AdapterHelper.UpdateOp updateOp) {
                dispatchUpdate(updateOp);
            }

            @Override // android.support.v7.widget.AdapterHelper.Callback
            public void onDispatchSecondPass(AdapterHelper.UpdateOp updateOp) {
                dispatchUpdate(updateOp);
            }
        });
    }

    @VisibleForTesting
    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            throw new IllegalArgumentException(C0402a.m108a(this, C0413b.m112a("Trying to set fast scroller without both required drawables.")));
        }
        Resources resources = getContext().getResources();
        new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(C0311R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(C0311R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(C0311R.dimen.fastscroll_margin));
    }

    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() == 0) {
            return;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public boolean isAnimating() {
        ItemAnimator itemAnimator = this.mItemAnimator;
        return itemAnimator != null && itemAnimator.isRunning();
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    public boolean isLayoutFrozen() {
        return this.mLayoutFrozen;
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public void jumpToPositionForSmoothScroller(int i7) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            return;
        }
        layoutManager.scrollToPosition(i7);
        awakenScrollBars();
    }

    public void markItemDecorInsetsDirty() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i7 = 0; i7 < unfilteredChildCount; i7++) {
            ((LayoutParams) this.mChildHelper.getUnfilteredChildAt(i7).getLayoutParams()).mInsetsDirty = true;
        }
        this.mRecycler.markItemDecorInsetsDirty();
    }

    public void markKnownViewsInvalid() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i7 = 0; i7 < unfilteredChildCount; i7++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i7));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        this.mRecycler.markKnownViewsInvalid();
    }

    public void offsetChildrenHorizontal(@Px int i7) {
        int childCount = this.mChildHelper.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            this.mChildHelper.getChildAt(i8).offsetLeftAndRight(i7);
        }
    }

    public void offsetChildrenVertical(@Px int i7) {
        int childCount = this.mChildHelper.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            this.mChildHelper.getChildAt(i8).offsetTopAndBottom(i7);
        }
    }

    public void offsetPositionRecordsForInsert(int i7, int i8) {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i9 = 0; i9 < unfilteredChildCount; i9++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i9));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i7) {
                childViewHolderInt.offsetPosition(i8, false);
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForInsert(i7, i8);
        requestLayout();
    }

    public void offsetPositionRecordsForMove(int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        if (i7 < i8) {
            i11 = -1;
            i10 = i7;
            i9 = i8;
        } else {
            i9 = i7;
            i10 = i8;
            i11 = 1;
        }
        for (int i13 = 0; i13 < unfilteredChildCount; i13++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i13));
            if (childViewHolderInt != null && (i12 = childViewHolderInt.mPosition) >= i10 && i12 <= i9) {
                if (i12 == i7) {
                    childViewHolderInt.offsetPosition(i8 - i7, false);
                } else {
                    childViewHolderInt.offsetPosition(i11, false);
                }
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForMove(i7, i8);
        requestLayout();
    }

    public void offsetPositionRecordsForRemove(int i7, int i8, boolean z6) {
        int i9 = i7 + i8;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i10 = 0; i10 < unfilteredChildCount; i10++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i10));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i11 = childViewHolderInt.mPosition;
                if (i11 >= i9) {
                    childViewHolderInt.offsetPosition(-i8, z6);
                    this.mState.mStructureChanged = true;
                } else if (i11 >= i7) {
                    childViewHolderInt.flagRemovedAndOffsetPosition(i7 - 1, -i8, z6);
                    this.mState.mStructureChanged = true;
                }
            }
        }
        this.mRecycler.offsetPositionRecordsForRemove(i7, i8, z6);
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = true;
        this.mFirstLayoutComplete = this.mFirstLayoutComplete && !isLayoutRequested();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.dispatchAttachedToWindow(this);
        }
        this.mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK) {
            ThreadLocal<GapWorker> threadLocal = GapWorker.sGapWorker;
            GapWorker gapWorker = threadLocal.get();
            this.mGapWorker = gapWorker;
            if (gapWorker == null) {
                this.mGapWorker = new GapWorker();
                Display display = ViewCompat.getDisplay(this);
                float f7 = 60.0f;
                if (!isInEditMode() && display != null) {
                    float refreshRate = display.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f7 = refreshRate;
                    }
                }
                GapWorker gapWorker2 = this.mGapWorker;
                gapWorker2.mFrameIntervalNs = (long) (1.0E9f / f7);
                threadLocal.set(gapWorker2);
            }
            this.mGapWorker.add(this);
        }
    }

    public void onChildAttachedToWindow(@NonNull View view) {
    }

    public void onChildDetachedFromWindow(@NonNull View view) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        GapWorker gapWorker;
        super.onDetachedFromWindow();
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        stopScroll();
        this.mIsAttached = false;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
        if (!ALLOW_THREAD_GAP_WORK || (gapWorker = this.mGapWorker) == null) {
            return;
        }
        gapWorker.remove(this);
        this.mGapWorker = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.mItemDecorations.get(i7).onDraw(canvas, this, this.mState);
        }
    }

    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    public void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r5.mLayout
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.mLayoutFrozen
            if (r0 == 0) goto Lb
            return r1
        Lb:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L77
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L3c
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r5.mLayout
            boolean r0 = r0.canScrollVertically()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = 0
        L2d:
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r5.mLayout
            boolean r3 = r3.canScrollHorizontally()
            if (r3 == 0) goto L61
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L62
        L3c:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L60
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r5.mLayout
            boolean r3 = r3.canScrollVertically()
            if (r3 == 0) goto L55
            float r0 = -r0
            goto L61
        L55:
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r5.mLayout
            boolean r3 = r3.canScrollHorizontally()
            if (r3 == 0) goto L60
            r3 = r0
            r0 = 0
            goto L62
        L60:
            r0 = 0
        L61:
            r3 = 0
        L62:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L6a
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L77
        L6a:
            float r2 = r5.mScaledHorizontalScrollFactor
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.mScaledVerticalScrollFactor
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.scrollByInternal(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z6;
        if (this.mLayoutFrozen) {
            return false;
        }
        if (dispatchOnItemTouchIntercept(motionEvent)) {
            cancelTouch();
            return true;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            return false;
        }
        boolean zCanScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean zCanScrollVertically = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.mIgnoreMotionEventTillDown) {
                this.mIgnoreMotionEventTillDown = false;
            }
            this.mScrollPointerId = motionEvent.getPointerId(0);
            int x6 = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x6;
            this.mInitialTouchX = x6;
            int y6 = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y6;
            this.mInitialTouchY = y6;
            if (this.mScrollState == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
            }
            int[] iArr = this.mNestedOffsets;
            iArr[1] = 0;
            iArr[0] = 0;
            int i7 = zCanScrollHorizontally;
            if (zCanScrollVertically) {
                i7 = (zCanScrollHorizontally ? 1 : 0) | 2;
            }
            startNestedScroll(i7, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.clear();
            stopNestedScroll(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
            if (iFindPointerIndex < 0) {
                return false;
            }
            int x7 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y7 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.mScrollState != 1) {
                int i8 = x7 - this.mInitialTouchX;
                int i9 = y7 - this.mInitialTouchY;
                if (zCanScrollHorizontally == 0 || Math.abs(i8) <= this.mTouchSlop) {
                    z6 = false;
                } else {
                    this.mLastTouchX = x7;
                    z6 = true;
                }
                if (zCanScrollVertically && Math.abs(i9) > this.mTouchSlop) {
                    this.mLastTouchY = y7;
                    z6 = true;
                }
                if (z6) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            cancelTouch();
        } else if (actionMasked == 5) {
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            int x8 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x8;
            this.mInitialTouchX = x8;
            int y8 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y8;
            this.mInitialTouchY = y8;
        } else if (actionMasked == 6) {
            onPointerUp(motionEvent);
        }
        return this.mScrollState == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        TraceCompat.beginSection(TRACE_ON_LAYOUT_TAG);
        dispatchLayout();
        TraceCompat.endSection();
        this.mFirstLayoutComplete = true;
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            defaultOnMeasure(i7, i8);
            return;
        }
        boolean z6 = false;
        if (layoutManager.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i7);
            int mode2 = View.MeasureSpec.getMode(i8);
            this.mLayout.onMeasure(this.mRecycler, this.mState, i7, i8);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z6 = true;
            }
            if (z6 || this.mAdapter == null) {
                return;
            }
            if (this.mState.mLayoutStep == 1) {
                dispatchLayoutStep1();
            }
            this.mLayout.setMeasureSpecs(i7, i8);
            this.mState.mIsMeasuring = true;
            dispatchLayoutStep2();
            this.mLayout.setMeasuredDimensionFromChildren(i7, i8);
            if (this.mLayout.shouldMeasureTwice()) {
                this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(i7, i8);
                return;
            }
            return;
        }
        if (this.mHasFixedSize) {
            this.mLayout.onMeasure(this.mRecycler, this.mState, i7, i8);
            return;
        }
        if (this.mAdapterUpdateDuringMeasure) {
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            processAdapterUpdatesAndSetAnimationFlags();
            onExitLayoutOrScroll();
            State state = this.mState;
            if (state.mRunPredictiveAnimations) {
                state.mInPreLayout = true;
            } else {
                this.mAdapterHelper.consumeUpdatesInOnePass();
                this.mState.mInPreLayout = false;
            }
            this.mAdapterUpdateDuringMeasure = false;
            stopInterceptRequestLayout(false);
        } else if (this.mState.mRunPredictiveAnimations) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        Adapter adapter = this.mAdapter;
        if (adapter != null) {
            this.mState.mItemCount = adapter.getItemCount();
        } else {
            this.mState.mItemCount = 0;
        }
        startInterceptRequestLayout();
        this.mLayout.onMeasure(this.mRecycler, this.mState, i7, i8);
        stopInterceptRequestLayout(false);
        this.mState.mInPreLayout = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i7, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i7, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mPendingSavedState = savedState;
        super.onRestoreInstanceState(savedState.getSuperState());
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || (parcelable2 = this.mPendingSavedState.mLayoutState) == null) {
            return;
        }
        layoutManager.onRestoreInstanceState(parcelable2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            savedState.copyFrom(savedState2);
        } else {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null) {
                savedState.mLayoutState = layoutManager.onSaveInstanceState();
            } else {
                savedState.mLayoutState = null;
            }
        }
        return savedState;
    }

    public void onScrollStateChanged(int i7) {
    }

    public void onScrolled(@Px int i7, @Px int i8) {
    }

    @Override // android.view.View
    public void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        if (i7 == i9 && i8 == i10) {
            return;
        }
        invalidateGlows();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ec  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void postAnimationRunner() {
        if (this.mPostedAnimatorRunner || !this.mIsAttached) {
            return;
        }
        ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
        this.mPostedAnimatorRunner = true;
    }

    public void processDataSetCompletelyChanged(boolean z6) {
        this.mDispatchItemsChangedEvent = z6 | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    public void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo) {
        viewHolder.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.mViewInfoStore.addToOldChangeHolders(getChangedHolderKey(viewHolder), viewHolder);
        }
        this.mViewInfoStore.addToPreLayout(viewHolder, itemHolderInfo);
    }

    public void removeAndRecycleViews() {
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        }
        this.mRecycler.clear();
    }

    public boolean removeAnimatingView(View view) {
        startInterceptRequestLayout();
        boolean zRemoveViewIfHidden = this.mChildHelper.removeViewIfHidden(view);
        if (zRemoveViewIfHidden) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.unscrapView(childViewHolderInt);
            this.mRecycler.recycleViewHolderInternal(childViewHolderInt);
        }
        stopInterceptRequestLayout(!zRemoveViewIfHidden);
        return zRemoveViewIfHidden;
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z6) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb.append(childViewHolderInt);
                throw new IllegalArgumentException(C0402a.m108a(this, sb));
            }
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z6);
    }

    public void removeItemDecoration(@NonNull ItemDecoration itemDecoration) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(itemDecoration);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeItemDecorationAt(int i7) {
        int itemDecorationCount = getItemDecorationCount();
        if (i7 >= 0 && i7 < itemDecorationCount) {
            removeItemDecoration(getItemDecorationAt(i7));
            return;
        }
        throw new IndexOutOfBoundsException(i7 + " is an invalid index for size " + itemDecorationCount);
    }

    public void removeOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list == null) {
            return;
        }
        list.remove(onChildAttachStateChangeListener);
    }

    public void removeOnItemTouchListener(@NonNull OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.remove(onItemTouchListener);
        if (this.mActiveOnItemTouchListener == onItemTouchListener) {
            this.mActiveOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(@NonNull OnScrollListener onScrollListener) {
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            list.remove(onScrollListener);
        }
    }

    public void repositionShadowingViews() {
        ViewHolder viewHolder;
        int childCount = this.mChildHelper.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = this.mChildHelper.getChildAt(i7);
            ViewHolder childViewHolder = getChildViewHolder(childAt);
            if (childViewHolder != null && (viewHolder = childViewHolder.mShadowingHolder) != null) {
                View view = viewHolder.itemView;
                int left = childAt.getLeft();
                int top = childAt.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.onRequestChildFocus(this, this.mState, view, view2) && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z6) {
        int size = this.mOnItemTouchListeners.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.mOnItemTouchListeners.get(i7).onRequestDisallowInterceptTouchEvent(z6);
        }
        super.requestDisallowInterceptTouchEvent(z6);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutFrozen) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    public void saveOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i7 = 0; i7 < unfilteredChildCount; i7++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i7));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }

    @Override // android.view.View
    public void scrollBy(int i7, int i8) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || this.mLayoutFrozen) {
            return;
        }
        boolean zCanScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean zCanScrollVertically = this.mLayout.canScrollVertically();
        if (zCanScrollHorizontally || zCanScrollVertically) {
            if (!zCanScrollHorizontally) {
                i7 = 0;
            }
            if (!zCanScrollVertically) {
                i8 = 0;
            }
            scrollByInternal(i7, i8, null);
        }
    }

    public boolean scrollByInternal(int i7, int i8, MotionEvent motionEvent) {
        int i9;
        int i10;
        int i11;
        int i12;
        consumePendingUpdateOperations();
        if (this.mAdapter != null) {
            scrollStep(i7, i8, this.mScrollStepConsumed);
            int[] iArr = this.mScrollStepConsumed;
            int i13 = iArr[0];
            int i14 = iArr[1];
            i10 = i14;
            i11 = i13;
            i12 = i7 - i13;
            i9 = i8 - i14;
        } else {
            i9 = 0;
            i10 = 0;
            i11 = 0;
            i12 = 0;
        }
        if (!this.mItemDecorations.isEmpty()) {
            invalidate();
        }
        int i15 = i9;
        if (dispatchNestedScroll(i11, i10, i12, i9, this.mScrollOffset, 0)) {
            int i16 = this.mLastTouchX;
            int[] iArr2 = this.mScrollOffset;
            this.mLastTouchX = i16 - iArr2[0];
            this.mLastTouchY -= iArr2[1];
            if (motionEvent != null) {
                motionEvent.offsetLocation(iArr2[0], iArr2[1]);
            }
            int[] iArr3 = this.mNestedOffsets;
            int i17 = iArr3[0];
            int[] iArr4 = this.mScrollOffset;
            iArr3[0] = i17 + iArr4[0];
            iArr3[1] = iArr3[1] + iArr4[1];
        } else if (getOverScrollMode() != 2) {
            if (motionEvent != null && !MotionEventCompat.isFromSource(motionEvent, 8194)) {
                pullGlows(motionEvent.getX(), i12, motionEvent.getY(), i15);
            }
            considerReleasingGlowsOnScroll(i7, i8);
        }
        if (i11 != 0 || i10 != 0) {
            dispatchOnScrolled(i11, i10);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (i11 == 0 && i10 == 0) ? false : true;
    }

    public void scrollStep(int i7, int i8, @Nullable int[] iArr) {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        TraceCompat.beginSection(TRACE_SCROLL_TAG);
        fillRemainingScrollValues(this.mState);
        int iScrollHorizontallyBy = i7 != 0 ? this.mLayout.scrollHorizontallyBy(i7, this.mRecycler, this.mState) : 0;
        int iScrollVerticallyBy = i8 != 0 ? this.mLayout.scrollVerticallyBy(i8, this.mRecycler, this.mState) : 0;
        TraceCompat.endSection();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = iScrollHorizontallyBy;
            iArr[1] = iScrollVerticallyBy;
        }
    }

    @Override // android.view.View
    public void scrollTo(int i7, int i8) {
    }

    public void scrollToPosition(int i7) {
        if (this.mLayoutFrozen) {
            return;
        }
        stopScroll();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            return;
        }
        layoutManager.scrollToPosition(i7);
        awakenScrollBars();
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (shouldDeferAccessibilityEvent(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(@Nullable RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.mAccessibilityDelegate = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, recyclerViewAccessibilityDelegate);
    }

    public void setAdapter(@Nullable Adapter adapter) {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(@Nullable ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback == this.mChildDrawingOrderCallback) {
            return;
        }
        this.mChildDrawingOrderCallback = childDrawingOrderCallback;
        setChildrenDrawingOrderEnabled(childDrawingOrderCallback != null);
    }

    @VisibleForTesting
    public boolean setChildImportantForAccessibilityInternal(ViewHolder viewHolder, int i7) {
        if (!isComputingLayout()) {
            ViewCompat.setImportantForAccessibility(viewHolder.itemView, i7);
            return true;
        }
        viewHolder.mPendingAccessibilityState = i7;
        this.mPendingAccessibilityImportanceChange.add(viewHolder);
        return false;
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z6) {
        if (z6 != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z6;
        super.setClipToPadding(z6);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(@NonNull EdgeEffectFactory edgeEffectFactory) {
        Preconditions.checkNotNull(edgeEffectFactory);
        this.mEdgeEffectFactory = edgeEffectFactory;
        invalidateGlows();
    }

    public void setHasFixedSize(boolean z6) {
        this.mHasFixedSize = z6;
    }

    public void setItemAnimator(@Nullable ItemAnimator itemAnimator) {
        ItemAnimator itemAnimator2 = this.mItemAnimator;
        if (itemAnimator2 != null) {
            itemAnimator2.endAnimations();
            this.mItemAnimator.setListener(null);
        }
        this.mItemAnimator = itemAnimator;
        if (itemAnimator != null) {
            itemAnimator.setListener(this.mItemAnimatorListener);
        }
    }

    public void setItemViewCacheSize(int i7) {
        this.mRecycler.setViewCacheSize(i7);
    }

    public void setLayoutFrozen(boolean z6) {
        if (z6 != this.mLayoutFrozen) {
            assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if (z6) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.mLayoutFrozen = true;
                this.mIgnoreMotionEventTillDown = true;
                stopScroll();
                return;
            }
            this.mLayoutFrozen = false;
            if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
                requestLayout();
            }
            this.mLayoutWasDefered = false;
        }
    }

    public void setLayoutManager(@Nullable LayoutManager layoutManager) {
        if (layoutManager == this.mLayout) {
            return;
        }
        stopScroll();
        if (this.mLayout != null) {
            ItemAnimator itemAnimator = this.mItemAnimator;
            if (itemAnimator != null) {
                itemAnimator.endAnimations();
            }
            this.mLayout.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            this.mRecycler.clear();
            if (this.mIsAttached) {
                this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
            }
            this.mLayout.setRecyclerView(null);
            this.mLayout = null;
        } else {
            this.mRecycler.clear();
        }
        this.mChildHelper.removeAllViewsUnfiltered();
        this.mLayout = layoutManager;
        if (layoutManager != null) {
            if (layoutManager.mRecyclerView != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("LayoutManager ");
                sb.append(layoutManager);
                sb.append(" is already attached to a RecyclerView:");
                throw new IllegalArgumentException(C0402a.m108a(layoutManager.mRecyclerView, sb));
            }
            layoutManager.setRecyclerView(this);
            if (this.mIsAttached) {
                this.mLayout.dispatchAttachedToWindow(this);
            }
        }
        this.mRecycler.updateViewCacheSize();
        requestLayout();
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z6) {
        getScrollingChildHelper().setNestedScrollingEnabled(z6);
    }

    public void setOnFlingListener(@Nullable OnFlingListener onFlingListener) {
        this.mOnFlingListener = onFlingListener;
    }

    @Deprecated
    public void setOnScrollListener(@Nullable OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    public void setPreserveFocusAfterLayout(boolean z6) {
        this.mPreserveFocusAfterLayout = z6;
    }

    public void setRecycledViewPool(@Nullable RecycledViewPool recycledViewPool) {
        this.mRecycler.setRecycledViewPool(recycledViewPool);
    }

    public void setRecyclerListener(@Nullable RecyclerListener recyclerListener) {
        this.mRecyclerListener = recyclerListener;
    }

    public void setScrollState(int i7) {
        if (i7 == this.mScrollState) {
            return;
        }
        this.mScrollState = i7;
        if (i7 != 2) {
            stopScrollersInternal();
        }
        dispatchOnScrollStateChanged(i7);
    }

    public void setScrollingTouchSlop(int i7) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i7 != 1) {
            this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        } else {
            this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        }
    }

    public void setViewCacheExtension(@Nullable ViewCacheExtension viewCacheExtension) {
        this.mRecycler.setViewCacheExtension(viewCacheExtension);
    }

    public boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (!isComputingLayout()) {
            return false;
        }
        int contentChangeTypes = accessibilityEvent != null ? AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent) : 0;
        this.mEatenAccessibilityChangeFlags |= contentChangeTypes != 0 ? contentChangeTypes : 0;
        return true;
    }

    public void smoothScrollBy(@Px int i7, @Px int i8) {
        smoothScrollBy(i7, i8, null);
    }

    public void smoothScrollToPosition(int i7) {
        LayoutManager layoutManager;
        if (this.mLayoutFrozen || (layoutManager = this.mLayout) == null) {
            return;
        }
        layoutManager.smoothScrollToPosition(this, this.mState, i7);
    }

    public void startInterceptRequestLayout() {
        int i7 = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i7;
        if (i7 != 1 || this.mLayoutFrozen) {
            return;
        }
        this.mLayoutWasDefered = false;
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean startNestedScroll(int i7) {
        return getScrollingChildHelper().startNestedScroll(i7);
    }

    public void stopInterceptRequestLayout(boolean z6) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!z6 && !this.mLayoutFrozen) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z6 && this.mLayoutWasDefered && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutFrozen) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    public void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    public void swapAdapter(@Nullable Adapter adapter, boolean z6) {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, true, z6);
        processDataSetCompletelyChanged(true);
        requestLayout();
    }

    public void viewRangeUpdate(int i7, int i8, Object obj) {
        int i9;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int i10 = i7 + i8;
        for (int i11 = 0; i11 < unfilteredChildCount; i11++) {
            View unfilteredChildAt = this.mChildHelper.getUnfilteredChildAt(i11);
            ViewHolder childViewHolderInt = getChildViewHolderInt(unfilteredChildAt);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i9 = childViewHolderInt.mPosition) >= i7 && i9 < i10) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(obj);
                ((LayoutParams) unfilteredChildAt.getLayoutParams()).mInsetsDirty = true;
            }
        }
        this.mRecycler.viewRangeUpdate(i7, i8);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.support.v4.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i7, int i8, int[] iArr, int[] iArr2, int i9) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i7, i8, iArr, iArr2, i9);
    }

    @Override // android.support.v4.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, int[] iArr, int i11) {
        return getScrollingChildHelper().dispatchNestedScroll(i7, i8, i9, i10, iArr, i11);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.support.v7.widget.RecyclerView.ViewHolder findViewHolderForPosition(int r6, boolean r7) {
        /*
            r5 = this;
            android.support.v7.widget.ChildHelper r0 = r5.mChildHelper
            int r0 = r0.getUnfilteredChildCount()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            android.support.v7.widget.ChildHelper r3 = r5.mChildHelper
            android.view.View r3 = r3.getUnfilteredChildAt(r2)
            android.support.v7.widget.RecyclerView$ViewHolder r3 = getChildViewHolderInt(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.isRemoved()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.mPosition
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.getLayoutPosition()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            android.support.v7.widget.ChildHelper r1 = r5.mChildHelper
            android.view.View r4 = r3.itemView
            boolean r1 = r1.isHidden(r4)
            if (r1 == 0) goto L36
            r1 = r3
            goto L37
        L36:
            return r3
        L37:
            int r2 = r2 + 1
            goto L8
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.findViewHolderForPosition(int, boolean):android.support.v7.widget.RecyclerView$ViewHolder");
    }

    @Override // android.support.v4.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int i7) {
        return getScrollingChildHelper().hasNestedScrollingParent(i7);
    }

    public void onExitLayoutOrScroll(boolean z6) {
        int i7 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i7;
        if (i7 < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (z6) {
                dispatchContentChangedIfNecessary();
                dispatchPendingImportantForAccessibilityChanges();
            }
        }
    }

    public void smoothScrollBy(@Px int i7, @Px int i8, @Nullable Interpolator interpolator) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || this.mLayoutFrozen) {
            return;
        }
        if (!layoutManager.canScrollHorizontally()) {
            i7 = 0;
        }
        if (!this.mLayout.canScrollVertically()) {
            i8 = 0;
        }
        if (i7 == 0 && i8 == 0) {
            return;
        }
        this.mViewFlinger.smoothScrollBy(i7, i8, interpolator);
    }

    @Override // android.support.v4.view.NestedScrollingChild2
    public boolean startNestedScroll(int i7, int i8) {
        return getScrollingChildHelper().startNestedScroll(i7, i8);
    }

    @Override // android.support.v4.view.NestedScrollingChild2
    public void stopNestedScroll(int i7) {
        getScrollingChildHelper().stopNestedScroll(i7);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i7) throws NoSuchMethodException, SecurityException {
        super(context, attributeSet, i7);
        this.mObserver = new RecyclerViewDataObserver();
        this.mRecycler = new Recycler();
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new Runnable() { // from class: android.support.v7.widget.RecyclerView.1
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView = RecyclerView.this;
                if (!recyclerView.mFirstLayoutComplete || recyclerView.isLayoutRequested()) {
                    return;
                }
                RecyclerView recyclerView2 = RecyclerView.this;
                if (!recyclerView2.mIsAttached) {
                    recyclerView2.requestLayout();
                } else if (recyclerView2.mLayoutFrozen) {
                    recyclerView2.mLayoutWasDefered = true;
                } else {
                    recyclerView2.consumePendingUpdateOperations();
                }
            }
        };
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList<>();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new EdgeEffectFactory();
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        boolean z6 = true;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger();
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new GapWorker.LayoutPrefetchRegistryImpl() : null;
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new ItemAnimatorRestoreListener();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mNestedOffsets = new int[2];
        this.mScrollStepConsumed = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new Runnable() { // from class: android.support.v7.widget.RecyclerView.2
            @Override // java.lang.Runnable
            public void run() {
                ItemAnimator itemAnimator = RecyclerView.this.mItemAnimator;
                if (itemAnimator != null) {
                    itemAnimator.runPendingAnimations();
                }
                RecyclerView.this.mPostedAnimatorRunner = false;
            }
        };
        this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() { // from class: android.support.v7.widget.RecyclerView.4
            @Override // android.support.v7.widget.ViewInfoStore.ProcessCallback
            public void processAppeared(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2);
            }

            @Override // android.support.v7.widget.ViewInfoStore.ProcessCallback
            public void processDisappeared(ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.mRecycler.unscrapView(viewHolder);
                RecyclerView.this.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2);
            }

            @Override // android.support.v7.widget.ViewInfoStore.ProcessCallback
            public void processPersistent(ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                viewHolder.setIsRecyclable(false);
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mDataSetHasChangedAfterLayout) {
                    if (recyclerView.mItemAnimator.animateChange(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                        RecyclerView.this.postAnimationRunner();
                    }
                } else if (recyclerView.mItemAnimator.animatePersistence(viewHolder, itemHolderInfo, itemHolderInfo2)) {
                    RecyclerView.this.postAnimationRunner();
                }
            }

            @Override // android.support.v7.widget.ViewInfoStore.ProcessCallback
            public void unused(ViewHolder viewHolder) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mLayout.removeAndRecycleView(viewHolder.itemView, recyclerView.mRecycler);
            }
        };
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, CLIP_TO_PADDING_ATTR, i7, 0);
            this.mClipToPadding = typedArrayObtainStyledAttributes.getBoolean(0, true);
            typedArrayObtainStyledAttributes.recycle();
        } else {
            this.mClipToPadding = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, context);
        this.mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, context);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        initAdapterManager();
        initChildrenHelper();
        initAutofill();
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, C0311R.styleable.RecyclerView, i7, 0);
            String string = typedArrayObtainStyledAttributes2.getString(C0311R.styleable.RecyclerView_layoutManager);
            if (typedArrayObtainStyledAttributes2.getInt(C0311R.styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
                setDescendantFocusability(262144);
            }
            boolean z7 = typedArrayObtainStyledAttributes2.getBoolean(C0311R.styleable.RecyclerView_fastScrollEnabled, false);
            this.mEnableFastScroller = z7;
            if (z7) {
                initFastScroller((StateListDrawable) typedArrayObtainStyledAttributes2.getDrawable(C0311R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), typedArrayObtainStyledAttributes2.getDrawable(C0311R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) typedArrayObtainStyledAttributes2.getDrawable(C0311R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), typedArrayObtainStyledAttributes2.getDrawable(C0311R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
            }
            typedArrayObtainStyledAttributes2.recycle();
            createLayoutManager(context, string, attributeSet, i7, 0);
            TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, NESTED_SCROLLING_ATTRS, i7, 0);
            z6 = typedArrayObtainStyledAttributes3.getBoolean(0, true);
            typedArrayObtainStyledAttributes3.recycle();
        } else {
            setDescendantFocusability(262144);
        }
        setNestedScrollingEnabled(z6);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public final Rect mDecorInsets;
        public boolean mInsetsDirty;
        public boolean mPendingInvalidate;
        public ViewHolder mViewHolder;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public int getViewAdapterPosition() {
            return this.mViewHolder.getAdapterPosition();
        }

        public int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }

        @Deprecated
        public int getViewPosition() {
            return this.mViewHolder.getPosition();
        }

        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }

        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }

        public boolean isViewInvalid() {
            return this.mViewHolder.isInvalid();
        }

        public boolean viewNeedsUpdate() {
            return this.mViewHolder.needsUpdate();
        }

        public LayoutParams(int i7, int i8) {
            super(i7, i8);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v7.widget.RecyclerView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        public Parcelable mLayoutState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.mLayoutState = parcel.readParcelable(classLoader == null ? LayoutManager.class.getClassLoader() : classLoader);
        }

        public void copyFrom(SavedState savedState) {
            this.mLayoutState = savedState.mLayoutState;
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeParcelable(this.mLayoutState, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException(C0402a.m108a(this, C0413b.m112a("RecyclerView has no LayoutManager")));
    }

    public void addItemDecoration(@NonNull ItemDecoration itemDecoration) {
        addItemDecoration(itemDecoration, -1);
    }
}
