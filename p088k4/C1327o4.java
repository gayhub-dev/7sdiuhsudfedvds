package p088k4;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import p022c4.EnumC0515c;
import p181w4.C2033e;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableWindow.java */
/* renamed from: k4.o4 */
/* loaded from: classes.dex */
public final class C1327o4<T> extends AbstractC1238a<T, AbstractC2120l<T>> {

    /* renamed from: f */
    public final long f3499f;

    /* renamed from: g */
    public final long f3500g;

    /* renamed from: h */
    public final int f3501h;

    /* compiled from: ObservableWindow.java */
    /* renamed from: k4.o4$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b, Runnable {
        private static final long serialVersionUID = -7481782523886138128L;

        /* renamed from: e */
        public final InterfaceC2127s<? super AbstractC2120l<T>> f3502e;

        /* renamed from: f */
        public final long f3503f;

        /* renamed from: g */
        public final int f3504g;

        /* renamed from: h */
        public long f3505h;

        /* renamed from: i */
        public InterfaceC2153b f3506i;

        /* renamed from: j */
        public C2033e<T> f3507j;

        /* renamed from: k */
        public volatile boolean f3508k;

        public a(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s, long j7, int i7) {
            this.f3502e = interfaceC2127s;
            this.f3503f = j7;
            this.f3504g = i7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3508k = true;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3508k;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            C2033e<T> c2033e = this.f3507j;
            if (c2033e != null) {
                this.f3507j = null;
                c2033e.onComplete();
            }
            this.f3502e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            C2033e<T> c2033e = this.f3507j;
            if (c2033e != null) {
                this.f3507j = null;
                c2033e.onError(th);
            }
            this.f3502e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            C2033e<T> c2033eM2388c = this.f3507j;
            if (c2033eM2388c == null && !this.f3508k) {
                c2033eM2388c = C2033e.m2388c(this.f3504g, this);
                this.f3507j = c2033eM2388c;
                this.f3502e.onNext(c2033eM2388c);
            }
            if (c2033eM2388c != null) {
                c2033eM2388c.onNext(t6);
                long j7 = this.f3505h + 1;
                this.f3505h = j7;
                if (j7 >= this.f3503f) {
                    this.f3505h = 0L;
                    this.f3507j = null;
                    c2033eM2388c.onComplete();
                    if (this.f3508k) {
                        this.f3506i.dispose();
                    }
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3506i, interfaceC2153b)) {
                this.f3506i = interfaceC2153b;
                this.f3502e.onSubscribe(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3508k) {
                this.f3506i.dispose();
            }
        }
    }

    /* compiled from: ObservableWindow.java */
    /* renamed from: k4.o4$b */
    public static final class b<T> extends AtomicBoolean implements InterfaceC2127s<T>, InterfaceC2153b, Runnable {
        private static final long serialVersionUID = 3366976432059579510L;

        /* renamed from: e */
        public final InterfaceC2127s<? super AbstractC2120l<T>> f3509e;

        /* renamed from: f */
        public final long f3510f;

        /* renamed from: g */
        public final long f3511g;

        /* renamed from: h */
        public final int f3512h;

        /* renamed from: j */
        public long f3514j;

        /* renamed from: k */
        public volatile boolean f3515k;

        /* renamed from: l */
        public long f3516l;

        /* renamed from: m */
        public InterfaceC2153b f3517m;

        /* renamed from: n */
        public final AtomicInteger f3518n = new AtomicInteger();

        /* renamed from: i */
        public final ArrayDeque<C2033e<T>> f3513i = new ArrayDeque<>();

        public b(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s, long j7, long j8, int i7) {
            this.f3509e = interfaceC2127s;
            this.f3510f = j7;
            this.f3511g = j8;
            this.f3512h = i7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3515k = true;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3515k;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            ArrayDeque<C2033e<T>> arrayDeque = this.f3513i;
            while (!arrayDeque.isEmpty()) {
                arrayDeque.poll().onComplete();
            }
            this.f3509e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            ArrayDeque<C2033e<T>> arrayDeque = this.f3513i;
            while (!arrayDeque.isEmpty()) {
                arrayDeque.poll().onError(th);
            }
            this.f3509e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            ArrayDeque<C2033e<T>> arrayDeque = this.f3513i;
            long j7 = this.f3514j;
            long j8 = this.f3511g;
            if (j7 % j8 == 0 && !this.f3515k) {
                this.f3518n.getAndIncrement();
                C2033e<T> c2033eM2388c = C2033e.m2388c(this.f3512h, this);
                arrayDeque.offer(c2033eM2388c);
                this.f3509e.onNext(c2033eM2388c);
            }
            long j9 = this.f3516l + 1;
            Iterator<C2033e<T>> it = arrayDeque.iterator();
            while (it.hasNext()) {
                it.next().onNext(t6);
            }
            if (j9 >= this.f3510f) {
                arrayDeque.poll().onComplete();
                if (arrayDeque.isEmpty() && this.f3515k) {
                    this.f3517m.dispose();
                    return;
                }
                this.f3516l = j9 - j8;
            } else {
                this.f3516l = j9;
            }
            this.f3514j = j7 + 1;
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3517m, interfaceC2153b)) {
                this.f3517m = interfaceC2153b;
                this.f3509e.onSubscribe(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3518n.decrementAndGet() == 0 && this.f3515k) {
                this.f3517m.dispose();
            }
        }
    }

    public C1327o4(InterfaceC2125q<T> interfaceC2125q, long j7, long j8, int i7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3499f = j7;
        this.f3500g = j8;
        this.f3501h = i7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super AbstractC2120l<T>> interfaceC2127s) {
        if (this.f3499f == this.f3500g) {
            this.f2772e.subscribe(new a(interfaceC2127s, this.f3499f, this.f3501h));
        } else {
            this.f2772e.subscribe(new b(interfaceC2127s, this.f3499f, this.f3500g, this.f3501h));
        }
    }
}
