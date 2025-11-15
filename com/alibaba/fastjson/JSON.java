package com.alibaba.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

/* loaded from: classes.dex */
public abstract class JSON implements JSONStreamAware, JSONAware {
    public static int DEFAULT_GENERATE_FEATURE = 0;
    public static final String VERSION = "1.2.51";
    private static final ThreadLocal<byte[]> bytesLocal;
    private static final ThreadLocal<char[]> charsLocal;
    public static TimeZone defaultTimeZone = TimeZone.getDefault();
    public static Locale defaultLocale = Locale.getDefault();
    public static String DEFAULT_TYPE_KEY = "@type";
    public static final SerializeFilter[] emptyFilters = new SerializeFilter[0];
    public static String DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static int DEFAULT_PARSER_FEATURE = (((((((Feature.AutoCloseSource.getMask() | 0) | Feature.InternFieldNames.getMask()) | Feature.UseBigDecimal.getMask()) | Feature.AllowUnQuotedFieldNames.getMask()) | Feature.AllowSingleQuotes.getMask()) | Feature.AllowArbitraryCommas.getMask()) | Feature.SortFeidFastMatch.getMask()) | Feature.IgnoreNotMatch.getMask();

    static {
        int mask = 0 | SerializerFeature.QuoteFieldNames.getMask() | SerializerFeature.SkipTransientField.getMask() | SerializerFeature.WriteEnumUsingName.getMask() | SerializerFeature.SortField.getMask();
        String stringProperty = IOUtils.getStringProperty("fastjson.serializerFeatures.MapSortField");
        int mask2 = SerializerFeature.MapSortField.getMask();
        if ("true".equals(stringProperty)) {
            mask |= mask2;
        } else if ("false".equals(stringProperty)) {
            mask &= ~mask2;
        }
        DEFAULT_GENERATE_FEATURE = mask;
        if ("true".equals(IOUtils.DEFAULT_PROPERTIES.getProperty("parser.features.NonStringKeyAsString"))) {
            DEFAULT_PARSER_FEATURE |= Feature.NonStringKeyAsString.getMask();
        }
        bytesLocal = new ThreadLocal<>();
        charsLocal = new ThreadLocal<>();
    }

    private static byte[] allocateBytes(int i7) {
        ThreadLocal<byte[]> threadLocal = bytesLocal;
        byte[] bArr = threadLocal.get();
        if (bArr != null) {
            return bArr.length < i7 ? new byte[i7] : bArr;
        }
        if (i7 > 65536) {
            return new byte[i7];
        }
        byte[] bArr2 = new byte[65536];
        threadLocal.set(bArr2);
        return bArr2;
    }

    private static char[] allocateChars(int i7) {
        ThreadLocal<char[]> threadLocal = charsLocal;
        char[] cArr = threadLocal.get();
        if (cArr != null) {
            return cArr.length < i7 ? new char[i7] : cArr;
        }
        if (i7 > 65536) {
            return new char[i7];
        }
        char[] cArr2 = new char[65536];
        threadLocal.set(cArr2);
        return cArr2;
    }

    public static <T> void handleResovleTask(DefaultJSONParser defaultJSONParser, T t6) {
        defaultJSONParser.handleResovleTask(t6);
    }

    public static Object parse(String str) {
        return parse(str, DEFAULT_PARSER_FEATURE);
    }

