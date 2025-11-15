package p048f4;

import p160t4.C1908a;
import p194y3.InterfaceC2127s;

/* compiled from: DeferredScalarDisposable.java */
/* renamed from: f4.h */
/* loaded from: classes.dex */
public class C1003h<T> extends AbstractC0997b<T> {
    private static final long serialVersionUID = -5502432239815349361L;

    /* renamed from: e */
    public final InterfaceC2127s<? super T> f1883e;

    /* renamed from: f */
    public T f1884f;

    public C1003h(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f1883e = interfaceC2127s;
    }

    /* renamed from: b */
    public final void m1018b(T t6) {
        int i7 = get();
        if ((i7 & 54) != 0) {
            return;
        }
        InterfaceC2127s<? super T> interfaceC2127s = this.f1883e;
        if (i7 == 8) {
            this.f1884f = t6;
            lazySet(16);
            interfaceC2127s.onNext(null);
        } else {
            lazySet(2);
            interfaceC2127s.onNext(t6);
        }
        if (get() != 4) {
            interfaceC2127s.onComplete();
        }
    }

    @Override // p040e4.InterfaceC0951d
    /* renamed from: c */
    public final int mo331c(int i7) {
        if ((i7 & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    @Override // p040e4.InterfaceC0955h
    public final void clear() {
        lazySet(32);
        this.f1884f = null;
    }

    /* renamed from: d */
    public final void m1019d(Throwable th) {
        if ((get() & 54) != 0) {
            C1908a.m2205b(th);
        } else {
            lazySet(2);
            this.f1883e.onError(th);
        }
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        set(4);
        this.f1884f = null;
    }

    @Override // p201z3.InterfaceC2153b
    public final boolean isDisposed() {
        return get() == 4;
    }

    @Override // p040e4.InterfaceC0955h
    public final boolean isEmpty() {
        return get() != 16;
    }

    @Override // p040e4.InterfaceC0955h
    public final T poll() {
        if (get() != 16) {
            return null;
        }
        T t6 = this.f1884f;
        this.f1884f = null;
        lazySet(32);
        return t6;
    }
}
