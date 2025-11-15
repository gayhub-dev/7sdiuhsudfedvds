package p034d6;

import android.support.constraint.motion.C0079a;
import android.support.v4.media.AudioAttributesCompat;
import android.support.v7.widget.ActivityChooserView;
import java.util.Locale;
import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0475f;
import p016b6.AbstractC0477h;
import p016b6.AbstractC0478i;
import p016b6.C0479j;
import p034d6.AbstractC0874a;
import p050f6.C1019f;
import p050f6.C1021h;
import p050f6.C1022i;
import p050f6.C1023j;
import p050f6.C1025l;
import p050f6.C1026m;
import p050f6.C1030q;
import p159t3.AbstractC1904c;

/* compiled from: BasicChronology.java */
/* renamed from: d6.c */
/* loaded from: classes.dex */
public abstract class AbstractC0876c extends AbstractC0874a {

    /* renamed from: S */
    public static final AbstractC0477h f1419S;

    /* renamed from: T */
    public static final AbstractC0477h f1420T;

    /* renamed from: U */
    public static final AbstractC0477h f1421U;

    /* renamed from: V */
    public static final AbstractC0477h f1422V;

    /* renamed from: W */
    public static final AbstractC0477h f1423W;

    /* renamed from: X */
    public static final AbstractC0477h f1424X;

    /* renamed from: Y */
    public static final AbstractC0471b f1425Y;

    /* renamed from: Z */
    public static final AbstractC0471b f1426Z;

    /* renamed from: a0 */
    public static final AbstractC0471b f1427a0;

    /* renamed from: b0 */
    public static final AbstractC0471b f1428b0;

    /* renamed from: c0 */
    public static final AbstractC0471b f1429c0;

    /* renamed from: d0 */
    public static final AbstractC0471b f1430d0;

    /* renamed from: e0 */
    public static final AbstractC0471b f1431e0;

    /* renamed from: f0 */
    public static final AbstractC0471b f1432f0;

    /* renamed from: g0 */
    public static final AbstractC0471b f1433g0;

    /* renamed from: h0 */
    public static final AbstractC0471b f1434h0;

    /* renamed from: i0 */
    public static final AbstractC0471b f1435i0;
    private static final long serialVersionUID = 8283225332206808863L;

    /* renamed from: Q */
    public final transient b[] f1436Q;

    /* renamed from: R */
    public final int f1437R;

    /* compiled from: BasicChronology.java */
    /* renamed from: d6.c$a */
    public static class a extends C1023j {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a() {
            super(AbstractC0472c.f300r, AbstractC0876c.f1422V, AbstractC0876c.f1423W);
            AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: f */
        public String mo203f(int i7, Locale locale) {
            return C0881h.m757b(locale).f1455f[i7];
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: k */
        public int mo208k(Locale locale) {
            return C0881h.m757b(locale).f1462m;
        }

        @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
        /* renamed from: w */
        public long mo220w(long j7, String str, Locale locale) {
            String[] strArr = C0881h.m757b(locale).f1455f;
            int length = strArr.length;
            do {
                length--;
                if (length < 0) {
                    AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
                    throw new C0479j(AbstractC0472c.f300r, str);
                }
            } while (!strArr[length].equalsIgnoreCase(str));
            return mo219v(j7, length);
        }
    }

    /* compiled from: BasicChronology.java */
    /* renamed from: d6.c$b */
    public static class b {

        /* renamed from: a */
        public final int f1438a;

        /* renamed from: b */
        public final long f1439b;

        public b(int i7, long j7) {
            this.f1438a = i7;
            this.f1439b = j7;
        }
    }

    static {
        AbstractC0477h abstractC0477h = C1021h.f1923e;
        C1025l c1025l = new C1025l(AbstractC0478i.f332p, 1000L);
        f1419S = c1025l;
        C1025l c1025l2 = new C1025l(AbstractC0478i.f331o, 60000L);
        f1420T = c1025l2;
        C1025l c1025l3 = new C1025l(AbstractC0478i.f330n, 3600000L);
        f1421U = c1025l3;
        C1025l c1025l4 = new C1025l(AbstractC0478i.f329m, 43200000L);
        f1422V = c1025l4;
        C1025l c1025l5 = new C1025l(AbstractC0478i.f328l, 86400000L);
        f1423W = c1025l5;
        f1424X = new C1025l(AbstractC0478i.f327k, 604800000L);
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        f1425Y = new C1023j(AbstractC0472c.f287B, abstractC0477h, c1025l);
        f1426Z = new C1023j(AbstractC0472c.f286A, abstractC0477h, c1025l5);
        f1427a0 = new C1023j(AbstractC0472c.f308z, c1025l, c1025l2);
        f1428b0 = new C1023j(AbstractC0472c.f307y, c1025l, c1025l5);
        f1429c0 = new C1023j(AbstractC0472c.f306x, c1025l2, c1025l3);
        f1430d0 = new C1023j(AbstractC0472c.f305w, c1025l2, c1025l5);
        C1023j c1023j = new C1023j(AbstractC0472c.f304v, c1025l3, c1025l5);
        f1431e0 = c1023j;
        C1023j c1023j2 = new C1023j(AbstractC0472c.f301s, c1025l3, c1025l4);
        f1432f0 = c1023j2;
        f1433g0 = new C1030q(c1023j, AbstractC0472c.f303u);
        f1434h0 = new C1030q(c1023j2, AbstractC0472c.f302t);
        f1435i0 = new a();
    }

