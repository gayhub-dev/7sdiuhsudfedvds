package p088k4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p000a.C0000a;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0954g;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p194y3.InterfaceC2131w;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableMergeWithSingle.java */
/* renamed from: k4.l2 */
/* loaded from: classes.dex */
public final class C1307l2<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2131w<? extends T> f3335f;

    /* compiled from: ObservableMergeWithSingle.java */
    /* renamed from: k4.l2$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -4592979584110982903L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3336e;

        /* renamed from: f */
        public final AtomicReference<InterfaceC2153b> f3337f = new AtomicReference<>();

        /* renamed from: g */
        public final C2180a<T> f3338g = new C2180a<>(this);

        /* renamed from: h */
        public final C1771c f3339h = new C1771c();

        /* renamed from: i */
        public volatile InterfaceC0954g<T> f3340i;

        /* renamed from: j */
        public T f3341j;

        /* renamed from: k */
        public volatile boolean f3342k;

        /* renamed from: l */
        public volatile boolean f3343l;

        /* renamed from: m */
        public volatile int f3344m;

        /* compiled from: ObservableMergeWithSingle.java */
        /* renamed from: k4.l2$a$a, reason: collision with other inner class name */
        public static final class C2180a<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2130v<T> {
            private static final long serialVersionUID = -2935427570954647017L;

            /* renamed from: e */
            public final a<T> f3345e;

            public C2180a(a<T> aVar) {
                this.f3345e = aVar;
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2117i
            /* renamed from: a */
            public void mo1016a(T t6) {
                a<T> aVar = this.f3345e;
                if (aVar.compareAndSet(0, 1)) {
                    aVar.f3336e.onNext(t6);
                    aVar.f3344m = 2;
                } else {
                    aVar.f3341j = t6;
                    aVar.f3344m = 1;
                    if (aVar.getAndIncrement() != 0) {
                        return;
                    }
                }
                aVar.m1519b();
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a<T> aVar = this.f3345e;
                if (!C1774f.m1958a(aVar.f3339h, th)) {
                    C1908a.m2205b(th);
                } else {
                    EnumC0515c.m323a(aVar.f3337f);
                    aVar.m1518a();
                }
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3336e = interfaceC2127s;
        }

        /* renamed from: a */
        public void m1518a() {
            if (getAndIncrement() == 0) {
                m1519b();
            }
        }

        /* renamed from: b */
        public void m1519b() {
            InterfaceC2127s<? super T> interfaceC2127s = this.f3336e;
            int iAddAndGet = 1;
            while (!this.f3342k) {
                if (this.f3339h.get() != null) {
                    this.f3341j = null;
                    this.f3340i = null;
                    interfaceC2127s.onError(C1774f.m1959b(this.f3339h));
                    return;
                }
                int i7 = this.f3344m;
                if (i7 == 1) {
                    T t6 = this.f3341j;
                    this.f3341j = null;
                    this.f3344m = 2;
                    interfaceC2127s.onNext(t6);
                    i7 = 2;
                }
                boolean z6 = this.f3343l;
                InterfaceC0954g<T> interfaceC0954g = this.f3340i;
                C0000a c0000aPoll = interfaceC0954g != null ? interfaceC0954g.poll() : null;
                boolean z7 = c0000aPoll == null;
                if (z6 && z7 && i7 == 2) {
                    this.f3340i = null;
                    interfaceC2127s.onComplete();
                    return;
                } else if (z7) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    interfaceC2127s.onNext(c0000aPoll);
                }
            }
            this.f3341j = null;
            this.f3340i = null;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3342k = true;
            EnumC0515c.m323a(this.f3337f);
            EnumC0515c.m323a(this.f3338g);
            if (getAndIncrement() == 0) {
                this.f3340i = null;
                this.f3341j = null;
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f3337f.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3343l = true;
            m1518a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f3339h, th)) {
                C1908a.m2205b(th);
            } else {
                EnumC0515c.m323a(this.f3337f);
                m1518a();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (compareAndSet(0, 1)) {
                this.f3336e.onNext(t6);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                C1489c c1489c = this.f3340i;
                if (c1489c == null) {
                    c1489c = new C1489c(AbstractC2120l.bufferSize());
                    this.f3340i = c1489c;
                }
                c1489c.offer(t6);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            m1519b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3337f, interfaceC2153b);
        }
    }

    public C1307l2(AbstractC2120l<T> abstractC2120l, InterfaceC2131w<? extends T> interfaceC2131w) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3335f = interfaceC2131w;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        a aVar = new a(interfaceC2127s);
        interfaceC2127s.onSubscribe(aVar);
        this.f2772e.subscribe(aVar);
        this.f3335f.mo2562b(aVar.f3338g);
    }
}
