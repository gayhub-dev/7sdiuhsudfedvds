package p138q4;

import java.util.concurrent.CountDownLatch;
import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0446f;

/* compiled from: BlockingIgnoringReceiver.java */
/* renamed from: q4.d */
/* loaded from: classes.dex */
public final class C1772d extends CountDownLatch implements InterfaceC0446f<Throwable>, InterfaceC0441a {

    /* renamed from: e */
    public Throwable f5052e;

    public C1772d() {
        super(1);
    }

    @Override // p014b4.InterfaceC0446f
    public void accept(Throwable th) {
        this.f5052e = th;
        countDown();
    }

    @Override // p014b4.InterfaceC0441a
    public void run() {
        countDown();
    }
}
