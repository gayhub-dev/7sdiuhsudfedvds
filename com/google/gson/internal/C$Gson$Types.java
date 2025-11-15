package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import okhttp3.HttpUrl;
import p009b.C0413b;

/* renamed from: com.google.gson.internal.$Gson$Types, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$Gson$Types {
    public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    /* compiled from: $Gson$Types.java */
    /* renamed from: com.google.gson.internal.$Gson$Types$GenericArrayTypeImpl */
    public static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type type) {
            this.componentType = C$Gson$Types.canonicalize(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && C$Gson$Types.equals(this, (GenericArrayType) obj);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return C$Gson$Types.typeToString(this.componentType) + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
    }

    /* compiled from: $Gson$Types.java */
    /* renamed from: com.google.gson.internal.$Gson$Types$ParameterizedTypeImpl */
    public static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z6 = true;
                boolean z7 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
                if (type == null && !z7) {
                    z6 = false;
                }
                C$Gson$Preconditions.checkArgument(z6);
            }
            this.ownerType = type == null ? null : C$Gson$Types.canonicalize(type);
            this.rawType = C$Gson$Types.canonicalize(type2);
            Type[] typeArr2 = (Type[]) typeArr.clone();
            this.typeArguments = typeArr2;
            int length = typeArr2.length;
            for (int i7 = 0; i7 < length; i7++) {
                C$Gson$Preconditions.checkNotNull(this.typeArguments[i7]);
                C$Gson$Types.checkNotPrimitive(this.typeArguments[i7]);
                Type[] typeArr3 = this.typeArguments;
                typeArr3[i7] = C$Gson$Types.canonicalize(typeArr3[i7]);
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && C$Gson$Types.equals(this, (ParameterizedType) obj);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ C$Gson$Types.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            int length = this.typeArguments.length;
            if (length == 0) {
                return C$Gson$Types.typeToString(this.rawType);
            }
            StringBuilder sb = new StringBuilder((length + 1) * 30);
            sb.append(C$Gson$Types.typeToString(this.rawType));
            sb.append("<");
            sb.append(C$Gson$Types.typeToString(this.typeArguments[0]));
            for (int i7 = 1; i7 < length; i7++) {
                sb.append(", ");
                sb.append(C$Gson$Types.typeToString(this.typeArguments[i7]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    /* compiled from: $Gson$Types.java */
    /* renamed from: com.google.gson.internal.$Gson$Types$WildcardTypeImpl */
    public static final class WildcardTypeImpl implements WildcardType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            C$Gson$Preconditions.checkArgument(typeArr2.length <= 1);
            C$Gson$Preconditions.checkArgument(typeArr.length == 1);
            if (typeArr2.length != 1) {
                C$Gson$Preconditions.checkNotNull(typeArr[0]);
                C$Gson$Types.checkNotPrimitive(typeArr[0]);
                this.lowerBound = null;
                this.upperBound = C$Gson$Types.canonicalize(typeArr[0]);
                return;
            }
            C$Gson$Preconditions.checkNotNull(typeArr2[0]);
            C$Gson$Types.checkNotPrimitive(typeArr2[0]);
            C$Gson$Preconditions.checkArgument(typeArr[0] == Object.class);
            this.lowerBound = C$Gson$Types.canonicalize(typeArr2[0]);
            this.upperBound = Object.class;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && C$Gson$Types.equals(this, (WildcardType) obj);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.lowerBound;
            return type != null ? new Type[]{type} : C$Gson$Types.EMPTY_TYPE_ARRAY;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public int hashCode() {
            Type type = this.lowerBound;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            if (this.lowerBound != null) {
                StringBuilder sbM112a = C0413b.m112a("? super ");
                sbM112a.append(C$Gson$Types.typeToString(this.lowerBound));
                return sbM112a.toString();
            }
            if (this.upperBound == Object.class) {
                return "?";
            }
            StringBuilder sbM112a2 = C0413b.m112a("? extends ");
            sbM112a2.append(C$Gson$Types.typeToString(this.upperBound));
            return sbM112a2.toString();
        }
    }

    private C$Gson$Types() {
        throw new UnsupportedOperationException();
    }

    public static GenericArrayType arrayOf(Type type) {
        return new GenericArrayTypeImpl(type);
    }

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new GenericArrayTypeImpl(canonicalize(cls.getComponentType())) : cls;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
        }
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
    }

    public static void checkNotPrimitive(Type type) {
        C$Gson$Preconditions.checkArgument(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean equals(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            return equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return equals(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        }
        if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
        }
        if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) type;
        TypeVariable typeVariable2 = (TypeVariable) type2;
        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
    }

    public static Type getArrayComponentType(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    public static Type getCollectionElementType(Type type, Class<?> cls) {
        Type supertype = getSupertype(type, cls, Collection.class);
        if (supertype instanceof WildcardType) {
            supertype = ((WildcardType) supertype).getUpperBounds()[0];
        }
        return supertype instanceof ParameterizedType ? ((ParameterizedType) supertype).getActualTypeArguments()[0] : Object.class;
    }

    public static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i7 = 0; i7 < length; i7++) {
                if (interfaces[i7] == cls2) {
                    return cls.getGenericInterfaces()[i7];
                }
                if (cls2.isAssignableFrom(interfaces[i7])) {
                    return getGenericSupertype(cls.getGenericInterfaces()[i7], interfaces[i7], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return getGenericSupertype(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static Type[] getMapKeyAndValueTypes(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type supertype = getSupertype(type, cls, Map.class);
        return supertype instanceof ParameterizedType ? ((ParameterizedType) supertype).getActualTypeArguments() : new Type[]{Object.class, Object.class};
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            C$Gson$Preconditions.checkArgument(rawType instanceof Class);
            return (Class) rawType;
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public static Type getSupertype(Type type, Class<?> cls, Class<?> cls2) {
        C$Gson$Preconditions.checkArgument(cls2.isAssignableFrom(cls));
        return resolve(type, cls, getGenericSupertype(type, cls, cls2));
    }

    public static int hashCodeOrZero(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static int indexOf(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i7 = 0; i7 < length; i7++) {
            if (obj.equals(objArr[i7])) {
                return i7;
            }
        }
        throw new NoSuchElementException();
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type, Type type2, Type... typeArr) {
        return new ParameterizedTypeImpl(type, type2, typeArr);
    }

    public static Type resolve(Type type, Class<?> cls, Type type2) {
        return resolve(type, cls, type2, new HashSet());
    }

    public static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class<?> clsDeclaringClassOf = declaringClassOf(typeVariable);
        if (clsDeclaringClassOf == null) {
            return typeVariable;
        }
        Type genericSupertype = getGenericSupertype(type, cls, clsDeclaringClassOf);
        if (!(genericSupertype instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) genericSupertype).getActualTypeArguments()[indexOf(clsDeclaringClassOf.getTypeParameters(), typeVariable)];
    }

    public static WildcardType subtypeOf(Type type) {
        return new WildcardTypeImpl(type instanceof WildcardType ? ((WildcardType) type).getUpperBounds() : new Type[]{type}, EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type type) {
        return new WildcardTypeImpl(new Type[]{Object.class}, type instanceof WildcardType ? ((WildcardType) type).getLowerBounds() : new Type[]{type});
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    private static Type resolve(Type type, Class<?> cls, Type type2, Collection<TypeVariable> collection) {
        while (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            if (collection.contains(typeVariable)) {
                return type2;
            }
            collection.add(typeVariable);
            type2 = resolveTypeVariable(type, cls, typeVariable);
            if (type2 == typeVariable) {
                return type2;
            }
        }
        if (type2 instanceof Class) {
            Class cls2 = (Class) type2;
            if (cls2.isArray()) {
                Class<?> componentType = cls2.getComponentType();
                Type typeResolve = resolve(type, cls, componentType, collection);
                return componentType == typeResolve ? cls2 : arrayOf(typeResolve);
            }
        }
        if (type2 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type2;
            Type genericComponentType = genericArrayType.getGenericComponentType();
            Type typeResolve2 = resolve(type, cls, genericComponentType, collection);
            return genericComponentType == typeResolve2 ? genericArrayType : arrayOf(typeResolve2);
        }
        if (type2 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type2;
            Type ownerType = parameterizedType.getOwnerType();
            Type typeResolve3 = resolve(type, cls, ownerType, collection);
            boolean z6 = typeResolve3 != ownerType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            int length = actualTypeArguments.length;
            for (int i7 = 0; i7 < length; i7++) {
                Type typeResolve4 = resolve(type, cls, actualTypeArguments[i7], collection);
                if (typeResolve4 != actualTypeArguments[i7]) {
                    if (!z6) {
                        actualTypeArguments = (Type[]) actualTypeArguments.clone();
                        z6 = true;
                    }
                    actualTypeArguments[i7] = typeResolve4;
                }
            }
            return z6 ? newParameterizedTypeWithOwner(typeResolve3, parameterizedType.getRawType(), actualTypeArguments) : parameterizedType;
        }
        boolean z7 = type2 instanceof WildcardType;
        Type type3 = type2;
        if (z7) {
            WildcardType wildcardType = (WildcardType) type2;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 1) {
                Type typeResolve5 = resolve(type, cls, lowerBounds[0], collection);
                type3 = wildcardType;
                if (typeResolve5 != lowerBounds[0]) {
                    return supertypeOf(typeResolve5);
                }
            } else {
                type3 = wildcardType;
                if (upperBounds.length == 1) {
                    Type typeResolve6 = resolve(type, cls, upperBounds[0], collection);
                    type3 = wildcardType;
                    if (typeResolve6 != upperBounds[0]) {
                        return subtypeOf(typeResolve6);
                    }
                }
            }
        }
        return type3;
    }
}
