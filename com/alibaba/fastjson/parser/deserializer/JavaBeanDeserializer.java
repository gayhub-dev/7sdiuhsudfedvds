package com.alibaba.fastjson.parser.deserializer;

import android.support.v7.view.C0319a;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import p009b.C0413b;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    public final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] hashArray;
    private transient short[] hashArrayMapping;
    private transient long[] smartMatchHashArray;
    private transient short[] smartMatchHashArrayMapping;
    public final FieldDeserializer[] sortedFieldDeserializers;

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    private Object createFactoryInstance(ParserConfig parserConfig, Object obj) {
        return this.beanInfo.factoryMethod.invoke(null, obj);
    }

    public static boolean isSetFlag(int i7, int[] iArr) {
        if (iArr == null) {
            return false;
        }
        int i8 = i7 / 32;
        int i9 = i7 % 32;
        if (i8 < iArr.length) {
            if (((1 << i9) & iArr[i8]) != 0) {
                return true;
            }
        }
        return false;
    }

    public static void parseArray(Collection collection, ObjectDeserializer objectDeserializer, DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
        int i7 = jSONLexerBase.token();
        if (i7 == 8) {
            jSONLexerBase.nextToken(16);
            jSONLexerBase.token();
            return;
        }
        if (i7 != 14) {
            defaultJSONParser.throwException(i7);
        }
        if (jSONLexerBase.getCurrent() == '[') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(14);
        } else {
            jSONLexerBase.nextToken(14);
        }
        if (jSONLexerBase.token() == 15) {
            jSONLexerBase.nextToken();
            return;
        }
        int i8 = 0;
        while (true) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type, Integer.valueOf(i8)));
            i8++;
            if (jSONLexerBase.token() != 16) {
                break;
            }
            if (jSONLexerBase.getCurrent() == '[') {
                jSONLexerBase.next();
                jSONLexerBase.setToken(14);
            } else {
                jSONLexerBase.nextToken(14);
            }
        }
        int i9 = jSONLexerBase.token();
        if (i9 != 15) {
            defaultJSONParser.throwException(i9);
        }
        if (jSONLexerBase.getCurrent() != ',') {
            jSONLexerBase.nextToken(16);
        } else {
            jSONLexerBase.next();
            jSONLexerBase.setToken(16);
        }
    }

    public void check(JSONLexer jSONLexer, int i7) {
        if (jSONLexer.token() != i7) {
            throw new JSONException("syntax error");
        }
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
        JavaBeanInfo javaBeanInfo = this.beanInfo;
        Constructor<?> constructor = javaBeanInfo.defaultConstructor;
        Object obj = null;
        if (constructor == null && javaBeanInfo.factoryMethod == null) {
            return null;
        }
        Method method = javaBeanInfo.factoryMethod;
        if (method != null && javaBeanInfo.defaultConstructorParameterSize > 0) {
            return null;
        }
        try {
            if (javaBeanInfo.defaultConstructorParameterSize == 0) {
                objNewInstance = constructor != null ? constructor.newInstance(new Object[0]) : method.invoke(null, new Object[0]);
            } else {
                ParseContext context = defaultJSONParser.getContext();
                if (context == null || context.object == null) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                if (!(type instanceof Class)) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                String name = ((Class) type).getName();
                String strSubstring = name.substring(0, name.lastIndexOf(36));
                Object obj2 = context.object;
                String name2 = obj2.getClass().getName();
                if (!name2.equals(strSubstring)) {
                    ParseContext parseContext = context.parent;
                    if (parseContext == null || parseContext.object == null || !("java.util.ArrayList".equals(name2) || "java.util.List".equals(name2) || "java.util.Collection".equals(name2) || "java.util.Map".equals(name2) || "java.util.HashMap".equals(name2))) {
                        obj = obj2;
                    } else if (parseContext.object.getClass().getName().equals(strSubstring)) {
                        obj = parseContext.object;
                    }
                    obj2 = obj;
                }
                if (obj2 == null) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                objNewInstance = constructor.newInstance(obj2);
            }
            if (defaultJSONParser != null && defaultJSONParser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
                for (FieldInfo fieldInfo : this.beanInfo.fields) {
                    if (fieldInfo.fieldClass == String.class) {
                        try {
                            fieldInfo.set(objNewInstance, "");
                        } catch (Exception e7) {
                            throw new JSONException(C0319a.m107a(this.clazz, C0413b.m112a("create instance error, class ")), e7);
                        }
                    }
                }
            }
            return objNewInstance;
        } catch (JSONException e8) {
            throw e8;
        } catch (Exception e9) {
            throw new JSONException(C0319a.m107a(this.clazz, C0413b.m112a("create instance error, class ")), e9);
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, 0);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() != 14) {
            throw new JSONException(IjkMediaPlayer.OnNativeInvokeListener.ARG_ERROR);
        }
        T t6 = (T) createInstance(defaultJSONParser, type);
        int i7 = 0;
        int length = this.sortedFieldDeserializers.length;
        while (true) {
            if (i7 >= length) {
                break;
            }
            char c7 = i7 == length + (-1) ? ']' : ',';
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i7];
            Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
            if (cls == Integer.TYPE) {
                fieldDeserializer.setValue((Object) t6, jSONLexer.scanInt(c7));
            } else if (cls == String.class) {
                fieldDeserializer.setValue((Object) t6, jSONLexer.scanString(c7));
            } else if (cls == Long.TYPE) {
                fieldDeserializer.setValue(t6, jSONLexer.scanLong(c7));
            } else if (cls.isEnum()) {
                char current = jSONLexer.getCurrent();
                fieldDeserializer.setValue(t6, (current == '\"' || current == 'n') ? jSONLexer.scanEnum(cls, defaultJSONParser.getSymbolTable(), c7) : (current < '0' || current > '9') ? scanEnum(jSONLexer, c7) : ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.getConfig())).valueOf(jSONLexer.scanInt(c7)));
            } else if (cls == Boolean.TYPE) {
                fieldDeserializer.setValue(t6, jSONLexer.scanBoolean(c7));
            } else if (cls == Float.TYPE) {
                fieldDeserializer.setValue(t6, Float.valueOf(jSONLexer.scanFloat(c7)));
            } else if (cls == Double.TYPE) {
                fieldDeserializer.setValue(t6, Double.valueOf(jSONLexer.scanDouble(c7)));
            } else if (cls == Date.class && jSONLexer.getCurrent() == '1') {
                fieldDeserializer.setValue(t6, new Date(jSONLexer.scanLong(c7)));
            } else if (cls == BigDecimal.class) {
                fieldDeserializer.setValue(t6, jSONLexer.scanDecimal(c7));
            } else {
                jSONLexer.nextToken(14);
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                fieldDeserializer.setValue(t6, defaultJSONParser.parseObject(fieldInfo.fieldType, fieldInfo.name));
                if (jSONLexer.token() == 15) {
                    break;
                }
                check(jSONLexer, c7 == ']' ? 15 : 16);
            }
            i7++;
        }
        jSONLexer.nextToken(16);
        return t6;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return getFieldDeserializer(str, null);
    }

    public Type getFieldType(int i7) {
        return this.sortedFieldDeserializers[i7].fieldInfo.fieldType;
    }

    public JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        JSONType jSONType = javaBeanInfo.jsonType;
        if (jSONType == null) {
            return null;
        }
        for (Class<?> cls : jSONType.seeAlso()) {
            ObjectDeserializer deserializer = parserConfig.getDeserializer(cls);
            if (deserializer instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        return parseField(defaultJSONParser, str, obj, type, map, null);
    }

    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i7) {
        return parseRest(defaultJSONParser, type, obj, obj2, i7, new int[0]);
    }

    public Enum<?> scanEnum(JSONLexer jSONLexer, char c7) {
        StringBuilder sbM112a = C0413b.m112a("illegal enum. ");
        sbM112a.append(jSONLexer.info());
        throw new JSONException(sbM112a.toString());
    }

    public FieldDeserializer smartMatch(String str) {
        return smartMatch(str, null);
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, JavaBeanInfo.build(cls, type, parserConfig.propertyNamingStrategy, parserConfig.fieldBased, parserConfig.compatibleWithJavaBean, parserConfig.isJacksonCompatible()));
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, int i7) {
        return (T) deserialze(defaultJSONParser, type, obj, null, i7, null);
    }

    public FieldDeserializer getFieldDeserializer(String str, int[] iArr) {
        if (str == null) {
            return null;
        }
        int i7 = 0;
        int length = this.sortedFieldDeserializers.length - 1;
        while (i7 <= length) {
            int i8 = (i7 + length) >>> 1;
            int iCompareTo = this.sortedFieldDeserializers[i8].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i7 = i8 + 1;
            } else {
                if (iCompareTo <= 0) {
                    if (isSetFlag(i8, iArr)) {
                        return null;
                    }
                    return this.sortedFieldDeserializers[i8];
                }
                length = i8 - 1;
            }
        }
        Map<String, FieldDeserializer> map = this.alterNameFieldDeserializers;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0092 A[PHI: r2
      0x0092: PHI (r2v4 com.alibaba.fastjson.parser.deserializer.FieldDeserializer) = 
      (r2v3 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
      (r2v3 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
      (r2v32 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
      (r2v3 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
     binds: [B:10:0x0032, B:36:0x0089, B:39:0x008f, B:14:0x003f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r19v0 */
    /* JADX WARN: Type inference failed for: r19v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r19v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean parseField(com.alibaba.fastjson.parser.DefaultJSONParser r22, java.lang.String r23, java.lang.Object r24, java.lang.reflect.Type r25, java.util.Map<java.lang.String, java.lang.Object> r26, int[] r27) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseField(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.String, java.lang.Object, java.lang.reflect.Type, java.util.Map, int[]):boolean");
    }

    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i7, int[] iArr) {
        return deserialze(defaultJSONParser, type, obj, obj2, i7, iArr);
    }

    public Enum scanEnum(JSONLexerBase jSONLexerBase, char[] cArr, ObjectDeserializer objectDeserializer) {
        EnumDeserializer enumDeserializer = objectDeserializer instanceof EnumDeserializer ? (EnumDeserializer) objectDeserializer : null;
        if (enumDeserializer == null) {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        long jScanFieldSymbol = jSONLexerBase.scanFieldSymbol(cArr);
        if (jSONLexerBase.matchStat > 0) {
            return enumDeserializer.getEnumByHashCode(jScanFieldSymbol);
        }
        return null;
    }

    public FieldDeserializer smartMatch(String str, int[] iArr) {
        boolean zStartsWith;
        if (str == null) {
            return null;
        }
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str, iArr);
        if (fieldDeserializer == null) {
            long jFnv1a_64_lower = TypeUtils.fnv1a_64_lower(str);
            int i7 = 0;
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                int i8 = 0;
                while (true) {
                    FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                    if (i8 >= fieldDeserializerArr.length) {
                        break;
                    }
                    jArr[i8] = TypeUtils.fnv1a_64_lower(fieldDeserializerArr[i8].fieldInfo.name);
                    i8++;
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int iBinarySearch = Arrays.binarySearch(this.smartMatchHashArray, jFnv1a_64_lower);
            if (iBinarySearch < 0) {
                zStartsWith = str.startsWith("is");
                if (zStartsWith) {
                    iBinarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(str.substring(2)));
                }
            } else {
                zStartsWith = false;
            }
            if (iBinarySearch >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    short[] sArr = new short[this.smartMatchHashArray.length];
                    Arrays.fill(sArr, (short) -1);
                    while (true) {
                        FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                        if (i7 >= fieldDeserializerArr2.length) {
                            break;
                        }
                        int iBinarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(fieldDeserializerArr2[i7].fieldInfo.name));
                        if (iBinarySearch2 >= 0) {
                            sArr[iBinarySearch2] = (short) i7;
                        }
                        i7++;
                    }
                    this.smartMatchHashArrayMapping = sArr;
                }
                short s6 = this.smartMatchHashArrayMapping[iBinarySearch];
                if (s6 != -1 && !isSetFlag(s6, iArr)) {
                    fieldDeserializer = this.sortedFieldDeserializers[s6];
                }
            }
            if (fieldDeserializer != null) {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                if ((fieldInfo.parserFeatures & Feature.DisableFieldSmartMatch.mask) != 0) {
                    return null;
                }
                Class<?> cls = fieldInfo.fieldClass;
                if (zStartsWith && cls != Boolean.TYPE && cls != Boolean.class) {
                    return null;
                }
            }
        }
        return fieldDeserializer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:385:0x04d7, code lost:
    
        r1 = getSeeAlso(r14, r31.beanInfo, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:386:0x04dd, code lost:
    
        if (r1 != null) goto L389;
     */
    /* JADX WARN: Code restructure failed: missing block: B:387:0x04df, code lost:
    
        r7 = r14.checkAutoType(r0, com.alibaba.fastjson.util.TypeUtils.getClass(r33), r13.getFeatures());
        r1 = r32.getConfig().getDeserializer(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:389:0x04f4, code lost:
    
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:390:0x04f5, code lost:
    
        r2 = (T) r1.deserialze(r32, r7, r34);
     */
    /* JADX WARN: Code restructure failed: missing block: B:391:0x04fb, code lost:
    
        if ((r1 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L395;
     */
    /* JADX WARN: Code restructure failed: missing block: B:392:0x04fd, code lost:
    
        r1 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:393:0x04ff, code lost:
    
        if (r3 == null) goto L395;
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x0501, code lost:
    
        r1.getFieldDeserializer(r3).setValue((java.lang.Object) r2, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x0508, code lost:
    
        if (r4 == null) goto L397;
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:0x050a, code lost:
    
        r4.object = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x050e, code lost:
    
        r32.setContext(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:398:0x0511, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x051f, code lost:
    
        r7 = r4;
        r20 = r11;
        r0 = r19;
        r36 = 0;
        r11 = r6;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:656:0x08ee, code lost:
    
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r13.token()));
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:250:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0566 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x059c  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0604  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0653 A[Catch: all -> 0x08f7, TryCatch #15 {all -> 0x08f7, blocks: (B:654:0x08ba, B:467:0x0646, B:470:0x0653, B:472:0x0659, B:650:0x08ab, B:652:0x08b3, B:655:0x08d0, B:656:0x08ee, B:459:0x0627, B:461:0x062d, B:463:0x0633, B:465:0x063e, B:657:0x08ef, B:658:0x08f6), top: B:696:0x08ba }] */
    /* JADX WARN: Removed duplicated region for block: B:603:0x07e8 A[Catch: Exception -> 0x081a, all -> 0x0879, TRY_ENTER, TRY_LEAVE, TryCatch #13 {Exception -> 0x081a, blocks: (B:586:0x07be, B:588:0x07c4, B:603:0x07e8), top: B:692:0x07be }] */
    /* JADX WARN: Removed duplicated region for block: B:667:0x090a  */
    /* JADX WARN: Removed duplicated region for block: B:686:0x0391 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:692:0x07be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v100 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v158, types: [int] */
    /* JADX WARN: Type inference failed for: r1v159, types: [int] */
    /* JADX WARN: Type inference failed for: r1v161 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v29, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v91, types: [int] */
    /* JADX WARN: Type inference failed for: r1v99 */
    /* JADX WARN: Type inference failed for: r32v0, types: [com.alibaba.fastjson.parser.DefaultJSONParser] */
    /* JADX WARN: Type inference failed for: r3v19, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v31, types: [int] */
    /* JADX WARN: Type inference failed for: r3v36, types: [java.lang.Class<?>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r32, java.lang.reflect.Type r33, java.lang.Object r34, java.lang.Object r35, int r36, int[] r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 2325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object, int, int[]):java.lang.Object");
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) {
        this.clazz = javaBeanInfo.clazz;
        this.beanInfo = javaBeanInfo;
        FieldInfo[] fieldInfoArr = javaBeanInfo.sortedFields;
        this.sortedFieldDeserializers = new FieldDeserializer[fieldInfoArr.length];
        int length = fieldInfoArr.length;
        HashMap map = null;
        for (int i7 = 0; i7 < length; i7++) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i7];
            FieldDeserializer fieldDeserializerCreateFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, javaBeanInfo, fieldInfo);
            this.sortedFieldDeserializers[i7] = fieldDeserializerCreateFieldDeserializer;
            for (String str : fieldInfo.alternateNames) {
                if (map == null) {
                    map = new HashMap();
                }
                map.put(str, fieldDeserializerCreateFieldDeserializer);
            }
        }
        this.alterNameFieldDeserializers = map;
        FieldInfo[] fieldInfoArr2 = javaBeanInfo.fields;
        this.fieldDeserializers = new FieldDeserializer[fieldInfoArr2.length];
        int length2 = fieldInfoArr2.length;
        for (int i8 = 0; i8 < length2; i8++) {
            this.fieldDeserializers[i8] = getFieldDeserializer(javaBeanInfo.fields[i8].name);
        }
    }

    public FieldDeserializer getFieldDeserializer(long j7) {
        int i7 = 0;
        if (this.hashArray == null) {
            long[] jArr = new long[this.sortedFieldDeserializers.length];
            int i8 = 0;
            while (true) {
                FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                if (i8 >= fieldDeserializerArr.length) {
                    break;
                }
                jArr[i8] = TypeUtils.fnv1a_64(fieldDeserializerArr[i8].fieldInfo.name);
                i8++;
            }
            Arrays.sort(jArr);
            this.hashArray = jArr;
        }
        int iBinarySearch = Arrays.binarySearch(this.hashArray, j7);
        if (iBinarySearch < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            while (true) {
                FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                if (i7 >= fieldDeserializerArr2.length) {
                    break;
                }
                int iBinarySearch2 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(fieldDeserializerArr2[i7].fieldInfo.name));
                if (iBinarySearch2 >= 0) {
                    sArr[iBinarySearch2] = (short) i7;
                }
                i7++;
            }
            this.hashArrayMapping = sArr;
        }
        short s6 = this.hashArrayMapping[iBinarySearch];
        if (s6 != -1) {
            return this.sortedFieldDeserializers[s6];
        }
        return null;
    }

    public Object createInstance(Map<String, Object> map, ParserConfig parserConfig) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        boolean z6;
        Object objNewInstance;
        Constructor<?> constructor;
        FieldInfo[] fieldInfoArr;
        FieldInfo[] fieldInfoArr2;
        Integer num;
        Object objCast;
        JavaBeanInfo javaBeanInfo = this.beanInfo;
        if (javaBeanInfo.creatorConstructor == null && javaBeanInfo.factoryMethod == null) {
            Object objCreateInstance = createInstance((DefaultJSONParser) null, this.clazz);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                FieldDeserializer fieldDeserializerSmartMatch = smartMatch(key);
                if (fieldDeserializerSmartMatch != null) {
                    FieldInfo fieldInfo = fieldDeserializerSmartMatch.fieldInfo;
                    Type type = fieldInfo.fieldType;
                    String str = fieldInfo.format;
                    if (str != null && type == Date.class) {
                        objCast = TypeUtils.castToDate(value, str);
                    } else {
                        objCast = TypeUtils.cast(value, type, parserConfig);
                    }
                    fieldDeserializerSmartMatch.setValue(objCreateInstance, objCast);
                }
            }
            Method method = this.beanInfo.buildMethod;
            if (method == null) {
                return objCreateInstance;
            }
            try {
                return method.invoke(objCreateInstance, new Object[0]);
            } catch (Exception e7) {
                throw new JSONException("build object error", e7);
            }
        }
        FieldInfo[] fieldInfoArr3 = javaBeanInfo.fields;
        int length = fieldInfoArr3.length;
        Object[] objArr = new Object[length];
        HashMap map2 = null;
        for (int i7 = 0; i7 < length; i7++) {
            FieldInfo fieldInfo2 = fieldInfoArr3[i7];
            Object objValueOf = map.get(fieldInfo2.name);
            if (objValueOf == null) {
                Class<?> cls = fieldInfo2.fieldClass;
                if (cls == Integer.TYPE) {
                    objValueOf = 0;
                } else if (cls == Long.TYPE) {
                    objValueOf = 0L;
                } else if (cls == Short.TYPE) {
                    objValueOf = (short) 0;
                } else if (cls == Byte.TYPE) {
                    objValueOf = (byte) 0;
                } else if (cls == Float.TYPE) {
                    objValueOf = Float.valueOf(0.0f);
                } else if (cls == Double.TYPE) {
                    objValueOf = Double.valueOf(0.0d);
                } else if (cls == Character.TYPE) {
                    objValueOf = '0';
                } else if (cls == Boolean.TYPE) {
                    objValueOf = Boolean.FALSE;
                }
                if (map2 == null) {
                    map2 = new HashMap();
                }
                map2.put(fieldInfo2.name, Integer.valueOf(i7));
            }
            objArr[i7] = objValueOf;
        }
        if (map2 != null) {
            for (Map.Entry<String, Object> entry2 : map.entrySet()) {
                String key2 = entry2.getKey();
                Object value2 = entry2.getValue();
                FieldDeserializer fieldDeserializerSmartMatch2 = smartMatch(key2);
                if (fieldDeserializerSmartMatch2 != null && (num = (Integer) map2.get(fieldDeserializerSmartMatch2.fieldInfo.name)) != null) {
                    objArr[num.intValue()] = value2;
                }
            }
        }
        JavaBeanInfo javaBeanInfo2 = this.beanInfo;
        if (javaBeanInfo2.creatorConstructor != null) {
            if (javaBeanInfo2.kotlin) {
                int i8 = 0;
                while (true) {
                    if (i8 >= length) {
                        break;
                    }
                    if (objArr[i8] != null || (fieldInfoArr2 = this.beanInfo.fields) == null || i8 >= fieldInfoArr2.length) {
                        i8++;
                    } else {
                        z6 = fieldInfoArr2[i8].fieldClass == String.class;
                    }
                }
            }
            if (z6 && (constructor = this.beanInfo.kotlinDefaultConstructor) != null) {
                try {
                    objNewInstance = constructor.newInstance(new Object[0]);
                    for (int i9 = 0; i9 < length; i9++) {
                        Object obj = objArr[i9];
                        if (obj != null && (fieldInfoArr = this.beanInfo.fields) != null && i9 < fieldInfoArr.length) {
                            fieldInfoArr[i9].set(objNewInstance, obj);
                        }
                    }
                } catch (Exception e8) {
                    StringBuilder sbM112a = C0413b.m112a("create instance error, ");
                    sbM112a.append(this.beanInfo.creatorConstructor.toGenericString());
                    throw new JSONException(sbM112a.toString(), e8);
                }
            } else {
                try {
                    objNewInstance = this.beanInfo.creatorConstructor.newInstance(objArr);
                } catch (Exception e9) {
                    StringBuilder sbM112a2 = C0413b.m112a("create instance error, ");
                    sbM112a2.append(this.beanInfo.creatorConstructor.toGenericString());
                    throw new JSONException(sbM112a2.toString(), e9);
                }
            }
            return objNewInstance;
        }
        Method method2 = javaBeanInfo2.factoryMethod;
        if (method2 == null) {
            return null;
        }
        try {
            return method2.invoke(null, objArr);
        } catch (Exception e10) {
            StringBuilder sbM112a3 = C0413b.m112a("create factory method error, ");
            sbM112a3.append(this.beanInfo.factoryMethod.toString());
            throw new JSONException(sbM112a3.toString(), e10);
        }
    }
}
