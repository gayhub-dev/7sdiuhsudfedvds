package p183x;

import android.os.Looper;
import android.os.MessageQueue;
import android.support.v4.util.Pools;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import p001a0.C0004b;
import p043f.C0984a;
import p108n0.InterfaceC1513d;
import p142r0.C1823h;
import p149s0.C1860a;
import p162u.InterfaceC1964h;
import p183x.C2052m;
import p183x.RunnableC2045f;
import p197z.C2135b;
import p197z.C2137d;
import p197z.C2141h;
import p197z.InterfaceC2134a;
import p197z.InterfaceC2142i;

/* compiled from: Engine.java */
/* renamed from: x.i */
/* loaded from: classes.dex */
public class C2048i implements InterfaceC2050k, InterfaceC2142i.a, C2052m.a {

    /* renamed from: a */
    public final Map<InterfaceC1964h, C2049j<?>> f6046a;

    /* renamed from: b */
    public final C0984a f6047b;

    /* renamed from: c */
    public final InterfaceC2142i f6048c;

    /* renamed from: d */
    public final b f6049d;

    /* renamed from: e */
    public final Map<InterfaceC1964h, WeakReference<C2052m<?>>> f6050e;

    /* renamed from: f */
    public final C2060u f6051f;

    /* renamed from: g */
    public final a f6052g;

    /* renamed from: h */
    public ReferenceQueue<C2052m<?>> f6053h;

    /* compiled from: Engine.java */
    /* renamed from: x.i$a */
    public static class a {

        /* renamed from: a */
        public final RunnableC2045f.d f6054a;

        /* renamed from: b */
        public final Pools.Pool<RunnableC2045f<?>> f6055b = C1860a.m2131a(150, new C2191a());

        /* renamed from: c */
        public int f6056c;

        /* compiled from: Engine.java */
        /* renamed from: x.i$a$a, reason: collision with other inner class name */
        public class C2191a implements C1860a.b<RunnableC2045f<?>> {
            public C2191a() {
            }

            @Override // p149s0.C1860a.b
            /* renamed from: a */
            public RunnableC2045f<?> mo1703a() {
                a aVar = a.this;
                return new RunnableC2045f<>(aVar.f6054a, aVar.f6055b);
            }
        }

        public a(RunnableC2045f.d dVar) {
            this.f6054a = dVar;
        }
    }

    /* compiled from: Engine.java */
    /* renamed from: x.i$b */
    public static class b {

        /* renamed from: a */
        public final C0004b f6058a;

        /* renamed from: b */
        public final C0004b f6059b;

        /* renamed from: c */
        public final C0004b f6060c;

        /* renamed from: d */
        public final InterfaceC2050k f6061d;

        /* renamed from: e */
        public final Pools.Pool<C2049j<?>> f6062e = C1860a.m2131a(150, new a());

        /* compiled from: Engine.java */
        /* renamed from: x.i$b$a */
        public class a implements C1860a.b<C2049j<?>> {
            public a() {
            }

            @Override // p149s0.C1860a.b
            /* renamed from: a */
            public C2049j<?> mo1703a() {
                b bVar = b.this;
                return new C2049j<>(bVar.f6058a, bVar.f6059b, bVar.f6060c, bVar.f6061d, bVar.f6062e);
            }
        }

        public b(C0004b c0004b, C0004b c0004b2, C0004b c0004b3, InterfaceC2050k interfaceC2050k) {
            this.f6058a = c0004b;
            this.f6059b = c0004b2;
            this.f6060c = c0004b3;
            this.f6061d = interfaceC2050k;
        }
    }

    /* compiled from: Engine.java */
    /* renamed from: x.i$c */
    public static class c implements RunnableC2045f.d {

        /* renamed from: a */
        public final InterfaceC2134a.a f6064a;

        /* renamed from: b */
        public volatile InterfaceC2134a f6065b;

        public c(InterfaceC2134a.a aVar) {
            this.f6064a = aVar;
        }

        /* renamed from: a */
        public InterfaceC2134a m2434a() {
            if (this.f6065b == null) {
                synchronized (this) {
                    if (this.f6065b == null) {
                        this.f6065b = ((C2137d) this.f6064a).m2569a();
                    }
                    if (this.f6065b == null) {
                        this.f6065b = new C2135b();
                    }
                }
            }
            return this.f6065b;
        }
    }

    /* compiled from: Engine.java */
    /* renamed from: x.i$d */
    public static class d {

        /* renamed from: a */
        public final C2049j<?> f6066a;

        /* renamed from: b */
        public final InterfaceC1513d f6067b;

        public d(InterfaceC1513d interfaceC1513d, C2049j<?> c2049j) {
            this.f6067b = interfaceC1513d;
            this.f6066a = c2049j;
        }
    }

    /* compiled from: Engine.java */
    /* renamed from: x.i$e */
    public static class e implements MessageQueue.IdleHandler {

        /* renamed from: a */
        public final Map<InterfaceC1964h, WeakReference<C2052m<?>>> f6068a;

        /* renamed from: b */
        public final ReferenceQueue<C2052m<?>> f6069b;

        public e(Map<InterfaceC1964h, WeakReference<C2052m<?>>> map, ReferenceQueue<C2052m<?>> referenceQueue) {
            this.f6068a = map;
            this.f6069b = referenceQueue;
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            f fVar = (f) this.f6069b.poll();
            if (fVar == null) {
                return true;
            }
            this.f6068a.remove(fVar.f6070a);
            return true;
        }
    }

    /* compiled from: Engine.java */
    /* renamed from: x.i$f */
    public static class f extends WeakReference<C2052m<?>> {

        /* renamed from: a */
        public final InterfaceC1964h f6070a;

        public f(InterfaceC1964h interfaceC1964h, C2052m<?> c2052m, ReferenceQueue<? super C2052m<?>> referenceQueue) {
            super(c2052m, referenceQueue);
            this.f6070a = interfaceC1964h;
        }
    }

    public C2048i(InterfaceC2142i interfaceC2142i, InterfaceC2134a.a aVar, C0004b c0004b, C0004b c0004b2, C0004b c0004b3) {
        this.f6048c = interfaceC2142i;
        c cVar = new c(aVar);
        this.f6050e = new HashMap();
        this.f6047b = new C0984a(2);
        this.f6046a = new HashMap();
        this.f6049d = new b(c0004b, c0004b2, c0004b3, this);
        this.f6052g = new a(cVar);
        this.f6051f = new C2060u();
        ((C2141h) interfaceC2142i).f6295d = this;
    }

    /* renamed from: a */
    public final ReferenceQueue<C2052m<?>> m2431a() {
        if (this.f6053h == null) {
            this.f6053h = new ReferenceQueue<>();
            Looper.myQueue().addIdleHandler(new e(this.f6050e, this.f6053h));
        }
        return this.f6053h;
    }

    /* renamed from: b */
    public void m2432b(C2049j c2049j, InterfaceC1964h interfaceC1964h) {
        C1823h.m2057a();
        if (c2049j.equals(this.f6046a.get(interfaceC1964h))) {
            this.f6046a.remove(interfaceC1964h);
        }
    }

    /* renamed from: c */
    public void m2433c(InterfaceC1964h interfaceC1964h, C2052m<?> c2052m) {
        C1823h.m2057a();
        if (c2052m != null) {
            c2052m.f6104g = interfaceC1964h;
            c2052m.f6103f = this;
            if (c2052m.f6102e) {
                this.f6050e.put(interfaceC1964h, new f(interfaceC1964h, c2052m, m2431a()));
            }
        }
        this.f6046a.remove(interfaceC1964h);
    }
}
