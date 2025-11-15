package p115o;

import android.content.Context;
import android.graphics.RectF;
import android.os.Looper;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import p035e.AbstractC0889b;
import p035e.C0888a;
import p043f.C0984a;
import p043f.C0987d;
import p051g.AbstractC1039i;
import p051g.C1032b;
import p051g.C1037g;
import p051g.C1040j;
import p067i.AbstractC1126a;
import p075j.AbstractC1174b;
import p075j.C1179g;
import p091l.AbstractC1408c;
import p091l.InterfaceC1406a;
import p115o.C1569f;

/* compiled from: ProjectionModeManager.java */
/* renamed from: o.g */
/* loaded from: classes.dex */
public class C1570g extends AbstractC1408c<AbstractC1564a> implements InterfaceC1567d {

    /* renamed from: e */
    public RectF f4712e;

    /* renamed from: f */
    public AbstractC0889b f4713f;

    /* renamed from: g */
    public AbstractC1174b f4714g;

    /* renamed from: h */
    public C1037g f4715h;

    /* renamed from: i */
    public C0984a f4716i;

    /* renamed from: j */
    public final List<C0888a> f4717j;

    /* renamed from: k */
    public final C1032b f4718k;

    /* renamed from: l */
    public final AbstractC1174b f4719l;

    /* renamed from: m */
    public b f4720m;

    /* compiled from: ProjectionModeManager.java */
    /* renamed from: o.g$a */
    public class a extends C1179g {
        public a() {
        }

        @Override // p075j.AbstractC1174b
        /* renamed from: a */
        public void mo807a(int i7, int i8) {
            if (C1570g.this.f4717j.size() > 0) {
                C1570g c1570g = C1570g.this;
                C1032b c1032b = c1570g.f4718k;
                C1040j c1040j = c1570g.f4717j.get(0).f1495n;
                Objects.requireNonNull(c1032b);
                c1032b.f1943a = c1040j.m1039c() * 57.295776f;
                c1032b.f1944b = c1040j.m1043g() * 57.295776f;
                c1032b.f1945c = c1040j.m1040d() * 57.295776f;
                c1032b.f1946d = c1040j.m1039c();
                c1032b.f1947e = c1040j.m1043g();
                c1032b.f1948f = c1040j.m1040d();
                C1570g c1570g2 = C1570g.this;
                b bVar = c1570g2.f4720m;
                if (bVar != null) {
                    bVar.mo560a(c1570g2.f4718k);
                }
            }
        }
    }

    /* compiled from: ProjectionModeManager.java */
    /* renamed from: o.g$b */
    public interface b {
        /* renamed from: a */
        void mo560a(C1032b c1032b);
    }

    /* compiled from: ProjectionModeManager.java */
    /* renamed from: o.g$c */
    public static class c {

        /* renamed from: a */
        public RectF f4722a;

        /* renamed from: b */
        public AbstractC0889b f4723b;

        /* renamed from: c */
        public C1037g f4724c;

        /* renamed from: d */
        public C0984a f4725d;
    }

    /* compiled from: ProjectionModeManager.java */
    /* renamed from: o.g$d */
    public static class d implements Runnable {

        /* renamed from: e */
        public AbstractC1174b f4726e;

        public d(AbstractC1174b abstractC1174b) {
            this.f4726e = abstractC1174b;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                throw new RuntimeException("must call in gl thread");
            }
            AbstractC1174b abstractC1174b = this.f4726e;
            if (abstractC1174b != null) {
                abstractC1174b.mo1374b();
                this.f4726e = null;
            }
        }
    }

    public C1570g(int i7, C0987d c0987d, c cVar) {
        super(i7, c0987d);
        this.f4717j = new LinkedList();
        this.f4712e = cVar.f4722a;
        this.f4713f = cVar.f4723b;
        this.f4716i = cVar.f4725d;
        C1037g c1037g = cVar.f4724c;
        this.f4715h = c1037g;
        c1037g.f1963c = this;
        this.f4718k = new C1032b();
        this.f4719l = new a();
    }

    @Override // p091l.AbstractC1408c
    /* renamed from: a */
    public InterfaceC1406a mo1593a(int i7) {
        C1568e c1568e;
        if (this.f4716i != null) {
            C1568e c1568e2 = i7 != 9611 ? null : new C1568e(0.745f, 2);
            if (c1568e2 != null) {
                return c1568e2;
            }
        }
        switch (i7) {
            case 202:
                return new C1566c(this.f4712e, 180.0f, false);
            case 203:
                return new C1566c(this.f4712e, 230.0f, false);
            case 204:
                return new C1566c(this.f4712e, 180.0f, true);
            case 205:
                return new C1566c(this.f4712e, 230.0f, true);
            case 206:
            case 213:
                return new C1571h(2);
            case 207:
            case 208:
            case 209:
                return new C1569f(new C1569f.d(i7, this.f4712e));
            case 210:
                c1568e = new C1568e(1.0f, 1);
                break;
            case 211:
                c1568e = new C1568e(1.0f, 2);
                break;
            case 212:
                return new C1571h(1);
            case 214:
                return new C1565b(0);
            default:
                return new C1565b(1);
        }
        return c1568e;
    }

    @Override // p091l.AbstractC1408c
    /* renamed from: b */
    public void mo1594b(Context context) {
        super.mo1594b(context);
        AbstractC1174b abstractC1174b = this.f4714g;
        if (abstractC1174b != null) {
            this.f4144d.m956b(new d(abstractC1174b));
            this.f4714g = null;
        }
        this.f4717j.clear();
        AbstractC0889b abstractC0889bMo1811i = ((AbstractC1564a) this.f4142b).mo1811i();
        if (abstractC0889bMo1811i == null) {
            abstractC0889bMo1811i = this.f4713f;
        }
        for (int i7 = 0; i7 < 2; i7++) {
            this.f4717j.add(abstractC0889bMo1811i.mo779a(i7));
        }
    }

    @Override // p115o.InterfaceC1567d
    /* renamed from: f */
    public AbstractC1126a mo1812f() {
        return ((AbstractC1564a) this.f4142b).mo1812f();
    }

    @Override // p115o.InterfaceC1567d
    /* renamed from: g */
    public AbstractC1039i mo1813g() {
        return ((AbstractC1564a) this.f4142b).mo1813g();
    }
}
