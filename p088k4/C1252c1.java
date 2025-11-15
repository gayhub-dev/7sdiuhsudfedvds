package p088k4;

import java.util.Iterator;
import java.util.Objects;
import p022c4.EnumC0516d;
import p048f4.AbstractC0998c;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableFromIterable.java */
/* renamed from: k4.c1 */
/* loaded from: classes.dex */
public final class C1252c1<T> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final Iterable<? extends T> f2915e;

    /* compiled from: ObservableFromIterable.java */
    /* renamed from: k4.c1$a */
    public static final class a<T> extends AbstractC0998c<T> {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2916e;

        /* renamed from: f */
        public final Iterator<? extends T> f2917f;

        /* renamed from: g */
        public volatile boolean f2918g;

        /* renamed from: h */
        public boolean f2919h;

        /* renamed from: i */
        public boolean f2920i;

        /* renamed from: j */
        public boolean f2921j;

        public a(InterfaceC2127s<? super T> interfaceC2127s, Iterator<? extends T> it) {
            this.f2916e = interfaceC2127s;
            this.f2917f = it;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            if ((i7 & 1) == 0) {
                return 0;
            }
            this.f2919h = true;
            return 1;
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            this.f2920i = true;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2918g = true;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2918g;
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return this.f2920i;
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            if (this.f2920i) {
                return null;
            }
            if (!this.f2921j) {
                this.f2921j = true;
            } else if (!this.f2917f.hasNext()) {
                this.f2920i = true;
                return null;
            }
            T next = this.f2917f.next();
            Objects.requireNonNull(next, "The iterator returned a null value");
            return next;
        }
    }

    public C1252c1(Iterable<? extends T> iterable) {
        this.f2915e = iterable;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        try {
            Iterator<? extends T> it = this.f2915e.iterator();
            try {
                if (!it.hasNext()) {
                    interfaceC2127s.onSubscribe(enumC0516d);
                    interfaceC2127s.onComplete();
                    return;
                }
                a aVar = new a(interfaceC2127s, it);
                interfaceC2127s.onSubscribe(aVar);
                if (aVar.f2919h) {
                    return;
                }
                while (!aVar.f2918g) {
                    try {
                        T next = aVar.f2917f.next();
                        Objects.requireNonNull(next, "The iterator returned a null value");
                        aVar.f2916e.onNext(next);
                        if (aVar.f2918g) {
                            return;
                        }
                        try {
                            if (!aVar.f2917f.hasNext()) {
                                if (aVar.f2918g) {
                                    return;
                                }
                                aVar.f2916e.onComplete();
                                return;
                            }
                        } catch (Throwable th) {
                            C2074b.m2470J(th);
                            aVar.f2916e.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        C2074b.m2470J(th2);
                        aVar.f2916e.onError(th2);
                        return;
                    }
                }
            } catch (Throwable th3) {
                C2074b.m2470J(th3);
                interfaceC2127s.onSubscribe(enumC0516d);
                interfaceC2127s.onError(th3);
            }
        } catch (Throwable th4) {
            C2074b.m2470J(th4);
            interfaceC2127s.onSubscribe(enumC0516d);
            interfaceC2127s.onError(th4);
        }
    }
}
