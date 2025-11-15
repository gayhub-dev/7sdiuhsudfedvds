package p088k4;

import p022c4.EnumC0515c;
import p194y3.AbstractC2116h;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableLastMaybe.java */
/* renamed from: k4.d2 */
/* loaded from: classes.dex */
public final class C1259d2<T> extends AbstractC2116h<T> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f2944a;

    /* compiled from: ObservableLastMaybe.java */
    /* renamed from: k4.d2$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2117i<? super T> f2945e;

        /* renamed from: f */
        public InterfaceC2153b f2946f;

        /* renamed from: g */
        public T f2947g;

        public a(InterfaceC2117i<? super T> interfaceC2117i) {
            this.f2945e = interfaceC2117i;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2946f.dispose();
            this.f2946f = EnumC0515c.DISPOSED;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2946f == EnumC0515c.DISPOSED;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2946f = EnumC0515c.DISPOSED;
            T t6 = this.f2947g;
            if (t6 == null) {
                this.f2945e.onComplete();
            } else {
                this.f2947g = null;
                this.f2945e.mo1016a(t6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f2946f = EnumC0515c.DISPOSED;
            this.f2947g = null;
            this.f2945e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f2947g = t6;
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2946f, interfaceC2153b)) {
                this.f2946f = interfaceC2153b;
                this.f2945e.onSubscribe(this);
            }
        }
    }

    public C1259d2(InterfaceC2125q<T> interfaceC2125q) {
        this.f2944a = interfaceC2125q;
    }

    @Override // p194y3.AbstractC2116h
    /* renamed from: c */
    public void mo1488c(InterfaceC2117i<? super T> interfaceC2117i) {
        this.f2944a.subscribe(new a(interfaceC2117i));
    }
}
