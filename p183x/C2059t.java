package p183x;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import p009b.C0413b;
import p142r0.C1820e;
import p142r0.C1823h;
import p162u.C1966j;
import p162u.InterfaceC1964h;
import p162u.InterfaceC1969m;

/* compiled from: ResourceCacheKey.java */
/* renamed from: x.t */
/* loaded from: classes.dex */
public final class C2059t implements InterfaceC1964h {

    /* renamed from: i */
    public static final C1820e<Class<?>, byte[]> f6133i = new C1820e<>(50);

    /* renamed from: b */
    public final InterfaceC1964h f6134b;

    /* renamed from: c */
    public final InterfaceC1964h f6135c;

    /* renamed from: d */
    public final int f6136d;

    /* renamed from: e */
    public final int f6137e;

    /* renamed from: f */
    public final Class<?> f6138f;

    /* renamed from: g */
    public final C1966j f6139g;

    /* renamed from: h */
    public final InterfaceC1969m<?> f6140h;

    public C2059t(InterfaceC1964h interfaceC1964h, InterfaceC1964h interfaceC1964h2, int i7, int i8, InterfaceC1969m<?> interfaceC1969m, Class<?> cls, C1966j c1966j) {
        this.f6134b = interfaceC1964h;
        this.f6135c = interfaceC1964h2;
        this.f6136d = i7;
        this.f6137e = i8;
        this.f6140h = interfaceC1969m;
        this.f6138f = cls;
        this.f6139g = c1966j;
    }

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        byte[] bArrArray = ByteBuffer.allocate(8).putInt(this.f6136d).putInt(this.f6137e).array();
        this.f6135c.mo130b(messageDigest);
        this.f6134b.mo130b(messageDigest);
        messageDigest.update(bArrArray);
        InterfaceC1969m<?> interfaceC1969m = this.f6140h;
        if (interfaceC1969m != null) {
            interfaceC1969m.mo130b(messageDigest);
        }
        this.f6139g.mo130b(messageDigest);
        C1820e<Class<?>, byte[]> c1820e = f6133i;
        byte[] bArrM2051a = c1820e.m2051a(this.f6138f);
        if (bArrM2051a == null) {
            bArrM2051a = this.f6138f.getName().getBytes(InterfaceC1964h.f5736a);
            c1820e.m2053d(this.f6138f, bArrM2051a);
        }
        messageDigest.update(bArrM2051a);
    }

    @Override // p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        if (!(obj instanceof C2059t)) {
            return false;
        }
        C2059t c2059t = (C2059t) obj;
        return this.f6137e == c2059t.f6137e && this.f6136d == c2059t.f6136d && C1823h.m2058b(this.f6140h, c2059t.f6140h) && this.f6138f.equals(c2059t.f6138f) && this.f6134b.equals(c2059t.f6134b) && this.f6135c.equals(c2059t.f6135c) && this.f6139g.equals(c2059t.f6139g);
    }

    @Override // p162u.InterfaceC1964h
    public int hashCode() {
        int iHashCode = ((((this.f6135c.hashCode() + (this.f6134b.hashCode() * 31)) * 31) + this.f6136d) * 31) + this.f6137e;
        InterfaceC1969m<?> interfaceC1969m = this.f6140h;
        if (interfaceC1969m != null) {
            iHashCode = (iHashCode * 31) + interfaceC1969m.hashCode();
        }
        return this.f6139g.hashCode() + ((this.f6138f.hashCode() + (iHashCode * 31)) * 31);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("ResourceCacheKey{sourceKey=");
        sbM112a.append(this.f6134b);
        sbM112a.append(", signature=");
        sbM112a.append(this.f6135c);
        sbM112a.append(", width=");
        sbM112a.append(this.f6136d);
        sbM112a.append(", height=");
        sbM112a.append(this.f6137e);
        sbM112a.append(", decodedResourceClass=");
        sbM112a.append(this.f6138f);
        sbM112a.append(", transformation='");
        sbM112a.append(this.f6140h);
        sbM112a.append('\'');
        sbM112a.append(", options=");
        sbM112a.append(this.f6139g);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
