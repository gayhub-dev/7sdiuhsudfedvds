package p088k4;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0950c;
import p040e4.InterfaceC0955h;
import p048f4.C1008m;
import p048f4.InterfaceC1009n;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableConcatMapEager.java */
/* renamed from: k4.u */
/* loaded from: classes.dex */
public final class C1358u<T, R> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> f3821f;

    /* renamed from: g */
    public final int f3822g;

    /* renamed from: h */
    public final int f3823h;

    /* renamed from: i */
    public final int f3824i;

    /* compiled from: ObservableConcatMapEager.java */
    /* renamed from: k4.u$a */
    public static final class a<T, R> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b, InterfaceC1009n<R> {
        private static final long serialVersionUID = 8080567949447303262L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3825e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> f3826f;

        /* renamed from: g */
        public final int f3827g;

        /* renamed from: h */
        public final int f3828h;

        /* renamed from: i */
        public final int f3829i;

        /* renamed from: j */
        public final C1771c f3830j = new C1771c();

        /* renamed from: k */
        public final ArrayDeque<C1008m<R>> f3831k = new ArrayDeque<>();

        /* renamed from: l */
        public InterfaceC0955h<T> f3832l;

        /* renamed from: m */
        public InterfaceC2153b f3833m;

        /* renamed from: n */
        public volatile boolean f3834n;

        /* renamed from: o */
        public int f3835o;

        /* renamed from: p */
        public volatile boolean f3836p;

        /* renamed from: q */
        public C1008m<R> f3837q;

        /* renamed from: r */
        public int f3838r;

        /* JADX WARN: Incorrect types in method signature: (Ly3/s<-TR;>;Lb4/n<-TT;+Ly3/q<+TR;>;>;IILjava/lang/Object;)V */
        public a(InterfaceC2127s interfaceC2127s, InterfaceC0454n interfaceC0454n, int i7, int i8, int i9) {
            this.f3825e = interfaceC2127s;
            this.f3826f = interfaceC0454n;
            this.f3827g = i7;
            this.f3828h = i8;
            this.f3829i = i9;
        }

        /* renamed from: a */
        public void m1548a() {
            C1008m<R> c1008m = this.f3837q;
            if (c1008m != null) {
                EnumC0515c.m323a(c1008m);
            }
            while (true) {
                C1008m<R> c1008mPoll = this.f3831k.poll();
                if (c1008mPoll == null) {
                    return;
                } else {
                    EnumC0515c.m323a(c1008mPoll);
                }
            }
        }

        /* renamed from: b */
        public void m1549b() {
            R rPoll;
            boolean z6;
            if (getAndIncrement() != 0) {
                return;
            }
            InterfaceC0955h<T> interfaceC0955h = this.f3832l;
            ArrayDeque<C1008m<R>> arrayDeque = this.f3831k;
            InterfaceC2127s<? super R> interfaceC2127s = this.f3825e;
            int i7 = this.f3829i;
            int iAddAndGet = 1;
            while (true) {
                int i8 = this.f3838r;
                while (i8 != this.f3827g) {
                    if (this.f3836p) {
                        interfaceC0955h.clear();
                        m1548a();
                        return;
                    }
                    if (i7 == 1 && this.f3830j.get() != null) {
                        interfaceC0955h.clear();
                        m1548a();
                        interfaceC2127s.onError(C1774f.m1959b(this.f3830j));
                        return;
                    }
                    try {
                        T tPoll = interfaceC0955h.poll();
                        if (tPoll == null) {
                            break;
                        }
                        InterfaceC2125q<? extends R> interfaceC2125qApply = this.f3826f.apply(tPoll);
                        Objects.requireNonNull(interfaceC2125qApply, "The mapper returned a null ObservableSource");
                        InterfaceC2125q<? extends R> interfaceC2125q = interfaceC2125qApply;
                        C1008m<R> c1008m = new C1008m<>(this, this.f3828h);
                        arrayDeque.offer(c1008m);
                        interfaceC2125q.subscribe(c1008m);
                        i8++;
                    } catch (Throwable th) {
                        C2074b.m2470J(th);
                        this.f3833m.dispose();
                        interfaceC0955h.clear();
                        m1548a();
                        C1774f.m1958a(this.f3830j, th);
                        interfaceC2127s.onError(C1774f.m1959b(this.f3830j));
                        return;
                    }
                }
                this.f3838r = i8;
                if (this.f3836p) {
                    interfaceC0955h.clear();
                    m1548a();
                    return;
                }
                if (i7 == 1 && this.f3830j.get() != null) {
                    interfaceC0955h.clear();
                    m1548a();
                    interfaceC2127s.onError(C1774f.m1959b(this.f3830j));
                    return;
                }
                C1008m<R> c1008m2 = this.f3837q;
                if (c1008m2 == null) {
                    if (i7 == 2 && this.f3830j.get() != null) {
                        interfaceC0955h.clear();
                        m1548a();
                        interfaceC2127s.onError(C1774f.m1959b(this.f3830j));
                        return;
                    }
                    boolean z7 = this.f3834n;
                    C1008m<R> c1008mPoll = arrayDeque.poll();
                    boolean z8 = c1008mPoll == null;
                    if (z7 && z8) {
                        if (this.f3830j.get() == null) {
                            interfaceC2127s.onComplete();
                            return;
                        }
                        interfaceC0955h.clear();
                        m1548a();
                        interfaceC2127s.onError(C1774f.m1959b(this.f3830j));
                        return;
                    }
                    if (!z8) {
                        this.f3837q = c1008mPoll;
                    }
                    c1008m2 = c1008mPoll;
                }
                if (c1008m2 != null) {
                    InterfaceC0955h<R> interfaceC0955h2 = c1008m2.f1898g;
                    while (!this.f3836p) {
                        boolean z9 = c1008m2.f1899h;
                        if (i7 == 1 && this.f3830j.get() != null) {
                            interfaceC0955h.clear();
                            m1548a();
                            interfaceC2127s.onError(C1774f.m1959b(this.f3830j));
                            return;
                        }
                        try {
                            rPoll = interfaceC0955h2.poll();
                            z6 = rPoll == null;
                        } catch (Throwable th2) {
                            C2074b.m2470J(th2);
                            C1774f.m1958a(this.f3830j, th2);
                            this.f3837q = null;
                            this.f3838r--;
                        }
                        if (z9 && z6) {
                            this.f3837q = null;
                            this.f3838r--;
                        } else if (!z6) {
                            interfaceC2127s.onNext(rPoll);
                        }
                    }
                    interfaceC0955h.clear();
                    m1548a();
                    return;
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3836p) {
                return;
            }
            this.f3836p = true;
            this.f3833m.dispose();
            if (getAndIncrement() == 0) {
                do {
                    this.f3832l.clear();
                    m1548a();
                } while (decrementAndGet() != 0);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3836p;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3834n = true;
            m1549b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f3830j, th)) {
                C1908a.m2205b(th);
            } else {
                this.f3834n = true;
                m1549b();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3835o == 0) {
                this.f3832l.offer(t6);
            }
            m1549b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3833m, interfaceC2153b)) {
                this.f3833m = interfaceC2153b;
                if (interfaceC2153b instanceof InterfaceC0950c) {
                    InterfaceC0950c interfaceC0950c = (InterfaceC0950c) interfaceC2153b;
                    int iMo331c = interfaceC0950c.mo331c(3);
                    if (iMo331c == 1) {
                        this.f3835o = iMo331c;
                        this.f3832l = interfaceC0950c;
                        this.f3834n = true;
                        this.f3825e.onSubscribe(this);
                        m1549b();
                        return;
                    }
                    if (iMo331c == 2) {
                        this.f3835o = iMo331c;
                        this.f3832l = interfaceC0950c;
                        this.f3825e.onSubscribe(this);
                        return;
                    }
                }
                this.f3832l = new C1489c(this.f3828h);
                this.f3825e.onSubscribe(this);
            }
        }
    }

    /* JADX WARN: Incorrect types in method signature: (Ly3/q<TT;>;Lb4/n<-TT;+Ly3/q<+TR;>;>;Ljava/lang/Object;II)V */
    public C1358u(InterfaceC2125q interfaceC2125q, InterfaceC0454n interfaceC0454n, int i7, int i8, int i9) {
        super(interfaceC2125q);
        this.f3821f = interfaceC0454n;
        this.f3822g = i7;
        this.f3823h = i8;
        this.f3824i = i9;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3821f, this.f3823h, this.f3824i, this.f3822g));
    }
}
