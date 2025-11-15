package p113n5;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import p007a6.InterfaceC0044d;
import p065h5.C1097e;
import p065h5.InterfaceC1096d;
import p073i5.InterfaceC1156i;
import p073i5.InterfaceC1161n;
import p121o5.C1591a;
import p168u5.C1981b;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;
import p203z5.C2157a;

/* compiled from: AbstractConnector.java */
/* renamed from: n5.a */
/* loaded from: classes.dex */
public abstract class AbstractC1540a extends C1981b implements InterfaceC1096d, InterfaceC1545f {

    /* renamed from: A */
    public static final InterfaceC2016c f4518A;

    /* renamed from: h */
    public C1555p f4519h;

    /* renamed from: i */
    public InterfaceC0044d f4520i;

    /* renamed from: j */
    public String f4521j;

    /* renamed from: w */
    public transient Thread[] f4534w;

    /* renamed from: z */
    public final C1097e f4537z;

    /* renamed from: k */
    public int f4522k = 0;

    /* renamed from: l */
    public String f4523l = "https";

    /* renamed from: m */
    public String f4524m = "https";

    /* renamed from: n */
    public int f4525n = 1;

    /* renamed from: o */
    public String f4526o = "X-Forwarded-Host";

    /* renamed from: p */
    public String f4527p = "X-Forwarded-Server";

    /* renamed from: q */
    public String f4528q = "X-Forwarded-For";

    /* renamed from: r */
    public String f4529r = "X-Forwarded-Proto";

    /* renamed from: s */
    public boolean f4530s = true;

    /* renamed from: t */
    public int f4531t = 200000;

    /* renamed from: u */
    public int f4532u = -1;

    /* renamed from: v */
    public int f4533v = -1;

    /* renamed from: x */
    public final AtomicLong f4535x = new AtomicLong(-1);

    /* renamed from: y */
    public final C2157a f4536y = new C2157a();

    /* compiled from: AbstractConnector.java */
    /* renamed from: n5.a$a */
    public class a implements Runnable {

        /* renamed from: e */
        public final int f4538e;

        public a(int i7) {
            this.f4538e = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z6;
            Thread threadCurrentThread = Thread.currentThread();
            synchronized (AbstractC1540a.this) {
                Thread[] threadArr = AbstractC1540a.this.f4534w;
                if (threadArr == null) {
                    return;
                }
                int i7 = this.f4538e;
                threadArr[i7] = threadCurrentThread;
                String name = threadArr[i7].getName();
                threadCurrentThread.setName(name + " Acceptor" + this.f4538e + " " + AbstractC1540a.this);
                int priority = threadCurrentThread.getPriority();
                try {
                    Objects.requireNonNull(AbstractC1540a.this);
                    threadCurrentThread.setPriority(priority + 0);
                    loop0: while (true) {
                        Throwable th = null;
                        while (AbstractC1540a.this.isRunning()) {
                            AbstractC1540a abstractC1540a = AbstractC1540a.this;
                            if (((C1591a) abstractC1540a).f4840B == null) {
                                break loop0;
                            }
                            try {
                                abstractC1540a.mo1723M(this.f4538e);
                                break;
                            } catch (Throwable th2) {
                                AbstractC1540a abstractC1540a2 = AbstractC1540a.this;
                                if (!abstractC1540a2.isRunning() || ((C1591a) abstractC1540a2).f4840B == null) {
                                    AbstractC1540a.f4518A.mo2360k(th2);
                                } else if (th == null) {
                                    AbstractC1540a.f4518A.mo2358i(th2);
                                } else {
                                    AbstractC1540a.f4518A.mo2359j(th2);
                                }
                                try {
                                    Thread.sleep(1000L);
                                    z6 = true;
                                } catch (Throwable unused) {
                                    z6 = false;
                                }
                                if (!z6) {
                                    break loop0;
                                } else {
                                    th = th2;
                                }
                            }
                        }
                    }
                    threadCurrentThread.setPriority(priority);
                    threadCurrentThread.setName(name);
                    synchronized (AbstractC1540a.this) {
                        Thread[] threadArr2 = AbstractC1540a.this.f4534w;
                        if (threadArr2 != null) {
                            threadArr2[this.f4538e] = null;
                        }
                    }
                } catch (Throwable th3) {
                    threadCurrentThread.setPriority(priority);
                    threadCurrentThread.setName(name);
                    synchronized (AbstractC1540a.this) {
                        Thread[] threadArr3 = AbstractC1540a.this.f4534w;
                        if (threadArr3 != null) {
                            threadArr3[this.f4538e] = null;
                        }
                        throw th3;
                    }
                }
            }
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f4518A = C2015b.m2349a(AbstractC1540a.class.getName());
    }

