package p088k4;

import p022c4.EnumC0515c;
import p040e4.InterfaceC0949b;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableCountSingle.java */
/* renamed from: k4.z */
/* loaded from: classes.dex */
public final class C1387z<T> extends AbstractC2129u<Long> implements InterfaceC0949b<Long> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f4024a;

    /* compiled from: ObservableCountSingle.java */
    /* renamed from: k4.z$a */
    public static final class a implements InterfaceC2127s<Object>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2130v<? super Long> f4025e;

        /* renamed from: f */
        public InterfaceC2153b f4026f;

        /* renamed from: g */
        public long f4027g;

        public a(InterfaceC2130v<? super Long> interfaceC2130v) {
            this.f4025e = interfaceC2130v;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f4026f.dispose();
            this.f4026f = EnumC0515c.DISPOSED;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4026f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f4026f = EnumC0515c.DISPOSED;
            this.f4025e.mo1016a(Long.valueOf(this.f4027g));
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f4026f = EnumC0515c.DISPOSED;
            this.f4025e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            this.f4027g++;
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f4026f, interfaceC2153b)) {
                this.f4026f = interfaceC2153b;
                this.f4025e.onSubscribe(this);
            }
        }
    }

    public C1387z(InterfaceC2125q<T> interfaceC2125q) {
        this.f4024a = interfaceC2125q;
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<Long> mo860a() {
        return new C1382y(this.f4024a);
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super Long> interfaceC2130v) {
        this.f4024a.subscribe(new a(interfaceC2130v));
    }
}
