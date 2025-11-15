package p161t5;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ReadLineInputStream.java */
/* renamed from: t5.p */
/* loaded from: classes.dex */
public class C1924p extends BufferedInputStream {

    /* renamed from: e */
    public boolean f5682e;

    /* renamed from: f */
    public boolean f5683f;

    public C1924p(InputStream inputStream) {
        super(inputStream);
    }

    /* renamed from: a */
    public String m2244a() throws IOException {
        mark(((BufferedInputStream) this).buf.length);
        while (true) {
            int i7 = super.read();
            int i8 = ((BufferedInputStream) this).markpos;
            if (i8 < 0) {
                throw new IOException("Buffer size exceeded: no line terminator");
            }
            if (i7 == -1) {
                ((BufferedInputStream) this).markpos = -1;
                if (((BufferedInputStream) this).pos > i8) {
                    return new String(((BufferedInputStream) this).buf, i8, ((BufferedInputStream) this).pos - i8, C1926r.f5699b);
                }
                return null;
            }
            if (i7 == 13) {
                int i9 = ((BufferedInputStream) this).pos;
                if (!this.f5682e || i9 >= ((BufferedInputStream) this).count) {
                    this.f5683f = true;
                } else {
                    byte[] bArr = ((BufferedInputStream) this).buf;
                    int i10 = ((BufferedInputStream) this).pos;
                    if (bArr[i10] == 10) {
                        ((BufferedInputStream) this).pos = i10 + 1;
                    }
                }
                int i11 = ((BufferedInputStream) this).markpos;
                ((BufferedInputStream) this).markpos = -1;
                return new String(((BufferedInputStream) this).buf, i11, (i9 - i11) - 1, C1926r.f5699b);
            }
            if (i7 == 10) {
                if (!this.f5683f) {
                    ((BufferedInputStream) this).markpos = -1;
                    return new String(((BufferedInputStream) this).buf, i8, (((BufferedInputStream) this).pos - i8) - 1, C1926r.f5699b);
                }
                this.f5683f = false;
                this.f5682e = true;
                ((BufferedInputStream) this).markpos = i8 + 1;
            }
        }
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        int i7;
        i7 = super.read();
        if (this.f5683f) {
            this.f5683f = false;
            if (this.f5682e && i7 == 10) {
                i7 = super.read();
            }
        }
        return i7;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i7, int i8) {
        if (this.f5683f && i8 > 0) {
            this.f5683f = false;
            if (this.f5682e) {
                int i9 = super.read();
                if (i9 == -1) {
                    return -1;
                }
                if (i9 != 10) {
                    bArr[i7] = (byte) (i9 & 255);
                    return super.read(bArr, i7 + 1, i8 - 1) + 1;
                }
            }
        }
        return super.read(bArr, i7, i8);
    }
}