    public AbstractC0876c(AbstractC1904c abstractC1904c, Object obj, int i7) {
        super(abstractC1904c, obj);
        this.f1436Q = new b[1024];
        if (i7 < 1 || i7 > 7) {
            throw new IllegalArgumentException(C0079a.m93a("Invalid min days in first week: ", i7));
        }
        this.f1437R = i7;
    }

    @Override // p034d6.AbstractC0874a
    /* renamed from: Q */
    public void mo694Q(AbstractC0874a.a aVar) {
        aVar.f1393a = C1021h.f1923e;
        aVar.f1394b = f1419S;
        aVar.f1395c = f1420T;
        aVar.f1396d = f1421U;
        aVar.f1397e = f1422V;
        aVar.f1398f = f1423W;
        aVar.f1399g = f1424X;
        aVar.f1405m = f1425Y;
        aVar.f1406n = f1426Z;
        aVar.f1407o = f1427a0;
        aVar.f1408p = f1428b0;
        aVar.f1409q = f1429c0;
        aVar.f1410r = f1430d0;
        aVar.f1411s = f1431e0;
        aVar.f1413u = f1432f0;
        aVar.f1412t = f1433g0;
        aVar.f1414v = f1434h0;
        aVar.f1415w = f1435i0;
        C0879f c0879f = new C0879f(this, 1);
        aVar.f1388E = c0879f;
        C0883j c0883j = new C0883j(c0879f, this);
        aVar.f1389F = c0883j;
        C1022i c1022i = new C1022i(c0883j, AbstractC0472c.f289g, 99, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        C1019f c1019f = new C1019f(c1022i, AbstractC0472c.f290h, 100);
        aVar.f1391H = c1019f;
        aVar.f1403k = c1019f.f1916d;
        C1019f c1019f2 = c1019f;
        aVar.f1390G = new C1022i(new C1026m(c1019f2, c1019f2.f1911a), AbstractC0472c.f291i, 1, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        aVar.f1392I = new C0880g(this);
        aVar.f1416x = new C0877d(this, aVar.f1398f, 3);
        aVar.f1417y = new C0877d(this, aVar.f1398f, 0);
        aVar.f1418z = new C0877d(this, aVar.f1398f, 1);
        aVar.f1387D = new C0882i(this);
        aVar.f1385B = new C0879f(this, 0);
        aVar.f1384A = new C0877d(this, aVar.f1399g, 2);
        AbstractC0471b abstractC0471b = aVar.f1385B;
        AbstractC0477h abstractC0477h = aVar.f1403k;
        AbstractC0472c abstractC0472c2 = AbstractC0472c.f296n;
        aVar.f1386C = new C1022i(new C1026m(abstractC0471b, abstractC0477h, abstractC0472c2, 100), abstractC0472c2, 1, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        aVar.f1402j = aVar.f1388E.mo206i();
        aVar.f1401i = aVar.f1387D.mo206i();
        aVar.f1400h = aVar.f1385B.mo206i();
    }

    /* renamed from: S */
    public abstract long mo724S(int i7);

    /* renamed from: T */
    public abstract long mo725T();

    /* renamed from: U */
    public abstract long mo726U();

    /* renamed from: V */
    public abstract long mo727V();

    /* renamed from: W */
    public abstract long mo728W();

    /* renamed from: X */
    public int m729X(long j7, int i7, int i8) {
        return ((int) ((j7 - (mo738g0(i7, i8) + m745n0(i7))) / 86400000)) + 1;
    }

    /* renamed from: Y */
    public int m730Y(long j7) {
        long j8;
        if (j7 >= 0) {
            j8 = j7 / 86400000;
        } else {
            j8 = (j7 - 86399999) / 86400000;
            if (j8 < -3) {
                return ((int) ((j8 + 4) % 7)) + 7;
            }
        }
        return ((int) ((j8 + 3) % 7)) + 1;
    }

    /* renamed from: Z */
    public int mo731Z(long j7, int i7) {
        int iM743l0 = m743l0(j7);
        return mo732a0(iM743l0, mo737f0(j7, iM743l0));
    }

    /* renamed from: a0 */
    public abstract int mo732a0(int i7, int i8);

    /* renamed from: b0 */
    public long m733b0(int i7) {
        long jM745n0 = m745n0(i7);
        return m730Y(jM745n0) > 8 - this.f1437R ? ((8 - r8) * 86400000) + jM745n0 : jM745n0 - ((r8 - 1) * 86400000);
    }

    /* renamed from: c0 */
    public abstract int mo734c0();

    /* renamed from: d0 */
    public int m735d0(long j7) {
        return j7 >= 0 ? (int) (j7 % 86400000) : ((int) ((j7 + 1) % 86400000)) + 86399999;
    }

    /* renamed from: e0 */
    public abstract int mo736e0();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractC0876c abstractC0876c = (AbstractC0876c) obj;
        return this.f1437R == abstractC0876c.f1437R && mo230n().equals(abstractC0876c.mo230n());
    }

    /* renamed from: f0 */
    public abstract int mo737f0(long j7, int i7);

    /* renamed from: g0 */
    public abstract long mo738g0(int i7, int i8);

    /* renamed from: h0 */
    public int m739h0(long j7) {
        return m740i0(j7, m743l0(j7));
    }

    public int hashCode() {
        return mo230n().hashCode() + (getClass().getName().hashCode() * 11) + this.f1437R;
    }

    /* renamed from: i0 */
    public int m740i0(long j7, int i7) {
        long jM733b0 = m733b0(i7);
        if (j7 < jM733b0) {
            return m741j0(i7 - 1);
        }
        if (j7 >= m733b0(i7 + 1)) {
            return 1;
        }
        return ((int) ((j7 - jM733b0) / 604800000)) + 1;
    }

    /* renamed from: j0 */
    public int m741j0(int i7) {
        return (int) ((m733b0(i7 + 1) - m733b0(i7)) / 604800000);
    }

    /* renamed from: k0 */
    public int m742k0(long j7) {
        int iM743l0 = m743l0(j7);
        int iM740i0 = m740i0(j7, iM743l0);
        return iM740i0 == 1 ? m743l0(j7 + 604800000) : iM740i0 > 51 ? m743l0(j7 - 1209600000) : iM743l0;
    }

    /* renamed from: l0 */
    public int m743l0(long j7) {
        long jMo728W = mo728W();
        long jMo725T = mo725T() + (j7 >> 1);
        if (jMo725T < 0) {
            jMo725T = (jMo725T - jMo728W) + 1;
        }
        int i7 = (int) (jMo725T / jMo728W);
        long jM745n0 = m745n0(i7);
        long j8 = j7 - jM745n0;
        if (j8 < 0) {
            return i7 - 1;
        }
        if (j8 >= 31536000000L) {
            return jM745n0 + (mo749r0(i7) ? 31622400000L : 31536000000L) <= j7 ? i7 + 1 : i7;
        }
        return i7;
    }

    /* renamed from: m0 */
    public abstract long mo744m0(long j7, long j8);

    @Override // p034d6.AbstractC0874a, p159t3.AbstractC1904c
    /* renamed from: n */
    public AbstractC0475f mo230n() {
        AbstractC1904c abstractC1904c = this.f1362e;
        return abstractC1904c != null ? abstractC1904c.mo230n() : AbstractC0475f.f314f;
    }

    /* renamed from: n0 */
    public long m745n0(int i7) {
        b[] bVarArr = this.f1436Q;
        int i8 = i7 & AudioAttributesCompat.FLAG_ALL;
        b bVar = bVarArr[i8];
        if (bVar == null || bVar.f1438a != i7) {
            bVar = new b(i7, mo724S(i7));
            this.f1436Q[i8] = bVar;
        }
        return bVar.f1439b;
    }

    /* renamed from: o0 */
    public long m746o0(int i7, int i8, int i9) {
        return ((i9 - 1) * 86400000) + mo738g0(i7, i8) + m745n0(i7);
    }

    /* renamed from: p0 */
    public long m747p0(int i7, int i8) {
        return mo738g0(i7, i8) + m745n0(i7);
    }

    /* renamed from: q0 */
    public boolean mo748q0(long j7) {
        return false;
    }

    /* renamed from: r0 */
    public abstract boolean mo749r0(int i7);

    /* renamed from: s0 */
    public abstract long mo750s0(long j7, int i7);

    public String toString() {
        StringBuilder sb = new StringBuilder(60);
        String name = getClass().getName();
        int iLastIndexOf = name.lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            name = name.substring(iLastIndexOf + 1);
        }
        sb.append(name);
        sb.append('[');
        AbstractC0475f abstractC0475fMo230n = mo230n();
        if (abstractC0475fMo230n != null) {
            sb.append(abstractC0475fMo230n.f318e);
        }
        if (this.f1437R != 4) {
            sb.append(",mdfw=");
            sb.append(this.f1437R);
        }
        sb.append(']');
        return sb.toString();
    }
}
