package p097l5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import p006a5.C0027m;
import p015b5.InterfaceC0466k;
import p097l5.InterfaceC1436a;
import p105m5.C1490a;
import p105m5.C1491b;
import p105m5.C1493d;
import p105m5.C1494e;
import p105m5.C1497h;
import p113n5.AbstractC1541b;
import p113n5.C1553n;
import p113n5.C1554o;
import p113n5.InterfaceC1561v;
import p131p5.C1743c;
import p131p5.C1747g;
import p168u5.InterfaceC1984e;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: SecurityHandler.java */
/* renamed from: l5.h */
/* loaded from: classes.dex */
public abstract class AbstractC1443h extends C1747g implements InterfaceC1436a.a {

    /* renamed from: s */
    public static final InterfaceC2016c f4189s;

    /* renamed from: k */
    public InterfaceC1436a f4190k;

    /* renamed from: m */
    public String f4192m;

    /* renamed from: o */
    public InterfaceC1441f f4194o;

    /* renamed from: p */
    public boolean f4195p;

    /* renamed from: q */
    public InterfaceC1440e f4196q;

    /* renamed from: l */
    public InterfaceC1436a.b f4191l = new C1439d();

    /* renamed from: n */
    public final Map<String, String> f4193n = new HashMap();

    /* renamed from: r */
    public boolean f4197r = true;

    /* compiled from: SecurityHandler.java */
    /* renamed from: l5.h$a */
    public class a implements InterfaceC0466k {
        public a(AbstractC1443h abstractC1443h) {
        }

        @Override // p015b5.InterfaceC0466k
        /* renamed from: g */
        public void mo196g(C0027m c0027m) {
            C1553n c1553n;
            AbstractC1541b abstractC1541bM1735g = AbstractC1541b.m1735g();
            if (abstractC1541bM1735g == null || (c1553n = abstractC1541bM1735g.f4548i) == null || !c1553n.mo33p()) {
                return;
            }
            c0027m.mo19a().mo187b("org.eclipse.jetty.security.sessionKnownOnlytoAuthenticated", Boolean.TRUE);
        }

        @Override // p015b5.InterfaceC0466k
        /* renamed from: k */
        public void mo197k(C0027m c0027m) {
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f4189s = C2015b.m2349a(AbstractC1443h.class.getName());
    }

    /* renamed from: S */
    public static AbstractC1443h m1629S() {
        C1743c.b bVarM1894W = C1743c.m1894W();
        if (bVarM1894W == null) {
            return null;
        }
        return (AbstractC1443h) C1743c.this.m1893O(AbstractC1443h.class);
    }

    /* renamed from: Q */
    public abstract boolean mo1612Q(String str, C1553n c1553n, C1554o c1554o, Object obj);

    /* renamed from: R */
    public abstract boolean mo1613R(String str, C1553n c1553n, C1554o c1554o, Object obj, InterfaceC1561v interfaceC1561v);

    /* renamed from: T */
    public abstract boolean mo1614T(C1553n c1553n, C1554o c1554o, Object obj);

    /* renamed from: U */
    public abstract Object mo1615U(String str, C1553n c1553n);

    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() {
        InterfaceC1436a.b bVar;
        C1743c.b bVarM1894W = C1743c.m1894W();
        if (bVarM1894W != null) {
            Enumeration enumeration = Collections.enumeration(C1743c.this.f4941q.keySet());
            while (enumeration != null && enumeration.hasMoreElements()) {
                String str = (String) enumeration.nextElement();
                if (str.startsWith("org.eclipse.jetty.security.") && this.f4193n.get(str) == null) {
                    String str2 = C1743c.this.f4941q.get(str);
                    if (isRunning()) {
                        throw new IllegalStateException("running");
                    }
                    this.f4193n.put(str, str2);
                }
            }
            C1743c.this.m1897T(new a(this));
        }
        InterfaceC1436a c1490a = null;
        if (this.f4194o == null) {
            ArrayList arrayList = (ArrayList) this.f4928h.m2308K(InterfaceC1441f.class);
            InterfaceC1441f interfaceC1441f = arrayList.size() == 1 ? (InterfaceC1441f) arrayList.get(0) : null;
            this.f4194o = interfaceC1441f;
            if (interfaceC1441f != null) {
                this.f4195p = true;
            }
        }
        if (this.f4196q == null) {
            InterfaceC1441f interfaceC1441f2 = this.f4194o;
            if (interfaceC1441f2 != null) {
                this.f4196q = interfaceC1441f2.m1622b();
            }
            if (this.f4196q == null) {
                this.f4196q = (InterfaceC1440e) this.f4928h.m2307J(InterfaceC1440e.class);
            }
            InterfaceC1440e interfaceC1440e = this.f4196q;
        }
        InterfaceC1441f interfaceC1441f3 = this.f4194o;
        if (interfaceC1441f3 != null) {
            if (interfaceC1441f3.m1622b() == null) {
                this.f4194o.m1625e(this.f4196q);
            } else if (this.f4194o.m1622b() != this.f4196q) {
                throw new IllegalStateException("LoginService has different IdentityService to " + this);
            }
        }
        if (!this.f4195p) {
            InterfaceC1441f interfaceC1441f4 = this.f4194o;
            if (interfaceC1441f4 instanceof InterfaceC1984e) {
                ((InterfaceC1984e) interfaceC1441f4).start();
            }
        }
        if (this.f4190k == null && (bVar = this.f4191l) != null && this.f4196q != null) {
            C1743c.m1894W();
            Objects.requireNonNull((C1439d) bVar);
            String str3 = this.f4192m;
            if (str3 == null || "BASIC".equalsIgnoreCase(str3)) {
                c1490a = new C1490a();
            } else if ("DIGEST".equalsIgnoreCase(str3)) {
                c1490a = new C1493d();
            } else if ("FORM".equalsIgnoreCase(str3)) {
                c1490a = new C1494e();
            } else if ("SPNEGO".equalsIgnoreCase(str3)) {
                c1490a = new C1497h();
            } else if ("NEGOTIATE".equalsIgnoreCase(str3)) {
                c1490a = new C1497h("NEGOTIATE");
            }
            if ("CLIENT_CERT".equalsIgnoreCase(str3) || "CLIENT-CERT".equalsIgnoreCase(str3)) {
                c1490a = new C1491b();
            }
            this.f4190k = c1490a;
            if (c1490a != null) {
                this.f4192m = c1490a.mo1608a();
            }
        }
        InterfaceC1436a interfaceC1436a = this.f4190k;
        if (interfaceC1436a != null) {
            interfaceC1436a.mo1610c(this);
            InterfaceC1436a interfaceC1436a2 = this.f4190k;
            if (interfaceC1436a2 instanceof InterfaceC1984e) {
                ((InterfaceC1984e) interfaceC1436a2).start();
            }
        }
        super.doStart();
    }

    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() {
        super.doStop();
        if (this.f4195p) {
            return;
        }
        InterfaceC1441f interfaceC1441f = this.f4194o;
        if (interfaceC1441f instanceof InterfaceC1984e) {
            ((InterfaceC1984e) interfaceC1441f).stop();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:137:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    @Override // p131p5.C1747g, p113n5.InterfaceC1548i
    /* renamed from: u */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo1630u(java.lang.String r20, p113n5.C1553n r21, p015b5.InterfaceC0458c r22, p015b5.InterfaceC0460e r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p097l5.AbstractC1443h.mo1630u(java.lang.String, n5.n, b5.c, b5.e):void");
    }
}
