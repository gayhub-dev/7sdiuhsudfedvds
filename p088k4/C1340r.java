package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import p014b4.InterfaceC0442b;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p040e4.InterfaceC0949b;
import p160t4.C1908a;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableCollectSingle.java */
/* renamed from: k4.r */
/* loaded from: classes.dex */
public final class C1340r<T, U> extends AbstractC2129u<U> implements InterfaceC0949b<U> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3619a;

    /* renamed from: b */
    public final Callable<? extends U> f3620b;

    /* renamed from: c */
    public final InterfaceC0442b<? super U, ? super T> f3621c;

    /* compiled from: ObservableCollectSingle.java */
    /* renamed from: k4.r$a */
    public static final class a<T, U> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2130v<? super U> f3622e;

        /* renamed from: f */
        public final InterfaceC0442b<? super U, ? super T> f3623f;

        /* renamed from: g */
        public final U f3624g;

        /* renamed from: h */
        public InterfaceC2153b f3625h;

        /* renamed from: i */
        public boolean f3626i;

        public a(InterfaceC2130v<? super U> interfaceC2130v, U u6, InterfaceC0442b<? super U, ? super T> interfaceC0442b) {
            this.f3622e = interfaceC2130v;
            this.f3623f = interfaceC0442b;
            this.f3624g = u6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3625h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3625h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3626i) {
                return;
            }
            this.f3626i = true;
            this.f3622e.mo1016a(this.f3624g);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3626i) {
                C1908a.m2205b(th);
            } else {
                this.f3626i = true;
                this.f3622e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3626i) {
                return;
            }
            try {
                this.f3623f.mo152a(this.f3624g, t6);
            } catch (Throwable th) {
                this.f3625h.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3625h, interfaceC2153b)) {
                this.f3625h = interfaceC2153b;
                this.f3622e.onSubscribe(this);
            }
        }
    }

    public C1340r(InterfaceC2125q<T> interfaceC2125q, Callable<? extends U> callable, InterfaceC0442b<? super U, ? super T> interfaceC0442b) {
        this.f3619a = interfaceC2125q;
        this.f3620b = callable;
        this.f3621c = interfaceC0442b;
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<U> mo860a() {
        return new C1334q(this.f3619a, this.f3620b, this.f3621c);
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super U> interfaceC2130v) {
        try {
            U uCall = this.f3620b.call();
            Objects.requireNonNull(uCall, "The initialSupplier returned a null value");
            this.f3619a.subscribe(new a(interfaceC2130v, uCall, this.f3621c));
        } catch (Throwable th) {
            interfaceC2130v.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2130v.onError(th);
        }
    }
}
