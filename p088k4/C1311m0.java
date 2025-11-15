package p088k4;

import p005a4.C0009a;
import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0446f;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableDoOnEach.java */
/* renamed from: k4.m0 */
/* loaded from: classes.dex */
public final class C1311m0<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0446f<? super T> f3378f;

    /* renamed from: g */
    public final InterfaceC0446f<? super Throwable> f3379g;

    /* renamed from: h */
    public final InterfaceC0441a f3380h;

    /* renamed from: i */
    public final InterfaceC0441a f3381i;

    /* compiled from: ObservableDoOnEach.java */
    /* renamed from: k4.m0$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3382e;

        /* renamed from: f */
        public final InterfaceC0446f<? super T> f3383f;

        /* renamed from: g */
        public final InterfaceC0446f<? super Throwable> f3384g;

        /* renamed from: h */
        public final InterfaceC0441a f3385h;

        /* renamed from: i */
        public final InterfaceC0441a f3386i;

        /* renamed from: j */
        public InterfaceC2153b f3387j;

        /* renamed from: k */
        public boolean f3388k;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2, InterfaceC0441a interfaceC0441a, InterfaceC0441a interfaceC0441a2) {
            this.f3382e = interfaceC2127s;
            this.f3383f = interfaceC0446f;
            this.f3384g = interfaceC0446f2;
            this.f3385h = interfaceC0441a;
            this.f3386i = interfaceC0441a2;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3387j.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3387j.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3388k) {
                return;
            }
            try {
                this.f3385h.run();
                this.f3388k = true;
                this.f3382e.onComplete();
                try {
                    this.f3386i.run();
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    C1908a.m2205b(th);
                }
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                onError(th2);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3388k) {
                C1908a.m2205b(th);
                return;
            }
            this.f3388k = true;
            try {
                this.f3384g.accept(th);
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                th = new C0009a(th, th2);
            }
            this.f3382e.onError(th);
            try {
                this.f3386i.run();
            } catch (Throwable th3) {
                C2074b.m2470J(th3);
                C1908a.m2205b(th3);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3388k) {
                return;
            }
            try {
                this.f3383f.accept(t6);
                this.f3382e.onNext(t6);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3387j.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3387j, interfaceC2153b)) {
                this.f3387j = interfaceC2153b;
                this.f3382e.onSubscribe(this);
            }
        }
    }

    public C1311m0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2, InterfaceC0441a interfaceC0441a, InterfaceC0441a interfaceC0441a2) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3378f = interfaceC0446f;
        this.f3379g = interfaceC0446f2;
        this.f3380h = interfaceC0441a;
        this.f3381i = interfaceC0441a2;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3378f, this.f3379g, this.f3380h, this.f3381i));
    }
}
