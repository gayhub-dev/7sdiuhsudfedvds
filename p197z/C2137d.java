package p197z;

import java.io.File;
import p197z.InterfaceC2134a;

/* compiled from: DiskLruCacheFactory.java */
/* renamed from: z.d */
/* loaded from: classes.dex */
public class C2137d implements InterfaceC2134a.a {

    /* renamed from: a */
    public final int f6285a;

    /* renamed from: b */
    public final a f6286b;

    /* compiled from: DiskLruCacheFactory.java */
    /* renamed from: z.d$a */
    public interface a {
    }

    public C2137d(a aVar, int i7) {
        this.f6285a = i7;
        this.f6286b = aVar;
    }

    /* renamed from: a */
    public InterfaceC2134a m2569a() {
        C2138e c2138e;
        C2139f c2139f = (C2139f) this.f6286b;
        File cacheDir = c2139f.f6293a.getCacheDir();
        if (cacheDir == null) {
            cacheDir = null;
        } else if (c2139f.f6294b != null) {
            cacheDir = new File(cacheDir, c2139f.f6294b);
        }
        if (cacheDir == null) {
            return null;
        }
        if (!cacheDir.mkdirs() && (!cacheDir.exists() || !cacheDir.isDirectory())) {
            return null;
        }
        int i7 = this.f6285a;
        synchronized (C2138e.class) {
            if (C2138e.f6287f == null) {
                C2138e.f6287f = new C2138e(cacheDir, i7);
            }
            c2138e = C2138e.f6287f;
        }
        return c2138e;
    }
}
