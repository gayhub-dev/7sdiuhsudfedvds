package p088k4;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0443c;
import p014b4.InterfaceC0454n;
import p088k4.C1276g1;
import p104m4.C1489c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableJoin.java */
/* renamed from: k4.b2 */
/* loaded from: classes.dex */
public final class C1247b2<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractC1238a<TLeft, R> {

    /* renamed from: f */
    public final InterfaceC2125q<? extends TRight> f2833f;

    /* renamed from: g */
    public final InterfaceC0454n<? super TLeft, ? extends InterfaceC2125q<TLeftEnd>> f2834g;

    /* renamed from: h */
    public final InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> f2835h;

    /* renamed from: i */
    public final InterfaceC0443c<? super TLeft, ? super TRight, ? extends R> f2836i;

    /* compiled from: ObservableJoin.java */
    /* renamed from: k4.b2$a */
    public static final class a<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements InterfaceC2153b, C1276g1.b {
        private static final long serialVersionUID = -6071216598687999801L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f2841e;

        /* renamed from: k */
        public final InterfaceC0454n<? super TLeft, ? extends InterfaceC2125q<TLeftEnd>> f2847k;

        /* renamed from: l */
        public final InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> f2848l;

        /* renamed from: m */
        public final InterfaceC0443c<? super TLeft, ? super TRight, ? extends R> f2849m;

        /* renamed from: o */
        public int f2851o;

        /* renamed from: p */
        public int f2852p;

        /* renamed from: q */
        public volatile boolean f2853q;

        /* renamed from: r */
        public static final Integer f2837r = 1;

        /* renamed from: s */
        public static final Integer f2838s = 2;

        /* renamed from: t */
        public static final Integer f2839t = 3;

        /* renamed from: u */
        public static final Integer f2840u = 4;

        /* renamed from: g */
        public final C2152a f2843g = new C2152a(0);

        /* renamed from: f */
        public final C1489c<Object> f2842f = new C1489c<>(AbstractC2120l.bufferSize());

        /* renamed from: h */
        public final Map<Integer, TLeft> f2844h = new LinkedHashMap();

        /* renamed from: i */
        public final Map<Integer, TRight> f2845i = new LinkedHashMap();

        /* renamed from: j */
        public final AtomicReference<Throwable> f2846j = new AtomicReference<>();

