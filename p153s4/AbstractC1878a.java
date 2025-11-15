package p153s4;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import p138q4.C1778j;
import p153s4.AbstractC1878a;
import p201z3.InterfaceC2153b;

/* compiled from: BaseTestConsumer.java */
/* renamed from: s4.a */
/* loaded from: classes.dex */
public abstract class AbstractC1878a<T, U extends AbstractC1878a<T, U>> implements InterfaceC2153b {

    /* renamed from: h */
    public long f5467h;

    /* renamed from: i */
    public boolean f5468i;

    /* renamed from: f */
    public final List<T> f5465f = new C1778j();

    /* renamed from: g */
    public final List<Throwable> f5466g = new C1778j();

    /* renamed from: e */
    public final CountDownLatch f5464e = new CountDownLatch(1);
}
