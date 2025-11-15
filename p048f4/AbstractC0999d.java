package p048f4;

import java.util.concurrent.CountDownLatch;
import p138q4.C1774f;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: BlockingBaseObserver.java */
/* renamed from: f4.d */
/* loaded from: classes.dex */
public abstract class AbstractC0999d<T> extends CountDownLatch implements InterfaceC2127s<T>, InterfaceC2153b {

    /* renamed from: e */
    public T f1872e;

    /* renamed from: f */
    public Throwable f1873f;

    /* renamed from: g */
    public InterfaceC2153b f1874g;

    /* renamed from: h */
    public volatile boolean f1875h;

    public AbstractC0999d() {
        super(1);
    }

    /* renamed from: a */
    public final T m1015a() {
        if (getCount() != 0) {
            try {
                await();
            } catch (InterruptedException e7) {
                dispose();
                throw C1774f.m1961d(e7);
            }
        }
        Throwable th = this.f1873f;
        if (th == null) {
            return this.f1872e;
        }
        throw C1774f.m1961d(th);
    }

    @Override // p201z3.InterfaceC2153b
    public final void dispose() {
        this.f1875h = true;
        InterfaceC2153b interfaceC2153b = this.f1874g;
        if (interfaceC2153b != null) {
            interfaceC2153b.dispose();
        }
    }

    @Override // p201z3.InterfaceC2153b
    public final boolean isDisposed() {
        return this.f1875h;
    }

    @Override // p194y3.InterfaceC2127s
    public final void onComplete() {
        countDown();
    }

    @Override // p194y3.InterfaceC2127s
    public final void onSubscribe(InterfaceC2153b interfaceC2153b) {
        this.f1874g = interfaceC2153b;
        if (this.f1875h) {
            interfaceC2153b.dispose();
        }
    }
}
