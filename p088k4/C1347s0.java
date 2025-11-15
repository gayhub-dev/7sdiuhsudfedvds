package p088k4;

import p014b4.InterfaceC0455o;
import p048f4.AbstractC0996a;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableFilter.java */
/* renamed from: k4.s0 */
/* loaded from: classes.dex */
public final class C1347s0<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0455o<? super T> f3684f;

    /* compiled from: ObservableFilter.java */
    /* renamed from: k4.s0$a */
    public static final class a<T> extends AbstractC0996a<T, T> {

        /* renamed from: j */
        public final InterfaceC0455o<? super T> f3685j;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0455o<? super T> interfaceC0455o) {
            super(interfaceC2127s);
            this.f3685j = interfaceC0455o;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            return m1014b(i7);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f1871i != 0) {
                this.f1867e.onNext(null);
                return;
            }
            try {
                if (this.f3685j.test(t6)) {
                    this.f1867e.onNext(t6);
                }
            } catch (Throwable th) {
                m1013a(th);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            T tPoll;
            do {
                tPoll = this.f1869g.poll();
                if (tPoll == null) {
                    break;
                }
            } while (!this.f3685j.test(tPoll));
            return tPoll;
        }
    }

    public C1347s0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0455o<? super T> interfaceC0455o) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3684f = interfaceC0455o;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3684f));
    }
}
