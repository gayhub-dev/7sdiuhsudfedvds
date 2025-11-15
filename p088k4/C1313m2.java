package p088k4;

import p022c4.EnumC0516d;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableNever.java */
/* renamed from: k4.m2 */
/* loaded from: classes.dex */
public final class C1313m2 extends AbstractC2120l<Object> {

    /* renamed from: e */
    public static final AbstractC2120l<Object> f3390e = new C1313m2();

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Object> interfaceC2127s) {
        interfaceC2127s.onSubscribe(EnumC0516d.NEVER);
    }
}
