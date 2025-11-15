package p010b0;

import android.support.v4.util.Pools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import p010b0.C0430p;

/* compiled from: ModelLoaderRegistry.java */
/* renamed from: b0.n */
/* loaded from: classes.dex */
public class C0428n {

    /* renamed from: a */
    public final C0430p f234a;

    /* renamed from: b */
    public final a f235b;

    /* compiled from: ModelLoaderRegistry.java */
    /* renamed from: b0.n$a */
    public static class a {

        /* renamed from: a */
        public final Map<Class<?>, C2162a<?>> f236a = new HashMap();

        /* compiled from: ModelLoaderRegistry.java */
        /* renamed from: b0.n$a$a, reason: collision with other inner class name */
        public static class C2162a<Model> {

            /* renamed from: a */
            public final List<InterfaceC0426l<Model, ?>> f237a;

            public C2162a(List<InterfaceC0426l<Model, ?>> list) {
                this.f237a = list;
            }
        }
    }

    public C0428n(Pools.Pool<List<Exception>> pool) {
        C0430p c0430p = new C0430p(pool);
        this.f235b = new a();
        this.f234a = c0430p;
    }

    /* renamed from: a */
    public final <A> List<InterfaceC0426l<A, ?>> m139a(Class<A> cls) {
        ArrayList arrayList;
        a.C2162a<?> c2162a = this.f235b.f236a.get(cls);
        List<InterfaceC0426l<?, ?>> list = c2162a == null ? (List<InterfaceC0426l<A, ?>>) null : c2162a.f237a;
        if (list == null) {
            C0430p c0430p = this.f234a;
            synchronized (c0430p) {
                try {
                    arrayList = new ArrayList();
                    for (C0430p.b<?, ?> bVar : c0430p.f248a) {
                        if (!c0430p.f250c.contains(bVar) && bVar.f252a.isAssignableFrom(cls)) {
                            c0430p.f250c.add(bVar);
                            InterfaceC0426l<?, ?> interfaceC0426lMo120b = bVar.f254c.mo120b(c0430p);
                            Objects.requireNonNull(interfaceC0426lMo120b, "Argument must not be null");
                            arrayList.add(interfaceC0426lMo120b);
                            c0430p.f250c.remove(bVar);
                        }
                    }
                } catch (Throwable th) {
                    c0430p.f250c.clear();
                    throw th;
                }
            }
            list = (List<InterfaceC0426l<A, ?>>) Collections.unmodifiableList(arrayList);
            if (this.f235b.f236a.put(cls, new a.C2162a<>(list)) != null) {
                throw new IllegalStateException("Already cached loaders for model: " + cls);
            }
        }
        return (List<InterfaceC0426l<A, ?>>) list;
    }
}
