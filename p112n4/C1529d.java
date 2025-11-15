package p112n4;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0516d;
import p194y3.AbstractC2128t;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: IoScheduler.java */
/* renamed from: n4.d */
/* loaded from: classes.dex */
public final class C1529d extends AbstractC2128t {

    /* renamed from: c */
    public static final ThreadFactoryC1532g f4446c;

    /* renamed from: d */
    public static final ThreadFactoryC1532g f4447d;

    /* renamed from: g */
    public static final c f4450g;

    /* renamed from: h */
    public static final a f4451h;

    /* renamed from: a */
    public final ThreadFactory f4452a;

    /* renamed from: b */
    public final AtomicReference<a> f4453b;

    /* renamed from: f */
    public static final TimeUnit f4449f = TimeUnit.SECONDS;

    /* renamed from: e */
    public static final long f4448e = Long.getLong("rx2.io-keep-alive-time", 60).longValue();

    /* compiled from: IoScheduler.java */
    /* renamed from: n4.d$a */
    public static final class a implements Runnable {

        /* renamed from: e */
        public final long f4454e;

        /* renamed from: f */
        public final ConcurrentLinkedQueue<c> f4455f;

        /* renamed from: g */
        public final C2152a f4456g;

        /* renamed from: h */
        public final ScheduledExecutorService f4457h;

        /* renamed from: i */
        public final Future<?> f4458i;

        /* renamed from: j */
        public final ThreadFactory f4459j;

        public a(long j7, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            long nanos = timeUnit != null ? timeUnit.toNanos(j7) : 0L;
            this.f4454e = nanos;
            this.f4455f = new ConcurrentLinkedQueue<>();
            this.f4456g = new C2152a(0);
            this.f4459j = threadFactory;
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, C1529d.f4447d);
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.f4457h = scheduledExecutorServiceNewScheduledThreadPool;
            this.f4458i = scheduledFutureScheduleWithFixedDelay;
        }

        /* renamed from: a */
        public void m1713a() {
            this.f4456g.dispose();
            Future<?> future = this.f4458i;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.f4457h;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4455f.isEmpty()) {
                return;
            }
            long jNanoTime = System.nanoTime();
            Iterator<c> it = this.f4455f.iterator();
            while (it.hasNext()) {
                c next = it.next();
                if (next.f4464g > jNanoTime) {
                    return;
                }
                if (this.f4455f.remove(next)) {
                    this.f4456g.m2596c(next);
                }
            }
        }
    }

    /* compiled from: IoScheduler.java */
    /* renamed from: n4.d$b */
    public static final class b extends AbstractC2128t.c {

        /* renamed from: f */
        public final a f4461f;

        /* renamed from: g */
        public final c f4462g;

        /* renamed from: h */
        public final AtomicBoolean f4463h = new AtomicBoolean();

        /* renamed from: e */
        public final C2152a f4460e = new C2152a(0);

        public b(a aVar) {
            c cVar;
            c cVar2;
            this.f4461f = aVar;
            if (aVar.f4456g.isDisposed()) {
                cVar2 = C1529d.f4450g;
            } else {
                while (true) {
                    if (aVar.f4455f.isEmpty()) {
                        cVar = new c(aVar.f4459j);
                        aVar.f4456g.m2595b(cVar);
                        break;
                    } else {
                        cVar = aVar.f4455f.poll();
                        if (cVar != null) {
                            break;
                        }
                    }
                }
                cVar2 = cVar;
            }
            this.f4462g = cVar2;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f4463h.compareAndSet(false, true)) {
                this.f4460e.dispose();
                a aVar = this.f4461f;
                c cVar = this.f4462g;
                Objects.requireNonNull(aVar);
                cVar.f4464g = System.nanoTime() + aVar.f4454e;
                aVar.f4455f.offer(cVar);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4463h.get();
        }

        @Override // p194y3.AbstractC2128t.c
        public InterfaceC2153b schedule(Runnable runnable, long j7, TimeUnit timeUnit) {
            return this.f4460e.isDisposed() ? EnumC0516d.INSTANCE : this.f4462g.m1714a(runnable, j7, timeUnit, this.f4460e);
        }
    }

    /* compiled from: IoScheduler.java */
    /* renamed from: n4.d$c */
    public static final class c extends C1531f {

        /* renamed from: g */
        public long f4464g;

        public c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.f4464g = 0L;
        }
    }

    static {
        c cVar = new c(new ThreadFactoryC1532g("RxCachedThreadSchedulerShutdown"));
        f4450g = cVar;
        cVar.dispose();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        ThreadFactoryC1532g threadFactoryC1532g = new ThreadFactoryC1532g("RxCachedThreadScheduler", iMax);
        f4446c = threadFactoryC1532g;
        f4447d = new ThreadFactoryC1532g("RxCachedWorkerPoolEvictor", iMax);
        a aVar = new a(0L, null, threadFactoryC1532g);
        f4451h = aVar;
        aVar.m1713a();
    }

    public C1529d() {
        ThreadFactoryC1532g threadFactoryC1532g = f4446c;
        this.f4452a = threadFactoryC1532g;
        a aVar = f4451h;
        AtomicReference<a> atomicReference = new AtomicReference<>(aVar);
        this.f4453b = atomicReference;
        a aVar2 = new a(f4448e, f4449f, threadFactoryC1532g);
        if (atomicReference.compareAndSet(aVar, aVar2)) {
            return;
        }
        aVar2.m1713a();
    }

    @Override // p194y3.AbstractC2128t
    public AbstractC2128t.c createWorker() {
        return new b(this.f4453b.get());
    }

    @Override // p194y3.AbstractC2128t
    public void shutdown() {
        a aVar;
        a aVar2;
        do {
            aVar = this.f4453b.get();
            aVar2 = f4451h;
            if (aVar == aVar2) {
                return;
            }
        } while (!this.f4453b.compareAndSet(aVar, aVar2));
        aVar.m1713a();
    }

    @Override // p194y3.AbstractC2128t
    public void start() {
        a aVar = new a(f4448e, f4449f, this.f4452a);
        if (this.f4453b.compareAndSet(f4451h, aVar)) {
            return;
        }
        aVar.m1713a();
    }
}
