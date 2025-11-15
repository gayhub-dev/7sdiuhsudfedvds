package p088k4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0950c;
import p040e4.InterfaceC0955h;
import p104m4.C1489c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSwitchMap.java */
/* renamed from: k4.w3 */
/* loaded from: classes.dex */
public final class C1374w3<T, R> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> f3941f;

    /* renamed from: g */
    public final int f3942g;

    /* renamed from: h */
    public final boolean f3943h;

    /* compiled from: ObservableSwitchMap.java */
    /* renamed from: k4.w3$a */
    public static final class a<T, R> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<R> {
        private static final long serialVersionUID = 3837284832786408377L;

        /* renamed from: e */
        public final b<T, R> f3944e;

        /* renamed from: f */
        public final long f3945f;

        /* renamed from: g */
        public final int f3946g;

        /* renamed from: h */
        public volatile InterfaceC0955h<R> f3947h;

        /* renamed from: i */
        public volatile boolean f3948i;

        public a(b<T, R> bVar, long j7, int i7) {
            this.f3944e = bVar;
            this.f3945f = j7;
            this.f3946g = i7;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3945f == this.f3944e.f3959n) {
                this.f3948i = true;
                this.f3944e.m1556b();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            b<T, R> bVar = this.f3944e;
            Objects.requireNonNull(bVar);
            if (this.f3945f != bVar.f3959n || !C1774f.m1958a(bVar.f3954i, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (!bVar.f3953h) {
                bVar.f3957l.dispose();
            }
            this.f3948i = true;
            bVar.m1556b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(R r6) {
            if (this.f3945f == this.f3944e.f3959n) {
                if (r6 != null) {
                    this.f3947h.offer(r6);
                }
                this.f3944e.m1556b();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m327f(this, interfaceC2153b)) {
                if (interfaceC2153b instanceof InterfaceC0950c) {
                    InterfaceC0950c interfaceC0950c = (InterfaceC0950c) interfaceC2153b;
                    int iMo331c = interfaceC0950c.mo331c(7);
                    if (iMo331c == 1) {
                        this.f3947h = interfaceC0950c;
                        this.f3948i = true;
                        this.f3944e.m1556b();
                        return;
                    } else if (iMo331c == 2) {
                        this.f3947h = interfaceC0950c;
                        return;
                    }
                }
                this.f3947h = new C1489c(this.f3946g);
            }
        }
    }

    /* compiled from: ObservableSwitchMap.java */
    /* renamed from: k4.w3$b */
    public static final class b<T, R> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: o */
        public static final a<Object, Object> f3949o;
        private static final long serialVersionUID = -3491074160481096299L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3950e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> f3951f;

        /* renamed from: g */
        public final int f3952g;

        /* renamed from: h */
        public final boolean f3953h;

        /* renamed from: j */
        public volatile boolean f3955j;

        /* renamed from: k */
        public volatile boolean f3956k;

        /* renamed from: l */
        public InterfaceC2153b f3957l;

        /* renamed from: n */
        public volatile long f3959n;

        /* renamed from: m */
        public final AtomicReference<a<T, R>> f3958m = new AtomicReference<>();

        /* renamed from: i */
        public final C1771c f3954i = new C1771c();

        static {
            a<Object, Object> aVar = new a<>(null, -1L, 1);
            f3949o = aVar;
            EnumC0515c.m323a(aVar);
        }

        public b(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7, boolean z6) {
            this.f3950e = interfaceC2127s;
            this.f3951f = interfaceC0454n;
            this.f3952g = i7;
            this.f3953h = z6;
        }

        /* renamed from: a */
        public void m1555a() {
            a<Object, Object> aVar;
            a<T, R> aVar2 = this.f3958m.get();
            a<Object, Object> aVar3 = f3949o;
            if (aVar2 == aVar3 || (aVar = (a) this.f3958m.getAndSet(aVar3)) == aVar3 || aVar == null) {
                return;
            }
            EnumC0515c.m323a(aVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:101:0x000f A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:95:0x00e9 A[SYNTHETIC] */
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m1556b() {
            /*
                Method dump skipped, instructions count: 241
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p088k4.C1374w3.b.m1556b():void");
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3956k) {
                return;
            }
            this.f3956k = true;
            this.f3957l.dispose();
            m1555a();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3956k;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3955j) {
                return;
            }
            this.f3955j = true;
            m1556b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3955j || !C1774f.m1958a(this.f3954i, th)) {
                C1908a.m2205b(th);
                return;
            }
            if (!this.f3953h) {
                m1555a();
            }
            this.f3955j = true;
            m1556b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            a<T, R> aVar;
            long j7 = this.f3959n + 1;
            this.f3959n = j7;
            a<T, R> aVar2 = this.f3958m.get();
            if (aVar2 != null) {
                EnumC0515c.m323a(aVar2);
            }
            try {
                InterfaceC2125q<? extends R> interfaceC2125qApply = this.f3951f.apply(t6);
                Objects.requireNonNull(interfaceC2125qApply, "The ObservableSource returned is null");
                InterfaceC2125q<? extends R> interfaceC2125q = interfaceC2125qApply;
                a<T, R> aVar3 = new a<>(this, j7, this.f3952g);
                do {
                    aVar = this.f3958m.get();
                    if (aVar == f3949o) {
                        return;
                    }
                } while (!this.f3958m.compareAndSet(aVar, aVar3));
                interfaceC2125q.subscribe(aVar3);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3957l.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3957l, interfaceC2153b)) {
                this.f3957l = interfaceC2153b;
                this.f3950e.onSubscribe(this);
            }
        }
    }

    public C1374w3(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3941f = interfaceC0454n;
        this.f3942g = i7;
        this.f3943h = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        if (C1284h3.m1507a(this.f2772e, interfaceC2127s, this.f3941f)) {
            return;
        }
        this.f2772e.subscribe(new b(interfaceC2127s, this.f3941f, this.f3942g, this.f3943h));
    }
}
