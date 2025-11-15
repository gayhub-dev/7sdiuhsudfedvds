package p088k4;

import p022c4.EnumC0515c;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSkip.java */
/* renamed from: k4.p3 */
/* loaded from: classes.dex */
public final class C1332p3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f3550f;

    /* compiled from: ObservableSkip.java */
    /* renamed from: k4.p3$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3551e;

        /* renamed from: f */
        public long f3552f;

        /* renamed from: g */
        public InterfaceC2153b f3553g;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7) {
            this.f3551e = interfaceC2127s;
            this.f3552f = j7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3553g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3553g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3551e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3551e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            long j7 = this.f3552f;
            if (j7 != 0) {
                this.f3552f = j7 - 1;
            } else {
                this.f3551e.onNext(t6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3553g, interfaceC2153b)) {
                this.f3553g = interfaceC2153b;
                this.f3551e.onSubscribe(this);
            }
        }
    }

    public C1332p3(InterfaceC2125q<T> interfaceC2125q, long j7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3550f = j7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3550f));
    }
}
