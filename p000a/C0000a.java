package p000a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

/* compiled from: ArchTaskExecutor.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: a.a */
/* loaded from: classes.dex */
public class C0000a extends AbstractC0002c {

    /* renamed from: b */
    public static volatile C0000a f0b;

    /* renamed from: a */
    @NonNull
    public AbstractC0002c f1a = new C0001b();

    @NonNull
    /* renamed from: c */
    public static C0000a m0c() {
        if (f0b != null) {
            return f0b;
        }
        synchronized (C0000a.class) {
            if (f0b == null) {
                f0b = new C0000a();
            }
        }
        return f0b;
    }

    @Override // p000a.AbstractC0002c
    /* renamed from: a */
    public boolean mo1a() {
        return this.f1a.mo1a();
    }

    @Override // p000a.AbstractC0002c
    /* renamed from: b */
    public void mo2b(Runnable runnable) {
        this.f1a.mo2b(runnable);
    }
}
