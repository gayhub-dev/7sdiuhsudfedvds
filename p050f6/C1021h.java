package p050f6;

import java.io.Serializable;
import java.util.Objects;
import p016b6.AbstractC0477h;
import p016b6.AbstractC0478i;
import p186x2.C2074b;

/* compiled from: MillisDurationField.java */
/* renamed from: f6.h */
/* loaded from: classes.dex */
public final class C1021h extends AbstractC0477h implements Serializable {

    /* renamed from: e */
    public static final AbstractC0477h f1923e = new C1021h();
    private static final long serialVersionUID = 2656707858124633367L;

    private Object readResolve() {
        return f1923e;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: a */
    public long mo251a(long j7, int i7) {
        return C2074b.m2463C(j7, i7);
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: b */
    public long mo252b(long j7, long j8) {
        return C2074b.m2463C(j7, j8);
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: c */
    public int mo253c(long j7, long j8) {
        return C2074b.m2466F(C2074b.m2465E(j7, j8));
    }

    @Override // java.lang.Comparable
    public int compareTo(AbstractC0477h abstractC0477h) {
        long jMo256h = abstractC0477h.mo256h();
        if (1 == jMo256h) {
            return 0;
        }
        return 1 < jMo256h ? -1 : 1;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: e */
    public long mo254e(long j7, long j8) {
        return C2074b.m2465E(j7, j8);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C1021h)) {
            return false;
        }
        Objects.requireNonNull((C1021h) obj);
        return true;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: f */
    public AbstractC0478i mo255f() {
        return AbstractC0478i.f333q;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: h */
    public final long mo256h() {
        return 1L;
    }

    public int hashCode() {
        return (int) 1;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: i */
    public final boolean mo257i() {
        return true;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: l */
    public boolean mo258l() {
        return true;
    }

    public String toString() {
        return "DurationField[millis]";
    }
}
