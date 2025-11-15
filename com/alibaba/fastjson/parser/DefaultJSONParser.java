package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import p009b.C0413b;

/* loaded from: classes.dex */
public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    private String[] autoTypeAccept;
    private boolean autoTypeEnable;
    public ParserConfig config;
    public ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private List<ExtraProcessor> extraProcessors;
    private List<ExtraTypeProvider> extraTypeProviders;
    public FieldTypeResolver fieldTypeResolver;
    public final Object input;
    public transient BeanContext lastBeanContext;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    public static class ResolveTask {
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        public final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    static {
        Class<?>[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class};
        for (int i7 = 0; i7 < 17; i7++) {
            primitiveClasses.add(clsArr[i7]);
        }
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    private void addContext(ParseContext parseContext) {
        int i7 = this.contextArrayIndex;
        this.contextArrayIndex = i7 + 1;
        ParseContext[] parseContextArr = this.contextArray;
        if (parseContextArr == null) {
            this.contextArray = new ParseContext[8];
        } else if (i7 >= parseContextArr.length) {
            ParseContext[] parseContextArr2 = new ParseContext[(parseContextArr.length * 3) / 2];
            System.arraycopy(parseContextArr, 0, parseContextArr2, 0, parseContextArr.length);
            this.contextArray = parseContextArr2;
        }
        this.contextArray[i7] = parseContext;
    }

    public final void accept(int i7) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i7) {
            jSONLexer.nextToken();
            return;
        }
        StringBuilder sbM112a = C0413b.m112a("syntax error, expect ");
        sbM112a.append(JSONToken.name(i7));
        sbM112a.append(", actual ");
        sbM112a.append(JSONToken.name(jSONLexer.token()));
        throw new JSONException(sbM112a.toString());
    }

    public void acceptType(String str) {
        JSONLexer jSONLexer = this.lexer;
        jSONLexer.nextTokenWithColon();
        if (jSONLexer.token() != 4) {
            throw new JSONException("type not match error");
        }
        if (!str.equals(jSONLexer.stringVal())) {
            throw new JSONException("type not match error");
        }
        jSONLexer.nextToken();
        if (jSONLexer.token() == 16) {
            jSONLexer.nextToken();
        }
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus == 1) {
            if (!(collection instanceof List)) {
                ResolveTask lastResolveTask = getLastResolveTask();
                lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(collection);
                lastResolveTask.ownerContext = this.context;
                setResolveStatus(0);
                return;
            }
            int size = collection.size() - 1;
            ResolveTask lastResolveTask2 = getLastResolveTask();
            lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, size);
            lastResolveTask2.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public void checkMapResolve(Map map, Object obj) {
        if (this.resolveStatus == 1) {
            ResolveFieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        JSONLexer jSONLexer = this.lexer;
        try {
            if (jSONLexer.isEnabled(Feature.AutoCloseSource) && jSONLexer.token() != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(jSONLexer.token()));
            }
        } finally {
            jSONLexer.close();
        }
    }

    public void config(Feature feature, boolean z6) {
        this.lexer.config(feature, z6);
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public ParseContext getContext() {
        return this.context;
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.getLocale());
            this.dateFormat = simpleDateFormat;
            simpleDateFormat.setTimeZone(this.lexer.getTimeZone());
        }
        return this.dateFormat;
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public FieldTypeResolver getFieldTypeResolver() {
        return this.fieldTypeResolver;
    }

    public String getInput() {
        Object obj = this.input;
        return obj instanceof char[] ? new String((char[]) obj) : obj.toString();
    }

    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(r0.size() - 1);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public Object getObject(String str) {
        for (int i7 = 0; i7 < this.contextArrayIndex; i7++) {
            if (str.equals(this.contextArray[i7].toString())) {
                return this.contextArray[i7].object;
            }
        }
        return null;
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        return this.resolveTaskList;
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public void handleResovleTask(Object obj) {
        Object objEval;
        FieldInfo fieldInfo;
        List<ResolveTask> list = this.resolveTaskList;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            ResolveTask resolveTask = this.resolveTaskList.get(i7);
            String str = resolveTask.referenceValue;
            ParseContext parseContext = resolveTask.ownerContext;
            Object obj2 = parseContext != null ? parseContext.object : null;
            if (str.startsWith("$")) {
                objEval = getObject(str);
                if (objEval == null) {
                    try {
                        objEval = JSONPath.eval(obj, str);
                    } catch (JSONPathException unused) {
                    }
                }
            } else {
                objEval = resolveTask.context.object;
            }
            FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
            if (fieldDeserializer != null) {
                if (objEval != null && objEval.getClass() == JSONObject.class && (fieldInfo = fieldDeserializer.fieldInfo) != null && !Map.class.isAssignableFrom(fieldInfo.fieldClass)) {
                    objEval = JSONPath.eval(this.contextArray[0].object, str);
                }
                fieldDeserializer.setValue(obj2, objEval);
            }
        }
    }

    public boolean isEnabled(Feature feature) {
        return this.lexer.isEnabled(feature);
    }

    public Object parse() {
        return parse(null);
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new JSONException("not support type " + type);
        }
        Type type2 = actualTypeArguments[0];
        if (type2 instanceof Class) {
            ArrayList arrayList = new ArrayList();
            parseArray((Class<?>) type2, (Collection) arrayList);
            return arrayList;
        }
        if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Type type3 = wildcardType.getUpperBounds()[0];
            if (!Object.class.equals(type3)) {
                ArrayList arrayList2 = new ArrayList();
                parseArray((Class<?>) type3, (Collection) arrayList2);
                return arrayList2;
            }
            if (wildcardType.getLowerBounds().length == 0) {
                return parse();
            }
            throw new JSONException("not support type : " + type);
        }
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length != 1) {
                throw new JSONException("not support : " + typeVariable);
            }
            Type type4 = bounds[0];
            if (type4 instanceof Class) {
                ArrayList arrayList3 = new ArrayList();
                parseArray((Class<?>) type4, (Collection) arrayList3);
                return arrayList3;
            }
        }
        if (type2 instanceof ParameterizedType) {
            ArrayList arrayList4 = new ArrayList();
            parseArray((ParameterizedType) type2, arrayList4);
            return arrayList4;
        }
        throw new JSONException("TODO : " + type);
    }

    public void parseExtra(Object obj, String str) {
        this.lexer.nextTokenWithColon();
        List<ExtraTypeProvider> list = this.extraTypeProviders;
        Type extraType = null;
        if (list != null) {
            Iterator<ExtraTypeProvider> it = list.iterator();
            while (it.hasNext()) {
                extraType = it.next().getExtraType(obj, str);
            }
        }
        Object object = extraType == null ? parse() : parseObject(extraType);
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, object);
            return;
        }
        List<ExtraProcessor> list2 = this.extraProcessors;
        if (list2 != null) {
            Iterator<ExtraProcessor> it2 = list2.iterator();
            while (it2.hasNext()) {
                it2.next().processExtra(obj, str, object);
            }
        }
        if (this.resolveStatus == 1) {
            this.resolveStatus = 0;
        }
    }

    public Object parseKey() {
        if (this.lexer.token() != 18) {
            return parse(null);
        }
        String strStringVal = this.lexer.stringVal();
        this.lexer.nextToken(16);
        return strStringVal;
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x024f, code lost:
    
        r2.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x025a, code lost:
    
        if (r2.token() != 13) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x025c, code lost:
    
        r2.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x025f, code lost:
    
        r14 = r13.config.getDeserializer(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0267, code lost:
    
        if ((r14 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0269, code lost:
    
        r14 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r14;
        r8 = r14.createInstance(r13, r7);
        r15 = r4.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x027b, code lost:
    
        if (r15.hasNext() == false) goto L408;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x027d, code lost:
    
        r0 = (java.util.Map.Entry) r15.next();
        r1 = r0.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0289, code lost:
    
        if ((r1 instanceof java.lang.String) == false) goto L412;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x028b, code lost:
    
        r1 = r14.getFieldDeserializer((java.lang.String) r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0291, code lost:
    
        if (r1 == null) goto L413;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x0293, code lost:
    
        r1.setValue(r8, r0.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x029b, code lost:
    
        if (r8 != null) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x029f, code lost:
    
        if (r7 != java.lang.Cloneable.class) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x02a1, code lost:
    
        r8 = new java.util.HashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02ad, code lost:
    
        if ("java.util.Collections$EmptyMap".equals(r6) == false) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x02af, code lost:
    
        r8 = java.util.Collections.emptyMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x02b4, code lost:
    
        r8 = r7.newInstance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x02bb, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x02bc, code lost:
    
        r14 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x02c4, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error", r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x02c5, code lost:
    
        setResolveStatus(2);
        r0 = r13.context;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x02cb, code lost:
    
        if (r0 == null) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x02cd, code lost:
    
        if (r15 == null) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x02d1, code lost:
    
        if ((r15 instanceof java.lang.Integer) != false) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x02d7, code lost:
    
        if ((r0.fieldName instanceof java.lang.Integer) != false) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x02d9, code lost:
    
        popContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x02e0, code lost:
    
        if (r14.size() <= 0) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x02e2, code lost:
    
        r14 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r14, (java.lang.Class<java.lang.Object>) r7, r13.config);
        parseObject(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x02ee, code lost:
    
        return r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x02ef, code lost:
    
        r14 = r13.config.getDeserializer(r7);
        r0 = r14.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x02ff, code lost:
    
        if (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class.isAssignableFrom(r0) == false) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0303, code lost:
    
        if (r0 == com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0307, code lost:
    
        if (r0 == com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer.class) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0309, code lost:
    
        setResolveStatus(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0310, code lost:
    
        if ((r14 instanceof com.alibaba.fastjson.parser.deserializer.MapDeserializer) == false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0312, code lost:
    
        setResolveStatus(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x031d, code lost:
    
        return r14.deserialze(r13, r7, r15);
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01f9 A[Catch: all -> 0x060f, TryCatch #2 {all -> 0x060f, blocks: (B:24:0x006a, B:26:0x006e, B:29:0x0078, B:32:0x008b, B:36:0x00a3, B:109:0x01f9, B:110:0x01ff, B:112:0x020a, B:114:0x0212, B:118:0x0226, B:120:0x0234, B:123:0x0248, B:124:0x024f, B:126:0x025c, B:127:0x025f, B:129:0x0269, B:130:0x0277, B:132:0x027d, B:134:0x028b, B:136:0x0293, B:140:0x02a1, B:141:0x02a7, B:143:0x02af, B:144:0x02b4, B:148:0x02bd, B:149:0x02c4, B:150:0x02c5, B:153:0x02cf, B:155:0x02d3, B:157:0x02d9, B:158:0x02dc, B:160:0x02e2, B:163:0x02ef, B:169:0x0309, B:173:0x0316, B:170:0x030e, B:172:0x0312, B:121:0x023a, B:180:0x0325, B:182:0x032d, B:184:0x0337, B:186:0x0348, B:187:0x034d, B:189:0x0355, B:191:0x0359, B:193:0x035f, B:196:0x0364, B:198:0x0368, B:217:0x03b2, B:219:0x03ba, B:222:0x03c3, B:223:0x03dd, B:200:0x036d, B:202:0x0375, B:205:0x037b, B:206:0x0387, B:209:0x0390, B:212:0x0396, B:215:0x039b, B:216:0x03a7, B:224:0x03de, B:225:0x03fc, B:227:0x03ff, B:229:0x0403, B:231:0x0407, B:234:0x040d, B:238:0x0415, B:244:0x0425, B:246:0x0434, B:248:0x043f, B:249:0x0447, B:250:0x044a, B:262:0x0476, B:264:0x0481, B:267:0x048a, B:270:0x049a, B:271:0x04ba, B:257:0x045a, B:259:0x0464, B:261:0x0473, B:260:0x0469, B:274:0x04bf, B:276:0x04c9, B:278:0x04ce, B:279:0x04d1, B:281:0x04dc, B:282:0x04e0, B:284:0x04eb, B:287:0x04f2, B:290:0x04fc, B:291:0x0501, B:294:0x0506, B:296:0x050b, B:300:0x0514, B:302:0x051c, B:305:0x053a, B:307:0x0540, B:310:0x0546, B:312:0x054c, B:314:0x0554, B:317:0x0563, B:320:0x056b, B:322:0x056f, B:323:0x0576, B:325:0x057b, B:326:0x057e, B:328:0x0586, B:331:0x0590, B:334:0x059a, B:335:0x059f, B:336:0x05a4, B:337:0x05be, B:303:0x052d, B:338:0x05bf, B:340:0x05d1, B:343:0x05d8, B:346:0x05e2, B:347:0x0602, B:39:0x00b4, B:40:0x00d2, B:43:0x00d7, B:45:0x00e2, B:47:0x00e6, B:49:0x00ea, B:52:0x00f0, B:59:0x00ff, B:61:0x0107, B:64:0x0118, B:65:0x0130, B:66:0x0131, B:67:0x0136, B:78:0x014b, B:79:0x0151, B:81:0x0158, B:83:0x0161, B:85:0x0169, B:86:0x016d, B:89:0x0174, B:90:0x018c, B:82:0x015d, B:91:0x018d, B:92:0x01a5, B:98:0x01af, B:100:0x01b7, B:103:0x01c8, B:104:0x01e8, B:105:0x01e9, B:106:0x01ee, B:107:0x01ef, B:348:0x0603, B:349:0x0608, B:350:0x0609, B:351:0x060e), top: B:358:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:227:0x03ff A[Catch: all -> 0x060f, TryCatch #2 {all -> 0x060f, blocks: (B:24:0x006a, B:26:0x006e, B:29:0x0078, B:32:0x008b, B:36:0x00a3, B:109:0x01f9, B:110:0x01ff, B:112:0x020a, B:114:0x0212, B:118:0x0226, B:120:0x0234, B:123:0x0248, B:124:0x024f, B:126:0x025c, B:127:0x025f, B:129:0x0269, B:130:0x0277, B:132:0x027d, B:134:0x028b, B:136:0x0293, B:140:0x02a1, B:141:0x02a7, B:143:0x02af, B:144:0x02b4, B:148:0x02bd, B:149:0x02c4, B:150:0x02c5, B:153:0x02cf, B:155:0x02d3, B:157:0x02d9, B:158:0x02dc, B:160:0x02e2, B:163:0x02ef, B:169:0x0309, B:173:0x0316, B:170:0x030e, B:172:0x0312, B:121:0x023a, B:180:0x0325, B:182:0x032d, B:184:0x0337, B:186:0x0348, B:187:0x034d, B:189:0x0355, B:191:0x0359, B:193:0x035f, B:196:0x0364, B:198:0x0368, B:217:0x03b2, B:219:0x03ba, B:222:0x03c3, B:223:0x03dd, B:200:0x036d, B:202:0x0375, B:205:0x037b, B:206:0x0387, B:209:0x0390, B:212:0x0396, B:215:0x039b, B:216:0x03a7, B:224:0x03de, B:225:0x03fc, B:227:0x03ff, B:229:0x0403, B:231:0x0407, B:234:0x040d, B:238:0x0415, B:244:0x0425, B:246:0x0434, B:248:0x043f, B:249:0x0447, B:250:0x044a, B:262:0x0476, B:264:0x0481, B:267:0x048a, B:270:0x049a, B:271:0x04ba, B:257:0x045a, B:259:0x0464, B:261:0x0473, B:260:0x0469, B:274:0x04bf, B:276:0x04c9, B:278:0x04ce, B:279:0x04d1, B:281:0x04dc, B:282:0x04e0, B:284:0x04eb, B:287:0x04f2, B:290:0x04fc, B:291:0x0501, B:294:0x0506, B:296:0x050b, B:300:0x0514, B:302:0x051c, B:305:0x053a, B:307:0x0540, B:310:0x0546, B:312:0x054c, B:314:0x0554, B:317:0x0563, B:320:0x056b, B:322:0x056f, B:323:0x0576, B:325:0x057b, B:326:0x057e, B:328:0x0586, B:331:0x0590, B:334:0x059a, B:335:0x059f, B:336:0x05a4, B:337:0x05be, B:303:0x052d, B:338:0x05bf, B:340:0x05d1, B:343:0x05d8, B:346:0x05e2, B:347:0x0602, B:39:0x00b4, B:40:0x00d2, B:43:0x00d7, B:45:0x00e2, B:47:0x00e6, B:49:0x00ea, B:52:0x00f0, B:59:0x00ff, B:61:0x0107, B:64:0x0118, B:65:0x0130, B:66:0x0131, B:67:0x0136, B:78:0x014b, B:79:0x0151, B:81:0x0158, B:83:0x0161, B:85:0x0169, B:86:0x016d, B:89:0x0174, B:90:0x018c, B:82:0x015d, B:91:0x018d, B:92:0x01a5, B:98:0x01af, B:100:0x01b7, B:103:0x01c8, B:104:0x01e8, B:105:0x01e9, B:106:0x01ee, B:107:0x01ef, B:348:0x0603, B:349:0x0608, B:350:0x0609, B:351:0x060e), top: B:358:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:251:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0481 A[Catch: all -> 0x060f, TryCatch #2 {all -> 0x060f, blocks: (B:24:0x006a, B:26:0x006e, B:29:0x0078, B:32:0x008b, B:36:0x00a3, B:109:0x01f9, B:110:0x01ff, B:112:0x020a, B:114:0x0212, B:118:0x0226, B:120:0x0234, B:123:0x0248, B:124:0x024f, B:126:0x025c, B:127:0x025f, B:129:0x0269, B:130:0x0277, B:132:0x027d, B:134:0x028b, B:136:0x0293, B:140:0x02a1, B:141:0x02a7, B:143:0x02af, B:144:0x02b4, B:148:0x02bd, B:149:0x02c4, B:150:0x02c5, B:153:0x02cf, B:155:0x02d3, B:157:0x02d9, B:158:0x02dc, B:160:0x02e2, B:163:0x02ef, B:169:0x0309, B:173:0x0316, B:170:0x030e, B:172:0x0312, B:121:0x023a, B:180:0x0325, B:182:0x032d, B:184:0x0337, B:186:0x0348, B:187:0x034d, B:189:0x0355, B:191:0x0359, B:193:0x035f, B:196:0x0364, B:198:0x0368, B:217:0x03b2, B:219:0x03ba, B:222:0x03c3, B:223:0x03dd, B:200:0x036d, B:202:0x0375, B:205:0x037b, B:206:0x0387, B:209:0x0390, B:212:0x0396, B:215:0x039b, B:216:0x03a7, B:224:0x03de, B:225:0x03fc, B:227:0x03ff, B:229:0x0403, B:231:0x0407, B:234:0x040d, B:238:0x0415, B:244:0x0425, B:246:0x0434, B:248:0x043f, B:249:0x0447, B:250:0x044a, B:262:0x0476, B:264:0x0481, B:267:0x048a, B:270:0x049a, B:271:0x04ba, B:257:0x045a, B:259:0x0464, B:261:0x0473, B:260:0x0469, B:274:0x04bf, B:276:0x04c9, B:278:0x04ce, B:279:0x04d1, B:281:0x04dc, B:282:0x04e0, B:284:0x04eb, B:287:0x04f2, B:290:0x04fc, B:291:0x0501, B:294:0x0506, B:296:0x050b, B:300:0x0514, B:302:0x051c, B:305:0x053a, B:307:0x0540, B:310:0x0546, B:312:0x054c, B:314:0x0554, B:317:0x0563, B:320:0x056b, B:322:0x056f, B:323:0x0576, B:325:0x057b, B:326:0x057e, B:328:0x0586, B:331:0x0590, B:334:0x059a, B:335:0x059f, B:336:0x05a4, B:337:0x05be, B:303:0x052d, B:338:0x05bf, B:340:0x05d1, B:343:0x05d8, B:346:0x05e2, B:347:0x0602, B:39:0x00b4, B:40:0x00d2, B:43:0x00d7, B:45:0x00e2, B:47:0x00e6, B:49:0x00ea, B:52:0x00f0, B:59:0x00ff, B:61:0x0107, B:64:0x0118, B:65:0x0130, B:66:0x0131, B:67:0x0136, B:78:0x014b, B:79:0x0151, B:81:0x0158, B:83:0x0161, B:85:0x0169, B:86:0x016d, B:89:0x0174, B:90:0x018c, B:82:0x015d, B:91:0x018d, B:92:0x01a5, B:98:0x01af, B:100:0x01b7, B:103:0x01c8, B:104:0x01e8, B:105:0x01e9, B:106:0x01ee, B:107:0x01ef, B:348:0x0603, B:349:0x0608, B:350:0x0609, B:351:0x060e), top: B:358:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:315:0x055f  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0563 A[Catch: all -> 0x060f, TryCatch #2 {all -> 0x060f, blocks: (B:24:0x006a, B:26:0x006e, B:29:0x0078, B:32:0x008b, B:36:0x00a3, B:109:0x01f9, B:110:0x01ff, B:112:0x020a, B:114:0x0212, B:118:0x0226, B:120:0x0234, B:123:0x0248, B:124:0x024f, B:126:0x025c, B:127:0x025f, B:129:0x0269, B:130:0x0277, B:132:0x027d, B:134:0x028b, B:136:0x0293, B:140:0x02a1, B:141:0x02a7, B:143:0x02af, B:144:0x02b4, B:148:0x02bd, B:149:0x02c4, B:150:0x02c5, B:153:0x02cf, B:155:0x02d3, B:157:0x02d9, B:158:0x02dc, B:160:0x02e2, B:163:0x02ef, B:169:0x0309, B:173:0x0316, B:170:0x030e, B:172:0x0312, B:121:0x023a, B:180:0x0325, B:182:0x032d, B:184:0x0337, B:186:0x0348, B:187:0x034d, B:189:0x0355, B:191:0x0359, B:193:0x035f, B:196:0x0364, B:198:0x0368, B:217:0x03b2, B:219:0x03ba, B:222:0x03c3, B:223:0x03dd, B:200:0x036d, B:202:0x0375, B:205:0x037b, B:206:0x0387, B:209:0x0390, B:212:0x0396, B:215:0x039b, B:216:0x03a7, B:224:0x03de, B:225:0x03fc, B:227:0x03ff, B:229:0x0403, B:231:0x0407, B:234:0x040d, B:238:0x0415, B:244:0x0425, B:246:0x0434, B:248:0x043f, B:249:0x0447, B:250:0x044a, B:262:0x0476, B:264:0x0481, B:267:0x048a, B:270:0x049a, B:271:0x04ba, B:257:0x045a, B:259:0x0464, B:261:0x0473, B:260:0x0469, B:274:0x04bf, B:276:0x04c9, B:278:0x04ce, B:279:0x04d1, B:281:0x04dc, B:282:0x04e0, B:284:0x04eb, B:287:0x04f2, B:290:0x04fc, B:291:0x0501, B:294:0x0506, B:296:0x050b, B:300:0x0514, B:302:0x051c, B:305:0x053a, B:307:0x0540, B:310:0x0546, B:312:0x054c, B:314:0x0554, B:317:0x0563, B:320:0x056b, B:322:0x056f, B:323:0x0576, B:325:0x057b, B:326:0x057e, B:328:0x0586, B:331:0x0590, B:334:0x059a, B:335:0x059f, B:336:0x05a4, B:337:0x05be, B:303:0x052d, B:338:0x05bf, B:340:0x05d1, B:343:0x05d8, B:346:0x05e2, B:347:0x0602, B:39:0x00b4, B:40:0x00d2, B:43:0x00d7, B:45:0x00e2, B:47:0x00e6, B:49:0x00ea, B:52:0x00f0, B:59:0x00ff, B:61:0x0107, B:64:0x0118, B:65:0x0130, B:66:0x0131, B:67:0x0136, B:78:0x014b, B:79:0x0151, B:81:0x0158, B:83:0x0161, B:85:0x0169, B:86:0x016d, B:89:0x0174, B:90:0x018c, B:82:0x015d, B:91:0x018d, B:92:0x01a5, B:98:0x01af, B:100:0x01b7, B:103:0x01c8, B:104:0x01e8, B:105:0x01e9, B:106:0x01ee, B:107:0x01ef, B:348:0x0603, B:349:0x0608, B:350:0x0609, B:351:0x060e), top: B:358:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x056f A[Catch: all -> 0x060f, TryCatch #2 {all -> 0x060f, blocks: (B:24:0x006a, B:26:0x006e, B:29:0x0078, B:32:0x008b, B:36:0x00a3, B:109:0x01f9, B:110:0x01ff, B:112:0x020a, B:114:0x0212, B:118:0x0226, B:120:0x0234, B:123:0x0248, B:124:0x024f, B:126:0x025c, B:127:0x025f, B:129:0x0269, B:130:0x0277, B:132:0x027d, B:134:0x028b, B:136:0x0293, B:140:0x02a1, B:141:0x02a7, B:143:0x02af, B:144:0x02b4, B:148:0x02bd, B:149:0x02c4, B:150:0x02c5, B:153:0x02cf, B:155:0x02d3, B:157:0x02d9, B:158:0x02dc, B:160:0x02e2, B:163:0x02ef, B:169:0x0309, B:173:0x0316, B:170:0x030e, B:172:0x0312, B:121:0x023a, B:180:0x0325, B:182:0x032d, B:184:0x0337, B:186:0x0348, B:187:0x034d, B:189:0x0355, B:191:0x0359, B:193:0x035f, B:196:0x0364, B:198:0x0368, B:217:0x03b2, B:219:0x03ba, B:222:0x03c3, B:223:0x03dd, B:200:0x036d, B:202:0x0375, B:205:0x037b, B:206:0x0387, B:209:0x0390, B:212:0x0396, B:215:0x039b, B:216:0x03a7, B:224:0x03de, B:225:0x03fc, B:227:0x03ff, B:229:0x0403, B:231:0x0407, B:234:0x040d, B:238:0x0415, B:244:0x0425, B:246:0x0434, B:248:0x043f, B:249:0x0447, B:250:0x044a, B:262:0x0476, B:264:0x0481, B:267:0x048a, B:270:0x049a, B:271:0x04ba, B:257:0x045a, B:259:0x0464, B:261:0x0473, B:260:0x0469, B:274:0x04bf, B:276:0x04c9, B:278:0x04ce, B:279:0x04d1, B:281:0x04dc, B:282:0x04e0, B:284:0x04eb, B:287:0x04f2, B:290:0x04fc, B:291:0x0501, B:294:0x0506, B:296:0x050b, B:300:0x0514, B:302:0x051c, B:305:0x053a, B:307:0x0540, B:310:0x0546, B:312:0x054c, B:314:0x0554, B:317:0x0563, B:320:0x056b, B:322:0x056f, B:323:0x0576, B:325:0x057b, B:326:0x057e, B:328:0x0586, B:331:0x0590, B:334:0x059a, B:335:0x059f, B:336:0x05a4, B:337:0x05be, B:303:0x052d, B:338:0x05bf, B:340:0x05d1, B:343:0x05d8, B:346:0x05e2, B:347:0x0602, B:39:0x00b4, B:40:0x00d2, B:43:0x00d7, B:45:0x00e2, B:47:0x00e6, B:49:0x00ea, B:52:0x00f0, B:59:0x00ff, B:61:0x0107, B:64:0x0118, B:65:0x0130, B:66:0x0131, B:67:0x0136, B:78:0x014b, B:79:0x0151, B:81:0x0158, B:83:0x0161, B:85:0x0169, B:86:0x016d, B:89:0x0174, B:90:0x018c, B:82:0x015d, B:91:0x018d, B:92:0x01a5, B:98:0x01af, B:100:0x01b7, B:103:0x01c8, B:104:0x01e8, B:105:0x01e9, B:106:0x01ee, B:107:0x01ef, B:348:0x0603, B:349:0x0608, B:350:0x0609, B:351:0x060e), top: B:358:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:325:0x057b A[Catch: all -> 0x060f, TryCatch #2 {all -> 0x060f, blocks: (B:24:0x006a, B:26:0x006e, B:29:0x0078, B:32:0x008b, B:36:0x00a3, B:109:0x01f9, B:110:0x01ff, B:112:0x020a, B:114:0x0212, B:118:0x0226, B:120:0x0234, B:123:0x0248, B:124:0x024f, B:126:0x025c, B:127:0x025f, B:129:0x0269, B:130:0x0277, B:132:0x027d, B:134:0x028b, B:136:0x0293, B:140:0x02a1, B:141:0x02a7, B:143:0x02af, B:144:0x02b4, B:148:0x02bd, B:149:0x02c4, B:150:0x02c5, B:153:0x02cf, B:155:0x02d3, B:157:0x02d9, B:158:0x02dc, B:160:0x02e2, B:163:0x02ef, B:169:0x0309, B:173:0x0316, B:170:0x030e, B:172:0x0312, B:121:0x023a, B:180:0x0325, B:182:0x032d, B:184:0x0337, B:186:0x0348, B:187:0x034d, B:189:0x0355, B:191:0x0359, B:193:0x035f, B:196:0x0364, B:198:0x0368, B:217:0x03b2, B:219:0x03ba, B:222:0x03c3, B:223:0x03dd, B:200:0x036d, B:202:0x0375, B:205:0x037b, B:206:0x0387, B:209:0x0390, B:212:0x0396, B:215:0x039b, B:216:0x03a7, B:224:0x03de, B:225:0x03fc, B:227:0x03ff, B:229:0x0403, B:231:0x0407, B:234:0x040d, B:238:0x0415, B:244:0x0425, B:246:0x0434, B:248:0x043f, B:249:0x0447, B:250:0x044a, B:262:0x0476, B:264:0x0481, B:267:0x048a, B:270:0x049a, B:271:0x04ba, B:257:0x045a, B:259:0x0464, B:261:0x0473, B:260:0x0469, B:274:0x04bf, B:276:0x04c9, B:278:0x04ce, B:279:0x04d1, B:281:0x04dc, B:282:0x04e0, B:284:0x04eb, B:287:0x04f2, B:290:0x04fc, B:291:0x0501, B:294:0x0506, B:296:0x050b, B:300:0x0514, B:302:0x051c, B:305:0x053a, B:307:0x0540, B:310:0x0546, B:312:0x054c, B:314:0x0554, B:317:0x0563, B:320:0x056b, B:322:0x056f, B:323:0x0576, B:325:0x057b, B:326:0x057e, B:328:0x0586, B:331:0x0590, B:334:0x059a, B:335:0x059f, B:336:0x05a4, B:337:0x05be, B:303:0x052d, B:338:0x05bf, B:340:0x05d1, B:343:0x05d8, B:346:0x05e2, B:347:0x0602, B:39:0x00b4, B:40:0x00d2, B:43:0x00d7, B:45:0x00e2, B:47:0x00e6, B:49:0x00ea, B:52:0x00f0, B:59:0x00ff, B:61:0x0107, B:64:0x0118, B:65:0x0130, B:66:0x0131, B:67:0x0136, B:78:0x014b, B:79:0x0151, B:81:0x0158, B:83:0x0161, B:85:0x0169, B:86:0x016d, B:89:0x0174, B:90:0x018c, B:82:0x015d, B:91:0x018d, B:92:0x01a5, B:98:0x01af, B:100:0x01b7, B:103:0x01c8, B:104:0x01e8, B:105:0x01e9, B:106:0x01ee, B:107:0x01ef, B:348:0x0603, B:349:0x0608, B:350:0x0609, B:351:0x060e), top: B:358:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0590 A[Catch: all -> 0x060f, TRY_ENTER, TryCatch #2 {all -> 0x060f, blocks: (B:24:0x006a, B:26:0x006e, B:29:0x0078, B:32:0x008b, B:36:0x00a3, B:109:0x01f9, B:110:0x01ff, B:112:0x020a, B:114:0x0212, B:118:0x0226, B:120:0x0234, B:123:0x0248, B:124:0x024f, B:126:0x025c, B:127:0x025f, B:129:0x0269, B:130:0x0277, B:132:0x027d, B:134:0x028b, B:136:0x0293, B:140:0x02a1, B:141:0x02a7, B:143:0x02af, B:144:0x02b4, B:148:0x02bd, B:149:0x02c4, B:150:0x02c5, B:153:0x02cf, B:155:0x02d3, B:157:0x02d9, B:158:0x02dc, B:160:0x02e2, B:163:0x02ef, B:169:0x0309, B:173:0x0316, B:170:0x030e, B:172:0x0312, B:121:0x023a, B:180:0x0325, B:182:0x032d, B:184:0x0337, B:186:0x0348, B:187:0x034d, B:189:0x0355, B:191:0x0359, B:193:0x035f, B:196:0x0364, B:198:0x0368, B:217:0x03b2, B:219:0x03ba, B:222:0x03c3, B:223:0x03dd, B:200:0x036d, B:202:0x0375, B:205:0x037b, B:206:0x0387, B:209:0x0390, B:212:0x0396, B:215:0x039b, B:216:0x03a7, B:224:0x03de, B:225:0x03fc, B:227:0x03ff, B:229:0x0403, B:231:0x0407, B:234:0x040d, B:238:0x0415, B:244:0x0425, B:246:0x0434, B:248:0x043f, B:249:0x0447, B:250:0x044a, B:262:0x0476, B:264:0x0481, B:267:0x048a, B:270:0x049a, B:271:0x04ba, B:257:0x045a, B:259:0x0464, B:261:0x0473, B:260:0x0469, B:274:0x04bf, B:276:0x04c9, B:278:0x04ce, B:279:0x04d1, B:281:0x04dc, B:282:0x04e0, B:284:0x04eb, B:287:0x04f2, B:290:0x04fc, B:291:0x0501, B:294:0x0506, B:296:0x050b, B:300:0x0514, B:302:0x051c, B:305:0x053a, B:307:0x0540, B:310:0x0546, B:312:0x054c, B:314:0x0554, B:317:0x0563, B:320:0x056b, B:322:0x056f, B:323:0x0576, B:325:0x057b, B:326:0x057e, B:328:0x0586, B:331:0x0590, B:334:0x059a, B:335:0x059f, B:336:0x05a4, B:337:0x05be, B:303:0x052d, B:338:0x05bf, B:340:0x05d1, B:343:0x05d8, B:346:0x05e2, B:347:0x0602, B:39:0x00b4, B:40:0x00d2, B:43:0x00d7, B:45:0x00e2, B:47:0x00e6, B:49:0x00ea, B:52:0x00f0, B:59:0x00ff, B:61:0x0107, B:64:0x0118, B:65:0x0130, B:66:0x0131, B:67:0x0136, B:78:0x014b, B:79:0x0151, B:81:0x0158, B:83:0x0161, B:85:0x0169, B:86:0x016d, B:89:0x0174, B:90:0x018c, B:82:0x015d, B:91:0x018d, B:92:0x01a5, B:98:0x01af, B:100:0x01b7, B:103:0x01c8, B:104:0x01e8, B:105:0x01e9, B:106:0x01ee, B:107:0x01ef, B:348:0x0603, B:349:0x0608, B:350:0x0609, B:351:0x060e), top: B:358:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0486 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0425 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0586 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object parseObject(java.util.Map r14, java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 1556
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public void popContext() {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = this.context.parent;
        int i7 = this.contextArrayIndex;
        if (i7 <= 0) {
            return;
        }
        int i8 = i7 - 1;
        this.contextArrayIndex = i8;
        this.contextArray[i8] = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0022, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object resolveReference(java.lang.String r5) {
        /*
            r4 = this;
            com.alibaba.fastjson.parser.ParseContext[] r0 = r4.contextArray
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            r0 = 0
        L7:
            com.alibaba.fastjson.parser.ParseContext[] r2 = r4.contextArray
            int r3 = r2.length
            if (r0 >= r3) goto L22
            int r3 = r4.contextArrayIndex
            if (r0 >= r3) goto L22
            r2 = r2[r0]
            java.lang.String r3 = r2.toString()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L1f
            java.lang.Object r5 = r2.object
            return r5
        L1f:
            int r0 = r0 + 1
            goto L7
        L22:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.resolveReference(java.lang.String):java.lang.Object");
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public void setContext(ParseContext parseContext) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = parseContext;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver) {
        this.fieldTypeResolver = fieldTypeResolver;
    }

    public void setResolveStatus(int i7) {
        this.resolveStatus = i7;
    }

    public void throwException(int i7) {
        StringBuilder sbM112a = C0413b.m112a("syntax error, expect ");
        sbM112a.append(JSONToken.name(i7));
        sbM112a.append(", actual ");
        sbM112a.append(JSONToken.name(this.lexer.token()));
        throw new JSONException(sbM112a.toString());
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(str, new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        int i7 = jSONLexer.token();
        if (i7 == 2) {
            Number numberIntegerValue = jSONLexer.integerValue();
            jSONLexer.nextToken();
            return numberIntegerValue;
        }
        if (i7 == 3) {
            Number numberDecimalValue = jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
            jSONLexer.nextToken();
            return numberDecimalValue;
        }
        if (i7 == 4) {
            String strStringVal = jSONLexer.stringVal();
            jSONLexer.nextToken(16);
            if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                JSONScanner jSONScanner = new JSONScanner(strStringVal);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        return jSONScanner.getCalendar().getTime();
                    }
                } finally {
                    jSONScanner.close();
                }
            }
            return strStringVal;
        }
        if (i7 == 12) {
            return parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), obj);
        }
        if (i7 == 14) {
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            return jSONLexer.isEnabled(Feature.UseObjectArray) ? jSONArray.toArray() : jSONArray;
        }
        if (i7 == 18) {
            if ("NaN".equals(jSONLexer.stringVal())) {
                jSONLexer.nextToken();
                return null;
            }
            StringBuilder sbM112a = C0413b.m112a("syntax error, ");
            sbM112a.append(jSONLexer.info());
            throw new JSONException(sbM112a.toString());
        }
        if (i7 == 26) {
            byte[] bArrBytesValue = jSONLexer.bytesValue();
            jSONLexer.nextToken();
            return bArrBytesValue;
        }
        switch (i7) {
            case 6:
                jSONLexer.nextToken();
                return Boolean.TRUE;
            case 7:
                jSONLexer.nextToken();
                return Boolean.FALSE;
            case 8:
                jSONLexer.nextToken();
                return null;
            case 9:
                jSONLexer.nextToken(18);
                if (jSONLexer.token() != 18) {
                    throw new JSONException("syntax error");
                }
                jSONLexer.nextToken(10);
                accept(10);
                long jLongValue = jSONLexer.integerValue().longValue();
                accept(2);
                accept(11);
                return new Date(jLongValue);
            default:
                switch (i7) {
                    case 20:
                        if (jSONLexer.isBlankInput()) {
                            return null;
                        }
                        StringBuilder sbM112a2 = C0413b.m112a("unterminated json string, ");
                        sbM112a2.append(jSONLexer.info());
                        throw new JSONException(sbM112a2.toString());
                    case 21:
                        jSONLexer.nextToken();
                        HashSet hashSet = new HashSet();
                        parseArray(hashSet, obj);
                        return hashSet;
                    case 22:
                        jSONLexer.nextToken();
                        TreeSet treeSet = new TreeSet();
                        parseArray(treeSet, obj);
                        return treeSet;
                    case 23:
                        jSONLexer.nextToken();
                        return null;
                    default:
                        StringBuilder sbM112a3 = C0413b.m112a("syntax error, ");
                        sbM112a3.append(jSONLexer.info());
                        throw new JSONException(sbM112a3.toString());
                }
        }
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i7) {
        this(str, new JSONScanner(str, i7), parserConfig);
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public DefaultJSONParser(char[] cArr, int i7, ParserConfig parserConfig, int i8) {
        this(cArr, new JSONScanner(cArr, i7, i8), parserConfig);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer deserializer;
        int i7 = this.lexer.token();
        if (i7 == 21 || i7 == 22) {
            this.lexer.nextToken();
            i7 = this.lexer.token();
        }
        if (i7 == 14) {
            if (Integer.TYPE != type) {
                if (String.class == type) {
                    deserializer = StringCodec.instance;
                    this.lexer.nextToken(4);
                } else {
                    deserializer = this.config.getDeserializer(type);
                    this.lexer.nextToken(deserializer.getFastMatchToken());
                }
            } else {
                deserializer = IntegerCodec.instance;
                this.lexer.nextToken(2);
            }
            ParseContext parseContext = this.context;
            setContext(collection, obj);
            int i8 = 0;
            while (true) {
                try {
                    if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                        while (this.lexer.token() == 16) {
                            this.lexer.nextToken();
                        }
                    }
                    if (this.lexer.token() == 15) {
                        setContext(parseContext);
                        this.lexer.nextToken(16);
                        return;
                    }
                    Object objDeserialze = null;
                    if (Integer.TYPE != type) {
                        if (String.class == type) {
                            if (this.lexer.token() == 4) {
                                objDeserialze = this.lexer.stringVal();
                                this.lexer.nextToken(16);
                            } else {
                                Object obj2 = parse();
                                if (obj2 != null) {
                                    objDeserialze = obj2.toString();
                                }
                            }
                            collection.add(objDeserialze);
                        } else {
                            if (this.lexer.token() == 8) {
                                this.lexer.nextToken();
                            } else {
                                objDeserialze = deserializer.deserialze(this, type, Integer.valueOf(i8));
                            }
                            collection.add(objDeserialze);
                            checkListResolve(collection);
                        }
                    } else {
                        collection.add(IntegerCodec.instance.deserialze(this, null, null));
                    }
                    if (this.lexer.token() == 16) {
                        this.lexer.nextToken(deserializer.getFastMatchToken());
                    }
                    i8++;
                } catch (Throwable th) {
                    setContext(parseContext);
                    throw th;
                }
            }
        } else {
            StringBuilder sbM112a = C0413b.m112a("exepct '[', but ");
            sbM112a.append(JSONToken.name(i7));
            sbM112a.append(", ");
            sbM112a.append(this.lexer.info());
            throw new JSONException(sbM112a.toString());
        }
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        ParseContext parseContext2 = new ParseContext(parseContext, obj, obj2);
        this.context = parseContext2;
        addContext(parseContext2);
        return this.context;
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this((Object) null, jSONLexer, parserConfig);
    }

    public final void accept(int i7, int i8) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i7) {
            jSONLexer.nextToken(i8);
        } else {
            throwException(i7);
        }
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.autoTypeAccept = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char current = jSONLexer.getCurrent();
        if (current == '{') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 12;
        } else if (current == '[') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object objCast;
        boolean zIsArray;
        Class<?> componentType;
        int i7 = 8;
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        }
        int i8 = 14;
        if (this.lexer.token() == 14) {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                this.lexer.nextToken(15);
                if (this.lexer.token() == 15) {
                    this.lexer.nextToken(16);
                    return new Object[0];
                }
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(2);
            int i9 = 0;
            while (i9 < typeArr.length) {
                if (this.lexer.token() == i7) {
                    this.lexer.nextToken(16);
                    objCast = null;
                } else {
                    Type type = typeArr[i9];
                    if (type != Integer.TYPE && type != Integer.class) {
                        if (type == String.class) {
                            if (this.lexer.token() == 4) {
                                objCast = this.lexer.stringVal();
                                this.lexer.nextToken(16);
                            } else {
                                objCast = TypeUtils.cast(parse(), type, this.config);
                            }
                        } else {
                            if (i9 == typeArr.length - 1 && (type instanceof Class)) {
                                Class cls = (Class) type;
                                zIsArray = cls.isArray();
                                componentType = cls.getComponentType();
                            } else {
                                zIsArray = false;
                                componentType = null;
                            }
                            if (zIsArray && this.lexer.token() != i8) {
                                ArrayList arrayList = new ArrayList();
                                ObjectDeserializer deserializer = this.config.getDeserializer(componentType);
                                int fastMatchToken = deserializer.getFastMatchToken();
                                if (this.lexer.token() != 15) {
                                    while (true) {
                                        arrayList.add(deserializer.deserialze(this, type, null));
                                        if (this.lexer.token() != 16) {
                                            break;
                                        }
                                        this.lexer.nextToken(fastMatchToken);
                                    }
                                    if (this.lexer.token() != 15) {
                                        StringBuilder sbM112a = C0413b.m112a("syntax error :");
                                        sbM112a.append(JSONToken.name(this.lexer.token()));
                                        throw new JSONException(sbM112a.toString());
                                    }
                                }
                                objCast = TypeUtils.cast(arrayList, type, this.config);
                            } else {
                                objCast = this.config.getDeserializer(type).deserialze(this, type, Integer.valueOf(i9));
                            }
                        }
                    } else if (this.lexer.token() == 2) {
                        objCast = Integer.valueOf(this.lexer.intValue());
                        this.lexer.nextToken(16);
                    } else {
                        objCast = TypeUtils.cast(parse(), type, this.config);
                    }
                }
                objArr[i9] = objCast;
                if (this.lexer.token() == 15) {
                    break;
                }
                if (this.lexer.token() == 16) {
                    if (i9 == typeArr.length - 1) {
                        this.lexer.nextToken(15);
                    } else {
                        this.lexer.nextToken(2);
                    }
                    i9++;
                    i7 = 8;
                    i8 = 14;
                } else {
                    StringBuilder sbM112a2 = C0413b.m112a("syntax error :");
                    sbM112a2.append(JSONToken.name(this.lexer.token()));
                    throw new JSONException(sbM112a2.toString());
                }
            }
            if (this.lexer.token() == 15) {
                this.lexer.nextToken(16);
                return objArr;
            }
            throw new JSONException("syntax error");
        }
        StringBuilder sbM112a3 = C0413b.m112a("syntax error : ");
        sbM112a3.append(this.lexer.tokenName());
        throw new JSONException(sbM112a3.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x020d, code lost:
    
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object parse(com.alibaba.fastjson.parser.deserializer.PropertyProcessable r11, java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 572
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parse(com.alibaba.fastjson.parser.deserializer.PropertyProcessable, java.lang.Object):java.lang.Object");
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    public final void parseArray(Collection collection, Object obj) {
        Number numberDecimalValue;
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == 21 || jSONLexer.token() == 22) {
            jSONLexer.nextToken();
        }
        if (jSONLexer.token() == 14) {
            jSONLexer.nextToken(4);
            ParseContext parseContext = this.context;
            setContext(collection, obj);
            int i7 = 0;
            while (true) {
                try {
                    if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                        while (jSONLexer.token() == 16) {
                            jSONLexer.nextToken();
                        }
                    }
                    int i8 = jSONLexer.token();
                    Object object = null;
                    object = null;
                    if (i8 == 2) {
                        Number numberIntegerValue = jSONLexer.integerValue();
                        jSONLexer.nextToken(16);
                        object = numberIntegerValue;
                    } else if (i8 == 3) {
                        if (jSONLexer.isEnabled(Feature.UseBigDecimal)) {
                            numberDecimalValue = jSONLexer.decimalValue(true);
                        } else {
                            numberDecimalValue = jSONLexer.decimalValue(false);
                        }
                        object = numberDecimalValue;
                        jSONLexer.nextToken(16);
                    } else if (i8 == 4) {
                        String strStringVal = jSONLexer.stringVal();
                        jSONLexer.nextToken(16);
                        object = strStringVal;
                        if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                            JSONScanner jSONScanner = new JSONScanner(strStringVal);
                            Object time = strStringVal;
                            if (jSONScanner.scanISO8601DateIfMatch()) {
                                time = jSONScanner.getCalendar().getTime();
                            }
                            jSONScanner.close();
                            object = time;
                        }
                    } else if (i8 == 6) {
                        Boolean bool = Boolean.TRUE;
                        jSONLexer.nextToken(16);
                        object = bool;
                    } else if (i8 == 7) {
                        Boolean bool2 = Boolean.FALSE;
                        jSONLexer.nextToken(16);
                        object = bool2;
                    } else if (i8 == 8) {
                        jSONLexer.nextToken(4);
                    } else if (i8 == 12) {
                        object = parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), Integer.valueOf(i7));
                    } else {
                        if (i8 == 20) {
                            throw new JSONException("unclosed jsonArray");
                        }
                        if (i8 == 23) {
                            jSONLexer.nextToken(4);
                        } else if (i8 == 14) {
                            JSONArray jSONArray = new JSONArray();
                            parseArray(jSONArray, Integer.valueOf(i7));
                            object = jSONArray;
                            if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                                object = jSONArray.toArray();
                            }
                        } else if (i8 != 15) {
                            object = parse();
                        } else {
                            jSONLexer.nextToken(16);
                            return;
                        }
                    }
                    collection.add(object);
                    checkListResolve(collection);
                    if (jSONLexer.token() == 16) {
                        jSONLexer.nextToken(4);
                    }
                    i7++;
                } finally {
                    setContext(parseContext);
                }
            }
        } else {
            StringBuilder sbM112a = C0413b.m112a("syntax error, expect [, actual ");
            sbM112a.append(JSONToken.name(jSONLexer.token()));
            sbM112a.append(", pos ");
            sbM112a.append(jSONLexer.pos());
            sbM112a.append(", fieldName ");
            sbM112a.append(obj);
            throw new JSONException(sbM112a.toString());
        }
    }

    public <T> T parseObject(Class<T> cls) {
        return (T) parseObject(cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return (T) parseObject(type, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T parseObject(Type type, Object obj) {
        int i7 = this.lexer.token();
        if (i7 == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (i7 == 4) {
            if (type == byte[].class) {
                T t6 = (T) this.lexer.bytesValue();
                this.lexer.nextToken();
                return t6;
            }
            if (type == char[].class) {
                String strStringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return (T) strStringVal.toCharArray();
            }
        }
        ObjectDeserializer deserializer = this.config.getDeserializer(type);
        try {
            if (deserializer.getClass() == JavaBeanDeserializer.class) {
                return (T) ((JavaBeanDeserializer) deserializer).deserialze(this, type, obj, 0);
            }
            return (T) deserializer.deserialze(this, type, obj);
        } catch (JSONException e7) {
            throw e7;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public void parseObject(Object obj) {
        Object objDeserialze;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (this.lexer.token() != 12 && this.lexer.token() != 16) {
            StringBuilder sbM112a = C0413b.m112a("syntax error, expect {, actual ");
            sbM112a.append(this.lexer.tokenName());
            throw new JSONException(sbM112a.toString());
        }
        while (true) {
            String strScanSymbol = this.lexer.scanSymbol(this.symbolTable);
            if (strScanSymbol == null) {
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                } else if (this.lexer.token() != 16 || !this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                }
            }
            FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(strScanSymbol) : null;
            if (fieldDeserializer == null) {
                if (this.lexer.isEnabled(Feature.IgnoreNotMatch)) {
                    this.lexer.nextTokenWithColon();
                    parse();
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken();
                        return;
                    }
                } else {
                    StringBuilder sbM112a2 = C0413b.m112a("setter not found, class ");
                    sbM112a2.append(cls.getName());
                    sbM112a2.append(", property ");
                    sbM112a2.append(strScanSymbol);
                    throw new JSONException(sbM112a2.toString());
                }
            } else {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                Class<?> cls2 = fieldInfo.fieldClass;
                Type type = fieldInfo.fieldType;
                if (cls2 == Integer.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else if (cls2 == String.class) {
                    this.lexer.nextTokenWithColon(4);
                    objDeserialze = StringCodec.deserialze(this);
                } else if (cls2 == Long.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = LongCodec.instance.deserialze(this, type, null);
                } else {
                    ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                    this.lexer.nextTokenWithColon(deserializer2.getFastMatchToken());
                    objDeserialze = deserializer2.deserialze(this, type, null);
                }
                fieldDeserializer.setValue(obj, objDeserialze);
                if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                }
            }
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((Map) new JSONObject(this.lexer.isEnabled(Feature.OrderedField)));
    }
}
