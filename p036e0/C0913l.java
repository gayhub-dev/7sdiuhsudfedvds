package p036e0;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import java.security.MessageDigest;
import p162u.InterfaceC1964h;
import p190y.InterfaceC2086d;

/* compiled from: FitCenter.java */
/* renamed from: e0.l */
/* loaded from: classes.dex */
public class C0913l extends AbstractC0906e {

    /* renamed from: b */
    public static final byte[] f1656b = "com.bumptech.glide.load.resource.bitmap.FitCenter".getBytes(InterfaceC1964h.f5736a);

    @Override // p162u.InterfaceC1964h
    /* renamed from: b */
    public void mo130b(MessageDigest messageDigest) {
        messageDigest.update(f1656b);
    }

    @Override // p036e0.AbstractC0906e
    /* renamed from: c */
    public Bitmap mo825c(@NonNull InterfaceC2086d interfaceC2086d, @NonNull Bitmap bitmap, int i7, int i8) {
        return C0918q.m851b(interfaceC2086d, bitmap, i7, i8);
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public boolean equals(Object obj) {
        return obj instanceof C0913l;
    }

    @Override // p162u.InterfaceC1969m, p162u.InterfaceC1964h
    public int hashCode() {
        return 1572326941;
    }
}
