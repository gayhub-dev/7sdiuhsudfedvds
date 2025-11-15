package p088k4;

import java.util.concurrent.Callable;
import p146r4.AbstractC1837a;
import p194y3.AbstractC2120l;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.t1 */
/* loaded from: classes.dex */
public final class CallableC1354t1<T> implements Callable<AbstractC1837a<T>> {

    /* renamed from: e */
    public final AbstractC2120l<T> f3802e;

    public CallableC1354t1(AbstractC2120l<T> abstractC2120l) {
        this.f3802e = abstractC2120l;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.f3802e.replay();
    }
}
