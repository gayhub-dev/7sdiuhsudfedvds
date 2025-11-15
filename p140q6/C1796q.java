package p140q6;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: ExchangeImpl.java */
/* renamed from: q6.q */
/* loaded from: classes.dex */
public class C1796q extends OutputStream {

    /* renamed from: e */
    public OutputStream f5124e = null;

    public C1796q(OutputStream outputStream) {
    }

    /* renamed from: a */
    public final void m1998a() throws IOException {
        if (this.f5124e == null) {
            throw new IOException("response headers not sent yet");
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        m1998a();
        this.f5124e.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        m1998a();
        this.f5124e.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i7) throws IOException {
        m1998a();
        this.f5124e.write(i7);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        m1998a();
        this.f5124e.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i7, int i8) throws IOException {
        m1998a();
        this.f5124e.write(bArr, i7, i8);
    }
}
