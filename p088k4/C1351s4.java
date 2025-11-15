package p088k4;

import android.support.v7.widget.RecyclerView;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p048f4.AbstractC1011p;
import p104m4.C1487a;
import p153s4.C1882e;
import p181w4.C2033e;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableWindowTimed.java */
/* renamed from: k4.s4 */
/* loaded from: classes.dex */
public final class C1351s4<T> extends AbstractC1238a<T, AbstractC2120l<T>> {

    /* renamed from: f */
    public final long f3703f;

    /* renamed from: g */
    public final long f3704g;

    /* renamed from: h */
    public final TimeUnit f3705h;

    /* renamed from: i */
    public final AbstractC2128t f3706i;

    /* renamed from: j */
    public final long f3707j;

    /* renamed from: k */
    public final int f3708k;

    /* renamed from: l */
    public final boolean f3709l;

    /* compiled from: ObservableWindowTimed.java */
    /* renamed from: k4.s4$a */
    public static final class a<T> extends AbstractC1011p<T, Object, AbstractC2120l<T>> implements InterfaceC2153b {

        /* renamed from: k */
        public final long f3710k;

        /* renamed from: l */
        public final TimeUnit f3711l;

        /* renamed from: m */
        public final AbstractC2128t f3712m;

        /* renamed from: n */
        public final int f3713n;

        /* renamed from: o */
        public final boolean f3714o;

        /* renamed from: p */
        public final long f3715p;

        /* renamed from: q */
        public final AbstractC2128t.c f3716q;

        /* renamed from: r */
        public long f3717r;

        /* renamed from: s */
        public long f3718s;

        /* renamed from: t */
        public InterfaceC2153b f3719t;

        /* renamed from: u */
        public C2033e<T> f3720u;

        /* renamed from: v */
        public volatile boolean f3721v;

        /* renamed from: w */
        public final AtomicReference<InterfaceC2153b> f3722w;

        /* compiled from: ObservableWindowTimed.java */
        /* renamed from: k4.s4$a$a, reason: collision with other inner class name */
        public static final class RunnableC2182a implements Runnable {

            /* renamed from: e */
            public final long f3723e;

            /* renamed from: f */
            public final a<?> f3724f;

            public RunnableC2182a(long j7, a<?> aVar) {
                this.f3723e = j7;
                this.f3724f = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                a<?> aVar = this.f3724f;
                if (aVar.f1907h) {
                    aVar.f3721v = true;
                    aVar.m1536g();
                } else {
                    aVar.f1906g.offer(this);
                }
                if (aVar.m1021b()) {
                    aVar.m1537h();
                }
            }
        }

