package p125p;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.view.Surface;
import p035e.C0890c;
import p035e.C0901n;
import p043f.C0985b;

/* compiled from: MD360VideoTexture.java */
/* renamed from: p.b */
/* loaded from: classes.dex */
public class C1731b extends AbstractC1730a {

    /* renamed from: f */
    public static final int[] f4913f = {1};

    /* renamed from: b */
    public Surface f4914b;

    /* renamed from: c */
    public SurfaceTexture f4915c;

    /* renamed from: d */
    public C0901n.h f4916d;

    /* renamed from: e */
    public float[] f4917e = new float[16];

    public C1731b(C0901n.h hVar) {
        this.f4916d = hVar;
    }

    @Override // p125p.AbstractC1730a
    /* renamed from: a */
    public void mo1875a() {
        int[] iArr = new int[1];
        GLES20.glActiveTexture(33984);
        GLES20.glGenTextures(1, iArr, 0);
        C0985b.m946b("Texture generate");
        GLES20.glBindTexture(36197, iArr[0]);
        C0985b.m946b("Texture bind");
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        int i7 = iArr[0];
        if (i7 != 0) {
            this.f4912a = i7;
        }
        int i8 = this.f4912a;
        if (!(i8 == 0) && this.f4915c == null) {
            this.f4915c = new SurfaceTexture(i8);
            Surface surface = new Surface(this.f4915c);
            this.f4914b = surface;
            C0901n.h hVar = this.f4916d;
            if (hVar != null) {
                hVar.mo818a(surface);
            }
        }
    }

    @Override // p125p.AbstractC1730a
    /* renamed from: b */
    public boolean mo1876b(C0890c c0890c) {
        SurfaceTexture surfaceTexture;
        if ((this.f4912a == 0) || (surfaceTexture = this.f4915c) == null) {
            return false;
        }
        try {
            surfaceTexture.updateTexImage();
            this.f4915c.getTransformMatrix(this.f4917e);
            GLES20.glUniform1iv(c0890c.f1507g, 1, f4913f, 0);
            GLES20.glUniformMatrix4fv(c0890c.f1506f, 1, false, this.f4917e, 0);
            return true;
        } catch (Exception e7) {
            e7.printStackTrace();
            return false;
        }
    }
}
