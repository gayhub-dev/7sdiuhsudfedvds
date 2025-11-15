package p115o;

import android.content.Context;
import android.graphics.RectF;
import p051g.AbstractC1039i;
import p051g.C1037g;
import p067i.AbstractC1126a;
import p067i.C1129d;
import p067i.C1131f;
import p075j.AbstractC1174b;
import p075j.C1178f;

/* compiled from: DomeProjection.java */
/* renamed from: o.c */
/* loaded from: classes.dex */
public class C1566c extends AbstractC1564a {

    /* renamed from: a */
    public AbstractC1126a f4693a;

    /* renamed from: b */
    public float f4694b;

    /* renamed from: c */
    public boolean f4695c;

    /* renamed from: d */
    public RectF f4696d;

    public C1566c(RectF rectF, float f7, boolean z6) {
        this.f4696d = rectF;
        this.f4694b = f7;
        this.f4695c = z6;
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
        C1129d c1129d = new C1129d(this.f4696d, this.f4694b, this.f4695c);
        this.f4693a = c1129d;
        C1131f.m1294a(context, c1129d);
    }

    @Override // p115o.InterfaceC1567d
    /* renamed from: f */
    public AbstractC1126a mo1812f() {
        return this.f4693a;
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
}
