package android.arch.lifecycle;

import android.arch.lifecycle.AbstractC0052c;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {

    /* renamed from: e */
    public final InterfaceC0051b f80e;

    public SingleGeneratedAdapterObserver(InterfaceC0051b interfaceC0051b) {
        this.f80e = interfaceC0051b;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    /* renamed from: d */
    public void mo58d(InterfaceC0054e interfaceC0054e, AbstractC0052c.a aVar) {
        this.f80e.mo74a(interfaceC0054e, aVar, false, null);
        this.f80e.mo74a(interfaceC0054e, aVar, true, null);
    }
}
