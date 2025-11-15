package p131p5;

import java.util.Properties;
import p113n5.C1555p;
import p113n5.InterfaceC1548i;
import p168u5.C1981b;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AbstractHandler.java */
/* renamed from: p5.a */
/* loaded from: classes.dex */
public abstract class AbstractC1741a extends C1981b implements InterfaceC1548i {

    /* renamed from: i */
    public static final InterfaceC2016c f4927i;

    /* renamed from: h */
    public C1555p f4928h;

    static {
        Properties properties = C2015b.f5863a;
        f4927i = C2015b.m2349a(AbstractC1741a.class.getName());
    }

    @Override // p113n5.InterfaceC1548i
    /* renamed from: c */
    public C1555p mo1771c() {
        return this.f4928h;
    }

    @Override // p168u5.C1981b, p168u5.InterfaceC1983d
    public void destroy() {
        if (!isStopped()) {
            throw new IllegalStateException("!STOPPED");
        }
        super.destroy();
        C1555p c1555p = this.f4928h;
        if (c1555p != null) {
            c1555p.f4670k.m2312d(this);
        }
    }

    @Override // p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() {
        f4927i.mo2351a("starting {}", this);
        super.doStart();
    }

    @Override // p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() {
        f4927i.mo2351a("stopping {}", this);
        super.doStop();
    }

    @Override // p113n5.InterfaceC1548i
    /* renamed from: h */
    public void mo1772h(C1555p c1555p) {
        C1555p c1555p2 = this.f4928h;
        if (c1555p2 != null && c1555p2 != c1555p) {
            c1555p2.f4670k.m2312d(this);
        }
        this.f4928h = c1555p;
        if (c1555p == null || c1555p == c1555p2) {
            return;
        }
        c1555p.f4670k.m2310b(this);
    }
}
