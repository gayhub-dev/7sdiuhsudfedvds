package p075j;

import android.content.Context;
import android.graphics.PointF;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Objects;
import p035e.AbstractC0889b;
import p035e.C0888a;
import p035e.C0890c;
import p051g.C1031a;
import p067i.AbstractC1126a;
import p067i.C1128c;
import p099m.C1451b;

/* compiled from: MDBarrelDistortionLinePipe.java */
/* renamed from: j.c */
/* loaded from: classes.dex */
public class C1175c extends AbstractC1173a {

    /* renamed from: f */
    public boolean f2568f;

    /* renamed from: h */
    public C1031a f2570h;

    /* renamed from: i */
    public C1451b f2571i;

    /* renamed from: c */
    public C0890c f2565c = new C0890c(2);

    /* renamed from: e */
    public C0888a f2567e = new AbstractC0889b.c(new C0888a.a(), null);

    /* renamed from: d */
    public a f2566d = new a();

    /* renamed from: g */
    public C1176d f2569g = new C1176d();

    /* compiled from: MDBarrelDistortionLinePipe.java */
    /* renamed from: j.c$a */
    public class a extends AbstractC1126a {

        /* renamed from: e */
        public int f2572e;

        /* renamed from: f */
        public FloatBuffer f2573f;

        public a() {
        }

        @Override // p067i.AbstractC1126a
        /* renamed from: b */
        public void mo1289b(Context context) {
            float[] fArr;
            float[] fArr2;
            float[] fArr3;
            short[] sArr;
            float f7 = 1.0f;
            float f8 = 1.0f / 10;
            float[] fArr4 = new float[363];
            float[] fArr5 = new float[242];
            float[] fArr6 = new float[242];
            float[] fArr7 = new float[242];
            short[] sArr2 = new short[726];
            short s6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (true) {
                if (s6 >= 11) {
                    break;
                }
                short s7 = 0;
                for (short s8 = 11; s7 < s8; s8 = 11) {
                    int i9 = i7 + 1;
                    float f9 = s7 * f8;
                    fArr5[i7] = f9;
                    float f10 = s6 * f8;
                    fArr5[i9] = f10;
                    float f11 = f9 * 0.5f;
                    fArr6[i7] = f11;
                    fArr6[i9] = f10;
                    fArr7[i7] = f11 + 0.5f;
                    fArr7[i9] = f10;
                    int i10 = i8 + 1;
                    fArr4[i8] = (f9 * 2.0f) - 1.0f;
                    int i11 = i10 + 1;
                    fArr4[i10] = (f10 * 2.0f) - 1.0f;
                    fArr4[i11] = -8.0f;
                    s7 = (short) (s7 + 1);
                    i8 = i11 + 1;
                    i7 = i9 + 1;
                }
                s6 = (short) (s6 + 1);
            }
            PointF pointF = new PointF();
            int i12 = 0;
            while (i12 < 121) {
                int i13 = i12 * 3;
                int i14 = i13 + 1;
                pointF.set(fArr4[i13], fArr4[i14]);
                Objects.requireNonNull(C1175c.this.f2570h);
                Objects.requireNonNull(C1175c.this.f2570h);
                Objects.requireNonNull(C1175c.this.f2570h);
                double d7 = pointF.x;
                if (d7 == 0.0d) {
                    fArr = fArr7;
                    fArr2 = fArr5;
                    if (pointF.y == 0.0d) {
                        fArr3 = fArr6;
                        sArr = sArr2;
                    }
                    float f12 = pointF.x;
                    float f13 = C1175c.this.f2570h.f1941a;
                    fArr4[i13] = f12 * f13;
                    fArr4[i14] = pointF.y * f13;
                    i12++;
                    f7 = 1.0f;
                    sArr2 = sArr;
                    fArr6 = fArr3;
                    fArr5 = fArr2;
                    fArr7 = fArr;
                } else {
                    fArr = fArr7;
                    fArr2 = fArr5;
                }
                double d8 = f7;
                double d9 = (d7 - 0.0d) / d8;
                fArr3 = fArr6;
                sArr = sArr2;
                double d10 = (pointF.y - 0.0d) / d8;
                double dSqrt = Math.sqrt((d10 * d10) + (d9 * d9));
                double dAbs = Math.abs(dSqrt / (((((-0.2d) * dSqrt) + (((0.32d * dSqrt) * dSqrt) + ((((-0.068d) * dSqrt) * dSqrt) * dSqrt))) + 0.948d) * dSqrt));
                pointF.set((float) ((d9 * dAbs * d8) + 0.0d), (float) ((d10 * dAbs * d8) + 0.0d));
                float f122 = pointF.x;
                float f132 = C1175c.this.f2570h.f1941a;
                fArr4[i13] = f122 * f132;
                fArr4[i14] = pointF.y * f132;
                i12++;
                f7 = 1.0f;
                sArr2 = sArr;
                fArr6 = fArr3;
                fArr5 = fArr2;
                fArr7 = fArr;
            }
            float[] fArr8 = fArr7;
            float[] fArr9 = fArr5;
            float[] fArr10 = fArr6;
            short[] sArr3 = sArr2;
            int i15 = 0;
            for (short s9 = 0; s9 < 10; s9 = (short) (s9 + 1)) {
                short s10 = 0;
                while (s10 < 10) {
                    int i16 = s9 * 11;
                    int i17 = s10 + 1;
                    short s11 = (short) (i16 + i17);
                    int i18 = (s9 + 1) * 11;
                    short s12 = (short) (i18 + s10);
                    short s13 = (short) (i16 + s10);
                    short s14 = (short) (i18 + i17);
                    int i19 = i15 + 1;
                    sArr3[i15] = s11;
                    int i20 = i19 + 1;
                    sArr3[i19] = s12;
                    int i21 = i20 + 1;
                    sArr3[i20] = s13;
                    int i22 = i21 + 1;
                    sArr3[i21] = s11;
                    int i23 = i22 + 1;
                    sArr3[i22] = s14;
                    i15 = i23 + 1;
                    sArr3[i23] = s12;
                    s10 = (short) i17;
                }
            }
            FloatBuffer floatBufferM1293a = C1128c.m1293a(ByteBuffer.allocateDirect(1452), fArr4, 0);
            FloatBuffer floatBufferM1293a2 = C1128c.m1293a(ByteBuffer.allocateDirect(968), fArr9, 0);
            FloatBuffer floatBufferM1293a3 = C1128c.m1293a(ByteBuffer.allocateDirect(968), fArr10, 0);
            FloatBuffer floatBufferM1293a4 = C1128c.m1293a(ByteBuffer.allocateDirect(968), fArr8, 0);
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(1452);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect.asShortBuffer();
            shortBufferAsShortBuffer.put(sArr3);
            shortBufferAsShortBuffer.position(0);
            this.f2450a = shortBufferAsShortBuffer;
            this.f2452c.put(0, floatBufferM1293a3);
            this.f2452c.put(1, floatBufferM1293a4);
            this.f2453d.put(0, floatBufferM1293a);
            this.f2453d.put(1, floatBufferM1293a);
            this.f2451b = 726;
            this.f2573f = floatBufferM1293a2;
        }

        @Override // p067i.AbstractC1126a
        /* renamed from: c */
        public FloatBuffer mo1290c(int i7) {
            int i8 = this.f2572e;
            if (i8 == 1) {
                return this.f2573f;
            }
            if (i8 == 2) {
                return this.f2452c.get(i7);
            }
            return null;
        }
    }

    public C1175c(C1451b c1451b) {
        this.f2571i = c1451b;
        this.f2570h = c1451b.f4210f;
    }
}
