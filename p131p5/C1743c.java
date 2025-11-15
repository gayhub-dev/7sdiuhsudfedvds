package p131p5;

import java.util.Collections;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import org.fourthline.cling.model.ServiceReference;
import p006a5.C0027m;
import p006a5.C0035u;
import p006a5.InterfaceC0023i;
import p006a5.InterfaceC0026l;
import p006a5.InterfaceC0028n;
import p006a5.InterfaceC0034t;
import p006a5.InterfaceC0036v;
import p009b.C0413b;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p043f.C0986c;
import p065h5.C1100h;
import p065h5.C1115w;
import p113n5.C1547h;
import p113n5.C1553n;
import p113n5.C1555p;
import p113n5.InterfaceC1548i;
import p161t5.C1910b;
import p161t5.C1918j;
import p161t5.C1928t;
import p161t5.InterfaceC1909a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* compiled from: ContextHandler.java */
/* renamed from: p5.c */
/* loaded from: classes.dex */
public class C1743c extends AbstractC1748h implements InterfaceC1909a, C1555p.a {

    /* renamed from: H */
    public static final InterfaceC2016c f4929H;

    /* renamed from: I */
    public static final ThreadLocal<b> f4930I;

    /* renamed from: A */
    public Object f4931A;

    /* renamed from: B */
    public Object f4932B;

    /* renamed from: C */
    public Map<String, Object> f4933C;

    /* renamed from: D */
    public final CopyOnWriteArrayList<Object> f4934D;

    /* renamed from: E */
    public boolean f4935E;

    /* renamed from: F */
    public boolean f4936F;

    /* renamed from: G */
    public volatile int f4937G;

    /* renamed from: n */
    public b f4938n;

    /* renamed from: o */
    public final C1910b f4939o;

    /* renamed from: p */
    public final C1910b f4940p;

    /* renamed from: q */
    public final Map<String, String> f4941q;

    /* renamed from: s */
    public C1115w f4943s;

    /* renamed from: t */
    public C1745e f4944t;

    /* renamed from: u */
    public EventListener[] f4945u;

    /* renamed from: v */
    public InterfaceC2016c f4946v;

    /* renamed from: y */
    public Object f4949y;

    /* renamed from: z */
    public Object f4950z;

    /* renamed from: r */
    public String f4942r = ServiceReference.DELIMITER;

    /* renamed from: w */
    public int f4947w = Integer.getInteger("org.eclipse.jetty.server.Request.maxFormKeys", -1).intValue();

    /* renamed from: x */
    public int f4948x = Integer.getInteger("org.eclipse.jetty.server.Request.maxFormContentSize", -1).intValue();

    /* compiled from: ContextHandler.java */
    /* renamed from: p5.c$a */
    public static class a {
    }

    /* compiled from: ContextHandler.java */
    /* renamed from: p5.c$b */
    public class b implements InterfaceC0026l {
        public b() {
        }

        /* renamed from: a */
        public synchronized Object m1904a(String str) {
            Object obj;
            C1910b c1910b;
            obj = C1743c.this.f4939o.f5612e.get(str);
            if (obj == null && (c1910b = C1743c.this.f4940p) != null) {
                obj = c1910b.f5612e.get(str);
            }
            return obj;
        }

        /* renamed from: b */
        public synchronized Enumeration m1905b() {
            HashSet hashSet;
            hashSet = new HashSet();
            C1910b c1910b = C1743c.this.f4940p;
            if (c1910b != null) {
                Enumeration<String> enumerationM2206c = c1910b.m2206c();
                while (enumerationM2206c.hasMoreElements()) {
                    hashSet.add(enumerationM2206c.nextElement());
                }
            }
            Enumeration<String> enumerationM2206c2 = C1743c.this.f4939o.m2206c();
            while (enumerationM2206c2.hasMoreElements()) {
                hashSet.add(enumerationM2206c2.nextElement());
            }
            return Collections.enumeration(hashSet);
        }

