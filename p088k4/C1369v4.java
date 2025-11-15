package p088k4;

import io.reactivex.ObservableSource;
import io.reactivex.internal.operators.observable.ObservableZip;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p104m4.C1489c;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableZip.java */
/* renamed from: k4.v4 */
/* loaded from: classes.dex */
public final class C1369v4<T, R> extends AbstractC2120l<R> {

    /* renamed from: e */
    public final ObservableSource<? extends T>[] f3905e;

    /* renamed from: f */
    public final Iterable<? extends InterfaceC2125q<? extends T>> f3906f;

    /* renamed from: g */
    public final InterfaceC0454n<? super Object[], ? extends R> f3907g;

    /* renamed from: h */
    public final int f3908h;

    /* renamed from: i */
    public final boolean f3909i;

    /* compiled from: ObservableZip.java */
    /* renamed from: k4.v4$a */
    public static final class a<T, R> extends AtomicInteger implements InterfaceC2153b {
        private static final long serialVersionUID = 2983708048395377667L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3910e;

        /* renamed from: f */
        public final InterfaceC0454n<? super Object[], ? extends R> f3911f;

        /* renamed from: g */
        public final ObservableZip.ZipObserver<T, R>[] f3912g;

        /* renamed from: h */
        public final T[] f3913h;

        /* renamed from: i */
        public final boolean f3914i;

        /* renamed from: j */
        public volatile boolean f3915j;

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7, boolean z6) {
            this.f3910e = interfaceC2127s;
            this.f3911f = interfaceC0454n;
            this.f3912g = new b[i7];
            this.f3913h = (T[]) new Object[i7];
            this.f3914i = z6;
        }

