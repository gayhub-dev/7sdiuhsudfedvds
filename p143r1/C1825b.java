package p143r1;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import com.cctv.p025tv.module.collect.report.event.ReportPageEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.cache.DiskLruCache;
import p043f.C0988e;
import p117o1.C1579a;
import p127p1.C1735a;
import p135q1.C1760c;

/* compiled from: ReportSensorsDataPrivate.java */
/* renamed from: r1.b */
/* loaded from: classes.dex */
public class C1825b implements Application.ActivityLifecycleCallbacks {
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
            if (((HashMap) C1827d.f5305b).containsKey(string)) {
                try {
                    jLongValue = ((Long) ((HashMap) C1827d.f5305b).get(string)).longValue();
                } catch (Exception unused) {
                }
            } else {
                jLongValue = jElapsedRealtime - 1000;
            }
            ((HashMap) C1827d.f5305b).remove(string);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (((HashMap) C1827d.f5306c).containsKey(string)) {
                try {
                    jLongValue2 = ((Long) ((HashMap) C1827d.f5306c).get(string)).longValue();
                } catch (Exception unused2) {
                }
            } else {
                jLongValue2 = jCurrentTimeMillis - 1000;
            }
            ((HashMap) C1827d.f5306c).remove(string);
            String canonicalName = activity.getClass().getCanonicalName();
            C1824a c1824aM2066a = C1824a.m2066a();
            String strValueOf = String.valueOf(jCurrentTimeMillis);
            String strValueOf2 = String.valueOf(jLongValue2);
            String strValueOf3 = String.valueOf(jElapsedRealtime - jLongValue);
            Objects.requireNonNull(c1824aM2066a);
            ReportPageEvent reportPageEvent = new ReportPageEvent();
            ReportPageEvent.PageD1Bean pageD1Bean = new ReportPageEvent.PageD1Bean();
            pageD1Bean.setCctv_id(C0988e.m987m(C0988e.f1828f));
            pageD1Bean.setDevice_id(C0988e.m992r(C0988e.f1828f));
            pageD1Bean.setNetwork_type(C1735a.m1881e(C0988e.f1828f));
            pageD1Bean.setDuration(strValueOf3);
            pageD1Bean.setStart_time(strValueOf2);
            pageD1Bean.setEnd_time(strValueOf);
            pageD1Bean.setSession_id(C0988e.f1829g);
            pageD1Bean.setPage_name(canonicalName);
            reportPageEvent.setPage_d1(pageD1Bean);
            C1760c.m1927a(reportPageEvent.toJson());
            C1827d.f5308e = true;
            Runnable runnable = C1827d.f5310g;
            if (runnable != null) {
                C1827d.f5309f.removeCallbacks(runnable);
            }
            Handler handler = C1827d.f5309f;
            RunnableC1826c runnableC1826c = new RunnableC1826c();
            C1827d.f5310g = runnableC1826c;
            handler.postDelayed(runnableC1826c, 600L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (activity != null) {
            if (((ArrayList) C1827d.f5304a).contains(activity.toString())) {
                return;
            }
            String string = activity.toString();
            ((HashMap) C1827d.f5305b).put(string, Long.valueOf(SystemClock.elapsedRealtime()));
            ((HashMap) C1827d.f5306c).put(string, Long.valueOf(System.currentTimeMillis()));
            C1827d.f5308e = false;
            boolean z6 = !C1827d.f5307d;
            C1827d.f5307d = true;
            Runnable runnable = C1827d.f5310g;
            if (runnable != null) {
                C1827d.f5309f.removeCallbacks(runnable);
            }
            if (z6) {
                Objects.requireNonNull(C1824a.m2066a());
                HashMap map = new HashMap();
                map.put("state", DiskLruCache.VERSION_1);
                C1579a.m1830a().m1831b("app_state", "APP状态", map);
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
