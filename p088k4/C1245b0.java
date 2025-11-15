package p088k4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p153s4.AbstractC1880c;
import p153s4.C1882e;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableDebounce.java */
/* renamed from: k4.b0 */
/* loaded from: classes.dex */
public final class C1245b0<T, U> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<U>> f2818f;

    /* compiled from: ObservableDebounce.java */
    /* renamed from: k4.b0$a */
    public static final class a<T, U> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2819e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<U>> f2820f;

        /* renamed from: g */
        public InterfaceC2153b f2821g;

        /* renamed from: h */
        public final AtomicReference<InterfaceC2153b> f2822h = new AtomicReference<>();

        /* renamed from: i */
        public volatile long f2823i;

        /* renamed from: j */
        public boolean f2824j;

        /* compiled from: ObservableDebounce.java */
        /* renamed from: k4.b0$a$a, reason: collision with other inner class name */
        public static final class C2171a<T, U> extends AbstractC1880c<U> {

            /* renamed from: e */
            public final a<T, U> f2825e;

            /* renamed from: f */
            public final long f2826f;

            /* renamed from: g */
            public final T f2827g;

            /* renamed from: h */
            public boolean f2828h;

            /* renamed from: i */
            public final AtomicBoolean f2829i = new AtomicBoolean();

            public C2171a(a<T, U> aVar, long j7, T t6) {
                this.f2825e = aVar;
                this.f2826f = j7;
                this.f2827g = t6;
            }

            /* renamed from: a */
            public void m1465a() {
                if (this.f2829i.compareAndSet(false, true)) {
                    a<T, U> aVar = this.f2825e;
                    long j7 = this.f2826f;
                    T t6 = this.f2827g;
                    if (j7 == aVar.f2823i) {
                        aVar.f2819e.onNext(t6);
                    }
                }
            }

            @Override // p194y3.InterfaceC2127s
            public void onComplete() {
                if (this.f2828h) {
                    return;
                }
                this.f2828h = true;
                m1465a();
            }

            @Override // p194y3.InterfaceC2127s
            public void onError(Throwable th) {
                if (this.f2828h) {
                    C1908a.m2205b(th);
                    return;
                }
                this.f2828h = true;
                a<T, U> aVar = this.f2825e;
                EnumC0515c.m323a(aVar.f2822h);
                aVar.f2819e.onError(th);
            }

            @Override // p194y3.InterfaceC2127s
            public void onNext(U u6) {
                if (this.f2828h) {
                    return;
                }
                this.f2828h = true;
                dispose();
                m1465a();
            }
        }

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2125q<U>> interfaceC0454n) {
            this.f2819e = interfaceC2127s;
            this.f2820f = interfaceC0454n;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2821g.dispose();
            EnumC0515c.m323a(this.f2822h);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2821g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f2824j) {
                return;
            }
            this.f2824j = true;
            InterfaceC2153b interfaceC2153b = this.f2822h.get();
            if (interfaceC2153b != EnumC0515c.DISPOSED) {
                ((C2171a) interfaceC2153b).m1465a();
                EnumC0515c.m323a(this.f2822h);
                this.f2819e.onComplete();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            EnumC0515c.m323a(this.f2822h);
            this.f2819e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f2824j) {
                return;
            }
            long j7 = this.f2823i + 1;
            this.f2823i = j7;
            InterfaceC2153b interfaceC2153b = this.f2822h.get();
            if (interfaceC2153b != null) {
                interfaceC2153b.dispose();
            }
            try {
                InterfaceC2125q<U> interfaceC2125qApply = this.f2820f.apply(t6);
                Objects.requireNonNull(interfaceC2125qApply, "The ObservableSource supplied is null");
                InterfaceC2125q<U> interfaceC2125q = interfaceC2125qApply;
                C2171a c2171a = new C2171a(this, j7, t6);
                if (this.f2822h.compareAndSet(interfaceC2153b, c2171a)) {
                    interfaceC2125q.subscribe(c2171a);
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                dispose();
                this.f2819e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2821g, interfaceC2153b)) {
                this.f2821g = interfaceC2153b;
                this.f2819e.onSubscribe(this);
            }
        }
    }

    public C1245b0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<U>> interfaceC0454n) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f2818f = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(new C1882e(interfaceC2127s), this.f2818f));
    }
}
