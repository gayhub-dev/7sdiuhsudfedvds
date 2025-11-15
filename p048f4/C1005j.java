package p048f4;

import java.util.concurrent.atomic.AtomicReference;
import p005a4.C0011c;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p194y3.InterfaceC2111c;
import p201z3.InterfaceC2153b;

/* compiled from: EmptyCompletableObserver.java */
/* renamed from: f4.j */
/* loaded from: classes.dex */
public final class C1005j extends AtomicReference<InterfaceC2153b> implements InterfaceC2111c, InterfaceC2153b {
    private static final long serialVersionUID = -7545121636549663526L;

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        EnumC0515c.m323a(this);
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return get() == EnumC0515c.DISPOSED;
    }

    @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
    public void onComplete() {
        lazySet(EnumC0515c.DISPOSED);
    }

    @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
    public void onError(Throwable th) {
        lazySet(EnumC0515c.DISPOSED);
        C1908a.m2205b(new C0011c(th));
    }

    @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        EnumC0515c.m327f(this, interfaceC2153b);
    }
}
