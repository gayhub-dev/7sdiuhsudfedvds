package p112n4;

import java.util.concurrent.ThreadFactory;
import p194y3.AbstractC2128t;

/* compiled from: NewThreadScheduler.java */
/* renamed from: n4.e */
/* loaded from: classes.dex */
public final class C1530e extends AbstractC2128t {

    /* renamed from: b */
    public static final ThreadFactoryC1532g f4465b = new ThreadFactoryC1532g("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    /* renamed from: a */
    public final ThreadFactory f4466a = f4465b;

    @Override // p194y3.AbstractC2128t
    public AbstractC2128t.c createWorker() {
        return new C1531f(this.f4466a);
    }
}
