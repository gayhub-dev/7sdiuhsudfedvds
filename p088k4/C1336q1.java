package p088k4;

import p014b4.InterfaceC0441a;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.q1 */
/* loaded from: classes.dex */
public final class C1336q1<T> implements InterfaceC0441a {

    /* renamed from: e */
    public final InterfaceC2127s<T> f3585e;

    public C1336q1(InterfaceC2127s<T> interfaceC2127s) {
        this.f3585e = interfaceC2127s;
    }

    @Override // p014b4.InterfaceC0441a
    public void run() {
        this.f3585e.onComplete();
    }
}
