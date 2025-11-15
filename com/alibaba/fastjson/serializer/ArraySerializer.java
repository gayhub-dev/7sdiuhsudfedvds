package com.alibaba.fastjson.serializer;

import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class ArraySerializer implements ObjectSerializer {
    private final ObjectSerializer compObjectSerializer;
    private final Class<?> componentType;

    public ArraySerializer(Class<?> cls, ObjectSerializer objectSerializer) {
        this.componentType = cls;
        this.compObjectSerializer = objectSerializer;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        Object[] objArr = (Object[]) obj;
        int length = objArr.length;
        SerialContext serialContext = jSONSerializer.context;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        try {
            serializeWriter.append('[');
            for (int i8 = 0; i8 < length; i8++) {
                if (i8 != 0) {
                    serializeWriter.append(',');
                }
                Object obj3 = objArr[i8];
                if (obj3 == null) {
                    if (serializeWriter.isEnabled(SerializerFeature.WriteNullStringAsEmpty) && (obj instanceof String[])) {
                        serializeWriter.writeString("");
                    } else {
                        serializeWriter.append((CharSequence) "null");
                    }
                } else if (obj3.getClass() == this.componentType) {
                    this.compObjectSerializer.write(jSONSerializer, obj3, Integer.valueOf(i8), null, 0);
                } else {
                    jSONSerializer.getObjectWriter(obj3.getClass()).write(jSONSerializer, obj3, Integer.valueOf(i8), null, 0);
                }
            }
            serializeWriter.append(']');
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
