package p041e5;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.SSLEngine;
import p007a6.C0045e;
import p009b.C0413b;
import p041e5.C0962g;
import p073i5.InterfaceC1151d;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1160m;
import p089k5.AbstractC1400i;
import p089k5.C1401j;
import p168u5.C1981b;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: SelectConnector.java */
/* renamed from: e5.k */
/* loaded from: classes.dex */
public class C0966k extends C1981b implements C0962g.b {

    /* renamed from: k */
    public static final InterfaceC2016c f1781k;

    /* renamed from: h */
    public final C0962g f1782h;

    /* renamed from: i */
    public final b f1783i;

    /* renamed from: j */
    public final Map<SocketChannel, C0045e.a> f1784j;

    /* compiled from: SelectConnector.java */
    /* renamed from: e5.k$a */
    public class a extends C0045e.a {

        /* renamed from: i */
        public final SocketChannel f1785i;

        /* renamed from: j */
        public final C0963h f1786j;

        public a(SocketChannel socketChannel, C0963h c0963h) {
            this.f1785i = socketChannel;
            this.f1786j = c0963h;
        }

        @Override // p007a6.C0045e.a
        /* renamed from: c */
        public void mo57c() throws InterruptedException {
            if (this.f1785i.isConnectionPending()) {
                C0966k.f1781k.mo2351a("Channel {} timed out while connecting, closing it", this.f1785i);
                try {
                    this.f1785i.close();
                } catch (IOException e7) {
                    C0966k.f1781k.mo2360k(e7);
                }
                C0966k.this.f1784j.remove(this.f1785i);
                this.f1786j.m900c(new SocketTimeoutException());
            }
        }
    }

    /* compiled from: SelectConnector.java */
    /* renamed from: e5.k$b */
    public class b extends AbstractC1400i {

        /* renamed from: n */
        public InterfaceC2016c f1788n = C0966k.f1781k;

        public b() {
        }

        @Override // p089k5.AbstractC1400i
        /* renamed from: G */
        public void mo907G(SocketChannel socketChannel, Throwable th, Object obj) throws InterruptedException {
            C0045e.a aVarRemove = C0966k.this.f1784j.remove(socketChannel);
            if (aVarRemove != null) {
                aVarRemove.m56b();
            }
            if (obj instanceof C0963h) {
                ((C0963h) obj).m900c(th);
                return;
            }
            InterfaceC2016c interfaceC2016c = AbstractC1400i.f4084i;
            interfaceC2016c.mo2356g(th + "," + socketChannel + "," + obj, new Object[0]);
            interfaceC2016c.mo2359j(th);
        }

        @Override // p089k5.AbstractC1400i
        public boolean dispatch(Runnable runnable) {
            return C0966k.this.f1782h.f1747n.dispatch(runnable);
        }
    }

    /* compiled from: SelectConnector.java */
    /* renamed from: e5.k$c */
    public static class c implements InterfaceC1151d {

        /* renamed from: e */
        public InterfaceC1151d f1790e;

        /* renamed from: f */
        public SSLEngine f1791f;

