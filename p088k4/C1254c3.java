package p088k4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p005a4.C0009a;
import p014b4.InterfaceC0444d;
import p022c4.C0518f;
import p022c4.EnumC0515c;
import p032d4.C0871b;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableRetryBiPredicate.java */
/* renamed from: k4.c3 */
/* loaded from: classes.dex */
public final class C1254c3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0444d<? super Integer, ? super Throwable> f2923f;

    /* compiled from: ObservableRetryBiPredicate.java */
    /* renamed from: k4.c3$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T> {
        private static final long serialVersionUID = -7098360935104053232L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2924e;

        /* renamed from: f */
        public final C0518f f2925f;

        /* renamed from: g */
        public final InterfaceC2125q<? extends T> f2926g;

        /* renamed from: h */
        public final InterfaceC0444d<? super Integer, ? super Throwable> f2927h;

        /* renamed from: i */
        public int f2928i;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0444d<? super Integer, ? super Throwable> interfaceC0444d, C0518f c0518f, InterfaceC2125q<? extends T> interfaceC2125q) {
            this.f2924e = interfaceC2127s;
            this.f2925f = c0518f;
            this.f2926g = interfaceC2125q;
            this.f2927h = interfaceC0444d;
        }

        /* renamed from: a */
        public void m1487a() {
            if (getAndIncrement() == 0) {
                int iAddAndGet = 1;
                while (!this.f2925f.isDisposed()) {
                    this.f2926g.subscribe(this);
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2924e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            try {
                InterfaceC0444d<? super Integer, ? super Throwable> interfaceC0444d = this.f2927h;
                int i7 = this.f2928i + 1;
                this.f2928i = i7;
                Integer numValueOf = Integer.valueOf(i7);
                Objects.requireNonNull((C0871b.a) interfaceC0444d);
                if (C0871b.m676a(numValueOf, th)) {
                    m1487a();
                } else {
                    this.f2924e.onError(th);
                }
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                this.f2924e.onError(new C0009a(th, th2));
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f2924e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m325c(this.f2925f, interfaceC2153b);
        }
    }

    public C1254c3(AbstractC2120l<T> abstractC2120l, InterfaceC0444d<? super Integer, ? super Throwable> interfaceC0444d) {
        super((InterfaceC2125q) abstractC2120l);
        this.f2923f = interfaceC0444d;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C0518f c0518f = new C0518f();
        interfaceC2127s.onSubscribe(c0518f);
        new a(interfaceC2127s, this.f2923f, c0518f, this.f2772e).m1487a();
    }
}
