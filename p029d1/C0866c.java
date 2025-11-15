package p029d1;

/* compiled from: TencentXLog.java */
/* renamed from: d1.c */
/* loaded from: classes.dex */
public class C0866c {

    /* renamed from: a */
    public static volatile C0866c f1293a = null;

    /* renamed from: b */
    public static String f1294b = "";

    /* renamed from: a */
    public static C0866c m674a() {
        if (f1293a == null) {
            synchronized (C0866c.class) {
                if (f1293a == null) {
                    f1293a = new C0866c();
                }
            }
        }
        return f1293a;
    }
}
