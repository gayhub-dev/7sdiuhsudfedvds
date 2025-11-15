package android.support.v7.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
class ViewBoundsCheck {
    public static final int CVE_PVE_POS = 12;
    public static final int CVE_PVS_POS = 8;
    public static final int CVS_PVE_POS = 4;
    public static final int CVS_PVS_POS = 0;

    /* renamed from: EQ */
    public static final int f169EQ = 2;
    public static final int FLAG_CVE_EQ_PVE = 8192;
    public static final int FLAG_CVE_EQ_PVS = 512;
    public static final int FLAG_CVE_GT_PVE = 4096;
    public static final int FLAG_CVE_GT_PVS = 256;
    public static final int FLAG_CVE_LT_PVE = 16384;
    public static final int FLAG_CVE_LT_PVS = 1024;
    public static final int FLAG_CVS_EQ_PVE = 32;
    public static final int FLAG_CVS_EQ_PVS = 2;
    public static final int FLAG_CVS_GT_PVE = 16;
    public static final int FLAG_CVS_GT_PVS = 1;
    public static final int FLAG_CVS_LT_PVE = 64;
    public static final int FLAG_CVS_LT_PVS = 4;

    /* renamed from: GT */
    public static final int f170GT = 1;

    /* renamed from: LT */
    public static final int f171LT = 4;
    public static final int MASK = 7;
    public BoundFlags mBoundFlags = new BoundFlags();
    public final Callback mCallback;

    public static class BoundFlags {
        public int mBoundFlags = 0;
        public int mChildEnd;
        public int mChildStart;
        public int mRvEnd;
        public int mRvStart;

        public void addFlags(int i7) {
            this.mBoundFlags = i7 | this.mBoundFlags;
        }

        public boolean boundsMatch() {
            int i7 = this.mBoundFlags;
            if ((i7 & 7) != 0 && (i7 & (compare(this.mChildStart, this.mRvStart) << 0)) == 0) {
                return false;
            }
            int i8 = this.mBoundFlags;
            if ((i8 & 112) != 0 && (i8 & (compare(this.mChildStart, this.mRvEnd) << 4)) == 0) {
                return false;
            }
            int i9 = this.mBoundFlags;
            if ((i9 & 1792) != 0 && (i9 & (compare(this.mChildEnd, this.mRvStart) << 8)) == 0) {
                return false;
            }
            int i10 = this.mBoundFlags;
            return (i10 & 28672) == 0 || (i10 & (compare(this.mChildEnd, this.mRvEnd) << 12)) != 0;
        }

        public int compare(int i7, int i8) {
            if (i7 > i8) {
                return 1;
            }
            return i7 == i8 ? 2 : 4;
        }

        public void resetFlags() {
            this.mBoundFlags = 0;
        }

        public void setBounds(int i7, int i8, int i9, int i10) {
            this.mRvStart = i7;
            this.mRvEnd = i8;
            this.mChildStart = i9;
            this.mChildEnd = i10;
        }

        public void setFlags(int i7, int i8) {
            this.mBoundFlags = (i7 & i8) | (this.mBoundFlags & (~i8));
        }
    }

    public interface Callback {
        View getChildAt(int i7);

        int getChildCount();

        int getChildEnd(View view);

        int getChildStart(View view);

        View getParent();

        int getParentEnd();

        int getParentStart();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewBounds {
    }

    public ViewBoundsCheck(Callback callback) {
        this.mCallback = callback;
    }

    public View findOneViewWithinBoundFlags(int i7, int i8, int i9, int i10) {
        int parentStart = this.mCallback.getParentStart();
        int parentEnd = this.mCallback.getParentEnd();
        int i11 = i8 > i7 ? 1 : -1;
        View view = null;
        while (i7 != i8) {
            View childAt = this.mCallback.getChildAt(i7);
            this.mBoundFlags.setBounds(parentStart, parentEnd, this.mCallback.getChildStart(childAt), this.mCallback.getChildEnd(childAt));
            if (i9 != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(i9);
                if (this.mBoundFlags.boundsMatch()) {
                    return childAt;
                }
            }
            if (i10 != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(i10);
                if (this.mBoundFlags.boundsMatch()) {
                    view = childAt;
                }
            }
            i7 += i11;
        }
        return view;
    }

    public boolean isViewWithinBoundFlags(View view, int i7) {
        this.mBoundFlags.setBounds(this.mCallback.getParentStart(), this.mCallback.getParentEnd(), this.mCallback.getChildStart(view), this.mCallback.getChildEnd(view));
        if (i7 == 0) {
            return false;
        }
        this.mBoundFlags.resetFlags();
        this.mBoundFlags.addFlags(i7);
        return this.mBoundFlags.boundsMatch();
    }
}
