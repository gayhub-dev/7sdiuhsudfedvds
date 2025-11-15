package p088k4;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0516d;
import p048f4.AbstractC0996a;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableDistinct.java */
/* renamed from: k4.i0 */
/* loaded from: classes.dex */
public final class C1287i0<T, K> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, K> f3183f;

    /* renamed from: g */
    public final Callable<? extends Collection<? super K>> f3184g;

    /* compiled from: ObservableDistinct.java */
    /* renamed from: k4.i0$a */
    public static final class a<T, K> extends AbstractC0996a<T, T> {

        /* renamed from: j */
        public final Collection<? super K> f3185j;

        /* renamed from: k */
        public final InterfaceC0454n<? super T, K> f3186k;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0454n<? super T, K> interfaceC0454n, Collection<? super K> collection) {
            super(interfaceC2127s);
            this.f3186k = interfaceC0454n;
            this.f3185j = collection;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            return m1014b(i7);
        }

        @Override // p048f4.AbstractC0996a, p040e4.InterfaceC0955h
        public void clear() {
            this.f3185j.clear();
            super.clear();
        }

        @Override // p048f4.AbstractC0996a, p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f1870h) {
                return;
            }
            this.f1870h = true;
            this.f3185j.clear();
            this.f1867e.onComplete();
        }

        @Override // p048f4.AbstractC0996a, p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f1870h) {
                C1908a.m2205b(th);
                return;
            }
            this.f1870h = true;
            this.f3185j.clear();
            this.f1867e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f1870h) {
                return;
            }
            if (this.f1871i != 0) {
                this.f1867e.onNext(null);
                return;
            }
            try {
                K kApply = this.f3186k.apply(t6);
                Objects.requireNonNull(kApply, "The keySelector returned a null key");
                if (this.f3185j.add(kApply)) {
                    this.f1867e.onNext(t6);
                }
            } catch (Throwable th) {
                m1013a(th);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            T tPoll;
            Collection<? super K> collection;
            K kApply;
            do {
                tPoll = this.f1869g.poll();
                if (tPoll == null) {
                    break;
                }
                collection = this.f3185j;
                kApply = this.f3186k.apply(tPoll);
                Objects.requireNonNull(kApply, "The keySelector returned a null key");
            } while (!collection.add(kApply));
            return tPoll;
        }
    }

    public C1287i0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, K> interfaceC0454n, Callable<? extends Collection<? super K>> callable) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3183f = interfaceC0454n;
        this.f3184g = callable;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        try {
            Collection<? super K> collectionCall = this.f3184g.call();
            Objects.requireNonNull(collectionCall, "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.");
            this.f2772e.subscribe(new a(interfaceC2127s, this.f3183f, collectionCall));
        } catch (Throwable th) {
            C2074b.m2470J(th);
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onError(th);
        }
    }
}
