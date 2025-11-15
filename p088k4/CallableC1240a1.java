package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import p048f4.C1003h;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableFromCallable.java */
/* renamed from: k4.a1 */
/* loaded from: classes.dex */
public final class CallableC1240a1<T> extends AbstractC2120l<T> implements Callable<T> {

    /* renamed from: e */
    public final Callable<? extends T> f2775e;

    public CallableC1240a1(Callable<? extends T> callable) {
        this.f2775e = callable;
    }

    @Override // java.util.concurrent.Callable
    public T call() throws Exception {
        T tCall = this.f2775e.call();
        Objects.requireNonNull(tCall, "The callable returned a null value");
        return tCall;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C1003h c1003h = new C1003h(interfaceC2127s);
        interfaceC2127s.onSubscribe(c1003h);
        if (c1003h.isDisposed()) {
            return;
        }
        try {
            T tCall = this.f2775e.call();
            Objects.requireNonNull(tCall, "Callable returned null");
            c1003h.m1018b(tCall);
        } catch (Throwable th) {
            C2074b.m2470J(th);
            if (c1003h.isDisposed()) {
                C1908a.m2205b(th);
            } else {
                interfaceC2127s.onError(th);
            }
        }
    }
}
