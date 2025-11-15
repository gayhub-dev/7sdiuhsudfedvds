package p100m0;

import java.util.ArrayList;
import java.util.List;
import p162u.InterfaceC1967k;

/* compiled from: ResourceDecoderRegistry.java */
/* renamed from: m0.c */
/* loaded from: classes.dex */
public class C1455c {

    /* renamed from: a */
    public final List<a<?, ?>> f4217a = new ArrayList();

    /* compiled from: ResourceDecoderRegistry.java */
    /* renamed from: m0.c$a */
    public static class a<T, R> {

        /* renamed from: a */
        public final Class<T> f4218a;

        /* renamed from: b */
        public final Class<R> f4219b;

        /* renamed from: c */
        public final InterfaceC1967k<T, R> f4220c;

        public a(Class<T> cls, Class<R> cls2, InterfaceC1967k<T, R> interfaceC1967k) {
            this.f4218a = cls;
            this.f4219b = cls2;
            this.f4220c = interfaceC1967k;
        }

        /* renamed from: a */
        public boolean m1640a(Class<?> cls, Class<?> cls2) {
            return this.f4218a.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f4219b);
        }
    }

    /* renamed from: a */
    public synchronized <T, R> List<Class<R>> m1639a(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (a<?, ?> aVar : this.f4217a) {
            if (aVar.m1640a(cls, cls2)) {
                arrayList.add(aVar.f4219b);
            }
        }
        return arrayList;
    }
}
