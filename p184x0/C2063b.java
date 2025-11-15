package p184x0;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.cache.DiskLruCache;
import p156t0.C1896a;
import p163u0.C1970a;
import p170v0.C1999d;
import p191y0.C2095a;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: SensorsDataPrivate.java */
/* renamed from: x0.b */
/* loaded from: classes.dex */
public final class C2063b implements Application.ActivityLifecycleCallbacks {
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        long jLongValue;
        long jLongValue2;
        if (activity != null) {
            String string = activity.toString();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (((HashMap) C2065d.f6152b).containsKey(string)) {
                try {
                    jLongValue = ((Long) ((HashMap) C2065d.f6152b).get(string)).longValue();
                } catch (Exception unused) {
                }
            } else {
                jLongValue = jElapsedRealtime - 1000;
            }
            ((HashMap) C2065d.f6152b).remove(string);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (((HashMap) C2065d.f6153c).containsKey(string)) {
                try {
                    jLongValue2 = ((Long) ((HashMap) C2065d.f6153c).get(string)).longValue();
                } catch (Exception unused2) {
                }
            } else {
                jLongValue2 = jCurrentTimeMillis - 1000;
            }
            ((HashMap) C2065d.f6153c).remove(string);
            String canonicalName = activity.getClass().getCanonicalName();
            if (C1970a.f5751i) {
                C2062a c2062aM2448a = C2062a.m2448a();
                String strValueOf = String.valueOf(jCurrentTimeMillis);
                String strValueOf2 = String.valueOf(jLongValue2);
                String strValueOf3 = String.valueOf(jElapsedRealtime - jLongValue);
                Objects.requireNonNull(c2062aM2448a);
                C1999d c1999d = new C1999d();
                C1999d.a aVar = new C1999d.a();
                C2146b.m2588b(C1970a.f5748f);
                C2146b.m2589c(C1970a.f5748f);
                aVar.f5837f = C2145a.m2580h(C1970a.f5748f);
                aVar.f5834c = strValueOf3;
                aVar.f5832a = strValueOf2;
                aVar.f5833b = strValueOf;
                aVar.f5836e = C1970a.f5747e;
                aVar.f5835d = canonicalName;
                c1999d.f5831a = aVar;
                C2095a.m2543b(c1999d);
            }
            C2065d.f6155e = true;
            Runnable runnable = C2065d.f6157g;
            if (runnable != null) {
                C2065d.f6156f.removeCallbacks(runnable);
            }
            Handler handler = C2065d.f6156f;
            RunnableC2064c runnableC2064c = new RunnableC2064c();
            C2065d.f6157g = runnableC2064c;
            handler.postDelayed(runnableC2064c, 600L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (activity != null) {
            if (((ArrayList) C2065d.f6151a).contains(activity.toString())) {
                return;
            }
            String string = activity.toString();
            ((HashMap) C2065d.f6152b).put(string, Long.valueOf(SystemClock.elapsedRealtime()));
            ((HashMap) C2065d.f6153c).put(string, Long.valueOf(System.currentTimeMillis()));
            C2065d.f6155e = false;
            boolean z6 = !C2065d.f6154d;
            C2065d.f6154d = true;
            Runnable runnable = C2065d.f6157g;
            if (runnable != null) {
                C2065d.f6156f.removeCallbacks(runnable);
            }
            if (z6) {
                Objects.requireNonNull(C2062a.m2448a());
                HashMap map = new HashMap();
                map.put("state", DiskLruCache.VERSION_1);
                C1896a.m2197a().m2198b("app_state", "APP状态", map);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
