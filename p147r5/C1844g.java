package p147r5;

import java.util.EnumSet;
import p006a5.EnumC0039y;
import p015b5.C0456a;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p015b5.InterfaceC0462g;
import p043f.C0986c;
import p113n5.C1553n;
import p113n5.C1555p;
import p113n5.InterfaceC1548i;
import p113n5.InterfaceC1559t;
import p131p5.AbstractC1748h;
import p168u5.AbstractC1980a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: SessionHandler.java */
/* renamed from: r5.g */
/* loaded from: classes.dex */
public class C1844g extends AbstractC1748h {

    /* renamed from: o */
    public static final InterfaceC2016c f5397o = C2015b.m2349a("org.eclipse.jetty.server.session");

    /* renamed from: n */
    public InterfaceC1559t f5398n;

    static {
        EnumSet.of(EnumC0039y.COOKIE, EnumC0039y.URL);
    }

    public C1844g() {
        C1842e c1842e = new C1842e();
        if (isStarted()) {
            throw new IllegalStateException();
        }
        InterfaceC1559t interfaceC1559t = this.f5398n;
        C1555p c1555p = this.f4928h;
        if (c1555p != null) {
            c1555p.f4670k.m2314f(this, interfaceC1559t, c1842e, "sessionManager", true);
        }
        c1842e.f5363h = this;
        this.f5398n = c1842e;
        if (interfaceC1559t != null) {
            ((AbstractC1840c) interfaceC1559t).f5363h = null;
        }
    }

    @Override // p131p5.AbstractC1748h
    /* renamed from: Q */
    public void mo1895Q(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        AbstractC1748h abstractC1748h = this.f4963l;
        if (abstractC1748h != null && abstractC1748h == this.f4960j) {
            abstractC1748h.mo1895Q(str, c1553n, interfaceC0458c, interfaceC0460e);
            return;
        }
        InterfaceC1548i interfaceC1548i = this.f4960j;
        if (interfaceC1548i != null) {
            interfaceC1548i.mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x003a  */
    @Override // p131p5.AbstractC1748h
    /* renamed from: R */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo1896R(java.lang.String r10, p113n5.C1553n r11, p015b5.InterfaceC0458c r12, p015b5.InterfaceC0460e r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p147r5.C1844g.mo1896R(java.lang.String, n5.n, b5.c, b5.e):void");
    }

    /* renamed from: T */
    public void m2109T(C1553n c1553n, InterfaceC0458c interfaceC0458c) {
        boolean z6;
        int iIndexOf;
        char cCharAt;
        C0456a[] c0456aArrMo169h;
        String strMo163K = interfaceC0458c.mo163K();
        InterfaceC1559t interfaceC1559t = this.f5398n;
        if (strMo163K != null && interfaceC1559t != null) {
            AbstractC1840c abstractC1840c = (AbstractC1840c) interfaceC1559t;
            InterfaceC0462g interfaceC0462gM2100L = abstractC1840c.m2100L(strMo163K);
            if (interfaceC0462gM2100L == null || !abstractC1840c.m2102N(interfaceC0462gM2100L)) {
                return;
            }
            c1553n.f4626I = interfaceC0462gM2100L;
            return;
        }
        if (C0986c.m949b(3, c1553n.f4644m)) {
            InterfaceC0462g interfaceC0462gM2100L2 = null;
            boolean z7 = false;
            if (!((AbstractC1840c) this.f5398n).f5361f || (c0456aArrMo169h = interfaceC0458c.mo169h()) == null || c0456aArrMo169h.length <= 0) {
                z6 = false;
            } else {
                AbstractC1840c abstractC1840c2 = (AbstractC1840c) interfaceC1559t;
                String str = AbstractC1840c.this.f5370o;
                int i7 = 0;
                z6 = false;
                while (true) {
                    if (i7 >= c0456aArrMo169h.length) {
                        break;
                    }
                    if (str.equalsIgnoreCase(c0456aArrMo169h[i7].f276e)) {
                        strMo163K = c0456aArrMo169h[i7].f277f;
                        InterfaceC2016c interfaceC2016c = f5397o;
                        interfaceC2016c.mo2351a("Got Session ID {} from cookie", strMo163K);
                        if (strMo163K != null) {
                            interfaceC0462gM2100L2 = abstractC1840c2.m2100L(strMo163K);
                            if (interfaceC0462gM2100L2 != null && abstractC1840c2.m2102N(interfaceC0462gM2100L2)) {
                                z6 = true;
                                break;
                            }
                        } else {
                            interfaceC2016c.mo2356g("null session id from cookie", new Object[0]);
                        }
                        z6 = true;
                    }
                    i7++;
                }
            }
            if (strMo163K == null || interfaceC0462gM2100L2 == null) {
                String strMo167U = interfaceC0458c.mo167U();
                AbstractC1840c abstractC1840c3 = (AbstractC1840c) interfaceC1559t;
                String str2 = abstractC1840c3.f5372q;
                if (str2 != null && (iIndexOf = strMo167U.indexOf(str2)) >= 0) {
                    int length = str2.length() + iIndexOf;
                    int i8 = length;
                    while (i8 < strMo167U.length() && (cCharAt = strMo167U.charAt(i8)) != ';' && cCharAt != '#' && cCharAt != '?' && cCharAt != '/') {
                        i8++;
                    }
                    strMo163K = strMo167U.substring(length, i8);
                    interfaceC0462gM2100L2 = abstractC1840c3.m2100L(strMo163K);
                    InterfaceC2016c interfaceC2016c2 = f5397o;
                    if (interfaceC2016c2.mo2353d()) {
                        interfaceC2016c2.mo2351a("Got Session ID {} from URL", strMo163K);
                    }
                    z6 = false;
                }
            }
            c1553n.f4619B = strMo163K;
            if (strMo163K != null && z6) {
                z7 = true;
            }
            c1553n.f4620C = z7;
            if (interfaceC0462gM2100L2 == null || !((AbstractC1840c) interfaceC1559t).m2102N(interfaceC0462gM2100L2)) {
                return;
            }
            c1553n.f4626I = interfaceC0462gM2100L2;
        }
    }

    @Override // p131p5.AbstractC1748h, p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() {
        ((AbstractC1980a) this.f5398n).start();
        super.doStart();
    }

    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() {
        ((AbstractC1980a) this.f5398n).stop();
        super.doStop();
    }

    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p113n5.InterfaceC1548i
    /* renamed from: h */
    public void mo1772h(C1555p c1555p) {
        C1555p c1555p2 = this.f4928h;
        if (c1555p2 != null && c1555p2 != c1555p) {
            c1555p2.f4670k.m2314f(this, this.f5398n, null, "sessionManager", true);
        }
        super.mo1772h(c1555p);
        if (c1555p == null || c1555p == c1555p2) {
            return;
        }
        c1555p.f4670k.m2314f(this, null, this.f5398n, "sessionManager", true);
    }
}
