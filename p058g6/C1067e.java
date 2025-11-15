package p058g6;

import android.support.constraint.motion.C0080b;
import java.util.Arrays;
import java.util.Locale;
import p009b.C0413b;
import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0475f;
import p016b6.AbstractC0477h;
import p016b6.AbstractC0478i;
import p016b6.C0473d;
import p016b6.C0479j;
import p016b6.C0480k;
import p159t3.AbstractC1904c;

/* compiled from: DateTimeParserBucket.java */
/* renamed from: g6.e */
/* loaded from: classes.dex */
public class C1067e {

    /* renamed from: a */
    public final AbstractC1904c f2055a;

    /* renamed from: b */
    public final long f2056b;

    /* renamed from: c */
    public final Locale f2057c;

    /* renamed from: d */
    public final int f2058d;

    /* renamed from: e */
    public AbstractC0475f f2059e;

    /* renamed from: f */
    public Integer f2060f;

    /* renamed from: g */
    public Integer f2061g;

    /* renamed from: h */
    public a[] f2062h;

    /* renamed from: i */
    public int f2063i;

    /* renamed from: j */
    public boolean f2064j;

    /* renamed from: k */
    public Object f2065k;

    /* compiled from: DateTimeParserBucket.java */
    /* renamed from: g6.e$a */
    public static class a implements Comparable<a> {

        /* renamed from: e */
        public AbstractC0471b f2066e;

        /* renamed from: f */
        public int f2067f;

        /* renamed from: g */
        public String f2068g;

        /* renamed from: h */
        public Locale f2069h;

        @Override // java.lang.Comparable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            AbstractC0471b abstractC0471b = aVar.f2066e;
            int iM1101a = C1067e.m1101a(this.f2066e.mo212o(), abstractC0471b.mo212o());
            return iM1101a != 0 ? iM1101a : C1067e.m1101a(this.f2066e.mo206i(), abstractC0471b.mo206i());
        }

