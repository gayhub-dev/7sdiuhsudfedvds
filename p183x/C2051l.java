package p183x;

import java.security.MessageDigest;
import java.util.Map;
import java.util.Objects;
import p009b.C0413b;
import p162u.C1966j;
import p162u.InterfaceC1964h;
import p162u.InterfaceC1969m;

/* compiled from: EngineKey.java */
/* renamed from: x.l */
/* loaded from: classes.dex */
public class C2051l implements InterfaceC1964h {

    /* renamed from: b */
    public final Object f6093b;

    /* renamed from: c */
    public final int f6094c;

    /* renamed from: d */
    public final int f6095d;

    /* renamed from: e */
    public final Class<?> f6096e;

    /* renamed from: f */
    public final Class<?> f6097f;

    /* renamed from: g */
    public final InterfaceC1964h f6098g;

    /* renamed from: h */
    public final Map<Class<?>, InterfaceC1969m<?>> f6099h;

    /* renamed from: i */
    public final C1966j f6100i;

    /* renamed from: j */
    public int f6101j;

    public C2051l(Object obj, InterfaceC1964h interfaceC1964h, int i7, int i8, Map<Class<?>, InterfaceC1969m<?>> map, Class<?> cls, Class<?> cls2, C1966j c1966j) {
        Objects.requireNonNull(obj, "Argument must not be null");
        this.f6093b = obj;
        Objects.requireNonNull(interfaceC1964h, "Signature must not be null");
        this.f6098g = interfaceC1964h;
        this.f6094c = i7;
        this.f6095d = i8;
        Objects.requireNonNull(map, "Argument must not be null");
        this.f6099h = map;
        Objects.requireNonNull(cls, "Resource class must not be null");
        this.f6096e = cls;
        Objects.requireNonNull(cls2, "Transcode class must not be null");
        this.f6097f = cls2;
        Objects.requireNonNull(c1966j, "Argument must not be null");
        this.f6100i = c1966j;
    }

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }

    @Override // p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        if (!(obj instanceof C2051l)) {
            return false;
        }
        C2051l c2051l = (C2051l) obj;
        return this.f6093b.equals(c2051l.f6093b) && this.f6098g.equals(c2051l.f6098g) && this.f6095d == c2051l.f6095d && this.f6094c == c2051l.f6094c && this.f6099h.equals(c2051l.f6099h) && this.f6096e.equals(c2051l.f6096e) && this.f6097f.equals(c2051l.f6097f) && this.f6100i.equals(c2051l.f6100i);
    }

    @Override // p162u.InterfaceC1964h
    public int hashCode() {
        if (this.f6101j == 0) {
            int iHashCode = this.f6093b.hashCode();
            this.f6101j = iHashCode;
            int iHashCode2 = this.f6098g.hashCode() + (iHashCode * 31);
            this.f6101j = iHashCode2;
            int i7 = (iHashCode2 * 31) + this.f6094c;
            this.f6101j = i7;
            int i8 = (i7 * 31) + this.f6095d;
            this.f6101j = i8;
            int iHashCode3 = this.f6099h.hashCode() + (i8 * 31);
            this.f6101j = iHashCode3;
            int iHashCode4 = this.f6096e.hashCode() + (iHashCode3 * 31);
            this.f6101j = iHashCode4;
            int iHashCode5 = this.f6097f.hashCode() + (iHashCode4 * 31);
            this.f6101j = iHashCode5;
            this.f6101j = this.f6100i.hashCode() + (iHashCode5 * 31);
        }
        return this.f6101j;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("EngineKey{model=");
        sbM112a.append(this.f6093b);
        sbM112a.append(", width=");
        sbM112a.append(this.f6094c);
        sbM112a.append(", height=");
        sbM112a.append(this.f6095d);
        sbM112a.append(", resourceClass=");
        sbM112a.append(this.f6096e);
        sbM112a.append(", transcodeClass=");
        sbM112a.append(this.f6097f);
        sbM112a.append(", signature=");
        sbM112a.append(this.f6098g);
        sbM112a.append(", hashCode=");
        sbM112a.append(this.f6101j);
        sbM112a.append(", transformations=");
        sbM112a.append(this.f6099h);
        sbM112a.append(", options=");
        sbM112a.append(this.f6100i);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
