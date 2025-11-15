package p113n5;

import java.lang.reflect.Array;
import java.util.Properties;
import p007a6.ExecutorC0042b;
import p007a6.InterfaceC0044d;
import p009b.C0413b;
import p015b5.InterfaceC0458c;
import p065h5.C1105m;
import p065h5.C1113u;
import p113n5.C1542c;
import p113n5.C1560u;
import p113n5.C1560u.c;
import p131p5.C1747g;
import p161t5.C1910b;
import p161t5.C1918j;
import p161t5.C1919k;
import p161t5.C1926r;
import p161t5.C1928t;
import p161t5.InterfaceC1909a;
import p168u5.C1982c;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: Server.java */
/* renamed from: n5.p */
/* loaded from: classes.dex */
public class C1555p extends C1747g implements InterfaceC1909a {

    /* renamed from: r */
    public static final InterfaceC2016c f4668r;

    /* renamed from: s */
    public static final String f4669s;

    /* renamed from: m */
    public InterfaceC0044d f4672m;

    /* renamed from: n */
    public InterfaceC1545f[] f4673n;

    /* renamed from: o */
    public InterfaceC1558s f4674o;

    /* renamed from: k */
    public final C1982c f4670k = new C1982c();

    /* renamed from: l */
    public final C1910b f4671l = new C1910b();

    /* renamed from: p */
    public boolean f4675p = true;

    /* renamed from: q */
    public int f4676q = 0;

    /* compiled from: Server.java */
    /* renamed from: n5.p$a */
    public interface a extends InterfaceC1548i {
        /* renamed from: B */
        void mo1805B(boolean z6);
    }

    static {
        Properties properties = C2015b.f5863a;
        f4668r = C2015b.m2349a(C1555p.class.getName());
        f4669s = (C1555p.class.getPackage() == null || !"Eclipse.org - Jetty".equals(C1555p.class.getPackage().getImplementationVendor()) || C1555p.class.getPackage().getImplementationVersion() == null) ? System.getProperty("jetty.version", "8.y.z-SNAPSHOT") : C1555p.class.getPackage().getImplementationVersion();
    }

    public C1555p() {
        mo1772h(this);
    }

    @Override // p168u5.C1981b
    /* renamed from: G */
    public boolean mo1798G(Object obj) {
        if (!super.mo1798G(obj)) {
            return false;
        }
        this.f4670k.m2310b(obj);
        return true;
    }

    @Override // p168u5.C1981b
    /* renamed from: L */
    public boolean mo1799L(Object obj) {
        if (!super.mo1799L(obj)) {
            return false;
        }
        this.f4670k.m2312d(obj);
        return true;
    }

    /* renamed from: Q */
    public void m1800Q(AbstractC1541b abstractC1541b) {
        C1553n c1553n = abstractC1541b.f4548i;
        String str = c1553n.f4652u;
        C1554o c1554o = abstractC1541b.f4552m;
        InterfaceC2016c interfaceC2016c = f4668r;
        if (!interfaceC2016c.mo2353d()) {
            mo1630u(str, c1553n, c1553n, c1554o);
            return;
        }
        interfaceC2016c.mo2351a("REQUEST " + str + " on " + abstractC1541b, new Object[0]);
        mo1630u(str, c1553n, c1553n, c1554o);
        interfaceC2016c.mo2351a("RESPONSE " + str + "  " + abstractC1541b.f4552m.f4660b + " handled=" + c1553n.f4647p, new Object[0]);
    }

