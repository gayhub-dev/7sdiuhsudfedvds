package p088k4;

import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTake.java */
/* renamed from: k4.x3 */
/* loaded from: classes.dex */
public final class C1380x3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f3999f;

    /* compiled from: ObservableTake.java */
    /* renamed from: k4.x3$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f4000e;

        /* renamed from: f */
        public boolean f4001f;

        /* renamed from: g */
        public InterfaceC2153b f4002g;

        /* renamed from: h */
        public long f4003h;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7) {
            this.f4000e = interfaceC2127s;
            this.f4003h = j7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f4002g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4002g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f4001f) {
                return;
            }
            this.f4001f = true;
            this.f4002g.dispose();
            this.f4000e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f4001f) {
                C1908a.m2205b(th);
                return;
            }
            this.f4001f = true;
            this.f4002g.dispose();
            this.f4000e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f4001f) {
                return;
            }
            long j7 = this.f4003h;
            long j8 = j7 - 1;
            this.f4003h = j8;
            if (j7 > 0) {
                boolean z6 = j8 == 0;
                this.f4000e.onNext(t6);
                if (z6) {
                    onComplete();
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f4002g, interfaceC2153b)) {
                this.f4002g = interfaceC2153b;
                if (this.f4003h != 0) {
                    this.f4000e.onSubscribe(this);
                    return;
                }
                this.f4001f = true;
                interfaceC2153b.dispose();
                EnumC0516d.m329a(this.f4000e);
            }
        }
    }

    public C1380x3(InterfaceC2125q<T> interfaceC2125q, long j7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3999f = j7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3999f));
    }
}
