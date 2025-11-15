package p088k4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p022c4.EnumC0515c;
import p104m4.C1489c;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSkipLastTimed.java */
/* renamed from: k4.r3 */
/* loaded from: classes.dex */
public final class C1344r3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f3634f;

    /* renamed from: g */
    public final TimeUnit f3635g;

    /* renamed from: h */
    public final AbstractC2128t f3636h;

    /* renamed from: i */
    public final int f3637i;

    /* renamed from: j */
    public final boolean f3638j;

    /* compiled from: ObservableSkipLastTimed.java */
    /* renamed from: k4.r3$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -5677354903406201275L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3639e;

        /* renamed from: f */
        public final long f3640f;

        /* renamed from: g */
        public final TimeUnit f3641g;

        /* renamed from: h */
        public final AbstractC2128t f3642h;

        /* renamed from: i */
        public final C1489c<Object> f3643i;

        /* renamed from: j */
        public final boolean f3644j;

        /* renamed from: k */
        public InterfaceC2153b f3645k;

        /* renamed from: l */
        public volatile boolean f3646l;

        /* renamed from: m */
        public volatile boolean f3647m;

        /* renamed from: n */
        public Throwable f3648n;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, int i7, boolean z6) {
            this.f3639e = interfaceC2127s;
            this.f3640f = j7;
            this.f3641g = timeUnit;
            this.f3642h = abstractC2128t;
            this.f3643i = new C1489c<>(i7);
            this.f3644j = z6;
        }

        /* renamed from: a */
        public void m1530a() {
            if (getAndIncrement() != 0) {
                return;
            }
            InterfaceC2127s<? super T> interfaceC2127s = this.f3639e;
            C1489c<Object> c1489c = this.f3643i;
            boolean z6 = this.f3644j;
            TimeUnit timeUnit = this.f3641g;
            AbstractC2128t abstractC2128t = this.f3642h;
            long j7 = this.f3640f;
            int iAddAndGet = 1;
            while (!this.f3646l) {
                boolean z7 = this.f3647m;
                Long l7 = (Long) c1489c.m1650e();
                boolean z8 = l7 == null;
                long jNow = abstractC2128t.now(timeUnit);
                if (!z8 && l7.longValue() > jNow - j7) {
                    z8 = true;
                }
                if (z7) {
                    if (!z6) {
                        Throwable th = this.f3648n;
                        if (th != null) {
                            this.f3643i.clear();
                            interfaceC2127s.onError(th);
                            return;
                        } else if (z8) {
                            interfaceC2127s.onComplete();
                            return;
                        }
                    } else if (z8) {
                        Throwable th2 = this.f3648n;
                        if (th2 != null) {
                            interfaceC2127s.onError(th2);
                            return;
                        } else {
                            interfaceC2127s.onComplete();
                            return;
                        }
                    }
                }
                if (z8) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    c1489c.poll();
                    interfaceC2127s.onNext(c1489c.poll());
                }
            }
            this.f3643i.clear();
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3646l) {
                return;
            }
            this.f3646l = true;
            this.f3645k.dispose();
            if (getAndIncrement() == 0) {
                this.f3643i.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3646l;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3647m = true;
            m1530a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3648n = th;
            this.f3647m = true;
            m1530a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3643i.m1649d(Long.valueOf(this.f3642h.now(this.f3641g)), t6);
            m1530a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3645k, interfaceC2153b)) {
                this.f3645k = interfaceC2153b;
                this.f3639e.onSubscribe(this);
            }
        }
    }

    public C1344r3(InterfaceC2125q<T> interfaceC2125q, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, int i7, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3634f = j7;
        this.f3635g = timeUnit;
        this.f3636h = abstractC2128t;
        this.f3637i = i7;
        this.f3638j = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3634f, this.f3635g, this.f3636h, this.f3637i, this.f3638j));
    }
}
