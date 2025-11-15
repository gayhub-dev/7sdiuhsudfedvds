package p086k2;

import android.app.Application;

/* compiled from: CtvitUtils.java */
/* renamed from: k2.b */
/* loaded from: classes.dex */
public class C1231b {

    /* renamed from: b */
    public static volatile C1231b f2760b;

    /* renamed from: c */
    public static Application f2761c;

    /* renamed from: d */
    public static boolean f2762d;

    /* renamed from: a */
    public String f2763a = "";

    /* renamed from: a */
    public static C1231b m1454a() {
        if (f2760b == null) {
            synchronized (C1231b.class) {
                if (f2760b == null) {
                    f2760b = new C1231b();
                }
            }
        }
        return f2760b;
    }
}
