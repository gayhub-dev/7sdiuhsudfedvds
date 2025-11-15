package p036e0;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.Log;
import java.security.MessageDigest;
import p162u.InterfaceC1964h;
import p190y.InterfaceC2086d;

/* compiled from: CenterInside.java */
/* renamed from: e0.h */
/* loaded from: classes.dex */
public class C0909h extends AbstractC0906e {

    /* renamed from: b */
    public static final byte[] f1634b = "com.bumptech.glide.load.resource.bitmap.CenterInside".getBytes(InterfaceC1964h.f5736a);

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        messageDigest.update(f1634b);
    }

    @Override // p036e0.AbstractC0906e
    /* renamed from: c */
    public Bitmap mo825c(@NonNull InterfaceC2086d interfaceC2086d, @NonNull Bitmap bitmap, int i7, int i8) {
        Paint paint = C0918q.f1674a;
        if (bitmap.getWidth() > i7 || bitmap.getHeight() > i8) {
            Log.isLoggable("TransformationUtils", 2);
            return C0918q.m851b(interfaceC2086d, bitmap, i7, i8);
        }
        Log.isLoggable("TransformationUtils", 2);
        return bitmap;
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        return obj instanceof C0909h;
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public int hashCode() {
        return -670243078;
    }
}
