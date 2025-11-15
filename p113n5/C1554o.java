package p113n5;

import android.support.constraint.motion.C0080b;
import com.ctvit.network.model.HttpHeaders;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;
import org.fourthline.cling.model.ServiceReference;
import p006a5.AbstractC0030p;
import p009b.C0413b;
import p015b5.InterfaceC0460e;
import p065h5.AbstractC1093a;
import p065h5.C1099g;
import p065h5.C1100h;
import p065h5.C1101i;
import p065h5.C1105m;
import p065h5.C1106n;
import p065h5.C1107o;
import p065h5.C1111s;
import p065h5.C1115w;
import p065h5.InterfaceC1095c;
import p073i5.C1153f;
import p073i5.C1155h;
import p073i5.C1158k;
import p073i5.InterfaceC1152e;
import p113n5.AbstractC1541b;
import p113n5.AbstractC1541b.b;
import p131p5.C1743c;
import p131p5.C1745e;
import p154s5.C1886c;
import p161t5.C1913e;
import p161t5.C1923o;
import p161t5.C1926r;
import p161t5.C1928t;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: Response.java */
/* renamed from: n5.o */
/* loaded from: classes.dex */
public class C1554o implements InterfaceC0460e {

    /* renamed from: j */
    public static final InterfaceC2016c f4658j;

    /* renamed from: a */
    public final AbstractC1541b f4659a;

    /* renamed from: b */
    public int f4660b = 200;

    /* renamed from: c */
    public String f4661c;

    /* renamed from: d */
    public String f4662d;

    /* renamed from: e */
    public C1153f.a f4663e;

    /* renamed from: f */
    public String f4664f;

    /* renamed from: g */
    public String f4665g;

    /* renamed from: h */
    public volatile int f4666h;

    /* renamed from: i */
    public PrintWriter f4667i;

    static {
        Properties properties = C2015b.f5863a;
        f4658j = C2015b.m2349a(C1554o.class.getName());
    }

    public C1554o(AbstractC1541b abstractC1541b) {
        this.f4659a = abstractC1541b;
    }

    @Override // p015b5.InterfaceC0460e
    /* renamed from: A */
    public void mo175A(int i7, String str) throws IOException {
        Objects.requireNonNull(this.f4659a);
        if (mo44P()) {
            f4658j.mo2356g("Committed before " + i7 + " " + str, new Object[0]);
        }
        mo47o();
        this.f4664f = null;
        mo177J("Expires", null);
        mo177J("Last-Modified", null);
        mo177J("Cache-Control", null);
        mo177J("Content-Type", null);
        mo177J("Content-Length", null);
        this.f4666h = 0;
        m1797e(i7, str);
        if (str == null) {
            str = C1111s.m1251a(i7);
        }
        if (i7 != 204 && i7 != 304 && i7 != 206 && i7 >= 200) {
            AbstractC1541b abstractC1541b = this.f4659a;
            C1553n c1553n = abstractC1541b.f4548i;
            C1743c.b bVar = c1553n.f4639h;
            C1745e c1745e = bVar != null ? C1743c.this.f4944t : null;
            if (c1745e == null) {
                c1745e = (C1745e) abstractC1541b.f4543d.mo1724c().m2307J(C1745e.class);
            }
            if (c1745e != null) {
                c1553n.mo27b("javax.servlet.error.status_code", new Integer(i7));
                c1553n.mo27b("javax.servlet.error.message", str);
                c1553n.mo27b("javax.servlet.error.request_uri", c1553n.mo167U());
                Object obj = c1553n.f4623F;
                c1553n.mo27b("javax.servlet.error.servlet_name", obj != null ? ((C1886c) obj).f5493k : null);
                C1553n c1553n2 = this.f4659a.f4548i;
                c1745e.mo1630u(null, c1553n2, c1553n2, this);
            } else {
                mo177J("Cache-Control", "must-revalidate,no-cache,no-store");
                mo48y("text/html;charset=ISO-8859-1");
                C1913e c1913e = new C1913e(2048);
                if (str != null) {
                    str = C1926r.m2256f(C1926r.m2256f(C1926r.m2256f(str, "&", "&amp;"), "<", "&lt;"), ">", "&gt;");
                }
                String strMo167U = c1553n.mo167U();
                if (strMo167U != null) {
                    strMo167U = C1926r.m2256f(C1926r.m2256f(C1926r.m2256f(strMo167U, "&", "&amp;"), "<", "&lt;"), ">", "&gt;");
                }
                c1913e.write("<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html;charset=ISO-8859-1\"/>\n");
                c1913e.write("<title>Error ");
                c1913e.write(Integer.toString(i7));
                c1913e.m2210a(1);
                byte[] bArr = c1913e.f5625e;
                int i8 = c1913e.f5626f;
                c1913e.f5626f = i8 + 1;
                bArr[i8] = (byte) 32;
                if (str == null) {
                    str = C1111s.m1251a(i7);
                }
                c1913e.write(str);
                c1913e.write("</title>\n</head>\n<body>\n<h2>HTTP ERROR: ");
                c1913e.write(Integer.toString(i7));
                c1913e.write("</h2>\n<p>Problem accessing ");
                c1913e.write(strMo167U);
                c1913e.write(". Reason:\n<pre>    ");
                c1913e.write(str);
                c1913e.write("</pre>");
                c1913e.write("</p>\n");
                if (this.f4659a.f4544e.f4675p) {
                    c1913e.write("<hr /><i><small>Powered by Jetty:// ");
                    c1913e.write(C1555p.f4669s);
                    c1913e.write("</small></i>");
                }
                for (int i9 = 0; i9 < 20; i9++) {
                    c1913e.write("\n                                                ");
                }
                c1913e.write("\n</body>\n</html>\n");
                mo42H(c1913e.f5626f);
                mo45i().write(c1913e.f5625e, 0, c1913e.f5626f);
                c1913e.f5625e = null;
            }
        } else if (i7 != 206) {
            this.f4659a.f4547h.m1232m(C1107o.f2331i);
            this.f4659a.f4547h.m1232m(C1107o.f2328f);
            this.f4664f = null;
            this.f4662d = null;
            this.f4663e = null;
        }
        m1794b();
    }

