package p034d6;

import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p050f6.AbstractC1017d;
import p186x2.C2074b;

/* compiled from: GJYearOfEraDateTimeField.java */
/* renamed from: d6.j */
/* loaded from: classes.dex */
public final class C0883j extends AbstractC1017d {

    /* renamed from: c */
    public final AbstractC0876c f1466c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0883j(AbstractC0471b abstractC0471b, AbstractC0876c abstractC0876c) {
        super(abstractC0471b, AbstractC0472c.f289g);
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        this.f1466c = abstractC0876c;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: a */
    public long mo198a(long j7, int i7) {
        return this.f1913b.mo198a(j7, i7);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        int iMo199b = this.f1913b.mo199b(j7);
        return iMo199b <= 0 ? 1 - iMo199b : iMo199b;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        return this.f1913b.mo209l();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        return 1;
    }

    @Override // p050f6.AbstractC1017d, p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        return this.f1466c.f1375r;
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
        C2074b.m2477Q(this, i7, 1, mo209l());
        if (this.f1466c.m743l0(j7) <= 0) {
            i7 = 1 - i7;
        }
        return this.f1913b.mo219v(j7, i7);
    }
}
