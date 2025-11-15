package p078j2;

import android.content.Context;

/* compiled from: SharedPFUtils.java */
/* renamed from: j2.l */
/* loaded from: classes.dex */
public class C1197l {
    /* renamed from: a */
    public static int m1421a(Context context, String str, int i7) {
        return context.getSharedPreferences("config", 0).getInt(str, i7);
    }

    /* renamed from: b */
    public static boolean m1422b(Context context, String str, boolean z6) {
        return context.getSharedPreferences("config", 0).getBoolean(str, z6);
    }

    /* renamed from: c */
    public static void m1423c(Context context, String str, int i7) {
        context.getSharedPreferences("config", 0).edit().putInt(str, i7).commit();
    }

    /* renamed from: d */
    public static void m1424d(Context context, String str, boolean z6) {
        context.getSharedPreferences("config", 0).edit().putBoolean(str, z6).commit();
    }
}
