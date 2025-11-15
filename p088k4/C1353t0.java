package p088k4;

import io.reactivex.internal.operators.observable.ObservableFlatMap;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0950c;
import p040e4.InterfaceC0954g;
import p040e4.InterfaceC0955h;
import p104m4.C1488b;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableFlatMap.java */
/* renamed from: k4.t0 */
/* loaded from: classes.dex */
public final class C1353t0<T, U> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> f3775f;

    /* renamed from: g */
    public final boolean f3776g;

    /* renamed from: h */
    public final int f3777h;

    /* renamed from: i */
    public final int f3778i;

    /* compiled from: ObservableFlatMap.java */
    /* renamed from: k4.t0$a */
    public static final class a<T, U> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<U> {
        private static final long serialVersionUID = -4606175640614850599L;

        /* renamed from: e */
        public final long f3779e;

        /* renamed from: f */
        public final b<T, U> f3780f;

        /* renamed from: g */
        public volatile boolean f3781g;

        /* renamed from: h */
        public volatile InterfaceC0955h<U> f3782h;

        /* renamed from: i */
        public int f3783i;

        public a(b<T, U> bVar, long j7) {
            this.f3779e = j7;
            this.f3780f = bVar;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3781g = true;
            this.f3780f.m1544c();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f3780f.f3793l, th)) {
                C1908a.m2205b(th);
                return;
            }
            b<T, U> bVar = this.f3780f;
            if (!bVar.f3788g) {
                bVar.m1543b();
            }
            this.f3781g = true;
            this.f3780f.m1544c();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(U u6) {
            if (this.f3783i != 0) {
                this.f3780f.m1544c();
                return;
            }
            b<T, U> bVar = this.f3780f;
            if (bVar.get() == 0 && bVar.compareAndSet(0, 1)) {
                bVar.f3786e.onNext(u6);
                if (bVar.decrementAndGet() == 0) {
                    return;
                }
            } else {
                InterfaceC0955h c1489c = this.f3782h;
                if (c1489c == null) {
                    c1489c = new C1489c(bVar.f3790i);
                    this.f3782h = c1489c;
                }
                c1489c.offer(u6);
                if (bVar.getAndIncrement() != 0) {
                    return;
                }
            }
            bVar.m1545d();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m327f(this, interfaceC2153b) && (interfaceC2153b instanceof InterfaceC0950c)) {
                InterfaceC0950c interfaceC0950c = (InterfaceC0950c) interfaceC2153b;
                int iMo331c = interfaceC0950c.mo331c(7);
                if (iMo331c == 1) {
                    this.f3783i = iMo331c;
                    this.f3782h = interfaceC0950c;
                    this.f3781g = true;
                    this.f3780f.m1544c();
                    return;
                }
                if (iMo331c == 2) {
                    this.f3783i = iMo331c;
                    this.f3782h = interfaceC0950c;
                }
            }
        }
    }

    /* compiled from: ObservableFlatMap.java */
    /* renamed from: k4.t0$b */
    public static final class b<T, U> extends AtomicInteger implements InterfaceC2153b, InterfaceC2127s<T> {
        private static final long serialVersionUID = -2117620485640801370L;

        /* renamed from: u */
        public static final ObservableFlatMap.InnerObserver<?, ?>[] f3784u = new a[0];

        /* renamed from: v */
        public static final ObservableFlatMap.InnerObserver<?, ?>[] f3785v = new a[0];

        /* renamed from: e */
        public final InterfaceC2127s<? super U> f3786e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> f3787f;

        /* renamed from: g */
        public final boolean f3788g;

        /* renamed from: h */
        public final int f3789h;

        /* renamed from: i */
        public final int f3790i;

        /* renamed from: j */
        public volatile InterfaceC0954g<U> f3791j;

        /* renamed from: k */
        public volatile boolean f3792k;

        /* renamed from: l */
        public final C1771c f3793l = new C1771c();

        /* renamed from: m */
        public volatile boolean f3794m;

        /* renamed from: n */
        public final AtomicReference<ObservableFlatMap.InnerObserver<?, ?>[]> f3795n;

        /* renamed from: o */
        public InterfaceC2153b f3796o;

        /* renamed from: p */
        public long f3797p;

        /* renamed from: q */
        public long f3798q;

        /* renamed from: r */
        public int f3799r;

        /* renamed from: s */
        public Queue<InterfaceC2125q<? extends U>> f3800s;

        /* renamed from: t */
        public int f3801t;

        public b(InterfaceC2127s<? super U> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> interfaceC0454n, boolean z6, int i7, int i8) {
            this.f3786e = interfaceC2127s;
            this.f3787f = interfaceC0454n;
            this.f3788g = z6;
            this.f3789h = i7;
            this.f3790i = i8;
            if (i7 != Integer.MAX_VALUE) {
                this.f3800s = new ArrayDeque(i7);
            }
            this.f3795n = new AtomicReference<>(f3784u);
        }

        /* renamed from: a */
        public boolean m1542a() {
            if (this.f3794m) {
                return true;
            }
            Throwable th = this.f3793l.get();
            if (this.f3788g || th == null) {
                return false;
            }
            m1543b();
            Throwable thM1959b = C1774f.m1959b(this.f3793l);
            if (thM1959b != C1774f.f5055a) {
                this.f3786e.onError(thM1959b);
            }
            return true;
        }

        /* renamed from: b */
        public boolean m1543b() {
            a[] andSet;
            this.f3796o.dispose();
            a[] aVarArr = this.f3795n.get();
            a[] aVarArr2 = f3785v;
            if (aVarArr == aVarArr2 || (andSet = this.f3795n.getAndSet(aVarArr2)) == aVarArr2) {
                return false;
            }
            for (a aVar : andSet) {
                EnumC0515c.m323a(aVar);
            }
            return true;
        }

        /* renamed from: c */
        public void m1544c() {
            if (getAndIncrement() == 0) {
                m1545d();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:128:0x0004, code lost:
        
            continue;
         */
        /* JADX WARN: Removed duplicated region for block: B:120:0x00ea A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:133:0x00f2 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x00eb  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x00f1 A[PHI: r4
          0x00f1: PHI (r4v10 int) = (r4v8 int), (r4v11 int) binds: [B:72:0x00d0, B:84:0x00ef] A[DONT_GENERATE, DONT_INLINE]] */
        /* renamed from: d */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m1545d() {
            /*
                Method dump skipped, instructions count: 299
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p088k4.C1353t0.b.m1545d():void");
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            Throwable thM1959b;
            if (this.f3794m) {
                return;
            }
            this.f3794m = true;
            if (!m1543b() || (thM1959b = C1774f.m1959b(this.f3793l)) == null || thM1959b == C1774f.f5055a) {
                return;
            }
            C1908a.m2205b(thM1959b);
        }

        /* renamed from: e */
        public void m1546e(a<T, U> aVar) {
            ObservableFlatMap.InnerObserver<?, ?>[] innerObserverArr;
            ObservableFlatMap.InnerObserver<?, ?>[] innerObserverArr2;
            do {
                innerObserverArr = (a[]) this.f3795n.get();
                int length = innerObserverArr.length;
                if (length == 0) {
                    return;
                }
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        i7 = -1;
                        break;
                    } else if (innerObserverArr[i7] == aVar) {
                        break;
                    } else {
                        i7++;
                    }
                }
                if (i7 < 0) {
                    return;
                }
                if (length == 1) {
                    innerObserverArr2 = f3784u;
                } else {
                    ObservableFlatMap.InnerObserver<?, ?>[] innerObserverArr3 = new a[length - 1];
                    System.arraycopy(innerObserverArr, 0, innerObserverArr3, 0, i7);
                    System.arraycopy(innerObserverArr, i7 + 1, innerObserverArr3, i7, (length - i7) - 1);
                    innerObserverArr2 = innerObserverArr3;
                }
            } while (!this.f3795n.compareAndSet(innerObserverArr, innerObserverArr2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v22 */
        /* JADX WARN: Type inference failed for: r3v23 */
        /* JADX WARN: Type inference failed for: r3v8, types: [e4.h] */
        /* renamed from: f */
        public void m1547f(InterfaceC2125q<? extends U> interfaceC2125q) {
            boolean z6;
            boolean z7;
            Object objCall;
            do {
                z6 = false;
                if (!(interfaceC2125q instanceof Callable)) {
                    long j7 = this.f3797p;
                    this.f3797p = 1 + j7;
                    ObservableFlatMap.InnerObserver<?, ?> aVar = new a<>(this, j7);
                    while (true) {
                        ObservableFlatMap.InnerObserver<?, ?>[] innerObserverArr = (a[]) this.f3795n.get();
                        if (innerObserverArr == f3785v) {
                            EnumC0515c.m323a(aVar);
                            break;
                        }
                        int length = innerObserverArr.length;
                        ObservableFlatMap.InnerObserver<?, ?>[] innerObserverArr2 = new a[length + 1];
                        System.arraycopy(innerObserverArr, 0, innerObserverArr2, 0, length);
                        innerObserverArr2[length] = aVar;
                        if (this.f3795n.compareAndSet(innerObserverArr, innerObserverArr2)) {
                            z6 = true;
                            break;
                        }
                    }
                    if (z6) {
                        interfaceC2125q.subscribe(aVar);
                        return;
                    }
                    return;
                }
                try {
                    objCall = ((Callable) interfaceC2125q).call();
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    C1774f.m1958a(this.f3793l, th);
                    m1544c();
                }
                if (objCall != null) {
                    if (get() == 0 && compareAndSet(0, 1)) {
                        this.f3786e.onNext(objCall);
                        if (decrementAndGet() != 0) {
                            m1545d();
                        }
                    } else {
                        InterfaceC0954g<U> interfaceC0954g = this.f3791j;
                        ?? r32 = interfaceC0954g;
                        if (interfaceC0954g == false) {
                            InterfaceC0954g<U> c1489c = this.f3789h == Integer.MAX_VALUE ? new C1489c(this.f3790i) : new C1488b(this.f3789h);
                            this.f3791j = c1489c;
                            r32 = c1489c;
                        }
                        if (r32.offer(objCall)) {
                            z7 = getAndIncrement() == 0;
                            m1545d();
                        } else {
                            onError(new IllegalStateException("Scalar queue full?!"));
                        }
                    }
                }
                if (!z7 || this.f3789h == Integer.MAX_VALUE) {
                    return;
                }
                synchronized (this) {
                    interfaceC2125q = this.f3800s.poll();
                    if (interfaceC2125q == null) {
                        this.f3801t--;
                        z6 = true;
                    }
                }
            } while (!z6);
            m1544c();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3794m;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3792k) {
                return;
            }
            this.f3792k = true;
            m1544c();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3792k) {
                C1908a.m2205b(th);
            } else if (!C1774f.m1958a(this.f3793l, th)) {
                C1908a.m2205b(th);
            } else {
                this.f3792k = true;
                m1544c();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3792k) {
                return;
            }
            try {
                InterfaceC2125q<? extends U> interfaceC2125qApply = this.f3787f.apply(t6);
                Objects.requireNonNull(interfaceC2125qApply, "The mapper returned a null ObservableSource");
                InterfaceC2125q<? extends U> interfaceC2125q = interfaceC2125qApply;
                if (this.f3789h != Integer.MAX_VALUE) {
                    synchronized (this) {
                        int i7 = this.f3801t;
                        if (i7 == this.f3789h) {
                            this.f3800s.offer(interfaceC2125q);
                            return;
                        }
                        this.f3801t = i7 + 1;
                    }
                }
                m1547f(interfaceC2125q);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3796o.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3796o, interfaceC2153b)) {
                this.f3796o = interfaceC2153b;
                this.f3786e.onSubscribe(this);
            }
        }
    }

    public C1353t0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> interfaceC0454n, boolean z6, int i7, int i8) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3775f = interfaceC0454n;
        this.f3776g = z6;
        this.f3777h = i7;
        this.f3778i = i8;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        if (C1284h3.m1507a(this.f2772e, interfaceC2127s, this.f3775f)) {
            return;
        }
        this.f2772e.subscribe(new b(interfaceC2127s, this.f3775f, this.f3776g, this.f3777h, this.f3778i));
    }
}
