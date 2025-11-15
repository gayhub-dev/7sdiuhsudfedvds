package p048f4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0950c;
import p040e4.InterfaceC0955h;
import p088k4.C1358u;
import p104m4.C1488b;
import p104m4.C1489c;
import p138q4.C1774f;
import p160t4.C1908a;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: InnerQueuedObserver.java */
/* renamed from: f4.m */
/* loaded from: classes.dex */
public final class C1008m<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b {
    private static final long serialVersionUID = -5417183359794346637L;

    /* renamed from: e */
    public final InterfaceC1009n<T> f1896e;

    /* renamed from: f */
    public final int f1897f;

    /* renamed from: g */
    public InterfaceC0955h<T> f1898g;

    /* renamed from: h */
    public volatile boolean f1899h;

    /* renamed from: i */
    public int f1900i;

    public C1008m(InterfaceC1009n<T> interfaceC1009n, int i7) {
        this.f1896e = interfaceC1009n;
        this.f1897f = i7;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        EnumC0515c.m323a(this);
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return EnumC0515c.m324b(get());
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        C1358u.a aVar = (C1358u.a) this.f1896e;
        Objects.requireNonNull(aVar);
        this.f1899h = true;
        aVar.m1549b();
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        C1358u.a aVar = (C1358u.a) this.f1896e;
        if (!C1774f.m1958a(aVar.f3830j, th)) {
            C1908a.m2205b(th);
            return;
        }
        if (aVar.f3829i == 1) {
            aVar.f3833m.dispose();
        }
        this.f1899h = true;
        aVar.m1549b();
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        if (this.f1900i != 0) {
            ((C1358u.a) this.f1896e).m1549b();
            return;
        }
        C1358u.a aVar = (C1358u.a) this.f1896e;
        Objects.requireNonNull(aVar);
        this.f1898g.offer(t6);
        aVar.m1549b();
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (EnumC0515c.m327f(this, interfaceC2153b)) {
            if (interfaceC2153b instanceof InterfaceC0950c) {
                InterfaceC0950c interfaceC0950c = (InterfaceC0950c) interfaceC2153b;
                int iMo331c = interfaceC0950c.mo331c(3);
                if (iMo331c == 1) {
                    this.f1900i = iMo331c;
                    this.f1898g = interfaceC0950c;
                    this.f1899h = true;
                    C1358u.a aVar = (C1358u.a) this.f1896e;
                    Objects.requireNonNull(aVar);
                    this.f1899h = true;
                    aVar.m1549b();
                    return;
                }
                if (iMo331c == 2) {
                    this.f1900i = iMo331c;
                    this.f1898g = interfaceC0950c;
                    return;
                }
            }
            int i7 = -this.f1897f;
            this.f1898g = i7 < 0 ? new C1489c<>(-i7) : new C1488b<>(i7);
        }
    }
}
