package p115o;

import android.content.Context;
import p035e.AbstractC0889b;
import p035e.C0888a;
import p051g.AbstractC1039i;
import p051g.C1037g;
import p067i.AbstractC1126a;
import p067i.C1131f;
import p067i.C1134i;
import p075j.AbstractC1174b;
import p075j.C1178f;

/* compiled from: StereoSphereProjection.java */
/* renamed from: o.h */
/* loaded from: classes.dex */
public class C1571h extends AbstractC1564a {

    /* renamed from: a */
    public int f4727a;

    /* renamed from: b */
    public AbstractC1126a f4728b;

    /* compiled from: StereoSphereProjection.java */
    /* renamed from: o.h$b */
    public static class b extends AbstractC0889b {
        public b(a aVar) {
        }

        @Override // p035e.AbstractC0889b
        /* renamed from: a */
        public C0888a mo779a(int i7) {
            return new C0888a(new C0888a.a());
        }
    }

    public C1571h(int i7) {
        this.f4727a = i7;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: a */
    public boolean mo1588a(Context context) {
        return true;
    }

    @Override // p115o.AbstractC1564a
    /* renamed from: c */
    public AbstractC1174b mo1810c(C1037g c1037g) {
        return new C1178f(c1037g);
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: e */
    public void mo1591e(Context context) {
        C1134i c1134i = new C1134i(this.f4727a);
        this.f4728b = c1134i;
        C1131f.m1294a(context, c1134i);
    }

    @Override // p115o.InterfaceC1567d
    /* renamed from: f */
    public AbstractC1126a mo1812f() {
        return this.f4728b;
    }

    @Override // p115o.InterfaceC1567d
    /* renamed from: g */
    public AbstractC1039i mo1813g() {
        return AbstractC1039i.f1969a;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: h */
    public void mo1592h(Context context) {
    }

    @Override // p115o.AbstractC1564a
    /* renamed from: i */
    public AbstractC0889b mo1811i() {
        return new b(null);
    }
}
