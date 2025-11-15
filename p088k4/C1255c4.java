package p088k4;

import p014b4.InterfaceC0455o;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTakeUntilPredicate.java */
/* renamed from: k4.c4 */
/* loaded from: classes.dex */
public final class C1255c4<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0455o<? super T> f2929f;

    /* compiled from: ObservableTakeUntilPredicate.java */
    /* renamed from: k4.c4$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2930e;

        /* renamed from: f */
        public final InterfaceC0455o<? super T> f2931f;

        /* renamed from: g */
        public InterfaceC2153b f2932g;

        /* renamed from: h */
        public boolean f2933h;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0455o<? super T> interfaceC0455o) {
            this.f2930e = interfaceC2127s;
            this.f2931f = interfaceC0455o;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2932g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2932g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f2933h) {
                return;
            }
            this.f2933h = true;
            this.f2930e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f2933h) {
                C1908a.m2205b(th);
            } else {
                this.f2933h = true;
                this.f2930e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f2933h) {
                return;
            }
            this.f2930e.onNext(t6);
            try {
                if (this.f2931f.test(t6)) {
                    this.f2933h = true;
                    this.f2932g.dispose();
                    this.f2930e.onComplete();
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f2932g.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2932g, interfaceC2153b)) {
                this.f2932g = interfaceC2153b;
                this.f2930e.onSubscribe(this);
            }
        }
    }

    public C1255c4(InterfaceC2125q<T> interfaceC2125q, InterfaceC0455o<? super T> interfaceC0455o) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f2929f = interfaceC0455o;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f2929f));
    }
}
