package p119o3;

import java.util.Objects;
import p035e.C0892e;

/* compiled from: OrientationEKF.java */
/* renamed from: o3.c */
/* loaded from: classes.dex */
public class C1584c {

    /* renamed from: P */
    public boolean f4785P;

    /* renamed from: r */
    public long f4804r;

    /* renamed from: v */
    public float f4808v;

    /* renamed from: x */
    public int f4810x;

    /* renamed from: a */
    public double[] f4787a = new double[16];

    /* renamed from: b */
    public C0892e f4788b = new C0892e(3);

    /* renamed from: c */
    public C0892e f4789c = new C0892e(3);

    /* renamed from: d */
    public C0892e f4790d = new C0892e(3);

    /* renamed from: e */
    public C0892e f4791e = new C0892e(3);

    /* renamed from: f */
    public C0892e f4792f = new C0892e(3);

    /* renamed from: g */
    public C0892e f4793g = new C0892e(3);

    /* renamed from: h */
    public C0892e f4794h = new C0892e(3);

    /* renamed from: i */
    public C0892e f4795i = new C0892e(3);

    /* renamed from: j */
    public C0892e f4796j = new C0892e(3);

    /* renamed from: k */
    public C1587f f4797k = new C1587f();

    /* renamed from: l */
    public C1587f f4798l = new C1587f();

    /* renamed from: m */
    public C1587f f4799m = new C1587f();

    /* renamed from: n */
    public C1587f f4800n = new C1587f();

    /* renamed from: o */
    public C1587f f4801o = new C1587f();

    /* renamed from: p */
    public C1587f f4802p = new C1587f();

    /* renamed from: q */
    public C1587f f4803q = new C1587f();

    /* renamed from: s */
    public final C1587f f4805s = new C1587f();

    /* renamed from: t */
    public double f4806t = 0.0d;

    /* renamed from: u */
    public double f4807u = 0.0d;

    /* renamed from: w */
    public boolean f4809w = false;

    /* renamed from: y */
    public boolean f4811y = true;

    /* renamed from: z */
    public C0892e f4812z = new C0892e(3);

    /* renamed from: A */
    public C0892e f4770A = new C0892e(3);

    /* renamed from: B */
    public C1587f f4771B = new C1587f();

    /* renamed from: C */
    public C0892e f4772C = new C0892e(3);

    /* renamed from: D */
    public C0892e f4773D = new C0892e(3);

    /* renamed from: E */
    public C0892e f4774E = new C0892e(3);

    /* renamed from: F */
    public C0892e f4775F = new C0892e(3);

    /* renamed from: G */
    public C0892e f4776G = new C0892e(3);

    /* renamed from: H */
    public C0892e f4777H = new C0892e(3);

    /* renamed from: I */
    public C0892e f4778I = new C0892e(3);

    /* renamed from: J */
    public C1587f f4779J = new C1587f();

    /* renamed from: K */
    public C1587f f4780K = new C1587f();

    /* renamed from: L */
    public C1587f f4781L = new C1587f();

    /* renamed from: M */
    public C0892e f4782M = new C0892e(3);

    /* renamed from: N */
    public C0892e f4783N = new C0892e(3);

    /* renamed from: O */
    public C0892e f4784O = new C0892e(3);

    /* renamed from: Q */
    public C1585d f4786Q = new C1585d();

    public C1584c() {
        m1844c();
    }

