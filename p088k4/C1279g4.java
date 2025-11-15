package p088k4;

import java.util.concurrent.TimeUnit;
import p022c4.EnumC0515c;
import p174v4.C2013b;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTimeInterval.java */
/* renamed from: k4.g4 */
/* loaded from: classes.dex */
public final class C1279g4<T> extends AbstractC1238a<T, C2013b<T>> {

    /* renamed from: f */
    public final AbstractC2128t f3131f;

    /* renamed from: g */
    public final TimeUnit f3132g;

    /* compiled from: ObservableTimeInterval.java */
    /* renamed from: k4.g4$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super C2013b<T>> f3133e;

        /* renamed from: f */
        public final TimeUnit f3134f;

        /* renamed from: g */
        public final AbstractC2128t f3135g;

        /* renamed from: h */
        public long f3136h;

        /* renamed from: i */
        public InterfaceC2153b f3137i;

        public a(InterfaceC2127s<? super C2013b<T>> interfaceC2127s, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
            this.f3133e = interfaceC2127s;
            this.f3135g = abstractC2128t;
            this.f3134f = timeUnit;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3137i.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3137i.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3133e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3133e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            long jNow = this.f3135g.now(this.f3134f);
            long j7 = this.f3136h;
            this.f3136h = jNow;
            this.f3133e.onNext(new C2013b(t6, jNow - j7, this.f3134f));
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3137i, interfaceC2153b)) {
                this.f3137i = interfaceC2153b;
                this.f3136h = this.f3135g.now(this.f3134f);
                this.f3133e.onSubscribe(this);
            }
        }
    }

    public C1279g4(InterfaceC2125q<T> interfaceC2125q, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3131f = abstractC2128t;
        this.f3132g = timeUnit;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super C2013b<T>> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3132g, this.f3131f));
    }
}
