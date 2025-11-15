package p180w3;

import android.support.annotation.RestrictTo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.atomic.AtomicBoolean;
import p101m1.RunnableC1457a;
import p201z3.InterfaceC2153b;

/* compiled from: MainThreadDisposable.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: w3.b */
/* loaded from: classes.dex */
public abstract class AbstractC2028b implements InterfaceC2153b {

    /* renamed from: e */
    public final AtomicBoolean f5896e = new AtomicBoolean();

    @Override // p201z3.InterfaceC2153b
    public final void dispose() {
        if (this.f5896e.compareAndSet(false, true)) {
            if (C2027a.m2380a()) {
                mo667h();
            } else {
                AndroidSchedulers.mainThread().scheduleDirect(new RunnableC1457a(this));
            }
        }
    }

    /* renamed from: h */
    public abstract void mo667h();

    @Override // p201z3.InterfaceC2153b
    public final boolean isDisposed() {
        return this.f5896e.get();
    }
}
