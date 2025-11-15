package p064h4;

import android.support.v7.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicLong;
import p005a4.C0010b;
import p074i6.InterfaceC1168b;
import p074i6.InterfaceC1169c;
import p130p4.EnumC1740c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2114f;
import p194y3.InterfaceC2115g;

/* compiled from: FlowableOnBackpressureError.java */
/* renamed from: h4.f */
/* loaded from: classes.dex */
public final class C1091f<T> extends AbstractC1086a<T, T> {

    /* compiled from: FlowableOnBackpressureError.java */
    /* renamed from: h4.f$a */
    public static final class a<T> extends AtomicLong implements InterfaceC2115g<T>, InterfaceC1169c {
        private static final long serialVersionUID = -3176480756392482682L;

        /* renamed from: e */
        public final InterfaceC1168b<? super T> f2231e;

        /* renamed from: f */
        public InterfaceC1169c f2232f;

        /* renamed from: g */
        public boolean f2233g;

        public a(InterfaceC1168b<? super T> interfaceC1168b) {
            this.f2231e = interfaceC1168b;
        }

        @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
        /* renamed from: b */
        public void mo1177b(InterfaceC1169c interfaceC1169c) {
            if (EnumC1740c.m1890b(this.f2232f, interfaceC1169c)) {
                this.f2232f = interfaceC1169c;
                this.f2231e.mo1177b(this);
                interfaceC1169c.request(RecyclerView.FOREVER_NS);
            }
        }

        @Override // p074i6.InterfaceC1169c
        public void cancel() {
            this.f2232f.cancel();
        }

        @Override // p074i6.InterfaceC1168b
        public void onComplete() {
            if (this.f2233g) {
                return;
            }
            this.f2233g = true;
            this.f2231e.onComplete();
        }

        @Override // p074i6.InterfaceC1168b
        public void onError(Throwable th) {
            if (this.f2233g) {
                C1908a.m2205b(th);
            } else {
                this.f2233g = true;
                this.f2231e.onError(th);
            }
        }

        @Override // p074i6.InterfaceC1168b
        public void onNext(T t6) {
            if (this.f2233g) {
                return;
            }
            if (get() == 0) {
                onError(new C0010b("could not emit value due to lack of requests"));
            } else {
                this.f2231e.onNext(t6);
                C2074b.m2503z(this, 1L);
            }
        }

        @Override // p074i6.InterfaceC1169c
        public void request(long j7) {
            if (EnumC1740c.m1889a(j7)) {
                C2074b.m2478a(this, j7);
            }
        }
    }

    public C1091f(AbstractC2114f<T> abstractC2114f) {
        super(abstractC2114f);
    }

    @Override // p194y3.AbstractC2114f
    /* renamed from: d */
    public void mo1175d(InterfaceC1168b<? super T> interfaceC1168b) {
        this.f2206f.m2554c(new a(interfaceC1168b));
    }
}
