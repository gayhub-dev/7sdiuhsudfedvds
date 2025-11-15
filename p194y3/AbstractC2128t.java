package p194y3;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import p014b4.InterfaceC0454n;
import p022c4.C0518f;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p112n4.C1531f;
import p112n4.C1537l;
import p138q4.C1774f;
import p186x2.C2074b;
import p201z3.InterfaceC2153b;

/* compiled from: Scheduler.java */
/* renamed from: y3.t */
/* loaded from: classes.dex */
public abstract class AbstractC2128t {
    public static final long CLOCK_DRIFT_TOLERANCE_NANOSECONDS = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* compiled from: Scheduler.java */
    /* renamed from: y3.t$a */
    public static final class a implements InterfaceC2153b, Runnable {

        /* renamed from: e */
        public final Runnable f6249e;

        /* renamed from: f */
        public final c f6250f;

        /* renamed from: g */
        public Thread f6251g;

        public a(Runnable runnable, c cVar) {
            this.f6249e = runnable;
            this.f6250f = cVar;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f6251g == Thread.currentThread()) {
                c cVar = this.f6250f;
                if (cVar instanceof C1531f) {
                    C1531f c1531f = (C1531f) cVar;
                    if (c1531f.f4468f) {
                        return;
                    }
                    c1531f.f4468f = true;
                    c1531f.f4467e.shutdown();
                    return;
                }
            }
            this.f6250f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f6250f.isDisposed();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6251g = Thread.currentThread();
            try {
                this.f6249e.run();
            } finally {
                dispose();
                this.f6251g = null;
            }
        }
    }

    /* compiled from: Scheduler.java */
    /* renamed from: y3.t$b */
    public static final class b implements InterfaceC2153b, Runnable {

        /* renamed from: e */
        public final Runnable f6252e;

        /* renamed from: f */
        public final c f6253f;

        /* renamed from: g */
        public volatile boolean f6254g;

        public b(Runnable runnable, c cVar) {
            this.f6252e = runnable;
            this.f6253f = cVar;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f6254g = true;
            this.f6253f.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f6254g;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f6254g) {
                return;
            }
            try {
                this.f6252e.run();
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f6253f.dispose();
                throw C1774f.m1961d(th);
            }
        }
    }

    /* compiled from: Scheduler.java */
    /* renamed from: y3.t$c */
    public static abstract class c implements InterfaceC2153b {

        /* compiled from: Scheduler.java */
        /* renamed from: y3.t$c$a */
        public final class a implements Runnable {

            /* renamed from: e */
            public final Runnable f6255e;

            /* renamed from: f */
            public final C0518f f6256f;

            /* renamed from: g */
            public final long f6257g;

            /* renamed from: h */
            public long f6258h;

            /* renamed from: i */
            public long f6259i;

            /* renamed from: j */
            public long f6260j;

            public a(long j7, Runnable runnable, long j8, C0518f c0518f, long j9) {
                this.f6255e = runnable;
                this.f6256f = c0518f;
                this.f6257g = j9;
                this.f6259i = j8;
                this.f6260j = j7;
            }

            /* JADX WARN: Removed duplicated region for block: B:10:0x0035  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r12 = this;
                    java.lang.Runnable r0 = r12.f6255e
                    r0.run()
                    c4.f r0 = r12.f6256f
                    boolean r0 = r0.isDisposed()
                    if (r0 != 0) goto L52
                    y3.t$c r0 = p194y3.AbstractC2128t.c.this
                    java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS
                    long r2 = r0.now(r1)
                    long r4 = p194y3.AbstractC2128t.CLOCK_DRIFT_TOLERANCE_NANOSECONDS
                    long r6 = r2 + r4
                    long r8 = r12.f6259i
                    r10 = 1
                    int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                    if (r0 < 0) goto L35
                    long r6 = r12.f6257g
                    long r8 = r8 + r6
                    long r8 = r8 + r4
                    int r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
                    if (r0 < 0) goto L2a
                    goto L35
                L2a:
                    long r4 = r12.f6260j
                    long r8 = r12.f6258h
                    long r8 = r8 + r10
                    r12.f6258h = r8
                    long r8 = r8 * r6
                    long r8 = r8 + r4
                    goto L44
                L35:
                    long r4 = r12.f6257g
                    long r8 = r2 + r4
                    long r6 = r12.f6258h
                    long r6 = r6 + r10
                    r12.f6258h = r6
                    long r4 = r4 * r6
                    long r4 = r8 - r4
                    r12.f6260j = r4
                L44:
                    r12.f6259i = r2
                    long r8 = r8 - r2
                    c4.f r0 = r12.f6256f
                    y3.t$c r2 = p194y3.AbstractC2128t.c.this
                    z3.b r1 = r2.schedule(r12, r8, r1)
                    p022c4.EnumC0515c.m325c(r0, r1)
                L52:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: p194y3.AbstractC2128t.c.a.run():void");
            }
        }

        public long now(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public InterfaceC2153b schedule(Runnable runnable) {
            return schedule(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        public abstract InterfaceC2153b schedule(Runnable runnable, long j7, TimeUnit timeUnit);

        public InterfaceC2153b schedulePeriodically(Runnable runnable, long j7, long j8, TimeUnit timeUnit) {
            C0518f c0518f = new C0518f();
            C0518f c0518f2 = new C0518f(c0518f);
            Objects.requireNonNull(runnable, "run is null");
            long nanos = timeUnit.toNanos(j8);
            long jNow = now(TimeUnit.NANOSECONDS);
            InterfaceC2153b interfaceC2153bSchedule = schedule(new a(timeUnit.toNanos(j7) + jNow, runnable, jNow, c0518f2, nanos), j7, timeUnit);
            if (interfaceC2153bSchedule == EnumC0516d.INSTANCE) {
                return interfaceC2153bSchedule;
            }
            EnumC0515c.m325c(c0518f, interfaceC2153bSchedule);
            return c0518f2;
        }
    }

    public static long clockDriftTolerance() {
        return CLOCK_DRIFT_TOLERANCE_NANOSECONDS;
    }

    public abstract c createWorker();

    public long now(TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public InterfaceC2153b scheduleDirect(Runnable runnable) {
        return scheduleDirect(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public InterfaceC2153b schedulePeriodicallyDirect(Runnable runnable, long j7, long j8, TimeUnit timeUnit) {
        c cVarCreateWorker = createWorker();
        Objects.requireNonNull(runnable, "run is null");
        b bVar = new b(runnable, cVarCreateWorker);
        InterfaceC2153b interfaceC2153bSchedulePeriodically = cVarCreateWorker.schedulePeriodically(bVar, j7, j8, timeUnit);
        return interfaceC2153bSchedulePeriodically == EnumC0516d.INSTANCE ? interfaceC2153bSchedulePeriodically : bVar;
    }

    public void shutdown() {
    }

    public void start() {
    }

    public <S extends AbstractC2128t & InterfaceC2153b> S when(InterfaceC0454n<AbstractC2114f<AbstractC2114f<AbstractC2110b>>, AbstractC2110b> interfaceC0454n) {
        return new C1537l(interfaceC0454n, this);
    }

    public InterfaceC2153b scheduleDirect(Runnable runnable, long j7, TimeUnit timeUnit) {
        c cVarCreateWorker = createWorker();
        Objects.requireNonNull(runnable, "run is null");
        a aVar = new a(runnable, cVarCreateWorker);
        cVarCreateWorker.schedule(aVar, j7, timeUnit);
        return aVar;
    }
}
