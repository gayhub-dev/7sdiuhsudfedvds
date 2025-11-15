package p041e5;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.Properties;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import p041e5.C0962g;
import p065h5.C1097e;
import p073i5.InterfaceC1160m;
import p081j5.C1210a;
import p168u5.AbstractC1980a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;
import p196y5.C2133a;

/* compiled from: SocketConnector.java */
/* renamed from: e5.l */
/* loaded from: classes.dex */
public class C0967l extends AbstractC1980a implements C0962g.b {

    /* renamed from: f */
    public static final InterfaceC2016c f1792f;

    /* renamed from: e */
    public final C0962g f1793e;

    /* compiled from: SocketConnector.java */
    /* renamed from: e5.l$a */
    public class a implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ AbstractC0956a f1794e;

        /* renamed from: f */
        public final /* synthetic */ C0963h f1795f;

        public a(C0967l c0967l, AbstractC0956a abstractC0956a, C0963h c0963h) {
            this.f1794e = abstractC0956a;
            this.f1795f = c0963h;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    InterfaceC1160m interfaceC1160m = this.f1794e;
                    while (true) {
                        InterfaceC1160m interfaceC1160mMo887e = interfaceC1160m.mo887e();
                        if (interfaceC1160mMo887e == interfaceC1160m) {
                            try {
                                this.f1795f.m902e(this.f1794e, true);
                                return;
                            } catch (IOException e7) {
                                e = e7;
                                C0967l.f1792f.mo2359j(e);
                            }
                        }
                        interfaceC1160m = interfaceC1160mMo887e;
                    }
                } catch (IOException e8) {
                    if (e8 instanceof InterruptedIOException) {
                        C0967l.f1792f.mo2360k(e8);
                    } else {
                        C0967l.f1792f.mo2359j(e8);
                        C0963h c0963h = this.f1795f;
                        synchronized (c0963h) {
                            c0963h.f1771l--;
                            if (c0963h.f1760a.size() > 0) {
                                C0965j c0965jRemove = c0963h.f1760a.remove(0);
                                if (c0965jRemove.setStatus(9)) {
                                    c0965jRemove.getEventListener().mo880f(e8);
                                }
                            }
                        }
                    }
                    try {
                        this.f1795f.m902e(this.f1794e, true);
                    } catch (IOException e9) {
                        e = e9;
                        C0967l.f1792f.mo2359j(e);
                    }
                }
            } catch (Throwable th) {
                try {
                    this.f1795f.m902e(this.f1794e, true);
                } catch (IOException e10) {
                    C0967l.f1792f.mo2359j(e10);
                }
                throw th;
            }
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f1792f = C2015b.m2349a(C0967l.class.getName());
    }

    public C0967l(C0962g c0962g) {
        this.f1793e = c0962g;
    }

    @Override // p041e5.C0962g.b
    /* renamed from: n */
    public void mo897n(C0963h c0963h) throws IOException {
        Socket socketCreateSocket;
        if (c0963h.f1766g) {
            C2133a c2133a = c0963h.f1767h;
            SSLSocket sSLSocket = (SSLSocket) c2133a.f6278q.getSocketFactory().createSocket();
            sSLSocket.setEnabledCipherSuites(c2133a.m2564H(sSLSocket.getEnabledCipherSuites(), sSLSocket.getSupportedCipherSuites()));
            sSLSocket.setEnabledProtocols(c2133a.m2565J(sSLSocket.getEnabledProtocols(), sSLSocket.getSupportedProtocols()));
            socketCreateSocket = sSLSocket;
        } else {
            socketCreateSocket = SocketFactory.getDefault().createSocket();
        }
        socketCreateSocket.setSoTimeout(0);
        socketCreateSocket.setTcpNoDelay(true);
        socketCreateSocket.connect((c0963h.m899b() ? c0963h.f1773n : c0963h.f1765f).m885a(), this.f1793e.f1751r);
        C1210a c1210a = new C1210a(socketCreateSocket);
        C1097e c1097e = this.f1793e.f1757x;
        C0959d c0959d = new C0959d(c1097e.f2271n, c1097e.f2272o, c1210a);
        c0959d.f1716c = c0963h;
        c0963h.m901d(c0959d);
        this.f1793e.f1747n.dispatch(new a(this, c0959d, c0963h));
    }
}
