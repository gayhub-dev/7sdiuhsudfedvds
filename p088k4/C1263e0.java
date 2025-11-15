package p088k4;

import java.util.concurrent.TimeUnit;
import p022c4.EnumC0515c;
import p153s4.C1882e;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableDelay.java */
/* renamed from: k4.e0 */
/* loaded from: classes.dex */
public final class C1263e0<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f2970f;

    /* renamed from: g */
    public final TimeUnit f2971g;

    /* renamed from: h */
    public final AbstractC2128t f2972h;

    /* renamed from: i */
    public final boolean f2973i;

    /* compiled from: ObservableDelay.java */
    /* renamed from: k4.e0$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2974e;

        /* renamed from: f */
        public final long f2975f;

        /* renamed from: g */
        public final TimeUnit f2976g;

        /* renamed from: h */
        public final AbstractC2128t.c f2977h;

        /* renamed from: i */
        public final boolean f2978i;

        /* renamed from: j */
        public InterfaceC2153b f2979j;

        /* compiled from: ObservableDelay.java */
        /* renamed from: k4.e0$a$a, reason: collision with other inner class name */
        public final class RunnableC2174a implements Runnable {
            public RunnableC2174a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.f2974e.onComplete();
                } finally {
                    a.this.f2977h.dispose();
                }
            }
        }

        /* compiled from: ObservableDelay.java */
        /* renamed from: k4.e0$a$b */
        public final class b implements Runnable {

            /* renamed from: e */
            public final Throwable f2981e;

            public b(Throwable th) {
                this.f2981e = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.f2974e.onError(this.f2981e);
                } finally {
                    a.this.f2977h.dispose();
                }
            }
        }

        /* compiled from: ObservableDelay.java */
        /* renamed from: k4.e0$a$c */
        public final class c implements Runnable {

            /* renamed from: e */
            public final T f2983e;

            public c(T t6) {
                this.f2983e = t6;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2974e.onNext(this.f2983e);
            }
        }

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t.c cVar, boolean z6) {
            this.f2974e = interfaceC2127s;
            this.f2975f = j7;
            this.f2976g = timeUnit;
            this.f2977h = cVar;
            this.f2978i = z6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2979j.dispose();
            this.f2977h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2977h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2977h.schedule(new RunnableC2174a(), this.f2975f, this.f2976g);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f2977h.schedule(new b(th), this.f2978i ? this.f2975f : 0L, this.f2976g);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f2977h.schedule(new c(t6), this.f2975f, this.f2976g);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2979j, interfaceC2153b)) {
                this.f2979j = interfaceC2153b;
                this.f2974e.onSubscribe(this);
            }
        }
    }

    public C1263e0(InterfaceC2125q<T> interfaceC2125q, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f2970f = j7;
        this.f2971g = timeUnit;
        this.f2972h = abstractC2128t;
        this.f2973i = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(this.f2973i ? interfaceC2127s : new C1882e(interfaceC2127s), this.f2970f, this.f2971g, this.f2972h.createWorker(), this.f2973i));
    }
}
