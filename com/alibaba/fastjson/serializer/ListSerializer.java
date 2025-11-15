package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.HttpUrl;

/* loaded from: classes.dex */
public final class ListSerializer implements ObjectSerializer {
    public static final ListSerializer instance = new ListSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws IOException {
        int i8;
        Object obj3;
        boolean z6;
        SerializeWriter serializeWriter = jSONSerializer.out;
        SerializerFeature serializerFeature = SerializerFeature.WriteClassName;
        boolean z7 = serializeWriter.isEnabled(serializerFeature) || SerializerFeature.isEnabled(i7, serializerFeature);
        SerializeWriter serializeWriter2 = jSONSerializer.out;
        Type collectionItemType = z7 ? TypeUtils.getCollectionItemType(type) : null;
        if (obj == null) {
            serializeWriter2.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        List list = (List) obj;
        if (list.size() == 0) {
            serializeWriter2.append((CharSequence) HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        try {
            char c7 = ',';
            if (serializeWriter2.isEnabled(SerializerFeature.PrettyFormat)) {
                serializeWriter2.append('[');
                jSONSerializer.incrementIndent();
                int i9 = 0;
                for (Object obj4 : list) {
                    if (i9 != 0) {
                        serializeWriter2.append(c7);
                    }
                    jSONSerializer.println();
                    if (obj4 == null) {
                        jSONSerializer.out.writeNull();
                    } else if (jSONSerializer.containsReference(obj4)) {
                        jSONSerializer.writeReference(obj4);
                    } else {
                        ObjectSerializer objectWriter = jSONSerializer.getObjectWriter(obj4.getClass());
                        jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0, 0);
                        objectWriter.write(jSONSerializer, obj4, Integer.valueOf(i9), collectionItemType, i7);
                    }
                    i9++;
                    c7 = ',';
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter2.append(']');
                return;
            }
            char c8 = ']';
            serializeWriter2.append('[');
            int size = list.size();
            int i10 = 0;
            while (i10 < size) {
                Object obj5 = list.get(i10);
                if (i10 != 0) {
                    serializeWriter2.append(',');
                }
                if (obj5 == null) {
                    serializeWriter2.append((CharSequence) "null");
                } else {
                    Class<?> cls = obj5.getClass();
                    if (cls == Integer.class) {
                        serializeWriter2.writeInt(((Integer) obj5).intValue());
                    } else if (cls == Long.class) {
                        long jLongValue = ((Long) obj5).longValue();
                        if (z7) {
                            serializeWriter2.writeLong(jLongValue);
                            serializeWriter2.write(76);
                        } else {
                            serializeWriter2.writeLong(jLongValue);
                        }
                    } else {
                        if ((SerializerFeature.DisableCircularReferenceDetect.mask & i7) != 0) {
                            i8 = i10;
                            jSONSerializer.getObjectWriter(obj5.getClass()).write(jSONSerializer, obj5, Integer.valueOf(i10), collectionItemType, i7);
                            z6 = z7;
                        } else {
                            i8 = i10;
                            if (serializeWriter2.disableCircularReferenceDetect) {
                                obj3 = obj5;
                                z6 = z7;
                            } else {
                                obj3 = obj5;
                                z6 = z7;
                                jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0, 0);
                            }
                            if (jSONSerializer.containsReference(obj3)) {
                                jSONSerializer.writeReference(obj3);
                            } else {
                                ObjectSerializer objectWriter2 = jSONSerializer.getObjectWriter(obj3.getClass());
                                if ((SerializerFeature.WriteClassName.mask & i7) == 0 || !(objectWriter2 instanceof JavaBeanSerializer)) {
                                    objectWriter2.write(jSONSerializer, obj3, Integer.valueOf(i8), collectionItemType, i7);
                                } else {
                                    ((JavaBeanSerializer) objectWriter2).writeNoneASM(jSONSerializer, obj3, Integer.valueOf(i8), collectionItemType, i7);
                                }
                            }
                        }
                        i10 = i8 + 1;
                        z7 = z6;
                        c8 = ']';
                    }
                }
                i8 = i10;
                z6 = z7;
                i10 = i8 + 1;
                z7 = z6;
                c8 = ']';
            }
            serializeWriter2.append(c8);
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
