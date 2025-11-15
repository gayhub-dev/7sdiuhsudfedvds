package p112n4;

import p160t4.C1908a;

/* compiled from: ScheduledDirectPeriodicTask.java */
/* renamed from: n4.h */
/* loaded from: classes.dex */
public final class RunnableC1533h extends AbstractC1526a implements Runnable {
    private static final long serialVersionUID = 1811839108042568751L;

    public RunnableC1533h(Runnable runnable) {
        super(runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4425f = Thread.currentThread();
        try {
            this.f4424e.run();
            this.f4425f = null;
        } catch (Throwable th) {
            this.f4425f = null;
            lazySet(AbstractC1526a.f4422g);
            C1908a.m2205b(th);
        }
    }
}
