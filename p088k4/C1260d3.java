package p088k4;

import android.support.v7.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicInteger;
import p005a4.C0009a;
import p014b4.InterfaceC0455o;
import p022c4.C0518f;
import p022c4.EnumC0515c;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableRetryPredicate.java */
/* renamed from: k4.d3 */
/* loaded from: classes.dex */
public final class C1260d3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0455o<? super Throwable> f2948f;

    /* renamed from: g */
    public final long f2949g;

    /* compiled from: ObservableRetryPredicate.java */
    /* renamed from: k4.d3$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T> {
        private static final long serialVersionUID = -7098360935104053232L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2950e;

        /* renamed from: f */
        public final C0518f f2951f;

        /* renamed from: g */
        public final InterfaceC2125q<? extends T> f2952g;

        /* renamed from: h */
        public final InterfaceC0455o<? super Throwable> f2953h;

        /* renamed from: i */
        public long f2954i;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7, InterfaceC0455o<? super Throwable> interfaceC0455o, C0518f c0518f, InterfaceC2125q<? extends T> interfaceC2125q) {
            this.f2950e = interfaceC2127s;
            this.f2951f = c0518f;
            this.f2952g = interfaceC2125q;
            this.f2953h = interfaceC0455o;
            this.f2954i = j7;
        }

        /* renamed from: a */
        public void m1489a() {
            if (getAndIncrement() == 0) {
                int iAddAndGet = 1;
                while (!this.f2951f.isDisposed()) {
                    this.f2952g.subscribe(this);
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2950e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            long j7 = this.f2954i;
            if (j7 != RecyclerView.FOREVER_NS) {
                this.f2954i = j7 - 1;
            }
            if (j7 == 0) {
                this.f2950e.onError(th);
                return;
            }
            try {
                if (this.f2953h.test(th)) {
                    m1489a();
                } else {
                    this.f2950e.onError(th);
                }
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                this.f2950e.onError(new C0009a(th, th2));
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f2950e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m325c(this.f2951f, interfaceC2153b);
        }
    }

    public C1260d3(AbstractC2120l<T> abstractC2120l, long j7, InterfaceC0455o<? super Throwable> interfaceC0455o) {
        super((InterfaceC2125q) abstractC2120l);
        this.f2948f = interfaceC0455o;
        this.f2949g = j7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C0518f c0518f = new C0518f();
        interfaceC2127s.onSubscribe(c0518f);
        new a(interfaceC2127s, this.f2949g, this.f2948f, c0518f, this.f2772e).m1489a();
    }
}
