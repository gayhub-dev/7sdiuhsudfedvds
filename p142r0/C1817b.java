package p142r0;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import p009b.C0413b;

/* compiled from: ContentLengthInputStream.java */
/* renamed from: r0.b */
/* loaded from: classes.dex */
public final class C1817b extends FilterInputStream {

    /* renamed from: e */
    public final long f5286e;

    /* renamed from: f */
    public int f5287f;

    public C1817b(InputStream inputStream, long j7) {
        super(inputStream);
        this.f5286e = j7;
    }

    /* renamed from: a */
    public final int m2048a(int i7) throws IOException {
        if (i7 >= 0) {
            this.f5287f += i7;
        } else if (this.f5286e - this.f5287f > 0) {
            StringBuilder sbM112a = C0413b.m112a("Failed to read all expected data, expected: ");
            sbM112a.append(this.f5286e);
            sbM112a.append(", but read: ");
            sbM112a.append(this.f5287f);
            throw new IOException(sbM112a.toString());
        }
        return i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        return (int) Math.max(this.f5286e - this.f5287f, ((FilterInputStream) this).in.available());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        int i7;
        i7 = super.read();
        m2048a(i7 >= 0 ? 1 : -1);
        return i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i7, int i8) {
        int i9;
        i9 = super.read(bArr, i7, i8);
        m2048a(i9);
        return i9;
    }
}