        /* renamed from: b */
        public long m1108b(long j7, boolean z6) {
            String str = this.f2068g;
            long jMo221x = str == null ? this.f2066e.mo221x(j7, this.f2067f) : this.f2066e.mo220w(j7, str, this.f2069h);
            return z6 ? this.f2066e.mo218u(jMo221x) : jMo221x;
        }
    }

    /* compiled from: DateTimeParserBucket.java */
    /* renamed from: g6.e$b */
    public class b {

        /* renamed from: a */
        public final AbstractC0475f f2070a;

        /* renamed from: b */
        public final Integer f2071b;

        /* renamed from: c */
        public final a[] f2072c;

        /* renamed from: d */
        public final int f2073d;

        public b() {
            this.f2070a = C1067e.this.f2059e;
            this.f2071b = C1067e.this.f2060f;
            this.f2072c = C1067e.this.f2062h;
            this.f2073d = C1067e.this.f2063i;
        }
    }

    public C1067e(long j7, AbstractC1904c abstractC1904c, Locale locale, Integer num, int i7) {
        AbstractC1904c abstractC1904cM225a = C0473d.m225a(abstractC1904c);
        this.f2056b = j7;
        AbstractC0475f abstractC0475fMo230n = abstractC1904cM225a.mo230n();
        this.f2055a = abstractC1904cM225a.mo228K();
        this.f2057c = locale == null ? Locale.getDefault() : locale;
        this.f2058d = i7;
        this.f2059e = abstractC0475fMo230n;
        this.f2061g = num;
        this.f2062h = new a[8];
    }

    /* renamed from: a */
    public static int m1101a(AbstractC0477h abstractC0477h, AbstractC0477h abstractC0477h2) {
        if (abstractC0477h == null || !abstractC0477h.mo258l()) {
            return (abstractC0477h2 == null || !abstractC0477h2.mo258l()) ? 0 : -1;
        }
        if (abstractC0477h2 == null || !abstractC0477h2.mo258l()) {
            return 1;
        }
        return -abstractC0477h.compareTo(abstractC0477h2);
    }

    /* renamed from: b */
    public long m1102b(boolean z6, CharSequence charSequence) {
        a[] aVarArr = this.f2062h;
        int i7 = this.f2063i;
        if (this.f2064j) {
            aVarArr = (a[]) aVarArr.clone();
            this.f2062h = aVarArr;
            this.f2064j = false;
        }
        if (i7 > 10) {
            Arrays.sort(aVarArr, 0, i7);
        } else {
            for (int i8 = 0; i8 < i7; i8++) {
                int i9 = i8;
                while (i9 > 0) {
                    int i10 = i9 - 1;
                    if (aVarArr[i10].compareTo(aVarArr[i9]) > 0) {
                        a aVar = aVarArr[i9];
                        aVarArr[i9] = aVarArr[i10];
                        aVarArr[i10] = aVar;
                        i9 = i10;
                    }
                }
            }
        }
        if (i7 > 0) {
            AbstractC0477h abstractC0477hMo259a = AbstractC0478i.f326j.mo259a(this.f2055a);
            AbstractC0477h abstractC0477hMo259a2 = AbstractC0478i.f328l.mo259a(this.f2055a);
            AbstractC0477h abstractC0477hMo206i = aVarArr[0].f2066e.mo206i();
            if (m1101a(abstractC0477hMo206i, abstractC0477hMo259a) >= 0 && m1101a(abstractC0477hMo206i, abstractC0477hMo259a2) <= 0) {
                AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
                m1105e(AbstractC0472c.f292j, this.f2058d);
                return m1102b(z6, charSequence);
            }
        }
        long jM1108b = this.f2056b;
        for (int i11 = 0; i11 < i7; i11++) {
            try {
                jM1108b = aVarArr[i11].m1108b(jM1108b, z6);
            } catch (C0479j e7) {
                if (charSequence != null) {
                    String str = "Cannot parse \"" + ((Object) charSequence) + '\"';
                    if (e7.f336e == null) {
                        e7.f336e = str;
                    } else if (str != null) {
                        StringBuilder sbM94a = C0080b.m94a(str, ": ");
                        sbM94a.append(e7.f336e);
                        e7.f336e = sbM94a.toString();
                    }
                }
                throw e7;
            }
        }
        if (z6) {
            int i12 = 0;
            while (i12 < i7) {
                if (!aVarArr[i12].f2066e.mo215r()) {
                    jM1108b = aVarArr[i12].m1108b(jM1108b, i12 == i7 + (-1));
                }
                i12++;
            }
        }
        if (this.f2060f != null) {
            return jM1108b - r9.intValue();
        }
        AbstractC0475f abstractC0475f = this.f2059e;
        if (abstractC0475f == null) {
            return jM1108b;
        }
        int iMo246l = abstractC0475f.mo246l(jM1108b);
        long j7 = jM1108b - iMo246l;
        if (iMo246l == this.f2059e.mo245k(j7)) {
            return j7;
        }
        StringBuilder sbM112a = C0413b.m112a("Illegal instant due to time zone offset transition (");
        sbM112a.append(this.f2059e);
        sbM112a.append(')');
        String string = sbM112a.toString();
        if (charSequence != null) {
            string = "Cannot parse \"" + ((Object) charSequence) + "\": " + string;
        }
        throw new C0480k(string);
    }

    /* renamed from: c */
    public final a m1103c() {
        a[] aVarArr = this.f2062h;
        int i7 = this.f2063i;
        if (i7 == aVarArr.length || this.f2064j) {
            a[] aVarArr2 = new a[i7 == aVarArr.length ? i7 * 2 : aVarArr.length];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, i7);
            this.f2062h = aVarArr2;
            this.f2064j = false;
            aVarArr = aVarArr2;
        }
        this.f2065k = null;
        a aVar = aVarArr[i7];
        if (aVar == null) {
            aVar = new a();
            aVarArr[i7] = aVar;
        }
        this.f2063i = i7 + 1;
        return aVar;
    }

    /* renamed from: d */
    public boolean m1104d(Object obj) {
        boolean z6;
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (this != C1067e.this) {
                z6 = false;
            } else {
                this.f2059e = bVar.f2070a;
                this.f2060f = bVar.f2071b;
                this.f2062h = bVar.f2072c;
                int i7 = bVar.f2073d;
                if (i7 < this.f2063i) {
                    this.f2064j = true;
                }
                this.f2063i = i7;
                z6 = true;
            }
            if (z6) {
                this.f2065k = obj;
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    public void m1105e(AbstractC0472c abstractC0472c, int i7) {
        a aVarM1103c = m1103c();
        aVarM1103c.f2066e = abstractC0472c.mo223b(this.f2055a);
        aVarM1103c.f2067f = i7;
        aVarM1103c.f2068g = null;
        aVarM1103c.f2069h = null;
    }

    /* renamed from: f */
    public void m1106f(Integer num) {
        this.f2065k = null;
        this.f2060f = num;
    }
}
