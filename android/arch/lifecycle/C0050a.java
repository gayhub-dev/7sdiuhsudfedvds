package android.arch.lifecycle;

import android.arch.lifecycle.AbstractC0052c;
import android.support.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import p009b.C0413b;

/* compiled from: ClassesInfoCache.java */
/* renamed from: android.arch.lifecycle.a */
/* loaded from: classes.dex */
public class C0050a {

    /* renamed from: c */
    public static C0050a f81c = new C0050a();

    /* renamed from: a */
    public final Map<Class, a> f82a = new HashMap();

    /* renamed from: b */
    public final Map<Class, Boolean> f83b = new HashMap();

    /* compiled from: ClassesInfoCache.java */
    /* renamed from: android.arch.lifecycle.a$a */
    public static class a {

        /* renamed from: a */
        public final Map<AbstractC0052c.a, List<b>> f84a = new HashMap();

        /* renamed from: b */
        public final Map<b, AbstractC0052c.a> f85b;

        public a(Map<b, AbstractC0052c.a> map) {
            this.f85b = map;
            for (Map.Entry<b, AbstractC0052c.a> entry : map.entrySet()) {
                AbstractC0052c.a value = entry.getValue();
                List<b> arrayList = this.f84a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f84a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        /* renamed from: a */
        public static void m73a(List<b> list, InterfaceC0054e interfaceC0054e, AbstractC0052c.a aVar, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    b bVar = list.get(size);
                    Objects.requireNonNull(bVar);
                    try {
                        int i7 = bVar.f86a;
                        if (i7 == 0) {
                            bVar.f87b.invoke(obj, new Object[0]);
                        } else if (i7 == 1) {
                            bVar.f87b.invoke(obj, interfaceC0054e);
                        } else if (i7 == 2) {
                            bVar.f87b.invoke(obj, interfaceC0054e, aVar);
                        }
                    } catch (IllegalAccessException e7) {
                        throw new RuntimeException(e7);
                    } catch (InvocationTargetException e8) {
                        throw new RuntimeException("Failed to call observer method", e8.getCause());
                    }
                }
            }
        }
    }

    /* compiled from: ClassesInfoCache.java */
    /* renamed from: android.arch.lifecycle.a$b */
    public static class b {

        /* renamed from: a */
        public final int f86a;

        /* renamed from: b */
        public final Method f87b;

        public b(int i7, Method method) {
            this.f86a = i7;
            this.f87b = method;
            method.setAccessible(true);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.f86a == bVar.f86a && this.f87b.getName().equals(bVar.f87b.getName());
        }

        public int hashCode() {
            return this.f87b.getName().hashCode() + (this.f86a * 31);
        }
    }

    /* renamed from: a */
    public final a m70a(Class cls, @Nullable Method[] methodArr) throws SecurityException {
        int i7;
        a aVarM71b;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (aVarM71b = m71b(superclass)) != null) {
            map.putAll(aVarM71b.f85b);
        }
        for (Class cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, AbstractC0052c.a> entry : m71b(cls2).f85b.entrySet()) {
                m72c(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e7) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e7);
            }
        }
        boolean z6 = false;
        for (Method method : methodArr) {
            InterfaceC0061l interfaceC0061l = (InterfaceC0061l) method.getAnnotation(InterfaceC0061l.class);
            if (interfaceC0061l != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i7 = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(InterfaceC0054e.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i7 = 1;
                }
                AbstractC0052c.a aVarValue = interfaceC0061l.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(AbstractC0052c.a.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (aVarValue != AbstractC0052c.a.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i7 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                m72c(map, new b(i7, method), aVarValue, cls);
                z6 = true;
            }
        }
        a aVar = new a(map);
        this.f82a.put(cls, aVar);
        this.f83b.put(cls, Boolean.valueOf(z6));
        return aVar;
    }

    /* renamed from: b */
    public a m71b(Class cls) {
        a aVar = this.f82a.get(cls);
        return aVar != null ? aVar : m70a(cls, null);
    }

    /* renamed from: c */
    public final void m72c(Map<b, AbstractC0052c.a> map, b bVar, AbstractC0052c.a aVar, Class cls) {
        AbstractC0052c.a aVar2 = map.get(bVar);
        if (aVar2 == null || aVar == aVar2) {
            if (aVar2 == null) {
                map.put(bVar, aVar);
                return;
            }
            return;
        }
        Method method = bVar.f87b;
        StringBuilder sbM112a = C0413b.m112a("Method ");
        sbM112a.append(method.getName());
        sbM112a.append(" in ");
        sbM112a.append(cls.getName());
        sbM112a.append(" already declared with different @OnLifecycleEvent value: previous value ");
        sbM112a.append(aVar2);
        sbM112a.append(", new value ");
        sbM112a.append(aVar);
        throw new IllegalArgumentException(sbM112a.toString());
    }
}
