package p088k4;

import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0446f;
import p048f4.C1004i;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableDoOnLifecycle.java */
/* renamed from: k4.n0 */
/* loaded from: classes.dex */
public final class C1317n0<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0446f<? super InterfaceC2153b> f3404f;

    /* renamed from: g */
    public final InterfaceC0441a f3405g;

    public C1317n0(AbstractC2120l<T> abstractC2120l, InterfaceC0446f<? super InterfaceC2153b> interfaceC0446f, InterfaceC0441a interfaceC0441a) {
        super((InterfaceC2125q) abstractC2120l);
        this.f3404f = interfaceC0446f;
        this.f3405g = interfaceC0441a;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new C1004i(interfaceC2127s, this.f3404f, this.f3405g));
    }
}
