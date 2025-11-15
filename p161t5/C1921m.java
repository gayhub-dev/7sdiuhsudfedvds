package p161t5;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: MultiPartInputStream.java */
/* renamed from: t5.m */
/* loaded from: classes.dex */
public class C1921m extends FilterInputStream {
    public C1921m(C1922n c1922n, InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i7 = ((FilterInputStream) this).in.read();
        if (i7 < 0 || i7 != 61) {
            return i7;
        }
        int i8 = ((FilterInputStream) this).in.read();
        int i9 = ((FilterInputStream) this).in.read();
        if (i8 < 0 || i9 < 0) {
            throw new IOException("Unexpected end to quoted-printable byte");
        }
        return Integer.parseInt(new String(new char[]{(char) i8, (char) i9}), 16);
    }
}
