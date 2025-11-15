package p088k4;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import p146r4.AbstractC1837a;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.l1 */
/* loaded from: classes.dex */
public final class CallableC1306l1<T> implements Callable<AbstractC1837a<T>> {

    /* renamed from: e */
    public final AbstractC2120l<T> f3330e;

    /* renamed from: f */
    public final int f3331f;

    /* renamed from: g */
    public final long f3332g;

    /* renamed from: h */
    public final TimeUnit f3333h;

    /* renamed from: i */
    public final AbstractC2128t f3334i;

    public CallableC1306l1(AbstractC2120l<T> abstractC2120l, int i7, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        this.f3330e = abstractC2120l;
        this.f3331f = i7;
        this.f3332g = j7;
        this.f3333h = timeUnit;
        this.f3334i = abstractC2128t;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.f3330e.replay(this.f3331f, this.f3332g, this.f3333h, this.f3334i);
    }
}
