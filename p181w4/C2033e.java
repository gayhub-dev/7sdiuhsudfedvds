package p181w4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0516d;
import p032d4.C0871b;
import p040e4.InterfaceC0955h;
import p048f4.AbstractC0997b;
import p104m4.C1489c;
import p160t4.C1908a;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: UnicastSubject.java */
/* renamed from: w4.e */
/* loaded from: classes.dex */
public final class C2033e<T> extends AbstractC2032d<T> {

    /* renamed from: e */
    public final C1489c<T> f5924e;

    /* renamed from: f */
    public final AtomicReference<InterfaceC2127s<? super T>> f5925f;

    /* renamed from: g */
    public final AtomicReference<Runnable> f5926g;

    /* renamed from: h */
    public final boolean f5927h;

    /* renamed from: i */
    public volatile boolean f5928i;

    /* renamed from: j */
    public volatile boolean f5929j;

    /* renamed from: k */
    public Throwable f5930k;

    /* renamed from: l */
    public final AtomicBoolean f5931l;

    /* renamed from: m */
    public final AbstractC0997b<T> f5932m;

    /* renamed from: n */
    public boolean f5933n;

    /* compiled from: UnicastSubject.java */
    /* renamed from: w4.e$a */
    public final class a extends AbstractC0997b<T> {
        private static final long serialVersionUID = 7926949470189395511L;

        public a() {
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            if ((i7 & 2) == 0) {
                return 0;
            }
            C2033e.this.f5933n = true;
            return 2;
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            C2033e.this.f5924e.clear();
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (C2033e.this.f5928i) {
                return;
            }
            C2033e.this.f5928i = true;
            C2033e.this.m2389d();
            C2033e.this.f5925f.lazySet(null);
            if (C2033e.this.f5932m.getAndIncrement() == 0) {
                C2033e.this.f5925f.lazySet(null);
                C2033e.this.f5924e.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return C2033e.this.f5928i;
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return C2033e.this.f5924e.isEmpty();
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            return C2033e.this.f5924e.poll();
        }
    }

    public C2033e(int i7, boolean z6) {
        C0871b.m677b(i7, "capacityHint");
        this.f5924e = new C1489c<>(i7);
        this.f5926g = new AtomicReference<>();
        this.f5927h = z6;
        this.f5925f = new AtomicReference<>();
        this.f5931l = new AtomicBoolean();
        this.f5932m = new a();
    }

    /* renamed from: b */
    public static <T> C2033e<T> m2387b(int i7) {
        return new C2033e<>(i7, true);
    }

    /* renamed from: c */
    public static <T> C2033e<T> m2388c(int i7, Runnable runnable) {
        return new C2033e<>(i7, runnable, true);
    }

    /* renamed from: d */
    public void m2389d() {
        Runnable runnable = this.f5926g.get();
        if (runnable == null || !this.f5926g.compareAndSet(runnable, null)) {
            return;
        }
        runnable.run();
    }

    /* renamed from: e */
    public void m2390e() {
        if (this.f5932m.getAndIncrement() != 0) {
            return;
        }
        InterfaceC2127s<? super T> interfaceC2127s = this.f5925f.get();
        int iAddAndGet = 1;
        int iAddAndGet2 = 1;
        while (interfaceC2127s == null) {
            iAddAndGet2 = this.f5932m.addAndGet(-iAddAndGet2);
            if (iAddAndGet2 == 0) {
                return;
            } else {
                interfaceC2127s = this.f5925f.get();
            }
        }
        if (this.f5933n) {
            C1489c<T> c1489c = this.f5924e;
            boolean z6 = !this.f5927h;
            while (!this.f5928i) {
                boolean z7 = this.f5929j;
                if (z6 && z7 && m2391f(c1489c, interfaceC2127s)) {
                    return;
                }
                interfaceC2127s.onNext(null);
                if (z7) {
                    this.f5925f.lazySet(null);
                    Throwable th = this.f5930k;
                    if (th != null) {
                        interfaceC2127s.onError(th);
                        return;
                    } else {
                        interfaceC2127s.onComplete();
                        return;
                    }
                }
                iAddAndGet = this.f5932m.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
            this.f5925f.lazySet(null);
            c1489c.clear();
            return;
        }
        C1489c<T> c1489c2 = this.f5924e;
        boolean z8 = !this.f5927h;
        boolean z9 = true;
        int iAddAndGet3 = 1;
        while (!this.f5928i) {
            boolean z10 = this.f5929j;
            T tPoll = this.f5924e.poll();
            boolean z11 = tPoll == null;
            if (z10) {
                if (z8 && z9) {
                    if (m2391f(c1489c2, interfaceC2127s)) {
                        return;
                    } else {
                        z9 = false;
                    }
                }
                if (z11) {
                    this.f5925f.lazySet(null);
                    Throwable th2 = this.f5930k;
                    if (th2 != null) {
                        interfaceC2127s.onError(th2);
                        return;
                    } else {
                        interfaceC2127s.onComplete();
                        return;
                    }
                }
            }
            if (z11) {
                iAddAndGet3 = this.f5932m.addAndGet(-iAddAndGet3);
                if (iAddAndGet3 == 0) {
                    return;
                }
            } else {
                interfaceC2127s.onNext(tPoll);
            }
        }
        this.f5925f.lazySet(null);
        c1489c2.clear();
    }

    /* renamed from: f */
    public boolean m2391f(InterfaceC0955h<T> interfaceC0955h, InterfaceC2127s<? super T> interfaceC2127s) {
        Throwable th = this.f5930k;
        if (th == null) {
            return false;
        }
        this.f5925f.lazySet(null);
        ((C1489c) interfaceC0955h).clear();
        interfaceC2127s.onError(th);
        return true;
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        if (this.f5929j || this.f5928i) {
            return;
        }
        this.f5929j = true;
        m2389d();
        m2390e();
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        Objects.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f5929j || this.f5928i) {
            C1908a.m2205b(th);
            return;
        }
        this.f5930k = th;
        this.f5929j = true;
        m2389d();
        m2390e();
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        Objects.requireNonNull(t6, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f5929j || this.f5928i) {
            return;
        }
        this.f5924e.offer(t6);
        m2390e();
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (this.f5929j || this.f5928i) {
            interfaceC2153b.dispose();
        }
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        if (this.f5931l.get() || !this.f5931l.compareAndSet(false, true)) {
            IllegalStateException illegalStateException = new IllegalStateException("Only a single observer allowed.");
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onError(illegalStateException);
        } else {
            interfaceC2127s.onSubscribe(this.f5932m);
            this.f5925f.lazySet(interfaceC2127s);
            if (this.f5928i) {
                this.f5925f.lazySet(null);
            } else {
                m2390e();
            }
        }
    }

    public C2033e(int i7, Runnable runnable, boolean z6) {
        C0871b.m677b(i7, "capacityHint");
        this.f5924e = new C1489c<>(i7);
        Objects.requireNonNull(runnable, "onTerminate");
        this.f5926g = new AtomicReference<>(runnable);
        this.f5927h = z6;
        this.f5925f = new AtomicReference<>();
        this.f5931l = new AtomicBoolean();
        this.f5932m = new a();
    }
}
