package p140q6;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: FixedLengthInputStream.java */
/* renamed from: q6.h */
/* loaded from: classes.dex */
public class C1787h extends AbstractC1795p {

    /* renamed from: h */
    public int f5098h;

    public C1787h(C1786g c1786g, InputStream inputStream, int i7) {
        super(c1786g, inputStream);
        this.f5098h = i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        if (this.f5122f) {
            return 0;
        }
        int iAvailable = ((FilterInputStream) this).in.available();
        int i7 = this.f5098h;
        return iAvailable < i7 ? iAvailable : i7;
    }

    @Override // p140q6.AbstractC1795p
    /* renamed from: b */
    public int mo1971b(byte[] bArr, int i7, int i8) throws IOException {
        int i9 = this.f5098h;
        boolean z6 = i9 == 0;
        this.f5122f = z6;
        if (z6) {
            return -1;
        }
        if (i8 > i9) {
            i8 = i9;
        }
        int i10 = ((FilterInputStream) this).in.read(bArr, i7, i8);
        if (i10 > -1) {
            this.f5098h -= i10;
        }
        return i10;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i7) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }
}
