package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import p014b4.InterfaceC0442b;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableCollect.java */
/* renamed from: k4.q */
/* loaded from: classes.dex */
public final class C1334q<T, U> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final Callable<? extends U> f3569f;

    /* renamed from: g */
    public final InterfaceC0442b<? super U, ? super T> f3570g;

    /* compiled from: ObservableCollect.java */
    /* renamed from: k4.q$a */
    public static final class a<T, U> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super U> f3571e;

        /* renamed from: f */
        public final InterfaceC0442b<? super U, ? super T> f3572f;

        /* renamed from: g */
        public final U f3573g;

        /* renamed from: h */
        public InterfaceC2153b f3574h;

        /* renamed from: i */
        public boolean f3575i;

        public a(InterfaceC2127s<? super U> interfaceC2127s, U u6, InterfaceC0442b<? super U, ? super T> interfaceC0442b) {
            this.f3571e = interfaceC2127s;
            this.f3572f = interfaceC0442b;
            this.f3573g = u6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3574h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3574h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3575i) {
                return;
            }
            this.f3575i = true;
            this.f3571e.onNext(this.f3573g);
            this.f3571e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3575i) {
                C1908a.m2205b(th);
            } else {
                this.f3575i = true;
                this.f3571e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3575i) {
                return;
            }
            try {
                this.f3572f.mo152a(this.f3573g, t6);
            } catch (Throwable th) {
                this.f3574h.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3574h, interfaceC2153b)) {
                this.f3574h = interfaceC2153b;
                this.f3571e.onSubscribe(this);
            }
        }
    }

    public C1334q(InterfaceC2125q<T> interfaceC2125q, Callable<? extends U> callable, InterfaceC0442b<? super U, ? super T> interfaceC0442b) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3569f = callable;
        this.f3570g = interfaceC0442b;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        try {
            U uCall = this.f3569f.call();
            Objects.requireNonNull(uCall, "The initialSupplier returned a null value");
            this.f2772e.subscribe(new a(interfaceC2127s, uCall, this.f3570g));
        } catch (Throwable th) {
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onError(th);
        }
    }
}
