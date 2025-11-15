package p048f4;

import java.util.concurrent.CountDownLatch;
import p138q4.C1774f;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: BlockingMultiObserver.java */
/* renamed from: f4.f */
/* loaded from: classes.dex */
public final class C1001f<T> extends CountDownLatch implements InterfaceC2130v<T>, InterfaceC2111c, InterfaceC2117i<T> {

    /* renamed from: e */
    public T f1877e;

    /* renamed from: f */
    public Throwable f1878f;

    /* renamed from: g */
    public InterfaceC2153b f1879g;

    /* renamed from: h */
    public volatile boolean f1880h;

    public C1001f() {
        super(1);
    }

    @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2117i
    /* renamed from: a */
    public void mo1016a(T t6) {
        this.f1877e = t6;
        countDown();
    }

    /* renamed from: b */
    public T m1017b() throws InterruptedException {
        if (getCount() != 0) {
            try {
                await();
            } catch (InterruptedException e7) {
                this.f1880h = true;
                InterfaceC2153b interfaceC2153b = this.f1879g;
                if (interfaceC2153b != null) {
                    interfaceC2153b.dispose();
                }
                throw C1774f.m1961d(e7);
            }
        }
        Throwable th = this.f1878f;
        if (th == null) {
            return this.f1877e;
        }
        throw C1774f.m1961d(th);
    }

    @Override // p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
    public void onComplete() {
        countDown();
    }

    @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
    public void onError(Throwable th) {
        this.f1878f = th;
        countDown();
    }

    @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        this.f1879g = interfaceC2153b;
        if (this.f1880h) {
            interfaceC2153b.dispose();
        }
    }
}
