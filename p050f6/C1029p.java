package p050f6;

import java.io.Serializable;
import java.util.HashMap;
import p009b.C0413b;
import p016b6.AbstractC0477h;
import p016b6.AbstractC0478i;

/* compiled from: UnsupportedDurationField.java */
/* renamed from: f6.p */
/* loaded from: classes.dex */
public final class C1029p extends AbstractC0477h implements Serializable {

    /* renamed from: f */
    public static HashMap<AbstractC0478i, C1029p> f1939f = null;
    private static final long serialVersionUID = -6390301302770925357L;

    /* renamed from: e */
    public final AbstractC0478i f1940e;

    public C1029p(AbstractC0478i abstractC0478i) {
        this.f1940e = abstractC0478i;
    }

    /* renamed from: m */
    public static synchronized C1029p m1034m(AbstractC0478i abstractC0478i) {
        C1029p c1029p;
        HashMap<AbstractC0478i, C1029p> map = f1939f;
        if (map == null) {
            f1939f = new HashMap<>(7);
            c1029p = null;
        } else {
            c1029p = map.get(abstractC0478i);
        }
        if (c1029p == null) {
            c1029p = new C1029p(abstractC0478i);
            f1939f.put(abstractC0478i, c1029p);
        }
        return c1029p;
    }

    private Object readResolve() {
        return m1034m(this.f1940e);
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: a */
    public long mo251a(long j7, int i7) {
        throw m1035n();
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: b */
    public long mo252b(long j7, long j8) {
        throw m1035n();
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: c */
    public int mo253c(long j7, long j8) {
        throw m1035n();
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(AbstractC0477h abstractC0477h) {
        return 0;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: e */
    public long mo254e(long j7, long j8) {
        throw m1035n();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1029p)) {
            return false;
        }
        String str = ((C1029p) obj).f1940e.f334e;
        return str == null ? this.f1940e.f334e == null : str.equals(this.f1940e.f334e);
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: f */
    public final AbstractC0478i mo255f() {
        return this.f1940e;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: h */
    public long mo256h() {
        return 0L;
    }

    public int hashCode() {
        return this.f1940e.f334e.hashCode();
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: i */
    public boolean mo257i() {
        return true;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: l */
    public boolean mo258l() {
        return false;
    }

    /* renamed from: n */
    public final UnsupportedOperationException m1035n() {
        return new UnsupportedOperationException(this.f1940e + " field is unsupported");
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("UnsupportedDurationField[");
        sbM112a.append(this.f1940e.f334e);
        sbM112a.append(']');
        return sbM112a.toString();
    }
}
