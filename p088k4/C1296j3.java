package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import p014b4.InterfaceC0443c;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableScanSeed.java */
/* renamed from: k4.j3 */
/* loaded from: classes.dex */
public final class C1296j3<T, R> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final InterfaceC0443c<R, ? super T, R> f3242f;

    /* renamed from: g */
    public final Callable<R> f3243g;

    /* compiled from: ObservableScanSeed.java */
    /* renamed from: k4.j3$a */
    public static final class a<T, R> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3244e;

        /* renamed from: f */
        public final InterfaceC0443c<R, ? super T, R> f3245f;

        /* renamed from: g */
        public R f3246g;

        /* renamed from: h */
        public InterfaceC2153b f3247h;

        /* renamed from: i */
        public boolean f3248i;

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0443c<R, ? super T, R> interfaceC0443c, R r6) {
            this.f3244e = interfaceC2127s;
            this.f3245f = interfaceC0443c;
            this.f3246g = r6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3247h.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3247h.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3248i) {
                return;
            }
            this.f3248i = true;
            this.f3244e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3248i) {
                C1908a.m2205b(th);
            } else {
                this.f3248i = true;
                this.f3244e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3248i) {
                return;
            }
            try {
                R rApply = this.f3245f.apply(this.f3246g, t6);
                Objects.requireNonNull(rApply, "The accumulator returned a null value");
                this.f3246g = rApply;
                this.f3244e.onNext(rApply);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3247h.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3247h, interfaceC2153b)) {
                this.f3247h = interfaceC2153b;
                this.f3244e.onSubscribe(this);
                this.f3244e.onNext(this.f3246g);
            }
        }
    }

    public C1296j3(InterfaceC2125q<T> interfaceC2125q, Callable<R> callable, InterfaceC0443c<R, ? super T, R> interfaceC0443c) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3242f = interfaceC0443c;
        this.f3243g = callable;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        try {
            R rCall = this.f3243g.call();
            Objects.requireNonNull(rCall, "The seed supplied is null");
            this.f2772e.subscribe(new a(interfaceC2127s, this.f3242f, rCall));
        } catch (Throwable th) {
            C2074b.m2470J(th);
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onError(th);
        }
    }
}
