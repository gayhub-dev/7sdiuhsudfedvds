package p088k4;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p048f4.AbstractC1011p;
import p104m4.C1487a;
import p153s4.AbstractC1880c;
import p153s4.C1882e;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableBufferBoundarySupplier.java */
/* renamed from: k4.m */
/* loaded from: classes.dex */
public final class C1310m<T, U extends Collection<? super T>, B> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final Callable<? extends InterfaceC2125q<B>> f3369f;

    /* renamed from: g */
    public final Callable<U> f3370g;

    /* compiled from: ObservableBufferBoundarySupplier.java */
    /* renamed from: k4.m$a */
    public static final class a<T, U extends Collection<? super T>, B> extends AbstractC1880c<B> {

        /* renamed from: e */
        public final b<T, U, B> f3371e;

        /* renamed from: f */
        public boolean f3372f;

        public a(b<T, U, B> bVar) {
            this.f3371e = bVar;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3372f) {
                return;
            }
            this.f3372f = true;
            this.f3371e.m1522g();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3372f) {
                C1908a.m2205b(th);
                return;
            }
            this.f3372f = true;
            b<T, U, B> bVar = this.f3371e;
            bVar.dispose();
            bVar.f1905f.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(B b7) {
            if (this.f3372f) {
                return;
            }
            this.f3372f = true;
            dispose();
            this.f3371e.m1522g();
        }
    }

    /* JADX WARN: Unexpected interfaces in signature: [z3.b] */
    /* compiled from: ObservableBufferBoundarySupplier.java */
    /* renamed from: k4.m$b */
    public static final class b<T, U extends Collection<? super T>, B> extends AbstractC1011p<T, U, U> implements InterfaceC2127s<T> {

        /* renamed from: k */
        public final Callable<U> f3373k;

        /* renamed from: l */
        public final Callable<? extends InterfaceC2125q<B>> f3374l;

        /* renamed from: m */
        public InterfaceC2153b f3375m;

        /* renamed from: n */
        public final AtomicReference<InterfaceC2153b> f3376n;

        /* renamed from: o */
        public U f3377o;

        public b(InterfaceC2127s<? super U> interfaceC2127s, Callable<U> callable, Callable<? extends InterfaceC2125q<B>> callable2) {
            super(interfaceC2127s, new C1487a());
            this.f3376n = new AtomicReference<>();
            this.f3373k = callable;
            this.f3374l = callable2;
        }

        @Override // p048f4.AbstractC1011p
        /* renamed from: a */
        public void mo1020a(InterfaceC2127s interfaceC2127s, Object obj) {
            this.f1905f.onNext((Collection) obj);
        }

        public void dispose() {
            if (this.f1907h) {
                return;
            }
            this.f1907h = true;
            this.f3375m.dispose();
            EnumC0515c.m323a(this.f3376n);
            if (m1021b()) {
                this.f1906g.clear();
            }
        }

        /* renamed from: g */
        public void m1522g() {
            try {
                U uCall = this.f3373k.call();
                Objects.requireNonNull(uCall, "The buffer supplied is null");
                U u6 = uCall;
                try {
                    InterfaceC2125q<B> interfaceC2125qCall = this.f3374l.call();
                    Objects.requireNonNull(interfaceC2125qCall, "The boundary ObservableSource supplied is null");
                    InterfaceC2125q<B> interfaceC2125q = interfaceC2125qCall;
                    a aVar = new a(this);
                    if (EnumC0515c.m325c(this.f3376n, aVar)) {
                        synchronized (this) {
                            U u7 = this.f3377o;
                            if (u7 == null) {
                                return;
                            }
                            this.f3377o = u6;
                            interfaceC2125q.subscribe(aVar);
                            m1023d(u7, false, this);
                        }
                    }
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    this.f1907h = true;
                    this.f3375m.dispose();
                    this.f1905f.onError(th);
                }
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                dispose();
                this.f1905f.onError(th2);
            }
        }

        public boolean isDisposed() {
            return this.f1907h;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            synchronized (this) {
                U u6 = this.f3377o;
                if (u6 == null) {
                    return;
                }
                this.f3377o = null;
                this.f1906g.offer(u6);
                this.f1908i = true;
                if (m1021b()) {
                    C2074b.m2482e(this.f1906g, this.f1905f, false, this, this);
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            dispose();
            this.f1905f.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            synchronized (this) {
                U u6 = this.f3377o;
                if (u6 == null) {
                    return;
                }
                u6.add(t6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3375m, interfaceC2153b)) {
                this.f3375m = interfaceC2153b;
                InterfaceC2127s<? super V> interfaceC2127s = this.f1905f;
                try {
                    U uCall = this.f3373k.call();
                    Objects.requireNonNull(uCall, "The buffer supplied is null");
                    this.f3377o = uCall;
                    try {
                        InterfaceC2125q<B> interfaceC2125qCall = this.f3374l.call();
                        Objects.requireNonNull(interfaceC2125qCall, "The boundary ObservableSource supplied is null");
                        InterfaceC2125q<B> interfaceC2125q = interfaceC2125qCall;
                        a aVar = new a(this);
                        this.f3376n.set(aVar);
                        interfaceC2127s.onSubscribe(this);
                        if (this.f1907h) {
                            return;
                        }
                        interfaceC2125q.subscribe(aVar);
                    } catch (Throwable th) {
                        C2074b.m2470J(th);
                        this.f1907h = true;
                        interfaceC2153b.dispose();
                        EnumC0516d.m330b(th, interfaceC2127s);
                    }
                } catch (Throwable th2) {
                    C2074b.m2470J(th2);
                    this.f1907h = true;
                    interfaceC2153b.dispose();
                    EnumC0516d.m330b(th2, interfaceC2127s);
                }
            }
        }
    }

    public C1310m(InterfaceC2125q<T> interfaceC2125q, Callable<? extends InterfaceC2125q<B>> callable, Callable<U> callable2) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3369f = callable;
        this.f3370g = callable2;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        this.f2772e.subscribe(new b(new C1882e(interfaceC2127s), this.f3370g, this.f3369f));
    }
}
