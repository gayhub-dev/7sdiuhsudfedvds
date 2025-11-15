package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p104m4.C1487a;
import p138q4.C1771c;
import p138q4.C1774f;
import p153s4.AbstractC1880c;
import p160t4.C1908a;
import p181w4.C2033e;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableWindowBoundarySupplier.java */
/* renamed from: k4.r4 */
/* loaded from: classes.dex */
public final class C1345r4<T, B> extends AbstractC1238a<T, AbstractC2120l<T>> {

    /* renamed from: f */
    public final Callable<? extends InterfaceC2125q<B>> f3649f;

    /* renamed from: g */
    public final int f3650g;

    /* compiled from: ObservableWindowBoundarySupplier.java */
    /* renamed from: k4.r4$a */
    public static final class a<T, B> extends AbstractC1880c<B> {

        /* renamed from: e */
        public final b<T, B> f3651e;

        /* renamed from: f */
        public boolean f3652f;

        public a(b<T, B> bVar) {
            this.f3651e = bVar;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3652f) {
                return;
            }
            this.f3652f = true;
            b<T, B> bVar = this.f3651e;
            bVar.f3663m.dispose();
            bVar.f3664n = true;
            bVar.m1532b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3652f) {
                C1908a.m2205b(th);
                return;
            }
            this.f3652f = true;
            b<T, B> bVar = this.f3651e;
            bVar.f3663m.dispose();
            if (!C1774f.m1958a(bVar.f3660j, th)) {
                C1908a.m2205b(th);
            } else {
                bVar.f3664n = true;
                bVar.m1532b();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(B b7) {
            if (this.f3652f) {
                return;
            }
            this.f3652f = true;
            dispose();
            b<T, B> bVar = this.f3651e;
            bVar.f3657g.compareAndSet(this, null);
            bVar.f3659i.offer(b.f3654q);
            bVar.m1532b();
        }
    }

    /* compiled from: ObservableWindowBoundarySupplier.java */
    /* renamed from: k4.r4$b */
    public static final class b<T, B> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b, Runnable {

        /* renamed from: p */
        public static final a<Object, Object> f3653p = new a<>(null);

        /* renamed from: q */
        public static final Object f3654q = new Object();
        private static final long serialVersionUID = 2233020065421370272L;

        /* renamed from: e */
        public final InterfaceC2127s<? super AbstractC2120l<T>> f3655e;

        /* renamed from: f */
        public final int f3656f;

        /* renamed from: g */
        public final AtomicReference<a<T, B>> f3657g = new AtomicReference<>();

        /* renamed from: h */
        public final AtomicInteger f3658h = new AtomicInteger(1);

        /* renamed from: i */
        public final C1487a<Object> f3659i = new C1487a<>();

        /* renamed from: j */
        public final C1771c f3660j = new C1771c();

        /* renamed from: k */
        public final AtomicBoolean f3661k = new AtomicBoolean();

        /* renamed from: l */
        public final Callable<? extends InterfaceC2125q<B>> f3662l;

        /* renamed from: m */
        public InterfaceC2153b f3663m;

        /* renamed from: n */
        public volatile boolean f3664n;

        /* renamed from: o */
        public C2033e<T> f3665o;

        public b(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s, int i7, Callable<? extends InterfaceC2125q<B>> callable) {
            this.f3655e = interfaceC2127s;
            this.f3656f = i7;
            this.f3662l = callable;
        }

        /* renamed from: a */
        public void m1531a() {
            AtomicReference<a<T, B>> atomicReference = this.f3657g;
            a<Object, Object> aVar = f3653p;
            InterfaceC2153b interfaceC2153b = (InterfaceC2153b) atomicReference.getAndSet(aVar);
            if (interfaceC2153b == null || interfaceC2153b == aVar) {
                return;
            }
            interfaceC2153b.dispose();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: b */
        public void m1532b() {
            if (getAndIncrement() != 0) {
                return;
            }
            InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s = this.f3655e;
            C1487a<Object> c1487a = this.f3659i;
            C1771c c1771c = this.f3660j;
            int iAddAndGet = 1;
            while (this.f3658h.get() != 0) {
                C2033e<T> c2033e = this.f3665o;
                boolean z6 = this.f3664n;
                if (z6 && c1771c.get() != null) {
                    c1487a.clear();
                    Throwable thM1959b = C1774f.m1959b(c1771c);
                    if (c2033e != 0) {
                        this.f3665o = null;
                        c2033e.onError(thM1959b);
                    }
                    interfaceC2127s.onError(thM1959b);
                    return;
                }
                Object objPoll = c1487a.poll();
                boolean z7 = objPoll == null;
                if (z6 && z7) {
                    Throwable thM1959b2 = C1774f.m1959b(c1771c);
                    if (thM1959b2 == null) {
                        if (c2033e != 0) {
                            this.f3665o = null;
                            c2033e.onComplete();
                        }
                        interfaceC2127s.onComplete();
                        return;
                    }
                    if (c2033e != 0) {
                        this.f3665o = null;
                        c2033e.onError(thM1959b2);
                    }
                    interfaceC2127s.onError(thM1959b2);
                    return;
                }
                if (z7) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else if (objPoll != f3654q) {
                    c2033e.onNext(objPoll);
                } else {
                    if (c2033e != 0) {
                        this.f3665o = null;
                        c2033e.onComplete();
                    }
                    if (!this.f3661k.get()) {
                        C2033e<T> c2033eM2388c = C2033e.m2388c(this.f3656f, this);
                        this.f3665o = c2033eM2388c;
                        this.f3658h.getAndIncrement();
                        try {
                            InterfaceC2125q<B> interfaceC2125qCall = this.f3662l.call();
                            Objects.requireNonNull(interfaceC2125qCall, "The other Callable returned a null ObservableSource");
                            InterfaceC2125q<B> interfaceC2125q = interfaceC2125qCall;
                            a<T, B> aVar = new a<>(this);
                            if (this.f3657g.compareAndSet(null, aVar)) {
                                interfaceC2125q.subscribe(aVar);
                                interfaceC2127s.onNext(c2033eM2388c);
                            }
                        } catch (Throwable th) {
                            C2074b.m2470J(th);
                            C1774f.m1958a(c1771c, th);
                            this.f3664n = true;
                        }
                    }
                }
            }
            c1487a.clear();
            this.f3665o = null;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3661k.compareAndSet(false, true)) {
                m1531a();
                if (this.f3658h.decrementAndGet() == 0) {
                    this.f3663m.dispose();
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3661k.get();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            m1531a();
            this.f3664n = true;
            m1532b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            m1531a();
            if (!C1774f.m1958a(this.f3660j, th)) {
                C1908a.m2205b(th);
            } else {
                this.f3664n = true;
                m1532b();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3659i.offer(t6);
            m1532b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3663m, interfaceC2153b)) {
                this.f3663m = interfaceC2153b;
                this.f3655e.onSubscribe(this);
                this.f3659i.offer(f3654q);
                m1532b();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3658h.decrementAndGet() == 0) {
                this.f3663m.dispose();
            }
        }
    }

    public C1345r4(InterfaceC2125q<T> interfaceC2125q, Callable<? extends InterfaceC2125q<B>> callable, int i7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3649f = callable;
        this.f3650g = i7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s) {
        this.f2772e.subscribe(new b(interfaceC2127s, this.f3650g, this.f3649f));
    }
}
