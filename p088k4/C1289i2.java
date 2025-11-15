package p088k4;

import java.util.Objects;
import p022c4.EnumC0515c;
import p138q4.EnumC1776h;
import p194y3.C2119k;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableMaterialize.java */
/* renamed from: k4.i2 */
/* loaded from: classes.dex */
public final class C1289i2<T> extends AbstractC1238a<T, C2119k<T>> {

    /* compiled from: ObservableMaterialize.java */
    /* renamed from: k4.i2$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super C2119k<T>> f3189e;

        /* renamed from: f */
        public InterfaceC2153b f3190f;

        public a(InterfaceC2127s<? super C2119k<T>> interfaceC2127s) {
            this.f3189e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3190f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3190f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3189e.onNext(C2119k.f6247b);
            this.f3189e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            Objects.requireNonNull(th, "error is null");
            this.f3189e.onNext(new C2119k(new EnumC1776h.b(th)));
            this.f3189e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            InterfaceC2127s<? super C2119k<T>> interfaceC2127s = this.f3189e;
            Objects.requireNonNull(t6, "value is null");
            interfaceC2127s.onNext(new C2119k(t6));
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3190f, interfaceC2153b)) {
                this.f3190f = interfaceC2153b;
                this.f3189e.onSubscribe(this);
            }
        }
    }

    public C1289i2(InterfaceC2125q<T> interfaceC2125q) {
        super((InterfaceC2125q) interfaceC2125q);
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super C2119k<T>> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s));
    }
}
