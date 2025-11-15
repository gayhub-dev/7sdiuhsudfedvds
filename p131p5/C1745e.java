package p131p5;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;
import p006a5.C0029o;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p065h5.C1111s;
import p113n5.AbstractC1541b;
import p113n5.C1547h;
import p113n5.C1553n;
import p113n5.C1554o;
import p161t5.C1913e;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: ErrorHandler.java */
/* renamed from: p5.e */
/* loaded from: classes.dex */
public class C1745e extends AbstractC1741a {

    /* renamed from: m */
    public static final InterfaceC2016c f4954m;

    /* renamed from: j */
    public boolean f4955j = true;

    /* renamed from: k */
    public boolean f4956k = true;

    /* renamed from: l */
    public String f4957l = "must-revalidate,no-cache,no-store";

    /* compiled from: ErrorHandler.java */
    /* renamed from: p5.e$a */
    public interface a {
        /* renamed from: a */
        String m1909a(InterfaceC0458c interfaceC0458c);
    }

    static {
        Properties properties = C2015b.f5863a;
        f4954m = C2015b.m2349a(C1745e.class.getName());
    }

    /* renamed from: M */
    public void m1908M(Writer writer, String str) throws IOException {
        if (str == null) {
            return;
        }
        for (int i7 = 0; i7 < str.length(); i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt == '&') {
                writer.write("&amp;");
            } else if (cCharAt == '<') {
                writer.write("&lt;");
            } else if (cCharAt == '>') {
                writer.write("&gt;");
            } else if (!Character.isISOControl(cCharAt) || Character.isWhitespace(cCharAt)) {
                writer.write(cCharAt);
            } else {
                writer.write(63);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p113n5.InterfaceC1548i
    /* renamed from: u */
    public void mo1630u(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        String strM1909a;
        String str2;
        AbstractC1541b abstractC1541bM1735g = AbstractC1541b.m1735g();
        String strMo165N = interfaceC0458c.mo165N();
        if (!strMo165N.equals("GET") && !strMo165N.equals("POST") && !strMo165N.equals("HEAD")) {
            abstractC1541bM1735g.f4548i.f4647p = true;
            return;
        }
        if ((this instanceof a) && (strM1909a = ((a) this).m1909a(interfaceC0458c)) != null && interfaceC0458c.getServletContext() != null && ((str2 = (String) interfaceC0458c.mo26a("org.eclipse.jetty.server.error_page")) == null || !str2.equals(strM1909a))) {
            interfaceC0458c.mo27b("org.eclipse.jetty.server.error_page", strM1909a);
            C1547h c1547h = (C1547h) interfaceC0458c.getServletContext().mo17f(strM1909a);
            try {
                if (c1547h != null) {
                    c1547h.m1770a(interfaceC0458c, interfaceC0460e, 5);
                    return;
                }
                f4954m.mo2356g("No error page " + strM1909a, new Object[0]);
            } catch (C0029o e7) {
                f4954m.mo2354e("EXCEPTION ", e7);
                return;
            }
        }
        abstractC1541bM1735g.f4548i.f4647p = true;
        interfaceC0460e.mo48y("text/html;charset=ISO-8859-1");
        String str3 = this.f4957l;
        if (str3 != null) {
            interfaceC0460e.mo177J("Cache-Control", str3);
        }
        C1913e c1913e = new C1913e(4096);
        C1554o c1554o = abstractC1541bM1735g.f4552m;
        int i7 = c1554o.f4660b;
        String strM1251a = c1554o.f4661c;
        boolean z6 = this.f4955j;
        if (strM1251a == null) {
            strM1251a = C1111s.m1251a(i7);
        }
        c1913e.write("<html>\n<head>\n");
        c1913e.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"/>\n");
        c1913e.write("<title>Error ");
        c1913e.write(Integer.toString(i7));
        if (this.f4956k) {
            c1913e.write(32);
            m1908M(c1913e, strM1251a);
        }
        c1913e.write("</title>\n");
        c1913e.write("</head>\n<body>");
        String strMo167U = interfaceC0458c.mo167U();
        c1913e.write("<h2>HTTP ERROR ");
        c1913e.write(Integer.toString(i7));
        c1913e.write("</h2>\n<p>Problem accessing ");
        m1908M(c1913e, strMo167U);
        c1913e.write(". Reason:\n<pre>    ");
        m1908M(c1913e, strM1251a);
        c1913e.write("</pre></p>");
        if (z6) {
            for (Throwable cause = (Throwable) interfaceC0458c.mo26a("javax.servlet.error.exception"); cause != null; cause = cause.getCause()) {
                c1913e.write("<h3>Caused by:</h3><pre>");
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                cause.printStackTrace(printWriter);
                printWriter.flush();
                m1908M(c1913e, stringWriter.getBuffer().toString());
                c1913e.write("</pre>\n");
            }
        }
        c1913e.write("<hr /><i><small>Powered by Jetty://</small></i>");
        for (int i8 = 0; i8 < 20; i8++) {
            c1913e.write("<br/>                                                \n");
        }
        c1913e.write("\n</body>\n</html>\n");
        interfaceC0460e.mo42H(c1913e.f5626f);
        interfaceC0460e.mo45i().write(c1913e.f5625e, 0, c1913e.f5626f);
        c1913e.f5625e = null;
    }
}
