package p034d6;

import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p050f6.AbstractC1020g;
import p186x2.C2074b;

/* compiled from: BasicWeekyearDateTimeField.java */
/* renamed from: d6.f */
/* loaded from: classes.dex */
public class C0879f extends AbstractC1020g {

    /* renamed from: d */
    public final /* synthetic */ int f1446d;

    /* renamed from: e */
    public final AbstractC0876c f1447e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0879f(AbstractC0876c abstractC0876c, int i7) {
        super(AbstractC0472c.f297o, abstractC0876c.mo727V());
        this.f1446d = i7;
        if (i7 != 1) {
            AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
            this.f1447e = abstractC0876c;
        } else {
            AbstractC0472c abstractC0472c2 = AbstractC0472c.f288f;
            super(AbstractC0472c.f292j, abstractC0876c.mo727V());
            this.f1447e = abstractC0876c;
        }
    }

    @Override // p050f6.AbstractC1020g
    /* renamed from: A */
    public long mo754A(long j7, long j8) {
        switch (this.f1446d) {
        }
        return mo198a(j7, C2074b.m2466F(j8));
    }

    @Override // p050f6.AbstractC1020g
    /* renamed from: C */
    public long mo755C(long j7, long j8) {
        switch (this.f1446d) {
            case 0:
                if (j7 < j8) {
                    return -C2074b.m2466F(mo755C(j8, j7));
                }
                int iMo199b = mo199b(j7);
                int iMo199b2 = mo199b(j8);
                long jMo217t = mo217t(j7);
                long jMo217t2 = mo217t(j8);
                if (jMo217t2 >= 31449600000L && this.f1447e.m741j0(iMo199b) <= 52) {
                    jMo217t2 -= 604800000;
                }
                int i7 = iMo199b - iMo199b2;
                if (jMo217t < jMo217t2) {
                    i7--;
                }
                return i7;
            default:
                return j7 < j8 ? -this.f1447e.mo744m0(j8, j7) : this.f1447e.mo744m0(j7, j8);
        }
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: a */
    public long mo198a(long j7, int i7) {
        switch (this.f1446d) {
            case 0:
                return i7 == 0 ? j7 : mo219v(j7, mo199b(j7) + i7);
            default:
                if (i7 == 0) {
                    return j7;
                }
                int iMo199b = mo199b(j7);
                int i8 = iMo199b + i7;
                if ((iMo199b ^ i8) >= 0 || (iMo199b ^ i7) < 0) {
                    return mo219v(j7, i8);
                }
                throw new ArithmeticException("The calculation caused an overflow: " + iMo199b + " + " + i7);
        }
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        switch (this.f1446d) {
            case 0:
                return this.f1447e.m742k0(j7);
            default:
                return this.f1447e.m743l0(j7);
        }
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: j */
    public AbstractC0477h mo207j() {
        switch (this.f1446d) {
            case 0:
                return this.f1447e.f1370m;
            default:
                return this.f1447e.f1369l;
        }
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        switch (this.f1446d) {
        }
        return this.f1447e.mo734c0();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        switch (this.f1446d) {
        }
        return this.f1447e.mo736e0();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        return null;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: q */
    public boolean mo214q(long j7) {
        switch (this.f1446d) {
            case 0:
                AbstractC0876c abstractC0876c = this.f1447e;
                return abstractC0876c.m741j0(abstractC0876c.m742k0(j7)) > 52;
            default:
                return this.f1447e.mo749r0(mo199b(j7));
        }
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: r */
    public boolean mo215r() {
        return false;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: t */
    public long mo217t(long j7) {
        long jMo218u;
        switch (this.f1446d) {
            case 0:
                jMo218u = mo218u(j7);
                break;
            default:
                jMo218u = mo218u(j7);
                break;
        }
        return j7 - jMo218u;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: u */
    public long mo218u(long j7) {
        switch (this.f1446d) {
            case 0:
                long jMo218u = this.f1447e.f1352G.mo218u(j7);
                return this.f1447e.m739h0(jMo218u) > 1 ? jMo218u - ((r0 - 1) * 604800000) : jMo218u;
            default:
                return this.f1447e.m745n0(mo199b(j7));
        }
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: v */
    public long mo219v(long j7, int i7) {
        switch (this.f1446d) {
            case 0:
                C2074b.m2477Q(this, Math.abs(i7), this.f1447e.mo736e0(), this.f1447e.mo734c0());
                int iMo199b = mo199b(j7);
                if (iMo199b == i7) {
                    return j7;
                }
                int iM730Y = this.f1447e.m730Y(j7);
                int iM741j0 = this.f1447e.m741j0(iMo199b);
                int iM741j02 = this.f1447e.m741j0(i7);
                if (iM741j02 < iM741j0) {
                    iM741j0 = iM741j02;
                }
                AbstractC0876c abstractC0876c = this.f1447e;
                int iM740i0 = abstractC0876c.m740i0(j7, abstractC0876c.m743l0(j7));
                if (iM740i0 <= iM741j0) {
                    iM741j0 = iM740i0;
                }
                long jMo750s0 = this.f1447e.mo750s0(j7, i7);
                int iMo199b2 = mo199b(jMo750s0);
                if (iMo199b2 < i7) {
                    jMo750s0 += 604800000;
                } else if (iMo199b2 > i7) {
                    jMo750s0 -= 604800000;
                }
                return this.f1447e.f1349D.mo219v(((iM741j0 - this.f1447e.m739h0(jMo750s0)) * 604800000) + jMo750s0, iM730Y);
            default:
                C2074b.m2477Q(this, i7, this.f1447e.mo736e0(), this.f1447e.mo734c0());
                return this.f1447e.mo750s0(j7, i7);
        }
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: x */
    public long mo221x(long j7, int i7) {
        switch (this.f1446d) {
            case 1:
                C2074b.m2477Q(this, i7, this.f1447e.mo736e0() - 1, this.f1447e.mo734c0() + 1);
                return this.f1447e.mo750s0(j7, i7);
            default:
                return mo219v(j7, i7);
        }
    }
}
