package p078j2;

import android.content.Context;
import com.cctv.p025tv.app.MyApplication;
import com.tencent.mars.xlog.Log;
import p043f.C0988e;
import p093l1.C1415a;

/* compiled from: DeviceUtils.java */
/* renamed from: j2.d */
/* loaded from: classes.dex */
public class C1189d {
    /* renamed from: a */
    public static String m1399a() {
        String strM997w = C1415a.f4150b;
        if (strM997w == null) {
            strM997w = C0988e.m997w(MyApplication.f561e);
        }
        return "KuKai".equals(strM997w) ? "视听备【2024】B5858" : ("huanshiwang".equals(strM997w) || "ChangHong".equals(strM997w)) ? "视听备【2023】B1927" : "JuHaoKan".equals(strM997w) ? "视听备【2024】B5646" : "huawei".equals(strM997w) ? "视听备【2023】B4393" : "视听备【2023】B3406";
    }

    /* renamed from: b */
    public static boolean m1400b() {
        return "ChangHong".equals(C1415a.f4150b);
    }

    /* renamed from: c */
    public static boolean m1401c() {
        return "huanshiwang".equals(C1415a.f4150b);
    }

    /* renamed from: d */
    public static boolean m1402d(Context context) {
        String strM997w = C1415a.f4150b;
        if (strM997w == null) {
            strM997w = C0988e.m997w(context);
        }
        return "huawei".equals(strM997w);
    }

    /* renamed from: e */
    public static boolean m1403e(Context context) {
        String strM997w = C1415a.f4150b;
        if (strM997w == null) {
            strM997w = C0988e.m997w(context);
        }
        Log.m655i("XLog_APP ", "isJuHaoKan channel = " + strM997w);
        return "JuHaoKan".equals(strM997w);
    }

    /* renamed from: f */
    public static boolean m1404f(Context context) {
        String strM997w = C1415a.f4150b;
        if (strM997w == null) {
            strM997w = C0988e.m997w(context);
        }
        return "KuKai".equals(strM997w);
    }

    /* renamed from: g */
    public static boolean m1405g(Context context) {
        String strM997w = C1415a.f4150b;
        if (strM997w == null) {
            strM997w = C0988e.m997w(context);
        }
        return "XiaoMi".equals(strM997w);
    }
}
