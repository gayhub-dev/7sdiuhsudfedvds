package p105m5;

import com.ctvit.network.model.HttpHeaders;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import org.fourthline.cling.model.ServiceReference;
import p006a5.C0029o;
import p006a5.InterfaceC0023i;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p009b.C0413b;
import p015b5.C0459d;
import p015b5.C0461f;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p015b5.InterfaceC0462g;
import p097l5.AbstractC1443h;
import p097l5.C1444i;
import p097l5.C1445j;
import p097l5.InterfaceC1436a;
import p097l5.InterfaceC1441f;
import p113n5.AbstractC1541b;
import p113n5.C1547h;
import p113n5.C1553n;
import p113n5.InterfaceC1543d;
import p113n5.InterfaceC1561v;
import p161t5.C1926r;
import p161t5.C1928t;
import p161t5.ConcurrentMapC1920l;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: FormAuthenticator.java */
/* renamed from: m5.e */
/* loaded from: classes.dex */
public class C1494e extends AbstractC1495f {

    /* renamed from: i */
    public static final InterfaceC2016c f4267i;

    /* renamed from: d */
    public String f4268d;

    /* renamed from: e */
    public String f4269e;

    /* renamed from: f */
    public String f4270f;

    /* renamed from: g */
    public String f4271g;

    /* renamed from: h */
    public boolean f4272h;

    /* compiled from: FormAuthenticator.java */
    /* renamed from: m5.e$a */
    public static class a extends C1445j implements InterfaceC1543d.f {
        public a(String str, InterfaceC1561v interfaceC1561v) {
            super(str, interfaceC1561v);
        }

