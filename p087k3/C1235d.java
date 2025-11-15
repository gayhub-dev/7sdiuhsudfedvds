package p087k3;

import android.content.Context;
import p035e.AbstractC0889b;
import p035e.C0888a;
import p035e.C0893f;
import p035e.C0901n;
import p043f.C0984a;
import p051g.C1031a;
import p051g.C1038h;
import p115o.C1570g;
import p125p.C1731b;

/* compiled from: PolyvVR.java */
/* renamed from: k3.d */
/* loaded from: classes.dex */
public class C1235d {

    /* compiled from: PolyvVR.java */
    /* renamed from: k3.d$a */
    public class a extends AbstractC0889b {
        @Override // p035e.AbstractC0889b
        /* renamed from: a */
        public C0888a mo779a(int i7) {
            C0888a.a aVar = new C0888a.a();
            C0893f c0893f = aVar.f1500a;
            c0893f.f1536m.m1170b(270.0f);
            c0893f.f1535l = true;
            return new C0888a(aVar);
        }
    }

    /* renamed from: a */
    public static C0901n.b m1460a(Context context, int i7, C0901n.h hVar, C0901n.g gVar, C0901n.f fVar, C1570g.b bVar) {
        C0901n.b bVar2 = new C0901n.b(context, null);
        bVar2.f1607a = i7;
        bVar2.f1608b = 5;
        bVar2.f1610d = new C1731b(hVar);
        bVar2.f1611e = gVar;
        C1038h c1038h = new C1038h();
        c1038h.f1967b = 1.0f;
        c1038h.f1966a = 8.0f;
        c1038h.f1968c = 0.1f;
        bVar2.f1618l = c1038h;
        bVar2.f1620n = bVar;
        bVar2.f1613g = true;
        bVar2.f1615i = new a();
        bVar2.f1617k = new C0984a(11);
        C1031a c1031a = new C1031a();
        c1031a.f1942b = false;
        c1031a.f1941a = 0.95f;
        bVar2.f1614h = c1031a;
        bVar2.f1612f = fVar;
        return bVar2;
    }
}
