package p088k4;

import java.util.ArrayDeque;
import p022c4.EnumC0515c;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSkipLast.java */
/* renamed from: k4.q3 */
/* loaded from: classes.dex */
public final class C1338q3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final int f3597f;

    /* compiled from: ObservableSkipLast.java */
    /* renamed from: k4.q3$a */
    public static final class a<T> extends ArrayDeque<T> implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -3807491841935125653L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3598e;

        /* renamed from: f */
        public final int f3599f;

        /* renamed from: g */
        public InterfaceC2153b f3600g;

        public a(InterfaceC2127s<? super T> interfaceC2127s, int i7) {
            super(i7);
            this.f3598e = interfaceC2127s;
            this.f3599f = i7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3600g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3600g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3598e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3598e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3599f == size()) {
                this.f3598e.onNext(poll());
            }
            offer(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3600g, interfaceC2153b)) {
                this.f3600g = interfaceC2153b;
                this.f3598e.onSubscribe(this);
            }
        }
    }

    public C1338q3(InterfaceC2125q<T> interfaceC2125q, int i7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3597f = i7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3597f));
    }
}
