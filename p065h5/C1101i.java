package p065h5;

import android.support.constraint.C0072a;
import android.support.v7.widget.RecyclerView;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import okhttp3.internal.cache.DiskLruCache;
import p009b.C0413b;
import p073i5.C1153f;
import p073i5.C1154g;
import p073i5.C1155h;
import p073i5.C1158k;
import p073i5.InterfaceC1152e;
import p082j6.C1212a;
import p161t5.C1925q;
import p161t5.C1926r;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: HttpFields.java */
/* renamed from: h5.i */
/* loaded from: classes.dex */
public class C1101i {

    /* renamed from: c */
    public static final InterfaceC2016c f2284c;

    /* renamed from: d */
    public static final TimeZone f2285d;

    /* renamed from: e */
    public static final C1154g f2286e;

    /* renamed from: f */
    public static final String[] f2287f;

    /* renamed from: g */
    public static final String[] f2288g;

    /* renamed from: h */
    public static final ThreadLocal<c> f2289h;

    /* renamed from: i */
    public static final String[] f2290i;

    /* renamed from: j */
    public static final ThreadLocal<d> f2291j;

    /* renamed from: k */
    public static final InterfaceC1152e f2292k;

    /* renamed from: l */
    public static final String f2293l;

    /* renamed from: m */
    public static ConcurrentMap<String, InterfaceC1152e> f2294m;

    /* renamed from: n */
    public static int f2295n;

    /* renamed from: a */
    public final ArrayList<e> f2296a = new ArrayList<>(20);

    /* renamed from: b */
    public final HashMap<InterfaceC1152e, e> f2297b = new HashMap<>(32);

    /* compiled from: HttpFields.java */
    /* renamed from: h5.i$a */
    public static class a extends ThreadLocal<c> {
        @Override // java.lang.ThreadLocal
        public c initialValue() {
            return new c(null);
        }
    }

    /* compiled from: HttpFields.java */
    /* renamed from: h5.i$b */
    public static class b extends ThreadLocal<d> {
        @Override // java.lang.ThreadLocal
        public d initialValue() {
            return new d(null);
        }
    }

    /* compiled from: HttpFields.java */
    /* renamed from: h5.i$e */
    public static final class e {

        /* renamed from: a */
        public InterfaceC1152e f2301a;

        /* renamed from: b */
        public InterfaceC1152e f2302b;

        /* renamed from: c */
        public e f2303c = null;

        public e(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2, a aVar) {
            this.f2301a = interfaceC1152e;
            this.f2302b = interfaceC1152e2;
        }

        /* renamed from: a */
        public String m1233a() {
            return C1155h.m1365c(this.f2302b);
        }

