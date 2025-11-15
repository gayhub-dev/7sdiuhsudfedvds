package p088k4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p138q4.C1771c;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTakeUntil.java */
/* renamed from: k4.b4 */
/* loaded from: classes.dex */
public final class C1249b4<T, U> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2125q<? extends U> f2890f;

    /* compiled from: ObservableTakeUntil.java */
    /* renamed from: k4.b4$a */
    public static final class a<T, U> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 1418547743690811973L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2891e;

        /* renamed from: f */
        public final AtomicReference<InterfaceC2153b> f2892f = new AtomicReference<>();

        /* renamed from: g */
        public final a<T, U>.C2172a f2893g = new C2172a();

        /* renamed from: h */
        public final C1771c f2894h = new C1771c();

        /* compiled from: ObservableTakeUntil.java */
        /* renamed from: k4.b4$a$a, reason: collision with other inner class name */
        public final class C2172a extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<U> {
            private static final long serialVersionUID = -8693423678067375039L;

            public C2172a() {
            }

            @Override // p194y3.InterfaceC2127s
            public void onComplete() {
                a aVar = a.this;
                EnumC0515c.m323a(aVar.f2892f);
                C2074b.m2500w(aVar.f2891e, aVar, aVar.f2894h);
            }

            @Override // p194y3.InterfaceC2127s
            public void onError(Throwable th) {
                a aVar = a.this;
                EnumC0515c.m323a(aVar.f2892f);
                C2074b.m2501x(aVar.f2891e, th, aVar, aVar.f2894h);
            }

            @Override // p194y3.InterfaceC2127s
            public void onNext(U u6) {
                EnumC0515c.m323a(this);
                a aVar = a.this;
                EnumC0515c.m323a(aVar.f2892f);
                C2074b.m2500w(aVar.f2891e, aVar, aVar.f2894h);
            }

            @Override // p194y3.InterfaceC2127s
            public void onSubscribe(InterfaceC2153b interfaceC2153b) {
                EnumC0515c.m327f(this, interfaceC2153b);
            }
        }

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f2891e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f2892f);
            EnumC0515c.m323a(this.f2893g);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f2892f.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            EnumC0515c.m323a(this.f2893g);
            C2074b.m2500w(this.f2891e, this, this.f2894h);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            EnumC0515c.m323a(this.f2893g);
            C2074b.m2501x(this.f2891e, th, this, this.f2894h);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            C2074b.m2502y(this.f2891e, t6, this, this.f2894h);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f2892f, interfaceC2153b);
        }
    }

    public C1249b4(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<? extends U> interfaceC2125q2) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f2890f = interfaceC2125q2;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        a aVar = new a(interfaceC2127s);
        interfaceC2127s.onSubscribe(aVar);
        this.f2890f.subscribe(aVar.f2893g);
        this.f2772e.subscribe(aVar);
    }
}
