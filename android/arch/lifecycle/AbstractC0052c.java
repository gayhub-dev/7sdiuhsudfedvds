package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/* compiled from: Lifecycle.java */
/* renamed from: android.arch.lifecycle.c */
/* loaded from: classes.dex */
public abstract class AbstractC0052c {

    /* compiled from: Lifecycle.java */
    /* renamed from: android.arch.lifecycle.c$a */
    public enum a {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY
    }

    /* compiled from: Lifecycle.java */
    /* renamed from: android.arch.lifecycle.c$b */
    public enum b {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED
    }

    @MainThread
    /* renamed from: a */
    public abstract void mo75a(@NonNull InterfaceC0053d interfaceC0053d);
}
