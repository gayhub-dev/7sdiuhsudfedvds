package p112n4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import p032d4.C0870a;
import p160t4.C1908a;
import p201z3.InterfaceC2153b;

/* compiled from: InstantPeriodicTask.java */
/* renamed from: n4.c */
/* loaded from: classes.dex */
public final class CallableC1528c implements Callable<Void>, InterfaceC2153b {

    /* renamed from: j */
    public static final FutureTask<Void> f4440j = new FutureTask<>(C0870a.f1299b, null);

    /* renamed from: e */
    public final Runnable f4441e;

    /* renamed from: h */
    public final ExecutorService f4444h;

    /* renamed from: i */
    public Thread f4445i;

    /* renamed from: g */
    public final AtomicReference<Future<?>> f4443g = new AtomicReference<>();

    /* renamed from: f */
    public final AtomicReference<Future<?>> f4442f = new AtomicReference<>();

    public CallableC1528c(Runnable runnable, ExecutorService executorService) {
        this.f4441e = runnable;
        this.f4444h = executorService;
    }

    /* renamed from: a */
    public void m1712a(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.f4443g.get();
            if (future2 == f4440j) {
                future.cancel(this.f4445i != Thread.currentThread());
                return;
            }
        } while (!this.f4443g.compareAndSet(future2, future));
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        this.f4445i = Thread.currentThread();
        try {
            this.f4441e.run();
            Future<?> futureSubmit = this.f4444h.submit(this);
            while (true) {
                Future<?> future = this.f4442f.get();
                if (future == f4440j) {
                    futureSubmit.cancel(this.f4445i != Thread.currentThread());
                } else if (this.f4442f.compareAndSet(future, futureSubmit)) {
                    break;
                }
            }
            this.f4445i = null;
        } catch (Throwable th) {
            this.f4445i = null;
            C1908a.m2205b(th);
        }
        return null;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        AtomicReference<Future<?>> atomicReference = this.f4443g;
        FutureTask<Void> futureTask = f4440j;
        Future<?> andSet = atomicReference.getAndSet(futureTask);
        if (andSet != null && andSet != futureTask) {
            andSet.cancel(this.f4445i != Thread.currentThread());
        }
        Future<?> andSet2 = this.f4442f.getAndSet(futureTask);
        if (andSet2 == null || andSet2 == futureTask) {
            return;
        }
        andSet2.cancel(this.f4445i != Thread.currentThread());
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return this.f4443g.get() == f4440j;
    }
}
