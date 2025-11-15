package p067i;

import android.content.Context;
import android.opengl.GLES20;
import android.util.SparseArray;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import p035e.C0890c;

/* compiled from: MDAbsObject3D.java */
/* renamed from: i.a */
/* loaded from: classes.dex */
public abstract class AbstractC1126a {

    /* renamed from: a */
    public ShortBuffer f2450a;

    /* renamed from: b */
    public int f2451b;

    /* renamed from: c */
    public SparseArray<FloatBuffer> f2452c = new SparseArray<>(2);

    /* renamed from: d */
    public SparseArray<FloatBuffer> f2453d = new SparseArray<>(2);

    /* renamed from: a */
    public void m1288a() {
        ShortBuffer shortBuffer = this.f2450a;
        if (shortBuffer == null) {
            GLES20.glDrawArrays(4, 0, this.f2451b);
        } else {
            shortBuffer.position(0);
            GLES20.glDrawElements(4, this.f2451b, 5123, this.f2450a);
        }
    }

    /* renamed from: b */
    public abstract void mo1289b(Context context);

    /* renamed from: c */
    public FloatBuffer mo1290c(int i7) {
        return this.f2452c.get(i7);
    }

    /* renamed from: d */
    public void mo1291d(C0890c c0890c, int i7) {
        FloatBuffer floatBufferMo1290c = mo1290c(i7);
        if (floatBufferMo1290c == null) {
            return;
        }
        floatBufferMo1290c.position(0);
        int i8 = c0890c.f1504d;
        GLES20.glVertexAttribPointer(i8, 2, 5126, false, 0, (Buffer) floatBufferMo1290c);
        GLES20.glEnableVertexAttribArray(i8);
    }

    /* renamed from: e */
    public void mo1292e(C0890c c0890c, int i7) {
        FloatBuffer floatBuffer = this.f2453d.get(i7);
        if (floatBuffer == null) {
            return;
        }
        floatBuffer.position(0);
        int i8 = c0890c.f1503c;
        GLES20.glVertexAttribPointer(i8, 3, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(i8);
    }
}
