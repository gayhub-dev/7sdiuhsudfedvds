package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0454n;
import p032d4.C0870a;
import p194y3.InterfaceC2125q;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.p1 */
/* loaded from: classes.dex */
public final class C1330p1<T, U> implements InterfaceC0454n<T, InterfaceC2125q<T>> {

    /* renamed from: e */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<U>> f3545e;

    public C1330p1(InterfaceC0454n<? super T, ? extends InterfaceC2125q<U>> interfaceC0454n) {
        this.f3545e = interfaceC0454n;
    }

    @Override // p014b4.InterfaceC0454n
    public Object apply(Object obj) {
        InterfaceC2125q<U> interfaceC2125qApply = this.f3545e.apply(obj);
        Objects.requireNonNull(interfaceC2125qApply, "The itemDelay returned a null ObservableSource");
        return new C1380x3(interfaceC2125qApply, 1L).map(new C0870a.u(obj)).defaultIfEmpty(obj);
    }
}
