package p088k4;

import android.support.v7.widget.RecyclerView;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.C0518f;
import p022c4.EnumC0515c;
import p088k4.C1291i4;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTimeout.java */
/* renamed from: k4.h4 */
/* loaded from: classes.dex */
public final class C1285h4<T, U, V> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2125q<U> f3163f;

    /* renamed from: g */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<V>> f3164g;

    /* renamed from: h */
    public final InterfaceC2125q<? extends T> f3165h;

    /* compiled from: ObservableTimeout.java */
    /* renamed from: k4.h4$a */
    public static final class a extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<Object>, InterfaceC2153b {
        private static final long serialVersionUID = 8708641127342403073L;

        /* renamed from: e */
        public final d f3166e;

        /* renamed from: f */
        public final long f3167f;

        public a(long j7, d dVar) {
            this.f3167f = j7;
            this.f3166e = dVar;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            Object obj = get();
            EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
            if (obj != enumC0515c) {
                lazySet(enumC0515c);
                this.f3166e.mo1509b(this.f3167f);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            Object obj = get();
            EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
            if (obj == enumC0515c) {
                C1908a.m2205b(th);
            } else {
                lazySet(enumC0515c);
                this.f3166e.mo1508a(this.f3167f, th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            InterfaceC2153b interfaceC2153b = (InterfaceC2153b) get();
            EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
            if (interfaceC2153b != enumC0515c) {
                interfaceC2153b.dispose();
                lazySet(enumC0515c);
                this.f3166e.mo1509b(this.f3167f);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this, interfaceC2153b);
        }
    }

    /* compiled from: ObservableTimeout.java */
    /* renamed from: k4.h4$b */
    public static final class b<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b, d {
        private static final long serialVersionUID = -7508389464265974549L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3168e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<?>> f3169f;

        /* renamed from: g */
        public final C0518f f3170g = new C0518f();

        /* renamed from: h */
        public final AtomicLong f3171h = new AtomicLong();

        /* renamed from: i */
        public final AtomicReference<InterfaceC2153b> f3172i = new AtomicReference<>();

        /* renamed from: j */
        public InterfaceC2125q<? extends T> f3173j;

        public b(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2125q<?>> interfaceC0454n, InterfaceC2125q<? extends T> interfaceC2125q) {
            this.f3168e = interfaceC2127s;
            this.f3169f = interfaceC0454n;
            this.f3173j = interfaceC2125q;
        }

        @Override // p088k4.C1285h4.d
        /* renamed from: a */
        public void mo1508a(long j7, Throwable th) {
            if (!this.f3171h.compareAndSet(j7, RecyclerView.FOREVER_NS)) {
                C1908a.m2205b(th);
            } else {
                EnumC0515c.m323a(this);
                this.f3168e.onError(th);
            }
        }

        @Override // p088k4.C1291i4.d
        /* renamed from: b */
        public void mo1509b(long j7) {
            if (this.f3171h.compareAndSet(j7, RecyclerView.FOREVER_NS)) {
                EnumC0515c.m323a(this.f3172i);
                InterfaceC2125q<? extends T> interfaceC2125q = this.f3173j;
                this.f3173j = null;
                interfaceC2125q.subscribe(new C1291i4.a(this.f3168e, this));
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3172i);
            EnumC0515c.m323a(this);
            EnumC0515c.m323a(this.f3170g);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3171h.getAndSet(RecyclerView.FOREVER_NS) != RecyclerView.FOREVER_NS) {
                EnumC0515c.m323a(this.f3170g);
                this.f3168e.onComplete();
                EnumC0515c.m323a(this.f3170g);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3171h.getAndSet(RecyclerView.FOREVER_NS) == RecyclerView.FOREVER_NS) {
                C1908a.m2205b(th);
                return;
            }
            EnumC0515c.m323a(this.f3170g);
            this.f3168e.onError(th);
            EnumC0515c.m323a(this.f3170g);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            long j7 = this.f3171h.get();
            if (j7 != RecyclerView.FOREVER_NS) {
                long j8 = 1 + j7;
                if (this.f3171h.compareAndSet(j7, j8)) {
                    InterfaceC2153b interfaceC2153b = this.f3170g.get();
                    if (interfaceC2153b != null) {
                        interfaceC2153b.dispose();
                    }
                    this.f3168e.onNext(t6);
                    try {
                        InterfaceC2125q<?> interfaceC2125qApply = this.f3169f.apply(t6);
                        Objects.requireNonNull(interfaceC2125qApply, "The itemTimeoutIndicator returned a null ObservableSource.");
                        InterfaceC2125q<?> interfaceC2125q = interfaceC2125qApply;
                        a aVar = new a(j8, this);
                        if (EnumC0515c.m325c(this.f3170g, aVar)) {
                            interfaceC2125q.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        C2074b.m2470J(th);
                        this.f3172i.get().dispose();
                        this.f3171h.getAndSet(RecyclerView.FOREVER_NS);
                        this.f3168e.onError(th);
                    }
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3172i, interfaceC2153b);
        }
    }

    /* compiled from: ObservableTimeout.java */
    /* renamed from: k4.h4$c */
    public static final class c<T> extends AtomicLong implements InterfaceC2127s<T>, InterfaceC2153b, d {
        private static final long serialVersionUID = 3764492702657003550L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3174e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<?>> f3175f;

        /* renamed from: g */
        public final C0518f f3176g = new C0518f();

        /* renamed from: h */
        public final AtomicReference<InterfaceC2153b> f3177h = new AtomicReference<>();

        public c(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2125q<?>> interfaceC0454n) {
            this.f3174e = interfaceC2127s;
            this.f3175f = interfaceC0454n;
        }

        @Override // p088k4.C1285h4.d
        /* renamed from: a */
        public void mo1508a(long j7, Throwable th) {
            if (!compareAndSet(j7, RecyclerView.FOREVER_NS)) {
                C1908a.m2205b(th);
            } else {
                EnumC0515c.m323a(this.f3177h);
                this.f3174e.onError(th);
            }
        }

        @Override // p088k4.C1291i4.d
        /* renamed from: b */
        public void mo1509b(long j7) {
            if (compareAndSet(j7, RecyclerView.FOREVER_NS)) {
                EnumC0515c.m323a(this.f3177h);
                this.f3174e.onError(new TimeoutException());
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3177h);
            EnumC0515c.m323a(this.f3176g);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f3177h.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (getAndSet(RecyclerView.FOREVER_NS) != RecyclerView.FOREVER_NS) {
                EnumC0515c.m323a(this.f3176g);
                this.f3174e.onComplete();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (getAndSet(RecyclerView.FOREVER_NS) == RecyclerView.FOREVER_NS) {
                C1908a.m2205b(th);
            } else {
                EnumC0515c.m323a(this.f3176g);
                this.f3174e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            long j7 = get();
            if (j7 != RecyclerView.FOREVER_NS) {
                long j8 = 1 + j7;
                if (compareAndSet(j7, j8)) {
                    InterfaceC2153b interfaceC2153b = this.f3176g.get();
                    if (interfaceC2153b != null) {
                        interfaceC2153b.dispose();
                    }
                    this.f3174e.onNext(t6);
                    try {
                        InterfaceC2125q<?> interfaceC2125qApply = this.f3175f.apply(t6);
                        Objects.requireNonNull(interfaceC2125qApply, "The itemTimeoutIndicator returned a null ObservableSource.");
                        InterfaceC2125q<?> interfaceC2125q = interfaceC2125qApply;
                        a aVar = new a(j8, this);
                        if (EnumC0515c.m325c(this.f3176g, aVar)) {
                            interfaceC2125q.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        C2074b.m2470J(th);
                        this.f3177h.get().dispose();
                        getAndSet(RecyclerView.FOREVER_NS);
                        this.f3174e.onError(th);
                    }
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3177h, interfaceC2153b);
        }
    }

    /* compiled from: ObservableTimeout.java */
    /* renamed from: k4.h4$d */
    public interface d extends C1291i4.d {
        /* renamed from: a */
        void mo1508a(long j7, Throwable th);
    }

    public C1285h4(AbstractC2120l<T> abstractC2120l, InterfaceC2125q<U> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<V>> interfaceC0454n, InterfaceC2125q<? extends T> interfaceC2125q2) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3163f = interfaceC2125q;
        this.f3164g = interfaceC0454n;
        this.f3165h = interfaceC2125q2;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        if (this.f3165h == null) {
            c cVar = new c(interfaceC2127s, this.f3164g);
            interfaceC2127s.onSubscribe(cVar);
            InterfaceC2125q<U> interfaceC2125q = this.f3163f;
            if (interfaceC2125q != null) {
                a aVar = new a(0L, cVar);
                if (EnumC0515c.m325c(cVar.f3176g, aVar)) {
                    interfaceC2125q.subscribe(aVar);
                }
            }
            this.f2772e.subscribe(cVar);
            return;
        }
        b bVar = new b(interfaceC2127s, this.f3164g, this.f3165h);
        interfaceC2127s.onSubscribe(bVar);
        InterfaceC2125q<U> interfaceC2125q2 = this.f3163f;
        if (interfaceC2125q2 != null) {
            a aVar2 = new a(0L, bVar);
            if (EnumC0515c.m325c(bVar.f3170g, aVar2)) {
                interfaceC2125q2.subscribe(aVar2);
            }
        }
        this.f2772e.subscribe(bVar);
    }
}
