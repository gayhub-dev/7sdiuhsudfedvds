package p144r2;

/* compiled from: ZxtMediaRenderer.java */
/* renamed from: r2.f */
/* loaded from: classes.dex */
public class C1833f extends Thread {

    /* renamed from: e */
    public final /* synthetic */ C1832e f5339e;

    public C1833f(C1832e c1832e) {
        this.f5339e = c1832e;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws InterruptedException {
        while (true) {
            try {
                this.f5339e.f5334f.fireLastChange();
                this.f5339e.f5335g.fireLastChange();
                Thread.sleep(500L);
            } catch (Exception unused) {
                return;
            }
        }
    }
}
