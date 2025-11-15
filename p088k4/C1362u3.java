package p088k4;

import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSubscribeOn.java */
/* renamed from: k4.u3 */
/* loaded from: classes.dex */
public final class C1362u3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final AbstractC2128t f3858f;

    /* compiled from: ObservableSubscribeOn.java */
    /* renamed from: k4.u3$a */
    public static final class a<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 8094547886072529208L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3859e;

        /* renamed from: f */
        public final AtomicReference<InterfaceC2153b> f3860f = new AtomicReference<>();

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3859e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3860f);
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3859e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3859e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3859e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3860f, interfaceC2153b);
        }
    }

    /* compiled from: ObservableSubscribeOn.java */
    /* renamed from: k4.u3$b */
    public final class b implements Runnable {

        /* renamed from: e */
        public final a<T> f3861e;

        public b(a<T> aVar) {
            this.f3861e = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            C1362u3.this.f2772e.subscribe(this.f3861e);
        }
    }

    public C1362u3(InterfaceC2125q<T> interfaceC2125q, AbstractC2128t abstractC2128t) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3858f = abstractC2128t;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        a aVar = new a(interfaceC2127s);
        interfaceC2127s.onSubscribe(aVar);
        EnumC0515c.m327f(aVar, this.f3858f.scheduleDirect(new b(aVar)));
    }
}
