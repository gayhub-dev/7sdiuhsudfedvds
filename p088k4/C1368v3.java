package p088k4;

import p022c4.C0518f;
import p022c4.EnumC0515c;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSwitchIfEmpty.java */
/* renamed from: k4.v3 */
/* loaded from: classes.dex */
public final class C1368v3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2125q<? extends T> f3900f;

    /* compiled from: ObservableSwitchIfEmpty.java */
    /* renamed from: k4.v3$a */
    public static final class a<T> implements InterfaceC2127s<T> {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3901e;

        /* renamed from: f */
        public final InterfaceC2125q<? extends T> f3902f;

        /* renamed from: h */
        public boolean f3904h = true;

        /* renamed from: g */
        public final C0518f f3903g = new C0518f();

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC2125q<? extends T> interfaceC2125q) {
            this.f3901e = interfaceC2127s;
            this.f3902f = interfaceC2125q;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (!this.f3904h) {
                this.f3901e.onComplete();
            } else {
                this.f3904h = false;
                this.f3902f.subscribe(this);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3901e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3904h) {
                this.f3904h = false;
            }
            this.f3901e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m326e(this.f3903g, interfaceC2153b);
        }
    }

    public C1368v3(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3900f = interfaceC2125q2;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        a aVar = new a(interfaceC2127s, this.f3900f);
        interfaceC2127s.onSubscribe(aVar.f3903g);
        this.f2772e.subscribe(aVar);
    }
}
