package p131p5;

import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p113n5.C1553n;
import p113n5.InterfaceC1548i;

/* compiled from: ScopedHandler.java */
/* renamed from: p5.h */
/* loaded from: classes.dex */
public abstract class AbstractC1748h extends C1747g {

    /* renamed from: m */
    public static final ThreadLocal<AbstractC1748h> f4961m = new ThreadLocal<>();

    /* renamed from: k */
    public AbstractC1748h f4962k;

    /* renamed from: l */
    public AbstractC1748h f4963l;

    /* renamed from: Q */
    public abstract void mo1895Q(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e);

    /* renamed from: R */
    public abstract void mo1896R(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e);

    /* renamed from: S */
    public final void m1911S(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        AbstractC1748h abstractC1748h = this.f4963l;
        if (abstractC1748h != null && abstractC1748h == this.f4960j) {
            abstractC1748h.mo1895Q(str, c1553n, interfaceC0458c, interfaceC0460e);
            return;
        }
        InterfaceC1548i interfaceC1548i = this.f4960j;
        if (interfaceC1548i != null) {
            interfaceC1548i.mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
        }
    }

    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() {
        try {
            ThreadLocal<AbstractC1748h> threadLocal = f4961m;
            AbstractC1748h abstractC1748h = threadLocal.get();
            this.f4962k = abstractC1748h;
            if (abstractC1748h == null) {
                threadLocal.set(this);
            }
            super.doStart();
            this.f4963l = (AbstractC1748h) m1893O(AbstractC1748h.class);
            if (this.f4962k == null) {
                threadLocal.set(null);
            }
        } catch (Throwable th) {
            if (this.f4962k == null) {
                f4961m.set(null);
            }
            throw th;
        }
    }

    @Override // p131p5.C1747g, p113n5.InterfaceC1548i
    /* renamed from: u */
    public final void mo1630u(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        if (this.f4962k == null) {
            mo1896R(str, c1553n, interfaceC0458c, interfaceC0460e);
        } else {
            mo1895Q(str, c1553n, interfaceC0458c, interfaceC0460e);
        }
    }
}
