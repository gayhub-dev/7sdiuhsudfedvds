package p080j4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2118j;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSwitchMapMaybe.java */
/* renamed from: j4.e */
/* loaded from: classes.dex */
public final class C1208e<T, R> extends AbstractC2120l<R> {

    /* renamed from: e */
    public final AbstractC2120l<T> f2698e;

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> f2699f;

    /* renamed from: g */
    public final boolean f2700g;

    /* compiled from: ObservableSwitchMapMaybe.java */
    /* renamed from: j4.e$a */
    public static final class a<T, R> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: m */
        public static final C2168a<Object> f2701m = new C2168a<>(null);
        private static final long serialVersionUID = -5402190102429853762L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f2702e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> f2703f;

        /* renamed from: g */
        public final boolean f2704g;

        /* renamed from: h */
        public final C1771c f2705h = new C1771c();

        /* renamed from: i */
        public final AtomicReference<C2168a<R>> f2706i = new AtomicReference<>();

        /* renamed from: j */
        public InterfaceC2153b f2707j;

        /* renamed from: k */
        public volatile boolean f2708k;

        /* renamed from: l */
        public volatile boolean f2709l;

        /* compiled from: ObservableSwitchMapMaybe.java */
        /* renamed from: j4.e$a$a, reason: collision with other inner class name */
        public static final class C2168a<R> extends AtomicReference<InterfaceC2153b> implements InterfaceC2117i<R> {
            private static final long serialVersionUID = 8042919737683345351L;

            /* renamed from: e */
            public final a<?, R> f2710e;

            /* renamed from: f */
            public volatile R f2711f;

            public C2168a(a<?, R> aVar) {
                this.f2710e = aVar;
            }

            @Override // p194y3.InterfaceC2117i
            /* renamed from: a */
            public void mo1016a(R r6) {
                this.f2711f = r6;
                this.f2710e.m1435b();
            }

            @Override // p194y3.InterfaceC2117i
            public void onComplete() {
                a<?, R> aVar = this.f2710e;
                if (aVar.f2706i.compareAndSet(this, null)) {
                    aVar.m1435b();
                }
            }

            @Override // p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a<?, R> aVar = this.f2710e;
                if (!aVar.f2706i.compareAndSet(this, null) || !C1774f.m1958a(aVar.f2705h, th)) {
                    C1908a.m2205b(th);
                    return;
                }
                if (!aVar.f2704g) {
                    aVar.f2707j.dispose();
                    aVar.m1434a();
                }
                aVar.m1435b();
            }

            @Override // p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n, boolean z6) {
            this.f2702e = interfaceC2127s;
            this.f2703f = interfaceC0454n;
            this.f2704g = z6;
        }

        /* renamed from: a */
        public void m1434a() {
            AtomicReference<C2168a<R>> atomicReference = this.f2706i;
            C2168a<Object> c2168a = f2701m;
            C2168a<Object> c2168a2 = (C2168a) atomicReference.getAndSet(c2168a);
            if (c2168a2 == null || c2168a2 == c2168a) {
                return;
            }
            EnumC0515c.m323a(c2168a2);
        }

        /* renamed from: b */
        public void m1435b() {
            if (getAndIncrement() != 0) {
                return;
            }
            InterfaceC2127s<? super R> interfaceC2127s = this.f2702e;
            C1771c c1771c = this.f2705h;
            AtomicReference<C2168a<R>> atomicReference = this.f2706i;
            int iAddAndGet = 1;
            while (!this.f2709l) {
                if (c1771c.get() != null && !this.f2704g) {
                    interfaceC2127s.onError(C1774f.m1959b(c1771c));
                    return;
                }
                boolean z6 = this.f2708k;
                C2168a<R> c2168a = atomicReference.get();
                boolean z7 = c2168a == null;
                if (z6 && z7) {
                    Throwable thM1959b = C1774f.m1959b(c1771c);
                    if (thM1959b != null) {
                        interfaceC2127s.onError(thM1959b);
                        return;
                    } else {
                        interfaceC2127s.onComplete();
                        return;
                    }
                }
                if (z7 || c2168a.f2711f == null) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    atomicReference.compareAndSet(c2168a, null);
                    interfaceC2127s.onNext(c2168a.f2711f);
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2709l = true;
            this.f2707j.dispose();
            m1434a();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2709l;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2708k = true;
            m1435b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f2705h, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (!this.f2704g) {
                m1434a();
            }
            this.f2708k = true;
            m1435b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            C2168a<R> c2168a;
            C2168a<R> c2168a2 = this.f2706i.get();
            if (c2168a2 != null) {
                EnumC0515c.m323a(c2168a2);
            }
            try {
                InterfaceC2118j<? extends R> interfaceC2118jApply = this.f2703f.apply(t6);
                Objects.requireNonNull(interfaceC2118jApply, "The mapper returned a null MaybeSource");
                InterfaceC2118j<? extends R> interfaceC2118j = interfaceC2118jApply;
                C2168a<R> c2168a3 = new C2168a<>(this);
                do {
                    c2168a = this.f2706i.get();
                    if (c2168a == f2701m) {
                        return;
                    }
                } while (!this.f2706i.compareAndSet(c2168a, c2168a3));
                interfaceC2118j.mo2555b(c2168a3);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f2707j.dispose();
                this.f2706i.getAndSet(f2701m);
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2707j, interfaceC2153b)) {
                this.f2707j = interfaceC2153b;
                this.f2702e.onSubscribe(this);
            }
        }
    }

    public C1208e(AbstractC2120l<T> abstractC2120l, InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n, boolean z6) {
        this.f2698e = abstractC2120l;
        this.f2699f = interfaceC0454n;
        this.f2700g = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        if (C2074b.m2473M(this.f2698e, this.f2699f, interfaceC2127s)) {
            return;
        }
        this.f2698e.subscribe(new a(interfaceC2127s, this.f2699f, this.f2700g));
    }
}
