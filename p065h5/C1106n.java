package p065h5;

import com.ctvit.network.model.HttpHeaders;
import p073i5.C1153f;
import p073i5.InterfaceC1152e;

/* compiled from: HttpHeaderValues.java */
/* renamed from: h5.n */
/* loaded from: classes.dex */
public class C1106n extends C1153f {

    /* renamed from: d */
    public static final C1106n f2323d;

    /* renamed from: e */
    public static final InterfaceC1152e f2324e;

    /* renamed from: f */
    public static final InterfaceC1152e f2325f;

    static {
        C1106n c1106n = new C1106n();
        f2323d = c1106n;
        f2324e = c1106n.m1353a(HttpHeaders.HEAD_VALUE_CONNECTION_CLOSE, 1);
        c1106n.m1353a("chunked", 2);
        c1106n.m1353a("gzip", 3);
        c1106n.m1353a("identity", 4);
        f2325f = c1106n.m1353a(HttpHeaders.HEAD_VALUE_CONNECTION_KEEP_ALIVE, 5);
        c1106n.m1353a("100-continue", 6);
        c1106n.m1353a("102-processing", 7);
        c1106n.m1353a("TE", 8);
        c1106n.m1353a("bytes", 9);
        c1106n.m1353a("no-cache", 10);
        c1106n.m1353a("Upgrade", 11);
    }
}