    /* renamed from: R */
    public void m1801R(AbstractC1541b abstractC1541b) {
        C1542c.a aVar;
        C1542c c1542c = abstractC1541b.f4548i.f4632a;
        synchronized (c1542c) {
            aVar = c1542c.f4576i;
        }
        C1553n c1553n = abstractC1541b.f4548i;
        String str = aVar.f4580f;
        if (str != null) {
            C1113u c1113u = new C1113u(C1928t.m2264b(aVar.m1764a().mo16d(), str));
            c1553n.f4630M = c1113u;
            c1553n.f4621D = null;
            c1553n.f4652u = c1553n.mo167U();
            if (c1113u.mo1207i() != null) {
                c1553n.m1789s(c1113u.mo1207i());
            }
        }
        String str2 = c1553n.f4652u;
        C1542c.a aVar2 = c1542c.f4576i;
        InterfaceC0458c interfaceC0458c = (InterfaceC0458c) (aVar2 != null ? aVar2.f27a : c1542c.f4568a.f4548i);
        C1554o c1554o = c1542c.f4568a.f4552m;
        InterfaceC2016c interfaceC2016c = f4668r;
        if (!interfaceC2016c.mo2353d()) {
            mo1630u(str2, c1553n, interfaceC0458c, c1554o);
            return;
        }
        interfaceC2016c.mo2351a("REQUEST " + str2 + " on " + abstractC1541b, new Object[0]);
        mo1630u(str2, c1553n, interfaceC0458c, c1554o);
        interfaceC2016c.mo2351a("RESPONSE " + str2 + "  " + abstractC1541b.f4552m.f4660b, new Object[0]);
    }

