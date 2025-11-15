package p088k4;

import java.util.NoSuchElementException;
import p022c4.EnumC0515c;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableLastSingle.java */
/* renamed from: k4.e2 */
/* loaded from: classes.dex */
public final class C1265e2<T> extends AbstractC2129u<T> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f2994a;

    /* renamed from: b */
    public final T f2995b;

    /* compiled from: ObservableLastSingle.java */
    /* renamed from: k4.e2$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2130v<? super T> f2996e;

        /* renamed from: f */
        public final T f2997f;

        /* renamed from: g */
        public InterfaceC2153b f2998g;

        /* renamed from: h */
        public T f2999h;

        public a(InterfaceC2130v<? super T> interfaceC2130v, T t6) {
            this.f2996e = interfaceC2130v;
            this.f2997f = t6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2998g.dispose();
            this.f2998g = EnumC0515c.DISPOSED;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2998g == EnumC0515c.DISPOSED;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2998g = EnumC0515c.DISPOSED;
            T t6 = this.f2999h;
            if (t6 != null) {
                this.f2999h = null;
                this.f2996e.mo1016a(t6);
                return;
            }
            T t7 = this.f2997f;
            if (t7 != null) {
                this.f2996e.mo1016a(t7);
            } else {
                this.f2996e.onError(new NoSuchElementException());
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f2998g = EnumC0515c.DISPOSED;
            this.f2999h = null;
            this.f2996e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f2999h = t6;
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2998g, interfaceC2153b)) {
                this.f2998g = interfaceC2153b;
                this.f2996e.onSubscribe(this);
            }
        }
    }

    public C1265e2(InterfaceC2125q<T> interfaceC2125q, T t6) {
        this.f2994a = interfaceC2125q;
        this.f2995b = t6;
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super T> interfaceC2130v) {
        this.f2994a.subscribe(new a(interfaceC2130v, this.f2995b));
    }
}
