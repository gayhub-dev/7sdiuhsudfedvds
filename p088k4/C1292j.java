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

/* compiled from: ObservableAnySingle.java */
/* renamed from: k4.j */
/* loaded from: classes.dex */
public final class C1292j<T> extends AbstractC2129u<Boolean> implements InterfaceC0949b<Boolean> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3219a;

    /* renamed from: b */
    public final InterfaceC0455o<? super T> f3220b;

    /* compiled from: ObservableAnySingle.java */
    /* renamed from: k4.j$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2130v<? super Boolean> f3221e;

        /* renamed from: f */
        public final InterfaceC0455o<? super T> f3222f;

        /* renamed from: g */
        public InterfaceC2153b f3223g;

        /* renamed from: h */
        public boolean f3224h;

        public a(InterfaceC2130v<? super Boolean> interfaceC2130v, InterfaceC0455o<? super T> interfaceC0455o) {
            this.f3221e = interfaceC2130v;
            this.f3222f = interfaceC0455o;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3223g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3223g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3224h) {
                return;
            }
            this.f3224h = true;
            this.f3221e.mo1016a(Boolean.FALSE);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3224h) {
                C1908a.m2205b(th);
            } else {
                this.f3224h = true;
                this.f3221e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3224h) {
                return;
            }
            try {
                if (this.f3222f.test(t6)) {
                    this.f3224h = true;
                    this.f3223g.dispose();
                    this.f3221e.mo1016a(Boolean.TRUE);
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3223g.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3223g, interfaceC2153b)) {
                this.f3223g = interfaceC2153b;
                this.f3221e.onSubscribe(this);
            }
        }
    }

    public C1292j(InterfaceC2125q<T> interfaceC2125q, InterfaceC0455o<? super T> interfaceC0455o) {
        this.f3219a = interfaceC2125q;
        this.f3220b = interfaceC0455o;
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<Boolean> mo860a() {
        return new C1286i(this.f3219a, this.f3220b);
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super Boolean> interfaceC2130v) {
        this.f3219a.subscribe(new a(interfaceC2130v, this.f3220b));
    }
}
