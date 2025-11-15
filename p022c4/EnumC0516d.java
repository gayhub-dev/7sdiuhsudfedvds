package p022c4;

import p040e4.InterfaceC0950c;
import p194y3.InterfaceC2127s;

/* compiled from: EmptyDisposable.java */
/* renamed from: c4.d */
/* loaded from: classes.dex */
public enum EnumC0516d implements InterfaceC0950c<Object> {
    INSTANCE,
    NEVER;

    /* renamed from: a */
    public static void m329a(InterfaceC2127s<?> interfaceC2127s) {
        interfaceC2127s.onSubscribe(INSTANCE);
        interfaceC2127s.onComplete();
    }

    /* renamed from: b */
    public static void m330b(Throwable th, InterfaceC2127s<?> interfaceC2127s) {
        interfaceC2127s.onSubscribe(INSTANCE);
        interfaceC2127s.onError(th);
    }

    @Override // p040e4.InterfaceC0951d
    /* renamed from: c */
    public int mo331c(int i7) {
        return i7 & 2;
    }

    @Override // p040e4.InterfaceC0955h
    public void clear() {
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return this == INSTANCE;
    }

    @Override // p040e4.InterfaceC0955h
    public boolean isEmpty() {
        return true;
    }

    @Override // p040e4.InterfaceC0955h
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // p040e4.InterfaceC0955h
    public Object poll() {
        return null;
    }
}
