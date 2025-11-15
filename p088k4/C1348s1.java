package p088k4;

import p014b4.InterfaceC0446f;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.s1 */
/* loaded from: classes.dex */
public final class C1348s1<T> implements InterfaceC0446f<T> {

    /* renamed from: e */
    public final InterfaceC2127s<T> f3686e;

    public C1348s1(InterfaceC2127s<T> interfaceC2127s) {
        this.f3686e = interfaceC2127s;
    }

    @Override // p014b4.InterfaceC0446f
    public void accept(T t6) {
        this.f3686e.onNext(t6);
    }
}
