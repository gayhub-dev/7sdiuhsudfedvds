package p111n3;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.Matrix;
import android.view.Display;
import java.util.Objects;
import p035e.C0892e;
import p043f.C0984a;
import p119o3.C1582a;
import p119o3.C1583b;
import p119o3.C1584c;
import p119o3.C1586e;
import p119o3.C1587f;

/* compiled from: HeadTracker.java */
/* renamed from: n3.d */
/* loaded from: classes.dex */
public class C1524d implements SensorEventListener {

    /* renamed from: a */
    public final Display f4403a;

    /* renamed from: e */
    public final float[] f4407e;

    /* renamed from: h */
    public volatile boolean f4410h;

    /* renamed from: i */
    public final C1584c f4411i;

    /* renamed from: j */
    public final Object f4412j;

    /* renamed from: k */
    public C1582a f4413k;

    /* renamed from: l */
    public InterfaceC1525e f4414l;

    /* renamed from: m */
    public C0984a f4415m;

    /* renamed from: n */
    public long f4416n;

    /* renamed from: o */
    public volatile boolean f4417o;

    /* renamed from: p */
    public float[] f4418p;

    /* renamed from: q */
    public final C1587f f4419q;

    /* renamed from: r */
    public final C1587f f4420r;

    /* renamed from: s */
    public final C1587f f4421s;

    /* renamed from: b */
    public final float[] f4404b = new float[16];

    /* renamed from: c */
    public final float[] f4405c = new float[16];

    /* renamed from: d */
    public float f4406d = -1.0f;

    /* renamed from: f */
    public final float[] f4408f = new float[16];

    /* renamed from: g */
    public final float[] f4409g = new float[16];

