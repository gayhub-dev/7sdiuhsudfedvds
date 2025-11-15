package p035e;

import android.opengl.GLES20;
import android.opengl.Matrix;
import java.util.Objects;
import p035e.C0901n;
import p051g.AbstractC1039i;
import p051g.C1040j;
import p059h.C1078a;

/* compiled from: MD360Director.java */
/* renamed from: e.a */
/* loaded from: classes.dex */
public class C0888a {

    /* renamed from: k */
    public final C0893f f1492k;

    /* renamed from: n */
    public final C1040j f1495n;

    /* renamed from: o */
    public C0894g f1496o;

    /* renamed from: p */
    public float f1497p;

    /* renamed from: q */
    public float f1498q;

    /* renamed from: r */
    public boolean f1499r;

    /* renamed from: a */
    public float[] f1482a = new float[16];

    /* renamed from: b */
    public float[] f1483b = new float[16];

    /* renamed from: c */
    public float[] f1484c = new float[16];

    /* renamed from: d */
    public float[] f1485d = new float[16];

    /* renamed from: e */
    public float[] f1486e = new float[16];

    /* renamed from: f */
    public float[] f1487f = new float[16];

    /* renamed from: g */
    public float[] f1488g = new float[16];

    /* renamed from: h */
    public float[] f1489h = new float[16];

    /* renamed from: i */
    public float[] f1490i = new float[16];

    /* renamed from: j */
    public float[] f1491j = new float[16];

    /* renamed from: l */
    public final C0892e f1493l = new C0892e(0);

    /* renamed from: m */
    public final C1078a f1494m = new C1078a();

    /* compiled from: MD360Director.java */
    /* renamed from: e.a$a */
    public static class a {

        /* renamed from: a */
        public C0893f f1500a = new C0893f();
    }

    public C0888a(a aVar) {
        C1040j c1040j = new C1040j(0);
        this.f1495n = c1040j;
        this.f1499r = true;
        this.f1492k = aVar.f1500a;
        Matrix.setIdentityM(this.f1482a, 0);
        Matrix.setIdentityM(this.f1489h, 0);
        c1040j.m1037a(this.f1482a);
    }

