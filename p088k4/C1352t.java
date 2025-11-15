package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p000a.C0000a;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0950c;
import p040e4.InterfaceC0955h;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p153s4.C1882e;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableConcatMap.java */
/* renamed from: k4.t */
/* loaded from: classes.dex */
public final class C1352t<T, U> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> f3746f;

    /* renamed from: g */
    public final int f3747g;

    /* renamed from: h */
    public final int f3748h;

    /* compiled from: ObservableConcatMap.java */
    /* renamed from: k4.t$a */
    public static final class a<T, R> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -6951100001833242599L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3749e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> f3750f;

        /* renamed from: g */
        public final int f3751g;

        /* renamed from: h */
        public final C1771c f3752h = new C1771c();

        /* renamed from: i */
        public final C2183a<R> f3753i;

        /* renamed from: j */
        public final boolean f3754j;

        /* renamed from: k */
        public InterfaceC0955h<T> f3755k;

        /* renamed from: l */
        public InterfaceC2153b f3756l;

        /* renamed from: m */
        public volatile boolean f3757m;

        /* renamed from: n */
        public volatile boolean f3758n;

        /* renamed from: o */
        public volatile boolean f3759o;

        /* renamed from: p */
        public int f3760p;

        /* compiled from: ObservableConcatMap.java */
        /* renamed from: k4.t$a$a, reason: collision with other inner class name */
        public static final class C2183a<R> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<R> {
            private static final long serialVersionUID = 2620149119579502636L;

            /* renamed from: e */
            public final InterfaceC2127s<? super R> f3761e;

            /* renamed from: f */
            public final a<?, R> f3762f;

            public C2183a(InterfaceC2127s<? super R> interfaceC2127s, a<?, R> aVar) {
                this.f3761e = interfaceC2127s;
                this.f3762f = aVar;
            }

            @Override // p194y3.InterfaceC2127s
            public void onComplete() {
                a<?, R> aVar = this.f3762f;
                aVar.f3757m = false;
                aVar.m1540a();
            }

            @Override // p194y3.InterfaceC2127s
            public void onError(Throwable th) {
                a<?, R> aVar = this.f3762f;
                if (!C1774f.m1958a(aVar.f3752h, th)) {
                    C1908a.m2205b(th);
                    return;
                }
                if (!aVar.f3754j) {
                    aVar.f3756l.dispose();
                }
                aVar.f3757m = false;
                aVar.m1540a();
            }

            @Override // p194y3.InterfaceC2127s
            public void onNext(R r6) {
                this.f3761e.onNext(r6);
            }

            @Override // p194y3.InterfaceC2127s
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m325c(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7, boolean z6) {
            this.f3749e = interfaceC2127s;
            this.f3750f = interfaceC0454n;
            this.f3751g = i7;
            this.f3754j = z6;
            this.f3753i = new C2183a<>(interfaceC2127s, this);
        }

        /* renamed from: a */
        public void m1540a() {
            if (getAndIncrement() != 0) {
                return;
            }
            InterfaceC2127s<? super R> interfaceC2127s = this.f3749e;
            InterfaceC0955h<T> interfaceC0955h = this.f3755k;
            C1771c c1771c = this.f3752h;
            while (true) {
                if (!this.f3757m) {
                    if (this.f3759o) {
                        interfaceC0955h.clear();
                        return;
                    }
                    if (!this.f3754j && c1771c.get() != null) {
                        interfaceC0955h.clear();
                        this.f3759o = true;
                        interfaceC2127s.onError(C1774f.m1959b(c1771c));
                        return;
                    }
                    boolean z6 = this.f3758n;
                    try {
                        T tPoll = interfaceC0955h.poll();
                        boolean z7 = tPoll == null;
                        if (z6 && z7) {
                            this.f3759o = true;
                            Throwable thM1959b = C1774f.m1959b(c1771c);
                            if (thM1959b != null) {
                                interfaceC2127s.onError(thM1959b);
                                return;
                            } else {
                                interfaceC2127s.onComplete();
                                return;
                            }
                        }
                        if (!z7) {
                            try {
                                InterfaceC2125q<? extends R> interfaceC2125qApply = this.f3750f.apply(tPoll);
                                Objects.requireNonNull(interfaceC2125qApply, "The mapper returned a null ObservableSource");
                                InterfaceC2125q<? extends R> interfaceC2125q = interfaceC2125qApply;
                                if (interfaceC2125q instanceof Callable) {
                                    try {
                                        C0000a c0000a = (Object) ((Callable) interfaceC2125q).call();
                                        if (c0000a != null && !this.f3759o) {
                                            interfaceC2127s.onNext(c0000a);
                                        }
                                    } catch (Throwable th) {
                                        C2074b.m2470J(th);
                                        C1774f.m1958a(c1771c, th);
                                    }
                                } else {
                                    this.f3757m = true;
                                    interfaceC2125q.subscribe(this.f3753i);
                                }
                            } catch (Throwable th2) {
                                C2074b.m2470J(th2);
                                this.f3759o = true;
                                this.f3756l.dispose();
                                interfaceC0955h.clear();
                                C1774f.m1958a(c1771c, th2);
                                interfaceC2127s.onError(C1774f.m1959b(c1771c));
                                return;
                            }
                        }
                    } catch (Throwable th3) {
                        C2074b.m2470J(th3);
                        this.f3759o = true;
                        this.f3756l.dispose();
                        C1774f.m1958a(c1771c, th3);
                        interfaceC2127s.onError(C1774f.m1959b(c1771c));
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3759o = true;
            this.f3756l.dispose();
            EnumC0515c.m323a(this.f3753i);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3759o;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3758n = true;
            m1540a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f3752h, th)) {
                C1908a.m2205b(th);
            } else {
                this.f3758n = true;
                m1540a();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3760p == 0) {
                this.f3755k.offer(t6);
            }
            m1540a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3756l, interfaceC2153b)) {
                this.f3756l = interfaceC2153b;
                if (interfaceC2153b instanceof InterfaceC0950c) {
                    InterfaceC0950c interfaceC0950c = (InterfaceC0950c) interfaceC2153b;
                    int iMo331c = interfaceC0950c.mo331c(3);
                    if (iMo331c == 1) {
                        this.f3760p = iMo331c;
                        this.f3755k = interfaceC0950c;
                        this.f3758n = true;
                        this.f3749e.onSubscribe(this);
                        m1540a();
                        return;
                    }
                    if (iMo331c == 2) {
                        this.f3760p = iMo331c;
                        this.f3755k = interfaceC0950c;
                        this.f3749e.onSubscribe(this);
                        return;
                    }
                }
                this.f3755k = new C1489c(this.f3751g);
                this.f3749e.onSubscribe(this);
            }
        }
    }

    /* compiled from: ObservableConcatMap.java */
    /* renamed from: k4.t$b */
    public static final class b<T, U> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 8828587559905699186L;

        /* renamed from: e */
        public final InterfaceC2127s<? super U> f3763e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> f3764f;

        /* renamed from: g */
        public final a<U> f3765g;

        /* renamed from: h */
        public final int f3766h;

        /* renamed from: i */
        public InterfaceC0955h<T> f3767i;

        /* renamed from: j */
        public InterfaceC2153b f3768j;

        /* renamed from: k */
        public volatile boolean f3769k;

        /* renamed from: l */
        public volatile boolean f3770l;

        /* renamed from: m */
        public volatile boolean f3771m;

        /* renamed from: n */
        public int f3772n;

        /* compiled from: ObservableConcatMap.java */
        /* renamed from: k4.t$b$a */
        public static final class a<U> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<U> {
            private static final long serialVersionUID = -7449079488798789337L;

            /* renamed from: e */
            public final InterfaceC2127s<? super U> f3773e;

            /* renamed from: f */
            public final b<?, ?> f3774f;

            public a(InterfaceC2127s<? super U> interfaceC2127s, b<?, ?> bVar) {
                this.f3773e = interfaceC2127s;
                this.f3774f = bVar;
            }

            @Override // p194y3.InterfaceC2127s
            public void onComplete() {
                b<?, ?> bVar = this.f3774f;
                bVar.f3769k = false;
                bVar.m1541a();
            }

            @Override // p194y3.InterfaceC2127s
            public void onError(Throwable th) {
                this.f3774f.dispose();
                this.f3773e.onError(th);
            }

            @Override // p194y3.InterfaceC2127s
            public void onNext(U u6) {
                this.f3773e.onNext(u6);
            }

            @Override // p194y3.InterfaceC2127s
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m325c(this, interfaceC2153b);
            }
        }

        public b(InterfaceC2127s<? super U> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> interfaceC0454n, int i7) {
            this.f3763e = interfaceC2127s;
            this.f3764f = interfaceC0454n;
            this.f3766h = i7;
            this.f3765g = new a<>(interfaceC2127s, this);
        }

        /* renamed from: a */
        public void m1541a() {
            if (getAndIncrement() != 0) {
                return;
            }
            while (!this.f3770l) {
                if (!this.f3769k) {
                    boolean z6 = this.f3771m;
                    try {
                        T tPoll = this.f3767i.poll();
                        boolean z7 = tPoll == null;
                        if (z6 && z7) {
                            this.f3770l = true;
                            this.f3763e.onComplete();
                            return;
                        }
                        if (!z7) {
                            try {
                                InterfaceC2125q<? extends U> interfaceC2125qApply = this.f3764f.apply(tPoll);
                                Objects.requireNonNull(interfaceC2125qApply, "The mapper returned a null ObservableSource");
                                InterfaceC2125q<? extends U> interfaceC2125q = interfaceC2125qApply;
                                this.f3769k = true;
                                interfaceC2125q.subscribe(this.f3765g);
                            } catch (Throwable th) {
                                C2074b.m2470J(th);
                                dispose();
                                this.f3767i.clear();
                                this.f3763e.onError(th);
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        C2074b.m2470J(th2);
                        dispose();
                        this.f3767i.clear();
                        this.f3763e.onError(th2);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            this.f3767i.clear();
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3770l = true;
            EnumC0515c.m323a(this.f3765g);
            this.f3768j.dispose();
            if (getAndIncrement() == 0) {
                this.f3767i.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3770l;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3771m) {
                return;
            }
            this.f3771m = true;
            m1541a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3771m) {
                C1908a.m2205b(th);
                return;
            }
            this.f3771m = true;
            dispose();
            this.f3763e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3771m) {
                return;
            }
            if (this.f3772n == 0) {
                this.f3767i.offer(t6);
            }
            m1541a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3768j, interfaceC2153b)) {
                this.f3768j = interfaceC2153b;
                if (interfaceC2153b instanceof InterfaceC0950c) {
                    InterfaceC0950c interfaceC0950c = (InterfaceC0950c) interfaceC2153b;
                    int iMo331c = interfaceC0950c.mo331c(3);
                    if (iMo331c == 1) {
                        this.f3772n = iMo331c;
                        this.f3767i = interfaceC0950c;
                        this.f3771m = true;
                        this.f3763e.onSubscribe(this);
                        m1541a();
                        return;
                    }
                    if (iMo331c == 2) {
                        this.f3772n = iMo331c;
                        this.f3767i = interfaceC0950c;
                        this.f3763e.onSubscribe(this);
                        return;
                    }
                }
                this.f3767i = new C1489c(this.f3766h);
                this.f3763e.onSubscribe(this);
            }
        }
    }

    /* JADX WARN: Incorrect types in method signature: (Ly3/q<TT;>;Lb4/n<-TT;+Ly3/q<+TU;>;>;ILjava/lang/Object;)V */
    public C1352t(InterfaceC2125q interfaceC2125q, InterfaceC0454n interfaceC0454n, int i7, int i8) {
        super(interfaceC2125q);
        this.f3746f = interfaceC0454n;
        this.f3748h = i8;
        this.f3747g = Math.max(8, i7);
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        if (C1284h3.m1507a(this.f2772e, interfaceC2127s, this.f3746f)) {
            return;
        }
        if (this.f3748h == 1) {
            this.f2772e.subscribe(new b(new C1882e(interfaceC2127s), this.f3746f, this.f3747g));
        } else {
            this.f2772e.subscribe(new a(interfaceC2127s, this.f3746f, this.f3747g, this.f3748h == 3));
        }
    }
}
