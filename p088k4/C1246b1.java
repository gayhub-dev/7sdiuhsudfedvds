package p088k4;

import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import p048f4.C1003h;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableFromFuture.java */
/* renamed from: k4.b1 */
/* loaded from: classes.dex */
public final class C1246b1<T> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final Future<? extends T> f2830e;

    /* renamed from: f */
    public final long f2831f;

    /* renamed from: g */
    public final TimeUnit f2832g;

    public C1246b1(Future<? extends T> future, long j7, TimeUnit timeUnit) {
        this.f2830e = future;
        this.f2831f = j7;
        this.f2832g = timeUnit;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        C1003h c1003h = new C1003h(interfaceC2127s);
        interfaceC2127s.onSubscribe(c1003h);
        if (c1003h.isDisposed()) {
            return;
        }
        try {
            TimeUnit timeUnit = this.f2832g;
            T t6 = timeUnit != null ? this.f2830e.get(this.f2831f, timeUnit) : this.f2830e.get();
            Objects.requireNonNull(t6, "Future returned null");
            c1003h.m1018b(t6);
        } catch (Throwable th) {
            C2074b.m2470J(th);
            if (c1003h.isDisposed()) {
                return;
            }
            interfaceC2127s.onError(th);
        }
    }
}
