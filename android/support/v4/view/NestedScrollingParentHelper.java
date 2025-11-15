package android.support.v4.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class NestedScrollingParentHelper {
    private int mNestedScrollAxes;
    private final ViewGroup mViewGroup;

    public NestedScrollingParentHelper(@NonNull ViewGroup viewGroup) {
        this.mViewGroup = viewGroup;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxes;
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i7) {
        onNestedScrollAccepted(view, view2, i7, 0);
    }

    public void onStopNestedScroll(@NonNull View view) {
        onStopNestedScroll(view, 0);
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i7, int i8) {
        this.mNestedScrollAxes = i7;
    }

    public void onStopNestedScroll(@NonNull View view, int i7) {
        this.mNestedScrollAxes = 0;
    }
}
