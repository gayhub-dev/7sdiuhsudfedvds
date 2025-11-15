package p088k4;

import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2112d;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableConcatWithCompletable.java */
/* renamed from: k4.v */
/* loaded from: classes.dex */
public final class C1364v<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2112d f3877f;

    /* compiled from: ObservableConcatWithCompletable.java */
    /* renamed from: k4.v$a */
    public static final class a<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2111c, InterfaceC2153b {
        private static final long serialVersionUID = -1953724749712440952L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3878e;

        /* renamed from: f */
        public InterfaceC2112d f3879f;

        /* renamed from: g */
        public boolean f3880g;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC2112d interfaceC2112d) {
            this.f3878e = interfaceC2127s;
            this.f3879f = interfaceC2112d;
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
            if (this.f3880g) {
                this.f3878e.onComplete();
                return;
            }
            this.f3880g = true;
            EnumC0515c.m325c(this, null);
            InterfaceC2112d interfaceC2112d = this.f3879f;
            this.f3879f = null;
            interfaceC2112d.mo2552b(this);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3878e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3878e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (!EnumC0515c.m327f(this, interfaceC2153b) || this.f3880g) {
                return;
            }
            this.f3878e.onSubscribe(this);
        }
    }

    public C1364v(AbstractC2120l<T> abstractC2120l, InterfaceC2112d interfaceC2112d) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3877f = interfaceC2112d;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3877f));
    }
}
