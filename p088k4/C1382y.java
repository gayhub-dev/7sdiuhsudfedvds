package p088k4;

import p022c4.EnumC0515c;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableCount.java */
/* renamed from: k4.y */
/* loaded from: classes.dex */
public final class C1382y<T> extends AbstractC1238a<T, Long> {

    /* compiled from: ObservableCount.java */
    /* renamed from: k4.y$a */
    public static final class a implements InterfaceC2127s<Object>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super Long> f4006e;

        /* renamed from: f */
        public InterfaceC2153b f4007f;

        /* renamed from: g */
        public long f4008g;

        public a(InterfaceC2127s<? super Long> interfaceC2127s) {
            this.f4006e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f4007f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4007f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f4006e.onNext(Long.valueOf(this.f4008g));
            this.f4006e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f4006e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            this.f4008g++;
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f4007f, interfaceC2153b)) {
                this.f4007f = interfaceC2153b;
                this.f4006e.onSubscribe(this);
            }
        }
    }

    public C1382y(InterfaceC2125q<T> interfaceC2125q) {
        super((InterfaceC2125q) interfaceC2125q);
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Long> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s));
    }
}
