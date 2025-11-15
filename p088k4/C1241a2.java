package p088k4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p112n4.C1539n;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableIntervalRange.java */
/* renamed from: k4.a2 */
/* loaded from: classes.dex */
public final class C1241a2 extends AbstractC2120l<Long> {

    /* renamed from: e */
    public final AbstractC2128t f2776e;

    /* renamed from: f */
    public final long f2777f;

    /* renamed from: g */
    public final long f2778g;

    /* renamed from: h */
    public final long f2779h;

    /* renamed from: i */
    public final long f2780i;

    /* renamed from: j */
    public final TimeUnit f2781j;

    /* compiled from: ObservableIntervalRange.java */
    /* renamed from: k4.a2$a */
    public static final class a extends AtomicReference<InterfaceC2153b> implements InterfaceC2153b, Runnable {
        private static final long serialVersionUID = 1891866368734007884L;

        /* renamed from: e */
        public final InterfaceC2127s<? super Long> f2782e;

        /* renamed from: f */
        public final long f2783f;

        /* renamed from: g */
        public long f2784g;

        public a(InterfaceC2127s<? super Long> interfaceC2127s, long j7, long j8) {
            this.f2782e = interfaceC2127s;
            this.f2784g = j7;
            this.f2783f = j8;
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
            long j7 = this.f2784g;
            this.f2782e.onNext(Long.valueOf(j7));
            if (j7 != this.f2783f) {
                this.f2784g = j7 + 1;
            } else {
                EnumC0515c.m323a(this);
                this.f2782e.onComplete();
            }
        }
    }

    public C1241a2(long j7, long j8, long j9, long j10, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        this.f2779h = j9;
        this.f2780i = j10;
        this.f2781j = timeUnit;
        this.f2776e = abstractC2128t;
        this.f2777f = j7;
        this.f2778g = j8;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Long> interfaceC2127s) {
        a aVar = new a(interfaceC2127s, this.f2777f, this.f2778g);
        interfaceC2127s.onSubscribe(aVar);
        AbstractC2128t abstractC2128t = this.f2776e;
        if (!(abstractC2128t instanceof C1539n)) {
            EnumC0515c.m327f(aVar, abstractC2128t.schedulePeriodicallyDirect(aVar, this.f2779h, this.f2780i, this.f2781j));
            return;
        }
        AbstractC2128t.c cVarCreateWorker = abstractC2128t.createWorker();
        EnumC0515c.m327f(aVar, cVarCreateWorker);
        cVarCreateWorker.schedulePeriodically(aVar, this.f2779h, this.f2780i, this.f2781j);
    }
}
