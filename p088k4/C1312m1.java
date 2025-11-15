package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0454n;
import p194y3.InterfaceC2125q;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.m1 */
/* loaded from: classes.dex */
public final class C1312m1<T, U> implements InterfaceC0454n<T, InterfaceC2125q<U>> {

    /* renamed from: e */
    public final InterfaceC0454n<? super T, ? extends Iterable<? extends U>> f3389e;

    public C1312m1(InterfaceC0454n<? super T, ? extends Iterable<? extends U>> interfaceC0454n) {
        this.f3389e = interfaceC0454n;
    }

    @Override // p014b4.InterfaceC0454n
    public Object apply(Object obj) {
        Iterable<? extends U> iterableApply = this.f3389e.apply(obj);
        Objects.requireNonNull(iterableApply, "The mapper returned a null Iterable");
        return new C1252c1(iterableApply);
    }
}
