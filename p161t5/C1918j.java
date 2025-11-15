package p161t5;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: LazyList.java */
/* renamed from: t5.j */
/* loaded from: classes.dex */
public class C1918j implements Cloneable, Serializable {
    /* renamed from: I */
    public static Object m2221I(Object obj, Class<?> cls) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NegativeArraySizeException {
        if (obj == null) {
            return Array.newInstance(cls, 0);
        }
        if (!(obj instanceof List)) {
            Object objNewInstance = Array.newInstance(cls, 1);
            Array.set(objNewInstance, 0, obj);
            return objNewInstance;
        }
        List list = (List) obj;
        if (!cls.isPrimitive()) {
            return list.toArray((Object[]) Array.newInstance(cls, list.size()));
        }
        Object objNewInstance2 = Array.newInstance(cls, list.size());
        for (int i7 = 0; i7 < list.size(); i7++) {
            Array.set(objNewInstance2, i7, list.get(i7));
        }
        return objNewInstance2;
    }

    /* renamed from: b */
    public static Object m2222b(Object obj, Object obj2) {
        if (obj == null) {
            if (!(obj2 instanceof List) && obj2 != null) {
                return obj2;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(obj2);
            return arrayList;
        }
        if (obj instanceof List) {
            ((List) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(obj);
        arrayList2.add(obj2);
        return arrayList2;
    }

    /* renamed from: d */
    public static <T> T[] m2223d(T[] tArr, T t6, Class<?> cls) {
        if (tArr == null) {
            T[] tArr2 = (T[]) ((Object[]) Array.newInstance(cls, 1));
            tArr2[0] = t6;
            return tArr2;
        }
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), Array.getLength(tArr) + 1));
        System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
        tArr3[tArr.length] = t6;
        return tArr3;
    }

    /* renamed from: g */
    public static <E> List<E> m2224g(E[] eArr) {
        return (eArr == null || eArr.length == 0) ? new ArrayList() : new ArrayList(Arrays.asList(eArr));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: j */
    public static <E> E m2225j(Object obj, int i7) {
        if (obj == 0) {
            throw new IndexOutOfBoundsException();
        }
        if (obj instanceof List) {
            return (E) ((List) obj).get(i7);
        }
        if (i7 == 0) {
            return obj;
        }
        throw new IndexOutOfBoundsException();
    }

    /* renamed from: k */
    public static <E> List<E> m2226k(Object obj, boolean z6) {
        if (obj != null) {
            return obj instanceof List ? (List) obj : Collections.singletonList(obj);
        }
        if (z6) {
            return null;
        }
        return Collections.emptyList();
    }

    /* renamed from: q */
    public static Object m2227q(Object obj, int i7) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof List)) {
            if (i7 == 0) {
                return null;
            }
            return obj;
        }
        List list = (List) obj;
        list.remove(i7);
        if (list.size() == 0) {
            return null;
        }
        return obj;
    }

    /* renamed from: x */
    public static int m2228x(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof List) {
            return ((List) obj).size();
        }
        return 1;
    }
}
