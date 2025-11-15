package p181w4;

import io.reactivex.subjects.BehaviorSubject;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import p138q4.C1769a;
import p138q4.C1774f;
import p138q4.EnumC1776h;
import p160t4.C1908a;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: BehaviorSubject.java */
/* renamed from: w4.a */
/* loaded from: classes.dex */
public final class C2029a<T> extends AbstractC2032d<T> {

    /* renamed from: k */
    public static final Object[] f5897k = new Object[0];

    /* renamed from: l */
    public static final a[] f5898l = new a[0];

    /* renamed from: m */
    public static final a[] f5899m = new a[0];

    /* renamed from: e */
    public final AtomicReference<Object> f5900e;

    /* renamed from: f */
    public final AtomicReference<BehaviorSubject.BehaviorDisposable<T>[]> f5901f;

    /* renamed from: g */
    public final Lock f5902g;

    /* renamed from: h */
    public final Lock f5903h;

    /* renamed from: i */
    public final AtomicReference<Throwable> f5904i;

    /* renamed from: j */
    public long f5905j;

    /* compiled from: BehaviorSubject.java */
    /* renamed from: w4.a$a */
    public static final class a<T> implements InterfaceC2153b, C1769a.a<Object> {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f5906e;

        /* renamed from: f */
        public final C2029a<T> f5907f;

        /* renamed from: g */
        public boolean f5908g;

        /* renamed from: h */
        public boolean f5909h;

        /* renamed from: i */
        public C1769a<Object> f5910i;

        /* renamed from: j */
        public boolean f5911j;

        /* renamed from: k */
        public volatile boolean f5912k;

        /* renamed from: l */
        public long f5913l;

        public a(InterfaceC2127s<? super T> interfaceC2127s, C2029a<T> c2029a) {
            this.f5906e = interfaceC2127s;
            this.f5907f = c2029a;
        }

        /* renamed from: a */
        public void m2384a(Object obj, long j7) {
            if (this.f5912k) {
                return;
            }
            if (!this.f5911j) {
                synchronized (this) {
                    if (this.f5912k) {
                        return;
                    }
                    if (this.f5913l == j7) {
                        return;
                    }
                    if (this.f5909h) {
                        C1769a<Object> c1769a = this.f5910i;
                        if (c1769a == null) {
                            c1769a = new C1769a<>(4);
                            this.f5910i = c1769a;
                        }
                        c1769a.m1956a(obj);
                        return;
                    }
                    this.f5908g = true;
                    this.f5911j = true;
                }
            }
            test(obj);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f5912k) {
                return;
            }
            this.f5912k = true;
            this.f5907f.m2382c(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f5912k;
        }

        @Override // p138q4.C1769a.a, p014b4.InterfaceC0455o
        public boolean test(Object obj) {
            return this.f5912k || EnumC1776h.m1962a(obj, this.f5906e);
        }
    }

