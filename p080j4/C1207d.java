package p080j4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
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

/* compiled from: ObservableSwitchMapCompletable.java */
/* renamed from: j4.d */
/* loaded from: classes.dex */
public final class C1207d<T> extends AbstractC2110b {

    /* renamed from: a */
    public final AbstractC2120l<T> f2686a;

    /* renamed from: b */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2112d> f2687b;

    /* renamed from: c */
    public final boolean f2688c;

    /* compiled from: ObservableSwitchMapCompletable.java */
    /* renamed from: j4.d$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: l */
        public static final C2167a f2689l = new C2167a(null);

        /* renamed from: e */
        public final InterfaceC2111c f2690e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2112d> f2691f;

        /* renamed from: g */
        public final boolean f2692g;

        /* renamed from: h */
        public final C1771c f2693h = new C1771c();

        /* renamed from: i */
        public final AtomicReference<C2167a> f2694i = new AtomicReference<>();

        /* renamed from: j */
        public volatile boolean f2695j;

        /* renamed from: k */
        public InterfaceC2153b f2696k;

        /* compiled from: ObservableSwitchMapCompletable.java */
        /* renamed from: j4.d$a$a, reason: collision with other inner class name */
        public static final class C2167a extends AtomicReference<InterfaceC2153b> implements InterfaceC2111c {
            private static final long serialVersionUID = -8003404460084760287L;

            /* renamed from: e */
            public final a<?> f2697e;

            public C2167a(a<?> aVar) {
                this.f2697e = aVar;
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onComplete() {
                a<?> aVar = this.f2697e;
                if (aVar.f2694i.compareAndSet(this, null) && aVar.f2695j) {
                    Throwable thM1959b = C1774f.m1959b(aVar.f2693h);
                    if (thM1959b == null) {
                        aVar.f2690e.onComplete();
                    } else {
                        aVar.f2690e.onError(thM1959b);
                    }
                }
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a<?> aVar = this.f2697e;
                if (!aVar.f2694i.compareAndSet(this, null) || !C1774f.m1958a(aVar.f2693h, th)) {
                    C1908a.m2205b(th);
                    return;
                }
                if (aVar.f2692g) {
                    if (aVar.f2695j) {
                        aVar.f2690e.onError(C1774f.m1959b(aVar.f2693h));
                        return;
                    }
                    return;
                }
                aVar.dispose();
                Throwable thM1959b = C1774f.m1959b(aVar.f2693h);
                if (thM1959b != C1774f.f5055a) {
                    aVar.f2690e.onError(thM1959b);
                }
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2111c interfaceC2111c, InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, boolean z6) {
            this.f2690e = interfaceC2111c;
            this.f2691f = interfaceC0454n;
            this.f2692g = z6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2696k.dispose();
            AtomicReference<C2167a> atomicReference = this.f2694i;
            C2167a c2167a = f2689l;
            C2167a andSet = atomicReference.getAndSet(c2167a);
            if (andSet == null || andSet == c2167a) {
                return;
            }
            EnumC0515c.m323a(andSet);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2694i.get() == f2689l;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2695j = true;
            if (this.f2694i.get() == null) {
                Throwable thM1959b = C1774f.m1959b(this.f2693h);
                if (thM1959b == null) {
                    this.f2690e.onComplete();
                } else {
                    this.f2690e.onError(thM1959b);
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f2693h, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (this.f2692g) {
                onComplete();
                return;
            }
            AtomicReference<C2167a> atomicReference = this.f2694i;
            C2167a c2167a = f2689l;
            C2167a andSet = atomicReference.getAndSet(c2167a);
            if (andSet != null && andSet != c2167a) {
                EnumC0515c.m323a(andSet);
            }
            Throwable thM1959b = C1774f.m1959b(this.f2693h);
            if (thM1959b != C1774f.f5055a) {
                this.f2690e.onError(thM1959b);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            C2167a c2167a;
            try {
                InterfaceC2112d interfaceC2112dApply = this.f2691f.apply(t6);
                Objects.requireNonNull(interfaceC2112dApply, "The mapper returned a null CompletableSource");
                InterfaceC2112d interfaceC2112d = interfaceC2112dApply;
                C2167a c2167a2 = new C2167a(this);
                do {
                    c2167a = this.f2694i.get();
                    if (c2167a == f2689l) {
                        return;
                    }
                } while (!this.f2694i.compareAndSet(c2167a, c2167a2));
                if (c2167a != null) {
                    EnumC0515c.m323a(c2167a);
                }
                interfaceC2112d.mo2552b(c2167a2);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f2696k.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2696k, interfaceC2153b)) {
                this.f2696k = interfaceC2153b;
                this.f2690e.onSubscribe(this);
            }
        }
    }

    public C1207d(AbstractC2120l<T> abstractC2120l, InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, boolean z6) {
        this.f2686a = abstractC2120l;
        this.f2687b = interfaceC0454n;
        this.f2688c = z6;
    }

    @Override // p194y3.AbstractC2110b
    /* renamed from: c */
    public void mo1054c(InterfaceC2111c interfaceC2111c) {
        if (C2074b.m2472L(this.f2686a, this.f2687b, interfaceC2111c)) {
            return;
        }
        this.f2686a.subscribe(new a(interfaceC2111c, this.f2687b, this.f2688c));
    }
}
