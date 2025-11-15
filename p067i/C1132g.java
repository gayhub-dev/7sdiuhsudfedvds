package p067i;

import android.content.Context;
import android.graphics.RectF;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import p035e.C0890c;
import p115o.C1569f;

/* compiled from: MDPlane.java */
/* renamed from: i.g */
/* loaded from: classes.dex */
public class C1132g extends AbstractC1126a {

    /* renamed from: e */
    public float f2462e;

    /* renamed from: f */
    public RectF f2463f;

    /* renamed from: g */
    public C1569f.d f2464g;

    public C1132g(C1569f.d dVar) {
        RectF rectF = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
        this.f2464g = dVar;
        this.f2463f = rectF;
    }

    @Override // p067i.AbstractC1126a
    /* renamed from: b */
    public void mo1289b(Context context) {
        float[] fArrM1296f = m1296f();
        float[] fArr = new float[8];
        float f7 = 1.0f / 1;
        int i7 = 0;
        for (short s6 = 0; s6 < 2; s6 = (short) (s6 + 1)) {
            for (short s7 = 0; s7 < 2; s7 = (short) (s7 + 1)) {
                int i8 = i7 + 1;
                fArr[i7] = s7 * f7;
                i7 = i8 + 1;
                fArr[i8] = s6 * f7;
            }
        }
        short[] sArr = new short[24];
        int i9 = 0;
        for (short s8 = 0; s8 < 1; s8 = (short) (s8 + 1)) {
            short s9 = 0;
            while (s9 < 1) {
                int i10 = s8 * 2;
                int i11 = s9 + 1;
                short s10 = (short) (i10 + i11);
                int i12 = (s8 + 1) * 2;
                short s11 = (short) (i12 + s9);
                short s12 = (short) (i10 + s9);
                short s13 = (short) (i12 + i11);
                int i13 = i9 + 1;
                sArr[i9] = s10;
                int i14 = i13 + 1;
                sArr[i13] = s11;
                int i15 = i14 + 1;
                sArr[i14] = s12;
                int i16 = i15 + 1;
                sArr[i15] = s10;
                int i17 = i16 + 1;
                sArr[i16] = s13;
                i9 = i17 + 1;
                sArr[i17] = s11;
                s9 = (short) i11;
            }
        }
        FloatBuffer floatBufferM1293a = C1128c.m1293a(ByteBuffer.allocateDirect(fArrM1296f.length * 4), fArrM1296f, 0);
        FloatBuffer floatBufferM1293a2 = C1128c.m1293a(ByteBuffer.allocateDirect(32), fArr, 0);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(48);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect.asShortBuffer();
        shortBufferAsShortBuffer.put(sArr);
        shortBufferAsShortBuffer.position(0);
        this.f2450a = shortBufferAsShortBuffer;
        this.f2452c.put(0, floatBufferM1293a2);
        this.f2452c.put(1, floatBufferM1293a2);
        this.f2453d.put(0, floatBufferM1293a);
        this.f2453d.put(1, floatBufferM1293a);
        this.f2451b = 24;
    }

    @Override // p067i.AbstractC1126a
    /* renamed from: e */
    public void mo1292e(C0890c c0890c, int i7) {
        if (this.f2453d.get(i7) == null) {
            return;
        }
        if (i7 == 0) {
            float fM1815b = this.f2464g.m1815b();
            if (fM1815b != this.f2462e) {
                float[] fArrM1296f = m1296f();
                FloatBuffer floatBufferM1293a = C1128c.m1293a(ByteBuffer.allocateDirect(fArrM1296f.length * 4), fArrM1296f, 0);
                this.f2453d.put(0, floatBufferM1293a);
                this.f2453d.put(1, floatBufferM1293a);
                this.f2462e = fM1815b;
            }
        }
        super.mo1292e(c0890c, i7);
    }

    /* renamed from: f */
    public final float[] m1296f() {
        this.f2464g.m1814a();
        this.f2462e = this.f2464g.m1815b();
        float fWidth = this.f2463f.width() * this.f2464g.f4710f;
        float fHeight = this.f2463f.height() * this.f2464g.f4711g;
        float[] fArr = new float[12];
        float f7 = 1.0f / 1;
        int i7 = 0;
        for (short s6 = 0; s6 < 2; s6 = (short) (s6 + 1)) {
            short s7 = 0;
            while (s7 < 2) {
                int i8 = i7 + 1;
                fArr[i7] = ((s7 * f7) - 0.5f) * fWidth;
                int i9 = i8 + 1;
                fArr[i8] = ((s6 * f7) - 0.5f) * fHeight;
                fArr[i9] = 0;
                s7 = (short) (s7 + 1);
                i7 = i9 + 1;
            }
        }
        return fArr;
    }
}
