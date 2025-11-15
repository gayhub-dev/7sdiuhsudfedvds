package com.alibaba.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.io.NotActiveException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public class JSONArray extends JSON implements List<Object>, Cloneable, RandomAccess, Serializable {
    private static final long serialVersionUID = 1;
    public transient Type componentType;
    private final List<Object> list;
    public transient Object relatedArray;

    public JSONArray() {
        this.list = new ArrayList();
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        JSONObject.SecureObjectInputStream.ensureFields();
        if (JSONObject.SecureObjectInputStream.fields != null && !JSONObject.SecureObjectInputStream.fields_error) {
            try {
                new JSONObject.SecureObjectInputStream(objectInputStream).defaultReadObject();
                return;
            } catch (NotActiveException unused) {
            }
        }
        objectInputStream.defaultReadObject();
        for (Object obj : this.list) {
            if (obj != null) {
                ParserConfig.global.checkAutoType(obj.getClass().getName(), null);
            }
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        return this.list.add(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends Object> collection) {
        return this.list.addAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.list.clear();
    }

    public Object clone() {
        return new JSONArray(new ArrayList(this.list));
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.list.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.list.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return this.list.equals(obj);
    }

    public JSONArray fluentAdd(Object obj) {
        this.list.add(obj);
        return this;
    }

    public JSONArray fluentAddAll(Collection<? extends Object> collection) {
        this.list.addAll(collection);
        return this;
    }

    public JSONArray fluentClear() {
        this.list.clear();
        return this;
    }

    public JSONArray fluentRemove(Object obj) {
        this.list.remove(obj);
        return this;
    }

    public JSONArray fluentRemoveAll(Collection<?> collection) {
        this.list.removeAll(collection);
        return this;
    }

    public JSONArray fluentRetainAll(Collection<?> collection) {
        this.list.retainAll(collection);
        return this;
    }

    public JSONArray fluentSet(int i7, Object obj) {
        set(i7, obj);
        return this;
    }

    @Override // java.util.List
    public Object get(int i7) {
        return this.list.get(i7);
    }

    public BigDecimal getBigDecimal(int i7) {
        return TypeUtils.castToBigDecimal(get(i7));
    }

    public BigInteger getBigInteger(int i7) {
        return TypeUtils.castToBigInteger(get(i7));
    }

    public Boolean getBoolean(int i7) {
        Object obj = get(i7);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBoolean(obj);
    }

    public boolean getBooleanValue(int i7) {
        Object obj = get(i7);
        if (obj == null) {
            return false;
        }
        return TypeUtils.castToBoolean(obj).booleanValue();
    }

    public Byte getByte(int i7) {
        return TypeUtils.castToByte(get(i7));
    }

    public byte getByteValue(int i7) {
        Byte bCastToByte = TypeUtils.castToByte(get(i7));
        if (bCastToByte == null) {
            return (byte) 0;
        }
        return bCastToByte.byteValue();
    }

    public Type getComponentType() {
        return this.componentType;
    }

    public Date getDate(int i7) {
        return TypeUtils.castToDate(get(i7));
    }

    public Double getDouble(int i7) {
        return TypeUtils.castToDouble(get(i7));
    }

    public double getDoubleValue(int i7) {
        Double dCastToDouble = TypeUtils.castToDouble(get(i7));
        if (dCastToDouble == null) {
            return 0.0d;
        }
        return dCastToDouble.doubleValue();
    }

    public Float getFloat(int i7) {
        return TypeUtils.castToFloat(get(i7));
    }

    public float getFloatValue(int i7) {
        Float fCastToFloat = TypeUtils.castToFloat(get(i7));
        if (fCastToFloat == null) {
            return 0.0f;
        }
        return fCastToFloat.floatValue();
    }

    public int getIntValue(int i7) {
        Integer numCastToInt = TypeUtils.castToInt(get(i7));
        if (numCastToInt == null) {
            return 0;
        }
        return numCastToInt.intValue();
    }

    public Integer getInteger(int i7) {
        return TypeUtils.castToInt(get(i7));
    }

    public JSONArray getJSONArray(int i7) {
        Object obj = this.list.get(i7);
        return obj instanceof JSONArray ? (JSONArray) obj : (JSONArray) JSON.toJSON(obj);
    }

    public JSONObject getJSONObject(int i7) {
        Object obj = this.list.get(i7);
        return obj instanceof JSONObject ? (JSONObject) obj : (JSONObject) JSON.toJSON(obj);
    }

    public Long getLong(int i7) {
        return TypeUtils.castToLong(get(i7));
    }

    public long getLongValue(int i7) {
        Long lCastToLong = TypeUtils.castToLong(get(i7));
        if (lCastToLong == null) {
            return 0L;
        }
        return lCastToLong.longValue();
    }

    public <T> T getObject(int i7, Class<T> cls) {
        return (T) TypeUtils.castToJavaBean(this.list.get(i7), cls);
    }

    public Object getRelatedArray() {
        return this.relatedArray;
    }

    public Short getShort(int i7) {
        return TypeUtils.castToShort(get(i7));
    }

    public short getShortValue(int i7) {
        Short shCastToShort = TypeUtils.castToShort(get(i7));
        if (shCastToShort == null) {
            return (short) 0;
        }
        return shCastToShort.shortValue();
    }

    public java.sql.Date getSqlDate(int i7) {
        return TypeUtils.castToSqlDate(get(i7));
    }

    public String getString(int i7) {
        return TypeUtils.castToString(get(i7));
    }

    public Timestamp getTimestamp(int i7) {
        return TypeUtils.castToTimestamp(get(i7));
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.list.hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.list.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.list.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.list.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator() {
        return this.list.listIterator();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        return this.list.remove(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return this.list.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return this.list.retainAll(collection);
    }

    @Override // java.util.List
    public Object set(int i7, Object obj) {
        if (i7 == -1) {
            this.list.add(obj);
            return null;
        }
        if (this.list.size() > i7) {
            return this.list.set(i7, obj);
        }
        for (int size = this.list.size(); size < i7; size++) {
            this.list.add(null);
        }
        this.list.add(obj);
        return null;
    }

    public void setComponentType(Type type) {
        this.componentType = type;
    }

    public void setRelatedArray(Object obj) {
        this.relatedArray = obj;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.list.size();
    }

    @Override // java.util.List
    public List<Object> subList(int i7, int i8) {
        return this.list.subList(i7, i8);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.list.toArray();
    }

    public <T> List<T> toJavaList(Class<T> cls) {
        ArrayList arrayList = new ArrayList(size());
        ParserConfig globalInstance = ParserConfig.getGlobalInstance();
        Iterator<Object> it = iterator();
        while (it.hasNext()) {
            arrayList.add(TypeUtils.cast(it.next(), (Class) cls, globalInstance));
        }
        return arrayList;
    }

    @Override // java.util.List
    public void add(int i7, Object obj) {
        this.list.add(i7, obj);
    }

    @Override // java.util.List
    public boolean addAll(int i7, Collection<? extends Object> collection) {
        return this.list.addAll(i7, collection);
    }

    public JSONArray fluentAdd(int i7, Object obj) {
        this.list.add(i7, obj);
        return this;
    }

    public JSONArray fluentAddAll(int i7, Collection<? extends Object> collection) {
        this.list.addAll(i7, collection);
        return this;
    }

    public JSONArray fluentRemove(int i7) {
        this.list.remove(i7);
        return this;
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator(int i7) {
        return this.list.listIterator(i7);
    }

    @Override // java.util.List
    public Object remove(int i7) {
        return this.list.remove(i7);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.list.toArray(tArr);
    }

    public JSONArray(List<Object> list) {
        this.list = list;
    }

    public <T> T getObject(int i7, Type type) {
        Object obj = this.list.get(i7);
        if (type instanceof Class) {
            return (T) TypeUtils.castToJavaBean(obj, (Class) type);
        }
        return (T) JSON.parseObject(JSON.toJSONString(obj), type, new Feature[0]);
    }

    public JSONArray(int i7) {
        this.list = new ArrayList(i7);
    }
}
