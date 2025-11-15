package p088k4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0949b;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2110b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2112d;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableFlatMapCompletableCompletable.java */
/* renamed from: k4.v0 */
/* loaded from: classes.dex */
public final class C1365v0<T> extends AbstractC2110b implements InterfaceC0949b<T> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3881a;

    /* renamed from: b */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2112d> f3882b;

    /* renamed from: c */
    public final boolean f3883c;

    /* compiled from: ObservableFlatMapCompletableCompletable.java */
    /* renamed from: k4.v0$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2153b, InterfaceC2127s<T> {
        private static final long serialVersionUID = 8443155186132538303L;

        /* renamed from: e */
        public final InterfaceC2111c f3884e;

        /* renamed from: g */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2112d> f3886g;

        /* renamed from: h */
        public final boolean f3887h;

        /* renamed from: j */
        public InterfaceC2153b f3889j;

        /* renamed from: k */
        public volatile boolean f3890k;

        /* renamed from: f */
        public final C1771c f3885f = new C1771c();

        /* renamed from: i */
        public final C2152a f3888i = new C2152a(0);

        /* compiled from: ObservableFlatMapCompletableCompletable.java */
        /* renamed from: k4.v0$a$a, reason: collision with other inner class name */
        public final class C2185a extends AtomicReference<InterfaceC2153b> implements InterfaceC2111c, InterfaceC2153b {
            private static final long serialVersionUID = 8606673141535671828L;

            public C2185a() {
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
                aVar.f3888i.mo322a(this);
                aVar.onComplete();
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a aVar = a.this;
                aVar.f3888i.mo322a(this);
                aVar.onError(th);
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2111c interfaceC2111c, InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, boolean z6) {
            this.f3884e = interfaceC2111c;
            this.f3886g = interfaceC0454n;
            this.f3887h = z6;
            lazySet(1);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3890k = true;
            this.f3889j.dispose();
            this.f3888i.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3889j.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (decrementAndGet() == 0) {
                Throwable thM1959b = C1774f.m1959b(this.f3885f);
                if (thM1959b != null) {
                    this.f3884e.onError(thM1959b);
                } else {
                    this.f3884e.onComplete();
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f3885f, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (this.f3887h) {
                if (decrementAndGet() == 0) {
                    this.f3884e.onError(C1774f.m1959b(this.f3885f));
                    return;
                }
                return;
            }
            dispose();
            if (getAndSet(0) > 0) {
                this.f3884e.onError(C1774f.m1959b(this.f3885f));
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            try {
                InterfaceC2112d interfaceC2112dApply = this.f3886g.apply(t6);
                Objects.requireNonNull(interfaceC2112dApply, "The mapper returned a null CompletableSource");
                InterfaceC2112d interfaceC2112d = interfaceC2112dApply;
                getAndIncrement();
                C2185a c2185a = new C2185a();
                if (this.f3890k || !this.f3888i.m2595b(c2185a)) {
                    return;
                }
                interfaceC2112d.mo2552b(c2185a);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3889j.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3889j, interfaceC2153b)) {
                this.f3889j = interfaceC2153b;
                this.f3884e.onSubscribe(this);
            }
        }
    }

    public C1365v0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, boolean z6) {
        this.f3881a = interfaceC2125q;
        this.f3882b = interfaceC0454n;
        this.f3883c = z6;
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<T> mo860a() {
        return new C1359u0(this.f3881a, this.f3882b, this.f3883c);
    }

    @Override // p194y3.AbstractC2110b
    /* renamed from: c */
    public void mo1054c(InterfaceC2111c interfaceC2111c) {
        this.f3881a.subscribe(new a(interfaceC2111c, this.f3882b, this.f3883c));
    }
}
