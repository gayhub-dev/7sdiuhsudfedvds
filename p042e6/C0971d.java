package p042e6;

import android.support.constraint.solver.C0084a;
import p009b.C0413b;

/* compiled from: ConverterManager.java */
/* renamed from: e6.d */
/* loaded from: classes.dex */
public final class C0971d {

    /* renamed from: f */
    public static C0971d f1797f;

    /* renamed from: a */
    public C0972e f1798a;

    /* renamed from: b */
    public C0972e f1799b;

    /* renamed from: c */
    public C0972e f1800c;

    /* renamed from: d */
    public C0972e f1801d;

    /* renamed from: e */
    public C0972e f1802e;

    public C0971d() {
        C0979l c0979l = C0979l.f1811a;
        C0983p c0983p = C0983p.f1815a;
        C0969b c0969b = C0969b.f1796a;
        C0973f c0973f = C0973f.f1807a;
        C0975h c0975h = C0975h.f1808a;
        C0976i c0976i = C0976i.f1809a;
        this.f1798a = new C0972e(new InterfaceC0970c[]{c0979l, c0983p, c0969b, c0973f, c0975h, c0976i});
        this.f1799b = new C0972e(new InterfaceC0970c[]{C0981n.f1813a, c0979l, c0983p, c0969b, c0973f, c0975h, c0976i});
        C0978k c0978k = C0978k.f1810a;
        C0980m c0980m = C0980m.f1812a;
        this.f1800c = new C0972e(new InterfaceC0970c[]{c0978k, c0980m, c0983p, c0975h, c0976i});
        this.f1801d = new C0972e(new InterfaceC0970c[]{c0978k, C0982o.f1814a, c0980m, c0983p, c0976i});
        this.f1802e = new C0972e(new InterfaceC0970c[]{c0980m, c0983p, c0976i});
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("ConverterManager[");
        sbM112a.append(this.f1798a.f1803a.length);
        sbM112a.append(" instant,");
        sbM112a.append(this.f1799b.f1803a.length);
        sbM112a.append(" partial,");
        sbM112a.append(this.f1800c.f1803a.length);
        sbM112a.append(" duration,");
        sbM112a.append(this.f1801d.f1803a.length);
        sbM112a.append(" period,");
        return C0084a.m96a(sbM112a, this.f1802e.f1803a.length, " interval]");
    }
}
