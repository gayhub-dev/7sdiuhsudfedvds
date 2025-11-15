package p088k4;

import p005a4.C0009a;
import p014b4.InterfaceC0454n;
import p022c4.C0518f;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableOnErrorNext.java */
/* renamed from: k4.o2 */
/* loaded from: classes.dex */
public final class C1325o2<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends T>> f3484f;

    /* renamed from: g */
    public final boolean f3485g;

    /* compiled from: ObservableOnErrorNext.java */
    /* renamed from: k4.o2$a */
    public static final class a<T> implements InterfaceC2127s<T> {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3486e;

        /* renamed from: f */
        public final InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends T>> f3487f;

        /* renamed from: g */
        public final boolean f3488g;

        /* renamed from: h */
        public final C0518f f3489h = new C0518f();

        /* renamed from: i */
        public boolean f3490i;

        /* renamed from: j */
        public boolean f3491j;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends T>> interfaceC0454n, boolean z6) {
            this.f3486e = interfaceC2127s;
            this.f3487f = interfaceC0454n;
            this.f3488g = z6;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3491j) {
                return;
            }
            this.f3491j = true;
            this.f3490i = true;
            this.f3486e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3490i) {
                if (this.f3491j) {
                    C1908a.m2205b(th);
                    return;
                } else {
                    this.f3486e.onError(th);
                    return;
                }
            }
            this.f3490i = true;
            if (this.f3488g && !(th instanceof Exception)) {
                this.f3486e.onError(th);
                return;
            }
            try {
                InterfaceC2125q<? extends T> interfaceC2125qApply = this.f3487f.apply(th);
                if (interfaceC2125qApply != null) {
                    interfaceC2125qApply.subscribe(this);
                    return;
                }
                NullPointerException nullPointerException = new NullPointerException("Observable is null");
                nullPointerException.initCause(th);
                this.f3486e.onError(nullPointerException);
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                this.f3486e.onError(new C0009a(th, th2));
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3491j) {
                return;
            }
            this.f3486e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m325c(this.f3489h, interfaceC2153b);
        }
    }

    public C1325o2(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends T>> interfaceC0454n, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3484f = interfaceC0454n;
        this.f3485g = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        a aVar = new a(interfaceC2127s, this.f3484f, this.f3485g);
        interfaceC2127s.onSubscribe(aVar.f3489h);
        this.f2772e.subscribe(aVar);
    }
}
