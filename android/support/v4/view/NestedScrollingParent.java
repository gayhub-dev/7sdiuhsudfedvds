package android.support.v4.view;

import android.support.annotation.NonNull;
import android.view.View;

/* loaded from: classes.dex */
public interface NestedScrollingParent {
    int getNestedScrollAxes();

    boolean onNestedFling(@NonNull View view, float f7, float f8, boolean z6);

    boolean onNestedPreFling(@NonNull View view, float f7, float f8);

    void onNestedPreScroll(@NonNull View view, int i7, int i8, @NonNull int[] iArr);

    void onNestedScroll(@NonNull View view, int i7, int i8, int i9, int i10);

    void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i7);

    boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i7);

    void onStopNestedScroll(@NonNull View view);
}
