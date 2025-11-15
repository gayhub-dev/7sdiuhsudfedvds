package p145r3;

/* compiled from: Pair.java */
/* renamed from: r3.a */
/* loaded from: classes.dex */
public final class C1834a<A, B> {

    /* renamed from: a */
    public final A f5340a;

    /* renamed from: b */
    public final B f5341b;

    public C1834a(A a7, B b7) {
        this.f5340a = a7;
        this.f5341b = b7;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1834a.class != obj.getClass()) {
            return false;
        }
        C1834a c1834a = (C1834a) obj;
        A a7 = this.f5340a;
        if (a7 == null) {
            if (c1834a.f5340a != null) {
                return false;
            }
        } else if (!a7.equals(c1834a.f5340a)) {
            return false;
        }
        B b7 = this.f5341b;
        if (b7 == null) {
            if (c1834a.f5341b != null) {
                return false;
            }
        } else if (!b7.equals(c1834a.f5341b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        A a7 = this.f5340a;
        int iHashCode = ((a7 == null ? 0 : a7.hashCode()) + 31) * 31;
        B b7 = this.f5341b;
        return iHashCode + (b7 != null ? b7.hashCode() : 0);
    }
}
