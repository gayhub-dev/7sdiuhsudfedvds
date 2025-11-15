package p067i;

import android.content.Context;
import com.alibaba.fastjson.asm.Opcodes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* compiled from: MDSphere3D.java */
/* renamed from: i.h */
/* loaded from: classes.dex */
public class C1133h extends AbstractC1126a {
    @Override // p067i.AbstractC1126a
    /* renamed from: b */
    public void mo1289b(Context context) {
        float f7 = 1.0f / 75;
        float f8 = 1.0f / 150;
        float[] fArr = new float[34428];
        float[] fArr2 = new float[22952];
        short[] sArr = new short[68856];
        short s6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            short s7 = 151;
            if (s6 >= 76) {
                break;
            }
            short s8 = 0;
            while (s8 < s7) {
                float f9 = s8;
                double d7 = 6.2831855f * f9 * f8;
                float f10 = s6;
                short[] sArr2 = sArr;
                short s9 = s6;
                double d8 = 3.1415927f * f10 * f7;
                float fCos = (float) (Math.cos(d7) * Math.sin(d8));
                short s10 = s8;
                float f11 = -((float) Math.sin(r10 - 1.5707964f));
                float fSin = (float) (Math.sin(d8) * Math.sin(d7));
                int i9 = i7 + 1;
                fArr2[i7] = f9 * f8;
                i7 = i9 + 1;
                fArr2[i9] = 1.0f - (f10 * f7);
                int i10 = i8 + 1;
                fArr[i8] = fCos * 18.0f;
                int i11 = i10 + 1;
                fArr[i10] = f11 * 18.0f;
                fArr[i11] = fSin * 18.0f;
                s8 = (short) (s10 + 1);
                s7 = 151;
                sArr = sArr2;
                i8 = i11 + 1;
                s6 = s9;
            }
            s6 = (short) (s6 + 1);
        }
        short[] sArr3 = sArr;
        int i12 = 0;
        for (short s11 = 0; s11 < 75; s11 = (short) (s11 + 1)) {
            short s12 = 0;
            while (s12 < 150) {
                int i13 = i12 + 1;
                int i14 = s11 * 151;
                sArr3[i12] = (short) (i14 + s12);
                int i15 = i13 + 1;
                int i16 = (s11 + 1) * Opcodes.DCMPL;
                short s13 = (short) (i16 + s12);
                sArr3[i13] = s13;
                int i17 = i15 + 1;
                int i18 = s12 + 1;
                short s14 = (short) (i14 + i18);
                sArr3[i15] = s14;
                int i19 = i17 + 1;
                sArr3[i17] = s14;
                int i20 = i19 + 1;
                sArr3[i19] = s13;
                i12 = i20 + 1;
                sArr3[i20] = (short) (i16 + i18);
                s12 = (short) i18;
            }
        }
        FloatBuffer floatBufferM1293a = C1128c.m1293a(ByteBuffer.allocateDirect(137712), fArr, 0);
        FloatBuffer floatBufferM1293a2 = C1128c.m1293a(ByteBuffer.allocateDirect(91808), fArr2, 0);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(137712);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect.asShortBuffer();
        shortBufferAsShortBuffer.put(sArr3);
        shortBufferAsShortBuffer.position(0);
        this.f2450a = shortBufferAsShortBuffer;
        this.f2452c.put(0, floatBufferM1293a2);
        this.f2452c.put(1, floatBufferM1293a2);
        this.f2453d.put(0, floatBufferM1293a);
        this.f2453d.put(1, floatBufferM1293a);
        this.f2451b = 68856;
    }
}
