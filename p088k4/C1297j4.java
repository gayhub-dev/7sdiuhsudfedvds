package p088k4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTimer.java */
/* renamed from: k4.j4 */
/* loaded from: classes.dex */
public final class C1297j4 extends AbstractC2120l<Long> {

    /* renamed from: e */
    public final AbstractC2128t f3249e;

    /* renamed from: f */
    public final long f3250f;

    /* renamed from: g */
    public final TimeUnit f3251g;

    /* compiled from: ObservableTimer.java */
    /* renamed from: k4.j4$a */
    public static final class a extends AtomicReference<InterfaceC2153b> implements InterfaceC2153b, Runnable {
        private static final long serialVersionUID = -2809475196591179431L;

        /* renamed from: e */
        public final InterfaceC2127s<? super Long> f3252e;

        public a(InterfaceC2127s<? super Long> interfaceC2127s) {
            this.f3252e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get() == EnumC0515c.DISPOSED;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (isDisposed()) {
                return;
            }
            this.f3252e.onNext(0L);
            lazySet(EnumC0516d.INSTANCE);
            this.f3252e.onComplete();
        }
    }

    public C1297j4(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        this.f3250f = j7;
        this.f3251g = timeUnit;
        this.f3249e = abstractC2128t;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Long> interfaceC2127s) {
        a aVar = new a(interfaceC2127s);
        interfaceC2127s.onSubscribe(aVar);
        InterfaceC2153b interfaceC2153bScheduleDirect = this.f3249e.scheduleDirect(aVar, this.f3250f, this.f3251g);
        if (aVar.compareAndSet(null, interfaceC2153bScheduleDirect) || aVar.get() != EnumC0515c.DISPOSED) {
            return;
        }
        interfaceC2153bScheduleDirect.dispose();
    }
}
