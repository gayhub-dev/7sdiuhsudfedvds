package p000a;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: DefaultTaskExecutor.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: a.b */
/* loaded from: classes.dex */
public class C0001b extends AbstractC0002c {

    /* renamed from: a */
    public final Object f2a = new Object();

    /* renamed from: b */
    public ExecutorService f3b = Executors.newFixedThreadPool(2);

    /* renamed from: c */
    @Nullable
    public volatile Handler f4c;

    @Override // p000a.AbstractC0002c
    /* renamed from: a */
    public boolean mo1a() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // p000a.AbstractC0002c
    /* renamed from: b */
    public void mo2b(Runnable runnable) {
        if (this.f4c == null) {
            synchronized (this.f2a) {
                if (this.f4c == null) {
                    this.f4c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f4c.post(runnable);
    }
}
