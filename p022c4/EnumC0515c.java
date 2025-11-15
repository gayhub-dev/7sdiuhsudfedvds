package p022c4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p005a4.C0012d;
import p160t4.C1908a;
import p201z3.InterfaceC2153b;

/* compiled from: DisposableHelper.java */
/* renamed from: c4.c */
/* loaded from: classes.dex */
public enum EnumC0515c implements InterfaceC2153b {
    DISPOSED;

    /* renamed from: a */
    public static boolean m323a(AtomicReference<InterfaceC2153b> atomicReference) {
        InterfaceC2153b andSet;
        InterfaceC2153b interfaceC2153b = atomicReference.get();
        EnumC0515c enumC0515c = DISPOSED;
        if (interfaceC2153b == enumC0515c || (andSet = atomicReference.getAndSet(enumC0515c)) == enumC0515c) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.dispose();
        return true;
    }

    /* renamed from: b */
    public static boolean m324b(InterfaceC2153b interfaceC2153b) {
        return interfaceC2153b == DISPOSED;
    }

    /* renamed from: c */
    public static boolean m325c(AtomicReference<InterfaceC2153b> atomicReference, InterfaceC2153b interfaceC2153b) {
        InterfaceC2153b interfaceC2153b2;
        do {
            interfaceC2153b2 = atomicReference.get();
            if (interfaceC2153b2 == DISPOSED) {
                if (interfaceC2153b == null) {
                    return false;
                }
                interfaceC2153b.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(interfaceC2153b2, interfaceC2153b));
        return true;
    }

    /* renamed from: e */
    public static boolean m326e(AtomicReference<InterfaceC2153b> atomicReference, InterfaceC2153b interfaceC2153b) {
        InterfaceC2153b interfaceC2153b2;
        do {
            interfaceC2153b2 = atomicReference.get();
            if (interfaceC2153b2 == DISPOSED) {
                if (interfaceC2153b == null) {
                    return false;
                }
                interfaceC2153b.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(interfaceC2153b2, interfaceC2153b));
        if (interfaceC2153b2 == null) {
            return true;
        }
        interfaceC2153b2.dispose();
        return true;
    }

    /* renamed from: f */
    public static boolean m327f(AtomicReference<InterfaceC2153b> atomicReference, InterfaceC2153b interfaceC2153b) {
        Objects.requireNonNull(interfaceC2153b, "d is null");
        if (atomicReference.compareAndSet(null, interfaceC2153b)) {
            return true;
        }
        interfaceC2153b.dispose();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        C1908a.m2205b(new C0012d("Disposable already set!"));
        return false;
    }

    /* renamed from: h */
    public static boolean m328h(InterfaceC2153b interfaceC2153b, InterfaceC2153b interfaceC2153b2) {
        if (interfaceC2153b2 == null) {
            C1908a.m2205b(new NullPointerException("next is null"));
            return false;
        }
        if (interfaceC2153b == null) {
            return true;
        }
        interfaceC2153b2.dispose();
        C1908a.m2205b(new C0012d("Disposable already set!"));
        return false;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return true;
    }
}
