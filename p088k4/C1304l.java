package p088k4;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableBufferBoundary.java */
/* renamed from: k4.l */
/* loaded from: classes.dex */
public final class C1304l<T, U extends Collection<? super T>, Open, Close> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final Callable<U> f3306f;

    /* renamed from: g */
    public final InterfaceC2125q<? extends Open> f3307g;

    /* renamed from: h */
    public final InterfaceC0454n<? super Open, ? extends InterfaceC2125q<? extends Close>> f3308h;

    /* compiled from: ObservableBufferBoundary.java */
    /* renamed from: k4.l$a */
    public static final class a<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -8466418554264089604L;

        /* renamed from: e */
        public final InterfaceC2127s<? super C> f3309e;

        /* renamed from: f */
        public final Callable<C> f3310f;

        /* renamed from: g */
        public final InterfaceC2125q<? extends Open> f3311g;

        /* renamed from: h */
        public final InterfaceC0454n<? super Open, ? extends InterfaceC2125q<? extends Close>> f3312h;

        /* renamed from: l */
        public volatile boolean f3316l;

        /* renamed from: n */
        public volatile boolean f3318n;

        /* renamed from: o */
        public long f3319o;

        /* renamed from: m */
        public final C1489c<C> f3317m = new C1489c<>(AbstractC2120l.bufferSize());

        /* renamed from: i */
        public final C2152a f3313i = new C2152a(0);

        /* renamed from: j */
        public final AtomicReference<InterfaceC2153b> f3314j = new AtomicReference<>();

        /* renamed from: p */
        public Map<Long, C> f3320p = new LinkedHashMap();

        /* renamed from: k */
        public final C1771c f3315k = new C1771c();

        /* compiled from: ObservableBufferBoundary.java */
        /* renamed from: k4.l$a$a, reason: collision with other inner class name */
        public static final class C2179a<Open> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<Open>, InterfaceC2153b {
            private static final long serialVersionUID = -8498650778633225126L;

            /* renamed from: e */
            public final a<?, ?, Open, ?> f3321e;

            public C2179a(a<?, ?, Open, ?> aVar) {
                this.f3321e = aVar;
            }

            @Override // p201z3.InterfaceC2153b
            public void dispose() {
                EnumC0515c.m323a(this);
            }

            @Override // p201z3.InterfaceC2153b
            public boolean isDisposed() {
                return get() == EnumC0515c.DISPOSED;
            }

            @Override // p194y3.InterfaceC2127s
            public void onComplete() {
                lazySet(EnumC0515c.DISPOSED);
                a<?, ?, Open, ?> aVar = this.f3321e;
                aVar.f3313i.mo322a(this);
                if (aVar.f3313i.m2597d() == 0) {
                    EnumC0515c.m323a(aVar.f3314j);
                    aVar.f3316l = true;
                    aVar.m1516b();
                }
            }

            @Override // p194y3.InterfaceC2127s
            public void onError(Throwable th) {
                lazySet(EnumC0515c.DISPOSED);
                a<?, ?, Open, ?> aVar = this.f3321e;
                EnumC0515c.m323a(aVar.f3314j);
                aVar.f3313i.mo322a(this);
                aVar.onError(th);
            }

            @Override // p194y3.InterfaceC2127s
            public void onNext(Open open) {
                a<?, ?, Open, ?> aVar = this.f3321e;
                Objects.requireNonNull(aVar);
                try {
                    C cCall = aVar.f3310f.call();
                    Objects.requireNonNull(cCall, "The bufferSupplier returned a null Collection");
                    C c7 = cCall;
                    InterfaceC2125q<? extends Object> interfaceC2125qApply = aVar.f3312h.apply(open);
                    Objects.requireNonNull(interfaceC2125qApply, "The bufferClose returned a null ObservableSource");
                    InterfaceC2125q<? extends Object> interfaceC2125q = interfaceC2125qApply;
                    long j7 = aVar.f3319o;
                    aVar.f3319o = 1 + j7;
                    synchronized (aVar) {
                        Map<Long, C> map = aVar.f3320p;
                        if (map != null) {
                            map.put(Long.valueOf(j7), c7);
                            b bVar = new b(aVar, j7);
                            aVar.f3313i.m2595b(bVar);
                            interfaceC2125q.subscribe(bVar);
                        }
                    }
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    EnumC0515c.m323a(aVar.f3314j);
                    aVar.onError(th);
                }
            }

            @Override // p194y3.InterfaceC2127s
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super C> interfaceC2127s, InterfaceC2125q<? extends Open> interfaceC2125q, InterfaceC0454n<? super Open, ? extends InterfaceC2125q<? extends Close>> interfaceC0454n, Callable<C> callable) {
            this.f3309e = interfaceC2127s;
            this.f3310f = callable;
            this.f3311g = interfaceC2125q;
            this.f3312h = interfaceC0454n;
        }

        /* renamed from: a */
        public void m1515a(b<T, C> bVar, long j7) {
            boolean z6;
            this.f3313i.mo322a(bVar);
            if (this.f3313i.m2597d() == 0) {
                EnumC0515c.m323a(this.f3314j);
                z6 = true;
            } else {
                z6 = false;
            }
            synchronized (this) {
                Map<Long, C> map = this.f3320p;
                if (map == null) {
                    return;
                }
                this.f3317m.offer(map.remove(Long.valueOf(j7)));
                if (z6) {
                    this.f3316l = true;
                }
                m1516b();
            }
        }

        /* renamed from: b */
        public void m1516b() {
            if (getAndIncrement() != 0) {
                return;
            }
            InterfaceC2127s<? super C> interfaceC2127s = this.f3309e;
            C1489c<C> c1489c = this.f3317m;
            int iAddAndGet = 1;
            while (!this.f3318n) {
                boolean z6 = this.f3316l;
                if (z6 && this.f3315k.get() != null) {
                    c1489c.clear();
                    interfaceC2127s.onError(C1774f.m1959b(this.f3315k));
                    return;
                }
                C cPoll = c1489c.poll();
                boolean z7 = cPoll == null;
                if (z6 && z7) {
                    interfaceC2127s.onComplete();
                    return;
                } else if (z7) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    interfaceC2127s.onNext(cPoll);
                }
            }
            c1489c.clear();
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (EnumC0515c.m323a(this.f3314j)) {
                this.f3318n = true;
                this.f3313i.dispose();
                synchronized (this) {
                    this.f3320p = null;
                }
                if (getAndIncrement() != 0) {
                    this.f3317m.clear();
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f3314j.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3313i.dispose();
            synchronized (this) {
                Map<Long, C> map = this.f3320p;
                if (map == null) {
                    return;
                }
                Iterator<C> it = map.values().iterator();
                while (it.hasNext()) {
                    this.f3317m.offer(it.next());
                }
                this.f3320p = null;
                this.f3316l = true;
                m1516b();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f3315k, th)) {
                C1908a.m2205b(th);
                return;
            }
            this.f3313i.dispose();
            synchronized (this) {
                this.f3320p = null;
            }
            this.f3316l = true;
            m1516b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            synchronized (this) {
                Map<Long, C> map = this.f3320p;
                if (map == null) {
                    return;
                }
                Iterator<C> it = map.values().iterator();
                while (it.hasNext()) {
                    it.next().add(t6);
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m327f(this.f3314j, interfaceC2153b)) {
                C2179a c2179a = new C2179a(this);
                this.f3313i.m2595b(c2179a);
                this.f3311g.subscribe(c2179a);
            }
        }
    }

    /* compiled from: ObservableBufferBoundary.java */
    /* renamed from: k4.l$b */
    public static final class b<T, C extends Collection<? super T>> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<Object>, InterfaceC2153b {
        private static final long serialVersionUID = -8498650778633225126L;

        /* renamed from: e */
        public final a<T, C, ?, ?> f3322e;

        /* renamed from: f */
        public final long f3323f;

        public b(a<T, C, ?, ?> aVar, long j7) {
            this.f3322e = aVar;
            this.f3323f = j7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get() == EnumC0515c.DISPOSED;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            InterfaceC2153b interfaceC2153b = get();
            EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
            if (interfaceC2153b != enumC0515c) {
                lazySet(enumC0515c);
                this.f3322e.m1515a(this, this.f3323f);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            InterfaceC2153b interfaceC2153b = get();
            EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
            if (interfaceC2153b == enumC0515c) {
                C1908a.m2205b(th);
                return;
            }
            lazySet(enumC0515c);
            a<T, C, ?, ?> aVar = this.f3322e;
            EnumC0515c.m323a(aVar.f3314j);
            aVar.f3313i.mo322a(this);
            aVar.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            InterfaceC2153b interfaceC2153b = get();
            EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
            if (interfaceC2153b != enumC0515c) {
                lazySet(enumC0515c);
                interfaceC2153b.dispose();
                this.f3322e.m1515a(this, this.f3323f);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this, interfaceC2153b);
        }
    }

    public C1304l(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<? extends Open> interfaceC2125q2, InterfaceC0454n<? super Open, ? extends InterfaceC2125q<? extends Close>> interfaceC0454n, Callable<U> callable) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3307g = interfaceC2125q2;
        this.f3308h = interfaceC0454n;
        this.f3306f = callable;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        a aVar = new a(interfaceC2127s, this.f3307g, this.f3308h, this.f3306f);
        interfaceC2127s.onSubscribe(aVar);
        this.f2772e.subscribe(aVar);
    }
}
