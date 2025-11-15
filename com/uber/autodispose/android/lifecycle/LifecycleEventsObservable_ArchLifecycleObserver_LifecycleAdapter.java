package com.uber.autodispose.android.lifecycle;

import android.arch.lifecycle.AbstractC0052c;
import android.arch.lifecycle.C0058i;
import android.arch.lifecycle.InterfaceC0051b;
import android.arch.lifecycle.InterfaceC0054e;
import com.uber.autodispose.android.lifecycle.LifecycleEventsObservable;

/* loaded from: classes.dex */
public class LifecycleEventsObservable_ArchLifecycleObserver_LifecycleAdapter implements InterfaceC0051b {

    /* renamed from: a */
    public final LifecycleEventsObservable.ArchLifecycleObserver f1284a;

    public LifecycleEventsObservable_ArchLifecycleObserver_LifecycleAdapter(LifecycleEventsObservable.ArchLifecycleObserver archLifecycleObserver) {
        this.f1284a = archLifecycleObserver;
    }

    @Override // android.arch.lifecycle.InterfaceC0051b
    /* renamed from: a */
    public void mo74a(InterfaceC0054e interfaceC0054e, AbstractC0052c.a aVar, boolean z6, C0058i c0058i) {
        boolean z7 = c0058i != null;
        if (z6) {
            if (z7) {
                Integer num = c0058i.f107a.get("onStateChange");
                int iIntValue = num != null ? num.intValue() : 0;
                boolean z8 = (iIntValue & 4) != 0;
                c0058i.f107a.put("onStateChange", Integer.valueOf(4 | iIntValue));
                if (!(!z8)) {
                    return;
                }
            }
            this.f1284a.onStateChange(interfaceC0054e, aVar);
        }
    }
}
