package p161t5;

import android.support.constraint.motion.C0079a;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: TypeUtil.java */
/* renamed from: t5.s */
/* loaded from: classes.dex */
public class C1927s {

    /* renamed from: a */
    public static final InterfaceC2016c f5702a;

    static {
        Properties properties = C2015b.f5863a;
        f5702a = C2015b.m2349a(C1927s.class.getName());
        HashMap map = new HashMap();
        Class cls = Boolean.TYPE;
        map.put("boolean", cls);
        map.put("byte", Byte.TYPE);
        map.put("char", Character.TYPE);
        map.put("double", Double.TYPE);
        Class cls2 = Float.TYPE;
        map.put("float", cls2);
        Class cls3 = Integer.TYPE;
        map.put("int", cls3);
        Class cls4 = Long.TYPE;
        map.put("long", cls4);
        map.put("short", Short.TYPE);
        map.put("void", Void.TYPE);
        map.put("java.lang.Boolean.TYPE", cls);
        map.put("java.lang.Byte.TYPE", Byte.TYPE);
        map.put("java.lang.Character.TYPE", Character.TYPE);
        map.put("java.lang.Double.TYPE", Double.TYPE);
        map.put("java.lang.Float.TYPE", cls2);
        map.put("java.lang.Integer.TYPE", cls3);
        map.put("java.lang.Long.TYPE", cls4);
        map.put("java.lang.Short.TYPE", Short.TYPE);
        map.put("java.lang.Void.TYPE", Void.TYPE);
        map.put("java.lang.Boolean", Boolean.class);
        map.put("java.lang.Byte", Byte.class);
        map.put("java.lang.Character", Character.class);
        map.put("java.lang.Double", Double.class);
        map.put("java.lang.Float", Float.class);
        map.put("java.lang.Integer", Integer.class);
        map.put("java.lang.Long", Long.class);
        map.put("java.lang.Short", Short.class);
        map.put("Boolean", Boolean.class);
        map.put("Byte", Byte.class);
        map.put("Character", Character.class);
        map.put("Double", Double.class);
        map.put("Float", Float.class);
        map.put("Integer", Integer.class);
        map.put("Long", Long.class);
        map.put("Short", Short.class);
        map.put(null, Void.TYPE);
        map.put("string", String.class);
        map.put("String", String.class);
        map.put("java.lang.String", String.class);
        HashMap map2 = new HashMap();
        map2.put(cls, "boolean");
        map2.put(Byte.TYPE, "byte");
        map2.put(Character.TYPE, "char");
        map2.put(Double.TYPE, "double");
        map2.put(cls2, "float");
        map2.put(cls3, "int");
        map2.put(cls4, "long");
        map2.put(Short.TYPE, "short");
        map2.put(Void.TYPE, "void");
        map2.put(Boolean.class, "java.lang.Boolean");
        map2.put(Byte.class, "java.lang.Byte");
        map2.put(Character.class, "java.lang.Character");
        map2.put(Double.class, "java.lang.Double");
        map2.put(Float.class, "java.lang.Float");
        map2.put(Integer.class, "java.lang.Integer");
        map2.put(Long.class, "java.lang.Long");
        map2.put(Short.class, "java.lang.Short");
        map2.put(null, "void");
        map2.put(String.class, "java.lang.String");
        HashMap map3 = new HashMap();
        try {
            Class[] clsArr = {String.class};
            map3.put(cls, Boolean.class.getMethod("valueOf", clsArr));
            map3.put(Byte.TYPE, Byte.class.getMethod("valueOf", clsArr));
            map3.put(Double.TYPE, Double.class.getMethod("valueOf", clsArr));
            map3.put(cls2, Float.class.getMethod("valueOf", clsArr));
            map3.put(cls3, Integer.class.getMethod("valueOf", clsArr));
            map3.put(cls4, Long.class.getMethod("valueOf", clsArr));
            map3.put(Short.TYPE, Short.class.getMethod("valueOf", clsArr));
            map3.put(Boolean.class, Boolean.class.getMethod("valueOf", clsArr));
            map3.put(Byte.class, Byte.class.getMethod("valueOf", clsArr));
            map3.put(Double.class, Double.class.getMethod("valueOf", clsArr));
            map3.put(Float.class, Float.class.getMethod("valueOf", clsArr));
            map3.put(Integer.class, Integer.class.getMethod("valueOf", clsArr));
            map3.put(Long.class, Long.class.getMethod("valueOf", clsArr));
            map3.put(Short.class, Short.class.getMethod("valueOf", clsArr));
        } catch (Exception e7) {
            throw new Error(e7);
        }
    }

