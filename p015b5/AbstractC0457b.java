package p015b5;

import android.arch.lifecycle.C0063n;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;
import p006a5.AbstractC0021g;
import p006a5.C0029o;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;

/* compiled from: HttpServlet.java */
/* renamed from: b5.b */
/* loaded from: classes.dex */
public abstract class AbstractC0457b extends AbstractC0021g {
    private static final String HEADER_IFMODSINCE = "If-Modified-Since";
    private static final String HEADER_LASTMOD = "Last-Modified";
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_TRACE = "TRACE";
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);

    private Method[] getAllDeclaredMethods(Class<?> cls) throws SecurityException {
        if (cls.equals(AbstractC0457b.class)) {
            return null;
        }
        Method[] allDeclaredMethods = getAllDeclaredMethods(cls.getSuperclass());
        Method[] declaredMethods = cls.getDeclaredMethods();
        if (allDeclaredMethods == null || allDeclaredMethods.length <= 0) {
            return declaredMethods;
        }
        Method[] methodArr = new Method[allDeclaredMethods.length + declaredMethods.length];
        System.arraycopy(allDeclaredMethods, 0, methodArr, 0, allDeclaredMethods.length);
        System.arraycopy(declaredMethods, 0, methodArr, allDeclaredMethods.length, declaredMethods.length);
        return methodArr;
    }

    private void maybeSetLastModified(InterfaceC0460e interfaceC0460e, long j7) {
        if (!interfaceC0460e.mo176C("Last-Modified") && j7 >= 0) {
            interfaceC0460e.mo181m("Last-Modified", j7);
        }
    }

    public void doDelete(InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        String strMo24Q = interfaceC0458c.mo24Q();
        String string = lStrings.getString("http.method_delete_not_supported");
        if (strMo24Q.endsWith("1.1")) {
            interfaceC0460e.mo175A(405, string);
        } else {
            interfaceC0460e.mo175A(400, string);
        }
    }

    public void doGet(InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        String strMo24Q = interfaceC0458c.mo24Q();
        String string = lStrings.getString("http.method_get_not_supported");
        if (strMo24Q.endsWith("1.1")) {
            interfaceC0460e.mo175A(405, string);
        } else {
            interfaceC0460e.mo175A(400, string);
        }
    }

    public void doHead(InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        C0468m c0468m = new C0468m(interfaceC0460e);
        doGet(interfaceC0458c, c0468m);
        if (c0468m.f284e) {
            return;
        }
        PrintWriter printWriter = c0468m.f283d;
        if (printWriter != null) {
            printWriter.flush();
        }
        c0468m.mo42H(c0468m.f282c.f280f);
    }

    public void doOptions(InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) throws SecurityException {
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        for (Method method : getAllDeclaredMethods(getClass())) {
            if (method.getName().equals("doGet")) {
                z6 = true;
                z7 = true;
            }
            if (method.getName().equals("doPost")) {
                z8 = true;
            }
            if (method.getName().equals("doPut")) {
                z9 = true;
            }
            if (method.getName().equals("doDelete")) {
                z10 = true;
            }
        }
        String strM88a = z6 ? METHOD_GET : null;
        if (z7) {
            strM88a = strM88a == null ? METHOD_HEAD : C0063n.m88a(strM88a, ", HEAD");
        }
        if (z8) {
            strM88a = strM88a == null ? METHOD_POST : C0063n.m88a(strM88a, ", POST");
        }
        if (z9) {
            strM88a = strM88a == null ? METHOD_PUT : C0063n.m88a(strM88a, ", PUT");
        }
        if (z10) {
            strM88a = strM88a == null ? METHOD_DELETE : C0063n.m88a(strM88a, ", DELETE");
        }
        String strM88a2 = strM88a == null ? METHOD_TRACE : C0063n.m88a(strM88a, ", TRACE");
        interfaceC0460e.mo177J("Allow", strM88a2 == null ? METHOD_OPTIONS : C0063n.m88a(strM88a2, ", OPTIONS"));
    }

    public void doPost(InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        String strMo24Q = interfaceC0458c.mo24Q();
        String string = lStrings.getString("http.method_post_not_supported");
        if (strMo24Q.endsWith("1.1")) {
            interfaceC0460e.mo175A(405, string);
        } else {
            interfaceC0460e.mo175A(400, string);
        }
    }

    public void doPut(InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        String strMo24Q = interfaceC0458c.mo24Q();
        String string = lStrings.getString("http.method_put_not_supported");
        if (strMo24Q.endsWith("1.1")) {
            interfaceC0460e.mo175A(405, string);
        } else {
            interfaceC0460e.mo175A(400, string);
        }
    }

    public void doTrace(InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) throws IOException {
        StringBuilder sb = new StringBuilder("TRACE ");
        sb.append(interfaceC0458c.mo167U());
        sb.append(" ");
        sb.append(interfaceC0458c.mo24Q());
        Enumeration<String> enumerationMo172w = interfaceC0458c.mo172w();
        while (enumerationMo172w.hasMoreElements()) {
            String strNextElement = enumerationMo172w.nextElement();
            sb.append("\r\n");
            sb.append(strNextElement);
            sb.append(": ");
            sb.append(interfaceC0458c.mo173x(strNextElement));
        }
        sb.append("\r\n");
        int length = sb.length();
        interfaceC0460e.mo48y("message/http");
        interfaceC0460e.mo42H(length);
        interfaceC0460e.mo45i().mo22a(sb.toString());
    }

    public long getLastModified(InterfaceC0458c interfaceC0458c) {
        return -1L;
    }

    public void service(InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) throws SecurityException, IOException {
        String strMo165N = interfaceC0458c.mo165N();
        if (strMo165N.equals(METHOD_GET)) {
            long lastModified = getLastModified(interfaceC0458c);
            if (lastModified == -1) {
                doGet(interfaceC0458c, interfaceC0460e);
                return;
            } else if (interfaceC0458c.mo162I("If-Modified-Since") >= lastModified) {
                interfaceC0460e.mo178S(304);
                return;
            } else {
                maybeSetLastModified(interfaceC0460e, lastModified);
                doGet(interfaceC0458c, interfaceC0460e);
                return;
            }
        }
        if (strMo165N.equals(METHOD_HEAD)) {
            maybeSetLastModified(interfaceC0460e, getLastModified(interfaceC0458c));
            doHead(interfaceC0458c, interfaceC0460e);
            return;
        }
        if (strMo165N.equals(METHOD_POST)) {
            doPost(interfaceC0458c, interfaceC0460e);
            return;
        }
        if (strMo165N.equals(METHOD_PUT)) {
            doPut(interfaceC0458c, interfaceC0460e);
            return;
        }
        if (strMo165N.equals(METHOD_DELETE)) {
            doDelete(interfaceC0458c, interfaceC0460e);
            return;
        }
        if (strMo165N.equals(METHOD_OPTIONS)) {
            doOptions(interfaceC0458c, interfaceC0460e);
        } else if (strMo165N.equals(METHOD_TRACE)) {
            doTrace(interfaceC0458c, interfaceC0460e);
        } else {
            interfaceC0460e.mo175A(501, MessageFormat.format(lStrings.getString("http.method_not_implemented"), strMo165N));
        }
    }

    @Override // p006a5.AbstractC0021g, p006a5.InterfaceC0024j
    public void service(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w) throws C0029o, SecurityException, IOException {
        if ((interfaceC0032r instanceof InterfaceC0458c) && (interfaceC0037w instanceof InterfaceC0460e)) {
            service((InterfaceC0458c) interfaceC0032r, (InterfaceC0460e) interfaceC0037w);
            return;
        }
        throw new C0029o("non-HTTP request or response");
    }
}
