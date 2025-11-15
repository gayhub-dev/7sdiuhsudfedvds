package p036e0;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import p141r.ComponentCallbacks2C1808c;
import p142r0.C1823h;
import p162u.InterfaceC1969m;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2086d;

/* compiled from: BitmapTransformation.java */
/* renamed from: e0.e */
/* loaded from: classes.dex */
public abstract class AbstractC0906e implements InterfaceC1969m<Bitmap> {
    @Override // p162u.InterfaceC1969m
    /* renamed from: a */
    public final InterfaceC2057r<Bitmap> mo672a(Context context, InterfaceC2057r<Bitmap> interfaceC2057r, int i7, int i8) {
        if (!C1823h.m2065i(i7, i8)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + i7 + " or height: " + i8 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        InterfaceC2086d interfaceC2086d = ComponentCallbacks2C1808c.m2022c(context).f5213e;
        Bitmap bitmap = interfaceC2057r.get();
        if (i7 == Integer.MIN_VALUE) {
            i7 = bitmap.getWidth();
        }
        if (i8 == Integer.MIN_VALUE) {
            i8 = bitmap.getHeight();
        }
        Bitmap bitmapMo825c = mo825c(interfaceC2086d, bitmap, i7, i8);
        return bitmap.equals(bitmapMo825c) ? interfaceC2057r : C0905d.m822c(bitmapMo825c, interfaceC2086d);
    }

    /* renamed from: c */
    public abstract Bitmap mo825c(@NonNull InterfaceC2086d interfaceC2086d, @NonNull Bitmap bitmap, int i7, int i8);
}
