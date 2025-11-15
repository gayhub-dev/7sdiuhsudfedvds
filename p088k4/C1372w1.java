package p088k4;

import p014b4.InterfaceC0443c;
import p014b4.InterfaceC0446f;
import p194y3.InterfaceC2113e;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.w1 */
/* loaded from: classes.dex */
public final class C1372w1<T, S> implements InterfaceC0443c<S, InterfaceC2113e<T>, S> {

    /* renamed from: a */
    public final InterfaceC0446f<InterfaceC2113e<T>> f3937a;

    public C1372w1(InterfaceC0446f<InterfaceC2113e<T>> interfaceC0446f) {
        this.f3937a = interfaceC0446f;
    }

    @Override // p014b4.InterfaceC0443c
    public Object apply(Object obj, Object obj2) {
        this.f3937a.accept((InterfaceC2113e) obj2);
        return obj;
    }
}
