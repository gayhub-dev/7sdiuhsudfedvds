package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import p005a4.C0009a;
import p014b4.InterfaceC0446f;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableUsing.java */
/* renamed from: k4.n4 */
/* loaded from: classes.dex */
public final class C1321n4<T, D> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final Callable<? extends D> f3427e;

    /* renamed from: f */
    public final InterfaceC0454n<? super D, ? extends InterfaceC2125q<? extends T>> f3428f;

    /* renamed from: g */
    public final InterfaceC0446f<? super D> f3429g;

    /* renamed from: h */
    public final boolean f3430h;

    /* compiled from: ObservableUsing.java */
    /* renamed from: k4.n4$a */
    public static final class a<T, D> extends AtomicBoolean implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 5904473792286235046L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3431e;

        /* renamed from: f */
        public final D f3432f;

        /* renamed from: g */
        public final InterfaceC0446f<? super D> f3433g;

        /* renamed from: h */
        public final boolean f3434h;

        /* renamed from: i */
        public InterfaceC2153b f3435i;

        public a(InterfaceC2127s<? super T> interfaceC2127s, D d7, InterfaceC0446f<? super D> interfaceC0446f, boolean z6) {
            this.f3431e = interfaceC2127s;
            this.f3432f = d7;
            this.f3433g = interfaceC0446f;
            this.f3434h = z6;
        }

        /* renamed from: a */
        public void m1525a() {
            if (compareAndSet(false, true)) {
                try {
                    this.f3433g.accept(this.f3432f);
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    C1908a.m2205b(th);
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            m1525a();
            this.f3435i.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (!this.f3434h) {
                this.f3431e.onComplete();
                this.f3435i.dispose();
                m1525a();
                return;
            }
            if (compareAndSet(false, true)) {
                try {
                    this.f3433g.accept(this.f3432f);
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    this.f3431e.onError(th);
                    return;
                }
            }
            this.f3435i.dispose();
            this.f3431e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!this.f3434h) {
                this.f3431e.onError(th);
                this.f3435i.dispose();
                m1525a();
                return;
            }
            if (compareAndSet(false, true)) {
                try {
                    this.f3433g.accept(this.f3432f);
                } catch (Throwable th2) {
                    C2074b.m2470J(th2);
                    th = new C0009a(th, th2);
                }
            }
            this.f3435i.dispose();
            this.f3431e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3431e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3435i, interfaceC2153b)) {
                this.f3435i = interfaceC2153b;
                this.f3431e.onSubscribe(this);
            }
        }
    }

    public C1321n4(Callable<? extends D> callable, InterfaceC0454n<? super D, ? extends InterfaceC2125q<? extends T>> interfaceC0454n, InterfaceC0446f<? super D> interfaceC0446f, boolean z6) {
        this.f3427e = callable;
        this.f3428f = interfaceC0454n;
        this.f3429g = interfaceC0446f;
        this.f3430h = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        try {
            D dCall = this.f3427e.call();
            try {
                InterfaceC2125q<? extends T> interfaceC2125qApply = this.f3428f.apply(dCall);
                Objects.requireNonNull(interfaceC2125qApply, "The sourceSupplier returned a null ObservableSource");
                interfaceC2125qApply.subscribe(new a(interfaceC2127s, dCall, this.f3429g, this.f3430h));
            } catch (Throwable th) {
                C2074b.m2470J(th);
                try {
                    this.f3429g.accept(dCall);
                    interfaceC2127s.onSubscribe(enumC0516d);
                    interfaceC2127s.onError(th);
                } catch (Throwable th2) {
                    C2074b.m2470J(th2);
                    C0009a c0009a = new C0009a(th, th2);
                    interfaceC2127s.onSubscribe(enumC0516d);
                    interfaceC2127s.onError(c0009a);
                }
            }
        } catch (Throwable th3) {
            C2074b.m2470J(th3);
            interfaceC2127s.onSubscribe(enumC0516d);
            interfaceC2127s.onError(th3);
        }
    }
}
