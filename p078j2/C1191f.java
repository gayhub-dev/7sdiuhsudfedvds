package p078j2;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ctvit.dlna.moudle.dmr.DLNARendererService;
import com.tencent.mars.xlog.Log;
import java.util.Objects;
import p085k1.C1227a;
import p118o2.C1581b;
import p118o2.ServiceConnectionC1580a;
import p179w2.C2025a;
import p186x2.C2073a;
import p192y1.C2104h;
import p200z2.C2150a;

/* compiled from: DlnaServiceUtils.java */
/* renamed from: j2.f */
/* loaded from: classes.dex */
public class C1191f {
    /* renamed from: a */
    public static void m1407a() {
        if (C1227a.f2757a.equals("BINDING") || C1227a.f2757a.equals("UNBINDING")) {
            return;
        }
        C1227a.f2757a = "BINDING";
        C1581b c1581bM1836e = C1581b.m1836e();
        Objects.requireNonNull(c1581bM1836e);
        if (C2025a.m2377e(DLNARendererService.class.getName())) {
            C2073a.m2459d("投屏服务存在，不执行绑定服务逻辑");
            return;
        }
        c1581bM1836e.f4754j = null;
        try {
            c1581bM1836e.f4754j = new ServiceConnectionC1580a(c1581bM1836e, true);
            C1581b.m1836e();
            Context context = C1581b.f4744m;
            C1581b.m1836e();
            context.bindService(new Intent(C1581b.f4744m, (Class<?>) DLNARendererService.class), c1581bM1836e.f4754j, 1);
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public static void m1408b(Context context) {
        C1581b.m1836e().f4748d = new C2104h(context);
    }

    /* renamed from: c */
    public static synchronized void m1409c(boolean z6) {
        Log.m655i("XLog_DLNA ", "DlnaServiceUtils service()");
        if (!z6) {
            Log.m655i("XLog_DLNA ", "检查是否同意隐私再看开不开启服务");
            if (!C1186a.m1385e()) {
                return;
            }
        }
        if (C2025a.m2377e(DLNARendererService.class.getName())) {
            C2073a.m2459d("投屏服务存在");
            Log.m655i("XLog_DLNA ", "DlnaServiceUtils 投屏服务存在");
            String strM1417a = C1195j.m1417a();
            String str = (String) C2150a.m2590a("SERVICE_START_IP", "");
            C2073a.m2459d("curIp：" + strM1417a);
            C2073a.m2459d("oldIp：" + str);
            if (!TextUtils.isEmpty(strM1417a) && !TextUtils.isEmpty(str)) {
                if (!str.equals(strM1417a)) {
                    C2073a.m2459d("投屏服务存在，但不是一个IP Restart");
                    Log.m655i("XLog_DLNA ", "DlnaServiceUtils 投屏服务存在，但不是一个IP Restart");
                    m1410d();
                }
            }
            return;
        }
        C2073a.m2459d("投屏服务不存在 开启投屏服务");
        Log.m655i("XLog_DLNA ", "DlnaServiceUtils 投屏服务不存在 开启投屏服务");
        String strM1417a2 = C1195j.m1417a();
        if (!TextUtils.isEmpty(strM1417a2)) {
            C2150a.m2591b("SERVICE_START_IP", strM1417a2);
        }
        m1407a();
    }

    /* renamed from: d */
    public static void m1410d() {
        if (C1227a.f2757a.equals("BINDING") || C1227a.f2757a.equals("UNBINDING")) {
            return;
        }
        C1227a.f2757a = "UNBINDING";
        C1581b c1581bM1836e = C1581b.m1836e();
        Objects.requireNonNull(c1581bM1836e);
        if (!C2025a.m2377e(DLNARendererService.class.getName())) {
            C2073a.m2459d("投屏服务不存在，不执行解绑服务逻辑");
            return;
        }
        try {
            C1581b.m1836e();
            if (C1581b.f4744m == null || c1581bM1836e.f4754j == null) {
                return;
            }
            C2073a.m2459d("解绑Upnp服务");
            C1581b.m1836e();
            C1581b.f4744m.unbindService(c1581bM1836e.f4754j);
        } catch (Exception unused) {
        }
    }
}
