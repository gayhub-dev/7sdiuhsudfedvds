package p034d6;

import java.util.Locale;
import java.util.Objects;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p016b6.C0479j;
import p050f6.AbstractC1024k;

/* compiled from: BasicDayOfMonthDateTimeField.java */
/* renamed from: d6.d */
/* loaded from: classes.dex */
public final class C0877d extends AbstractC1024k {

    /* renamed from: d */
    public final /* synthetic */ int f1440d;

    /* renamed from: e */
    public final AbstractC0876c f1441e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0877d(AbstractC0876c abstractC0876c, AbstractC0477h abstractC0477h, int i7) {
        super(AbstractC0472c.f295m, abstractC0477h);
        this.f1440d = i7;
        if (i7 == 1) {
            AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
            super(AbstractC0472c.f293k, abstractC0477h);
            this.f1441e = abstractC0876c;
        } else if (i7 == 2) {
            AbstractC0472c abstractC0472c2 = AbstractC0472c.f288f;
            super(AbstractC0472c.f298p, abstractC0477h);
            this.f1441e = abstractC0876c;
        } else if (i7 != 3) {
            AbstractC0472c abstractC0472c3 = AbstractC0472c.f288f;
            this.f1441e = abstractC0876c;
        } else {
            AbstractC0472c abstractC0472c4 = AbstractC0472c.f288f;
            super(AbstractC0472c.f299q, abstractC0477h);
            this.f1441e = abstractC0876c;
        }
    }

    @Override // p050f6.AbstractC1024k
    /* renamed from: A */
    public int mo751A(long j7, int i7) {
        switch (this.f1440d) {
            case 1:
                Objects.requireNonNull(this.f1441e);
                if (i7 > 365 || i7 < 1) {
                    break;
                }
                break;
            case 2:
                if (i7 > 52) {
                    break;
                }
                break;
        }
        return mo753z(j7);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        switch (this.f1440d) {
            case 0:
                AbstractC0876c abstractC0876c = this.f1441e;
                int iM743l0 = abstractC0876c.m743l0(j7);
                return abstractC0876c.m729X(j7, iM743l0, abstractC0876c.mo737f0(j7, iM743l0));
            case 1:
                AbstractC0876c abstractC0876c2 = this.f1441e;
                return ((int) ((j7 - abstractC0876c2.m745n0(abstractC0876c2.m743l0(j7))) / 86400000)) + 1;
            case 2:
                AbstractC0876c abstractC0876c3 = this.f1441e;
                return abstractC0876c3.m740i0(j7, abstractC0876c3.m743l0(j7));
            default:
                return this.f1441e.m730Y(j7);
        }
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: c */
    public String mo200c(int i7, Locale locale) {
        switch (this.f1440d) {
            case 3:
                return C0881h.m757b(locale).f1452c[i7];
            default:
                return mo203f(i7, locale);
        }
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: f */
    public String mo203f(int i7, Locale locale) {
        switch (this.f1440d) {
            case 3:
                return C0881h.m757b(locale).f1451b[i7];
            default:
                return Integer.toString(i7);
        }
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: k */
    public int mo208k(Locale locale) {
        switch (this.f1440d) {
            case 3:
                return C0881h.m757b(locale).f1460k;
            default:
                return super.mo208k(locale);
        }
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        switch (this.f1440d) {
            case 0:
                Objects.requireNonNull(this.f1441e);
                return 31;
            case 1:
                Objects.requireNonNull(this.f1441e);
                return 366;
            case 2:
                return 53;
            default:
                return 7;
        }
    }

    @Override // p050f6.AbstractC1024k, p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        return 1;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        switch (this.f1440d) {
            case 0:
                return this.f1441e.f1372o;
            case 1:
                return this.f1441e.f1373p;
            case 2:
                return this.f1441e.f1371n;
            default:
                return this.f1441e.f1370m;
        }
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: q */
    public boolean mo214q(long j7) {
        switch (this.f1440d) {
        }
        return this.f1441e.mo748q0(j7);
    }

    @Override // p050f6.AbstractC1024k, p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: t */
    public long mo217t(long j7) {
        switch (this.f1440d) {
            case 2:
                return super.mo217t(j7 + 259200000);
            default:
                return super.mo217t(j7);
        }
    }

    @Override // p050f6.AbstractC1024k, p016b6.AbstractC0471b
    /* renamed from: u */
    public long mo218u(long j7) {
        switch (this.f1440d) {
            case 2:
                return super.mo218u(j7 + 259200000) - 259200000;
            default:
                return super.mo218u(j7);
        }
    }

    @Override // p050f6.AbstractC1015b
    /* renamed from: y */
    public int mo752y(String str, Locale locale) {
        switch (this.f1440d) {
            case 3:
                Integer num = C0881h.m757b(locale).f1457h.get(str);
                if (num != null) {
                    return num.intValue();
                }
                AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
                throw new C0479j(AbstractC0472c.f299q, str);
            default:
                try {
                    return Integer.parseInt(str);
                } catch (NumberFormatException unused) {
                    throw new C0479j(this.f1911a, str);
                }
        }
    }

    @Override // p050f6.AbstractC1015b
    /* renamed from: z */
    public int mo753z(long j7) {
        switch (this.f1440d) {
            case 0:
                AbstractC0876c abstractC0876c = this.f1441e;
                int iM743l0 = abstractC0876c.m743l0(j7);
                return abstractC0876c.mo732a0(iM743l0, abstractC0876c.mo737f0(j7, iM743l0));
            case 1:
                return this.f1441e.mo749r0(this.f1441e.m743l0(j7)) ? 366 : 365;
            case 2:
                return this.f1441e.m741j0(this.f1441e.m742k0(j7));
            default:
                return mo209l();
        }
    }
}
