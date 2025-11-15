package p088k4;

import p014b4.InterfaceC0442b;
import p014b4.InterfaceC0443c;
import p194y3.InterfaceC2113e;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.v1 */
/* loaded from: classes.dex */
public final class C1366v1<T, S> implements InterfaceC0443c<S, InterfaceC2113e<T>, S> {

    /* renamed from: a */
    public final InterfaceC0442b<S, InterfaceC2113e<T>> f3892a;

    public C1366v1(InterfaceC0442b<S, InterfaceC2113e<T>> interfaceC0442b) {
        this.f3892a = interfaceC0442b;
    }

    @Override // p014b4.InterfaceC0443c
    public Object apply(Object obj, Object obj2) {
        this.f3892a.mo152a(obj, (InterfaceC2113e) obj2);
        return obj;
    }
}