        public a(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, int i7, long j8, boolean z6) {
            super(interfaceC2127s, new C1487a());
            this.f3722w = new AtomicReference<>();
            this.f3710k = j7;
            this.f3711l = timeUnit;
            this.f3712m = abstractC2128t;
            this.f3713n = i7;
            this.f3715p = j8;
            this.f3714o = z6;
            if (z6) {
                this.f3716q = abstractC2128t.createWorker();
            } else {
                this.f3716q = null;
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f1907h = true;
        }

        /* renamed from: g */
        public void m1536g() {
            EnumC0515c.m323a(this.f3722w);
            AbstractC2128t.c cVar = this.f3716q;
            if (cVar != null) {
                cVar.dispose();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [w4.e<T>] */
        /* renamed from: h */
        public void m1537h() {
            C1487a c1487a = (C1487a) this.f1906g;
            InterfaceC2127s<? super V> interfaceC2127s = this.f1905f;
            C2033e<T> c2033e = this.f3720u;
            int iM1025f = 1;
            while (!this.f3721v) {
                boolean z6 = this.f1908i;
                Object objPoll = c1487a.poll();
                boolean z7 = objPoll == null;
                boolean z8 = objPoll instanceof RunnableC2182a;
                if (z6 && (z7 || z8)) {
                    this.f3720u = null;
                    c1487a.clear();
                    m1536g();
                    Throwable th = this.f1909j;
                    if (th != null) {
                        c2033e.onError(th);
                        return;
                    } else {
                        c2033e.onComplete();
                        return;
                    }
                }
                if (z7) {
                    iM1025f = m1025f(-iM1025f);
                    if (iM1025f == 0) {
                        return;
                    }
                } else if (z8) {
                    RunnableC2182a runnableC2182a = (RunnableC2182a) objPoll;
                    if (this.f3714o || this.f3718s == runnableC2182a.f3723e) {
                        c2033e.onComplete();
                        this.f3717r = 0L;
                        c2033e = (C2033e<T>) C2033e.m2387b(this.f3713n);
                        this.f3720u = c2033e;
                        interfaceC2127s.onNext(c2033e);
                    }
                } else {
                    c2033e.onNext(objPoll);
                    long j7 = this.f3717r + 1;
                    if (j7 >= this.f3715p) {
                        this.f3718s++;
                        this.f3717r = 0L;
                        c2033e.onComplete();
                        c2033e = (C2033e<T>) C2033e.m2387b(this.f3713n);
                        this.f3720u = c2033e;
                        this.f1905f.onNext(c2033e);
                        if (this.f3714o) {
                            InterfaceC2153b interfaceC2153b = this.f3722w.get();
                            interfaceC2153b.dispose();
                            AbstractC2128t.c cVar = this.f3716q;
                            RunnableC2182a runnableC2182a2 = new RunnableC2182a(this.f3718s, this);
                            long j8 = this.f3710k;
                            InterfaceC2153b interfaceC2153bSchedulePeriodically = cVar.schedulePeriodically(runnableC2182a2, j8, j8, this.f3711l);
                            if (!this.f3722w.compareAndSet(interfaceC2153b, interfaceC2153bSchedulePeriodically)) {
                                interfaceC2153bSchedulePeriodically.dispose();
                            }
                        }
                    } else {
                        this.f3717r = j7;
                    }
                }
            }
            this.f3719t.dispose();
            c1487a.clear();
            m1536g();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f1907h;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f1908i = true;
            if (m1021b()) {
                m1537h();
            }
            this.f1905f.onComplete();
            m1536g();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f1909j = th;
            this.f1908i = true;
            if (m1021b()) {
                m1537h();
            }
            this.f1905f.onError(th);
            m1536g();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3721v) {
                return;
            }
            if (m1022c()) {
                C2033e<T> c2033e = this.f3720u;
                c2033e.onNext(t6);
                long j7 = this.f3717r + 1;
                if (j7 >= this.f3715p) {
                    this.f3718s++;
                    this.f3717r = 0L;
                    c2033e.onComplete();
                    C2033e<T> c2033eM2387b = C2033e.m2387b(this.f3713n);
                    this.f3720u = c2033eM2387b;
                    this.f1905f.onNext(c2033eM2387b);
                    if (this.f3714o) {
                        this.f3722w.get().dispose();
                        AbstractC2128t.c cVar = this.f3716q;
                        RunnableC2182a runnableC2182a = new RunnableC2182a(this.f3718s, this);
                        long j8 = this.f3710k;
                        EnumC0515c.m325c(this.f3722w, cVar.schedulePeriodically(runnableC2182a, j8, j8, this.f3711l));
                    }
                } else {
                    this.f3717r = j7;
                }
                if (m1025f(-1) == 0) {
                    return;
                }
            } else {
                this.f1906g.offer(t6);
                if (!m1021b()) {
                    return;
                }
            }
            m1537h();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            InterfaceC2153b interfaceC2153bSchedulePeriodicallyDirect;
            if (EnumC0515c.m328h(this.f3719t, interfaceC2153b)) {
                this.f3719t = interfaceC2153b;
                InterfaceC2127s<? super V> interfaceC2127s = this.f1905f;
                interfaceC2127s.onSubscribe(this);
                if (this.f1907h) {
                    return;
                }
                C2033e<T> c2033eM2387b = C2033e.m2387b(this.f3713n);
                this.f3720u = c2033eM2387b;
                interfaceC2127s.onNext(c2033eM2387b);
                RunnableC2182a runnableC2182a = new RunnableC2182a(this.f3718s, this);
                if (this.f3714o) {
                    AbstractC2128t.c cVar = this.f3716q;
                    long j7 = this.f3710k;
                    interfaceC2153bSchedulePeriodicallyDirect = cVar.schedulePeriodically(runnableC2182a, j7, j7, this.f3711l);
                } else {
                    AbstractC2128t abstractC2128t = this.f3712m;
                    long j8 = this.f3710k;
                    interfaceC2153bSchedulePeriodicallyDirect = abstractC2128t.schedulePeriodicallyDirect(runnableC2182a, j8, j8, this.f3711l);
                }
                EnumC0515c.m325c(this.f3722w, interfaceC2153bSchedulePeriodicallyDirect);
            }
        }
    }

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Runnable] */
    /* compiled from: ObservableWindowTimed.java */
    /* renamed from: k4.s4$b */
    public static final class b<T> extends AbstractC1011p<T, Object, AbstractC2120l<T>> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: s */
        public static final Object f3725s = new Object();

