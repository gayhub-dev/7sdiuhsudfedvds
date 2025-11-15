package p088k4;

import p014b4.InterfaceC0455o;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableAny.java */
/* renamed from: k4.i */
/* loaded from: classes.dex */
public final class C1286i<T> extends AbstractC1238a<T, Boolean> {

    /* renamed from: f */
    public final InterfaceC0455o<? super T> f3178f;

    /* compiled from: ObservableAny.java */
    /* renamed from: k4.i$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super Boolean> f3179e;

        /* renamed from: f */
        public final InterfaceC0455o<? super T> f3180f;

        /* renamed from: g */
        public InterfaceC2153b f3181g;

        /* renamed from: h */
        public boolean f3182h;

        public a(InterfaceC2127s<? super Boolean> interfaceC2127s, InterfaceC0455o<? super T> interfaceC0455o) {
            this.f3179e = interfaceC2127s;
            this.f3180f = interfaceC0455o;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3181g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3181g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3182h) {
                return;
            }
            this.f3182h = true;
            this.f3179e.onNext(Boolean.FALSE);
            this.f3179e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3182h) {
                C1908a.m2205b(th);
            } else {
                this.f3182h = true;
                this.f3179e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3182h) {
                return;
            }
            try {
                if (this.f3180f.test(t6)) {
                    this.f3182h = true;
                    this.f3181g.dispose();
                    this.f3179e.onNext(Boolean.TRUE);
                    this.f3179e.onComplete();
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3181g.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3181g, interfaceC2153b)) {
                this.f3181g = interfaceC2153b;
                this.f3179e.onSubscribe(this);
            }
        }
    }

    public C1286i(InterfaceC2125q<T> interfaceC2125q, InterfaceC0455o<? super T> interfaceC0455o) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3178f = interfaceC0455o;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Boolean> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3178f));
    }
}