    public AbstractC1540a() {
        new AtomicLong();
        new AtomicLong();
        new AtomicLong();
        new AtomicLong();
        new AtomicLong();
        new AtomicLong();
        new AtomicLong();
        new AtomicLong();
        C1097e c1097e = new C1097e();
        this.f4537z = c1097e;
        mo1798G(c1097e);
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: A */
    public int mo1719A() {
        return 0;
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: C */
    public boolean mo1720C(C1553n c1553n) {
        return false;
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: D */
    public void mo1721D(InterfaceC1161n interfaceC1161n) {
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: E */
    public boolean mo1722E() {
        InterfaceC0044d interfaceC0044d = this.f4520i;
        return interfaceC0044d != null ? interfaceC0044d.isLowOnThreads() : this.f4519h.f4672m.isLowOnThreads();
    }

    @Override // p065h5.InterfaceC1096d
    /* renamed from: F */
    public InterfaceC1156i mo891F() {
        return this.f4537z.f2271n;
    }

    /* renamed from: M */
    public abstract void mo1723M(int i7);

    @Override // p113n5.InterfaceC1545f
    /* renamed from: c */
    public C1555p mo1724c() {
        return this.f4519h;
    }

    @Override // p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() {
        if (this.f4519h == null) {
            throw new IllegalStateException("No server");
        }
        ((C1591a) this).m1858O();
        if (this.f4520i == null) {
            InterfaceC0044d interfaceC0044d = this.f4519h.f4672m;
            this.f4520i = interfaceC0044d;
            m2306H(interfaceC0044d, false);
        }
        super.doStart();
        synchronized (this) {
            this.f4534w = new Thread[this.f4525n];
            for (int i7 = 0; i7 < this.f4534w.length; i7++) {
                if (!this.f4520i.dispatch(new a(i7))) {
                    throw new IllegalStateException("!accepting");
                }
            }
            if (this.f4520i.isLowOnThreads()) {
                f4518A.mo2356g("insufficient threads configured for {}", this);
            }
        }
        f4518A.mo2357h("Started {}", this);
    }

    @Override // p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() throws IOException {
        Thread[] threadArr;
        try {
            C1591a c1591a = (C1591a) this;
            ServerSocket serverSocket = c1591a.f4840B;
            if (serverSocket != null) {
                serverSocket.close();
            }
            c1591a.f4840B = null;
            c1591a.f4842D = -2;
        } catch (IOException e7) {
            f4518A.mo2358i(e7);
        }
        super.doStop();
        synchronized (this) {
            threadArr = this.f4534w;
            this.f4534w = null;
        }
        if (threadArr != null) {
            for (Thread thread : threadArr) {
                if (thread != null) {
                    thread.interrupt();
                }
            }
        }
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: h */
    public void mo1725h(C1555p c1555p) {
        this.f4519h = c1555p;
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: i */
    public int mo1726i() {
        return this.f4531t;
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: m */
    public boolean mo1727m(C1553n c1553n) {
        return false;
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: o */
    public String mo1728o() {
        return this.f4524m;
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: p */
    public int mo1729p() {
        return 0;
    }

    @Override // p113n5.InterfaceC1545f
    @Deprecated
    /* renamed from: q */
    public final int mo1730q() {
        return this.f4532u;
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: r */
    public boolean mo1731r() {
        return false;
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: s */
    public String mo1732s() {
        return this.f4521j;
    }

    @Override // p065h5.InterfaceC1096d
    /* renamed from: t */
    public InterfaceC1156i mo895t() {
        return this.f4537z.f2272o;
    }

    public String toString() {
        Object[] objArr = new Object[3];
        objArr[0] = getClass().getSimpleName();
        String str = this.f4521j;
        if (str == null) {
            str = "0.0.0.0";
        }
        objArr[1] = str;
        C1591a c1591a = (C1591a) this;
        objArr[2] = Integer.valueOf(c1591a.f4842D <= 0 ? this.f4522k : c1591a.f4842D);
        return String.format("%s@%s:%d", objArr);
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: y */
    public void mo1733y(InterfaceC1161n interfaceC1161n, C1553n c1553n) {
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: z */
    public String mo1734z() {
        return this.f4523l;
    }
}
