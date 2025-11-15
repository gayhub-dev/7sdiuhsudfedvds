package android.support.v4.view;

import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public interface NestedScrollingChild2 extends NestedScrollingChild {
    boolean dispatchNestedPreScroll(int i7, int i8, @Nullable int[] iArr, @Nullable int[] iArr2, int i9);

    boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, @Nullable int[] iArr, int i11);

    boolean hasNestedScrollingParent(int i7);

    boolean startNestedScroll(int i7, int i8);

    void stopNestedScroll(int i7);
}
