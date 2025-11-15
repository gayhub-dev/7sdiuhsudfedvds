package android.arch.lifecycle;

import android.arch.lifecycle.AbstractC0052c;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {

    /* renamed from: e */
    public final InterfaceC0051b[] f67e;

    public CompositeGeneratedAdaptersObserver(InterfaceC0051b[] interfaceC0051bArr) {
        this.f67e = interfaceC0051bArr;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    /* renamed from: d */
    public void mo58d(InterfaceC0054e interfaceC0054e, AbstractC0052c.a aVar) {
        C0058i c0058i = new C0058i();
        for (InterfaceC0051b interfaceC0051b : this.f67e) {
            interfaceC0051b.mo74a(interfaceC0054e, aVar, false, c0058i);
        }
        for (InterfaceC0051b interfaceC0051b2 : this.f67e) {
            interfaceC0051b2.mo74a(interfaceC0054e, aVar, true, c0058i);
        }
    }
}
