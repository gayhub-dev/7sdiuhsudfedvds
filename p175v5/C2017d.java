package p175v5;

import android.arch.lifecycle.C0063n;
import java.io.PrintStream;
import java.security.AccessControlException;
import java.util.Objects;
import java.util.Properties;
import p009b.C0413b;
import p161t5.C1916h;

/* compiled from: StdErrLog.java */
/* renamed from: v5.d */
/* loaded from: classes.dex */
public class C2017d extends AbstractC2014a {

    /* renamed from: h */
    public static final String f5869h = System.getProperty("line.separator");

    /* renamed from: i */
    public static C1916h f5870i;

    /* renamed from: j */
    public static final Properties f5871j;

    /* renamed from: k */
    public static final boolean f5872k;

    /* renamed from: l */
    public static final boolean f5873l;

    /* renamed from: a */
    public int f5874a;

    /* renamed from: b */
    public int f5875b;

    /* renamed from: c */
    public PrintStream f5876c;

    /* renamed from: d */
    public boolean f5877d;

    /* renamed from: e */
    public boolean f5878e;

    /* renamed from: f */
    public final String f5879f;

    /* renamed from: g */
    public final String f5880g;

    static {
        Properties properties = new Properties();
        f5871j = properties;
        Properties properties2 = C2015b.f5863a;
        f5872k = Boolean.parseBoolean(properties2.getProperty("org.eclipse.jetty.util.log.SOURCE", properties2.getProperty("org.eclipse.jetty.util.log.stderr.SOURCE", "false")));
        f5873l = Boolean.parseBoolean(C2015b.f5863a.getProperty("org.eclipse.jetty.util.log.stderr.LONG", "false"));
        properties.putAll(C2015b.f5863a);
        String[] strArr = {"DEBUG", "org.eclipse.jetty.util.log.DEBUG", "org.eclipse.jetty.util.log.stderr.DEBUG"};
        for (int i7 = 0; i7 < 3; i7++) {
            String str = strArr[i7];
            if (System.getProperty(str) != null) {
                System.err.printf("System Property [%s] has been deprecated! (Use org.eclipse.jetty.LEVEL=DEBUG instead)%n", str);
            }
        }
        try {
            f5870i = new C1916h("yyyy-MM-dd HH:mm:ss");
        } catch (Exception e7) {
            e7.printStackTrace(System.err);
        }
    }

    public C2017d() {
        this(null);
    }

