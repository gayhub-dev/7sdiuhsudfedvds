package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: classes.dex */
public final class ViewParentCompat {
    private static final String TAG = "ViewParentCompat";

    private ViewParentCompat() {
    }

    public static void notifySubtreeAccessibilityStateChanged(ViewParent viewParent, View view, View view2, int i7) {
        viewParent.notifySubtreeAccessibilityStateChanged(view, view2, i7);
    }

    public static boolean onNestedFling(ViewParent viewParent, View view, float f7, float f8, boolean z6) {
        try {
            return viewParent.onNestedFling(view, f7, f8, z6);
        } catch (AbstractMethodError unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("ViewParent ");
            sb.append(viewParent);
            sb.append(" does not implement interface ");
            sb.append("method onNestedFling");
            return false;
        }
    }

    public static boolean onNestedPreFling(ViewParent viewParent, View view, float f7, float f8) {
        try {
            return viewParent.onNestedPreFling(view, f7, f8);
        } catch (AbstractMethodError unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("ViewParent ");
            sb.append(viewParent);
            sb.append(" does not implement interface ");
            sb.append("method onNestedPreFling");
            return false;
        }
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int i7, int i8, int[] iArr) {
        onNestedPreScroll(viewParent, view, i7, i8, iArr, 0);
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int i7, int i8, int i9, int i10) {
        onNestedScroll(viewParent, view, i7, i8, i9, i10, 0);
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i7) {
        onNestedScrollAccepted(viewParent, view, view2, i7, 0);
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i7) {
        return onStartNestedScroll(viewParent, view, view2, i7, 0);
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view) {
        onStopNestedScroll(viewParent, view, 0);
    }

    @Deprecated
    public static boolean requestSendAccessibilityEvent(ViewParent viewParent, View view, AccessibilityEvent accessibilityEvent) {
        return viewParent.requestSendAccessibilityEvent(view, accessibilityEvent);
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int i7, int i8, int[] iArr, int i9) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedPreScroll(view, i7, i8, iArr, i9);
            return;
        }
        if (i9 == 0) {
            try {
                viewParent.onNestedPreScroll(view, i7, i8, iArr);
            } catch (AbstractMethodError unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("ViewParent ");
                sb.append(viewParent);
                sb.append(" does not implement interface ");
                sb.append("method onNestedPreScroll");
            }
        }
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int i7, int i8, int i9, int i10, int i11) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScroll(view, i7, i8, i9, i10, i11);
            return;
        }
        if (i11 == 0) {
            try {
                viewParent.onNestedScroll(view, i7, i8, i9, i10);
            } catch (AbstractMethodError unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("ViewParent ");
                sb.append(viewParent);
                sb.append(" does not implement interface ");
                sb.append("method onNestedScroll");
            }
        }
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i7, int i8) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScrollAccepted(view, view2, i7, i8);
            return;
        }
        if (i8 == 0) {
            try {
                viewParent.onNestedScrollAccepted(view, view2, i7);
            } catch (AbstractMethodError unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("ViewParent ");
                sb.append(viewParent);
                sb.append(" does not implement interface ");
                sb.append("method onNestedScrollAccepted");
            }
        }
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i7, int i8) {
        if (viewParent instanceof NestedScrollingParent2) {
            return ((NestedScrollingParent2) viewParent).onStartNestedScroll(view, view2, i7, i8);
        }
        if (i8 != 0) {
            return false;
        }
        try {
            return viewParent.onStartNestedScroll(view, view2, i7);
        } catch (AbstractMethodError unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("ViewParent ");
            sb.append(viewParent);
            sb.append(" does not implement interface ");
            sb.append("method onStartNestedScroll");
            return false;
        }
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view, int i7) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onStopNestedScroll(view, i7);
            return;
        }
        if (i7 == 0) {
            try {
                viewParent.onStopNestedScroll(view);
            } catch (AbstractMethodError unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("ViewParent ");
                sb.append(viewParent);
                sb.append(" does not implement interface ");
                sb.append("method onStopNestedScroll");
            }
        }
    }
}
