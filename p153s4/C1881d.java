package p153s4;

import p005a4.C0009a;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: SafeObserver.java */
/* renamed from: s4.d */
/* loaded from: classes.dex */
public final class C1881d<T> implements InterfaceC2127s<T>, InterfaceC2153b {

    /* renamed from: e */
    public final InterfaceC2127s<? super T> f5470e;

    /* renamed from: f */
    public InterfaceC2153b f5471f;

    /* renamed from: g */
    public boolean f5472g;

    public C1881d(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f5470e = interfaceC2127s;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        this.f5471f.dispose();
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return this.f5471f.isDisposed();
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        if (this.f5472g) {
            return;
        }
        this.f5472g = true;
        if (this.f5471f != null) {
            try {
                this.f5470e.onComplete();
                return;
            } catch (Throwable th) {
                C2074b.m2470J(th);
                C1908a.m2205b(th);
                return;
            }
        }
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.f5470e.onSubscribe(EnumC0516d.INSTANCE);
            try {
                this.f5470e.onError(nullPointerException);
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                C1908a.m2205b(new C0009a(nullPointerException, th2));
            }
        } catch (Throwable th3) {
            C2074b.m2470J(th3);
            C1908a.m2205b(new C0009a(nullPointerException, th3));
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        if (this.f5472g) {
            C1908a.m2205b(th);
            return;
        }
        this.f5472g = true;
        if (this.f5471f != null) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            try {
                this.f5470e.onError(th);
                return;
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                C1908a.m2205b(new C0009a(th, th2));
                return;
            }
        }
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.f5470e.onSubscribe(EnumC0516d.INSTANCE);
            try {
                this.f5470e.onError(new C0009a(th, nullPointerException));
            } catch (Throwable th3) {
                C2074b.m2470J(th3);
                C1908a.m2205b(new C0009a(th, nullPointerException, th3));
            }
        } catch (Throwable th4) {
            C2074b.m2470J(th4);
            C1908a.m2205b(new C0009a(th, nullPointerException, th4));
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        if (this.f5472g) {
            return;
        }
        if (this.f5471f == null) {
            this.f5472g = true;
            NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
            try {
                this.f5470e.onSubscribe(EnumC0516d.INSTANCE);
                try {
                    this.f5470e.onError(nullPointerException);
                    return;
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    C1908a.m2205b(new C0009a(nullPointerException, th));
                    return;
                }
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                C1908a.m2205b(new C0009a(nullPointerException, th2));
                return;
            }
        }
        if (t6 == null) {
            NullPointerException nullPointerException2 = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
            try {
                this.f5471f.dispose();
                onError(nullPointerException2);
                return;
            } catch (Throwable th3) {
                C2074b.m2470J(th3);
                onError(new C0009a(nullPointerException2, th3));
                return;
            }
        }
        try {
            this.f5470e.onNext(t6);
        } catch (Throwable th4) {
            C2074b.m2470J(th4);
            try {
                this.f5471f.dispose();
                onError(th4);
            } catch (Throwable th5) {
                C2074b.m2470J(th5);
                onError(new C0009a(th4, th5));
            }
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (EnumC0515c.m328h(this.f5471f, interfaceC2153b)) {
            this.f5471f = interfaceC2153b;
            try {
                this.f5470e.onSubscribe(this);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f5472g = true;
                try {
                    interfaceC2153b.dispose();
                    C1908a.m2205b(th);
                } catch (Throwable th2) {
                    C2074b.m2470J(th2);
                    C1908a.m2205b(new C0009a(th, th2));
                }
            }
        }
    }
}
