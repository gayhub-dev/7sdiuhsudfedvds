package p088k4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p138q4.C1771c;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2112d;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableMergeWithCompletable.java */
/* renamed from: k4.j2 */
/* loaded from: classes.dex */
public final class C1295j2<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2112d f3234f;

    /* compiled from: ObservableMergeWithCompletable.java */
    /* renamed from: k4.j2$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = -4592979584110982903L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3235e;

        /* renamed from: f */
        public final AtomicReference<InterfaceC2153b> f3236f = new AtomicReference<>();

        /* renamed from: g */
        public final C2177a f3237g = new C2177a(this);

        /* renamed from: h */
        public final C1771c f3238h = new C1771c();

        /* renamed from: i */
        public volatile boolean f3239i;

        /* renamed from: j */
        public volatile boolean f3240j;

        /* compiled from: ObservableMergeWithCompletable.java */
        /* renamed from: k4.j2$a$a, reason: collision with other inner class name */
        public static final class C2177a extends AtomicReference<InterfaceC2153b> implements InterfaceC2111c {
            private static final long serialVersionUID = -2935427570954647017L;

            /* renamed from: e */
            public final a<?> f3241e;

            public C2177a(a<?> aVar) {
                this.f3241e = aVar;
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onComplete() {
                a<?> aVar = this.f3241e;
                aVar.f3240j = true;
                if (aVar.f3239i) {
                    C2074b.m2500w(aVar.f3235e, aVar, aVar.f3238h);
                }
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onError(Throwable th) {
                a<?> aVar = this.f3241e;
                EnumC0515c.m323a(aVar.f3236f);
                C2074b.m2501x(aVar.f3235e, th, aVar, aVar.f3238h);
            }

            @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3235e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3236f);
            EnumC0515c.m323a(this.f3237g);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f3236f.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3239i = true;
            if (this.f3240j) {
                C2074b.m2500w(this.f3235e, this, this.f3238h);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            EnumC0515c.m323a(this.f3236f);
            C2074b.m2501x(this.f3235e, th, this, this.f3238h);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            C2074b.m2502y(this.f3235e, t6, this, this.f3238h);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3236f, interfaceC2153b);
        }
    }

    public C1295j2(AbstractC2120l<T> abstractC2120l, InterfaceC2112d interfaceC2112d) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3234f = interfaceC2112d;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        a aVar = new a(interfaceC2127s);
        interfaceC2127s.onSubscribe(aVar);
        this.f2772e.subscribe(aVar);
        this.f3234f.mo2552b(aVar.f3237g);
    }
}
