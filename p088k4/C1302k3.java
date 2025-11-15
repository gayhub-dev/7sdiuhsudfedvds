package p088k4;

import io.reactivex.internal.operators.observable.ObservableSequenceEqual;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p014b4.InterfaceC0444d;
import p022c4.C0513a;
import p032d4.C0871b;
import p104m4.C1489c;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSequenceEqual.java */
/* renamed from: k4.k3 */
/* loaded from: classes.dex */
public final class C1302k3<T> extends AbstractC2120l<Boolean> {

    /* renamed from: e */
    public final InterfaceC2125q<? extends T> f3284e;

    /* renamed from: f */
    public final InterfaceC2125q<? extends T> f3285f;

    /* renamed from: g */
    public final InterfaceC0444d<? super T, ? super T> f3286g;

    /* renamed from: h */
    public final int f3287h;

    /* compiled from: ObservableSequenceEqual.java */
    /* renamed from: k4.k3$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2153b {
        private static final long serialVersionUID = -6178010334400373240L;

        /* renamed from: e */
        public final InterfaceC2127s<? super Boolean> f3288e;

        /* renamed from: f */
        public final InterfaceC0444d<? super T, ? super T> f3289f;

        /* renamed from: g */
        public final C0513a f3290g = new C0513a(2);

        /* renamed from: h */
        public final InterfaceC2125q<? extends T> f3291h;

        /* renamed from: i */
        public final InterfaceC2125q<? extends T> f3292i;

        /* renamed from: j */
        public final ObservableSequenceEqual.EqualObserver<T>[] f3293j;

        /* renamed from: k */
        public volatile boolean f3294k;

        /* renamed from: l */
        public T f3295l;

        /* renamed from: m */
        public T f3296m;

        public a(InterfaceC2127s<? super Boolean> interfaceC2127s, int i7, InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC0444d<? super T, ? super T> interfaceC0444d) {
            this.f3288e = interfaceC2127s;
            this.f3291h = interfaceC2125q;
            this.f3292i = interfaceC2125q2;
            this.f3289f = interfaceC0444d;
            this.f3293j = new b[]{new b(this, 0, i7), new b(this, 1, i7)};
        }

        /* renamed from: a */
        public void m1513a(C1489c<T> c1489c, C1489c<T> c1489c2) {
            this.f3294k = true;
            c1489c.clear();
            c1489c2.clear();
        }

        /* renamed from: b */
        public void m1514b() {
            Throwable th;
            Throwable th2;
            if (getAndIncrement() != 0) {
                return;
            }
            b[] bVarArr = this.f3293j;
            b bVar = bVarArr[0];
            C1489c<T> c1489c = bVar.f3298f;
            b bVar2 = bVarArr[1];
            C1489c<T> c1489c2 = bVar2.f3298f;
            int iAddAndGet = 1;
            while (!this.f3294k) {
                boolean z6 = bVar.f3300h;
                if (z6 && (th2 = bVar.f3301i) != null) {
                    m1513a(c1489c, c1489c2);
                    this.f3288e.onError(th2);
                    return;
                }
                boolean z7 = bVar2.f3300h;
                if (z7 && (th = bVar2.f3301i) != null) {
                    m1513a(c1489c, c1489c2);
                    this.f3288e.onError(th);
                    return;
                }
                if (this.f3295l == null) {
                    this.f3295l = c1489c.poll();
                }
                boolean z8 = this.f3295l == null;
                if (this.f3296m == null) {
                    this.f3296m = c1489c2.poll();
                }
                T t6 = this.f3296m;
                boolean z9 = t6 == null;
                if (z6 && z7 && z8 && z9) {
                    this.f3288e.onNext(Boolean.TRUE);
                    this.f3288e.onComplete();
                    return;
                }
                if (z6 && z7 && z8 != z9) {
                    m1513a(c1489c, c1489c2);
                    this.f3288e.onNext(Boolean.FALSE);
                    this.f3288e.onComplete();
                    return;
                }
                if (!z8 && !z9) {
                    try {
                        InterfaceC0444d<? super T, ? super T> interfaceC0444d = this.f3289f;
                        T t7 = this.f3295l;
                        Objects.requireNonNull((C0871b.a) interfaceC0444d);
                        if (!C0871b.m676a(t7, t6)) {
                            m1513a(c1489c, c1489c2);
                            this.f3288e.onNext(Boolean.FALSE);
                            this.f3288e.onComplete();
                            return;
                        }
                        this.f3295l = null;
                        this.f3296m = null;
                    } catch (Throwable th3) {
                        C2074b.m2470J(th3);
                        m1513a(c1489c, c1489c2);
                        this.f3288e.onError(th3);
                        return;
                    }
                }
                if (z8 || z9) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
            c1489c.clear();
            c1489c2.clear();
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3294k) {
                return;
            }
            this.f3294k = true;
            this.f3290g.dispose();
            if (getAndIncrement() == 0) {
                b[] bVarArr = this.f3293j;
                bVarArr[0].f3298f.clear();
                bVarArr[1].f3298f.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3294k;
        }
    }

    /* compiled from: ObservableSequenceEqual.java */
    /* renamed from: k4.k3$b */
    public static final class b<T> implements InterfaceC2127s<T> {

        /* renamed from: e */
        public final a<T> f3297e;

        /* renamed from: f */
        public final C1489c<T> f3298f;

        /* renamed from: g */
        public final int f3299g;

        /* renamed from: h */
        public volatile boolean f3300h;

        /* renamed from: i */
        public Throwable f3301i;

        public b(a<T> aVar, int i7, int i8) {
            this.f3297e = aVar;
            this.f3299g = i7;
            this.f3298f = new C1489c<>(i8);
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3300h = true;
            this.f3297e.m1514b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3301i = th;
            this.f3300h = true;
            this.f3297e.m1514b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3298f.offer(t6);
            this.f3297e.m1514b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            a<T> aVar = this.f3297e;
            aVar.f3290g.m321a(this.f3299g, interfaceC2153b);
        }
    }

    public C1302k3(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC0444d<? super T, ? super T> interfaceC0444d, int i7) {
        this.f3284e = interfaceC2125q;
        this.f3285f = interfaceC2125q2;
        this.f3286g = interfaceC0444d;
        this.f3287h = i7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Boolean> interfaceC2127s) {
        a aVar = new a(interfaceC2127s, this.f3287h, this.f3284e, this.f3285f, this.f3286g);
        interfaceC2127s.onSubscribe(aVar);
        InterfaceC2127s<? super Object>[] interfaceC2127sArr = aVar.f3293j;
        aVar.f3291h.subscribe(interfaceC2127sArr[0]);
        aVar.f3292i.subscribe(interfaceC2127sArr[1]);
    }
}
