package p041e5;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Properties;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1156i;
import p073i5.InterfaceC1160m;
import p073i5.InterfaceC1161n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: BlockingHttpConnection.java */
/* renamed from: e5.d */
/* loaded from: classes.dex */
public class C0959d extends AbstractC0956a {

    /* renamed from: q */
    public static final InterfaceC2016c f1737q;

    /* renamed from: n */
    public boolean f1738n;

    /* renamed from: o */
    public InterfaceC1152e f1739o;

    /* renamed from: p */
    public boolean f1740p;

    static {
        Properties properties = C2015b.f5863a;
        f1737q = C2015b.m2349a(C0959d.class.getName());
    }

    public C0959d(InterfaceC1156i interfaceC1156i, InterfaceC1156i interfaceC1156i2, InterfaceC1161n interfaceC1161n) {
        super(interfaceC1156i, interfaceC1156i2, interfaceC1161n);
        this.f1740p = false;
    }

    @Override // p073i5.AbstractC1150c, p073i5.InterfaceC1160m
    /* renamed from: d */
    public void mo889d(long j7) {
        try {
            f1737q.mo2351a("onIdleExpired {}ms {} {}", Long.valueOf(j7), this, this.f2538a);
            this.f1740p = true;
            this.f2538a.close();
        } catch (IOException e7) {
            f1737q.mo2360k(e7);
            try {
                this.f2538a.close();
            } catch (IOException e8) {
                f1737q.mo2360k(e8);
            }
        }
        synchronized (this) {
            notifyAll();
        }
    }