        @Override // p006a5.InterfaceC0026l
        /* renamed from: d */
        public String mo16d() {
            String str = C1743c.this.f4942r;
            return (str == null || !str.equals(ServiceReference.DELIMITER)) ? C1743c.this.f4942r : "";
        }

        @Override // p006a5.InterfaceC0026l
        /* renamed from: f */
        public InterfaceC0023i mo17f(String str) {
            String strSubstring;
            if (str == null || !str.startsWith(ServiceReference.DELIMITER)) {
                return null;
            }
            try {
                int iIndexOf = str.indexOf(63);
                if (iIndexOf > 0) {
                    strSubstring = str.substring(iIndexOf + 1);
                    str = str.substring(0, iIndexOf);
                } else {
                    strSubstring = null;
                }
                String strM2265d = C1928t.m2265d(C1928t.m2267j(str));
                if (strM2265d != null) {
                    return new C1547h(C1743c.this, C1928t.m2264b(mo16d(), str), strM2265d, strSubstring);
                }
            } catch (Exception e7) {
                C1743c.f4929H.mo2360k(e7);
            }
            return null;
        }

        @Override // p006a5.InterfaceC0026l
        /* renamed from: g */
        public void mo18g(String str, Throwable th) {
            C1743c.this.f4946v.mo2354e(str, th);
        }

        @Override // p006a5.InterfaceC0026l
        public void log(String str) {
            C1743c.this.f4946v.mo2357h(str, new Object[0]);
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("ServletContext@");
            sbM112a.append(C1743c.this.toString());
            return sbM112a.toString();
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f4929H = C2015b.m2349a(C1743c.class.getName());
        f4930I = new ThreadLocal<>();
    }

    public C1743c() {
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.f4934D = copyOnWriteArrayList;
        this.f4935E = false;
        this.f4936F = true;
        this.f4938n = new b();
        this.f4939o = new C1910b();
        this.f4940p = new C1910b();
        this.f4941q = new HashMap();
        copyOnWriteArrayList.add(new a());
    }

    /* renamed from: W */
    public static b m1894W() {
        return f4930I.get();
    }

    @Override // p113n5.C1555p.a
    /* renamed from: B */
    public void mo1805B(boolean z6) {
        synchronized (this) {
            this.f4935E = z6;
            this.f4937G = isRunning() ? this.f4935E ? 2 : this.f4936F ? 1 : 3 : 0;
        }
    }

