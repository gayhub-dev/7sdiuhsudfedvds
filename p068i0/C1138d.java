package p068i0;

import android.graphics.Bitmap;
import p052g0.AbstractC1042a;
import p068i0.C1140f;
import p142r0.C1823h;

/* JADX WARN: Unexpected interfaces in signature: [x.o] */
/* compiled from: GifDrawableResource.java */
/* renamed from: i0.d */
/* loaded from: classes.dex */
public class C1138d extends AbstractC1042a<C1137c> {
    public C1138d(C1137c c1137c) {
        super(c1137c);
    }

    @Override // p183x.InterfaceC2054o
    /* renamed from: a */
    public void mo823a() {
        ((C1137c) this.f1975e).f2478e.f2489b.f2502l.prepareToDraw();
    }

    @Override // p183x.InterfaceC2057r
    /* renamed from: b */
    public Class<C1137c> mo824b() {
        return C1137c.class;
    }

    @Override // p183x.InterfaceC2057r
    public int getSize() {
        C1140f c1140f = ((C1137c) this.f1975e).f2478e.f2489b;
        return C1823h.m2059c(c1140f.m1305a().getWidth(), c1140f.m1305a().getHeight(), c1140f.m1305a().getConfig()) + c1140f.f2491a.mo2185f();
    }

    @Override // p183x.InterfaceC2057r
    public void recycle() {
        ((C1137c) this.f1975e).stop();
        C1137c c1137c = (C1137c) this.f1975e;
        c1137c.f2481h = true;
        C1140f c1140f = c1137c.f2478e.f2489b;
        c1140f.f2493c.clear();
        Bitmap bitmap = c1140f.f2502l;
        if (bitmap != null) {
            c1140f.f2495e.mo2520e(bitmap);
            c1140f.f2502l = null;
        }
        c1140f.f2496f = false;
        C1140f.a aVar = c1140f.f2499i;
        if (aVar != null) {
            c1140f.f2494d.m2041k(aVar);
            c1140f.f2499i = null;
        }
        C1140f.a aVar2 = c1140f.f2501k;
        if (aVar2 != null) {
            c1140f.f2494d.m2041k(aVar2);
            c1140f.f2501k = null;
        }
        c1140f.f2491a.clear();
        c1140f.f2500j = true;
    }
}
