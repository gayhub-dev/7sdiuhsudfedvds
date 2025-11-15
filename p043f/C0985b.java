package p043f;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* compiled from: GLUtil.java */
/* renamed from: f.b */
/* loaded from: classes.dex */
public class C0985b {

    /* renamed from: a */
    public static final float[] f1817a;

    static {
        float[] fArr = new float[16];
        f1817a = fArr;
        Matrix.setIdentityM(fArr, 0);
    }

    /* renamed from: a */
    public static int m945a(int i7, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i7);
        if (iGlCreateShader != 0) {
            GLES20.glShaderSource(iGlCreateShader, str);
            GLES20.glCompileShader(iGlCreateShader);
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
            if (iArr[0] == 0) {
                GLES20.glGetShaderInfoLog(iGlCreateShader);
                GLES20.glDeleteShader(iGlCreateShader);
                iGlCreateShader = 0;
            }
        }
        if (iGlCreateShader != 0) {
            return iGlCreateShader;
        }
        throw new RuntimeException("Error creating shader.");
    }

    /* renamed from: b */
    public static void m946b(String str) {
        while (true) {
            int iGlGetError = GLES20.glGetError();
            if (iGlGetError == 0) {
                return;
            } else {
                GLUtils.getEGLErrorString(iGlGetError);
            }
        }
    }

    /* renamed from: c */
    public static String m947c(Context context, int i7) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(i7)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return sb.toString();
                }
                sb.append(line);
                sb.append('\n');
            } catch (IOException unused) {
                return null;
            }
        }
    }
}
