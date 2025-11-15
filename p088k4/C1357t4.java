package p088k4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0443c;
import p022c4.EnumC0515c;
import p153s4.C1882e;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableWithLatestFrom.java */
/* renamed from: k4.t4 */
/* loaded from: classes.dex */
public final class C1357t4<T, U, R> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final InterfaceC0443c<? super T, ? super U, ? extends R> f3814f;

    /* renamed from: g */
    public final InterfaceC2125q<? extends U> f3815g;

    /* compiled from: ObservableWithLatestFrom.java */
    /* renamed from: k4.t4$a */
    public static final class a<T, U, R> extends AtomicReference<U> implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -312246233408980075L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3816e;

        /* renamed from: f */
        public final InterfaceC0443c<? super T, ? super U, ? extends R> f3817f;

        /* renamed from: g */
        public final AtomicReference<InterfaceC2153b> f3818g = new AtomicReference<>();

        /* renamed from: h */
        public final AtomicReference<InterfaceC2153b> f3819h = new AtomicReference<>();

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c) {
            this.f3816e = interfaceC2127s;
            this.f3817f = interfaceC0443c;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3818g);
            EnumC0515c.m323a(this.f3819h);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f3818g.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            EnumC0515c.m323a(this.f3819h);
            this.f3816e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            EnumC0515c.m323a(this.f3819h);
            this.f3816e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            U u6 = get();
            if (u6 != null) {
                try {
                    R rApply = this.f3817f.apply(t6, u6);
                    Objects.requireNonNull(rApply, "The combiner returned a null value");
                    this.f3816e.onNext(rApply);
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    dispose();
                    this.f3816e.onError(th);
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3818g, interfaceC2153b);
        }
    }

    /* compiled from: ObservableWithLatestFrom.java */
    /* renamed from: k4.t4$b */
    public final class b implements InterfaceC2127s<U> {

        /* renamed from: e */
        public final a<T, U, R> f3820e;

        public b(C1357t4 c1357t4, a<T, U, R> aVar) {
            this.f3820e = aVar;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            a<T, U, R> aVar = this.f3820e;
            EnumC0515c.m323a(aVar.f3818g);
            aVar.f3816e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(U u6) {
            this.f3820e.lazySet(u6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3820e.f3819h, interfaceC2153b);
        }
    }

    public C1357t4(InterfaceC2125q<T> interfaceC2125q, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c, InterfaceC2125q<? extends U> interfaceC2125q2) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3814f = interfaceC0443c;
        this.f3815g = interfaceC2125q2;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        C1882e c1882e = new C1882e(interfaceC2127s);
        a aVar = new a(c1882e, this.f3814f);
        c1882e.onSubscribe(aVar);
        this.f3815g.subscribe(new b(this, aVar));
        this.f2772e.subscribe(aVar);
    }
}
