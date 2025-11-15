package p088k4;

import p153s4.C1882e;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableSerialized.java */
/* renamed from: k4.m3 */
/* loaded from: classes.dex */
public final class C1314m3<T> extends AbstractC1238a<T, T> {
    public C1314m3(AbstractC2120l<T> abstractC2120l) {
        super((InterfaceC2125q) abstractC2120l);
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new C1882e(interfaceC2127s));
    }
}