    /* renamed from: a */
    public static <T> List<T> m2258a(T[] tArr) {
        return tArr == null ? Collections.emptyList() : Arrays.asList(tArr);
    }

    /* renamed from: b */
    public static byte m2259b(byte b7) {
        byte b8 = (byte) ((((b7 >> 6) * 25) + (b7 & 31)) - 16);
        if (b8 < 0 || b8 > 15) {
            throw new IllegalArgumentException(C0079a.m93a("!hex ", b7));
        }
        return b8;
    }

    /* renamed from: c */
    public static int m2260c(int i7) {
        int i8 = (((i7 >> 6) * 25) + (i7 & 31)) - 16;
        if (i8 < 0 || i8 > 15) {
            throw new NumberFormatException(C0079a.m93a("!hex ", i7));
        }
        return i8;
    }

    /* renamed from: d */
    public static int m2261d(String str, int i7, int i8, int i9) {
        if (i8 < 0) {
            i8 = str.length() - i7;
        }
        int i10 = 0;
        for (int i11 = 0; i11 < i8; i11++) {
            int iM2260c = m2260c(str.charAt(i7 + i11));
            if (iM2260c < 0 || iM2260c >= i9) {
                throw new NumberFormatException(str.substring(i7, i8 + i7));
            }
            i10 = (i10 * i9) + iM2260c;
        }
        return i10;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: SimplifyVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v4 int, still in use, count: 1, list:
          (r2v4 int) from 0x0021: ARITH (r3v2 int) = (r2v4 int) + (-97 int)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.unbindArgUsage(InsnRemover.java:174)
        	at jadx.core.utils.InsnRemover.unbindAllArgs(InsnRemover.java:106)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:90)
        	at jadx.core.utils.InsnRemover.unbindArgUsage(InsnRemover.java:174)
        	at jadx.core.dex.instructions.args.InsnArg.wrapInstruction(InsnArg.java:141)
        	at jadx.core.dex.visitors.SimplifyVisitor.simplifyArgs(SimplifyVisitor.java:116)
        	at jadx.core.dex.visitors.SimplifyVisitor.simplifyInsn(SimplifyVisitor.java:132)
        	at jadx.core.dex.visitors.SimplifyVisitor.simplifyBlock(SimplifyVisitor.java:86)
        	at jadx.core.dex.visitors.SimplifyVisitor.visit(SimplifyVisitor.java:71)
        */
    /* renamed from: e */
    public static int m2262e(byte[] r5, int r6, int r7, int r8) {
        /*
            if (r7 >= 0) goto L4
            int r7 = r5.length
            int r7 = r7 - r6
        L4:
            r0 = 0
            r1 = 0
        L6:
            if (r0 >= r7) goto L38
            int r2 = r6 + r0
            r2 = r5[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            char r2 = (char) r2
            int r3 = r2 + (-48)
            r4 = 10
            if (r3 < 0) goto L19
            if (r3 >= r8) goto L19
            if (r3 < r4) goto L23
        L19:
            int r2 = r2 + 10
            int r3 = r2 + (-65)
            if (r3 < r4) goto L21
            if (r3 < r8) goto L23
        L21:
            int r3 = r2 + (-97)
        L23:
            if (r3 < 0) goto L2d
            if (r3 >= r8) goto L2d
            int r1 = r1 * r8
            int r1 = r1 + r3
            int r0 = r0 + 1
            goto L6
        L2d:
            java.lang.NumberFormatException r8 = new java.lang.NumberFormatException
            java.lang.String r0 = new java.lang.String
            r0.<init>(r5, r6, r7)
            r8.<init>(r0)
            throw r8
        L38:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p161t5.C1927s.m2262e(byte[], int, int, int):int");
    }

    /* renamed from: f */
    public static void m2263f(byte b7, Appendable appendable) {
        int i7 = ((b7 & 240) >> 4) & 15;
        try {
            appendable.append((char) ((i7 > 9 ? 55 : 48) + i7));
            int i8 = b7 & 15;
            appendable.append((char) ((i8 > 9 ? 55 : 48) + i8));
        } catch (IOException e7) {
            throw new RuntimeException(e7);
        }
    }
}
