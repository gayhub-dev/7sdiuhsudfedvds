package p064h4;

import android.support.v7.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicLong;
import p014b4.InterfaceC0446f;
import p074i6.InterfaceC1168b;
import p074i6.InterfaceC1169c;
import p130p4.EnumC1740c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2114f;
import p194y3.InterfaceC2115g;

/* compiled from: FlowableOnBackpressureDrop.java */
/* renamed from: h4.e */
/* loaded from: classes.dex */
public final class C1090e<T> extends AbstractC1086a<T, T> implements InterfaceC0446f<T> {

    /* renamed from: g */
    public final InterfaceC0446f<? super T> f2226g;

    /* compiled from: FlowableOnBackpressureDrop.java */
    /* renamed from: h4.e$a */
    public static final class a<T> extends AtomicLong implements InterfaceC2115g<T>, InterfaceC1169c {
        private static final long serialVersionUID = -6246093802440953054L;

        /* renamed from: e */
        public final InterfaceC1168b<? super T> f2227e;

        /* renamed from: f */
        public final InterfaceC0446f<? super T> f2228f;

        /* renamed from: g */
        public InterfaceC1169c f2229g;

        /* renamed from: h */
        public boolean f2230h;

        public a(InterfaceC1168b<? super T> interfaceC1168b, InterfaceC0446f<? super T> interfaceC0446f) {
            this.f2227e = interfaceC1168b;
            this.f2228f = interfaceC0446f;
        }

        @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
        /* renamed from: b */
        public void mo1177b(InterfaceC1169c interfaceC1169c) {
            if (EnumC1740c.m1890b(this.f2229g, interfaceC1169c)) {
                this.f2229g = interfaceC1169c;
                this.f2227e.mo1177b(this);
                interfaceC1169c.request(RecyclerView.FOREVER_NS);
            }
        }

        @Override // p074i6.InterfaceC1169c
        public void cancel() {
            this.f2229g.cancel();
        }

        @Override // p074i6.InterfaceC1168b
        public void onComplete() {
            if (this.f2230h) {
                return;
            }
            this.f2230h = true;
            this.f2227e.onComplete();
        }

        @Override // p074i6.InterfaceC1168b
        public void onError(Throwable th) {
            if (this.f2230h) {
                C1908a.m2205b(th);
            } else {
                this.f2230h = true;
                this.f2227e.onError(th);
            }
        }

        @Override // p074i6.InterfaceC1168b
        public void onNext(T t6) {
            if (this.f2230h) {
                return;
            }
            if (get() != 0) {
                this.f2227e.onNext(t6);
                C2074b.m2503z(this, 1L);
                return;
            }
            try {
                this.f2228f.accept(t6);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f2229g.cancel();
                onError(th);
            }
        }

        @Override // p074i6.InterfaceC1169c
        public void request(long j7) {
            if (EnumC1740c.m1889a(j7)) {
                C2074b.m2478a(this, j7);
            }
        }
    }

    public C1090e(AbstractC2114f<T> abstractC2114f) {
        super(abstractC2114f);
        this.f2226g = this;
    }

    @Override // p014b4.InterfaceC0446f
    public void accept(T t6) {
    }

    @Override // p194y3.AbstractC2114f
    /* renamed from: d */
    public void mo1175d(InterfaceC1168b<? super T> interfaceC1168b) {
        this.f2206f.m2554c(new a(interfaceC1168b, this.f2226g));
    }
}