    /* renamed from: S */
    public void m1802S(InterfaceC1545f interfaceC1545f) {
        Object[] objArr = this.f4673n;
        if (objArr != null) {
            int length = objArr.length;
            while (true) {
                int i7 = length - 1;
                if (length <= 0) {
                    break;
                }
                if (interfaceC1545f.equals(objArr[i7])) {
                    Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), Array.getLength(objArr) - 1);
                    if (i7 > 0) {
                        System.arraycopy(objArr, 0, objArr2, 0, i7);
                    }
                    int i8 = i7 + 1;
                    if (i8 < objArr.length) {
                        System.arraycopy(objArr, i8, objArr2, i7, objArr.length - i8);
                    }
                    objArr = objArr2;
                } else {
                    length = i7;
                }
            }
        }
        m1803T((InterfaceC1545f[]) objArr);
    }

    /* renamed from: T */
    public void m1803T(InterfaceC1545f[] interfaceC1545fArr) {
        if (interfaceC1545fArr != null) {
            for (InterfaceC1545f interfaceC1545f : interfaceC1545fArr) {
                interfaceC1545f.mo1725h(this);
            }
        }
        this.f4670k.m2315g(this, this.f4673n, interfaceC1545fArr, "connector");
        this.f4673n = interfaceC1545fArr;
    }

    /* renamed from: U */
    public void m1804U(InterfaceC0044d interfaceC0044d) {
        InterfaceC0044d interfaceC0044d2 = this.f4672m;
        if (interfaceC0044d2 != null) {
            mo1799L(interfaceC0044d2);
        }
        this.f4670k.m2314f(this, this.f4672m, interfaceC0044d, "threadpool", false);
        this.f4672m = interfaceC0044d;
        mo1798G(interfaceC0044d);
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: a */
    public Object mo892a(String str) {
        return this.f4671l.f5612e.get(str);
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: b */
    public void mo893b(String str, Object obj) {
        C1910b c1910b = this.f4671l;
        if (obj == null) {
            c1910b.f5612e.remove(str);
        } else {
            c1910b.f5612e.put(str, obj);
        }
    }

    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() throws Exception {
        int i7;
        C1560u c1560u = C1560u.b.f4683a;
        synchronized (c1560u) {
            C1560u.c cVar = c1560u.f4682f;
            i7 = 0;
            if (cVar == null || !cVar.isAlive()) {
                C1560u.c cVar2 = c1560u.new c();
                c1560u.f4682f = cVar2;
                cVar2.start();
            } else {
                System.err.printf("ShutdownMonitorThread already started", new Object[0]);
            }
        }
        InterfaceC2016c interfaceC2016c = f4668r;
        StringBuilder sbM112a = C0413b.m112a("jetty-");
        String str = f4669s;
        sbM112a.append(str);
        interfaceC2016c.mo2357h(sbM112a.toString(), new Object[0]);
        InterfaceC2016c interfaceC2016c2 = C1105m.f2315y;
        C1105m.f2314H = C1926r.m2253c("Server: Jetty(" + str + ")\r\n");
        C1919k c1919k = new C1919k();
        if (this.f4672m == null) {
            m1804U(new ExecutorC0042b());
        }
        try {
            super.doStart();
        } catch (Throwable th) {
            c1919k.m2229a(th);
        }
        if (this.f4673n != null && C1918j.m2228x(c1919k.f5649e) == 0) {
            while (true) {
                InterfaceC1545f[] interfaceC1545fArr = this.f4673n;
                if (i7 >= interfaceC1545fArr.length) {
                    break;
                }
                try {
                    interfaceC1545fArr[i7].start();
                } catch (Throwable th2) {
                    c1919k.m2229a(th2);
                }
                i7++;
            }
        }
        c1919k.m2230b();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|(6:4|(2:6|(2:7|(5:9|38|10|44|14)(1:42)))(0)|15|(2:18|16)|45|19)|20|(5:22|(2:23|(4:40|25|47|29)(0))|30|34|35)(0)|36|30|34|35) */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0076, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0077, code lost:
    
        r0.m2229a(r1);
     */
    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doStop() throws java.lang.Exception {
        /*
            r9 = this;
            t5.k r0 = new t5.k
            r0.<init>()
            int r1 = r9.f4676q
            if (r1 <= 0) goto L5b
            n5.f[] r1 = r9.f4673n
            java.lang.String r2 = "Graceful shutdown {}"
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L31
            int r1 = r1.length
        L12:
            int r5 = r1 + (-1)
            if (r1 <= 0) goto L31
            v5.c r1 = p113n5.C1555p.f4668r
            java.lang.Object[] r6 = new java.lang.Object[r3]
            n5.f[] r7 = r9.f4673n
            r7 = r7[r5]
            r6[r4] = r7
            r1.mo2357h(r2, r6)
            n5.f[] r1 = r9.f4673n     // Catch: java.lang.Throwable -> L2b
            r1 = r1[r5]     // Catch: java.lang.Throwable -> L2b
            r1.close()     // Catch: java.lang.Throwable -> L2b
            goto L2f
        L2b:
            r1 = move-exception
            r0.m2229a(r1)
        L2f:
            r1 = r5
            goto L12
        L31:
            java.lang.Class<n5.p$a> r1 = p113n5.C1555p.a.class
            r5 = 0
            java.lang.Object r5 = r9.mo1891M(r5, r1)
            java.lang.Object r1 = p161t5.C1918j.m2221I(r5, r1)
            n5.i[] r1 = (p113n5.InterfaceC1548i[]) r1
            r5 = 0
        L3f:
            int r6 = r1.length
            if (r5 >= r6) goto L55
            r6 = r1[r5]
            n5.p$a r6 = (p113n5.C1555p.a) r6
            v5.c r7 = p113n5.C1555p.f4668r
            java.lang.Object[] r8 = new java.lang.Object[r3]
            r8[r4] = r6
            r7.mo2357h(r2, r8)
            r6.mo1805B(r3)
            int r5 = r5 + 1
            goto L3f
        L55:
            int r1 = r9.f4676q
            long r1 = (long) r1
            java.lang.Thread.sleep(r1)
        L5b:
            n5.f[] r1 = r9.f4673n
            if (r1 == 0) goto L72
            int r1 = r1.length
        L60:
            int r2 = r1 + (-1)
            if (r1 <= 0) goto L72
            n5.f[] r1 = r9.f4673n     // Catch: java.lang.Throwable -> L6c
            r1 = r1[r2]     // Catch: java.lang.Throwable -> L6c
            r1.stop()     // Catch: java.lang.Throwable -> L6c
            goto L70
        L6c:
            r1 = move-exception
            r0.m2229a(r1)
        L70:
            r1 = r2
            goto L60
        L72:
            super.doStop()     // Catch: java.lang.Throwable -> L76
            goto L7a
        L76:
            r1 = move-exception
            r0.m2229a(r1)
        L7a:
            r0.m2230b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p113n5.C1555p.doStop():void");
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: e */
    public void mo894e(String str) {
        this.f4671l.f5612e.remove(str);
    }

    public String toString() {
        return C1555p.class.getName() + "@" + Integer.toHexString(hashCode());
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: v */
    public void mo896v() {
        this.f4671l.f5612e.clear();
    }
}
