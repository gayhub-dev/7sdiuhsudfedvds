package p088k4;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p032d4.C0870a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableToList.java */
/* renamed from: k4.k4 */
/* loaded from: classes.dex */
public final class C1303k4<T, U extends Collection<? super T>> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final Callable<U> f3302f;

    /* compiled from: ObservableToList.java */
    /* renamed from: k4.k4$a */
    public static final class a<T, U extends Collection<? super T>> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super U> f3303e;

        /* renamed from: f */
        public InterfaceC2153b f3304f;

        /* renamed from: g */
        public U f3305g;

        public a(InterfaceC2127s<? super U> interfaceC2127s, U u6) {
            this.f3303e = interfaceC2127s;
            this.f3305g = u6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3304f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3304f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            U u6 = this.f3305g;
            this.f3305g = null;
            this.f3303e.onNext(u6);
            this.f3303e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3305g = null;
            this.f3303e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3305g.add(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3304f, interfaceC2153b)) {
                this.f3304f = interfaceC2153b;
                this.f3303e.onSubscribe(this);
            }
        }
    }

    public C1303k4(InterfaceC2125q<T> interfaceC2125q, int i7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3302f = new C0870a.j(i7);
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        try {
            U uCall = this.f3302f.call();
            Objects.requireNonNull(uCall, "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.");
            this.f2772e.subscribe(new a(interfaceC2127s, uCall));
        } catch (Throwable th) {
            C2074b.m2470J(th);
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onError(th);
        }
    }

    public C1303k4(InterfaceC2125q<T> interfaceC2125q, Callable<U> callable) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3302f = callable;
    }
}
