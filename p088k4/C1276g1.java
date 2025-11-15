package p088k4;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0443c;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p104m4.C1489c;
import p138q4.C1774f;
import p160t4.C1908a;
import p181w4.C2033e;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableGroupJoin.java */
/* renamed from: k4.g1 */
/* loaded from: classes.dex */
public final class C1276g1<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractC1238a<TLeft, R> {

    /* renamed from: f */
    public final InterfaceC2125q<? extends TRight> f3094f;

    /* renamed from: g */
    public final InterfaceC0454n<? super TLeft, ? extends InterfaceC2125q<TLeftEnd>> f3095g;

    /* renamed from: h */
    public final InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> f3096h;

    /* renamed from: i */
    public final InterfaceC0443c<? super TLeft, ? super AbstractC2120l<TRight>, ? extends R> f3097i;

    /* compiled from: ObservableGroupJoin.java */
    /* renamed from: k4.g1$a */
    public static final class a<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements InterfaceC2153b, b {
        private static final long serialVersionUID = -6071216598687999801L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3102e;

        /* renamed from: k */
        public final InterfaceC0454n<? super TLeft, ? extends InterfaceC2125q<TLeftEnd>> f3108k;

        /* renamed from: l */
        public final InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> f3109l;

        /* renamed from: m */
        public final InterfaceC0443c<? super TLeft, ? super AbstractC2120l<TRight>, ? extends R> f3110m;

        /* renamed from: o */
        public int f3112o;

        /* renamed from: p */
        public int f3113p;

        /* renamed from: q */
        public volatile boolean f3114q;

        /* renamed from: r */
        public static final Integer f3098r = 1;

        /* renamed from: s */
        public static final Integer f3099s = 2;

        /* renamed from: t */
        public static final Integer f3100t = 3;

        /* renamed from: u */
        public static final Integer f3101u = 4;

        /* renamed from: g */
        public final C2152a f3104g = new C2152a(0);

        /* renamed from: f */
        public final C1489c<Object> f3103f = new C1489c<>(AbstractC2120l.bufferSize());

        /* renamed from: h */
        public final Map<Integer, C2033e<TRight>> f3105h = new LinkedHashMap();

        /* renamed from: i */
        public final Map<Integer, TRight> f3106i = new LinkedHashMap();

        /* renamed from: j */
        public final AtomicReference<Throwable> f3107j = new AtomicReference<>();

