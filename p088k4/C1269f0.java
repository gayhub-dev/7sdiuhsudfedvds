package p088k4;

import p022c4.C0518f;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableDelaySubscriptionOther.java */
/* renamed from: k4.f0 */
/* loaded from: classes.dex */
public final class C1269f0<T, U> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final InterfaceC2125q<? extends T> f3025e;

    /* renamed from: f */
    public final InterfaceC2125q<U> f3026f;

    /* compiled from: ObservableDelaySubscriptionOther.java */
    /* renamed from: k4.f0$a */
    public final class a implements InterfaceC2127s<U> {

        /* renamed from: e */
        public final C0518f f3027e;

        /* renamed from: f */
        public final InterfaceC2127s<? super T> f3028f;

        /* renamed from: g */
        public boolean f3029g;

        /* compiled from: ObservableDelaySubscriptionOther.java */
        /* renamed from: k4.f0$a$a, reason: collision with other inner class name */
        public final class C2176a implements InterfaceC2127s<T> {
            public C2176a() {
            }

            @Override // p194y3.InterfaceC2127s
            public void onComplete() {
                a.this.f3028f.onComplete();
            }

            @Override // p194y3.InterfaceC2127s
            public void onError(Throwable th) {
                a.this.f3028f.onError(th);
            }

            @Override // p194y3.InterfaceC2127s
            public void onNext(T t6) {
                a.this.f3028f.onNext(t6);
            }

            @Override // p194y3.InterfaceC2127s
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m326e(a.this.f3027e, interfaceC2153b);
            }
        }

        public a(C0518f c0518f, InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3027e = c0518f;
            this.f3028f = interfaceC2127s;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3029g) {
                return;
            }
            this.f3029g = true;
            C1269f0.this.f3025e.subscribe(new C2176a());
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3029g) {
                C1908a.m2205b(th);
            } else {
                this.f3029g = true;
                this.f3028f.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(U u6) {
            onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m326e(this.f3027e, interfaceC2153b);
        }
    }

    public C1269f0(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<U> interfaceC2125q2) {
        this.f3025e = interfaceC2125q;
        this.f3026f = interfaceC2125q2;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C0518f c0518f = new C0518f();
        interfaceC2127s.onSubscribe(c0518f);
        this.f3026f.subscribe(new a(c0518f, interfaceC2127s));
    }
}
