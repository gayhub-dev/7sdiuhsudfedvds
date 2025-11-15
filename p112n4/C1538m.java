package p112n4;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p194y3.AbstractC2128t;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: SingleScheduler.java */
/* renamed from: n4.m */
/* loaded from: classes.dex */
public final class C1538m extends AbstractC2128t {

    /* renamed from: c */
    public static final ThreadFactoryC1532g f4497c;

    /* renamed from: d */
    public static final ScheduledExecutorService f4498d;

    /* renamed from: a */
    public final ThreadFactory f4499a;

    /* renamed from: b */
    public final AtomicReference<ScheduledExecutorService> f4500b;

    /* compiled from: SingleScheduler.java */
    /* renamed from: n4.m$a */
    public static final class a extends AbstractC2128t.c {

        /* renamed from: e */
        public final ScheduledExecutorService f4501e;

        /* renamed from: f */
        public final C2152a f4502f = new C2152a(0);

        /* renamed from: g */
        public volatile boolean f4503g;

        public a(ScheduledExecutorService scheduledExecutorService) {
            this.f4501e = scheduledExecutorService;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f4503g) {
                return;
            }
            this.f4503g = true;
            this.f4502f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4503g;
        }

        @Override // p194y3.AbstractC2128t.c
        public InterfaceC2153b schedule(Runnable runnable, long j7, TimeUnit timeUnit) {
            EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
            if (this.f4503g) {
                return enumC0516d;
            }
            Objects.requireNonNull(runnable, "run is null");
            RunnableC1535j runnableC1535j = new RunnableC1535j(runnable, this.f4502f);
            this.f4502f.m2595b(runnableC1535j);
            try {
                runnableC1535j.m1715a(j7 <= 0 ? this.f4501e.submit((Callable) runnableC1535j) : this.f4501e.schedule((Callable) runnableC1535j, j7, timeUnit));
                return runnableC1535j;
            } catch (RejectedExecutionException e7) {
                dispose();
                C1908a.m2205b(e7);
                return enumC0516d;
            }
        }
    }

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f4498d = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
        f4497c = new ThreadFactoryC1532g("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    }

    public C1538m() {
        ThreadFactoryC1532g threadFactoryC1532g = f4497c;
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.f4500b = atomicReference;
        this.f4499a = threadFactoryC1532g;
        atomicReference.lazySet(C1536k.m1716a(threadFactoryC1532g));
    }

    @Override // p194y3.AbstractC2128t
    public AbstractC2128t.c createWorker() {
        return new a(this.f4500b.get());
    }

    @Override // p194y3.AbstractC2128t
    public InterfaceC2153b scheduleDirect(Runnable runnable, long j7, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run is null");
        CallableC1534i callableC1534i = new CallableC1534i(runnable);
        try {
            callableC1534i.m1709a(j7 <= 0 ? this.f4500b.get().submit(callableC1534i) : this.f4500b.get().schedule(callableC1534i, j7, timeUnit));
            return callableC1534i;
        } catch (RejectedExecutionException e7) {
            C1908a.m2205b(e7);
            return EnumC0516d.INSTANCE;
        }
    }

    @Override // p194y3.AbstractC2128t
    public InterfaceC2153b schedulePeriodicallyDirect(Runnable runnable, long j7, long j8, TimeUnit timeUnit) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        Objects.requireNonNull(runnable, "run is null");
        if (j8 > 0) {
            RunnableC1533h runnableC1533h = new RunnableC1533h(runnable);
            try {
                runnableC1533h.m1709a(this.f4500b.get().scheduleAtFixedRate(runnableC1533h, j7, j8, timeUnit));
                return runnableC1533h;
            } catch (RejectedExecutionException e7) {
                C1908a.m2205b(e7);
                return enumC0516d;
            }
        }
        ScheduledExecutorService scheduledExecutorService = this.f4500b.get();
        CallableC1528c callableC1528c = new CallableC1528c(runnable, scheduledExecutorService);
        try {
            callableC1528c.m1712a(j7 <= 0 ? scheduledExecutorService.submit(callableC1528c) : scheduledExecutorService.schedule(callableC1528c, j7, timeUnit));
            return callableC1528c;
        } catch (RejectedExecutionException e8) {
            C1908a.m2205b(e8);
            return enumC0516d;
        }
    }

    @Override // p194y3.AbstractC2128t
    public void shutdown() {
        ScheduledExecutorService andSet;
        ScheduledExecutorService scheduledExecutorService = this.f4500b.get();
        ScheduledExecutorService scheduledExecutorService2 = f4498d;
        if (scheduledExecutorService == scheduledExecutorService2 || (andSet = this.f4500b.getAndSet(scheduledExecutorService2)) == scheduledExecutorService2) {
            return;
        }
        andSet.shutdownNow();
    }

    @Override // p194y3.AbstractC2128t
    public void start() {
        ScheduledExecutorService scheduledExecutorService;
        ScheduledExecutorService scheduledExecutorServiceM1716a = null;
        do {
            scheduledExecutorService = this.f4500b.get();
            if (scheduledExecutorService != f4498d) {
                if (scheduledExecutorServiceM1716a != null) {
                    scheduledExecutorServiceM1716a.shutdown();
                    return;
                }
                return;
            } else if (scheduledExecutorServiceM1716a == null) {
                scheduledExecutorServiceM1716a = C1536k.m1716a(this.f4499a);
            }
        } while (!this.f4500b.compareAndSet(scheduledExecutorService, scheduledExecutorServiceM1716a));
    }
}
