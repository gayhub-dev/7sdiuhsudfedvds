package p153s4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p186x2.C2074b;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: DisposableObserver.java */
/* renamed from: s4.c */
/* loaded from: classes.dex */
public abstract class AbstractC1880c<T> implements InterfaceC2127s<T>, InterfaceC2153b {
    public final AtomicReference<InterfaceC2153b> upstream = new AtomicReference<>();

    @Override // p201z3.InterfaceC2153b
    public final void dispose() {
        EnumC0515c.m323a(this.upstream);
    }

    @Override // p201z3.InterfaceC2153b
    public final boolean isDisposed() {
        return this.upstream.get() == EnumC0515c.DISPOSED;
    }

    public void onStart() {
    }

    @Override // p194y3.InterfaceC2127s
    public final void onSubscribe(InterfaceC2153b interfaceC2153b) {
        boolean z6;
        AtomicReference<InterfaceC2153b> atomicReference = this.upstream;
        Class<?> cls = getClass();
        Objects.requireNonNull(interfaceC2153b, "next is null");
        if (atomicReference.compareAndSet(null, interfaceC2153b)) {
            z6 = true;
        } else {
            interfaceC2153b.dispose();
            if (atomicReference.get() != EnumC0515c.DISPOSED) {
                C2074b.m2461A(cls);
            }
            z6 = false;
        }
        if (z6) {
            onStart();
        }
    }
}
