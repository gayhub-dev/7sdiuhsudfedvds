package p068i0;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import p162u.C1963g;
import p162u.C1965i;
import p162u.C1966j;
import p162u.InterfaceC1962f;
import p162u.InterfaceC1967k;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2084b;

/* compiled from: StreamGifDecoder.java */
/* renamed from: i0.g */
/* loaded from: classes.dex */
public class C1141g implements InterfaceC1967k<InputStream, C1137c> {

    /* renamed from: d */
    public static final C1965i<Boolean> f2510d = C1965i.m2295a("com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder.DisableAnimation", Boolean.FALSE);

    /* renamed from: a */
    public final List<InterfaceC1962f> f2511a;

    /* renamed from: b */
    public final InterfaceC1967k<ByteBuffer, C1137c> f2512b;

    /* renamed from: c */
    public final InterfaceC2084b f2513c;

    public C1141g(List<InterfaceC1962f> list, InterfaceC1967k<ByteBuffer, C1137c> interfaceC1967k, InterfaceC2084b interfaceC2084b) {
        this.f2511a = list;
        this.f2512b = interfaceC1967k;
        this.f2513c = interfaceC2084b;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: a */
    public boolean mo819a(InputStream inputStream, C1966j c1966j) {
        return !((Boolean) c1966j.m2296c(f2510d)).booleanValue() && C1963g.m2294b(this.f2511a, inputStream, this.f2513c) == InterfaceC1962f.a.GIF;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: b */
    public InterfaceC2057r<C1137c> mo820b(InputStream inputStream, int i7, int i8, C1966j c1966j) throws IOException {
        byte[] byteArray;
        InputStream inputStream2 = inputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int i9 = inputStream2.read(bArr);
                if (i9 == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i9);
            }
            byteArrayOutputStream.flush();
            byteArray = byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            Log.isLoggable("StreamGifDecoder", 5);
            byteArray = null;
        }
        if (byteArray == null) {
            return null;
        }
        return this.f2512b.mo820b(ByteBuffer.wrap(byteArray), i7, i8, c1966j);
    }
}
