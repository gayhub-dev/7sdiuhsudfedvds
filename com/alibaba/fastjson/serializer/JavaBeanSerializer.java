package com.alibaba.fastjson.serializer;

import android.arch.lifecycle.C0063n;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    public SerializeBeanInfo beanInfo;
    public final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    public final FieldSerializer[] sortedGetters;

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    public static Map<String, String> createAliasMap(String... strArr) {
        HashMap map = new HashMap();
        for (String str : strArr) {
            map.put(str, str);
        }
        return map;
    }

    public boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        List<LabelFilter> list = jSONSerializer.labelFilters;
        if (list != null) {
            Iterator<LabelFilter> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().apply(str)) {
                    return false;
                }
            }
        }
        List<LabelFilter> list2 = this.labelFilters;
        if (list2 == null) {
            return true;
        }
        Iterator<LabelFilter> it2 = list2.iterator();
        while (it2.hasNext()) {
            if (!it2.next().apply(str)) {
                return false;
            }
        }
        return true;
    }

    public BeanContext getBeanContext(int i7) {
        return this.sortedGetters[i7].fieldContext;
    }

    public Set<String> getFieldNames(Object obj) {
        HashSet hashSet = new HashSet();
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                hashSet.add(fieldSerializer.fieldInfo.name);
            }
        }
        return hashSet;
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int i7 = 0;
        int length = this.sortedGetters.length - 1;
        while (i7 <= length) {
            int i8 = (i7 + length) >>> 1;
            int iCompareTo = this.sortedGetters[i8].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i7 = i8 + 1;
            } else {
                if (iCompareTo <= 0) {
                    return this.sortedGetters[i8];
                }
                length = i8 - 1;
            }
        }
        return null;
    }

    public Type getFieldType(int i7) {
        return this.sortedGetters[i7].fieldInfo.fieldType;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer == null) {
            throw new JSONException(C0063n.m88a("field not found. ", str));
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e7) {
            throw new JSONException(C0063n.m88a("getFieldValue error.", str), e7);
        } catch (InvocationTargetException e8) {
            throw new JSONException(C0063n.m88a("getFieldValue error.", str), e8);
        }
    }

    public List<Object> getFieldValues(Object obj) {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            arrayList.add(fieldSerializer.getPropertyValue(obj));
        }
        return arrayList;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
        }
        return linkedHashMap;
    }

    public List<Object> getObjectFieldValues(Object obj) {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) {
        int i7 = 0;
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                i7++;
            }
        }
        return i7;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    public boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i7, false);
    }

    public char writeAfter(JSONSerializer jSONSerializer, Object obj, char c7) {
        List<AfterFilter> list = jSONSerializer.afterFilters;
        if (list != null) {
            Iterator<AfterFilter> it = list.iterator();
            while (it.hasNext()) {
                c7 = it.next().writeAfter(jSONSerializer, obj, c7);
            }
        }
        List<AfterFilter> list2 = this.afterFilters;
        if (list2 != null) {
            Iterator<AfterFilter> it2 = list2.iterator();
            while (it2.hasNext()) {
                c7 = it2.next().writeAfter(jSONSerializer, obj, c7);
            }
        }
        return c7;
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i7);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i7);
    }

    public char writeBefore(JSONSerializer jSONSerializer, Object obj, char c7) {
        List<BeforeFilter> list = jSONSerializer.beforeFilters;
        if (list != null) {
            Iterator<BeforeFilter> it = list.iterator();
            while (it.hasNext()) {
                c7 = it.next().writeBefore(jSONSerializer, obj, c7);
            }
        }
        List<BeforeFilter> list2 = this.beforeFilters;
        if (list2 != null) {
            Iterator<BeforeFilter> it2 = list2.iterator();
            while (it2.hasNext()) {
                c7 = it2.next().writeBefore(jSONSerializer, obj, c7);
            }
        }
        return c7;
    }

    public void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String name = this.beanInfo.typeName;
        if (name == null) {
            Class<?> superclass = obj.getClass();
            if (TypeUtils.isProxy(superclass)) {
                superclass = superclass.getSuperclass();
            }
            name = superclass.getName();
        }
        jSONSerializer.write(name);
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i7);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i7, false);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i7) {
        IdentityHashMap<Object, SerialContext> identityHashMap;
        SerialContext serialContext = jSONSerializer.context;
        int i8 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i8) != 0 || (i7 & i8) != 0 || (identityHashMap = jSONSerializer.references) == null || !identityHashMap.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    public boolean isWriteAsArray(JSONSerializer jSONSerializer, int i7) {
        int i8 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i8) == 0 && !jSONSerializer.out.beanToArray && (i7 & i8) == 0) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0255 A[PHI: r1
      0x0255: PHI (r1v42 java.lang.Object) = 
      (r1v41 java.lang.Object)
      (r1v41 java.lang.Object)
      (r1v41 java.lang.Object)
      (r1v41 java.lang.Object)
      (r1v48 java.lang.Object)
      (r1v41 java.lang.Object)
      (r1v49 java.lang.Object)
      (r1v41 java.lang.Object)
      (r1v50 java.lang.Object)
      (r1v41 java.lang.Object)
      (r1v51 java.lang.Object)
      (r1v41 java.lang.Object)
     binds: [B:116:0x0191, B:175:0x0243, B:177:0x0247, B:179:0x0251, B:174:0x023e, B:173:0x023c, B:160:0x0213, B:159:0x0211, B:146:0x01ea, B:145:0x01e8, B:133:0x01c6, B:132:0x01c4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0257 A[Catch: Exception -> 0x03eb, all -> 0x040c, TryCatch #3 {Exception -> 0x03eb, blocks: (B:102:0x0146, B:114:0x016f, B:117:0x0193, B:119:0x019b, B:122:0x01a8, B:124:0x01b3, B:126:0x01b7, B:129:0x01be, B:131:0x01c1, B:133:0x01c6, B:135:0x01cc, B:137:0x01d7, B:139:0x01db, B:142:0x01e2, B:144:0x01e5, B:147:0x01ed, B:149:0x01f5, B:151:0x0200, B:153:0x0204, B:156:0x020b, B:158:0x020e, B:160:0x0213, B:161:0x0218, B:163:0x0220, B:165:0x022b, B:167:0x022f, B:170:0x0236, B:172:0x0239, B:174:0x023e, B:176:0x0245, B:178:0x0249, B:182:0x0257, B:184:0x025b, B:186:0x0264, B:188:0x026b, B:190:0x0271, B:192:0x0275, B:195:0x0280, B:197:0x0284, B:199:0x0288, B:202:0x0293, B:204:0x0297, B:206:0x029b, B:209:0x02a6, B:211:0x02aa, B:213:0x02ae, B:216:0x02bc, B:218:0x02c0, B:220:0x02c4, B:223:0x02d1, B:225:0x02d5, B:227:0x02d9, B:230:0x02e7, B:232:0x02eb, B:234:0x02ef, B:238:0x02fb, B:240:0x02ff, B:242:0x0303, B:245:0x030e, B:247:0x031b, B:251:0x0325, B:253:0x032b, B:295:0x03ac, B:297:0x03b0, B:299:0x03b4, B:302:0x03be, B:304:0x03c6, B:305:0x03ce, B:307:0x03d4, B:258:0x0336, B:259:0x0339, B:262:0x0341, B:265:0x0347, B:269:0x0357, B:272:0x035f, B:275:0x0369, B:277:0x0372, B:280:0x0378, B:281:0x037c, B:282:0x0380, B:284:0x0387, B:285:0x038b, B:286:0x038f, B:288:0x0393, B:290:0x0397, B:293:0x03a5, B:294:0x03a9, B:266:0x034f), top: B:384:0x0146 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x026b A[Catch: Exception -> 0x03eb, all -> 0x040c, TryCatch #3 {Exception -> 0x03eb, blocks: (B:102:0x0146, B:114:0x016f, B:117:0x0193, B:119:0x019b, B:122:0x01a8, B:124:0x01b3, B:126:0x01b7, B:129:0x01be, B:131:0x01c1, B:133:0x01c6, B:135:0x01cc, B:137:0x01d7, B:139:0x01db, B:142:0x01e2, B:144:0x01e5, B:147:0x01ed, B:149:0x01f5, B:151:0x0200, B:153:0x0204, B:156:0x020b, B:158:0x020e, B:160:0x0213, B:161:0x0218, B:163:0x0220, B:165:0x022b, B:167:0x022f, B:170:0x0236, B:172:0x0239, B:174:0x023e, B:176:0x0245, B:178:0x0249, B:182:0x0257, B:184:0x025b, B:186:0x0264, B:188:0x026b, B:190:0x0271, B:192:0x0275, B:195:0x0280, B:197:0x0284, B:199:0x0288, B:202:0x0293, B:204:0x0297, B:206:0x029b, B:209:0x02a6, B:211:0x02aa, B:213:0x02ae, B:216:0x02bc, B:218:0x02c0, B:220:0x02c4, B:223:0x02d1, B:225:0x02d5, B:227:0x02d9, B:230:0x02e7, B:232:0x02eb, B:234:0x02ef, B:238:0x02fb, B:240:0x02ff, B:242:0x0303, B:245:0x030e, B:247:0x031b, B:251:0x0325, B:253:0x032b, B:295:0x03ac, B:297:0x03b0, B:299:0x03b4, B:302:0x03be, B:304:0x03c6, B:305:0x03ce, B:307:0x03d4, B:258:0x0336, B:259:0x0339, B:262:0x0341, B:265:0x0347, B:269:0x0357, B:272:0x035f, B:275:0x0369, B:277:0x0372, B:280:0x0378, B:281:0x037c, B:282:0x0380, B:284:0x0387, B:285:0x038b, B:286:0x038f, B:288:0x0393, B:290:0x0397, B:293:0x03a5, B:294:0x03a9, B:266:0x034f), top: B:384:0x0146 }] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x03e8 A[PHI: r28 r29
      0x03e8: PHI (r28v1 int) = (r28v0 int), (r28v2 int) binds: [B:314:0x03e1, B:105:0x0150] A[DONT_GENERATE, DONT_INLINE]
      0x03e8: PHI (r29v2 char) = (r29v1 char), (r29v3 char) binds: [B:314:0x03e1, B:105:0x0150] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:356:0x047f A[Catch: all -> 0x04fe, TRY_ENTER, TryCatch #12 {all -> 0x04fe, blocks: (B:353:0x045b, B:356:0x047f, B:364:0x04cd, B:366:0x04d3, B:367:0x04eb, B:369:0x04ef, B:373:0x04f8, B:374:0x04fd, B:358:0x0494, B:360:0x0498, B:362:0x049c, B:363:0x04b7), top: B:398:0x045b }] */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x04d3 A[Catch: all -> 0x04fe, TryCatch #12 {all -> 0x04fe, blocks: (B:353:0x045b, B:356:0x047f, B:364:0x04cd, B:366:0x04d3, B:367:0x04eb, B:369:0x04ef, B:373:0x04f8, B:374:0x04fd, B:358:0x0494, B:360:0x0498, B:362:0x049c, B:363:0x04b7), top: B:398:0x045b }] */
    /* JADX WARN: Removed duplicated region for block: B:369:0x04ef A[Catch: all -> 0x04fe, TryCatch #12 {all -> 0x04fe, blocks: (B:353:0x045b, B:356:0x047f, B:364:0x04cd, B:366:0x04d3, B:367:0x04eb, B:369:0x04ef, B:373:0x04f8, B:374:0x04fd, B:358:0x0494, B:360:0x0498, B:362:0x049c, B:363:0x04b7), top: B:398:0x045b }] */
    /* JADX WARN: Removed duplicated region for block: B:371:0x04f5  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r33, java.lang.Object r34, java.lang.Object r35, java.lang.reflect.Type r36, int r37, boolean r38) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        FieldSerializer[] fieldSerializerArr;
        boolean z6;
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        int i7 = 0;
        while (true) {
            fieldSerializerArr = this.sortedGetters;
            if (i7 >= fieldSerializerArr.length) {
                break;
            }
            fieldSerializerArr[i7] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i7]);
            i7++;
        }
        FieldInfo[] fieldInfoArr = serializeBeanInfo.fields;
        if (fieldInfoArr == serializeBeanInfo.sortedFields) {
            this.getters = fieldSerializerArr;
        } else {
            this.getters = new FieldSerializer[fieldInfoArr.length];
            int i8 = 0;
            while (true) {
                if (i8 >= this.getters.length) {
                    z6 = false;
                    break;
                }
                FieldSerializer fieldSerializer = getFieldSerializer(serializeBeanInfo.fields[i8].name);
                if (fieldSerializer == null) {
                    z6 = true;
                    break;
                } else {
                    this.getters[i8] = fieldSerializer;
                    i8++;
                }
            }
            if (z6) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                System.arraycopy(fieldSerializerArr2, 0, this.getters, 0, fieldSerializerArr2.length);
            }
        }
        JSONType jSONType = serializeBeanInfo.jsonType;
        if (jSONType != null) {
            for (Class<? extends SerializeFilter> cls : jSONType.serialzeFilters()) {
                try {
                    addFilter(cls.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused) {
                }
            }
        }
        JSONType jSONType2 = serializeBeanInfo.jsonType;
        if (jSONType2 != null) {
            for (Class<? extends SerializeFilter> cls2 : jSONType2.serialzeFilters()) {
                try {
                    addFilter(cls2.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused2) {
                }
            }
        }
    }

    public FieldSerializer getFieldSerializer(long j7) {
        PropertyNamingStrategy[] propertyNamingStrategyArrValues;
        int iBinarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            long[] jArr = new long[this.sortedGetters.length * propertyNamingStrategyArrValues.length];
            int i7 = 0;
            int i8 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr = this.sortedGetters;
                if (i7 >= fieldSerializerArr.length) {
                    break;
                }
                String str = fieldSerializerArr[i7].fieldInfo.name;
                jArr[i8] = TypeUtils.fnv1a_64(str);
                i8++;
                for (PropertyNamingStrategy propertyNamingStrategy : propertyNamingStrategyArrValues) {
                    String strTranslate = propertyNamingStrategy.translate(str);
                    if (!str.equals(strTranslate)) {
                        jArr[i8] = TypeUtils.fnv1a_64(strTranslate);
                        i8++;
                    }
                }
                i7++;
            }
            Arrays.sort(jArr, 0, i8);
            this.hashArray = new long[i8];
            System.arraycopy(jArr, 0, this.hashArray, 0, i8);
        } else {
            propertyNamingStrategyArrValues = null;
        }
        int iBinarySearch2 = Arrays.binarySearch(this.hashArray, j7);
        if (iBinarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArrValues == null) {
                propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            int i9 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                if (i9 >= fieldSerializerArr2.length) {
                    break;
                }
                String str2 = fieldSerializerArr2[i9].fieldInfo.name;
                int iBinarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (iBinarySearch3 >= 0) {
                    sArr[iBinarySearch3] = (short) i9;
                }
                for (PropertyNamingStrategy propertyNamingStrategy2 : propertyNamingStrategyArrValues) {
                    String strTranslate2 = propertyNamingStrategy2.translate(str2);
                    if (!str2.equals(strTranslate2) && (iBinarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(strTranslate2))) >= 0) {
                        sArr[iBinarySearch] = (short) i9;
                    }
                }
                i9++;
            }
            this.hashArrayMapping = sArr;
        }
        short s6 = this.hashArrayMapping[iBinarySearch2];
        if (s6 != -1) {
            return this.sortedGetters[s6];
        }
        return null;
    }

    public Object getFieldValue(Object obj, String str, long j7, boolean z6) {
        FieldSerializer fieldSerializer = getFieldSerializer(j7);
        if (fieldSerializer == null) {
            if (z6) {
                throw new JSONException(C0063n.m88a("field not found. ", str));
            }
            return null;
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e7) {
            throw new JSONException(C0063n.m88a("getFieldValue error.", str), e7);
        } catch (InvocationTargetException e8) {
            throw new JSONException(C0063n.m88a("getFieldValue error.", str), e8);
        }
    }
}
