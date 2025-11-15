package android.support.v4.view;

import android.support.annotation.NonNull;
import android.view.View;

/* loaded from: classes.dex */
public interface NestedScrollingParent2 extends NestedScrollingParent {
    void onNestedPreScroll(@NonNull View view, int i7, int i8, @NonNull int[] iArr, int i9);

    void onNestedScroll(@NonNull View view, int i7, int i8, int i9, int i10, int i11);

    void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i7, int i8);

    boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i7, int i8);

    void onStopNestedScroll(@NonNull View view, int i7);
}
