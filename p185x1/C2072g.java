package p185x1;

import android.os.Build;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.entity.PlayerThresholdEntity;
import p009b.C0413b;
import p078j2.C1197l;
import p186x2.C2073a;

/* compiled from: PlayerThreshold.java */
/* renamed from: x1.g */
/* loaded from: classes.dex */
public class C2072g {

    /* renamed from: e */
    public static volatile C2072g f6167e;

    /* renamed from: a */
    public PlayerThresholdEntity.DataBean.DefaultVodBean f6168a;

    /* renamed from: b */
    public PlayerThresholdEntity.DataBean.DefaultLiveBean f6169b;

    /* renamed from: c */
    public String f6170c = Build.BRAND;

    /* renamed from: d */
    public String f6171d = Build.MODEL;

    public C2072g() {
        StringBuilder sbM112a = C0413b.m112a("设备品牌：");
        sbM112a.append(this.f6170c);
        C2073a.m2459d(sbM112a.toString());
        C2073a.m2459d("设备型号：" + this.f6171d);
    }

    /* renamed from: a */
    public final void m2454a(int i7, int i8, int i9, int i10) {
        int i11 = 0;
        int iM1421a = C1197l.m1421a(MyApplication.f561e, "NETSPEED_RESULT", 0);
        int iM2455b = m2455b(i7, 38);
        int iM2455b2 = m2455b(i8, 12);
        int iM2455b3 = m2455b(i9, 28);
        int iM2455b4 = m2455b(i10, 10);
        int i12 = iM1421a >= iM2455b ? 2 : iM1421a >= iM2455b2 ? 1 : 0;
        if (iM1421a >= iM2455b3) {
            i11 = 2;
        } else if (iM1421a >= iM2455b4) {
            i11 = 1;
        }
        C2073a.m2459d("vodIndex =" + i12);
        C2073a.m2459d("liveIndex =" + i11);
        C1197l.m1423c(MyApplication.f561e, "VOD_RESULT", i12);
        C1197l.m1423c(MyApplication.f561e, "LIVE_RESULT", i11);
        C1197l.m1424d(MyApplication.f561e, "IS_NETSPEED_TEST", true);
    }

    /* renamed from: b */
    public final int m2455b(int i7, int i8) {
        if (i7 != 0) {
            return i7;
        }
        C2073a.m2459d("defaultValue = " + i8);
        return i8;
    }
}
