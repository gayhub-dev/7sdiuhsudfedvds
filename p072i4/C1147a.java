package p072i4;

import p022c4.EnumC0515c;
import p048f4.C1003h;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: MaybeToObservable.java */
/* renamed from: i4.a */
/* loaded from: classes.dex */
public final class C1147a<T> extends C1003h<T> implements InterfaceC2117i<T> {
    private static final long serialVersionUID = 7603343402964826922L;

    /* renamed from: g */
    public InterfaceC2153b f2520g;

    public C1147a(InterfaceC2127s<? super T> interfaceC2127s) {
        super(interfaceC2127s);
    }

    @Override // p194y3.InterfaceC2117i
    /* renamed from: a */
    public void mo1016a(T t6) {
        m1018b(t6);
    }

    @Override // p048f4.C1003h, p201z3.InterfaceC2153b
    public void dispose() {
        super.dispose();
        this.f2520g.dispose();
    }

    @Override // p194y3.InterfaceC2117i
    public void onComplete() {
        if ((get() & 54) != 0) {
            return;
        }
        lazySet(2);
        this.f1883e.onComplete();
    }

    @Override // p194y3.InterfaceC2117i
    public void onError(Throwable th) {
        m1019d(th);
    }

    @Override // p194y3.InterfaceC2117i
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (EnumC0515c.m328h(this.f2520g, interfaceC2153b)) {
            this.f2520g = interfaceC2153b;
            this.f1883e.onSubscribe(this);
        }
    }
}
