package p066h6;

import p016b6.AbstractC0475f;

/* compiled from: CachedDateTimeZone.java */
/* renamed from: h6.a */
/* loaded from: classes.dex */
public class C1118a extends AbstractC0475f {

    /* renamed from: l */
    public static final int f2411l;
    private static final long serialVersionUID = 5472298452022250685L;

    /* renamed from: j */
    public final AbstractC0475f f2412j;

    /* renamed from: k */
    public final transient a[] f2413k;

    /* compiled from: CachedDateTimeZone.java */
    /* renamed from: h6.a$a */
    public static final class a {

        /* renamed from: a */
        public final long f2414a;

        /* renamed from: b */
        public final AbstractC0475f f2415b;

        /* renamed from: c */
        public a f2416c;

        /* renamed from: d */
        public String f2417d;

        /* renamed from: e */
        public int f2418e = Integer.MIN_VALUE;

        /* renamed from: f */
        public int f2419f = Integer.MIN_VALUE;

        public a(AbstractC0475f abstractC0475f, long j7) {
            this.f2414a = j7;
            this.f2415b = abstractC0475f;
        }

        /* renamed from: a */
        public String m1263a(long j7) {
            a aVar = this.f2416c;
            if (aVar != null && j7 >= aVar.f2414a) {
                return aVar.m1263a(j7);
            }
            if (this.f2417d == null) {
                this.f2417d = this.f2415b.mo244i(this.f2414a);
            }
            return this.f2417d;
        }

        /* renamed from: b */
        public int m1264b(long j7) {
            a aVar = this.f2416c;
            if (aVar != null && j7 >= aVar.f2414a) {
                return aVar.m1264b(j7);
            }
            if (this.f2418e == Integer.MIN_VALUE) {
                this.f2418e = this.f2415b.mo245k(this.f2414a);
            }
            return this.f2418e;
        }

        /* renamed from: c */
        public int m1265c(long j7) {
            a aVar = this.f2416c;
            if (aVar != null && j7 >= aVar.f2414a) {
                return aVar.m1265c(j7);
            }
            if (this.f2419f == Integer.MIN_VALUE) {
                this.f2419f = this.f2415b.mo247n(this.f2414a);
            }
            return this.f2419f;
        }
    }

    static {
        Integer integer;
        int i7;
        try {
            integer = Integer.getInteger("org.joda.time.tz.CachedDateTimeZone.size");
        } catch (SecurityException unused) {
            integer = null;
        }
        if (integer == null) {
            i7 = 512;
        } else {
            int i8 = 0;
            for (int iIntValue = integer.intValue() - 1; iIntValue > 0; iIntValue >>= 1) {
                i8++;
            }
            i7 = 1 << i8;
        }
        f2411l = i7 - 1;
    }

    public C1118a(AbstractC0475f abstractC0475f) {
        super(abstractC0475f.f318e);
        this.f2413k = new a[f2411l + 1];
        this.f2412j = abstractC0475f;
    }

    @Override // p016b6.AbstractC0475f
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C1118a) {
            return this.f2412j.equals(((C1118a) obj).f2412j);
        }
        return false;
    }

    @Override // p016b6.AbstractC0475f
    public int hashCode() {
        return this.f2412j.hashCode();
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: i */
    public String mo244i(long j7) {
        return m1262u(j7).m1263a(j7);
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: k */
    public int mo245k(long j7) {
        return m1262u(j7).m1264b(j7);
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: n */
    public int mo247n(long j7) {
        return m1262u(j7).m1265c(j7);
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: o */
    public boolean mo248o() {
        return this.f2412j.mo248o();
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: p */
    public long mo249p(long j7) {
        return this.f2412j.mo249p(j7);
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: r */
    public long mo250r(long j7) {
        return this.f2412j.mo250r(j7);
    }

    /* renamed from: u */
    public final a m1262u(long j7) {
        int i7 = (int) (j7 >> 32);
        a[] aVarArr = this.f2413k;
        int i8 = f2411l & i7;
        a aVar = aVarArr[i8];
        if (aVar == null || ((int) (aVar.f2414a >> 32)) != i7) {
            long j8 = j7 & (-4294967296L);
            aVar = new a(this.f2412j, j8);
            long j9 = 4294967295L | j8;
            a aVar2 = aVar;
            while (true) {
                long jMo249p = this.f2412j.mo249p(j8);
                if (jMo249p == j8 || jMo249p > j9) {
                    break;
                }
                a aVar3 = new a(this.f2412j, jMo249p);
                aVar2.f2416c = aVar3;
                aVar2 = aVar3;
                j8 = jMo249p;
            }
            aVarArr[i8] = aVar;
        }
        return aVar;
    }
}
