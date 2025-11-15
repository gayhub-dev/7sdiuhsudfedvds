package p183x;

import java.security.MessageDigest;
import p009b.C0413b;
import p162u.InterfaceC1964h;

/* compiled from: DataCacheKey.java */
/* renamed from: x.b */
/* loaded from: classes.dex */
public final class C2041b implements InterfaceC1964h {

    /* renamed from: b */
    public final InterfaceC1964h f5966b;

    /* renamed from: c */
    public final InterfaceC1964h f5967c;

    public C2041b(InterfaceC1964h interfaceC1964h, InterfaceC1964h interfaceC1964h2) {
        this.f5966b = interfaceC1964h;
        this.f5967c = interfaceC1964h2;
    }

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        this.f5966b.mo130b(messageDigest);
        this.f5967c.mo130b(messageDigest);
    }

    @Override // p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        if (!(obj instanceof C2041b)) {
            return false;
        }
        C2041b c2041b = (C2041b) obj;
        return this.f5966b.equals(c2041b.f5966b) && this.f5967c.equals(c2041b.f5967c);
    }

    @Override // p162u.InterfaceC1964h
    public int hashCode() {
        return this.f5967c.hashCode() + (this.f5966b.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("DataCacheKey{sourceKey=");
        sbM112a.append(this.f5966b);
        sbM112a.append(", signature=");
        sbM112a.append(this.f5967c);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
