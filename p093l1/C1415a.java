package p093l1;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.constraint.C0072a;
import android.support.v4.app.C0164a;
import android.text.TextUtils;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.module.collect.report.event.ReportStartEvent;
import com.tencent.mars.xlog.Log;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import p009b.C0413b;
import p019c1.C0504a;
import p043f.C0988e;
import p086k2.C1231b;
import p117o1.C1579a;
import p135q1.C1759b;
import p143r1.C1825b;
import p143r1.C1827d;
import p145r3.C1836c;
import p156t0.C1896a;
import p163u0.C1970a;
import p170v0.C2001f;
import p184x0.C2063b;
import p184x0.C2065d;
import p186x2.C2073a;
import p191y0.C2095a;
import p198z0.C2145a;
import p198z0.C2146b;
import p200z2.C2150a;

/* compiled from: CCTVAgentInit.java */
/* renamed from: l1.a */
/* loaded from: classes.dex */
public class C1415a {

    /* renamed from: a */
    public static volatile C1415a f4149a;

    /* renamed from: b */
    public static String f4150b;

    /* renamed from: a */
    public static C1415a m1599a() {
        if (f4149a == null) {
            synchronized (C1415a.class) {
                if (f4149a == null) {
                    f4149a = new C1415a();
                }
            }
        }
        return f4149a;
    }

