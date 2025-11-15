package p162u;

import java.io.InputStream;
import java.nio.ByteBuffer;
import p190y.InterfaceC2084b;

/* compiled from: ImageHeaderParser.java */
/* renamed from: u.f */
/* loaded from: classes.dex */
public interface InterfaceC1962f {

    /* compiled from: ImageHeaderParser.java */
    /* renamed from: u.f$a */
    public enum a {
        GIF(true),
        JPEG(false),
        /* JADX INFO: Fake field, exist only in values array */
        RAW(false),
        PNG_A(true),
        PNG(false),
        WEBP_A(true),
        WEBP(false),
        UNKNOWN(false);


        /* renamed from: e */
        public final boolean f5735e;

        a(boolean z6) {
            this.f5735e = z6;
        }
    }

    /* renamed from: a */
    a mo826a(ByteBuffer byteBuffer);

    /* renamed from: b */
    int mo827b(InputStream inputStream, InterfaceC2084b interfaceC2084b);

    /* renamed from: c */
    a mo828c(InputStream inputStream);
}
