package android.support.v7.widget;

/* loaded from: classes.dex */
class RtlSpacingHelper {
    public static final int UNDEFINED = Integer.MIN_VALUE;
    private int mLeft = 0;
    private int mRight = 0;
    private int mStart = Integer.MIN_VALUE;
    private int mEnd = Integer.MIN_VALUE;
    private int mExplicitLeft = 0;
    private int mExplicitRight = 0;
    private boolean mIsRtl = false;
    private boolean mIsRelative = false;

    public int getEnd() {
        return this.mIsRtl ? this.mLeft : this.mRight;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getStart() {
        return this.mIsRtl ? this.mRight : this.mLeft;
    }

    public void setAbsolute(int i7, int i8) {
        this.mIsRelative = false;
        if (i7 != Integer.MIN_VALUE) {
            this.mExplicitLeft = i7;
            this.mLeft = i7;
        }
        if (i8 != Integer.MIN_VALUE) {
            this.mExplicitRight = i8;
            this.mRight = i8;
        }
    }

    public void setDirection(boolean z6) {
        if (z6 == this.mIsRtl) {
            return;
        }
        this.mIsRtl = z6;
        if (!this.mIsRelative) {
            this.mLeft = this.mExplicitLeft;
            this.mRight = this.mExplicitRight;
            return;
        }
        if (z6) {
            int i7 = this.mEnd;
            if (i7 == Integer.MIN_VALUE) {
                i7 = this.mExplicitLeft;
            }
            this.mLeft = i7;
            int i8 = this.mStart;
            if (i8 == Integer.MIN_VALUE) {
                i8 = this.mExplicitRight;
            }
            this.mRight = i8;
            return;
        }
        int i9 = this.mStart;
        if (i9 == Integer.MIN_VALUE) {
            i9 = this.mExplicitLeft;
        }
        this.mLeft = i9;
        int i10 = this.mEnd;
        if (i10 == Integer.MIN_VALUE) {
            i10 = this.mExplicitRight;
        }
        this.mRight = i10;
    }

    public void setRelative(int i7, int i8) {
        this.mStart = i7;
        this.mEnd = i8;
        this.mIsRelative = true;
        if (this.mIsRtl) {
            if (i8 != Integer.MIN_VALUE) {
                this.mLeft = i8;
            }
            if (i7 != Integer.MIN_VALUE) {
                this.mRight = i7;
                return;
            }
            return;
        }
        if (i7 != Integer.MIN_VALUE) {
            this.mLeft = i7;
        }
        if (i8 != Integer.MIN_VALUE) {
            this.mRight = i8;
        }
    }
}
