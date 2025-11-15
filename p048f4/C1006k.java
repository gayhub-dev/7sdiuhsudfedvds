package p048f4;

import java.util.concurrent.atomic.AtomicReference;
import p005a4.C0009a;
import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0446f;
import p014b4.InterfaceC0455o;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ForEachWhileObserver.java */
/* renamed from: f4.k */
/* loaded from: classes.dex */
public final class C1006k<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b {
    private static final long serialVersionUID = -4403180040475402120L;

    /* renamed from: e */
    public final InterfaceC0455o<? super T> f1889e;

    /* renamed from: f */
    public final InterfaceC0446f<? super Throwable> f1890f;

    /* renamed from: g */
    public final InterfaceC0441a f1891g;

    /* renamed from: h */
    public boolean f1892h;

    public C1006k(InterfaceC0455o<? super T> interfaceC0455o, InterfaceC0446f<? super Throwable> interfaceC0446f, InterfaceC0441a interfaceC0441a) {
        this.f1889e = interfaceC0455o;
        this.f1890f = interfaceC0446f;
        this.f1891g = interfaceC0441a;
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
        if (this.f1892h) {
            return;
        }
        this.f1892h = true;
        try {
            this.f1891g.run();
        } catch (Throwable th) {
            C2074b.m2470J(th);
            C1908a.m2205b(th);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        if (this.f1892h) {
            C1908a.m2205b(th);
            return;
        }
        this.f1892h = true;
        try {
            this.f1890f.accept(th);
        } catch (Throwable th2) {
            C2074b.m2470J(th2);
            C1908a.m2205b(new C0009a(th, th2));
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        if (this.f1892h) {
            return;
        }
        try {
            if (this.f1889e.test(t6)) {
                return;
            }
            EnumC0515c.m323a(this);
            onComplete();
        } catch (Throwable th) {
            C2074b.m2470J(th);
            EnumC0515c.m323a(this);
            onError(th);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        EnumC0515c.m327f(this, interfaceC2153b);
    }
}
