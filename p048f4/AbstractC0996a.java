package p048f4;

import p022c4.EnumC0515c;
import p040e4.InterfaceC0950c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: BasicFuseableObserver.java */
/* renamed from: f4.a */
/* loaded from: classes.dex */
public abstract class AbstractC0996a<T, R> implements InterfaceC2127s<T>, InterfaceC0950c<R> {

    /* renamed from: e */
    public final InterfaceC2127s<? super R> f1867e;

    /* renamed from: f */
    public InterfaceC2153b f1868f;

    /* renamed from: g */
    public InterfaceC0950c<T> f1869g;

    /* renamed from: h */
    public boolean f1870h;

    /* renamed from: i */
    public int f1871i;

    public AbstractC0996a(InterfaceC2127s<? super R> interfaceC2127s) {
        this.f1867e = interfaceC2127s;
    }

    /* renamed from: a */
    public final void m1013a(Throwable th) {
        C2074b.m2470J(th);
        this.f1868f.dispose();
        onError(th);
    }

    /* renamed from: b */
    public final int m1014b(int i7) {
        InterfaceC0950c<T> interfaceC0950c = this.f1869g;
        if (interfaceC0950c == null || (i7 & 4) != 0) {
            return 0;
        }
        int iMo331c = interfaceC0950c.mo331c(i7);
        if (iMo331c != 0) {
            this.f1871i = iMo331c;
        }
        return iMo331c;
    }

    @Override // p040e4.InterfaceC0955h
    public void clear() {
        this.f1869g.clear();
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        this.f1868f.dispose();
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return this.f1868f.isDisposed();
    }

    @Override // p040e4.InterfaceC0955h
    public boolean isEmpty() {
        return this.f1869g.isEmpty();
    }

    @Override // p040e4.InterfaceC0955h
    public final boolean offer(R r6) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        if (this.f1870h) {
            return;
        }
        this.f1870h = true;
        this.f1867e.onComplete();
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        if (this.f1870h) {
            C1908a.m2205b(th);
        } else {
            this.f1870h = true;
            this.f1867e.onError(th);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public final void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (EnumC0515c.m328h(this.f1868f, interfaceC2153b)) {
            this.f1868f = interfaceC2153b;
            if (interfaceC2153b instanceof InterfaceC0950c) {
                this.f1869g = (InterfaceC0950c) interfaceC2153b;
            }
            this.f1867e.onSubscribe(this);
        }
    }
}
