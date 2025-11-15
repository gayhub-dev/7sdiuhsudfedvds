package p036e0;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.util.Objects;
import p142r0.C1823h;
import p183x.InterfaceC2054o;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2086d;

/* compiled from: LazyBitmapDrawableResource.java */
/* renamed from: e0.n */
/* loaded from: classes.dex */
public class C0915n implements InterfaceC2057r<BitmapDrawable>, InterfaceC2054o {

    /* renamed from: e */
    public final Bitmap f1661e;

    /* renamed from: f */
    public final Resources f1662f;

    /* renamed from: g */
    public final InterfaceC2086d f1663g;

    public C0915n(Resources resources, InterfaceC2086d interfaceC2086d, Bitmap bitmap) {
        Objects.requireNonNull(resources, "Argument must not be null");
        this.f1662f = resources;
        Objects.requireNonNull(interfaceC2086d, "Argument must not be null");
        this.f1663g = interfaceC2086d;
        Objects.requireNonNull(bitmap, "Argument must not be null");
        this.f1661e = bitmap;
    }

    @Override // p183x.InterfaceC2054o
    /* renamed from: a */
    public void mo823a() {
        this.f1661e.prepareToDraw();
    }

    @Override // p183x.InterfaceC2057r
    /* renamed from: b */
    public Class<BitmapDrawable> mo824b() {
        return BitmapDrawable.class;
    }

    @Override // p183x.InterfaceC2057r
    public BitmapDrawable get() {
        return new BitmapDrawable(this.f1662f, this.f1661e);
    }

    @Override // p183x.InterfaceC2057r
    public int getSize() {
        return C1823h.m2060d(this.f1661e);
    }

    @Override // p183x.InterfaceC2057r
    public void recycle() {
        this.f1663g.mo2520e(this.f1661e);
    }
}
