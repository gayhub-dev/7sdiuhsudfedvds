package p165u2;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Toast;
import p172v2.C2004a;

/* compiled from: CtvitToast.java */
/* renamed from: u2.a */
/* loaded from: classes.dex */
public class C1974a {

    /* renamed from: b */
    public static volatile C1974a f5772b;

    /* renamed from: c */
    public static Application f5773c;

    /* renamed from: a */
    public Toast f5774a;

    /* compiled from: CtvitToast.java */
    /* renamed from: u2.a$a */
    public static class a implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (C1974a.m2298a().f5774a != null) {
                C1974a.m2298a().f5774a.cancel();
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (C1974a.m2298a().f5774a != null) {
                C1974a.m2298a().f5774a.cancel();
            }
        }
    }

    /* renamed from: a */
    public static C1974a m2298a() {
        if (f5772b == null) {
            synchronized (C1974a.class) {
                if (f5772b == null) {
                    f5772b = new C1974a();
                }
            }
        }
        return f5772b;
    }

    /* renamed from: b */
    public static C2004a m2299b(@StringRes int i7) {
        return new C2004a(f5773c.getResources().getString(i7));
    }
}
