package p088k4;

import p014b4.InterfaceC0446f;
import p048f4.AbstractC0996a;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableDoAfterNext.java */
/* renamed from: k4.k0 */
/* loaded from: classes.dex */
public final class C1299k0<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0446f<? super T> f3269f;

    /* compiled from: ObservableDoAfterNext.java */
    /* renamed from: k4.k0$a */
    public static final class a<T> extends AbstractC0996a<T, T> {

        /* renamed from: j */
        public final InterfaceC0446f<? super T> f3270j;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0446f<? super T> interfaceC0446f) {
            super(interfaceC2127s);
            this.f3270j = interfaceC0446f;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            return m1014b(i7);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f1867e.onNext(t6);
            if (this.f1871i == 0) {
                try {
                    this.f3270j.accept(t6);
                } catch (Throwable th) {
                    m1013a(th);
                }
            }
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            T tPoll = this.f1869g.poll();
            if (tPoll != null) {
                this.f3270j.accept(tPoll);
            }
            return tPoll;
        }
    }

    public C1299k0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0446f<? super T> interfaceC0446f) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3269f = interfaceC0446f;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3269f));
    }
}
