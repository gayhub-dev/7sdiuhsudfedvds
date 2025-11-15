package p088k4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p048f4.AbstractC1011p;
import p104m4.C1487a;
import p153s4.AbstractC1880c;
import p153s4.C1882e;
import p160t4.C1908a;
import p181w4.C2033e;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableWindowBoundarySelector.java */
/* renamed from: k4.q4 */
/* loaded from: classes.dex */
public final class C1339q4<T, B, V> extends AbstractC1238a<T, AbstractC2120l<T>> {

    /* renamed from: f */
    public final InterfaceC2125q<B> f3601f;

    /* renamed from: g */
    public final InterfaceC0454n<? super B, ? extends InterfaceC2125q<V>> f3602g;

    /* renamed from: h */
    public final int f3603h;

    /* compiled from: ObservableWindowBoundarySelector.java */
    /* renamed from: k4.q4$a */
    public static final class a<T, V> extends AbstractC1880c<V> {

        /* renamed from: e */
        public final c<T, ?, V> f3604e;

        /* renamed from: f */
        public final C2033e<T> f3605f;

        /* renamed from: g */
        public boolean f3606g;

        public a(c<T, ?, V> cVar, C2033e<T> c2033e) {
            this.f3604e = cVar;
            this.f3605f = c2033e;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3606g) {
                return;
            }
            this.f3606g = true;
            c<T, ?, V> cVar = this.f3604e;
            cVar.f3611n.mo322a(this);
            cVar.f1906g.offer(new d(this.f3605f, null));
            if (cVar.m1021b()) {
                cVar.m1529g();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3606g) {
                C1908a.m2205b(th);
                return;
            }
            this.f3606g = true;
            c<T, ?, V> cVar = this.f3604e;
            cVar.f3612o.dispose();
            cVar.f3611n.dispose();
            cVar.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(V v6) {
            dispose();
            onComplete();
        }
    }

    /* compiled from: ObservableWindowBoundarySelector.java */
    /* renamed from: k4.q4$b */
    public static final class b<T, B> extends AbstractC1880c<B> {

        /* renamed from: e */
        public final c<T, B, ?> f3607e;

        public b(c<T, B, ?> cVar) {
            this.f3607e = cVar;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3607e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            c<T, B, ?> cVar = this.f3607e;
            cVar.f3612o.dispose();
            cVar.f3611n.dispose();
            cVar.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(B b7) {
            c<T, B, ?> cVar = this.f3607e;
            cVar.f1906g.offer(new d(null, b7));
            if (cVar.m1021b()) {
                cVar.m1529g();
            }
        }
    }

    /* compiled from: ObservableWindowBoundarySelector.java */
    /* renamed from: k4.q4$c */
    public static final class c<T, B, V> extends AbstractC1011p<T, Object, AbstractC2120l<T>> implements InterfaceC2153b {

        /* renamed from: k */
        public final InterfaceC2125q<B> f3608k;

        /* renamed from: l */
        public final InterfaceC0454n<? super B, ? extends InterfaceC2125q<V>> f3609l;

        /* renamed from: m */
        public final int f3610m;

        /* renamed from: n */
        public final C2152a f3611n;

        /* renamed from: o */
        public InterfaceC2153b f3612o;

        /* renamed from: p */
        public final AtomicReference<InterfaceC2153b> f3613p;

        /* renamed from: q */
        public final List<C2033e<T>> f3614q;

        /* renamed from: r */
        public final AtomicLong f3615r;

        /* renamed from: s */
        public final AtomicBoolean f3616s;

        public c(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s, InterfaceC2125q<B> interfaceC2125q, InterfaceC0454n<? super B, ? extends InterfaceC2125q<V>> interfaceC0454n, int i7) {
            super(interfaceC2127s, new C1487a());
            this.f3613p = new AtomicReference<>();
            AtomicLong atomicLong = new AtomicLong();
            this.f3615r = atomicLong;
            this.f3616s = new AtomicBoolean();
            this.f3608k = interfaceC2125q;
            this.f3609l = interfaceC0454n;
            this.f3610m = i7;
            this.f3611n = new C2152a(0);
            this.f3614q = new ArrayList();
            atomicLong.lazySet(1L);
        }

