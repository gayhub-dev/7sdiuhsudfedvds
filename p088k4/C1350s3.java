package p088k4;

import p022c4.C0513a;
import p022c4.EnumC0515c;
import p153s4.C1882e;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableSkipUntil.java */
/* renamed from: k4.s3 */
/* loaded from: classes.dex */
public final class C1350s3<T, U> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC2125q<U> f3693f;

    /* compiled from: ObservableSkipUntil.java */
    /* renamed from: k4.s3$a */
    public final class a implements InterfaceC2127s<U> {

        /* renamed from: e */
        public final C0513a f3694e;

        /* renamed from: f */
        public final b<T> f3695f;

        /* renamed from: g */
        public final C1882e<T> f3696g;

        /* renamed from: h */
        public InterfaceC2153b f3697h;

        public a(C1350s3 c1350s3, C0513a c0513a, b<T> bVar, C1882e<T> c1882e) {
            this.f3694e = c0513a;
            this.f3695f = bVar;
            this.f3696g = c1882e;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3695f.f3701h = true;
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3694e.dispose();
            this.f3696g.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(U u6) {
            this.f3697h.dispose();
            this.f3695f.f3701h = true;
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3697h, interfaceC2153b)) {
                this.f3697h = interfaceC2153b;
                this.f3694e.m321a(1, interfaceC2153b);
            }
        }
    }

    /* compiled from: ObservableSkipUntil.java */
    /* renamed from: k4.s3$b */
    public static final class b<T> implements InterfaceC2127s<T> {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3698e;

        /* renamed from: f */
        public final C0513a f3699f;

        /* renamed from: g */
        public InterfaceC2153b f3700g;

        /* renamed from: h */
        public volatile boolean f3701h;

        /* renamed from: i */
        public boolean f3702i;

        public b(InterfaceC2127s<? super T> interfaceC2127s, C0513a c0513a) {
            this.f3698e = interfaceC2127s;
            this.f3699f = c0513a;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3699f.dispose();
            this.f3698e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3699f.dispose();
            this.f3698e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3702i) {
                this.f3698e.onNext(t6);
            } else if (this.f3701h) {
                this.f3702i = true;
                this.f3698e.onNext(t6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3700g, interfaceC2153b)) {
                this.f3700g = interfaceC2153b;
                this.f3699f.m321a(0, interfaceC2153b);
            }
        }
    }

    public C1350s3(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<U> interfaceC2125q2) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3693f = interfaceC2125q2;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C1882e c1882e = new C1882e(interfaceC2127s);
        C0513a c0513a = new C0513a(2);
        c1882e.onSubscribe(c0513a);
        b bVar = new b(c1882e, c0513a);
        this.f3693f.subscribe(new a(this, c0513a, bVar, c1882e));
        this.f2772e.subscribe(bVar);
    }
}
