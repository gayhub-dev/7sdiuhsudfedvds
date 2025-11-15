package p140q6;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: UndefLengthOutputStream.java */
/* renamed from: q6.x */
/* loaded from: classes.dex */
public class C1803x extends FilterOutputStream {

    /* renamed from: e */
    public boolean f5208e;

    /* renamed from: f */
    public C1786g f5209f;

    public C1803x(C1786g c1786g, OutputStream outputStream) {
        super(outputStream);
        this.f5208e = false;
        this.f5209f = c1786g;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f5208e) {
            return;
        }
        this.f5208e = true;
        flush();
        AbstractC1795p abstractC1795p = this.f5209f.f5093o;
        if (!abstractC1795p.f5121e) {
            try {
                abstractC1795p.close();
            } catch (IOException unused) {
            }
        }
        C1786g c1786g = this.f5209f;
        c1786g.f5084f.f5102a.f5115d.m2007a(new C1805z(c1786g));
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i7) throws IOException {
        if (this.f5208e) {
            throw new IOException("stream closed");
        }
        ((FilterOutputStream) this).out.write(i7);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i7, int i8) throws IOException {
        if (!this.f5208e) {
            ((FilterOutputStream) this).out.write(bArr, i7, i8);
            return;
        }
        throw new IOException("stream closed");
    }
}
