package p088k4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p153s4.C1882e;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSampleTimed.java */
/* renamed from: k4.f3 */
/* loaded from: classes.dex */
public final class C1272f3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f3056f;

    /* renamed from: g */
    public final TimeUnit f3057g;

    /* renamed from: h */
    public final AbstractC2128t f3058h;

    /* renamed from: i */
    public final boolean f3059i;

    /* compiled from: ObservableSampleTimed.java */
    /* renamed from: k4.f3$a */
    public static final class a<T> extends c<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        /* renamed from: k */
        public final AtomicInteger f3060k;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
            super(interfaceC2127s, j7, timeUnit, abstractC2128t);
            this.f3060k = new AtomicInteger(1);
        }

        @Override // p088k4.C1272f3.c
        /* renamed from: a */
        public void mo1496a() {
            m1497b();
            if (this.f3060k.decrementAndGet() == 0) {
                this.f3061e.onComplete();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3060k.incrementAndGet() == 2) {
                m1497b();
                if (this.f3060k.decrementAndGet() == 0) {
                    this.f3061e.onComplete();
                }
            }
        }
    }

    /* compiled from: ObservableSampleTimed.java */
    /* renamed from: k4.f3$b */
    public static final class b<T> extends c<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        public b(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
            super(interfaceC2127s, j7, timeUnit, abstractC2128t);
        }

        @Override // p088k4.C1272f3.c
        /* renamed from: a */
        public void mo1496a() {
            this.f3061e.onComplete();
        }

        @Override // java.lang.Runnable
        public void run() {
            m1497b();
        }
    }

    /* compiled from: ObservableSampleTimed.java */
    /* renamed from: k4.f3$c */
    public static abstract class c<T> extends AtomicReference<T> implements InterfaceC2127s<T>, InterfaceC2153b, Runnable {
        private static final long serialVersionUID = -3517602651313910099L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3061e;

        /* renamed from: f */
        public final long f3062f;

        /* renamed from: g */
        public final TimeUnit f3063g;

        /* renamed from: h */
        public final AbstractC2128t f3064h;

        /* renamed from: i */
        public final AtomicReference<InterfaceC2153b> f3065i = new AtomicReference<>();

        /* renamed from: j */
        public InterfaceC2153b f3066j;

        public c(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
            this.f3061e = interfaceC2127s;
            this.f3062f = j7;
            this.f3063g = timeUnit;
            this.f3064h = abstractC2128t;
        }

        /* renamed from: a */
        public abstract void mo1496a();

        /* renamed from: b */
        public void m1497b() {
            T andSet = getAndSet(null);
            if (andSet != null) {
                this.f3061e.onNext(andSet);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3065i);
            this.f3066j.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3066j.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            EnumC0515c.m323a(this.f3065i);
            mo1496a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            EnumC0515c.m323a(this.f3065i);
            this.f3061e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            lazySet(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3066j, interfaceC2153b)) {
                this.f3066j = interfaceC2153b;
                this.f3061e.onSubscribe(this);
                AbstractC2128t abstractC2128t = this.f3064h;
                long j7 = this.f3062f;
                EnumC0515c.m325c(this.f3065i, abstractC2128t.schedulePeriodicallyDirect(this, j7, j7, this.f3063g));
            }
        }
    }

    public C1272f3(InterfaceC2125q<T> interfaceC2125q, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3056f = j7;
        this.f3057g = timeUnit;
        this.f3058h = abstractC2128t;
        this.f3059i = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C1882e c1882e = new C1882e(interfaceC2127s);
        if (this.f3059i) {
            this.f2772e.subscribe(new a(c1882e, this.f3056f, this.f3057g, this.f3058h));
        } else {
            this.f2772e.subscribe(new b(c1882e, this.f3056f, this.f3057g, this.f3058h));
        }
    }
}
