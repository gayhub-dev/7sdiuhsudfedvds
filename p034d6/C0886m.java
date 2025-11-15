package p034d6;

import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p050f6.AbstractC1017d;
import p186x2.C2074b;

/* compiled from: ISOYearOfEraDateTimeField.java */
/* renamed from: d6.m */
/* loaded from: classes.dex */
public class C0886m extends AbstractC1017d {

    /* renamed from: c */
    public static final AbstractC0471b f1472c = new C0886m();

    /* JADX WARN: Illegal instructions before constructor call */
    public C0886m() {
        AbstractC0471b abstractC0471b = C0884k.f1467n0.f1356K;
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        super(abstractC0471b, AbstractC0472c.f289g);
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
        return iMo199b < 0 ? -iMo199b : iMo199b;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        return this.f1913b.mo209l();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        return 0;
    }

    @Override // p050f6.AbstractC1017d, p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        return C0884k.f1467n0.f1375r;
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
        C2074b.m2477Q(this, i7, 0, mo209l());
        if (this.f1913b.mo199b(j7) < 0) {
            i7 = -i7;
        }
        return super.mo219v(j7, i7);
    }
}
