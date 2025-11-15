package p050f6;

import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p186x2.C2074b;

/* compiled from: PreciseDateTimeField.java */
/* renamed from: f6.j */
/* loaded from: classes.dex */
public class C1023j extends AbstractC1024k {

    /* renamed from: d */
    public final int f1927d;

    /* renamed from: e */
    public final AbstractC0477h f1928e;

    public C1023j(AbstractC0472c abstractC0472c, AbstractC0477h abstractC0477h, AbstractC0477h abstractC0477h2) {
        super(abstractC0472c, abstractC0477h);
        if (!abstractC0477h2.mo257i()) {
            throw new IllegalArgumentException("Range duration field must be precise");
        }
        int iMo256h = (int) (abstractC0477h2.mo256h() / this.f1929b);
        this.f1927d = iMo256h;
        if (iMo256h < 2) {
            throw new IllegalArgumentException("The effective range must be at least 2");
        }
        this.f1928e = abstractC0477h2;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        if (j7 >= 0) {
            return (int) ((j7 / this.f1929b) % this.f1927d);
        }
        int i7 = this.f1927d;
        return (i7 - 1) + ((int) (((j7 + 1) / this.f1929b) % i7));
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        return this.f1927d - 1;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        return this.f1928e;
    }

    @Override // p050f6.AbstractC1024k, p016b6.AbstractC0471b
    /* renamed from: v */
    public long mo219v(long j7, int i7) {
        C2074b.m2477Q(this, i7, 0, this.f1927d - 1);
        return ((i7 - mo199b(j7)) * this.f1929b) + j7;
    }
}
