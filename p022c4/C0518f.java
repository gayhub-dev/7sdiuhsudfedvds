package p022c4;

import java.util.concurrent.atomic.AtomicReference;
import p201z3.InterfaceC2153b;

/* compiled from: SequentialDisposable.java */
/* renamed from: c4.f */
/* loaded from: classes.dex */
public final class C0518f extends AtomicReference<InterfaceC2153b> implements InterfaceC2153b {
    private static final long serialVersionUID = -754898800686245608L;

    public C0518f() {
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        EnumC0515c.m323a(this);
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return EnumC0515c.m324b(get());
    }

    public C0518f(InterfaceC2153b interfaceC2153b) {
        lazySet(interfaceC2153b);
    }
}
