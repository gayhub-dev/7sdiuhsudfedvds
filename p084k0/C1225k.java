package p084k0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import p108n0.InterfaceC1510a;

/* compiled from: RequestTracker.java */
/* renamed from: k0.k */
/* loaded from: classes.dex */
public class C1225k {

    /* renamed from: a */
    public final Set<InterfaceC1510a> f2753a = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: b */
    public final List<InterfaceC1510a> f2754b = new ArrayList();

    /* renamed from: c */
    public boolean f2755c;

    /* renamed from: a */
    public boolean m1452a(InterfaceC1510a interfaceC1510a) {
        if (interfaceC1510a == null) {
            return false;
        }
        boolean z6 = this.f2754b.remove(interfaceC1510a) || this.f2753a.remove(interfaceC1510a);
        if (z6) {
            interfaceC1510a.clear();
            interfaceC1510a.recycle();
        }
        return z6;
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.f2753a.size() + ", isPaused=" + this.f2755c + "}";
    }
}
