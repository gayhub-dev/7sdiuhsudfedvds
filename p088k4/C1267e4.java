package p088k4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p153s4.C1882e;
import p160t4.C1908a;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableThrottleFirstTimed.java */
/* renamed from: k4.e4 */
/* loaded from: classes.dex */
public final class C1267e4<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f3010f;

    /* renamed from: g */
    public final TimeUnit f3011g;

    /* renamed from: h */
    public final AbstractC2128t f3012h;

    /* compiled from: ObservableThrottleFirstTimed.java */
    /* renamed from: k4.e4$a */
    public static final class a<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b, Runnable {
        private static final long serialVersionUID = 786994795061867455L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3013e;

        /* renamed from: f */
        public final long f3014f;

        /* renamed from: g */
        public final TimeUnit f3015g;

        /* renamed from: h */
        public final AbstractC2128t.c f3016h;

        /* renamed from: i */
        public InterfaceC2153b f3017i;

        /* renamed from: j */
        public volatile boolean f3018j;

        /* renamed from: k */
        public boolean f3019k;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t.c cVar) {
            this.f3013e = interfaceC2127s;
            this.f3014f = j7;
            this.f3015g = timeUnit;
            this.f3016h = cVar;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3017i.dispose();
            this.f3016h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3016h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3019k) {
                return;
            }
            this.f3019k = true;
            this.f3013e.onComplete();
            this.f3016h.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3019k) {
                C1908a.m2205b(th);
                return;
            }
            this.f3019k = true;
            this.f3013e.onError(th);
            this.f3016h.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3018j || this.f3019k) {
                return;
            }
            this.f3018j = true;
            this.f3013e.onNext(t6);
            InterfaceC2153b interfaceC2153b = get();
            if (interfaceC2153b != null) {
                interfaceC2153b.dispose();
            }
            EnumC0515c.m325c(this, this.f3016h.schedule(this, this.f3014f, this.f3015g));
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3017i, interfaceC2153b)) {
                this.f3017i = interfaceC2153b;
                this.f3013e.onSubscribe(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f3018j = false;
        }
    }

    public C1267e4(InterfaceC2125q<T> interfaceC2125q, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3010f = j7;
        this.f3011g = timeUnit;
        this.f3012h = abstractC2128t;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(new C1882e(interfaceC2127s), this.f3010f, this.f3011g, this.f3012h.createWorker()));
    }
}
