package p107n;

import android.content.res.Resources;
import p035e.C0888a;
import p107n.C1506f;

/* compiled from: MotionWithTouchStrategy.java */
/* renamed from: n.h */
/* loaded from: classes.dex */
public class C1508h extends C1507g {

    /* renamed from: l */
    public static final float f4337l = Resources.getSystem().getDisplayMetrics().density;

    public C1508h(C1506f.a aVar) {
        super(aVar);
    }

    @Override // p107n.C1507g, p107n.InterfaceC1504d
    /* renamed from: c */
    public boolean mo1668c(int i7, int i8) {
        for (C0888a c0888a : m1666f()) {
            float f7 = c0888a.f1497p;
            float f8 = f4337l;
            c0888a.mo773d(f7 - ((i7 / f8) * 0.2f));
            c0888a.mo774e(c0888a.f1498q - ((i8 / f8) * 0.2f));
        }
        return false;
    }
}