    @Override // p073i5.InterfaceC1160m
    /* renamed from: e */
    public InterfaceC1160m mo887e() {
        C0965j c0965j;
        InterfaceC2016c interfaceC2016c;
        boolean z6 = false;
        boolean z7 = false;
        InterfaceC1160m interfaceC1160mOnSwitchProtocol = this;
        while (this.f2538a.isOpen() && interfaceC1160mOnSwitchProtocol == this) {
            try {
                f1737q.mo2351a("open={} more={}", Boolean.valueOf(this.f2538a.isOpen()), Boolean.valueOf(this.f1718e.m1245e()));
            } finally {
            }
            synchronized (this) {
                c0965j = this.f1722i;
                while (true) {
                    if (c0965j != null) {
                        break;
                    }
                    try {
                        wait();
                        c0965j = this.f1722i;
                        if (this.f1740p) {
                            throw new InterruptedException();
                        }
                    } catch (InterruptedException unused) {
                        throw new InterruptedIOException();
                    }
                    this.f1718e.m1250j();
                    this.f1717d.m1194n();
                }
            }
            InterfaceC2016c interfaceC2016c2 = f1737q;
            interfaceC2016c2.mo2351a("exchange {}", c0965j);
            try {
                if (!this.f1717d.m1187g() && c0965j.getStatus() == 2) {
                    interfaceC2016c2.mo2351a("commit", new Object[0]);
                    m864g();
                }
                while (this.f1717d.m1187g() && !this.f1717d.m1188h()) {
                    if (this.f1717d.mo1184d() > 0) {
                        f1737q.mo2351a("flushed", new Object[0]);
                    }
                    if (this.f1717d.f2244c == 2) {
                        if (this.f1739o == null) {
                            this.f1739o = c0965j.getRequestContentChunk(null);
                        }
                        if (this.f1739o == null) {
                            f1737q.mo2351a("complete", new Object[0]);
                            this.f1717d.complete();
                        } else if (this.f1717d.m1237v()) {
                            f1737q.mo2351a("addChunk", new Object[0]);
                            InterfaceC1152e interfaceC1152e = this.f1739o;
                            InterfaceC1152e requestContentChunk = c0965j.getRequestContentChunk(null);
                            this.f1739o = requestContentChunk;
                            this.f1717d.m1235t(interfaceC1152e, requestContentChunk == null);
                            if (this.f1739o == null) {
                                c0965j.setStatus(4);
                            }
                        }
                    }
                }
                if (this.f1717d.m1188h() && !this.f1738n) {
                    f1737q.mo2351a("requestComplete", new Object[0]);
                    this.f1738n = true;
                    c0965j.getEventListener().mo884j();
                }
                if (!this.f1718e.m1243c() && this.f1718e.m1247g()) {
                    f1737q.mo2351a("parsed", new Object[0]);
                }
                this.f2538a.flush();
                InterfaceC2016c interfaceC2016c3 = f1737q;
                interfaceC2016c3.mo2351a("{} {}", this.f1717d, this.f1718e);
                interfaceC2016c3.mo2351a("{}", this.f2538a);
                if (z7 || (this.f1717d.m1188h() && this.f1718e.m1243c())) {
                    boolean z8 = !z7 && this.f1718e.f2352l && this.f1717d.m1191k();
                    this.f1717d.m1197q(z8);
                    m890k();
                    if (z8) {
                        this.f2538a.mo918k((int) this.f1716c.f1764e.f1749p);
                    }
                    synchronized (this) {
                        C0965j c0965j2 = this.f1722i;
                        this.f1722i = null;
                        if (c0965j2 != null) {
                            c0965j2.cancelTimeout(this.f1716c.f1764e);
                        }
                        if (this.f1719f == 101) {
                            interfaceC1160mOnSwitchProtocol = c0965j2.onSwitchProtocol(this.f2538a);
                            this.f1723j = null;
                            this.f1723j = null;
                        }
                        C0965j c0965j3 = this.f1723j;
                        if (c0965j3 != null) {
                            if (z8 && interfaceC1160mOnSwitchProtocol == this) {
                                this.f1722i = c0965j3;
                            } else {
                                this.f1716c.m905h(c0965j3);
                            }
                            this.f1723j = null;
                        }
                        if (this.f1722i == null && !this.f1721h) {
                            this.f1716c.m902e(this, !z8);
                        }
                    }
                } else {
                    continue;
                }
            } catch (Throwable th) {
                try {
                    interfaceC2016c = f1737q;
                    interfaceC2016c.mo2355f("Failure on " + this.f1722i, th);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    synchronized (this) {
                        if (c0965j.getStatus() != 10 && c0965j.getStatus() != 11 && !c0965j.isDone() && c0965j.setStatus(9)) {
                            c0965j.getEventListener().mo880f(th);
                        }
                        interfaceC2016c.mo2351a("{} {}", this.f1717d, this.f1718e);
                        interfaceC2016c.mo2351a("{}", this.f2538a);
                        this.f1717d.m1197q(false);
                        m890k();
                        synchronized (this) {
                            C0965j c0965j4 = this.f1722i;
                            this.f1722i = null;
                            if (c0965j4 != null) {
                                c0965j4.cancelTimeout(this.f1716c.f1764e);
                            }
                            if (this.f1719f == 101) {
                                interfaceC1160mOnSwitchProtocol = c0965j4.onSwitchProtocol(this.f2538a);
                                this.f1723j = null;
                                this.f1723j = null;
                            }
                            C0965j c0965j5 = this.f1723j;
                            if (c0965j5 != null) {
                                this.f1716c.m905h(c0965j5);
                                this.f1723j = null;
                            }
                            if (this.f1722i == null && !this.f1721h) {
                                this.f1716c.m902e(this, true);
                            }
                            z7 = true;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    z7 = true;
                    InterfaceC2016c interfaceC2016c4 = f1737q;
                    interfaceC2016c4.mo2351a("{} {}", this.f1717d, this.f1718e);
                    interfaceC2016c4.mo2351a("{}", this.f2538a);
                    if (z7 || (this.f1717d.m1188h() && this.f1718e.m1243c())) {
                        if (!z7 && this.f1718e.f2352l && this.f1717d.m1191k()) {
                            z6 = true;
                        }
                        this.f1717d.m1197q(z6);
                        m890k();
                        if (z6) {
                            this.f2538a.mo918k((int) this.f1716c.f1764e.f1749p);
                        }
                        synchronized (this) {
                            C0965j c0965j6 = this.f1722i;
                            this.f1722i = null;
                            if (c0965j6 != null) {
                                c0965j6.cancelTimeout(this.f1716c.f1764e);
                            }
                            if (this.f1719f == 101) {
                                interfaceC1160mOnSwitchProtocol = c0965j6.onSwitchProtocol(this.f2538a);
                                this.f1723j = null;
                                this.f1723j = null;
                            }
                            C0965j c0965j7 = this.f1723j;
                            if (c0965j7 != null) {
                                if (z6 && interfaceC1160mOnSwitchProtocol == this) {
                                    this.f1722i = c0965j7;
                                } else {
                                    this.f1716c.m905h(c0965j7);
                                }
                                this.f1723j = null;
                            }
                            if (this.f1722i == null && !this.f1721h) {
                                this.f1716c.m902e(this, !z6);
                            }
                        }
                    }
                    throw th;
                }
            }
        }
        return interfaceC1160mOnSwitchProtocol;
    }

    @Override // p041e5.AbstractC0956a
    /* renamed from: h */
    public void mo865h(C0965j c0965j) {
        synchronized (this) {
            super.mo865h(c0965j);
            this.f1740p = true;
            notifyAll();
        }
    }

    @Override // p041e5.AbstractC0956a
    /* renamed from: i */
    public boolean mo866i(C0965j c0965j) {
        boolean zMo866i = super.mo866i(c0965j);
        if (zMo866i) {
            synchronized (this) {
                notifyAll();
            }
        }
        return zMo866i;
    }

    /* renamed from: k */
    public void m890k() {
        this.f1738n = false;
        this.f1740p = false;
        this.f1718e.m1249i();
        this.f1717d.reset();
    }
}
