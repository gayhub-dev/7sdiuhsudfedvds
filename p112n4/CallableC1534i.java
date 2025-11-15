package p112n4;

import java.util.concurrent.Callable;

/* compiled from: ScheduledDirectTask.java */
/* renamed from: n4.i */
/* loaded from: classes.dex */
public final class CallableC1534i extends AbstractC1526a implements Callable<Void> {
    private static final long serialVersionUID = 1811839108042568751L;

    public CallableC1534i(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        this.f4425f = Thread.currentThread();
        try {
            this.f4424e.run();
            return null;
        } finally {
            lazySet(AbstractC1526a.f4422g);
            this.f4425f = null;
        }
    }
}