        /* renamed from: n */
        public final AtomicInteger f2850n = new AtomicInteger(2);

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super TLeft, ? extends InterfaceC2125q<TLeftEnd>> interfaceC0454n, InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> interfaceC0454n2, InterfaceC0443c<? super TLeft, ? super TRight, ? extends R> interfaceC0443c) {
            this.f2841e = interfaceC2127s;
            this.f2847k = interfaceC0454n;
            this.f2848l = interfaceC0454n2;
            this.f2849m = interfaceC0443c;
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: a */
        public void mo1466a(boolean z6, C1276g1.c cVar) {
            synchronized (this) {
                this.f2842f.m1649d(z6 ? f2839t : f2840u, cVar);
            }
            m1471f();
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: b */
        public void mo1467b(Throwable th) {
            if (C1774f.m1958a(this.f2846j, th)) {
                m1471f();
            } else {
                C1908a.m2205b(th);
            }
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: c */
        public void mo1468c(boolean z6, Object obj) {
            synchronized (this) {
                this.f2842f.m1649d(z6 ? f2837r : f2838s, obj);
            }
            m1471f();
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: d */
        public void mo1469d(Throwable th) {
            if (!C1774f.m1958a(this.f2846j, th)) {
                C1908a.m2205b(th);
            } else {
                this.f2850n.decrementAndGet();
                m1471f();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f2853q) {
                return;
            }
            this.f2853q = true;
            this.f2843g.dispose();
            if (getAndIncrement() == 0) {
                this.f2842f.clear();
            }
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: e */
        public void mo1470e(C1276g1.d dVar) {
            this.f2843g.mo322a(dVar);
            this.f2850n.decrementAndGet();
            m1471f();
        }

        /* renamed from: f */
        public void m1471f() {
            if (getAndIncrement() != 0) {
                return;
            }
            C1489c<?> c1489c = this.f2842f;
            InterfaceC2127s<? super R> interfaceC2127s = this.f2841e;
            int iAddAndGet = 1;
            while (!this.f2853q) {
                if (this.f2846j.get() != null) {
                    c1489c.clear();
                    this.f2843g.dispose();
                    m1472g(interfaceC2127s);
                    return;
                }
                boolean z6 = this.f2850n.get() == 0;
                Integer num = (Integer) c1489c.poll();
                boolean z7 = num == null;
                if (z6 && z7) {
                    this.f2844h.clear();
                    this.f2845i.clear();
                    this.f2843g.dispose();
                    interfaceC2127s.onComplete();
                    return;
                }
                if (z7) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    Object objPoll = c1489c.poll();
                    if (num == f2837r) {
                        int i7 = this.f2851o;
                        this.f2851o = i7 + 1;
                        this.f2844h.put(Integer.valueOf(i7), objPoll);
                        try {
                            InterfaceC2125q interfaceC2125qApply = this.f2847k.apply(objPoll);
                            Objects.requireNonNull(interfaceC2125qApply, "The leftEnd returned a null ObservableSource");
                            InterfaceC2125q interfaceC2125q = interfaceC2125qApply;
                            C1276g1.c cVar = new C1276g1.c(this, true, i7);
                            this.f2843g.m2595b(cVar);
                            interfaceC2125q.subscribe(cVar);
                            if (this.f2846j.get() != null) {
                                c1489c.clear();
                                this.f2843g.dispose();
                                m1472g(interfaceC2127s);
                                return;
                            }
                            Iterator<TRight> it = this.f2845i.values().iterator();
                            while (it.hasNext()) {
                                try {
                                    R rApply = this.f2849m.apply(objPoll, it.next());
                                    Objects.requireNonNull(rApply, "The resultSelector returned a null value");
                                    interfaceC2127s.onNext(rApply);
                                } catch (Throwable th) {
                                    m1473h(th, interfaceC2127s, c1489c);
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            m1473h(th2, interfaceC2127s, c1489c);
                            return;
                        }
                    } else if (num == f2838s) {
                        int i8 = this.f2852p;
                        this.f2852p = i8 + 1;
                        this.f2845i.put(Integer.valueOf(i8), objPoll);
                        try {
                            InterfaceC2125q interfaceC2125qApply2 = this.f2848l.apply(objPoll);
                            Objects.requireNonNull(interfaceC2125qApply2, "The rightEnd returned a null ObservableSource");
                            InterfaceC2125q interfaceC2125q2 = interfaceC2125qApply2;
                            C1276g1.c cVar2 = new C1276g1.c(this, false, i8);
                            this.f2843g.m2595b(cVar2);
                            interfaceC2125q2.subscribe(cVar2);
                            if (this.f2846j.get() != null) {
                                c1489c.clear();
                                this.f2843g.dispose();
                                m1472g(interfaceC2127s);
                                return;
                            }
                            Iterator<TLeft> it2 = this.f2844h.values().iterator();
                            while (it2.hasNext()) {
                                try {
                                    R rApply2 = this.f2849m.apply(it2.next(), objPoll);
                                    Objects.requireNonNull(rApply2, "The resultSelector returned a null value");
                                    interfaceC2127s.onNext(rApply2);
                                } catch (Throwable th3) {
                                    m1473h(th3, interfaceC2127s, c1489c);
                                    return;
                                }
                            }
                        } catch (Throwable th4) {
                            m1473h(th4, interfaceC2127s, c1489c);
                            return;
                        }
                    } else if (num == f2839t) {
                        C1276g1.c cVar3 = (C1276g1.c) objPoll;
                        this.f2844h.remove(Integer.valueOf(cVar3.f3117g));
                        this.f2843g.m2596c(cVar3);
                    } else {
                        C1276g1.c cVar4 = (C1276g1.c) objPoll;
                        this.f2845i.remove(Integer.valueOf(cVar4.f3117g));
                        this.f2843g.m2596c(cVar4);
                    }
                }
            }
            c1489c.clear();
        }

        /* renamed from: g */
        public void m1472g(InterfaceC2127s<?> interfaceC2127s) {
            Throwable thM1959b = C1774f.m1959b(this.f2846j);
            this.f2844h.clear();
            this.f2845i.clear();
            interfaceC2127s.onError(thM1959b);
        }

        /* renamed from: h */
        public void m1473h(Throwable th, InterfaceC2127s<?> interfaceC2127s, C1489c<?> c1489c) {
            C2074b.m2470J(th);
            C1774f.m1958a(this.f2846j, th);
            c1489c.clear();
            this.f2843g.dispose();
            m1472g(interfaceC2127s);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2853q;
        }
    }

    public C1247b2(InterfaceC2125q<TLeft> interfaceC2125q, InterfaceC2125q<? extends TRight> interfaceC2125q2, InterfaceC0454n<? super TLeft, ? extends InterfaceC2125q<TLeftEnd>> interfaceC0454n, InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> interfaceC0454n2, InterfaceC0443c<? super TLeft, ? super TRight, ? extends R> interfaceC0443c) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f2833f = interfaceC2125q2;
        this.f2834g = interfaceC0454n;
        this.f2835h = interfaceC0454n2;
        this.f2836i = interfaceC0443c;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        a aVar = new a(interfaceC2127s, this.f2834g, this.f2835h, this.f2836i);
        interfaceC2127s.onSubscribe(aVar);
        C1276g1.d dVar = new C1276g1.d(aVar, true);
        aVar.f2843g.m2595b(dVar);
        C1276g1.d dVar2 = new C1276g1.d(aVar, false);
        aVar.f2843g.m2595b(dVar2);
        this.f2772e.subscribe(dVar);
        this.f2833f.subscribe(dVar2);
    }
}
