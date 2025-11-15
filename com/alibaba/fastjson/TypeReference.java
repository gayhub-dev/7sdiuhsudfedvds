package com.alibaba.fastjson;

import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public class TypeReference<T> {
    public final Type type;
    public static ConcurrentMap<Type, Type> classTypeCache = new ConcurrentHashMap(16, 0.75f, 1);
    public static final Type LIST_STRING = new TypeReference<List<String>>() { // from class: com.alibaba.fastjson.TypeReference.1
    }.getType();

    public TypeReference() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Type type2 = classTypeCache.get(type);
        if (type2 == null) {
            classTypeCache.putIfAbsent(type, type);
            type2 = classTypeCache.get(type);
        }
        this.type = type2;
    }

    private Type handlerParameterizedType(ParameterizedType parameterizedType, Type[] typeArr, int i7) {
        Class<?> cls = getClass();
        Type rawType = parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i8 = 0; i8 < actualTypeArguments.length; i8++) {
            if ((actualTypeArguments[i8] instanceof TypeVariable) && i7 < typeArr.length) {
                actualTypeArguments[i8] = typeArr[i7];
                i7++;
            }
            if (actualTypeArguments[i8] instanceof GenericArrayType) {
                actualTypeArguments[i8] = TypeUtils.checkPrimitiveArray((GenericArrayType) actualTypeArguments[i8]);
            }
            if (actualTypeArguments[i8] instanceof ParameterizedType) {
                return handlerParameterizedType((ParameterizedType) actualTypeArguments[i8], typeArr, i7);
            }
        }
        return new ParameterizedTypeImpl(actualTypeArguments, cls, rawType);
    }

    public Type getType() {
        return this.type;
    }

    public TypeReference(Type... typeArr) {
        Class<?> cls = getClass();
        ParameterizedType parameterizedType = (ParameterizedType) ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0];
        Type rawType = parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int i7 = 0;
        for (int i8 = 0; i8 < actualTypeArguments.length; i8++) {
            if ((actualTypeArguments[i8] instanceof TypeVariable) && i7 < typeArr.length) {
                actualTypeArguments[i8] = typeArr[i7];
                i7++;
            }
            if (actualTypeArguments[i8] instanceof GenericArrayType) {
                actualTypeArguments[i8] = TypeUtils.checkPrimitiveArray((GenericArrayType) actualTypeArguments[i8]);
            }
            if (actualTypeArguments[i8] instanceof ParameterizedType) {
                actualTypeArguments[i8] = handlerParameterizedType((ParameterizedType) actualTypeArguments[i8], typeArr, i7);
            }
        }
        ParameterizedTypeImpl parameterizedTypeImpl = new ParameterizedTypeImpl(actualTypeArguments, cls, rawType);
        Type type = classTypeCache.get(parameterizedTypeImpl);
        if (type == null) {
            classTypeCache.putIfAbsent(parameterizedTypeImpl, parameterizedTypeImpl);
            type = classTypeCache.get(parameterizedTypeImpl);
        }
        this.type = type;
    }
}
