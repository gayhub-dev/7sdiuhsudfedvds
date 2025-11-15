package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import p014b4.InterfaceC0443c;
import p022c4.EnumC0516d;
import p088k4.C1367v2;
import p186x2.C2074b;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2130v;

/* compiled from: ObservableReduceWithSingle.java */
/* renamed from: k4.w2 */
/* loaded from: classes.dex */
public final class C1373w2<T, R> extends AbstractC2129u<R> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3938a;

    /* renamed from: b */
    public final Callable<R> f3939b;

    /* renamed from: c */
    public final InterfaceC0443c<R, ? super T, R> f3940c;

    public C1373w2(InterfaceC2125q<T> interfaceC2125q, Callable<R> callable, InterfaceC0443c<R, ? super T, R> interfaceC0443c) {
        this.f3938a = interfaceC2125q;
        this.f3939b = callable;
        this.f3940c = interfaceC0443c;
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super R> interfaceC2130v) {
        try {
            R rCall = this.f3939b.call();
            Objects.requireNonNull(rCall, "The seedSupplier returned a null value");
            this.f3938a.subscribe(new C1367v2.a(interfaceC2130v, this.f3940c, rCall));
        } catch (Throwable th) {
            C2074b.m2470J(th);
            interfaceC2130v.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2130v.onError(th);
        }
    }
}
