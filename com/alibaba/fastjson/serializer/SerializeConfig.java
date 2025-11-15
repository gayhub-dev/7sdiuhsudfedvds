package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class SerializeConfig {
    private boolean asm;
    private ASMSerializerFactory asmFactory;
    private final boolean fieldBased;
    public PropertyNamingStrategy propertyNamingStrategy;
    private final IdentityHashMap<Type, ObjectSerializer> serializers;
    public String typeKey;
    public static final SerializeConfig globalInstance = new SerializeConfig();
    private static boolean awtError = false;
    private static boolean jdk8Error = false;
    private static boolean oracleJdbcError = false;
    private static boolean springfoxError = false;
    private static boolean guavaError = false;
    private static boolean jsonnullError = false;
    private static boolean jodaError = false;

    public SerializeConfig() {
        this(8192);
    }

    private final JavaBeanSerializer createASMSerializer(SerializeBeanInfo serializeBeanInfo) {
        JavaBeanSerializer javaBeanSerializerCreateJavaBeanSerializer = this.asmFactory.createJavaBeanSerializer(serializeBeanInfo);
        int i7 = 0;
        while (true) {
            FieldSerializer[] fieldSerializerArr = javaBeanSerializerCreateJavaBeanSerializer.sortedGetters;
            if (i7 >= fieldSerializerArr.length) {
                return javaBeanSerializerCreateJavaBeanSerializer;
            }
            Class<?> cls = fieldSerializerArr[i7].fieldInfo.fieldClass;
            if (cls.isEnum() && !(getObjectWriter(cls) instanceof EnumSerializer)) {
                javaBeanSerializerCreateJavaBeanSerializer.writeDirect = false;
            }
            i7++;
        }
    }

    public static SerializeConfig getGlobalInstance() {
        return globalInstance;
    }

    private void initSerializers() {
        put(Boolean.class, (ObjectSerializer) BooleanCodec.instance);
        put(Character.class, (ObjectSerializer) CharacterCodec.instance);
        put(Byte.class, (ObjectSerializer) IntegerCodec.instance);
        put(Short.class, (ObjectSerializer) IntegerCodec.instance);
        put(Integer.class, (ObjectSerializer) IntegerCodec.instance);
        put(Long.class, (ObjectSerializer) LongCodec.instance);
        put(Float.class, (ObjectSerializer) FloatCodec.instance);
        put(Double.class, (ObjectSerializer) DoubleSerializer.instance);
        put(BigDecimal.class, (ObjectSerializer) BigDecimalCodec.instance);
        put(BigInteger.class, (ObjectSerializer) BigIntegerCodec.instance);
        put(String.class, (ObjectSerializer) StringCodec.instance);
        put(byte[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(short[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(int[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(long[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(float[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(double[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(boolean[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(char[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(Object[].class, (ObjectSerializer) ObjectArrayCodec.instance);
        MiscCodec miscCodec = MiscCodec.instance;
        put(Class.class, (ObjectSerializer) miscCodec);
        put(SimpleDateFormat.class, (ObjectSerializer) miscCodec);
        put(Currency.class, (ObjectSerializer) new MiscCodec());
        put(TimeZone.class, (ObjectSerializer) miscCodec);
        put(InetAddress.class, (ObjectSerializer) miscCodec);
        put(Inet4Address.class, (ObjectSerializer) miscCodec);
        put(Inet6Address.class, (ObjectSerializer) miscCodec);
        put(InetSocketAddress.class, (ObjectSerializer) miscCodec);
        put(File.class, (ObjectSerializer) miscCodec);
        AppendableSerializer appendableSerializer = AppendableSerializer.instance;
        put(Appendable.class, (ObjectSerializer) appendableSerializer);
        put(StringBuffer.class, (ObjectSerializer) appendableSerializer);
        put(StringBuilder.class, (ObjectSerializer) appendableSerializer);
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        put(Charset.class, (ObjectSerializer) toStringSerializer);
        put(Pattern.class, (ObjectSerializer) toStringSerializer);
        put(Locale.class, (ObjectSerializer) toStringSerializer);
        put(URI.class, (ObjectSerializer) toStringSerializer);
        put(URL.class, (ObjectSerializer) toStringSerializer);
        put(UUID.class, (ObjectSerializer) toStringSerializer);
        AtomicCodec atomicCodec = AtomicCodec.instance;
        put(AtomicBoolean.class, (ObjectSerializer) atomicCodec);
        put(AtomicInteger.class, (ObjectSerializer) atomicCodec);
        put(AtomicLong.class, (ObjectSerializer) atomicCodec);
        ReferenceCodec referenceCodec = ReferenceCodec.instance;
        put(AtomicReference.class, (ObjectSerializer) referenceCodec);
        put(AtomicIntegerArray.class, (ObjectSerializer) atomicCodec);
        put(AtomicLongArray.class, (ObjectSerializer) atomicCodec);
        put(WeakReference.class, (ObjectSerializer) referenceCodec);
        put(SoftReference.class, (ObjectSerializer) referenceCodec);
        put(LinkedList.class, (ObjectSerializer) CollectionCodec.instance);
    }

    public void addFilter(Class<?> cls, SerializeFilter serializeFilter) {
        Object objectWriter = getObjectWriter(cls);
        if (objectWriter instanceof SerializeFilterable) {
            SerializeFilterable serializeFilterable = (SerializeFilterable) objectWriter;
            if (this == globalInstance || serializeFilterable != MapSerializer.instance) {
                serializeFilterable.addFilter(serializeFilter);
                return;
            }
            MapSerializer mapSerializer = new MapSerializer();
            put((Type) cls, (ObjectSerializer) mapSerializer);
            mapSerializer.addFilter(serializeFilter);
        }
    }

    public void clearSerializers() {
        this.serializers.clear();
        initSerializers();
    }

    public void config(Class<?> cls, SerializerFeature serializerFeature, boolean z6) throws ClassNotFoundException {
        ObjectSerializer objectWriter = getObjectWriter(cls, false);
        if (objectWriter == null) {
            SerializeBeanInfo serializeBeanInfoBuildBeanInfo = TypeUtils.buildBeanInfo(cls, null, this.propertyNamingStrategy);
            if (z6) {
                serializeBeanInfoBuildBeanInfo.features = serializerFeature.mask | serializeBeanInfoBuildBeanInfo.features;
            } else {
                serializeBeanInfoBuildBeanInfo.features = (~serializerFeature.mask) & serializeBeanInfoBuildBeanInfo.features;
            }
            put((Type) cls, createJavaBeanSerializer(serializeBeanInfoBuildBeanInfo));
            return;
        }
        if (objectWriter instanceof JavaBeanSerializer) {
            SerializeBeanInfo serializeBeanInfo = ((JavaBeanSerializer) objectWriter).beanInfo;
            int i7 = serializeBeanInfo.features;
            if (z6) {
                serializeBeanInfo.features = serializerFeature.mask | i7;
            } else {
                serializeBeanInfo.features = (~serializerFeature.mask) & i7;
            }
            if (i7 == serializeBeanInfo.features || objectWriter.getClass() == JavaBeanSerializer.class) {
                return;
            }
            put((Type) cls, createJavaBeanSerializer(serializeBeanInfo));
        }
    }

    public void configEnumAsJavaBean(Class<? extends Enum>... clsArr) {
        for (Class<? extends Enum> cls : clsArr) {
            put((Type) cls, createJavaBeanSerializer(cls));
        }
    }

    public final ObjectSerializer createJavaBeanSerializer(Class<?> cls) {
        SerializeBeanInfo serializeBeanInfoBuildBeanInfo = TypeUtils.buildBeanInfo(cls, null, this.propertyNamingStrategy, this.fieldBased);
        return (serializeBeanInfoBuildBeanInfo.fields.length == 0 && Iterable.class.isAssignableFrom(cls)) ? MiscCodec.instance : createJavaBeanSerializer(serializeBeanInfoBuildBeanInfo);
    }

    public final ObjectSerializer get(Type type) {
        return this.serializers.get(type);
    }

    public ObjectSerializer getObjectWriter(Class<?> cls) {
        return getObjectWriter(cls, true);
    }

    public String getTypeKey() {
        return this.typeKey;
    }

    public boolean isAsmEnable() {
        return this.asm;
    }

    public boolean put(Object obj, Object obj2) {
        return put((Type) obj, (ObjectSerializer) obj2);
    }

    public void setAsmEnable(boolean z6) {
        if (ASMUtils.IS_ANDROID) {
            return;
        }
        this.asm = z6;
    }

    public void setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this.propertyNamingStrategy = propertyNamingStrategy;
    }

    public void setTypeKey(String str) {
        this.typeKey = str;
    }

    public SerializeConfig(boolean z6) {
        this(8192, z6);
    }

    /* JADX WARN: Removed duplicated region for block: B:215:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:295:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.alibaba.fastjson.serializer.ObjectSerializer getObjectWriter(java.lang.Class<?> r26, boolean r27) throws java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instructions count: 1095
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.SerializeConfig.getObjectWriter(java.lang.Class, boolean):com.alibaba.fastjson.serializer.ObjectSerializer");
    }

    public boolean put(Type type, ObjectSerializer objectSerializer) {
        return this.serializers.put(type, objectSerializer);
    }

    public SerializeConfig(int i7) {
        this(i7, false);
    }

    public SerializeConfig(int i7, boolean z6) {
        this.asm = !ASMUtils.IS_ANDROID;
        this.typeKey = JSON.DEFAULT_TYPE_KEY;
        this.fieldBased = z6;
        this.serializers = new IdentityHashMap<>(i7);
        try {
            if (this.asm) {
                this.asmFactory = new ASMSerializerFactory();
            }
        } catch (Throwable unused) {
            this.asm = false;
        }
        initSerializers();
    }

    public ObjectSerializer createJavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        Method method;
        JSONType jSONType = serializeBeanInfo.jsonType;
        boolean z6 = false;
        boolean z7 = this.asm && !this.fieldBased;
        if (jSONType != null) {
            Class<?> clsSerializer = jSONType.serializer();
            if (clsSerializer != Void.class) {
                try {
                    Object objNewInstance = clsSerializer.newInstance();
                    if (objNewInstance instanceof ObjectSerializer) {
                        return (ObjectSerializer) objNewInstance;
                    }
                } catch (Throwable unused) {
                }
            }
            if (!jSONType.asm()) {
                z7 = false;
            }
            if (z7) {
                for (SerializerFeature serializerFeature : jSONType.serialzeFeatures()) {
                    if (SerializerFeature.WriteNonStringValueAsString == serializerFeature || SerializerFeature.WriteEnumUsingToString == serializerFeature || SerializerFeature.NotWriteDefaultValue == serializerFeature || SerializerFeature.BrowserCompatible == serializerFeature) {
                        z7 = false;
                        break;
                    }
                }
            }
            if (z7 && jSONType.serialzeFilters().length != 0) {
                z7 = false;
            }
        }
        Class<?> cls = serializeBeanInfo.beanType;
        if (!Modifier.isPublic(cls.getModifiers())) {
            return new JavaBeanSerializer(serializeBeanInfo);
        }
        if ((z7 && this.asmFactory.classLoader.isExternalClass(cls)) || cls == Serializable.class || cls == Object.class) {
            z7 = false;
        }
        if (z7 && !ASMUtils.checkName(cls.getSimpleName())) {
            z7 = false;
        }
        if (z7 && serializeBeanInfo.beanType.isInterface()) {
            z7 = false;
        }
        if (z7) {
            for (FieldInfo fieldInfo : serializeBeanInfo.fields) {
                Field field = fieldInfo.field;
                if ((field != null && !field.getType().equals(fieldInfo.fieldClass)) || ((method = fieldInfo.method) != null && !method.getReturnType().equals(fieldInfo.fieldClass))) {
                    break;
                }
                JSONField annotation = fieldInfo.getAnnotation();
                if (annotation != null) {
                    String str = annotation.format();
                    if ((str.length() != 0 && (fieldInfo.fieldClass != String.class || !"trim".equals(str))) || !ASMUtils.checkName(annotation.name()) || annotation.jsonDirect() || annotation.serializeUsing() != Void.class || annotation.unwrapped()) {
                        break;
                    }
                    for (SerializerFeature serializerFeature2 : annotation.serialzeFeatures()) {
                        if (SerializerFeature.WriteNonStringValueAsString == serializerFeature2 || SerializerFeature.WriteEnumUsingToString == serializerFeature2 || SerializerFeature.NotWriteDefaultValue == serializerFeature2 || SerializerFeature.BrowserCompatible == serializerFeature2 || SerializerFeature.WriteClassName == serializerFeature2) {
                            z7 = false;
                            break;
                        }
                    }
                    if (TypeUtils.isAnnotationPresentOneToMany(method) || TypeUtils.isAnnotationPresentManyToMany(method)) {
                        break;
                    }
                }
            }
            z6 = z7;
        } else {
            z6 = z7;
        }
        if (z6) {
            try {
                JavaBeanSerializer javaBeanSerializerCreateASMSerializer = createASMSerializer(serializeBeanInfo);
                if (javaBeanSerializerCreateASMSerializer != null) {
                    return javaBeanSerializerCreateASMSerializer;
                }
            } catch (ClassCastException | ClassFormatError | ClassNotFoundException unused2) {
            } catch (OutOfMemoryError e7) {
                if (e7.getMessage().indexOf("Metaspace") != -1) {
                    throw e7;
                }
            } catch (Throwable th) {
                throw new JSONException("create asm serializer error, verson 1.2.51, class " + cls, th);
            }
        }
        return new JavaBeanSerializer(serializeBeanInfo);
    }
}
