package p119o3;

import p009b.C0413b;

/* compiled from: Vector3d.java */
/* renamed from: o3.f */
/* loaded from: classes.dex */
public class C1587f {

    /* renamed from: a */
    public double f4822a;

    /* renamed from: b */
    public double f4823b;

    /* renamed from: c */
    public double f4824c;

    /* renamed from: a */
    public static void m1848a(C1587f c1587f, C1587f c1587f2, C1587f c1587f3) {
        double d7 = c1587f.f4823b;
        double d8 = c1587f2.f4824c;
        double d9 = c1587f.f4824c;
        double d10 = c1587f2.f4823b;
        double d11 = c1587f2.f4822a;
        double d12 = c1587f.f4822a;
        c1587f3.m1854f((d7 * d8) - (d9 * d10), (d9 * d11) - (d8 * d12), (d12 * d10) - (d7 * d11));
    }

    /* renamed from: b */
    public static double m1849b(C1587f c1587f, C1587f c1587f2) {
        return (c1587f.f4824c * c1587f2.f4824c) + (c1587f.f4823b * c1587f2.f4823b) + (c1587f.f4822a * c1587f2.f4822a);
    }

    /* renamed from: i */
    public static void m1850i(C1587f c1587f, C1587f c1587f2, C1587f c1587f3) {
        c1587f3.m1854f(c1587f.f4822a - c1587f2.f4822a, c1587f.f4823b - c1587f2.f4823b, c1587f.f4824c - c1587f2.f4824c);
    }

    /* renamed from: c */
    public double m1851c() {
        double d7 = this.f4822a;
        double d8 = this.f4823b;
        double d9 = (d8 * d8) + (d7 * d7);
        double d10 = this.f4824c;
        return Math.sqrt((d10 * d10) + d9);
    }

    /* renamed from: d */
    public void m1852d() {
        double dM1851c = m1851c();
        if (dM1851c != 0.0d) {
            m1853e(1.0d / dM1851c);
        }
    }

    /* renamed from: e */
    public void m1853e(double d7) {
        this.f4822a *= d7;
        this.f4823b *= d7;
        this.f4824c *= d7;
    }

    /* renamed from: f */
    public void m1854f(double d7, double d8, double d9) {
        this.f4822a = d7;
        this.f4823b = d8;
        this.f4824c = d9;
    }

    /* renamed from: g */
    public void m1855g(C1587f c1587f) {
        this.f4822a = c1587f.f4822a;
        this.f4823b = c1587f.f4823b;
        this.f4824c = c1587f.f4824c;
    }

    /* renamed from: h */
    public void m1856h() {
        this.f4824c = 0.0d;
        this.f4823b = 0.0d;
        this.f4822a = 0.0d;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("{ ");
        sbM112a.append(Double.toString(this.f4822a));
        sbM112a.append(", ");
        sbM112a.append(Double.toString(this.f4823b));
        sbM112a.append(", ");
        sbM112a.append(Double.toString(this.f4824c));
        sbM112a.append(" }");
        return sbM112a.toString();
    }
}
