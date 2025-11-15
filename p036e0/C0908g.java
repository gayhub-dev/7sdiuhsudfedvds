package p036e0;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import java.security.MessageDigest;
import p162u.InterfaceC1964h;
import p190y.InterfaceC2086d;

/* compiled from: CenterCrop.java */
/* renamed from: e0.g */
/* loaded from: classes.dex */
public class C0908g extends AbstractC0906e {

    /* renamed from: b */
    public static final byte[] f1633b = "com.bumptech.glide.load.resource.bitmap.CenterCrop".getBytes(InterfaceC1964h.f5736a);

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        messageDigest.update(f1633b);
    }

    @Override // p036e0.AbstractC0906e
    /* renamed from: c */
    public Bitmap mo825c(@NonNull InterfaceC2086d interfaceC2086d, @NonNull Bitmap bitmap, int i7, int i8) {
        float width;
        float height;
        Paint paint = C0918q.f1674a;
        if (bitmap.getWidth() == i7 && bitmap.getHeight() == i8) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        float width2 = 0.0f;
        if (bitmap.getWidth() * i8 > bitmap.getHeight() * i7) {
            width = i8 / bitmap.getHeight();
            width2 = (i7 - (bitmap.getWidth() * width)) * 0.5f;
            height = 0.0f;
        } else {
            width = i7 / bitmap.getWidth();
            height = (i8 - (bitmap.getHeight() * width)) * 0.5f;
        }
        matrix.setScale(width, width);
        matrix.postTranslate((int) (width2 + 0.5f), (int) (height + 0.5f));
        Bitmap bitmapMo2519d = interfaceC2086d.mo2519d(i7, i8, C0918q.m852c(bitmap));
        bitmapMo2519d.setHasAlpha(bitmap.hasAlpha());
        C0918q.m850a(bitmap, bitmapMo2519d, matrix);
        return bitmapMo2519d;
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        return obj instanceof C0908g;
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public int hashCode() {
        return -599754482;
    }
}
