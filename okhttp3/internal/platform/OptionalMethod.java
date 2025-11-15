package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p009b.C0413b;

/* loaded from: classes.dex */
class OptionalMethod<T> {
    private final String methodName;
    private final Class[] methodParams;
    private final Class<?> returnType;

    public OptionalMethod(Class<?> cls, String str, Class... clsArr) {
        this.returnType = cls;
        this.methodName = str;
        this.methodParams = clsArr;
    }

    private Method getMethod(Class<?> cls) throws NoSuchMethodException, SecurityException {
        Class<?> cls2;
        String str = this.methodName;
        if (str == null) {
            return null;
        }
        Method publicMethod = getPublicMethod(cls, str, this.methodParams);
        if (publicMethod == null || (cls2 = this.returnType) == null || cls2.isAssignableFrom(publicMethod.getReturnType())) {
            return publicMethod;
        }
        return null;
    }

    private static Method getPublicMethod(Class<?> cls, String str, Class[] clsArr) throws NoSuchMethodException, SecurityException {
        try {
            Method method = cls.getMethod(str, clsArr);
            try {
                if ((method.getModifiers() & 1) == 0) {
                    return null;
                }
            } catch (NoSuchMethodException unused) {
            }
            return method;
        } catch (NoSuchMethodException unused2) {
            return null;
        }
    }

    public Object invoke(T t6, Object... objArr) throws NoSuchMethodException, SecurityException {
        Method method = getMethod(t6.getClass());
        if (method == null) {
            StringBuilder sbM112a = C0413b.m112a("Method ");
            sbM112a.append(this.methodName);
            sbM112a.append(" not supported for object ");
            sbM112a.append(t6);
            throw new AssertionError(sbM112a.toString());
        }
        try {
            return method.invoke(t6, objArr);
        } catch (IllegalAccessException e7) {
            AssertionError assertionError = new AssertionError("Unexpectedly could not call: " + method);
            assertionError.initCause(e7);
            throw assertionError;
        }
    }

    public Object invokeOptional(T t6, Object... objArr) throws NoSuchMethodException, SecurityException {
        Method method = getMethod(t6.getClass());
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(t6, objArr);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    public Object invokeOptionalWithoutCheckedException(T t6, Object... objArr) {
        try {
            return invokeOptional(t6, objArr);
        } catch (InvocationTargetException e7) {
            Throwable targetException = e7.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public Object invokeWithoutCheckedException(T t6, Object... objArr) {
        try {
            return invoke(t6, objArr);
        } catch (InvocationTargetException e7) {
            Throwable targetException = e7.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public boolean isSupported(T t6) {
        return getMethod(t6.getClass()) != null;
    }
}