    /* renamed from: m */
    public static String m2361m(String str) {
        String[] strArrSplit = str.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i7 = 0; i7 < strArrSplit.length - 1; i7++) {
            sb.append(strArrSplit[i7].charAt(0));
        }
        if (sb.length() > 0) {
            sb.append('.');
        }
        sb.append(strArrSplit[strArrSplit.length - 1]);
        return sb.toString();
    }

    /* renamed from: r */
    public static int m2362r(String str, String str2) {
        if (str2 == null) {
            return -1;
        }
        String strTrim = str2.trim();
        if ("ALL".equalsIgnoreCase(strTrim)) {
            return 0;
        }
        if ("DEBUG".equalsIgnoreCase(strTrim)) {
            return 1;
        }
        if ("INFO".equalsIgnoreCase(strTrim)) {
            return 2;
        }
        if ("WARN".equalsIgnoreCase(strTrim)) {
            return 3;
        }
        System.err.println("Unknown StdErrLog level [" + str + "]=[" + strTrim + "], expecting only [ALL, DEBUG, INFO, WARN] as values.");
        return -1;
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: a */
    public void mo2351a(String str, Object... objArr) {
        if (this.f5874a <= 1) {
            StringBuilder sb = new StringBuilder(64);
            m2364o(sb, ":DBUG:", str, objArr);
            PrintStream printStream = this.f5876c;
            if (printStream == null) {
                printStream = System.err;
            }
            printStream.println(sb);
        }
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: c */
    public void mo2352c(String str, Throwable th) {
        if (this.f5874a <= 2) {
            StringBuilder sb = new StringBuilder(64);
            m2364o(sb, ":INFO:", str, new Object[0]);
            m2366q(sb, th);
            PrintStream printStream = this.f5876c;
            if (printStream == null) {
                printStream = System.err;
            }
            printStream.println(sb);
        }
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: d */
    public boolean mo2353d() {
        return this.f5874a <= 1;
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: e */
    public void mo2354e(String str, Throwable th) {
        if (this.f5874a <= 3) {
            StringBuilder sb = new StringBuilder(64);
            m2364o(sb, ":WARN:", str, new Object[0]);
            m2366q(sb, th);
            PrintStream printStream = this.f5876c;
            if (printStream == null) {
                printStream = System.err;
            }
            printStream.println(sb);
        }
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: f */
    public void mo2355f(String str, Throwable th) {
        if (this.f5874a <= 1) {
            StringBuilder sb = new StringBuilder(64);
            m2364o(sb, ":DBUG:", str, new Object[0]);
            m2366q(sb, th);
            PrintStream printStream = this.f5876c;
            if (printStream == null) {
                printStream = System.err;
            }
            printStream.println(sb);
        }
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: g */
    public void mo2356g(String str, Object... objArr) {
        if (this.f5874a <= 3) {
            StringBuilder sb = new StringBuilder(64);
            m2364o(sb, ":WARN:", str, objArr);
            PrintStream printStream = this.f5876c;
            if (printStream == null) {
                printStream = System.err;
            }
            printStream.println(sb);
        }
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: h */
    public void mo2357h(String str, Object... objArr) {
        if (this.f5874a <= 2) {
            StringBuilder sb = new StringBuilder(64);
            m2364o(sb, ":INFO:", str, objArr);
            PrintStream printStream = this.f5876c;
            if (printStream == null) {
                printStream = System.err;
            }
            printStream.println(sb);
        }
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: i */
    public void mo2358i(Throwable th) {
        mo2354e("", th);
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: j */
    public void mo2359j(Throwable th) {
        mo2355f("", th);
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: k */
    public void mo2360k(Throwable th) {
        if (this.f5874a <= 0) {
            StringBuilder sb = new StringBuilder(64);
            m2364o(sb, ":IGNORED:", "", new Object[0]);
            m2366q(sb, th);
            PrintStream printStream = this.f5876c;
            if (printStream == null) {
                printStream = System.err;
            }
            printStream.println(sb);
        }
    }

    /* renamed from: n */
    public final void m2363n(StringBuilder sb, String str) {
        for (int i7 = 0; i7 < str.length(); i7++) {
            char cCharAt = str.charAt(i7);
            if (!Character.isISOControl(cCharAt)) {
                sb.append(cCharAt);
            } else if (cCharAt == '\n') {
                sb.append('|');
            } else if (cCharAt == '\r') {
                sb.append('<');
            } else {
                sb.append('?');
            }
        }
    }

    /* renamed from: o */
    public final void m2364o(StringBuilder sb, String str, String str2, Object... objArr) {
        C1916h c1916h = f5870i;
        Objects.requireNonNull(c1916h);
        long jCurrentTimeMillis = System.currentTimeMillis();
        c1916h.f5641k = (int) (jCurrentTimeMillis % 1000);
        String strM2215a = c1916h.m2215a(jCurrentTimeMillis);
        int i7 = f5870i.f5641k;
        int i8 = 0;
        sb.setLength(0);
        sb.append(strM2215a);
        if (i7 > 99) {
            sb.append('.');
        } else if (i7 > 9) {
            sb.append(".0");
        } else {
            sb.append(".00");
        }
        sb.append(i7);
        sb.append(str);
        if (this.f5878e) {
            sb.append(this.f5879f);
        } else {
            sb.append(this.f5880g);
        }
        sb.append(':');
        if (this.f5877d) {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            while (true) {
                if (i8 >= stackTrace.length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i8];
                String className = stackTraceElement.getClassName();
                if (className.equals(C2017d.class.getName()) || className.equals(C2015b.class.getName())) {
                    i8++;
                } else {
                    if (this.f5878e || !className.startsWith("org.eclipse.jetty.")) {
                        sb.append(className);
                    } else {
                        sb.append(m2361m(className));
                    }
                    sb.append('#');
                    sb.append(stackTraceElement.getMethodName());
                    if (stackTraceElement.getFileName() != null) {
                        sb.append('(');
                        sb.append(stackTraceElement.getFileName());
                        sb.append(':');
                        sb.append(stackTraceElement.getLineNumber());
                        sb.append(')');
                    }
                    sb.append(':');
                }
            }
        }
        m2365p(sb, str2, objArr);
    }

    /* renamed from: p */
    public final void m2365p(StringBuilder sb, String str, Object... objArr) {
        if (str == null) {
            str = "";
            for (int i7 = 0; i7 < objArr.length; i7++) {
                str = C0063n.m88a(str, "{} ");
            }
        }
        int length = 0;
        for (Object obj : objArr) {
            int iIndexOf = str.indexOf("{}", length);
            if (iIndexOf < 0) {
                m2363n(sb, str.substring(length));
                sb.append(" ");
                sb.append(obj);
                length = str.length();
            } else {
                m2363n(sb, str.substring(length, iIndexOf));
                sb.append(String.valueOf(obj));
                length = iIndexOf + 2;
            }
        }
        m2363n(sb, str.substring(length));
    }

    /* renamed from: q */
    public final void m2366q(StringBuilder sb, Throwable th) {
        if (th == null) {
            sb.append("null");
            return;
        }
        sb.append(f5869h);
        m2365p(sb, th.toString(), new Object[0]);
        StackTraceElement[] stackTrace = th.getStackTrace();
        for (int i7 = 0; stackTrace != null && i7 < stackTrace.length; i7++) {
            sb.append(f5869h);
            sb.append("\tat ");
            m2365p(sb, stackTrace[i7].toString(), new Object[0]);
        }
        Throwable cause = th.getCause();
        if (cause == null || cause == th) {
            return;
        }
        sb.append(f5869h);
        sb.append("Caused by: ");
        m2366q(sb, cause);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("StdErrLog:");
        sbM112a.append(this.f5879f);
        sbM112a.append(":LEVEL=");
        int i7 = this.f5874a;
        if (i7 == 0) {
            sbM112a.append("ALL");
        } else if (i7 == 1) {
            sbM112a.append("DEBUG");
        } else if (i7 == 2) {
            sbM112a.append("INFO");
        } else if (i7 != 3) {
            sbM112a.append("?");
        } else {
            sbM112a.append("WARN");
        }
        return sbM112a.toString();
    }

    public C2017d(String str) {
        int iM2362r;
        Properties properties = f5871j;
        this.f5874a = 2;
        this.f5876c = null;
        this.f5877d = f5872k;
        this.f5878e = f5873l;
        str = str == null ? "" : str;
        this.f5879f = str;
        this.f5880g = m2361m(str);
        while (str != null && str.length() > 0) {
            iM2362r = m2362r(str + ".LEVEL", properties.getProperty(str + ".LEVEL"));
            if (iM2362r != -1) {
                break;
            }
            int iLastIndexOf = str.lastIndexOf(46);
            str = iLastIndexOf >= 0 ? str.substring(0, iLastIndexOf) : null;
        }
        iM2362r = m2362r("log.LEVEL", properties.getProperty("log.LEVEL", "INFO"));
        this.f5874a = iM2362r;
        this.f5875b = iM2362r;
        try {
            this.f5877d = Boolean.parseBoolean(properties.getProperty(this.f5879f + ".SOURCE", Boolean.toString(this.f5877d)));
        } catch (AccessControlException unused) {
            this.f5877d = f5872k;
        }
    }
}
