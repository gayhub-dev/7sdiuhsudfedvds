package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class ChildHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "ChildrenHelper";
    public final Callback mCallback;
    public final Bucket mBucket = new Bucket();
    public final List<View> mHiddenViews = new ArrayList();

    public static class Bucket {
        public static final int BITS_PER_WORD = 64;
        public static final long LAST_BIT = Long.MIN_VALUE;
        public long mData = 0;
        public Bucket mNext;

        private void ensureNext() {
            if (this.mNext == null) {
                this.mNext = new Bucket();
            }
        }

        public void clear(int i7) {
            if (i7 < 64) {
                this.mData &= ~(1 << i7);
                return;
            }
            Bucket bucket = this.mNext;
            if (bucket != null) {
                bucket.clear(i7 - 64);
            }
        }

        public int countOnesBefore(int i7) {
            Bucket bucket = this.mNext;
            if (bucket == null) {
                return i7 >= 64 ? Long.bitCount(this.mData) : Long.bitCount(this.mData & ((1 << i7) - 1));
            }
            if (i7 < 64) {
                return Long.bitCount(this.mData & ((1 << i7) - 1));
            }
            return Long.bitCount(this.mData) + bucket.countOnesBefore(i7 - 64);
        }

        public boolean get(int i7) {
            if (i7 < 64) {
                return (this.mData & (1 << i7)) != 0;
            }
            ensureNext();
            return this.mNext.get(i7 - 64);
        }

        public void insert(int i7, boolean z6) {
            if (i7 >= 64) {
                ensureNext();
                this.mNext.insert(i7 - 64, z6);
                return;
            }
            long j7 = this.mData;
            boolean z7 = (Long.MIN_VALUE & j7) != 0;
            long j8 = (1 << i7) - 1;
            this.mData = ((j7 & (~j8)) << 1) | (j7 & j8);
            if (z6) {
                set(i7);
            } else {
                clear(i7);
            }
            if (z7 || this.mNext != null) {
                ensureNext();
                this.mNext.insert(0, z7);
            }
        }

        public boolean remove(int i7) {
            if (i7 >= 64) {
                ensureNext();
                return this.mNext.remove(i7 - 64);
            }
            long j7 = 1 << i7;
            long j8 = this.mData;
            boolean z6 = (j8 & j7) != 0;
            long j9 = j8 & (~j7);
            this.mData = j9;
            long j10 = j7 - 1;
            this.mData = (j9 & j10) | Long.rotateRight((~j10) & j9, 1);
            Bucket bucket = this.mNext;
            if (bucket != null) {
                if (bucket.get(0)) {
                    set(63);
                }
                this.mNext.remove(0);
            }
            return z6;
        }

        public void reset() {
            this.mData = 0L;
            Bucket bucket = this.mNext;
            if (bucket != null) {
                bucket.reset();
            }
        }

        public void set(int i7) {
            if (i7 < 64) {
                this.mData |= 1 << i7;
            } else {
                ensureNext();
                this.mNext.set(i7 - 64);
            }
        }

        public String toString() {
            if (this.mNext == null) {
                return Long.toBinaryString(this.mData);
            }
            return this.mNext.toString() + "xx" + Long.toBinaryString(this.mData);
        }
    }

    public interface Callback {
        void addView(View view, int i7);

        void attachViewToParent(View view, int i7, ViewGroup.LayoutParams layoutParams);

        void detachViewFromParent(int i7);

        View getChildAt(int i7);

        int getChildCount();

        RecyclerView.ViewHolder getChildViewHolder(View view);

        int indexOfChild(View view);

        void onEnteredHiddenState(View view);

        void onLeftHiddenState(View view);

        void removeAllViews();

        void removeViewAt(int i7);
    }

    public ChildHelper(Callback callback) {
        this.mCallback = callback;
    }

    private int getOffset(int i7) {
        if (i7 < 0) {
            return -1;
        }
        int childCount = this.mCallback.getChildCount();
        int i8 = i7;
        while (i8 < childCount) {
            int iCountOnesBefore = i7 - (i8 - this.mBucket.countOnesBefore(i8));
            if (iCountOnesBefore == 0) {
                while (this.mBucket.get(i8)) {
                    i8++;
                }
                return i8;
            }
            i8 += iCountOnesBefore;
        }
        return -1;
    }

    private void hideViewInternal(View view) {
        this.mHiddenViews.add(view);
        this.mCallback.onEnteredHiddenState(view);
    }

    private boolean unhideViewInternal(View view) {
        if (!this.mHiddenViews.remove(view)) {
            return false;
        }
        this.mCallback.onLeftHiddenState(view);
        return true;
    }

    public void addView(View view, boolean z6) {
        addView(view, -1, z6);
    }

    public void attachViewToParent(View view, int i7, ViewGroup.LayoutParams layoutParams, boolean z6) {
        int childCount = i7 < 0 ? this.mCallback.getChildCount() : getOffset(i7);
        this.mBucket.insert(childCount, z6);
        if (z6) {
            hideViewInternal(view);
        }
        this.mCallback.attachViewToParent(view, childCount, layoutParams);
    }

    public void detachViewFromParent(int i7) {
        int offset = getOffset(i7);
        this.mBucket.remove(offset);
        this.mCallback.detachViewFromParent(offset);
    }

    public View findHiddenNonRemovedView(int i7) {
        int size = this.mHiddenViews.size();
        for (int i8 = 0; i8 < size; i8++) {
            View view = this.mHiddenViews.get(i8);
            RecyclerView.ViewHolder childViewHolder = this.mCallback.getChildViewHolder(view);
            if (childViewHolder.getLayoutPosition() == i7 && !childViewHolder.isInvalid() && !childViewHolder.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    public View getChildAt(int i7) {
        return this.mCallback.getChildAt(getOffset(i7));
    }

    public int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    public View getUnfilteredChildAt(int i7) {
        return this.mCallback.getChildAt(i7);
    }

    public int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    public void hide(View view) {
        int iIndexOfChild = this.mCallback.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.mBucket.set(iIndexOfChild);
            hideViewInternal(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public int indexOfChild(View view) {
        int iIndexOfChild = this.mCallback.indexOfChild(view);
        if (iIndexOfChild == -1 || this.mBucket.get(iIndexOfChild)) {
            return -1;
        }
        return iIndexOfChild - this.mBucket.countOnesBefore(iIndexOfChild);
    }

    public boolean isHidden(View view) {
        return this.mHiddenViews.contains(view);
    }

    public void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            this.mCallback.onLeftHiddenState(this.mHiddenViews.get(size));
            this.mHiddenViews.remove(size);
        }
        this.mCallback.removeAllViews();
    }

    public void removeView(View view) {
        int iIndexOfChild = this.mCallback.indexOfChild(view);
        if (iIndexOfChild < 0) {
            return;
        }
        if (this.mBucket.remove(iIndexOfChild)) {
            unhideViewInternal(view);
        }
        this.mCallback.removeViewAt(iIndexOfChild);
    }

    public void removeViewAt(int i7) {
        int offset = getOffset(i7);
        View childAt = this.mCallback.getChildAt(offset);
        if (childAt == null) {
            return;
        }
        if (this.mBucket.remove(offset)) {
            unhideViewInternal(childAt);
        }
        this.mCallback.removeViewAt(offset);
    }

    public boolean removeViewIfHidden(View view) {
        int iIndexOfChild = this.mCallback.indexOfChild(view);
        if (iIndexOfChild == -1) {
            unhideViewInternal(view);
            return true;
        }
        if (!this.mBucket.get(iIndexOfChild)) {
            return false;
        }
        this.mBucket.remove(iIndexOfChild);
        unhideViewInternal(view);
        this.mCallback.removeViewAt(iIndexOfChild);
        return true;
    }

    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }

    public void unhide(View view) {
        int iIndexOfChild = this.mCallback.indexOfChild(view);
        if (iIndexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.mBucket.get(iIndexOfChild)) {
            this.mBucket.clear(iIndexOfChild);
            unhideViewInternal(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public void addView(View view, int i7, boolean z6) {
        int childCount = i7 < 0 ? this.mCallback.getChildCount() : getOffset(i7);
        this.mBucket.insert(childCount, z6);
        if (z6) {
            hideViewInternal(view);
        }
        this.mCallback.addView(view, childCount);
    }
}
