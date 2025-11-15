package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.WindowInsets;

/* loaded from: classes.dex */
public class WindowInsetsCompat {
    private final Object mInsets;

    private WindowInsetsCompat(Object obj) {
        this.mInsets = obj;
    }

    public static Object unwrap(WindowInsetsCompat windowInsetsCompat) {
        if (windowInsetsCompat == null) {
            return null;
        }
        return windowInsetsCompat.mInsets;
    }

    public static WindowInsetsCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new WindowInsetsCompat(obj);
    }

    public WindowInsetsCompat consumeDisplayCutout() {
        return Build.VERSION.SDK_INT >= 28 ? new WindowInsetsCompat(((WindowInsets) this.mInsets).consumeDisplayCutout()) : this;
    }

    public WindowInsetsCompat consumeStableInsets() {
        return new WindowInsetsCompat(((WindowInsets) this.mInsets).consumeStableInsets());
    }

    public WindowInsetsCompat consumeSystemWindowInsets() {
        return new WindowInsetsCompat(((WindowInsets) this.mInsets).consumeSystemWindowInsets());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Object obj2 = this.mInsets;
        Object obj3 = ((WindowInsetsCompat) obj).mInsets;
        return obj2 == null ? obj3 == null : obj2.equals(obj3);
    }

    @Nullable
    public DisplayCutoutCompat getDisplayCutout() {
        if (Build.VERSION.SDK_INT >= 28) {
            return DisplayCutoutCompat.wrap(((WindowInsets) this.mInsets).getDisplayCutout());
        }
        return null;
    }

    public int getStableInsetBottom() {
        return ((WindowInsets) this.mInsets).getStableInsetBottom();
    }

    public int getStableInsetLeft() {
        return ((WindowInsets) this.mInsets).getStableInsetLeft();
    }

    public int getStableInsetRight() {
        return ((WindowInsets) this.mInsets).getStableInsetRight();
    }

    public int getStableInsetTop() {
        return ((WindowInsets) this.mInsets).getStableInsetTop();
    }

    public int getSystemWindowInsetBottom() {
        return ((WindowInsets) this.mInsets).getSystemWindowInsetBottom();
    }

    public int getSystemWindowInsetLeft() {
        return ((WindowInsets) this.mInsets).getSystemWindowInsetLeft();
    }

    public int getSystemWindowInsetRight() {
        return ((WindowInsets) this.mInsets).getSystemWindowInsetRight();
    }

    public int getSystemWindowInsetTop() {
        return ((WindowInsets) this.mInsets).getSystemWindowInsetTop();
    }

    public boolean hasInsets() {
        return ((WindowInsets) this.mInsets).hasInsets();
    }

    public boolean hasStableInsets() {
        return ((WindowInsets) this.mInsets).hasStableInsets();
    }

    public boolean hasSystemWindowInsets() {
        return ((WindowInsets) this.mInsets).hasSystemWindowInsets();
    }

    public int hashCode() {
        Object obj = this.mInsets;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean isConsumed() {
        return ((WindowInsets) this.mInsets).isConsumed();
    }

    public boolean isRound() {
        return ((WindowInsets) this.mInsets).isRound();
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int i7, int i8, int i9, int i10) {
        return new WindowInsetsCompat(((WindowInsets) this.mInsets).replaceSystemWindowInsets(i7, i8, i9, i10));
    }

    public WindowInsetsCompat(WindowInsetsCompat windowInsetsCompat) {
        this.mInsets = windowInsetsCompat == null ? null : new WindowInsets((WindowInsets) windowInsetsCompat.mInsets);
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect) {
        return new WindowInsetsCompat(((WindowInsets) this.mInsets).replaceSystemWindowInsets(rect));
    }
}
