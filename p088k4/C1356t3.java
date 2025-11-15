package p088k4;

import p014b4.InterfaceC0455o;
import p022c4.EnumC0515c;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSkipWhile.java */
/* renamed from: k4.t3 */
/* loaded from: classes.dex */
public final class C1356t3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0455o<? super T> f3809f;

    /* compiled from: ObservableSkipWhile.java */
    /* renamed from: k4.t3$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3810e;

        /* renamed from: f */
        public final InterfaceC0455o<? super T> f3811f;

        /* renamed from: g */
        public InterfaceC2153b f3812g;

        /* renamed from: h */
        public boolean f3813h;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0455o<? super T> interfaceC0455o) {
            this.f3810e = interfaceC2127s;
            this.f3811f = interfaceC0455o;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3812g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3812g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3810e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3810e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3813h) {
                this.f3810e.onNext(t6);
                return;
            }
            try {
                if (this.f3811f.test(t6)) {
                    return;
                }
                this.f3813h = true;
                this.f3810e.onNext(t6);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3812g.dispose();
                this.f3810e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3812g, interfaceC2153b)) {
                this.f3812g = interfaceC2153b;
                this.f3810e.onSubscribe(this);
            }
        }
    }

    public C1356t3(InterfaceC2125q<T> interfaceC2125q, InterfaceC0455o<? super T> interfaceC0455o) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3809f = interfaceC0455o;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3809f));
    }
}
