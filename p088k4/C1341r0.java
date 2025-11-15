package p088k4;

import p022c4.EnumC0516d;
import p040e4.InterfaceCallableC0953f;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableEmpty.java */
/* renamed from: k4.r0 */
/* loaded from: classes.dex */
public final class C1341r0 extends AbstractC2120l<Object> implements InterfaceCallableC0953f<Object> {

    /* renamed from: e */
    public static final AbstractC2120l<Object> f3627e = new C1341r0();

    @Override // p040e4.InterfaceCallableC0953f, java.util.concurrent.Callable
    public Object call() {
        return null;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Object> interfaceC2127s) {
        interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
        interfaceC2127s.onComplete();
    }
}
