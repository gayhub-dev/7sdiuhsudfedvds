package p088k4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p048f4.AbstractC0997b;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2112d;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableFlatMapCompletable.java */
/* renamed from: k4.u0 */
/* loaded from: classes.dex */
public final class C1359u0<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2112d> f3839f;

    /* renamed from: g */
    public final boolean f3840g;

    /* compiled from: ObservableFlatMapCompletable.java */
    /* renamed from: k4.u0$a */
    public static final class a<T> extends AbstractC0997b<T> implements InterfaceC2127s<T> {
        private static final long serialVersionUID = 8443155186132538303L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3841e;

        /* renamed from: g */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2112d> f3843g;

        /* renamed from: h */
        public final boolean f3844h;

        /* renamed from: j */
        public InterfaceC2153b f3846j;

        /* renamed from: k */
        public volatile boolean f3847k;

        /* renamed from: f */
        public final C1771c f3842f = new C1771c();

        /* renamed from: i */
        public final C2152a f3845i = new C2152a(0);

        /* compiled from: ObservableFlatMapCompletable.java */
        /* renamed from: k4.u0$a$a, reason: collision with other inner class name */
        public final class C2184a extends AtomicReference<InterfaceC2153b> implements InterfaceC2111c, InterfaceC2153b {
            private static final long serialVersionUID = 8606673141535671828L;

            public C2184a() {
            }

            @Override // p201z3.InterfaceC2153b
            public void dispose() {
                EnumC0515c.m323a(this);
            }

            @Override // p201z3.InterfaceC2153b
            public boolean isDisposed() {
                return EnumC0515c.m324b(get());
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onComplete() {
                a aVar = a.this;
                aVar.f3845i.mo322a(this);
                aVar.onComplete();
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a aVar = a.this;
                aVar.f3845i.mo322a(this);
                aVar.onError(th);
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, boolean z6) {
            this.f3841e = interfaceC2127s;
            this.f3843g = interfaceC0454n;
            this.f3844h = z6;
            lazySet(1);
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            return i7 & 2;
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3847k = true;
            this.f3846j.dispose();
            this.f3845i.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3846j.isDisposed();
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return true;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (decrementAndGet() == 0) {
                Throwable thM1959b = C1774f.m1959b(this.f3842f);
                if (thM1959b != null) {
                    this.f3841e.onError(thM1959b);
                } else {
                    this.f3841e.onComplete();
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f3842f, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (this.f3844h) {
                if (decrementAndGet() == 0) {
                    this.f3841e.onError(C1774f.m1959b(this.f3842f));
                    return;
                }
                return;
            }
            dispose();
            if (getAndSet(0) > 0) {
                this.f3841e.onError(C1774f.m1959b(this.f3842f));
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            try {
                InterfaceC2112d interfaceC2112dApply = this.f3843g.apply(t6);
                Objects.requireNonNull(interfaceC2112dApply, "The mapper returned a null CompletableSource");
                InterfaceC2112d interfaceC2112d = interfaceC2112dApply;
                getAndIncrement();
                C2184a c2184a = new C2184a();
                if (this.f3847k || !this.f3845i.m2595b(c2184a)) {
                    return;
                }
                interfaceC2112d.mo2552b(c2184a);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3846j.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3846j, interfaceC2153b)) {
                this.f3846j = interfaceC2153b;
                this.f3841e.onSubscribe(this);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            return null;
        }
    }

    public C1359u0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3839f = interfaceC0454n;
        this.f3840g = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3839f, this.f3840g));
    }
}
