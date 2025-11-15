package p112n4;

import java.util.Objects;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p194y3.AbstractC2128t;
import p201z3.C2152a;
import p201z3.InterfaceC2153b;

/* compiled from: ComputationScheduler.java */
/* renamed from: n4.b */
/* loaded from: classes.dex */
public final class C1527b extends AbstractC2128t {

    /* renamed from: c */
    public static final b f4426c;

    /* renamed from: d */
    public static final ThreadFactoryC1532g f4427d;

    /* renamed from: e */
    public static final int f4428e;

    /* renamed from: f */
    public static final c f4429f;

    /* renamed from: a */
    public final ThreadFactory f4430a;

    /* renamed from: b */
    public final AtomicReference<b> f4431b;

    /* compiled from: ComputationScheduler.java */
    /* renamed from: n4.b$b */
    public static final class b {

        /* renamed from: a */
        public final int f4437a;

        /* renamed from: b */
        public final c[] f4438b;

        /* renamed from: c */
        public long f4439c;

        public b(int i7, ThreadFactory threadFactory) {
            this.f4437a = i7;
            this.f4438b = new c[i7];
            for (int i8 = 0; i8 < i7; i8++) {
                this.f4438b[i8] = new c(threadFactory);
            }
        }

        /* renamed from: a */
        public c m1710a() {
            int i7 = this.f4437a;
            if (i7 == 0) {
                return C1527b.f4429f;
            }
            c[] cVarArr = this.f4438b;
            long j7 = this.f4439c;
            this.f4439c = 1 + j7;
            return cVarArr[(int) (j7 % i7)];
        }

        /* renamed from: b */
        public void m1711b() {
            for (c cVar : this.f4438b) {
                cVar.dispose();
            }
        }
    }

    /* compiled from: ComputationScheduler.java */
    /* renamed from: n4.b$c */
    public static final class c extends C1531f {
        public c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        int iIntValue = Integer.getInteger("rx2.computation-threads", 0).intValue();
        if (iIntValue > 0 && iIntValue <= iAvailableProcessors) {
            iAvailableProcessors = iIntValue;
        }
        f4428e = iAvailableProcessors;
        c cVar = new c(new ThreadFactoryC1532g("RxComputationShutdown"));
        f4429f = cVar;
        cVar.dispose();
        ThreadFactoryC1532g threadFactoryC1532g = new ThreadFactoryC1532g("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        f4427d = threadFactoryC1532g;
        b bVar = new b(0, threadFactoryC1532g);
        f4426c = bVar;
        bVar.m1711b();
    }

    public C1527b() {
        ThreadFactoryC1532g threadFactoryC1532g = f4427d;
        this.f4430a = threadFactoryC1532g;
        b bVar = f4426c;
        AtomicReference<b> atomicReference = new AtomicReference<>(bVar);
        this.f4431b = atomicReference;
        b bVar2 = new b(f4428e, threadFactoryC1532g);
        if (atomicReference.compareAndSet(bVar, bVar2)) {
            return;
        }
        bVar2.m1711b();
    }

    @Override // p194y3.AbstractC2128t
    public AbstractC2128t.c createWorker() {
        return new a(this.f4431b.get().m1710a());
    }

    @Override // p194y3.AbstractC2128t
    public InterfaceC2153b scheduleDirect(Runnable runnable, long j7, TimeUnit timeUnit) {
        c cVarM1710a = this.f4431b.get().m1710a();
        Objects.requireNonNull(cVarM1710a);
        Objects.requireNonNull(runnable, "run is null");
        CallableC1534i callableC1534i = new CallableC1534i(runnable);
        try {
            callableC1534i.m1709a(j7 <= 0 ? cVarM1710a.f4467e.submit(callableC1534i) : cVarM1710a.f4467e.schedule(callableC1534i, j7, timeUnit));
            return callableC1534i;
        } catch (RejectedExecutionException e7) {
            C1908a.m2205b(e7);
            return EnumC0516d.INSTANCE;
        }
    }

    @Override // p194y3.AbstractC2128t
    public InterfaceC2153b schedulePeriodicallyDirect(Runnable runnable, long j7, long j8, TimeUnit timeUnit) {
        c cVarM1710a = this.f4431b.get().m1710a();
        Objects.requireNonNull(cVarM1710a);
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        Objects.requireNonNull(runnable, "run is null");
        if (j8 <= 0) {
            CallableC1528c callableC1528c = new CallableC1528c(runnable, cVarM1710a.f4467e);
            try {
                callableC1528c.m1712a(j7 <= 0 ? cVarM1710a.f4467e.submit(callableC1528c) : cVarM1710a.f4467e.schedule(callableC1528c, j7, timeUnit));
                return callableC1528c;
            } catch (RejectedExecutionException e7) {
                C1908a.m2205b(e7);
                return enumC0516d;
            }
        }
        RunnableC1533h runnableC1533h = new RunnableC1533h(runnable);
        try {
            runnableC1533h.m1709a(cVarM1710a.f4467e.scheduleAtFixedRate(runnableC1533h, j7, j8, timeUnit));
            return runnableC1533h;
        } catch (RejectedExecutionException e8) {
            C1908a.m2205b(e8);
            return enumC0516d;
        }
    }

    @Override // p194y3.AbstractC2128t
    public void shutdown() {
        b bVar;
        b bVar2;
        do {
            bVar = this.f4431b.get();
            bVar2 = f4426c;
            if (bVar == bVar2) {
                return;
            }
        } while (!this.f4431b.compareAndSet(bVar, bVar2));
        bVar.m1711b();
    }

    @Override // p194y3.AbstractC2128t
    public void start() {
        b bVar = new b(f4428e, this.f4430a);
        if (this.f4431b.compareAndSet(f4426c, bVar)) {
            return;
        }
        bVar.m1711b();
    }

    /* compiled from: ComputationScheduler.java */
    /* renamed from: n4.b$a */
    public static final class a extends AbstractC2128t.c {

        /* renamed from: e */
        public final C2152a f4432e;

        /* renamed from: f */
        public final C2152a f4433f;

        /* renamed from: g */
        public final C2152a f4434g;

        /* renamed from: h */
        public final c f4435h;

        /* renamed from: i */
        public volatile boolean f4436i;

        public a(c cVar) {
            this.f4435h = cVar;
            C2152a c2152a = new C2152a(1);
            this.f4432e = c2152a;
            C2152a c2152a2 = new C2152a(0);
            this.f4433f = c2152a2;
            C2152a c2152a3 = new C2152a(1);
            this.f4434g = c2152a3;
            c2152a3.m2595b(c2152a);
            c2152a3.m2595b(c2152a2);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f4436i) {
                return;
            }
            this.f4436i = true;
            this.f4434g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4436i;
        }

        @Override // p194y3.AbstractC2128t.c
        public InterfaceC2153b schedule(Runnable runnable) {
            return this.f4436i ? EnumC0516d.INSTANCE : this.f4435h.m1714a(runnable, 0L, TimeUnit.MILLISECONDS, this.f4432e);
        }

        @Override // p194y3.AbstractC2128t.c
        public InterfaceC2153b schedule(Runnable runnable, long j7, TimeUnit timeUnit) {
            if (this.f4436i) {
                return EnumC0516d.INSTANCE;
            }
            return this.f4435h.m1714a(runnable, j7, timeUnit, this.f4433f);
        }
    }
}
