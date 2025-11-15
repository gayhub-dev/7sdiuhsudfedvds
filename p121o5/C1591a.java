package p121o5;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import p007a6.InterfaceC0044d;
import p065h5.C1100h;
import p073i5.C1162o;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1159l;
import p073i5.InterfaceC1160m;
import p073i5.InterfaceC1161n;
import p081j5.C1210a;
import p113n5.AbstractC1540a;
import p113n5.AbstractC1541b;
import p113n5.C1542c;
import p113n5.C1544e;
import p113n5.C1553n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: SocketConnector.java */
/* renamed from: o5.a */
/* loaded from: classes.dex */
public class C1591a extends AbstractC1540a {

    /* renamed from: E */
    public static final InterfaceC2016c f4839E;

    /* renamed from: B */
    public ServerSocket f4840B;

    /* renamed from: D */
    public volatile int f4842D = -1;

    /* renamed from: C */
    public final Set<InterfaceC1161n> f4841C = new HashSet();

    /* compiled from: SocketConnector.java */
    /* renamed from: o5.a$a */
    public class a extends C1210a implements Runnable, InterfaceC1159l {

        /* renamed from: n */
        public volatile InterfaceC1160m f4843n;

        /* renamed from: o */
        public final Socket f4844o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Socket socket) {
            super(socket, C1591a.this.f4531t);
            InterfaceC2016c interfaceC2016c = C1591a.f4839E;
            this.f4843n = new C1544e(C1591a.this, this, C1591a.this.f4519h);
            this.f4844o = socket;
        }

