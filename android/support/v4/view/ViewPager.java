package android.support.v4.view;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.constraint.solver.C0084a;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private int mActivePointerId;
    public PagerAdapter mAdapter;
    private List<OnAdapterChangeListener> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    public int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private int mPageTransformerLayerType;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffect mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    public static final int[] LAYOUT_ATTRS = {R.attr.layout_gravity};
    private static final Comparator<ItemInfo> COMPARATOR = new Comparator<ItemInfo>() { // from class: android.support.v4.view.ViewPager.1
        @Override // java.util.Comparator
        public int compare(ItemInfo itemInfo, ItemInfo itemInfo2) {
            return itemInfo.position - itemInfo2.position;
        }
    };
    private static final Interpolator sInterpolator = new Interpolator() { // from class: android.support.v4.view.ViewPager.2
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            float f8 = f7 - 1.0f;
            return (f8 * f8 * f8 * f8 * f8) + 1.0f;
        }
    };
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DecorView {
    }

    public static class ItemInfo {
        public Object object;
        public float offset;
        public int position;
        public boolean scrolling;
        public float widthFactor;
    }

    public class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        public MyAccessibilityDelegate() {
        }

        private boolean canScroll() {
            PagerAdapter pagerAdapter = ViewPager.this.mAdapter;
            return pagerAdapter != null && pagerAdapter.getCount() > 1;
        }

        @Override // android.support.v4.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            PagerAdapter pagerAdapter;
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(canScroll());
            if (accessibilityEvent.getEventType() != 4096 || (pagerAdapter = ViewPager.this.mAdapter) == null) {
                return;
            }
            accessibilityEvent.setItemCount(pagerAdapter.getCount());
            accessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
            accessibilityEvent.setToIndex(ViewPager.this.mCurItem);
        }

        @Override // android.support.v4.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
            accessibilityNodeInfoCompat.setScrollable(canScroll());
            if (ViewPager.this.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
            }
        }

        @Override // android.support.v4.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i7, Bundle bundle) throws Resources.NotFoundException {
            if (super.performAccessibilityAction(view, i7, bundle)) {
                return true;
            }
            if (i7 == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.mCurItem + 1);
                return true;
            }
            if (i7 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.mCurItem - 1);
            return true;
        }
    }

    public interface OnAdapterChangeListener {
        void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2);
    }

    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i7);

        void onPageScrolled(int i7, float f7, @Px int i8);

        void onPageSelected(int i7);
    }

    public interface PageTransformer {
        void transformPage(@NonNull View view, float f7);
    }

    public class PagerObserver extends DataSetObserver {
        public PagerObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() throws Resources.NotFoundException {
            ViewPager.this.dataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() throws Resources.NotFoundException {
            ViewPager.this.dataSetChanged();
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v4.view.ViewPager.SavedState.1
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
        public Parcelable adapterState;
        public ClassLoader loader;
        public int position;

        public SavedState(@NonNull Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("FragmentPager.SavedState{");
            sbM112a.append(Integer.toHexString(System.identityHashCode(this)));
            sbM112a.append(" position=");
            return C0084a.m96a(sbM112a, this.position, "}");
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.position);
            parcel.writeParcelable(this.adapterState, i7);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.position = parcel.readInt();
            this.adapterState = parcel.readParcelable(classLoader);
            this.loader = classLoader;
        }
    }

    public static class SimpleOnPageChangeListener implements OnPageChangeListener {
        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i7) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i7, float f7, int i8) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i7) {
        }
    }

    public static class ViewPositionComparator implements Comparator<View> {
        @Override // java.util.Comparator
        public int compare(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            boolean z6 = layoutParams.isDecor;
            return z6 != layoutParams2.isDecor ? z6 ? 1 : -1 : layoutParams.position - layoutParams2.position;
        }
    }

    public ViewPager(@NonNull Context context) {
        super(context);
        this.mItems = new ArrayList<>();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() { // from class: android.support.v4.view.ViewPager.3
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                ViewPager.this.setScrollState(0);
                ViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        initViewPager();
    }

    private void calculatePageOffsets(ItemInfo itemInfo, int i7, ItemInfo itemInfo2) {
        int i8;
        int i9;
        ItemInfo itemInfo3;
        ItemInfo itemInfo4;
        int count = this.mAdapter.getCount();
        int clientWidth = getClientWidth();
        float f7 = clientWidth > 0 ? this.mPageMargin / clientWidth : 0.0f;
        if (itemInfo2 != null) {
            int i10 = itemInfo2.position;
            int i11 = itemInfo.position;
            if (i10 < i11) {
                float pageWidth = itemInfo2.offset + itemInfo2.widthFactor + f7;
                int i12 = i10 + 1;
                int i13 = 0;
                while (i12 <= itemInfo.position && i13 < this.mItems.size()) {
                    ItemInfo itemInfo5 = this.mItems.get(i13);
                    while (true) {
                        itemInfo4 = itemInfo5;
                        if (i12 <= itemInfo4.position || i13 >= this.mItems.size() - 1) {
                            break;
                        }
                        i13++;
                        itemInfo5 = this.mItems.get(i13);
                    }
                    while (i12 < itemInfo4.position) {
                        pageWidth += this.mAdapter.getPageWidth(i12) + f7;
                        i12++;
                    }
                    itemInfo4.offset = pageWidth;
                    pageWidth += itemInfo4.widthFactor + f7;
                    i12++;
                }
            } else if (i10 > i11) {
                int size = this.mItems.size() - 1;
                float pageWidth2 = itemInfo2.offset;
                while (true) {
                    i10--;
                    if (i10 < itemInfo.position || size < 0) {
                        break;
                    }
                    ItemInfo itemInfo6 = this.mItems.get(size);
                    while (true) {
                        itemInfo3 = itemInfo6;
                        if (i10 >= itemInfo3.position || size <= 0) {
                            break;
                        }
                        size--;
                        itemInfo6 = this.mItems.get(size);
                    }
                    while (i10 > itemInfo3.position) {
                        pageWidth2 -= this.mAdapter.getPageWidth(i10) + f7;
                        i10--;
                    }
                    pageWidth2 -= itemInfo3.widthFactor + f7;
                    itemInfo3.offset = pageWidth2;
                }
            }
        }
        int size2 = this.mItems.size();
        float pageWidth3 = itemInfo.offset;
        int i14 = itemInfo.position;
        int i15 = i14 - 1;
        this.mFirstOffset = i14 == 0 ? pageWidth3 : -3.4028235E38f;
        int i16 = count - 1;
        this.mLastOffset = i14 == i16 ? (itemInfo.widthFactor + pageWidth3) - 1.0f : Float.MAX_VALUE;
        int i17 = i7 - 1;
        while (i17 >= 0) {
            ItemInfo itemInfo7 = this.mItems.get(i17);
            while (true) {
                i9 = itemInfo7.position;
                if (i15 <= i9) {
                    break;
                }
                pageWidth3 -= this.mAdapter.getPageWidth(i15) + f7;
                i15--;
            }
            pageWidth3 -= itemInfo7.widthFactor + f7;
            itemInfo7.offset = pageWidth3;
            if (i9 == 0) {
                this.mFirstOffset = pageWidth3;
            }
            i17--;
            i15--;
        }
        float pageWidth4 = itemInfo.offset + itemInfo.widthFactor + f7;
        int i18 = itemInfo.position + 1;
        int i19 = i7 + 1;
        while (i19 < size2) {
            ItemInfo itemInfo8 = this.mItems.get(i19);
            while (true) {
                i8 = itemInfo8.position;
                if (i18 >= i8) {
                    break;
                }
                pageWidth4 += this.mAdapter.getPageWidth(i18) + f7;
                i18++;
            }
            if (i8 == i16) {
                this.mLastOffset = (itemInfo8.widthFactor + pageWidth4) - 1.0f;
            }
            itemInfo8.offset = pageWidth4;
            pageWidth4 += itemInfo8.widthFactor + f7;
            i19++;
            i18++;
        }
        this.mNeedCalculatePageOffsets = false;
    }

    private void completeScroll(boolean z6) {
        boolean z7 = this.mScrollState == 2;
        if (z7) {
            setScrollingCacheEnabled(false);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        pageScrolled(currX);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        for (int i7 = 0; i7 < this.mItems.size(); i7++) {
            ItemInfo itemInfo = this.mItems.get(i7);
            if (itemInfo.scrolling) {
                itemInfo.scrolling = false;
                z7 = true;
            }
        }
        if (z7) {
            if (z6) {
                ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
            } else {
                this.mEndScrollRunnable.run();
            }
        }
    }

    private int determineTargetPage(int i7, float f7, int i8, int i9) {
        if (Math.abs(i9) <= this.mFlingDistance || Math.abs(i8) <= this.mMinimumVelocity) {
            i7 += (int) (f7 + (i7 >= this.mCurItem ? 0.4f : 0.6f));
        } else if (i8 <= 0) {
            i7++;
        }
        if (this.mItems.size() <= 0) {
            return i7;
        }
        return Math.max(this.mItems.get(0).position, Math.min(i7, this.mItems.get(r4.size() - 1).position));
    }

    private void dispatchOnPageScrolled(int i7, float f7, int i8) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i7, f7, i8);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i9 = 0; i9 < size; i9++) {
                OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListeners.get(i9);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.onPageScrolled(i7, f7, i8);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageScrolled(i7, f7, i8);
        }
    }

    private void dispatchOnPageSelected(int i7) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i7);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i8 = 0; i8 < size; i8++) {
                OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListeners.get(i8);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.onPageSelected(i7);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageSelected(i7);
        }
    }

    private void dispatchOnScrollStateChanged(int i7) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i7);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i8 = 0; i8 < size; i8++) {
                OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListeners.get(i8);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.onPageScrollStateChanged(i7);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageScrollStateChanged(i7);
        }
    }

    private void enableLayers(boolean z6) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            getChildAt(i7).setLayerType(z6 ? this.mPageTransformerLayerType : 0, null);
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left = viewGroup.getLeft() + rect.left;
            rect.right = viewGroup.getRight() + rect.right;
            rect.top = viewGroup.getTop() + rect.top;
            rect.bottom = viewGroup.getBottom() + rect.bottom;
            parent = viewGroup.getParent();
        }
        return rect;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private ItemInfo infoForCurrentScrollPosition() {
        int i7;
        int clientWidth = getClientWidth();
        float f7 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f8 = clientWidth > 0 ? this.mPageMargin / clientWidth : 0.0f;
        ItemInfo itemInfo = null;
        float f9 = 0.0f;
        int i8 = -1;
        int i9 = 0;
        boolean z6 = true;
        while (i9 < this.mItems.size()) {
            ItemInfo itemInfo2 = this.mItems.get(i9);
            if (!z6 && itemInfo2.position != (i7 = i8 + 1)) {
                itemInfo2 = this.mTempItem;
                itemInfo2.offset = f7 + f9 + f8;
                itemInfo2.position = i7;
                itemInfo2.widthFactor = this.mAdapter.getPageWidth(i7);
                i9--;
            }
            f7 = itemInfo2.offset;
            float f10 = itemInfo2.widthFactor + f7 + f8;
            if (!z6 && scrollX < f7) {
                return itemInfo;
            }
            if (scrollX < f10 || i9 == this.mItems.size() - 1) {
                return itemInfo2;
            }
            i8 = itemInfo2.position;
            f9 = itemInfo2.widthFactor;
            i9++;
            itemInfo = itemInfo2;
            z6 = false;
        }
        return itemInfo;
    }

    private static boolean isDecorView(@NonNull View view) {
        return view.getClass().getAnnotation(DecorView.class) != null;
    }

    private boolean isGutterDrag(float f7, float f8) {
        return (f7 < ((float) this.mGutterSize) && f8 > 0.0f) || (f7 > ((float) (getWidth() - this.mGutterSize)) && f8 < 0.0f);
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            int i7 = actionIndex == 0 ? 1 : 0;
            this.mLastMotionX = motionEvent.getX(i7);
            this.mActivePointerId = motionEvent.getPointerId(i7);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private boolean pageScrolled(int i7) {
        if (this.mItems.size() == 0) {
            if (this.mFirstLayout) {
                return false;
            }
            this.mCalledSuper = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        ItemInfo itemInfoInfoForCurrentScrollPosition = infoForCurrentScrollPosition();
        int clientWidth = getClientWidth();
        int i8 = this.mPageMargin;
        int i9 = clientWidth + i8;
        float f7 = clientWidth;
        int i10 = itemInfoInfoForCurrentScrollPosition.position;
        float f8 = ((i7 / f7) - itemInfoInfoForCurrentScrollPosition.offset) / (itemInfoInfoForCurrentScrollPosition.widthFactor + (i8 / f7));
        this.mCalledSuper = false;
        onPageScrolled(i10, f8, (int) (i9 * f8));
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private boolean performDrag(float f7) {
        boolean z6;
        boolean z7;
        float f8 = this.mLastMotionX - f7;
        this.mLastMotionX = f7;
        float scrollX = getScrollX() + f8;
        float clientWidth = getClientWidth();
        float f9 = this.mFirstOffset * clientWidth;
        float f10 = this.mLastOffset * clientWidth;
        boolean z8 = false;
        ItemInfo itemInfo = this.mItems.get(0);
        ArrayList<ItemInfo> arrayList = this.mItems;
        ItemInfo itemInfo2 = arrayList.get(arrayList.size() - 1);
        if (itemInfo.position != 0) {
            f9 = itemInfo.offset * clientWidth;
            z6 = false;
        } else {
            z6 = true;
        }
        if (itemInfo2.position != this.mAdapter.getCount() - 1) {
            f10 = itemInfo2.offset * clientWidth;
            z7 = false;
        } else {
            z7 = true;
        }
        if (scrollX < f9) {
            if (z6) {
                this.mLeftEdge.onPull(Math.abs(f9 - scrollX) / clientWidth);
                z8 = true;
            }
            scrollX = f9;
        } else if (scrollX > f10) {
            if (z7) {
                this.mRightEdge.onPull(Math.abs(scrollX - f10) / clientWidth);
                z8 = true;
            }
            scrollX = f10;
        }
        int i7 = (int) scrollX;
        this.mLastMotionX = (scrollX - i7) + this.mLastMotionX;
        scrollTo(i7, getScrollY());
        pageScrolled(i7);
        return z8;
    }

    private void recomputeScrollPosition(int i7, int i8, int i9, int i10) {
        if (i8 > 0 && !this.mItems.isEmpty()) {
            if (!this.mScroller.isFinished()) {
                this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i8 - getPaddingLeft()) - getPaddingRight()) + i10)) * (((i7 - getPaddingLeft()) - getPaddingRight()) + i9)), getScrollY());
                return;
            }
        }
        ItemInfo itemInfoInfoForPosition = infoForPosition(this.mCurItem);
        int iMin = (int) ((itemInfoInfoForPosition != null ? Math.min(itemInfoInfoForPosition.offset, this.mLastOffset) : 0.0f) * ((i7 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            completeScroll(false);
            scrollTo(iMin, getScrollY());
        }
    }

    private void removeNonDecorViews() {
        int i7 = 0;
        while (i7 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i7).getLayoutParams()).isDecor) {
                removeViewAt(i7);
                i7--;
            }
            i7++;
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z6) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z6);
        }
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        endDrag();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        return this.mLeftEdge.isFinished() || this.mRightEdge.isFinished();
    }

    private void scrollToItem(int i7, boolean z6, int i8, boolean z7) throws Resources.NotFoundException {
        int iMax;
        ItemInfo itemInfoInfoForPosition = infoForPosition(i7);
        if (itemInfoInfoForPosition != null) {
            iMax = (int) (Math.max(this.mFirstOffset, Math.min(itemInfoInfoForPosition.offset, this.mLastOffset)) * getClientWidth());
        } else {
            iMax = 0;
        }
        if (z6) {
            smoothScrollTo(iMax, 0, i8);
            if (z7) {
                dispatchOnPageSelected(i7);
                return;
            }
            return;
        }
        if (z7) {
            dispatchOnPageSelected(i7);
        }
        completeScroll(false);
        scrollTo(iMax, 0);
        pageScrolled(iMax);
    }

    private void setScrollingCacheEnabled(boolean z6) {
        if (this.mScrollingCacheEnabled != z6) {
            this.mScrollingCacheEnabled = z6;
        }
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                this.mDrawingOrderedChildren.add(getChildAt(i7));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i7, int i8) {
        ItemInfo itemInfoInfoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i9 = 0; i9 < getChildCount(); i9++) {
                View childAt = getChildAt(i9);
                if (childAt.getVisibility() == 0 && (itemInfoInfoForChild = infoForChild(childAt)) != null && itemInfoInfoForChild.position == this.mCurItem) {
                    childAt.addFocusables(arrayList, i7, i8);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i8 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    public ItemInfo addNewItem(int i7, int i8) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.position = i7;
        itemInfo.object = this.mAdapter.instantiateItem((ViewGroup) this, i7);
        itemInfo.widthFactor = this.mAdapter.getPageWidth(i7);
        if (i8 < 0 || i8 >= this.mItems.size()) {
            this.mItems.add(itemInfo);
        } else {
            this.mItems.add(i8, itemInfo);
        }
        return itemInfo;
    }

    public void addOnAdapterChangeListener(@NonNull OnAdapterChangeListener onAdapterChangeListener) {
        if (this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }
        this.mAdapterChangeListeners.add(onAdapterChangeListener);
    }

    public void addOnPageChangeListener(@NonNull OnPageChangeListener onPageChangeListener) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(onPageChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        ItemInfo itemInfoInfoForChild;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() == 0 && (itemInfoInfoForChild = infoForChild(childAt)) != null && itemInfoInfoForChild.position == this.mCurItem) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        boolean zIsDecorView = layoutParams2.isDecor | isDecorView(view);
        layoutParams2.isDecor = zIsDecorView;
        if (!this.mInLayout) {
            super.addView(view, i7, layoutParams);
        } else {
            if (zIsDecorView) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            layoutParams2.needsMeasure = true;
            addViewInLayout(view, i7, layoutParams);
        }
    }

    public boolean arrowScroll(int i7) throws Resources.NotFoundException {
        boolean z6;
        boolean zRequestFocus;
        View viewFindFocus = findFocus();
        boolean zPageLeft = false;
        if (viewFindFocus == this) {
            viewFindFocus = null;
        } else if (viewFindFocus != null) {
            ViewParent parent = viewFindFocus.getParent();
            while (true) {
                if (!(parent instanceof ViewGroup)) {
                    z6 = false;
                    break;
                }
                if (parent == this) {
                    z6 = true;
                    break;
                }
                parent = parent.getParent();
            }
            if (!z6) {
                StringBuilder sb = new StringBuilder();
                sb.append(viewFindFocus.getClass().getSimpleName());
                for (ViewParent parent2 = viewFindFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ");
                    sb.append(parent2.getClass().getSimpleName());
                }
                viewFindFocus = null;
            }
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i7);
        if (viewFindNextFocus != null && viewFindNextFocus != viewFindFocus) {
            if (i7 == 17) {
                zRequestFocus = (viewFindFocus == null || getChildRectInPagerCoordinates(this.mTempRect, viewFindNextFocus).left < getChildRectInPagerCoordinates(this.mTempRect, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : pageLeft();
            } else if (i7 == 66) {
                zRequestFocus = (viewFindFocus == null || getChildRectInPagerCoordinates(this.mTempRect, viewFindNextFocus).left > getChildRectInPagerCoordinates(this.mTempRect, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : pageRight();
            }
            zPageLeft = zRequestFocus;
        } else if (i7 == 17 || i7 == 1) {
            zPageLeft = pageLeft();
        } else if (i7 == 66 || i7 == 2) {
            zPageLeft = pageRight();
        }
        if (zPageLeft) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i7));
        }
        return zPageLeft;
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(motionEventObtain);
        motionEventObtain.recycle();
        this.mFakeDragBeginTime = jUptimeMillis;
        return true;
    }

    public boolean canScroll(View view, boolean z6, int i7, int i8, int i9) {
        int i10;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i11 = i8 + scrollX;
                if (i11 >= childAt.getLeft() && i11 < childAt.getRight() && (i10 = i9 + scrollY) >= childAt.getTop() && i10 < childAt.getBottom() && canScroll(childAt, true, i7, i11 - childAt.getLeft(), i10 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z6 && view.canScrollHorizontally(-i7);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i7) {
        if (this.mAdapter == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i7 < 0 ? scrollX > ((int) (((float) clientWidth) * this.mFirstOffset)) : i7 > 0 && scrollX < ((int) (((float) clientWidth) * this.mLastOffset));
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public void clearOnPageChangeListeners() {
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            completeScroll(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!pageScrolled(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void dataSetChanged() throws Resources.NotFoundException {
        int count = this.mAdapter.getCount();
        this.mExpectedAdapterCount = count;
        boolean z6 = this.mItems.size() < (this.mOffscreenPageLimit * 2) + 1 && this.mItems.size() < count;
        int iMax = this.mCurItem;
        int i7 = 0;
        boolean z7 = false;
        while (i7 < this.mItems.size()) {
            ItemInfo itemInfo = this.mItems.get(i7);
            int itemPosition = this.mAdapter.getItemPosition(itemInfo.object);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.mItems.remove(i7);
                    i7--;
                    if (!z7) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        z7 = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, itemInfo.position, itemInfo.object);
                    int i8 = this.mCurItem;
                    if (i8 == itemInfo.position) {
                        iMax = Math.max(0, Math.min(i8, count - 1));
                    }
                } else {
                    int i9 = itemInfo.position;
                    if (i9 != itemPosition) {
                        if (i9 == this.mCurItem) {
                            iMax = itemPosition;
                        }
                        itemInfo.position = itemPosition;
                    }
                }
                z6 = true;
            }
            i7++;
        }
        if (z7) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (z6) {
            int childCount = getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i10).getLayoutParams();
                if (!layoutParams.isDecor) {
                    layoutParams.widthFactor = 0.0f;
                }
            }
            setCurrentItemInternal(iMax, false, true);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ItemInfo itemInfoInfoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() == 0 && (itemInfoInfoForChild = infoForChild(childAt)) != null && itemInfoInfoForChild.position == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    public float distanceInfluenceForSnapDuration(float f7) {
        return (float) Math.sin((f7 - 0.5f) * 0.47123894f);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        PagerAdapter pagerAdapter;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (pagerAdapter = this.mAdapter) != null && pagerAdapter.getCount() > 1)) {
            if (!this.mLeftEdge.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate(getPaddingTop() + (-height), this.mFirstOffset * width);
                this.mLeftEdge.setSize(height, width);
                zDraw = false | this.mLeftEdge.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.mRightEdge.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.mLastOffset + 1.0f)) * width2);
                this.mRightEdge.setSize(height2, width2);
                zDraw |= this.mRightEdge.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (zDraw) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public void endFakeDrag() throws Resources.NotFoundException {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.mAdapter != null) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
            this.mPopulatePending = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            ItemInfo itemInfoInfoForCurrentScrollPosition = infoForCurrentScrollPosition();
            setCurrentItemInternal(determineTargetPage(itemInfoInfoForCurrentScrollPosition.position, ((scrollX / clientWidth) - itemInfoInfoForCurrentScrollPosition.offset) / itemInfoInfoForCurrentScrollPosition.widthFactor, xVelocity, (int) (this.mLastMotionX - this.mInitialMotionX)), true, true, xVelocity);
        }
        endDrag();
        this.mFakeDragging = false;
    }

    public boolean executeKeyEvent(@NonNull KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return keyEvent.hasModifiers(2) ? pageLeft() : arrowScroll(17);
            }
            if (keyCode == 22) {
                return keyEvent.hasModifiers(2) ? pageRight() : arrowScroll(66);
            }
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return arrowScroll(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return arrowScroll(1);
                }
            }
        }
        return false;
    }

    public void fakeDragBy(float f7) {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.mAdapter == null) {
            return;
        }
        this.mLastMotionX += f7;
        float scrollX = getScrollX() - f7;
        float clientWidth = getClientWidth();
        float f8 = this.mFirstOffset * clientWidth;
        float f9 = this.mLastOffset * clientWidth;
        ItemInfo itemInfo = this.mItems.get(0);
        ItemInfo itemInfo2 = this.mItems.get(r4.size() - 1);
        if (itemInfo.position != 0) {
            f8 = itemInfo.offset * clientWidth;
        }
        if (itemInfo2.position != this.mAdapter.getCount() - 1) {
            f9 = itemInfo2.offset * clientWidth;
        }
        if (scrollX < f8) {
            scrollX = f8;
        } else if (scrollX > f9) {
            scrollX = f9;
        }
        int i7 = (int) scrollX;
        this.mLastMotionX = (scrollX - i7) + this.mLastMotionX;
        scrollTo(i7, getScrollY());
        pageScrolled(i7);
        MotionEvent motionEventObtain = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, this.mLastMotionX, 0.0f, 0);
        this.mVelocityTracker.addMovement(motionEventObtain);
        motionEventObtain.recycle();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    @Nullable
    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i7, int i8) {
        if (this.mDrawingOrder == 2) {
            i8 = (i7 - 1) - i8;
        }
        return ((LayoutParams) this.mDrawingOrderedChildren.get(i8).getLayoutParams()).childIndex;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    public ItemInfo infoForAnyChild(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return infoForChild(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    public ItemInfo infoForChild(View view) {
        for (int i7 = 0; i7 < this.mItems.size(); i7++) {
            ItemInfo itemInfo = this.mItems.get(i7);
            if (this.mAdapter.isViewFromObject(view, itemInfo.object)) {
                return itemInfo;
            }
        }
        return null;
    }

    public ItemInfo infoForPosition(int i7) {
        for (int i8 = 0; i8 < this.mItems.size(); i8++) {
            ItemInfo itemInfo = this.mItems.get(i8);
            if (itemInfo.position == i7) {
                return itemInfo;
            }
        }
        return null;
    }

    public void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f7 = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f7);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context);
        this.mRightEdge = new EdgeEffect(context);
        this.mFlingDistance = (int) (25.0f * f7);
        this.mCloseEnough = (int) (2.0f * f7);
        this.mDefaultGutterSize = (int) (f7 * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() { // from class: android.support.v4.view.ViewPager.4
            private final Rect mTempRect = new Rect();

            @Override // android.support.v4.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat windowInsetsCompatOnApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                if (windowInsetsCompatOnApplyWindowInsets.isConsumed()) {
                    return windowInsetsCompatOnApplyWindowInsets;
                }
                Rect rect = this.mTempRect;
                rect.left = windowInsetsCompatOnApplyWindowInsets.getSystemWindowInsetLeft();
                rect.top = windowInsetsCompatOnApplyWindowInsets.getSystemWindowInsetTop();
                rect.right = windowInsetsCompatOnApplyWindowInsets.getSystemWindowInsetRight();
                rect.bottom = windowInsetsCompatOnApplyWindowInsets.getSystemWindowInsetBottom();
                int childCount = ViewPager.this.getChildCount();
                for (int i7 = 0; i7 < childCount; i7++) {
                    WindowInsetsCompat windowInsetsCompatDispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(ViewPager.this.getChildAt(i7), windowInsetsCompatOnApplyWindowInsets);
                    rect.left = Math.min(windowInsetsCompatDispatchApplyWindowInsets.getSystemWindowInsetLeft(), rect.left);
                    rect.top = Math.min(windowInsetsCompatDispatchApplyWindowInsets.getSystemWindowInsetTop(), rect.top);
                    rect.right = Math.min(windowInsetsCompatDispatchApplyWindowInsets.getSystemWindowInsetRight(), rect.right);
                    rect.bottom = Math.min(windowInsetsCompatDispatchApplyWindowInsets.getSystemWindowInsetBottom(), rect.bottom);
                }
                return windowInsetsCompatOnApplyWindowInsets.replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i7;
        float f7;
        float f8;
        super.onDraw(canvas);
        if (this.mPageMargin <= 0 || this.mMarginDrawable == null || this.mItems.size() <= 0 || this.mAdapter == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f9 = this.mPageMargin / width;
        int i8 = 0;
        ItemInfo itemInfo = this.mItems.get(0);
        float f10 = itemInfo.offset;
        int size = this.mItems.size();
        int i9 = itemInfo.position;
        int i10 = this.mItems.get(size - 1).position;
        while (i9 < i10) {
            while (true) {
                i7 = itemInfo.position;
                if (i9 <= i7 || i8 >= size) {
                    break;
                }
                i8++;
                itemInfo = this.mItems.get(i8);
            }
            if (i9 == i7) {
                float f11 = itemInfo.offset;
                float f12 = itemInfo.widthFactor;
                f7 = (f11 + f12) * width;
                f10 = f11 + f12 + f9;
            } else {
                float pageWidth = this.mAdapter.getPageWidth(i9);
                f7 = (f10 + pageWidth) * width;
                f10 = pageWidth + f9 + f10;
            }
            if (this.mPageMargin + f7 > scrollX) {
                f8 = f9;
                this.mMarginDrawable.setBounds(Math.round(f7), this.mTopPageBounds, Math.round(this.mPageMargin + f7), this.mBottomPageBounds);
                this.mMarginDrawable.draw(canvas);
            } else {
                f8 = f9;
            }
            if (f7 > scrollX + r2) {
                return;
            }
            i9++;
            f9 = f8;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) throws Resources.NotFoundException {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            resetTouch();
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        if (action == 0) {
            float x6 = motionEvent.getX();
            this.mInitialMotionX = x6;
            this.mLastMotionX = x6;
            float y6 = motionEvent.getY();
            this.mInitialMotionY = y6;
            this.mLastMotionY = y6;
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mIsUnableToDrag = false;
            this.mIsScrollStarted = true;
            this.mScroller.computeScrollOffset();
            if (this.mScrollState != 2 || Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) <= this.mCloseEnough) {
                completeScroll(false);
                this.mIsBeingDragged = false;
            } else {
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                populate();
                this.mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i7 = this.mActivePointerId;
            if (i7 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i7);
                float x7 = motionEvent.getX(iFindPointerIndex);
                float f7 = x7 - this.mLastMotionX;
                float fAbs = Math.abs(f7);
                float y7 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y7 - this.mInitialMotionY);
                if (f7 != 0.0f && !isGutterDrag(this.mLastMotionX, f7) && canScroll(this, false, (int) f7, (int) x7, (int) y7)) {
                    this.mLastMotionX = x7;
                    this.mLastMotionY = y7;
                    this.mIsUnableToDrag = true;
                    return false;
                }
                int i8 = this.mTouchSlop;
                if (fAbs > i8 && fAbs * 0.5f > fAbs2) {
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                    float f8 = this.mInitialMotionX;
                    float f9 = this.mTouchSlop;
                    this.mLastMotionX = f7 > 0.0f ? f8 + f9 : f8 - f9;
                    this.mLastMotionY = y7;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i8) {
                    this.mIsUnableToDrag = true;
                }
                if (this.mIsBeingDragged && performDrag(x7)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else if (action == 6) {
            onSecondaryPointerUp(motionEvent);
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a8  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r14, int r15) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.onMeasure(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    @android.support.annotation.CallSuper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onPageScrolled(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.mDecorChildCount
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L6b
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = 0
        L1b:
            if (r7 >= r6) goto L6b
            android.view.View r8 = r12.getChildAt(r7)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            android.support.v4.view.ViewPager$LayoutParams r9 = (android.support.v4.view.ViewPager.LayoutParams) r9
            boolean r10 = r9.isDecor
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.gravity
            r9 = r9 & 7
            if (r9 == r2) goto L4d
            r10 = 3
            if (r9 == r10) goto L47
            r10 = 5
            if (r9 == r10) goto L3a
            r9 = r3
            goto L5c
        L3a:
            int r9 = r5 - r4
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r4 = r4 + r10
            goto L59
        L47:
            int r9 = r8.getWidth()
            int r9 = r9 + r3
            goto L5c
        L4d:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r3)
        L59:
            r11 = r9
            r9 = r3
            r3 = r11
        L5c:
            int r3 = r3 + r0
            int r10 = r8.getLeft()
            int r3 = r3 - r10
            if (r3 == 0) goto L67
            r8.offsetLeftAndRight(r3)
        L67:
            r3 = r9
        L68:
            int r7 = r7 + 1
            goto L1b
        L6b:
            r12.dispatchOnPageScrolled(r13, r14, r15)
            android.support.v4.view.ViewPager$PageTransformer r13 = r12.mPageTransformer
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r1 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            android.support.v4.view.ViewPager$LayoutParams r0 = (android.support.v4.view.ViewPager.LayoutParams) r0
            boolean r0 = r0.isDecor
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            android.support.v4.view.ViewPager$PageTransformer r3 = r12.mPageTransformer
            r3.transformPage(r15, r0)
        L9c:
            int r1 = r1 + 1
            goto L7a
        L9f:
            r12.mCalledSuper = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.onPageScrolled(int, float, int):void");
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i7, Rect rect) {
        int i8;
        int i9;
        ItemInfo itemInfoInfoForChild;
        int childCount = getChildCount();
        int i10 = -1;
        if ((i7 & 2) != 0) {
            i10 = childCount;
            i8 = 0;
            i9 = 1;
        } else {
            i8 = childCount - 1;
            i9 = -1;
        }
        while (i8 != i10) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() == 0 && (itemInfoInfoForChild = infoForChild(childAt)) != null && itemInfoInfoForChild.position == this.mCurItem && childAt.requestFocus(i7, rect)) {
                return true;
            }
            i8 += i9;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) throws Resources.NotFoundException {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.restoreState(savedState.adapterState, savedState.loader);
            setCurrentItemInternal(savedState.position, false, true);
        } else {
            this.mRestoredCurItem = savedState.position;
            this.mRestoredAdapterState = savedState.adapterState;
            this.mRestoredClassLoader = savedState.loader;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.position = this.mCurItem;
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            savedState.adapterState = pagerAdapter.saveState();
        }
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        if (i7 != i9) {
            int i11 = this.mPageMargin;
            recomputeScrollPosition(i7, i9, i11, i11);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00dc  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean pageLeft() throws Resources.NotFoundException {
        int i7 = this.mCurItem;
        if (i7 <= 0) {
            return false;
        }
        setCurrentItem(i7 - 1, true);
        return true;
    }

    public boolean pageRight() throws Resources.NotFoundException {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null || this.mCurItem >= pagerAdapter.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.mCurItem + 1, true);
        return true;
    }

    public void populate() throws Resources.NotFoundException {
        populate(this.mCurItem);
    }

    public void removeOnAdapterChangeListener(@NonNull OnAdapterChangeListener onAdapterChangeListener) {
        List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
        if (list != null) {
            list.remove(onAdapterChangeListener);
        }
    }

    public void removeOnPageChangeListener(@NonNull OnPageChangeListener onPageChangeListener) {
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.remove(onPageChangeListener);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(@Nullable PagerAdapter pagerAdapter) throws Resources.NotFoundException {
        PagerAdapter pagerAdapter2 = this.mAdapter;
        if (pagerAdapter2 != null) {
            pagerAdapter2.setViewPagerObserver(null);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i7 = 0; i7 < this.mItems.size(); i7++) {
                ItemInfo itemInfo = this.mItems.get(i7);
                this.mAdapter.destroyItem((ViewGroup) this, itemInfo.position, itemInfo.object);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            removeNonDecorViews();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter3 = this.mAdapter;
        this.mAdapter = pagerAdapter;
        this.mExpectedAdapterCount = 0;
        if (pagerAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new PagerObserver();
            }
            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean z6 = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (z6) {
                requestLayout();
            } else {
                populate();
            }
        }
        List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.mAdapterChangeListeners.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.mAdapterChangeListeners.get(i8).onAdapterChanged(this, pagerAdapter3, pagerAdapter);
        }
    }

    public void setCurrentItem(int i7) throws Resources.NotFoundException {
        this.mPopulatePending = false;
        setCurrentItemInternal(i7, !this.mFirstLayout, false);
    }

    public void setCurrentItemInternal(int i7, boolean z6, boolean z7) throws Resources.NotFoundException {
        setCurrentItemInternal(i7, z6, z7, 0);
    }

    public OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener onPageChangeListener) {
        OnPageChangeListener onPageChangeListener2 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = onPageChangeListener;
        return onPageChangeListener2;
    }

    public void setOffscreenPageLimit(int i7) throws Resources.NotFoundException {
        if (i7 < 1) {
            i7 = 1;
        }
        if (i7 != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i7;
            populate();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void setPageMargin(int i7) {
        int i8 = this.mPageMargin;
        this.mPageMargin = i7;
        int width = getWidth();
        recomputeScrollPosition(width, width, i7, i8);
        requestLayout();
    }

    public void setPageMarginDrawable(@Nullable Drawable drawable) {
        this.mMarginDrawable = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageTransformer(boolean z6, @Nullable PageTransformer pageTransformer) throws Resources.NotFoundException {
        setPageTransformer(z6, pageTransformer, 2);
    }

    public void setScrollState(int i7) {
        if (this.mScrollState == i7) {
            return;
        }
        this.mScrollState = i7;
        if (this.mPageTransformer != null) {
            enableLayers(i7 != 0);
        }
        dispatchOnScrollStateChanged(i7);
    }

    public void smoothScrollTo(int i7, int i8) throws Resources.NotFoundException {
        smoothScrollTo(i7, i8, 0);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mMarginDrawable;
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public int childIndex;
        public int gravity;
        public boolean isDecor;
        public boolean needsMeasure;
        public int position;
        public float widthFactor;

        public LayoutParams() {
            super(-1, -1);
            this.widthFactor = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.widthFactor = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.LAYOUT_ATTRS);
            this.gravity = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
    
        r4 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00f3 A[PHI: r3 r6 r12
      0x00f3: PHI (r3v12 int) = (r3v11 int), (r3v10 int), (r3v14 int) binds: [B:62:0x00e8, B:59:0x00d2, B:53:0x00bc] A[DONT_GENERATE, DONT_INLINE]
      0x00f3: PHI (r6v9 int) = (r6v1 int), (r6v8 int), (r6v11 int) binds: [B:62:0x00e8, B:59:0x00d2, B:53:0x00bc] A[DONT_GENERATE, DONT_INLINE]
      0x00f3: PHI (r12v5 float) = (r12v3 float), (r12v4 float), (r12v2 float) binds: [B:62:0x00e8, B:59:0x00d2, B:53:0x00bc] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void populate(int r15) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 605
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.populate(int):void");
    }

    public void setCurrentItemInternal(int i7, boolean z6, boolean z7, int i8) throws Resources.NotFoundException {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null || pagerAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z7 && this.mCurItem == i7 && this.mItems.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i7 < 0) {
            i7 = 0;
        } else if (i7 >= this.mAdapter.getCount()) {
            i7 = this.mAdapter.getCount() - 1;
        }
        int i9 = this.mOffscreenPageLimit;
        int i10 = this.mCurItem;
        if (i7 > i10 + i9 || i7 < i10 - i9) {
            for (int i11 = 0; i11 < this.mItems.size(); i11++) {
                this.mItems.get(i11).scrolling = true;
            }
        }
        boolean z8 = this.mCurItem != i7;
        if (!this.mFirstLayout) {
            populate(i7);
            scrollToItem(i7, z6, i8, z8);
        } else {
            this.mCurItem = i7;
            if (z8) {
                dispatchOnPageSelected(i7);
            }
            requestLayout();
        }
    }

    public void setPageTransformer(boolean z6, @Nullable PageTransformer pageTransformer, int i7) throws Resources.NotFoundException {
        boolean z7 = pageTransformer != null;
        boolean z8 = z7 != (this.mPageTransformer != null);
        this.mPageTransformer = pageTransformer;
        setChildrenDrawingOrderEnabled(z7);
        if (z7) {
            this.mDrawingOrder = z6 ? 2 : 1;
            this.mPageTransformerLayerType = i7;
        } else {
            this.mDrawingOrder = 0;
        }
        if (z8) {
            populate();
        }
    }

    public void smoothScrollTo(int i7, int i8, int i9) throws Resources.NotFoundException {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.mScroller;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i10 = scrollX;
        int scrollY = getScrollY();
        int i11 = i7 - i10;
        int i12 = i8 - scrollY;
        if (i11 == 0 && i12 == 0) {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i13 = clientWidth / 2;
        float f7 = clientWidth;
        float f8 = i13;
        float fDistanceInfluenceForSnapDuration = (distanceInfluenceForSnapDuration(Math.min(1.0f, (Math.abs(i11) * 1.0f) / f7)) * f8) + f8;
        int iAbs = Math.abs(i9);
        int iMin = Math.min(iAbs > 0 ? Math.round(Math.abs(fDistanceInfluenceForSnapDuration / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i11) / ((this.mAdapter.getPageWidth(this.mCurItem) * f7) + this.mPageMargin)) + 1.0f) * 100.0f), 600);
        this.mIsScrollStarted = false;
        this.mScroller.startScroll(i10, scrollY, i11, i12, iMin);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void setCurrentItem(int i7, boolean z6) throws Resources.NotFoundException {
        this.mPopulatePending = false;
        setCurrentItemInternal(i7, z6, false);
    }

    public void setPageMarginDrawable(@DrawableRes int i7) {
        setPageMarginDrawable(ContextCompat.getDrawable(getContext(), i7));
    }

    public ViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItems = new ArrayList<>();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() { // from class: android.support.v4.view.ViewPager.3
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                ViewPager.this.setScrollState(0);
                ViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        initViewPager();
    }
}
