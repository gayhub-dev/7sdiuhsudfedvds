package p050f6;

import p016b6.AbstractC0477h;
import p016b6.AbstractC0478i;
import p186x2.C2074b;

/* compiled from: ScaledDurationField.java */
/* renamed from: f6.n */
/* loaded from: classes.dex */
public class C1027n extends C1018e {
    private static final long serialVersionUID = -3205227092378684157L;

    /* renamed from: g */
    public final int f1935g;

    public C1027n(AbstractC0477h abstractC0477h, AbstractC0478i abstractC0478i, int i7) {
        super(abstractC0477h, abstractC0478i);
        if (i7 == 0 || i7 == 1) {
            throw new IllegalArgumentException("The scalar must not be 0 or 1");
        }
        this.f1935g = i7;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: a */
    public long mo251a(long j7, int i7) {
        return this.f1914f.mo252b(j7, i7 * this.f1935g);
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: b */
    public long mo252b(long j7, long j8) {
        return this.f1914f.mo252b(j7, C2074b.m2464D(j8, this.f1935g));
    }

    @Override // p050f6.AbstractC1016c, p016b6.AbstractC0477h
    /* renamed from: c */
    public int mo253c(long j7, long j8) {
        return this.f1914f.mo253c(j7, j8) / this.f1935g;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: e */
    public long mo254e(long j7, long j8) {
        return this.f1914f.mo254e(j7, j8) / this.f1935g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1027n)) {
            return false;
        }
        C1027n c1027n = (C1027n) obj;
        return this.f1914f.equals(c1027n.f1914f) && this.f1912e == c1027n.f1912e && this.f1935g == c1027n.f1935g;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: h */
    public long mo256h() {
        return this.f1914f.mo256h() * this.f1935g;
    }

    public int hashCode() {
        long j7 = this.f1935g;
        return this.f1914f.hashCode() + this.f1912e.hashCode() + ((int) (j7 ^ (j7 >>> 32)));
    }
}
