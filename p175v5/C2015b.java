package p175v5;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import p043f.C0984a;

/* compiled from: Log.java */
/* renamed from: v5.b */
/* loaded from: classes.dex */
public class C2015b {

    /* renamed from: b */
    public static String f5864b;

    /* renamed from: c */
    public static boolean f5865c;

    /* renamed from: e */
    public static InterfaceC2016c f5867e;

    /* renamed from: f */
    public static boolean f5868f;

    /* renamed from: d */
    public static final ConcurrentMap<String, InterfaceC2016c> f5866d = new ConcurrentHashMap();

    /* renamed from: a */
    public static Properties f5863a = new Properties();

    /* compiled from: Log.java */
    /* renamed from: v5.b$a */
    public static class a implements PrivilegedAction<Object> {
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0080  */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.ClassLoader] */
        /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.ClassLoader] */
        /* JADX WARN: Type inference failed for: r0v16 */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r0v18 */
        /* JADX WARN: Type inference failed for: r0v19 */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.ClassLoader] */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v6, types: [java.io.InputStream] */
        @Override // java.security.PrivilegedAction
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object run() throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.Class<v5.b> r0 = p175v5.C2015b.class
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                java.lang.ClassLoader r1 = r1.getContextClassLoader()
                r2 = 0
                r3 = r2
            Lc:
                java.lang.String r4 = "jetty-logging.properties"
                if (r3 != 0) goto L1f
                if (r1 == 0) goto L1f
                java.net.URL r3 = r1.getResource(r4)
                if (r3 != 0) goto L1d
                java.lang.ClassLoader r1 = r1.getParent()
                goto Lc
            L1d:
                r1 = r2
                goto Lc
            L1f:
                java.lang.ClassLoader r0 = r0.getClassLoader()
            L23:
                if (r3 != 0) goto L34
                if (r0 == 0) goto L34
                java.net.URL r3 = r0.getResource(r4)
                if (r3 != 0) goto L32
                java.lang.ClassLoader r0 = r0.getParent()
                goto L23
            L32:
                r0 = r2
                goto L23
            L34:
                if (r3 != 0) goto L3a
                java.net.URL r3 = java.lang.ClassLoader.getSystemResource(r4)
            L3a:
                if (r3 == 0) goto L72
                java.io.InputStream r0 = r3.openStream()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L50
                java.util.Properties r1 = p175v5.C2015b.f5863a     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c
                r1.load(r0)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c
            L45:
                p161t5.C1917i.m2219a(r0)
                goto L72
            L49:
                r1 = move-exception
                r2 = r0
                goto L6e
            L4c:
                r1 = move-exception
                goto L52
            L4e:
                r1 = move-exception
                goto L6e
            L50:
                r1 = move-exception
                r0 = r2
            L52:
                java.io.PrintStream r4 = java.lang.System.err     // Catch: java.lang.Throwable -> L49
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L49
                r5.<init>()     // Catch: java.lang.Throwable -> L49
                java.lang.String r6 = "Unable to load "
                r5.append(r6)     // Catch: java.lang.Throwable -> L49
                r5.append(r3)     // Catch: java.lang.Throwable -> L49
                java.lang.String r3 = r5.toString()     // Catch: java.lang.Throwable -> L49
                r4.println(r3)     // Catch: java.lang.Throwable -> L49
                java.io.PrintStream r3 = java.lang.System.err     // Catch: java.lang.Throwable -> L49
                r1.printStackTrace(r3)     // Catch: java.lang.Throwable -> L49
                goto L45
            L6e:
                p161t5.C1917i.m2219a(r2)
                throw r1
            L72:
                java.util.Properties r0 = java.lang.System.getProperties()
                java.util.Enumeration r0 = r0.propertyNames()
            L7a:
                boolean r1 = r0.hasMoreElements()
                if (r1 == 0) goto L92
                java.lang.Object r1 = r0.nextElement()
                java.lang.String r1 = (java.lang.String) r1
                java.lang.String r3 = java.lang.System.getProperty(r1)
                if (r3 == 0) goto L7a
                java.util.Properties r4 = p175v5.C2015b.f5863a
                r4.setProperty(r1, r3)
                goto L7a
            L92:
                java.util.Properties r0 = p175v5.C2015b.f5863a
                java.lang.String r1 = "org.eclipse.jetty.util.log.class"
                java.lang.String r3 = "org.eclipse.jetty.util.log.Slf4jLog"
                java.lang.String r0 = r0.getProperty(r1, r3)
                p175v5.C2015b.f5864b = r0
                java.util.Properties r0 = p175v5.C2015b.f5863a
                java.lang.String r1 = "org.eclipse.jetty.util.log.IGNORED"
                java.lang.String r3 = "false"
                java.lang.String r0 = r0.getProperty(r1, r3)
                boolean r0 = java.lang.Boolean.parseBoolean(r0)
                p175v5.C2015b.f5865c = r0
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p175v5.C2015b.a.run():java.lang.Object");
        }
    }

    static {
        AccessController.doPrivileged(new a());
    }

    /* renamed from: a */
    public static InterfaceC2016c m2349a(String str) {
        if (!m2350b()) {
            return null;
        }
        if (str == null) {
            return f5867e;
        }
        InterfaceC2016c interfaceC2016c = (InterfaceC2016c) ((ConcurrentHashMap) f5866d).get(str);
        return interfaceC2016c == null ? f5867e.mo2348b(str) : interfaceC2016c;
    }

    /* renamed from: b */
    public static boolean m2350b() {
        boolean z6 = true;
        if (f5867e != null) {
            return true;
        }
        synchronized (C2015b.class) {
            if (f5868f) {
                if (f5867e == null) {
                    z6 = false;
                }
                return z6;
            }
            f5868f = true;
            try {
                Class clsM940e = C0984a.m940e(C2015b.class, f5864b);
                InterfaceC2016c interfaceC2016c = f5867e;
                if (interfaceC2016c == null || !interfaceC2016c.getClass().equals(clsM940e)) {
                    InterfaceC2016c interfaceC2016c2 = (InterfaceC2016c) clsM940e.newInstance();
                    f5867e = interfaceC2016c2;
                    interfaceC2016c2.mo2351a("Logging to {} via {}", interfaceC2016c2, clsM940e.getName());
                }
            } catch (Throwable th) {
                if (f5865c) {
                    th.printStackTrace();
                }
                if (f5867e == null) {
                    C2017d c2017d = new C2017d(null);
                    f5867e = c2017d;
                    c2017d.mo2351a("Logging to {} via {}", c2017d, C2017d.class.getName());
                }
            }
            return f5867e != null;
        }
    }
}
