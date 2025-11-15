package p088k4;

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
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableWindowBoundary.java */
/* renamed from: k4.p4 */
/* loaded from: classes.dex */
public final class C1333p4<T, B> extends AbstractC1238a<T, AbstractC2120l<T>> {

    /* renamed from: f */
    public final InterfaceC2125q<B> f3554f;

    /* renamed from: g */
    public final int f3555g;

    /* compiled from: ObservableWindowBoundary.java */
    /* renamed from: k4.p4$a */
    public static final class a<T, B> extends AbstractC1880c<B> {

        /* renamed from: e */
        public final b<T, B> f3556e;

        /* renamed from: f */
        public boolean f3557f;

        public a(b<T, B> bVar) {
            this.f3556e = bVar;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3557f) {
                return;
            }
            this.f3557f = true;
            b<T, B> bVar = this.f3556e;
            EnumC0515c.m323a(bVar.f3562h);
            bVar.f3567m = true;
            bVar.m1527a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3557f) {
                C1908a.m2205b(th);
                return;
            }
            this.f3557f = true;
            b<T, B> bVar = this.f3556e;
            EnumC0515c.m323a(bVar.f3562h);
            if (!C1774f.m1958a(bVar.f3565k, th)) {
                C1908a.m2205b(th);
            } else {
                bVar.f3567m = true;
                bVar.m1527a();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(B b7) {
            if (this.f3557f) {
                return;
            }
            b<T, B> bVar = this.f3556e;
            bVar.f3564j.offer(b.f3558o);
            bVar.m1527a();
        }
    }

    /* compiled from: ObservableWindowBoundary.java */
    /* renamed from: k4.p4$b */
    public static final class b<T, B> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b, Runnable {

        /* renamed from: o */
        public static final Object f3558o = new Object();
        private static final long serialVersionUID = 2233020065421370272L;

        /* renamed from: e */
        public final InterfaceC2127s<? super AbstractC2120l<T>> f3559e;

        /* renamed from: f */
        public final int f3560f;

        /* renamed from: g */
        public final a<T, B> f3561g = new a<>(this);

        /* renamed from: h */
        public final AtomicReference<InterfaceC2153b> f3562h = new AtomicReference<>();

        /* renamed from: i */
        public final AtomicInteger f3563i = new AtomicInteger(1);

        /* renamed from: j */
        public final C1487a<Object> f3564j = new C1487a<>();

        /* renamed from: k */
        public final C1771c f3565k = new C1771c();

        /* renamed from: l */
        public final AtomicBoolean f3566l = new AtomicBoolean();

        /* renamed from: m */
        public volatile boolean f3567m;

        /* renamed from: n */
        public C2033e<T> f3568n;

        public b(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s, int i7) {
            this.f3559e = interfaceC2127s;
            this.f3560f = i7;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public void m1527a() {
            if (getAndIncrement() != 0) {
                return;
            }
            InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s = this.f3559e;
            C1487a<Object> c1487a = this.f3564j;
            C1771c c1771c = this.f3565k;
            int iAddAndGet = 1;
            while (this.f3563i.get() != 0) {
                C2033e<T> c2033e = this.f3568n;
                boolean z6 = this.f3567m;
                if (z6 && c1771c.get() != null) {
                    c1487a.clear();
                    Throwable thM1959b = C1774f.m1959b(c1771c);
                    if (c2033e != 0) {
                        this.f3568n = null;
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
                            this.f3568n = null;
                            c2033e.onComplete();
                        }
                        interfaceC2127s.onComplete();
                        return;
                    }
                    if (c2033e != 0) {
                        this.f3568n = null;
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
                } else if (objPoll != f3558o) {
                    c2033e.onNext(objPoll);
                } else {
                    if (c2033e != 0) {
                        this.f3568n = null;
                        c2033e.onComplete();
                    }
                    if (!this.f3566l.get()) {
                        C2033e<T> c2033eM2388c = C2033e.m2388c(this.f3560f, this);
                        this.f3568n = c2033eM2388c;
                        this.f3563i.getAndIncrement();
                        interfaceC2127s.onNext(c2033eM2388c);
                    }
                }
            }
            c1487a.clear();
            this.f3568n = null;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3566l.compareAndSet(false, true)) {
                this.f3561g.dispose();
                if (this.f3563i.decrementAndGet() == 0) {
                    EnumC0515c.m323a(this.f3562h);
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3566l.get();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3561g.dispose();
            this.f3567m = true;
            m1527a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3561g.dispose();
            if (!C1774f.m1958a(this.f3565k, th)) {
                C1908a.m2205b(th);
            } else {
                this.f3567m = true;
                m1527a();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3564j.offer(t6);
            m1527a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m327f(this.f3562h, interfaceC2153b)) {
                this.f3564j.offer(f3558o);
                m1527a();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3563i.decrementAndGet() == 0) {
                EnumC0515c.m323a(this.f3562h);
            }
        }
    }

    public C1333p4(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<B> interfaceC2125q2, int i7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3554f = interfaceC2125q2;
        this.f3555g = i7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s) {
        b bVar = new b(interfaceC2127s, this.f3555g);
        interfaceC2127s.onSubscribe(bVar);
        this.f3554f.subscribe(bVar.f3561g);
        this.f2772e.subscribe(bVar);
    }
}
