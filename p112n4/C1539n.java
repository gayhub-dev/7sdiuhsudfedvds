package p112n4;

import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p194y3.AbstractC2128t;
import p201z3.C2155d;
import p201z3.InterfaceC2153b;

/* compiled from: TrampolineScheduler.java */
/* renamed from: n4.n */
/* loaded from: classes.dex */
public final class C1539n extends AbstractC2128t {

    /* renamed from: a */
    public static final C1539n f4504a = new C1539n();

    /* compiled from: TrampolineScheduler.java */
    /* renamed from: n4.n$a */
    public static final class a implements Runnable {

        /* renamed from: e */
        public final Runnable f4505e;

        /* renamed from: f */
        public final c f4506f;

        /* renamed from: g */
        public final long f4507g;

        public a(Runnable runnable, c cVar, long j7) {
            this.f4505e = runnable;
            this.f4506f = cVar;
            this.f4507g = j7;
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            if (this.f4506f.f4515h) {
                return;
            }
            long jNow = this.f4506f.now(TimeUnit.MILLISECONDS);
            long j7 = this.f4507g;
            if (j7 > jNow) {
                try {
                    Thread.sleep(j7 - jNow);
                } catch (InterruptedException e7) {
                    Thread.currentThread().interrupt();
                    C1908a.m2205b(e7);
                    return;
                }
            }
            if (this.f4506f.f4515h) {
                return;
            }
            this.f4505e.run();
        }
    }

    /* compiled from: TrampolineScheduler.java */
    /* renamed from: n4.n$b */
    public static final class b implements Comparable<b> {

        /* renamed from: e */
        public final Runnable f4508e;

        /* renamed from: f */
        public final long f4509f;

        /* renamed from: g */
        public final int f4510g;

        /* renamed from: h */
        public volatile boolean f4511h;

        public b(Runnable runnable, Long l7, int i7) {
            this.f4508e = runnable;
            this.f4509f = l7.longValue();
            this.f4510g = i7;
        }

        @Override // java.lang.Comparable
        public int compareTo(b bVar) {
            b bVar2 = bVar;
            long j7 = this.f4509f;
            long j8 = bVar2.f4509f;
            int i7 = 0;
            int i8 = j7 < j8 ? -1 : j7 > j8 ? 1 : 0;
            if (i8 != 0) {
                return i8;
            }
            int i9 = this.f4510g;
            int i10 = bVar2.f4510g;
            if (i9 < i10) {
                i7 = -1;
            } else if (i9 > i10) {
                i7 = 1;
            }
            return i7;
        }
    }

    /* compiled from: TrampolineScheduler.java */
    /* renamed from: n4.n$c */
    public static final class c extends AbstractC2128t.c {

        /* renamed from: e */
        public final PriorityBlockingQueue<b> f4512e = new PriorityBlockingQueue<>();

        /* renamed from: f */
        public final AtomicInteger f4513f = new AtomicInteger();

        /* renamed from: g */
        public final AtomicInteger f4514g = new AtomicInteger();

        /* renamed from: h */
        public volatile boolean f4515h;

        /* compiled from: TrampolineScheduler.java */
        /* renamed from: n4.n$c$a */
        public final class a implements Runnable {

            /* renamed from: e */
            public final b f4516e;

            public a(b bVar) {
                this.f4516e = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f4516e.f4511h = true;
                c.this.f4512e.remove(this.f4516e);
            }
        }

        /* renamed from: a */
        public InterfaceC2153b m1718a(Runnable runnable, long j7) {
            EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
            if (this.f4515h) {
                return enumC0516d;
            }
            b bVar = new b(runnable, Long.valueOf(j7), this.f4514g.incrementAndGet());
            this.f4512e.add(bVar);
            if (this.f4513f.getAndIncrement() != 0) {
                return new C2155d(new a(bVar));
            }
            int iAddAndGet = 1;
            while (!this.f4515h) {
                b bVarPoll = this.f4512e.poll();
                if (bVarPoll == null) {
                    iAddAndGet = this.f4513f.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return enumC0516d;
                    }
                } else if (!bVarPoll.f4511h) {
                    bVarPoll.f4508e.run();
                }
            }
            this.f4512e.clear();
            return enumC0516d;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f4515h = true;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4515h;
        }

        @Override // p194y3.AbstractC2128t.c
        public InterfaceC2153b schedule(Runnable runnable) {
            return m1718a(runnable, now(TimeUnit.MILLISECONDS));
        }

        @Override // p194y3.AbstractC2128t.c
        public InterfaceC2153b schedule(Runnable runnable, long j7, TimeUnit timeUnit) {
            long millis = timeUnit.toMillis(j7) + now(TimeUnit.MILLISECONDS);
            return m1718a(new a(runnable, this, millis), millis);
        }
    }

    @Override // p194y3.AbstractC2128t
    public AbstractC2128t.c createWorker() {
        return new c();
    }

    @Override // p194y3.AbstractC2128t
    public InterfaceC2153b scheduleDirect(Runnable runnable) {
        Objects.requireNonNull(runnable, "run is null");
        runnable.run();
        return EnumC0516d.INSTANCE;
    }

    @Override // p194y3.AbstractC2128t
    public InterfaceC2153b scheduleDirect(Runnable runnable, long j7, TimeUnit timeUnit) throws InterruptedException {
        try {
            timeUnit.sleep(j7);
            Objects.requireNonNull(runnable, "run is null");
            runnable.run();
        } catch (InterruptedException e7) {
            Thread.currentThread().interrupt();
            C1908a.m2205b(e7);
        }
        return EnumC0516d.INSTANCE;
    }
}
