package p088k4;

import io.reactivex.internal.operators.observable.ObservableSequenceEqualSingle;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p014b4.InterfaceC0444d;
import p022c4.C0513a;
import p032d4.C0871b;
import p040e4.InterfaceC0949b;
import p104m4.C1489c;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSequenceEqualSingle.java */
/* renamed from: k4.l3 */
/* loaded from: classes.dex */
public final class C1308l3<T> extends AbstractC2129u<Boolean> implements InterfaceC0949b<Boolean> {

    /* renamed from: a */
    public final InterfaceC2125q<? extends T> f3346a;

    /* renamed from: b */
    public final InterfaceC2125q<? extends T> f3347b;

    /* renamed from: c */
    public final InterfaceC0444d<? super T, ? super T> f3348c;

    /* renamed from: d */
    public final int f3349d;

    /* compiled from: ObservableSequenceEqualSingle.java */
    /* renamed from: k4.l3$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2153b {
        private static final long serialVersionUID = -6178010334400373240L;

        /* renamed from: e */
        public final InterfaceC2130v<? super Boolean> f3350e;

        /* renamed from: f */
        public final InterfaceC0444d<? super T, ? super T> f3351f;

        /* renamed from: g */
        public final C0513a f3352g = new C0513a(2);

        /* renamed from: h */
        public final InterfaceC2125q<? extends T> f3353h;

        /* renamed from: i */
        public final InterfaceC2125q<? extends T> f3354i;

        /* renamed from: j */
        public final ObservableSequenceEqualSingle.EqualObserver<T>[] f3355j;

        /* renamed from: k */
        public volatile boolean f3356k;

        /* renamed from: l */
        public T f3357l;

        /* renamed from: m */
        public T f3358m;

        public a(InterfaceC2130v<? super Boolean> interfaceC2130v, int i7, InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC0444d<? super T, ? super T> interfaceC0444d) {
            this.f3350e = interfaceC2130v;
            this.f3353h = interfaceC2125q;
            this.f3354i = interfaceC2125q2;
            this.f3351f = interfaceC0444d;
            this.f3355j = new b[]{new b(this, 0, i7), new b(this, 1, i7)};
        }

        /* renamed from: a */
        public void m1520a(C1489c<T> c1489c, C1489c<T> c1489c2) {
            this.f3356k = true;
            c1489c.clear();
            c1489c2.clear();
        }

        /* renamed from: b */
        public void m1521b() {
            Throwable th;
            Throwable th2;
            if (getAndIncrement() != 0) {
                return;
            }
            b[] bVarArr = this.f3355j;
            b bVar = bVarArr[0];
            C1489c<T> c1489c = bVar.f3360f;
            b bVar2 = bVarArr[1];
            C1489c<T> c1489c2 = bVar2.f3360f;
            int iAddAndGet = 1;
            while (!this.f3356k) {
                boolean z6 = bVar.f3362h;
                if (z6 && (th2 = bVar.f3363i) != null) {
                    m1520a(c1489c, c1489c2);
                    this.f3350e.onError(th2);
                    return;
                }
                boolean z7 = bVar2.f3362h;
                if (z7 && (th = bVar2.f3363i) != null) {
                    m1520a(c1489c, c1489c2);
                    this.f3350e.onError(th);
                    return;
                }
                if (this.f3357l == null) {
                    this.f3357l = c1489c.poll();
                }
                boolean z8 = this.f3357l == null;
                if (this.f3358m == null) {
                    this.f3358m = c1489c2.poll();
                }
                T t6 = this.f3358m;
                boolean z9 = t6 == null;
                if (z6 && z7 && z8 && z9) {
                    this.f3350e.mo1016a(Boolean.TRUE);
                    return;
                }
                if (z6 && z7 && z8 != z9) {
                    m1520a(c1489c, c1489c2);
                    this.f3350e.mo1016a(Boolean.FALSE);
                    return;
                }
                if (!z8 && !z9) {
                    try {
                        InterfaceC0444d<? super T, ? super T> interfaceC0444d = this.f3351f;
                        T t7 = this.f3357l;
                        Objects.requireNonNull((C0871b.a) interfaceC0444d);
                        if (!C0871b.m676a(t7, t6)) {
                            m1520a(c1489c, c1489c2);
                            this.f3350e.mo1016a(Boolean.FALSE);
                            return;
                        } else {
                            this.f3357l = null;
                            this.f3358m = null;
                        }
                    } catch (Throwable th3) {
                        C2074b.m2470J(th3);
                        m1520a(c1489c, c1489c2);
                        this.f3350e.onError(th3);
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
            if (this.f3356k) {
                return;
            }
            this.f3356k = true;
            this.f3352g.dispose();
            if (getAndIncrement() == 0) {
                b[] bVarArr = this.f3355j;
                bVarArr[0].f3360f.clear();
                bVarArr[1].f3360f.clear();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3356k;
        }
    }

    /* compiled from: ObservableSequenceEqualSingle.java */
    /* renamed from: k4.l3$b */
    public static final class b<T> implements InterfaceC2127s<T> {

        /* renamed from: e */
        public final a<T> f3359e;

        /* renamed from: f */
        public final C1489c<T> f3360f;

        /* renamed from: g */
        public final int f3361g;

        /* renamed from: h */
        public volatile boolean f3362h;

        /* renamed from: i */
        public Throwable f3363i;

        public b(a<T> aVar, int i7, int i8) {
            this.f3359e = aVar;
            this.f3361g = i7;
            this.f3360f = new C1489c<>(i8);
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3362h = true;
            this.f3359e.m1521b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3363i = th;
            this.f3362h = true;
            this.f3359e.m1521b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3360f.offer(t6);
            this.f3359e.m1521b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            a<T> aVar = this.f3359e;
            aVar.f3352g.m321a(this.f3361g, interfaceC2153b);
        }
    }

    public C1308l3(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC0444d<? super T, ? super T> interfaceC0444d, int i7) {
        this.f3346a = interfaceC2125q;
        this.f3347b = interfaceC2125q2;
        this.f3348c = interfaceC0444d;
        this.f3349d = i7;
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<Boolean> mo860a() {
        return new C1302k3(this.f3346a, this.f3347b, this.f3348c, this.f3349d);
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super Boolean> interfaceC2130v) {
        a aVar = new a(interfaceC2130v, this.f3349d, this.f3346a, this.f3347b, this.f3348c);
        interfaceC2130v.onSubscribe(aVar);
        InterfaceC2127s<? super Object>[] interfaceC2127sArr = aVar.f3355j;
        aVar.f3353h.subscribe(interfaceC2127sArr[0]);
        aVar.f3354i.subscribe(interfaceC2127sArr[1]);
    }
}
