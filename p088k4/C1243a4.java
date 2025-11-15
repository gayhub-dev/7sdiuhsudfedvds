package p088k4;

import android.support.v7.widget.RecyclerView;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import p022c4.EnumC0515c;
import p104m4.C1489c;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTakeLastTimed.java */
/* renamed from: k4.a4 */
/* loaded from: classes.dex */
public final class C1243a4<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f2795f;

    /* renamed from: g */
    public final long f2796g;

    /* renamed from: h */
    public final TimeUnit f2797h;

    /* renamed from: i */
    public final AbstractC2128t f2798i;

    /* renamed from: j */
    public final int f2799j;

    /* renamed from: k */
    public final boolean f2800k;

    /* compiled from: ObservableTakeLastTimed.java */
    /* renamed from: k4.a4$a */
    public static final class a<T> extends AtomicBoolean implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -5677354903406201275L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2801e;

        /* renamed from: f */
        public final long f2802f;

        /* renamed from: g */
        public final long f2803g;

        /* renamed from: h */
        public final TimeUnit f2804h;

        /* renamed from: i */
        public final AbstractC2128t f2805i;

        /* renamed from: j */
        public final C1489c<Object> f2806j;

        /* renamed from: k */
        public final boolean f2807k;

        /* renamed from: l */
        public InterfaceC2153b f2808l;

        /* renamed from: m */
        public volatile boolean f2809m;

        /* renamed from: n */
        public Throwable f2810n;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t, int i7, boolean z6) {
            this.f2801e = interfaceC2127s;
            this.f2802f = j7;
            this.f2803g = j8;
            this.f2804h = timeUnit;
            this.f2805i = abstractC2128t;
            this.f2806j = new C1489c<>(i7);
            this.f2807k = z6;
        }

        /* renamed from: a */
        public void m1463a() {
            Throwable th;
            if (compareAndSet(false, true)) {
                InterfaceC2127s<? super T> interfaceC2127s = this.f2801e;
                C1489c<Object> c1489c = this.f2806j;
                boolean z6 = this.f2807k;
                while (!this.f2809m) {
                    if (!z6 && (th = this.f2810n) != null) {
                        c1489c.clear();
                        interfaceC2127s.onError(th);
                        return;
                    }
                    Object objPoll = c1489c.poll();
                    if (objPoll == null) {
                        Throwable th2 = this.f2810n;
                        if (th2 != null) {
                            interfaceC2127s.onError(th2);
                            return;
                        } else {
                            interfaceC2127s.onComplete();
                            return;
                        }
                    }
                    Object objPoll2 = c1489c.poll();
                    if (((Long) objPoll).longValue() >= this.f2805i.now(this.f2804h) - this.f2803g) {
                        interfaceC2127s.onNext(objPoll2);
                    }
                }
                c1489c.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f2809m) {
                return;
            }
            this.f2809m = true;
            this.f2808l.dispose();
            if (compareAndSet(false, true)) {
                this.f2806j.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2809m;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            m1463a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f2810n = th;
            m1463a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            long jM1648b;
            long jM1647a;
            C1489c<Object> c1489c = this.f2806j;
            long jNow = this.f2805i.now(this.f2804h);
            long j7 = this.f2803g;
            long j8 = this.f2802f;
            boolean z6 = j8 == RecyclerView.FOREVER_NS;
            c1489c.m1649d(Long.valueOf(jNow), t6);
            while (!c1489c.isEmpty()) {
                if (((Long) c1489c.m1650e()).longValue() > jNow - j7) {
                    if (z6) {
                        return;
                    }
                    long jM1647a2 = c1489c.m1647a();
                    while (true) {
                        jM1648b = c1489c.m1648b();
                        jM1647a = c1489c.m1647a();
                        if (jM1647a2 == jM1647a) {
                            break;
                        } else {
                            jM1647a2 = jM1647a;
                        }
                    }
                    if ((((int) (jM1648b - jM1647a)) >> 1) <= j8) {
                        return;
                    }
                }
                c1489c.poll();
                c1489c.poll();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2808l, interfaceC2153b)) {
                this.f2808l = interfaceC2153b;
                this.f2801e.onSubscribe(this);
            }
        }
    }

    public C1243a4(InterfaceC2125q<T> interfaceC2125q, long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t, int i7, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f2795f = j7;
        this.f2796g = j8;
        this.f2797h = timeUnit;
        this.f2798i = abstractC2128t;
        this.f2799j = i7;
        this.f2800k = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f2795f, this.f2796g, this.f2797h, this.f2798i, this.f2799j, this.f2800k));
    }
}
