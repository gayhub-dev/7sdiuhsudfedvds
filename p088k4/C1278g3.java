package p088k4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p153s4.C1882e;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSampleWithObservable.java */
/* renamed from: k4.g3 */
/* loaded from: classes.dex */
public final class C1278g3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2125q<?> f3122f;

    /* renamed from: g */
    public final boolean f3123g;

    /* compiled from: ObservableSampleWithObservable.java */
    /* renamed from: k4.g3$a */
    public static final class a<T> extends c<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        /* renamed from: i */
        public final AtomicInteger f3124i;

        /* renamed from: j */
        public volatile boolean f3125j;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC2125q<?> interfaceC2125q) {
            super(interfaceC2127s, interfaceC2125q);
            this.f3124i = new AtomicInteger();
        }

        @Override // p088k4.C1278g3.c
        /* renamed from: a */
        public void mo1502a() {
            this.f3125j = true;
            if (this.f3124i.getAndIncrement() == 0) {
                m1505c();
                this.f3126e.onComplete();
            }
        }

        @Override // p088k4.C1278g3.c
        /* renamed from: b */
        public void mo1503b() {
            this.f3125j = true;
            if (this.f3124i.getAndIncrement() == 0) {
                m1505c();
                this.f3126e.onComplete();
            }
        }

        @Override // p088k4.C1278g3.c
        /* renamed from: d */
        public void mo1504d() {
            if (this.f3124i.getAndIncrement() == 0) {
                do {
                    boolean z6 = this.f3125j;
                    m1505c();
                    if (z6) {
                        this.f3126e.onComplete();
                        return;
                    }
                } while (this.f3124i.decrementAndGet() != 0);
            }
        }
    }

    /* compiled from: ObservableSampleWithObservable.java */
    /* renamed from: k4.g3$b */
    public static final class b<T> extends c<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        public b(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC2125q<?> interfaceC2125q) {
            super(interfaceC2127s, interfaceC2125q);
        }

        @Override // p088k4.C1278g3.c
        /* renamed from: a */
        public void mo1502a() {
            this.f3126e.onComplete();
        }

        @Override // p088k4.C1278g3.c
        /* renamed from: b */
        public void mo1503b() {
            this.f3126e.onComplete();
        }

        @Override // p088k4.C1278g3.c
        /* renamed from: d */
        public void mo1504d() {
            m1505c();
        }
    }

    /* compiled from: ObservableSampleWithObservable.java */
    /* renamed from: k4.g3$c */
    public static abstract class c<T> extends AtomicReference<T> implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -3517602651313910099L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3126e;

        /* renamed from: f */
        public final InterfaceC2125q<?> f3127f;

        /* renamed from: g */
        public final AtomicReference<InterfaceC2153b> f3128g = new AtomicReference<>();

        /* renamed from: h */
        public InterfaceC2153b f3129h;

        public c(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC2125q<?> interfaceC2125q) {
            this.f3126e = interfaceC2127s;
            this.f3127f = interfaceC2125q;
        }

        /* renamed from: a */
        public abstract void mo1502a();

        /* renamed from: b */
        public abstract void mo1503b();

        /* renamed from: c */
        public void m1505c() {
            T andSet = getAndSet(null);
            if (andSet != null) {
                this.f3126e.onNext(andSet);
            }
        }

        /* renamed from: d */
        public abstract void mo1504d();

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3128g);
            this.f3129h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3128g.get() == EnumC0515c.DISPOSED;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            EnumC0515c.m323a(this.f3128g);
            mo1502a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            EnumC0515c.m323a(this.f3128g);
            this.f3126e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            lazySet(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3129h, interfaceC2153b)) {
                this.f3129h = interfaceC2153b;
                this.f3126e.onSubscribe(this);
                if (this.f3128g.get() == null) {
                    this.f3127f.subscribe(new d(this));
                }
            }
        }
    }

    /* compiled from: ObservableSampleWithObservable.java */
    /* renamed from: k4.g3$d */
    public static final class d<T> implements InterfaceC2127s<Object> {

        /* renamed from: e */
        public final c<T> f3130e;

        public d(c<T> cVar) {
            this.f3130e = cVar;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            c<T> cVar = this.f3130e;
            cVar.f3129h.dispose();
            cVar.mo1503b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            c<T> cVar = this.f3130e;
            cVar.f3129h.dispose();
            cVar.f3126e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            this.f3130e.mo1504d();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3130e.f3128g, interfaceC2153b);
        }
    }

    public C1278g3(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<?> interfaceC2125q2, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3122f = interfaceC2125q2;
        this.f3123g = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C1882e c1882e = new C1882e(interfaceC2127s);
        if (this.f3123g) {
            this.f2772e.subscribe(new a(c1882e, this.f3122f));
        } else {
            this.f2772e.subscribe(new b(c1882e, this.f3122f));
        }
    }
}
