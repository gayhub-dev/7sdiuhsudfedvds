package p075j;

import android.graphics.Rect;
import android.opengl.GLES20;
import p043f.C0985b;

/* compiled from: MDDrawingCache.java */
/* renamed from: j.d */
/* loaded from: classes.dex */
public class C1176d {

    /* renamed from: a */
    public int f2575a;

    /* renamed from: b */
    public int f2576b;

    /* renamed from: c */
    public int f2577c;

    /* renamed from: d */
    public Rect f2578d = new Rect();

    /* renamed from: e */
    public int[] f2579e = new int[1];

    /* renamed from: a */
    public void m1378a(int i7, int i8) {
        if (this.f2578d.width() != i7 || this.f2578d.height() != i8) {
            int i9 = this.f2575a;
            if (i9 != 0) {
                GLES20.glDeleteTextures(1, new int[]{i9}, 0);
            }
            int i10 = this.f2577c;
            if (i10 != 0) {
                GLES20.glDeleteRenderbuffers(1, new int[]{i10}, 0);
            }
            int i11 = this.f2576b;
            if (i11 != 0) {
                GLES20.glDeleteFramebuffers(1, new int[]{i11}, 0);
            }
            GLES20.glGetIntegerv(36006, this.f2579e, 0);
            int[] iArr = new int[1];
            GLES20.glGenFramebuffers(1, iArr, 0);
            GLES20.glBindFramebuffer(36160, iArr[0]);
            this.f2576b = iArr[0];
            C0985b.m946b("Multi Fish Eye frame buffer");
            int[] iArr2 = {0};
            GLES20.glGenRenderbuffers(1, iArr2, 0);
            GLES20.glBindRenderbuffer(36161, iArr2[0]);
            GLES20.glRenderbufferStorage(36161, 33189, i7, i8);
            this.f2577c = iArr2[0];
            C0985b.m946b("Multi Fish Eye renderer buffer");
            int[] iArr3 = {0};
            GLES20.glGenTextures(1, iArr3, 0);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, iArr3[0]);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexImage2D(3553, 0, 6408, i7, i8, 0, 6408, 5121, null);
            this.f2575a = iArr3[0];
            C0985b.m946b("Multi Fish Eye texture");
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f2575a, 0);
            GLES20.glFramebufferRenderbuffer(36160, 36096, 36161, iArr2[0]);
            C0985b.m946b("Multi Fish Eye attach");
            int iGlCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
            if (iGlCheckFramebufferStatus != 36053) {
                String strValueOf = String.valueOf(Integer.toHexString(iGlCheckFramebufferStatus));
                throw new RuntimeException(strValueOf.length() != 0 ? "Framebuffer is not complete: ".concat(strValueOf) : "Framebuffer is not complete: ");
            }
            GLES20.glBindFramebuffer(36160, this.f2579e[0]);
            C0985b.m946b("Multi Fish Eye attach");
            this.f2578d.set(0, 0, i7, i8);
        }
        GLES20.glGetIntegerv(36006, this.f2579e, 0);
        GLES20.glBindFramebuffer(36160, this.f2576b);
    }
}
