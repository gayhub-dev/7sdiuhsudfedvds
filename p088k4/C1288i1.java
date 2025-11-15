package p088k4;

import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableIgnoreElements.java */
/* renamed from: k4.i1 */
/* loaded from: classes.dex */
public final class C1288i1<T> extends AbstractC1238a<T, T> {

    /* compiled from: ObservableIgnoreElements.java */
    /* renamed from: k4.i1$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3187e;

        /* renamed from: f */
        public InterfaceC2153b f3188f;

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3187e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3188f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3188f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3187e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3187e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            this.f3188f = interfaceC2153b;
            this.f3187e.onSubscribe(this);
        }
    }

    public C1288i1(InterfaceC2125q<T> interfaceC2125q) {
        super((InterfaceC2125q) interfaceC2125q);
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s));
    }
}
