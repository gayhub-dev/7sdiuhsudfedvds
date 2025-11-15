package p190y;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/* compiled from: BitmapPoolAdapter.java */
/* renamed from: y.e */
/* loaded from: classes.dex */
public class C2087e implements InterfaceC2086d {
    @Override // p190y.InterfaceC2086d
    /* renamed from: a */
    public void mo2516a(int i7) {
    }

    @Override // p190y.InterfaceC2086d
    /* renamed from: b */
    public void mo2517b() {
    }

    @Override // p190y.InterfaceC2086d
    @NonNull
    /* renamed from: c */
    public Bitmap mo2518c(int i7, int i8, Bitmap.Config config) {
        return Bitmap.createBitmap(i7, i8, config);
    }

    @Override // p190y.InterfaceC2086d
    @NonNull
    /* renamed from: d */
    public Bitmap mo2519d(int i7, int i8, Bitmap.Config config) {
        return Bitmap.createBitmap(i7, i8, config);
    }

    @Override // p190y.InterfaceC2086d
    /* renamed from: e */
    public void mo2520e(Bitmap bitmap) {
        bitmap.recycle();
    }
}
