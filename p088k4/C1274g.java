package p088k4;

import p014b4.InterfaceC0455o;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0949b;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableAllSingle.java */
/* renamed from: k4.g */
/* loaded from: classes.dex */
public final class C1274g<T> extends AbstractC2129u<Boolean> implements InterfaceC0949b<Boolean> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3083a;

    /* renamed from: b */
    public final InterfaceC0455o<? super T> f3084b;

    /* compiled from: ObservableAllSingle.java */
    /* renamed from: k4.g$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2130v<? super Boolean> f3085e;

        /* renamed from: f */
        public final InterfaceC0455o<? super T> f3086f;

        /* renamed from: g */
        public InterfaceC2153b f3087g;

        /* renamed from: h */
        public boolean f3088h;

        public a(InterfaceC2130v<? super Boolean> interfaceC2130v, InterfaceC0455o<? super T> interfaceC0455o) {
            this.f3085e = interfaceC2130v;
            this.f3086f = interfaceC0455o;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3087g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3087g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3088h) {
                return;
            }
            this.f3088h = true;
            this.f3085e.mo1016a(Boolean.TRUE);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3088h) {
                C1908a.m2205b(th);
            } else {
                this.f3088h = true;
                this.f3085e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3088h) {
                return;
            }
            try {
                if (this.f3086f.test(t6)) {
                    return;
                }
                this.f3088h = true;
                this.f3087g.dispose();
                this.f3085e.mo1016a(Boolean.FALSE);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3087g.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3087g, interfaceC2153b)) {
                this.f3087g = interfaceC2153b;
                this.f3085e.onSubscribe(this);
            }
        }
    }

    public C1274g(InterfaceC2125q<T> interfaceC2125q, InterfaceC0455o<? super T> interfaceC0455o) {
        this.f3083a = interfaceC2125q;
        this.f3084b = interfaceC0455o;
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<Boolean> mo860a() {
        return new C1268f(this.f3083a, this.f3084b);
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super Boolean> interfaceC2130v) {
        this.f3083a.subscribe(new a(interfaceC2130v, this.f3084b));
    }
}
