package p088k4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p138q4.C1771c;
import p181w4.AbstractC2032d;
import p181w4.C2030b;
import p181w4.C2031c;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableRetryWhen.java */
/* renamed from: k4.e3 */
/* loaded from: classes.dex */
public final class C1266e3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0454n<? super AbstractC2120l<Throwable>, ? extends InterfaceC2125q<?>> f3000f;

    /* compiled from: ObservableRetryWhen.java */
    /* renamed from: k4.e3$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 802743776666017014L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3001e;

        /* renamed from: h */
        public final AbstractC2032d<Throwable> f3004h;

        /* renamed from: k */
        public final InterfaceC2125q<T> f3007k;

        /* renamed from: l */
        public volatile boolean f3008l;

        /* renamed from: f */
        public final AtomicInteger f3002f = new AtomicInteger();

        /* renamed from: g */
        public final C1771c f3003g = new C1771c();

        /* renamed from: i */
        public final a<T>.C2175a f3005i = new C2175a();

        /* renamed from: j */
        public final AtomicReference<InterfaceC2153b> f3006j = new AtomicReference<>();

        /* compiled from: ObservableRetryWhen.java */
        /* renamed from: k4.e3$a$a, reason: collision with other inner class name */
        public final class C2175a extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<Object> {
            private static final long serialVersionUID = 3254781284376480842L;

            public C2175a() {
            }

            @Override // p194y3.InterfaceC2127s
            public void onComplete() {
                a aVar = a.this;
                EnumC0515c.m323a(aVar.f3006j);
                C2074b.m2500w(aVar.f3001e, aVar, aVar.f3003g);
            }

            @Override // p194y3.InterfaceC2127s
            public void onError(Throwable th) {
                a aVar = a.this;
                EnumC0515c.m323a(aVar.f3006j);
                C2074b.m2501x(aVar.f3001e, th, aVar, aVar.f3003g);
            }

            @Override // p194y3.InterfaceC2127s
            public void onNext(Object obj) {
                a.this.m1493a();
            }

            @Override // p194y3.InterfaceC2127s
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super T> interfaceC2127s, AbstractC2032d<Throwable> abstractC2032d, InterfaceC2125q<T> interfaceC2125q) {
            this.f3001e = interfaceC2127s;
            this.f3004h = abstractC2032d;
            this.f3007k = interfaceC2125q;
        }

        /* renamed from: a */
        public void m1493a() {
            if (this.f3002f.getAndIncrement() == 0) {
                while (!isDisposed()) {
                    if (!this.f3008l) {
                        this.f3008l = true;
                        this.f3007k.subscribe(this);
                    }
                    if (this.f3002f.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3006j);
            EnumC0515c.m323a(this.f3005i);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f3006j.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            EnumC0515c.m323a(this.f3005i);
            C2074b.m2500w(this.f3001e, this, this.f3003g);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            EnumC0515c.m325c(this.f3006j, null);
            this.f3008l = false;
            this.f3004h.onNext(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            C2074b.m2502y(this.f3001e, t6, this, this.f3003g);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m325c(this.f3006j, interfaceC2153b);
        }
    }

    public C1266e3(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super AbstractC2120l<Throwable>, ? extends InterfaceC2125q<?>> interfaceC0454n) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3000f = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        AbstractC2032d c2030b = new C2030b();
        if (!(c2030b instanceof C2031c)) {
            c2030b = new C2031c(c2030b);
        }
        try {
            InterfaceC2125q<?> interfaceC2125qApply = this.f3000f.apply(c2030b);
            Objects.requireNonNull(interfaceC2125qApply, "The handler returned a null ObservableSource");
            InterfaceC2125q<?> interfaceC2125q = interfaceC2125qApply;
            a aVar = new a(interfaceC2127s, c2030b, this.f2772e);
            interfaceC2127s.onSubscribe(aVar);
            interfaceC2125q.subscribe(aVar.f3005i);
            aVar.m1493a();
        } catch (Throwable th) {
            C2074b.m2470J(th);
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onError(th);
        }
    }
}
