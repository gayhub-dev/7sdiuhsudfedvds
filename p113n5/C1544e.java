package p113n5;

import java.util.Properties;
import p065h5.AbstractC1093a;
import p065h5.C1100h;
import p065h5.C1105m;
import p065h5.C1109q;
import p073i5.InterfaceC1160m;
import p073i5.InterfaceC1161n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: BlockingHttpConnection.java */
/* renamed from: n5.e */
/* loaded from: classes.dex */
public class C1544e extends AbstractC1541b {

    /* renamed from: B */
    public static final InterfaceC2016c f4587B;

    static {
        Properties properties = C2015b.f5863a;
        f4587B = C2015b.m2349a(C1544e.class.getName());
    }

    public C1544e(InterfaceC1545f interfaceC1545f, InterfaceC1161n interfaceC1161n, C1555p c1555p) {
        super(interfaceC1545f, interfaceC1161n, c1555p);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v23, types: [i5.m] */
    /* JADX WARN: Type inference failed for: r4v43, types: [i5.m] */
    @Override // p073i5.InterfaceC1160m
    /* renamed from: e */
    public InterfaceC1160m mo887e() {
        InterfaceC1161n interfaceC1161n;
        ?? r42;
        ?? r43;
        try {
            AbstractC1541b.f4540A.set(this);
            C1544e c1544e = this;
            while (this.f2538a.isOpen() && c1544e == this) {
                try {
                    try {
                        if (!((C1109q) this.f4546g).m1243c() && !this.f2538a.mo926s()) {
                            ((C1109q) this.f4546g).m1247g();
                        }
                        if (((AbstractC1093a) this.f4550k).m1187g() && !((AbstractC1093a) this.f4550k).m1188h() && !this.f2538a.mo925r()) {
                            ((C1105m) this.f4550k).mo1184d();
                        }
                        this.f2538a.flush();
                        if (((C1109q) this.f4546g).m1243c() && ((AbstractC1093a) this.f4550k).m1188h()) {
                            m1740k();
                            if (this.f4552m.f4660b == 101 && (r43 = (InterfaceC1160m) this.f4548i.mo26a("org.eclipse.jetty.io.Connection")) != 0) {
                                c1544e = r43;
                            }
                            if (!((AbstractC1093a) this.f4550k).m1191k() && !this.f2538a.mo925r()) {
                                f4587B.mo2356g("Safety net oshut!!! Please open a bugzilla", new Object[0]);
                                this.f2538a.mo927t();
                            }
                        }
                    } finally {
                    }
                } catch (C1100h e7) {
                    InterfaceC2016c interfaceC2016c = f4587B;
                    if (interfaceC2016c.mo2353d()) {
                        interfaceC2016c.mo2351a("uri=" + this.f4545f, new Object[0]);
                        interfaceC2016c.mo2351a("fields=" + this.f4547h, new Object[0]);
                        interfaceC2016c.mo2359j(e7);
                    }
                    ((AbstractC1093a) this.f4550k).m1195o(e7.f2282e, e7.f2283f, null, true);
                    ((C1109q) this.f4546g).m1249i();
                    this.f2538a.mo927t();
                    if (((C1109q) this.f4546g).m1243c() && ((AbstractC1093a) this.f4550k).m1188h()) {
                        m1740k();
                        if (this.f4552m.f4660b == 101 && (r42 = (InterfaceC1160m) this.f4548i.mo26a("org.eclipse.jetty.io.Connection")) != 0) {
                            c1544e = r42;
                        }
                        if (!((AbstractC1093a) this.f4550k).m1191k() && !this.f2538a.mo925r()) {
                            interfaceC2016c.mo2356g("Safety net oshut!!! Please open a bugzilla", new Object[0]);
                            this.f2538a.mo927t();
                        }
                    }
                    if (this.f2538a.mo926s() && ((AbstractC1093a) this.f4550k).m1189i() && !this.f4548i.f4632a.m1759q()) {
                        interfaceC1161n = this.f2538a;
                    }
                }
                if (this.f2538a.mo926s() && ((AbstractC1093a) this.f4550k).m1189i() && !this.f4548i.f4632a.m1759q()) {
                    interfaceC1161n = this.f2538a;
                    interfaceC1161n.close();
                }
            }
            AbstractC1541b.f4540A.set(null);
            ((C1109q) this.f4546g).m1250j();
            ((AbstractC1093a) this.f4550k).m1194n();
            return c1544e;
        } catch (Throwable th) {
            AbstractC1541b.f4540A.set(null);
            ((C1109q) this.f4546g).m1250j();
            ((AbstractC1093a) this.f4550k).m1194n();
            throw th;
        }
    }

    @Override // p113n5.AbstractC1541b
    /* renamed from: j */
    public void mo1739j() throws Throwable {
        super.mo1739j();
    }
}
