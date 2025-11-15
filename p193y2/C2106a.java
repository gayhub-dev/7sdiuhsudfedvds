package p193y2;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.util.Objects;
import p086k2.C1231b;

/* compiled from: CtvitDeviceUtils.java */
/* renamed from: y2.a */
/* loaded from: classes.dex */
public class C2106a {
    /* renamed from: a */
    public static String m2548a() {
        Objects.requireNonNull(C1231b.m1454a());
        return "";
    }

    /* renamed from: b */
    public static String m2549b() {
        return C1231b.m1454a().f2763a;
    }

    /* renamed from: c */
    public static String m2550c() {
        WindowManager windowManager = (WindowManager) C1231b.f2761c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
    }
}
