package p080j4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0950c;
import p040e4.InterfaceC0955h;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2110b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2112d;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableConcatMapCompletable.java */
/* renamed from: j4.a */
/* loaded from: classes.dex */
public final class C1204a<T> extends AbstractC2110b {

    /* renamed from: a */
    public final AbstractC2120l<T> f2638a;

    /* renamed from: b */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2112d> f2639b;

    /* renamed from: c */
    public final int f2640c;

    /* renamed from: d */
    public final int f2641d;

    /* compiled from: ObservableConcatMapCompletable.java */
    /* renamed from: j4.a$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 3610901111000061034L;

        /* renamed from: e */
        public final InterfaceC2111c f2642e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2112d> f2643f;

        /* renamed from: g */
        public final int f2644g;

        /* renamed from: h */
        public final C1771c f2645h = new C1771c();

        /* renamed from: i */
        public final C2164a f2646i = new C2164a(this);

        /* renamed from: j */
        public final int f2647j;

        /* renamed from: k */
        public InterfaceC0955h<T> f2648k;

        /* renamed from: l */
        public InterfaceC2153b f2649l;

        /* renamed from: m */
        public volatile boolean f2650m;

        /* renamed from: n */
        public volatile boolean f2651n;

        /* renamed from: o */
        public volatile boolean f2652o;

        /* compiled from: ObservableConcatMapCompletable.java */
        /* renamed from: j4.a$a$a, reason: collision with other inner class name */
        public static final class C2164a extends AtomicReference<InterfaceC2153b> implements InterfaceC2111c {
            private static final long serialVersionUID = 5638352172918776687L;

            /* renamed from: e */
            public final a<?> f2653e;

            public C2164a(a<?> aVar) {
                this.f2653e = aVar;
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onComplete() {
                a<?> aVar = this.f2653e;
                aVar.f2650m = false;
                aVar.m1431a();
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a<?> aVar = this.f2653e;
                if (!C1774f.m1958a(aVar.f2645h, th)) {
                    C1908a.m2205b(th);
                    return;
                }
                if (aVar.f2644g != 1) {
                    aVar.f2650m = false;
                    aVar.m1431a();
                    return;
                }
                aVar.f2652o = true;
                aVar.f2649l.dispose();
                Throwable thM1959b = C1774f.m1959b(aVar.f2645h);
                if (thM1959b != C1774f.f5055a) {
                    aVar.f2642e.onError(thM1959b);
                }
                if (aVar.getAndIncrement() == 0) {
                    aVar.f2648k.clear();
                }
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m325c(this, interfaceC2153b);
            }
        }

        /* JADX WARN: Incorrect types in method signature: (Ly3/c;Lb4/n<-TT;+Ly3/d;>;Ljava/lang/Object;I)V */
        public a(InterfaceC2111c interfaceC2111c, InterfaceC0454n interfaceC0454n, int i7, int i8) {
            this.f2642e = interfaceC2111c;
            this.f2643f = interfaceC0454n;
            this.f2644g = i7;
            this.f2647j = i8;
        }

        /* renamed from: a */
        public void m1431a() {
            boolean z6;
            if (getAndIncrement() != 0) {
                return;
            }
            C1771c c1771c = this.f2645h;
            int i7 = this.f2644g;
            while (!this.f2652o) {
                if (!this.f2650m) {
                    if (i7 == 2 && c1771c.get() != null) {
                        this.f2652o = true;
                        this.f2648k.clear();
                        this.f2642e.onError(C1774f.m1959b(c1771c));
                        return;
                    }
                    boolean z7 = this.f2651n;
                    InterfaceC2112d interfaceC2112d = null;
                    try {
                        T tPoll = this.f2648k.poll();
                        if (tPoll != null) {
                            InterfaceC2112d interfaceC2112dApply = this.f2643f.apply(tPoll);
                            Objects.requireNonNull(interfaceC2112dApply, "The mapper returned a null CompletableSource");
                            interfaceC2112d = interfaceC2112dApply;
                            z6 = false;
                        } else {
                            z6 = true;
                        }
                        if (z7 && z6) {
                            this.f2652o = true;
                            Throwable thM1959b = C1774f.m1959b(c1771c);
                            if (thM1959b != null) {
                                this.f2642e.onError(thM1959b);
                                return;
                            } else {
                                this.f2642e.onComplete();
                                return;
                            }
                        }
                        if (!z6) {
                            this.f2650m = true;
                            interfaceC2112d.mo2552b(this.f2646i);
                        }
                    } catch (Throwable th) {
                        C2074b.m2470J(th);
                        this.f2652o = true;
                        this.f2648k.clear();
                        this.f2649l.dispose();
                        C1774f.m1958a(c1771c, th);
                        this.f2642e.onError(C1774f.m1959b(c1771c));
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            this.f2648k.clear();
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2652o = true;
            this.f2649l.dispose();
            EnumC0515c.m323a(this.f2646i);
            if (getAndIncrement() == 0) {
                this.f2648k.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2652o;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2651n = true;
            m1431a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f2645h, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (this.f2644g != 1) {
                this.f2651n = true;
                m1431a();
                return;
            }
            this.f2652o = true;
            EnumC0515c.m323a(this.f2646i);
            Throwable thM1959b = C1774f.m1959b(this.f2645h);
            if (thM1959b != C1774f.f5055a) {
                this.f2642e.onError(thM1959b);
            }
            if (getAndIncrement() == 0) {
                this.f2648k.clear();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (t6 != null) {
                this.f2648k.offer(t6);
            }
            m1431a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2649l, interfaceC2153b)) {
                this.f2649l = interfaceC2153b;
                if (interfaceC2153b instanceof InterfaceC0950c) {
                    InterfaceC0950c interfaceC0950c = (InterfaceC0950c) interfaceC2153b;
                    int iMo331c = interfaceC0950c.mo331c(3);
                    if (iMo331c == 1) {
                        this.f2648k = interfaceC0950c;
                        this.f2651n = true;
                        this.f2642e.onSubscribe(this);
                        m1431a();
                        return;
                    }
                    if (iMo331c == 2) {
                        this.f2648k = interfaceC0950c;
                        this.f2642e.onSubscribe(this);
                        return;
                    }
                }
                this.f2648k = new C1489c(this.f2647j);
                this.f2642e.onSubscribe(this);
            }
        }
    }

    /* JADX WARN: Incorrect types in method signature: (Ly3/l<TT;>;Lb4/n<-TT;+Ly3/d;>;Ljava/lang/Object;I)V */
    public C1204a(AbstractC2120l abstractC2120l, InterfaceC0454n interfaceC0454n, int i7, int i8) {
        this.f2638a = abstractC2120l;
        this.f2639b = interfaceC0454n;
        this.f2640c = i7;
        this.f2641d = i8;
    }

    @Override // p194y3.AbstractC2110b
    /* renamed from: c */
    public void mo1054c(InterfaceC2111c interfaceC2111c) {
        if (C2074b.m2472L(this.f2638a, this.f2639b, interfaceC2111c)) {
            return;
        }
        this.f2638a.subscribe(new a(interfaceC2111c, this.f2639b, this.f2640c, this.f2641d));
    }
}
