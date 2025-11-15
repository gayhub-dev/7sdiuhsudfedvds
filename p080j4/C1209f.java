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
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p194y3.InterfaceC2131w;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSwitchMapSingle.java */
/* renamed from: j4.f */
/* loaded from: classes.dex */
public final class C1209f<T, R> extends AbstractC2120l<R> {

    /* renamed from: e */
    public final AbstractC2120l<T> f2712e;

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> f2713f;

    /* renamed from: g */
    public final boolean f2714g;

    /* compiled from: ObservableSwitchMapSingle.java */
    /* renamed from: j4.f$a */
    public static final class a<T, R> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: m */
        public static final C2169a<Object> f2715m = new C2169a<>(null);
        private static final long serialVersionUID = -5402190102429853762L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f2716e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> f2717f;

        /* renamed from: g */
        public final boolean f2718g;

        /* renamed from: h */
        public final C1771c f2719h = new C1771c();

        /* renamed from: i */
        public final AtomicReference<C2169a<R>> f2720i = new AtomicReference<>();

        /* renamed from: j */
        public InterfaceC2153b f2721j;

        /* renamed from: k */
        public volatile boolean f2722k;

        /* renamed from: l */
        public volatile boolean f2723l;

        /* compiled from: ObservableSwitchMapSingle.java */
        /* renamed from: j4.f$a$a, reason: collision with other inner class name */
        public static final class C2169a<R> extends AtomicReference<InterfaceC2153b> implements InterfaceC2130v<R> {
            private static final long serialVersionUID = 8042919737683345351L;

            /* renamed from: e */
            public final a<?, R> f2724e;

            /* renamed from: f */
            public volatile R f2725f;

            public C2169a(a<?, R> aVar) {
                this.f2724e = aVar;
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2117i
            /* renamed from: a */
            public void mo1016a(R r6) {
                this.f2725f = r6;
                this.f2724e.m1437b();
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a<?, R> aVar = this.f2724e;
                if (!aVar.f2720i.compareAndSet(this, null) || !C1774f.m1958a(aVar.f2719h, th)) {
                    C1908a.m2205b(th);
                    return;
                }
                if (!aVar.f2718g) {
                    aVar.f2721j.dispose();
                    aVar.m1436a();
                }
                aVar.m1437b();
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n, boolean z6) {
            this.f2716e = interfaceC2127s;
            this.f2717f = interfaceC0454n;
            this.f2718g = z6;
        }

        /* renamed from: a */
        public void m1436a() {
            AtomicReference<C2169a<R>> atomicReference = this.f2720i;
            C2169a<Object> c2169a = f2715m;
            C2169a<Object> c2169a2 = (C2169a) atomicReference.getAndSet(c2169a);
            if (c2169a2 == null || c2169a2 == c2169a) {
                return;
            }
            EnumC0515c.m323a(c2169a2);
        }

        /* renamed from: b */
        public void m1437b() {
            if (getAndIncrement() != 0) {
                return;
            }
            InterfaceC2127s<? super R> interfaceC2127s = this.f2716e;
            C1771c c1771c = this.f2719h;
            AtomicReference<C2169a<R>> atomicReference = this.f2720i;
            int iAddAndGet = 1;
            while (!this.f2723l) {
                if (c1771c.get() != null && !this.f2718g) {
                    interfaceC2127s.onError(C1774f.m1959b(c1771c));
                    return;
                }
                boolean z6 = this.f2722k;
                C2169a<R> c2169a = atomicReference.get();
                boolean z7 = c2169a == null;
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
                if (z7 || c2169a.f2725f == null) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    atomicReference.compareAndSet(c2169a, null);
                    interfaceC2127s.onNext(c2169a.f2725f);
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2723l = true;
            this.f2721j.dispose();
            m1436a();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2723l;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2722k = true;
            m1437b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f2719h, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (!this.f2718g) {
                m1436a();
            }
            this.f2722k = true;
            m1437b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            C2169a<R> c2169a;
            C2169a<R> c2169a2 = this.f2720i.get();
            if (c2169a2 != null) {
                EnumC0515c.m323a(c2169a2);
            }
            try {
                InterfaceC2131w<? extends R> interfaceC2131wApply = this.f2717f.apply(t6);
                Objects.requireNonNull(interfaceC2131wApply, "The mapper returned a null SingleSource");
                InterfaceC2131w<? extends R> interfaceC2131w = interfaceC2131wApply;
                C2169a<R> c2169a3 = new C2169a<>(this);
                do {
                    c2169a = this.f2720i.get();
                    if (c2169a == f2715m) {
                        return;
                    }
                } while (!this.f2720i.compareAndSet(c2169a, c2169a3));
                interfaceC2131w.mo2562b(c2169a3);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f2721j.dispose();
                this.f2720i.getAndSet(f2715m);
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2721j, interfaceC2153b)) {
                this.f2721j = interfaceC2153b;
                this.f2716e.onSubscribe(this);
            }
        }
    }

    public C1209f(AbstractC2120l<T> abstractC2120l, InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n, boolean z6) {
        this.f2712e = abstractC2120l;
        this.f2713f = interfaceC0454n;
        this.f2714g = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        if (C2074b.m2474N(this.f2712e, this.f2713f, interfaceC2127s)) {
            return;
        }
        this.f2712e.subscribe(new a(interfaceC2127s, this.f2713f, this.f2714g));
    }
}
