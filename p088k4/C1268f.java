package p088k4;

import p014b4.InterfaceC0455o;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableAll.java */
/* renamed from: k4.f */
/* loaded from: classes.dex */
public final class C1268f<T> extends AbstractC1238a<T, Boolean> {

    /* renamed from: f */
    public final InterfaceC0455o<? super T> f3020f;

    /* compiled from: ObservableAll.java */
    /* renamed from: k4.f$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super Boolean> f3021e;

        /* renamed from: f */
        public final InterfaceC0455o<? super T> f3022f;

        /* renamed from: g */
        public InterfaceC2153b f3023g;

        /* renamed from: h */
        public boolean f3024h;

        public a(InterfaceC2127s<? super Boolean> interfaceC2127s, InterfaceC0455o<? super T> interfaceC0455o) {
            this.f3021e = interfaceC2127s;
            this.f3022f = interfaceC0455o;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3023g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3023g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3024h) {
                return;
            }
            this.f3024h = true;
            this.f3021e.onNext(Boolean.TRUE);
            this.f3021e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3024h) {
                C1908a.m2205b(th);
            } else {
                this.f3024h = true;
                this.f3021e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3024h) {
                return;
            }
            try {
                if (this.f3022f.test(t6)) {
                    return;
                }
                this.f3024h = true;
                this.f3023g.dispose();
                this.f3021e.onNext(Boolean.FALSE);
                this.f3021e.onComplete();
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3023g.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3023g, interfaceC2153b)) {
                this.f3023g = interfaceC2153b;
                this.f3021e.onSubscribe(this);
            }
        }
    }

    public C1268f(InterfaceC2125q<T> interfaceC2125q, InterfaceC0455o<? super T> interfaceC0455o) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3020f = interfaceC0455o;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Boolean> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3020f));
    }
}