    public C2029a() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f5902g = reentrantReadWriteLock.readLock();
        this.f5903h = reentrantReadWriteLock.writeLock();
        this.f5901f = new AtomicReference<>(f5898l);
        this.f5900e = new AtomicReference<>();
        this.f5904i = new AtomicReference<>();
    }

    /* renamed from: b */
    public T m2381b() {
        T t6 = (T) this.f5900e.get();
        if (EnumC1776h.m1964c(t6) || (t6 instanceof EnumC1776h.b)) {
            return null;
        }
        return t6;
    }

    /* renamed from: c */
    public void m2382c(a<T> aVar) {
        BehaviorSubject.BehaviorDisposable<T>[] behaviorDisposableArr;
        a[] aVarArr;
        do {
            behaviorDisposableArr = (a[]) this.f5901f.get();
            int length = behaviorDisposableArr.length;
            if (length == 0) {
                return;
            }
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    i7 = -1;
                    break;
                } else if (behaviorDisposableArr[i7] == aVar) {
                    break;
                } else {
                    i7++;
                }
            }
            if (i7 < 0) {
                return;
            }
            if (length == 1) {
                aVarArr = f5898l;
            } else {
                a[] aVarArr2 = new a[length - 1];
                System.arraycopy(behaviorDisposableArr, 0, aVarArr2, 0, i7);
                System.arraycopy(behaviorDisposableArr, i7 + 1, aVarArr2, i7, (length - i7) - 1);
                aVarArr = aVarArr2;
            }
        } while (!this.f5901f.compareAndSet(behaviorDisposableArr, aVarArr));
    }

    /* renamed from: d */
    public void m2383d(Object obj) {
        this.f5903h.lock();
        this.f5905j++;
        this.f5900e.lazySet(obj);
        this.f5903h.unlock();
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        if (this.f5904i.compareAndSet(null, C1774f.f5055a)) {
            EnumC1776h enumC1776h = EnumC1776h.COMPLETE;
            AtomicReference<BehaviorSubject.BehaviorDisposable<T>[]> atomicReference = this.f5901f;
            a[] aVarArr = f5899m;
            a[] aVarArr2 = (a[]) atomicReference.getAndSet(aVarArr);
            if (aVarArr2 != aVarArr) {
                m2383d(enumC1776h);
            }
            for (a aVar : aVarArr2) {
                aVar.m2384a(enumC1776h, this.f5905j);
            }
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        Objects.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.f5904i.compareAndSet(null, th)) {
            C1908a.m2205b(th);
            return;
        }
        EnumC1776h.b bVar = new EnumC1776h.b(th);
        AtomicReference<BehaviorSubject.BehaviorDisposable<T>[]> atomicReference = this.f5901f;
        a[] aVarArr = f5899m;
        a[] aVarArr2 = (a[]) atomicReference.getAndSet(aVarArr);
        if (aVarArr2 != aVarArr) {
            m2383d(bVar);
        }
        for (a aVar : aVarArr2) {
            aVar.m2384a(bVar, this.f5905j);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        Objects.requireNonNull(t6, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f5904i.get() != null) {
            return;
        }
        m2383d(t6);
        for (a aVar : this.f5901f.get()) {
            aVar.m2384a(t6, this.f5905j);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (this.f5904i.get() != null) {
            interfaceC2153b.dispose();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0079, code lost:
    
        r0.f5909h = false;
     */
    @Override // p194y3.AbstractC2120l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void subscribeActual(p194y3.InterfaceC2127s<? super T> r8) {
        /*
            r7 = this;
            w4.a$a r0 = new w4.a$a
            r0.<init>(r8, r7)
            r8.onSubscribe(r0)
        L8:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.subjects.BehaviorSubject$BehaviorDisposable<T>[]> r1 = r7.f5901f
            java.lang.Object r1 = r1.get()
            w4.a$a[] r1 = (p181w4.C2029a.a[]) r1
            w4.a$a[] r2 = p181w4.C2029a.f5899m
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L18
            r1 = 0
            goto L2b
        L18:
            int r2 = r1.length
            int r5 = r2 + 1
            w4.a$a[] r5 = new p181w4.C2029a.a[r5]
            java.lang.System.arraycopy(r1, r3, r5, r3, r2)
            r5[r2] = r0
            java.util.concurrent.atomic.AtomicReference<io.reactivex.subjects.BehaviorSubject$BehaviorDisposable<T>[]> r2 = r7.f5901f
            boolean r1 = r2.compareAndSet(r1, r5)
            if (r1 == 0) goto L8
            r1 = 1
        L2b:
            if (r1 == 0) goto L8b
            boolean r8 = r0.f5912k
            if (r8 == 0) goto L36
            r7.m2382c(r0)
            goto L9e
        L36:
            boolean r8 = r0.f5912k
            if (r8 == 0) goto L3b
            goto L9e
        L3b:
            monitor-enter(r0)
            boolean r8 = r0.f5912k     // Catch: java.lang.Throwable -> L88
            if (r8 == 0) goto L42
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            goto L9e
        L42:
            boolean r8 = r0.f5908g     // Catch: java.lang.Throwable -> L88
            if (r8 == 0) goto L48
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            goto L9e
        L48:
            w4.a<T> r8 = r0.f5907f     // Catch: java.lang.Throwable -> L88
            java.util.concurrent.locks.Lock r1 = r8.f5902g     // Catch: java.lang.Throwable -> L88
            r1.lock()     // Catch: java.lang.Throwable -> L88
            long r5 = r8.f5905j     // Catch: java.lang.Throwable -> L88
            r0.f5913l = r5     // Catch: java.lang.Throwable -> L88
            java.util.concurrent.atomic.AtomicReference<java.lang.Object> r8 = r8.f5900e     // Catch: java.lang.Throwable -> L88
            java.lang.Object r8 = r8.get()     // Catch: java.lang.Throwable -> L88
            r1.unlock()     // Catch: java.lang.Throwable -> L88
            if (r8 == 0) goto L60
            r1 = 1
            goto L61
        L60:
            r1 = 0
        L61:
            r0.f5909h = r1     // Catch: java.lang.Throwable -> L88
            r0.f5908g = r4     // Catch: java.lang.Throwable -> L88
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            if (r8 == 0) goto L9e
            boolean r8 = r0.test(r8)
            if (r8 == 0) goto L6f
            goto L9e
        L6f:
            boolean r8 = r0.f5912k
            if (r8 == 0) goto L74
            goto L9e
        L74:
            monitor-enter(r0)
            q4.a<java.lang.Object> r8 = r0.f5910i     // Catch: java.lang.Throwable -> L85
            if (r8 != 0) goto L7d
            r0.f5909h = r3     // Catch: java.lang.Throwable -> L85
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L85
            goto L9e
        L7d:
            r1 = 0
            r0.f5910i = r1     // Catch: java.lang.Throwable -> L85
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L85
            r8.m1957b(r0)
            goto L6f
        L85:
            r8 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L85
            throw r8
        L88:
            r8 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            throw r8
        L8b:
            java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r0 = r7.f5904i
            java.lang.Object r0 = r0.get()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.Throwable r1 = p138q4.C1774f.f5055a
            if (r0 != r1) goto L9b
            r8.onComplete()
            goto L9e
        L9b:
            r8.onError(r0)
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p181w4.C2029a.subscribeActual(y3.s):void");
    }
}