    @Override // p131p5.AbstractC1748h
    /* renamed from: Q */
    public void mo1895Q(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        int i7 = c1553n.f4644m;
        boolean z6 = c1553n.f4640i;
        c1553n.f4640i = false;
        try {
            if (z6) {
                try {
                    Object obj = this.f4931A;
                    if (obj != null) {
                        int iM2228x = C1918j.m2228x(obj);
                        for (int i8 = 0; i8 < iM2228x; i8++) {
                            c1553n.m1783i((EventListener) C1918j.m2225j(this.f4931A, i8));
                        }
                    }
                    Object obj2 = this.f4950z;
                    if (obj2 != null) {
                        int iM2228x2 = C1918j.m2228x(obj2);
                        C0035u c0035u = new C0035u(this.f4938n, interfaceC0458c);
                        for (int i9 = 0; i9 < iM2228x2; i9++) {
                            ((InterfaceC0036v) C1918j.m2225j(this.f4950z, i9)).m40o(c0035u);
                        }
                    }
                } catch (C1100h e7) {
                    f4929H.mo2359j(e7);
                    c1553n.f4647p = true;
                    interfaceC0460e.mo175A(e7.f2282e, e7.f2283f);
                    if (!z6) {
                        return;
                    }
                    if (this.f4950z != null) {
                        C0035u c0035u2 = new C0035u(this.f4938n, interfaceC0458c);
                        int iM2228x3 = C1918j.m2228x(this.f4950z);
                        while (true) {
                            int i10 = iM2228x3 - 1;
                            if (iM2228x3 <= 0) {
                                break;
                            }
                            ((InterfaceC0036v) C1918j.m2225j(this.f4950z, i10)).m39e(c0035u2);
                            iM2228x3 = i10;
                        }
                    }
                    Object obj3 = this.f4931A;
                    if (obj3 == null) {
                        return;
                    }
                    int iM2228x4 = C1918j.m2228x(obj3);
                    while (true) {
                        int i11 = iM2228x4 - 1;
                        if (iM2228x4 <= 0) {
                            return;
                        }
                        c1553n.m1790v((EventListener) C1918j.m2225j(this.f4931A, i11));
                        iM2228x4 = i11;
                    }
                }
            }
            C0986c.m949b(3, i7);
            AbstractC1748h abstractC1748h = this.f4963l;
            if (abstractC1748h == null || abstractC1748h != this.f4960j) {
                InterfaceC1548i interfaceC1548i = this.f4960j;
                if (interfaceC1548i != null) {
                    interfaceC1548i.mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
                }
            } else {
                abstractC1748h.mo1895Q(str, c1553n, interfaceC0458c, interfaceC0460e);
            }
            if (!z6) {
                return;
            }
            if (this.f4950z != null) {
                C0035u c0035u3 = new C0035u(this.f4938n, interfaceC0458c);
                int iM2228x5 = C1918j.m2228x(this.f4950z);
                while (true) {
                    int i12 = iM2228x5 - 1;
                    if (iM2228x5 <= 0) {
                        break;
                    }
                    ((InterfaceC0036v) C1918j.m2225j(this.f4950z, i12)).m39e(c0035u3);
                    iM2228x5 = i12;
                }
            }
            Object obj4 = this.f4931A;
            if (obj4 == null) {
                return;
            }
            int iM2228x6 = C1918j.m2228x(obj4);
            while (true) {
                int i13 = iM2228x6 - 1;
                if (iM2228x6 <= 0) {
                    return;
                }
                c1553n.m1790v((EventListener) C1918j.m2225j(this.f4931A, i13));
                iM2228x6 = i13;
            }
        } catch (Throwable th) {
            if (z6) {
                if (this.f4950z != null) {
                    C0035u c0035u4 = new C0035u(this.f4938n, interfaceC0458c);
                    int iM2228x7 = C1918j.m2228x(this.f4950z);
                    while (true) {
                        int i14 = iM2228x7 - 1;
                        if (iM2228x7 <= 0) {
                            break;
                        }
                        ((InterfaceC0036v) C1918j.m2225j(this.f4950z, i14)).m39e(c0035u4);
                        iM2228x7 = i14;
                    }
                }
                Object obj5 = this.f4931A;
                if (obj5 != null) {
                    int iM2228x8 = C1918j.m2228x(obj5);
                    while (true) {
                        int i15 = iM2228x8 - 1;
                        if (iM2228x8 <= 0) {
                            break;
                        }
                        c1553n.m1790v((EventListener) C1918j.m2225j(this.f4931A, i15));
                        iM2228x8 = i15;
                    }
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:160:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x00e9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x00ea  */
    @Override // p131p5.AbstractC1748h
    /* renamed from: R */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo1896R(java.lang.String r18, p113n5.C1553n r19, p015b5.InterfaceC0458c r20, p015b5.InterfaceC0460e r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p131p5.C1743c.mo1896R(java.lang.String, n5.n, b5.c, b5.e):void");
    }

    /* renamed from: T */
    public void m1897T(EventListener eventListener) {
        if (!isStarted() && !isStarting()) {
            this.f4932B = C1918j.m2222b(this.f4932B, eventListener);
        }
        m1902Z((EventListener[]) C1918j.m2223d(this.f4945u, eventListener, EventListener.class));
    }

    /* renamed from: U */
    public void mo1898U(InterfaceC0028n interfaceC0028n, C0027m c0027m) {
        interfaceC0028n.m21n(c0027m);
    }

    /* renamed from: V */
    public void m1899V(String str, Object obj) {
        Map<String, Object> map = this.f4933C;
        if (map == null || !map.containsKey(str)) {
            return;
        }
        this.f4928h.f4670k.m2314f(this, this.f4933C.put(str, obj), obj, str, true);
    }

    /* renamed from: X */
    public C1745e m1900X() {
        return this.f4944t;
    }

    /* renamed from: Y */
    public void m1901Y(String str) {
        if (str.length() > 1 && str.endsWith(ServiceReference.DELIMITER)) {
            throw new IllegalArgumentException("ends with /");
        }
        this.f4942r = str;
        C1555p c1555p = this.f4928h;
        if (c1555p != null) {
            if (c1555p.isStarting() || this.f4928h.isStarted()) {
                InterfaceC1548i[] interfaceC1548iArr = (InterfaceC1548i[]) C1918j.m2221I(this.f4928h.mo1891M(null, C1744d.class), C1744d.class);
                for (int i7 = 0; interfaceC1548iArr != null && i7 < interfaceC1548iArr.length; i7++) {
                    ((C1744d) interfaceC1548iArr[i7]).m1907Q();
                }
            }
        }
    }

    /* renamed from: Z */
    public void m1902Z(EventListener[] eventListenerArr) {
        this.f4949y = null;
        this.f4950z = null;
        this.f4931A = null;
        this.f4945u = eventListenerArr;
        for (int i7 = 0; eventListenerArr != null && i7 < eventListenerArr.length; i7++) {
            EventListener eventListener = this.f4945u[i7];
            if (eventListener instanceof InterfaceC0028n) {
                this.f4949y = C1918j.m2222b(this.f4949y, eventListener);
            }
            if (eventListener instanceof InterfaceC0036v) {
                this.f4950z = C1918j.m2222b(this.f4950z, eventListener);
            }
            if (eventListener instanceof InterfaceC0034t) {
                this.f4931A = C1918j.m2222b(this.f4931A, eventListener);
            }
        }
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: a */
    public Object mo892a(String str) {
        return this.f4939o.f5612e.get(str);
    }

    /* renamed from: a0 */
    public void mo1903a0() {
        String str = this.f4941q.get("org.eclipse.jetty.server.context.ManagedAttributes");
        if (str != null) {
            this.f4933C = new HashMap();
            for (String str2 : str.split(",")) {
                this.f4933C.put(str2, null);
            }
            Enumeration enumerationM1905b = this.f4938n.m1905b();
            while (enumerationM1905b.hasMoreElements()) {
                String str3 = (String) enumerationM1905b.nextElement();
                m1899V(str3, this.f4938n.m1904a(str3));
            }
        }
        super.doStart();
        C1745e c1745e = this.f4944t;
        if (c1745e != null) {
            c1745e.start();
        }
        if (this.f4949y != null) {
            C0027m c0027m = new C0027m(this.f4938n);
            for (int i7 = 0; i7 < C1918j.m2228x(this.f4949y); i7++) {
                mo1898U((InterfaceC0028n) C1918j.m2225j(this.f4949y, i7), c0027m);
            }
        }
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: b */
    public void mo893b(String str, Object obj) {
        m1899V(str, obj);
        C1910b c1910b = this.f4939o;
        if (obj == null) {
            c1910b.f5612e.remove(str);
        } else {
            c1910b.f5612e.put(str, obj);
        }
    }

    @Override // p131p5.AbstractC1748h, p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() throws Throwable {
        ThreadLocal<b> threadLocal;
        b bVar;
        this.f4937G = 0;
        String str = this.f4942r;
        if (str == null) {
            throw new IllegalStateException("Null contextPath");
        }
        this.f4946v = C2015b.m2349a(str);
        b bVar2 = null;
        try {
            if (this.f4943s == null) {
                this.f4943s = new C1115w();
            }
            threadLocal = f4930I;
            bVar = threadLocal.get();
        } catch (Throwable th) {
            th = th;
        }
        try {
            threadLocal.set(this.f4938n);
            mo1903a0();
            synchronized (this) {
                this.f4937G = this.f4935E ? 2 : this.f4936F ? 1 : 3;
            }
            threadLocal.set(bVar);
        } catch (Throwable th2) {
            th = th2;
            bVar2 = bVar;
            f4930I.set(bVar2);
            throw th;
        }
    }

    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() {
        this.f4937G = 0;
        ThreadLocal<b> threadLocal = f4930I;
        b bVar = threadLocal.get();
        threadLocal.set(this.f4938n);
        try {
            super.doStop();
            if (this.f4949y != null) {
                C0027m c0027m = new C0027m(this.f4938n);
                int iM2228x = C1918j.m2228x(this.f4949y);
                while (true) {
                    int i7 = iM2228x - 1;
                    if (iM2228x <= 0) {
                        break;
                    }
                    ((InterfaceC0028n) C1918j.m2225j(this.f4949y, i7)).m20m(c0027m);
                    iM2228x = i7;
                }
            }
            m1902Z((EventListener[]) C1918j.m2221I(this.f4932B, EventListener.class));
            this.f4932B = null;
            C1745e c1745e = this.f4944t;
            if (c1745e != null) {
                c1745e.stop();
            }
            Enumeration enumerationM1905b = this.f4938n.m1905b();
            while (enumerationM1905b.hasMoreElements()) {
                m1899V((String) enumerationM1905b.nextElement(), null);
            }
            f4929H.mo2357h("stopped {}", this);
            f4930I.set(bVar);
            this.f4940p.f5612e.clear();
        } catch (Throwable th) {
            f4929H.mo2357h("stopped {}", this);
            f4930I.set(bVar);
            throw th;
        }
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: e */
    public void mo894e(String str) {
        m1899V(str, null);
        this.f4939o.f5612e.remove(str);
    }

    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p113n5.InterfaceC1548i
    /* renamed from: h */
    public void mo1772h(C1555p c1555p) {
        C1745e c1745e = this.f4944t;
        if (c1745e == null) {
            super.mo1772h(c1555p);
            return;
        }
        C1555p c1555p2 = this.f4928h;
        if (c1555p2 != null && c1555p2 != c1555p) {
            c1555p2.f4670k.m2314f(this, c1745e, null, IjkMediaPlayer.OnNativeInvokeListener.ARG_ERROR, true);
        }
        super.mo1772h(c1555p);
        if (c1555p != null && c1555p != c1555p2) {
            c1555p.f4670k.m2314f(this, null, this.f4944t, IjkMediaPlayer.OnNativeInvokeListener.ARG_ERROR, true);
        }
        this.f4944t.mo1772h(c1555p);
    }

    public String toString() {
        String name;
        StringBuilder sb = new StringBuilder();
        Package r12 = getClass().getPackage();
        if (r12 != null && (name = r12.getName()) != null && name.length() > 0) {
            for (String str : name.split("\\.")) {
                sb.append(str.charAt(0));
                sb.append('.');
            }
        }
        sb.append(getClass().getSimpleName());
        sb.append('{');
        sb.append(this.f4942r);
        sb.append(',');
        sb.append((Object) null);
        sb.append('}');
        return sb.toString();
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: v */
    public void mo896v() {
        Enumeration<String> enumerationM2206c = this.f4939o.m2206c();
        while (enumerationM2206c.hasMoreElements()) {
            m1899V(enumerationM2206c.nextElement(), null);
        }
        this.f4939o.f5612e.clear();
    }

    public C1743c(b bVar) {
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.f4934D = copyOnWriteArrayList;
        this.f4935E = false;
        this.f4936F = true;
        this.f4938n = null;
        this.f4939o = new C1910b();
        this.f4940p = new C1910b();
        this.f4941q = new HashMap();
        copyOnWriteArrayList.add(new a());
    }
}
