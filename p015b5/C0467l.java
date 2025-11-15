package p015b5;

import java.io.IOException;
import java.util.ResourceBundle;
import p006a5.AbstractC0030p;

/* compiled from: HttpServlet.java */
/* renamed from: b5.l */
/* loaded from: classes.dex */
public class C0467l extends AbstractC0030p {

    /* renamed from: g */
    public static ResourceBundle f279g = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");

    /* renamed from: f */
    public int f280f = 0;

    @Override // java.io.OutputStream
    public void write(int i7) {
        this.f280f++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i7, int i8) throws IOException {
        if (i8 < 0) {
            throw new IOException(f279g.getString("err.io.negativelength"));
        }
        this.f280f += i8;
    }
}
