package p119o3;

import p035e.C0892e;

/* compiled from: So3Util.java */
/* renamed from: o3.e */
/* loaded from: classes.dex */
public class C1586e {
    /* renamed from: a */
    public static void m1847a(C1587f c1587f, C0892e c0892e) {
        double d7;
        double dM1849b = C1587f.m1849b(c1587f, c1587f);
        double dSqrt = Math.sqrt(dM1849b);
        double d8 = 0.5d;
        if (dM1849b < 1.0E-8d) {
            d7 = 1.0d - (dM1849b * 0.1666666716337204d);
        } else if (dM1849b < 1.0E-6d) {
            d8 = 0.5d - (0.0416666679084301d * dM1849b);
            double d9 = dM1849b * 0.1666666716337204d;
            d7 = 1.0d - ((1.0d - d9) * d9);
        } else {
            double d10 = 1.0d / dSqrt;
            double dSin = Math.sin(dSqrt) * d10;
            double dCos = d10 * d10 * (1.0d - Math.cos(dSqrt));
            d7 = dSin;
            d8 = dCos;
        }
        double d11 = c1587f.f4822a;
        double d12 = d11 * d11;
        double d13 = c1587f.f4823b;
        double d14 = d13 * d13;
        double d15 = c1587f.f4824c;
        double d16 = d15 * d15;
        c0892e.m789c0(0, 0, 1.0d - ((d14 + d16) * d8));
        c0892e.m789c0(1, 1, 1.0d - ((d16 + d12) * d8));
        c0892e.m789c0(2, 2, 1.0d - ((d12 + d14) * d8));
        double d17 = c1587f.f4824c * d7;
        double d18 = c1587f.f4822a * c1587f.f4823b * d8;
        c0892e.m789c0(0, 1, d18 - d17);
        c0892e.m789c0(1, 0, d18 + d17);
        double d19 = c1587f.f4823b * d7;
        double d20 = c1587f.f4822a * c1587f.f4824c * d8;
        c0892e.m789c0(0, 2, d20 + d19);
        c0892e.m789c0(2, 0, d20 - d19);
        double d21 = c1587f.f4822a * d7;
        double d22 = c1587f.f4823b * c1587f.f4824c * d8;
        c0892e.m789c0(1, 2, d22 - d21);
        c0892e.m789c0(2, 1, d22 + d21);
    }
}
