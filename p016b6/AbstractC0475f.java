package p016b6;

import android.support.constraint.motion.C0079a;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import android.support.v7.widget.RecyclerView;
import com.ctvit.network.CtvitHttp;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import org.joda.convert.FromString;
import p058g6.C1064b;
import p058g6.C1065c;
import p058g6.C1067e;
import p058g6.C1070h;
import p058g6.InterfaceC1072j;
import p066h6.C1120c;
import p066h6.C1121d;
import p066h6.C1124g;
import p066h6.C1125h;
import p066h6.InterfaceC1122e;
import p066h6.InterfaceC1123f;

/* compiled from: DateTimeZone.java */
/* renamed from: b6.f */
/* loaded from: classes.dex */
public abstract class AbstractC0475f implements Serializable {

    /* renamed from: f */
    public static final AbstractC0475f f314f = C0495z.f354j;

    /* renamed from: g */
    public static final AtomicReference<InterfaceC1123f> f315g = new AtomicReference<>();

    /* renamed from: h */
    public static final AtomicReference<InterfaceC1122e> f316h = new AtomicReference<>();

    /* renamed from: i */
    public static final AtomicReference<AbstractC0475f> f317i = new AtomicReference<>();
    private static final long serialVersionUID = 5546345482340108586L;

    /* renamed from: e */
    public final String f318e;

    /* compiled from: DateTimeZone.java */
    /* renamed from: b6.f$a */
    public static final class a {

        /* renamed from: a */
        public static final Map<String, String> f319a;

        /* renamed from: b */
        public static final C1064b f320b;

        static {
            HashMap map = new HashMap();
            map.put("GMT", "UTC");
            map.put("WET", "WET");
            map.put("CET", "CET");
            map.put("MET", "CET");
            map.put("ECT", "CET");
            map.put("EET", "EET");
            map.put("MIT", "Pacific/Apia");
            map.put("HST", "Pacific/Honolulu");
            map.put("AST", "America/Anchorage");
            map.put("PST", "America/Los_Angeles");
            map.put("MST", "America/Denver");
            map.put("PNT", "America/Phoenix");
            map.put("CST", "America/Chicago");
            map.put("EST", "America/New_York");
            map.put("IET", "America/Indiana/Indianapolis");
            map.put("PRT", "America/Puerto_Rico");
            map.put("CNT", "America/St_Johns");
            map.put("AGT", "America/Argentina/Buenos_Aires");
            map.put("BET", "America/Sao_Paulo");
            map.put("ART", "Africa/Cairo");
            map.put("CAT", "Africa/Harare");
            map.put("EAT", "Africa/Addis_Ababa");
            map.put("NET", "Asia/Yerevan");
            map.put("PLT", "Asia/Karachi");
            map.put("IST", "Asia/Kolkata");
            map.put("BST", "Asia/Dhaka");
            map.put("VST", "Asia/Ho_Chi_Minh");
            map.put("CTT", "Asia/Shanghai");
            map.put("JST", "Asia/Tokyo");
            map.put("ACT", "Australia/Darwin");
            map.put("AET", "Australia/Sydney");
            map.put("SST", "Pacific/Guadalcanal");
            map.put("NST", "Pacific/Auckland");
            f319a = Collections.unmodifiableMap(map);
            C0474e c0474e = new C0474e();
            C1065c c1065c = new C1065c();
            c1065c.m1086p(null, true, 2, 4);
            C1064b c1064bM1091x = c1065c.m1091x();
            if (c1064bM1091x.f2015e != c0474e) {
                c1064bM1091x = new C1064b(c1064bM1091x.f2011a, c1064bM1091x.f2012b, c1064bM1091x.f2013c, c1064bM1091x.f2014d, c0474e, c1064bM1091x.f2016f, c1064bM1091x.f2017g, c1064bM1091x.f2018h);
            }
            f320b = c1064bM1091x;
        }
    }

    /* compiled from: DateTimeZone.java */
    /* renamed from: b6.f$b */
    public static final class b implements Serializable {
        private static final long serialVersionUID = -6471952376487863581L;

        /* renamed from: e */
        public transient String f321e;

        public b(String str) {
            this.f321e = str;
        }

        private void readObject(ObjectInputStream objectInputStream) {
            this.f321e = objectInputStream.readUTF();
        }

        private Object readResolve() {
            return AbstractC0475f.m232d(this.f321e);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeUTF(this.f321e);
        }
    }

    public AbstractC0475f(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        this.f318e = str;
    }

    /* renamed from: c */
    public static AbstractC0475f m231c(String str, int i7) {
        return i7 == 0 ? f314f : new C1121d(str, null, i7, i7);
    }

