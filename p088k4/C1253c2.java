package p088k4;

import p040e4.InterfaceCallableC0953f;
import p088k4.C1284h3;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableJust.java */
/* renamed from: k4.c2 */
/* loaded from: classes.dex */
public final class C1253c2<T> extends AbstractC2120l<T> implements InterfaceCallableC0953f<T> {

    /* renamed from: e */
    public final T f2922e;

    public C1253c2(T t6) {
        this.f2922e = t6;
    }

    @Override // p040e4.InterfaceCallableC0953f, java.util.concurrent.Callable
    public T call() {
        return this.f2922e;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C1284h3.a aVar = new C1284h3.a(interfaceC2127s, this.f2922e);
        interfaceC2127s.onSubscribe(aVar);
        aVar.run();
    }
}
