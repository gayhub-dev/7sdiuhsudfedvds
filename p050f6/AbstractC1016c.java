package p050f6;

import java.io.Serializable;
import p009b.C0413b;
import p016b6.AbstractC0477h;
import p016b6.AbstractC0478i;
import p186x2.C2074b;

/* compiled from: BaseDurationField.java */
/* renamed from: f6.c */
/* loaded from: classes.dex */
public abstract class AbstractC1016c extends AbstractC0477h implements Serializable {
    private static final long serialVersionUID = -2554245107589433218L;

    /* renamed from: e */
    public final AbstractC0478i f1912e;

    public AbstractC1016c(AbstractC0478i abstractC0478i) {
        if (abstractC0478i == null) {
            throw new IllegalArgumentException("The type must not be null");
        }
        this.f1912e = abstractC0478i;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: c */
    public int mo253c(long j7, long j8) {
        return C2074b.m2466F(mo254e(j7, j8));
    }

    @Override // java.lang.Comparable
    public int compareTo(AbstractC0477h abstractC0477h) {
        long jMo256h = abstractC0477h.mo256h();
        long jMo256h2 = mo256h();
        if (jMo256h2 == jMo256h) {
            return 0;
        }
        return jMo256h2 < jMo256h ? -1 : 1;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: f */
    public final AbstractC0478i mo255f() {
        return this.f1912e;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: l */
    public final boolean mo258l() {
        return true;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("DurationField[");
        sbM112a.append(this.f1912e.f334e);
        sbM112a.append(']');
        return sbM112a.toString();
    }
}
