package p112n4;

import android.support.constraint.C0072a;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import p009b.C0413b;

/* compiled from: RxThreadFactory.java */
/* renamed from: n4.g */
/* loaded from: classes.dex */
public final class ThreadFactoryC1532g extends AtomicLong implements ThreadFactory {
    private static final long serialVersionUID = -7789753024099756196L;

    /* renamed from: e */
    public final String f4469e;

    /* renamed from: f */
    public final int f4470f;

    /* renamed from: g */
    public final boolean f4471g;

    /* compiled from: RxThreadFactory.java */
    /* renamed from: n4.g$a */
    public static final class a extends Thread {
        public a(Runnable runnable, String str) {
            super(runnable, str);
        }
    }

    public ThreadFactoryC1532g(String str) {
        this.f4469e = str;
        this.f4470f = 5;
        this.f4471g = false;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        String str = this.f4469e + '-' + incrementAndGet();
        Thread aVar = this.f4471g ? new a(runnable, str) : new Thread(runnable, str);
        aVar.setPriority(this.f4470f);
        aVar.setDaemon(true);
        return aVar;
    }

    @Override // java.util.concurrent.atomic.AtomicLong
    public String toString() {
        return C0072a.m92a(C0413b.m112a("RxThreadFactory["), this.f4469e, "]");
    }

    public ThreadFactoryC1532g(String str, int i7) {
        this.f4469e = str;
        this.f4470f = i7;
        this.f4471g = false;
    }

    public ThreadFactoryC1532g(String str, int i7, boolean z6) {
        this.f4469e = str;
        this.f4470f = i7;
        this.f4471g = z6;
    }
}
