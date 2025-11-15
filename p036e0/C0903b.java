package p036e0;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.security.MessageDigest;
import java.util.Objects;
import p141r.ComponentCallbacks2C1808c;
import p162u.InterfaceC1969m;
import p183x.InterfaceC2057r;

/* compiled from: BitmapDrawableTransformation.java */
/* renamed from: e0.b */
/* loaded from: classes.dex */
public class C0903b implements InterfaceC1969m<BitmapDrawable> {

    /* renamed from: b */
    public final InterfaceC1969m<Bitmap> f1626b;

    public C0903b(InterfaceC1969m<Bitmap> interfaceC1969m) {
        Objects.requireNonNull(interfaceC1969m, "Argument must not be null");
        this.f1626b = interfaceC1969m;
    }

    @Override // p162u.InterfaceC1969m
    /* renamed from: a */
    public InterfaceC2057r<BitmapDrawable> mo672a(Context context, InterfaceC2057r<BitmapDrawable> interfaceC2057r, int i7, int i8) {
        C0905d c0905dM822c = C0905d.m822c(interfaceC2057r.get().getBitmap(), ComponentCallbacks2C1808c.m2022c(context).f5213e);
        InterfaceC2057r<Bitmap> interfaceC2057rMo672a = this.f1626b.mo672a(context, c0905dM822c, i7, i8);
        if (interfaceC2057rMo672a.equals(c0905dM822c)) {
            return interfaceC2057r;
        }
        return new C0915n(context.getResources(), ComponentCallbacks2C1808c.m2022c(context).f5213e, interfaceC2057rMo672a.get());
    }

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        this.f1626b.mo130b(messageDigest);
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        if (obj instanceof C0903b) {
            return this.f1626b.equals(((C0903b) obj).f1626b);
        }
        return false;
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public int hashCode() {
        return this.f1626b.hashCode();
    }
}
