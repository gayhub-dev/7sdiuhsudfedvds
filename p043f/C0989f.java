package p043f;

import android.opengl.Matrix;
import android.os.Looper;
import p051g.C1033c;
import p051g.C1040j;
import p051g.C1041k;

/* compiled from: VRUtil.java */
/* renamed from: f.f */
/* loaded from: classes.dex */
public class C0989f {

    /* renamed from: a */
    public static float[] f1832a = new float[16];

    /* renamed from: b */
    public static float[] f1833b = new float[4];

    /* renamed from: c */
    public static boolean f1834c = false;

    /* renamed from: a */
    public static void m1001a(String str) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new RuntimeException(str);
        }
    }

    /* renamed from: b */
    public static C1041k m1002b(float f7, float f8, C1033c c1033c) {
        m1001a("point2Ray must called in main Thread");
        float[] fArr = c1033c.f1949a;
        float[] fArr2 = f1832a;
        if (!(fArr == fArr2 ? false : Matrix.invertM(fArr2, 0, fArr, 0))) {
            return null;
        }
        C1040j c1040j = new C1040j(1);
        float[] fArr3 = c1033c.f1950b;
        c1040j.m1045i((-(((f7 * 2.0f) / c1033c.f1951c) - 1.0f)) / fArr3[0]);
        c1040j.m1046j((((f8 * 2.0f) / c1033c.f1952d) - 1.0f) / fArr3[5]);
        c1040j.m1047k(1.0f);
        C1040j c1040j2 = new C1040j(1);
        C1040j c1040j3 = new C1040j(1);
        c1040j2.m1045i((c1040j.m1044h() * fArr2[8]) + (c1040j.m1042f() * fArr2[4]) + (c1040j.m1041e() * fArr2[0]));
        c1040j2.m1046j((c1040j.m1044h() * fArr2[9]) + (c1040j.m1042f() * fArr2[5]) + (c1040j.m1041e() * fArr2[1]));
        c1040j2.m1047k((c1040j.m1044h() * fArr2[10]) + (c1040j.m1042f() * fArr2[6]) + (c1040j.m1041e() * fArr2[2]));
        c1040j3.m1045i(fArr2[12]);
        c1040j3.m1046j(fArr2[13]);
        c1040j3.m1047k(fArr2[14]);
        return new C1041k(c1040j3, c1040j2);
    }
}
