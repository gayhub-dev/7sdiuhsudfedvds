package p050f6;

import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p186x2.C2074b;

/* compiled from: ZeroIsMaxDateTimeField.java */
/* renamed from: f6.q */
/* loaded from: classes.dex */
public final class C1030q extends AbstractC1017d {
    public C1030q(AbstractC0471b abstractC0471b, AbstractC0472c abstractC0472c) {
        super(abstractC0471b, abstractC0472c);
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
        return iMo199b == 0 ? mo209l() : iMo199b;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: j */
    public AbstractC0477h mo207j() {
        return this.f1913b.mo207j();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        return this.f1913b.mo209l() + 1;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        return 1;
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
        int iMo209l = mo209l();
        C2074b.m2477Q(this, i7, 1, iMo209l);
        if (i7 == iMo209l) {
            i7 = 0;
        }
        return this.f1913b.mo219v(j7, i7);
    }
}
