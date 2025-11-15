package p088k4;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p032d4.C0870a;
import p040e4.InterfaceC0949b;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableToListSingle.java */
/* renamed from: k4.l4 */
/* loaded from: classes.dex */
public final class C1309l4<T, U extends Collection<? super T>> extends AbstractC2129u<U> implements InterfaceC0949b<U> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3364a;

    /* renamed from: b */
    public final Callable<U> f3365b;

    /* compiled from: ObservableToListSingle.java */
    /* renamed from: k4.l4$a */
    public static final class a<T, U extends Collection<? super T>> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2130v<? super U> f3366e;

        /* renamed from: f */
        public U f3367f;

        /* renamed from: g */
        public InterfaceC2153b f3368g;

        public a(InterfaceC2130v<? super U> interfaceC2130v, U u6) {
            this.f3366e = interfaceC2130v;
            this.f3367f = u6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3368g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3368g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            U u6 = this.f3367f;
            this.f3367f = null;
            this.f3366e.mo1016a(u6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3367f = null;
            this.f3366e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3367f.add(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3368g, interfaceC2153b)) {
                this.f3368g = interfaceC2153b;
                this.f3366e.onSubscribe(this);
            }
        }
    }

    public C1309l4(InterfaceC2125q<T> interfaceC2125q, int i7) {
        this.f3364a = interfaceC2125q;
        this.f3365b = new C0870a.j(i7);
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<U> mo860a() {
        return new C1303k4(this.f3364a, this.f3365b);
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super U> interfaceC2130v) {
        try {
            U uCall = this.f3365b.call();
            Objects.requireNonNull(uCall, "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.");
            this.f3364a.subscribe(new a(interfaceC2130v, uCall));
        } catch (Throwable th) {
            C2074b.m2470J(th);
            interfaceC2130v.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2130v.onError(th);
        }
    }

    public C1309l4(InterfaceC2125q<T> interfaceC2125q, Callable<U> callable) {
        this.f3364a = interfaceC2125q;
        this.f3365b = callable;
    }
}
