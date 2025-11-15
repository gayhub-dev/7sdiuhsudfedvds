package p084k0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import p116o0.InterfaceC1577f;
import p142r0.C1823h;

/* compiled from: TargetTracker.java */
/* renamed from: k0.l */
/* loaded from: classes.dex */
public final class C1226l implements InterfaceC1221g {

    /* renamed from: a */
    public final Set<InterfaceC1577f<?>> f2756a = Collections.newSetFromMap(new WeakHashMap());

    @Override // p084k0.InterfaceC1221g
    /* renamed from: a */
    public void mo1446a() {
        Iterator it = ((ArrayList) C1823h.m2061e(this.f2756a)).iterator();
        while (it.hasNext()) {
            ((InterfaceC1577f) it.next()).mo1446a();
        }
    }

    @Override // p084k0.InterfaceC1221g
    /* renamed from: j */
    public void mo1447j() {
        Iterator it = ((ArrayList) C1823h.m2061e(this.f2756a)).iterator();
        while (it.hasNext()) {
            ((InterfaceC1577f) it.next()).mo1447j();
        }
    }

    @Override // p084k0.InterfaceC1221g
    public void onStop() {
        Iterator it = ((ArrayList) C1823h.m2061e(this.f2756a)).iterator();
        while (it.hasNext()) {
            ((InterfaceC1577f) it.next()).onStop();
        }
    }
}
