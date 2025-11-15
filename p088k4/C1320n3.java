package p088k4;

import p022c4.EnumC0515c;
import p160t4.C1908a;
import p194y3.AbstractC2116h;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSingleMaybe.java */
/* renamed from: k4.n3 */
/* loaded from: classes.dex */
public final class C1320n3<T> extends AbstractC2116h<T> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3422a;

    /* compiled from: ObservableSingleMaybe.java */
    /* renamed from: k4.n3$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2117i<? super T> f3423e;

        /* renamed from: f */
        public InterfaceC2153b f3424f;

        /* renamed from: g */
        public T f3425g;

        /* renamed from: h */
        public boolean f3426h;

        public a(InterfaceC2117i<? super T> interfaceC2117i) {
            this.f3423e = interfaceC2117i;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3424f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3424f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3426h) {
                return;
            }
            this.f3426h = true;
            T t6 = this.f3425g;
            this.f3425g = null;
            if (t6 == null) {
                this.f3423e.onComplete();
            } else {
                this.f3423e.mo1016a(t6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3426h) {
                C1908a.m2205b(th);
            } else {
                this.f3426h = true;
                this.f3423e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3426h) {
                return;
            }
            if (this.f3425g == null) {
                this.f3425g = t6;
                return;
            }
            this.f3426h = true;
            this.f3424f.dispose();
            this.f3423e.onError(new IllegalArgumentException("Sequence contains more than one element!"));
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3424f, interfaceC2153b)) {
                this.f3424f = interfaceC2153b;
                this.f3423e.onSubscribe(this);
            }
        }
    }

    public C1320n3(InterfaceC2125q<T> interfaceC2125q) {
        this.f3422a = interfaceC2125q;
    }

    @Override // p194y3.AbstractC2116h
    /* renamed from: c */
    public void mo1488c(InterfaceC2117i<? super T> interfaceC2117i) {
        this.f3422a.subscribe(new a(interfaceC2117i));
    }
}
