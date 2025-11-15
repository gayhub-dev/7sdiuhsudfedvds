package p147r5;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import p009b.C0413b;
import p131p5.C1743c;
import p175v5.InterfaceC2016c;

/* compiled from: HashSessionManager.java */
/* renamed from: r5.e */
/* loaded from: classes.dex */
public class C1842e extends AbstractC1840c {

    /* renamed from: H */
    public static final InterfaceC2016c f5384H = C1844g.f5397o;

    /* renamed from: I */
    public static int f5385I;

    /* renamed from: C */
    public Timer f5387C;

    /* renamed from: E */
    public TimerTask f5389E;

    /* renamed from: B */
    public final ConcurrentMap<String, C1843f> f5386B = new ConcurrentHashMap();

    /* renamed from: D */
    public boolean f5388D = false;

    /* renamed from: F */
    public long f5390F = 30000;

    /* renamed from: G */
    public long f5391G = 0;

    /* compiled from: HashSessionManager.java */
    /* renamed from: r5.e$a */
    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            C1842e c1842e = C1842e.this;
            if (c1842e.isStopping() || c1842e.isStopped()) {
                return;
            }
            Thread threadCurrentThread = Thread.currentThread();
            ClassLoader contextClassLoader = threadCurrentThread.getContextClassLoader();
            try {
                ClassLoader classLoader = c1842e.f5368m;
                if (classLoader != null) {
                    threadCurrentThread.setContextClassLoader(classLoader);
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                for (C1843f c1843f : c1842e.f5386B.values()) {
                    long jM2092l = c1843f.m2092l() * 1000;
                    if (jM2092l > 0 && c1843f.m2091k() + jM2092l < jCurrentTimeMillis) {
                        try {
                            c1843f.m2093m();
                        } catch (Exception e7) {
                            C1842e.f5384H.mo2354e("Problem scavenging sessions", e7);
                        }
                    }
                }
            } finally {
                threadCurrentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    /* renamed from: Q */
    public void m2108Q(int i7) {
        if (i7 == 0) {
            i7 = 60;
        }
        long j7 = this.f5390F;
        long j8 = i7 * 1000;
        if (j8 > 60000) {
            j8 = 60000;
        }
        long j9 = j8 >= 1000 ? j8 : 1000L;
        this.f5390F = j9;
        if (this.f5387C != null) {
            if (j9 != j7 || this.f5389E == null) {
                synchronized (this) {
                    TimerTask timerTask = this.f5389E;
                    if (timerTask != null) {
                        timerTask.cancel();
                    }
                    a aVar = new a();
                    this.f5389E = aVar;
                    Timer timer = this.f5387C;
                    long j10 = this.f5390F;
                    timer.schedule(aVar, j10, j10);
                }
            }
        }
    }

    @Override // p147r5.AbstractC1840c, p168u5.AbstractC1980a
    public void doStart() {
        super.doStart();
        this.f5388D = false;
        C1743c.b bVarM1894W = C1743c.m1894W();
        if (bVarM1894W != null) {
            this.f5387C = (Timer) bVarM1894W.m1904a("org.eclipse.jetty.server.session.timer");
        }
        if (this.f5387C == null) {
            this.f5388D = true;
            StringBuilder sbM112a = C0413b.m112a("HashSessionScavenger-");
            int i7 = f5385I;
            f5385I = i7 + 1;
            sbM112a.append(i7);
            this.f5387C = new Timer(sbM112a.toString(), true);
        }
        m2108Q((int) (this.f5390F / 1000));
        long j7 = (this.f5391G > 0 ? (int) (r1 / 1000) : 0) * 1000;
        this.f5391G = j7 >= 0 ? j7 : 0L;
        if (this.f5387C != null) {
            synchronized (this) {
            }
        }
    }

    @Override // p147r5.AbstractC1840c, p168u5.AbstractC1980a
    public void doStop() {
        synchronized (this) {
            TimerTask timerTask = this.f5389E;
            if (timerTask != null) {
                timerTask.cancel();
            }
            this.f5389E = null;
            Timer timer = this.f5387C;
            if (timer != null && this.f5388D) {
                timer.cancel();
            }
            this.f5387C = null;
        }
        super.doStop();
        this.f5386B.clear();
    }
}
