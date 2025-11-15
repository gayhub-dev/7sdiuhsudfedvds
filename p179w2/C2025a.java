package p179w2;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.support.v7.widget.ActivityChooserModel;
import android.support.v7.widget.ActivityChooserView;
import android.text.TextUtils;
import java.util.List;
import p086k2.C1231b;
import p186x2.C2073a;

/* compiled from: CtvitAppUtils.java */
/* renamed from: w2.a */
/* loaded from: classes.dex */
public final class C2025a {
    /* renamed from: a */
    public static String m2373a() {
        try {
            return C1231b.f2761c.getResources().getString(m2374b().applicationInfo.labelRes);
        } catch (Exception e7) {
            C2073a.m2458c(e7);
            return null;
        }
    }

    /* renamed from: b */
    public static PackageInfo m2374b() {
        Application application = C1231b.f2761c;
        return application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
    }

    /* renamed from: c */
    public static int m2375c() {
        try {
            return m2374b().versionCode;
        } catch (Exception e7) {
            C2073a.m2458c(e7);
            return -1;
        }
    }

    /* renamed from: d */
    public static String m2376d() {
        try {
            return m2374b().versionName;
        } catch (Exception e7) {
            C2073a.m2458c(e7);
            return null;
        }
    }

    /* renamed from: e */
    public static boolean m2377e(String str) throws SecurityException {
        try {
            List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) C1231b.f2761c.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY)).getRunningServices(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            int iMyUid = Process.myUid();
            for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                ComponentName componentName = runningServiceInfo.service;
                if (componentName != null && !TextUtils.isEmpty(componentName.getClassName()) && runningServiceInfo.uid == iMyUid && runningServiceInfo.service.getClassName().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
