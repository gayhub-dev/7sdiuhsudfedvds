package p088k4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p104m4.C1489c;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableGroupBy.java */
/* renamed from: k4.f1 */
/* loaded from: classes.dex */
public final class C1270f1<T, K, V> extends AbstractC1238a<T, AbstractC1238a> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends K> f3032f;

    /* renamed from: g */
    public final InterfaceC0454n<? super T, ? extends V> f3033g;

    /* renamed from: h */
    public final int f3034h;

    /* renamed from: i */
    public final boolean f3035i;

    /* compiled from: ObservableGroupBy.java */
    /* renamed from: k4.f1$a */
    public static final class a<T, K, V> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: m */
        public static final Object f3036m = new Object();
        private static final long serialVersionUID = -3688291656102519502L;

        /* renamed from: e */
        public final InterfaceC2127s<? super AbstractC1238a> f3037e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends K> f3038f;

        /* renamed from: g */
        public final InterfaceC0454n<? super T, ? extends V> f3039g;

        /* renamed from: h */
        public final int f3040h;

        /* renamed from: i */
        public final boolean f3041i;

        /* renamed from: k */
        public InterfaceC2153b f3043k;

        /* renamed from: l */
        public final AtomicBoolean f3044l = new AtomicBoolean();

        /* renamed from: j */
        public final Map<Object, b<K, V>> f3042j = new ConcurrentHashMap();

        public a(InterfaceC2127s<? super AbstractC1238a> interfaceC2127s, InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2, int i7, boolean z6) {
            this.f3037e = interfaceC2127s;
            this.f3038f = interfaceC0454n;
            this.f3039g = interfaceC0454n2;
            this.f3040h = i7;
            this.f3041i = z6;
            lazySet(1);
        }

        /* renamed from: a */
        public void m1494a(K k7) {
            if (k7 == null) {
                k7 = (K) f3036m;
            }
            this.f3042j.remove(k7);
            if (decrementAndGet() == 0) {
                this.f3043k.dispose();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3044l.compareAndSet(false, true) && decrementAndGet() == 0) {
                this.f3043k.dispose();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3044l.get();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            ArrayList arrayList = new ArrayList(this.f3042j.values());
            this.f3042j.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                c<T, K> cVar = ((b) it.next()).f3045f;
                cVar.f3050i = true;
                cVar.m1495a();
            }
            this.f3037e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            ArrayList arrayList = new ArrayList(this.f3042j.values());
            this.f3042j.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                c<T, K> cVar = ((b) it.next()).f3045f;
                cVar.f3051j = th;
                cVar.f3050i = true;
                cVar.m1495a();
            }
            this.f3037e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            try {
                K kApply = this.f3038f.apply(t6);
                Object obj = kApply != null ? kApply : f3036m;
                b<K, V> bVar = this.f3042j.get(obj);
                if (bVar == null) {
                    if (this.f3044l.get()) {
                        return;
                    }
                    bVar = new b<>(kApply, new c(this.f3040h, this, kApply, this.f3041i));
                    this.f3042j.put(obj, bVar);
                    getAndIncrement();
                    this.f3037e.onNext(bVar);
                }
                try {
                    V vApply = this.f3039g.apply(t6);
                    Objects.requireNonNull(vApply, "The value supplied is null");
                    c<V, K> cVar = bVar.f3045f;
                    cVar.f3047f.offer(vApply);
                    cVar.m1495a();
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    this.f3043k.dispose();
                    onError(th);
                }
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                this.f3043k.dispose();
                onError(th2);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3043k, interfaceC2153b)) {
                this.f3043k = interfaceC2153b;
                this.f3037e.onSubscribe(this);
            }
        }
    }

    /* compiled from: ObservableGroupBy.java */
    /* renamed from: k4.f1$b */
    public static final class b<K, T> extends AbstractC1238a {

        /* renamed from: f */
        public final c<T, K> f3045f;

        public b(K k7, c<T, K> cVar) {
            super(k7);
            this.f3045f = cVar;
        }

        @Override // p194y3.AbstractC2120l
        public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3045f.subscribe(interfaceC2127s);
        }
    }

    /* compiled from: ObservableGroupBy.java */
    /* renamed from: k4.f1$c */
    public static final class c<T, K> extends AtomicInteger implements InterfaceC2153b, InterfaceC2125q<T> {
        private static final long serialVersionUID = -3852313036005250360L;

        /* renamed from: e */
        public final K f3046e;

        /* renamed from: f */
        public final C1489c<T> f3047f;

        /* renamed from: g */
        public final a<?, K, T> f3048g;

        /* renamed from: h */
        public final boolean f3049h;

        /* renamed from: i */
        public volatile boolean f3050i;

        /* renamed from: j */
        public Throwable f3051j;

        /* renamed from: k */
        public final AtomicBoolean f3052k = new AtomicBoolean();

        /* renamed from: l */
        public final AtomicBoolean f3053l = new AtomicBoolean();

        /* renamed from: m */
        public final AtomicReference<InterfaceC2127s<? super T>> f3054m = new AtomicReference<>();

        public c(int i7, a<?, K, T> aVar, K k7, boolean z6) {
            this.f3047f = new C1489c<>(i7);
            this.f3048g = aVar;
            this.f3046e = k7;
            this.f3049h = z6;
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0076  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0075 A[SYNTHETIC] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m1495a() {
            /*
                r11 = this;
                int r0 = r11.getAndIncrement()
                if (r0 == 0) goto L7
                return
            L7:
                m4.c<T> r0 = r11.f3047f
                boolean r1 = r11.f3049h
                java.util.concurrent.atomic.AtomicReference<y3.s<? super T>> r2 = r11.f3054m
                java.lang.Object r2 = r2.get()
                y3.s r2 = (p194y3.InterfaceC2127s) r2
                r3 = 1
                r4 = 1
            L15:
                if (r2 == 0) goto L7d
            L17:
                boolean r5 = r11.f3050i
                java.lang.Object r6 = r0.poll()
                r7 = 0
                if (r6 != 0) goto L22
                r8 = 1
                goto L23
            L22:
                r8 = 0
            L23:
                java.util.concurrent.atomic.AtomicBoolean r9 = r11.f3052k
                boolean r9 = r9.get()
                r10 = 0
                if (r9 == 0) goto L3f
                m4.c<T> r5 = r11.f3047f
                r5.clear()
                k4.f1$a<?, K, T> r5 = r11.f3048g
                K r7 = r11.f3046e
                r5.m1494a(r7)
                java.util.concurrent.atomic.AtomicReference<y3.s<? super T>> r5 = r11.f3054m
                r5.lazySet(r10)
            L3d:
                r7 = 1
                goto L73
            L3f:
                if (r5 == 0) goto L73
                if (r1 == 0) goto L56
                if (r8 == 0) goto L73
                java.lang.Throwable r5 = r11.f3051j
                java.util.concurrent.atomic.AtomicReference<y3.s<? super T>> r7 = r11.f3054m
                r7.lazySet(r10)
                if (r5 == 0) goto L52
                r2.onError(r5)
                goto L3d
            L52:
                r2.onComplete()
                goto L3d
            L56:
                java.lang.Throwable r5 = r11.f3051j
                if (r5 == 0) goto L68
                m4.c<T> r7 = r11.f3047f
                r7.clear()
                java.util.concurrent.atomic.AtomicReference<y3.s<? super T>> r7 = r11.f3054m
                r7.lazySet(r10)
                r2.onError(r5)
                goto L3d
            L68:
                if (r8 == 0) goto L73
                java.util.concurrent.atomic.AtomicReference<y3.s<? super T>> r5 = r11.f3054m
                r5.lazySet(r10)
                r2.onComplete()
                goto L3d
            L73:
                if (r7 == 0) goto L76
                return
            L76:
                if (r8 == 0) goto L79
                goto L7d
            L79:
                r2.onNext(r6)
                goto L17
            L7d:
                int r4 = -r4
                int r4 = r11.addAndGet(r4)
                if (r4 != 0) goto L85
                return
            L85:
                if (r2 != 0) goto L15
                java.util.concurrent.atomic.AtomicReference<y3.s<? super T>> r2 = r11.f3054m
                java.lang.Object r2 = r2.get()
                y3.s r2 = (p194y3.InterfaceC2127s) r2
                goto L15
            */
            throw new UnsupportedOperationException("Method not decompiled: p088k4.C1270f1.c.m1495a():void");
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3052k.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.f3054m.lazySet(null);
                this.f3048g.m1494a(this.f3046e);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3052k.get();
        }

        @Override // p194y3.InterfaceC2125q
        public void subscribe(InterfaceC2127s<? super T> interfaceC2127s) {
            if (!this.f3053l.compareAndSet(false, true)) {
                IllegalStateException illegalStateException = new IllegalStateException("Only one Observer allowed!");
                interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
                interfaceC2127s.onError(illegalStateException);
            } else {
                interfaceC2127s.onSubscribe(this);
                this.f3054m.lazySet(interfaceC2127s);
                if (this.f3052k.get()) {
                    this.f3054m.lazySet(null);
                } else {
                    m1495a();
                }
            }
        }
    }

    public C1270f1(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2, int i7, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3032f = interfaceC0454n;
        this.f3033g = interfaceC0454n2;
        this.f3034h = i7;
        this.f3035i = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super AbstractC1238a> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3032f, this.f3033g, this.f3034h, this.f3035i));
    }
}
