package p041e5;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.fourthline.cling.model.ServiceReference;
import p007a6.C0045e;
import p049f5.InterfaceC1013a;
import p065h5.C1101i;
import p065h5.C1105m;
import p065h5.C1106n;
import p065h5.C1107o;
import p065h5.C1108p;
import p065h5.C1109q;
import p065h5.C1114v;
import p073i5.AbstractC1150c;
import p073i5.C1155h;
import p073i5.C1158k;
import p073i5.C1162o;
import p073i5.C1166s;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1156i;
import p073i5.InterfaceC1161n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AbstractHttpConnection.java */
/* renamed from: e5.a */
/* loaded from: classes.dex */
public abstract class AbstractC0956a extends AbstractC1150c {

    /* renamed from: m */
    public static final InterfaceC2016c f1715m;

    /* renamed from: c */
    public C0963h f1716c;

    /* renamed from: d */
    public C1105m f1717d;

    /* renamed from: e */
    public C1109q f1718e;

    /* renamed from: f */
    public int f1719f;

    /* renamed from: g */
    public InterfaceC1152e f1720g;

    /* renamed from: h */
    public boolean f1721h;

    /* renamed from: i */
    public volatile C0965j f1722i;

    /* renamed from: j */
    public C0965j f1723j;

    /* renamed from: k */
    public final C0045e.a f1724k;

    /* renamed from: l */
    public AtomicBoolean f1725l;

    /* compiled from: AbstractHttpConnection.java */
    /* renamed from: e5.a$b */
    public class b extends C0045e.a {
        public b(a aVar) {
        }

        @Override // p007a6.C0045e.a
        /* renamed from: c */
        public void mo57c() {
            if (AbstractC0956a.this.f1725l.compareAndSet(true, false)) {
                AbstractC0956a abstractC0956a = AbstractC0956a.this;
                abstractC0956a.f1716c.m903f(abstractC0956a);
            }
        }
    }

    /* compiled from: AbstractHttpConnection.java */
    /* renamed from: e5.a$c */
    public class c extends C1109q.a {
        public c(a aVar) {
        }

        @Override // p065h5.C1109q.a
        /* renamed from: a */
        public void mo868a(InterfaceC1152e interfaceC1152e) {
            C0965j c0965j = AbstractC0956a.this.f1722i;
            if (c0965j != null) {
                c0965j.getEventListener().mo877c(interfaceC1152e);
            }
        }

        @Override // p065h5.C1109q.a
        /* renamed from: b */
        public void mo869b() {
            C0965j c0965j = AbstractC0956a.this.f1722i;
            if (c0965j == null || c0965j.isDone() || !c0965j.setStatus(9)) {
                return;
            }
            c0965j.getEventListener().mo880f(new C1162o("early EOF"));
        }

        @Override // p065h5.C1109q.a
        /* renamed from: c */
        public void mo870c() {
            C0965j c0965j = AbstractC0956a.this.f1722i;
            if (c0965j != null) {
                c0965j.setStatus(6);
                if ("CONNECT".equalsIgnoreCase(c0965j.getMethod())) {
                    AbstractC0956a.this.f1718e.f2352l = true;
                }
            }
        }

        @Override // p065h5.C1109q.a
        /* renamed from: d */
        public void mo871d(long j7) {
            C0965j c0965j = AbstractC0956a.this.f1722i;
            if (c0965j != null) {
                c0965j.setStatus(7);
            }
        }

        @Override // p065h5.C1109q.a
        /* renamed from: e */
        public void mo872e(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
            C0965j c0965j = AbstractC0956a.this.f1722i;
            if (c0965j != null) {
                if (C1107o.f2326d.m1357e(interfaceC1152e) == 1) {
                    AbstractC0956a.this.f1720g = C1106n.f2323d.m1359g(interfaceC1152e2);
                }
                c0965j.getEventListener().mo879e(interfaceC1152e, interfaceC1152e2);
            }
        }

        @Override // p065h5.C1109q.a
        /* renamed from: f */
        public void mo873f(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2, InterfaceC1152e interfaceC1152e3) {
        }

        @Override // p065h5.C1109q.a
        /* renamed from: g */
        public void mo874g(InterfaceC1152e interfaceC1152e, int i7, InterfaceC1152e interfaceC1152e2) {
            C0965j c0965j = AbstractC0956a.this.f1722i;
            if (c0965j == null) {
                AbstractC0956a.f1715m.mo2356g("No exchange for response", new Object[0]);
                AbstractC0956a.this.f2538a.close();
                return;
            }
            if (i7 == 100 || i7 == 102) {
                c0965j.setEventListener(AbstractC0956a.this.new d(c0965j));
            } else if (i7 == 200 && "CONNECT".equalsIgnoreCase(c0965j.getMethod())) {
                AbstractC0956a.this.f1718e.f2361u = true;
            }
            AbstractC0956a abstractC0956a = AbstractC0956a.this;
            C1114v.f2382c.equals(interfaceC1152e);
            Objects.requireNonNull(abstractC0956a);
            AbstractC0956a.this.f1719f = i7;
            c0965j.getEventListener().mo883i(interfaceC1152e, i7, interfaceC1152e2);
            c0965j.setStatus(5);
        }
    }