    @Override // p015b5.InterfaceC0460e
    /* renamed from: C */
    public boolean mo176C(String str) {
        return this.f4659a.f4551l.f2297b.containsKey(C1107o.f2326d.m1360h(str));
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: F */
    public PrintWriter mo41F() {
        if (this.f4666h != 0 && this.f4666h != 2) {
            throw new IllegalStateException("STREAM");
        }
        if (this.f4667i == null) {
            String strM1257a = this.f4664f;
            if (strM1257a == null) {
                C1153f.a aVar = this.f4663e;
                if (aVar != null) {
                    strM1257a = C1115w.m1257a(aVar);
                }
                if (strM1257a == null) {
                    strM1257a = "ISO-8859-1";
                }
                mo43M(strM1257a);
            }
            this.f4667i = this.f4659a.m1738i(strM1257a);
        }
        this.f4666h = 2;
        return this.f4667i;
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: H */
    public void mo42H(int i7) throws IOException {
        if (mo44P()) {
            return;
        }
        Objects.requireNonNull(this.f4659a);
        long j7 = i7;
        ((AbstractC1093a) this.f4659a.f4550k).m1196p(j7);
        if (i7 > 0) {
            C1101i c1101i = this.f4659a.f4551l;
            Objects.requireNonNull(c1101i);
            InterfaceC1152e interfaceC1152eM1360h = C1107o.f2326d.m1360h("Content-Length");
            C1158k c1158k = new C1158k(32);
            C1155h.m1363a(c1158k, j7);
            c1101i.m1228i(interfaceC1152eM1360h, c1158k);
            if (((AbstractC1093a) this.f4659a.f4550k).m1185e()) {
                if (this.f4666h == 2) {
                    this.f4667i.close();
                } else if (this.f4666h == 1) {
                    try {
                        mo45i().close();
                    } catch (IOException e7) {
                        throw new RuntimeException(e7);
                    }
                }
            }
        }
    }

    @Override // p015b5.InterfaceC0460e
    /* renamed from: J */
    public void mo177J(String str, String str2) {
        if ("Content-Type".equalsIgnoreCase(str)) {
            mo48y(str2);
            return;
        }
        Objects.requireNonNull(this.f4659a);
        this.f4659a.f4551l.m1230k(str, str2);
        if ("Content-Length".equalsIgnoreCase(str)) {
            if (str2 == null) {
                ((AbstractC1093a) this.f4659a.f4550k).m1196p(-1L);
                return;
            }
            ((AbstractC1093a) this.f4659a.f4550k).m1196p(Long.parseLong(str2));
        }
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: M */
    public void mo43M(String str) {
        C1153f.a aVarM1361b;
        Objects.requireNonNull(this.f4659a);
        if (this.f4666h != 0 || mo44P()) {
            return;
        }
        if (str == null) {
            if (this.f4664f != null) {
                this.f4664f = null;
                C1153f.a aVar = this.f4663e;
                if (aVar != null) {
                    this.f4665g = aVar.toString();
                } else {
                    String str2 = this.f4662d;
                    if (str2 != null) {
                        this.f4665g = str2;
                    } else {
                        this.f4665g = null;
                    }
                }
                String str3 = this.f4665g;
                if (str3 == null) {
                    this.f4659a.f4551l.m1232m(C1107o.f2331i);
                    return;
                } else {
                    this.f4659a.f4551l.m1229j(C1107o.f2331i, str3);
                    return;
                }
            }
            return;
        }
        this.f4664f = str;
        String str4 = this.f4665g;
        if (str4 != null) {
            int iIndexOf = str4.indexOf(59);
            if (iIndexOf < 0) {
                this.f4665g = null;
                C1153f.a aVar2 = this.f4663e;
                if (aVar2 != null && (aVarM1361b = aVar2.m1361b(this.f4664f)) != null) {
                    this.f4665g = aVarM1361b.toString();
                    this.f4659a.f4551l.m1228i(C1107o.f2331i, aVarM1361b);
                }
                if (this.f4665g == null) {
                    String str5 = this.f4662d + ";charset=" + C1923o.m2239c(this.f4664f, ";= ");
                    this.f4665g = str5;
                    this.f4659a.f4551l.m1229j(C1107o.f2331i, str5);
                    return;
                }
                return;
            }
            int iIndexOf2 = this.f4665g.indexOf("charset=", iIndexOf);
            if (iIndexOf2 < 0) {
                this.f4665g += ";charset=" + C1923o.m2239c(this.f4664f, ";= ");
            } else {
                int i7 = iIndexOf2 + 8;
                int iIndexOf3 = this.f4665g.indexOf(" ", i7);
                if (iIndexOf3 < 0) {
                    this.f4665g = this.f4665g.substring(0, i7) + C1923o.m2239c(this.f4664f, ";= ");
                } else {
                    this.f4665g = this.f4665g.substring(0, i7) + C1923o.m2239c(this.f4664f, ";= ") + this.f4665g.substring(iIndexOf3);
                }
            }
            this.f4659a.f4551l.m1229j(C1107o.f2331i, this.f4665g);
        }
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: P */
    public boolean mo44P() {
        return ((AbstractC1093a) this.f4659a.f4550k).m1187g();
    }

    @Override // p015b5.InterfaceC0460e
    /* renamed from: S */
    public void mo178S(int i7) {
        m1797e(i7, null);
    }

    @Override // p015b5.InterfaceC0460e
    /* renamed from: T */
    public void mo179T(String str) {
        String strM2265d;
        int iLastIndexOf;
        Objects.requireNonNull(this.f4659a);
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (!C1928t.m2269q(str)) {
            C1553n c1553n = this.f4659a.f4548i;
            StringBuilder sb = new StringBuilder(48);
            String str2 = c1553n.f4622E;
            int iM1787o = c1553n.m1787o();
            sb.append(str2);
            sb.append("://");
            sb.append(c1553n.mo35u());
            if (iM1787o > 0 && ((str2.equalsIgnoreCase("http") && iM1787o != 80) || (str2.equalsIgnoreCase("https") && iM1787o != 443))) {
                sb.append(':');
                sb.append(iM1787o);
            }
            if (str.startsWith(ServiceReference.DELIMITER)) {
                strM2265d = C1928t.m2265d(str);
            } else {
                String strMo167U = this.f4659a.f4548i.mo167U();
                if (!strMo167U.endsWith(ServiceReference.DELIMITER)) {
                    strMo167U = (!ServiceReference.DELIMITER.equals(strMo167U) && (iLastIndexOf = strMo167U.lastIndexOf(47, strMo167U.length() + (-2))) >= 0) ? strMo167U.substring(0, iLastIndexOf + 1) : null;
                }
                strM2265d = C1928t.m2265d(C1928t.m2264b(strMo167U, str));
                if (!strM2265d.startsWith(ServiceReference.DELIMITER)) {
                    sb.append('/');
                }
            }
            if (strM2265d == null) {
                throw new IllegalStateException("path cannot be above root");
            }
            sb.append(strM2265d);
            str = sb.toString();
        }
        mo47o();
        mo177J("Location", str);
        m1797e(302, null);
        m1794b();
    }

    /* renamed from: a */
    public void m1793a(C1099g c1099g) {
        boolean z6;
        boolean z7;
        C1101i c1101i = this.f4659a.f4551l;
        Objects.requireNonNull(c1101i);
        String str = c1099g.f2273a;
        String str2 = c1099g.f2274b;
        String str3 = c1099g.f2276d;
        String str4 = c1099g.f2278f;
        long j7 = c1099g.f2277e;
        String str5 = c1099g.f2275c;
        boolean z8 = c1099g.f2279g;
        boolean z9 = c1099g.f2281i;
        int i7 = c1099g.f2280h;
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Bad cookie name");
        }
        StringBuilder sb = new StringBuilder(128);
        C1923o.m2240d(sb, str, "\"\\\n\r\t\f\b%+ ;=");
        sb.append('=');
        String string = sb.toString();
        if (str2 != null && str2.length() > 0) {
            C1923o.m2240d(sb, str2, "\"\\\n\r\t\f\b%+ ;=");
        }
        if (str5 != null && str5.length() > 0) {
            sb.append(";Comment=");
            C1923o.m2240d(sb, str5, "\"\\\n\r\t\f\b%+ ;=");
        }
        boolean z10 = false;
        if (str4 == null || str4.length() <= 0) {
            z6 = false;
        } else {
            sb.append(";Path=");
            if (str4.trim().startsWith("\"")) {
                sb.append(str4);
            } else {
                C1923o.m2240d(sb, str4, "\"\\\n\r\t\f\b%+ ;=");
            }
            z6 = true;
        }
        if (str3 != null && str3.length() > 0) {
            sb.append(";Domain=");
            C1923o.m2240d(sb, str3.toLowerCase(Locale.ENGLISH), "\"\\\n\r\t\f\b%+ ;=");
            z10 = true;
        }
        if (j7 >= 0) {
            sb.append(";Expires=");
            if (j7 == 0) {
                sb.append(C1101i.f2293l);
                z7 = z6;
            } else {
                z7 = z6;
                C1101i.m1219d(sb, (1000 * j7) + System.currentTimeMillis());
            }
            if (i7 > 0) {
                sb.append(";Max-Age=");
                sb.append(j7);
            }
        } else {
            z7 = z6;
        }
        if (z8) {
            sb.append(";Secure");
        }
        if (z9) {
            sb.append(";HttpOnly");
        }
        String string2 = sb.toString();
        C1101i.e eVar = null;
        for (C1101i.e eVarM1226g = c1101i.m1226g(HttpHeaders.HEAD_KEY_SET_COOKIE); eVarM1226g != null; eVarM1226g = eVarM1226g.f2303c) {
            InterfaceC1152e interfaceC1152e = eVarM1226g.f2302b;
            String string3 = interfaceC1152e == null ? null : interfaceC1152e.toString();
            if (string3 != null && string3.startsWith(string)) {
                if (z10 || string3.contains("Domain")) {
                    if (z10) {
                        if (!string3.contains("Domain=" + str3)) {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                if (z7 || string3.contains("Path")) {
                    if (z7) {
                        if (string3.contains("Path=" + str4)) {
                        }
                    } else {
                        continue;
                    }
                }
                c1101i.f2296a.remove(eVarM1226g);
                if (eVar == null) {
                    c1101i.f2297b.put(C1107o.f2337o, eVarM1226g.f2303c);
                } else {
                    eVar.f2303c = eVarM1226g.f2303c;
                }
                c1101i.m1222a(C1107o.f2337o, new C1158k(string2));
                c1101i.m1228i(C1107o.f2332j, C1101i.f2292k);
            }
            eVar = eVarM1226g;
        }
        c1101i.m1222a(C1107o.f2337o, new C1158k(string2));
        c1101i.m1228i(C1107o.f2332j, C1101i.f2292k);
    }

    /* renamed from: b */
    public void m1794b() {
        AbstractC1541b abstractC1541b = this.f4659a;
        if (!((AbstractC1093a) abstractC1541b.f4550k).m1187g()) {
            InterfaceC1095c interfaceC1095c = abstractC1541b.f4550k;
            C1554o c1554o = abstractC1541b.f4552m;
            ((AbstractC1093a) interfaceC1095c).m1198r(c1554o.f4660b, c1554o.f4661c);
            try {
                ((C1105m) abstractC1541b.f4550k).mo1182b(abstractC1541b.f4551l, true);
            } catch (RuntimeException e7) {
                InterfaceC2016c interfaceC2016c = AbstractC1541b.f4541z;
                interfaceC2016c.mo2356g("header full: " + e7, new Object[0]);
                interfaceC2016c.mo2359j(e7);
                abstractC1541b.f4552m.m1796d();
                abstractC1541b.f4550k.reset();
                ((AbstractC1093a) abstractC1541b.f4550k).m1198r(500, null);
                ((C1105m) abstractC1541b.f4550k).mo1182b(abstractC1541b.f4551l, true);
                abstractC1541b.f4550k.complete();
                throw new C1100h(500);
            }
        }
        abstractC1541b.f4550k.complete();
    }

    /* renamed from: c */
    public String m1795c() {
        return this.f4661c;
    }

    /* renamed from: d */
    public void m1796d() {
        mo47o();
        mo47o();
        this.f4667i = null;
        this.f4666h = 0;
        this.f4660b = 200;
        this.f4661c = null;
        C1101i c1101i = this.f4659a.f4551l;
        c1101i.m1223b();
        C1101i.e eVarM1225f = this.f4659a.f4547h.m1225f(C1107o.f2329g);
        String strM1233a = eVarM1225f != null ? eVarM1225f.m1233a() : null;
        if (strM1233a != null) {
            String[] strArrSplit = strM1233a.split(",");
            for (int i7 = 0; strArrSplit != null && i7 < strArrSplit.length; i7++) {
                C1153f.a aVarM1355c = C1106n.f2323d.m1355c(strArrSplit[0].trim());
                if (aVarM1355c != null) {
                    int i8 = aVarM1355c.f2542r;
                    if (i8 == 1) {
                        c1101i.m1228i(C1107o.f2329g, C1106n.f2324e);
                    } else if (i8 != 5) {
                        if (i8 == 8) {
                            c1101i.m1229j(C1107o.f2329g, "TE");
                        }
                    } else if ("HTTP/1.0".equalsIgnoreCase(this.f4659a.f4548i.f4654w)) {
                        c1101i.m1229j(C1107o.f2329g, HttpHeaders.HEAD_VALUE_CONNECTION_KEEP_ALIVE);
                    }
                }
            }
        }
    }

    /* renamed from: e */
    public void m1797e(int i7, String str) {
        if (i7 <= 0) {
            throw new IllegalArgumentException();
        }
        Objects.requireNonNull(this.f4659a);
        this.f4660b = i7;
        this.f4661c = str;
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: i */
    public AbstractC0030p mo45i() {
        if (this.f4666h != 0 && this.f4666h != 1) {
            throw new IllegalStateException("WRITER");
        }
        AbstractC1541b abstractC1541b = this.f4659a;
        if (abstractC1541b.f4553n == null) {
            abstractC1541b.f4553n = abstractC1541b.new b();
        }
        AbstractC1541b.b bVar = abstractC1541b.f4553n;
        this.f4666h = 1;
        return bVar;
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: j */
    public String mo46j() {
        if (this.f4664f == null) {
            this.f4664f = "ISO-8859-1";
        }
        return this.f4664f;
    }

    @Override // p015b5.InterfaceC0460e
    /* renamed from: k */
    public void mo180k(String str, long j7) {
        Objects.requireNonNull(this.f4659a);
        C1101i c1101i = this.f4659a.f4551l;
        Objects.requireNonNull(c1101i);
        c1101i.m1222a(C1107o.f2326d.m1360h(str), new C1158k(C1101i.m1220e(j7)));
    }

    @Override // p015b5.InterfaceC0460e
    /* renamed from: m */
    public void mo181m(String str, long j7) {
        Objects.requireNonNull(this.f4659a);
        C1101i c1101i = this.f4659a.f4551l;
        Objects.requireNonNull(c1101i);
        c1101i.m1231l(C1107o.f2326d.m1360h(str), j7);
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: o */
    public void mo47o() {
        if (mo44P()) {
            throw new IllegalStateException("Committed");
        }
        AbstractC1093a abstractC1093a = (AbstractC1093a) this.f4659a.f4550k;
        if (abstractC1093a.f2244c >= 3) {
            throw new IllegalStateException("Flushed");
        }
        abstractC1093a.f2252k = false;
        abstractC1093a.f2255n = null;
        abstractC1093a.f2250i = 0L;
        abstractC1093a.f2251j = -3L;
        abstractC1093a.f2258q = null;
        InterfaceC1152e interfaceC1152e = abstractC1093a.f2257p;
        if (interfaceC1152e != null) {
            interfaceC1152e.clear();
        }
    }

    @Override // p015b5.InterfaceC0460e
    /* renamed from: q */
    public void mo182q(String str, String str2) {
        Objects.requireNonNull(this.f4659a);
        if ("Content-Type".equalsIgnoreCase(str)) {
            mo48y(str2);
            return;
        }
        C1101i c1101i = this.f4659a.f4551l;
        Objects.requireNonNull(c1101i);
        if (str2 != null) {
            c1101i.m1222a(C1107o.f2326d.m1360h(str), c1101i.m1224c(str2));
        }
        if ("Content-Length".equalsIgnoreCase(str)) {
            ((AbstractC1093a) this.f4659a.f4550k).m1196p(Long.parseLong(str2));
        }
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("HTTP/1.1 ");
        sbM112a.append(this.f4660b);
        sbM112a.append(" ");
        String str = this.f4661c;
        if (str == null) {
            str = "";
        }
        sbM112a.append(str);
        sbM112a.append(System.getProperty("line.separator"));
        sbM112a.append(this.f4659a.f4551l.toString());
        return sbM112a.toString();
    }

    @Override // p015b5.InterfaceC0460e
    /* renamed from: v */
    public void mo183v(int i7) {
        if (i7 == -1) {
            this.f4659a.f2538a.close();
            return;
        }
        if (i7 != 102) {
            mo175A(i7, null);
        } else {
            if (!this.f4659a.f4560u || mo44P()) {
                return;
            }
            ((C1105m) this.f4659a.f4550k).m1240y(102);
        }
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: y */
    public void mo48y(String str) {
        if (mo44P()) {
            return;
        }
        Objects.requireNonNull(this.f4659a);
        if (str == null) {
            this.f4664f = null;
            this.f4662d = null;
            this.f4663e = null;
            this.f4665g = null;
            this.f4659a.f4551l.m1232m(C1107o.f2331i);
            return;
        }
        int iIndexOf = str.indexOf(59);
        if (iIndexOf <= 0) {
            this.f4662d = str;
            C1153f.a aVarM1355c = C1115w.f2385c.m1355c(str);
            this.f4663e = aVarM1355c;
            String str2 = this.f4664f;
            if (str2 == null) {
                if (aVarM1355c != null) {
                    this.f4665g = aVarM1355c.toString();
                    this.f4659a.f4551l.m1228i(C1107o.f2331i, this.f4663e);
                    return;
                } else {
                    this.f4665g = str;
                    this.f4659a.f4551l.m1229j(C1107o.f2331i, str);
                    return;
                }
            }
            if (aVarM1355c == null) {
                StringBuilder sbM94a = C0080b.m94a(str, ";charset=");
                sbM94a.append(C1923o.m2239c(this.f4664f, ";= "));
                String string = sbM94a.toString();
                this.f4665g = string;
                this.f4659a.f4551l.m1229j(C1107o.f2331i, string);
                return;
            }
            C1153f.a aVarM1361b = aVarM1355c.m1361b(str2);
            if (aVarM1361b != null) {
                this.f4665g = aVarM1361b.toString();
                this.f4659a.f4551l.m1228i(C1107o.f2331i, aVarM1361b);
                return;
            }
            String str3 = this.f4662d + ";charset=" + C1923o.m2239c(this.f4664f, ";= ");
            this.f4665g = str3;
            this.f4659a.f4551l.m1229j(C1107o.f2331i, str3);
            return;
        }
        String strTrim = str.substring(0, iIndexOf).trim();
        this.f4662d = strTrim;
        C1153f c1153f = C1115w.f2385c;
        this.f4663e = c1153f.m1355c(strTrim);
        int i7 = iIndexOf + 1;
        int iIndexOf2 = str.indexOf("charset=", i7);
        if (iIndexOf2 < 0) {
            this.f4663e = null;
            if (this.f4664f != null) {
                StringBuilder sbM94a2 = C0080b.m94a(str, ";charset=");
                sbM94a2.append(C1923o.m2239c(this.f4664f, ";= "));
                str = sbM94a2.toString();
            }
            this.f4665g = str;
            this.f4659a.f4551l.m1229j(C1107o.f2331i, str);
            return;
        }
        int i8 = iIndexOf2 + 8;
        int iIndexOf3 = str.indexOf(32, i8);
        if (this.f4666h != 2) {
            if ((iIndexOf2 != i7 || iIndexOf3 >= 0) && !(iIndexOf2 == iIndexOf + 2 && iIndexOf3 < 0 && str.charAt(i7) == ' ')) {
                if (iIndexOf3 > 0) {
                    this.f4664f = C1923o.m2241e(str.substring(i8, iIndexOf3));
                    this.f4665g = str;
                    this.f4659a.f4551l.m1229j(C1107o.f2331i, str);
                    return;
                } else {
                    this.f4664f = C1923o.m2241e(str.substring(i8));
                    this.f4665g = str;
                    this.f4659a.f4551l.m1229j(C1107o.f2331i, str);
                    return;
                }
            }
            this.f4663e = c1153f.m1355c(this.f4662d);
            String strM2241e = C1923o.m2241e(str.substring(i8));
            this.f4664f = strM2241e;
            C1153f.a aVar = this.f4663e;
            if (aVar == null) {
                this.f4665g = str;
                this.f4659a.f4551l.m1229j(C1107o.f2331i, str);
                return;
            }
            C1153f.a aVarM1361b2 = aVar.m1361b(strM2241e);
            if (aVarM1361b2 != null) {
                this.f4665g = aVarM1361b2.toString();
                this.f4659a.f4551l.m1228i(C1107o.f2331i, aVarM1361b2);
                return;
            } else {
                this.f4665g = str;
                this.f4659a.f4551l.m1229j(C1107o.f2331i, str);
                return;
            }
        }
        if ((iIndexOf2 != i7 || iIndexOf3 >= 0) && !(iIndexOf2 == iIndexOf + 2 && iIndexOf3 < 0 && str.charAt(i7) == ' ')) {
            if (iIndexOf3 < 0) {
                String str4 = str.substring(0, iIndexOf2) + ";charset=" + C1923o.m2239c(this.f4664f, ";= ");
                this.f4665g = str4;
                this.f4659a.f4551l.m1229j(C1107o.f2331i, str4);
                return;
            }
            String str5 = str.substring(0, iIndexOf2) + str.substring(iIndexOf3) + ";charset=" + C1923o.m2239c(this.f4664f, ";= ");
            this.f4665g = str5;
            this.f4659a.f4551l.m1229j(C1107o.f2331i, str5);
            return;
        }
        C1153f.a aVar2 = this.f4663e;
        if (aVar2 == null) {
            String str6 = this.f4662d + ";charset=" + this.f4664f;
            this.f4665g = str6;
            this.f4659a.f4551l.m1229j(C1107o.f2331i, str6);
            return;
        }
        C1153f.a aVarM1361b3 = aVar2.m1361b(this.f4664f);
        if (aVarM1361b3 != null) {
            this.f4665g = aVarM1361b3.toString();
            this.f4659a.f4551l.m1228i(C1107o.f2331i, aVarM1361b3);
            return;
        }
        String str7 = this.f4662d + ";charset=" + this.f4664f;
        this.f4665g = str7;
        this.f4659a.f4551l.m1229j(C1107o.f2331i, str7);
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x007b  */
    @Override // p015b5.InterfaceC0460e
    /* renamed from: z */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String mo184z(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p113n5.C1554o.mo184z(java.lang.String):java.lang.String");
    }
}
