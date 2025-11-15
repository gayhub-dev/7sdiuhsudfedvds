package p050f6;

import p016b6.AbstractC0478i;
import p186x2.C2074b;

/* compiled from: PreciseDurationField.java */
/* renamed from: f6.l */
/* loaded from: classes.dex */
public class C1025l extends AbstractC1016c {
    private static final long serialVersionUID = -8346152187724495365L;

    /* renamed from: f */
    public final long f1931f;

    public C1025l(AbstractC0478i abstractC0478i, long j7) {
        super(abstractC0478i);
        this.f1931f = j7;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: a */
    public long mo251a(long j7, int i7) {
        return C2074b.m2463C(j7, i7 * this.f1931f);
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: b */
    public long mo252b(long j7, long j8) {
        long j9 = this.f1931f;
        if (j9 != 1) {
            if (j8 == 1) {
                j8 = j9;
            } else {
                long j10 = 0;
                if (j8 != 0 && j9 != 0) {
                    j10 = j8 * j9;
                    if (j10 / j9 != j8 || ((j8 == Long.MIN_VALUE && j9 == -1) || (j9 == Long.MIN_VALUE && j8 == -1))) {
                        throw new ArithmeticException("Multiplication overflows a long: " + j8 + " * " + j9);
                    }
                }
                j8 = j10;
            }
        }
        return C2074b.m2463C(j7, j8);
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: e */
    public long mo254e(long j7, long j8) {
        return C2074b.m2465E(j7, j8) / this.f1931f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1025l)) {
            return false;
        }
        C1025l c1025l = (C1025l) obj;
        return this.f1912e == c1025l.f1912e && this.f1931f == c1025l.f1931f;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: h */
    public final long mo256h() {
        return this.f1931f;
    }

    public int hashCode() {
        long j7 = this.f1931f;
        return this.f1912e.hashCode() + ((int) (j7 ^ (j7 >>> 32)));
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: i */
    public final boolean mo257i() {
        return true;
    }
}
