package p089k5;

import android.support.v7.widget.RecyclerView;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import p007a6.C0045e;
import p073i5.AbstractC1150c;
import p073i5.InterfaceC1151d;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1160m;
import p073i5.InterfaceC1161n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: SslConnection.java */
/* renamed from: k5.j */
/* loaded from: classes.dex */
public class C1401j extends AbstractC1150c implements InterfaceC1392a {

    /* renamed from: s */
    public static final InterfaceC1396e f4112s = new C1395d(0);

    /* renamed from: t */
    public static final ThreadLocal<b> f4113t = new ThreadLocal<>();

    /* renamed from: c */
    public final InterfaceC2016c f4114c;

    /* renamed from: d */
    public final SSLEngine f4115d;

    /* renamed from: e */
    public final SSLSession f4116e;

    /* renamed from: f */
    public InterfaceC1392a f4117f;

    /* renamed from: g */
    public final c f4118g;

    /* renamed from: h */
    public int f4119h;

    /* renamed from: i */
    public b f4120i;

    /* renamed from: j */
    public InterfaceC1396e f4121j;

    /* renamed from: k */
    public InterfaceC1396e f4122k;

    /* renamed from: l */
    public InterfaceC1396e f4123l;

    /* renamed from: m */
    public InterfaceC1151d f4124m;

    /* renamed from: n */
    public boolean f4125n;

    /* renamed from: o */
    public boolean f4126o;

    /* renamed from: p */
    public boolean f4127p;

    /* renamed from: q */
    public boolean f4128q;

    /* renamed from: r */
    public final AtomicBoolean f4129r;

    /* compiled from: SslConnection.java */
    /* renamed from: k5.j$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f4130a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f4131b;

        static {
            int[] iArr = new int[SSLEngineResult.Status.values().length];
            f4131b = iArr;
            try {
                iArr[SSLEngineResult.Status.BUFFER_UNDERFLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4131b[SSLEngineResult.Status.BUFFER_OVERFLOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4131b[SSLEngineResult.Status.OK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4131b[SSLEngineResult.Status.CLOSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[SSLEngineResult.HandshakeStatus.values().length];
            f4130a = iArr2;
            try {
                iArr2[SSLEngineResult.HandshakeStatus.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4130a[SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4130a[SSLEngineResult.HandshakeStatus.NEED_TASK.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4130a[SSLEngineResult.HandshakeStatus.NEED_WRAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4130a[SSLEngineResult.HandshakeStatus.NEED_UNWRAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* compiled from: SslConnection.java */
    /* renamed from: k5.j$b */
    public static class b {

        /* renamed from: a */
        public final InterfaceC1396e f4132a;

        /* renamed from: b */
        public final InterfaceC1396e f4133b;

        /* renamed from: c */
        public final InterfaceC1396e f4134c;

        public b(int i7, int i8) {
            this.f4132a = new C1395d(i7);
            this.f4133b = new C1395d(i7);
            this.f4134c = new C1395d(i8);
        }
    }