    @FromString
    /* renamed from: d */
    public static AbstractC0475f m232d(String str) {
        if (str == null) {
            return m235g();
        }
        if (str.equals("UTC")) {
            return f314f;
        }
        AbstractC0475f abstractC0475fMo1282a = m237m().mo1282a(str);
        if (abstractC0475fMo1282a != null) {
            return abstractC0475fMo1282a;
        }
        if (!str.startsWith("+") && !str.startsWith("-")) {
            throw new IllegalArgumentException(C0096a.m97a("The datetime zone id '", str, "' is not recognised"));
        }
        int iM238q = m238q(str);
        return ((long) iM238q) == 0 ? f314f : m231c(m239s(iM238q), iM238q);
    }

    /* renamed from: e */
    public static AbstractC0475f m233e(int i7) {
        if (i7 < -86399999 || i7 > 86399999) {
            throw new IllegalArgumentException(C0079a.m93a("Millis out of range: ", i7));
        }
        return m231c(m239s(i7), i7);
    }

    /* renamed from: f */
    public static AbstractC0475f m234f(TimeZone timeZone) throws ClassNotFoundException {
        char cCharAt;
        if (timeZone == null) {
            return m235g();
        }
        String id = timeZone.getID();
        if (id == null) {
            throw new IllegalArgumentException("The TimeZone id must not be null");
        }
        if (id.equals("UTC")) {
            return f314f;
        }
        String str = a.f319a.get(id);
        InterfaceC1123f interfaceC1123fM237m = m237m();
        AbstractC0475f abstractC0475fMo1282a = str != null ? interfaceC1123fM237m.mo1282a(str) : null;
        if (abstractC0475fMo1282a == null) {
            abstractC0475fMo1282a = interfaceC1123fM237m.mo1282a(id);
        }
        if (abstractC0475fMo1282a != null) {
            return abstractC0475fMo1282a;
        }
        if (str != null || (!id.startsWith("GMT+") && !id.startsWith("GMT-"))) {
            throw new IllegalArgumentException(C0096a.m97a("The datetime zone id '", id, "' is not recognised"));
        }
        String strSubstring = id.substring(3);
        if (strSubstring.length() > 2 && (cCharAt = strSubstring.charAt(1)) > '9' && Character.isDigit(cCharAt)) {
            StringBuilder sb = new StringBuilder(strSubstring);
            for (int i7 = 0; i7 < sb.length(); i7++) {
                int iDigit = Character.digit(sb.charAt(i7), 10);
                if (iDigit >= 0) {
                    sb.setCharAt(i7, (char) (iDigit + 48));
                }
            }
            strSubstring = sb.toString();
        }
        int iM238q = m238q(strSubstring);
        return ((long) iM238q) == 0 ? f314f : m231c(m239s(iM238q), iM238q);
    }

    /* renamed from: g */
    public static AbstractC0475f m235g() throws ClassNotFoundException {
        AbstractC0475f abstractC0475fM234f = f317i.get();
        if (abstractC0475fM234f != null) {
            return abstractC0475fM234f;
        }
        try {
            String property = System.getProperty("user.timezone");
            if (property != null) {
                abstractC0475fM234f = m232d(property);
            }
        } catch (RuntimeException unused) {
        }
        if (abstractC0475fM234f == null) {
            try {
                abstractC0475fM234f = m234f(TimeZone.getDefault());
            } catch (IllegalArgumentException unused2) {
            }
        }
        if (abstractC0475fM234f == null) {
            abstractC0475fM234f = f314f;
        }
        AtomicReference<AbstractC0475f> atomicReference = f317i;
        return !atomicReference.compareAndSet(null, abstractC0475fM234f) ? atomicReference.get() : abstractC0475fM234f;
    }

    /* renamed from: j */
    public static InterfaceC1122e m236j() throws ClassNotFoundException {
        InterfaceC1122e c1120c;
        String property;
        InterfaceC1122e interfaceC1122e = f316h.get();
        if (interfaceC1122e != null) {
            return interfaceC1122e;
        }
        try {
            property = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
        } catch (SecurityException unused) {
        }
        if (property != null) {
            try {
                Class<?> cls = Class.forName(property, false, AbstractC0475f.class.getClassLoader());
                if (!InterfaceC1122e.class.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException("System property referred to class that does not implement " + InterfaceC1122e.class);
                }
                c1120c = (InterfaceC1122e) cls.asSubclass(InterfaceC1122e.class).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e7) {
                throw new RuntimeException(e7);
            }
        } else {
            c1120c = null;
        }
        if (c1120c == null) {
            c1120c = new C1120c();
        }
        AtomicReference<InterfaceC1122e> atomicReference = f316h;
        return !atomicReference.compareAndSet(null, c1120c) ? atomicReference.get() : c1120c;
    }

