package p100m0;

import android.support.v4.util.ArrayMap;
import java.util.concurrent.atomic.AtomicReference;
import p142r0.C1822g;
import p183x.C2055p;

/* compiled from: LoadPathCache.java */
/* renamed from: m0.b */
/* loaded from: classes.dex */
public class C1454b {

    /* renamed from: a */
    public final ArrayMap<C1822g, C2055p<?, ?, ?>> f4215a;

    /* renamed from: b */
    public final AtomicReference<C1822g> f4216b;

    public C1454b(int i7) {
        if (i7 != 1) {
            this.f4215a = new ArrayMap<>();
            this.f4216b = new AtomicReference<>();
        } else {
            this.f4216b = new AtomicReference<>();
            this.f4215a = new ArrayMap<>();
        }
    }

    /* renamed from: a */
    public C1822g m1637a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        C1822g andSet = this.f4216b.getAndSet(null);
        if (andSet == null) {
            andSet = new C1822g();
        }
        andSet.f5297a = cls;
        andSet.f5298b = cls2;
        andSet.f5299c = cls3;
        return andSet;
    }

    /* renamed from: b */
    public void m1638b(Class<?> cls, Class<?> cls2, Class<?> cls3, C2055p<?, ?, ?> c2055p) {
        synchronized (this.f4215a) {
            this.f4215a.put(new C1822g(cls, cls2, cls3), c2055p);
        }
    }
}
