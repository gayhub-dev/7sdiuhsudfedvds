package p089k5;

import android.arch.lifecycle.C0063n;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.Objects;
import p007a6.C0045e;
import p073i5.C1162o;
import p073i5.InterfaceC1151d;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1159l;
import p073i5.InterfaceC1160m;
import p089k5.AbstractC1400i;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: SelectChannelEndPoint.java */
/* renamed from: k5.g */
/* loaded from: classes.dex */
public class C1398g extends C1393b implements InterfaceC1151d, InterfaceC1159l {

    /* renamed from: D */
    public static final InterfaceC2016c f4064D = C2015b.m2349a("org.eclipse.jetty.io.nio");

    /* renamed from: A */
    public volatile long f4065A;

    /* renamed from: B */
    public volatile boolean f4066B;

    /* renamed from: C */
    public boolean f4067C;

    /* renamed from: n */
    public final boolean f4068n;

    /* renamed from: o */
    public final AbstractC1400i.d f4069o;

    /* renamed from: p */
    public final AbstractC1400i f4070p;

    /* renamed from: q */
    public SelectionKey f4071q;

    /* renamed from: r */
    public final Runnable f4072r;

    /* renamed from: s */
    public int f4073s;

    /* renamed from: t */
    public volatile InterfaceC1392a f4074t;

    /* renamed from: u */
    public int f4075u;

    /* renamed from: v */
    public boolean f4076v;

    /* renamed from: w */
    public volatile boolean f4077w;

    /* renamed from: x */
    public boolean f4078x;

    /* renamed from: y */
    public boolean f4079y;

    /* renamed from: z */
    public boolean f4080z;

    /* compiled from: SelectChannelEndPoint.java */
    /* renamed from: k5.g$a */
    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:147:0x01a3  */
        /* JADX WARN: Removed duplicated region for block: B:148:0x01a5  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 468
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p089k5.C1398g.a.run():void");
        }
    }

    public C1398g(SocketChannel socketChannel, AbstractC1400i.d dVar, SelectionKey selectionKey, int i7) {
        super(socketChannel, i7);
        this.f4068n = System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win");
        this.f4072r = new a();
        this.f4077w = true;
        this.f4070p = AbstractC1400i.this;
        this.f4069o = dVar;
        this.f4075u = 0;
        this.f4076v = false;
        this.f4080z = true;
        this.f4071q = selectionKey;
        m1567D(true);
    }

    /* renamed from: A */
    public void m1564A() {
        this.f4065A = System.currentTimeMillis();
    }

    /* renamed from: B */
    public void m1565B(long j7) {
        try {
            synchronized (this) {
                this.f4076v = true;
            }
            this.f4074t.mo889d(j7);
            synchronized (this) {
                this.f4076v = false;
                if (this.f4075u == -1) {
                    mo911d();
                }
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.f4076v = false;
                if (this.f4075u == -1) {
                    mo911d();
                }
                throw th;
            }
        }
    }

    /* renamed from: C */
    public void m1566C() {
        synchronized (this) {
            SelectionKey selectionKey = this.f4071q;
            if (selectionKey != null && selectionKey.isValid()) {
                boolean z6 = this.f4078x;
                if (!z6 && !this.f4079y) {
                    if ((this.f4071q.readyOps() & 4) == 4 && (this.f4071q.interestOps() & 4) == 4) {
                        int iInterestOps = this.f4071q.interestOps() & (-5);
                        this.f4073s = iInterestOps;
                        this.f4071q.interestOps(iInterestOps);
                        this.f4077w = true;
                    }
                    if (this.f4075u >= 1) {
                        this.f4071q.interestOps(0);
                    } else {
                        mo911d();
                        if (this.f4075u >= 1 && !AbstractC1400i.this.f4092h) {
                            this.f4071q.interestOps(0);
                        }
                    }
                    return;
                }
                if (z6 && this.f4071q.isReadable()) {
                    this.f4078x = false;
                }
                if (this.f4079y && this.f4071q.isWritable()) {
                    this.f4079y = false;
                }
                notifyAll();
                this.f4071q.interestOps(0);
                if (this.f4075u < 1) {
                    m1569F();
                }
                return;
            }
            this.f4078x = false;
            this.f4079y = false;
            notifyAll();
        }
    }

    /* renamed from: D */
    public void m1567D(boolean z6) {
        if (!z6) {
            this.f4066B = false;
        } else {
            this.f4065A = System.currentTimeMillis();
            this.f4066B = true;
        }
    }

