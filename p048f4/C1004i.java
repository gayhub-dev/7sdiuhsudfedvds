package p048f4;

import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0446f;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: DisposableLambdaObserver.java */
/* renamed from: f4.i */
/* loaded from: classes.dex */
public final class C1004i<T> implements InterfaceC2127s<T>, InterfaceC2153b {

    /* renamed from: e */
    public final InterfaceC2127s<? super T> f1885e;

    /* renamed from: f */
    public final InterfaceC0446f<? super InterfaceC2153b> f1886f;

    /* renamed from: g */
    public final InterfaceC0441a f1887g;

    /* renamed from: h */
    public InterfaceC2153b f1888h;

    public C1004i(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0446f<? super InterfaceC2153b> interfaceC0446f, InterfaceC0441a interfaceC0441a) {
        this.f1885e = interfaceC2127s;
        this.f1886f = interfaceC0446f;
        this.f1887g = interfaceC0441a;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        InterfaceC2153b interfaceC2153b = this.f1888h;
        EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
        if (interfaceC2153b != enumC0515c) {
            this.f1888h = enumC0515c;
            try {
                this.f1887g.run();
            } catch (Throwable th) {
                C2074b.m2470J(th);
                C1908a.m2205b(th);
            }
            interfaceC2153b.dispose();
        }
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return this.f1888h.isDisposed();
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        InterfaceC2153b interfaceC2153b = this.f1888h;
        EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
        if (interfaceC2153b != enumC0515c) {
            this.f1888h = enumC0515c;
            this.f1885e.onComplete();
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        InterfaceC2153b interfaceC2153b = this.f1888h;
        EnumC0515c enumC0515c = EnumC0515c.DISPOSED;
        if (interfaceC2153b == enumC0515c) {
            C1908a.m2205b(th);
        } else {
            this.f1888h = enumC0515c;
            this.f1885e.onError(th);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        this.f1885e.onNext(t6);
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        try {
            this.f1886f.accept(interfaceC2153b);
            if (EnumC0515c.m328h(this.f1888h, interfaceC2153b)) {
                this.f1888h = interfaceC2153b;
                this.f1885e.onSubscribe(this);
            }
        } catch (Throwable th) {
            C2074b.m2470J(th);
            interfaceC2153b.dispose();
            this.f1888h = EnumC0515c.DISPOSED;
            EnumC0516d.m330b(th, this.f1885e);
        }
    }
}
