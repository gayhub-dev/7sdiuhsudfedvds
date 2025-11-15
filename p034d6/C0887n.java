package p034d6;

import android.support.v7.widget.RecyclerView;
import java.util.HashMap;
import java.util.Locale;
import p009b.C0413b;
import p016b6.AbstractC0471b;
import p016b6.AbstractC0475f;
import p016b6.AbstractC0477h;
import p016b6.C0479j;
import p016b6.C0480k;
import p034d6.AbstractC0874a;
import p050f6.AbstractC1015b;
import p050f6.AbstractC1016c;
import p159t3.AbstractC1904c;

/* compiled from: ZonedChronology.java */
/* renamed from: d6.n */
/* loaded from: classes.dex */
public final class C0887n extends AbstractC0874a {
    private static final long serialVersionUID = -1079258847191166848L;

    /* compiled from: ZonedChronology.java */
    /* renamed from: d6.n$a */
    public static final class a extends AbstractC1015b {

        /* renamed from: b */
        public final AbstractC0471b f1473b;

        /* renamed from: c */
        public final AbstractC0475f f1474c;

        /* renamed from: d */
        public final AbstractC0477h f1475d;

        /* renamed from: e */
        public final boolean f1476e;

        /* renamed from: f */
        public final AbstractC0477h f1477f;

        /* renamed from: g */
        public final AbstractC0477h f1478g;

        public a(AbstractC0471b abstractC0471b, AbstractC0475f abstractC0475f, AbstractC0477h abstractC0477h, AbstractC0477h abstractC0477h2, AbstractC0477h abstractC0477h3) {
            super(abstractC0471b.mo213p());
            if (!abstractC0471b.mo216s()) {
                throw new IllegalArgumentException();
            }
            this.f1473b = abstractC0471b;
            this.f1474c = abstractC0475f;
            this.f1475d = abstractC0477h;
            this.f1476e = abstractC0477h != null && abstractC0477h.mo256h() < 43200000;
            this.f1477f = abstractC0477h2;
            this.f1478g = abstractC0477h3;
        }

