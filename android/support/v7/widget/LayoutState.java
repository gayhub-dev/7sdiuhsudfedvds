package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import p009b.C0413b;

/* loaded from: classes.dex */
class LayoutState {
    public static final int INVALID_LAYOUT = Integer.MIN_VALUE;
    public static final int ITEM_DIRECTION_HEAD = -1;
    public static final int ITEM_DIRECTION_TAIL = 1;
    public static final int LAYOUT_END = 1;
    public static final int LAYOUT_START = -1;
    public static final String TAG = "LayoutState";
    public int mAvailable;
    public int mCurrentPosition;
    public boolean mInfinite;
    public int mItemDirection;
    public int mLayoutDirection;
    public boolean mStopInFocusable;
    public boolean mRecycle = true;
    public int mStartLine = 0;
    public int mEndLine = 0;

    public boolean hasMore(RecyclerView.State state) {
        int i7 = this.mCurrentPosition;
        return i7 >= 0 && i7 < state.getItemCount();
    }

    public View next(RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return viewForPosition;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("LayoutState{mAvailable=");
        sbM112a.append(this.mAvailable);
        sbM112a.append(", mCurrentPosition=");
        sbM112a.append(this.mCurrentPosition);
        sbM112a.append(", mItemDirection=");
        sbM112a.append(this.mItemDirection);
        sbM112a.append(", mLayoutDirection=");
        sbM112a.append(this.mLayoutDirection);
        sbM112a.append(", mStartLine=");
        sbM112a.append(this.mStartLine);
        sbM112a.append(", mEndLine=");
        sbM112a.append(this.mEndLine);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
