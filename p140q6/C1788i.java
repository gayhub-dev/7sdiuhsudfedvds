package p140q6;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: FixedLengthOutputStream.java */
/* renamed from: q6.i */
/* loaded from: classes.dex */
public class C1788i extends FilterOutputStream {

    /* renamed from: e */
    public long f5099e;

    /* renamed from: f */
    public boolean f5100f;

    /* renamed from: g */
    public C1786g f5101g;

    public C1788i(C1786g c1786g, OutputStream outputStream, long j7) {
        super(outputStream);
        this.f5100f = false;
        this.f5101g = c1786g;
        this.f5099e = j7;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f5100f) {
            return;
        }
        boolean z6 = true;
        this.f5100f = true;
        if (this.f5099e <= 0) {
            flush();
            AbstractC1795p abstractC1795p = this.f5101g.f5093o;
            if (!abstractC1795p.f5121e) {
                try {
                    abstractC1795p.close();
                } catch (IOException unused) {
                }
            }
            C1786g c1786g = this.f5101g;
            c1786g.f5084f.f5102a.f5115d.m2007a(new C1805z(c1786g));
            return;
        }
        C1786g c1786g2 = this.f5101g;
        if (!c1786g2.f5089k) {
            c1786g2.f5089k = true;
            try {
                AbstractC1795p abstractC1795p2 = c1786g2.f5093o;
                if (abstractC1795p2 == null || c1786g2.f5092n == null) {
                    c1786g2.f5084f.m1983a();
                } else {
                    if (c1786g2.f5094p.f5124e == null) {
                        z6 = false;
                    }
                    if (z6) {
                        if (!abstractC1795p2.f5121e) {
                            abstractC1795p2.close();
                        }
                        c1786g2.f5092n.close();
                    } else {
                        c1786g2.f5084f.m1983a();
                    }
                }
            } catch (IOException unused2) {
                c1786g2.f5084f.m1983a();
            }
        }
        throw new IOException("insufficient bytes written to stream");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i7) throws IOException {
        if (this.f5100f) {
            throw new IOException("stream closed");
        }
        if (this.f5099e == 0) {
            throw new C1802w();
        }
        ((FilterOutputStream) this).out.write(i7);
        this.f5099e--;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i7, int i8) throws IOException {
        if (!this.f5100f) {
            long j7 = this.f5099e;
            if (j7 == 0) {
                throw new C1802w();
            }
            long j8 = i8;
            if (j8 <= j7) {
                ((FilterOutputStream) this).out.write(bArr, i7, i8);
                this.f5099e -= j8;
                return;
            }
            throw new IOException("too many bytes to write to stream");
        }
        throw new IOException("stream closed");
    }
}
