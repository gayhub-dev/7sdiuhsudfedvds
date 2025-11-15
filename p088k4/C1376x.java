package p088k4;

import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p194y3.InterfaceC2131w;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableConcatWithSingle.java */
/* renamed from: k4.x */
/* loaded from: classes.dex */
public final class C1376x<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2131w<? extends T> f3968f;

    /* compiled from: ObservableConcatWithSingle.java */
    /* renamed from: k4.x$a */
    public static final class a<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2130v<T>, InterfaceC2153b {
        private static final long serialVersionUID = -1953724749712440952L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3969e;

        /* renamed from: f */
        public InterfaceC2131w<? extends T> f3970f;

        /* renamed from: g */
        public boolean f3971g;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC2131w<? extends T> interfaceC2131w) {
            this.f3969e = interfaceC2127s;
            this.f3970f = interfaceC2131w;
        }

        @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2117i
        /* renamed from: a */
        public void mo1016a(T t6) {
            this.f3969e.onNext(t6);
            this.f3969e.onComplete();
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
            this.f3971g = true;
            EnumC0515c.m325c(this, null);
            InterfaceC2131w<? extends T> interfaceC2131w = this.f3970f;
            this.f3970f = null;
            interfaceC2131w.mo2562b(this);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3969e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3969e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (!EnumC0515c.m327f(this, interfaceC2153b) || this.f3971g) {
                return;
            }
            this.f3969e.onSubscribe(this);
        }
    }

    public C1376x(AbstractC2120l<T> abstractC2120l, InterfaceC2131w<? extends T> interfaceC2131w) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3968f = interfaceC2131w;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3968f));
    }
}
