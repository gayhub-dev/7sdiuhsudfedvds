package p142r0;

import p009b.C0413b;

/* compiled from: MultiClassKey.java */
/* renamed from: r0.g */
/* loaded from: classes.dex */
public class C1822g {

    /* renamed from: a */
    public Class<?> f5297a;

    /* renamed from: b */
    public Class<?> f5298b;

    /* renamed from: c */
    public Class<?> f5299c;

    public C1822g() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1822g.class != obj.getClass()) {
            return false;
        }
        C1822g c1822g = (C1822g) obj;
        return this.f5297a.equals(c1822g.f5297a) && this.f5298b.equals(c1822g.f5298b) && C1823h.m2058b(this.f5299c, c1822g.f5299c);
    }

    public int hashCode() {
        int iHashCode = (this.f5298b.hashCode() + (this.f5297a.hashCode() * 31)) * 31;
        Class<?> cls = this.f5299c;
        return iHashCode + (cls != null ? cls.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("MultiClassKey{first=");
        sbM112a.append(this.f5297a);
        sbM112a.append(", second=");
        sbM112a.append(this.f5298b);
        sbM112a.append('}');
        return sbM112a.toString();
    }

    public C1822g(Class<?> cls, Class<?> cls2) {
        this.f5297a = cls;
        this.f5298b = cls2;
        this.f5299c = null;
    }

    public C1822g(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        this.f5297a = cls;
        this.f5298b = cls2;
        this.f5299c = cls3;
    }
}
