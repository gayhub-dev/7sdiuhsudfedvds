package p088k4;

import java.util.Iterator;
import java.util.Objects;
import p014b4.InterfaceC0443c;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableZipIterable.java */
/* renamed from: k4.w4 */
/* loaded from: classes.dex */
public final class C1375w4<T, U, V> extends AbstractC2120l<V> {

    /* renamed from: e */
    public final AbstractC2120l<? extends T> f3960e;

    /* renamed from: f */
    public final Iterable<U> f3961f;

    /* renamed from: g */
    public final InterfaceC0443c<? super T, ? super U, ? extends V> f3962g;

    /* compiled from: ObservableZipIterable.java */
    /* renamed from: k4.w4$a */
    public static final class a<T, U, V> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super V> f3963e;

        /* renamed from: f */
        public final Iterator<U> f3964f;

        /* renamed from: g */
        public final InterfaceC0443c<? super T, ? super U, ? extends V> f3965g;

        /* renamed from: h */
        public InterfaceC2153b f3966h;

        /* renamed from: i */
        public boolean f3967i;

        public a(InterfaceC2127s<? super V> interfaceC2127s, Iterator<U> it, InterfaceC0443c<? super T, ? super U, ? extends V> interfaceC0443c) {
            this.f3963e = interfaceC2127s;
            this.f3964f = it;
            this.f3965g = interfaceC0443c;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3966h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3966h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3967i) {
                return;
            }
            this.f3967i = true;
            this.f3963e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3967i) {
                C1908a.m2205b(th);
            } else {
                this.f3967i = true;
                this.f3963e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3967i) {
                return;
            }
            try {
                U next = this.f3964f.next();
                Objects.requireNonNull(next, "The iterator returned a null value");
                try {
                    V vApply = this.f3965g.apply(t6, next);
                    Objects.requireNonNull(vApply, "The zipper function returned a null value");
                    this.f3963e.onNext(vApply);
                    try {
                        if (this.f3964f.hasNext()) {
                            return;
                        }
                        this.f3967i = true;
                        this.f3966h.dispose();
                        this.f3963e.onComplete();
                    } catch (Throwable th) {
                        C2074b.m2470J(th);
                        this.f3967i = true;
                        this.f3966h.dispose();
                        this.f3963e.onError(th);
                    }
                } catch (Throwable th2) {
                    C2074b.m2470J(th2);
                    this.f3967i = true;
                    this.f3966h.dispose();
                    this.f3963e.onError(th2);
                }
            } catch (Throwable th3) {
                C2074b.m2470J(th3);
                this.f3967i = true;
                this.f3966h.dispose();
                this.f3963e.onError(th3);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3966h, interfaceC2153b)) {
                this.f3966h = interfaceC2153b;
                this.f3963e.onSubscribe(this);
            }
        }
    }

    public C1375w4(AbstractC2120l<? extends T> abstractC2120l, Iterable<U> iterable, InterfaceC0443c<? super T, ? super U, ? extends V> interfaceC0443c) {
        this.f3960e = abstractC2120l;
        this.f3961f = iterable;
        this.f3962g = interfaceC0443c;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super V> interfaceC2127s) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        try {
            Iterator<U> it = this.f3961f.iterator();
            Objects.requireNonNull(it, "The iterator returned by other is null");
            try {
                if (it.hasNext()) {
                    this.f3960e.subscribe(new a(interfaceC2127s, it, this.f3962g));
                } else {
                    interfaceC2127s.onSubscribe(enumC0516d);
                    interfaceC2127s.onComplete();
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                interfaceC2127s.onSubscribe(enumC0516d);
                interfaceC2127s.onError(th);
            }
        } catch (Throwable th2) {
            C2074b.m2470J(th2);
            interfaceC2127s.onSubscribe(enumC0516d);
            interfaceC2127s.onError(th2);
        }
    }
}
