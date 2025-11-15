package p088k4;

import p048f4.AbstractC0997b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableRange.java */
/* renamed from: k4.s2 */
/* loaded from: classes.dex */
public final class C1349s2 extends AbstractC2120l<Integer> {

    /* renamed from: e */
    public final int f3687e;

    /* renamed from: f */
    public final long f3688f;

    /* compiled from: ObservableRange.java */
    /* renamed from: k4.s2$a */
    public static final class a extends AbstractC0997b<Integer> {
        private static final long serialVersionUID = 396518478098735504L;

        /* renamed from: e */
        public final InterfaceC2127s<? super Integer> f3689e;

        /* renamed from: f */
        public final long f3690f;

        /* renamed from: g */
        public long f3691g;

        /* renamed from: h */
        public boolean f3692h;

        public a(InterfaceC2127s<? super Integer> interfaceC2127s, long j7, long j8) {
            this.f3689e = interfaceC2127s;
            this.f3691g = j7;
            this.f3690f = j8;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            if ((i7 & 1) == 0) {
                return 0;
            }
            this.f3692h = true;
            return 1;
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            this.f3691g = this.f3690f;
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
            return this.f3691g == this.f3690f;
        }

        @Override // p040e4.InterfaceC0955h
        public Object poll() {
            long j7 = this.f3691g;
            if (j7 != this.f3690f) {
                this.f3691g = 1 + j7;
                return Integer.valueOf((int) j7);
            }
            lazySet(1);
            return null;
        }
    }

    public C1349s2(int i7, int i8) {
        this.f3687e = i7;
        this.f3688f = i7 + i8;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Integer> interfaceC2127s) {
        a aVar = new a(interfaceC2127s, this.f3687e, this.f3688f);
        interfaceC2127s.onSubscribe(aVar);
        if (aVar.f3692h) {
            return;
        }
        InterfaceC2127s<? super Integer> interfaceC2127s2 = aVar.f3689e;
        long j7 = aVar.f3690f;
        for (long j8 = aVar.f3691g; j8 != j7 && aVar.get() == 0; j8++) {
            interfaceC2127s2.onNext(Integer.valueOf((int) j8));
        }
        if (aVar.get() == 0) {
            aVar.lazySet(1);
            interfaceC2127s2.onComplete();
        }
    }
}
