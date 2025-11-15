package p051g;

import p133q.C1755a;

/* compiled from: MDQuaternion.java */
/* renamed from: g.j */
/* loaded from: classes.dex */
public class C1040j {

    /* renamed from: a */
    public final /* synthetic */ int f1970a;

    /* renamed from: b */
    public float[] f1971b;

    public C1040j(int i7) {
        this.f1970a = i7;
        if (i7 != 1) {
            this.f1971b = new float[]{1.0f, 0.0f, 0.0f, 0.0f};
            return;
        }
        float[] fArr = new float[4];
        this.f1971b = fArr;
        fArr[3] = 1.0f;
    }

    /* renamed from: a */
    public void m1037a(float[] fArr) {
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12 = fArr[0];
        float f13 = fArr[1];
        float f14 = fArr[2];
        float f15 = fArr[4];
        float f16 = fArr[5];
        float f17 = fArr[6];
        float f18 = fArr[8];
        float f19 = fArr[9];
        float f20 = fArr[10];
        if (f12 + f16 + f20 >= 0.0f) {
            float fSqrt = (float) Math.sqrt(r13 + 1.0f);
            f7 = fSqrt * 0.5f;
            float f21 = 0.5f / fSqrt;
            f10 = (f19 - f17) * f21;
            f11 = (f14 - f18) * f21;
            f8 = (f15 - f13) * f21;
        } else {
            if (f12 > f16 && f12 > f20) {
                float fSqrt2 = (float) Math.sqrt(((f12 + 1.0d) - f16) - f20);
                float f22 = 0.5f / fSqrt2;
                f7 = (f19 - f17) * f22;
                f10 = fSqrt2 * 0.5f;
                f9 = (f15 + f13) * f22;
                f8 = (f14 + f18) * f22;
            } else if (f16 > f20) {
                float fSqrt3 = (float) Math.sqrt(((f16 + 1.0d) - f12) - f20);
                float f23 = 0.5f / fSqrt3;
                float f24 = (f19 + f17) * f23;
                f7 = (f14 - f18) * f23;
                f11 = fSqrt3 * 0.5f;
                f10 = (f15 + f13) * f23;
                f8 = f24;
            } else {
                float fSqrt4 = (float) Math.sqrt(((f20 + 1.0d) - f12) - f16);
                float f25 = 0.5f / fSqrt4;
                f7 = (f15 - f13) * f25;
                f8 = fSqrt4 * 0.5f;
                f9 = (f19 + f17) * f25;
                f10 = (f14 + f18) * f25;
            }
            f11 = f9;
        }
        float[] fArr2 = this.f1971b;
        fArr2[0] = f7;
        fArr2[1] = f10;
        fArr2[2] = f11;
        fArr2[3] = f8;
    }

    /* renamed from: b */
    public int m1038b() {
        float[] fArr = this.f1971b;
        float f7 = fArr[0];
        float f8 = fArr[1];
        float f9 = (fArr[3] * f7) + (fArr[2] * f8);
        if (f9 > 0.499f) {
            return 1;
        }
        return f9 < -0.499f ? -1 : 0;
    }

    /* renamed from: c */
    public float m1039c() {
        float[] fArr = this.f1971b;
        float f7 = fArr[0];
        float f8 = fArr[1];
        float f9 = fArr[2];
        float f10 = fArr[3];
        int iM1038b = m1038b();
        if (iM1038b != 0) {
            return iM1038b * 3.1415927f * 0.5f;
        }
        float f11 = ((f7 * f8) - (f10 * f9)) * 2.0f;
        int i7 = C1755a.f4996a;
        if (f11 < -1.0f) {
            f11 = -1.0f;
        } else if (f11 > 1.0f) {
            f11 = 1.0f;
        }
        return (float) Math.asin(f11);
    }

    /* renamed from: d */
    public float m1040d() {
        float[] fArr = this.f1971b;
        float f7 = fArr[0];
        float f8 = fArr[1];
        float f9 = fArr[2];
        float f10 = fArr[3];
        int iM1038b = m1038b();
        if (iM1038b != 0) {
            return iM1038b * 2.0f * C1755a.m1923a(f9, f7);
        }
        return C1755a.m1923a(((f9 * f8) + (f7 * f10)) * 2.0f, 1.0f - (((f10 * f10) + (f8 * f8)) * 2.0f));
    }

    /* renamed from: e */
    public float m1041e() {
        return this.f1971b[0];
    }

    /* renamed from: f */
    public float m1042f() {
        return this.f1971b[1];
    }

    /* renamed from: g */
    public float m1043g() {
        float[] fArr = this.f1971b;
        float f7 = fArr[0];
        float f8 = fArr[1];
        float f9 = fArr[2];
        float f10 = fArr[3];
        if (m1038b() != 0) {
            return 0.0f;
        }
        return C1755a.m1923a(((f10 * f8) + (f7 * f9)) * 2.0f, 1.0f - (((f8 * f8) + (f9 * f9)) * 2.0f));
    }

    /* renamed from: h */
    public float m1044h() {
        return this.f1971b[2];
    }

    /* renamed from: i */
    public C1040j m1045i(float f7) {
        this.f1971b[0] = f7;
        return this;
    }

    /* renamed from: j */
    public C1040j m1046j(float f7) {
        this.f1971b[1] = f7;
        return this;
    }

    /* renamed from: k */
    public C1040j m1047k(float f7) {
        this.f1971b[2] = f7;
        return this;
    }

    public String toString() {
        switch (this.f1970a) {
            case 0:
                return String.format("MDQuaternion w=%f x=%f, y=%f, z=%f", Float.valueOf(this.f1971b[0]), Float.valueOf(this.f1971b[1]), Float.valueOf(this.f1971b[2]), Float.valueOf(this.f1971b[3]));
            default:
                return "MDVector3D{x=" + m1041e() + ", y=" + m1042f() + ", z=" + m1044h() + '}';
        }
    }
}
