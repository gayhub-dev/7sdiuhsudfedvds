package p036e0;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import p190y.InterfaceC2084b;

/* compiled from: RecyclableBufferedInputStream.java */
/* renamed from: e0.o */
/* loaded from: classes.dex */
public class C0916o extends FilterInputStream {

    /* renamed from: e */
    public volatile byte[] f1664e;

    /* renamed from: f */
    public int f1665f;

    /* renamed from: g */
    public int f1666g;

    /* renamed from: h */
    public int f1667h;

    /* renamed from: i */
    public int f1668i;

    /* renamed from: j */
    public final InterfaceC2084b f1669j;

    /* compiled from: RecyclableBufferedInputStream.java */
    /* renamed from: e0.o$a */
    public static class a extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        public a(String str) {
            super(str);
        }
    }

    public C0916o(InputStream inputStream, InterfaceC2084b interfaceC2084b) {
        super(inputStream);
        this.f1667h = -1;
        this.f1669j = interfaceC2084b;
        this.f1664e = (byte[]) interfaceC2084b.mo2511d(65536, byte[].class);
    }

    /* renamed from: c */
    public static IOException m847c() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    /* renamed from: a */
    public final int m848a(InputStream inputStream, byte[] bArr) throws IOException {
        int i7 = this.f1667h;
        if (i7 != -1) {
            int i8 = this.f1668i - i7;
            int i9 = this.f1666g;
            if (i8 < i9) {
                if (i7 == 0 && i9 > bArr.length && this.f1665f == bArr.length) {
                    int length = bArr.length * 2;
                    if (length <= i9) {
                        i9 = length;
                    }
                    byte[] bArr2 = (byte[]) this.f1669j.mo2511d(i9, byte[].class);
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    this.f1664e = bArr2;
                    this.f1669j.mo2510c(bArr, byte[].class);
                    bArr = bArr2;
                } else if (i7 > 0) {
                    System.arraycopy(bArr, i7, bArr, 0, bArr.length - i7);
                }
                int i10 = this.f1668i - this.f1667h;
                this.f1668i = i10;
                this.f1667h = 0;
                this.f1665f = 0;
                int i11 = inputStream.read(bArr, i10, bArr.length - i10);
                int i12 = this.f1668i;
                if (i11 > 0) {
                    i12 += i11;
                }
                this.f1665f = i12;
                return i11;
            }
        }
        int i13 = inputStream.read(bArr);
        if (i13 > 0) {
            this.f1667h = -1;
            this.f1668i = 0;
            this.f1665f = i13;
        }
        return i13;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        InputStream inputStream;
        inputStream = ((FilterInputStream) this).in;
        if (this.f1664e == null || inputStream == null) {
            m847c();
            throw null;
        }
        return (this.f1665f - this.f1668i) + inputStream.available();
    }

    /* renamed from: b */
    public synchronized void m849b() {
        if (this.f1664e != null) {
            this.f1669j.mo2510c(this.f1664e, byte[].class);
            this.f1664e = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f1664e != null) {
            this.f1669j.mo2510c(this.f1664e, byte[].class);
            this.f1664e = null;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        ((FilterInputStream) this).in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i7) {
        this.f1666g = Math.max(this.f1666g, i7);
        this.f1667h = this.f1668i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        byte[] bArr = this.f1664e;
        InputStream inputStream = ((FilterInputStream) this).in;
        if (bArr == null || inputStream == null) {
            m847c();
            throw null;
        }
        if (this.f1668i >= this.f1665f && m848a(inputStream, bArr) == -1) {
            return -1;
        }
        if (bArr != this.f1664e && (bArr = this.f1664e) == null) {
            m847c();
            throw null;
        }
        int i7 = this.f1665f;
        int i8 = this.f1668i;
        if (i7 - i8 <= 0) {
            return -1;
        }
        this.f1668i = i8 + 1;
        return bArr[i8] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        if (this.f1664e == null) {
            throw new IOException("Stream is closed");
        }
        int i7 = this.f1667h;
        if (-1 == i7) {
            throw new a("Mark has been invalidated, pos: " + this.f1668i + " markLimit: " + this.f1666g);
        }
        this.f1668i = i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j7) {
        byte[] bArr = this.f1664e;
        InputStream inputStream = ((FilterInputStream) this).in;
        if (bArr == null) {
            m847c();
            throw null;
        }
        if (j7 < 1) {
            return 0L;
        }
        if (inputStream == null) {
            m847c();
            throw null;
        }
        int i7 = this.f1665f;
        int i8 = this.f1668i;
        if (i7 - i8 >= j7) {
            this.f1668i = (int) (i8 + j7);
            return j7;
        }
        long j8 = i7 - i8;
        this.f1668i = i7;
        if (this.f1667h == -1 || j7 > this.f1666g) {
            return j8 + inputStream.skip(j7 - j8);
        }
        if (m848a(inputStream, bArr) == -1) {
            return j8;
        }
        int i9 = this.f1665f;
        int i10 = this.f1668i;
        long j9 = j7 - j8;
        if (i9 - i10 >= j9) {
            this.f1668i = (int) (i10 + j9);
            return j7;
        }
        long j10 = (j8 + i9) - i10;
        this.f1668i = i9;
        return j10;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i7, int i8) {
        int i9;
        int i10;
        byte[] bArr2 = this.f1664e;
        if (bArr2 == null) {
            m847c();
            throw null;
        }
        if (i8 == 0) {
            return 0;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream != null) {
            int i11 = this.f1668i;
            int i12 = this.f1665f;
            if (i11 < i12) {
                int i13 = i12 - i11 >= i8 ? i8 : i12 - i11;
                System.arraycopy(bArr2, i11, bArr, i7, i13);
                this.f1668i += i13;
                if (i13 == i8 || inputStream.available() == 0) {
                    return i13;
                }
                i7 += i13;
                i9 = i8 - i13;
            } else {
                i9 = i8;
            }
            while (true) {
                if (this.f1667h == -1 && i9 >= bArr2.length) {
                    i10 = inputStream.read(bArr, i7, i9);
                    if (i10 == -1) {
                        return i9 != i8 ? i8 - i9 : -1;
                    }
                } else {
                    if (m848a(inputStream, bArr2) == -1) {
                        return i9 != i8 ? i8 - i9 : -1;
                    }
                    if (bArr2 != this.f1664e && (bArr2 = this.f1664e) == null) {
                        m847c();
                        throw null;
                    }
                    int i14 = this.f1665f;
                    int i15 = this.f1668i;
                    i10 = i14 - i15 >= i9 ? i9 : i14 - i15;
                    System.arraycopy(bArr2, i15, bArr, i7, i10);
                    this.f1668i += i10;
                }
                i9 -= i10;
                if (i9 == 0) {
                    return i8;
                }
                if (inputStream.available() == 0) {
                    return i8 - i9;
                }
                i7 += i10;
            }
        } else {
            m847c();
            throw null;
        }
    }
}
