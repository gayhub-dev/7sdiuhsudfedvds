package p088k4;

import p014b4.InterfaceC0446f;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.r1 */
/* loaded from: classes.dex */
public final class C1342r1<T> implements InterfaceC0446f<Throwable> {

    /* renamed from: e */
    public final InterfaceC2127s<T> f3628e;

    public C1342r1(InterfaceC2127s<T> interfaceC2127s) {
        this.f3628e = interfaceC2127s;
    }

    @Override // p014b4.InterfaceC0446f
    public void accept(Throwable th) {
        this.f3628e.onError(th);
    }
}
