package p088k4;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableBuffer.java */
/* renamed from: k4.k */
/* loaded from: classes.dex */
public final class C1298k<T, U extends Collection<? super T>> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final int f3253f;

    /* renamed from: g */
    public final int f3254g;

    /* renamed from: h */
    public final Callable<U> f3255h;

    /* compiled from: ObservableBuffer.java */
    /* renamed from: k4.k$a */
    public static final class a<T, U extends Collection<? super T>> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super U> f3256e;

        /* renamed from: f */
        public final int f3257f;

        /* renamed from: g */
        public final Callable<U> f3258g;

        /* renamed from: h */
        public U f3259h;

        /* renamed from: i */
        public int f3260i;

        /* renamed from: j */
        public InterfaceC2153b f3261j;

        public a(InterfaceC2127s<? super U> interfaceC2127s, int i7, Callable<U> callable) {
            this.f3256e = interfaceC2127s;
            this.f3257f = i7;
            this.f3258g = callable;
        }

        /* renamed from: a */
        public boolean m1510a() {
            try {
                U uCall = this.f3258g.call();
                Objects.requireNonNull(uCall, "Empty buffer supplied");
                this.f3259h = uCall;
                return true;
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3259h = null;
                InterfaceC2153b interfaceC2153b = this.f3261j;
                if (interfaceC2153b == null) {
                    EnumC0516d.m330b(th, this.f3256e);
                    return false;
                }
                interfaceC2153b.dispose();
                this.f3256e.onError(th);
                return false;
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3261j.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3261j.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            U u6 = this.f3259h;
            if (u6 != null) {
                this.f3259h = null;
                if (!u6.isEmpty()) {
                    this.f3256e.onNext(u6);
                }
                this.f3256e.onComplete();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3259h = null;
            this.f3256e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            U u6 = this.f3259h;
            if (u6 != null) {
                u6.add(t6);
                int i7 = this.f3260i + 1;
                this.f3260i = i7;
                if (i7 >= this.f3257f) {
                    this.f3256e.onNext(u6);
                    this.f3260i = 0;
                    m1510a();
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3261j, interfaceC2153b)) {
                this.f3261j = interfaceC2153b;
                this.f3256e.onSubscribe(this);
            }
        }
    }

    /* compiled from: ObservableBuffer.java */
    /* renamed from: k4.k$b */
    public static final class b<T, U extends Collection<? super T>> extends AtomicBoolean implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -8223395059921494546L;

        /* renamed from: e */
        public final InterfaceC2127s<? super U> f3262e;

        /* renamed from: f */
        public final int f3263f;

        /* renamed from: g */
        public final int f3264g;

        /* renamed from: h */
        public final Callable<U> f3265h;

        /* renamed from: i */
        public InterfaceC2153b f3266i;

        /* renamed from: j */
        public final ArrayDeque<U> f3267j = new ArrayDeque<>();

        /* renamed from: k */
        public long f3268k;

        public b(InterfaceC2127s<? super U> interfaceC2127s, int i7, int i8, Callable<U> callable) {
            this.f3262e = interfaceC2127s;
            this.f3263f = i7;
            this.f3264g = i8;
            this.f3265h = callable;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3266i.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3266i.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            while (!this.f3267j.isEmpty()) {
                this.f3262e.onNext(this.f3267j.poll());
            }
            this.f3262e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3267j.clear();
            this.f3262e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            long j7 = this.f3268k;
            this.f3268k = 1 + j7;
            if (j7 % this.f3264g == 0) {
                try {
                    U uCall = this.f3265h.call();
                    Objects.requireNonNull(uCall, "The bufferSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.");
                    this.f3267j.offer(uCall);
                } catch (Throwable th) {
                    this.f3267j.clear();
                    this.f3266i.dispose();
                    this.f3262e.onError(th);
                    return;
                }
            }
            Iterator<U> it = this.f3267j.iterator();
            while (it.hasNext()) {
                U next = it.next();
                next.add(t6);
                if (this.f3263f <= next.size()) {
                    it.remove();
                    this.f3262e.onNext(next);
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3266i, interfaceC2153b)) {
                this.f3266i = interfaceC2153b;
                this.f3262e.onSubscribe(this);
            }
        }
    }

    public C1298k(InterfaceC2125q<T> interfaceC2125q, int i7, int i8, Callable<U> callable) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3253f = i7;
        this.f3254g = i8;
        this.f3255h = callable;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        int i7 = this.f3254g;
        int i8 = this.f3253f;
        if (i7 != i8) {
            this.f2772e.subscribe(new b(interfaceC2127s, this.f3253f, this.f3254g, this.f3255h));
            return;
        }
        a aVar = new a(interfaceC2127s, i8, this.f3255h);
        if (aVar.m1510a()) {
            this.f2772e.subscribe(aVar);
        }
    }
}