        @Override // p081j5.C1210a, p081j5.C1211b, p073i5.InterfaceC1161n
        public void close() throws IOException {
            if (this.f4843n instanceof AbstractC1541b) {
                C1542c c1542c = ((AbstractC1541b) this.f4843n).f4548i.f4632a;
                synchronized (c1542c) {
                    c1542c.m1744b();
                }
            }
            super.close();
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            Socket socket;
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    C1591a.m1857N(C1591a.this, this.f4843n);
                                    synchronized (C1591a.this.f4841C) {
                                        C1591a.this.f4841C.add(this);
                                    }
                                    while (C1591a.this.isStarted() && !(!isOpen())) {
                                        if (this.f4843n.mo862b() && C1591a.this.mo1722E()) {
                                            mo918k(C1591a.this.f4532u);
                                        }
                                        this.f4843n = this.f4843n.mo887e();
                                    }
                                    C1591a c1591a = C1591a.this;
                                    InterfaceC1160m interfaceC1160m = this.f4843n;
                                    Objects.requireNonNull(c1591a);
                                    interfaceC1160m.onClose();
                                    synchronized (C1591a.this.f4841C) {
                                        C1591a.this.f4841C.remove(this);
                                    }
                                } catch (C1162o e7) {
                                    C1591a.f4839E.mo2355f("EOF", e7);
                                    try {
                                        close();
                                    } catch (IOException e8) {
                                        C1591a.f4839E.mo2360k(e8);
                                    }
                                    C1591a c1591a2 = C1591a.this;
                                    InterfaceC1160m interfaceC1160m2 = this.f4843n;
                                    Objects.requireNonNull(c1591a2);
                                    interfaceC1160m2.onClose();
                                    synchronized (C1591a.this.f4841C) {
                                        C1591a.this.f4841C.remove(this);
                                        if (this.f4844o.isClosed()) {
                                            return;
                                        }
                                        long jCurrentTimeMillis = System.currentTimeMillis();
                                        int i7 = this.f2732g;
                                        this.f4844o.setSoTimeout(i7);
                                        while (this.f4844o.getInputStream().read() >= 0 && System.currentTimeMillis() - jCurrentTimeMillis < i7) {
                                        }
                                        if (this.f4844o.isClosed()) {
                                            return;
                                        } else {
                                            socket = this.f4844o;
                                        }
                                    }
                                }
                            } catch (Exception e9) {
                                C1591a.f4839E.mo2354e("handle failed?", e9);
                                try {
                                    close();
                                } catch (IOException e10) {
                                    C1591a.f4839E.mo2360k(e10);
                                }
                                C1591a c1591a3 = C1591a.this;
                                InterfaceC1160m interfaceC1160m3 = this.f4843n;
                                Objects.requireNonNull(c1591a3);
                                interfaceC1160m3.onClose();
                                synchronized (C1591a.this.f4841C) {
                                    C1591a.this.f4841C.remove(this);
                                    if (this.f4844o.isClosed()) {
                                        return;
                                    }
                                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                                    int i8 = this.f2732g;
                                    this.f4844o.setSoTimeout(i8);
                                    while (this.f4844o.getInputStream().read() >= 0 && System.currentTimeMillis() - jCurrentTimeMillis2 < i8) {
                                    }
                                    if (this.f4844o.isClosed()) {
                                        return;
                                    } else {
                                        socket = this.f4844o;
                                    }
                                }
                            }
                        } catch (SocketException e11) {
                            C1591a.f4839E.mo2355f("EOF", e11);
                            try {
                                close();
                            } catch (IOException e12) {
                                C1591a.f4839E.mo2360k(e12);
                            }
                            C1591a c1591a4 = C1591a.this;
                            InterfaceC1160m interfaceC1160m4 = this.f4843n;
                            Objects.requireNonNull(c1591a4);
                            interfaceC1160m4.onClose();
                            synchronized (C1591a.this.f4841C) {
                                C1591a.this.f4841C.remove(this);
                                if (this.f4844o.isClosed()) {
                                    return;
                                }
                                long jCurrentTimeMillis3 = System.currentTimeMillis();
                                int i9 = this.f2732g;
                                this.f4844o.setSoTimeout(i9);
                                while (this.f4844o.getInputStream().read() >= 0 && System.currentTimeMillis() - jCurrentTimeMillis3 < i9) {
                                }
                                if (this.f4844o.isClosed()) {
                                    return;
                                } else {
                                    socket = this.f4844o;
                                }
                            }
                        }
                    } catch (C1100h e13) {
                        C1591a.f4839E.mo2355f("BAD", e13);
                        try {
                            close();
                        } catch (IOException e14) {
                            C1591a.f4839E.mo2360k(e14);
                        }
                        C1591a c1591a5 = C1591a.this;
                        InterfaceC1160m interfaceC1160m5 = this.f4843n;
                        Objects.requireNonNull(c1591a5);
                        interfaceC1160m5.onClose();
                        synchronized (C1591a.this.f4841C) {
                            C1591a.this.f4841C.remove(this);
                            if (this.f4844o.isClosed()) {
                                return;
                            }
                            long jCurrentTimeMillis4 = System.currentTimeMillis();
                            int i10 = this.f2732g;
                            this.f4844o.setSoTimeout(i10);
                            while (this.f4844o.getInputStream().read() >= 0 && System.currentTimeMillis() - jCurrentTimeMillis4 < i10) {
                            }
                            if (this.f4844o.isClosed()) {
                                return;
                            } else {
                                socket = this.f4844o;
                            }
                        }
                    }
                    if (this.f4844o.isClosed()) {
                        return;
                    }
                    long jCurrentTimeMillis5 = System.currentTimeMillis();
                    int i11 = this.f2732g;
                    this.f4844o.setSoTimeout(i11);
                    while (this.f4844o.getInputStream().read() >= 0 && System.currentTimeMillis() - jCurrentTimeMillis5 < i11) {
                    }
                    if (this.f4844o.isClosed()) {
                        return;
                    }
                    socket = this.f4844o;
                    socket.close();
                } catch (IOException e15) {
                    C1591a.f4839E.mo2360k(e15);
                }
            } catch (Throwable th) {
                C1591a c1591a6 = C1591a.this;
                InterfaceC1160m interfaceC1160m6 = this.f4843n;
                InterfaceC2016c interfaceC2016c = C1591a.f4839E;
                Objects.requireNonNull(c1591a6);
                interfaceC1160m6.onClose();
                synchronized (C1591a.this.f4841C) {
                    C1591a.this.f4841C.remove(this);
                    try {
                        if (!this.f4844o.isClosed()) {
                            long jCurrentTimeMillis6 = System.currentTimeMillis();
                            int i12 = this.f2732g;
                            this.f4844o.setSoTimeout(i12);
                            while (this.f4844o.getInputStream().read() >= 0 && System.currentTimeMillis() - jCurrentTimeMillis6 < i12) {
                            }
                            if (!this.f4844o.isClosed()) {
                                this.f4844o.close();
                            }
                        }
                    } catch (IOException e16) {
                        C1591a.f4839E.mo2360k(e16);
                    }
                    throw th;
                }
            }
        }

        @Override // p081j5.C1211b, p073i5.InterfaceC1161n
        /* renamed from: v */
        public int mo929v(InterfaceC1152e interfaceC1152e) throws IOException {
            int iMo929v = super.mo929v(interfaceC1152e);
            if (iMo929v < 0) {
                if (!mo926s()) {
                    mo920m();
                }
                if (mo925r()) {
                    close();
                }
            }
            return iMo929v;
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f4839E = C2015b.m2349a(C1591a.class.getName());
    }

    /* renamed from: N */
    public static void m1857N(C1591a c1591a, InterfaceC1160m interfaceC1160m) {
        if (c1591a.f4535x.get() == -1) {
            return;
        }
        c1591a.f4536y.m2598a(1L);
    }

    @Override // p113n5.AbstractC1540a
    /* renamed from: M */
    public void mo1723M(int i7) throws IOException {
        Socket socketAccept = this.f4840B.accept();
        try {
            socketAccept.setTcpNoDelay(true);
            int i8 = this.f4533v;
            if (i8 >= 0) {
                socketAccept.setSoLinger(true, i8 / 1000);
            } else {
                socketAccept.setSoLinger(false, 0);
            }
        } catch (Exception e7) {
            AbstractC1540a.f4518A.mo2360k(e7);
        }
        a aVar = new a(socketAccept);
        InterfaceC0044d interfaceC0044d = this.f4520i;
        if (interfaceC0044d == null || !interfaceC0044d.dispatch(aVar)) {
            f4839E.mo2356g("dispatch failed for {}", aVar.f4843n);
            aVar.close();
        }
    }

    /* renamed from: O */
    public void m1858O() {
        ServerSocket serverSocket = this.f4840B;
        if (serverSocket == null || serverSocket.isClosed()) {
            String str = this.f4521j;
            int i7 = this.f4522k;
            this.f4840B = str == null ? new ServerSocket(i7, 0) : new ServerSocket(i7, 0, InetAddress.getByName(str));
        }
        this.f4840B.setReuseAddress(this.f4530s);
        this.f4842D = this.f4840B.getLocalPort();
        if (this.f4842D > 0) {
            return;
        }
        throw new IllegalStateException("port not allocated for " + this);
    }

    @Override // p113n5.InterfaceC1545f
    public void close() throws IOException {
        ServerSocket serverSocket = this.f4840B;
        if (serverSocket != null) {
            serverSocket.close();
        }
        this.f4840B = null;
        this.f4842D = -2;
    }

    @Override // p113n5.AbstractC1540a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() {
        this.f4841C.clear();
        super.doStart();
    }

    @Override // p113n5.AbstractC1540a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() throws IOException {
        super.doStop();
        HashSet hashSet = new HashSet();
        synchronized (this.f4841C) {
            hashSet.addAll(this.f4841C);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((a) ((InterfaceC1161n) it.next())).close();
        }
    }

    @Override // p113n5.InterfaceC1545f
    /* renamed from: f */
    public int mo1768f() {
        return this.f4842D;
    }

    @Override // p113n5.AbstractC1540a, p113n5.InterfaceC1545f
    /* renamed from: y */
    public void mo1733y(InterfaceC1161n interfaceC1161n, C1553n c1553n) throws SocketException {
        ((a) interfaceC1161n).mo918k(mo1722E() ? this.f4532u : this.f4531t);
    }
}