    /* compiled from: AbstractHttpConnection.java */
    /* renamed from: e5.a$d */
    public class d implements InterfaceC0964i {

        /* renamed from: a */
        public final C0965j f1728a;

        /* renamed from: b */
        public final InterfaceC0964i f1729b;

        public d(C0965j c0965j) {
            this.f1728a = c0965j;
            this.f1729b = c0965j.getEventListener();
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: a */
        public void mo875a() {
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: b */
        public void mo876b() {
            this.f1728a.setEventListener(this.f1729b);
            this.f1728a.setStatus(4);
            AbstractC0956a.this.f1718e.m1249i();
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: c */
        public void mo877c(InterfaceC1152e interfaceC1152e) {
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: d */
        public void mo878d(Throwable th) {
            this.f1728a.setEventListener(this.f1729b);
            this.f1729b.mo878d(th);
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: e */
        public void mo879e(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
            this.f1729b.mo879e(interfaceC1152e, interfaceC1152e2);
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: f */
        public void mo880f(Throwable th) {
            this.f1728a.setEventListener(this.f1729b);
            this.f1729b.mo880f(th);
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: g */
        public void mo881g() {
            this.f1729b.mo881g();
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: h */
        public void mo882h() {
            this.f1728a.setEventListener(this.f1729b);
            this.f1729b.mo882h();
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: i */
        public void mo883i(InterfaceC1152e interfaceC1152e, int i7, InterfaceC1152e interfaceC1152e2) {
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: j */
        public void mo884j() {
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f1715m = C2015b.m2349a(AbstractC0956a.class.getName());
    }

    public AbstractC0956a(InterfaceC1156i interfaceC1156i, InterfaceC1156i interfaceC1156i2, InterfaceC1161n interfaceC1161n) {
        super(interfaceC1161n);
        this.f1724k = new b(null);
        this.f1725l = new AtomicBoolean(false);
        this.f1717d = new C1105m(interfaceC1156i, interfaceC1161n);
        this.f1718e = new C1109q(interfaceC1156i2, interfaceC1161n, new c(null));
    }

    @Override // p073i5.InterfaceC1160m
    /* renamed from: a */
    public boolean mo861a() {
        return false;
    }

    @Override // p073i5.InterfaceC1160m
    /* renamed from: b */
    public boolean mo862b() {
        boolean z6;
        synchronized (this) {
            z6 = this.f1722i == null;
        }
        return z6;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0024  */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m863f() {
        /*
            r5 = this;
            e5.j r0 = r5.f1722i
            r1 = 1
            if (r0 == 0) goto L58
            boolean r2 = r0.isDone()
            if (r2 != 0) goto L58
            int r2 = r0.getStatus()
            switch(r2) {
                case 6: goto L13;
                case 7: goto L58;
                case 8: goto L58;
                case 9: goto L58;
                case 10: goto L58;
                case 11: goto L58;
                default: goto L12;
            }
        L12:
            goto L24
        L13:
            i5.n r2 = r5.f2538a
            boolean r2 = r2.mo926s()
            if (r2 == 0) goto L24
            h5.q r2 = r5.f1718e
            boolean r2 = r2.m1246f(r1)
            if (r2 == 0) goto L24
            goto L58
        L24:
            java.lang.String r2 = r0.toString()
            i5.n r3 = r5.f2538a
            boolean r3 = r3.isOpen()
            if (r3 == 0) goto L3e
            i5.n r3 = r5.f2538a
            boolean r3 = r3.mo926s()
            if (r3 == 0) goto L3b
            java.lang.String r3 = "half closed: "
            goto L40
        L3b:
            java.lang.String r3 = "local close: "
            goto L40
        L3e:
            java.lang.String r3 = "closed: "
        L40:
            r4 = 9
            boolean r4 = r0.setStatus(r4)
            if (r4 == 0) goto L58
            e5.i r0 = r0.getEventListener()
            i5.o r4 = new i5.o
            java.lang.String r2 = android.arch.lifecycle.C0063n.m88a(r3, r2)
            r4.<init>(r2)
            r0.mo880f(r4)
        L58:
            i5.n r0 = r5.f2538a
            boolean r0 = r0.isOpen()
            if (r0 == 0) goto L6a
            i5.n r0 = r5.f2538a
            r0.close()
            e5.h r0 = r5.f1716c
            r0.m902e(r5, r1)
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p041e5.AbstractC0956a.m863f():void");
    }

    /* renamed from: g */
    public void m864g() {
        synchronized (this) {
            this.f1719f = 0;
            if (this.f1722i.getStatus() != 2) {
                throw new IllegalStateException();
            }
            this.f1722i.setStatus(3);
            this.f1717d.m1199s(this.f1722i.getVersion());
            String method = this.f1722i.getMethod();
            String requestURI = this.f1722i.getRequestURI();
            if (this.f1716c.m899b()) {
                if (!"CONNECT".equals(method) && requestURI.startsWith(ServiceReference.DELIMITER)) {
                    C0963h c0963h = this.f1716c;
                    boolean z6 = c0963h.f1766g;
                    C0957b c0957b = c0963h.f1765f;
                    String str = c0957b.f1731a;
                    int i7 = c0957b.f1732b;
                    StringBuilder sb = new StringBuilder();
                    sb.append(z6 ? "https" : "http");
                    sb.append("://");
                    sb.append(str);
                    if ((!z6 || i7 != 443) && (z6 || i7 != 80)) {
                        sb.append(":");
                        sb.append(i7);
                    }
                    sb.append(requestURI);
                    requestURI = sb.toString();
                }
                InterfaceC1013a interfaceC1013a = this.f1716c.f1774o;
                if (interfaceC1013a != null) {
                    interfaceC1013a.m1026a(this.f1722i);
                }
            }
            C1105m c1105m = this.f1717d;
            Objects.requireNonNull(c1105m);
            if (method == null || "GET".equals(method)) {
                c1105m.f2248g = C1108p.f2339b;
            } else {
                c1105m.f2248g = C1108p.f2338a.m1360h(method);
            }
            c1105m.f2249h = requestURI;
            if (c1105m.f2246e == 9) {
                c1105m.f2254m = true;
            }
            this.f1718e.f2361u = "HEAD".equalsIgnoreCase(method);
            C1101i requestFields = this.f1722i.getRequestFields();
            if (this.f1722i.getVersion() >= 11) {
                InterfaceC1152e interfaceC1152e = C1107o.f2327e;
                if (!requestFields.f2297b.containsKey(C1107o.f2326d.m1359g(interfaceC1152e))) {
                    requestFields.m1222a(interfaceC1152e, this.f1716c.f1768i);
                }
            }
            InterfaceC1152e requestContent = this.f1722i.getRequestContent();
            if (requestContent != null) {
                long length = requestContent.length();
                Objects.requireNonNull(requestFields);
                InterfaceC1152e interfaceC1152eM1360h = C1107o.f2326d.m1360h("Content-Length");
                C1158k c1158k = new C1158k(32);
                C1155h.m1363a(c1158k, length);
                requestFields.m1228i(interfaceC1152eM1360h, c1158k);
                this.f1717d.mo1182b(requestFields, false);
                this.f1717d.m1235t(new C1166s(requestContent), true);
                this.f1722i.setStatus(4);
            } else if (this.f1722i.getRequestContentSource() != null) {
                this.f1717d.mo1182b(requestFields, false);
            } else {
                Objects.requireNonNull(requestFields);
                requestFields.m1232m(C1107o.f2326d.m1360h("Content-Length"));
                this.f1717d.mo1182b(requestFields, true);
                this.f1722i.setStatus(4);
            }
        }
    }

    /* renamed from: h */
    public void mo865h(C0965j c0965j) {
        synchronized (this) {
            if (this.f1722i == c0965j) {
                try {
                    this.f1716c.m902e(this, true);
                } catch (IOException e7) {
                    f1715m.mo2360k(e7);
                }
            }
        }
    }

    /* renamed from: i */
    public boolean mo866i(C0965j c0965j) {
        f1715m.mo2351a("Send {} on {}", c0965j, this);
        synchronized (this) {
            if (this.f1722i != null) {
                if (this.f1723j == null) {
                    this.f1723j = c0965j;
                    return true;
                }
                throw new IllegalStateException(this + " PIPELINED!!!  _exchange=" + this.f1722i);
            }
            this.f1722i = c0965j;
            this.f1722i.associate(this);
            if (!this.f2538a.isOpen()) {
                this.f1722i.disassociate();
                this.f1722i = null;
                return false;
            }
            this.f1722i.setStatus(2);
            long timeout = this.f1722i.getTimeout();
            if (timeout <= 0) {
                timeout = this.f1716c.f1764e.f1750q;
            }
            long jMo916i = this.f2538a.mo916i();
            if (timeout > 0 && timeout > jMo916i) {
                this.f2538a.mo918k(((int) timeout) * 2);
            }
            return true;
        }
    }

    /* renamed from: j */
    public void m867j() {
        synchronized (this) {
            if (!this.f1725l.compareAndSet(false, true)) {
                throw new IllegalStateException();
            }
            C0962g c0962g = this.f1716c.f1764e;
            c0962g.f1753t.m53d(this.f1724k, 0L);
        }
    }

    @Override // p073i5.InterfaceC1160m
    public void onClose() {
    }

    @Override // p073i5.AbstractC1150c
    public String toString() {
        Object[] objArr = new Object[4];
        objArr[0] = super.toString();
        C0963h c0963h = this.f1716c;
        objArr[1] = c0963h == null ? "?.?.?.?:??" : c0963h.f1765f;
        objArr[2] = this.f1717d;
        objArr[3] = this.f1718e;
        return String.format("%s %s g=%s p=%s", objArr);
    }
}
