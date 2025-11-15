package p033d5;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ByteArrayOutputStream.java */
/* renamed from: d5.a */
/* loaded from: classes.dex */
public class C0872a extends OutputStream {

    /* renamed from: j */
    public static final byte[] f1339j = new byte[0];

    /* renamed from: e */
    public final List<byte[]> f1340e = new ArrayList();

    /* renamed from: f */
    public int f1341f;

    /* renamed from: g */
    public int f1342g;

    /* renamed from: h */
    public byte[] f1343h;

    /* renamed from: i */
    public int f1344i;

    public C0872a() {
        synchronized (this) {
            m679a(1024);
        }
    }

    /* renamed from: a */
    public final void m679a(int i7) {
        if (this.f1341f < this.f1340e.size() - 1) {
            this.f1342g += this.f1343h.length;
            int i8 = this.f1341f + 1;
            this.f1341f = i8;
            this.f1343h = this.f1340e.get(i8);
            return;
        }
        byte[] bArr = this.f1343h;
        if (bArr == null) {
            this.f1342g = 0;
        } else {
            i7 = Math.max(bArr.length << 1, i7 - this.f1342g);
            this.f1342g += this.f1343h.length;
        }
        this.f1341f++;
        byte[] bArr2 = new byte[i7];
        this.f1343h = bArr2;
        this.f1340e.add(bArr2);
    }

    /* renamed from: b */
    public synchronized byte[] m680b() {
        int i7 = this.f1344i;
        if (i7 == 0) {
            return f1339j;
        }
        byte[] bArr = new byte[i7];
        int i8 = 0;
        for (byte[] bArr2 : this.f1340e) {
            int iMin = Math.min(bArr2.length, i7);
            System.arraycopy(bArr2, 0, bArr, i8, iMin);
            i8 += iMin;
            i7 -= iMin;
            if (i7 == 0) {
                break;
            }
        }
        return bArr;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Deprecated
    public String toString() {
        return new String(m680b(), Charset.defaultCharset());
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i7, int i8) {
        int i9;
        if (i7 < 0 || i7 > bArr.length || i8 < 0 || (i9 = i7 + i8) > bArr.length || i9 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i8 == 0) {
            return;
        }
        synchronized (this) {
            int i10 = this.f1344i;
            int i11 = i10 + i8;
            int i12 = i10 - this.f1342g;
            while (i8 > 0) {
                int iMin = Math.min(i8, this.f1343h.length - i12);
                System.arraycopy(bArr, i9 - i8, this.f1343h, i12, iMin);
                i8 -= iMin;
                if (i8 > 0) {
                    m679a(i11);
                    i12 = 0;
                }
            }
            this.f1344i = i11;
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i7) {
        int i8 = this.f1344i;
        int i9 = i8 - this.f1342g;
        if (i9 == this.f1343h.length) {
            m679a(i8 + 1);
            i9 = 0;
        }
        this.f1343h[i9] = (byte) i7;
        this.f1344i++;
    }
}