        @Override // p097l5.C1445j
        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("Form");
            sbM112a.append(super.toString());
            return sbM112a.toString();
        }
    }

    /* compiled from: FormAuthenticator.java */
    /* renamed from: m5.e$b */
    public static class b extends C0459d {
        public b(InterfaceC0458c interfaceC0458c) {
            super(interfaceC0458c);
        }

        @Override // p015b5.InterfaceC0458c
        /* renamed from: I */
        public long mo162I(String str) {
            if (str.toLowerCase(Locale.ENGLISH).startsWith("if-")) {
                return -1L;
            }
            return m174j0().mo162I(str);
        }

        @Override // p015b5.InterfaceC0458c
        /* renamed from: L */
        public Enumeration mo164L(String str) {
            return str.toLowerCase(Locale.ENGLISH).startsWith("if-") ? Collections.enumeration(Collections.EMPTY_LIST) : m174j0().mo164L(str);
        }

        @Override // p015b5.InterfaceC0458c
        /* renamed from: w */
        public Enumeration mo172w() {
            return Collections.enumeration(Collections.list(m174j0().mo172w()));
        }

        @Override // p015b5.InterfaceC0458c
        /* renamed from: x */
        public String mo173x(String str) {
            if (str.toLowerCase(Locale.ENGLISH).startsWith("if-")) {
                return null;
            }
            return m174j0().mo173x(str);
        }
    }

    /* compiled from: FormAuthenticator.java */
    /* renamed from: m5.e$c */
    public static class c extends C0461f {
        public c(InterfaceC0460e interfaceC0460e) {
            super(interfaceC0460e);
        }

        @Override // p015b5.C0461f, p015b5.InterfaceC0460e
        /* renamed from: J */
        public void mo177J(String str, String str2) {
            if (m1656k0(str)) {
                super.mo177J(str, str2);
            }
        }

        @Override // p015b5.C0461f, p015b5.InterfaceC0460e
        /* renamed from: k */
        public void mo180k(String str, long j7) {
            if (m1656k0(str)) {
                super.mo180k(str, j7);
            }
        }

        /* renamed from: k0 */
        public final boolean m1656k0(String str) {
            return ("Cache-Control".equalsIgnoreCase(str) || HttpHeaders.HEAD_KEY_PRAGMA.equalsIgnoreCase(str) || "ETag".equalsIgnoreCase(str) || "Expires".equalsIgnoreCase(str) || "Last-Modified".equalsIgnoreCase(str) || "Age".equalsIgnoreCase(str)) ? false : true;
        }

        @Override // p015b5.C0461f, p015b5.InterfaceC0460e
        /* renamed from: m */
        public void mo181m(String str, long j7) {
            if (m1656k0(str)) {
                super.mo181m(str, j7);
            }
        }

        @Override // p015b5.C0461f, p015b5.InterfaceC0460e
        /* renamed from: q */
        public void mo182q(String str, String str2) {
            if (m1656k0(str)) {
                super.mo182q(str, str2);
            }
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f4267i = C2015b.m2349a(C1494e.class.getName());
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: a */
    public String mo1608a() {
        return "FORM";
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: b */
    public boolean mo1609b(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6, InterfaceC1543d.g gVar) {
        return true;
    }

    @Override // p105m5.AbstractC1495f, p097l5.InterfaceC1436a
    /* renamed from: c */
    public void mo1610c(InterfaceC1436a.a aVar) {
        super.mo1610c(aVar);
        AbstractC1443h abstractC1443h = (AbstractC1443h) aVar;
        String str = abstractC1443h.f4193n.get("org.eclipse.jetty.security.form_login_page");
        if (str != null) {
            if (!str.startsWith(ServiceReference.DELIMITER)) {
                f4267i.mo2356g("form-login-page must start with /", new Object[0]);
                str = ServiceReference.DELIMITER + str;
            }
            this.f4270f = str;
            this.f4271g = str;
            if (str.indexOf(63) > 0) {
                String str2 = this.f4271g;
                this.f4271g = str2.substring(0, str2.indexOf(63));
            }
        }
        String str3 = abstractC1443h.f4193n.get("org.eclipse.jetty.security.form_error_page");
        if (str3 != null) {
            if (str3.trim().length() == 0) {
                this.f4269e = null;
                this.f4268d = null;
            } else {
                if (!str3.startsWith(ServiceReference.DELIMITER)) {
                    f4267i.mo2356g("form-error-page must start with /", new Object[0]);
                    str3 = ServiceReference.DELIMITER + str3;
                }
                this.f4268d = str3;
                this.f4269e = str3;
                if (str3.indexOf(63) > 0) {
                    String str4 = this.f4269e;
                    this.f4269e = str4.substring(0, str4.indexOf(63));
                }
            }
        }
        String str5 = abstractC1443h.f4193n.get("org.eclipse.jetty.security.dispatch");
        this.f4272h = str5 == null ? this.f4272h : Boolean.valueOf(str5).booleanValue();
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: d */
    public InterfaceC1543d mo1611d(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6) throws C1444i {
        InterfaceC1441f interfaceC1441f;
        String strMo168d;
        InterfaceC0458c interfaceC0458c = (InterfaceC0458c) interfaceC0032r;
        InterfaceC0460e interfaceC0460e = (InterfaceC0460e) interfaceC0037w;
        String strMo167U = interfaceC0458c.mo167U();
        if (strMo167U == null) {
            strMo167U = ServiceReference.DELIMITER;
        }
        if (!z6 && !m1655f(strMo167U)) {
            return new C1492c(this);
        }
        String strM2264b = C1928t.m2264b(interfaceC0458c.mo160B(), interfaceC0458c.mo161G());
        if ((strM2264b != null && (strM2264b.equals(this.f4269e) || strM2264b.equals(this.f4271g))) && !C1492c.m1651b(interfaceC0460e)) {
            return new C1492c(this);
        }
        InterfaceC0462g interfaceC0462gMo171t = interfaceC0458c.mo171t(true);
        try {
            if (m1655f(strMo167U)) {
                String strMo34r = interfaceC0458c.mo34r("j_username");
                InterfaceC1561v interfaceC1561vMo1654e = mo1654e(strMo34r, interfaceC0458c.mo34r("j_password"), interfaceC0458c);
                InterfaceC0462g interfaceC0462gMo171t2 = interfaceC0458c.mo171t(true);
                if (interfaceC1561vMo1654e != null) {
                    synchronized (interfaceC0462gMo171t2) {
                        strMo168d = (String) interfaceC0462gMo171t2.mo186a("org.eclipse.jetty.security.form_URI");
                        if (strMo168d == null || strMo168d.length() == 0) {
                            strMo168d = interfaceC0458c.mo168d();
                            if (strMo168d.length() == 0) {
                                strMo168d = ServiceReference.DELIMITER;
                            }
                        }
                    }
                    interfaceC0460e.mo42H(0);
                    interfaceC0460e.mo179T(interfaceC0460e.mo184z(strMo168d));
                    return new a("FORM", interfaceC1561vMo1654e);
                }
                InterfaceC2016c interfaceC2016c = f4267i;
                if (interfaceC2016c.mo2353d()) {
                    interfaceC2016c.mo2351a("Form authentication FAILED for " + C1926r.m2255e(strMo34r), new Object[0]);
                }
                String str = this.f4268d;
                if (str == null) {
                    if (interfaceC0460e != null) {
                        interfaceC0460e.mo183v(403);
                    }
                } else if (this.f4272h) {
                    InterfaceC0023i interfaceC0023iMo30f = interfaceC0458c.mo30f(str);
                    interfaceC0460e.mo177J("Cache-Control", "No-cache");
                    interfaceC0460e.mo181m("Expires", 1L);
                    ((C1547h) interfaceC0023iMo30f).m1770a(new b(interfaceC0458c), new c(interfaceC0460e), 1);
                } else {
                    interfaceC0460e.mo179T(interfaceC0460e.mo184z(C1928t.m2264b(interfaceC0458c.mo168d(), this.f4268d)));
                }
                return InterfaceC1543d.f4586d;
            }
            InterfaceC1543d interfaceC1543d = (InterfaceC1543d) interfaceC0462gMo171t.mo186a("org.eclipse.jetty.security.UserIdentity");
            if (interfaceC1543d != null) {
                if (!(interfaceC1543d instanceof InterfaceC1543d.g) || (interfaceC1441f = this.f4273a) == null || interfaceC1441f.m1623c(((InterfaceC1543d.g) interfaceC1543d).mo1632h())) {
                    String str2 = (String) interfaceC0462gMo171t.mo186a("org.eclipse.jetty.security.form_URI");
                    if (str2 != null) {
                        ConcurrentMapC1920l<String> concurrentMapC1920l = (ConcurrentMapC1920l) interfaceC0462gMo171t.mo186a("org.eclipse.jetty.security.form_POST");
                        if (concurrentMapC1920l != null) {
                            StringBuffer stringBufferMo170n = interfaceC0458c.mo170n();
                            if (interfaceC0458c.mo166O() != null) {
                                stringBufferMo170n.append("?");
                                stringBufferMo170n.append(interfaceC0458c.mo166O());
                            }
                            if (str2.equals(stringBufferMo170n.toString())) {
                                interfaceC0462gMo171t.mo188e("org.eclipse.jetty.security.form_POST");
                                C1553n c1553n = interfaceC0032r instanceof C1553n ? (C1553n) interfaceC0032r : AbstractC1541b.m1735g().f4548i;
                                c1553n.f4649r = "POST";
                                c1553n.m1779C(concurrentMapC1920l);
                            }
                        } else {
                            interfaceC0462gMo171t.mo188e("org.eclipse.jetty.security.form_URI");
                        }
                    }
                    return interfaceC1543d;
                }
                interfaceC0462gMo171t.mo188e("org.eclipse.jetty.security.UserIdentity");
            }
            if (C1492c.m1651b(interfaceC0460e)) {
                f4267i.mo2351a("auth deferred {}", interfaceC0462gMo171t.getId());
                return InterfaceC1543d.f4583a;
            }
            synchronized (interfaceC0462gMo171t) {
                if (interfaceC0462gMo171t.mo186a("org.eclipse.jetty.security.form_URI") == null) {
                    StringBuffer stringBufferMo170n2 = interfaceC0458c.mo170n();
                    if (interfaceC0458c.mo166O() != null) {
                        stringBufferMo170n2.append("?");
                        stringBufferMo170n2.append(interfaceC0458c.mo166O());
                    }
                    interfaceC0462gMo171t.mo187b("org.eclipse.jetty.security.form_URI", stringBufferMo170n2.toString());
                    if ("application/x-www-form-urlencoded".equalsIgnoreCase(interfaceC0032r.getContentType()) && "POST".equals(interfaceC0458c.mo165N())) {
                        C1553n c1553n2 = interfaceC0032r instanceof C1553n ? (C1553n) interfaceC0032r : AbstractC1541b.m1735g().f4548i;
                        c1553n2.m1784j();
                        interfaceC0462gMo171t.mo187b("org.eclipse.jetty.security.form_POST", new ConcurrentMapC1920l(c1553n2.f4650s));
                    }
                }
            }
            if (this.f4272h) {
                InterfaceC0023i interfaceC0023iMo30f2 = interfaceC0458c.mo30f(this.f4270f);
                interfaceC0460e.mo177J("Cache-Control", "No-cache");
                interfaceC0460e.mo181m("Expires", 1L);
                ((C1547h) interfaceC0023iMo30f2).m1770a(new b(interfaceC0458c), new c(interfaceC0460e), 1);
            } else {
                interfaceC0460e.mo179T(interfaceC0460e.mo184z(C1928t.m2264b(interfaceC0458c.mo168d(), this.f4270f)));
            }
            return InterfaceC1543d.f4585c;
        } catch (C0029o e7) {
            throw new C1444i(e7);
        } catch (IOException e8) {
            throw new C1444i(e8);
        }
    }

    @Override // p105m5.AbstractC1495f
    /* renamed from: e */
    public InterfaceC1561v mo1654e(String str, Object obj, InterfaceC0032r interfaceC0032r) {
        InterfaceC1561v interfaceC1561vMo1654e = super.mo1654e(str, obj, interfaceC0032r);
        if (interfaceC1561vMo1654e != null) {
            ((InterfaceC0458c) interfaceC0032r).mo171t(true).mo187b("org.eclipse.jetty.security.UserIdentity", new C1496g("FORM", interfaceC1561vMo1654e, obj));
        }
        return interfaceC1561vMo1654e;
    }

    /* renamed from: f */
    public boolean m1655f(String str) {
        char cCharAt;
        int iIndexOf = str.indexOf("/j_security_check");
        if (iIndexOf < 0) {
            return false;
        }
        int i7 = iIndexOf + 17;
        return i7 == str.length() || (cCharAt = str.charAt(i7)) == ';' || cCharAt == '#' || cCharAt == '/' || cCharAt == '?';
    }
}
