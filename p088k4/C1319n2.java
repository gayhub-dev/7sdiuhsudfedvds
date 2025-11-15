package p088k4;

import p022c4.EnumC0515c;
import p040e4.InterfaceC0950c;
import p040e4.InterfaceC0955h;
import p048f4.AbstractC0997b;
import p104m4.C1489c;
import p112n4.C1539n;
import p160t4.C1908a;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableObserveOn.java */
/* renamed from: k4.n2 */
/* loaded from: classes.dex */
public final class C1319n2<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final AbstractC2128t f3408f;

    /* renamed from: g */
    public final boolean f3409g;

    /* renamed from: h */
    public final int f3410h;

    /* compiled from: ObservableObserveOn.java */
    /* renamed from: k4.n2$a */
    public static final class a<T> extends AbstractC0997b<T> implements InterfaceC2127s<T>, Runnable {
        private static final long serialVersionUID = 6576896619930983584L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3411e;

        /* renamed from: f */
        public final AbstractC2128t.c f3412f;

        /* renamed from: g */
        public final boolean f3413g;

        /* renamed from: h */
        public final int f3414h;

        /* renamed from: i */
        public InterfaceC0955h<T> f3415i;

        /* renamed from: j */
        public InterfaceC2153b f3416j;

        /* renamed from: k */
        public Throwable f3417k;

        /* renamed from: l */
        public volatile boolean f3418l;

        /* renamed from: m */
        public volatile boolean f3419m;

        /* renamed from: n */
        public int f3420n;

        /* renamed from: o */
        public boolean f3421o;

        public a(InterfaceC2127s<? super T> interfaceC2127s, AbstractC2128t.c cVar, boolean z6, int i7) {
            this.f3411e = interfaceC2127s;
            this.f3412f = cVar;
            this.f3413g = z6;
            this.f3414h = i7;
        }

        /* renamed from: b */
        public boolean m1523b(boolean z6, boolean z7, InterfaceC2127s<? super T> interfaceC2127s) {
            if (this.f3419m) {
                this.f3415i.clear();
                return true;
            }
            if (!z6) {
                return false;
            }
            Throwable th = this.f3417k;
            if (this.f3413g) {
                if (!z7) {
                    return false;
                }
                this.f3419m = true;
                if (th != null) {
                    interfaceC2127s.onError(th);
                } else {
                    interfaceC2127s.onComplete();
                }
                this.f3412f.dispose();
                return true;
            }
            if (th != null) {
                this.f3419m = true;
                this.f3415i.clear();
                interfaceC2127s.onError(th);
                this.f3412f.dispose();
                return true;
            }
            if (!z7) {
                return false;
            }
            this.f3419m = true;
            interfaceC2127s.onComplete();
            this.f3412f.dispose();
            return true;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            if ((i7 & 2) == 0) {
                return 0;
            }
            this.f3421o = true;
            return 2;
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            this.f3415i.clear();
        }

        /* renamed from: d */
        public void m1524d() {
            if (getAndIncrement() == 0) {
                this.f3412f.schedule(this);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3419m) {
                return;
            }
            this.f3419m = true;
            this.f3416j.dispose();
            this.f3412f.dispose();
            if (getAndIncrement() == 0) {
                this.f3415i.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3419m;
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return this.f3415i.isEmpty();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3418l) {
                return;
            }
            this.f3418l = true;
            m1524d();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3418l) {
                C1908a.m2205b(th);
                return;
            }
            this.f3417k = th;
            this.f3418l = true;
            m1524d();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3418l) {
                return;
            }
            if (this.f3420n != 2) {
                this.f3415i.offer(t6);
            }
            m1524d();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3416j, interfaceC2153b)) {
                this.f3416j = interfaceC2153b;
                if (interfaceC2153b instanceof InterfaceC0950c) {
                    InterfaceC0950c interfaceC0950c = (InterfaceC0950c) interfaceC2153b;
                    int iMo331c = interfaceC0950c.mo331c(7);
                    if (iMo331c == 1) {
                        this.f3420n = iMo331c;
                        this.f3415i = interfaceC0950c;
                        this.f3418l = true;
                        this.f3411e.onSubscribe(this);
                        m1524d();
                        return;
                    }
                    if (iMo331c == 2) {
                        this.f3420n = iMo331c;
                        this.f3415i = interfaceC0950c;
                        this.f3411e.onSubscribe(this);
                        return;
                    }
                }
                this.f3415i = new C1489c(this.f3414h);
                this.f3411e.onSubscribe(this);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            return this.f3415i.poll();
        }

        /* JADX WARN: Code restructure failed: missing block: B:36:0x0075, code lost:
        
            r3 = addAndGet(-r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x007a, code lost:
        
            if (r3 != 0) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
        
            return;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r7 = this;
                boolean r0 = r7.f3421o
                r1 = 1
                if (r0 == 0) goto L4f
                r0 = 1
            L6:
                boolean r2 = r7.f3419m
                if (r2 == 0) goto Lc
                goto L97
            Lc:
                boolean r2 = r7.f3418l
                java.lang.Throwable r3 = r7.f3417k
                boolean r4 = r7.f3413g
                if (r4 != 0) goto L28
                if (r2 == 0) goto L28
                if (r3 == 0) goto L28
                r7.f3419m = r1
                y3.s<? super T> r0 = r7.f3411e
                java.lang.Throwable r1 = r7.f3417k
                r0.onError(r1)
                y3.t$c r0 = r7.f3412f
                r0.dispose()
                goto L97
            L28:
                y3.s<? super T> r3 = r7.f3411e
                r4 = 0
                r3.onNext(r4)
                if (r2 == 0) goto L47
                r7.f3419m = r1
                java.lang.Throwable r0 = r7.f3417k
                if (r0 == 0) goto L3c
                y3.s<? super T> r1 = r7.f3411e
                r1.onError(r0)
                goto L41
            L3c:
                y3.s<? super T> r0 = r7.f3411e
                r0.onComplete()
            L41:
                y3.t$c r0 = r7.f3412f
                r0.dispose()
                goto L97
            L47:
                int r0 = -r0
                int r0 = r7.addAndGet(r0)
                if (r0 != 0) goto L6
                goto L97
            L4f:
                e4.h<T> r0 = r7.f3415i
                y3.s<? super T> r2 = r7.f3411e
                r3 = 1
            L54:
                boolean r4 = r7.f3418l
                boolean r5 = r0.isEmpty()
                boolean r4 = r7.m1523b(r4, r5, r2)
                if (r4 == 0) goto L61
                goto L97
            L61:
                boolean r4 = r7.f3418l
                java.lang.Object r5 = r0.poll()     // Catch: java.lang.Throwable -> L81
                if (r5 != 0) goto L6b
                r6 = 1
                goto L6c
            L6b:
                r6 = 0
            L6c:
                boolean r4 = r7.m1523b(r4, r6, r2)
                if (r4 == 0) goto L73
                goto L97
            L73:
                if (r6 == 0) goto L7d
                int r3 = -r3
                int r3 = r7.addAndGet(r3)
                if (r3 != 0) goto L54
                goto L97
            L7d:
                r2.onNext(r5)
                goto L61
            L81:
                r3 = move-exception
                p186x2.C2074b.m2470J(r3)
                r7.f3419m = r1
                z3.b r1 = r7.f3416j
                r1.dispose()
                r0.clear()
                r2.onError(r3)
                y3.t$c r0 = r7.f3412f
                r0.dispose()
            L97:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p088k4.C1319n2.a.run():void");
        }
    }

    public C1319n2(InterfaceC2125q<T> interfaceC2125q, AbstractC2128t abstractC2128t, boolean z6, int i7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3408f = abstractC2128t;
        this.f3409g = z6;
        this.f3410h = i7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        AbstractC2128t abstractC2128t = this.f3408f;
        if (abstractC2128t instanceof C1539n) {
            this.f2772e.subscribe(interfaceC2127s);
        } else {
            this.f2772e.subscribe(new a(interfaceC2127s, abstractC2128t.createWorker(), this.f3409g, this.f3410h));
        }
    }
}