    /* renamed from: a */
    public final void m1842a(C0892e c0892e, C1587f c1587f) {
        C0892e.m783a0(c0892e, this.f4802p, this.f4799m);
        this.f4786Q.m1846a(this.f4799m, this.f4798l, this.f4784O);
        C1585d c1585d = this.f4786Q;
        C0892e c0892e2 = this.f4784O;
        Objects.requireNonNull(c1585d);
        double dM786X = ((c0892e2.m786X(2, 2) + (c0892e2.m786X(1, 1) + c0892e2.m786X(0, 0))) - 1.0d) * 0.5d;
        c1587f.m1854f((c0892e2.m786X(2, 1) - c0892e2.m786X(1, 2)) / 2.0d, (c0892e2.m786X(0, 2) - c0892e2.m786X(2, 0)) / 2.0d, (c0892e2.m786X(1, 0) - c0892e2.m786X(0, 1)) / 2.0d);
        double dM1851c = c1587f.m1851c();
        if (dM786X > 0.7071067811865476d) {
            if (dM1851c > 0.0d) {
                c1587f.m1853e(Math.asin(dM1851c) / dM1851c);
                return;
            }
            return;
        }
        if (dM786X > -0.7071067811865476d) {
            c1587f.m1853e(Math.acos(dM786X) / dM1851c);
            return;
        }
        double dAsin = 3.141592653589793d - Math.asin(dM1851c);
        double dM786X2 = c0892e2.m786X(0, 0) - dM786X;
        double dM786X3 = c0892e2.m786X(1, 1) - dM786X;
        double dM786X4 = c0892e2.m786X(2, 2) - dM786X;
        C1587f c1587f2 = c1585d.f4820h;
        double d7 = dM786X2 * dM786X2;
        double d8 = dM786X3 * dM786X3;
        if (d7 > d8 && d7 > dM786X4 * dM786X4) {
            c1587f2.m1854f(dM786X2, (c0892e2.m786X(0, 1) + c0892e2.m786X(1, 0)) / 2.0d, (c0892e2.m786X(2, 0) + c0892e2.m786X(0, 2)) / 2.0d);
        } else if (d8 > dM786X4 * dM786X4) {
            c1587f2.m1854f((c0892e2.m786X(0, 1) + c0892e2.m786X(1, 0)) / 2.0d, dM786X3, (c0892e2.m786X(1, 2) + c0892e2.m786X(2, 1)) / 2.0d);
        } else {
            c1587f2.m1854f((c0892e2.m786X(2, 0) + c0892e2.m786X(0, 2)) / 2.0d, (c0892e2.m786X(1, 2) + c0892e2.m786X(2, 1)) / 2.0d, dM786X4);
        }
        if (C1587f.m1849b(c1587f2, c1587f) < 0.0d) {
            c1587f2.m1853e(-1.0d);
        }
        c1587f2.m1852d();
        c1587f2.m1853e(dAsin);
        c1587f.m1855g(c1587f2);
    }

    /* renamed from: b */
    public double[] m1843b(double d7) {
        C1587f c1587f = this.f4771B;
        c1587f.m1855g(this.f4805s);
        c1587f.m1853e(-d7);
        C0892e c0892e = this.f4812z;
        C1586e.m1847a(c1587f, c0892e);
        C0892e c0892e2 = this.f4770A;
        C0892e.m782Z(c0892e, this.f4788b, c0892e2);
        for (int i7 = 0; i7 < 3; i7++) {
            for (int i8 = 0; i8 < 3; i8++) {
                this.f4787a[(i8 * 4) + i7] = c0892e2.m786X(i7, i8);
            }
        }
        double[] dArr = this.f4787a;
        dArr[11] = 0.0d;
        dArr[7] = 0.0d;
        dArr[3] = 0.0d;
        dArr[14] = 0.0d;
        dArr[13] = 0.0d;
        dArr[12] = 0.0d;
        dArr[15] = 1.0d;
        return dArr;
    }

    /* renamed from: c */
    public synchronized void m1844c() {
        this.f4804r = 0L;
        this.f4788b.m792f0();
        this.f4789c.m792f0();
        this.f4790d.m794h0();
        this.f4790d.m793g0(25.0d);
        this.f4791e.m794h0();
        this.f4791e.m793g0(1.0d);
        this.f4792f.m794h0();
        this.f4792f.m793g0(0.0625d);
        this.f4793g.m794h0();
        this.f4793g.m793g0(0.5625d);
        this.f4794h.m794h0();
        this.f4795i.m794h0();
        this.f4796j.m794h0();
        this.f4797k.m1856h();
        this.f4798l.m1856h();
        this.f4799m.m1856h();
        this.f4800n.m1856h();
        this.f4801o.m1856h();
        this.f4802p.m1854f(0.0d, 0.0d, 9.81d);
        this.f4803q.m1854f(0.0d, 1.0d, 0.0d);
        this.f4785P = false;
    }

    /* renamed from: d */
    public final void m1845d() {
        this.f4789c.m795i0(this.f4782M);
        C0892e.m782Z(this.f4790d, this.f4782M, this.f4783N);
        C0892e.m782Z(this.f4789c, this.f4783N, this.f4790d);
        this.f4789c.m792f0();
    }
}
