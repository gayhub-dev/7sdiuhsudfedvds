package com.cctv.p025tv.module.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.cctv.p025tv.R;
import com.ctvit.dlna.moudle.dmr.DLNARendererService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p078j2.C1186a;
import p078j2.C1191f;
import p179w2.C2025a;
import p179w2.C2026b;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class LowVersionKeepAliveService extends Service {

    /* renamed from: e */
    public static List<String> f635e;

    /* renamed from: com.cctv.tv.module.service.LowVersionKeepAliveService$a */
    public class C0590a extends Thread {
        public C0590a(LowVersionKeepAliveService lowVersionKeepAliveService) {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Iterator it = ((ArrayList) LowVersionKeepAliveService.f635e).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String str = (String) it.next();
                if (!C2025a.m2377e(str)) {
                    C2073a.m2459d("服务被杀死，重新开启所有服务：" + str);
                    C1191f.m1409c(false);
                    C1186a.m1393m();
                    break;
                }
            }
            super.run();
        }
    }

    static {
        ArrayList arrayList = new ArrayList(3);
        f635e = arrayList;
        arrayList.add(CctvTvService.class.getName());
        f635e.add(DLNARendererService.class.getName());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        C2073a.m2459d("onCreate被调用，启动前台service");
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(C2025a.m2373a());
        builder.setContentText(C2026b.m2379b(R.string.notification_description));
        startForeground(1002, builder.build());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ((NotificationManager) getSystemService("notification")).cancel(1002);
        C2073a.m2459d("onDestroy，前台service被杀死");
        C1186a.m1390j();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        C2073a.m2459d("onStartCommand");
        new C0590a(this).start();
        return 1;
    }
}
