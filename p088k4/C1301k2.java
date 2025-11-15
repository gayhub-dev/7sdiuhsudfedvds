package p088k4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p000a.C0000a;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0954g;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2118j;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableMergeWithMaybe.java */
/* renamed from: k4.k2 */
/* loaded from: classes.dex */
public final class C1301k2<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2118j<? extends T> f3273f;

    /* compiled from: ObservableMergeWithMaybe.java */
    /* renamed from: k4.k2$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -4592979584110982903L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3274e;

        /* renamed from: f */
        public final AtomicReference<InterfaceC2153b> f3275f = new AtomicReference<>();

        /* renamed from: g */
        public final C2178a<T> f3276g = new C2178a<>(this);

        /* renamed from: h */
        public final C1771c f3277h = new C1771c();

        /* renamed from: i */
        public volatile InterfaceC0954g<T> f3278i;

        /* renamed from: j */
        public T f3279j;

        /* renamed from: k */
        public volatile boolean f3280k;

        /* renamed from: l */
        public volatile boolean f3281l;

        /* renamed from: m */
        public volatile int f3282m;

        /* compiled from: ObservableMergeWithMaybe.java */
        /* renamed from: k4.k2$a$a, reason: collision with other inner class name */
        public static final class C2178a<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2117i<T> {
            private static final long serialVersionUID = -2935427570954647017L;

            /* renamed from: e */
            public final a<T> f3283e;

            public C2178a(a<T> aVar) {
                this.f3283e = aVar;
            }

            @Override // p194y3.InterfaceC2117i
            /* renamed from: a */
            public void mo1016a(T t6) {
                a<T> aVar = this.f3283e;
                if (aVar.compareAndSet(0, 1)) {
                    aVar.f3274e.onNext(t6);
                    aVar.f3282m = 2;
                } else {
                    aVar.f3279j = t6;
                    aVar.f3282m = 1;
                    if (aVar.getAndIncrement() != 0) {
                        return;
                    }
                }
                aVar.m1512b();
            }

            @Override // p194y3.InterfaceC2117i
            public void onComplete() {
                a<T> aVar = this.f3283e;
                aVar.f3282m = 2;
                aVar.m1511a();
            }

            @Override // p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a<T> aVar = this.f3283e;
                if (!C1774f.m1958a(aVar.f3277h, th)) {
                    C1908a.m2205b(th);
                } else {
                    EnumC0515c.m323a(aVar.f3275f);
                    aVar.m1511a();
                }
            }

            @Override // p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3274e = interfaceC2127s;
        }

        /* renamed from: a */
        public void m1511a() {
            if (getAndIncrement() == 0) {
                m1512b();
            }
        }

        /* renamed from: b */
        public void m1512b() {
            InterfaceC2127s<? super T> interfaceC2127s = this.f3274e;
            int iAddAndGet = 1;
            while (!this.f3280k) {
                if (this.f3277h.get() != null) {
                    this.f3279j = null;
                    this.f3278i = null;
                    interfaceC2127s.onError(C1774f.m1959b(this.f3277h));
                    return;
                }
                int i7 = this.f3282m;
                if (i7 == 1) {
                    T t6 = this.f3279j;
                    this.f3279j = null;
                    this.f3282m = 2;
                    interfaceC2127s.onNext(t6);
                    i7 = 2;
                }
                boolean z6 = this.f3281l;
                InterfaceC0954g<T> interfaceC0954g = this.f3278i;
                C0000a c0000aPoll = interfaceC0954g != null ? interfaceC0954g.poll() : null;
                boolean z7 = c0000aPoll == null;
                if (z6 && z7 && i7 == 2) {
                    this.f3278i = null;
                    interfaceC2127s.onComplete();
                    return;
                } else if (z7) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    interfaceC2127s.onNext(c0000aPoll);
                }
            }
            this.f3279j = null;
            this.f3278i = null;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3280k = true;
            EnumC0515c.m323a(this.f3275f);
            EnumC0515c.m323a(this.f3276g);
            if (getAndIncrement() == 0) {
                this.f3278i = null;
                this.f3279j = null;
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f3275f.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3281l = true;
            m1511a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f3277h, th)) {
                C1908a.m2205b(th);
            } else {
                EnumC0515c.m323a(this.f3275f);
                m1511a();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (compareAndSet(0, 1)) {
                this.f3274e.onNext(t6);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                C1489c c1489c = this.f3278i;
                if (c1489c == null) {
                    c1489c = new C1489c(AbstractC2120l.bufferSize());
                    this.f3278i = c1489c;
                }
                c1489c.offer(t6);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            m1512b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3275f, interfaceC2153b);
        }
    }

    public C1301k2(AbstractC2120l<T> abstractC2120l, InterfaceC2118j<? extends T> interfaceC2118j) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3273f = interfaceC2118j;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        a aVar = new a(interfaceC2127s);
        interfaceC2127s.onSubscribe(aVar);
        this.f2772e.subscribe(aVar);
        this.f3273f.mo2555b(aVar.f3276g);
    }
}
