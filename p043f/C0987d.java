package p043f;

import android.os.Looper;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: MDGLHandler.java */
/* renamed from: f.d */
/* loaded from: classes.dex */
public class C0987d {

    /* renamed from: a */
    public boolean f1819a;

    /* renamed from: b */
    public Queue<Runnable> f1820b = new LinkedBlockingQueue();

    /* renamed from: c */
    public Queue<Runnable> f1821c = new LinkedBlockingQueue();

    /* renamed from: d */
    public final Object f1822d = new Object();

    /* renamed from: a */
    public void m955a() {
        synchronized (this.f1822d) {
            this.f1821c.addAll(this.f1820b);
            this.f1820b.clear();
        }
        while (this.f1821c.size() > 0) {
            this.f1821c.poll().run();
        }
    }

    /* renamed from: b */
    public void m956b(Runnable runnable) {
        if (this.f1819a || runnable == null) {
            return;
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            runnable.run();
            return;
        }
        synchronized (this.f1822d) {
            this.f1820b.remove(runnable);
            this.f1820b.offer(runnable);
        }
    }
}
