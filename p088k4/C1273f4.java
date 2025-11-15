package p088k4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableThrottleLatest.java */
/* renamed from: k4.f4 */
/* loaded from: classes.dex */
public final class C1273f4<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f3067f;

    /* renamed from: g */
    public final TimeUnit f3068g;

    /* renamed from: h */
    public final AbstractC2128t f3069h;

    /* renamed from: i */
    public final boolean f3070i;

    /* compiled from: ObservableThrottleLatest.java */
    /* renamed from: k4.f4$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b, Runnable {
        private static final long serialVersionUID = -8296689127439125014L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3071e;

        /* renamed from: f */
        public final long f3072f;

        /* renamed from: g */
        public final TimeUnit f3073g;

        /* renamed from: h */
        public final AbstractC2128t.c f3074h;

        /* renamed from: i */
        public final boolean f3075i;

        /* renamed from: j */
        public final AtomicReference<T> f3076j = new AtomicReference<>();

        /* renamed from: k */
        public InterfaceC2153b f3077k;

        /* renamed from: l */
        public volatile boolean f3078l;

        /* renamed from: m */
        public Throwable f3079m;

        /* renamed from: n */
        public volatile boolean f3080n;

        /* renamed from: o */
        public volatile boolean f3081o;

        /* renamed from: p */
        public boolean f3082p;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t.c cVar, boolean z6) {
            this.f3071e = interfaceC2127s;
            this.f3072f = j7;
            this.f3073g = timeUnit;
            this.f3074h = cVar;
            this.f3075i = z6;
        }

        /* renamed from: a */
        public void m1498a() {
            if (getAndIncrement() != 0) {
                return;
            }
            AtomicReference<T> atomicReference = this.f3076j;
            InterfaceC2127s<? super T> interfaceC2127s = this.f3071e;
            int iAddAndGet = 1;
            while (!this.f3080n) {
                boolean z6 = this.f3078l;
                if (z6 && this.f3079m != null) {
                    atomicReference.lazySet(null);
                    interfaceC2127s.onError(this.f3079m);
                    this.f3074h.dispose();
                    return;
                }
                boolean z7 = atomicReference.get() == null;
                if (z6) {
                    T andSet = atomicReference.getAndSet(null);
                    if (!z7 && this.f3075i) {
                        interfaceC2127s.onNext(andSet);
                    }
                    interfaceC2127s.onComplete();
                    this.f3074h.dispose();
                    return;
                }
                if (z7) {
                    if (this.f3081o) {
                        this.f3082p = false;
                        this.f3081o = false;
                    }
                } else if (!this.f3082p || this.f3081o) {
                    interfaceC2127s.onNext(atomicReference.getAndSet(null));
                    this.f3081o = false;
                    this.f3082p = true;
                    this.f3074h.schedule(this, this.f3072f, this.f3073g);
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
            atomicReference.lazySet(null);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3080n = true;
            this.f3077k.dispose();
            this.f3074h.dispose();
            if (getAndIncrement() == 0) {
                this.f3076j.lazySet(null);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3080n;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3078l = true;
            m1498a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3079m = th;
            this.f3078l = true;
            m1498a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3076j.set(t6);
            m1498a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3077k, interfaceC2153b)) {
                this.f3077k = interfaceC2153b;
                this.f3071e.onSubscribe(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f3081o = true;
            m1498a();
        }
    }

    public C1273f4(AbstractC2120l<T> abstractC2120l, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3067f = j7;
        this.f3068g = timeUnit;
        this.f3069h = abstractC2128t;
        this.f3070i = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3067f, this.f3068g, this.f3069h.createWorker(), this.f3070i));
    }
}