        public c(InterfaceC1151d interfaceC1151d, SSLEngine sSLEngine) {
            this.f1791f = sSLEngine;
            this.f1790e = interfaceC1151d;
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: a */
        public void mo908a(C0045e.a aVar) {
            this.f1790e.mo908a(aVar);
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: b */
        public void mo909b(C0045e.a aVar, long j7) {
            this.f1790e.mo909b(aVar, j7);
        }

        @Override // p073i5.InterfaceC1159l
        /* renamed from: c */
        public void mo910c(InterfaceC1160m interfaceC1160m) {
            this.f1790e.mo910c(interfaceC1160m);
        }

        @Override // p073i5.InterfaceC1161n
        public void close() {
            this.f1790e.close();
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: d */
        public void mo911d() {
            this.f1790e.mo915h();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: e */
        public String mo912e() {
            return this.f1790e.mo912e();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: f */
        public int mo913f() {
            return this.f1790e.mo913f();
        }

        @Override // p073i5.InterfaceC1161n
        public void flush() {
            this.f1790e.flush();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: g */
        public String mo914g() {
            return this.f1790e.mo914g();
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: h */
        public void mo915h() {
            this.f1790e.mo915h();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: i */
        public int mo916i() {
            return this.f1790e.mo916i();
        }

        @Override // p073i5.InterfaceC1161n
        public boolean isOpen() {
            return this.f1790e.isOpen();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: j */
        public int mo917j(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2, InterfaceC1152e interfaceC1152e3) {
            return this.f1790e.mo917j(interfaceC1152e, interfaceC1152e2, interfaceC1152e3);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: k */
        public void mo918k(int i7) {
            this.f1790e.mo918k(i7);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: l */
        public Object mo919l() {
            return this.f1790e.mo919l();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: m */
        public void mo920m() {
            this.f1790e.mo920m();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: n */
        public String mo921n() {
            return this.f1790e.mo921n();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: o */
        public boolean mo922o(long j7) {
            return this.f1790e.mo922o(j7);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: p */
        public boolean mo923p() {
            return this.f1790e.mo923p();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: q */
        public int mo924q() {
            return this.f1790e.mo924q();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: r */
        public boolean mo925r() {
            return this.f1790e.mo925r();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: s */
        public boolean mo926s() {
            return this.f1790e.mo926s();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: t */
        public void mo927t() {
            this.f1790e.mo927t();
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("Upgradable:");
            sbM112a.append(this.f1790e.toString());
            return sbM112a.toString();
        }

        @Override // p073i5.InterfaceC1151d
        /* renamed from: u */
        public boolean mo928u() {
            return this.f1790e.mo928u();
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: v */
        public int mo929v(InterfaceC1152e interfaceC1152e) {
            return this.f1790e.mo929v(interfaceC1152e);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: w */
        public boolean mo930w(long j7) {
            return this.f1790e.mo930w(j7);
        }

        @Override // p073i5.InterfaceC1161n
        /* renamed from: x */
        public int mo931x(InterfaceC1152e interfaceC1152e) {
            return this.f1790e.mo931x(interfaceC1152e);
        }

        @Override // p073i5.InterfaceC1159l
        /* renamed from: y */
        public InterfaceC1160m mo932y() {
            return this.f1790e.mo932y();
        }

        /* renamed from: z */
        public void m933z() {
            C0958c c0958c = (C0958c) this.f1790e.mo932y();
            C1401j c1401j = new C1401j(this.f1791f, this.f1790e);
            this.f1790e.mo910c(c1401j);
            C1401j.c cVar = c1401j.f4118g;
            this.f1790e = cVar;
            cVar.mo910c(c0958c);
            C0966k.f1781k.mo2351a("upgrade {} to {} for {}", this, c1401j, c0958c);
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f1781k = C2015b.m2349a(C0966k.class.getName());
    }

    public C0966k(C0962g c0962g) {
        b bVar = new b();
        this.f1783i = bVar;
        this.f1784j = new ConcurrentHashMap();
        this.f1782h = c0962g;
        m2306H(c0962g, false);
        m2306H(bVar, true);
    }

    @Override // p041e5.C0962g.b
    /* renamed from: n */
    public void mo897n(C0963h c0963h) throws InterruptedException, IOException {
        SocketChannel socketChannel = null;
        try {
            SocketChannel socketChannelOpen = SocketChannel.open();
            C0957b c0957b = c0963h.m899b() ? c0963h.f1773n : c0963h.f1765f;
            socketChannelOpen.socket().setTcpNoDelay(true);
            if (this.f1782h.f1743j) {
                socketChannelOpen.socket().connect(c0957b.m885a(), this.f1782h.f1751r);
                socketChannelOpen.configureBlocking(false);
                this.f1783i.m1571H(socketChannelOpen, c0963h);
                return;
            }
            socketChannelOpen.configureBlocking(false);
            socketChannelOpen.connect(c0957b.m885a());
            this.f1783i.m1571H(socketChannelOpen, c0963h);
            a aVar = new a(socketChannelOpen, c0963h);
            C0962g c0962g = this.f1782h;
            long j7 = c0962g.f1751r;
            C0045e c0045e = c0962g.f1752s;
            c0045e.m53d(aVar, j7 - c0045e.f60b);
            this.f1784j.put(socketChannelOpen, aVar);
        } catch (IOException e7) {
            if (0 != 0) {
                socketChannel.close();
            }
            c0963h.m900c(e7);
        } catch (UnresolvedAddressException e8) {
            if (0 != 0) {
                socketChannel.close();
            }
            c0963h.m900c(e8);
        }
    }
}
