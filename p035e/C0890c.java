package p035e;

import android.content.Context;
import android.opengl.GLES20;
import com.asha.vrlib.R$raw;
import java.io.IOException;
import p043f.C0985b;

/* compiled from: MD360Program.java */
/* renamed from: e.c */
/* loaded from: classes.dex */
public class C0890c {

    /* renamed from: a */
    public int f1501a;

    /* renamed from: b */
    public int f1502b;

    /* renamed from: c */
    public int f1503c;

    /* renamed from: d */
    public int f1504d;

    /* renamed from: e */
    public int f1505e;

    /* renamed from: f */
    public int f1506f;

    /* renamed from: g */
    public int f1507g;

    /* renamed from: h */
    public int f1508h;

    public C0890c(int i7) {
        this.f1508h = i7;
    }

    /* renamed from: a */
    public void m780a(Context context) throws IOException {
        String strM947c = C0985b.m947c(context, R$raw.per_pixel_vertex_shader);
        int i7 = this.f1508h;
        String strM947c2 = C0985b.m947c(context, i7 != 1 ? i7 != 2 ? i7 != 3 ? R$raw.per_pixel_fragment_shader : R$raw.per_pixel_fragment_shader_cubemap : R$raw.per_pixel_fragment_shader_bitmap_fbo : R$raw.per_pixel_fragment_shader_bitmap);
        int iM945a = C0985b.m945a(35633, strM947c);
        int iM945a2 = C0985b.m945a(35632, strM947c2);
        String[] strArr = {"a_Position", "a_TexCoordinate"};
        int iGlCreateProgram = GLES20.glCreateProgram();
        if (iGlCreateProgram != 0) {
            GLES20.glAttachShader(iGlCreateProgram, iM945a);
            GLES20.glAttachShader(iGlCreateProgram, iM945a2);
            for (int i8 = 0; i8 < 2; i8++) {
                GLES20.glBindAttribLocation(iGlCreateProgram, i8, strArr[i8]);
            }
            GLES20.glLinkProgram(iGlCreateProgram);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
            if (iArr[0] == 0) {
                GLES20.glGetProgramInfoLog(iGlCreateProgram);
                GLES20.glDeleteProgram(iGlCreateProgram);
                iGlCreateProgram = 0;
            }
        }
        if (iGlCreateProgram == 0) {
            throw new RuntimeException("Error creating program.");
        }
        this.f1505e = iGlCreateProgram;
        this.f1501a = GLES20.glGetUniformLocation(iGlCreateProgram, "u_MVPMatrix");
        this.f1502b = GLES20.glGetUniformLocation(this.f1505e, "u_MVMatrix");
        GLES20.glGetUniformLocation(this.f1505e, "u_Texture");
        this.f1503c = GLES20.glGetAttribLocation(this.f1505e, "a_Position");
        this.f1504d = GLES20.glGetAttribLocation(this.f1505e, "a_TexCoordinate");
        this.f1506f = GLES20.glGetUniformLocation(this.f1505e, "u_STMatrix");
        this.f1507g = GLES20.glGetUniformLocation(this.f1505e, "u_UseSTM");
        GLES20.glGetUniformLocation(this.f1505e, "u_IsSkybox");
    }
}
