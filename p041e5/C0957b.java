package p041e5;

import java.net.InetSocketAddress;

/* compiled from: Address.java */
/* renamed from: e5.b */
/* loaded from: classes.dex */
public class C0957b {

    /* renamed from: a */
    public final String f1731a;

    /* renamed from: b */
    public final int f1732b;

    public C0957b(String str, int i7) {
        if (str == null) {
            throw new IllegalArgumentException("Host is null");
        }
        this.f1731a = str.trim();
        this.f1732b = i7;
    }

    /* renamed from: a */
    public InetSocketAddress m885a() {
        return new InetSocketAddress(this.f1731a, this.f1732b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0957b.class != obj.getClass()) {
            return false;
        }
        C0957b c0957b = (C0957b) obj;
        return this.f1731a.equals(c0957b.f1731a) && this.f1732b == c0957b.f1732b;
    }

    public int hashCode() {
        return (this.f1731a.hashCode() * 31) + this.f1732b;
    }

    public String toString() {
        return this.f1731a + ":" + this.f1732b;
    }
}
