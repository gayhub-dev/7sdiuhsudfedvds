package p088k4;

import android.support.v7.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicInteger;
import p022c4.C0518f;
import p022c4.EnumC0515c;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableRepeat.java */
/* renamed from: k4.y2 */
/* loaded from: classes.dex */
public final class C1385y2<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f4014f;

    /* compiled from: ObservableRepeat.java */
    /* renamed from: k4.y2$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2127s<T> {
        private static final long serialVersionUID = -7098360935104053232L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f4015e;

        /* renamed from: f */
        public final C0518f f4016f;

        /* renamed from: g */
        public final InterfaceC2125q<? extends T> f4017g;

        /* renamed from: h */
        public long f4018h;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7, C0518f c0518f, InterfaceC2125q<? extends T> interfaceC2125q) {
            this.f4015e = interfaceC2127s;
            this.f4016f = c0518f;
            this.f4017g = interfaceC2125q;
            this.f4018h = j7;
        }

        /* renamed from: a */
        public void m1561a() {
            if (getAndIncrement() == 0) {
                int iAddAndGet = 1;
                while (!this.f4016f.isDisposed()) {
                    this.f4017g.subscribe(this);
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            long j7 = this.f4018h;
            if (j7 != RecyclerView.FOREVER_NS) {
                this.f4018h = j7 - 1;
            }
            if (j7 != 0) {
                m1561a();
            } else {
                this.f4015e.onComplete();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f4015e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f4015e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m325c(this.f4016f, interfaceC2153b);
        }
    }

    public C1385y2(AbstractC2120l<T> abstractC2120l, long j7) {
        super((InterfaceC2125q) abstractC2120l);
        this.f4014f = j7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C0518f c0518f = new C0518f();
        interfaceC2127s.onSubscribe(c0518f);
        long j7 = this.f4014f;
        long j8 = RecyclerView.FOREVER_NS;
        if (j7 != RecyclerView.FOREVER_NS) {
            j8 = j7 - 1;
        }
        new a(interfaceC2127s, j8, c0518f, this.f2772e).m1561a();
    }
}
