package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import p005a4.C0009a;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableMapNotification.java */
/* renamed from: k4.h2 */
/* loaded from: classes.dex */
public final class C1283h2<T, R> extends AbstractC1238a<T, InterfaceC2125q<? extends R>> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> f3151f;

    /* renamed from: g */
    public final InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends R>> f3152g;

    /* renamed from: h */
    public final Callable<? extends InterfaceC2125q<? extends R>> f3153h;

    /* compiled from: ObservableMapNotification.java */
    /* renamed from: k4.h2$a */
    public static final class a<T, R> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super InterfaceC2125q<? extends R>> f3154e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> f3155f;

        /* renamed from: g */
        public final InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends R>> f3156g;

        /* renamed from: h */
        public final Callable<? extends InterfaceC2125q<? extends R>> f3157h;

        /* renamed from: i */
        public InterfaceC2153b f3158i;

        public a(InterfaceC2127s<? super InterfaceC2125q<? extends R>> interfaceC2127s, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends R>> interfaceC0454n2, Callable<? extends InterfaceC2125q<? extends R>> callable) {
            this.f3154e = interfaceC2127s;
            this.f3155f = interfaceC0454n;
            this.f3156g = interfaceC0454n2;
            this.f3157h = callable;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3158i.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3158i.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            try {
                InterfaceC2125q<? extends R> interfaceC2125qCall = this.f3157h.call();
                Objects.requireNonNull(interfaceC2125qCall, "The onComplete ObservableSource returned is null");
                this.f3154e.onNext(interfaceC2125qCall);
                this.f3154e.onComplete();
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3154e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            try {
                InterfaceC2125q<? extends R> interfaceC2125qApply = this.f3156g.apply(th);
                Objects.requireNonNull(interfaceC2125qApply, "The onError ObservableSource returned is null");
                this.f3154e.onNext(interfaceC2125qApply);
                this.f3154e.onComplete();
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                this.f3154e.onError(new C0009a(th, th2));
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            try {
                InterfaceC2125q<? extends R> interfaceC2125qApply = this.f3155f.apply(t6);
                Objects.requireNonNull(interfaceC2125qApply, "The onNext ObservableSource returned is null");
                this.f3154e.onNext(interfaceC2125qApply);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3154e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3158i, interfaceC2153b)) {
                this.f3158i = interfaceC2153b;
                this.f3154e.onSubscribe(this);
            }
        }
    }

    public C1283h2(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends R>> interfaceC0454n2, Callable<? extends InterfaceC2125q<? extends R>> callable) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3151f = interfaceC0454n;
        this.f3152g = interfaceC0454n2;
        this.f3153h = callable;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super InterfaceC2125q<? extends R>> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3151f, this.f3152g, this.f3153h));
    }
}
