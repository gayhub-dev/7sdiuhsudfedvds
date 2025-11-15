package p107n;

import android.content.Context;
import android.content.res.Resources;
import java.util.Iterator;
import p035e.C0888a;
import p107n.C1506f;

/* compiled from: TouchStrategy.java */
/* renamed from: n.i */
/* loaded from: classes.dex */
public class C1509i extends AbstractC1501a {

    /* renamed from: c */
    public static final float f4338c = Resources.getSystem().getDisplayMetrics().density;

    public C1509i(C1506f.a aVar) {
        super(aVar);
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: a */
    public boolean mo1588a(Context context) {
        return true;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: b */
    public void mo1589b(Context context) {
    }

    @Override // p107n.InterfaceC1504d
    /* renamed from: c */
    public boolean mo1668c(int i7, int i8) {
        for (C0888a c0888a : m1666f()) {
            float f7 = c0888a.f1497p;
            float f8 = f4338c;
            c0888a.mo773d(f7 - ((i7 / f8) * 0.2f));
            c0888a.mo774e(c0888a.f1498q - ((i8 / f8) * 0.2f));
        }
        return false;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: d */
    public void mo1590d(Context context) {
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: e */
    public void mo1591e(Context context) {
        Iterator<C0888a> it = m1666f().iterator();
        while (it.hasNext()) {
            it.next().m772c();
        }
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: h */
    public void mo1592h(Context context) {
    }
}
