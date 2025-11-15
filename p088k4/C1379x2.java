package p088k4;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0446f;
import p022c4.EnumC0515c;
import p022c4.InterfaceC0517e;
import p146r4.AbstractC1837a;
import p160t4.C1908a;
import p174v4.C2012a;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableRefCount.java */
/* renamed from: k4.x2 */
/* loaded from: classes.dex */
public final class C1379x2<T> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final AbstractC1837a<T> f3988e;

    /* renamed from: f */
    public final int f3989f;

    /* renamed from: g */
    public a f3990g;

    /* compiled from: ObservableRefCount.java */
    /* renamed from: k4.x2$a */
    public static final class a extends AtomicReference<InterfaceC2153b> implements Runnable, InterfaceC0446f<InterfaceC2153b> {
        private static final long serialVersionUID = -4552101107598366241L;

        /* renamed from: e */
        public final C1379x2<?> f3991e;

        /* renamed from: f */
        public long f3992f;

        /* renamed from: g */
        public boolean f3993g;

        /* renamed from: h */
        public boolean f3994h;

        public a(C1379x2<?> c1379x2) {
            this.f3991e = c1379x2;
        }

        @Override // p014b4.InterfaceC0446f
        public void accept(InterfaceC2153b interfaceC2153b) {
            InterfaceC2153b interfaceC2153b2 = interfaceC2153b;
            EnumC0515c.m325c(this, interfaceC2153b2);
            synchronized (this.f3991e) {
                if (this.f3994h) {
                    ((InterfaceC0517e) this.f3991e.f3988e).mo332a(interfaceC2153b2);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f3991e.m1560c(this);
        }
    }

    /* compiled from: ObservableRefCount.java */
    /* renamed from: k4.x2$b */
    public static final class b<T> extends AtomicBoolean implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -7419642935409022375L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3995e;

        /* renamed from: f */
        public final C1379x2<T> f3996f;

        /* renamed from: g */
        public final a f3997g;

        /* renamed from: h */
        public InterfaceC2153b f3998h;

        public b(InterfaceC2127s<? super T> interfaceC2127s, C1379x2<T> c1379x2, a aVar) {
            this.f3995e = interfaceC2127s;
            this.f3996f = c1379x2;
            this.f3997g = aVar;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3998h.dispose();
            if (compareAndSet(false, true)) {
                C1379x2<T> c1379x2 = this.f3996f;
                a aVar = this.f3997g;
                synchronized (c1379x2) {
                    a aVar2 = c1379x2.f3990g;
                    if (aVar2 != null && aVar2 == aVar) {
                        long j7 = aVar.f3992f - 1;
                        aVar.f3992f = j7;
                        if (j7 == 0 && aVar.f3993g) {
                            c1379x2.m1560c(aVar);
                        }
                    }
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3998h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.f3996f.m1559b(this.f3997g);
                this.f3995e.onComplete();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!compareAndSet(false, true)) {
                C1908a.m2205b(th);
            } else {
                this.f3996f.m1559b(this.f3997g);
                this.f3995e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3995e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3998h, interfaceC2153b)) {
                this.f3998h = interfaceC2153b;
                this.f3995e.onSubscribe(this);
            }
        }
    }

    public C1379x2(AbstractC1837a<T> abstractC1837a) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        AbstractC2128t abstractC2128t = C2012a.f5855c;
        this.f3988e = abstractC1837a;
        this.f3989f = 1;
    }

    /* renamed from: b */
    public void m1559b(a aVar) {
        synchronized (this) {
            a aVar2 = this.f3990g;
            if (aVar2 != null && aVar2 == aVar) {
                this.f3990g = null;
                Objects.requireNonNull(aVar);
            }
            long j7 = aVar.f3992f - 1;
            aVar.f3992f = j7;
            if (j7 == 0) {
                AbstractC1837a<T> abstractC1837a = this.f3988e;
                if (abstractC1837a instanceof InterfaceC2153b) {
                    ((InterfaceC2153b) abstractC1837a).dispose();
                } else if (abstractC1837a instanceof InterfaceC0517e) {
                    ((InterfaceC0517e) abstractC1837a).mo332a(aVar.get());
                }
            }
        }
    }

    /* renamed from: c */
    public void m1560c(a aVar) {
        synchronized (this) {
            if (aVar.f3992f == 0 && aVar == this.f3990g) {
                this.f3990g = null;
                InterfaceC2153b interfaceC2153b = aVar.get();
                EnumC0515c.m323a(aVar);
                AbstractC1837a<T> abstractC1837a = this.f3988e;
                if (abstractC1837a instanceof InterfaceC2153b) {
                    ((InterfaceC2153b) abstractC1837a).dispose();
                } else if (abstractC1837a instanceof InterfaceC0517e) {
                    if (interfaceC2153b == null) {
                        aVar.f3994h = true;
                    } else {
                        ((InterfaceC0517e) abstractC1837a).mo332a(interfaceC2153b);
                    }
                }
            }
        }
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        a aVar;
        boolean z6;
        synchronized (this) {
            aVar = this.f3990g;
            if (aVar == null) {
                aVar = new a(this);
                this.f3990g = aVar;
            }
            long j7 = aVar.f3992f;
            int i7 = (j7 > 0L ? 1 : (j7 == 0L ? 0 : -1));
            long j8 = j7 + 1;
            aVar.f3992f = j8;
            z6 = true;
            if (aVar.f3993g || j8 != this.f3989f) {
                z6 = false;
            } else {
                aVar.f3993g = true;
            }
        }
        this.f3988e.subscribe(new b(interfaceC2127s, this, aVar));
        if (z6) {
            this.f3988e.mo1475b(aVar);
        }
    }
}
