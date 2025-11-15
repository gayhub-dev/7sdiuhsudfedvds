package io.reactivex.android;

import android.os.Looper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.atomic.AtomicBoolean;
import p009b.C0413b;
import p201z3.InterfaceC2153b;

/* loaded from: classes.dex */
public abstract class MainThreadDisposable implements InterfaceC2153b {
    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    public static void verifyMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        StringBuilder sbM112a = C0413b.m112a("Expected to be called on the main thread but was ");
        sbM112a.append(Thread.currentThread().getName());
        throw new IllegalStateException(sbM112a.toString());
    }

    @Override // p201z3.InterfaceC2153b
    public final void dispose() {
        if (this.unsubscribed.compareAndSet(false, true)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                onDispose();
            } else {
                AndroidSchedulers.mainThread().scheduleDirect(new Runnable() { // from class: io.reactivex.android.MainThreadDisposable.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainThreadDisposable.this.onDispose();
                    }
                });
            }
        }
    }

    @Override // p201z3.InterfaceC2153b
    public final boolean isDisposed() {
        return this.unsubscribed.get();
    }

    public abstract void onDispose();
}
