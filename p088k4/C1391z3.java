package p088k4;

import p022c4.EnumC0515c;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTakeLastOne.java */
/* renamed from: k4.z3 */
/* loaded from: classes.dex */
public final class C1391z3<T> extends AbstractC1238a<T, T> {

    /* compiled from: ObservableTakeLastOne.java */
    /* renamed from: k4.z3$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f4045e;

        /* renamed from: f */
        public InterfaceC2153b f4046f;

        /* renamed from: g */
        public T f4047g;

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f4045e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f4047g = null;
            this.f4046f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4046f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            T t6 = this.f4047g;
            if (t6 != null) {
                this.f4047g = null;
                this.f4045e.onNext(t6);
            }
            this.f4045e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f4047g = null;
            this.f4045e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f4047g = t6;
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f4046f, interfaceC2153b)) {
                this.f4046f = interfaceC2153b;
                this.f4045e.onSubscribe(this);
            }
        }
    }

    public C1391z3(InterfaceC2125q<T> interfaceC2125q) {
        super((InterfaceC2125q) interfaceC2125q);
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s));
    }
}
