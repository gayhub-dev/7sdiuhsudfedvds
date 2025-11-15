package p044f0;

import java.util.Objects;
import p183x.InterfaceC2057r;

/* compiled from: BytesResource.java */
/* renamed from: f0.b */
/* loaded from: classes.dex */
public class C0991b implements InterfaceC2057r<byte[]> {

    /* renamed from: e */
    public final byte[] f1836e;

    public C0991b(byte[] bArr) {
        Objects.requireNonNull(bArr, "Argument must not be null");
        this.f1836e = bArr;
    }

    @Override // p183x.InterfaceC2057r
    /* renamed from: b */
    public Class<byte[]> mo824b() {
        return byte[].class;
    }

    @Override // p183x.InterfaceC2057r
    public byte[] get() {
        return this.f1836e;
    }

    @Override // p183x.InterfaceC2057r
    public int getSize() {
        return this.f1836e.length;
    }

    @Override // p183x.InterfaceC2057r
    public void recycle() {
    }
}
