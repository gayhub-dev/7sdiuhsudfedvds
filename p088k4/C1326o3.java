package p088k4;

import java.util.NoSuchElementException;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSingleSingle.java */
/* renamed from: k4.o3 */
/* loaded from: classes.dex */
public final class C1326o3<T> extends AbstractC2129u<T> {

    /* renamed from: a */
    public final InterfaceC2125q<? extends T> f3492a;

    /* renamed from: b */
    public final T f3493b;

    /* compiled from: ObservableSingleSingle.java */
    /* renamed from: k4.o3$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2130v<? super T> f3494e;

        /* renamed from: f */
        public final T f3495f;

        /* renamed from: g */
        public InterfaceC2153b f3496g;

        /* renamed from: h */
        public T f3497h;

        /* renamed from: i */
        public boolean f3498i;

        public a(InterfaceC2130v<? super T> interfaceC2130v, T t6) {
            this.f3494e = interfaceC2130v;
            this.f3495f = t6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3496g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3496g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3498i) {
                return;
            }
            this.f3498i = true;
            T t6 = this.f3497h;
            this.f3497h = null;
            if (t6 == null) {
                t6 = this.f3495f;
            }
            if (t6 != null) {
                this.f3494e.mo1016a(t6);
            } else {
                this.f3494e.onError(new NoSuchElementException());
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3498i) {
                C1908a.m2205b(th);
            } else {
                this.f3498i = true;
                this.f3494e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3498i) {
                return;
            }
            if (this.f3497h == null) {
                this.f3497h = t6;
                return;
            }
            this.f3498i = true;
            this.f3496g.dispose();
            this.f3494e.onError(new IllegalArgumentException("Sequence contains more than one element!"));
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3496g, interfaceC2153b)) {
                this.f3496g = interfaceC2153b;
                this.f3494e.onSubscribe(this);
            }
        }
    }

    public C1326o3(InterfaceC2125q<? extends T> interfaceC2125q, T t6) {
        this.f3492a = interfaceC2125q;
        this.f3493b = t6;
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super T> interfaceC2130v) {
        this.f3492a.subscribe(new a(interfaceC2130v, this.f3493b));
    }
}
