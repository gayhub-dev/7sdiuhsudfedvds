package p019c1;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: SPUtil.java */
/* renamed from: c1.a */
/* loaded from: classes.dex */
public class C0504a {

    /* renamed from: a */
    public static SharedPreferences f372a;

    /* renamed from: a */
    public static String m313a(Context context, String str, String str2) {
        if (f372a == null) {
            f372a = context.getSharedPreferences("config", 0);
        }
        return f372a.getString(str, str2);
    }

    /* renamed from: b */
    public static void m314b(Context context, String str, String str2) {
        if (f372a == null) {
            f372a = context.getSharedPreferences("config", 0);
        }
        f372a.edit().putString(str, str2).apply();
    }
}
