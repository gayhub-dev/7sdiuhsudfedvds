package p088k4;

import java.util.concurrent.atomic.AtomicBoolean;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableUnsubscribeOn.java */
/* renamed from: k4.m4 */
/* loaded from: classes.dex */
public final class C1315m4<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final AbstractC2128t f3391f;

    /* compiled from: ObservableUnsubscribeOn.java */
    /* renamed from: k4.m4$a */
    public static final class a<T> extends AtomicBoolean implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 1015244841293359600L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3392e;

        /* renamed from: f */
        public final AbstractC2128t f3393f;

        /* renamed from: g */
        public InterfaceC2153b f3394g;

        /* compiled from: ObservableUnsubscribeOn.java */
        /* renamed from: k4.m4$a$a, reason: collision with other inner class name */
        public final class RunnableC2181a implements Runnable {
            public RunnableC2181a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f3394g.dispose();
            }
        }

        public a(InterfaceC2127s<? super T> interfaceC2127s, AbstractC2128t abstractC2128t) {
            this.f3392e = interfaceC2127s;
            this.f3393f = abstractC2128t;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.f3393f.scheduleDirect(new RunnableC2181a());
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (get()) {
                return;
            }
            this.f3392e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (get()) {
                C1908a.m2205b(th);
            } else {
                this.f3392e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (get()) {
                return;
            }
            this.f3392e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3394g, interfaceC2153b)) {
                this.f3394g = interfaceC2153b;
                this.f3392e.onSubscribe(this);
            }
        }
    }

    public C1315m4(InterfaceC2125q<T> interfaceC2125q, AbstractC2128t abstractC2128t) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3391f = abstractC2128t;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3391f));
    }
}
