package p101m1;

import android.content.Context;
import com.cctv.p025tv.module.broadcast.BootBroadcast;
import com.cctv.p025tv.mvp.p026ui.fragment.DeviceCheckFragment;
import java.io.File;
import org.fourthline.cling.model.ServiceReference;
import p038e2.C0945a;
import p078j2.C1186a;
import p078j2.C1191f;
import p078j2.C1193h;
import p135q1.C1759b;
import p180w3.AbstractC2028b;
import p186x2.C2073a;

/* renamed from: m1.a */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1457a implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ int f4224e = 0;

    /* renamed from: f */
    public final /* synthetic */ Object f4225f;

    public /* synthetic */ RunnableC1457a(String str) {
        this.f4225f = str;
    }

    public /* synthetic */ RunnableC1457a(C1759b c1759b) {
        this.f4225f = c1759b;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4224e) {
            case 0:
                Context context = (Context) this.f4225f;
                int i7 = BootBroadcast.f580a;
                C1191f.m1408b(context);
                C1191f.m1409c(false);
                C1186a.m1393m();
                return;
            case 1:
                C1759b c1759b = (C1759b) this.f4225f;
                synchronized (c1759b) {
                    File[] fileArrListFiles = new File(c1759b.f5007e).listFiles();
                    if (fileArrListFiles != null) {
                        for (File file : fileArrListFiles) {
                            C2073a.m2459d("report uploadCache file 上传 =" + file.getName());
                            c1759b.m1925b(file.getAbsolutePath());
                        }
                    }
                }
                return;
            case 2:
                String str = (String) this.f4225f;
                File file2 = new File(str);
                if (!file2.exists()) {
                    C2073a.m2456a("report ReportCache deleteFile 不存在了" + str);
                    return;
                }
                if (file2.delete()) {
                    C2073a.m2459d("report ReportCache deleteFile 成功" + str);
                    return;
                }
                C2073a.m2456a("report ReportCache deleteFile 失败" + str);
                return;
            case 3:
                DeviceCheckFragment deviceCheckFragment = (DeviceCheckFragment) this.f4225f;
                for (C0945a c0945a : deviceCheckFragment.f773k) {
                    if (c0945a.f1708a.contains("GPU信息")) {
                        c0945a.m857a(C1193h.f2615a + ServiceReference.DELIMITER + C1193h.f2616b);
                        deviceCheckFragment.f774l.notifyDataSetChanged();
                        return;
                    }
                }
                return;
            default:
                ((AbstractC2028b) this.f4225f).mo667h();
                return;
        }
    }
}
