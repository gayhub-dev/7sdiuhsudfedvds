package p078j2;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.cctv.p025tv.app.MyApplication;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: CtvitNetSpeedUtils.java */
/* renamed from: j2.c */
/* loaded from: classes.dex */
public class C1188c {

    /* renamed from: c */
    public long f2609c;

    /* renamed from: d */
    public b f2610d;

    /* renamed from: e */
    public Timer f2611e;

    /* renamed from: f */
    public TimerTask f2612f;

    /* renamed from: g */
    public Handler f2613g = new a(Looper.getMainLooper());

    /* renamed from: b */
    public long f2608b = m1397b();

    /* renamed from: a */
    public long f2607a = m1397b() - this.f2608b;

    /* compiled from: CtvitNetSpeedUtils.java */
    /* renamed from: j2.c$a */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                b bVar = C1188c.this.f2610d;
                if (bVar != null) {
                    bVar.mo431f(0L);
                    return;
                }
                return;
            }
            C1188c.this.f2609c = ((Long) message.obj).longValue();
            C1188c c1188c = C1188c.this;
            b bVar2 = c1188c.f2610d;
            if (bVar2 != null) {
                bVar2.mo431f(c1188c.f2609c);
            }
        }
    }

    /* compiled from: CtvitNetSpeedUtils.java */
    /* renamed from: j2.c$b */
    public interface b {
        /* renamed from: f */
        void mo431f(long j7);
    }

    /* renamed from: a */
    public void m1396a() {
        Timer timer = this.f2611e;
        if (timer != null) {
            timer.cancel();
            this.f2611e = null;
        }
        TimerTask timerTask = this.f2612f;
        if (timerTask != null) {
            timerTask.cancel();
            this.f2612f = null;
        }
    }

    @SuppressLint({"WrongConstant"})
    /* renamed from: b */
    public long m1397b() {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = MyApplication.f561e.getPackageManager().getApplicationInfo(MyApplication.f561e.getPackageName(), 1);
        } catch (PackageManager.NameNotFoundException e7) {
            e7.printStackTrace();
        }
        if (TrafficStats.getUidRxBytes(applicationInfo.uid) == -1) {
            return 0L;
        }
        return TrafficStats.getTotalRxBytes() / 1024;
    }

    /* renamed from: c */
    public void m1398c() {
        m1396a();
        this.f2612f = new C1187b(this);
        Timer timer = new Timer(C1188c.class.getSimpleName());
        this.f2611e = timer;
        timer.schedule(this.f2612f, 0L, 1000L);
    }
}
