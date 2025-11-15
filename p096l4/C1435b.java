package p096l4;

import p022c4.EnumC0515c;
import p048f4.C1003h;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p194y3.InterfaceC2131w;
import p201z3.InterfaceC2153b;

/* compiled from: SingleToObservable.java */
/* renamed from: l4.b */
/* loaded from: classes.dex */
public final class C1435b<T> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final InterfaceC2131w<? extends T> f4179e;

    /* compiled from: SingleToObservable.java */
    /* renamed from: l4.b$a */
    public static final class a<T> extends C1003h<T> implements InterfaceC2130v<T> {
        private static final long serialVersionUID = 3786543492451018833L;

        /* renamed from: g */
        public InterfaceC2153b f4180g;

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            super(interfaceC2127s);
        }

        @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2117i
        /* renamed from: a */
        public void mo1016a(T t6) {
            m1018b(t6);
        }

        @Override // p048f4.C1003h, p201z3.InterfaceC2153b
        public void dispose() {
            super.dispose();
            this.f4180g.dispose();
        }

        @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
        public void onError(Throwable th) {
            m1019d(th);
        }

        @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f4180g, interfaceC2153b)) {
                this.f4180g = interfaceC2153b;
                this.f1883e.onSubscribe(this);
            }
        }
    }

    public C1435b(InterfaceC2131w<? extends T> interfaceC2131w) {
        this.f4179e = interfaceC2131w;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f4179e.mo2562b(new a(interfaceC2127s));
    }
}
