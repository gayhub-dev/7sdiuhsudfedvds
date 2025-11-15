package p140q6;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: LeftOverInputStream.java */
/* renamed from: q6.p */
/* loaded from: classes.dex */
public abstract class AbstractC1795p extends FilterInputStream {

    /* renamed from: e */
    public boolean f5121e;

    /* renamed from: f */
    public boolean f5122f;

    /* renamed from: g */
    public byte[] f5123g;

    public AbstractC1795p(C1786g c1786g, InputStream inputStream) {
        super(inputStream);
        this.f5121e = false;
        this.f5122f = false;
        this.f5123g = new byte[1];
        C1801v c1801v = c1786g.f5084f.f5102a.f5115d;
    }

    /* renamed from: a */
    public boolean mo1970a() {
        return super.available() > 0;
    }

    /* renamed from: b */
    public abstract int mo1971b(byte[] bArr, int i7, int i8);

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f5121e) {
            return;
        }
        boolean z6 = true;
        this.f5121e = true;
        if (this.f5122f) {
            return;
        }
        long j7 = C1800u.f5169f;
        byte[] bArr = new byte[2048];
        while (true) {
            if (j7 <= 0) {
                z6 = false;
                break;
            }
            long jMo1971b = mo1971b(bArr, 0, 2048);
            if (jMo1971b == -1) {
                this.f5122f = true;
                break;
            }
            j7 -= jMo1971b;
        }
        this.f5122f = z6;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        if (this.f5121e) {
            throw new IOException("Stream is closed");
        }
        int iMo1971b = mo1971b(this.f5123g, 0, 1);
        if (iMo1971b != -1 && iMo1971b != 0) {
            return this.f5123g[0] & 255;
        }
        return iMo1971b;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i7, int i8) {
        if (!this.f5121e) {
        } else {
            throw new IOException("Stream is closed");
        }
        return mo1971b(bArr, i7, i8);
    }
}
