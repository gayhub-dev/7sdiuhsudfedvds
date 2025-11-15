package p107n;

import android.content.res.Resources;
import p035e.C0888a;
import p107n.C1506f;

/* compiled from: CardboardMTStrategy.java */
/* renamed from: n.b */
/* loaded from: classes.dex */
public class C1502b extends C1503c {

    /* renamed from: l */
    public static final float f4304l = Resources.getSystem().getDisplayMetrics().density;

    public C1502b(C1506f.a aVar) {
        super(aVar);
    }

    @Override // p107n.C1503c, p107n.InterfaceC1504d
    /* renamed from: c */
    public boolean mo1668c(int i7, int i8) {
        for (C0888a c0888a : m1666f()) {
            float f7 = c0888a.f1497p;
            float f8 = f4304l;
            c0888a.mo773d(f7 - ((i7 / f8) * 0.2f));
            c0888a.mo774e(c0888a.f1498q - ((i8 / f8) * 0.2f));
        }
        return false;
    }
}
