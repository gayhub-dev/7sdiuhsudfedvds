package p161t5;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/* compiled from: ByteArrayISO8859Writer.java */
/* renamed from: t5.e */
/* loaded from: classes.dex */
public class C1913e extends Writer {

    /* renamed from: e */
    public byte[] f5625e;

    /* renamed from: f */
    public int f5626f;

    /* renamed from: g */
    public C1914f f5627g = null;

    /* renamed from: h */
    public OutputStreamWriter f5628h = null;

    public C1913e(int i7) {
        this.f5625e = new byte[i7];
    }

    /* renamed from: a */
    public void m2210a(int i7) {
        int i8 = this.f5626f;
        int i9 = i8 + i7;
        byte[] bArr = this.f5625e;
        if (i9 > bArr.length) {
            byte[] bArr2 = new byte[((bArr.length + i7) * 4) / 3];
            System.arraycopy(bArr, 0, bArr2, 0, i8);
            this.f5625e = bArr2;
        }
    }

    /* renamed from: b */
    public final void m2211b(char[] cArr, int i7, int i8) throws IOException {
        C1914f c1914f = this.f5627g;
        if (c1914f == null) {
            this.f5627g = new C1914f(i8 * 2);
            this.f5628h = new OutputStreamWriter(this.f5627g, "ISO-8859-1");
        } else {
            c1914f.reset();
        }
        this.f5628h.write(cArr, i7, i8);
        this.f5628h.flush();
        m2210a(this.f5627g.m2213b());
        System.arraycopy(this.f5627g.m2212a(), 0, this.f5625e, this.f5626f, this.f5627g.m2213b());
        this.f5626f = this.f5627g.m2213b() + this.f5626f;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        m2210a(cArr.length);
        for (int i7 = 0; i7 < cArr.length; i7++) {
            char c7 = cArr[i7];
            if (c7 < 0 || c7 > 127) {
                m2211b(cArr, i7, cArr.length - i7);
                return;
            }
            byte[] bArr = this.f5625e;
            int i8 = this.f5626f;
            this.f5626f = i8 + 1;
            bArr[i8] = (byte) c7;
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i7, int i8) throws IOException {
        m2210a(i8);
        for (int i9 = 0; i9 < i8; i9++) {
            int i10 = i7 + i9;
            char c7 = cArr[i10];
            if (c7 >= 0 && c7 <= 127) {
                byte[] bArr = this.f5625e;
                int i11 = this.f5626f;
                this.f5626f = i11 + 1;
                bArr[i11] = (byte) c7;
            } else {
                m2211b(cArr, i10, i8 - i9);
                return;
            }
        }
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        if (str == null) {
            write("null", 0, 4);
            return;
        }
        int length = str.length();
        m2210a(length);
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt >= 0 && cCharAt <= 127) {
                byte[] bArr = this.f5625e;
                int i8 = this.f5626f;
                this.f5626f = i8 + 1;
                bArr[i8] = (byte) cCharAt;
            } else {
                m2211b(str.toCharArray(), i7, length - i7);
                return;
            }
        }
    }

    @Override // java.io.Writer
    public void write(String str, int i7, int i8) throws IOException {
        m2210a(i8);
        for (int i9 = 0; i9 < i8; i9++) {
            int i10 = i7 + i9;
            char cCharAt = str.charAt(i10);
            if (cCharAt >= 0 && cCharAt <= 127) {
                byte[] bArr = this.f5625e;
                int i11 = this.f5626f;
                this.f5626f = i11 + 1;
                bArr[i11] = (byte) cCharAt;
            } else {
                m2211b(str.toCharArray(), i10, i8 - i9);
                return;
            }
        }
    }
}
