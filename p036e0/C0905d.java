package p036e0;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import java.util.Objects;
import p142r0.C1823h;
import p183x.InterfaceC2054o;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2086d;

/* compiled from: BitmapResource.java */
/* renamed from: e0.d */
/* loaded from: classes.dex */
public class C0905d implements InterfaceC2057r<Bitmap>, InterfaceC2054o {

    /* renamed from: e */
    public final Bitmap f1629e;

    /* renamed from: f */
    public final InterfaceC2086d f1630f;

    public C0905d(Bitmap bitmap, InterfaceC2086d interfaceC2086d) {
        Objects.requireNonNull(bitmap, "Bitmap must not be null");
        this.f1629e = bitmap;
        Objects.requireNonNull(interfaceC2086d, "BitmapPool must not be null");
        this.f1630f = interfaceC2086d;
    }

    @Nullable
    /* renamed from: c */
    public static C0905d m822c(@Nullable Bitmap bitmap, InterfaceC2086d interfaceC2086d) {
        if (bitmap == null) {
            return null;
        }
        return new C0905d(bitmap, interfaceC2086d);
    }

    @Override // p183x.InterfaceC2054o
    /* renamed from: a */
    public void mo823a() {
        this.f1629e.prepareToDraw();
    }

    @Override // p183x.InterfaceC2057r
    /* renamed from: b */
    public Class<Bitmap> mo824b() {
        return Bitmap.class;
    }

    @Override // p183x.InterfaceC2057r
    public Bitmap get() {
        return this.f1629e;
    }

    @Override // p183x.InterfaceC2057r
    public int getSize() {
        return C1823h.m2060d(this.f1629e);
    }

    @Override // p183x.InterfaceC2057r
    public void recycle() {
        this.f1630f.mo2520e(this.f1629e);
    }
}