    /* renamed from: b */
    public void m1600b(Context context) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        String string;
        String string2;
        StringBuilder sbM112a = C0413b.m112a("Application Init...");
        sbM112a.append(C1415a.class.getSimpleName());
        C2073a.m2459d(sbM112a.toString());
        String strM2084c = C1836c.m2084c(context, null);
        if (TextUtils.isEmpty(strM2084c)) {
            try {
                string = C1231b.f2761c.getPackageManager().getApplicationInfo(C1231b.f2761c.getPackageName(), 128).metaData.getString("CCTV_TV_CHANNEL");
            } catch (Exception e7) {
                C2073a.m2458c(e7);
                string = "";
            }
            if (TextUtils.isEmpty(string)) {
                f4150b = "CCTV";
            } else {
                f4150b = string;
            }
        } else {
            f4150b = strM2084c;
        }
        StringBuilder sbM112a2 = C0413b.m112a("channel gradle = ");
        sbM112a2.append(f4150b);
        C2073a.m2459d(sbM112a2.toString());
        Log.m655i("XLog_APP ", "channel gradle = " + f4150b);
        String str = f4150b;
        Log.m655i("XLog_APP ", "initCCTVAgent");
        Objects.requireNonNull(C1896a.m2197a());
        C1970a.f5744b = "";
        C1970a.f5746d = str;
        C1970a.f5751i = true;
        C1970a.f5743a = false;
        C1970a.f5745c = "1178c84d-4818-44ff-b415-02106e87e144";
        C1970a.f5749g = 3;
        C1970a.f5750h = 2;
        C1970a.f5753k = "i7WTqTvh0OioIruIfFR4kMPnBqrS2rdiVPl/s2uC/CY=";
        MyApplication myApplication = MyApplication.f561e;
        if (myApplication == null) {
            C0988e.m982h("CCTVAgent初始化失败,context不可为空");
        } else if (C1970a.f5749g == 0) {
            C0988e.m982h("CCTVAgent初始化失败,DeviceType不可为空");
        } else if (TextUtils.isEmpty(C1970a.f5745c)) {
            C0988e.m982h("CCTVAgent初始化失败,appKey不可为空");
        } else if (!C1970a.f5752j) {
            C1970a.f5748f = myApplication;
            if (Environment.getExternalStorageState().equals("mounted") || !Environment.isExternalStorageRemovable()) {
                StringBuilder sb = new StringBuilder();
                File externalFilesDir = myApplication.getExternalFilesDir("");
                if (externalFilesDir != null) {
                    sb.append(externalFilesDir.getAbsolutePath());
                } else {
                    sb.append(Environment.getExternalStorageDirectory().getPath());
                    sb.append("/Android/data/");
                    sb.append(myApplication.getPackageName());
                    sb.append("/files");
                }
                string2 = sb.toString();
            } else {
                string2 = myApplication.getCacheDir().getAbsolutePath();
            }
            StringBuilder sbM112a3 = C0413b.m112a(string2);
            String str2 = File.separator;
            C1970a.f5754l = C0164a.m99a(sbM112a3, str2, "cctv_agent_cache", str2);
            C1970a.f5747e = UUID.randomUUID().toString();
            C2001f c2001f = new C2001f();
            C2001f.a aVar = new C2001f.a();
            C2146b.m2588b(myApplication);
            C2146b.m2589c(myApplication);
            C2146b.m2587a(myApplication);
            C2145a.m2578f(myApplication);
            String str3 = Build.BOARD;
            String str4 = Build.BRAND;
            String str5 = Build.DISPLAY;
            String str6 = Build.TYPE;
            String str7 = Build.FINGERPRINT;
            String str8 = Build.ID;
            String str9 = Build.HARDWARE;
            String str10 = Build.USER;
            String str11 = Build.PRODUCT;
            String str12 = Build.TAGS;
            String str13 = Build.HOST;
            C2145a.m2579g();
            C2145a.m2582j();
            C2145a.m2583k(myApplication);
            C2145a.m2585m();
            int i7 = C2145a.f6310a;
            C2145a.m2573a(myApplication);
            C2145a.m2584l();
            C2145a.m2581i();
            C2145a.m2574b();
            c2001f.f5845a = aVar;
            C2095a.m2543b(c2001f);
            String strM313a = C0504a.m313a(myApplication, "version", "");
            String strM2573a = C2145a.m2573a(myApplication);
            if (!TextUtils.isEmpty(strM2573a)) {
                if (!strM2573a.equals(strM313a)) {
                    HashMap map = new HashMap();
                    map.put("cur_version", strM2573a);
                    map.put("channel", C1970a.f5746d);
                    map.put("pre_version", strM313a);
                    C1896a.m2197a().m2198b("app_start", "APP启动", map);
                }
                C0504a.m314b(myApplication, "version", strM2573a);
            }
            List<String> list = C2065d.f6151a;
            myApplication.registerActivityLifecycleCallbacks(new C2063b());
            C2095a.m2544c();
            C1970a.f5752j = true;
        }
        Objects.requireNonNull(C1579a.m1830a());
        C0988e.f1826d = "1178c84d-4818-44ff-b415-02106e87e144";
        C0988e.f1827e = str;
        MyApplication myApplication2 = MyApplication.f561e;
        if (myApplication2 == null) {
            C2073a.m2456a("CCTVAgent初始化失败,context不可为空");
        } else {
            C0988e.f1828f = myApplication2;
            StringBuilder sb2 = new StringBuilder();
            File externalFilesDir2 = myApplication2.getExternalFilesDir("");
            if (externalFilesDir2 != null) {
                sb2.append(externalFilesDir2.getAbsolutePath());
            } else {
                sb2.append(Environment.getExternalStorageDirectory().getPath());
                sb2.append("/Android/data/");
                sb2.append(myApplication2.getPackageName());
                sb2.append("/files");
            }
            C0988e.f1830h = sb2.toString();
            StringBuilder sbM112a4 = C0413b.m112a("report CCTVAgentConstant.CACHE_DIR = ");
            sbM112a4.append(C0988e.f1830h);
            C2073a.m2459d(sbM112a4.toString());
            C0988e.f1829g = UUID.randomUUID().toString();
            C1759b c1759bM1924a = C1759b.m1924a();
            c1759bM1924a.f5004b = ((Integer) C2150a.m2590a(c1759bM1924a.f5005c, 0)).intValue();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(C0988e.f1830h);
            File file = new File(C0072a.m92a(sb3, File.separator, "report"));
            if (!file.exists()) {
                file.mkdir();
            }
            c1759bM1924a.f5007e = file.getAbsolutePath();
            StringBuilder sbM112a5 = C0413b.m112a("report_data_");
            sbM112a5.append(System.currentTimeMillis());
            c1759bM1924a.f5006d = sbM112a5.toString();
            ReportStartEvent.post(myApplication2);
            C2073a.m2459d("report 页面数据收集");
            List<String> list2 = C1827d.f5304a;
            myApplication2.registerActivityLifecycleCallbacks(new C1825b());
        }
        C0988e.f1831i = str;
    }
}
