package p050f6;

import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p186x2.C2074b;

/* compiled from: PreciseDurationDateTimeField.java */
/* renamed from: f6.k */
/* loaded from: classes.dex */
public abstract class AbstractC1024k extends AbstractC1015b {

    /* renamed from: b */
    public final long f1929b;

    /* renamed from: c */
    public final AbstractC0477h f1930c;

    public AbstractC1024k(AbstractC0472c abstractC0472c, AbstractC0477h abstractC0477h) {
        super(abstractC0472c);
        if (!abstractC0477h.mo257i()) {
            throw new IllegalArgumentException("Unit duration field must be precise");
        }
        long jMo256h = abstractC0477h.mo256h();
        this.f1929b = jMo256h;
        if (jMo256h < 1) {
            throw new IllegalArgumentException("The unit milliseconds must be at least 1");
        }
        this.f1930c = abstractC0477h;
    }

    /* renamed from: A */
    public int mo751A(long j7, int i7) {
        return mo753z(j7);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: i */
    public AbstractC0477h mo206i() {
        return this.f1930c;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        return 0;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: r */
    public boolean mo215r() {
        return false;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: t */
    public long mo217t(long j7) {
        if (j7 >= 0) {
            return j7 % this.f1929b;
        }
        long j8 = this.f1929b;
        return (((j7 + 1) % j8) + j8) - 1;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: u */
    public long mo218u(long j7) {
        long j8;
        if (j7 >= 0) {
            j8 = j7 % this.f1929b;
        } else {
            long j9 = j7 + 1;
            j8 = this.f1929b;
            j7 = j9 - (j9 % j8);
        }
        return j7 - j8;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: v */
    public long mo219v(long j7, int i7) {
        C2074b.m2477Q(this, i7, mo210m(), mo751A(j7, i7));
        return ((i7 - mo199b(j7)) * this.f1929b) + j7;
    }
}