        /* renamed from: k */
        public final long f3726k;

        /* renamed from: l */
        public final TimeUnit f3727l;

        /* renamed from: m */
        public final AbstractC2128t f3728m;

        /* renamed from: n */
        public final int f3729n;

        /* renamed from: o */
        public InterfaceC2153b f3730o;

        /* renamed from: p */
        public C2033e<T> f3731p;

        /* renamed from: q */
        public final AtomicReference<InterfaceC2153b> f3732q;

        /* renamed from: r */
        public volatile boolean f3733r;

        public b(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, int i7) {
            super(interfaceC2127s, new C1487a());
            this.f3732q = new AtomicReference<>();
            this.f3726k = j7;
            this.f3727l = timeUnit;
            this.f3728m = abstractC2128t;
            this.f3729n = i7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f1907h = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0028, code lost:
        
            r2.onError(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x002c, code lost:
        
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x002f, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        
            r7.f3731p = null;
            r0.clear();
            p022c4.EnumC0515c.m323a(r7.f3732q);
            r0 = r7.f1909j;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
        
            if (r0 == null) goto L11;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [w4.e<T>] */
        /* renamed from: g */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m1538g() {
            /*
                r7 = this;
                e4.g<U> r0 = r7.f1906g
                m4.a r0 = (p104m4.C1487a) r0
                y3.s<? super V> r1 = r7.f1905f
                w4.e<T> r2 = r7.f3731p
                r3 = 1
            L9:
                boolean r4 = r7.f3733r
                boolean r5 = r7.f1908i
                java.lang.Object r6 = r0.poll()
                if (r5 == 0) goto L30
                if (r6 == 0) goto L19
                java.lang.Object r5 = p088k4.C1351s4.b.f3725s
                if (r6 != r5) goto L30
            L19:
                r1 = 0
                r7.f3731p = r1
                r0.clear()
                java.util.concurrent.atomic.AtomicReference<z3.b> r0 = r7.f3732q
                p022c4.EnumC0515c.m323a(r0)
                java.lang.Throwable r0 = r7.f1909j
                if (r0 == 0) goto L2c
                r2.onError(r0)
                goto L2f
            L2c:
                r2.onComplete()
            L2f:
                return
            L30:
                if (r6 != 0) goto L3a
                int r3 = -r3
                int r3 = r7.m1025f(r3)
                if (r3 != 0) goto L9
                return
            L3a:
                java.lang.Object r5 = p088k4.C1351s4.b.f3725s
                if (r6 != r5) goto L55
                r2.onComplete()
                if (r4 != 0) goto L4f
                int r2 = r7.f3729n
                w4.e r2 = p181w4.C2033e.m2387b(r2)
                r7.f3731p = r2
                r1.onNext(r2)
                goto L9
            L4f:
                z3.b r4 = r7.f3730o
                r4.dispose()
                goto L9
            L55:
                r2.onNext(r6)
                goto L9
            */
            throw new UnsupportedOperationException("Method not decompiled: p088k4.C1351s4.b.m1538g():void");
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f1907h;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f1908i = true;
            if (m1021b()) {
                m1538g();
            }
            EnumC0515c.m323a(this.f3732q);
            this.f1905f.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f1909j = th;
            this.f1908i = true;
            if (m1021b()) {
                m1538g();
            }
            EnumC0515c.m323a(this.f3732q);
            this.f1905f.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3733r) {
                return;
            }
            if (m1022c()) {
                this.f3731p.onNext(t6);
                if (m1025f(-1) == 0) {
                    return;
                }
            } else {
                this.f1906g.offer(t6);
                if (!m1021b()) {
                    return;
                }
            }
            m1538g();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3730o, interfaceC2153b)) {
                this.f3730o = interfaceC2153b;
                this.f3731p = C2033e.m2387b(this.f3729n);
                InterfaceC2127s<? super V> interfaceC2127s = this.f1905f;
                interfaceC2127s.onSubscribe(this);
                interfaceC2127s.onNext(this.f3731p);
                if (this.f1907h) {
                    return;
                }
                AbstractC2128t abstractC2128t = this.f3728m;
                long j7 = this.f3726k;
                EnumC0515c.m325c(this.f3732q, abstractC2128t.schedulePeriodicallyDirect(this, j7, j7, this.f3727l));
            }
        }

