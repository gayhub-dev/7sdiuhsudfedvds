package p050f6;

import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p186x2.C2074b;

/* compiled from: OffsetDateTimeField.java */
/* renamed from: f6.i */
/* loaded from: classes.dex */
public class C1022i extends AbstractC1017d {

    /* renamed from: c */
    public final int f1924c;

    /* renamed from: d */
    public final int f1925d;

    /* renamed from: e */
    public final int f1926e;

    public C1022i(AbstractC0471b abstractC0471b, AbstractC0472c abstractC0472c, int i7, int i8, int i9) {
        super(abstractC0471b, abstractC0472c);
        if (i7 == 0) {
            throw new IllegalArgumentException("The offset cannot be zero");
        }
        this.f1924c = i7;
        if (i8 < abstractC0471b.mo210m() + i7) {
            this.f1925d = abstractC0471b.mo210m() + i7;
        } else {
            this.f1925d = i8;
        }
        if (i9 > abstractC0471b.mo209l() + i7) {
            this.f1926e = abstractC0471b.mo209l() + i7;
        } else {
            this.f1926e = i9;
        }
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: a */
    public long mo198a(long j7, int i7) {
        long jMo198a = super.mo198a(j7, i7);
        C2074b.m2477Q(this, mo199b(jMo198a), this.f1925d, this.f1926e);
        return jMo198a;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        return this.f1913b.mo199b(j7) + this.f1924c;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: j */
    public AbstractC0477h mo207j() {
        return this.f1913b.mo207j();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        return this.f1926e;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        return this.f1925d;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: q */
    public boolean mo214q(long j7) {
        return this.f1913b.mo214q(j7);
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: t */
    public long mo217t(long j7) {
        return this.f1913b.mo217t(j7);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: u */
    public long mo218u(long j7) {
        return this.f1913b.mo218u(j7);
    }

    @Override // p050f6.AbstractC1017d, p016b6.AbstractC0471b
    /* renamed from: v */
    public long mo219v(long j7, int i7) {
        C2074b.m2477Q(this, i7, this.f1925d, this.f1926e);
        return super.mo219v(j7, i7 - this.f1924c);
    }
}
