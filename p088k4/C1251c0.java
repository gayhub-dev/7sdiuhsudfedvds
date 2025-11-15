package p088k4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p153s4.C1882e;
import p160t4.C1908a;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableDebounceTimed.java */
/* renamed from: k4.c0 */
/* loaded from: classes.dex */
public final class C1251c0<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f2900f;

    /* renamed from: g */
    public final TimeUnit f2901g;

    /* renamed from: h */
    public final AbstractC2128t f2902h;

    /* compiled from: ObservableDebounceTimed.java */
    /* renamed from: k4.c0$a */
    public static final class a<T> extends AtomicReference<InterfaceC2153b> implements Runnable, InterfaceC2153b {
        private static final long serialVersionUID = 6812032969491025141L;

        /* renamed from: e */
        public final T f2903e;

        /* renamed from: f */
        public final long f2904f;

        /* renamed from: g */
        public final b<T> f2905g;

        /* renamed from: h */
        public final AtomicBoolean f2906h = new AtomicBoolean();

        public a(T t6, long j7, b<T> bVar) {
            this.f2903e = t6;
            this.f2904f = j7;
            this.f2905g = bVar;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get() == EnumC0515c.DISPOSED;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2906h.compareAndSet(false, true)) {
                b<T> bVar = this.f2905g;
                long j7 = this.f2904f;
                T t6 = this.f2903e;
                if (j7 == bVar.f2913k) {
                    bVar.f2907e.onNext(t6);
                    EnumC0515c.m323a(this);
                }
            }
        }
    }

    /* compiled from: ObservableDebounceTimed.java */
    /* renamed from: k4.c0$b */
    public static final class b<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2907e;

        /* renamed from: f */
        public final long f2908f;

        /* renamed from: g */
        public final TimeUnit f2909g;

        /* renamed from: h */
        public final AbstractC2128t.c f2910h;

        /* renamed from: i */
        public InterfaceC2153b f2911i;

        /* renamed from: j */
        public InterfaceC2153b f2912j;

        /* renamed from: k */
        public volatile long f2913k;

        /* renamed from: l */
        public boolean f2914l;

        public b(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t.c cVar) {
            this.f2907e = interfaceC2127s;
            this.f2908f = j7;
            this.f2909g = timeUnit;
            this.f2910h = cVar;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2911i.dispose();
            this.f2910h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2910h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f2914l) {
                return;
            }
            this.f2914l = true;
            InterfaceC2153b interfaceC2153b = this.f2912j;
            if (interfaceC2153b != null) {
                interfaceC2153b.dispose();
            }
            a aVar = (a) interfaceC2153b;
            if (aVar != null) {
                aVar.run();
            }
            this.f2907e.onComplete();
            this.f2910h.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f2914l) {
                C1908a.m2205b(th);
                return;
            }
            InterfaceC2153b interfaceC2153b = this.f2912j;
            if (interfaceC2153b != null) {
                interfaceC2153b.dispose();
            }
            this.f2914l = true;
            this.f2907e.onError(th);
            this.f2910h.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f2914l) {
                return;
            }
            long j7 = this.f2913k + 1;
            this.f2913k = j7;
            InterfaceC2153b interfaceC2153b = this.f2912j;
            if (interfaceC2153b != null) {
                interfaceC2153b.dispose();
            }
            a aVar = new a(t6, j7, this);
            this.f2912j = aVar;
            EnumC0515c.m325c(aVar, this.f2910h.schedule(aVar, this.f2908f, this.f2909g));
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2911i, interfaceC2153b)) {
                this.f2911i = interfaceC2153b;
                this.f2907e.onSubscribe(this);
            }
        }
    }

    public C1251c0(InterfaceC2125q<T> interfaceC2125q, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f2900f = j7;
        this.f2901g = timeUnit;
        this.f2902h = abstractC2128t;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new b(new C1882e(interfaceC2127s), this.f2900f, this.f2901g, this.f2902h.createWorker()));
    }
}
