package com.alibaba.fastjson.serializer;

import android.support.constraint.C0072a;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/* loaded from: classes.dex */
public class FieldSerializer implements Comparable<FieldSerializer> {
    public boolean browserCompatible;
    public boolean disableCircularReferenceDetect;
    private final String double_quoted_fieldPrefix;
    public int features;
    public BeanContext fieldContext;
    public final FieldInfo fieldInfo;
    private String format;
    public boolean persistenceXToMany;
    private RuntimeSerializerInfo runtimeInfo;
    public boolean serializeUsing = false;
    private String single_quoted_fieldPrefix;
    private String un_quoted_fieldPrefix;
    public boolean writeEnumUsingName;
    public boolean writeEnumUsingToString;
    public final boolean writeNull;

    public static class RuntimeSerializerInfo {
        public final ObjectSerializer fieldSerializer;
        public final Class<?> runtimeFieldClass;

        public RuntimeSerializerInfo(ObjectSerializer objectSerializer, Class<?> cls) {
            this.fieldSerializer = objectSerializer;
            this.runtimeFieldClass = cls;
        }
    }

    public FieldSerializer(Class<?> cls, FieldInfo fieldInfo) {
        boolean z6;
        JSONType jSONType;
        Class<?> cls2;
        this.writeEnumUsingToString = false;
        this.writeEnumUsingName = false;
        this.disableCircularReferenceDetect = false;
        this.persistenceXToMany = false;
        this.fieldInfo = fieldInfo;
        this.fieldContext = new BeanContext(cls, fieldInfo);
        if (cls != null && ((fieldInfo.isEnum || (cls2 = fieldInfo.fieldClass) == Long.TYPE || cls2 == Long.class) && (jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class)) != null)) {
            for (SerializerFeature serializerFeature : jSONType.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                    this.writeEnumUsingToString = true;
                } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                    this.writeEnumUsingName = true;
                } else if (serializerFeature == SerializerFeature.DisableCircularReferenceDetect) {
                    this.disableCircularReferenceDetect = true;
                } else if (serializerFeature == SerializerFeature.BrowserCompatible) {
                    this.browserCompatible = true;
                }
            }
        }
        fieldInfo.setAccessible();
        StringBuilder sb = new StringBuilder();
        sb.append('\"');
        this.double_quoted_fieldPrefix = C0072a.m92a(sb, fieldInfo.name, "\":");
        JSONField annotation = fieldInfo.getAnnotation();
        if (annotation != null) {
            SerializerFeature[] serializerFeatureArrSerialzeFeatures = annotation.serialzeFeatures();
            int length = serializerFeatureArrSerialzeFeatures.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    z6 = false;
                    break;
                } else {
                    if ((serializerFeatureArrSerialzeFeatures[i7].getMask() & SerializerFeature.WRITE_MAP_NULL_FEATURES) != 0) {
                        z6 = true;
                        break;
                    }
                    i7++;
                }
            }
            String str = annotation.format();
            this.format = str;
            if (str.trim().length() == 0) {
                this.format = null;
            }
            for (SerializerFeature serializerFeature2 : annotation.serialzeFeatures()) {
                if (serializerFeature2 == SerializerFeature.WriteEnumUsingToString) {
                    this.writeEnumUsingToString = true;
                } else if (serializerFeature2 == SerializerFeature.WriteEnumUsingName) {
                    this.writeEnumUsingName = true;
                } else if (serializerFeature2 == SerializerFeature.DisableCircularReferenceDetect) {
                    this.disableCircularReferenceDetect = true;
                } else if (serializerFeature2 == SerializerFeature.BrowserCompatible) {
                    this.browserCompatible = true;
                }
            }
            this.features = SerializerFeature.m342of(annotation.serialzeFeatures());
        } else {
            z6 = false;
        }
        this.writeNull = z6;
        this.persistenceXToMany = TypeUtils.isAnnotationPresentOneToMany(fieldInfo.method) || TypeUtils.isAnnotationPresentManyToMany(fieldInfo.method);
    }

    public Object getPropertyValue(Object obj) {
        Object obj2 = this.fieldInfo.get(obj);
        if (this.format == null || obj2 == null || this.fieldInfo.fieldClass != Date.class) {
            return obj2;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.format, JSON.defaultLocale);
        simpleDateFormat.setTimeZone(JSON.defaultTimeZone);
        return simpleDateFormat.format(obj2);
    }

    public Object getPropertyValueDirect(Object obj) {
        Object obj2 = this.fieldInfo.get(obj);
        if (!this.persistenceXToMany || TypeUtils.isHibernateInitialized(obj2)) {
            return obj2;
        }
        return null;
    }

    public void writePrefix(JSONSerializer jSONSerializer) {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (!serializeWriter.quoteFieldNames) {
            if (this.un_quoted_fieldPrefix == null) {
                this.un_quoted_fieldPrefix = C0072a.m92a(new StringBuilder(), this.fieldInfo.name, ":");
            }
            serializeWriter.write(this.un_quoted_fieldPrefix);
        } else {
            if (!serializeWriter.useSingleQuotes) {
                serializeWriter.write(this.double_quoted_fieldPrefix);
                return;
            }
            if (this.single_quoted_fieldPrefix == null) {
                StringBuilder sb = new StringBuilder();
                sb.append('\'');
                this.single_quoted_fieldPrefix = C0072a.m92a(sb, this.fieldInfo.name, "':");
            }
            serializeWriter.write(this.single_quoted_fieldPrefix);
        }
    }

    public void writeValue(JSONSerializer jSONSerializer, Object obj) throws Throwable {
        Class<?> cls;
        Class<?> cls2;
        ObjectSerializer objectWriter;
        if (this.runtimeInfo == null) {
            if (obj == null) {
                cls2 = this.fieldInfo.fieldClass;
                if (cls2 == Byte.TYPE) {
                    cls2 = Byte.class;
                } else if (cls2 == Short.TYPE) {
                    cls2 = Short.class;
                } else if (cls2 == Integer.TYPE) {
                    cls2 = Integer.class;
                } else if (cls2 == Long.TYPE) {
                    cls2 = Long.class;
                } else if (cls2 == Float.TYPE) {
                    cls2 = Float.class;
                } else if (cls2 == Double.TYPE) {
                    cls2 = Double.class;
                } else if (cls2 == Boolean.TYPE) {
                    cls2 = Boolean.class;
                }
            } else {
                cls2 = obj.getClass();
            }
            ObjectSerializer doubleSerializer = null;
            JSONField annotation = this.fieldInfo.getAnnotation();
            if (annotation == null || annotation.serializeUsing() == Void.class) {
                if (this.format != null) {
                    if (cls2 == Double.TYPE || cls2 == Double.class) {
                        doubleSerializer = new DoubleSerializer(this.format);
                    } else if (cls2 == Float.TYPE || cls2 == Float.class) {
                        doubleSerializer = new FloatCodec(this.format);
                    }
                }
                objectWriter = doubleSerializer == null ? jSONSerializer.getObjectWriter(cls2) : doubleSerializer;
            } else {
                objectWriter = (ObjectSerializer) annotation.serializeUsing().newInstance();
                this.serializeUsing = true;
            }
            this.runtimeInfo = new RuntimeSerializerInfo(objectWriter, cls2);
        }
        RuntimeSerializerInfo runtimeSerializerInfo = this.runtimeInfo;
        int mask = this.disableCircularReferenceDetect ? this.fieldInfo.serialzeFeatures | SerializerFeature.DisableCircularReferenceDetect.getMask() : this.fieldInfo.serialzeFeatures;
        if (obj == null) {
            SerializeWriter serializeWriter = jSONSerializer.out;
            if (this.fieldInfo.fieldClass == Object.class && serializeWriter.isEnabled(SerializerFeature.WRITE_MAP_NULL_FEATURES)) {
                serializeWriter.writeNull();
                return;
            }
            Class<?> cls3 = runtimeSerializerInfo.runtimeFieldClass;
            if (Number.class.isAssignableFrom(cls3)) {
                serializeWriter.writeNull(this.features, SerializerFeature.WriteNullNumberAsZero.mask);
                return;
            }
            if (String.class == cls3) {
                serializeWriter.writeNull(this.features, SerializerFeature.WriteNullStringAsEmpty.mask);
                return;
            }
            if (Boolean.class == cls3) {
                serializeWriter.writeNull(this.features, SerializerFeature.WriteNullBooleanAsFalse.mask);
                return;
            }
            if (Collection.class.isAssignableFrom(cls3)) {
                serializeWriter.writeNull(this.features, SerializerFeature.WriteNullListAsEmpty.mask);
                return;
            }
            ObjectSerializer objectSerializer = runtimeSerializerInfo.fieldSerializer;
            if (serializeWriter.isEnabled(SerializerFeature.WRITE_MAP_NULL_FEATURES) && (objectSerializer instanceof JavaBeanSerializer)) {
                serializeWriter.writeNull();
                return;
            } else {
                FieldInfo fieldInfo = this.fieldInfo;
                objectSerializer.write(jSONSerializer, null, fieldInfo.name, fieldInfo.fieldType, mask);
                return;
            }
        }
        if (this.fieldInfo.isEnum) {
            if (this.writeEnumUsingName) {
                jSONSerializer.out.writeString(((Enum) obj).name());
                return;
            } else if (this.writeEnumUsingToString) {
                jSONSerializer.out.writeString(((Enum) obj).toString());
                return;
            }
        }
        Class<?> cls4 = obj.getClass();
        ObjectSerializer objectWriter2 = (cls4 == runtimeSerializerInfo.runtimeFieldClass || this.serializeUsing) ? runtimeSerializerInfo.fieldSerializer : jSONSerializer.getObjectWriter(cls4);
        String str = this.format;
        if (str != null && !(objectWriter2 instanceof DoubleSerializer) && !(objectWriter2 instanceof FloatCodec)) {
            if (objectWriter2 instanceof ContextObjectSerializer) {
                ((ContextObjectSerializer) objectWriter2).write(jSONSerializer, obj, this.fieldContext);
                return;
            } else {
                jSONSerializer.writeWithFormat(obj, str);
                return;
            }
        }
        FieldInfo fieldInfo2 = this.fieldInfo;
        if (fieldInfo2.unwrapped) {
            if (objectWriter2 instanceof JavaBeanSerializer) {
                ((JavaBeanSerializer) objectWriter2).write(jSONSerializer, obj, fieldInfo2.name, fieldInfo2.fieldType, mask, true);
                return;
            } else if (objectWriter2 instanceof MapSerializer) {
                ((MapSerializer) objectWriter2).write(jSONSerializer, obj, fieldInfo2.name, fieldInfo2.fieldType, mask, true);
                return;
            }
        }
        if ((this.features & SerializerFeature.WriteClassName.mask) != 0 && cls4 != fieldInfo2.fieldClass && JavaBeanSerializer.class.isInstance(objectWriter2)) {
            FieldInfo fieldInfo3 = this.fieldInfo;
            ((JavaBeanSerializer) objectWriter2).write(jSONSerializer, obj, fieldInfo3.name, fieldInfo3.fieldType, mask, false);
            return;
        }
        if (this.browserCompatible && ((cls = this.fieldInfo.fieldClass) == Long.TYPE || cls == Long.class)) {
            long jLongValue = ((Long) obj).longValue();
            if (jLongValue > 9007199254740991L || jLongValue < -9007199254740991L) {
                jSONSerializer.getWriter().writeString(Long.toString(jLongValue));
                return;
            }
        }
        FieldInfo fieldInfo4 = this.fieldInfo;
        objectWriter2.write(jSONSerializer, obj, fieldInfo4.name, fieldInfo4.fieldType, mask);
    }

    @Override // java.lang.Comparable
    public int compareTo(FieldSerializer fieldSerializer) {
        return this.fieldInfo.compareTo(fieldSerializer.fieldInfo);
    }
}
