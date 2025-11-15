package p201z3;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ReferenceDisposable.java */
/* renamed from: z3.c */
/* loaded from: classes.dex */
public abstract class AbstractC2154c<T> extends AtomicReference<T> implements InterfaceC2153b {
    private static final long serialVersionUID = 6537757548749041217L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC2154c(T t6) {
        super(t6);
        Objects.requireNonNull(t6, "value is null");
    }

    @Override // p201z3.InterfaceC2153b
    public final void dispose() {
        T andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        ((Runnable) andSet).run();
    }

    @Override // p201z3.InterfaceC2153b
    public final boolean isDisposed() {
        return get() == null;
    }
}
