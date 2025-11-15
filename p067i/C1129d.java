package p067i;

import android.content.Context;
import android.graphics.RectF;
import com.alibaba.fastjson.asm.Opcodes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import p035e.C0890c;

/* compiled from: MDDome3D.java */
/* renamed from: i.d */
/* loaded from: classes.dex */
public class C1129d extends AbstractC1126a {

    /* renamed from: e */
    public float f2454e;

    /* renamed from: f */
    public boolean f2455f;

    /* renamed from: g */
    public RectF f2456g;

    /* renamed from: h */
    public float f2457h = 1.0f;

    /* renamed from: i */
    public float[] f2458i;

    public C1129d(RectF rectF, float f7, boolean z6) {
        this.f2456g = rectF;
        this.f2454e = f7;
        this.f2455f = z6;
    }

    @Override // p067i.AbstractC1126a
    /* renamed from: b */
    public void mo1289b(Context context) {
        float f7 = this.f2454e;
        boolean z6 = this.f2455f;
        float f8 = f7 / 360.0f;
        float f9 = 75;
        float f10 = 1.0f / f9;
        float f11 = 1.0f / 150;
        int i7 = ((int) (f9 * f8)) + 1;
        int i8 = i7 * Opcodes.DCMPL;
        int i9 = i8 * 3;
        float[] fArr = new float[i9];
        int i10 = i8 * 2;
        float[] fArr2 = new float[i10];
        int i11 = i8 * 6;
        short[] sArr = new short[i11];
        int i12 = z6 ? 1 : -1;
        short s6 = 0;
        int i13 = 0;
        int i14 = 0;
        while (true) {
            if (s6 >= i7) {
                break;
            }
            short s7 = 0;
            for (short s8 = 151; s7 < s8; s8 = 151) {
                int i15 = i11;
                double d7 = s7 * 6.2831855f * f11;
                float f12 = f11;
                float[] fArr3 = fArr;
                double d8 = s6 * 3.1415927f * f10;
                int i16 = i14;
                float fSin = ((float) (Math.sin(d8) * Math.cos(d7))) * i12;
                int i17 = i7;
                float fSin2 = ((float) Math.sin(r4 - 1.5707964f)) * (-i12);
                float fSin3 = (float) (Math.sin(d8) * Math.sin(d7));
                int i18 = i12;
                double d9 = s6;
                short[] sArr2 = sArr;
                double d10 = f10;
                double d11 = f8;
                float fCos = (((float) (((Math.cos(d7) * d9) * d10) / d11)) / 2.0f) + 0.5f;
                int i19 = i13 + 1;
                fArr2[i13] = (((float) (((Math.sin(d7) * d9) * d10) / d11)) / 2.0f) + 0.5f;
                i13 = i19 + 1;
                fArr2[i19] = fCos;
                int i20 = i16 + 1;
                fArr3[i16] = fSin * 18.0f;
                int i21 = i20 + 1;
                fArr3[i20] = fSin2 * 18.0f;
                i14 = i21 + 1;
                fArr3[i21] = fSin3 * 18.0f;
                s7 = (short) (s7 + 1);
                i11 = i15;
                i7 = i17;
                i12 = i18;
                fArr = fArr3;
                f11 = f12;
                i10 = i10;
                sArr = sArr2;
                s6 = s6;
                i9 = i9;
                f10 = f10;
            }
            s6 = (short) (s6 + 1);
        }
        int i22 = i7;
        int i23 = i11;
        int i24 = i9;
        float[] fArr4 = fArr;
        int i25 = i10;
        short[] sArr3 = sArr;
        int i26 = 0;
        for (short s9 = 0; s9 < i22 - 1; s9 = (short) (s9 + 1)) {
            short s10 = 0;
            while (s10 < 150) {
                int i27 = i26 + 1;
                int i28 = s9 * 151;
                sArr3[i26] = (short) (i28 + s10);
                int i29 = i27 + 1;
                int i30 = (s9 + 1) * Opcodes.DCMPL;
                short s11 = (short) (i30 + s10);
                sArr3[i27] = s11;
                int i31 = i29 + 1;
                int i32 = s10 + 1;
                short s12 = (short) (i28 + i32);
                sArr3[i29] = s12;
                int i33 = i31 + 1;
                sArr3[i31] = s12;
                int i34 = i33 + 1;
                sArr3[i33] = s11;
                i26 = i34 + 1;
                sArr3[i34] = (short) (i30 + i32);
                s10 = (short) i32;
            }
        }
        FloatBuffer floatBufferM1293a = C1128c.m1293a(ByteBuffer.allocateDirect(i24 * 4), fArr4, 0);
        FloatBuffer floatBufferM1293a2 = C1128c.m1293a(ByteBuffer.allocateDirect(i25 * 4), fArr2, 0);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i23 * 2);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect.asShortBuffer();
        shortBufferAsShortBuffer.put(sArr3);
        shortBufferAsShortBuffer.position(0);
        this.f2450a = shortBufferAsShortBuffer;
        this.f2452c.put(0, floatBufferM1293a2);
        this.f2452c.put(1, floatBufferM1293a2);
        this.f2453d.put(0, floatBufferM1293a);
        this.f2453d.put(1, floatBufferM1293a);
        this.f2451b = i23;
        this.f2458i = fArr2;
    }

    @Override // p067i.AbstractC1126a
    /* renamed from: d */
    public void mo1291d(C0890c c0890c, int i7) {
        if (this.f2452c.get(i7) == null) {
            return;
        }
        if (i7 == 0) {
            float fWidth = this.f2456g.width() / this.f2456g.height();
            if (fWidth != this.f2457h) {
                int length = this.f2458i.length;
                float[] fArr = new float[length];
                for (int i8 = 0; i8 < length; i8 += 2) {
                    float[] fArr2 = this.f2458i;
                    fArr[i8] = ((fArr2[i8] - 0.5f) / fWidth) + 0.5f;
                    int i9 = i8 + 1;
                    fArr[i9] = fArr2[i9];
                }
                FloatBuffer floatBufferM1293a = C1128c.m1293a(ByteBuffer.allocateDirect(length * 4), fArr, 0);
                this.f2452c.put(0, floatBufferM1293a);
                this.f2452c.put(1, floatBufferM1293a);
                this.f2457h = fWidth;
            }
        }
        super.mo1291d(c0890c, i7);
    }
}