        @Override // p048f4.AbstractC1011p
        /* renamed from: a */
        public void mo1020a(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s, Object obj) {
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3616s.compareAndSet(false, true)) {
                EnumC0515c.m323a(this.f3613p);
                if (this.f3615r.decrementAndGet() == 0) {
                    this.f3612o.dispose();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: g */
        public void m1529g() {
            C1487a c1487a = (C1487a) this.f1906g;
            InterfaceC2127s<? super V> interfaceC2127s = this.f1905f;
            List<C2033e<T>> list = this.f3614q;
            int iM1025f = 1;
            while (true) {
                boolean z6 = this.f1908i;
                Object objPoll = c1487a.poll();
                boolean z7 = objPoll == null;
                if (z6 && z7) {
                    this.f3611n.dispose();
                    EnumC0515c.m323a(this.f3613p);
                    Throwable th = this.f1909j;
                    if (th != null) {
                        Iterator<C2033e<T>> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().onError(th);
                        }
                    } else {
                        Iterator<C2033e<T>> it2 = list.iterator();
                        while (it2.hasNext()) {
                            it2.next().onComplete();
                        }
                    }
                    list.clear();
                    return;
                }
                if (z7) {
                    iM1025f = m1025f(-iM1025f);
                    if (iM1025f == 0) {
                        return;
                    }
                } else if (objPoll instanceof d) {
                    d dVar = (d) objPoll;
                    C2033e<T> c2033e = dVar.f3617a;
                    if (c2033e != null) {
                        if (list.remove(c2033e)) {
                            dVar.f3617a.onComplete();
                            if (this.f3615r.decrementAndGet() == 0) {
                                this.f3611n.dispose();
                                EnumC0515c.m323a(this.f3613p);
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.f3616s.get()) {
                        C2033e<T> c2033eM2387b = C2033e.m2387b(this.f3610m);
                        list.add(c2033eM2387b);
                        interfaceC2127s.onNext(c2033eM2387b);
                        try {
                            InterfaceC2125q<V> interfaceC2125qApply = this.f3609l.apply(dVar.f3618b);
                            Objects.requireNonNull(interfaceC2125qApply, "The ObservableSource supplied is null");
                            InterfaceC2125q<V> interfaceC2125q = interfaceC2125qApply;
                            a aVar = new a(this, c2033eM2387b);
                            if (this.f3611n.m2595b(aVar)) {
                                this.f3615r.getAndIncrement();
                                interfaceC2125q.subscribe(aVar);
                            }
                        } catch (Throwable th2) {
                            C2074b.m2470J(th2);
                            this.f3616s.set(true);
                            interfaceC2127s.onError(th2);
                        }
                    }
                } else {
                    Iterator<C2033e<T>> it3 = list.iterator();
                    while (it3.hasNext()) {
                        it3.next().onNext(objPoll);
                    }
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3616s.get();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f1908i) {
                return;
            }
            this.f1908i = true;
            if (m1021b()) {
                m1529g();
            }
            if (this.f3615r.decrementAndGet() == 0) {
                this.f3611n.dispose();
            }
            this.f1905f.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f1908i) {
                C1908a.m2205b(th);
                return;
            }
            this.f1909j = th;
            this.f1908i = true;
            if (m1021b()) {
                m1529g();
            }
            if (this.f3615r.decrementAndGet() == 0) {
                this.f3611n.dispose();
            }
            this.f1905f.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (m1022c()) {
                Iterator<C2033e<T>> it = this.f3614q.iterator();
                while (it.hasNext()) {
                    it.next().onNext(t6);
                }
                if (m1025f(-1) == 0) {
                    return;
                }
            } else {
                this.f1906g.offer(t6);
                if (!m1021b()) {
                    return;
                }
            }
            m1529g();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3612o, interfaceC2153b)) {
                this.f3612o = interfaceC2153b;
                this.f1905f.onSubscribe(this);
                if (this.f3616s.get()) {
                    return;
                }
                b bVar = new b(this);
                if (this.f3613p.compareAndSet(null, bVar)) {
                    this.f3608k.subscribe(bVar);
                }
            }
        }
    }

    /* compiled from: ObservableWindowBoundarySelector.java */
    /* renamed from: k4.q4$d */
    public static final class d<T, B> {

        /* renamed from: a */
        public final C2033e<T> f3617a;

        /* renamed from: b */
        public final B f3618b;

        public d(C2033e<T> c2033e, B b7) {
            this.f3617a = c2033e;
            this.f3618b = b7;
        }
    }

    public C1339q4(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<B> interfaceC2125q2, InterfaceC0454n<? super B, ? extends InterfaceC2125q<V>> interfaceC0454n, int i7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3601f = interfaceC2125q2;
        this.f3602g = interfaceC0454n;
        this.f3603h = i7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s) {
        this.f2772e.subscribe(new c(new C1882e(interfaceC2127s), this.f3601f, this.f3602g, this.f3603h));
    }
}