        /* renamed from: b */
        public void m1234b(InterfaceC1152e interfaceC1152e) {
            InterfaceC1152e interfaceC1152e2 = this.f2301a;
            if ((interfaceC1152e2 instanceof C1153f.a ? ((C1153f.a) interfaceC1152e2).f2542r : -1) >= 0) {
                interfaceC1152e.mo1337t(interfaceC1152e2);
            } else {
                int iMo1316C = interfaceC1152e2.mo1316C();
                int iMo1322M = this.f2301a.mo1322M();
                while (iMo1316C < iMo1322M) {
                    int i7 = iMo1316C + 1;
                    byte bMo1348H = this.f2301a.mo1348H(iMo1316C);
                    if (bMo1348H != 10 && bMo1348H != 13 && bMo1348H != 58) {
                        interfaceC1152e.mo1321L(bMo1348H);
                    }
                    iMo1316C = i7;
                }
            }
            interfaceC1152e.mo1321L((byte) 58);
            interfaceC1152e.mo1321L((byte) 32);
            InterfaceC1152e interfaceC1152e3 = this.f2302b;
            if ((interfaceC1152e3 instanceof C1153f.a ? ((C1153f.a) interfaceC1152e3).f2542r : -1) >= 0) {
                interfaceC1152e.mo1337t(interfaceC1152e3);
            } else {
                int iMo1316C2 = interfaceC1152e3.mo1316C();
                int iMo1322M2 = this.f2302b.mo1322M();
                while (iMo1316C2 < iMo1322M2) {
                    int i8 = iMo1316C2 + 1;
                    byte bMo1348H2 = this.f2302b.mo1348H(iMo1316C2);
                    if (bMo1348H2 != 10 && bMo1348H2 != 13) {
                        interfaceC1152e.mo1321L(bMo1348H2);
                    }
                    iMo1316C2 = i8;
                }
            }
            interfaceC1152e.mo1321L(C1212a.f2735CR);
            interfaceC1152e.mo1321L((byte) 10);
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("[");
            sbM112a.append(C1155h.m1365c(this.f2301a));
            sbM112a.append("=");
            sbM112a.append(this.f2302b);
            return C0072a.m92a(sbM112a, this.f2303c == null ? "" : "->", "]");
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f2284c = C2015b.m2349a(C1101i.class.getName());
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        f2285d = timeZone;
        C1154g c1154g = new C1154g("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        f2286e = c1154g;
        timeZone.setID("GMT");
        c1154g.m2217c(timeZone);
        f2287f = new String[]{"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        f2288g = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan"};
        f2289h = new a();
        f2290i = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss", "EEE MMM dd HH:mm:ss yyyy", "EEE, dd MMM yyyy HH:mm:ss", "EEE dd MMM yyyy HH:mm:ss zzz", "EEE dd MMM yyyy HH:mm:ss", "EEE MMM dd yyyy HH:mm:ss zzz", "EEE MMM dd yyyy HH:mm:ss", "EEE MMM-dd-yyyy HH:mm:ss zzz", "EEE MMM-dd-yyyy HH:mm:ss", "dd MMM yyyy HH:mm:ss zzz", "dd MMM yyyy HH:mm:ss", "dd-MMM-yy HH:mm:ss zzz", "dd-MMM-yy HH:mm:ss", "MMM dd HH:mm:ss yyyy zzz", "MMM dd HH:mm:ss yyyy", "EEE MMM dd HH:mm:ss yyyy zzz", "EEE, MMM dd HH:mm:ss yyyy zzz", "EEE, MMM dd HH:mm:ss yyyy", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE dd-MMM-yy HH:mm:ss zzz", "EEE dd-MMM-yy HH:mm:ss"};
        f2291j = new b();
        f2292k = new C1158k(m1220e(0L));
        StringBuilder sb = new StringBuilder(28);
        m1219d(sb, 0L);
        f2293l = sb.toString().trim();
        f2294m = new ConcurrentHashMap();
        f2295n = Integer.getInteger("org.eclipse.jetty.http.HttpFields.CACHE", RecyclerView.MAX_SCROLL_DURATION).intValue();
        Float f7 = new Float("1.0");
        Float f8 = new Float("0.0");
        C1925q c1925q = new C1925q();
        c1925q.m2247c(null, f7);
        c1925q.m2247c("1.0", f7);
        c1925q.m2247c(DiskLruCache.VERSION_1, f7);
        c1925q.m2247c("0.9", new Float("0.9"));
        c1925q.m2247c("0.8", new Float("0.8"));
        c1925q.m2247c("0.7", new Float("0.7"));
        c1925q.m2247c("0.66", new Float("0.66"));
        c1925q.m2247c("0.6", new Float("0.6"));
        c1925q.m2247c("0.5", new Float("0.5"));
        c1925q.m2247c("0.4", new Float("0.4"));
        c1925q.m2247c("0.33", new Float("0.33"));
        c1925q.m2247c("0.3", new Float("0.3"));
        c1925q.m2247c("0.2", new Float("0.2"));
        c1925q.m2247c("0.1", new Float("0.1"));
        c1925q.m2247c("0", f8);
        c1925q.m2247c("0.0", f8);
    }

    /* renamed from: d */
    public static void m1219d(StringBuilder sb, long j7) {
        c cVar = f2289h.get();
        cVar.f2299b.setTimeInMillis(j7);
        int i7 = cVar.f2299b.get(7);
        int i8 = cVar.f2299b.get(5);
        int i9 = cVar.f2299b.get(2);
        int i10 = cVar.f2299b.get(1) % 10000;
        int i11 = (int) ((j7 / 1000) % 86400);
        int i12 = i11 % 60;
        int i13 = i11 / 60;
        sb.append(f2287f[i7]);
        sb.append(',');
        sb.append(' ');
        C1926r.m2251a(sb, i8);
        sb.append('-');
        sb.append(f2288g[i9]);
        sb.append('-');
        C1926r.m2251a(sb, i10 / 100);
        C1926r.m2251a(sb, i10 % 100);
        sb.append(' ');
        C1926r.m2251a(sb, i13 / 60);
        sb.append(':');
        C1926r.m2251a(sb, i13 % 60);
        sb.append(':');
        C1926r.m2251a(sb, i12);
        sb.append(" GMT");
    }

