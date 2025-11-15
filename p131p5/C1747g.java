package p131p5;

import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p113n5.C1553n;
import p113n5.C1555p;
import p113n5.InterfaceC1548i;
import p168u5.AbstractC1980a;

/* compiled from: HandlerWrapper.java */
/* renamed from: p5.g */
/* loaded from: classes.dex */
public class C1747g extends AbstractC1742b {

    /* renamed from: j */
    public InterfaceC1548i f4960j;

    @Override // p131p5.AbstractC1742b
    /* renamed from: M */
    public Object mo1891M(Object obj, Class cls) {
        return m1892N(this.f4960j, obj, cls);
    }

    /* renamed from: P */
    public void m1910P(InterfaceC1548i interfaceC1548i) {
        if (isStarted()) {
            throw new IllegalStateException(AbstractC1980a.STARTED);
        }
        InterfaceC1548i interfaceC1548i2 = this.f4960j;
        this.f4960j = interfaceC1548i;
        if (interfaceC1548i != null) {
            interfaceC1548i.mo1772h(this.f4928h);
        }
        C1555p c1555p = this.f4928h;
        if (c1555p != null) {
            c1555p.f4670k.m2313e(this, interfaceC1548i2, interfaceC1548i, "handler");
        }
    }

    @Override // p131p5.AbstractC1741a, p168u5.C1981b, p168u5.InterfaceC1983d
    public void destroy() {
        if (!isStopped()) {
            throw new IllegalStateException("!STOPPED");
        }
        InterfaceC1548i interfaceC1548i = this.f4960j;
        if (interfaceC1548i != null) {
            m1910P(null);
            interfaceC1548i.destroy();
        }
        super.destroy();
    }

    @Override // p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() {
        InterfaceC1548i interfaceC1548i = this.f4960j;
        if (interfaceC1548i != null) {
            interfaceC1548i.start();
        }
        super.doStart();
    }

    @Override // p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() {
        InterfaceC1548i interfaceC1548i = this.f4960j;
        if (interfaceC1548i != null) {
            interfaceC1548i.stop();
        }
        super.doStop();
    }

    @Override // p131p5.AbstractC1741a, p113n5.InterfaceC1548i
    /* renamed from: h */
    public void mo1772h(C1555p c1555p) {
        C1555p c1555p2 = this.f4928h;
        if (c1555p == c1555p2) {
            return;
        }
        if (isStarted()) {
            throw new IllegalStateException(AbstractC1980a.STARTED);
        }
        super.mo1772h(c1555p);
        InterfaceC1548i interfaceC1548i = this.f4960j;
        if (interfaceC1548i != null) {
            interfaceC1548i.mo1772h(c1555p);
        }
        if (c1555p == null || c1555p == c1555p2) {
            return;
        }
        c1555p.f4670k.m2313e(this, null, this.f4960j, "handler");
    }

    /* renamed from: u */
    public void mo1630u(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
        if (this.f4960j == null || !isStarted()) {
            return;
        }
        this.f4960j.mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
    }
}
