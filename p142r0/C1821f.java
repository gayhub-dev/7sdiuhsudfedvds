package p142r0;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: MarkEnforcingInputStream.java */
/* renamed from: r0.f */
/* loaded from: classes.dex */
public class C1821f extends FilterInputStream {

    /* renamed from: e */
    public int f5296e;

    public C1821f(InputStream inputStream) {
        super(inputStream);
        this.f5296e = Integer.MIN_VALUE;
    }

    /* renamed from: a */
    public final long m2055a(long j7) {
        int i7 = this.f5296e;
        if (i7 == 0) {
            return -1L;
        }
        return (i7 == Integer.MIN_VALUE || j7 <= ((long) i7)) ? j7 : i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        int i7 = this.f5296e;
        return i7 == Integer.MIN_VALUE ? super.available() : Math.min(i7, super.available());
    }

    /* renamed from: b */
    public final void m2056b(long j7) {
        int i7 = this.f5296e;
        if (i7 == Integer.MIN_VALUE || j7 == -1) {
            return;
        }
        this.f5296e = (int) (i7 - j7);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i7) {
        super.mark(i7);
        this.f5296e = i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (m2055a(1L) == -1) {
            return -1;
        }
        int i7 = super.read();
        m2056b(1L);
        return i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        super.reset();
        this.f5296e = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j7) throws IOException {
        long jM2055a = m2055a(j7);
        if (jM2055a == -1) {
            return -1L;
        }
        long jSkip = super.skip(jM2055a);
        m2056b(jSkip);
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i7, int i8) throws IOException {
        int iM2055a = (int) m2055a(i8);
        if (iM2055a == -1) {
            return -1;
        }
        int i9 = super.read(bArr, i7, iM2055a);
        m2056b(i9);
        return i9;
    }
}
