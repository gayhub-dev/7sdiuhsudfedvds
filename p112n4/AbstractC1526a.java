package p112n4;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import p032d4.C0870a;
import p201z3.InterfaceC2153b;

/* compiled from: AbstractDirectTask.java */
/* renamed from: n4.a */
/* loaded from: classes.dex */
public abstract class AbstractC1526a extends AtomicReference<Future<?>> implements InterfaceC2153b {

    /* renamed from: g */
    public static final FutureTask<Void> f4422g;

    /* renamed from: h */
    public static final FutureTask<Void> f4423h;
    private static final long serialVersionUID = 1811839108042568751L;

    /* renamed from: e */
    public final Runnable f4424e;

    /* renamed from: f */
    public Thread f4425f;

    static {
        Runnable runnable = C0870a.f1299b;
        f4422g = new FutureTask<>(runnable, null);
        f4423h = new FutureTask<>(runnable, null);
    }

    public AbstractC1526a(Runnable runnable) {
        this.f4424e = runnable;
    }

    /* renamed from: a */
    public final void m1709a(Future<?> future) {
        Future<?> future2;
        do {
            future2 = get();
            if (future2 == f4422g) {
                return;
            }
            if (future2 == f4423h) {
                future.cancel(this.f4425f != Thread.currentThread());
                return;
            }
        } while (!compareAndSet(future2, future));
    }

    @Override // p201z3.InterfaceC2153b
    public final void dispose() {
        FutureTask<Void> futureTask;
        Future<?> future = get();
        if (future == f4422g || future == (futureTask = f4423h) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        future.cancel(this.f4425f != Thread.currentThread());
    }

    @Override // p201z3.InterfaceC2153b
    public final boolean isDisposed() {
        Future<?> future = get();
        return future == f4422g || future == f4423h;
    }
}
