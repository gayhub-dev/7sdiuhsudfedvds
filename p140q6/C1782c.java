package p140q6;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import p082j6.C1212a;

/* compiled from: ChunkedOutputStream.java */
/* renamed from: q6.c */
/* loaded from: classes.dex */
public class C1782c extends FilterOutputStream {

    /* renamed from: e */
    public boolean f5070e;

    /* renamed from: f */
    public int f5071f;

    /* renamed from: g */
    public int f5072g;

    /* renamed from: h */
    public byte[] f5073h;

    /* renamed from: i */
    public C1786g f5074i;

    public C1782c(C1786g c1786g, OutputStream outputStream) {
        super(outputStream);
        this.f5070e = false;
        this.f5071f = 6;
        this.f5072g = 0;
        this.f5073h = new byte[4104];
        this.f5074i = c1786g;
    }

    /* renamed from: a */
    public final void m1973a() throws IOException {
        char[] charArray = Integer.toHexString(this.f5072g).toCharArray();
        int length = charArray.length;
        int i7 = 4 - length;
        int i8 = 0;
        while (i8 < length) {
            this.f5073h[i7 + i8] = (byte) charArray[i8];
            i8++;
        }
        byte[] bArr = this.f5073h;
        int i9 = i8 + 1;
        bArr[i8 + i7] = C1212a.f2735CR;
        int i10 = i9 + 1;
        bArr[i9 + i7] = 10;
        int i11 = i10 + 1;
        int i12 = this.f5072g;
        bArr[i10 + i7 + i12] = C1212a.f2735CR;
        bArr[i11 + i7 + i12] = 10;
        ((FilterOutputStream) this).out.write(bArr, i7, i11 + 1 + i12);
        this.f5072g = 0;
        this.f5071f = 6;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f5070e) {
            return;
        }
        flush();
        try {
            m1973a();
            ((FilterOutputStream) this).out.flush();
            AbstractC1795p abstractC1795p = this.f5074i.f5093o;
            if (!abstractC1795p.f5121e) {
                abstractC1795p.close();
            }
        } catch (IOException unused) {
        } catch (Throwable th) {
            this.f5070e = true;
            throw th;
        }
        this.f5070e = true;
        C1786g c1786g = this.f5074i;
        c1786g.f5084f.f5102a.f5115d.m2007a(new C1805z(c1786g));
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.f5070e) {
            throw new C1802w();
        }
        if (this.f5072g > 0) {
            m1973a();
        }
        ((FilterOutputStream) this).out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i7) throws IOException {
        if (this.f5070e) {
            throw new C1802w();
        }
        byte[] bArr = this.f5073h;
        int i8 = this.f5071f;
        this.f5071f = i8 + 1;
        bArr[i8] = (byte) i7;
        int i9 = this.f5072g + 1;
        this.f5072g = i9;
        if (i9 == 4096) {
            m1973a();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i7, int i8) throws IOException {
        if (!this.f5070e) {
            int i9 = 4096 - this.f5072g;
            if (i8 > i9) {
                System.arraycopy(bArr, i7, this.f5073h, this.f5071f, i9);
                this.f5072g = 4096;
                m1973a();
                i8 -= i9;
                i7 += i9;
                while (i8 > 4096) {
                    System.arraycopy(bArr, i7, this.f5073h, 6, 4096);
                    i8 -= 4096;
                    i7 += 4096;
                    this.f5072g = 4096;
                    m1973a();
                }
                this.f5071f = 6;
            }
            if (i8 > 0) {
                System.arraycopy(bArr, i7, this.f5073h, this.f5071f, i8);
                this.f5072g += i8;
                this.f5071f += i8;
                return;
            }
            return;
        }
        throw new C1802w();
    }
}
