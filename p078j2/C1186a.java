package p078j2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.widget.RelativeLayout;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.module.service.CctvTvService;
import com.cctv.p025tv.module.service.JobSchedulerService;
import com.cctv.p025tv.module.service.LowVersionKeepAliveService;
import com.cctv.p025tv.module.service.WebSocketService;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import p094l2.C1420a;
import p150s1.C1868e;
import p165u2.C1974a;
import p179w2.C2025a;
import p179w2.C2026b;
import p186x2.C2073a;
import p186x2.C2074b;

/* compiled from: CctvTvUtils.java */
/* renamed from: j2.a */
/* loaded from: classes.dex */
public class C1186a {

    /* renamed from: a */
    public static long f2605a;

    /* renamed from: a */
    public static boolean m1381a() {
        return C1197l.m1422b(MyApplication.f561e, "AUDIO_TRACK_AUTO_SWITCH", true);
    }

    /* renamed from: b */
    public static boolean m1382b() {
        if (System.currentTimeMillis() - f2605a <= 2000) {
            return true;
        }
        C1974a.m2299b(R.string.back).m2345a();
        f2605a = System.currentTimeMillis();
        return false;
    }

    /* renamed from: c */
    public static void m1383c(RelativeLayout relativeLayout, Float f7) {
        ViewCompat.animate(relativeLayout).setDuration(200L).scaleX(f7.floatValue()).scaleY(f7.floatValue()).start();
    }

    /* renamed from: d */
    public static void m1384d(RelativeLayout relativeLayout) {
        ViewCompat.animate(relativeLayout).setDuration(200L).scaleX(1.0f).scaleY(1.0f).start();
    }

    /* renamed from: e */
    public static boolean m1385e() {
        return C1197l.m1422b(MyApplication.f561e, "AGREE_PRIVACY_POLICY", false) && m1386f().equals(MyApplication.f561e.getSharedPreferences("config", 0).getString("PRIVACY_POLICY_CONTENT", ""));
    }

    /* renamed from: f */
    public static String m1386f() throws NoSuchAlgorithmException {
        String strTrim = C2026b.m2379b(R.string.privacy_policy_text).replace("\\n", "").replace(" ", "").trim();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(strTrim.getBytes());
            return C2074b.m2479b(messageDigest.digest());
        } catch (NoSuchAlgorithmException e7) {
            C2073a.m2458c(e7);
            return null;
        }
    }

    /* renamed from: g */
    public static int m1387g() {
        return C1197l.m1421a(MyApplication.f561e, "VIDEO_FRAME_SWITCH_RATE", 0);
    }

    /* renamed from: h */
    public static void m1388h(FragmentManager fragmentManager, MainActivity mainActivity) {
        if (C1189d.m1405g(MyApplication.f561e)) {
            return;
        }
        if ((C1189d.m1401c() || C1189d.m1400b()) && mainActivity != null) {
            if (mainActivity.m471n().booleanValue()) {
                mainActivity.m467j(false);
                return;
            } else {
                mainActivity.m478u();
                return;
            }
        }
        C1420a c1420aM1606c = C1420a.m1606c();
        c1420aM1606c.f4158a = MyApplication.f561e;
        c1420aM1606c.f4159b = "https://ytpaddr.cctv.cn/gsnw/version";
        C1868e.m2138a(fragmentManager, true);
    }

    /* renamed from: i */
    public static boolean m1389i() {
        if (!C1868e.f5445a && !C1868e.f5446b) {
            return true;
        }
        C2073a.m2459d("开启了强更，或从后台唤起后检测到的更新，暂拒收DLNA投屏指令");
        return false;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: j */
    public static void m1390j() {
        if (!m1385e() || C1189d.m1401c() || C1189d.m1404f(MyApplication.f561e)) {
            return;
        }
        int i7 = Build.VERSION.SDK_INT;
        C2073a.m2459d("版本高于或者等于21！--JobService 开启");
        if (C2025a.m2377e(JobSchedulerService.class.getName())) {
            C2073a.m2459d("JobService 存在！");
            return;
        }
        JobScheduler jobScheduler = (JobScheduler) MyApplication.f561e.getSystemService("jobscheduler");
        JobInfo.Builder builder = new JobInfo.Builder(C2025a.m2373a().hashCode(), new ComponentName(MyApplication.f561e.getPackageName(), JobSchedulerService.class.getName()));
        builder.setMinimumLatency(10000L).setRequiredNetworkType(1).setOverrideDeadline(10000L);
        if (i7 >= 23 && MyApplication.f561e.checkSelfPermission("android.permission.RECEIVE_BOOT_COMPLETED") == 0) {
            builder.setPersisted(true);
        }
        if (jobScheduler == null) {
            C2073a.m2456a("jobScheduler 为空");
        } else if (jobScheduler.schedule(builder.build()) <= 0) {
            C2073a.m2456a("JobService 定时失败");
        } else {
            C2073a.m2459d("JobService 定时成功");
        }
    }

    /* renamed from: k */
    public static boolean m1391k(Context context) {
        return Build.VERSION.SDK_INT >= 29 && !Settings.canDrawOverlays(context);
    }

    /* renamed from: l */
    public static void m1392l(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("appmarket://details?id=com.cctv.tv"));
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e7) {
            C2073a.m2459d(e7.toString());
        }
        C1868e.f5447c = true;
        C1868e.f5445a = false;
    }

    /* renamed from: m */
    public static void m1393m() {
        if (C1189d.m1401c() || C1189d.m1404f(MyApplication.f561e)) {
            return;
        }
        Intent intent = new Intent(MyApplication.f561e, (Class<?>) CctvTvService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            MyApplication.f561e.startForegroundService(intent);
        } else {
            MyApplication.f561e.startService(intent);
        }
    }

    /* renamed from: n */
    public static void m1394n(Activity activity) {
        C1191f.m1410d();
        MyApplication.f561e.stopService(new Intent(MyApplication.f561e, (Class<?>) CctvTvService.class));
        MyApplication.f561e.stopService(new Intent(MyApplication.f561e, (Class<?>) LowVersionKeepAliveService.class));
        MyApplication.f561e.stopService(new Intent(MyApplication.f561e, (Class<?>) WebSocketService.class));
        if (activity != null) {
            ((JobScheduler) activity.getSystemService("jobscheduler")).cancelAll();
            MainActivity mainActivity = (MainActivity) activity;
            if (mainActivity.f670i) {
                MyApplication.f561e.unbindService(mainActivity.f686y);
            }
            activity.finish();
        }
        System.exit(0);
    }

    /* renamed from: o */
    public static boolean m1395o() {
        return C1197l.m1422b(MyApplication.f561e, "SHARPNESS_SWITCH_MODEL", false) && C1197l.m1422b(MyApplication.f561e, "SHARPNESS_SWITCH_HINT", false);
    }
}
