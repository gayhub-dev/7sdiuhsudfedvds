package p133q;

import java.util.Random;

/* compiled from: MathUtils.java */
/* renamed from: q.a */
/* loaded from: classes.dex */
public final class C1755a {

    /* renamed from: a */
    public static final /* synthetic */ int f4996a = 0;

    static {
        new Random();
    }

    /* renamed from: a */
    public static float m1923a(float f7, float f8) {
        if (f8 == 0.0f) {
            if (f7 > 0.0f) {
                return 1.5707964f;
            }
            return f7 == 0.0f ? 0.0f : -1.5707964f;
        }
        float f9 = f7 / f8;
        if (Math.abs(f9) >= 1.0f) {
            float f10 = 1.5707964f - (f9 / ((f9 * f9) + 0.28f));
            return f7 < 0.0f ? f10 - 3.1415927f : f10;
        }
        float f11 = f9 / (((0.28f * f9) * f9) + 1.0f);
        if (f8 < 0.0f) {
            return f11 + (f7 < 0.0f ? -3.1415927f : 3.1415927f);
        }
        return f11;
    }
}
