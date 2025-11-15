package p088k4;

import p005a4.C0009a;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableOnErrorReturn.java */
/* renamed from: k4.p2 */
/* loaded from: classes.dex */
public final class C1331p2<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0454n<? super Throwable, ? extends T> f3546f;

    /* compiled from: ObservableOnErrorReturn.java */
    /* renamed from: k4.p2$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3547e;

        /* renamed from: f */
        public final InterfaceC0454n<? super Throwable, ? extends T> f3548f;

        /* renamed from: g */
        public InterfaceC2153b f3549g;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0454n<? super Throwable, ? extends T> interfaceC0454n) {
            this.f3547e = interfaceC2127s;
            this.f3548f = interfaceC0454n;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3549g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3549g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3547e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            try {
                T tApply = this.f3548f.apply(th);
                if (tApply != null) {
                    this.f3547e.onNext(tApply);
                    this.f3547e.onComplete();
                } else {
                    NullPointerException nullPointerException = new NullPointerException("The supplied value is null");
                    nullPointerException.initCause(th);
                    this.f3547e.onError(nullPointerException);
                }
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                this.f3547e.onError(new C0009a(th, th2));
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3547e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3549g, interfaceC2153b)) {
                this.f3549g = interfaceC2153b;
                this.f3547e.onSubscribe(this);
            }
        }
    }

    public C1331p2(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super Throwable, ? extends T> interfaceC0454n) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3546f = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3546f));
    }
}
