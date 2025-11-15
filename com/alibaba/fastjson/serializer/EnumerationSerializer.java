package com.alibaba.fastjson.serializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Enumeration;

/* loaded from: classes.dex */
public class EnumerationSerializer implements ObjectSerializer {
    public static EnumerationSerializer instance = new EnumerationSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        Type type2 = null;
        int i8 = 0;
        if (serializeWriter.isEnabled(SerializerFeature.WriteClassName) && (type instanceof ParameterizedType)) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        Enumeration enumeration = (Enumeration) obj;
        SerialContext serialContext = jSONSerializer.context;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        try {
            serializeWriter.append('[');
            while (enumeration.hasMoreElements()) {
                Object objNextElement = enumeration.nextElement();
                int i9 = i8 + 1;
                if (i8 != 0) {
                    serializeWriter.append(',');
                }
                if (objNextElement == null) {
                    serializeWriter.writeNull();
                } else {
                    jSONSerializer.getObjectWriter(objNextElement.getClass()).write(jSONSerializer, objNextElement, Integer.valueOf(i9 - 1), type2, 0);
                }
                i8 = i9;
            }
            serializeWriter.append(']');
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
