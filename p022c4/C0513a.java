package p022c4;

import java.util.concurrent.atomic.AtomicReferenceArray;
import p201z3.InterfaceC2153b;

/* compiled from: ArrayCompositeDisposable.java */
/* renamed from: c4.a */
/* loaded from: classes.dex */
public final class C0513a extends AtomicReferenceArray<InterfaceC2153b> implements InterfaceC2153b {
    private static final long serialVersionUID = 2746389416410565408L;

    public C0513a(int i7) {
        super(i7);
    }

    /* renamed from: a */
    public boolean m321a(int i7, InterfaceC2153b interfaceC2153b) {
        InterfaceC2153b interfaceC2153b2;
        do {
            interfaceC2153b2 = get(i7);
            if (interfaceC2153b2 == EnumC0515c.DISPOSED) {
                interfaceC2153b.dispose();
                return false;
            }
        } while (!compareAndSet(i7, interfaceC2153b2, interfaceC2153b));
        if (interfaceC2153b2 == null) {
            return true;
        }
        interfaceC2153b2.dispose();
        return true;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        InterfaceC2153b andSet;
        EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
        if (get(0) != enumC0515c) {
            int length = length();
            for (int i7 = 0; i7 < length; i7++) {
                if (get(i7) != enumC0515c && (andSet = getAndSet(i7, enumC0515c)) != enumC0515c && andSet != null) {
                    andSet.dispose();
                }
            }
        }
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return get(0) == EnumC0515c.DISPOSED;
    }
}
