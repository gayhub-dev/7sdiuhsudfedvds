package p168u5;

import java.util.EventListener;

/* compiled from: LifeCycle.java */
/* renamed from: u5.e */
/* loaded from: classes.dex */
public interface InterfaceC1984e {

    /* compiled from: LifeCycle.java */
    /* renamed from: u5.e$a */
    public interface a extends EventListener {
        /* renamed from: B */
        void m2321B(InterfaceC1984e interfaceC1984e, Throwable th);

        /* renamed from: l */
        void m2322l(InterfaceC1984e interfaceC1984e);

        /* renamed from: q */
        void m2323q(InterfaceC1984e interfaceC1984e);

        /* renamed from: r */
        void m2324r(InterfaceC1984e interfaceC1984e);

        /* renamed from: s */
        void m2325s(InterfaceC1984e interfaceC1984e);
    }

    boolean isRunning();

    boolean isStarted();

    boolean isStarting();

    boolean isStopped();

    boolean isStopping();

    void start();

    void stop();
}
