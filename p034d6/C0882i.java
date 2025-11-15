package p034d6;

import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.util.Locale;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p016b6.C0479j;
import p050f6.AbstractC1020g;
import p186x2.C2074b;

/* compiled from: GJMonthOfYearDateTimeField.java */
/* renamed from: d6.i */
/* loaded from: classes.dex */
public final class C0882i extends AbstractC1020g {

    /* renamed from: d */
    public final AbstractC0876c f1463d;

    /* renamed from: e */
    public final int f1464e;

    /* renamed from: f */
    public final int f1465f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0882i(AbstractC0876c abstractC0876c) {
        super(AbstractC0472c.f294l, abstractC0876c.mo726U());
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        this.f1463d = abstractC0876c;
        this.f1464e = 12;
        this.f1465f = 2;
    }

    @Override // p050f6.AbstractC1020g
    /* renamed from: A */
    public long mo754A(long j7, long j8) {
        long j9;
        long j10;
        int i7 = (int) j8;
        if (i7 == j8) {
            return mo198a(j7, i7);
        }
        long jM735d0 = this.f1463d.m735d0(j7);
        int iM743l0 = this.f1463d.m743l0(j7);
        int iMo737f0 = this.f1463d.mo737f0(j7, iM743l0);
        long j11 = (iMo737f0 - 1) + j8;
        if (j11 >= 0) {
            long j12 = this.f1464e;
            j9 = (j11 / j12) + iM743l0;
            j10 = (j11 % j12) + 1;
        } else {
            j9 = ((j11 / this.f1464e) + iM743l0) - 1;
            long jAbs = Math.abs(j11);
            int i8 = this.f1464e;
            int i9 = (int) (jAbs % i8);
            if (i9 == 0) {
                i9 = i8;
            }
            j10 = (i8 - i9) + 1;
            if (j10 == 1) {
                j9++;
            }
        }
        long j13 = j9;
        if (j13 < this.f1463d.mo736e0() || j13 > this.f1463d.mo734c0()) {
            throw new IllegalArgumentException(C0534b.m341a("Magnitude of add amount is too large: ", j8));
        }
        int i10 = (int) j13;
        int i11 = (int) j10;
        int iM729X = this.f1463d.m729X(j7, iM743l0, iMo737f0);
        int iMo732a0 = this.f1463d.mo732a0(i10, i11);
        if (iM729X > iMo732a0) {
            iM729X = iMo732a0;
        }
        return this.f1463d.m746o0(i10, i11, iM729X) + jM735d0;
    }

