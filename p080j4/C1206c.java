package p080j4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0954g;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p194y3.InterfaceC2131w;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableConcatMapSingle.java */
/* renamed from: j4.c */
/* loaded from: classes.dex */
public final class C1206c<T, R> extends AbstractC2120l<R> {

    /* renamed from: e */
    public final AbstractC2120l<T> f2670e;

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> f2671f;

    /* renamed from: g */
    public final int f2672g;

    /* renamed from: h */
    public final int f2673h;

    /* compiled from: ObservableConcatMapSingle.java */
    /* renamed from: j4.c$a */
    public static final class a<T, R> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -9140123220065488293L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f2674e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> f2675f;

        /* renamed from: g */
        public final C1771c f2676g = new C1771c();

        /* renamed from: h */
        public final C2166a<R> f2677h = new C2166a<>(this);

        /* renamed from: i */
        public final InterfaceC0954g<T> f2678i;

        /* renamed from: j */
        public final int f2679j;

        /* renamed from: k */
        public InterfaceC2153b f2680k;

        /* renamed from: l */
        public volatile boolean f2681l;

        /* renamed from: m */
        public volatile boolean f2682m;

        /* renamed from: n */
        public R f2683n;

        /* renamed from: o */
        public volatile int f2684o;

        /* compiled from: ObservableConcatMapSingle.java */
        /* renamed from: j4.c$a$a, reason: collision with other inner class name */
        public static final class C2166a<R> extends AtomicReference<InterfaceC2153b> implements InterfaceC2130v<R> {
            private static final long serialVersionUID = -3051469169682093892L;

            /* renamed from: e */
            public final a<?, R> f2685e;

            public C2166a(a<?, R> aVar) {
                this.f2685e = aVar;
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2117i
            /* renamed from: a */
            public void mo1016a(R r6) {
                a<?, R> aVar = this.f2685e;
                aVar.f2683n = r6;
                aVar.f2684o = 2;
                aVar.m1433a();
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a<?, R> aVar = this.f2685e;
                if (!C1774f.m1958a(aVar.f2676g, th)) {
                    C1908a.m2205b(th);
                    return;
                }
                if (aVar.f2679j != 3) {
                    aVar.f2680k.dispose();
                }
                aVar.f2684o = 0;
                aVar.m1433a();
            }

            @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m325c(this, interfaceC2153b);
            }
        }

        /* JADX WARN: Incorrect types in method signature: (Ly3/s<-TR;>;Lb4/n<-TT;+Ly3/w<+TR;>;>;ILjava/lang/Object;)V */
        public a(InterfaceC2127s interfaceC2127s, InterfaceC0454n interfaceC0454n, int i7, int i8) {
            this.f2674e = interfaceC2127s;
            this.f2675f = interfaceC0454n;
            this.f2679j = i8;
            this.f2678i = new C1489c(i7);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
        
            r2.clear();
            r10.f2683n = null;
            r0.onError(p138q4.C1774f.m1959b(r3));
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        
            return;
         */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m1433a() {
            /*
                r10 = this;
                int r0 = r10.getAndIncrement()
                if (r0 == 0) goto L7
                return
            L7:
                y3.s<? super R> r0 = r10.f2674e
                int r1 = r10.f2679j
                e4.g<T> r2 = r10.f2678i
                q4.c r3 = r10.f2676g
                r4 = 1
                r5 = 1
            L11:
                boolean r6 = r10.f2682m
                r7 = 0
                if (r6 == 0) goto L1d
                r2.clear()
                r10.f2683n = r7
                goto L93
            L1d:
                int r6 = r10.f2684o
                java.lang.Object r8 = r3.get()
                r9 = 2
                if (r8 == 0) goto L39
                if (r1 == r4) goto L2c
                if (r1 != r9) goto L39
                if (r6 != 0) goto L39
            L2c:
                r2.clear()
                r10.f2683n = r7
                java.lang.Throwable r1 = p138q4.C1774f.m1959b(r3)
                r0.onError(r1)
                return
            L39:
                r8 = 0
                if (r6 != 0) goto L86
                boolean r6 = r10.f2681l
                java.lang.Object r7 = r2.poll()
                if (r7 != 0) goto L45
                r8 = 1
            L45:
                if (r6 == 0) goto L57
                if (r8 == 0) goto L57
                java.lang.Throwable r1 = p138q4.C1774f.m1959b(r3)
                if (r1 != 0) goto L53
                r0.onComplete()
                goto L56
            L53:
                r0.onError(r1)
            L56:
                return
            L57:
                if (r8 == 0) goto L5a
                goto L93
            L5a:
                b4.n<? super T, ? extends y3.w<? extends R>> r6 = r10.f2675f     // Catch: java.lang.Throwable -> L6f
                java.lang.Object r6 = r6.apply(r7)     // Catch: java.lang.Throwable -> L6f
                java.lang.String r7 = "The mapper returned a null SingleSource"
                java.util.Objects.requireNonNull(r6, r7)     // Catch: java.lang.Throwable -> L6f
                y3.w r6 = (p194y3.InterfaceC2131w) r6     // Catch: java.lang.Throwable -> L6f
                r10.f2684o = r4
                j4.c$a$a<R> r7 = r10.f2677h
                r6.mo2562b(r7)
                goto L93
            L6f:
                r1 = move-exception
                p186x2.C2074b.m2470J(r1)
                z3.b r4 = r10.f2680k
                r4.dispose()
                r2.clear()
                p138q4.C1774f.m1958a(r3, r1)
                java.lang.Throwable r1 = p138q4.C1774f.m1959b(r3)
                r0.onError(r1)
                return
            L86:
                if (r6 != r9) goto L93
                R r6 = r10.f2683n
                r10.f2683n = r7
                r0.onNext(r6)
                r10.f2684o = r8
                goto L11
            L93:
                int r5 = -r5
                int r5 = r10.addAndGet(r5)
                if (r5 != 0) goto L11
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p080j4.C1206c.a.m1433a():void");
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2682m = true;
            this.f2680k.dispose();
            EnumC0515c.m323a(this.f2677h);
            if (getAndIncrement() == 0) {
                this.f2678i.clear();
                this.f2683n = null;
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2682m;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2681l = true;
            m1433a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!C1774f.m1958a(this.f2676g, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (this.f2679j == 1) {
                EnumC0515c.m323a(this.f2677h);
            }
            this.f2681l = true;
            m1433a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f2678i.offer(t6);
            m1433a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f2680k, interfaceC2153b)) {
                this.f2680k = interfaceC2153b;
                this.f2674e.onSubscribe(this);
            }
        }
    }

    /* JADX WARN: Incorrect types in method signature: (Ly3/l<TT;>;Lb4/n<-TT;+Ly3/w<+TR;>;>;Ljava/lang/Object;I)V */
    public C1206c(AbstractC2120l abstractC2120l, InterfaceC0454n interfaceC0454n, int i7, int i8) {
        this.f2670e = abstractC2120l;
        this.f2671f = interfaceC0454n;
        this.f2672g = i7;
        this.f2673h = i8;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        if (C2074b.m2474N(this.f2670e, this.f2671f, interfaceC2127s)) {
            return;
        }
        this.f2670e.subscribe(new a(interfaceC2127s, this.f2671f, this.f2673h, this.f2672g));
    }
}
