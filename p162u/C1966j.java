package p162u;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import java.security.MessageDigest;
import java.util.Map;
import p009b.C0413b;
import p162u.C1965i;

/* compiled from: Options.java */
/* renamed from: u.j */
/* loaded from: classes.dex */
public final class C1966j implements InterfaceC1964h {

    /* renamed from: b */
    public final ArrayMap<C1965i<?>, Object> f5742b = new ArrayMap<>();

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        for (Map.Entry<C1965i<?>, Object> entry : this.f5742b.entrySet()) {
            C1965i<?> key = entry.getKey();
            Object value = entry.getValue();
            C1965i.b<?> bVar = key.f5739b;
            if (key.f5741d == null) {
                key.f5741d = key.f5740c.getBytes(InterfaceC1964h.f5736a);
            }
            bVar.mo853a(key.f5741d, value, messageDigest);
        }
    }

    /* renamed from: c */
    public <T> T m2296c(C1965i<T> c1965i) {
        return this.f5742b.containsKey(c1965i) ? (T) this.f5742b.get(c1965i) : c1965i.f5738a;
    }

    /* renamed from: d */
    public void m2297d(C1966j c1966j) {
        this.f5742b.putAll((SimpleArrayMap<? extends C1965i<?>, ? extends Object>) c1966j.f5742b);
    }

    @Override // p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        if (obj instanceof C1966j) {
            return this.f5742b.equals(((C1966j) obj).f5742b);
        }
        return false;
    }

    @Override // p162u.InterfaceC1964h
    public int hashCode() {
        return this.f5742b.hashCode();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Options{values=");
        sbM112a.append(this.f5742b);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
