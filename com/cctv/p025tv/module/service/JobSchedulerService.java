package com.cctv.p025tv.module.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import com.ctvit.dlna.moudle.dmr.DLNARendererService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p078j2.C1186a;
import p078j2.C1189d;
import p078j2.C1191f;
import p179w2.C2025a;
import p186x2.C2073a;

@RequiresApi(api = 21)
/* loaded from: classes.dex */
public class JobSchedulerService extends JobService {

    /* renamed from: e */
    public static List<String> f634e;

    static {
        ArrayList arrayList = new ArrayList(3);
        f634e = arrayList;
        arrayList.add(CctvTvService.class.getName());
        f634e.add(DLNARendererService.class.getName());
    }

    @Override // android.app.Service
    public void onDestroy() {
        C2073a.m2459d("onDestroy");
        C1186a.m1390j();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        return 1;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        if (!C1186a.m1385e() && !C1189d.m1403e(getApplicationContext()) && !C1189d.m1405g(getApplicationContext()) && !C1189d.m1402d(getApplicationContext()) && !C1189d.m1404f(getApplicationContext())) {
            jobFinished(jobParameters, false);
        }
        C2073a.m2459d("onStartJob");
        try {
            C2073a.m2459d("处理消息任务运行：JobService");
            Iterator it = ((ArrayList) f634e).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String str = (String) it.next();
                if (!C2025a.m2377e(str)) {
                    C2073a.m2456a("服务被杀死，重新开启所有服务：" + str);
                    C1191f.m1409c(false);
                    C1186a.m1393m();
                    break;
                }
            }
            jobFinished(jobParameters, false);
            return true;
        } catch (Exception e7) {
            C2073a.m2458c(e7);
            return false;
        }
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        C2073a.m2459d("onStopJob");
        return false;
    }
}
