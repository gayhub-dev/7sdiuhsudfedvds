package p075j;

import android.content.Context;
import android.opengl.GLES20;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import p035e.AbstractC0889b;
import p035e.C0888a;
import p035e.C0890c;
import p043f.C0985b;
import p051g.AbstractC1039i;
import p051g.C1037g;
import p067i.AbstractC1126a;
import p067i.C1128c;
import p067i.C1131f;
import p115o.C1570g;
import p125p.AbstractC1730a;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: MDMultiFishEyePlugin.java */
/* renamed from: j.e */
/* loaded from: classes.dex */
public class C1177e extends AbstractC1174b {

    /* renamed from: c */
    public C0890c f2580c;

    /* renamed from: e */
    @Nullable
    public AbstractC1730a f2582e;

    /* renamed from: f */
    public C1570g f2583f;

    /* renamed from: g */
    public a f2584g;

    /* renamed from: d */
    public C0890c f2581d = new C0890c(2);

    /* renamed from: h */
    public C0888a f2585h = new AbstractC0889b.c(new C0888a.a(), null);

    /* renamed from: i */
    public C1176d f2586i = new C1176d();

    /* compiled from: MDMultiFishEyePlugin.java */
    /* renamed from: j.e$a */
    public class a extends AbstractC1126a {

        /* renamed from: e */
        public final int f2587e;

        /* renamed from: f */
        public final float f2588f;

        public a(C1177e c1177e, float f7, int i7) {
            this.f2588f = f7;
            this.f2587e = i7;
        }

        @Override // p067i.AbstractC1126a
        /* renamed from: b */
        public void mo1289b(Context context) {
            float f7 = 1.0f;
            float f8 = 1.0f / 16;
            float[] fArr = new float[867];
            float[] fArr2 = new float[IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED_BASELINE];
            float[] fArr3 = new float[IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED_BASELINE];
            short[] sArr = new short[1734];
            short s6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (true) {
                short s7 = 17;
                if (s6 >= 17) {
                    break;
                }
                short s8 = 0;
                while (s8 < s7) {
                    int i9 = i8 + 1;
                    float f9 = s8 * f8;
                    fArr[i8] = (f9 * 2.0f) - f7;
                    int i10 = i9 + 1;
                    float f10 = s6 * f8;
                    fArr[i9] = (2.0f * f10) - f7;
                    int i11 = i10 + 1;
                    fArr[i10] = -8.0f;
                    double d7 = (f10 - 0.5f) * 3.1415927f;
                    double d8 = (f9 - 0.5f) * 3.1415927f;
                    float f11 = f8;
                    float fSin = (float) (Math.sin(d8) * Math.cos(d7));
                    float fCos = (float) (Math.cos(d8) * Math.cos(d7));
                    short[] sArr2 = sArr;
                    short s9 = s6;
                    float fAtan2 = (float) Math.atan2((float) Math.sin(d7), fSin);
                    double d9 = 0.5f;
                    double dAtan2 = (this.f2588f * ((float) Math.atan2(Math.sqrt((r9 * r9) + (fSin * fSin)), fCos))) / 3.1415927f;
                    double d10 = fAtan2;
                    float fCos2 = (float) ((Math.cos(d10) * dAtan2) + d9);
                    float fSin2 = (float) ((Math.sin(d10) * dAtan2) + d9);
                    if (this.f2587e == 1) {
                        int i12 = i7 * 2;
                        float f12 = fCos2 * 0.5f;
                        fArr2[i12] = f12;
                        int i13 = i12 + 1;
                        fArr2[i13] = fSin2;
                        fArr3[i12] = f12 + 0.5f;
                        fArr3[i13] = fSin2;
                    } else {
                        int i14 = i7 * 2;
                        fArr2[i14] = fCos2;
                        int i15 = i14 + 1;
                        float f13 = fSin2 * 0.5f;
                        fArr2[i15] = f13;
                        fArr3[i14] = fCos2;
                        fArr3[i15] = f13 + 0.5f;
                    }
                    i7++;
                    s8 = (short) (s8 + 1);
                    f7 = 1.0f;
                    s7 = 17;
                    f8 = f11;
                    s6 = s9;
                    sArr = sArr2;
                    i8 = i11;
                }
                s6 = (short) (s6 + 1);
                f7 = 1.0f;
            }
            short[] sArr3 = sArr;
            int i16 = 0;
            for (short s10 = 0; s10 < 16; s10 = (short) (s10 + 1)) {
                short s11 = 0;
                while (s11 < 16) {
                    int i17 = s10 * 17;
                    int i18 = s11 + 1;
                    short s12 = (short) (i17 + i18);
                    int i19 = (s10 + 1) * 17;
                    short s13 = (short) (i19 + s11);
                    short s14 = (short) (i17 + s11);
                    short s15 = (short) (i19 + i18);
                    int i20 = i16 + 1;
                    sArr3[i16] = s12;
                    int i21 = i20 + 1;
                    sArr3[i20] = s13;
                    int i22 = i21 + 1;
                    sArr3[i21] = s14;
                    int i23 = i22 + 1;
                    sArr3[i22] = s12;
                    int i24 = i23 + 1;
                    sArr3[i23] = s15;
                    i16 = i24 + 1;
                    sArr3[i24] = s13;
                    s11 = (short) i18;
                }
            }
            FloatBuffer floatBufferM1293a = C1128c.m1293a(ByteBuffer.allocateDirect(3468), fArr, 0);
            FloatBuffer floatBufferM1293a2 = C1128c.m1293a(ByteBuffer.allocateDirect(2312), fArr2, 0);
            FloatBuffer floatBufferM1293a3 = C1128c.m1293a(ByteBuffer.allocateDirect(2312), fArr3, 0);
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(3468);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect.asShortBuffer();
            shortBufferAsShortBuffer.put(sArr3);
            shortBufferAsShortBuffer.position(0);
            this.f2450a = shortBufferAsShortBuffer;
            this.f2452c.put(0, floatBufferM1293a2);
            this.f2452c.put(1, floatBufferM1293a3);
            this.f2453d.put(0, floatBufferM1293a);
            this.f2453d.put(1, floatBufferM1293a);
            this.f2451b = 1734;
        }
    }

