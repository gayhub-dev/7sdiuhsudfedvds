package p010b0;

import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import p010b0.InterfaceC0426l;
import p141r.C1812g;
import p162u.C1966j;

/* compiled from: MultiModelLoaderFactory.java */
/* renamed from: b0.p */
/* loaded from: classes.dex */
public class C0430p {

    /* renamed from: e */
    public static final c f246e = new c();

    /* renamed from: f */
    public static final InterfaceC0426l<Object, Object> f247f = new a();

    /* renamed from: a */
    public final List<b<?, ?>> f248a;

    /* renamed from: b */
    public final c f249b;

    /* renamed from: c */
    public final Set<b<?, ?>> f250c;

    /* renamed from: d */
    public final Pools.Pool<List<Exception>> f251d;

    /* compiled from: MultiModelLoaderFactory.java */
    /* renamed from: b0.p$a */
    public static class a implements InterfaceC0426l<Object, Object> {
        @Override // p010b0.InterfaceC0426l
        @Nullable
        /* renamed from: a */
        public InterfaceC0426l.a<Object> mo117a(Object obj, int i7, int i8, C1966j c1966j) {
            return null;
        }

        @Override // p010b0.InterfaceC0426l
        /* renamed from: b */
        public boolean mo118b(Object obj) {
            return false;
        }
    }

    /* compiled from: MultiModelLoaderFactory.java */
    /* renamed from: b0.p$b */
    public static class b<Model, Data> {

        /* renamed from: a */
        public final Class<Model> f252a;

        /* renamed from: b */
        public final Class<Data> f253b;

        /* renamed from: c */
        public final InterfaceC0427m<Model, Data> f254c;

        public b(Class<Model> cls, Class<Data> cls2, InterfaceC0427m<Model, Data> interfaceC0427m) {
            this.f252a = cls;
            this.f253b = cls2;
            this.f254c = interfaceC0427m;
        }
    }

    /* compiled from: MultiModelLoaderFactory.java */
    /* renamed from: b0.p$c */
    public static class c {
    }

    public C0430p(Pools.Pool<List<Exception>> pool) {
        c cVar = f246e;
        this.f248a = new ArrayList();
        this.f250c = new HashSet();
        this.f251d = pool;
        this.f249b = cVar;
    }

    /* renamed from: a */
    public final <Model, Data> InterfaceC0426l<Model, Data> m143a(b<?, ?> bVar) {
        InterfaceC0426l<Model, Data> interfaceC0426l = (InterfaceC0426l<Model, Data>) bVar.f254c.mo120b(this);
        Objects.requireNonNull(interfaceC0426l, "Argument must not be null");
        return interfaceC0426l;
    }

    /* renamed from: b */
    public synchronized <Model, Data> InterfaceC0426l<Model, Data> m144b(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z6 = false;
            for (b<?, ?> bVar : this.f248a) {
                if (this.f250c.contains(bVar)) {
                    z6 = true;
                } else if (bVar.f252a.isAssignableFrom(cls) && bVar.f253b.isAssignableFrom(cls2)) {
                    this.f250c.add(bVar);
                    arrayList.add(m143a(bVar));
                    this.f250c.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                c cVar = this.f249b;
                Pools.Pool<List<Exception>> pool = this.f251d;
                Objects.requireNonNull(cVar);
                return new C0429o(arrayList, pool);
            }
            if (arrayList.size() == 1) {
                return (InterfaceC0426l) arrayList.get(0);
            }
            if (z6) {
                return (InterfaceC0426l<Model, Data>) f247f;
            }
            throw new C1812g.c(cls, cls2);
        } catch (Throwable th) {
            this.f250c.clear();
            throw th;
        }
    }

    /* renamed from: c */
    public synchronized List<Class<?>> m145c(Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (b<?, ?> bVar : this.f248a) {
            if (!arrayList.contains(bVar.f253b) && bVar.f252a.isAssignableFrom(cls)) {
                arrayList.add(bVar.f253b);
            }
        }
        return arrayList;
    }
}
