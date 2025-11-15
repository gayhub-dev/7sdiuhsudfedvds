package p112n4;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import p022c4.EnumC0516d;
import p022c4.InterfaceC0514b;
import p160t4.C1908a;
import p194y3.AbstractC2128t;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: NewThreadWorker.java */
/* renamed from: n4.f */
/* loaded from: classes.dex */
public class C1531f extends AbstractC2128t.c {

    /* renamed from: e */
    public final ScheduledExecutorService f4467e;

    /* renamed from: f */
    public volatile boolean f4468f;

    public C1531f(ThreadFactory threadFactory) {
        this.f4467e = C1536k.m1716a(threadFactory);
    }

    /* renamed from: a */
    public RunnableC1535j m1714a(Runnable runnable, long j7, TimeUnit timeUnit, InterfaceC0514b interfaceC0514b) {
        Objects.requireNonNull(runnable, "run is null");
        RunnableC1535j runnableC1535j = new RunnableC1535j(runnable, interfaceC0514b);
        if (interfaceC0514b != null && !((C2152a) interfaceC0514b).m2595b(runnableC1535j)) {
            return runnableC1535j;
        }
        try {
            runnableC1535j.m1715a(j7 <= 0 ? this.f4467e.submit((Callable) runnableC1535j) : this.f4467e.schedule((Callable) runnableC1535j, j7, timeUnit));
        } catch (RejectedExecutionException e7) {
            if (interfaceC0514b != null) {
                ((C2152a) interfaceC0514b).m2596c(runnableC1535j);
            }
            C1908a.m2205b(e7);
        }
        return runnableC1535j;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        if (this.f4468f) {
            return;
        }
        this.f4468f = true;
        this.f4467e.shutdownNow();
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return this.f4468f;
    }

    @Override // p194y3.AbstractC2128t.c
    public InterfaceC2153b schedule(Runnable runnable) {
        return schedule(runnable, 0L, null);
    }

    @Override // p194y3.AbstractC2128t.c
    public InterfaceC2153b schedule(Runnable runnable, long j7, TimeUnit timeUnit) {
        return this.f4468f ? EnumC0516d.INSTANCE : m1714a(runnable, j7, timeUnit, null);
    }
}
