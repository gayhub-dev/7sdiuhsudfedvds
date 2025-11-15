package p066h6;

import p016b6.AbstractC0475f;

/* compiled from: FixedDateTimeZone.java */
/* renamed from: h6.d */
/* loaded from: classes.dex */
public final class C1121d extends AbstractC0475f {
    private static final long serialVersionUID = -3513011772763289092L;

    /* renamed from: j */
    public final String f2439j;

    /* renamed from: k */
    public final int f2440k;

    /* renamed from: l */
    public final int f2441l;

    public C1121d(String str, String str2, int i7, int i8) {
        super(str);
        this.f2439j = str2;
        this.f2440k = i7;
        this.f2441l = i8;
    }

    @Override // p016b6.AbstractC0475f
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1121d)) {
            return false;
        }
        C1121d c1121d = (C1121d) obj;
        return this.f318e.equals(c1121d.f318e) && this.f2441l == c1121d.f2441l && this.f2440k == c1121d.f2440k;
    }

    @Override // p016b6.AbstractC0475f
    public int hashCode() {
        return (this.f2440k * 31) + (this.f2441l * 37) + this.f318e.hashCode();
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: i */
    public String mo244i(long j7) {
        return this.f2439j;
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: k */
    public int mo245k(long j7) {
        return this.f2440k;
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: l */
    public int mo246l(long j7) {
        return this.f2440k;
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: n */
    public int mo247n(long j7) {
        return this.f2441l;
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: o */
    public boolean mo248o() {
        return true;
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: p */
    public long mo249p(long j7) {
        return j7;
    }

    @Override // p016b6.AbstractC0475f
    /* renamed from: r */
    public long mo250r(long j7) {
        return j7;
    }
}
