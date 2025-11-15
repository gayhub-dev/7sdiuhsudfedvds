package p076j0;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import p044f0.C0991b;
import p183x.InterfaceC2057r;

/* compiled from: BitmapBytesTranscoder.java */
/* renamed from: j0.a */
/* loaded from: classes.dex */
public class C1181a implements InterfaceC1182b<Bitmap, byte[]> {

    /* renamed from: a */
    public final Bitmap.CompressFormat f2595a = Bitmap.CompressFormat.JPEG;

    /* renamed from: b */
    public final int f2596b = 100;

    @Override // p076j0.InterfaceC1182b
    /* renamed from: b */
    public InterfaceC2057r<byte[]> mo942b(InterfaceC2057r<Bitmap> interfaceC2057r) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        interfaceC2057r.get().compress(this.f2595a, this.f2596b, byteArrayOutputStream);
        interfaceC2057r.recycle();
        return new C0991b(byteArrayOutputStream.toByteArray());
    }
}
