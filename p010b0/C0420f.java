package p010b0;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Objects;
import p162u.InterfaceC1964h;

/* compiled from: GlideUrl.java */
/* renamed from: b0.f */
/* loaded from: classes.dex */
public class C0420f implements InterfaceC1964h {

    /* renamed from: b */
    public final InterfaceC0421g f208b;

    /* renamed from: c */
    @Nullable
    public final URL f209c;

    /* renamed from: d */
    @Nullable
    public final String f210d;

    /* renamed from: e */
    @Nullable
    public String f211e;

    /* renamed from: f */
    @Nullable
    public URL f212f;

    /* renamed from: g */
    @Nullable
    public volatile byte[] f213g;

    /* renamed from: h */
    public int f214h;

    public C0420f(URL url) {
        InterfaceC0421g interfaceC0421g = InterfaceC0421g.f215a;
        Objects.requireNonNull(url, "Argument must not be null");
        this.f209c = url;
        this.f210d = null;
        Objects.requireNonNull(interfaceC0421g, "Argument must not be null");
        this.f208b = interfaceC0421g;
    }

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        if (this.f213g == null) {
            this.f213g = m131c().getBytes(InterfaceC1964h.f5736a);
        }
        messageDigest.update(this.f213g);
    }

    /* renamed from: c */
    public String m131c() {
        String str = this.f210d;
        return str != null ? str : this.f209c.toString();
    }

    /* renamed from: d */
    public URL m132d() {
        if (this.f212f == null) {
            if (TextUtils.isEmpty(this.f211e)) {
                String string = this.f210d;
                if (TextUtils.isEmpty(string)) {
                    string = this.f209c.toString();
                }
                this.f211e = Uri.encode(string, "@#&=*+-_.,:!?()/~'%");
            }
            this.f212f = new URL(this.f211e);
        }
        return this.f212f;
    }

    @Override // p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        if (!(obj instanceof C0420f)) {
            return false;
        }
        C0420f c0420f = (C0420f) obj;
        return m131c().equals(c0420f.m131c()) && this.f208b.equals(c0420f.f208b);
    }

    @Override // p162u.InterfaceC1964h
    public int hashCode() {
        if (this.f214h == 0) {
            int iHashCode = m131c().hashCode();
            this.f214h = iHashCode;
            this.f214h = this.f208b.hashCode() + (iHashCode * 31);
        }
        return this.f214h;
    }

    public String toString() {
        return m131c();
    }

    public C0420f(String str) {
        InterfaceC0421g interfaceC0421g = InterfaceC0421g.f215a;
        this.f209c = null;
        if (!TextUtils.isEmpty(str)) {
            this.f210d = str;
            Objects.requireNonNull(interfaceC0421g, "Argument must not be null");
            this.f208b = interfaceC0421g;
            return;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }
}
