package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import p000a.C0000a;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0516d;
import p040e4.InterfaceC0950c;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableScalarXMap.java */
/* renamed from: k4.h3 */
/* loaded from: classes.dex */
public final class C1284h3 {

    /* compiled from: ObservableScalarXMap.java */
    /* renamed from: k4.h3$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC0950c<T>, Runnable {
        private static final long serialVersionUID = 3880992722410194083L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3159e;

        /* renamed from: f */
        public final T f3160f;

        public a(InterfaceC2127s<? super T> interfaceC2127s, T t6) {
            this.f3159e = interfaceC2127s;
            this.f3160f = t6;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            if ((i7 & 1) == 0) {
                return 0;
            }
            lazySet(1);
            return 1;
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            lazySet(3);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            set(3);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get() == 3;
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return get() != 1;
        }

        @Override // p040e4.InterfaceC0955h
        public boolean offer(T t6) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            if (get() != 1) {
                return null;
            }
            lazySet(3);
            return this.f3160f;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() == 0 && compareAndSet(0, 2)) {
                this.f3159e.onNext(this.f3160f);
                if (get() == 2) {
                    lazySet(3);
                    this.f3159e.onComplete();
                }
            }
        }
    }

    /* compiled from: ObservableScalarXMap.java */
    /* renamed from: k4.h3$b */
    public static final class b<T, R> extends AbstractC2120l<R> {

        /* renamed from: e */
        public final T f3161e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> f3162f;

        public b(T t6, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n) {
            this.f3161e = t6;
            this.f3162f = interfaceC0454n;
        }

        @Override // p194y3.AbstractC2120l
        public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
            EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
            try {
                InterfaceC2125q<? extends R> interfaceC2125qApply = this.f3162f.apply(this.f3161e);
                Objects.requireNonNull(interfaceC2125qApply, "The mapper returned a null ObservableSource");
                InterfaceC2125q<? extends R> interfaceC2125q = interfaceC2125qApply;
                if (!(interfaceC2125q instanceof Callable)) {
                    interfaceC2125q.subscribe(interfaceC2127s);
                    return;
                }
                try {
                    Object objCall = ((Callable) interfaceC2125q).call();
                    if (objCall == null) {
                        interfaceC2127s.onSubscribe(enumC0516d);
                        interfaceC2127s.onComplete();
                    } else {
                        a aVar = new a(interfaceC2127s, objCall);
                        interfaceC2127s.onSubscribe(aVar);
                        aVar.run();
                    }
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    interfaceC2127s.onSubscribe(enumC0516d);
                    interfaceC2127s.onError(th);
                }
            } catch (Throwable th2) {
                interfaceC2127s.onSubscribe(enumC0516d);
                interfaceC2127s.onError(th2);
            }
        }
    }

    /* renamed from: a */
    public static <T, R> boolean m1507a(InterfaceC2125q<T> interfaceC2125q, InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        if (!(interfaceC2125q instanceof Callable)) {
            return false;
        }
        try {
            C0000a c0000a = (Object) ((Callable) interfaceC2125q).call();
            if (c0000a == null) {
                interfaceC2127s.onSubscribe(enumC0516d);
                interfaceC2127s.onComplete();
                return true;
            }
            try {
                InterfaceC2125q<? extends R> interfaceC2125qApply = interfaceC0454n.apply(c0000a);
                Objects.requireNonNull(interfaceC2125qApply, "The mapper returned a null ObservableSource");
                InterfaceC2125q<? extends R> interfaceC2125q2 = interfaceC2125qApply;
                if (interfaceC2125q2 instanceof Callable) {
                    try {
                        Object objCall = ((Callable) interfaceC2125q2).call();
                        if (objCall == null) {
                            interfaceC2127s.onSubscribe(enumC0516d);
                            interfaceC2127s.onComplete();
                            return true;
                        }
                        a aVar = new a(interfaceC2127s, objCall);
                        interfaceC2127s.onSubscribe(aVar);
                        aVar.run();
                    } catch (Throwable th) {
                        C2074b.m2470J(th);
                        interfaceC2127s.onSubscribe(enumC0516d);
                        interfaceC2127s.onError(th);
                        return true;
                    }
                } else {
                    interfaceC2125q2.subscribe(interfaceC2127s);
                }
                return true;
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                interfaceC2127s.onSubscribe(enumC0516d);
                interfaceC2127s.onError(th2);
                return true;
            }
        } catch (Throwable th3) {
            C2074b.m2470J(th3);
            interfaceC2127s.onSubscribe(enumC0516d);
            interfaceC2127s.onError(th3);
            return true;
        }
    }
}
