package p141r;

import android.support.v4.util.Pools;
import com.alibaba.fastjson.asm.C0532a;
import java.util.ArrayList;
import java.util.List;
import p010b0.C0428n;
import p010b0.C0430p;
import p010b0.InterfaceC0426l;
import p010b0.InterfaceC0427m;
import p075j.C1180h;
import p076j0.C1183c;
import p076j0.InterfaceC1182b;
import p100m0.C1453a;
import p100m0.C1454b;
import p100m0.C1455c;
import p100m0.C1456d;
import p149s0.C1860a;
import p149s0.C1861b;
import p149s0.C1862c;
import p162u.InterfaceC1960d;
import p162u.InterfaceC1962f;
import p162u.InterfaceC1967k;
import p162u.InterfaceC1968l;
import p169v.C1988d;
import p169v.InterfaceC1987c;

/* compiled from: Registry.java */
/* renamed from: r.g */
/* loaded from: classes.dex */
public class C1812g {

    /* renamed from: a */
    public final C0428n f5246a;

    /* renamed from: b */
    public final C1453a f5247b;

    /* renamed from: c */
    public final C1455c f5248c;

    /* renamed from: d */
    public final C1456d f5249d;

    /* renamed from: e */
    public final C1988d f5250e;

    /* renamed from: f */
    public final C1183c f5251f;

    /* renamed from: g */
    public final C1180h f5252g;

    /* renamed from: h */
    public final C1454b f5253h = new C1454b(1);

    /* renamed from: i */
    public final C1454b f5254i = new C1454b(0);

    /* renamed from: j */
    public final Pools.Pool<List<Exception>> f5255j;

    /* compiled from: Registry.java */
    /* renamed from: r.g$a */
    public static class a extends RuntimeException {
        public a(String str) {
            super(str);
        }
    }

    /* compiled from: Registry.java */
    /* renamed from: r.g$b */
    public static final class b extends a {
        public b() {
            super("Failed to find image header parser.");
        }
    }

    /* compiled from: Registry.java */
    /* renamed from: r.g$c */
    public static class c extends a {
        public c(Object obj) {
            super(C0532a.m338a("Failed to find any ModelLoaders for model: ", obj));
        }

        public c(Class<?> cls, Class<?> cls2) {
            super("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        }
    }

    /* compiled from: Registry.java */
    /* renamed from: r.g$d */
    public static class d extends a {
        public d(Class<?> cls) {
            super("Failed to find result encoder for resource class: " + cls);
        }
    }

    /* compiled from: Registry.java */
    /* renamed from: r.g$e */
    public static class e extends a {
        public e(Class<?> cls) {
            super("Failed to find source encoder for data class: " + cls);
        }
    }

    public C1812g() {
        C1860a.c cVar = new C1860a.c(new Pools.SynchronizedPool(20), new C1861b(), new C1862c());
        this.f5255j = cVar;
        this.f5246a = new C0428n(cVar);
        this.f5247b = new C1453a();
        this.f5248c = new C1455c();
        this.f5249d = new C1456d();
        this.f5250e = new C1988d();
        this.f5251f = new C1183c();
        this.f5252g = new C1180h(1);
    }

    /* renamed from: a */
    public <Model, Data> C1812g m2026a(Class<Model> cls, Class<Data> cls2, InterfaceC0427m<Model, Data> interfaceC0427m) {
        C0428n c0428n = this.f5246a;
        synchronized (c0428n) {
            C0430p c0430p = c0428n.f234a;
            synchronized (c0430p) {
                C0430p.b<?, ?> bVar = new C0430p.b<>(cls, cls2, interfaceC0427m);
                List<C0430p.b<?, ?>> list = c0430p.f248a;
                list.add(list.size(), bVar);
            }
            c0428n.f235b.f236a.clear();
        }
        return this;
    }

    /* renamed from: b */
    public <Data, TResource> C1812g m2027b(Class<Data> cls, Class<TResource> cls2, InterfaceC1967k<Data, TResource> interfaceC1967k) {
        C1455c c1455c = this.f5248c;
        synchronized (c1455c) {
            c1455c.f4217a.add(new C1455c.a<>(cls, cls2, interfaceC1967k));
        }
        return this;
    }

    /* renamed from: c */
    public List<InterfaceC1962f> m2028c() {
        List list;
        C1180h c1180h = this.f5252g;
        synchronized (c1180h) {
            list = c1180h.f2594a;
        }
        if (list.isEmpty()) {
            throw new b();
        }
        return list;
    }

    /* renamed from: d */
    public <Model> List<InterfaceC0426l<Model, ?>> m2029d(Model model) {
        ArrayList arrayList;
        C0428n c0428n = this.f5246a;
        synchronized (c0428n) {
            List listM139a = c0428n.m139a(model.getClass());
            int size = listM139a.size();
            arrayList = new ArrayList(size);
            for (int i7 = 0; i7 < size; i7++) {
                InterfaceC0426l interfaceC0426l = (InterfaceC0426l) listM139a.get(i7);
                if (interfaceC0426l.mo118b(model)) {
                    arrayList.add(interfaceC0426l);
                }
            }
        }
        if (arrayList.isEmpty()) {
            throw new c(model);
        }
        return arrayList;
    }

    /* renamed from: e */
    public <Data, TResource> C1812g m2030e(Class<Data> cls, Class<TResource> cls2, InterfaceC1967k<Data, TResource> interfaceC1967k) {
        C1455c c1455c = this.f5248c;
        synchronized (c1455c) {
            c1455c.f4217a.add(0, new C1455c.a<>(cls, cls2, interfaceC1967k));
        }
        return this;
    }

    /* renamed from: f */
    public <TResource, Transcode> C1812g m2031f(Class<TResource> cls, Class<Transcode> cls2, InterfaceC1182b<TResource, Transcode> interfaceC1182b) {
        C1183c c1183c = this.f5251f;
        synchronized (c1183c) {
            c1183c.f2597a.add(new C1183c.a<>(cls, cls2, interfaceC1182b));
        }
        return this;
    }

    /* renamed from: g */
    public <Data> C1812g m2032g(Class<Data> cls, InterfaceC1960d<Data> interfaceC1960d) {
        C1453a c1453a = this.f5247b;
        synchronized (c1453a) {
            c1453a.f4212a.add(new C1453a.a<>(cls, interfaceC1960d));
        }
        return this;
    }

    /* renamed from: h */
    public <TResource> C1812g m2033h(Class<TResource> cls, InterfaceC1968l<TResource> interfaceC1968l) {
        C1456d c1456d = this.f5249d;
        synchronized (c1456d) {
            c1456d.f4221a.add(new C1456d.a<>(cls, interfaceC1968l));
        }
        return this;
    }

    /* renamed from: i */
    public C1812g m2034i(InterfaceC1987c.a aVar) {
        C1988d c1988d = this.f5250e;
        synchronized (c1988d) {
            c1988d.f5806a.put(aVar.mo1005a(), aVar);
        }
        return this;
    }
}
