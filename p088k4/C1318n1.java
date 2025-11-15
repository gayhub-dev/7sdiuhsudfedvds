package p088k4;

import p014b4.InterfaceC0443c;
import p014b4.InterfaceC0454n;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.n1 */
/* loaded from: classes.dex */
public final class C1318n1<U, R, T> implements InterfaceC0454n<U, R> {

    /* renamed from: e */
    public final InterfaceC0443c<? super T, ? super U, ? extends R> f3406e;

    /* renamed from: f */
    public final T f3407f;

    public C1318n1(InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c, T t6) {
        this.f3406e = interfaceC0443c;
        this.f3407f = t6;
    }

    @Override // p014b4.InterfaceC0454n
    public R apply(U u6) {
        return this.f3406e.apply(this.f3407f, u6);
    }
}
