package p088k4;

import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObserverResourceWrapper.java */
/* renamed from: k4.x4 */
/* loaded from: classes.dex */
public final class C1381x4<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b {
    private static final long serialVersionUID = -8612022020200669122L;

    /* renamed from: e */
    public final InterfaceC2127s<? super T> f4004e;

    /* renamed from: f */
    public final AtomicReference<InterfaceC2153b> f4005f = new AtomicReference<>();

    public C1381x4(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f4004e = interfaceC2127s;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        EnumC0515c.m323a(this.f4005f);
        EnumC0515c.m323a(this);
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return this.f4005f.get() == EnumC0515c.DISPOSED;
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        dispose();
        this.f4004e.onComplete();
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        dispose();
        this.f4004e.onError(th);
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        this.f4004e.onNext(t6);
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (EnumC0515c.m327f(this.f4005f, interfaceC2153b)) {
            this.f4004e.onSubscribe(this);
        }
    }
}
