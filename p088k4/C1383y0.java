package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableFlattenIterable.java */
/* renamed from: k4.y0 */
/* loaded from: classes.dex */
public final class C1383y0<T, R> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends Iterable<? extends R>> f4009f;

    /* compiled from: ObservableFlattenIterable.java */
    /* renamed from: k4.y0$a */
    public static final class a<T, R> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f4010e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends Iterable<? extends R>> f4011f;

        /* renamed from: g */
        public InterfaceC2153b f4012g;

        public a(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super T, ? extends Iterable<? extends R>> interfaceC0454n) {
            this.f4010e = interfaceC2127s;
            this.f4011f = interfaceC0454n;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f4012g.dispose();
            this.f4012g = EnumC0515c.DISPOSED;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4012g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            InterfaceC2153b interfaceC2153b = this.f4012g;
            EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
            if (interfaceC2153b == enumC0515c) {
                return;
            }
            this.f4012g = enumC0515c;
            this.f4010e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            InterfaceC2153b interfaceC2153b = this.f4012g;
            EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
            if (interfaceC2153b == enumC0515c) {
                C1908a.m2205b(th);
            } else {
                this.f4012g = enumC0515c;
                this.f4010e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f4012g == EnumC0515c.DISPOSED) {
                return;
            }
            try {
                InterfaceC2127s<? super R> interfaceC2127s = this.f4010e;
                for (R r6 : this.f4011f.apply(t6)) {
                    try {
                        try {
                            Objects.requireNonNull(r6, "The iterator returned a null value");
                            interfaceC2127s.onNext(r6);
                        } catch (Throwable th) {
                            C2074b.m2470J(th);
                            this.f4012g.dispose();
                            onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        C2074b.m2470J(th2);
                        this.f4012g.dispose();
                        onError(th2);
                        return;
                    }
                }
            } catch (Throwable th3) {
                C2074b.m2470J(th3);
                this.f4012g.dispose();
                onError(th3);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f4012g, interfaceC2153b)) {
                this.f4012g = interfaceC2153b;
                this.f4010e.onSubscribe(this);
            }
        }
    }

    public C1383y0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends Iterable<? extends R>> interfaceC0454n) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f4009f = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f4009f));
    }
}