    /* renamed from: e */
    public static String m1220e(long j7) {
        c cVar = f2289h.get();
        cVar.f2298a.setLength(0);
        cVar.f2299b.setTimeInMillis(j7);
        int i7 = cVar.f2299b.get(7);
        int i8 = cVar.f2299b.get(5);
        int i9 = cVar.f2299b.get(2);
        int i10 = cVar.f2299b.get(1);
        int i11 = cVar.f2299b.get(11);
        int i12 = cVar.f2299b.get(12);
        int i13 = cVar.f2299b.get(13);
        cVar.f2298a.append(f2287f[i7]);
        cVar.f2298a.append(',');
        cVar.f2298a.append(' ');
        C1926r.m2251a(cVar.f2298a, i8);
        cVar.f2298a.append(' ');
        cVar.f2298a.append(f2288g[i9]);
        cVar.f2298a.append(' ');
        C1926r.m2251a(cVar.f2298a, i10 / 100);
        C1926r.m2251a(cVar.f2298a, i10 % 100);
        cVar.f2298a.append(' ');
        C1926r.m2251a(cVar.f2298a, i11);
        cVar.f2298a.append(':');
        C1926r.m2251a(cVar.f2298a, i12);
        cVar.f2298a.append(':');
        C1926r.m2251a(cVar.f2298a, i13);
        cVar.f2298a.append(" GMT");
        return cVar.f2298a.toString();
    }

    /* renamed from: n */
    public static String m1221n(String str, Map<String, String> map) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf(59);
        return iIndexOf < 0 ? str : str.substring(0, iIndexOf).trim();
    }

    /* renamed from: a */
    public void m1222a(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
        if (interfaceC1152e2 == null) {
            throw new IllegalArgumentException("null value");
        }
        if (!(interfaceC1152e instanceof C1153f.a)) {
            interfaceC1152e = C1107o.f2326d.m1359g(interfaceC1152e);
        }
        InterfaceC1152e interfaceC1152eMo1325T = interfaceC1152e.mo1325T();
        if (!(interfaceC1152e2 instanceof C1153f.a)) {
            int iM1357e = C1107o.f2326d.m1357e(interfaceC1152eMo1325T);
            C1106n c1106n = C1106n.f2323d;
            boolean z6 = true;
            if (iM1357e != 1 && iM1357e != 5 && iM1357e != 10) {
                z6 = false;
            }
            if (z6) {
                interfaceC1152e2 = C1106n.f2323d.m1359g(interfaceC1152e2);
            }
        }
        InterfaceC1152e interfaceC1152eMo1325T2 = interfaceC1152e2.mo1325T();
        e eVar = null;
        for (e eVar2 = this.f2297b.get(interfaceC1152eMo1325T); eVar2 != null; eVar2 = eVar2.f2303c) {
            eVar = eVar2;
        }
        e eVar3 = new e(interfaceC1152eMo1325T, interfaceC1152eMo1325T2, null);
        this.f2296a.add(eVar3);
        if (eVar != null) {
            eVar.f2303c = eVar3;
        } else {
            this.f2297b.put(interfaceC1152eMo1325T, eVar3);
        }
    }

    /* renamed from: b */
    public void m1223b() {
        this.f2296a.clear();
        this.f2297b.clear();
    }

