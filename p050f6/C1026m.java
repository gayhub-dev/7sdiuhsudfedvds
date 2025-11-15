package p050f6;

import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p186x2.C2074b;

/* compiled from: RemainderDateTimeField.java */
/* renamed from: f6.m */
/* loaded from: classes.dex */
public class C1026m extends AbstractC1017d {

    /* renamed from: c */
    public final int f1932c;

    /* renamed from: d */
    public final AbstractC0477h f1933d;

    /* renamed from: e */
    public final AbstractC0477h f1934e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1026m(C1019f c1019f, AbstractC0472c abstractC0472c) {
        super(c1019f.f1913b, abstractC0472c);
        AbstractC0477h abstractC0477hMo206i = c1019f.f1913b.mo206i();
        this.f1932c = c1019f.f1915c;
        this.f1933d = abstractC0477hMo206i;
        this.f1934e = c1019f.f1916d;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        int iMo199b = this.f1913b.mo199b(j7);
        if (iMo199b >= 0) {
            return iMo199b % this.f1932c;
        }
        int i7 = this.f1932c;
        return ((iMo199b + 1) % i7) + (i7 - 1);
    }

    @Override // p050f6.AbstractC1017d, p016b6.AbstractC0471b
    /* renamed from: i */
    public AbstractC0477h mo206i() {
        return this.f1933d;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        return this.f1932c - 1;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        return 0;
    }

    @Override // p050f6.AbstractC1017d, p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        return this.f1934e;
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
        C2074b.m2477Q(this, i7, 0, this.f1932c - 1);
        int iMo199b = this.f1913b.mo199b(j7);
        return this.f1913b.mo219v(j7, ((iMo199b >= 0 ? iMo199b / this.f1932c : ((iMo199b + 1) / this.f1932c) - 1) * this.f1932c) + i7);
    }

    public C1026m(C1019f c1019f, AbstractC0477h abstractC0477h, AbstractC0472c abstractC0472c) {
        super(c1019f.f1913b, abstractC0472c);
        this.f1932c = c1019f.f1915c;
        this.f1933d = abstractC0477h;
        this.f1934e = c1019f.f1916d;
    }

    public C1026m(AbstractC0471b abstractC0471b, AbstractC0477h abstractC0477h, AbstractC0472c abstractC0472c, int i7) {
        super(abstractC0471b, abstractC0472c);
        if (i7 >= 2) {
            this.f1934e = abstractC0477h;
            this.f1933d = abstractC0471b.mo206i();
            this.f1932c = i7;
            return;
        }
        throw new IllegalArgumentException("The divisor must be at least 2");
    }
}
