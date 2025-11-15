package p020c2;

import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.cctv.p025tv.mvp.p026ui.fragment.VersionUpdateFragment;
import com.ctvit.dlna.entity.DlnaContentEntity;
import com.tencent.mars.xlog.Log;
import java.util.Objects;
import p078j2.C1186a;
import p078j2.C1189d;
import p085k1.C1229c;
import p118o2.C1581b;
import p136q2.InterfaceC1761a;
import p144r2.C1830c;
import p150s1.C1868e;
import p186x2.C2073a;

/* renamed from: c2.e */
/* loaded from: classes.dex */
public final /* synthetic */ class C0510e implements InterfaceC1761a, C1868e.a {

    /* renamed from: e */
    public final /* synthetic */ MainActivity f384e;

    public /* synthetic */ C0510e(MainActivity mainActivity, int i7) {
        this.f384e = mainActivity;
    }

    @Override // p136q2.InterfaceC1761a
    /* renamed from: a */
    public void mo318a(DlnaContentEntity dlnaContentEntity) {
        MainActivity mainActivity = this.f384e;
        int i7 = MainActivity.f663D;
        Objects.requireNonNull(mainActivity);
        C2073a.m2459d("DLNA 播放指令 - 首页：" + C1868e.f5445a);
        Log.m655i("XLog_DLNA ", "MainActivity 播放指令");
        if (mainActivity.m472o()) {
            MyApplication.f569m = dlnaContentEntity;
            Log.m655i("XLog_DLNA ", "MainActivity 华为拦截 接收投屏数据");
            if (C1581b.m1833b() != null) {
                ((C1830c.b) C1581b.m1833b()).m2078e();
                return;
            }
            return;
        }
        if ((C1189d.m1403e(mainActivity.getApplicationContext()) || C1189d.m1405g(mainActivity.getApplicationContext()) || C1189d.m1402d(mainActivity.getApplicationContext()) || C1189d.m1404f(mainActivity.getApplicationContext())) && !C1186a.m1385e()) {
            MyApplication.f562f = dlnaContentEntity;
            Log.m655i("XLog_DLNA ", "MainActivity JuHaoKan 接收投屏数据");
            if (C1581b.m1833b() != null) {
                ((C1830c.b) C1581b.m1833b()).m2078e();
                return;
            }
            return;
        }
        if (VersionUpdateFragment.f849l) {
            C2073a.m2459d("更新页显示中 - 首页");
            MyApplication.f563g = dlnaContentEntity;
        } else if (C1186a.m1389i()) {
            C1229c.f2759a = "other";
            mainActivity.m477t(dlnaContentEntity, false);
        }
    }
}
