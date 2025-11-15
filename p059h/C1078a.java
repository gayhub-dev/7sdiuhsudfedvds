package p059h;

import android.opengl.Matrix;
import p051g.AbstractC1039i;

/* compiled from: MDMutablePosition.java */
/* renamed from: h.a */
/* loaded from: classes.dex */
public class C1078a extends AbstractC1039i {

    /* renamed from: b */
    public float[] f2171b = null;

    /* renamed from: c */
    public float f2172c = 0.0f;

    /* renamed from: f */
    public float f2175f = 0.0f;

    /* renamed from: e */
    public float f2174e = 0.0f;

    /* renamed from: d */
    public float f2173d = 0.0f;

    /* renamed from: g */
    public boolean f2176g = true;

    @Override // p051g.AbstractC1039i
    /* renamed from: a */
    public float[] mo1036a() {
        if (this.f2171b == null) {
            float[] fArr = new float[16];
            this.f2171b = fArr;
            Matrix.setIdentityM(fArr, 0);
        }
        if (this.f2176g) {
            Matrix.setIdentityM(this.f2171b, 0);
            Matrix.rotateM(this.f2171b, 0, 0.0f, 1.0f, 0.0f, 0.0f);
            Matrix.rotateM(this.f2171b, 0, 0.0f, 0.0f, 1.0f, 0.0f);
            Matrix.rotateM(this.f2171b, 0, 0.0f, 0.0f, 0.0f, 1.0f);
            Matrix.translateM(this.f2171b, 0, 0.0f, 0.0f, this.f2172c);
            Matrix.rotateM(this.f2171b, 0, this.f2174e, 1.0f, 0.0f, 0.0f);
            Matrix.rotateM(this.f2171b, 0, this.f2173d, 0.0f, 1.0f, 0.0f);
            Matrix.rotateM(this.f2171b, 0, this.f2175f, 0.0f, 0.0f, 1.0f);
            this.f2176g = false;
        }
        return this.f2171b;
    }

    /* renamed from: b */
    public C1078a m1170b(float f7) {
        this.f2176g |= this.f2173d != f7;
        this.f2173d = f7;
        return this;
    }

    /* renamed from: c */
    public C1078a m1171c(float f7) {
        this.f2176g |= this.f2175f != f7;
        this.f2175f = f7;
        return this;
    }

    /* renamed from: d */
    public C1078a m1172d(float f7) {
        this.f2176g |= this.f2174e != f7;
        this.f2174e = f7;
        return this;
    }

    public String toString() {
        return "MDPosition{mX=0.0, mY=0.0, mZ=" + this.f2172c + ", mAngleX=0.0, mAngleY=0.0, mAngleZ=0.0, mPitch=" + this.f2173d + ", mYaw=" + this.f2174e + ", mRoll=" + this.f2175f + '}';
    }
}
