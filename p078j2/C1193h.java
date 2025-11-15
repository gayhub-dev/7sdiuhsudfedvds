package p078j2;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GpuInfoUtil.java */
/* renamed from: j2.h */
/* loaded from: classes.dex */
public class C1193h implements GLSurfaceView.Renderer {

    /* renamed from: a */
    public static String f2615a;

    /* renamed from: b */
    public static String f2616b;

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i7, int i8) {
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        f2615a = gl10.glGetString(7937);
        f2616b = gl10.glGetString(7936);
        gl10.glGetString(7938);
        gl10.glGetString(7939);
    }
}
