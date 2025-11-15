package p088k4;

import java.util.concurrent.Callable;
import p146r4.AbstractC1837a;
import p194y3.AbstractC2120l;

/* compiled from: ObservableInternalHelper.java */
/* renamed from: k4.k1 */
/* loaded from: classes.dex */
public final class CallableC1300k1<T> implements Callable<AbstractC1837a<T>> {

    /* renamed from: e */
    public final AbstractC2120l<T> f3271e;

    /* renamed from: f */
    public final int f3272f;

    public CallableC1300k1(AbstractC2120l<T> abstractC2120l, int i7) {
        this.f3271e = abstractC2120l;
        this.f3272f = i7;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.f3271e.replay(this.f3272f);
    }
}
