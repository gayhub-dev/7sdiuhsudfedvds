package p088k4;

import p040e4.InterfaceC0949b;
import p194y3.AbstractC2110b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableIgnoreElementsCompletable.java */
/* renamed from: k4.j1 */
/* loaded from: classes.dex */
public final class C1294j1<T> extends AbstractC2110b implements InterfaceC0949b<T> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3231a;

    /* compiled from: ObservableIgnoreElementsCompletable.java */
    /* renamed from: k4.j1$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2111c f3232e;

        /* renamed from: f */
        public InterfaceC2153b f3233f;

        public a(InterfaceC2111c interfaceC2111c) {
            this.f3232e = interfaceC2111c;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3233f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3233f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3232e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3232e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            this.f3233f = interfaceC2153b;
            this.f3232e.onSubscribe(this);
        }
    }

    public C1294j1(InterfaceC2125q<T> interfaceC2125q) {
        this.f3231a = interfaceC2125q;
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<T> mo860a() {
        return new C1288i1(this.f3231a);
    }

    @Override // p194y3.AbstractC2110b
    /* renamed from: c */
    public void mo1054c(InterfaceC2111c interfaceC2111c) {
        this.f3231a.subscribe(new a(interfaceC2111c));
    }
}
