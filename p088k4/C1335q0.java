package p088k4;

import java.util.NoSuchElementException;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0949b;
import p160t4.C1908a;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableElementAtSingle.java */
/* renamed from: k4.q0 */
/* loaded from: classes.dex */
public final class C1335q0<T> extends AbstractC2129u<T> implements InterfaceC0949b<T> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3576a;

    /* renamed from: b */
    public final long f3577b;

    /* renamed from: c */
    public final T f3578c;

    /* compiled from: ObservableElementAtSingle.java */
    /* renamed from: k4.q0$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2130v<? super T> f3579e;

        /* renamed from: f */
        public final long f3580f;

        /* renamed from: g */
        public final T f3581g;

        /* renamed from: h */
        public InterfaceC2153b f3582h;

        /* renamed from: i */
        public long f3583i;

        /* renamed from: j */
        public boolean f3584j;

        public a(InterfaceC2130v<? super T> interfaceC2130v, long j7, T t6) {
            this.f3579e = interfaceC2130v;
            this.f3580f = j7;
            this.f3581g = t6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3582h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3582h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3584j) {
                return;
            }
            this.f3584j = true;
            T t6 = this.f3581g;
            if (t6 != null) {
                this.f3579e.mo1016a(t6);
            } else {
                this.f3579e.onError(new NoSuchElementException());
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3584j) {
                C1908a.m2205b(th);
            } else {
                this.f3584j = true;
                this.f3579e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3584j) {
                return;
            }
            long j7 = this.f3583i;
            if (j7 != this.f3580f) {
                this.f3583i = j7 + 1;
                return;
            }
            this.f3584j = true;
            this.f3582h.dispose();
            this.f3579e.mo1016a(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3582h, interfaceC2153b)) {
                this.f3582h = interfaceC2153b;
                this.f3579e.onSubscribe(this);
            }
        }
    }

    public C1335q0(InterfaceC2125q<T> interfaceC2125q, long j7, T t6) {
        this.f3576a = interfaceC2125q;
        this.f3577b = j7;
        this.f3578c = t6;
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<T> mo860a() {
        return new C1323o0(this.f3576a, this.f3577b, this.f3578c, true);
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super T> interfaceC2130v) {
        this.f3576a.subscribe(new a(interfaceC2130v, this.f3577b, this.f3578c));
    }
}
