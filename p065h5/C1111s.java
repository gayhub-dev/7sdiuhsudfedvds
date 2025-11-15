package p065h5;

import p043f.C0986c;

/* compiled from: HttpStatus.java */
/* renamed from: h5.s */
/* loaded from: classes.dex */
public class C1111s {

    /* renamed from: a */
    public static final int[] f2364a = new int[508];

    static {
        for (int i7 : C0986c.org$eclipse$jetty$http$HttpStatus$Code$s$values()) {
            f2364a[C0986c.m953f(i7)] = i7;
        }
    }

    /* renamed from: a */
    public static String m1251a(int i7) {
        int i8 = i7 <= 507 ? f2364a[i7] : 0;
        return i8 != 0 ? C0986c.m954g(i8) : Integer.toString(i7);
    }
}
