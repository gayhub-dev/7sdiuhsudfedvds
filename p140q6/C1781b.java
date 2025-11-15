package p140q6;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ChunkedInputStream.java */
/* renamed from: q6.b */
/* loaded from: classes.dex */
public class C1781b extends AbstractC1795p {

    /* renamed from: h */
    public int f5068h;

    /* renamed from: i */
    public boolean f5069i;

    public C1781b(C1786g c1786g, InputStream inputStream) {
        super(c1786g, inputStream);
        this.f5069i = true;
    }

    @Override // p140q6.AbstractC1795p
    /* renamed from: a */
    public boolean mo1970a() {
        return ((FilterInputStream) this).in.available() > 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        if (this.f5122f || this.f5121e) {
            return 0;
        }
        int iAvailable = ((FilterInputStream) this).in.available();
        int i7 = this.f5068h;
        return iAvailable > i7 ? i7 : iAvailable;
    }

    @Override // p140q6.AbstractC1795p
    /* renamed from: b */
    public int mo1971b(byte[] bArr, int i7, int i8) throws IOException {
        int i9;
        int i10;
        if (this.f5122f) {
            return -1;
        }
        if (this.f5069i) {
            char[] cArr = new char[16];
            int i11 = 0;
            boolean z6 = false;
            boolean z7 = false;
            while (true) {
                char c7 = (char) ((FilterInputStream) this).in.read();
                if (c7 == 65535) {
                    throw new IOException("end of stream reading chunk header");
                }
                if (i11 == 15) {
                    throw new IOException("invalid chunk header");
                }
                if (z6) {
                    if (c7 == '\n') {
                        int i12 = 0;
                        for (int i13 = 0; i13 < i11; i13++) {
                            char c8 = cArr[i13];
                            if (c8 < '0' || c8 > '9') {
                                if (c8 >= 'a' && c8 <= 'f') {
                                    i9 = c8 - 'a';
                                } else {
                                    if (c8 < 'A' || c8 > 'F') {
                                        throw new IOException("invalid chunk length");
                                    }
                                    i9 = c8 - 'A';
                                }
                                i10 = i9 + 10;
                            } else {
                                i10 = c8 - '0';
                            }
                            i12 = (i12 * 16) + i10;
                        }
                        this.f5068h = i12;
                        if (i12 == 0) {
                            this.f5122f = true;
                            m1972c();
                            return -1;
                        }
                        this.f5069i = false;
                    } else {
                        if (!z7) {
                            cArr[i11] = c7;
                            i11++;
                        }
                        z6 = false;
                    }
                } else if (c7 == '\r') {
                    z6 = true;
                } else if (c7 == ';') {
                    z7 = true;
                } else if (!z7) {
                    cArr[i11] = c7;
                    i11++;
                }
            }
        }
        int i14 = this.f5068h;
        if (i8 > i14) {
            i8 = i14;
        }
        int i15 = ((FilterInputStream) this).in.read(bArr, i7, i8);
        if (i15 > -1) {
            this.f5068h -= i15;
        }
        if (this.f5068h == 0) {
            this.f5069i = true;
            m1972c();
        }
        return i15;
    }

    /* renamed from: c */
    public final void m1972c() throws IOException {
        if (((char) ((FilterInputStream) this).in.read()) != '\r') {
            throw new IOException("invalid chunk end");
        }
        if (((char) ((FilterInputStream) this).in.read()) != '\n') {
            throw new IOException("invalid chunk end");
        }
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
