package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0443c;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableReduceSeedSingle.java */
/* renamed from: k4.v2 */
/* loaded from: classes.dex */
public final class C1367v2<T, R> extends AbstractC2129u<R> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3893a;

    /* renamed from: b */
    public final R f3894b;

    /* renamed from: c */
    public final InterfaceC0443c<R, ? super T, R> f3895c;

    /* compiled from: ObservableReduceSeedSingle.java */
    /* renamed from: k4.v2$a */
    public static final class a<T, R> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2130v<? super R> f3896e;

        /* renamed from: f */
        public final InterfaceC0443c<R, ? super T, R> f3897f;

        /* renamed from: g */
        public R f3898g;

        /* renamed from: h */
        public InterfaceC2153b f3899h;

        public a(InterfaceC2130v<? super R> interfaceC2130v, InterfaceC0443c<R, ? super T, R> interfaceC0443c, R r6) {
            this.f3896e = interfaceC2130v;
            this.f3898g = r6;
            this.f3897f = interfaceC0443c;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3899h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3899h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            R r6 = this.f3898g;
            if (r6 != null) {
                this.f3898g = null;
                this.f3896e.mo1016a(r6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3898g == null) {
                C1908a.m2205b(th);
            } else {
                this.f3898g = null;
                this.f3896e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            R r6 = this.f3898g;
            if (r6 != null) {
                try {
                    R rApply = this.f3897f.apply(r6, t6);
                    Objects.requireNonNull(rApply, "The reducer returned a null value");
                    this.f3898g = rApply;
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    this.f3899h.dispose();
                    onError(th);
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3899h, interfaceC2153b)) {
                this.f3899h = interfaceC2153b;
                this.f3896e.onSubscribe(this);
            }
        }
    }

    public C1367v2(InterfaceC2125q<T> interfaceC2125q, R r6, InterfaceC0443c<R, ? super T, R> interfaceC0443c) {
        this.f3893a = interfaceC2125q;
        this.f3894b = r6;
        this.f3895c = interfaceC0443c;
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super R> interfaceC2130v) {
        this.f3893a.subscribe(new a(interfaceC2130v, this.f3895c, this.f3894b));
    }
}
