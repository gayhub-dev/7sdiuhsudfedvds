package android.support.v4.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewParent;

/* loaded from: classes.dex */
public class NestedScrollingChildHelper {
    private boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    public NestedScrollingChildHelper(@NonNull View view) {
        this.mView = view;
    }

    private ViewParent getNestedScrollingParentForType(int i7) {
        if (i7 == 0) {
            return this.mNestedScrollingParentTouch;
        }
        if (i7 != 1) {
            return null;
        }
        return this.mNestedScrollingParentNonTouch;
    }

    private void setNestedScrollingParentForType(int i7, ViewParent viewParent) {
        if (i7 == 0) {
            this.mNestedScrollingParentTouch = viewParent;
        } else {
            if (i7 != 1) {
                return;
            }
            this.mNestedScrollingParentNonTouch = viewParent;
        }
    }

    public boolean dispatchNestedFling(float f7, float f8, boolean z6) {
        ViewParent nestedScrollingParentForType;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedFling(nestedScrollingParentForType, this.mView, f7, f8, z6);
    }

    public boolean dispatchNestedPreFling(float f7, float f8) {
        ViewParent nestedScrollingParentForType;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedPreFling(nestedScrollingParentForType, this.mView, f7, f8);
    }

    public boolean dispatchNestedPreScroll(int i7, int i8, @Nullable int[] iArr, @Nullable int[] iArr2) {
        return dispatchNestedPreScroll(i7, i8, iArr, iArr2, 0);
    }

    public boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, @Nullable int[] iArr) {
        return dispatchNestedScroll(i7, i8, i9, i10, iArr, 0);
    }

    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    public void onDetachedFromWindow() {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void onStopNestedScroll(@NonNull View view) {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void setNestedScrollingEnabled(boolean z6) {
        if (this.mIsNestedScrollingEnabled) {
            ViewCompat.stopNestedScroll(this.mView);
        }
        this.mIsNestedScrollingEnabled = z6;
    }

    public boolean startNestedScroll(int i7) {
        return startNestedScroll(i7, 0);
    }

    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public boolean dispatchNestedPreScroll(int i7, int i8, @Nullable int[] iArr, @Nullable int[] iArr2, int i9) {
        ViewParent nestedScrollingParentForType;
        int i10;
        int i11;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(i9)) == null) {
            return false;
        }
        if (i7 == 0 && i8 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.mView.getLocationInWindow(iArr2);
            i10 = iArr2[0];
            i11 = iArr2[1];
        } else {
            i10 = 0;
            i11 = 0;
        }
        if (iArr == null) {
            if (this.mTempNestedScrollConsumed == null) {
                this.mTempNestedScrollConsumed = new int[2];
            }
            iArr = this.mTempNestedScrollConsumed;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        ViewParentCompat.onNestedPreScroll(nestedScrollingParentForType, this.mView, i7, i8, iArr, i9);
        if (iArr2 != null) {
            this.mView.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i10;
            iArr2[1] = iArr2[1] - i11;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, @Nullable int[] iArr, int i11) {
        ViewParent nestedScrollingParentForType;
        int i12;
        int i13;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(i11)) == null) {
            return false;
        }
        if (i7 == 0 && i8 == 0 && i9 == 0 && i10 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.mView.getLocationInWindow(iArr);
            i12 = iArr[0];
            i13 = iArr[1];
        } else {
            i12 = 0;
            i13 = 0;
        }
        ViewParentCompat.onNestedScroll(nestedScrollingParentForType, this.mView, i7, i8, i9, i10, i11);
        if (iArr != null) {
            this.mView.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i12;
            iArr[1] = iArr[1] - i13;
        }
        return true;
    }

    public boolean hasNestedScrollingParent(int i7) {
        return getNestedScrollingParentForType(i7) != null;
    }

    public boolean startNestedScroll(int i7, int i8) {
        if (hasNestedScrollingParent(i8)) {
            return true;
        }
        if (!isNestedScrollingEnabled()) {
            return false;
        }
        View view = this.mView;
        for (ViewParent parent = this.mView.getParent(); parent != null; parent = parent.getParent()) {
            if (ViewParentCompat.onStartNestedScroll(parent, view, this.mView, i7, i8)) {
                setNestedScrollingParentForType(i8, parent);
                ViewParentCompat.onNestedScrollAccepted(parent, view, this.mView, i7, i8);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public void stopNestedScroll(int i7) {
        ViewParent nestedScrollingParentForType = getNestedScrollingParentForType(i7);
        if (nestedScrollingParentForType != null) {
            ViewParentCompat.onStopNestedScroll(nestedScrollingParentForType, this.mView, i7);
            setNestedScrollingParentForType(i7, null);
        }
    }
}
