package p202z4;

import android.support.constraint.C0072a;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import p009b.C0413b;

/* compiled from: AnnotationLiteral.java */
/* renamed from: z4.a */
/* loaded from: classes.dex */
public abstract class AbstractC2156a<T extends Annotation> implements Annotation, Serializable {
    private static final long serialVersionUID = 1;
    private transient Class<T> annotationType;
    private transient Integer cachedHashCode;
    private transient Method[] members;

    public AbstractC2156a() {
        if (getMembers().length == 0) {
            this.cachedHashCode = 0;
        } else {
            this.cachedHashCode = null;
        }
    }

    private void appendInBraces(StringBuilder sb, String str) {
        sb.append('{');
        sb.append(str.substring(1, str.length() - 1));
        sb.append('}');
    }

    private static Class<?> getAnnotationLiteralSubclass(Class<?> cls) {
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass.equals(AbstractC2156a.class)) {
            return cls;
        }
        if (superclass.equals(Object.class)) {
            return null;
        }
        return getAnnotationLiteralSubclass(superclass);
    }

    private static Object getMemberValue(Method method, Annotation annotation) {
        Object objInvoke = invoke(method, annotation);
        if (objInvoke != null) {
            return objInvoke;
        }
        StringBuilder sbM112a = C0413b.m112a("Annotation member value ");
        sbM112a.append(annotation.getClass().getName());
        sbM112a.append(".");
        sbM112a.append(method.getName());
        sbM112a.append(" must not be null");
        throw new IllegalArgumentException(sbM112a.toString());
    }

    private Method[] getMembers() throws SecurityException {
        if (this.members == null) {
            Method[] declaredMethods = annotationType().getDeclaredMethods();
            this.members = declaredMethods;
            if (declaredMethods.length > 0 && !annotationType().isAssignableFrom(getClass())) {
                throw new RuntimeException(getClass() + " does not implement the annotation type with members " + annotationType().getName());
            }
        }
        return this.members;
    }

    private static <T> Class<T> getTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        if (parameterizedType.getActualTypeArguments().length == 1) {
            return (Class) parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }

    private static Object invoke(Method method, Object obj) {
        try {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method.invoke(obj, new Object[0]);
        } catch (IllegalAccessException e7) {
            StringBuilder sbM112a = C0413b.m112a("Error checking value of member method ");
            sbM112a.append(method.getName());
            sbM112a.append(" on ");
            sbM112a.append(method.getDeclaringClass());
            throw new RuntimeException(sbM112a.toString(), e7);
        } catch (IllegalArgumentException e8) {
            StringBuilder sbM112a2 = C0413b.m112a("Error checking value of member method ");
            sbM112a2.append(method.getName());
            sbM112a2.append(" on ");
            sbM112a2.append(method.getDeclaringClass());
            throw new RuntimeException(sbM112a2.toString(), e8);
        } catch (InvocationTargetException e9) {
            StringBuilder sbM112a3 = C0413b.m112a("Error checking value of member method ");
            sbM112a3.append(method.getName());
            sbM112a3.append(" on ");
            sbM112a3.append(method.getDeclaringClass());
            throw new RuntimeException(sbM112a3.toString(), e9);
        }
    }

    @Override // java.lang.annotation.Annotation
    public Class<? extends Annotation> annotationType() {
        if (this.annotationType == null) {
            Class<?> annotationLiteralSubclass = getAnnotationLiteralSubclass(getClass());
            if (annotationLiteralSubclass == null) {
                throw new RuntimeException(getClass() + " is not a subclass of AnnotationLiteral");
            }
            Class<T> typeParameter = getTypeParameter(annotationLiteralSubclass);
            this.annotationType = typeParameter;
            if (typeParameter == null) {
                throw new RuntimeException(getClass() + " does not specify the type parameter T of AnnotationLiteral<T>");
            }
        }
        return this.annotationType;
    }

    @Override // java.lang.annotation.Annotation
    public boolean equals(Object obj) throws SecurityException {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof Annotation)) {
            Annotation annotation = (Annotation) obj;
            if (annotationType().equals(annotation.annotationType())) {
                for (Method method : getMembers()) {
                    Object memberValue = getMemberValue(method, this);
                    Object memberValue2 = getMemberValue(method, annotation);
                    if ((memberValue instanceof byte[]) && (memberValue2 instanceof byte[])) {
                        if (!Arrays.equals((byte[]) memberValue, (byte[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof short[]) && (memberValue2 instanceof short[])) {
                        if (!Arrays.equals((short[]) memberValue, (short[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof int[]) && (memberValue2 instanceof int[])) {
                        if (!Arrays.equals((int[]) memberValue, (int[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof long[]) && (memberValue2 instanceof long[])) {
                        if (!Arrays.equals((long[]) memberValue, (long[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof float[]) && (memberValue2 instanceof float[])) {
                        if (!Arrays.equals((float[]) memberValue, (float[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof double[]) && (memberValue2 instanceof double[])) {
                        if (!Arrays.equals((double[]) memberValue, (double[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof char[]) && (memberValue2 instanceof char[])) {
                        if (!Arrays.equals((char[]) memberValue, (char[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof boolean[]) && (memberValue2 instanceof boolean[])) {
                        if (!Arrays.equals((boolean[]) memberValue, (boolean[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof Object[]) && (memberValue2 instanceof Object[])) {
                        if (!Arrays.equals((Object[]) memberValue, (Object[]) memberValue2)) {
                            return false;
                        }
                    } else if (!memberValue.equals(memberValue2)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public int hashCode() throws SecurityException {
        Integer num = this.cachedHashCode;
        if (num != null) {
            return num.intValue();
        }
        int iHashCode = 0;
        for (Method method : getMembers()) {
            int iHashCode2 = method.getName().hashCode() * 127;
            Object memberValue = getMemberValue(method, this);
            iHashCode += (memberValue instanceof boolean[] ? Arrays.hashCode((boolean[]) memberValue) : memberValue instanceof short[] ? Arrays.hashCode((short[]) memberValue) : memberValue instanceof int[] ? Arrays.hashCode((int[]) memberValue) : memberValue instanceof long[] ? Arrays.hashCode((long[]) memberValue) : memberValue instanceof float[] ? Arrays.hashCode((float[]) memberValue) : memberValue instanceof double[] ? Arrays.hashCode((double[]) memberValue) : memberValue instanceof byte[] ? Arrays.hashCode((byte[]) memberValue) : memberValue instanceof char[] ? Arrays.hashCode((char[]) memberValue) : memberValue instanceof Object[] ? Arrays.hashCode((Object[]) memberValue) : memberValue.hashCode()) ^ iHashCode2;
        }
        return iHashCode;
    }

    @Override // java.lang.annotation.Annotation
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        sb.append(annotationType().getName());
        sb.append('(');
        for (int i7 = 0; i7 < getMembers().length; i7++) {
            sb.append(getMembers()[i7].getName());
            sb.append('=');
            Object memberValue = getMemberValue(getMembers()[i7], this);
            if (memberValue instanceof boolean[]) {
                appendInBraces(sb, Arrays.toString((boolean[]) memberValue));
            } else if (memberValue instanceof byte[]) {
                appendInBraces(sb, Arrays.toString((byte[]) memberValue));
            } else if (memberValue instanceof short[]) {
                appendInBraces(sb, Arrays.toString((short[]) memberValue));
            } else if (memberValue instanceof int[]) {
                appendInBraces(sb, Arrays.toString((int[]) memberValue));
            } else if (memberValue instanceof long[]) {
                appendInBraces(sb, Arrays.toString((long[]) memberValue));
            } else if (memberValue instanceof float[]) {
                appendInBraces(sb, Arrays.toString((float[]) memberValue));
            } else if (memberValue instanceof double[]) {
                appendInBraces(sb, Arrays.toString((double[]) memberValue));
            } else if (memberValue instanceof char[]) {
                appendInBraces(sb, Arrays.toString((char[]) memberValue));
            } else if (memberValue instanceof String[]) {
                String[] strArr = (String[]) memberValue;
                String[] strArr2 = new String[strArr.length];
                for (int i8 = 0; i8 < strArr.length; i8++) {
                    strArr2[i8] = C0072a.m92a(C0413b.m112a("\""), strArr[i8], "\"");
                }
                appendInBraces(sb, Arrays.toString(strArr2));
            } else if (memberValue instanceof Class[]) {
                Class[] clsArr = (Class[]) memberValue;
                String[] strArr3 = new String[clsArr.length];
                for (int i9 = 0; i9 < clsArr.length; i9++) {
                    strArr3[i9] = clsArr[i9].getName() + ".class";
                }
                appendInBraces(sb, Arrays.toString(strArr3));
            } else if (memberValue instanceof Object[]) {
                appendInBraces(sb, Arrays.toString((Object[]) memberValue));
            } else if (memberValue instanceof String) {
                sb.append('\"');
                sb.append(memberValue);
                sb.append('\"');
            } else if (memberValue instanceof Class) {
                sb.append(((Class) memberValue).getName());
                sb.append(".class");
            } else {
                sb.append(memberValue);
            }
            if (i7 < getMembers().length - 1) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
