package com.alibaba.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.io.NotActiveException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class JSONObject extends JSON implements Map<String, Object>, Cloneable, Serializable, InvocationHandler {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long serialVersionUID = 1;
    private final Map<String, Object> map;

    public static class SecureObjectInputStream extends ObjectInputStream {
        public static Field[] fields;
        public static volatile boolean fields_error;

        public SecureObjectInputStream(ObjectInputStream objectInputStream) throws IllegalAccessException, IllegalArgumentException {
            super(objectInputStream);
            int i7 = 0;
            while (true) {
                try {
                    Field[] fieldArr = fields;
                    if (i7 >= fieldArr.length) {
                        return;
                    }
                    Field field = fieldArr[i7];
                    field.set(this, field.get(objectInputStream));
                    i7++;
                } catch (IllegalAccessException unused) {
                    fields_error = true;
                    return;
                }
            }
        }

        public static void ensureFields() {
            if (fields != null || fields_error) {
                return;
            }
            try {
                Field[] declaredFields = ObjectInputStream.class.getDeclaredFields();
                String[] strArr = {"bin", "passHandle", "handles", "curContext"};
                Field[] fieldArr = new Field[4];
                for (int i7 = 0; i7 < 4; i7++) {
                    Field field = TypeUtils.getField(ObjectInputStream.class, strArr[i7], declaredFields);
                    field.setAccessible(true);
                    fieldArr[i7] = field;
                }
                fields = fieldArr;
            } catch (Throwable unused) {
                fields_error = true;
            }
        }

        @Override // java.io.ObjectInputStream
        public void readStreamHeader() {
        }

        @Override // java.io.ObjectInputStream
        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) {
            ParserConfig.global.checkAutoType(objectStreamClass.getName(), null);
            return super.resolveClass(objectStreamClass);
        }

        @Override // java.io.ObjectInputStream
        public Class<?> resolveProxyClass(String[] strArr) {
            for (String str : strArr) {
                ParserConfig.global.checkAutoType(str, null);
            }
            return super.resolveProxyClass(strArr);
        }
    }

    public JSONObject() {
        this(16, false);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        SecureObjectInputStream.ensureFields();
        if (SecureObjectInputStream.fields != null && !SecureObjectInputStream.fields_error) {
            try {
                new SecureObjectInputStream(objectInputStream).defaultReadObject();
                return;
            } catch (NotActiveException unused) {
            }
        }
        objectInputStream.defaultReadObject();
        for (Map.Entry<String, Object> entry : this.map.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                ParserConfig.global.checkAutoType(key.getClass().getName(), null);
            }
            Object value = entry.getValue();
            if (value != null) {
                ParserConfig.global.checkAutoType(value.getClass().getName(), null);
            }
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    public Object clone() {
        return new JSONObject((Map<String, Object>) (this.map instanceof LinkedHashMap ? new LinkedHashMap(this.map) : new HashMap(this.map)));
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    public JSONObject fluentClear() {
        this.map.clear();
        return this;
    }

    public JSONObject fluentPut(String str, Object obj) {
        this.map.put(str, obj);
        return this;
    }

    public JSONObject fluentPutAll(Map<? extends String, ? extends Object> map) {
        this.map.putAll(map);
        return this;
    }

    public JSONObject fluentRemove(Object obj) {
        this.map.remove(obj);
        return this;
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        Object obj2 = this.map.get(obj);
        return (obj2 == null && (obj instanceof Number)) ? this.map.get(obj.toString()) : obj2;
    }

    public BigDecimal getBigDecimal(String str) {
        return TypeUtils.castToBigDecimal(get(str));
    }

    public BigInteger getBigInteger(String str) {
        return TypeUtils.castToBigInteger(get(str));
    }

    public Boolean getBoolean(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBoolean(obj);
    }

    public boolean getBooleanValue(String str) {
        Boolean boolCastToBoolean = TypeUtils.castToBoolean(get(str));
        if (boolCastToBoolean == null) {
            return false;
        }
        return boolCastToBoolean.booleanValue();
    }

    public Byte getByte(String str) {
        return TypeUtils.castToByte(get(str));
    }

    public byte getByteValue(String str) {
        Byte bCastToByte = TypeUtils.castToByte(get(str));
        if (bCastToByte == null) {
            return (byte) 0;
        }
        return bCastToByte.byteValue();
    }

    public byte[] getBytes(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBytes(obj);
    }

    public Date getDate(String str) {
        return TypeUtils.castToDate(get(str));
    }

    public Double getDouble(String str) {
        return TypeUtils.castToDouble(get(str));
    }

    public double getDoubleValue(String str) {
        Double dCastToDouble = TypeUtils.castToDouble(get(str));
        if (dCastToDouble == null) {
            return 0.0d;
        }
        return dCastToDouble.doubleValue();
    }

    public Float getFloat(String str) {
        return TypeUtils.castToFloat(get(str));
    }

    public float getFloatValue(String str) {
        Float fCastToFloat = TypeUtils.castToFloat(get(str));
        if (fCastToFloat == null) {
            return 0.0f;
        }
        return fCastToFloat.floatValue();
    }

    public Map<String, Object> getInnerMap() {
        return this.map;
    }

    public int getIntValue(String str) {
        Integer numCastToInt = TypeUtils.castToInt(get(str));
        if (numCastToInt == null) {
            return 0;
        }
        return numCastToInt.intValue();
    }

    public Integer getInteger(String str) {
        return TypeUtils.castToInt(get(str));
    }

    public JSONArray getJSONArray(String str) {
        Object obj = this.map.get(str);
        return obj instanceof JSONArray ? (JSONArray) obj : obj instanceof String ? (JSONArray) JSON.parse((String) obj) : (JSONArray) JSON.toJSON(obj);
    }

    public JSONObject getJSONObject(String str) {
        Object obj = this.map.get(str);
        return obj instanceof JSONObject ? (JSONObject) obj : obj instanceof String ? JSON.parseObject((String) obj) : (JSONObject) JSON.toJSON(obj);
    }

    public Long getLong(String str) {
        return TypeUtils.castToLong(get(str));
    }

    public long getLongValue(String str) {
        Long lCastToLong = TypeUtils.castToLong(get(str));
        if (lCastToLong == null) {
            return 0L;
        }
        return lCastToLong.longValue();
    }

    public <T> T getObject(String str, Class<T> cls) {
        return (T) TypeUtils.castToJavaBean(this.map.get(str), cls);
    }

    public Short getShort(String str) {
        return TypeUtils.castToShort(get(str));
    }

    public short getShortValue(String str) {
        Short shCastToShort = TypeUtils.castToShort(get(str));
        if (shCastToShort == null) {
            return (short) 0;
        }
        return shCastToShort.shortValue();
    }

    public java.sql.Date getSqlDate(String str) {
        return TypeUtils.castToSqlDate(get(str));
    }

    public String getString(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public Timestamp getTimestamp(String str) {
        return TypeUtils.castToTimestamp(get(str));
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String strName = null;
        if (parameterTypes.length == 1) {
            if (method.getName().equals("equals")) {
                return Boolean.valueOf(equals(objArr[0]));
            }
            if (method.getReturnType() != Void.TYPE) {
                throw new JSONException("illegal setter");
            }
            JSONField jSONField = (JSONField) method.getAnnotation(JSONField.class);
            String strName2 = (jSONField == null || jSONField.name().length() == 0) ? null : jSONField.name();
            if (strName2 == null) {
                String name = method.getName();
                if (!name.startsWith("set")) {
                    throw new JSONException("illegal setter");
                }
                String strSubstring = name.substring(3);
                if (strSubstring.length() == 0) {
                    throw new JSONException("illegal setter");
                }
                strName2 = Character.toLowerCase(strSubstring.charAt(0)) + strSubstring.substring(1);
            }
            this.map.put(strName2, objArr[0]);
            return null;
        }
        if (parameterTypes.length != 0) {
            throw new UnsupportedOperationException(method.toGenericString());
        }
        if (method.getReturnType() == Void.TYPE) {
            throw new JSONException("illegal getter");
        }
        JSONField jSONField2 = (JSONField) method.getAnnotation(JSONField.class);
        if (jSONField2 != null && jSONField2.name().length() != 0) {
            strName = jSONField2.name();
        }
        if (strName == null) {
            String name2 = method.getName();
            if (name2.startsWith("get")) {
                String strSubstring2 = name2.substring(3);
                if (strSubstring2.length() == 0) {
                    throw new JSONException("illegal getter");
                }
                strName = Character.toLowerCase(strSubstring2.charAt(0)) + strSubstring2.substring(1);
            } else {
                if (!name2.startsWith("is")) {
                    if (name2.startsWith("hashCode")) {
                        return Integer.valueOf(hashCode());
                    }
                    if (name2.startsWith("toString")) {
                        return toString();
                    }
                    throw new JSONException("illegal getter");
                }
                String strSubstring3 = name2.substring(2);
                if (strSubstring3.length() == 0) {
                    throw new JSONException("illegal getter");
                }
                strName = Character.toLowerCase(strSubstring3.charAt(0)) + strSubstring3.substring(1);
            }
        }
        return TypeUtils.cast(this.map.get(strName), method.getGenericReturnType(), ParserConfig.getGlobalInstance());
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        this.map.putAll(map);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.map.values();
    }

    public JSONObject(Map<String, Object> map) {
        if (map == null) {
            throw new IllegalArgumentException("map is null.");
        }
        this.map = map;
    }

    @Override // java.util.Map
    public Object put(String str, Object obj) {
        return this.map.put(str, obj);
    }

    public <T> T getObject(String str, Type type) {
        return (T) TypeUtils.cast(this.map.get(str), type, ParserConfig.getGlobalInstance());
    }

    public JSONObject(boolean z6) {
        this(16, z6);
    }

    public <T> T getObject(String str, TypeReference typeReference) {
        T t6 = (T) this.map.get(str);
        return typeReference == null ? t6 : (T) TypeUtils.cast(t6, typeReference.getType(), ParserConfig.getGlobalInstance());
    }

    public JSONObject(int i7) {
        this(i7, false);
    }

    public JSONObject(int i7, boolean z6) {
        if (z6) {
            this.map = new LinkedHashMap(i7);
        } else {
            this.map = new HashMap(i7);
        }
    }
}
