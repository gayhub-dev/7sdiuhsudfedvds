package p201z3;

import p009b.C0413b;

/* compiled from: RunnableDisposable.java */
/* renamed from: z3.d */
/* loaded from: classes.dex */
public final class C2155d extends AbstractC2154c<Runnable> {
    private static final long serialVersionUID = -8219729196779211169L;

    public C2155d(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("RunnableDisposable(disposed=");
        sbM112a.append(isDisposed());
        sbM112a.append(", ");
        sbM112a.append(get());
        sbM112a.append(")");
        return sbM112a.toString();
    }
}
