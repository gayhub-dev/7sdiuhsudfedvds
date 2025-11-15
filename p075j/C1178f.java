package p075j;

import android.content.Context;
import android.opengl.GLES20;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import p035e.C0888a;
import p035e.C0890c;
import p035e.C0892e;
import p035e.C0893f;
import p035e.C0894g;
import p043f.C0985b;
import p051g.C1037g;
import p067i.AbstractC1126a;
import p115o.C1570g;
import p125p.AbstractC1730a;

/* compiled from: MDPanoramaPlugin.java */
/* renamed from: j.f */
/* loaded from: classes.dex */
public class C1178f extends AbstractC1174b {

    /* renamed from: c */
    public C0890c f2589c;

    /* renamed from: d */
    @Nullable
    public AbstractC1730a f2590d;

    /* renamed from: e */
    public C1570g f2591e;

    /* renamed from: f */
    public C0892e f2592f;

    /* renamed from: g */
    public C0894g f2593g;

    public C1178f(C1037g c1037g) {
        this.f2590d = c1037g.f1961a;
        this.f2589c = new C0890c(c1037g.f1962b);
        this.f2591e = c1037g.f1963c;
        this.f2592f = c1037g.f1964d;
        this.f2593g = c1037g.f1965e;
    }

    @Override // p075j.AbstractC1174b
    /* renamed from: a */
    public void mo807a(int i7, int i8) {
        List<C0888a> list = this.f2591e.f4717j;
        if (list == null) {
            return;
        }
        Iterator<C0888a> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                C0893f c0893f = (C0893f) this.f2592f.f1523b;
                c0893f.f1524a = false;
                c0893f.f1535l = false;
                c0893f.f1530g = false;
                return;
            }
            C0888a next = it.next();
            C0892e c0892e = this.f2592f;
            C0893f c0893f2 = (C0893f) c0892e.f1523b;
            if (c0893f2.f1524a || c0893f2.f1535l || c0893f2.f1530g) {
                C0892e c0892e2 = next.f1493l;
                Objects.requireNonNull(c0892e2);
                C0893f c0893f3 = (C0893f) c0892e.f1523b;
                float f7 = c0893f3.f1528e;
                C0893f c0893f4 = (C0893f) c0892e2.f1523b;
                c0893f4.f1528e = f7;
                c0893f4.f1524a = true;
                c0893f4.f1529f = c0893f3.f1529f;
                c0893f4.f1524a = true;
                c0893f4.f1525b = c0893f3.f1525b;
                c0893f4.f1524a = true;
                c0893f4.f1526c = c0893f3.f1526c;
                c0893f4.f1524a = true;
                c0893f4.f1527d = c0893f3.f1527d;
                c0893f4.f1524a = true;
                c0893f4.f1531h = c0893f3.f1531h;
                c0893f4.f1530g = true;
                c0893f4.f1536m.m1170b(c0893f3.f1536m.f2173d);
                c0893f4.f1535l = true;
                float f8 = ((C0893f) c0892e.f1523b).f1536m.f2174e;
                C0893f c0893f5 = (C0893f) c0892e2.f1523b;
                c0893f5.f1536m.m1172d(f8);
                c0893f5.f1535l = true;
                float f9 = ((C0893f) c0892e.f1523b).f1536m.f2175f;
                C0893f c0893f6 = (C0893f) c0892e2.f1523b;
                c0893f6.f1536m.m1171c(f9);
                c0893f6.f1535l = true;
            }
            next.f1496o = this.f2593g;
        }
    }

    @Override // p075j.AbstractC1174b
    /* renamed from: b */
    public void mo1374b() {
        this.f2590d = null;
    }

    @Override // p075j.AbstractC1174b
    /* renamed from: c */
    public void mo1375c(Context context) throws IOException {
        this.f2589c.m780a(context);
        AbstractC1730a abstractC1730a = this.f2590d;
        if (abstractC1730a != null) {
            abstractC1730a.mo1875a();
        }
    }

    @Override // p075j.AbstractC1174b
    /* renamed from: d */
    public void mo1376d(int i7, int i8, int i9, C0888a c0888a) {
        AbstractC1126a abstractC1126aMo1812f = this.f2591e.mo1812f();
        if (abstractC1126aMo1812f == null) {
            return;
        }
        c0888a.m775f(i8, i9);
        GLES20.glUseProgram(this.f2589c.f1505e);
        C0985b.m946b("MDPanoramaPlugin mProgram use");
        AbstractC1730a abstractC1730a = this.f2590d;
        if (abstractC1730a == null) {
            return;
        }
        abstractC1730a.mo1876b(this.f2589c);
        abstractC1126aMo1812f.mo1292e(this.f2589c, i7);
        abstractC1126aMo1812f.mo1291d(this.f2589c, i7);
        c0888a.m770a();
        c0888a.m776g(this.f2589c, this.f2591e.mo1813g());
        abstractC1126aMo1812f.m1288a();
    }
}
