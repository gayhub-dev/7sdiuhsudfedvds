package p032d4;

import p014b4.InterfaceC0444d;

/* compiled from: ObjectHelper.java */
/* renamed from: d4.b */
/* loaded from: classes.dex */
public final class C0871b {

    /* renamed from: a */
    public static final InterfaceC0444d<Object, Object> f1338a = new a();

    /* compiled from: ObjectHelper.java */
    /* renamed from: d4.b$a */
    public static final class a implements InterfaceC0444d<Object, Object> {
    }

    /* renamed from: a */
    public static boolean m676a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: b */
    public static int m677b(int i7, String str) {
        if (i7 > 0) {
            return i7;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + i7);
    }

    /* renamed from: c */
    public static long m678c(long j7, String str) {
        if (j7 > 0) {
            return j7;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + j7);
    }
}