        /* renamed from: A */
        public final int m767A(long j7) {
            int iMo245k = this.f1474c.mo245k(j7);
            long j8 = iMo245k;
            if (((j7 + j8) ^ j7) >= 0 || (j7 ^ j8) < 0) {
                return iMo245k;
            }
            throw new ArithmeticException("Adding time zone offset caused overflow");
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: a */
        public long mo198a(long j7, int i7) {
            if (this.f1476e) {
                long jM767A = m767A(j7);
                return this.f1473b.mo198a(j7 + jM767A, i7) - jM767A;
            }
            return this.f1474c.m241a(this.f1473b.mo198a(this.f1474c.m242b(j7), i7), false, j7);
        }

        @Override // p016b6.AbstractC0471b
        /* renamed from: b */
        public int mo199b(long j7) {
            return this.f1473b.mo199b(this.f1474c.m242b(j7));
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: c */
        public String mo200c(int i7, Locale locale) {
            return this.f1473b.mo200c(i7, locale);
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: d */
        public String mo201d(long j7, Locale locale) {
            return this.f1473b.mo201d(this.f1474c.m242b(j7), locale);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f1473b.equals(aVar.f1473b) && this.f1474c.equals(aVar.f1474c) && this.f1475d.equals(aVar.f1475d) && this.f1477f.equals(aVar.f1477f);
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: f */
        public String mo203f(int i7, Locale locale) {
            return this.f1473b.mo203f(i7, locale);
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: g */
        public String mo204g(long j7, Locale locale) {
            return this.f1473b.mo204g(this.f1474c.m242b(j7), locale);
        }

        public int hashCode() {
            return this.f1473b.hashCode() ^ this.f1474c.hashCode();
        }

        @Override // p016b6.AbstractC0471b
        /* renamed from: i */
        public final AbstractC0477h mo206i() {
            return this.f1475d;
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: j */
        public final AbstractC0477h mo207j() {
            return this.f1478g;
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: k */
        public int mo208k(Locale locale) {
            return this.f1473b.mo208k(locale);
        }

        @Override // p016b6.AbstractC0471b
        /* renamed from: l */
        public int mo209l() {
            return this.f1473b.mo209l();
        }

        @Override // p016b6.AbstractC0471b
        /* renamed from: m */
        public int mo210m() {
            return this.f1473b.mo210m();
        }

        @Override // p016b6.AbstractC0471b
        /* renamed from: o */
        public final AbstractC0477h mo212o() {
            return this.f1477f;
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: q */
        public boolean mo214q(long j7) {
            return this.f1473b.mo214q(this.f1474c.m242b(j7));
        }

        @Override // p016b6.AbstractC0471b
        /* renamed from: r */
        public boolean mo215r() {
            return this.f1473b.mo215r();
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: t */
        public long mo217t(long j7) {
            return this.f1473b.mo217t(this.f1474c.m242b(j7));
        }

        @Override // p016b6.AbstractC0471b
        /* renamed from: u */
        public long mo218u(long j7) {
            if (this.f1476e) {
                long jM767A = m767A(j7);
                return this.f1473b.mo218u(j7 + jM767A) - jM767A;
            }
            return this.f1474c.m241a(this.f1473b.mo218u(this.f1474c.m242b(j7)), false, j7);
        }

        @Override // p016b6.AbstractC0471b
        /* renamed from: v */
        public long mo219v(long j7, int i7) {
            long jMo219v = this.f1473b.mo219v(this.f1474c.m242b(j7), i7);
            long jM241a = this.f1474c.m241a(jMo219v, false, j7);
            if (mo199b(jM241a) == i7) {
                return jM241a;
            }
            C0480k c0480k = new C0480k(jMo219v, this.f1474c.f318e);
            C0479j c0479j = new C0479j(this.f1473b.mo213p(), Integer.valueOf(i7), c0480k.getMessage());
            c0479j.initCause(c0480k);
            throw c0479j;
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: w */
        public long mo220w(long j7, String str, Locale locale) {
            return this.f1474c.m241a(this.f1473b.mo220w(this.f1474c.m242b(j7), str, locale), false, j7);
        }
    }

    /* compiled from: ZonedChronology.java */
    /* renamed from: d6.n$b */
    public static class b extends AbstractC1016c {
        private static final long serialVersionUID = -485345310999208286L;

        /* renamed from: f */
        public final AbstractC0477h f1479f;

        /* renamed from: g */
        public final boolean f1480g;

        /* renamed from: h */
        public final AbstractC0475f f1481h;

        public b(AbstractC0477h abstractC0477h, AbstractC0475f abstractC0475f) {
            super(abstractC0477h.mo255f());
            if (!abstractC0477h.mo258l()) {
                throw new IllegalArgumentException();
            }
            this.f1479f = abstractC0477h;
            this.f1480g = abstractC0477h.mo256h() < 43200000;
            this.f1481h = abstractC0475f;
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: a */
        public long mo251a(long j7, int i7) {
            int iM769n = m769n(j7);
            long jMo251a = this.f1479f.mo251a(j7 + iM769n, i7);
            if (!this.f1480g) {
                iM769n = m768m(jMo251a);
            }
            return jMo251a - iM769n;
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: b */
        public long mo252b(long j7, long j8) {
            int iM769n = m769n(j7);
            long jMo252b = this.f1479f.mo252b(j7 + iM769n, j8);
            if (!this.f1480g) {
                iM769n = m768m(jMo252b);
            }
            return jMo252b - iM769n;
        }

        @Override // p050f6.AbstractC1016c, p016b6.AbstractC0477h
        /* renamed from: c */
        public int mo253c(long j7, long j8) {
            return this.f1479f.mo253c(j7 + (this.f1480g ? r0 : m769n(j7)), j8 + m769n(j8));
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: e */
        public long mo254e(long j7, long j8) {
            return this.f1479f.mo254e(j7 + (this.f1480g ? r0 : m769n(j7)), j8 + m769n(j8));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f1479f.equals(bVar.f1479f) && this.f1481h.equals(bVar.f1481h);
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: h */
        public long mo256h() {
            return this.f1479f.mo256h();
        }

        public int hashCode() {
            return this.f1479f.hashCode() ^ this.f1481h.hashCode();
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: i */
        public boolean mo257i() {
            return this.f1480g ? this.f1479f.mo257i() : this.f1479f.mo257i() && this.f1481h.mo248o();
        }

        /* renamed from: m */
        public final int m768m(long j7) {
            int iMo246l = this.f1481h.mo246l(j7);
            long j8 = iMo246l;
            if (((j7 - j8) ^ j7) >= 0 || (j7 ^ j8) >= 0) {
                return iMo246l;
            }
            throw new ArithmeticException("Subtracting time zone offset caused overflow");
        }

        /* renamed from: n */
        public final int m769n(long j7) {
            int iMo245k = this.f1481h.mo245k(j7);
            long j8 = iMo245k;
            if (((j7 + j8) ^ j7) >= 0 || (j7 ^ j8) < 0) {
                return iMo245k;
            }
            throw new ArithmeticException("Adding time zone offset caused overflow");
        }
    }

    public C0887n(AbstractC1904c abstractC1904c, AbstractC0475f abstractC0475f) {
        super(abstractC1904c, abstractC0475f);
    }

    /* renamed from: U */
    public static C0887n m763U(AbstractC1904c abstractC1904c, AbstractC0475f abstractC0475f) {
        if (abstractC1904c == null) {
            throw new IllegalArgumentException("Must supply a chronology");
        }
        AbstractC1904c abstractC1904cMo228K = abstractC1904c.mo228K();
        if (abstractC1904cMo228K == null) {
            throw new IllegalArgumentException("UTC chronology must not be null");
        }
        if (abstractC0475f != null) {
            return new C0887n(abstractC1904cMo228K, abstractC0475f);
        }
        throw new IllegalArgumentException("DateTimeZone must not be null");
    }

    @Override // p159t3.AbstractC1904c
    /* renamed from: K */
    public AbstractC1904c mo228K() {
        return this.f1362e;
    }

    @Override // p159t3.AbstractC1904c
    /* renamed from: L */
    public AbstractC1904c mo229L(AbstractC0475f abstractC0475f) throws ClassNotFoundException {
        if (abstractC0475f == null) {
            abstractC0475f = AbstractC0475f.m235g();
        }
        return abstractC0475f == this.f1363f ? this : abstractC0475f == AbstractC0475f.f314f ? this.f1362e : new C0887n(this.f1362e, abstractC0475f);
    }

    @Override // p034d6.AbstractC0874a
    /* renamed from: Q */
    public void mo694Q(AbstractC0874a.a aVar) {
        HashMap<Object, Object> map = new HashMap<>();
        aVar.f1404l = m765T(aVar.f1404l, map);
        aVar.f1403k = m765T(aVar.f1403k, map);
        aVar.f1402j = m765T(aVar.f1402j, map);
        aVar.f1401i = m765T(aVar.f1401i, map);
        aVar.f1400h = m765T(aVar.f1400h, map);
        aVar.f1399g = m765T(aVar.f1399g, map);
        aVar.f1398f = m765T(aVar.f1398f, map);
        aVar.f1397e = m765T(aVar.f1397e, map);
        aVar.f1396d = m765T(aVar.f1396d, map);
        aVar.f1395c = m765T(aVar.f1395c, map);
        aVar.f1394b = m765T(aVar.f1394b, map);
        aVar.f1393a = m765T(aVar.f1393a, map);
        aVar.f1388E = m764S(aVar.f1388E, map);
        aVar.f1389F = m764S(aVar.f1389F, map);
        aVar.f1390G = m764S(aVar.f1390G, map);
        aVar.f1391H = m764S(aVar.f1391H, map);
        aVar.f1392I = m764S(aVar.f1392I, map);
        aVar.f1416x = m764S(aVar.f1416x, map);
        aVar.f1417y = m764S(aVar.f1417y, map);
        aVar.f1418z = m764S(aVar.f1418z, map);
        aVar.f1387D = m764S(aVar.f1387D, map);
        aVar.f1384A = m764S(aVar.f1384A, map);
        aVar.f1385B = m764S(aVar.f1385B, map);
        aVar.f1386C = m764S(aVar.f1386C, map);
        aVar.f1405m = m764S(aVar.f1405m, map);
        aVar.f1406n = m764S(aVar.f1406n, map);
        aVar.f1407o = m764S(aVar.f1407o, map);
        aVar.f1408p = m764S(aVar.f1408p, map);
        aVar.f1409q = m764S(aVar.f1409q, map);
        aVar.f1410r = m764S(aVar.f1410r, map);
        aVar.f1411s = m764S(aVar.f1411s, map);
        aVar.f1413u = m764S(aVar.f1413u, map);
        aVar.f1412t = m764S(aVar.f1412t, map);
        aVar.f1414v = m764S(aVar.f1414v, map);
        aVar.f1415w = m764S(aVar.f1415w, map);
    }

    /* renamed from: S */
    public final AbstractC0471b m764S(AbstractC0471b abstractC0471b, HashMap<Object, Object> map) {
        if (abstractC0471b == null || !abstractC0471b.mo216s()) {
            return abstractC0471b;
        }
        if (map.containsKey(abstractC0471b)) {
            return (AbstractC0471b) map.get(abstractC0471b);
        }
        a aVar = new a(abstractC0471b, (AbstractC0475f) this.f1363f, m765T(abstractC0471b.mo206i(), map), m765T(abstractC0471b.mo212o(), map), m765T(abstractC0471b.mo207j(), map));
        map.put(abstractC0471b, aVar);
        return aVar;
    }

    /* renamed from: T */
    public final AbstractC0477h m765T(AbstractC0477h abstractC0477h, HashMap<Object, Object> map) {
        if (abstractC0477h == null || !abstractC0477h.mo258l()) {
            return abstractC0477h;
        }
        if (map.containsKey(abstractC0477h)) {
            return (AbstractC0477h) map.get(abstractC0477h);
        }
        b bVar = new b(abstractC0477h, (AbstractC0475f) this.f1363f);
        map.put(abstractC0477h, bVar);
        return bVar;
    }

    /* renamed from: V */
    public final long m766V(long j7) {
        if (j7 == RecyclerView.FOREVER_NS) {
            return RecyclerView.FOREVER_NS;
        }
        if (j7 == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        AbstractC0475f abstractC0475f = (AbstractC0475f) this.f1363f;
        int iMo246l = abstractC0475f.mo246l(j7);
        long j8 = j7 - iMo246l;
        if (j7 > 604800000 && j8 < 0) {
            return RecyclerView.FOREVER_NS;
        }
        if (j7 < -604800000 && j8 > 0) {
            return Long.MIN_VALUE;
        }
        if (iMo246l == abstractC0475f.mo245k(j8)) {
            return j8;
        }
        throw new C0480k(j7, abstractC0475f.f318e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0887n)) {
            return false;
        }
        C0887n c0887n = (C0887n) obj;
        return this.f1362e.equals(c0887n.f1362e) && ((AbstractC0475f) this.f1363f).equals((AbstractC0475f) c0887n.f1363f);
    }

    public int hashCode() {
        return (this.f1362e.hashCode() * 7) + (((AbstractC0475f) this.f1363f).hashCode() * 11) + 326565;
    }

    @Override // p034d6.AbstractC0874a, p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: m */
    public long mo706m(long j7, int i7, int i8, int i9, int i10) {
        return m766V(this.f1362e.mo706m(((AbstractC0475f) this.f1363f).mo245k(j7) + j7, i7, i8, i9, i10));
    }

    @Override // p034d6.AbstractC0874a, p159t3.AbstractC1904c
    /* renamed from: n */
    public AbstractC0475f mo230n() {
        return (AbstractC0475f) this.f1363f;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("ZonedChronology[");
        sbM112a.append(this.f1362e);
        sbM112a.append(", ");
        sbM112a.append(((AbstractC0475f) this.f1363f).f318e);
        sbM112a.append(']');
        return sbM112a.toString();
    }
}
