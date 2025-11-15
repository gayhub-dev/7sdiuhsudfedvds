package android.arch.lifecycle;

import android.support.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: Lifecycling.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.arch.lifecycle.h */
/* loaded from: classes.dex */
public class C0057h {

    /* renamed from: a */
    public static Map<Class, Integer> f105a = new HashMap();

    /* renamed from: b */
    public static Map<Class, List<Constructor<? extends InterfaceC0051b>>> f106b = new HashMap();

    /* renamed from: a */
    public static InterfaceC0051b m85a(Constructor<? extends InterfaceC0051b> constructor, Object obj) {
        try {
            return constructor.newInstance(obj);
        } catch (IllegalAccessException e7) {
            throw new RuntimeException(e7);
        } catch (InstantiationException e8) {
            throw new RuntimeException(e8);
        } catch (InvocationTargetException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: b */
    public static String m86b(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0131 A[SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m87c(java.lang.Class<?> r11) throws java.lang.NoSuchMethodException, java.lang.SecurityException {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.C0057h.m87c(java.lang.Class):int");
    }
}