    public static JSONArray parseArray(String str) {
        JSONArray jSONArray = null;
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.getGlobalInstance());
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
        } else if (jSONLexer.token() != 20) {
            jSONArray = new JSONArray();
            defaultJSONParser.parseArray(jSONArray);
            defaultJSONParser.handleResovleTask(jSONArray);
        }
        defaultJSONParser.close();
        return jSONArray;
    }

    public static JSONObject parseObject(String str, Feature... featureArr) {
        return (JSONObject) parse(str, featureArr);
    }

    public static void setDefaultTypeKey(String str) {
        DEFAULT_TYPE_KEY = str;
        ParserConfig.global.symbolTable.addSymbol(str, 0, str.length(), str.hashCode(), true);
    }

    public static Object toJSON(Object obj) {
        return toJSON(obj, SerializeConfig.globalInstance);
    }

    public static byte[] toJSONBytes(Object obj, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj) {
        return toJSONString(obj, emptyFilters, new SerializerFeature[0]);
    }

    public static String toJSONStringWithDateFormat(Object obj, String str, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, SerializeConfig.globalInstance, null, str, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONStringZ(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, serializeConfig, emptyFilters, null, 0, serializerFeatureArr);
    }

    public static <T> T toJavaObject(JSON json, Class<T> cls) {
        return (T) TypeUtils.cast((Object) json, (Class) cls, ParserConfig.getGlobalInstance());
    }

    public static void writeJSONString(Writer writer, Object obj, SerializerFeature... serializerFeatureArr) {
        writeJSONString(writer, obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static void writeJSONStringTo(Object obj, Writer writer, SerializerFeature... serializerFeatureArr) {
        writeJSONString(writer, obj, serializerFeatureArr);
    }

    public String toString() {
        return toJSONString();
    }

    public static Object parse(String str, ParserConfig parserConfig) {
        return parse(str, parserConfig, DEFAULT_PARSER_FEATURE);
    }

    public static JSONObject parseObject(String str) {
        Object obj = parse(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        try {
            return (JSONObject) toJSON(obj);
        } catch (RuntimeException e7) {
            throw new JSONException("can not cast to JSONObject.", e7);
        }
    }

    public static Object toJSON(Object obj, ParserConfig parserConfig) {
        return toJSON(obj, SerializeConfig.globalInstance);
    }

    public static byte[] toJSONBytes(Object obj, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, SerializeConfig.globalInstance, new SerializeFilter[]{serializeFilter}, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static void writeJSONString(Writer writer, Object obj, int i7, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(writer, i7, serializerFeatureArr);
        try {
            new JSONSerializer(serializeWriter).write(obj);
        } finally {
            serializeWriter.close();
        }
    }

    public <T> T toJavaObject(Class<T> cls) {
        return (T) TypeUtils.cast((Object) this, (Class) cls, ParserConfig.getGlobalInstance());
    }

    public static Object parse(String str, ParserConfig parserConfig, int i7) {
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, parserConfig, i7);
        Object obj = defaultJSONParser.parse();
        defaultJSONParser.handleResovleTask(obj);
        defaultJSONParser.close();
        return obj;
    }

    public static Object toJSON(Object obj, SerializeConfig serializeConfig) {
        Map map;
        if (obj == null) {
            return null;
        }
        if (obj instanceof JSON) {
            return obj;
        }
        if (obj instanceof Map) {
            Map map2 = (Map) obj;
            int size = map2.size();
            if (map2 instanceof LinkedHashMap) {
                map = new LinkedHashMap(size);
            } else if (map2 instanceof TreeMap) {
                map = new TreeMap();
            } else {
                map = new HashMap(size);
            }
            JSONObject jSONObject = new JSONObject((Map<String, Object>) map);
            for (Map.Entry entry : map2.entrySet()) {
                jSONObject.put(TypeUtils.castToString(entry.getKey()), toJSON(entry.getValue()));
            }
            return jSONObject;
        }
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            JSONArray jSONArray = new JSONArray(collection.size());
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                jSONArray.add(toJSON(it.next()));
            }
            return jSONArray;
        }
        if (obj instanceof JSONSerializable) {
            return parse(toJSONString(obj));
        }
        Class<?> cls = obj.getClass();
        if (cls.isEnum()) {
            return ((Enum) obj).name();
        }
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            JSONArray jSONArray2 = new JSONArray(length);
            for (int i7 = 0; i7 < length; i7++) {
                jSONArray2.add(toJSON(Array.get(obj, i7)));
            }
            return jSONArray2;
        }
        if (ParserConfig.isPrimitive2(cls)) {
            return obj;
        }
        ObjectSerializer objectWriter = serializeConfig.getObjectWriter(cls);
        if (objectWriter instanceof JavaBeanSerializer) {
            JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer) objectWriter;
            JSONObject jSONObject2 = new JSONObject();
            try {
                for (Map.Entry<String, Object> entry2 : javaBeanSerializer.getFieldValuesMap(obj).entrySet()) {
                    jSONObject2.put(entry2.getKey(), toJSON(entry2.getValue()));
                }
                return jSONObject2;
            } catch (Exception e7) {
                throw new JSONException("toJSON error", e7);
            }
        }
        return parse(toJSONString(obj));
    }

    public static byte[] toJSONBytes(Object obj, int i7, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, SerializeConfig.globalInstance, i7, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, int i7, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(null, i7, serializerFeatureArr);
        try {
            new JSONSerializer(serializeWriter).write(obj);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public <T> T toJavaObject(Type type) {
        return (T) TypeUtils.cast(this, type, ParserConfig.getGlobalInstance());
    }

    public static byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, serializeConfig, emptyFilters, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public <T> T toJavaObject(TypeReference typeReference) {
        return (T) TypeUtils.cast(this, typeReference != null ? typeReference.getType() : null, ParserConfig.getGlobalInstance());
    }

    public static byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, int i7, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, serializeConfig, emptyFilters, i7, serializerFeatureArr);
    }

    public static byte[] toJSONBytes(Object obj, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, SerializeConfig.globalInstance, serializeFilterArr, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static Object parse(String str, int i7) {
        return parse(str, ParserConfig.getGlobalInstance(), i7);
    }

    public static <T> T parseObject(String str, TypeReference<T> typeReference, Feature... featureArr) {
        return (T) parseObject(str, typeReference.type, ParserConfig.global, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, serializeConfig, new SerializeFilter[]{serializeFilter}, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, SerializerFeature... serializerFeatureArr) {
        return writeJSONString(outputStream, obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static Object parse(byte[] bArr, Feature... featureArr) {
        char[] cArrAllocateChars = allocateChars(bArr.length);
        int iDecodeUTF8 = IOUtils.decodeUTF8(bArr, 0, bArr.length, cArrAllocateChars);
        if (iDecodeUTF8 < 0) {
            return null;
        }
        return parse(new String(cArrAllocateChars, 0, iDecodeUTF8), featureArr);
    }

    public static <T> T parseObject(String str, Class<T> cls, Feature... featureArr) {
        return (T) parseObject(str, cls, ParserConfig.global, (ParseProcess) null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, int i7, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(null, i7, serializerFeatureArr);
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter, serializeConfig);
            if (serializeFilterArr != null) {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    jSONSerializer.addFilter(serializeFilter);
                }
            }
            jSONSerializer.write(obj);
            return serializeWriter.toBytes(IOUtils.UTF8);
        } finally {
            serializeWriter.close();
        }
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, int i7, SerializerFeature... serializerFeatureArr) {
        return writeJSONString(outputStream, IOUtils.UTF8, obj, SerializeConfig.globalInstance, null, null, i7, serializerFeatureArr);
    }

    public static <T> T parseObject(String str, Class<T> cls, ParseProcess parseProcess, Feature... featureArr) {
        return (T) parseObject(str, cls, ParserConfig.global, parseProcess, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static String toJSONString(Object obj, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, SerializeConfig.globalInstance, new SerializeFilter[]{serializeFilter}, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Charset charset, Object obj, SerializerFeature... serializerFeatureArr) {
        return writeJSONString(outputStream, charset, obj, SerializeConfig.globalInstance, null, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static <T> List<T> parseArray(String str, Class<T> cls) {
        ArrayList arrayList = null;
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.getGlobalInstance());
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i7 = jSONLexer.token();
        if (i7 == 8) {
            jSONLexer.nextToken();
        } else if (i7 != 20 || !jSONLexer.isBlankInput()) {
            arrayList = new ArrayList();
            defaultJSONParser.parseArray((Class<?>) cls, (Collection) arrayList);
            defaultJSONParser.handleResovleTask(arrayList);
        }
        defaultJSONParser.close();
        return arrayList;
    }

    public static <T> T parseObject(String str, Type type, Feature... featureArr) {
        return (T) parseObject(str, type, ParserConfig.global, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static String toJSONString(Object obj, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, SerializeConfig.globalInstance, serializeFilterArr, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Charset charset, Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, String str, int i7, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(null, i7, serializerFeatureArr);
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter, serializeConfig);
            if (str != null && str.length() != 0) {
                jSONSerializer.setDateFormat(str);
                jSONSerializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (serializeFilterArr != null) {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    jSONSerializer.addFilter(serializeFilter);
                }
            }
            jSONSerializer.write(obj);
            return serializeWriter.writeToEx(outputStream, charset);
        } finally {
            serializeWriter.close();
        }
    }

    public static Object parse(byte[] bArr, int i7, int i8, CharsetDecoder charsetDecoder, Feature... featureArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int iConfig = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            iConfig = Feature.config(iConfig, feature, true);
        }
        return parse(bArr, i7, i8, charsetDecoder, iConfig);
    }

    public static <T> T parseObject(String str, Type type, ParseProcess parseProcess, Feature... featureArr) {
        return (T) parseObject(str, type, ParserConfig.global, parseProcess, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static String toJSONString(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, serializeConfig, (SerializeFilter) null, serializerFeatureArr);
    }

    public static <T> T parseObject(String str, Type type, int i7, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        for (Feature feature : featureArr) {
            i7 = Feature.config(i7, feature, true);
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.getGlobalInstance(), i7);
        T t6 = (T) defaultJSONParser.parseObject(type);
        defaultJSONParser.handleResovleTask(t6);
        defaultJSONParser.close();
        return t6;
    }

    public static String toJSONString(Object obj, SerializeConfig serializeConfig, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, serializeConfig, new SerializeFilter[]{serializeFilter}, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, serializeConfig, serializeFilterArr, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, String str, int i7, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(null, i7, serializerFeatureArr);
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter, serializeConfig);
            if (str != null && str.length() != 0) {
                jSONSerializer.setDateFormat(str);
                jSONSerializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (serializeFilterArr != null) {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    jSONSerializer.addFilter(serializeFilter);
                }
            }
            jSONSerializer.write(obj);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public static Object parse(byte[] bArr, int i7, int i8, CharsetDecoder charsetDecoder, int i9) throws CharacterCodingException {
        charsetDecoder.reset();
        char[] cArrAllocateChars = allocateChars((int) (i8 * charsetDecoder.maxCharsPerByte()));
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i7, i8);
        CharBuffer charBufferWrap = CharBuffer.wrap(cArrAllocateChars);
        IOUtils.decode(charsetDecoder, byteBufferWrap, charBufferWrap);
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(cArrAllocateChars, charBufferWrap.position(), ParserConfig.getGlobalInstance(), i9);
        Object obj = defaultJSONParser.parse();
        defaultJSONParser.handleResovleTask(obj);
        defaultJSONParser.close();
        return obj;
    }

    public static <T> T parseObject(String str, Type type, ParserConfig parserConfig, Feature... featureArr) {
        return (T) parseObject(str, type, parserConfig, (ParseProcess) null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static List<Object> parseArray(String str, Type[] typeArr) {
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.getGlobalInstance());
        Object[] array = defaultJSONParser.parseArray(typeArr);
        List<Object> listAsList = array != null ? Arrays.asList(array) : null;
        defaultJSONParser.handleResovleTask(listAsList);
        defaultJSONParser.close();
        return listAsList;
    }

    public static <T> T parseObject(String str, Type type, ParserConfig parserConfig, int i7, Feature... featureArr) {
        return (T) parseObject(str, type, parserConfig, (ParseProcess) null, i7, featureArr);
    }

    public static <T> T parseObject(String str, Type type, ParserConfig parserConfig, ParseProcess parseProcess, int i7, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        if (featureArr != null) {
            for (Feature feature : featureArr) {
                i7 |= feature.mask;
            }
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, parserConfig, i7);
        if (parseProcess != null) {
            if (parseProcess instanceof ExtraTypeProvider) {
                defaultJSONParser.getExtraTypeProviders().add((ExtraTypeProvider) parseProcess);
            }
            if (parseProcess instanceof ExtraProcessor) {
                defaultJSONParser.getExtraProcessors().add((ExtraProcessor) parseProcess);
            }
            if (parseProcess instanceof FieldTypeResolver) {
                defaultJSONParser.setFieldTypeResolver((FieldTypeResolver) parseProcess);
            }
        }
        T t6 = (T) defaultJSONParser.parseObject(type, (Object) null);
        defaultJSONParser.handleResovleTask(t6);
        defaultJSONParser.close();
        return t6;
    }

    @Override // com.alibaba.fastjson.JSONStreamAware
    public void writeJSONString(Appendable appendable) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            try {
                new JSONSerializer(serializeWriter).write(this);
                appendable.append(serializeWriter.toString());
            } catch (IOException e7) {
                throw new JSONException(e7.getMessage(), e7);
            }
        } finally {
            serializeWriter.close();
        }
    }

    public static String toJSONString(Object obj, boolean z6) {
        return !z6 ? toJSONString(obj) : toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    public static Object parse(String str, Feature... featureArr) {
        int iConfig = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            iConfig = Feature.config(iConfig, feature, true);
        }
        return parse(str, iConfig);
    }

    @Override // com.alibaba.fastjson.JSONAware
    public String toJSONString() {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            new JSONSerializer(serializeWriter).write(this);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public static <T> T parseObject(byte[] bArr, Type type, Feature... featureArr) {
        return (T) parseObject(bArr, 0, bArr.length, IOUtils.UTF8, type, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, int i7, int i8, Charset charset, Type type, Feature... featureArr) {
        String str;
        if (charset == null) {
            charset = IOUtils.UTF8;
        }
        if (charset == IOUtils.UTF8) {
            char[] cArrAllocateChars = allocateChars(bArr.length);
            int iDecodeUTF8 = IOUtils.decodeUTF8(bArr, i7, i8, cArrAllocateChars);
            if (iDecodeUTF8 < 0) {
                return null;
            }
            str = new String(cArrAllocateChars, 0, iDecodeUTF8);
        } else {
            if (i8 < 0) {
                return null;
            }
            str = new String(bArr, i7, i8, charset);
        }
        return (T) parseObject(str, type, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, int i7, int i8, CharsetDecoder charsetDecoder, Type type, Feature... featureArr) throws CharacterCodingException {
        charsetDecoder.reset();
        char[] cArrAllocateChars = allocateChars((int) (i8 * charsetDecoder.maxCharsPerByte()));
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i7, i8);
        CharBuffer charBufferWrap = CharBuffer.wrap(cArrAllocateChars);
        IOUtils.decode(charsetDecoder, byteBufferWrap, charBufferWrap);
        return (T) parseObject(cArrAllocateChars, charBufferWrap.position(), type, featureArr);
    }

    public static <T> T parseObject(char[] cArr, int i7, Type type, Feature... featureArr) {
        if (cArr == null || cArr.length == 0) {
            return null;
        }
        int iConfig = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            iConfig = Feature.config(iConfig, feature, true);
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(cArr, i7, ParserConfig.getGlobalInstance(), iConfig);
        T t6 = (T) defaultJSONParser.parseObject(type);
        defaultJSONParser.handleResovleTask(t6);
        defaultJSONParser.close();
        return t6;
    }

    public static <T> T parseObject(InputStream inputStream, Type type, Feature... featureArr) {
        return (T) parseObject(inputStream, IOUtils.UTF8, type, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, Feature... featureArr) throws IOException {
        if (charset == null) {
            charset = IOUtils.UTF8;
        }
        Charset charset2 = charset;
        byte[] bArrAllocateBytes = allocateBytes(65536);
        int i7 = 0;
        while (true) {
            int i8 = inputStream.read(bArrAllocateBytes, i7, bArrAllocateBytes.length - i7);
            if (i8 == -1) {
                return (T) parseObject(bArrAllocateBytes, 0, i7, charset2, type, featureArr);
            }
            i7 += i8;
            if (i7 == bArrAllocateBytes.length) {
                byte[] bArr = new byte[(bArrAllocateBytes.length * 3) / 2];
                System.arraycopy(bArrAllocateBytes, 0, bArr, 0, bArrAllocateBytes.length);
                bArrAllocateBytes = bArr;
            }
        }
    }

    public static <T> T parseObject(String str, Class<T> cls) {
        return (T) parseObject(str, (Class) cls, new Feature[0]);
    }
}
