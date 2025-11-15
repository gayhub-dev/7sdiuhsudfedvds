package p068i0;

import android.support.annotation.Nullable;
import p155t.InterfaceC1891a;
import p190y.InterfaceC2084b;
import p190y.InterfaceC2086d;

/* compiled from: GifBitmapProvider.java */
/* renamed from: i0.b */
/* loaded from: classes.dex */
public final class C1136b implements InterfaceC1891a.a {

    /* renamed from: a */
    public final InterfaceC2086d f2476a;

    /* renamed from: b */
    @Nullable
    public final InterfaceC2084b f2477b;

    public C1136b(InterfaceC2086d interfaceC2086d, InterfaceC2084b interfaceC2084b) {
        this.f2476a = interfaceC2086d;
        this.f2477b = interfaceC2084b;
    }

    /* renamed from: a */
    public byte[] m1299a(int i7) {
        InterfaceC2084b interfaceC2084b = this.f2477b;
        return interfaceC2084b == null ? new byte[i7] : (byte[]) interfaceC2084b.mo2511d(i7, byte[].class);
    }

    /* renamed from: b */
    public void m1300b(byte[] bArr) {
        InterfaceC2084b interfaceC2084b = this.f2477b;
        if (interfaceC2084b == null) {
            return;
        }
        interfaceC2084b.mo2510c(bArr, byte[].class);
    }
}
