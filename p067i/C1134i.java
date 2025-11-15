package p067i;

import android.content.Context;
import com.alibaba.fastjson.asm.Opcodes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* compiled from: MDStereoSphere3D.java */
/* renamed from: i.i */
/* loaded from: classes.dex */
public class C1134i extends AbstractC1126a {

    /* renamed from: e */
    public int f2465e;

    public C1134i(int i7) {
        this.f2465e = 1;
        this.f2465e = i7;
    }

    @Override // p067i.AbstractC1126a
    /* renamed from: b */
    public void mo1289b(Context context) {
        int i7;
        int i8 = this.f2465e;
        short s6 = 75;
        float f7 = 1.0f / 75;
        float f8 = 1.0f / 150;
        float[] fArr = new float[34428];
        float[] fArr2 = new float[22952];
        float[] fArr3 = new float[22952];
        short[] sArr = new short[68856];
        short s7 = 0;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            short s8 = 151;
            if (s7 >= 76) {
                break;
            }
            short s9 = 0;
            while (s9 < s8) {
                float f9 = s9;
                double d7 = 6.2831855f * f9 * f8;
                float f10 = s7;
                short[] sArr2 = sArr;
                short s10 = s7;
                double d8 = 3.1415927f * f10 * f7;
                short s11 = s9;
                float f11 = f7;
                float fSin = (float) (Math.sin(d8) * Math.cos(d7));
                float f12 = -((float) Math.sin(r12 - 1.5707964f));
                float fSin2 = (float) (Math.sin(d8) * Math.sin(d7));
                if (2 == i8) {
                    float f13 = f9 * f8;
                    fArr2[i9] = f13;
                    fArr3[i9] = f13;
                    i7 = i9 + 1;
                    float f14 = (f10 * f11) / 2.0f;
                    fArr2[i7] = 1.0f - f14;
                    fArr3[i7] = 0.5f - f14;
                } else {
                    float f15 = (f9 * f8) / 2.0f;
                    fArr2[i9] = f15;
                    fArr3[i9] = f15 + 0.5f;
                    i7 = i9 + 1;
                    float f16 = 1.0f - (f10 * f11);
                    fArr2[i7] = f16;
                    fArr3[i7] = f16;
                }
                i9 = i7 + 1;
                int i11 = i10 + 1;
                fArr[i10] = fSin * 18.0f;
                int i12 = i11 + 1;
                fArr[i11] = f12 * 18.0f;
                i10 = i12 + 1;
                fArr[i12] = fSin2 * 18.0f;
                s9 = (short) (s11 + 1);
                s8 = 151;
                sArr = sArr2;
                s7 = s10;
                f7 = f11;
            }
            s7 = (short) (s7 + 1);
            s6 = 75;
        }
        short[] sArr3 = sArr;
        int i13 = 0;
        for (short s12 = 0; s12 < s6; s12 = (short) (s12 + 1)) {
            short s13 = 0;
            while (s13 < 150) {
                int i14 = i13 + 1;
                int i15 = s12 * 151;
                sArr3[i13] = (short) (i15 + s13);
                int i16 = i14 + 1;
                int i17 = (s12 + 1) * Opcodes.DCMPL;
                short s14 = (short) (i17 + s13);
                sArr3[i14] = s14;
                int i18 = i16 + 1;
                int i19 = s13 + 1;
                short s15 = (short) (i15 + i19);
                sArr3[i16] = s15;
                int i20 = i18 + 1;
                sArr3[i18] = s15;
                int i21 = i20 + 1;
                sArr3[i20] = s14;
                i13 = i21 + 1;
                sArr3[i21] = (short) (i17 + i19);
                s13 = (short) i19;
            }
        }
        FloatBuffer floatBufferM1293a = C1128c.m1293a(ByteBuffer.allocateDirect(137712), fArr, 0);
        FloatBuffer floatBufferM1293a2 = C1128c.m1293a(ByteBuffer.allocateDirect(91808), fArr2, 0);
        FloatBuffer floatBufferM1293a3 = C1128c.m1293a(ByteBuffer.allocateDirect(91808), fArr3, 0);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(137712);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect.asShortBuffer();
        shortBufferAsShortBuffer.put(sArr3);
        shortBufferAsShortBuffer.position(0);
        this.f2450a = shortBufferAsShortBuffer;
        this.f2452c.put(0, floatBufferM1293a2);
        this.f2452c.put(1, floatBufferM1293a3);
        this.f2453d.put(0, floatBufferM1293a);
        this.f2453d.put(1, floatBufferM1293a);
        this.f2451b = 68856;
    }
}
