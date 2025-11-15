package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0443c;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2116h;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableReduceMaybe.java */
/* renamed from: k4.u2 */
/* loaded from: classes.dex */
public final class C1361u2<T> extends AbstractC2116h<T> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3851a;

    /* renamed from: b */
    public final InterfaceC0443c<T, T, T> f3852b;

    /* compiled from: ObservableReduceMaybe.java */
    /* renamed from: k4.u2$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2117i<? super T> f3853e;

        /* renamed from: f */
        public final InterfaceC0443c<T, T, T> f3854f;

        /* renamed from: g */
        public boolean f3855g;

        /* renamed from: h */
        public T f3856h;

        /* renamed from: i */
        public InterfaceC2153b f3857i;

        public a(InterfaceC2117i<? super T> interfaceC2117i, InterfaceC0443c<T, T, T> interfaceC0443c) {
            this.f3853e = interfaceC2117i;
            this.f3854f = interfaceC0443c;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3857i.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3857i.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3855g) {
                return;
            }
            this.f3855g = true;
            T t6 = this.f3856h;
            this.f3856h = null;
            if (t6 != null) {
                this.f3853e.mo1016a(t6);
            } else {
                this.f3853e.onComplete();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3855g) {
                C1908a.m2205b(th);
                return;
            }
            this.f3855g = true;
            this.f3856h = null;
            this.f3853e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3855g) {
                return;
            }
            T t7 = this.f3856h;
            if (t7 == null) {
                this.f3856h = t6;
                return;
            }
            try {
                T tApply = this.f3854f.apply(t7, t6);
                Objects.requireNonNull(tApply, "The reducer returned a null value");
                this.f3856h = tApply;
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3857i.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3857i, interfaceC2153b)) {
                this.f3857i = interfaceC2153b;
                this.f3853e.onSubscribe(this);
            }
        }
    }

    public C1361u2(InterfaceC2125q<T> interfaceC2125q, InterfaceC0443c<T, T, T> interfaceC0443c) {
        this.f3851a = interfaceC2125q;
        this.f3852b = interfaceC0443c;
    }

    @Override // p194y3.AbstractC2116h
    /* renamed from: c */
    public void mo1488c(InterfaceC2117i<? super T> interfaceC2117i) {
        this.f3851a.subscribe(new a(interfaceC2117i, this.f3852b));
    }
}