        /* renamed from: n */
        public final AtomicInteger f3111n = new AtomicInteger(2);

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super TLeft, ? extends InterfaceC2125q<TLeftEnd>> interfaceC0454n, InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> interfaceC0454n2, InterfaceC0443c<? super TLeft, ? super AbstractC2120l<TRight>, ? extends R> interfaceC0443c) {
            this.f3102e = interfaceC2127s;
            this.f3108k = interfaceC0454n;
            this.f3109l = interfaceC0454n2;
            this.f3110m = interfaceC0443c;
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: a */
        public void mo1466a(boolean z6, c cVar) {
            synchronized (this) {
                this.f3103f.m1649d(z6 ? f3100t : f3101u, cVar);
            }
            m1499f();
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: b */
        public void mo1467b(Throwable th) {
            if (C1774f.m1958a(this.f3107j, th)) {
                m1499f();
            } else {
                C1908a.m2205b(th);
            }
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: c */
        public void mo1468c(boolean z6, Object obj) {
            synchronized (this) {
                this.f3103f.m1649d(z6 ? f3098r : f3099s, obj);
            }
            m1499f();
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: d */
        public void mo1469d(Throwable th) {
            if (!C1774f.m1958a(this.f3107j, th)) {
                C1908a.m2205b(th);
            } else {
                this.f3111n.decrementAndGet();
                m1499f();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3114q) {
                return;
            }
            this.f3114q = true;
            this.f3104g.dispose();
            if (getAndIncrement() == 0) {
                this.f3103f.clear();
            }
        }

        @Override // p088k4.C1276g1.b
        /* renamed from: e */
        public void mo1470e(d dVar) {
            this.f3104g.mo322a(dVar);
            this.f3111n.decrementAndGet();
            m1499f();
        }

        /* renamed from: f */
        public void m1499f() {
            if (getAndIncrement() != 0) {
                return;
            }
            C1489c<?> c1489c = this.f3103f;
            InterfaceC2127s<? super R> interfaceC2127s = this.f3102e;
            int iAddAndGet = 1;
            while (!this.f3114q) {
                if (this.f3107j.get() != null) {
                    c1489c.clear();
                    this.f3104g.dispose();
                    m1500g(interfaceC2127s);
                    return;
                }
                boolean z6 = this.f3111n.get() == 0;
                Integer num = (Integer) c1489c.poll();
                boolean z7 = num == null;
                if (z6 && z7) {
                    Iterator<C2033e<TRight>> it = this.f3105h.values().iterator();
                    while (it.hasNext()) {
                        it.next().onComplete();
                    }
                    this.f3105h.clear();
                    this.f3106i.clear();
                    this.f3104g.dispose();
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
                    if (num == f3098r) {
                        C2033e c2033e = new C2033e(AbstractC2120l.bufferSize(), true);
                        int i7 = this.f3112o;
                        this.f3112o = i7 + 1;
                        this.f3105h.put(Integer.valueOf(i7), c2033e);
                        try {
                            InterfaceC2125q interfaceC2125qApply = this.f3108k.apply(objPoll);
                            Objects.requireNonNull(interfaceC2125qApply, "The leftEnd returned a null ObservableSource");
                            InterfaceC2125q interfaceC2125q = interfaceC2125qApply;
                            c cVar = new c(this, true, i7);
                            this.f3104g.m2595b(cVar);
                            interfaceC2125q.subscribe(cVar);
                            if (this.f3107j.get() != null) {
                                c1489c.clear();
                                this.f3104g.dispose();
                                m1500g(interfaceC2127s);
                                return;
                            }
                            try {
                                R rApply = this.f3110m.apply(objPoll, c2033e);
                                Objects.requireNonNull(rApply, "The resultSelector returned a null value");
                                interfaceC2127s.onNext(rApply);
                                Iterator<TRight> it2 = this.f3106i.values().iterator();
                                while (it2.hasNext()) {
                                    c2033e.onNext(it2.next());
                                }
                            } catch (Throwable th) {
                                m1501h(th, interfaceC2127s, c1489c);
                                return;
                            }
                        } catch (Throwable th2) {
                            m1501h(th2, interfaceC2127s, c1489c);
                            return;
                        }
                    } else if (num == f3099s) {
                        int i8 = this.f3113p;
                        this.f3113p = i8 + 1;
                        this.f3106i.put(Integer.valueOf(i8), objPoll);
                        try {
                            InterfaceC2125q interfaceC2125qApply2 = this.f3109l.apply(objPoll);
                            Objects.requireNonNull(interfaceC2125qApply2, "The rightEnd returned a null ObservableSource");
                            InterfaceC2125q interfaceC2125q2 = interfaceC2125qApply2;
                            c cVar2 = new c(this, false, i8);
                            this.f3104g.m2595b(cVar2);
                            interfaceC2125q2.subscribe(cVar2);
                            if (this.f3107j.get() != null) {
                                c1489c.clear();
                                this.f3104g.dispose();
                                m1500g(interfaceC2127s);
                                return;
                            } else {
                                Iterator<C2033e<TRight>> it3 = this.f3105h.values().iterator();
                                while (it3.hasNext()) {
                                    it3.next().onNext(objPoll);
                                }
                            }
                        } catch (Throwable th3) {
                            m1501h(th3, interfaceC2127s, c1489c);
                            return;
                        }
                    } else if (num == f3100t) {
                        c cVar3 = (c) objPoll;
                        C2033e<TRight> c2033eRemove = this.f3105h.remove(Integer.valueOf(cVar3.f3117g));
                        this.f3104g.m2596c(cVar3);
                        if (c2033eRemove != null) {
                            c2033eRemove.onComplete();
                        }
                    } else if (num == f3101u) {
                        c cVar4 = (c) objPoll;
                        this.f3106i.remove(Integer.valueOf(cVar4.f3117g));
                        this.f3104g.m2596c(cVar4);
                    }
                }
            }
            c1489c.clear();
        }

        /* renamed from: g */
        public void m1500g(InterfaceC2127s<?> interfaceC2127s) {
            Throwable thM1959b = C1774f.m1959b(this.f3107j);
            Iterator<C2033e<TRight>> it = this.f3105h.values().iterator();
            while (it.hasNext()) {
                it.next().onError(thM1959b);
            }
            this.f3105h.clear();
            this.f3106i.clear();
            interfaceC2127s.onError(thM1959b);
        }

        /* renamed from: h */
        public void m1501h(Throwable th, InterfaceC2127s<?> interfaceC2127s, C1489c<?> c1489c) {
            C2074b.m2470J(th);
            C1774f.m1958a(this.f3107j, th);
            c1489c.clear();
            this.f3104g.dispose();
            m1500g(interfaceC2127s);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3114q;
        }
    }

    /* compiled from: ObservableGroupJoin.java */
    /* renamed from: k4.g1$b */
    public interface b {
        /* renamed from: a */
        void mo1466a(boolean z6, c cVar);

        /* renamed from: b */
        void mo1467b(Throwable th);

        /* renamed from: c */
        void mo1468c(boolean z6, Object obj);

        /* renamed from: d */
        void mo1469d(Throwable th);

        /* renamed from: e */
        void mo1470e(d dVar);
    }

    /* compiled from: ObservableGroupJoin.java */
    /* renamed from: k4.g1$c */
    public static final class c extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<Object>, InterfaceC2153b {
        private static final long serialVersionUID = 1883890389173668373L;

        /* renamed from: e */
        public final b f3115e;

        /* renamed from: f */
        public final boolean f3116f;

        /* renamed from: g */
        public final int f3117g;

        public c(b bVar, boolean z6, int i7) {
            this.f3115e = bVar;
            this.f3116f = z6;
            this.f3117g = i7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3115e.mo1466a(this.f3116f, this);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3115e.mo1467b(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            if (EnumC0515c.m323a(this)) {
                this.f3115e.mo1466a(this.f3116f, this);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this, interfaceC2153b);
        }
    }

    /* compiled from: ObservableGroupJoin.java */
    /* renamed from: k4.g1$d */
    public static final class d extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<Object>, InterfaceC2153b {
        private static final long serialVersionUID = 1883890389173668373L;

        /* renamed from: e */
        public final b f3118e;

        /* renamed from: f */
        public final boolean f3119f;

        public d(b bVar, boolean z6) {
            this.f3118e = bVar;
            this.f3119f = z6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3118e.mo1470e(this);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3118e.mo1469d(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            this.f3118e.mo1468c(this.f3119f, obj);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this, interfaceC2153b);
        }
    }

    public C1276g1(InterfaceC2125q<TLeft> interfaceC2125q, InterfaceC2125q<? extends TRight> interfaceC2125q2, InterfaceC0454n<? super TLeft, ? extends InterfaceC2125q<TLeftEnd>> interfaceC0454n, InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> interfaceC0454n2, InterfaceC0443c<? super TLeft, ? super AbstractC2120l<TRight>, ? extends R> interfaceC0443c) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3094f = interfaceC2125q2;
        this.f3095g = interfaceC0454n;
        this.f3096h = interfaceC0454n2;
        this.f3097i = interfaceC0443c;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        a aVar = new a(interfaceC2127s, this.f3095g, this.f3096h, this.f3097i);
        interfaceC2127s.onSubscribe(aVar);
        d dVar = new d(aVar, true);
        aVar.f3104g.m2595b(dVar);
        d dVar2 = new d(aVar, false);
        aVar.f3104g.m2595b(dVar2);
        this.f2772e.subscribe(dVar);
        this.f3094f.subscribe(dVar2);
    }
}
