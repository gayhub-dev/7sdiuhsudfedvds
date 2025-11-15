package android.support.v4.view;

import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public interface NestedScrollingChild {
    boolean dispatchNestedFling(float f7, float f8, boolean z6);

    boolean dispatchNestedPreFling(float f7, float f8);

    boolean dispatchNestedPreScroll(int i7, int i8, @Nullable int[] iArr, @Nullable int[] iArr2);

    boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, @Nullable int[] iArr);

    boolean hasNestedScrollingParent();

    boolean isNestedScrollingEnabled();

    void setNestedScrollingEnabled(boolean z6);

    boolean startNestedScroll(int i7);

    void stopNestedScroll();
}