    /* compiled from: SslConnection.java */
    /* renamed from: k5.j$c */
    public class c implements InterfaceC1151d {
        public c() {
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: a */
        public void mo908a(C0045e.a aVar) {
            C1401j.this.f4124m.mo908a(aVar);
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: b */
        public void mo909b(C0045e.a aVar, long j7) {
            C1401j.this.f4124m.mo909b(aVar, j7);
        }

        @Override // p073i5.InterfaceC1159l
        /* renamed from: c */
        public void mo910c(InterfaceC1160m interfaceC1160m) {
            C1401j.this.f4117f = (InterfaceC1392a) interfaceC1160m;
        }

        @Override // p073i5.InterfaceC1161n
        public void close() {
            C1401j c1401j = C1401j.this;
            c1401j.f4114c.mo2351a("{} ssl endp.close", c1401j.f4116e);
            C1401j.this.f2538a.close();
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: d */
        public void mo911d() {
            C1401j.this.f4124m.mo911d();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: e */
        public String mo912e() {
            return C1401j.this.f4124m.mo912e();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: f */
        public int mo913f() {
            return C1401j.this.f4124m.mo913f();
        }

        @Override // p073i5.InterfaceC1161n
        public void flush() {
            C1401j.m1578f(C1401j.this, null, null);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: g */
        public String mo914g() {
            return C1401j.this.f4124m.mo914g();
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: h */
        public void mo915h() {
            C1401j.this.f4124m.mo915h();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: i */
        public int mo916i() {
            return C1401j.this.f4124m.mo916i();
        }

        @Override // p073i5.InterfaceC1161n
        public boolean isOpen() {
            return C1401j.this.f2538a.isOpen();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: j */
        public int mo917j(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2, InterfaceC1152e interfaceC1152e3) {
            if (interfaceC1152e != null && interfaceC1152e.mo1314A()) {
                return mo931x(interfaceC1152e);
            }
            if (interfaceC1152e2 != null && interfaceC1152e2.mo1314A()) {
                return mo931x(interfaceC1152e2);
            }
            if (interfaceC1152e3 == null || !interfaceC1152e3.mo1314A()) {
                return 0;
            }
            return mo931x(interfaceC1152e3);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: k */
        public void mo918k(int i7) {
            C1401j.this.f4124m.mo918k(i7);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: l */
        public Object mo919l() {
            return C1401j.this.f2538a;
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: m */
        public void mo920m() {
            C1401j c1401j = C1401j.this;
            c1401j.f4114c.mo2351a("{} ssl endp.ishut!", c1401j.f4116e);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: n */
        public String mo921n() {
            return C1401j.this.f4124m.mo921n();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: o */
        public boolean mo922o(long j7) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j8 = j7 > 0 ? j7 + jCurrentTimeMillis : RecyclerView.FOREVER_NS;
            while (jCurrentTimeMillis < j8 && !C1401j.this.m1581i(null, null)) {
                C1401j.this.f2538a.mo922o(j8 - jCurrentTimeMillis);
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            return jCurrentTimeMillis < j8;
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: p */
        public boolean mo923p() {
            return false;
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: q */
        public int mo924q() {
            return C1401j.this.f4124m.mo924q();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: r */
        public boolean mo925r() {
            boolean z6;
            synchronized (C1401j.this) {
                z6 = C1401j.this.f4128q || !isOpen() || C1401j.this.f4115d.isOutboundDone();
            }
            return z6;
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: s */
        public boolean mo926s() {
            boolean z6;
            InterfaceC1396e interfaceC1396e;
            InterfaceC1396e interfaceC1396e2;
            synchronized (C1401j.this) {
                z6 = C1401j.this.f2538a.mo926s() && ((interfaceC1396e = C1401j.this.f4122k) == null || !interfaceC1396e.mo1314A()) && ((interfaceC1396e2 = C1401j.this.f4121j) == null || !interfaceC1396e2.mo1314A());
            }
            return z6;
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: t */
        public void mo927t() {
            synchronized (C1401j.this) {
                try {
                    C1401j c1401j = C1401j.this;
                    c1401j.f4114c.mo2351a("{} ssl endp.oshut {}", c1401j.f4116e, this);
                    C1401j c1401j2 = C1401j.this;
                    c1401j2.f4128q = true;
                    c1401j2.f4115d.closeOutbound();
                } catch (Exception e7) {
                    throw new IOException(e7);
                }
            }
            flush();
        }

        public String toString() {
            C1401j c1401j = C1401j.this;
            InterfaceC1396e interfaceC1396e = c1401j.f4121j;
            InterfaceC1396e interfaceC1396e2 = c1401j.f4123l;
            InterfaceC1396e interfaceC1396e3 = c1401j.f4122k;
            return String.format("SSL %s i/o/u=%d/%d/%d ishut=%b oshut=%b {%s}", C1401j.this.f4115d.getHandshakeStatus(), Integer.valueOf(interfaceC1396e == null ? -1 : interfaceC1396e.length()), Integer.valueOf(interfaceC1396e2 == null ? -1 : interfaceC1396e2.length()), Integer.valueOf(interfaceC1396e3 != null ? interfaceC1396e3.length() : -1), Boolean.valueOf(C1401j.this.f4127p), Boolean.valueOf(C1401j.this.f4128q), C1401j.this.f4117f);
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: u */
        public boolean mo928u() {
            return C1401j.this.f4129r.getAndSet(false);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: v */
        public int mo929v(InterfaceC1152e interfaceC1152e) {
            int length = interfaceC1152e.length();
            C1401j.m1578f(C1401j.this, interfaceC1152e, null);
            int length2 = interfaceC1152e.length() - length;
            if (length2 == 0 && mo926s()) {
                return -1;
            }
            return length2;
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: w */
        public boolean mo930w(long j7) {
            return C1401j.this.f2538a.mo930w(j7);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: x */
        public int mo931x(InterfaceC1152e interfaceC1152e) {
            int length = interfaceC1152e.length();
            C1401j.this.m1581i(null, interfaceC1152e);
            return length - interfaceC1152e.length();
        }

        @Override // p073i5.InterfaceC1159l
        /* renamed from: y */
        public InterfaceC1160m mo932y() {
            return C1401j.this.f4117f;
        }
    }

    public C1401j(SSLEngine sSLEngine, InterfaceC1161n interfaceC1161n) {
        super(interfaceC1161n, System.currentTimeMillis());
        this.f4114c = C2015b.m2349a("org.eclipse.jetty.io.nio.ssl");
        this.f4125n = true;
        this.f4129r = new AtomicBoolean();
        this.f4115d = sSLEngine;
        this.f4116e = sSLEngine.getSession();
        this.f4124m = (InterfaceC1151d) interfaceC1161n;
        this.f4118g = new c();
    }

    /* renamed from: f */
    public static /* synthetic */ boolean m1578f(C1401j c1401j, InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
        return c1401j.m1581i(interfaceC1152e, null);
    }

    @Override // p073i5.InterfaceC1160m
    /* renamed from: a */
    public boolean mo861a() {
        return false;
    }

    @Override // p073i5.InterfaceC1160m
    /* renamed from: b */
    public boolean mo862b() {
        return false;
    }

    @Override // p089k5.InterfaceC1392a
    /* renamed from: c */
    public void mo886c() {
    }

    @Override // p073i5.AbstractC1150c, p073i5.InterfaceC1160m
    /* renamed from: d */
    public void mo889d(long j7) {
        try {
            this.f4114c.mo2351a("onIdleExpired {}ms on {}", Long.valueOf(j7), this);
            if (this.f2538a.mo925r()) {
                this.f4118g.close();
            } else {
                this.f4118g.mo927t();
            }
        } catch (IOException e7) {
            this.f4114c.mo2358i(e7);
            super.mo889d(j7);
        }
    }

    @Override // p073i5.InterfaceC1160m
    /* renamed from: e */
    public InterfaceC1160m mo887e() {
        try {
            m1579g();
            boolean zM1581i = true;
            while (zM1581i) {
                zM1581i = this.f4115d.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING ? m1581i(null, null) : false;
                InterfaceC1392a interfaceC1392a = (InterfaceC1392a) this.f4117f.mo887e();
                if (interfaceC1392a != this.f4117f && interfaceC1392a != null) {
                    this.f4117f = interfaceC1392a;
                    zM1581i = true;
                }
                this.f4114c.mo2351a("{} handle {} progress={}", this.f4116e, this, Boolean.valueOf(zM1581i));
            }
            return this;
        } finally {
            m1582j();
            if (!this.f4127p && this.f4118g.mo926s() && this.f4118g.isOpen()) {
                this.f4127p = true;
                try {
                    this.f4117f.mo886c();
                } catch (Throwable th) {
                    this.f4114c.mo2354e("onInputShutdown failed", th);
                    try {
                        this.f4118g.close();
                    } catch (IOException e7) {
                        this.f4114c.mo2360k(e7);
                    }
                }
            }
        }
    }

    /* renamed from: g */
    public final void m1579g() {
        synchronized (this) {
            int i7 = this.f4119h;
            this.f4119h = i7 + 1;
            if (i7 == 0 && this.f4120i == null) {
                ThreadLocal<b> threadLocal = f4113t;
                b bVar = threadLocal.get();
                this.f4120i = bVar;
                if (bVar == null) {
                    this.f4120i = new b(this.f4116e.getPacketBufferSize() * 2, this.f4116e.getApplicationBufferSize() * 2);
                }
                b bVar2 = this.f4120i;
                this.f4121j = bVar2.f4132a;
                this.f4123l = bVar2.f4133b;
                this.f4122k = bVar2.f4134c;
                threadLocal.set(null);
            }
        }
    }

    /* renamed from: h */
    public final ByteBuffer m1580h(InterfaceC1152e interfaceC1152e) {
        return interfaceC1152e.buffer() instanceof InterfaceC1396e ? ((InterfaceC1396e) interfaceC1152e.buffer()).mo1563o() : ByteBuffer.wrap(interfaceC1152e.mo1349P());
    }

    /* renamed from: i */
    public final synchronized boolean m1581i(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
        int i7;
        boolean z6;
        int iMo931x;
        boolean z7;
        InterfaceC1152e interfaceC1152e3 = interfaceC1152e;
        InterfaceC1152e interfaceC1152e4 = interfaceC1152e2;
        synchronized (this) {
            boolean z8 = false;
            try {
                m1579g();
                if (interfaceC1152e3 == null) {
                    this.f4122k.mo1340w();
                    interfaceC1152e3 = this.f4122k;
                } else {
                    if (interfaceC1152e.mo1350a() < this.f4116e.getApplicationBufferSize()) {
                        boolean zM1581i = m1581i(null, interfaceC1152e4);
                        InterfaceC1396e interfaceC1396e = this.f4122k;
                        if (interfaceC1396e == null || !interfaceC1396e.mo1314A()) {
                            m1582j();
                            return zM1581i;
                        }
                        InterfaceC1396e interfaceC1396e2 = this.f4122k;
                        interfaceC1396e2.mo1330h(interfaceC1152e3.mo1337t(interfaceC1396e2));
                        m1582j();
                        return true;
                    }
                    InterfaceC1396e interfaceC1396e3 = this.f4122k;
                    if (interfaceC1396e3 != null && interfaceC1396e3.mo1314A()) {
                        InterfaceC1396e interfaceC1396e4 = this.f4122k;
                        interfaceC1396e4.mo1330h(interfaceC1152e3.mo1337t(interfaceC1396e4));
                        m1582j();
                        return true;
                    }
                }
                InterfaceC1152e interfaceC1152e5 = interfaceC1152e3;
                if (interfaceC1152e4 == null) {
                    interfaceC1152e4 = f4112s;
                }
                boolean z9 = false;
                for (boolean z10 = true; z10; z10 = z7) {
                    try {
                        if (this.f4121j.mo1317D() > 0) {
                            int iMo929v = this.f2538a.mo929v(this.f4121j);
                            i7 = iMo929v;
                            z6 = iMo929v > 0;
                        } else {
                            z6 = false;
                            i7 = 0;
                        }
                    } catch (IOException e7) {
                        e = e7;
                        i7 = 0;
                    } catch (Throwable th) {
                        th = th;
                        i7 = 0;
                        this.f4114c.mo2351a("{} {} {} filled={}/{} flushed={}/{}", this.f4116e, this, this.f4115d.getHandshakeStatus(), Integer.valueOf(i7), Integer.valueOf(this.f4121j.length()), 0, Integer.valueOf(this.f4123l.length()));
                        throw th;
                    }
                    try {
                        try {
                            if (this.f4123l.mo1314A()) {
                                iMo931x = this.f2538a.mo931x(this.f4123l);
                                if (iMo931x > 0) {
                                    z6 = true;
                                }
                            } else {
                                iMo931x = 0;
                            }
                            try {
                                this.f4114c.mo2351a("{} {} {} filled={}/{} flushed={}/{}", this.f4116e, this, this.f4115d.getHandshakeStatus(), Integer.valueOf(i7), Integer.valueOf(this.f4121j.length()), Integer.valueOf(iMo931x), Integer.valueOf(this.f4123l.length()));
                                int i8 = a.f4130a[this.f4115d.getHandshakeStatus().ordinal()];
                                if (i8 == 1) {
                                    throw new IllegalStateException();
                                }
                                if (i8 != 2) {
                                    if (i8 == 3) {
                                        while (true) {
                                            Runnable delegatedTask = this.f4115d.getDelegatedTask();
                                            if (delegatedTask == null) {
                                                break;
                                            }
                                            delegatedTask.run();
                                            z6 = true;
                                        }
                                    } else if (i8 != 4) {
                                        if (i8 == 5) {
                                            if (this.f4126o && !this.f4125n) {
                                                this.f2538a.close();
                                            } else if (!this.f4121j.mo1314A() && i7 == -1) {
                                                this.f2538a.mo920m();
                                            } else if (m1583k(interfaceC1152e5)) {
                                                z7 = true;
                                            }
                                        }
                                    } else if (this.f4126o && !this.f4125n) {
                                        this.f2538a.close();
                                    } else if (m1584l(interfaceC1152e4)) {
                                        z7 = true;
                                    }
                                    z7 = z6;
                                } else {
                                    if (interfaceC1152e5.mo1317D() > 0 && this.f4121j.mo1314A() && m1583k(interfaceC1152e5)) {
                                        z6 = true;
                                    }
                                    if (interfaceC1152e4.mo1314A() && this.f4123l.mo1317D() > 0 && m1584l(interfaceC1152e4)) {
                                        z7 = true;
                                    }
                                    z7 = z6;
                                }
                                if (this.f2538a.isOpen() && this.f2538a.mo926s() && !this.f4121j.mo1314A()) {
                                    try {
                                        this.f4115d.closeInbound();
                                    } catch (SSLException e8) {
                                        this.f4114c.mo2359j(e8);
                                    }
                                }
                                if (this.f2538a.isOpen() && this.f4115d.isOutboundDone() && !this.f4123l.mo1314A()) {
                                    this.f2538a.mo927t();
                                }
                                z9 |= z7;
                            } catch (Throwable th2) {
                                th = th2;
                                z8 = z9;
                                m1582j();
                                if (z8) {
                                    this.f4129r.set(true);
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            this.f4114c.mo2351a("{} {} {} filled={}/{} flushed={}/{}", this.f4116e, this, this.f4115d.getHandshakeStatus(), Integer.valueOf(i7), Integer.valueOf(this.f4121j.length()), 0, Integer.valueOf(this.f4123l.length()));
                            throw th;
                        }
                    } catch (IOException e9) {
                        e = e9;
                        this.f2538a.close();
                        throw e;
                    }
                }
                InterfaceC1396e interfaceC1396e5 = this.f4122k;
                if (interfaceC1152e5 == interfaceC1396e5 && interfaceC1396e5.mo1314A() && !this.f4117f.mo861a()) {
                    this.f4124m.mo911d();
                }
                m1582j();
                if (z9) {
                    this.f4129r.set(true);
                }
                return z9;
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    /* renamed from: j */
    public final void m1582j() {
        synchronized (this) {
            int i7 = this.f4119h - 1;
            this.f4119h = i7;
            if (i7 == 0 && this.f4120i != null && this.f4121j.length() == 0 && this.f4123l.length() == 0 && this.f4122k.length() == 0) {
                this.f4121j = null;
                this.f4123l = null;
                this.f4122k = null;
                f4113t.set(this.f4120i);
                this.f4120i = null;
            }
        }
    }

    /* renamed from: k */
    public final synchronized boolean m1583k(InterfaceC1152e interfaceC1152e) {
        SSLEngineResult sSLEngineResultUnwrap;
        int iPosition;
        int iPosition2;
        int i7 = 0;
        int i8 = 0;
        if (!this.f4121j.mo1314A()) {
            return false;
        }
        ByteBuffer byteBufferM1580h = m1580h(interfaceC1152e);
        synchronized (byteBufferM1580h) {
            ByteBuffer byteBufferMo1563o = this.f4121j.mo1563o();
            synchronized (byteBufferMo1563o) {
                try {
                    try {
                        try {
                            try {
                                byteBufferM1580h.position(interfaceC1152e.mo1322M());
                                byteBufferM1580h.limit(interfaceC1152e.mo1350a());
                                int iPosition3 = byteBufferM1580h.position();
                                byteBufferMo1563o.position(this.f4121j.mo1316C());
                                byteBufferMo1563o.limit(this.f4121j.mo1322M());
                                int iPosition4 = byteBufferMo1563o.position();
                                sSLEngineResultUnwrap = this.f4115d.unwrap(byteBufferMo1563o, byteBufferM1580h);
                                if (this.f4114c.mo2353d()) {
                                    this.f4114c.mo2351a("{} unwrap {} {} consumed={} produced={}", this.f4116e, sSLEngineResultUnwrap.getStatus(), sSLEngineResultUnwrap.getHandshakeStatus(), Integer.valueOf(sSLEngineResultUnwrap.bytesConsumed()), Integer.valueOf(sSLEngineResultUnwrap.bytesProduced()));
                                }
                                iPosition = byteBufferMo1563o.position() - iPosition4;
                                this.f4121j.mo1330h(iPosition);
                                this.f4121j.mo1340w();
                                iPosition2 = byteBufferM1580h.position() - iPosition3;
                                interfaceC1152e.mo1324Q(interfaceC1152e.mo1322M() + iPosition2);
                            } catch (IOException e7) {
                                throw e7;
                            }
                        } catch (SSLException e8) {
                            this.f4114c.mo2355f(String.valueOf(this.f2538a), e8);
                            this.f2538a.close();
                            throw e8;
                        }
                    } catch (Exception e9) {
                        throw new IOException(e9);
                    }
                } finally {
                    byteBufferMo1563o.position(0);
                    byteBufferMo1563o.limit(byteBufferMo1563o.capacity());
                    byteBufferM1580h.position(0);
                    byteBufferM1580h.limit(byteBufferM1580h.capacity());
                }
            }
        }
        int i9 = a.f4131b[sSLEngineResultUnwrap.getStatus().ordinal()];
        if (i9 != 1) {
            if (i9 != 2) {
                if (i9 != 3) {
                    if (i9 != 4) {
                        this.f4114c.mo2351a("{} wrap default {}", this.f4116e, sSLEngineResultUnwrap);
                        throw new IOException(sSLEngineResultUnwrap.toString());
                    }
                    this.f4114c.mo2351a("unwrap CLOSE {} {}", this, sSLEngineResultUnwrap);
                    if (sSLEngineResultUnwrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                        this.f2538a.close();
                    }
                } else if (sSLEngineResultUnwrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                    this.f4126o = true;
                }
            } else if (this.f4114c.mo2353d()) {
                this.f4114c.mo2351a("{} unwrap {} {}->{}", this.f4116e, sSLEngineResultUnwrap.getStatus(), this.f4121j.mo1342z(), interfaceC1152e.mo1342z());
            }
        } else if (this.f2538a.mo926s()) {
            this.f4121j.clear();
        }
        return iPosition > 0 || iPosition2 > 0;
    }

    /* renamed from: l */
    public final synchronized boolean m1584l(InterfaceC1152e interfaceC1152e) {
        SSLEngineResult sSLEngineResultWrap;
        int iPosition;
        int iPosition2;
        ByteBuffer byteBufferM1580h = m1580h(interfaceC1152e);
        synchronized (byteBufferM1580h) {
            this.f4123l.mo1340w();
            ByteBuffer byteBufferMo1563o = this.f4123l.mo1563o();
            synchronized (byteBufferMo1563o) {
                int i7 = 0;
                int i8 = 0;
                try {
                    try {
                        try {
                            try {
                                byteBufferM1580h.position(interfaceC1152e.mo1316C());
                                byteBufferM1580h.limit(interfaceC1152e.mo1322M());
                                int iPosition3 = byteBufferM1580h.position();
                                byteBufferMo1563o.position(this.f4123l.mo1322M());
                                byteBufferMo1563o.limit(byteBufferMo1563o.capacity());
                                int iPosition4 = byteBufferMo1563o.position();
                                sSLEngineResultWrap = this.f4115d.wrap(byteBufferM1580h, byteBufferMo1563o);
                                if (this.f4114c.mo2353d()) {
                                    this.f4114c.mo2351a("{} wrap {} {} consumed={} produced={}", this.f4116e, sSLEngineResultWrap.getStatus(), sSLEngineResultWrap.getHandshakeStatus(), Integer.valueOf(sSLEngineResultWrap.bytesConsumed()), Integer.valueOf(sSLEngineResultWrap.bytesProduced()));
                                }
                                iPosition = byteBufferM1580h.position() - iPosition3;
                                interfaceC1152e.mo1330h(iPosition);
                                iPosition2 = byteBufferMo1563o.position() - iPosition4;
                                InterfaceC1396e interfaceC1396e = this.f4123l;
                                interfaceC1396e.mo1324Q(interfaceC1396e.mo1322M() + iPosition2);
                            } catch (IOException e7) {
                                throw e7;
                            }
                        } catch (SSLException e8) {
                            this.f4114c.mo2355f(String.valueOf(this.f2538a), e8);
                            this.f2538a.close();
                            throw e8;
                        }
                    } catch (Exception e9) {
                        throw new IOException(e9);
                    }
                } finally {
                    byteBufferMo1563o.position(0);
                    byteBufferMo1563o.limit(byteBufferMo1563o.capacity());
                    byteBufferM1580h.position(0);
                    byteBufferM1580h.limit(byteBufferM1580h.capacity());
                }
            }
        }
        int i9 = a.f4131b[sSLEngineResultWrap.getStatus().ordinal()];
        if (i9 == 1) {
            throw new IllegalStateException();
        }
        if (i9 != 2) {
            if (i9 != 3) {
                if (i9 != 4) {
                    this.f4114c.mo2351a("{} wrap default {}", this.f4116e, sSLEngineResultWrap);
                    throw new IOException(sSLEngineResultWrap.toString());
                }
                this.f4114c.mo2351a("wrap CLOSE {} {}", this, sSLEngineResultWrap);
                if (sSLEngineResultWrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                    this.f2538a.close();
                }
            } else if (sSLEngineResultWrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                this.f4126o = true;
            }
        }
        return iPosition > 0 || iPosition2 > 0;
    }

    @Override // p073i5.InterfaceC1160m
    public void onClose() {
        InterfaceC1392a interfaceC1392a = C1401j.this.f4117f;
        if (interfaceC1392a == null || interfaceC1392a == this) {
            return;
        }
        interfaceC1392a.onClose();
    }

    @Override // p073i5.AbstractC1150c
    public String toString() {
        return String.format("%s %s", super.toString(), this.f4118g);
    }
}