    /* renamed from: a */
    public void m770a() {
        if (this.f1492k.f1530g || ((C0893f) this.f1493l.f1523b).f1530g) {
            mo777h();
            this.f1492k.f1530g = false;
            ((C0893f) this.f1493l.f1523b).f1530g = false;
        }
        C0893f c0893f = this.f1492k;
        boolean z6 = c0893f.f1524a || ((C0893f) this.f1493l.f1523b).f1524a;
        boolean z7 = this.f1499r || c0893f.f1535l || ((C0893f) this.f1493l.f1523b).f1535l;
        if (z6) {
            float f7 = c0893f.f1525b;
            C0893f c0893f2 = (C0893f) this.f1493l.f1523b;
            float f8 = f7 + c0893f2.f1525b;
            float f9 = c0893f.f1526c + c0893f2.f1526c;
            float f10 = c0893f.f1527d + c0893f2.f1527d;
            float f11 = c0893f.f1528e + c0893f2.f1528e;
            float f12 = c0893f.f1529f + c0893f2.f1529f;
            Matrix.setIdentityM(this.f1491j, 0);
            Matrix.setLookAtM(this.f1491j, 0, f8, f9, f10, f11, f12, -1.0f, 0.0f, 1.0f, 0.0f);
            this.f1492k.f1524a = false;
            ((C0893f) this.f1493l.f1523b).f1524a = false;
        }
        if (z7) {
            this.f1494m.m1170b(this.f1492k.f1536m.f2173d + ((C0893f) this.f1493l.f1523b).f1536m.f2173d);
            this.f1494m.m1171c(this.f1492k.f1536m.f2175f + ((C0893f) this.f1493l.f1523b).f1536m.f2175f);
            this.f1494m.m1172d(this.f1492k.f1536m.f2174e + ((C0893f) this.f1493l.f1523b).f1536m.f2174e);
            Matrix.setIdentityM(this.f1486e, 0);
            Matrix.rotateM(this.f1486e, 0, -this.f1498q, 1.0f, 0.0f, 0.0f);
            Matrix.setIdentityM(this.f1488g, 0);
            Matrix.rotateM(this.f1488g, 0, -this.f1497p, 0.0f, 1.0f, 0.0f);
            Matrix.setIdentityM(this.f1490i, 0);
            Matrix.multiplyMM(this.f1490i, 0, this.f1488g, 0, this.f1494m.mo1036a(), 0);
            Matrix.multiplyMM(this.f1488g, 0, this.f1489h, 0, this.f1490i, 0);
            Matrix.multiplyMM(this.f1490i, 0, this.f1486e, 0, this.f1488g, 0);
            System.arraycopy(this.f1490i, 0, this.f1486e, 0, 16);
            float[] fArr = this.f1487f;
            float[] fArr2 = this.f1486e;
            if (!(fArr2 == fArr ? false : Matrix.invertM(fArr, 0, fArr2, 0))) {
                Matrix.setIdentityM(this.f1487f, 0);
            }
            this.f1499r = false;
            this.f1492k.f1535l = false;
            ((C0893f) this.f1493l.f1523b).f1535l = false;
        }
        if (z6 || z7) {
            Matrix.multiplyMM(this.f1482a, 0, this.f1491j, 0, this.f1486e, 0);
            if (this.f1496o == null) {
                return;
            }
            this.f1495n.m1037a(this.f1482a);
            float fM1039c = this.f1495n.m1039c() * 57.295776f;
            float fM1043g = this.f1495n.m1043g() * 57.295776f;
            float fM1040d = this.f1495n.m1040d() * 57.295776f;
            C0901n.d dVar = this.f1496o.f1537a;
            float fM796a = dVar != null ? ((C0894g) dVar).m796a(fM1039c) : fM1039c;
            C0901n.d dVar2 = this.f1496o.f1537a;
            float fM798c = dVar2 != null ? ((C0894g) dVar2).m798c(fM1043g) : fM1043g;
            C0901n.d dVar3 = this.f1496o.f1537a;
            float fM797b = dVar3 != null ? ((C0894g) dVar3).m797b(fM1040d) : fM1040d;
            if (fM1039c == fM796a && fM1043g == fM798c && fM1040d == fM797b) {
                return;
            }
            C1040j c1040j = this.f1495n;
            Objects.requireNonNull(c1040j);
            double d7 = fM797b * 0.017453292f * 0.5f;
            float fSin = (float) Math.sin(d7);
            float fCos = (float) Math.cos(d7);
            double d8 = fM796a * 0.017453292f * 0.5f;
            float fSin2 = (float) Math.sin(d8);
            float fCos2 = (float) Math.cos(d8);
            double d9 = fM798c * 0.017453292f * 0.5f;
            float fSin3 = (float) Math.sin(d9);
            float fCos3 = (float) Math.cos(d9);
            float f13 = fCos3 * fSin2;
            float f14 = fSin3 * fCos2;
            float f15 = fCos3 * fCos2;
            float f16 = fSin3 * fSin2;
            float[] fArr3 = c1040j.f1971b;
            fArr3[1] = (f14 * fSin) + (f13 * fCos);
            fArr3[2] = (f14 * fCos) - (f13 * fSin);
            fArr3[3] = (f15 * fSin) - (f16 * fCos);
            fArr3[0] = (f16 * fSin) + (f15 * fCos);
            C1040j c1040j2 = this.f1495n;
            float[] fArr4 = this.f1482a;
            float[] fArr5 = c1040j2.f1971b;
            float f17 = fArr5[1] * fArr5[1];
            float f18 = fArr5[1] * fArr5[2];
            float f19 = fArr5[1] * fArr5[3];
            float f20 = fArr5[1] * fArr5[0];
            float f21 = fArr5[2] * fArr5[2];
            float f22 = fArr5[2] * fArr5[3];
            float f23 = fArr5[2] * fArr5[0];
            float f24 = fArr5[3] * fArr5[3];
            float f25 = fArr5[3] * fArr5[0];
            fArr4[0] = 1.0f - ((f21 + f24) * 2.0f);
            fArr4[1] = (f18 - f25) * 2.0f;
            fArr4[2] = (f19 + f23) * 2.0f;
            fArr4[4] = (f18 + f25) * 2.0f;
            fArr4[5] = 1.0f - ((f24 + f17) * 2.0f);
            fArr4[6] = (f22 - f20) * 2.0f;
            fArr4[8] = (f19 - f23) * 2.0f;
            fArr4[9] = (f22 + f20) * 2.0f;
            fArr4[10] = 1.0f - ((f17 + f21) * 2.0f);
            fArr4[14] = 0.0f;
            fArr4[13] = 0.0f;
            fArr4[12] = 0.0f;
            fArr4[11] = 0.0f;
            fArr4[7] = 0.0f;
            fArr4[3] = 0.0f;
            fArr4[15] = 1.0f;
        }
    }

    /* renamed from: b */
    public float m771b() {
        return (this.f1492k.f1531h + ((C0893f) this.f1493l.f1523b).f1531h) * 0.7f;
    }

    /* renamed from: c */
    public void m772c() {
        this.f1498q = 0.0f;
        this.f1497p = 0.0f;
        Matrix.setIdentityM(this.f1489h, 0);
        this.f1499r = true;
    }

    /* renamed from: d */
    public void mo773d(float f7) {
        this.f1497p = f7;
        this.f1499r = true;
    }

    /* renamed from: e */
    public void mo774e(float f7) {
        this.f1498q = f7;
        this.f1499r = true;
    }

    /* renamed from: f */
    public void m775f(int i7, int i8) {
        C0893f c0893f = this.f1492k;
        c0893f.f1533j = i7;
        c0893f.f1534k = i8;
        c0893f.f1532i = (i7 * 1.0f) / i8;
        c0893f.f1530g = true;
    }

    /* renamed from: g */
    public void m776g(C0890c c0890c, AbstractC1039i abstractC1039i) {
        Matrix.multiplyMM(this.f1484c, 0, this.f1482a, 0, abstractC1039i.mo1036a(), 0);
        Matrix.multiplyMM(this.f1485d, 0, this.f1483b, 0, this.f1484c, 0);
        GLES20.glUniformMatrix4fv(c0890c.f1502b, 1, false, this.f1484c, 0);
        GLES20.glUniformMatrix4fv(c0890c.f1501a, 1, false, this.f1485d, 0);
    }

    /* renamed from: h */
    public void mo777h() {
        float f7 = this.f1492k.f1532i;
        Matrix.frustumM(this.f1483b, 0, (-f7) / 2.0f, f7 / 2.0f, -0.5f, 0.5f, m771b(), 500.0f);
    }

    /* renamed from: i */
    public void mo778i(float[] fArr) {
        if (fArr == null || fArr.length != 16 || Float.isNaN(fArr[0]) || Float.isNaN(fArr[1])) {
            return;
        }
        System.arraycopy(fArr, 0, this.f1489h, 0, 16);
        this.f1499r = true;
    }
}
