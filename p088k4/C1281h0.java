package p088k4;

import p022c4.EnumC0515c;
import p138q4.EnumC1773e;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableDetach.java */
/* renamed from: k4.h0 */
/* loaded from: classes.dex */
public final class C1281h0<T> extends AbstractC1238a<T, T> {

    /* compiled from: ObservableDetach.java */
    /* renamed from: k4.h0$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public InterfaceC2127s<? super T> f3147e;

        /* renamed from: f */
        public InterfaceC2153b f3148f;

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3147e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            InterfaceC2153b interfaceC2153b = this.f3148f;
            EnumC1773e enumC1773e = EnumC1773e.INSTANCE;
            this.f3148f = enumC1773e;
            this.f3147e = enumC1773e;
            interfaceC2153b.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3148f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            InterfaceC2127s<? super T> interfaceC2127s = this.f3147e;
            EnumC1773e enumC1773e = EnumC1773e.INSTANCE;
            this.f3148f = enumC1773e;
            this.f3147e = enumC1773e;
            interfaceC2127s.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            InterfaceC2127s<? super T> interfaceC2127s = this.f3147e;
            EnumC1773e enumC1773e = EnumC1773e.INSTANCE;
            this.f3148f = enumC1773e;
            this.f3147e = enumC1773e;
            interfaceC2127s.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3147e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3148f, interfaceC2153b)) {
                this.f3148f = interfaceC2153b;
                this.f3147e.onSubscribe(this);
            }
        }
    }

    public C1281h0(InterfaceC2125q<T> interfaceC2125q) {
        super((InterfaceC2125q) interfaceC2125q);
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s));
    }
}
