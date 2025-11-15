package p119o3;

import java.util.concurrent.TimeUnit;

/* compiled from: LowPassFilter.java */
/* renamed from: o3.b */
/* loaded from: classes.dex */
public class C1583b {

    /* renamed from: f */
    public static final double f4764f = 1.0d / TimeUnit.NANOSECONDS.convert(1, TimeUnit.SECONDS);

    /* renamed from: a */
    public final double f4765a;

    /* renamed from: c */
    public long f4767c;

    /* renamed from: d */
    public int f4768d;

    /* renamed from: b */
    public final C1587f f4766b = new C1587f();

    /* renamed from: e */
    public final C1587f f4769e = new C1587f();

    public C1583b(double d7) {
        this.f4765a = 1.0d / (d7 * 6.283185307179586d);
    }

    /* renamed from: a */
    public void m1841a(C1587f c1587f, long j7, double d7) {
        int i7 = this.f4768d + 1;
        this.f4768d = i7;
        if (i7 == 1) {
            this.f4766b.m1855g(c1587f);
            this.f4767c = j7;
            return;
        }
        double d8 = d7 * (j7 - this.f4767c) * f4764f;
        double d9 = d8 / (this.f4765a + d8);
        this.f4766b.m1853e(1.0d - d9);
        this.f4769e.m1855g(c1587f);
        this.f4769e.m1853e(d9);
        C1587f c1587f2 = this.f4769e;
        C1587f c1587f3 = this.f4766b;
        c1587f3.m1854f(c1587f3.f4822a + c1587f2.f4822a, c1587f3.f4823b + c1587f2.f4823b, c1587f3.f4824c + c1587f2.f4824c);
        this.f4767c = j7;
    }
}
