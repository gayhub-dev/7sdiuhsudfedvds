package p142r0;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;

/* compiled from: ExceptionCatchingInputStream.java */
/* renamed from: r0.c */
/* loaded from: classes.dex */
public class C1818c extends InputStream {

    /* renamed from: g */
    public static final Queue<C1818c> f5288g;

    /* renamed from: e */
    public InputStream f5289e;

    /* renamed from: f */
    public IOException f5290f;

    static {
        char[] cArr = C1823h.f5300a;
        f5288g = new ArrayDeque(0);
    }

    /* renamed from: a */
    public void m2049a() {
        this.f5290f = null;
        this.f5289e = null;
        Queue<C1818c> queue = f5288g;
        synchronized (queue) {
            ((ArrayDeque) queue).offer(this);
        }
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f5289e.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5289e.close();
    }

    @Override // java.io.InputStream
    public void mark(int i7) {
        this.f5289e.mark(i7);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f5289e.markSupported();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        try {
            return this.f5289e.read(bArr);
        } catch (IOException e7) {
            this.f5290f = e7;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.f5289e.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j7) {
        try {
            return this.f5289e.skip(j7);
        } catch (IOException e7) {
            this.f5290f = e7;
            return 0L;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i7, int i8) {
        try {
            return this.f5289e.read(bArr, i7, i8);
        } catch (IOException e7) {
            this.f5290f = e7;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            return this.f5289e.read();
        } catch (IOException e7) {
            this.f5290f = e7;
            return -1;
        }
    }
}
