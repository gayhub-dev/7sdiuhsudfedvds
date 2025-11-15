package org.fourthline.cling.model.types.csv;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.types.Datatype;
import org.fourthline.cling.model.types.InvalidValueException;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class CSV<T> extends ArrayList<T> {
    public final Datatype.Builtin datatype = getBuiltinDatatype();

    public CSV() {
    }

    public Datatype.Builtin getBuiltinDatatype() {
        Type genericSuperclass = getClass();
        HashMap map = new HashMap();
        while (true) {
            if (C2074b.m2485h(genericSuperclass).equals(ArrayList.class)) {
                break;
            }
            if (genericSuperclass instanceof Class) {
                genericSuperclass = ((Class) genericSuperclass).getGenericSuperclass();
            } else {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                Class cls = (Class) parameterizedType.getRawType();
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                TypeVariable<Class<T>>[] typeParameters = cls.getTypeParameters();
                for (int i7 = 0; i7 < actualTypeArguments.length; i7++) {
                    map.put(typeParameters[i7], actualTypeArguments[i7]);
                }
                if (!cls.equals(ArrayList.class)) {
                    genericSuperclass = cls.getGenericSuperclass();
                }
            }
        }
        Type[] typeParameters2 = genericSuperclass instanceof Class ? ((Class) genericSuperclass).getTypeParameters() : ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        ArrayList arrayList = new ArrayList();
        int length = typeParameters2.length;
        for (int i8 = 0; i8 < length; i8++) {
            Type type = typeParameters2[i8];
            while (map.containsKey(type)) {
                type = (Type) map.get(type);
            }
            arrayList.add(C2074b.m2485h(type));
        }
        Class cls2 = (Class) arrayList.get(0);
        Datatype.Default byJavaType = Datatype.Default.getByJavaType(cls2);
        if (byJavaType != null) {
            return byJavaType.getBuiltinType();
        }
        throw new InvalidValueException("No built-in UPnP datatype for Java type of CSV: " + cls2);
    }

    public List parseString(String str) {
        String[] strArrFromCommaSeparatedList = ModelUtil.fromCommaSeparatedList(str);
        ArrayList arrayList = new ArrayList();
        for (String str2 : strArrFromCommaSeparatedList) {
            arrayList.add(this.datatype.getDatatype().valueOf(str2));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection
    public String toString() {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            arrayList.add(this.datatype.getDatatype().getString(it.next()));
        }
        return ModelUtil.toCommaSeparatedList(arrayList.toArray(new Object[arrayList.size()]));
    }

    public CSV(String str) {
        addAll(parseString(str));
    }
}
