package p134q0;

import java.security.MessageDigest;
import java.util.Objects;
import p009b.C0413b;
import p162u.InterfaceC1964h;

/* compiled from: ObjectKey.java */
/* renamed from: q0.b */
/* loaded from: classes.dex */
public final class C1757b implements InterfaceC1964h {

    /* renamed from: b */
    public final Object f4998b;

    public C1757b(Object obj) {
        Objects.requireNonNull(obj, "Argument must not be null");
        this.f4998b = obj;
    }

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        messageDigest.update(this.f4998b.toString().getBytes(InterfaceC1964h.f5736a));
    }

    @Override // p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        if (obj instanceof C1757b) {
            return this.f4998b.equals(((C1757b) obj).f4998b);
        }
        return false;
    }

    @Override // p162u.InterfaceC1964h
    public int hashCode() {
        return this.f4998b.hashCode();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("ObjectKey{object=");
        sbM112a.append(this.f4998b);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