        public void run() {
            if (this.f1907h) {
                this.f3733r = true;
                EnumC0515c.m323a(this.f3732q);
            }
            this.f1906g.offer(f3725s);
            if (m1021b()) {
                m1538g();
            }
        }
    }

    /* compiled from: ObservableWindowTimed.java */
    /* renamed from: k4.s4$c */
    public static final class c<T> extends AbstractC1011p<T, Object, AbstractC2120l<T>> implements InterfaceC2153b, Runnable {

        /* renamed from: k */
        public final long f3734k;

        /* renamed from: l */
        public final long f3735l;

        /* renamed from: m */
        public final TimeUnit f3736m;

        /* renamed from: n */
        public final AbstractC2128t.c f3737n;

        /* renamed from: o */
        public final int f3738o;

        /* renamed from: p */
        public final List<C2033e<T>> f3739p;

        /* renamed from: q */
        public InterfaceC2153b f3740q;

        /* renamed from: r */
        public volatile boolean f3741r;

        /* compiled from: ObservableWindowTimed.java */
        /* renamed from: k4.s4$c$a */
        public final class a implements Runnable {

            /* renamed from: e */
            public final C2033e<T> f3742e;

            public a(C2033e<T> c2033e) {
                this.f3742e = c2033e;
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                cVar.f1906g.offer(new b(this.f3742e, false));
                if (cVar.m1021b()) {
                    cVar.m1539g();
                }
            }
        }

        /* compiled from: ObservableWindowTimed.java */
        /* renamed from: k4.s4$c$b */
        public static final class b<T> {

            /* renamed from: a */
            public final C2033e<T> f3744a;

            /* renamed from: b */
            public final boolean f3745b;

            public b(C2033e<T> c2033e, boolean z6) {
                this.f3744a = c2033e;
                this.f3745b = z6;
            }
        }

        public c(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s, long j7, long j8, TimeUnit timeUnit, AbstractC2128t.c cVar, int i7) {
            super(interfaceC2127s, new C1487a());
            this.f3734k = j7;
            this.f3735l = j8;
            this.f3736m = timeUnit;
            this.f3737n = cVar;
            this.f3738o = i7;
            this.f3739p = new LinkedList();
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f1907h = true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: g */
        public void m1539g() {
            C1487a c1487a = (C1487a) this.f1906g;
            InterfaceC2127s<? super V> interfaceC2127s = this.f1905f;
            List<C2033e<T>> list = this.f3739p;
            int iM1025f = 1;
            while (!this.f3741r) {
                boolean z6 = this.f1908i;
                Object objPoll = c1487a.poll();
                boolean z7 = objPoll == null;
                boolean z8 = objPoll instanceof b;
                if (z6 && (z7 || z8)) {
                    c1487a.clear();
                    Throwable th = this.f1909j;
                    if (th != null) {
                        Iterator<C2033e<T>> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().onError(th);
                        }
                    } else {
                        Iterator<C2033e<T>> it2 = list.iterator();
                        while (it2.hasNext()) {
                            it2.next().onComplete();
                        }
                    }
                    this.f3737n.dispose();
                    list.clear();
                    return;
                }
                if (z7) {
                    iM1025f = m1025f(-iM1025f);
                    if (iM1025f == 0) {
                        return;
                    }
                } else if (z8) {
                    b bVar = (b) objPoll;
                    if (!bVar.f3745b) {
                        list.remove(bVar.f3744a);
                        bVar.f3744a.onComplete();
                        if (list.isEmpty() && this.f1907h) {
                            this.f3741r = true;
                        }
                    } else if (!this.f1907h) {
                        C2033e<T> c2033eM2387b = C2033e.m2387b(this.f3738o);
                        list.add(c2033eM2387b);
                        interfaceC2127s.onNext(c2033eM2387b);
                        this.f3737n.schedule(new a(c2033eM2387b), this.f3734k, this.f3736m);
                    }
                } else {
                    Iterator<C2033e<T>> it3 = list.iterator();
                    while (it3.hasNext()) {
                        it3.next().onNext(objPoll);
                    }
                }
            }
            this.f3740q.dispose();
            this.f3737n.dispose();
            c1487a.clear();
            list.clear();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f1907h;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f1908i = true;
            if (m1021b()) {
                m1539g();
            }
            this.f1905f.onComplete();
            this.f3737n.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f1909j = th;
            this.f1908i = true;
            if (m1021b()) {
                m1539g();
            }
            this.f1905f.onError(th);
            this.f3737n.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (m1022c()) {
                Iterator<C2033e<T>> it = this.f3739p.iterator();
                while (it.hasNext()) {
                    it.next().onNext(t6);
                }
                if (m1025f(-1) == 0) {
                    return;
                }
            } else {
                this.f1906g.offer(t6);
                if (!m1021b()) {
                    return;
                }
            }
            m1539g();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3740q, interfaceC2153b)) {
                this.f3740q = interfaceC2153b;
                this.f1905f.onSubscribe(this);
                if (this.f1907h) {
                    return;
                }
                C2033e<T> c2033eM2387b = C2033e.m2387b(this.f3738o);
                this.f3739p.add(c2033eM2387b);
                this.f1905f.onNext(c2033eM2387b);
                this.f3737n.schedule(new a(c2033eM2387b), this.f3734k, this.f3736m);
                AbstractC2128t.c cVar = this.f3737n;
                long j7 = this.f3735l;
                cVar.schedulePeriodically(this, j7, j7, this.f3736m);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = new b(C2033e.m2387b(this.f3738o), true);
            if (!this.f1907h) {
                this.f1906g.offer(bVar);
            }
            if (m1021b()) {
                m1539g();
            }
        }
    }

    public C1351s4(InterfaceC2125q<T> interfaceC2125q, long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t, long j9, int i7, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3703f = j7;
        this.f3704g = j8;
        this.f3705h = timeUnit;
        this.f3706i = abstractC2128t;
        this.f3707j = j9;
        this.f3708k = i7;
        this.f3709l = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s) {
        C1882e c1882e = new C1882e(interfaceC2127s);
        long j7 = this.f3703f;
        long j8 = this.f3704g;
        if (j7 != j8) {
            this.f2772e.subscribe(new c(c1882e, j7, j8, this.f3705h, this.f3706i.createWorker(), this.f3708k));
            return;
        }
        long j9 = this.f3707j;
        if (j9 == RecyclerView.FOREVER_NS) {
            this.f2772e.subscribe(new b(c1882e, this.f3703f, this.f3705h, this.f3706i, this.f3708k));
        } else {
            this.f2772e.subscribe(new a(c1882e, j7, this.f3705h, this.f3706i, this.f3708k, j9, this.f3709l));
        }
    }
}