    /* renamed from: c */
    public final InterfaceC1152e m1224c(String str) {
        InterfaceC1152e interfaceC1152e = (InterfaceC1152e) ((ConcurrentHashMap) f2294m).get(str);
        if (interfaceC1152e != null) {
            return interfaceC1152e;
        }
        try {
            C1158k c1158k = new C1158k(str, "ISO-8859-1");
            if (f2295n <= 0) {
                return c1158k;
            }
            if (((ConcurrentHashMap) f2294m).size() > f2295n) {
                ((ConcurrentHashMap) f2294m).clear();
            }
            InterfaceC1152e interfaceC1152e2 = (InterfaceC1152e) ((ConcurrentHashMap) f2294m).putIfAbsent(str, c1158k);
            return interfaceC1152e2 != null ? interfaceC1152e2 : c1158k;
        } catch (UnsupportedEncodingException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* renamed from: f */
    public final e m1225f(InterfaceC1152e interfaceC1152e) {
        return this.f2297b.get(C1107o.f2326d.m1359g(interfaceC1152e));
    }

    /* renamed from: g */
    public final e m1226g(String str) {
        return this.f2297b.get(C1107o.f2326d.m1360h(str));
    }

    /* renamed from: h */
    public String m1227h(String str) {
        e eVarM1226g = m1226g(str);
        if (eVarM1226g == null) {
            return null;
        }
        return eVarM1226g.m1233a();
    }

    /* renamed from: i */
    public void m1228i(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
        m1232m(interfaceC1152e);
        if (interfaceC1152e2 == null) {
            return;
        }
        if (!(interfaceC1152e instanceof C1153f.a)) {
            interfaceC1152e = C1107o.f2326d.m1359g(interfaceC1152e);
        }
        if (!(interfaceC1152e2 instanceof C1153f.a)) {
            interfaceC1152e2 = C1106n.f2323d.m1359g(interfaceC1152e2).mo1325T();
        }
        e eVar = new e(interfaceC1152e, interfaceC1152e2, null);
        this.f2296a.add(eVar);
        this.f2297b.put(interfaceC1152e, eVar);
    }

    /* renamed from: j */
    public void m1229j(InterfaceC1152e interfaceC1152e, String str) {
        m1228i(C1107o.f2326d.m1359g(interfaceC1152e), m1224c(str));
    }

    /* renamed from: k */
    public void m1230k(String str, String str2) {
        if (str2 == null) {
            m1232m(C1107o.f2326d.m1360h(str));
        } else {
            m1228i(C1107o.f2326d.m1360h(str), m1224c(str2));
        }
    }

    /* renamed from: l */
    public void m1231l(InterfaceC1152e interfaceC1152e, long j7) {
        m1228i(interfaceC1152e, new C1158k(m1220e(j7)));
    }

    /* renamed from: m */
    public void m1232m(InterfaceC1152e interfaceC1152e) {
        if (!(interfaceC1152e instanceof C1153f.a)) {
            interfaceC1152e = C1107o.f2326d.m1359g(interfaceC1152e);
        }
        for (e eVarRemove = this.f2297b.remove(interfaceC1152e); eVarRemove != null; eVarRemove = eVarRemove.f2303c) {
            this.f2296a.remove(eVarRemove);
        }
    }

    public String toString() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i7 = 0; i7 < this.f2296a.size(); i7++) {
                e eVar = this.f2296a.get(i7);
                if (eVar != null) {
                    String strM1365c = C1155h.m1365c(eVar.f2301a);
                    if (strM1365c != null) {
                        stringBuffer.append(strM1365c);
                    }
                    stringBuffer.append(": ");
                    String strM1233a = eVar.m1233a();
                    if (strM1233a != null) {
                        stringBuffer.append(strM1233a);
                    }
                    stringBuffer.append("\r\n");
                }
            }
            stringBuffer.append("\r\n");
            return stringBuffer.toString();
        } catch (Exception e7) {
            f2284c.mo2358i(e7);
            return e7.toString();
        }
    }

    /* compiled from: HttpFields.java */
    /* renamed from: h5.i$c */
    public static class c {

        /* renamed from: a */
        public final StringBuilder f2298a = new StringBuilder(32);

        /* renamed from: b */
        public final GregorianCalendar f2299b = new GregorianCalendar(C1101i.f2285d);

        public c() {
        }

        public c(a aVar) {
        }
    }

    /* compiled from: HttpFields.java */
    /* renamed from: h5.i$d */
    public static class d {

        /* renamed from: a */
        public final SimpleDateFormat[] f2300a;

        public d() {
            this.f2300a = new SimpleDateFormat[C1101i.f2290i.length];
        }

        public d(a aVar) {
            this.f2300a = new SimpleDateFormat[C1101i.f2290i.length];
        }
    }
}
