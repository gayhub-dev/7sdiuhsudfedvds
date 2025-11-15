package p050f6;

import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p186x2.C2074b;

/* compiled from: DividedDateTimeField.java */
/* renamed from: f6.f */
/* loaded from: classes.dex */
public class C1019f extends AbstractC1017d {

    /* renamed from: c */
    public final int f1915c;

    /* renamed from: d */
    public final AbstractC0477h f1916d;

    /* renamed from: e */
    public final AbstractC0477h f1917e;

    /* renamed from: f */
    public final int f1918f;

    /* renamed from: g */
    public final int f1919g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1019f(AbstractC0471b abstractC0471b, AbstractC0472c abstractC0472c, int i7) {
        super(abstractC0471b, abstractC0472c);
        AbstractC0477h abstractC0477hMo212o = abstractC0471b.mo212o();
        if (i7 < 2) {
            throw new IllegalArgumentException("The divisor must be at least 2");
        }
        AbstractC0477h abstractC0477hMo206i = abstractC0471b.mo206i();
        if (abstractC0477hMo206i == null) {
            this.f1916d = null;
        } else {
            this.f1916d = new C1027n(abstractC0477hMo206i, ((AbstractC0472c.a) abstractC0472c).f311D, i7);
        }
        this.f1917e = abstractC0477hMo212o;
        this.f1915c = i7;
        int iMo210m = abstractC0471b.mo210m();
        int i8 = iMo210m >= 0 ? iMo210m / i7 : ((iMo210m + 1) / i7) - 1;
        int iMo209l = abstractC0471b.mo209l();
        int i9 = iMo209l >= 0 ? iMo209l / i7 : ((iMo209l + 1) / i7) - 1;
        this.f1918f = i8;
        this.f1919g = i9;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: a */
    public long mo198a(long j7, int i7) {
        return this.f1913b.mo198a(j7, i7 * this.f1915c);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        int iMo199b = this.f1913b.mo199b(j7);
        return iMo199b >= 0 ? iMo199b / this.f1915c : ((iMo199b + 1) / this.f1915c) - 1;
    }

    @Override // p050f6.AbstractC1017d, p016b6.AbstractC0471b
    /* renamed from: i */
    public AbstractC0477h mo206i() {
        return this.f1916d;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        return this.f1919g;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        return this.f1918f;
    }

    @Override // p050f6.AbstractC1017d, p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        AbstractC0477h abstractC0477h = this.f1917e;
        return abstractC0477h != null ? abstractC0477h : super.mo212o();
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: t */
    public long mo217t(long j7) {
        return mo219v(j7, mo199b(this.f1913b.mo217t(j7)));
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: u */
    public long mo218u(long j7) {
        AbstractC0471b abstractC0471b = this.f1913b;
        return abstractC0471b.mo218u(abstractC0471b.mo219v(j7, mo199b(j7) * this.f1915c));
    }

    @Override // p050f6.AbstractC1017d, p016b6.AbstractC0471b
    /* renamed from: v */
    public long mo219v(long j7, int i7) {
        int i8;
        C2074b.m2477Q(this, i7, this.f1918f, this.f1919g);
        int iMo199b = this.f1913b.mo199b(j7);
        if (iMo199b >= 0) {
            i8 = iMo199b % this.f1915c;
        } else {
            int i9 = this.f1915c;
            i8 = ((iMo199b + 1) % i9) + (i9 - 1);
        }
        return this.f1913b.mo219v(j7, (i7 * this.f1915c) + i8);
    }
}
