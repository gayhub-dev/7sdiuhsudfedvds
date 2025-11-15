package p092l0;

import com.alibaba.fastjson.asm.C0532a;

/* compiled from: ManifestParser.java */
/* renamed from: l0.d */
/* loaded from: classes.dex */
public final class C1413d {
    /* renamed from: a */
    public static InterfaceC1412c m1598a(String str) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        try {
            Class<?> cls = Class.forName(str);
            try {
                Object objNewInstance = cls.newInstance();
                if (objNewInstance instanceof InterfaceC1412c) {
                    return (InterfaceC1412c) objNewInstance;
                }
                throw new RuntimeException(C0532a.m338a("Expected instanceof GlideModule, but found: ", objNewInstance));
            } catch (IllegalAccessException e7) {
                throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, e7);
            } catch (InstantiationException e8) {
                throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, e8);
            }
        } catch (ClassNotFoundException e9) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e9);
        }
    }
}