    public C1177e(C1037g c1037g, float f7, int i7) {
        this.f2582e = c1037g.f1961a;
        this.f2580c = new C0890c(c1037g.f1962b);
        this.f2583f = c1037g.f1963c;
        this.f2584g = new a(this, f7, i7);
    }

    @Override // p075j.AbstractC1174b
    /* renamed from: a */
    public void mo807a(int i7, int i8) {
        this.f2585h.m775f(i7, i8);
        this.f2586i.m1378a(i7, i8);
        GLES20.glClear(16640);
        C0985b.m946b("MDMultiFisheyeConvertLinePipe glClear");
        if (this.f2582e != null) {
            int i9 = i7 / 2;
            for (int i10 = 0; i10 < 2; i10++) {
                int i11 = i9 * i10;
                GLES20.glViewport(i11, 0, i9, i8);
                GLES20.glEnable(3089);
                GLES20.glScissor(i11, 0, i9, i8);
                GLES20.glUseProgram(this.f2580c.f1505e);
                AbstractC1730a abstractC1730a = this.f2582e;
                if (abstractC1730a != null) {
                    abstractC1730a.mo1876b(this.f2580c);
                }
                this.f2585h.m775f(i9, i8);
                this.f2584g.mo1292e(this.f2580c, i10);
                this.f2584g.mo1291d(this.f2580c, i10);
                this.f2585h.m770a();
                this.f2585h.m776g(this.f2580c, AbstractC1039i.f1969a);
                this.f2584g.m1288a();
                GLES20.glDisable(3089);
            }
        }
        GLES20.glBindFramebuffer(36160, this.f2586i.f2579e[0]);
    }

    @Override // p075j.AbstractC1174b
    /* renamed from: b */
    public void mo1374b() {
        this.f2582e = null;
    }

    @Override // p075j.AbstractC1174b
    /* renamed from: c */
    public void mo1375c(Context context) throws IOException {
        this.f2580c.m780a(context);
        this.f2581d.m780a(context);
        AbstractC1730a abstractC1730a = this.f2582e;
        if (abstractC1730a != null) {
            abstractC1730a.mo1875a();
        }
        C1131f.m1294a(context, this.f2584g);
    }

    @Override // p075j.AbstractC1174b
    /* renamed from: d */
    public void mo1376d(int i7, int i8, int i9, C0888a c0888a) {
        AbstractC1126a abstractC1126aMo1812f = this.f2583f.mo1812f();
        if (abstractC1126aMo1812f == null) {
            return;
        }
        c0888a.m775f(i8, i9);
        GLES20.glUseProgram(this.f2581d.f1505e);
        C0985b.m946b("MDPanoramaPlugin mProgram use");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.f2586i.f2575a);
        abstractC1126aMo1812f.mo1292e(this.f2581d, i7);
        abstractC1126aMo1812f.mo1291d(this.f2581d, i7);
        c0888a.m770a();
        c0888a.m776g(this.f2581d, this.f2583f.mo1813g());
        abstractC1126aMo1812f.m1288a();
    }
}
