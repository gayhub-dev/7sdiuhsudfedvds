package p076j0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: TranscoderRegistry.java */
/* renamed from: j0.c */
/* loaded from: classes.dex */
public class C1183c {

    /* renamed from: a */
    public final List<a<?, ?>> f2597a = new ArrayList();

    /* compiled from: TranscoderRegistry.java */
    /* renamed from: j0.c$a */
    public static final class a<Z, R> {

        /* renamed from: a */
        public final Class<Z> f2598a;

        /* renamed from: b */
        public final Class<R> f2599b;

        /* renamed from: c */
        public final InterfaceC1182b<Z, R> f2600c;

        public a(Class<Z> cls, Class<R> cls2, InterfaceC1182b<Z, R> interfaceC1182b) {
            this.f2598a = cls;
            this.f2599b = cls2;
            this.f2600c = interfaceC1182b;
        }

        /* renamed from: a */
        public boolean m1380a(Class<?> cls, Class<?> cls2) {
            return this.f2598a.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f2599b);
        }
    }

    /* renamed from: a */
    public synchronized <Z, R> List<Class<R>> m1379a(Class<Z> cls, Class<R> cls2) {
        ArrayList arrayList = new ArrayList();
        if (cls2.isAssignableFrom(cls)) {
            arrayList.add(cls2);
            return arrayList;
        }
        Iterator<a<?, ?>> it = this.f2597a.iterator();
        while (it.hasNext()) {
            if (it.next().m1380a(cls, cls2)) {
                arrayList.add(cls2);
            }
        }
        return arrayList;
    }
}
