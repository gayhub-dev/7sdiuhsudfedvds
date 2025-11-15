package p088k4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p048f4.AbstractC1011p;
import p104m4.C1487a;
import p153s4.C1882e;
import p186x2.C2074b;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableBufferTimed.java */
/* renamed from: k4.o */
/* loaded from: classes.dex */
public final class C1322o<T, U extends Collection<? super T>> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final long f3436f;

    /* renamed from: g */
    public final long f3437g;

    /* renamed from: h */
    public final TimeUnit f3438h;

    /* renamed from: i */
    public final AbstractC2128t f3439i;

    /* renamed from: j */
    public final Callable<U> f3440j;

    /* renamed from: k */
    public final int f3441k;

    /* renamed from: l */
    public final boolean f3442l;

    /* compiled from: ObservableBufferTimed.java */
    /* renamed from: k4.o$a */
    public static final class a<T, U extends Collection<? super T>> extends AbstractC1011p<T, U, U> implements Runnable, InterfaceC2153b {

        /* renamed from: k */
        public final Callable<U> f3443k;

        /* renamed from: l */
        public final long f3444l;

        /* renamed from: m */
        public final TimeUnit f3445m;

        /* renamed from: n */
        public final int f3446n;

        /* renamed from: o */
        public final boolean f3447o;

        /* renamed from: p */
        public final AbstractC2128t.c f3448p;

        /* renamed from: q */
        public U f3449q;

        /* renamed from: r */
        public InterfaceC2153b f3450r;

        /* renamed from: s */
        public InterfaceC2153b f3451s;

        /* renamed from: t */
        public long f3452t;

        /* renamed from: u */
        public long f3453u;

        public a(InterfaceC2127s<? super U> interfaceC2127s, Callable<U> callable, long j7, TimeUnit timeUnit, int i7, boolean z6, AbstractC2128t.c cVar) {
            super(interfaceC2127s, new C1487a());
            this.f3443k = callable;
            this.f3444l = j7;
            this.f3445m = timeUnit;
            this.f3446n = i7;
            this.f3447o = z6;
            this.f3448p = cVar;
        }

        @Override // p048f4.AbstractC1011p
        /* renamed from: a */
        public void mo1020a(InterfaceC2127s interfaceC2127s, Object obj) {
            interfaceC2127s.onNext((Collection) obj);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f1907h) {
                return;
            }
            this.f1907h = true;
            this.f3451s.dispose();
            this.f3448p.dispose();
            synchronized (this) {
                this.f3449q = null;
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f1907h;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            U u6;
            this.f3448p.dispose();
            synchronized (this) {
                u6 = this.f3449q;
                this.f3449q = null;
            }
            this.f1906g.offer(u6);
            this.f1908i = true;
            if (m1021b()) {
                C2074b.m2482e(this.f1906g, this.f1905f, false, this, this);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            synchronized (this) {
                this.f3449q = null;
            }
            this.f1905f.onError(th);
            this.f3448p.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            synchronized (this) {
                U u6 = this.f3449q;
                if (u6 == null) {
                    return;
                }
                u6.add(t6);
                if (u6.size() < this.f3446n) {
                    return;
                }
                this.f3449q = null;
                this.f3452t++;
                if (this.f3447o) {
                    this.f3450r.dispose();
                }
                m1024e(u6, false, this);
                try {
                    U uCall = this.f3443k.call();
                    Objects.requireNonNull(uCall, "The buffer supplied is null");
                    U u7 = uCall;
                    synchronized (this) {
                        this.f3449q = u7;
                        this.f3453u++;
                    }
                    if (this.f3447o) {
                        AbstractC2128t.c cVar = this.f3448p;
                        long j7 = this.f3444l;
                        this.f3450r = cVar.schedulePeriodically(this, j7, j7, this.f3445m);
                    }
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    this.f1905f.onError(th);
                    dispose();
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3451s, interfaceC2153b)) {
                this.f3451s = interfaceC2153b;
                try {
                    U uCall = this.f3443k.call();
                    Objects.requireNonNull(uCall, "The buffer supplied is null");
                    this.f3449q = uCall;
                    this.f1905f.onSubscribe(this);
                    AbstractC2128t.c cVar = this.f3448p;
                    long j7 = this.f3444l;
                    this.f3450r = cVar.schedulePeriodically(this, j7, j7, this.f3445m);
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    interfaceC2153b.dispose();
                    EnumC0516d.m330b(th, this.f1905f);
                    this.f3448p.dispose();
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                U uCall = this.f3443k.call();
                Objects.requireNonNull(uCall, "The bufferSupplier returned a null buffer");
                U u6 = uCall;
                synchronized (this) {
                    U u7 = this.f3449q;
                    if (u7 != null && this.f3452t == this.f3453u) {
                        this.f3449q = u6;
                        m1024e(u7, false, this);
                    }
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                dispose();
                this.f1905f.onError(th);
            }
        }
    }

    /* compiled from: ObservableBufferTimed.java */
    /* renamed from: k4.o$b */
    public static final class b<T, U extends Collection<? super T>> extends AbstractC1011p<T, U, U> implements Runnable, InterfaceC2153b {

        /* renamed from: k */
        public final Callable<U> f3454k;

        /* renamed from: l */
        public final long f3455l;

        /* renamed from: m */
        public final TimeUnit f3456m;

        /* renamed from: n */
        public final AbstractC2128t f3457n;

        /* renamed from: o */
        public InterfaceC2153b f3458o;

        /* renamed from: p */
        public U f3459p;

        /* renamed from: q */
        public final AtomicReference<InterfaceC2153b> f3460q;

        public b(InterfaceC2127s<? super U> interfaceC2127s, Callable<U> callable, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
            super(interfaceC2127s, new C1487a());
            this.f3460q = new AtomicReference<>();
            this.f3454k = callable;
            this.f3455l = j7;
            this.f3456m = timeUnit;
            this.f3457n = abstractC2128t;
        }

        @Override // p048f4.AbstractC1011p
        /* renamed from: a */
        public void mo1020a(InterfaceC2127s interfaceC2127s, Object obj) {
            this.f1905f.onNext((Collection) obj);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3460q);
            this.f3458o.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3460q.get() == EnumC0515c.DISPOSED;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            U u6;
            synchronized (this) {
                u6 = this.f3459p;
                this.f3459p = null;
            }
            if (u6 != null) {
                this.f1906g.offer(u6);
                this.f1908i = true;
                if (m1021b()) {
                    C2074b.m2482e(this.f1906g, this.f1905f, false, null, this);
                }
            }
            EnumC0515c.m323a(this.f3460q);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            synchronized (this) {
                this.f3459p = null;
            }
            this.f1905f.onError(th);
            EnumC0515c.m323a(this.f3460q);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            synchronized (this) {
                U u6 = this.f3459p;
                if (u6 == null) {
                    return;
                }
                u6.add(t6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3458o, interfaceC2153b)) {
                this.f3458o = interfaceC2153b;
                try {
                    U uCall = this.f3454k.call();
                    Objects.requireNonNull(uCall, "The buffer supplied is null");
                    this.f3459p = uCall;
                    this.f1905f.onSubscribe(this);
                    if (this.f1907h) {
                        return;
                    }
                    AbstractC2128t abstractC2128t = this.f3457n;
                    long j7 = this.f3455l;
                    InterfaceC2153b interfaceC2153bSchedulePeriodicallyDirect = abstractC2128t.schedulePeriodicallyDirect(this, j7, j7, this.f3456m);
                    if (this.f3460q.compareAndSet(null, interfaceC2153bSchedulePeriodicallyDirect)) {
                        return;
                    }
                    interfaceC2153bSchedulePeriodicallyDirect.dispose();
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    dispose();
                    EnumC0516d.m330b(th, this.f1905f);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            U u6;
            try {
                U uCall = this.f3454k.call();
                Objects.requireNonNull(uCall, "The bufferSupplier returned a null buffer");
                U u7 = uCall;
                synchronized (this) {
                    u6 = this.f3459p;
                    if (u6 != null) {
                        this.f3459p = u7;
                    }
                }
                if (u6 == null) {
                    EnumC0515c.m323a(this.f3460q);
                } else {
                    m1023d(u6, false, this);
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f1905f.onError(th);
                dispose();
            }
        }
    }

    /* compiled from: ObservableBufferTimed.java */
    /* renamed from: k4.o$c */
    public static final class c<T, U extends Collection<? super T>> extends AbstractC1011p<T, U, U> implements Runnable, InterfaceC2153b {

        /* renamed from: k */
        public final Callable<U> f3461k;

        /* renamed from: l */
        public final long f3462l;

        /* renamed from: m */
        public final long f3463m;

        /* renamed from: n */
        public final TimeUnit f3464n;

        /* renamed from: o */
        public final AbstractC2128t.c f3465o;

        /* renamed from: p */
        public final List<U> f3466p;

        /* renamed from: q */
        public InterfaceC2153b f3467q;

        /* compiled from: ObservableBufferTimed.java */
        /* renamed from: k4.o$c$a */
        public final class a implements Runnable {

            /* renamed from: e */
            public final U f3468e;

            public a(U u6) {
                this.f3468e = u6;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (c.this) {
                    c.this.f3466p.remove(this.f3468e);
                }
                c cVar = c.this;
                cVar.m1024e(this.f3468e, false, cVar.f3465o);
            }
        }

        /* compiled from: ObservableBufferTimed.java */
        /* renamed from: k4.o$c$b */
        public final class b implements Runnable {

            /* renamed from: e */
            public final U f3470e;

            public b(U u6) {
                this.f3470e = u6;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (c.this) {
                    c.this.f3466p.remove(this.f3470e);
                }
                c cVar = c.this;
                cVar.m1024e(this.f3470e, false, cVar.f3465o);
            }
        }

        public c(InterfaceC2127s<? super U> interfaceC2127s, Callable<U> callable, long j7, long j8, TimeUnit timeUnit, AbstractC2128t.c cVar) {
            super(interfaceC2127s, new C1487a());
            this.f3461k = callable;
            this.f3462l = j7;
            this.f3463m = j8;
            this.f3464n = timeUnit;
            this.f3465o = cVar;
            this.f3466p = new LinkedList();
        }

        @Override // p048f4.AbstractC1011p
        /* renamed from: a */
        public void mo1020a(InterfaceC2127s interfaceC2127s, Object obj) {
            interfaceC2127s.onNext((Collection) obj);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f1907h) {
                return;
            }
            this.f1907h = true;
            synchronized (this) {
                this.f3466p.clear();
            }
            this.f3467q.dispose();
            this.f3465o.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f1907h;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            ArrayList arrayList;
            synchronized (this) {
                arrayList = new ArrayList(this.f3466p);
                this.f3466p.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.f1906g.offer((Collection) it.next());
            }
            this.f1908i = true;
            if (m1021b()) {
                C2074b.m2482e(this.f1906g, this.f1905f, false, this.f3465o, this);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f1908i = true;
            synchronized (this) {
                this.f3466p.clear();
            }
            this.f1905f.onError(th);
            this.f3465o.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            synchronized (this) {
                Iterator<U> it = this.f3466p.iterator();
                while (it.hasNext()) {
                    it.next().add(t6);
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3467q, interfaceC2153b)) {
                this.f3467q = interfaceC2153b;
                try {
                    U uCall = this.f3461k.call();
                    Objects.requireNonNull(uCall, "The buffer supplied is null");
                    U u6 = uCall;
                    this.f3466p.add(u6);
                    this.f1905f.onSubscribe(this);
                    AbstractC2128t.c cVar = this.f3465o;
                    long j7 = this.f3463m;
                    cVar.schedulePeriodically(this, j7, j7, this.f3464n);
                    this.f3465o.schedule(new b(u6), this.f3462l, this.f3464n);
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    interfaceC2153b.dispose();
                    EnumC0516d.m330b(th, this.f1905f);
                    this.f3465o.dispose();
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1907h) {
                return;
            }
            try {
                U uCall = this.f3461k.call();
                Objects.requireNonNull(uCall, "The bufferSupplier returned a null buffer");
                U u6 = uCall;
                synchronized (this) {
                    if (this.f1907h) {
                        return;
                    }
                    this.f3466p.add(u6);
                    this.f3465o.schedule(new a(u6), this.f3462l, this.f3464n);
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f1905f.onError(th);
                dispose();
            }
        }
    }

    public C1322o(InterfaceC2125q<T> interfaceC2125q, long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t, Callable<U> callable, int i7, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3436f = j7;
        this.f3437g = j8;
        this.f3438h = timeUnit;
        this.f3439i = abstractC2128t;
        this.f3440j = callable;
        this.f3441k = i7;
        this.f3442l = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        long j7 = this.f3436f;
        if (j7 == this.f3437g && this.f3441k == Integer.MAX_VALUE) {
            this.f2772e.subscribe(new b(new C1882e(interfaceC2127s), this.f3440j, j7, this.f3438h, this.f3439i));
            return;
        }
        AbstractC2128t.c cVarCreateWorker = this.f3439i.createWorker();
        long j8 = this.f3436f;
        long j9 = this.f3437g;
        if (j8 == j9) {
            this.f2772e.subscribe(new a(new C1882e(interfaceC2127s), this.f3440j, j8, this.f3438h, this.f3441k, this.f3442l, cVarCreateWorker));
        } else {
            this.f2772e.subscribe(new c(new C1882e(interfaceC2127s), this.f3440j, j8, j9, this.f3438h, cVarCreateWorker));
        }
    }
}