    /* renamed from: m */
    public static InterfaceC1123f m237m() throws ClassNotFoundException {
        InterfaceC1123f c1124g;
        String property;
        String property2;
        InterfaceC1123f interfaceC1123f = f315g.get();
        if (interfaceC1123f != null) {
            return interfaceC1123f;
        }
        try {
            property2 = System.getProperty("org.joda.time.DateTimeZone.Provider");
        } catch (SecurityException unused) {
        }
        if (property2 != null) {
            try {
                Class<?> cls = Class.forName(property2, false, AbstractC0475f.class.getClassLoader());
                if (!InterfaceC1123f.class.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException("System property referred to class that does not implement " + InterfaceC1123f.class);
                }
                c1124g = (InterfaceC1123f) cls.asSubclass(InterfaceC1123f.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                m240t(c1124g);
            } catch (Exception e7) {
                throw new RuntimeException(e7);
            }
        } else {
            try {
                property = System.getProperty("org.joda.time.DateTimeZone.Folder");
            } catch (SecurityException unused2) {
            }
            if (property != null) {
                try {
                    C1125h c1125h = new C1125h(new File(property));
                    m240t(c1125h);
                    c1124g = c1125h;
                } catch (Exception e8) {
                    throw new RuntimeException(e8);
                }
            } else {
                try {
                    c1124g = new C1125h("org/joda/time/tz/data");
                    m240t(c1124g);
                } catch (Exception e9) {
                    e9.printStackTrace();
                    c1124g = new C1124g();
                }
            }
        }
        AtomicReference<InterfaceC1123f> atomicReference = f315g;
        return !atomicReference.compareAndSet(null, c1124g) ? atomicReference.get() : c1124g;
    }

    /* renamed from: q */
    public static int m238q(String str) {
        C1064b c1064b = a.f320b;
        InterfaceC1072j interfaceC1072jM1063f = c1064b.m1063f();
        C1067e c1067e = new C1067e(0L, c1064b.m1065h(c1064b.f2015e), c1064b.f2013c, c1064b.f2017g, c1064b.f2018h);
        int iMo1097f = interfaceC1072jM1063f.mo1097f(c1067e, str, 0);
        if (iMo1097f < 0) {
            iMo1097f = ~iMo1097f;
        } else if (iMo1097f >= str.length()) {
            return -((int) c1067e.m1102b(true, str));
        }
        throw new IllegalArgumentException(C1070h.m1113d(str.toString(), iMo1097f));
    }

    /* renamed from: s */
    public static String m239s(int i7) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i7 >= 0) {
            stringBuffer.append('+');
        } else {
            stringBuffer.append('-');
            i7 = -i7;
        }
        int i8 = i7 / 3600000;
        int i9 = C1070h.f2077b;
        try {
            C1070h.m1110a(stringBuffer, i8, 2);
        } catch (IOException unused) {
        }
        int i10 = i7 - (i8 * 3600000);
        int i11 = i10 / CtvitHttp.DEFAULT_MILLISECONDS;
        stringBuffer.append(':');
        try {
            C1070h.m1110a(stringBuffer, i11, 2);
        } catch (IOException unused2) {
        }
        int i12 = i10 - (i11 * CtvitHttp.DEFAULT_MILLISECONDS);
        if (i12 == 0) {
            return stringBuffer.toString();
        }
        int i13 = i12 / 1000;
        stringBuffer.append(':');
        try {
            C1070h.m1110a(stringBuffer, i13, 2);
        } catch (IOException unused3) {
        }
        int i14 = i12 - (i13 * 1000);
        if (i14 == 0) {
            return stringBuffer.toString();
        }
        stringBuffer.append('.');
        try {
            C1070h.m1110a(stringBuffer, i14, 3);
        } catch (IOException unused4) {
        }
        return stringBuffer.toString();
    }

    /* renamed from: t */
    public static InterfaceC1123f m240t(InterfaceC1123f interfaceC1123f) {
        Set<String> setMo1283b = interfaceC1123f.mo1283b();
        if (setMo1283b == null || setMo1283b.size() == 0) {
            throw new IllegalArgumentException("The provider doesn't have any available ids");
        }
        if (!setMo1283b.contains("UTC")) {
            throw new IllegalArgumentException("The provider doesn't support UTC");
        }
        AbstractC0475f abstractC0475f = f314f;
        AbstractC0475f abstractC0475fMo1282a = interfaceC1123f.mo1282a("UTC");
        Objects.requireNonNull((C0495z) abstractC0475f);
        if (abstractC0475fMo1282a instanceof C0495z) {
            return interfaceC1123f;
        }
        throw new IllegalArgumentException("Invalid UTC zone provided");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0049  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long m241a(long r10, boolean r12, long r13) {
        /*
            r9 = this;
            int r13 = r9.mo245k(r13)
            long r0 = (long) r13
            long r0 = r10 - r0
            int r14 = r9.mo245k(r0)
            if (r14 != r13) goto Le
            return r0
        Le:
            int r13 = r9.mo245k(r10)
            long r0 = (long) r13
            long r0 = r10 - r0
            int r14 = r9.mo245k(r0)
            if (r13 == r14) goto L49
            if (r12 != 0) goto L1f
            if (r13 >= 0) goto L49
        L1f:
            long r2 = r9.mo249p(r0)
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r6 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r6 != 0) goto L2d
            r2 = r4
        L2d:
            long r0 = (long) r14
            long r0 = r10 - r0
            long r6 = r9.mo249p(r0)
            int r8 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r8 != 0) goto L39
            goto L3a
        L39:
            r4 = r6
        L3a:
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L49
            if (r12 != 0) goto L41
            goto L4a
        L41:
            b6.k r12 = new b6.k
            java.lang.String r13 = r9.f318e
            r12.<init>(r10, r13)
            throw r12
        L49:
            r13 = r14
        L4a:
            long r12 = (long) r13
            long r0 = r10 - r12
            long r2 = r10 ^ r0
            r4 = 0
            int r14 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r14 >= 0) goto L63
            long r10 = r10 ^ r12
            int r12 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r12 < 0) goto L5b
            goto L63
        L5b:
            java.lang.ArithmeticException r10 = new java.lang.ArithmeticException
            java.lang.String r11 = "Subtracting time zone offset caused overflow"
            r10.<init>(r11)
            throw r10
        L63:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p016b6.AbstractC0475f.m241a(long, boolean, long):long");
    }

    /* renamed from: b */
    public long m242b(long j7) {
        long jMo245k = mo245k(j7);
        long j8 = j7 + jMo245k;
        if ((j7 ^ j8) >= 0 || (j7 ^ jMo245k) < 0) {
            return j8;
        }
        throw new ArithmeticException("Adding time zone offset caused overflow");
    }

    public abstract boolean equals(Object obj);

    /* renamed from: h */
    public long m243h(AbstractC0475f abstractC0475f, long j7) throws ClassNotFoundException {
        if (abstractC0475f == null) {
            abstractC0475f = m235g();
        }
        AbstractC0475f abstractC0475f2 = abstractC0475f;
        return abstractC0475f2 == this ? j7 : abstractC0475f2.m241a(m242b(j7), false, j7);
    }

    public int hashCode() {
        return this.f318e.hashCode() + 57;
    }

    /* renamed from: i */
    public abstract String mo244i(long j7);

    /* renamed from: k */
    public abstract int mo245k(long j7);

    /* renamed from: l */
    public int mo246l(long j7) {
        int iMo245k = mo245k(j7);
        long j8 = j7 - iMo245k;
        int iMo245k2 = mo245k(j8);
        if (iMo245k != iMo245k2) {
            if (iMo245k - iMo245k2 < 0) {
                long jMo249p = mo249p(j8);
                long j9 = RecyclerView.FOREVER_NS;
                if (jMo249p == j8) {
                    jMo249p = Long.MAX_VALUE;
                }
                long j10 = j7 - iMo245k2;
                long jMo249p2 = mo249p(j10);
                if (jMo249p2 != j10) {
                    j9 = jMo249p2;
                }
                if (jMo249p != j9) {
                    return iMo245k;
                }
            }
        } else if (iMo245k >= 0) {
            long jMo250r = mo250r(j8);
            if (jMo250r < j8) {
                int iMo245k3 = mo245k(jMo250r);
                if (j8 - jMo250r <= iMo245k3 - iMo245k) {
                    return iMo245k3;
                }
            }
        }
        return iMo245k2;
    }

    /* renamed from: n */
    public abstract int mo247n(long j7);

    /* renamed from: o */
    public abstract boolean mo248o();

    /* renamed from: p */
    public abstract long mo249p(long j7);

    /* renamed from: r */
    public abstract long mo250r(long j7);

    public String toString() {
        return this.f318e;
    }

    public Object writeReplace() {
        return new b(this.f318e);
    }
}
