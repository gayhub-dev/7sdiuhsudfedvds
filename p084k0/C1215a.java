package p084k0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import p142r0.C1823h;

/* compiled from: ActivityFragmentLifecycle.java */
/* renamed from: k0.a */
/* loaded from: classes.dex */
public class C1215a implements InterfaceC1220f {

    /* renamed from: a */
    public final Set<InterfaceC1221g> f2738a = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: b */
    public boolean f2739b;

    /* renamed from: c */
    public boolean f2740c;

    @Override // p084k0.InterfaceC1220f
    /* renamed from: a */
    public void mo941a(InterfaceC1221g interfaceC1221g) {
        this.f2738a.add(interfaceC1221g);
        if (this.f2740c) {
            interfaceC1221g.mo1447j();
        } else if (this.f2739b) {
            interfaceC1221g.mo1446a();
        } else {
            interfaceC1221g.onStop();
        }
    }

    /* renamed from: b */
    public void m1443b() {
        this.f2740c = true;
        Iterator it = ((ArrayList) C1823h.m2061e(this.f2738a)).iterator();
        while (it.hasNext()) {
            ((InterfaceC1221g) it.next()).mo1447j();
        }
    }

    @Override // p084k0.InterfaceC1220f
    /* renamed from: c */
    public void mo943c(InterfaceC1221g interfaceC1221g) {
        this.f2738a.remove(interfaceC1221g);
    }

    /* renamed from: d */
    public void m1444d() {
        this.f2739b = true;
        Iterator it = ((ArrayList) C1823h.m2061e(this.f2738a)).iterator();
        while (it.hasNext()) {
            ((InterfaceC1221g) it.next()).mo1446a();
        }
    }

    /* renamed from: e */
    public void m1445e() {
        this.f2739b = false;
        Iterator it = ((ArrayList) C1823h.m2061e(this.f2738a)).iterator();
        while (it.hasNext()) {
            ((InterfaceC1221g) it.next()).onStop();
        }
    }
}
