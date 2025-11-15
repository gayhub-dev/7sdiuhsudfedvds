package p162u;

import android.support.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import p036e0.C0916o;
import p162u.InterfaceC1962f;
import p190y.InterfaceC2084b;

/* compiled from: ImageHeaderParserUtils.java */
/* renamed from: u.g */
/* loaded from: classes.dex */
public final class C1963g {
    /* renamed from: a */
    public static int m2293a(List<InterfaceC1962f> list, @Nullable InputStream inputStream, InterfaceC2084b interfaceC2084b) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new C0916o(inputStream, interfaceC2084b);
        }
        inputStream.mark(5242880);
        Iterator<InterfaceC1962f> it = list.iterator();
        while (it.hasNext()) {
            try {
                int iMo827b = it.next().mo827b(inputStream, interfaceC2084b);
                if (iMo827b != -1) {
                    return iMo827b;
                }
            } finally {
                inputStream.reset();
            }
        }
        return -1;
    }

    /* renamed from: b */
    public static InterfaceC1962f.a m2294b(List<InterfaceC1962f> list, @Nullable InputStream inputStream, InterfaceC2084b interfaceC2084b) throws IOException {
        InterfaceC1962f.a aVar = InterfaceC1962f.a.UNKNOWN;
        if (inputStream == null) {
            return aVar;
        }
        if (!inputStream.markSupported()) {
            inputStream = new C0916o(inputStream, interfaceC2084b);
        }
        inputStream.mark(5242880);
        Iterator<InterfaceC1962f> it = list.iterator();
        while (it.hasNext()) {
            try {
                InterfaceC1962f.a aVarMo828c = it.next().mo828c(inputStream);
                if (aVarMo828c != aVar) {
                    return aVarMo828c;
                }
            } finally {
                inputStream.reset();
            }
        }
        return aVar;
    }
}
