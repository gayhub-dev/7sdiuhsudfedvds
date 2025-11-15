package p088k4;

import p048f4.AbstractC0997b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableRangeLong.java */
/* renamed from: k4.t2 */
/* loaded from: classes.dex */
public final class C1355t2 extends AbstractC2120l<Long> {

    /* renamed from: e */
    public final long f3803e;

    /* renamed from: f */
    public final long f3804f;

    /* compiled from: ObservableRangeLong.java */
    /* renamed from: k4.t2$a */
    public static final class a extends AbstractC0997b<Long> {
        private static final long serialVersionUID = 396518478098735504L;

        /* renamed from: e */
        public final InterfaceC2127s<? super Long> f3805e;

        /* renamed from: f */
        public final long f3806f;

        /* renamed from: g */
        public long f3807g;

        /* renamed from: h */
        public boolean f3808h;

        public a(InterfaceC2127s<? super Long> interfaceC2127s, long j7, long j8) {
            this.f3805e = interfaceC2127s;
            this.f3807g = j7;
            this.f3806f = j8;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            if ((i7 & 1) == 0) {
                return 0;
            }
            this.f3808h = true;
            return 1;
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            this.f3807g = this.f3806f;
            lazySet(1);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            set(1);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get() != 0;
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return this.f3807g == this.f3806f;
        }

        @Override // p040e4.InterfaceC0955h
        public Object poll() {
            long j7 = this.f3807g;
            if (j7 != this.f3806f) {
                this.f3807g = 1 + j7;
                return Long.valueOf(j7);
            }
            lazySet(1);
            return null;
        }
    }

    public C1355t2(long j7, long j8) {
        this.f3803e = j7;
        this.f3804f = j8;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Long> interfaceC2127s) {
        long j7 = this.f3803e;
        a aVar = new a(interfaceC2127s, j7, j7 + this.f3804f);
        interfaceC2127s.onSubscribe(aVar);
        if (aVar.f3808h) {
            return;
        }
        InterfaceC2127s<? super Long> interfaceC2127s2 = aVar.f3805e;
        long j8 = aVar.f3806f;
        for (long j9 = aVar.f3807g; j9 != j8 && aVar.get() == 0; j9++) {
            interfaceC2127s2.onNext(Long.valueOf(j9));
        }
        if (aVar.get() == 0) {
            aVar.lazySet(1);
            interfaceC2127s2.onComplete();
        }
    }
}
