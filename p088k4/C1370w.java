package p088k4;

import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2118j;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableConcatWithMaybe.java */
/* renamed from: k4.w */
/* loaded from: classes.dex */
public final class C1370w<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2118j<? extends T> f3921f;

    /* compiled from: ObservableConcatWithMaybe.java */
    /* renamed from: k4.w$a */
    public static final class a<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2117i<T>, InterfaceC2153b {
        private static final long serialVersionUID = -1953724749712440952L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3922e;

        /* renamed from: f */
        public InterfaceC2118j<? extends T> f3923f;

        /* renamed from: g */
        public boolean f3924g;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC2118j<? extends T> interfaceC2118j) {
            this.f3922e = interfaceC2127s;
            this.f3923f = interfaceC2118j;
        }

        @Override // p194y3.InterfaceC2117i
        /* renamed from: a */
        public void mo1016a(T t6) {
            this.f3922e.onNext(t6);
            this.f3922e.onComplete();
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3924g) {
                this.f3922e.onComplete();
                return;
            }
            this.f3924g = true;
            EnumC0515c.m325c(this, null);
            InterfaceC2118j<? extends T> interfaceC2118j = this.f3923f;
            this.f3923f = null;
            interfaceC2118j.mo2555b(this);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3922e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3922e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (!EnumC0515c.m327f(this, interfaceC2153b) || this.f3924g) {
                return;
            }
            this.f3922e.onSubscribe(this);
        }
    }

    public C1370w(AbstractC2120l<T> abstractC2120l, InterfaceC2118j<? extends T> interfaceC2118j) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3921f = interfaceC2118j;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3921f));
    }
}
