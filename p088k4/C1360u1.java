package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0454n;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.u1 */
/* loaded from: classes.dex */
public final class C1360u1<T, R> implements InterfaceC0454n<AbstractC2120l<T>, InterfaceC2125q<R>> {

    /* renamed from: e */
    public final InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> f3849e;

    /* renamed from: f */
    public final AbstractC2128t f3850f;

    public C1360u1(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n, AbstractC2128t abstractC2128t) {
        this.f3849e = interfaceC0454n;
        this.f3850f = abstractC2128t;
    }

    @Override // p014b4.InterfaceC0454n
    public Object apply(Object obj) {
        InterfaceC2125q<R> interfaceC2125qApply = this.f3849e.apply((AbstractC2120l) obj);
        Objects.requireNonNull(interfaceC2125qApply, "The selector returned a null ObservableSource");
        return AbstractC2120l.wrap(interfaceC2125qApply).observeOn(this.f3850f);
    }
}
