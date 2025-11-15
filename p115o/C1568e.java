package p115o;

import p051g.C1037g;
import p075j.AbstractC1174b;
import p075j.C1177e;

/* compiled from: MultiFishEyeProjection.java */
/* renamed from: o.e */
/* loaded from: classes.dex */
public class C1568e extends C1565b {

    /* renamed from: c */
    public float f4697c;

    /* renamed from: d */
    public int f4698d;

    public C1568e(float f7, int i7) {
        super(1);
        this.f4697c = f7;
        this.f4698d = i7;
    }

    @Override // p115o.C1565b, p115o.AbstractC1564a
    /* renamed from: c */
    public AbstractC1174b mo1810c(C1037g c1037g) {
        return new C1177e(c1037g, this.f4697c, this.f4698d);
    }
}
