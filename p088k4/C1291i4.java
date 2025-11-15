package p088k4;

import android.support.v7.widget.RecyclerView;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.C0518f;
import p022c4.EnumC0515c;
import p138q4.C1774f;
import p160t4.C1908a;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTimeoutTimed.java */
/* renamed from: k4.i4 */
/* loaded from: classes.dex */
public final class C1291i4<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f3197f;

    /* renamed from: g */
    public final TimeUnit f3198g;

    /* renamed from: h */
    public final AbstractC2128t f3199h;

    /* renamed from: i */
    public final InterfaceC2125q<? extends T> f3200i;

    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: k4.i4$a */
    public static final class a<T> implements InterfaceC2127s<T> {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3201e;

        /* renamed from: f */
        public final AtomicReference<InterfaceC2153b> f3202f;

        public a(InterfaceC2127s<? super T> interfaceC2127s, AtomicReference<InterfaceC2153b> atomicReference) {
            this.f3201e = interfaceC2127s;
            this.f3202f = atomicReference;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3201e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3201e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3201e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m325c(this.f3202f, interfaceC2153b);
        }
    }

    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: k4.i4$b */
    public static final class b<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b, d {
        private static final long serialVersionUID = 3764492702657003550L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3203e;

        /* renamed from: f */
        public final long f3204f;

        /* renamed from: g */
        public final TimeUnit f3205g;

        /* renamed from: h */
        public final AbstractC2128t.c f3206h;

        /* renamed from: i */
        public final C0518f f3207i = new C0518f();

        /* renamed from: j */
        public final AtomicLong f3208j = new AtomicLong();

        /* renamed from: k */
        public final AtomicReference<InterfaceC2153b> f3209k = new AtomicReference<>();

        /* renamed from: l */
        public InterfaceC2125q<? extends T> f3210l;

        public b(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t.c cVar, InterfaceC2125q<? extends T> interfaceC2125q) {
            this.f3203e = interfaceC2127s;
            this.f3204f = j7;
            this.f3205g = timeUnit;
            this.f3206h = cVar;
            this.f3210l = interfaceC2125q;
        }

        @Override // p088k4.C1291i4.d
        /* renamed from: b */
        public void mo1509b(long j7) {
            if (this.f3208j.compareAndSet(j7, RecyclerView.FOREVER_NS)) {
                EnumC0515c.m323a(this.f3209k);
                InterfaceC2125q<? extends T> interfaceC2125q = this.f3210l;
                this.f3210l = null;
                interfaceC2125q.subscribe(new a(this.f3203e, this));
                this.f3206h.dispose();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3209k);
            EnumC0515c.m323a(this);
            this.f3206h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3208j.getAndSet(RecyclerView.FOREVER_NS) != RecyclerView.FOREVER_NS) {
                EnumC0515c.m323a(this.f3207i);
                this.f3203e.onComplete();
                this.f3206h.dispose();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3208j.getAndSet(RecyclerView.FOREVER_NS) == RecyclerView.FOREVER_NS) {
                C1908a.m2205b(th);
                return;
            }
            EnumC0515c.m323a(this.f3207i);
            this.f3203e.onError(th);
            this.f3206h.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            long j7 = this.f3208j.get();
            if (j7 != RecyclerView.FOREVER_NS) {
                long j8 = 1 + j7;
                if (this.f3208j.compareAndSet(j7, j8)) {
                    this.f3207i.get().dispose();
                    this.f3203e.onNext(t6);
                    EnumC0515c.m325c(this.f3207i, this.f3206h.schedule(new e(j8, this), this.f3204f, this.f3205g));
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3209k, interfaceC2153b);
        }
    }

    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: k4.i4$c */
    public static final class c<T> extends AtomicLong implements InterfaceC2127s<T>, InterfaceC2153b, d {
        private static final long serialVersionUID = 3764492702657003550L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3211e;

        /* renamed from: f */
        public final long f3212f;

        /* renamed from: g */
        public final TimeUnit f3213g;

        /* renamed from: h */
        public final AbstractC2128t.c f3214h;

        /* renamed from: i */
        public final C0518f f3215i = new C0518f();

        /* renamed from: j */
        public final AtomicReference<InterfaceC2153b> f3216j = new AtomicReference<>();

        public c(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t.c cVar) {
            this.f3211e = interfaceC2127s;
            this.f3212f = j7;
            this.f3213g = timeUnit;
            this.f3214h = cVar;
        }

        @Override // p088k4.C1291i4.d
        /* renamed from: b */
        public void mo1509b(long j7) {
            if (compareAndSet(j7, RecyclerView.FOREVER_NS)) {
                EnumC0515c.m323a(this.f3216j);
                this.f3211e.onError(new TimeoutException(C1774f.m1960c(this.f3212f, this.f3213g)));
                this.f3214h.dispose();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3216j);
            this.f3214h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f3216j.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (getAndSet(RecyclerView.FOREVER_NS) != RecyclerView.FOREVER_NS) {
                EnumC0515c.m323a(this.f3215i);
                this.f3211e.onComplete();
                this.f3214h.dispose();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (getAndSet(RecyclerView.FOREVER_NS) == RecyclerView.FOREVER_NS) {
                C1908a.m2205b(th);
                return;
            }
            EnumC0515c.m323a(this.f3215i);
            this.f3211e.onError(th);
            this.f3214h.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            long j7 = get();
            if (j7 != RecyclerView.FOREVER_NS) {
                long j8 = 1 + j7;
                if (compareAndSet(j7, j8)) {
                    this.f3215i.get().dispose();
                    this.f3211e.onNext(t6);
                    EnumC0515c.m325c(this.f3215i, this.f3214h.schedule(new e(j8, this), this.f3212f, this.f3213g));
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3216j, interfaceC2153b);
        }
    }

    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: k4.i4$d */
    public interface d {
        /* renamed from: b */
        void mo1509b(long j7);
    }

    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: k4.i4$e */
    public static final class e implements Runnable {

        /* renamed from: e */
        public final d f3217e;

        /* renamed from: f */
        public final long f3218f;

        public e(long j7, d dVar) {
            this.f3218f = j7;
            this.f3217e = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f3217e.mo1509b(this.f3218f);
        }
    }

    public C1291i4(AbstractC2120l<T> abstractC2120l, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, InterfaceC2125q<? extends T> interfaceC2125q) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3197f = j7;
        this.f3198g = timeUnit;
        this.f3199h = abstractC2128t;
        this.f3200i = interfaceC2125q;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        if (this.f3200i == null) {
            c cVar = new c(interfaceC2127s, this.f3197f, this.f3198g, this.f3199h.createWorker());
            interfaceC2127s.onSubscribe(cVar);
            EnumC0515c.m325c(cVar.f3215i, cVar.f3214h.schedule(new e(0L, cVar), cVar.f3212f, cVar.f3213g));
            this.f2772e.subscribe(cVar);
            return;
        }
        b bVar = new b(interfaceC2127s, this.f3197f, this.f3198g, this.f3199h.createWorker(), this.f3200i);
        interfaceC2127s.onSubscribe(bVar);
        EnumC0515c.m325c(bVar.f3207i, bVar.f3206h.schedule(new e(0L, bVar), bVar.f3204f, bVar.f3205g));
        this.f2772e.subscribe(bVar);
    }
}
