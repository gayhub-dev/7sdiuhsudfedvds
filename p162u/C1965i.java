package p162u;

import android.text.TextUtils;
import java.security.MessageDigest;
import java.util.Objects;
import p009b.C0413b;

/* compiled from: Option.java */
/* renamed from: u.i */
/* loaded from: classes.dex */
public final class C1965i<T> {

    /* renamed from: e */
    public static final b<Object> f5737e = new a();

    /* renamed from: a */
    public final T f5738a;

    /* renamed from: b */
    public final b<T> f5739b;

    /* renamed from: c */
    public final String f5740c;

    /* renamed from: d */
    public volatile byte[] f5741d;

    /* compiled from: Option.java */
    /* renamed from: u.i$a */
    public static class a implements b<Object> {
        @Override // p162u.C1965i.b
        /* renamed from: a */
        public void mo853a(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    }

    /* compiled from: Option.java */
    /* renamed from: u.i$b */
    public interface b<T> {
        /* renamed from: a */
        void mo853a(byte[] bArr, T t6, MessageDigest messageDigest);
    }

    public C1965i(String str, T t6, b<T> bVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must not be null or empty");
        }
        this.f5740c = str;
        this.f5738a = t6;
        Objects.requireNonNull(bVar, "Argument must not be null");
        this.f5739b = bVar;
    }

    /* renamed from: a */
    public static <T> C1965i<T> m2295a(String str, T t6) {
        return new C1965i<>(str, t6, f5737e);
    }

    public boolean equals(Object obj) {
        if (obj instanceof C1965i) {
            return this.f5740c.equals(((C1965i) obj).f5740c);
        }
        return false;
    }

    public int hashCode() {
        return this.f5740c.hashCode();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Option{key='");
        sbM112a.append(this.f5740c);
        sbM112a.append('\'');
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