    /* renamed from: E */
    public boolean m1568E() {
        synchronized (this) {
            if (this.f4075u == 2) {
                this.f4075u = 1;
                return false;
            }
            this.f4075u = 0;
            m1569F();
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0068 A[Catch: all -> 0x007d, PHI: r1
      0x0068: PHI (r1v1 int) = (r1v0 int), (r1v0 int), (r1v0 int), (r1v0 int), (r1v2 int) binds: [B:4:0x000a, B:42:0x0061, B:35:0x004f, B:37:0x0055, B:39:0x005d] A[DONT_GENERATE, DONT_INLINE], TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x000c, B:7:0x0010, B:9:0x0014, B:14:0x0020, B:16:0x0024, B:18:0x0028, B:23:0x0030, B:28:0x003d, B:33:0x004a, B:34:0x004d, B:36:0x0051, B:38:0x0057, B:42:0x0061, B:43:0x0068, B:46:0x006d), top: B:55:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x006c  */
    /* renamed from: F */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m1569F() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.nio.channels.ByteChannel r0 = r6.f4049e     // Catch: java.lang.Throwable -> L7d
            boolean r0 = r0.isOpen()     // Catch: java.lang.Throwable -> L7d
            r1 = -1
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L68
            boolean r0 = r6.f4078x     // Catch: java.lang.Throwable -> L7d
            if (r0 != 0) goto L1f
            int r0 = r6.f4075u     // Catch: java.lang.Throwable -> L7d
            if (r0 >= r3) goto L1d
            k5.a r0 = r6.f4074t     // Catch: java.lang.Throwable -> L7d
            boolean r0 = r0.mo861a()     // Catch: java.lang.Throwable -> L7d
            if (r0 != 0) goto L1d
            goto L1f
        L1d:
            r0 = 0
            goto L20
        L1f:
            r0 = 1
        L20:
            boolean r4 = r6.f4079y     // Catch: java.lang.Throwable -> L7d
            if (r4 != 0) goto L2f
            int r4 = r6.f4075u     // Catch: java.lang.Throwable -> L7d
            if (r4 >= r3) goto L2d
            boolean r4 = r6.f4077w     // Catch: java.lang.Throwable -> L7d
            if (r4 != 0) goto L2d
            goto L2f
        L2d:
            r4 = 0
            goto L30
        L2f:
            r4 = 1
        L30:
            java.net.Socket r5 = r6.f4051g     // Catch: java.lang.Throwable -> L7d
            boolean r5 = r5.isInputShutdown()     // Catch: java.lang.Throwable -> L7d
            if (r5 != 0) goto L3c
            if (r0 == 0) goto L3c
            r0 = 1
            goto L3d
        L3c:
            r0 = 0
        L3d:
            java.net.Socket r5 = r6.f4051g     // Catch: java.lang.Throwable -> L7d
            boolean r5 = r5.isOutputShutdown()     // Catch: java.lang.Throwable -> L7d
            if (r5 != 0) goto L49
            if (r4 == 0) goto L49
            r4 = 4
            goto L4a
        L49:
            r4 = 0
        L4a:
            r0 = r0 | r4
            r6.f4073s = r0     // Catch: java.lang.Throwable -> L7d
            java.nio.channels.SelectionKey r0 = r6.f4071q     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L7d
            if (r0 == 0) goto L68
            boolean r0 = r0.isValid()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L7d
            if (r0 == 0) goto L68
            java.nio.channels.SelectionKey r0 = r6.f4071q     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L7d
            int r0 = r0.interestOps()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L7d
            r1 = r0
            goto L68
        L5f:
            r0 = move-exception
            r4 = 0
            r6.f4071q = r4     // Catch: java.lang.Throwable -> L7d
            v5.c r4 = p089k5.C1398g.f4064D     // Catch: java.lang.Throwable -> L7d
            r4.mo2360k(r0)     // Catch: java.lang.Throwable -> L7d
        L68:
            int r0 = r6.f4073s     // Catch: java.lang.Throwable -> L7d
            if (r0 == r1) goto L6d
            r2 = 1
        L6d:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L7d
            if (r2 == 0) goto L7c
            k5.i$d r0 = r6.f4069o
            java.util.concurrent.ConcurrentLinkedQueue<java.lang.Object> r0 = r0.f4099c
            r0.add(r6)
            k5.i$d r0 = r6.f4069o
            r0.m1577f()
        L7c:
            return
        L7d:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L7d
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p089k5.C1398g.m1569F():void");
    }

    @Override // p073i5.InterfaceC1151d
    /* renamed from: a */
    public void mo908a(C0045e.a aVar) {
        Objects.requireNonNull(this.f4069o);
        aVar.m56b();
    }

    @Override // p073i5.InterfaceC1151d
    /* renamed from: b */
    public void mo909b(C0045e.a aVar, long j7) {
        AbstractC1400i.d dVar = this.f4069o;
        Objects.requireNonNull(dVar);
        if (!(aVar instanceof Runnable)) {
            throw new IllegalArgumentException("!Runnable");
        }
        dVar.f4098b.m53d(aVar, j7);
    }

    @Override // p073i5.InterfaceC1159l
    /* renamed from: c */
    public void mo910c(InterfaceC1160m interfaceC1160m) {
        InterfaceC1392a interfaceC1392a = this.f4074t;
        this.f4074t = (InterfaceC1392a) interfaceC1160m;
        if (interfaceC1392a == null || interfaceC1392a == this.f4074t) {
            return;
        }
        Objects.requireNonNull(this.f4070p);
    }

    @Override // p089k5.C1393b, p073i5.InterfaceC1161n
    public void close() {
        if (this.f4068n) {
            try {
                SelectionKey selectionKey = this.f4071q;
                if (selectionKey != null) {
                    selectionKey.cancel();
                }
            } catch (Throwable th) {
                f4064D.mo2360k(th);
            }
        }
        try {
            try {
                super.close();
            } catch (IOException e7) {
                f4064D.mo2360k(e7);
            }
        } finally {
            m1569F();
        }
    }

    @Override // p073i5.InterfaceC1151d
    /* renamed from: d */
    public void mo911d() {
        synchronized (this) {
            if (this.f4075u <= 0) {
                if (this.f4076v) {
                    this.f4075u = -1;
                } else {
                    this.f4075u = 1;
                    if (!this.f4070p.dispatch(this.f4072r)) {
                        this.f4075u = -1;
                        f4064D.mo2356g("Dispatched Failed! " + this + " to " + this.f4070p, new Object[0]);
                        m1569F();
                    }
                }
            }
        }
    }

    @Override // p073i5.InterfaceC1151d
    /* renamed from: h */
    public void mo915h() {
        synchronized (this) {
            int i7 = this.f4075u;
            if (i7 == -1 || i7 == 0) {
                mo911d();
            } else if (i7 == 1 || i7 == 2) {
                this.f4075u = 2;
            }
        }
    }

    @Override // p089k5.C1393b, p073i5.InterfaceC1161n
    /* renamed from: j */
    public int mo917j(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2, InterfaceC1152e interfaceC1152e3) throws IOException {
        int iMo917j = super.mo917j(interfaceC1152e, interfaceC1152e2, interfaceC1152e3);
        if (iMo917j == 0 && ((interfaceC1152e != null && interfaceC1152e.mo1314A()) || ((interfaceC1152e2 != null && interfaceC1152e2.mo1314A()) || (interfaceC1152e3 != null && interfaceC1152e3.mo1314A())))) {
            synchronized (this) {
                this.f4077w = false;
                if (this.f4075u < 1) {
                    m1569F();
                }
            }
        } else if (iMo917j > 0) {
            this.f4077w = true;
            m1564A();
        }
        return iMo917j;
    }

    @Override // p089k5.C1393b, p073i5.InterfaceC1161n
    /* renamed from: k */
    public void mo918k(int i7) {
        this.f4054j = i7;
    }

    @Override // p089k5.C1393b, p073i5.InterfaceC1161n
    /* renamed from: o */
    public boolean mo922o(long j7) {
        C0045e c0045e;
        synchronized (this) {
            if (mo926s()) {
                throw new C1162o();
            }
            long j8 = this.f4069o.f4098b.f61c;
            long j9 = j8 + j7;
            boolean z6 = this.f4066B;
            m1567D(true);
            try {
                this.f4078x = true;
                while (!mo926s() && this.f4078x) {
                    try {
                        try {
                            m1569F();
                            wait(j7 > 0 ? j9 - j8 : 10000L);
                            c0045e = this.f4069o.f4098b;
                        } finally {
                        }
                    } catch (InterruptedException e7) {
                        f4064D.mo2358i(e7);
                        c0045e = this.f4069o.f4098b;
                    }
                    j8 = c0045e.f61c;
                    if (this.f4078x && j7 > 0 && j8 >= j9) {
                        return false;
                    }
                }
                return true;
            } finally {
                this.f4078x = false;
                m1567D(z6);
            }
        }
    }

    public String toString() {
        String strM88a;
        SelectionKey selectionKey = this.f4071q;
        if (selectionKey == null) {
            strM88a = "-";
        } else if (selectionKey.isValid()) {
            strM88a = selectionKey.isReadable() ? "r" : "";
            if (selectionKey.isWritable()) {
                strM88a = C0063n.m88a(strM88a, "w");
            }
        } else {
            strM88a = "!";
        }
        return String.format("SCEP@%x{l(%s)<->r(%s),s=%d,open=%b,ishut=%b,oshut=%b,rb=%b,wb=%b,w=%b,i=%d%s}-{%s}", Integer.valueOf(hashCode()), this.f4051g.getRemoteSocketAddress(), this.f4051g.getLocalSocketAddress(), Integer.valueOf(this.f4075u), Boolean.valueOf(isOpen()), Boolean.valueOf(mo926s()), Boolean.valueOf(mo925r()), Boolean.valueOf(this.f4078x), Boolean.valueOf(this.f4079y), Boolean.valueOf(this.f4077w), Integer.valueOf(this.f4073s), strM88a, this.f4074t);
    }

    @Override // p073i5.InterfaceC1151d
    /* renamed from: u */
    public boolean mo928u() {
        return false;
    }

    @Override // p089k5.C1393b, p073i5.InterfaceC1161n
    /* renamed from: v */
    public int mo929v(InterfaceC1152e interfaceC1152e) throws Throwable {
        int iMo929v = super.mo929v(interfaceC1152e);
        if (iMo929v > 0) {
            m1564A();
        }
        return iMo929v;
    }

    @Override // p089k5.C1393b, p073i5.InterfaceC1161n
    /* renamed from: w */
    public boolean mo930w(long j7) {
        C0045e c0045e;
        synchronized (this) {
            if (mo925r()) {
                throw new C1162o();
            }
            long j8 = this.f4069o.f4098b.f61c;
            long j9 = j8 + j7;
            boolean z6 = this.f4066B;
            m1567D(true);
            try {
                this.f4079y = true;
                while (this.f4079y && !mo925r()) {
                    try {
                        try {
                            m1569F();
                            wait(j7 > 0 ? j9 - j8 : 10000L);
                            c0045e = this.f4069o.f4098b;
                        } finally {
                        }
                    } catch (InterruptedException e7) {
                        f4064D.mo2358i(e7);
                        c0045e = this.f4069o.f4098b;
                    }
                    j8 = c0045e.f61c;
                    if (this.f4079y && j7 > 0 && j8 >= j9) {
                        return false;
                    }
                }
                return true;
            } finally {
                this.f4079y = false;
                m1567D(z6);
            }
        }
    }

    @Override // p089k5.C1393b, p073i5.InterfaceC1161n
    /* renamed from: x */
    public int mo931x(InterfaceC1152e interfaceC1152e) throws IOException {
        int iMo931x = super.mo931x(interfaceC1152e);
        if (iMo931x == 0 && interfaceC1152e.mo1314A()) {
            synchronized (this) {
                this.f4077w = false;
                if (this.f4075u < 1) {
                    m1569F();
                }
            }
        } else if (iMo931x > 0) {
            this.f4077w = true;
            m1564A();
        }
        return iMo931x;
    }

    @Override // p073i5.InterfaceC1159l
    /* renamed from: y */
    public InterfaceC1160m mo932y() {
        return this.f4074t;
    }

    /* renamed from: z */
    public void m1570z() {
        synchronized (this) {
            if (!this.f4049e.isOpen()) {
                SelectionKey selectionKey = this.f4071q;
                if (selectionKey != null && selectionKey.isValid()) {
                    this.f4071q.cancel();
                }
                if (this.f4080z) {
                    this.f4080z = false;
                    this.f4069o.m1574c(this);
                }
                this.f4071q = null;
            } else if (this.f4073s > 0) {
                SelectionKey selectionKey2 = this.f4071q;
                if (selectionKey2 != null && selectionKey2.isValid()) {
                    this.f4071q.interestOps(this.f4073s);
                } else if (((SelectableChannel) this.f4049e).isRegistered()) {
                    m1569F();
                } else {
                    try {
                        this.f4071q = ((SelectableChannel) this.f4049e).register(this.f4069o.f4100d, this.f4073s, this);
                    } catch (Exception e7) {
                        f4064D.mo2360k(e7);
                        SelectionKey selectionKey3 = this.f4071q;
                        if (selectionKey3 != null && selectionKey3.isValid()) {
                            this.f4071q.cancel();
                        }
                        if (this.f4080z) {
                            this.f4069o.m1574c(this);
                        }
                        this.f4080z = false;
                        this.f4071q = null;
                    }
                }
            } else {
                SelectionKey selectionKey4 = this.f4071q;
                if (selectionKey4 == null || !selectionKey4.isValid()) {
                    this.f4071q = null;
                } else {
                    this.f4071q.interestOps(0);
                }
            }
        }
    }
}
