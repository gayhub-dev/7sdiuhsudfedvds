package p069i1;

import android.app.Activity;

/* compiled from: SystemBarlUtils.java */
/* renamed from: i1.a */
/* loaded from: classes.dex */
public final class C1142a {
    /* renamed from: a */
    public static void m1309a(Activity activity) {
        if (activity == null) {
            return;
        }
        activity.getWindow().setStatusBarColor(0);
        activity.getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    /* renamed from: b */
    public static void m1310b(Activity activity, boolean z6) {
        if (z6) {
            activity.getWindow().getDecorView().setSystemUiVisibility(5890);
        } else {
            m1309a(activity);
        }
    }
}
