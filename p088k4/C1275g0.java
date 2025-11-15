package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p138q4.EnumC1776h;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.C2119k;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableDematerialize.java */
/* renamed from: k4.g0 */
/* loaded from: classes.dex */
public final class C1275g0<T, R> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends C2119k<R>> f3089f;

    /* compiled from: ObservableDematerialize.java */
    /* renamed from: k4.g0$a */
    public static final class a<T, R> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3090e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends C2119k<R>> f3091f;

        /* renamed from: g */
        public boolean f3092g;

        /* renamed from: h */
        public InterfaceC2153b f3093h;

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super T, ? extends C2119k<R>> interfaceC0454n) {
            this.f3090e = interfaceC2127s;
            this.f3091f = interfaceC0454n;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3093h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3093h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3092g) {
                return;
            }
            this.f3092g = true;
            this.f3090e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3092g) {
                C1908a.m2205b(th);
            } else {
                this.f3092g = true;
                this.f3090e.onError(th);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3092g) {
                if (t6 instanceof C2119k) {
                    C2119k c2119k = (C2119k) t6;
                    if (c2119k.f6248a instanceof EnumC1776h.b) {
                        C1908a.m2205b(c2119k.m2556a());
                        return;
                    }
                    return;
                }
                return;
            }
            try {
                C2119k<R> c2119kApply = this.f3091f.apply(t6);
                Objects.requireNonNull(c2119kApply, "The selector returned a null Notification");
                C2119k<R> c2119k2 = c2119kApply;
                Object obj = c2119k2.f6248a;
                if (obj instanceof EnumC1776h.b) {
                    this.f3093h.dispose();
                    onError(c2119k2.m2556a());
                    return;
                }
                if (!(obj == null)) {
                    this.f3090e.onNext(c2119k2.m2557b());
                } else {
                    this.f3093h.dispose();
                    onComplete();
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3093h.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3093h, interfaceC2153b)) {
                this.f3093h = interfaceC2153b;
                this.f3090e.onSubscribe(this);
            }
        }
    }

    public C1275g0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends C2119k<R>> interfaceC0454n) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3089f = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3089f));
    }
}