        /* renamed from: a */
        public void m1551a() {
            for (b bVar : this.f3912g) {
                bVar.f3917f.clear();
            }
            for (b bVar2 : this.f3912g) {
                EnumC0515c.m323a(bVar2.f3920i);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0061 A[SYNTHETIC] */
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m1552b() {
            /*
                r16 = this;
                r1 = r16
                int r0 = r16.getAndIncrement()
                if (r0 == 0) goto L9
                return
            L9:
                io.reactivex.internal.operators.observable.ObservableZip$ZipObserver<T, R>[] r0 = r1.f3912g
                y3.s<? super R> r2 = r1.f3910e
                T[] r3 = r1.f3913h
                boolean r4 = r1.f3914i
                r6 = 1
            L12:
                int r7 = r0.length
                r8 = 0
                r9 = 0
                r10 = 0
                r11 = 0
            L17:
                if (r9 >= r7) goto L80
                r12 = r0[r9]
                r13 = r3[r11]
                if (r13 != 0) goto L6a
                boolean r13 = r12.f3918g
                m4.c<T> r14 = r12.f3917f
                java.lang.Object r14 = r14.poll()
                if (r14 != 0) goto L2b
                r15 = 1
                goto L2c
            L2b:
                r15 = 0
            L2c:
                boolean r5 = r1.f3915j
                if (r5 == 0) goto L35
                r16.m1551a()
            L33:
                r5 = 1
                goto L5f
            L35:
                if (r13 == 0) goto L5e
                if (r4 == 0) goto L4a
                if (r15 == 0) goto L5e
                java.lang.Throwable r5 = r12.f3919h
                r16.m1551a()
                if (r5 == 0) goto L46
                r2.onError(r5)
                goto L33
            L46:
                r2.onComplete()
                goto L33
            L4a:
                java.lang.Throwable r5 = r12.f3919h
                if (r5 == 0) goto L55
                r16.m1551a()
                r2.onError(r5)
                goto L33
            L55:
                if (r15 == 0) goto L5e
                r16.m1551a()
                r2.onComplete()
                goto L33
            L5e:
                r5 = 0
            L5f:
                if (r5 == 0) goto L62
                return
            L62:
                if (r15 != 0) goto L67
                r3[r11] = r14
                goto L7b
            L67:
                int r10 = r10 + 1
                goto L7b
            L6a:
                boolean r5 = r12.f3918g
                if (r5 == 0) goto L7b
                if (r4 != 0) goto L7b
                java.lang.Throwable r5 = r12.f3919h
                if (r5 == 0) goto L7b
                r16.m1551a()
                r2.onError(r5)
                return
            L7b:
                int r11 = r11 + 1
                int r9 = r9 + 1
                goto L17
            L80:
                if (r10 == 0) goto L8a
                int r5 = -r6
                int r6 = r1.addAndGet(r5)
                if (r6 != 0) goto L12
                return
            L8a:
                b4.n<? super java.lang.Object[], ? extends R> r5 = r1.f3911f     // Catch: java.lang.Throwable -> La2
                java.lang.Object r7 = r3.clone()     // Catch: java.lang.Throwable -> La2
                java.lang.Object r5 = r5.apply(r7)     // Catch: java.lang.Throwable -> La2
                java.lang.String r7 = "The zipper returned a null value"
                java.util.Objects.requireNonNull(r5, r7)     // Catch: java.lang.Throwable -> La2
                r2.onNext(r5)
                r5 = 0
                java.util.Arrays.fill(r3, r5)
                goto L12
            La2:
                r0 = move-exception
                p186x2.C2074b.m2470J(r0)
                r16.m1551a()
                r2.onError(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p088k4.C1369v4.a.m1552b():void");
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3915j) {
                return;
            }
            this.f3915j = true;
            for (b bVar : this.f3912g) {
                EnumC0515c.m323a(bVar.f3920i);
            }
            if (getAndIncrement() == 0) {
                for (b bVar2 : this.f3912g) {
                    bVar2.f3917f.clear();
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3915j;
        }
    }

    /* compiled from: ObservableZip.java */
    /* renamed from: k4.v4$b */
    public static final class b<T, R> implements InterfaceC2127s<T> {

        /* renamed from: e */
        public final a<T, R> f3916e;

        /* renamed from: f */
        public final C1489c<T> f3917f;

        /* renamed from: g */
        public volatile boolean f3918g;

        /* renamed from: h */
        public Throwable f3919h;

        /* renamed from: i */
        public final AtomicReference<InterfaceC2153b> f3920i = new AtomicReference<>();

        public b(a<T, R> aVar, int i7) {
            this.f3916e = aVar;
            this.f3917f = new C1489c<>(i7);
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3918g = true;
            this.f3916e.m1552b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3919h = th;
            this.f3918g = true;
            this.f3916e.m1552b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3917f.offer(t6);
            this.f3916e.m1552b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3920i, interfaceC2153b);
        }
    }

    public C1369v4(ObservableSource<? extends T>[] observableSourceArr, Iterable<? extends InterfaceC2125q<? extends T>> iterable, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7, boolean z6) {
        this.f3905e = observableSourceArr;
        this.f3906f = iterable;
        this.f3907g = interfaceC0454n;
        this.f3908h = i7;
        this.f3909i = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        int length;
        ObservableSource<? extends T>[] observableSourceArr = this.f3905e;
        if (observableSourceArr == null) {
            observableSourceArr = new AbstractC2120l[8];
            Iterator<? extends InterfaceC2125q<? extends T>> it = this.f3906f.iterator();
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
        if (length == 0) {
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onComplete();
            return;
        }
        a aVar = new a(interfaceC2127s, this.f3907g, length, this.f3909i);
        int i7 = this.f3908h;
        InterfaceC2127s<? super T>[] interfaceC2127sArr = aVar.f3912g;
        int length2 = interfaceC2127sArr.length;
        for (int i8 = 0; i8 < length2; i8++) {
            interfaceC2127sArr[i8] = new b(aVar, i7);
        }
        aVar.lazySet(0);
        aVar.f3910e.onSubscribe(aVar);
        for (int i9 = 0; i9 < length2 && !aVar.f3915j; i9++) {
            observableSourceArr[i9].subscribe(interfaceC2127sArr[i9]);
        }
    }
}