    public C1524d(InterfaceC1525e interfaceC1525e, C0984a c0984a, Display display) {
        float[] fArr = new float[16];
        this.f4407e = fArr;
        Object obj = new Object();
        this.f4412j = obj;
        this.f4417o = true;
        this.f4418p = new float[3];
        this.f4419q = new C1587f();
        this.f4420r = new C1587f();
        this.f4421s = new C1587f();
        this.f4415m = c0984a;
        this.f4414l = interfaceC1525e;
        this.f4411i = new C1584c();
        this.f4403a = display;
        synchronized (obj) {
            if (this.f4413k == null) {
                this.f4413k = new C1582a();
            }
        }
        Matrix.setIdentityM(fArr, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0025  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m1708a(float[] r17, int r18) {
        /*
            Method dump skipped, instructions count: 198
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p111n3.C1524d.m1708a(float[], int):void");
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i7) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            C1587f c1587f = this.f4421s;
            float[] fArr = sensorEvent.values;
            c1587f.m1854f(fArr[0], fArr[1], fArr[2]);
            C1584c c1584c = this.f4411i;
            C1587f c1587f2 = this.f4421s;
            synchronized (c1584c) {
                c1584c.f4798l.m1855g(c1587f2);
                double dM1851c = c1584c.f4798l.m1851c();
                double dAbs = Math.abs(dM1851c - c1584c.f4806t);
                c1584c.f4806t = dM1851c;
                double d7 = (c1584c.f4807u * 0.5d) + (dAbs * 0.5d);
                c1584c.f4807u = d7;
                double dMin = Math.min(7.0d, ((d7 / 0.15d) * 6.25d) + 0.75d);
                c1584c.f4793g.m793g0(dMin * dMin);
                if (c1584c.f4785P) {
                    c1584c.m1842a(c1584c.f4788b, c1584c.f4797k);
                    for (int i7 = 0; i7 < 3; i7++) {
                        C1587f c1587f3 = c1584c.f4781L;
                        c1587f3.m1856h();
                        if (i7 == 0) {
                            c1587f3.f4822a = 1.0E-7d;
                        } else if (i7 == 1) {
                            c1587f3.f4823b = 1.0E-7d;
                        } else {
                            c1587f3.f4824c = 1.0E-7d;
                        }
                        C1586e.m1847a(c1587f3, c1584c.f4774E);
                        C0892e.m782Z(c1584c.f4774E, c1584c.f4788b, c1584c.f4775F);
                        c1584c.m1842a(c1584c.f4775F, c1584c.f4779J);
                        C1587f.m1850i(c1584c.f4797k, c1584c.f4779J, c1584c.f4780K);
                        c1584c.f4780K.m1853e(1.0E7d);
                        c1584c.f4795i.m791e0(i7, c1584c.f4780K);
                    }
                    c1584c.f4795i.m795i0(c1584c.f4776G);
                    C0892e.m782Z(c1584c.f4790d, c1584c.f4776G, c1584c.f4777H);
                    C0892e.m782Z(c1584c.f4795i, c1584c.f4777H, c1584c.f4778I);
                    C0892e.m781V(c1584c.f4778I, c1584c.f4793g, c1584c.f4794h);
                    c1584c.f4794h.m787Y(c1584c.f4776G);
                    c1584c.f4795i.m795i0(c1584c.f4777H);
                    C0892e.m782Z(c1584c.f4777H, c1584c.f4776G, c1584c.f4778I);
                    C0892e.m782Z(c1584c.f4790d, c1584c.f4778I, c1584c.f4796j);
                    C0892e.m783a0(c1584c.f4796j, c1584c.f4797k, c1584c.f4801o);
                    C0892e.m782Z(c1584c.f4796j, c1584c.f4795i, c1584c.f4776G);
                    c1584c.f4777H.m792f0();
                    C0892e c0892e = c1584c.f4777H;
                    C0892e c0892e2 = c1584c.f4776G;
                    Objects.requireNonNull(c0892e);
                    for (int i8 = 0; i8 < 9; i8++) {
                        double[] dArr = (double[]) c0892e.f1523b;
                        dArr[i8] = dArr[i8] - ((double[]) c0892e2.f1523b)[i8];
                    }
                    C0892e.m782Z(c1584c.f4777H, c1584c.f4790d, c1584c.f4776G);
                    c1584c.f4790d.m790d0(c1584c.f4776G);
                    C1586e.m1847a(c1584c.f4801o, c1584c.f4789c);
                    C0892e c0892e3 = c1584c.f4789c;
                    C0892e c0892e4 = c1584c.f4788b;
                    C0892e.m782Z(c0892e3, c0892e4, c0892e4);
                    c1584c.m1845d();
                } else {
                    c1584c.f4786Q.m1846a(c1584c.f4802p, c1584c.f4798l, c1584c.f4788b);
                    c1584c.f4785P = true;
                }
            }
            synchronized (this.f4412j) {
                C1582a c1582a = this.f4413k;
                if (c1582a != null) {
                    C1587f c1587f4 = this.f4421s;
                    c1582a.f4756a.m1841a(c1587f4, sensorEvent.timestamp, 1.0d);
                    C1587f.m1850i(c1587f4, c1582a.f4756a.f4766b, c1582a.f4760e);
                    C1582a.a aVar = c1582a.f4761f;
                    if (c1582a.f4760e.m1851c() < 0.5d) {
                        aVar.f4763a++;
                    } else {
                        aVar.f4763a = 0;
                    }
                }
            }
            return;
        }
        if (sensorEvent.sensor.getType() == 4 || sensorEvent.sensor.getType() == 16) {
            Objects.requireNonNull(this.f4415m);
            this.f4416n = System.nanoTime();
            if (sensorEvent.sensor.getType() == 16) {
                if (this.f4417o) {
                    float[] fArr2 = sensorEvent.values;
                    if (fArr2.length == 6) {
                        float[] fArr3 = this.f4418p;
                        fArr3[0] = fArr2[3];
                        fArr3[1] = fArr2[4];
                        fArr3[2] = fArr2[5];
                    }
                }
                C1587f c1587f5 = this.f4420r;
                float f7 = sensorEvent.values[0];
                float[] fArr4 = this.f4418p;
                c1587f5.m1854f(f7 - fArr4[0], r2[1] - fArr4[1], r2[2] - fArr4[2]);
            } else {
                C1587f c1587f6 = this.f4420r;
                float[] fArr5 = sensorEvent.values;
                c1587f6.m1854f(fArr5[0], fArr5[1], fArr5[2]);
            }
            this.f4417o = false;
            synchronized (this.f4412j) {
                C1582a c1582a2 = this.f4413k;
                if (c1582a2 != null) {
                    c1582a2.m1839a(this.f4420r, sensorEvent.timestamp);
                    C1582a c1582a3 = this.f4413k;
                    C1587f c1587f7 = this.f4419q;
                    C1583b c1583b = c1582a3.f4758c;
                    if (c1583b.f4768d < 30) {
                        c1587f7.m1856h();
                    } else {
                        c1587f7.m1855g(c1583b.f4766b);
                        c1587f7.m1853e(Math.min(1.0d, (c1582a3.f4758c.f4768d - 30) / 100.0d));
                    }
                    C1587f c1587f8 = this.f4420r;
                    C1587f.m1850i(c1587f8, this.f4419q, c1587f8);
                }
            }
            C1584c c1584c2 = this.f4411i;
            C1587f c1587f9 = this.f4420r;
            long j7 = sensorEvent.timestamp;
            synchronized (c1584c2) {
                if (c1584c2.f4804r != 0) {
                    float f8 = (j7 - r9) * 1.0E-9f;
                    if (f8 > 0.04f) {
                        f8 = c1584c2.f4811y ? c1584c2.f4808v : 0.01f;
                    } else if (c1584c2.f4809w) {
                        c1584c2.f4808v = (0.050000012f * f8) + (c1584c2.f4808v * 0.95f);
                        int i9 = c1584c2.f4810x + 1;
                        c1584c2.f4810x = i9;
                        if (i9 > 10.0f) {
                            c1584c2.f4811y = true;
                        }
                    } else {
                        c1584c2.f4808v = f8;
                        c1584c2.f4810x = 1;
                        c1584c2.f4809w = true;
                    }
                    c1584c2.f4800n.m1855g(c1587f9);
                    c1584c2.f4800n.m1853e(-f8);
                    C1586e.m1847a(c1584c2.f4800n, c1584c2.f4789c);
                    c1584c2.f4772C.m790d0(c1584c2.f4788b);
                    C0892e.m782Z(c1584c2.f4789c, c1584c2.f4788b, c1584c2.f4772C);
                    c1584c2.f4788b.m790d0(c1584c2.f4772C);
                    c1584c2.m1845d();
                    c1584c2.f4773D.m790d0(c1584c2.f4791e);
                    C0892e c0892e5 = c1584c2.f4773D;
                    double d8 = f8 * f8;
                    Objects.requireNonNull(c0892e5);
                    for (int i10 = 0; i10 < 9; i10++) {
                        double[] dArr2 = (double[]) c0892e5.f1523b;
                        dArr2[i10] = dArr2[i10] * d8;
                    }
                    C0892e c0892e6 = c1584c2.f4790d;
                    C0892e c0892e7 = c1584c2.f4773D;
                    Objects.requireNonNull(c0892e6);
                    for (int i11 = 0; i11 < 9; i11++) {
                        double[] dArr3 = (double[]) c0892e6.f1523b;
                        dArr3[i11] = dArr3[i11] + ((double[]) c0892e7.f1523b)[i11];
                    }
                }
                c1584c2.f4804r = j7;
                c1584c2.f4805s.m1855g(c1587f9);
            }
        }
    }
}
