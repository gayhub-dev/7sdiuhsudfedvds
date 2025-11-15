package p048f4;

import java.util.concurrent.atomic.AtomicReference;
import p005a4.C0009a;
import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0446f;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: LambdaObserver.java */
/* renamed from: f4.o */
/* loaded from: classes.dex */
public final class C1010o<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b {
    private static final long serialVersionUID = -7251123623727029452L;

    /* renamed from: e */
    public final InterfaceC0446f<? super T> f1901e;

    /* renamed from: f */
    public final InterfaceC0446f<? super Throwable> f1902f;

    /* renamed from: g */
    public final InterfaceC0441a f1903g;

    /* renamed from: h */
    public final InterfaceC0446f<? super InterfaceC2153b> f1904h;

    public C1010o(InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2, InterfaceC0441a interfaceC0441a, InterfaceC0446f<? super InterfaceC2153b> interfaceC0446f3) {
        this.f1901e = interfaceC0446f;
        this.f1902f = interfaceC0446f2;
        this.f1903g = interfaceC0441a;
        this.f1904h = interfaceC0446f3;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        EnumC0515c.m323a(this);
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return get() == EnumC0515c.DISPOSED;
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        if (isDisposed()) {
            return;
        }
        lazySet(EnumC0515c.DISPOSED);
        try {
            this.f1903g.run();
        } catch (Throwable th) {
            C2074b.m2470J(th);
            C1908a.m2205b(th);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        if (isDisposed()) {
            C1908a.m2205b(th);
            return;
        }
        lazySet(EnumC0515c.DISPOSED);
        try {
            this.f1902f.accept(th);
        } catch (Throwable th2) {
            C2074b.m2470J(th2);
            C1908a.m2205b(new C0009a(th, th2));
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        if (isDisposed()) {
            return;
        }
        try {
            this.f1901e.accept(t6);
        } catch (Throwable th) {
            C2074b.m2470J(th);
            get().dispose();
            onError(th);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (EnumC0515c.m327f(this, interfaceC2153b)) {
            try {
                this.f1904h.accept(this);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                interfaceC2153b.dispose();
                onError(th);
            }
        }
    }
}
