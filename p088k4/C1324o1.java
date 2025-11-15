package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0443c;
import p014b4.InterfaceC0454n;
import p194y3.InterfaceC2125q;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.o1 */
/* loaded from: classes.dex */
public final class C1324o1<T, R, U> implements InterfaceC0454n<T, InterfaceC2125q<R>> {

    /* renamed from: e */
    public final InterfaceC0443c<? super T, ? super U, ? extends R> f3482e;

    /* renamed from: f */
    public final InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> f3483f;

    public C1324o1(InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c, InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> interfaceC0454n) {
        this.f3482e = interfaceC0443c;
        this.f3483f = interfaceC0454n;
    }

    @Override // p014b4.InterfaceC0454n
    public Object apply(Object obj) {
        InterfaceC2125q<? extends U> interfaceC2125qApply = this.f3483f.apply(obj);
        Objects.requireNonNull(interfaceC2125qApply, "The mapper returned a null ObservableSource");
        return new C1277g2(interfaceC2125qApply, new C1318n1(this.f3482e, obj));
    }
}
