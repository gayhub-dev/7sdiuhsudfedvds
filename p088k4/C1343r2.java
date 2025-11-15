package p088k4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p181w4.C2030b;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservablePublishSelector.java */
/* renamed from: k4.r2 */
/* loaded from: classes.dex */
public final class C1343r2<T, R> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> f3629f;

    /* compiled from: ObservablePublishSelector.java */
    /* renamed from: k4.r2$a */
    public static final class a<T, R> implements InterfaceC2127s<T> {

        /* renamed from: e */
        public final C2030b<T> f3630e;

        /* renamed from: f */
        public final AtomicReference<InterfaceC2153b> f3631f;

        public a(C2030b<T> c2030b, AtomicReference<InterfaceC2153b> atomicReference) {
            this.f3630e = c2030b;
            this.f3631f = atomicReference;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3630e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3630e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3630e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3631f, interfaceC2153b);
        }
    }

    /* compiled from: ObservablePublishSelector.java */
    /* renamed from: k4.r2$b */
    public static final class b<T, R> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<R>, InterfaceC2153b {
        private static final long serialVersionUID = 854110278590336484L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3632e;

        /* renamed from: f */
        public InterfaceC2153b f3633f;

        public b(InterfaceC2127s<? super R> interfaceC2127s) {
            this.f3632e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3633f.dispose();
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3633f.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            EnumC0515c.m323a(this);
            this.f3632e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            EnumC0515c.m323a(this);
            this.f3632e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(R r6) {
            this.f3632e.onNext(r6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3633f, interfaceC2153b)) {
                this.f3633f = interfaceC2153b;
                this.f3632e.onSubscribe(this);
            }
        }
    }

    public C1343r2(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3629f = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        C2030b c2030b = new C2030b();
        try {
            InterfaceC2125q<R> interfaceC2125qApply = this.f3629f.apply(c2030b);
            Objects.requireNonNull(interfaceC2125qApply, "The selector returned a null ObservableSource");
            InterfaceC2125q<R> interfaceC2125q = interfaceC2125qApply;
            b bVar = new b(interfaceC2127s);
            interfaceC2125q.subscribe(bVar);
            this.f2772e.subscribe(new a(c2030b, bVar));
        } catch (Throwable th) {
            C2074b.m2470J(th);
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onError(th);
        }
    }
}
