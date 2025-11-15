package p088k4;

import java.util.concurrent.atomic.AtomicInteger;
import p014b4.InterfaceC0445e;
import p022c4.C0518f;
import p022c4.EnumC0515c;
import p186x2.C2074b;
import p192y1.C2103g;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableRepeatUntil.java */
/* renamed from: k4.z2 */
/* loaded from: classes.dex */
public final class C1390z2<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0445e f4040f;

    /* compiled from: ObservableRepeatUntil.java */
    /* renamed from: k4.z2$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T> {
        private static final long serialVersionUID = -7098360935104053232L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f4041e;

        /* renamed from: f */
        public final C0518f f4042f;

        /* renamed from: g */
        public final InterfaceC2125q<? extends T> f4043g;

        /* renamed from: h */
        public final InterfaceC0445e f4044h;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0445e interfaceC0445e, C0518f c0518f, InterfaceC2125q<? extends T> interfaceC2125q) {
            this.f4041e = interfaceC2127s;
            this.f4042f = c0518f;
            this.f4043g = interfaceC2125q;
            this.f4044h = interfaceC0445e;
        }

        /* renamed from: a */
        public void m1562a() {
            if (getAndIncrement() == 0) {
                int iAddAndGet = 1;
                do {
                    this.f4043g.subscribe(this);
                    iAddAndGet = addAndGet(-iAddAndGet);
                } while (iAddAndGet != 0);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            try {
                if (((C2103g) this.f4044h).m2546a()) {
                    this.f4041e.onComplete();
                } else {
                    m1562a();
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f4041e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f4041e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f4041e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m325c(this.f4042f, interfaceC2153b);
        }
    }

    public C1390z2(AbstractC2120l<T> abstractC2120l, InterfaceC0445e interfaceC0445e) {
        super((InterfaceC2125q) abstractC2120l);
        this.f4040f = interfaceC0445e;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C0518f c0518f = new C0518f();
        interfaceC2127s.onSubscribe(c0518f);
        new a(interfaceC2127s, this.f4040f, c0518f, this.f2772e).m1562a();
    }
}
