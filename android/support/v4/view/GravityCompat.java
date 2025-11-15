package android.support.v4.view;

import android.graphics.Rect;
import android.view.Gravity;

/* loaded from: classes.dex */
public final class GravityCompat {
    public static final int END = 8388613;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;
    public static final int START = 8388611;

    private GravityCompat() {
    }

    public static void apply(int i7, int i8, int i9, Rect rect, Rect rect2, int i10) {
        Gravity.apply(i7, i8, i9, rect, rect2, i10);
    }

    public static void applyDisplay(int i7, Rect rect, Rect rect2, int i8) {
        Gravity.applyDisplay(i7, rect, rect2, i8);
    }

    public static int getAbsoluteGravity(int i7, int i8) {
        return Gravity.getAbsoluteGravity(i7, i8);
    }

    public static void apply(int i7, int i8, int i9, Rect rect, int i10, int i11, Rect rect2, int i12) {
        Gravity.apply(i7, i8, i9, rect, i10, i11, rect2, i12);
    }
}
