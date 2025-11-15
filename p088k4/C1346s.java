package p088k4;

import io.reactivex.ObservableSource;
import io.reactivex.internal.operators.observable.ObservableCombineLatest;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableCombineLatest.java */
/* renamed from: k4.s */
/* loaded from: classes.dex */
public final class C1346s<T, R> extends AbstractC2120l<R> {

    /* renamed from: e */
    public final ObservableSource<? extends T>[] f3666e;

    /* renamed from: f */
    public final Iterable<? extends InterfaceC2125q<? extends T>> f3667f;

    /* renamed from: g */
    public final InterfaceC0454n<? super Object[], ? extends R> f3668g;

    /* renamed from: h */
    public final int f3669h;

    /* renamed from: i */
    public final boolean f3670i;

    /* compiled from: ObservableCombineLatest.java */
    /* renamed from: k4.s$a */
    public static final class a<T, R> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T> {
        private static final long serialVersionUID = -4823716997131257941L;

        /* renamed from: e */
        public final b<T, R> f3671e;

        /* renamed from: f */
        public final int f3672f;

        public a(b<T, R> bVar, int i7) {
            this.f3671e = bVar;
            this.f3672f = i7;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d A[Catch: all -> 0x0029, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x0009, B:8:0x000b, B:13:0x0015, B:16:0x001f, B:15:0x001d), top: B:24:0x0005 }] */
        @Override // p194y3.InterfaceC2127s
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onComplete() {
            /*
                r5 = this;
                k4.s$b<T, R> r0 = r5.f3671e
                int r1 = r5.f3672f
                monitor-enter(r0)
                java.lang.Object[] r2 = r0.f3676h     // Catch: java.lang.Throwable -> L29
                if (r2 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L29
                goto L28
            Lb:
                r1 = r2[r1]     // Catch: java.lang.Throwable -> L29
                r3 = 1
                if (r1 != 0) goto L12
                r1 = 1
                goto L13
            L12:
                r1 = 0
            L13:
                if (r1 != 0) goto L1d
                int r4 = r0.f3683o     // Catch: java.lang.Throwable -> L29
                int r4 = r4 + r3
                r0.f3683o = r4     // Catch: java.lang.Throwable -> L29
                int r2 = r2.length     // Catch: java.lang.Throwable -> L29
                if (r4 != r2) goto L1f
            L1d:
                r0.f3680l = r3     // Catch: java.lang.Throwable -> L29
            L1f:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L29
                if (r1 == 0) goto L25
                r0.m1533a()
            L25:
                r0.m1535c()
            L28:
                return
            L29:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L29
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: p088k4.C1346s.a.onComplete():void");
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0029 A[Catch: all -> 0x002e, TryCatch #0 {, blocks: (B:7:0x0012, B:9:0x0016, B:11:0x0018, B:16:0x0021, B:19:0x002b, B:18:0x0029), top: B:29:0x0012 }] */
        @Override // p194y3.InterfaceC2127s
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onError(java.lang.Throwable r5) {
            /*
                r4 = this;
                k4.s$b<T, R> r0 = r4.f3671e
                int r1 = r4.f3672f
                q4.c r2 = r0.f3681m
                boolean r2 = p138q4.C1774f.m1958a(r2, r5)
                if (r2 == 0) goto L3a
                boolean r5 = r0.f3678j
                r2 = 1
                if (r5 == 0) goto L31
                monitor-enter(r0)
                java.lang.Object[] r5 = r0.f3676h     // Catch: java.lang.Throwable -> L2e
                if (r5 != 0) goto L18
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L2e
                goto L3d
            L18:
                r1 = r5[r1]     // Catch: java.lang.Throwable -> L2e
                if (r1 != 0) goto L1e
                r1 = 1
                goto L1f
            L1e:
                r1 = 0
            L1f:
                if (r1 != 0) goto L29
                int r3 = r0.f3683o     // Catch: java.lang.Throwable -> L2e
                int r3 = r3 + r2
                r0.f3683o = r3     // Catch: java.lang.Throwable -> L2e
                int r5 = r5.length     // Catch: java.lang.Throwable -> L2e
                if (r3 != r5) goto L2b
            L29:
                r0.f3680l = r2     // Catch: java.lang.Throwable -> L2e
            L2b:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L2e
                r2 = r1
                goto L31
            L2e:
                r5 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L2e
                throw r5
            L31:
                if (r2 == 0) goto L36
                r0.m1533a()
            L36:
                r0.m1535c()
                goto L3d
            L3a:
                p160t4.C1908a.m2205b(r5)
            L3d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p088k4.C1346s.a.onError(java.lang.Throwable):void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            boolean z6;
            b<T, R> bVar = this.f3671e;
            int i7 = this.f3672f;
            synchronized (bVar) {
                Object[] objArr = bVar.f3676h;
                if (objArr == null) {
                    return;
                }
                Object obj = objArr[i7];
                int i8 = bVar.f3682n;
                if (obj == null) {
                    i8++;
                    bVar.f3682n = i8;
                }
                objArr[i7] = t6;
                if (i8 == objArr.length) {
                    bVar.f3677i.offer(objArr.clone());
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (z6) {
                    bVar.m1535c();
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this, interfaceC2153b);
        }
    }

    /* compiled from: ObservableCombineLatest.java */
    /* renamed from: k4.s$b */
    public static final class b<T, R> extends AtomicInteger implements InterfaceC2153b {
        private static final long serialVersionUID = 8567835998786448817L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3673e;

        /* renamed from: f */
        public final InterfaceC0454n<? super Object[], ? extends R> f3674f;

        /* renamed from: g */
        public final ObservableCombineLatest.CombinerObserver<T, R>[] f3675g;

        /* renamed from: h */
        public Object[] f3676h;

        /* renamed from: i */
        public final C1489c<Object[]> f3677i;

        /* renamed from: j */
        public final boolean f3678j;

        /* renamed from: k */
        public volatile boolean f3679k;

        /* renamed from: l */
        public volatile boolean f3680l;

        /* renamed from: m */
        public final C1771c f3681m = new C1771c();

        /* renamed from: n */
        public int f3682n;

        /* renamed from: o */
        public int f3683o;

        public b(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7, int i8, boolean z6) {
            this.f3673e = interfaceC2127s;
            this.f3674f = interfaceC0454n;
            this.f3678j = z6;
            this.f3676h = new Object[i7];
            a[] aVarArr = new a[i7];
            for (int i9 = 0; i9 < i7; i9++) {
                aVarArr[i9] = new a(this, i9);
            }
            this.f3675g = aVarArr;
            this.f3677i = new C1489c<>(i8);
        }

        /* renamed from: a */
        public void m1533a() {
            for (AtomicReference atomicReference : this.f3675g) {
                EnumC0515c.m323a(atomicReference);
            }
        }

        /* renamed from: b */
        public void m1534b(C1489c<?> c1489c) {
            synchronized (this) {
                this.f3676h = null;
            }
            c1489c.clear();
        }

        /* renamed from: c */
        public void m1535c() {
            if (getAndIncrement() != 0) {
                return;
            }
            C1489c<Object[]> c1489c = this.f3677i;
            InterfaceC2127s<? super R> interfaceC2127s = this.f3673e;
            boolean z6 = this.f3678j;
            int iAddAndGet = 1;
            while (!this.f3679k) {
                if (!z6 && this.f3681m.get() != null) {
                    m1533a();
                    m1534b(c1489c);
                    interfaceC2127s.onError(C1774f.m1959b(this.f3681m));
                    return;
                }
                boolean z7 = this.f3680l;
                Object[] objArrPoll = c1489c.poll();
                boolean z8 = objArrPoll == null;
                if (z7 && z8) {
                    m1534b(c1489c);
                    Throwable thM1959b = C1774f.m1959b(this.f3681m);
                    if (thM1959b == null) {
                        interfaceC2127s.onComplete();
                        return;
                    } else {
                        interfaceC2127s.onError(thM1959b);
                        return;
                    }
                }
                if (z8) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    try {
                        R rApply = this.f3674f.apply(objArrPoll);
                        Objects.requireNonNull(rApply, "The combiner returned a null value");
                        interfaceC2127s.onNext(rApply);
                    } catch (Throwable th) {
                        C2074b.m2470J(th);
                        C1774f.m1958a(this.f3681m, th);
                        m1533a();
                        m1534b(c1489c);
                        interfaceC2127s.onError(C1774f.m1959b(this.f3681m));
                        return;
                    }
                }
            }
            m1534b(c1489c);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3679k) {
                return;
            }
            this.f3679k = true;
            m1533a();
            if (getAndIncrement() == 0) {
                m1534b(this.f3677i);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3679k;
        }
    }

    public C1346s(ObservableSource<? extends T>[] observableSourceArr, Iterable<? extends InterfaceC2125q<? extends T>> iterable, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7, boolean z6) {
        this.f3666e = observableSourceArr;
        this.f3667f = iterable;
        this.f3668g = interfaceC0454n;
        this.f3669h = i7;
        this.f3670i = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        int length;
        ObservableSource<? extends T>[] observableSourceArr = this.f3666e;
        if (observableSourceArr == null) {
            observableSourceArr = new AbstractC2120l[8];
            Iterator<? extends InterfaceC2125q<? extends T>> it = this.f3667f.iterator();
            length = 0;
            while (it.hasNext()) {
                ObservableSource<? extends T> observableSource = (InterfaceC2125q) it.next();
                if (length == observableSourceArr.length) {
                    ObservableSource<? extends T>[] observableSourceArr2 = new InterfaceC2125q[(length >> 2) + length];
                    System.arraycopy(observableSourceArr, 0, observableSourceArr2, 0, length);
                    observableSourceArr = observableSourceArr2;
                }
                observableSourceArr[length] = observableSource;
                length++;
            }
        } else {
            length = observableSourceArr.length;
        }
        int i7 = length;
        if (i7 == 0) {
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onComplete();
            return;
        }
        b bVar = new b(interfaceC2127s, this.f3668g, i7, this.f3669h, this.f3670i);
        InterfaceC2127s<? super T>[] interfaceC2127sArr = bVar.f3675g;
        int length2 = interfaceC2127sArr.length;
        bVar.f3673e.onSubscribe(bVar);
        for (int i8 = 0; i8 < length2 && !bVar.f3680l && !bVar.f3679k; i8++) {
            observableSourceArr[i8].subscribe(interfaceC2127sArr[i8]);
        }
    }
}
