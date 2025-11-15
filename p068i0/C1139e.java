package p068i0;

import android.content.Context;
import android.graphics.Bitmap;
import java.security.MessageDigest;
import p036e0.C0905d;
import p141r.ComponentCallbacks2C1808c;
import p162u.InterfaceC1969m;
import p183x.InterfaceC2057r;

/* compiled from: GifDrawableTransformation.java */
/* renamed from: i0.e */
/* loaded from: classes.dex */
public class C1139e implements InterfaceC1969m<C1137c> {

    /* renamed from: b */
    public final InterfaceC1969m<Bitmap> f2490b;

    public C1139e(InterfaceC1969m<Bitmap> interfaceC1969m) {
        this.f2490b = interfaceC1969m;
    }

    @Override // p162u.InterfaceC1969m
    /* renamed from: a */
    public InterfaceC2057r<C1137c> mo672a(Context context, InterfaceC2057r<C1137c> interfaceC2057r, int i7, int i8) {
        C1137c c1137c = interfaceC2057r.get();
        InterfaceC2057r<Bitmap> c0905d = new C0905d(c1137c.f2478e.f2489b.f2502l, ComponentCallbacks2C1808c.m2022c(context).f5213e);
        InterfaceC2057r<Bitmap> interfaceC2057rMo672a = this.f2490b.mo672a(context, c0905d, i7, i8);
        if (!c0905d.equals(interfaceC2057rMo672a)) {
            c0905d.recycle();
        }
        Bitmap bitmap = interfaceC2057rMo672a.get();
        c1137c.f2478e.f2489b.m1307c(this.f2490b, bitmap);
        return interfaceC2057r;
    }

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        this.f2490b.mo130b(messageDigest);
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        if (obj instanceof C1139e) {
            return this.f2490b.equals(((C1139e) obj).f2490b);
        }
        return false;
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public int hashCode() {
        return this.f2490b.hashCode();
    }
}
