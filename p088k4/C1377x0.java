package p088k4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p000a.C0000a;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p194y3.InterfaceC2131w;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableFlatMapSingle.java */
/* renamed from: k4.x0 */
/* loaded from: classes.dex */
public final class C1377x0<T, R> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> f3972f;

    /* renamed from: g */
    public final boolean f3973g;

    /* compiled from: ObservableFlatMapSingle.java */
    /* renamed from: k4.x0$a */
    public static final class a<T, R> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 8600231336733376951L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3974e;

        /* renamed from: f */
        public final boolean f3975f;

        /* renamed from: j */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> f3979j;

        /* renamed from: l */
        public InterfaceC2153b f3981l;

        /* renamed from: m */
        public volatile boolean f3982m;

        /* renamed from: g */
        public final C2152a f3976g = new C2152a(0);

        /* renamed from: i */
        public final C1771c f3978i = new C1771c();

        /* renamed from: h */
        public final AtomicInteger f3977h = new AtomicInteger(1);

        /* renamed from: k */
        public final AtomicReference<C1489c<R>> f3980k = new AtomicReference<>();

        /* compiled from: ObservableFlatMapSingle.java */
        /* renamed from: k4.x0$a$a, reason: collision with other inner class name */
        public final class C2187a extends AtomicReference<InterfaceC2153b> implements InterfaceC2130v<R>, InterfaceC2153b {
            private static final long serialVersionUID = -502562646270949838L;

            public C2187a() {
            }

            /* JADX WARN: Removed duplicated region for block: B:41:? A[SYNTHETIC] */
            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2117i
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void mo1016a(R r5) {
                /*
                    r4 = this;
                    k4.x0$a r0 = p088k4.C1377x0.a.this
                    z3.a r1 = r0.f3976g
                    r1.mo322a(r4)
                    int r1 = r0.get()
                    if (r1 != 0) goto L50
                    r1 = 0
                    r2 = 1
                    boolean r3 = r0.compareAndSet(r1, r2)
                    if (r3 == 0) goto L50
                    y3.s<? super R> r3 = r0.f3974e
                    r3.onNext(r5)
                    java.util.concurrent.atomic.AtomicInteger r5 = r0.f3977h
                    int r5 = r5.decrementAndGet()
                    if (r5 != 0) goto L23
                    r1 = 1
                L23:
                    java.util.concurrent.atomic.AtomicReference<m4.c<R>> r5 = r0.f3980k
                    java.lang.Object r5 = r5.get()
                    m4.c r5 = (p104m4.C1489c) r5
                    if (r1 == 0) goto L49
                    if (r5 == 0) goto L35
                    boolean r5 = r5.isEmpty()
                    if (r5 == 0) goto L49
                L35:
                    q4.c r5 = r0.f3978i
                    java.lang.Throwable r5 = p138q4.C1774f.m1959b(r5)
                    if (r5 == 0) goto L43
                    y3.s<? super R> r0 = r0.f3974e
                    r0.onError(r5)
                    goto L81
                L43:
                    y3.s<? super R> r5 = r0.f3974e
                    r5.onComplete()
                    goto L81
                L49:
                    int r5 = r0.decrementAndGet()
                    if (r5 != 0) goto L7e
                    goto L81
                L50:
                    java.util.concurrent.atomic.AtomicReference<m4.c<R>> r1 = r0.f3980k
                    java.lang.Object r1 = r1.get()
                    m4.c r1 = (p104m4.C1489c) r1
                    if (r1 == 0) goto L5b
                    goto L6d
                L5b:
                    m4.c r1 = new m4.c
                    int r2 = p194y3.AbstractC2120l.bufferSize()
                    r1.<init>(r2)
                    java.util.concurrent.atomic.AtomicReference<m4.c<R>> r2 = r0.f3980k
                    r3 = 0
                    boolean r2 = r2.compareAndSet(r3, r1)
                    if (r2 == 0) goto L50
                L6d:
                    monitor-enter(r1)
                    r1.offer(r5)     // Catch: java.lang.Throwable -> L82
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L82
                    java.util.concurrent.atomic.AtomicInteger r5 = r0.f3977h
                    r5.decrementAndGet()
                    int r5 = r0.getAndIncrement()
                    if (r5 == 0) goto L7e
                    goto L81
                L7e:
                    r0.m1558b()
                L81:
                    return
                L82:
                    r5 = move-exception
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L82
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: p088k4.C1377x0.a.C2187a.mo1016a(java.lang.Object):void");
            }

            @Override // p201z3.InterfaceC2153b
            public void dispose() {
                EnumC0515c.m323a(this);
            }

            @Override // p201z3.InterfaceC2153b
            public boolean isDisposed() {
                return EnumC0515c.m324b(get());
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a aVar = a.this;
                aVar.f3976g.mo322a(this);
                if (!C1774f.m1958a(aVar.f3978i, th)) {
                    C1908a.m2205b(th);
                    return;
                }
                if (!aVar.f3975f) {
                    aVar.f3981l.dispose();
                    aVar.f3976g.dispose();
                }
                aVar.f3977h.decrementAndGet();
                aVar.m1557a();
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n, boolean z6) {
            this.f3974e = interfaceC2127s;
            this.f3979j = interfaceC0454n;
            this.f3975f = z6;
        }

        /* renamed from: a */
        public void m1557a() {
            if (getAndIncrement() == 0) {
                m1558b();
            }
        }

        /* renamed from: b */
        public void m1558b() {
            InterfaceC2127s<? super R> interfaceC2127s = this.f3974e;
            AtomicInteger atomicInteger = this.f3977h;
            AtomicReference<C1489c<R>> atomicReference = this.f3980k;
            int iAddAndGet = 1;
            while (!this.f3982m) {
                if (!this.f3975f && this.f3978i.get() != null) {
                    Throwable thM1959b = C1774f.m1959b(this.f3978i);
                    C1489c<R> c1489c = this.f3980k.get();
                    if (c1489c != null) {
                        c1489c.clear();
                    }
                    interfaceC2127s.onError(thM1959b);
                    return;
                }
                boolean z6 = atomicInteger.get() == 0;
                C1489c<R> c1489c2 = atomicReference.get();
                C0000a c0000aPoll = c1489c2 != null ? c1489c2.poll() : null;
                boolean z7 = c0000aPoll == null;
                if (z6 && z7) {
                    Throwable thM1959b2 = C1774f.m1959b(this.f3978i);
                    if (thM1959b2 != null) {
                        interfaceC2127s.onError(thM1959b2);
                        return;
                    } else {
                        interfaceC2127s.onComplete();
                        return;
                    }
                }
                if (z7) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    interfaceC2127s.onNext(c0000aPoll);
                }
            }
            C1489c<R> c1489c3 = this.f3980k.get();
            if (c1489c3 != null) {
                c1489c3.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3982m = true;
            this.f3981l.dispose();
            this.f3976g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3982m;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3977h.decrementAndGet();
            m1557a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3977h.decrementAndGet();
            if (!C1774f.m1958a(this.f3978i, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (!this.f3975f) {
                this.f3976g.dispose();
            }
            m1557a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            try {
                InterfaceC2131w<? extends R> interfaceC2131wApply = this.f3979j.apply(t6);
                Objects.requireNonNull(interfaceC2131wApply, "The mapper returned a null SingleSource");
                InterfaceC2131w<? extends R> interfaceC2131w = interfaceC2131wApply;
                this.f3977h.getAndIncrement();
                C2187a c2187a = new C2187a();
                if (this.f3982m || !this.f3976g.m2595b(c2187a)) {
                    return;
                }
                interfaceC2131w.mo2562b(c2187a);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3981l.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3981l, interfaceC2153b)) {
                this.f3981l = interfaceC2153b;
                this.f3974e.onSubscribe(this);
            }
        }
    }

    public C1377x0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3972f = interfaceC0454n;
        this.f3973g = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3972f, this.f3973g));
    }
}
