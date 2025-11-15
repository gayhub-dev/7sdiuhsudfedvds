package p088k4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p112n4.C1539n;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableInterval.java */
/* renamed from: k4.z1 */
/* loaded from: classes.dex */
public final class C1389z1 extends AbstractC2120l<Long> {

    /* renamed from: e */
    public final AbstractC2128t f4034e;

    /* renamed from: f */
    public final long f4035f;

    /* renamed from: g */
    public final long f4036g;

    /* renamed from: h */
    public final TimeUnit f4037h;

    /* compiled from: ObservableInterval.java */
    /* renamed from: k4.z1$a */
    public static final class a extends AtomicReference<InterfaceC2153b> implements InterfaceC2153b, Runnable {
        private static final long serialVersionUID = 346773832286157679L;

        /* renamed from: e */
        public final InterfaceC2127s<? super Long> f4038e;

        /* renamed from: f */
        public long f4039f;

        public a(InterfaceC2127s<? super Long> interfaceC2127s) {
            this.f4038e = interfaceC2127s;
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
            if (get() != EnumC0515c.DISPOSED) {
                InterfaceC2127s<? super Long> interfaceC2127s = this.f4038e;
                long j7 = this.f4039f;
                this.f4039f = 1 + j7;
                interfaceC2127s.onNext(Long.valueOf(j7));
            }
        }
    }

    public C1389z1(long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        this.f4035f = j7;
        this.f4036g = j8;
        this.f4037h = timeUnit;
        this.f4034e = abstractC2128t;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Long> interfaceC2127s) {
        a aVar = new a(interfaceC2127s);
        interfaceC2127s.onSubscribe(aVar);
        AbstractC2128t abstractC2128t = this.f4034e;
        if (!(abstractC2128t instanceof C1539n)) {
            EnumC0515c.m327f(aVar, abstractC2128t.schedulePeriodicallyDirect(aVar, this.f4035f, this.f4036g, this.f4037h));
            return;
        }
        AbstractC2128t.c cVarCreateWorker = abstractC2128t.createWorker();
        EnumC0515c.m327f(aVar, cVarCreateWorker);
        cVarCreateWorker.schedulePeriodically(aVar, this.f4035f, this.f4036g, this.f4037h);
    }
}