    @Override // p050f6.AbstractC1020g
    /* renamed from: C */
    public long mo755C(long j7, long j8) {
        if (j7 < j8) {
            return -m1031B(j8, j7);
        }
        int iM743l0 = this.f1463d.m743l0(j7);
        int iMo737f0 = this.f1463d.mo737f0(j7, iM743l0);
        int iM743l02 = this.f1463d.m743l0(j8);
        int iMo737f02 = this.f1463d.mo737f0(j8, iM743l02);
        long j9 = (((iM743l0 - iM743l02) * this.f1464e) + iMo737f0) - iMo737f02;
        int iM729X = this.f1463d.m729X(j7, iM743l0, iMo737f0);
        if (iM729X == this.f1463d.mo732a0(iM743l0, iMo737f0) && this.f1463d.m729X(j8, iM743l02, iMo737f02) > iM729X) {
            j8 = this.f1463d.f1350E.mo219v(j8, iM729X);
        }
        if (j7 - this.f1463d.m747p0(iM743l0, iMo737f0) < j8 - this.f1463d.m747p0(iM743l02, iMo737f02)) {
            j9--;
        }
        return j9;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: a */
    public long mo198a(long j7, int i7) {
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        if (i7 == 0) {
            return j7;
        }
        long jM735d0 = this.f1463d.m735d0(j7);
        int iM743l0 = this.f1463d.m743l0(j7);
        int iMo737f0 = this.f1463d.mo737f0(j7, iM743l0);
        int i13 = iMo737f0 - 1;
        int i14 = i13 + i7;
        if (iMo737f0 <= 0 || i14 >= 0) {
            i8 = iM743l0;
        } else {
            if (Math.signum(this.f1464e + i7) == Math.signum(i7)) {
                i11 = iM743l0 - 1;
                i12 = i7 + this.f1464e;
            } else {
                i11 = iM743l0 + 1;
                i12 = i7 - this.f1464e;
            }
            int i15 = i11;
            i14 = i12 + i13;
            i8 = i15;
        }
        if (i14 >= 0) {
            int i16 = this.f1464e;
            i9 = (i14 / i16) + i8;
            i10 = (i14 % i16) + 1;
        } else {
            i9 = ((i14 / this.f1464e) + i8) - 1;
            int iAbs = Math.abs(i14);
            int i17 = this.f1464e;
            int i18 = iAbs % i17;
            if (i18 == 0) {
                i18 = i17;
            }
            i10 = (i17 - i18) + 1;
            if (i10 == 1) {
                i9++;
            }
        }
        int iM729X = this.f1463d.m729X(j7, iM743l0, iMo737f0);
        int iMo732a0 = this.f1463d.mo732a0(i9, i10);
        if (iM729X > iMo732a0) {
            iM729X = iMo732a0;
        }
        return this.f1463d.m746o0(i9, i10, iM729X) + jM735d0;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        AbstractC0876c abstractC0876c = this.f1463d;
        return abstractC0876c.mo737f0(j7, abstractC0876c.m743l0(j7));
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: c */
    public String mo200c(int i7, Locale locale) {
        return C0881h.m757b(locale).f1454e[i7];
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: f */
    public String mo203f(int i7, Locale locale) {
        return C0881h.m757b(locale).f1453d[i7];
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: j */
    public AbstractC0477h mo207j() {
        return this.f1463d.f1369l;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: k */
    public int mo208k(Locale locale) {
        return C0881h.m757b(locale).f1461l;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        return this.f1464e;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public /* bridge */ /* synthetic */ int mo210m() {
        return 1;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        return this.f1463d.f1373p;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: q */
    public boolean mo214q(long j7) {
        int iM743l0 = this.f1463d.m743l0(j7);
        return this.f1463d.mo749r0(iM743l0) && this.f1463d.mo737f0(j7, iM743l0) == this.f1465f;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: r */
    public /* bridge */ /* synthetic */ boolean mo215r() {
        return false;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: t */
    public long mo217t(long j7) {
        return j7 - mo218u(j7);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: u */
    public long mo218u(long j7) {
        int iM743l0 = this.f1463d.m743l0(j7);
        return this.f1463d.m747p0(iM743l0, this.f1463d.mo737f0(j7, iM743l0));
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: v */
    public long mo219v(long j7, int i7) {
        C2074b.m2477Q(this, i7, 1, this.f1464e);
        int iM743l0 = this.f1463d.m743l0(j7);
        AbstractC0876c abstractC0876c = this.f1463d;
        int iM729X = abstractC0876c.m729X(j7, iM743l0, abstractC0876c.mo737f0(j7, iM743l0));
        int iMo732a0 = this.f1463d.mo732a0(iM743l0, i7);
        if (iM729X > iMo732a0) {
            iM729X = iMo732a0;
        }
        return this.f1463d.m746o0(iM743l0, i7, iM729X) + this.f1463d.m735d0(j7);
    }

    @Override // p050f6.AbstractC1015b
    /* renamed from: y */
    public int mo752y(String str, Locale locale) {
        Integer num = C0881h.m757b(locale).f1458i.get(str);
        if (num != null) {
            return num.intValue();
        }
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        throw new C0479j(AbstractC0472c.f294l, str);
    }
}
