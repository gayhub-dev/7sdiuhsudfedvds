package p088k4;

import android.support.v7.widget.RecyclerView;
import p074i6.InterfaceC1167a;
import p074i6.InterfaceC1169c;
import p130p4.EnumC1740c;
import p194y3.AbstractC2114f;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2115g;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableFromPublisher.java */
/* renamed from: k4.d1 */
/* loaded from: classes.dex */
public final class C1258d1<T> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final InterfaceC1167a<? extends T> f2941e;

    /* compiled from: ObservableFromPublisher.java */
    /* renamed from: k4.d1$a */
    public static final class a<T> implements InterfaceC2115g<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2942e;

        /* renamed from: f */
        public InterfaceC1169c f2943f;

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f2942e = interfaceC2127s;
        }

        @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
        /* renamed from: b */
        public void mo1177b(InterfaceC1169c interfaceC1169c) {
            if (EnumC1740c.m1890b(this.f2943f, interfaceC1169c)) {
                this.f2943f = interfaceC1169c;
                this.f2942e.onSubscribe(this);
                interfaceC1169c.request(RecyclerView.FOREVER_NS);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2943f.cancel();
            this.f2943f = EnumC1740c.CANCELLED;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2943f == EnumC1740c.CANCELLED;
        }

        @Override // p074i6.InterfaceC1168b
        public void onComplete() {
            this.f2942e.onComplete();
        }

        @Override // p074i6.InterfaceC1168b
        public void onError(Throwable th) {
            this.f2942e.onError(th);
        }

        @Override // p074i6.InterfaceC1168b
        public void onNext(T t6) {
            this.f2942e.onNext(t6);
        }
    }

    public C1258d1(InterfaceC1167a<? extends T> interfaceC1167a) {
        this.f2941e = interfaceC1167a;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        ((AbstractC2114f) this.f2941e).m2553a(new a(interfaceC2127s));
    }
}
