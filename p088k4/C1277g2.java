package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0454n;
import p048f4.AbstractC0996a;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableMap.java */
/* renamed from: k4.g2 */
/* loaded from: classes.dex */
public final class C1277g2<T, U> extends AbstractC1238a<T, U> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends U> f3120f;

    /* compiled from: ObservableMap.java */
    /* renamed from: k4.g2$a */
    public static final class a<T, U> extends AbstractC0996a<T, U> {

        /* renamed from: j */
        public final InterfaceC0454n<? super T, ? extends U> f3121j;

        public a(InterfaceC2127s<? super U> interfaceC2127s, InterfaceC0454n<? super T, ? extends U> interfaceC0454n) {
            super(interfaceC2127s);
            this.f3121j = interfaceC0454n;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            return m1014b(i7);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f1870h) {
                return;
            }
            if (this.f1871i != 0) {
                this.f1867e.onNext(null);
                return;
            }
            try {
                U uApply = this.f3121j.apply(t6);
                Objects.requireNonNull(uApply, "The mapper function returned a null value.");
                this.f1867e.onNext(uApply);
            } catch (Throwable th) {
                m1013a(th);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public U poll() {
            T tPoll = this.f1869g.poll();
            if (tPoll == null) {
                return null;
            }
            U uApply = this.f3121j.apply(tPoll);
            Objects.requireNonNull(uApply, "The mapper function returned a null value.");
            return uApply;
        }
    }

    public C1277g2(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, ? extends U> interfaceC0454n) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3120f = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super U> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3120f));
    }
}
