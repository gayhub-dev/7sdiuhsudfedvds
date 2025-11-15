package p088k4;

import p014b4.InterfaceC0455o;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTakeWhile.java */
/* renamed from: k4.d4 */
/* loaded from: classes.dex */
public final class C1261d4<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0455o<? super T> f2955f;

    /* compiled from: ObservableTakeWhile.java */
    /* renamed from: k4.d4$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2956e;

        /* renamed from: f */
        public final InterfaceC0455o<? super T> f2957f;

        /* renamed from: g */
        public InterfaceC2153b f2958g;

        /* renamed from: h */
        public boolean f2959h;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0455o<? super T> interfaceC0455o) {
            this.f2956e = interfaceC2127s;
            this.f2957f = interfaceC0455o;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2958g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2958g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f2959h) {
                return;
            }
            this.f2959h = true;
            this.f2956e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f2959h) {
                C1908a.m2205b(th);
            } else {
                this.f2959h = true;
                this.f2956e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f2959h) {
                return;
            }
            try {
                if (this.f2957f.test(t6)) {
                    this.f2956e.onNext(t6);
                    return;
                }
                this.f2959h = true;
                this.f2958g.dispose();
                this.f2956e.onComplete();
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f2958g.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2958g, interfaceC2153b)) {
                this.f2958g = interfaceC2153b;
                this.f2956e.onSubscribe(this);
            }
        }
    }

    public C1261d4(InterfaceC2125q<T> interfaceC2125q, InterfaceC0455o<? super T> interfaceC0455o) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f2955f = interfaceC0455o;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f2955f));
    }
}
