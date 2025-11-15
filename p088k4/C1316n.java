package p088k4;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p048f4.AbstractC1011p;
import p104m4.C1487a;
import p153s4.AbstractC1880c;
import p153s4.C1882e;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableBufferExactBoundary.java */
/* renamed from: k4.n */
/* loaded from: classes.dex */
public final class C1316n<T, U extends Collection<? super T>, B> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final InterfaceC2125q<B> f3396f;

    /* renamed from: g */
    public final Callable<U> f3397g;

    /* compiled from: ObservableBufferExactBoundary.java */
    /* renamed from: k4.n$a */
    public static final class a<T, U extends Collection<? super T>, B> extends AbstractC1880c<B> {

        /* renamed from: e */
        public final b<T, U, B> f3398e;

        public a(b<T, U, B> bVar) {
            this.f3398e = bVar;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3398e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            b<T, U, B> bVar = this.f3398e;
            bVar.dispose();
            bVar.f1905f.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(B b7) {
            b<T, U, B> bVar = this.f3398e;
            Objects.requireNonNull(bVar);
            try {
                U uCall = bVar.f3399k.call();
                Objects.requireNonNull(uCall, "The buffer supplied is null");
                U u6 = uCall;
                synchronized (bVar) {
                    U u7 = bVar.f3403o;
                    if (u7 != null) {
                        bVar.f3403o = u6;
                        bVar.m1023d(u7, false, bVar);
                    }
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                bVar.dispose();
                bVar.f1905f.onError(th);
            }
        }
    }

    /* JADX WARN: Unexpected interfaces in signature: [z3.b] */
    /* compiled from: ObservableBufferExactBoundary.java */
    /* renamed from: k4.n$b */
    public static final class b<T, U extends Collection<? super T>, B> extends AbstractC1011p<T, U, U> implements InterfaceC2127s<T> {

        /* renamed from: k */
        public final Callable<U> f3399k;

        /* renamed from: l */
        public final InterfaceC2125q<B> f3400l;

        /* renamed from: m */
        public InterfaceC2153b f3401m;

        /* renamed from: n */
        public InterfaceC2153b f3402n;

        /* renamed from: o */
        public U f3403o;

        public b(InterfaceC2127s<? super U> interfaceC2127s, Callable<U> callable, InterfaceC2125q<B> interfaceC2125q) {
            super(interfaceC2127s, new C1487a());
            this.f3399k = callable;
            this.f3400l = interfaceC2125q;
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
            this.f3402n.dispose();
            this.f3401m.dispose();
            if (m1021b()) {
                this.f1906g.clear();
            }
        }

        public boolean isDisposed() {
            return this.f1907h;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            synchronized (this) {
                U u6 = this.f3403o;
                if (u6 == null) {
                    return;
                }
                this.f3403o = null;
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
                U u6 = this.f3403o;
                if (u6 == null) {
                    return;
                }
                u6.add(t6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3401m, interfaceC2153b)) {
                this.f3401m = interfaceC2153b;
                try {
                    U uCall = this.f3399k.call();
                    Objects.requireNonNull(uCall, "The buffer supplied is null");
                    this.f3403o = uCall;
                    a aVar = new a(this);
                    this.f3402n = aVar;
                    this.f1905f.onSubscribe(this);
                    if (this.f1907h) {
                        return;
                    }
                    this.f3400l.subscribe(aVar);
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    this.f1907h = true;
                    interfaceC2153b.dispose();
                    EnumC0516d.m330b(th, this.f1905f);
                }
            }
        }
    }

    public C1316n(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<B> interfaceC2125q2, Callable<U> callable) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3396f = interfaceC2125q2;
        this.f3397g = callable;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        this.f2772e.subscribe(new b(new C1882e(interfaceC2127s), this.f3397g, this.f3396f));
    }
}
