package p088k4;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import p146r4.AbstractC1837a;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.x1 */
/* loaded from: classes.dex */
public final class CallableC1378x1<T> implements Callable<AbstractC1837a<T>> {

    /* renamed from: e */
    public final AbstractC2120l<T> f3984e;

    /* renamed from: f */
    public final long f3985f;

    /* renamed from: g */
    public final TimeUnit f3986g;

    /* renamed from: h */
    public final AbstractC2128t f3987h;

    public CallableC1378x1(AbstractC2120l<T> abstractC2120l, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        this.f3984e = abstractC2120l;
        this.f3985f = j7;
        this.f3986g = timeUnit;
        this.f3987h = abstractC2128t;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.f3984e.replay(this.f3985f, this.f3986g, this.f3987h);
    }
}
