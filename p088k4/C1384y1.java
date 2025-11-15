package p088k4;

import java.util.List;
import p014b4.InterfaceC0454n;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.y1 */
/* loaded from: classes.dex */
public final class C1384y1<T, R> implements InterfaceC0454n<List<InterfaceC2125q<? extends T>>, InterfaceC2125q<? extends R>> {

    /* renamed from: e */
    public final InterfaceC0454n<? super Object[], ? extends R> f4013e;

    public C1384y1(InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n) {
        this.f4013e = interfaceC0454n;
    }

    @Override // p014b4.InterfaceC0454n
    public Object apply(Object obj) {
        return AbstractC2120l.zipIterable((List) obj, this.f4013e, false, AbstractC2120l.bufferSize());
    }
}
