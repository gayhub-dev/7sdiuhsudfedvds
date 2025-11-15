package p088k4;

import io.reactivex.internal.operators.observable.ObservableCache;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableCache.java */
/* renamed from: k4.p */
/* loaded from: classes.dex */
public final class C1328p<T> extends AbstractC1238a<T, T> implements InterfaceC2127s<T> {

    /* renamed from: o */
    public static final a[] f3519o = new a[0];

    /* renamed from: p */
    public static final a[] f3520p = new a[0];

    /* renamed from: f */
    public final AtomicBoolean f3521f;

    /* renamed from: g */
    public final int f3522g;

    /* renamed from: h */
    public final AtomicReference<ObservableCache.CacheDisposable<T>[]> f3523h;

    /* renamed from: i */
    public volatile long f3524i;

    /* renamed from: j */
    public final b<T> f3525j;

    /* renamed from: k */
    public b<T> f3526k;

    /* renamed from: l */
    public int f3527l;

    /* renamed from: m */
    public Throwable f3528m;

    /* renamed from: n */
    public volatile boolean f3529n;

    /* compiled from: ObservableCache.java */
    /* renamed from: k4.p$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2153b {
        private static final long serialVersionUID = 6770240836423125754L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3530e;

        /* renamed from: f */
        public final C1328p<T> f3531f;

        /* renamed from: g */
        public b<T> f3532g;

        /* renamed from: h */
        public int f3533h;

        /* renamed from: i */
        public long f3534i;

        /* renamed from: j */
        public volatile boolean f3535j;

        public a(InterfaceC2127s<? super T> interfaceC2127s, C1328p<T> c1328p) {
            this.f3530e = interfaceC2127s;
            this.f3531f = c1328p;
            this.f3532g = c1328p.f3525j;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            ObservableCache.CacheDisposable<T>[] cacheDisposableArr;
            a[] aVarArr;
            if (this.f3535j) {
                return;
            }
            this.f3535j = true;
            C1328p<T> c1328p = this.f3531f;
            do {
                cacheDisposableArr = (a[]) c1328p.f3523h.get();
                int length = cacheDisposableArr.length;
                if (length == 0) {
                    return;
                }
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        i7 = -1;
                        break;
                    } else if (cacheDisposableArr[i7] == this) {
                        break;
                    } else {
                        i7++;
                    }
                }
                if (i7 < 0) {
                    return;
                }
                if (length == 1) {
                    aVarArr = C1328p.f3519o;
                } else {
                    a[] aVarArr2 = new a[length - 1];
                    System.arraycopy(cacheDisposableArr, 0, aVarArr2, 0, i7);
                    System.arraycopy(cacheDisposableArr, i7 + 1, aVarArr2, i7, (length - i7) - 1);
                    aVarArr = aVarArr2;
                }
            } while (!c1328p.f3523h.compareAndSet(cacheDisposableArr, aVarArr));
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3535j;
        }
    }

    /* compiled from: ObservableCache.java */
    /* renamed from: k4.p$b */
    public static final class b<T> {

        /* renamed from: a */
        public final T[] f3536a;

        /* renamed from: b */
        public volatile b<T> f3537b;

        public b(int i7) {
            this.f3536a = (T[]) new Object[i7];
        }
    }

    public C1328p(AbstractC2120l<T> abstractC2120l, int i7) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3522g = i7;
        this.f3521f = new AtomicBoolean();
        b<T> bVar = new b<>(i7);
        this.f3525j = bVar;
        this.f3526k = bVar;
        this.f3523h = new AtomicReference<>(f3519o);
    }

    /* renamed from: b */
    public void m1526b(a<T> aVar) {
        if (aVar.getAndIncrement() != 0) {
            return;
        }
        long j7 = aVar.f3534i;
        int i7 = aVar.f3533h;
        b<T> bVar = aVar.f3532g;
        InterfaceC2127s<? super T> interfaceC2127s = aVar.f3530e;
        int i8 = this.f3522g;
        int iAddAndGet = 1;
        while (!aVar.f3535j) {
            boolean z6 = this.f3529n;
            boolean z7 = this.f3524i == j7;
            if (z6 && z7) {
                aVar.f3532g = null;
                Throwable th = this.f3528m;
                if (th != null) {
                    interfaceC2127s.onError(th);
                    return;
                } else {
                    interfaceC2127s.onComplete();
                    return;
                }
            }
            if (z7) {
                aVar.f3534i = j7;
                aVar.f3533h = i7;
                aVar.f3532g = bVar;
                iAddAndGet = aVar.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                if (i7 == i8) {
                    bVar = bVar.f3537b;
                    i7 = 0;
                }
                interfaceC2127s.onNext(bVar.f3536a[i7]);
                i7++;
                j7++;
            }
        }
        aVar.f3532g = null;
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        this.f3529n = true;
        for (a<T> aVar : (a[]) this.f3523h.getAndSet(f3520p)) {
            m1526b(aVar);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        this.f3528m = th;
        this.f3529n = true;
        for (a<T> aVar : (a[]) this.f3523h.getAndSet(f3520p)) {
            m1526b(aVar);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        int i7 = this.f3527l;
        if (i7 == this.f3522g) {
            b<T> bVar = new b<>(i7);
            bVar.f3536a[0] = t6;
            this.f3527l = 1;
            this.f3526k.f3537b = bVar;
            this.f3526k = bVar;
        } else {
            this.f3526k.f3536a[i7] = t6;
            this.f3527l = i7 + 1;
        }
        this.f3524i++;
        for (a<T> aVar : (a[]) this.f3523h.get()) {
            m1526b(aVar);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        ObservableCache.CacheDisposable<T>[] cacheDisposableArr;
        ObservableCache.CacheDisposable<T>[] cacheDisposableArr2;
        ObservableCache.CacheDisposable<T> aVar = new a<>(interfaceC2127s, this);
        interfaceC2127s.onSubscribe(aVar);
        do {
            cacheDisposableArr = (a[]) this.f3523h.get();
            if (cacheDisposableArr == f3520p) {
                break;
            }
            int length = cacheDisposableArr.length;
            cacheDisposableArr2 = new a[length + 1];
            System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr2, 0, length);
            cacheDisposableArr2[length] = aVar;
        } while (!this.f3523h.compareAndSet(cacheDisposableArr, cacheDisposableArr2));
        if (this.f3521f.get() || !this.f3521f.compareAndSet(false, true)) {
            m1526b(aVar);
        } else {
            this.f2772e.subscribe(this);
        }
    }
}
