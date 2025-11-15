package p119o3;

/* compiled from: GyroscopeBiasEstimator.java */
/* renamed from: o3.a */
/* loaded from: classes.dex */
public class C1582a {

    /* renamed from: a */
    public C1583b f4756a;

    /* renamed from: b */
    public C1583b f4757b;

    /* renamed from: c */
    public C1583b f4758c;

    /* renamed from: d */
    public C1587f f4759d;

    /* renamed from: e */
    public C1587f f4760e;

    /* renamed from: f */
    public a f4761f;

    /* renamed from: g */
    public a f4762g;

    /* compiled from: GyroscopeBiasEstimator.java */
    /* renamed from: o3.a$a */
    public static class a {

        /* renamed from: a */
        public int f4763a;

        public a(int i7) {
        }
    }

    public C1582a() {
        m1840b();
    }

    /* renamed from: a */
    public void m1839a(C1587f c1587f, long j7) {
        this.f4757b.m1841a(c1587f, j7, 1.0d);
        C1587f.m1850i(c1587f, this.f4757b.f4766b, this.f4759d);
        a aVar = this.f4762g;
        if (this.f4759d.m1851c() < 0.00800000037997961d) {
            aVar.f4763a++;
        } else {
            aVar.f4763a = 0;
        }
        if (this.f4762g.f4763a >= 10) {
            if (!(this.f4761f.f4763a >= 10) || c1587f.m1851c() >= 0.3499999940395355d) {
                return;
            }
            double dMax = Math.max(0.0d, 1.0d - (c1587f.m1851c() / 0.3499999940395355d));
            this.f4758c.m1841a(this.f4757b.f4766b, j7, dMax * dMax);
        }
    }

    /* renamed from: b */
    public void m1840b() {
        this.f4759d = new C1587f();
        this.f4760e = new C1587f();
        this.f4756a = new C1583b(1.0d);
        this.f4757b = new C1583b(10.0d);
        this.f4758c = new C1583b(0.15000000596046448d);
        this.f4761f = new a(10);
        this.f4762g = new a(10);
    }
}
