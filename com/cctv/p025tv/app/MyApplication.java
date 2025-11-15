package com.cctv.p025tv.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import com.cctv.p025tv.utils.PermissionsUtils;
import com.ctvit.dlna.entity.DlnaContentEntity;
import com.ctvit.network.CtvitHttp;
import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;
import p009b.C0413b;
import p029d1.C0866c;
import p078j2.C1186a;
import p086k2.C1230a;
import p086k2.C1231b;
import p093l1.C1417c;
import p093l1.C1418d;
import p093l1.C1419e;
import p118o2.C1581b;
import p141r.ComponentCallbacks2C1808c;
import p152s3.C1873d;
import p152s3.C1874e;
import p152s3.C1876g;
import p158t2.C1898a;
import p160t4.C1908a;
import p165u2.C1974a;
import p186x2.C2073a;
import p192y1.C2103g;

/* loaded from: classes.dex */
public class MyApplication extends Application {

    /* renamed from: e */
    public static MyApplication f561e = null;

    /* renamed from: f */
    public static DlnaContentEntity f562f = null;

    /* renamed from: g */
    public static DlnaContentEntity f563g = null;

    /* renamed from: h */
    public static String f564h = null;

    /* renamed from: i */
    public static long f565i = 0;

    /* renamed from: j */
    public static String f566j = null;

    /* renamed from: k */
    public static String f567k = null;

    /* renamed from: l */
    public static boolean f568l = false;

    /* renamed from: m */
    public static DlnaContentEntity f569m = null;

    /* renamed from: n */
    public static boolean f570n = false;

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
        try {
            Class<?> cls = Class.forName("java.lang.Daemons$FinalizerWatchdogDaemon");
            Method declaredMethod = cls.getSuperclass().getDeclaredMethod("stop", new Class[0]);
            declaredMethod.setAccessible(true);
            Field declaredField = cls.getDeclaredField("INSTANCE");
            declaredField.setAccessible(true);
            declaredMethod.invoke(declaredField.get(null), new Object[0]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        String strValueOf;
        super.onCreate();
        f561e = this;
        C1418d c1418dM1602a = C1418d.m1602a();
        Objects.requireNonNull(c1418dM1602a);
        C2073a.m2459d("Application Init..." + C1418d.class.getSimpleName());
        c1418dM1602a.f4154a = this;
        C1231b.f2761c = this;
        C1231b c1231bM1454a = C1231b.m1454a();
        try {
            strValueOf = Settings.Secure.getString(c1418dM1602a.f4154a.getContentResolver(), "android_id");
        } catch (Exception unused) {
            strValueOf = "";
        }
        try {
            if (TextUtils.isEmpty(strValueOf) || "9774d56d682e549c".equals(strValueOf) || "000000000000000".equals(strValueOf)) {
                strValueOf = String.valueOf(UUID.randomUUID());
            }
        } catch (Exception unused2) {
        }
        c1231bM1454a.f2763a = strValueOf;
        C1231b.f2762d = false;
        C1231b.f2762d = false;
        C1876g.b bVar = new C1876g.b(null);
        bVar.f5463e = "ctvit_log";
        bVar.f5459a = Math.max(0, 0);
        bVar.f5460b = Math.max(0, 0);
        bVar.f5461c = false;
        bVar.f5462d = new C1873d();
        C1874e.f5451a.f5453b.add(new C1230a(c1231bM1454a, new C1876g(bVar, null)));
        C1581b.f4744m = c1418dM1602a.f4154a;
        C1581b.m1836e().f4753i = c1418dM1602a.f4155b;
        Objects.requireNonNull(C1581b.m1836e());
        CtvitHttp.init(c1418dM1602a.f4154a);
        if (C1186a.m1385e()) {
            C1419e.m1603a();
        }
        Application application = c1418dM1602a.f4154a;
        C1974a.f5773c = application;
        application.registerActivityLifecycleCallbacks(new C1974a.a());
        C1898a.f5593a = "https://ytpvdn.cctv.cn/cctvmobileinf/rest/cctv/videoliveUrl/getstream";
        C1898a.f5594b = "https://ytpvdn.cctv.cn/cctvmobileinf/rest/cctv/videoliveUrl/getscreenstream";
        C1417c c1417cM1601a = C1417c.m1601a();
        Objects.requireNonNull(c1417cM1601a);
        Thread.setDefaultUncaughtExceptionHandler(c1417cM1601a);
        c1417cM1601a.f4152a = Thread.getDefaultUncaughtExceptionHandler();
        PermissionsUtils.init();
        C1908a.f5611a = C2103g.f6235f;
        C0866c c0866cM674a = C0866c.m674a();
        Application application2 = c1418dM1602a.f4154a;
        Objects.requireNonNull(c0866cM674a);
        try {
            System.loadLibrary("c++_shared");
            System.loadLibrary("marsxlog");
        } catch (Exception e7) {
            C2073a.m2456a("xlog e = " + e7);
        }
        File externalFilesDir = application2.getExternalFilesDir("xlog");
        if (externalFilesDir != null) {
            C0866c.f1294b = externalFilesDir.getAbsolutePath();
        } else {
            C0866c.f1294b = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yspdstpzs/xlog";
        }
        StringBuilder sbM112a = C0413b.m112a("TXL logPath = ");
        sbM112a.append(C0866c.f1294b);
        C2073a.m2459d(sbM112a.toString());
        Xlog.open(false, 1, 0, application2.getFilesDir() + "/xlog", C0866c.f1294b, "CCTV_LOG_", "04c4c7e2cfaeafe73c4833af4b2fb2ce01d87f8ed883b411b32219257a056e134f303bbc391d8dc8297ee5bff30171aa643a60b128cbb6574a461e43efa7120e");
        Xlog xlog = new Xlog();
        xlog.setMaxAliveTime(0L, 86400L);
        xlog.setConsoleLogOpen(0L, false);
        Log.setLogImp(xlog);
        Log.m655i("XLog_APP ", "XLog 初始化");
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        ComponentCallbacks2C1808c.m2022c(this).m2024b();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i7) {
        super.onTrimMemory(i7);
        if (i7 == 20) {
            ComponentCallbacks2C1808c.m2022c(this).m2024b();
        }
        ComponentCallbacks2C1808c.m2022c(this).m2025d(i7);
    }
}
