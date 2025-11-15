package com.alibaba.fastjson;

import android.support.constraint.motion.C0079a;
import android.support.v4.app.C0164a;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.FieldSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class JSONPath implements JSONAware {
    public static final long LENGTH = -1580386065683472715L;
    public static final long SIZE = 5614464919154503228L;
    private static ConcurrentMap<String, JSONPath> pathCache = new ConcurrentHashMap(128, 0.75f, 1);
    private ParserConfig parserConfig;
    private final String path;
    private Segement[] segments;
    private SerializeConfig serializeConfig;

    /* renamed from: com.alibaba.fastjson.JSONPath$1 */
    public static /* synthetic */ class C05281 {
        public static final /* synthetic */ int[] $SwitchMap$com$alibaba$fastjson$JSONPath$Operator;

        static {
            int[] iArr = new int[Operator.values().length];
            $SwitchMap$com$alibaba$fastjson$JSONPath$Operator = iArr;
            try {
                iArr[Operator.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$JSONPath$Operator[Operator.NE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$JSONPath$Operator[Operator.GE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$JSONPath$Operator[Operator.GT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$JSONPath$Operator[Operator.LE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$JSONPath$Operator[Operator.LT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static class ArrayAccessSegement implements Segement {
        private final int index;

        public ArrayAccessSegement(int i7) {
            this.index = i7;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getArrayItem(obj2, this.index);
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            if (((JSONLexerBase) defaultJSONParser.lexer).seekArrayToItem(this.index) && context.eval) {
                context.object = defaultJSONParser.parse();
            }
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removeArrayItem(jSONPath, obj, this.index);
        }

        public boolean setValue(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.setArrayItem(jSONPath, obj, this.index, obj2);
        }
    }

    public static class Context {
        public final boolean eval;
        public Object object;
        public final Context parent;

        public Context(Context context, boolean z6) {
            this.parent = context;
            this.eval = z6;
        }
    }

    public static class DoubleOpSegement implements Filter {

        /* renamed from: op */
        private final Operator f402op;
        private final String propertyName;
        private final long propertyNameHash;
        private final double value;

        public DoubleOpSegement(String str, double d7, Operator operator) {
            this.propertyName = str;
            this.value = d7;
            this.f402op = operator;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            double dDoubleValue = ((Number) propertyValue).doubleValue();
            switch (C05281.$SwitchMap$com$alibaba$fastjson$JSONPath$Operator[this.f402op.ordinal()]) {
                case 1:
                    if (dDoubleValue == this.value) {
                    }
                    break;
                case 2:
                    if (dDoubleValue != this.value) {
                    }
                    break;
                case 3:
                    if (dDoubleValue >= this.value) {
                    }
                    break;
                case 4:
                    if (dDoubleValue > this.value) {
                    }
                    break;
                case 5:
                    if (dDoubleValue <= this.value) {
                    }
                    break;
                case 6:
                    if (dDoubleValue < this.value) {
                    }
                    break;
            }
            return false;
        }
    }

    public interface Filter {
        boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3);
    }

    public static class FilterGroup implements Filter {
        private boolean and;
        private List<Filter> fitlers;

        public FilterGroup(Filter filter, Filter filter2, boolean z6) {
            ArrayList arrayList = new ArrayList(2);
            this.fitlers = arrayList;
            arrayList.add(filter);
            this.fitlers.add(filter2);
            this.and = z6;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            if (this.and) {
                Iterator<Filter> it = this.fitlers.iterator();
                while (it.hasNext()) {
                    if (!it.next().apply(jSONPath, obj, obj2, obj3)) {
                        return false;
                    }
                }
                return true;
            }
            Iterator<Filter> it2 = this.fitlers.iterator();
            while (it2.hasNext()) {
                if (it2.next().apply(jSONPath, obj, obj2, obj3)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class FilterSegement implements Segement {
        private final Filter filter;

        public FilterSegement(Filter filter) {
            this.filter = filter;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj2 == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            if (!(obj2 instanceof Iterable)) {
                if (this.filter.apply(jSONPath, obj, obj2, obj2)) {
                    return obj2;
                }
                return null;
            }
            for (Object obj3 : (Iterable) obj2) {
                if (this.filter.apply(jSONPath, obj, obj2, obj3)) {
                    jSONArray.add(obj3);
                }
            }
            return jSONArray;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    public static class IntBetweenSegement implements Filter {
        private final long endValue;
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final long startValue;

        public IntBetweenSegement(String str, long j7, long j8, boolean z6) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.startValue = j7;
            this.endValue = j8;
            this.not = z6;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long jLongExtractValue = TypeUtils.longExtractValue((Number) propertyValue);
                if (jLongExtractValue >= this.startValue && jLongExtractValue <= this.endValue) {
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    public static class IntInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final long[] values;

        public IntInSegement(String str, long[] jArr, boolean z6) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = jArr;
            this.not = z6;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long jLongExtractValue = TypeUtils.longExtractValue((Number) propertyValue);
                for (long j7 : this.values) {
                    if (j7 == jLongExtractValue) {
                        return !this.not;
                    }
                }
            }
            return this.not;
        }
    }

    public static class IntObjInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final Long[] values;

        public IntObjInSegement(String str, Long[] lArr, boolean z6) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = lArr;
            this.not = z6;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            int i7 = 0;
            if (propertyValue == null) {
                Long[] lArr = this.values;
                int length = lArr.length;
                while (i7 < length) {
                    if (lArr[i7] == null) {
                        return !this.not;
                    }
                    i7++;
                }
                return this.not;
            }
            if (propertyValue instanceof Number) {
                long jLongExtractValue = TypeUtils.longExtractValue((Number) propertyValue);
                Long[] lArr2 = this.values;
                int length2 = lArr2.length;
                while (i7 < length2) {
                    Long l7 = lArr2[i7];
                    if (l7 != null && l7.longValue() == jLongExtractValue) {
                        return !this.not;
                    }
                    i7++;
                }
            }
            return this.not;
        }
    }

    public static class IntOpSegement implements Filter {

        /* renamed from: op */
        private final Operator f403op;
        private final String propertyName;
        private final long propertyNameHash;
        private final long value;
        private BigDecimal valueDecimal;
        private Double valueDouble;
        private Float valueFloat;

        public IntOpSegement(String str, long j7, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = j7;
            this.f403op = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            if (propertyValue instanceof BigDecimal) {
                if (this.valueDecimal == null) {
                    this.valueDecimal = BigDecimal.valueOf(this.value);
                }
                int iCompareTo = this.valueDecimal.compareTo((BigDecimal) propertyValue);
                switch (C05281.$SwitchMap$com$alibaba$fastjson$JSONPath$Operator[this.f403op.ordinal()]) {
                    case 1:
                        if (iCompareTo == 0) {
                        }
                        break;
                    case 2:
                        if (iCompareTo != 0) {
                        }
                        break;
                    case 3:
                        if (iCompareTo <= 0) {
                        }
                        break;
                    case 4:
                        if (iCompareTo < 0) {
                        }
                        break;
                    case 5:
                        if (iCompareTo >= 0) {
                        }
                        break;
                    case 6:
                        if (iCompareTo > 0) {
                        }
                        break;
                }
                return false;
            }
            if (propertyValue instanceof Float) {
                if (this.valueFloat == null) {
                    this.valueFloat = Float.valueOf(this.value);
                }
                int iCompareTo2 = this.valueFloat.compareTo((Float) propertyValue);
                switch (C05281.$SwitchMap$com$alibaba$fastjson$JSONPath$Operator[this.f403op.ordinal()]) {
                    case 1:
                        if (iCompareTo2 == 0) {
                        }
                        break;
                    case 2:
                        if (iCompareTo2 != 0) {
                        }
                        break;
                    case 3:
                        if (iCompareTo2 <= 0) {
                        }
                        break;
                    case 4:
                        if (iCompareTo2 < 0) {
                        }
                        break;
                    case 5:
                        if (iCompareTo2 >= 0) {
                        }
                        break;
                    case 6:
                        if (iCompareTo2 > 0) {
                        }
                        break;
                }
                return false;
            }
            if (!(propertyValue instanceof Double)) {
                long jLongExtractValue = TypeUtils.longExtractValue((Number) propertyValue);
                switch (C05281.$SwitchMap$com$alibaba$fastjson$JSONPath$Operator[this.f403op.ordinal()]) {
                    case 1:
                        if (jLongExtractValue == this.value) {
                        }
                        break;
                    case 2:
                        if (jLongExtractValue != this.value) {
                        }
                        break;
                    case 3:
                        if (jLongExtractValue >= this.value) {
                        }
                        break;
                    case 4:
                        if (jLongExtractValue > this.value) {
                        }
                        break;
                    case 5:
                        if (jLongExtractValue <= this.value) {
                        }
                        break;
                    case 6:
                        if (jLongExtractValue < this.value) {
                        }
                        break;
                }
                return false;
            }
            if (this.valueDouble == null) {
                this.valueDouble = Double.valueOf(this.value);
            }
            int iCompareTo3 = this.valueDouble.compareTo((Double) propertyValue);
            switch (C05281.$SwitchMap$com$alibaba$fastjson$JSONPath$Operator[this.f403op.ordinal()]) {
                case 1:
                    if (iCompareTo3 == 0) {
                    }
                    break;
                case 2:
                    if (iCompareTo3 != 0) {
                    }
                    break;
                case 3:
                    if (iCompareTo3 <= 0) {
                    }
                    break;
                case 4:
                    if (iCompareTo3 < 0) {
                    }
                    break;
                case 5:
                    if (iCompareTo3 >= 0) {
                    }
                    break;
                case 6:
                    if (iCompareTo3 > 0) {
                    }
                    break;
            }
            return false;
        }
    }

    public static class JSONPathParser {

        /* renamed from: ch */
        private char f404ch;
        private int level;
        private final String path;
        private int pos;

        public JSONPathParser(String str) {
            this.path = str;
            next();
        }

        public static boolean isDigitFirst(char c7) {
            return c7 == '-' || c7 == '+' || (c7 >= '0' && c7 <= '9');
        }

        public void accept(char c7) {
            if (this.f404ch == c7) {
                if (isEOF()) {
                    return;
                }
                next();
            } else {
                throw new JSONPathException("expect '" + c7 + ", but '" + this.f404ch + "'");
            }
        }

        public Segement buildArraySegement(String str) {
            int length = str.length();
            int i7 = 0;
            char cCharAt = str.charAt(0);
            int i8 = length - 1;
            char cCharAt2 = str.charAt(i8);
            int iIndexOf = str.indexOf(44);
            if (str.length() > 2 && cCharAt == '\'' && cCharAt2 == '\'') {
                if (iIndexOf == -1) {
                    return new PropertySegement(str.substring(1, i8), false);
                }
                String[] strArrSplit = str.split(",");
                String[] strArr = new String[strArrSplit.length];
                while (i7 < strArrSplit.length) {
                    String str2 = strArrSplit[i7];
                    strArr[i7] = str2.substring(1, str2.length() - 1);
                    i7++;
                }
                return new MultiPropertySegement(strArr);
            }
            int iIndexOf2 = str.indexOf(58);
            if (iIndexOf == -1 && iIndexOf2 == -1) {
                if (!TypeUtils.isNumber(str)) {
                    return new PropertySegement(str, false);
                }
                try {
                    return new ArrayAccessSegement(Integer.parseInt(str));
                } catch (NumberFormatException unused) {
                    return new PropertySegement(str, false);
                }
            }
            if (iIndexOf != -1) {
                String[] strArrSplit2 = str.split(",");
                int[] iArr = new int[strArrSplit2.length];
                while (i7 < strArrSplit2.length) {
                    iArr[i7] = Integer.parseInt(strArrSplit2[i7]);
                    i7++;
                }
                return new MultiIndexSegement(iArr);
            }
            if (iIndexOf2 == -1) {
                throw new UnsupportedOperationException();
            }
            String[] strArrSplit3 = str.split(":");
            int length2 = strArrSplit3.length;
            int[] iArr2 = new int[length2];
            for (int i9 = 0; i9 < strArrSplit3.length; i9++) {
                String str3 = strArrSplit3[i9];
                if (str3.length() != 0) {
                    iArr2[i9] = Integer.parseInt(str3);
                } else {
                    if (i9 != 0) {
                        throw new UnsupportedOperationException();
                    }
                    iArr2[i9] = 0;
                }
            }
            int i10 = iArr2[0];
            int i11 = length2 > 1 ? iArr2[1] : -1;
            int i12 = length2 == 3 ? iArr2[2] : 1;
            if (i11 < 0 || i11 >= i10) {
                if (i12 > 0) {
                    return new RangeSegement(i10, i11, i12);
                }
                throw new UnsupportedOperationException(C0079a.m93a("step must greater than zero : ", i12));
            }
            throw new UnsupportedOperationException("end must greater than or equals start. start " + i10 + ",  end " + i11);
        }

        public Segement[] explain() {
            String str = this.path;
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException();
            }
            Segement[] segementArr = new Segement[8];
            while (true) {
                Segement segement = readSegement();
                if (segement == null) {
                    break;
                }
                int i7 = this.level;
                if (i7 == segementArr.length) {
                    Segement[] segementArr2 = new Segement[(i7 * 3) / 2];
                    System.arraycopy(segementArr, 0, segementArr2, 0, i7);
                    segementArr = segementArr2;
                }
                int i8 = this.level;
                this.level = i8 + 1;
                segementArr[i8] = segement;
            }
            int i9 = this.level;
            if (i9 == segementArr.length) {
                return segementArr;
            }
            Segement[] segementArr3 = new Segement[i9];
            System.arraycopy(segementArr, 0, segementArr3, 0, i9);
            return segementArr3;
        }

        public Filter filterRest(Filter filter) {
            char c7 = this.f404ch;
            boolean z6 = c7 == '&';
            if ((c7 != '&' || getNextChar() != '&') && (this.f404ch != '|' || getNextChar() != '|')) {
                return filter;
            }
            next();
            next();
            while (this.f404ch == ' ') {
                next();
            }
            return new FilterGroup(filter, (Filter) parseArrayAccessFilter(false), z6);
        }

        public char getNextChar() {
            return this.path.charAt(this.pos);
        }

        public boolean isEOF() {
            return this.pos >= this.path.length();
        }

        public void next() {
            String str = this.path;
            int i7 = this.pos;
            this.pos = i7 + 1;
            this.f404ch = str.charAt(i7);
        }

        public Segement parseArrayAccess(boolean z6) {
            Object arrayAccessFilter = parseArrayAccessFilter(z6);
            return arrayAccessFilter instanceof Segement ? (Segement) arrayAccessFilter : new FilterSegement((Filter) arrayAccessFilter);
        }

        /* JADX WARN: Removed duplicated region for block: B:507:0x0085  */
        /* JADX WARN: Removed duplicated region for block: B:514:0x00a6  */
        /* JADX WARN: Removed duplicated region for block: B:718:0x0334 A[LOOP:7: B:716:0x0330->B:718:0x0334, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:721:0x033c  */
        /* JADX WARN: Removed duplicated region for block: B:723:0x0342  */
        /* JADX WARN: Removed duplicated region for block: B:725:0x0347  */
        /* JADX WARN: Removed duplicated region for block: B:902:0x0338 A[EDGE_INSN: B:902:0x0338->B:719:0x0338 BREAK  A[LOOP:7: B:716:0x0330->B:718:0x0334], SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object parseArrayAccessFilter(boolean r18) {
            /*
                Method dump skipped, instructions count: 1422
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.JSONPath.JSONPathParser.parseArrayAccessFilter(boolean):java.lang.Object");
        }

        public double readDoubleValue(long j7) {
            int i7 = this.pos - 1;
            next();
            while (true) {
                char c7 = this.f404ch;
                if (c7 < '0' || c7 > '9') {
                    break;
                }
                next();
            }
            return Double.parseDouble(this.path.substring(i7, this.pos - 1)) + j7;
        }

        public long readLongValue() {
            int i7 = this.pos - 1;
            char c7 = this.f404ch;
            if (c7 == '+' || c7 == '-') {
                next();
            }
            while (true) {
                char c8 = this.f404ch;
                if (c8 < '0' || c8 > '9') {
                    break;
                }
                next();
            }
            return Long.parseLong(this.path.substring(i7, this.pos - 1));
        }

        public String readName() {
            skipWhitespace();
            char c7 = this.f404ch;
            if (c7 != '\\' && !Character.isJavaIdentifierStart(c7)) {
                StringBuilder sbM112a = C0413b.m112a("illeal jsonpath syntax. ");
                sbM112a.append(this.path);
                throw new JSONPathException(sbM112a.toString());
            }
            StringBuilder sb = new StringBuilder();
            while (!isEOF()) {
                char c8 = this.f404ch;
                if (c8 == '\\') {
                    next();
                    sb.append(this.f404ch);
                    if (isEOF()) {
                        return sb.toString();
                    }
                    next();
                } else {
                    if (!Character.isJavaIdentifierPart(c8)) {
                        break;
                    }
                    sb.append(this.f404ch);
                    next();
                }
            }
            if (isEOF() && Character.isJavaIdentifierPart(this.f404ch)) {
                sb.append(this.f404ch);
            }
            return sb.toString();
        }

        public Operator readOp() {
            Operator operator;
            char c7 = this.f404ch;
            if (c7 == '=') {
                next();
                operator = Operator.EQ;
            } else if (c7 == '!') {
                next();
                accept('=');
                operator = Operator.NE;
            } else if (c7 == '<') {
                next();
                if (this.f404ch == '=') {
                    next();
                    operator = Operator.LE;
                } else {
                    operator = Operator.LT;
                }
            } else if (c7 == '>') {
                next();
                if (this.f404ch == '=') {
                    next();
                    operator = Operator.GE;
                } else {
                    operator = Operator.GT;
                }
            } else {
                operator = null;
            }
            if (operator != null) {
                return operator;
            }
            String name = readName();
            if (!"not".equalsIgnoreCase(name)) {
                if ("like".equalsIgnoreCase(name)) {
                    return Operator.LIKE;
                }
                if ("rlike".equalsIgnoreCase(name)) {
                    return Operator.RLIKE;
                }
                if ("in".equalsIgnoreCase(name)) {
                    return Operator.IN;
                }
                if ("between".equalsIgnoreCase(name)) {
                    return Operator.BETWEEN;
                }
                throw new UnsupportedOperationException();
            }
            skipWhitespace();
            String name2 = readName();
            if ("like".equalsIgnoreCase(name2)) {
                return Operator.NOT_LIKE;
            }
            if ("rlike".equalsIgnoreCase(name2)) {
                return Operator.NOT_RLIKE;
            }
            if ("in".equalsIgnoreCase(name2)) {
                return Operator.NOT_IN;
            }
            if ("between".equalsIgnoreCase(name2)) {
                return Operator.NOT_BETWEEN;
            }
            throw new UnsupportedOperationException();
        }

        public Segement readSegement() {
            boolean z6 = true;
            if (this.level == 0 && this.path.length() == 1) {
                if (isDigitFirst(this.f404ch)) {
                    return new ArrayAccessSegement(this.f404ch - '0');
                }
                char c7 = this.f404ch;
                if ((c7 >= 'a' && c7 <= 'z') || (c7 >= 'A' && c7 <= 'Z')) {
                    return new PropertySegement(Character.toString(c7), false);
                }
            }
            while (!isEOF()) {
                skipWhitespace();
                char c8 = this.f404ch;
                if (c8 != '$') {
                    if (c8 != '.' && c8 != '/') {
                        if (c8 == '[') {
                            return parseArrayAccess(true);
                        }
                        if (this.level == 0) {
                            return new PropertySegement(readName(), false);
                        }
                        StringBuilder sbM112a = C0413b.m112a("not support jsonpath : ");
                        sbM112a.append(this.path);
                        throw new JSONPathException(sbM112a.toString());
                    }
                    next();
                    if (c8 == '.' && this.f404ch == '.') {
                        next();
                        int length = this.path.length();
                        int i7 = this.pos;
                        if (length > i7 + 3 && this.f404ch == '[' && this.path.charAt(i7) == '*' && this.path.charAt(this.pos + 1) == ']' && this.path.charAt(this.pos + 2) == '.') {
                            next();
                            next();
                            next();
                            next();
                        }
                    } else {
                        z6 = false;
                    }
                    char c9 = this.f404ch;
                    if (c9 == '*') {
                        if (!isEOF()) {
                            next();
                        }
                        return WildCardSegement.instance;
                    }
                    if (isDigitFirst(c9)) {
                        return parseArrayAccess(false);
                    }
                    String name = readName();
                    if (this.f404ch != '(') {
                        return new PropertySegement(name, z6);
                    }
                    next();
                    if (this.f404ch != ')') {
                        StringBuilder sbM112a2 = C0413b.m112a("not support jsonpath : ");
                        sbM112a2.append(this.path);
                        throw new JSONPathException(sbM112a2.toString());
                    }
                    if (!isEOF()) {
                        next();
                    }
                    if ("size".equals(name) || "length".equals(name)) {
                        return SizeSegement.instance;
                    }
                    if ("keySet".equals(name)) {
                        return KeySetSegement.instance;
                    }
                    StringBuilder sbM112a3 = C0413b.m112a("not support jsonpath : ");
                    sbM112a3.append(this.path);
                    throw new JSONPathException(sbM112a3.toString());
                }
                next();
            }
            return null;
        }

        public String readString() {
            char c7 = this.f404ch;
            next();
            int i7 = this.pos - 1;
            while (this.f404ch != c7 && !isEOF()) {
                next();
            }
            String strSubstring = this.path.substring(i7, isEOF() ? this.pos : this.pos - 1);
            accept(c7);
            return strSubstring;
        }

        public Object readValue() {
            skipWhitespace();
            if (isDigitFirst(this.f404ch)) {
                return Long.valueOf(readLongValue());
            }
            char c7 = this.f404ch;
            if (c7 == '\"' || c7 == '\'') {
                return readString();
            }
            if (c7 != 'n') {
                throw new UnsupportedOperationException();
            }
            if ("null".equals(readName())) {
                return null;
            }
            throw new JSONPathException(this.path);
        }

        public final void skipWhitespace() {
            while (true) {
                char c7 = this.f404ch;
                if (c7 > ' ') {
                    return;
                }
                if (c7 != ' ' && c7 != '\r' && c7 != '\n' && c7 != '\t' && c7 != '\f' && c7 != '\b') {
                    return;
                } else {
                    next();
                }
            }
        }
    }

    public static class KeySetSegement implements Segement {
        public static final KeySetSegement instance = new KeySetSegement();

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.evalKeySet(obj2);
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    public static class MatchSegement implements Filter {
        private final String[] containsValues;
        private final String endsWithValue;
        private final int minLength;
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final String startsWithValue;

        public MatchSegement(String str, String str2, String str3, String[] strArr, boolean z6) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.startsWithValue = str2;
            this.endsWithValue = str3;
            this.containsValues = strArr;
            this.not = z6;
            int length = str2 != null ? str2.length() + 0 : 0;
            length = str3 != null ? length + str3.length() : length;
            if (strArr != null) {
                for (String str4 : strArr) {
                    length += str4.length();
                }
            }
            this.minLength = length;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            int length;
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            String string = propertyValue.toString();
            if (string.length() < this.minLength) {
                return this.not;
            }
            String str = this.startsWithValue;
            if (str == null) {
                length = 0;
            } else {
                if (!string.startsWith(str)) {
                    return this.not;
                }
                length = this.startsWithValue.length() + 0;
            }
            String[] strArr = this.containsValues;
            if (strArr != null) {
                for (String str2 : strArr) {
                    int iIndexOf = string.indexOf(str2, length);
                    if (iIndexOf == -1) {
                        return this.not;
                    }
                    length = iIndexOf + str2.length();
                }
            }
            String str3 = this.endsWithValue;
            return (str3 == null || string.endsWith(str3)) ? !this.not : this.not;
        }
    }

    public static class MultiIndexSegement implements Segement {
        private final int[] indexes;

        public MultiIndexSegement(int[] iArr) {
            this.indexes = iArr;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            JSONArray jSONArray = new JSONArray(this.indexes.length);
            int i7 = 0;
            while (true) {
                int[] iArr = this.indexes;
                if (i7 >= iArr.length) {
                    return jSONArray;
                }
                jSONArray.add(jSONPath.getArrayItem(obj2, iArr[i7]));
                i7++;
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            if (context.eval) {
                Object obj = defaultJSONParser.parse();
                if (obj instanceof List) {
                    int[] iArr = this.indexes;
                    int length = iArr.length;
                    int[] iArr2 = new int[length];
                    System.arraycopy(iArr, 0, iArr2, 0, length);
                    List list = (List) obj;
                    if (iArr2[0] >= 0) {
                        for (int size = list.size() - 1; size >= 0; size--) {
                            if (Arrays.binarySearch(iArr2, size) < 0) {
                                list.remove(size);
                            }
                        }
                        context.object = list;
                        return;
                    }
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    public static class MultiPropertySegement implements Segement {
        private final String[] propertyNames;
        private final long[] propertyNamesHash;

        public MultiPropertySegement(String[] strArr) {
            this.propertyNames = strArr;
            this.propertyNamesHash = new long[strArr.length];
            int i7 = 0;
            while (true) {
                long[] jArr = this.propertyNamesHash;
                if (i7 >= jArr.length) {
                    return;
                }
                jArr[i7] = TypeUtils.fnv1a_64(strArr[i7]);
                i7++;
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            ArrayList arrayList = new ArrayList(this.propertyNames.length);
            int i7 = 0;
            while (true) {
                String[] strArr = this.propertyNames;
                if (i7 >= strArr.length) {
                    return arrayList;
                }
                arrayList.add(jSONPath.getPropertyValue(obj2, strArr[i7], this.propertyNamesHash[i7]));
                i7++;
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    public static class NotNullSegement implements Filter {
        private final String propertyName;
        private final long propertyNameHash;

        public NotNullSegement(String str) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash) != null;
        }
    }

    public static class NullSegement implements Filter {
        private final String propertyName;
        private final long propertyNameHash;

        public NullSegement(String str) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash) == null;
        }
    }

    public enum Operator {
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        LIKE,
        NOT_LIKE,
        RLIKE,
        NOT_RLIKE,
        IN,
        NOT_IN,
        BETWEEN,
        NOT_BETWEEN,
        And,
        Or
    }

    public static class PropertySegement implements Segement {
        private final boolean deep;
        private final String propertyName;
        private final long propertyNameHash;

        public PropertySegement(String str, boolean z6) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.deep = z6;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (!this.deep) {
                return jSONPath.getPropertyValue(obj2, this.propertyName, this.propertyNameHash);
            }
            ArrayList arrayList = new ArrayList();
            jSONPath.deepScan(obj2, this.propertyName, arrayList);
            return arrayList;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            Object objIntegerValue;
            Object objIntegerValue2;
            JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
            if (this.deep && context.object == null) {
                context.object = new JSONArray();
            }
            if (jSONLexerBase.token() != 14) {
                int iSeekObjectToField = jSONLexerBase.seekObjectToField(this.propertyNameHash, this.deep);
                if (iSeekObjectToField != 3) {
                    if (this.deep) {
                        if (iSeekObjectToField == 1 || iSeekObjectToField == 2) {
                            extract(jSONPath, defaultJSONParser, context);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (context.eval) {
                    int i7 = jSONLexerBase.token();
                    if (i7 == 2) {
                        objIntegerValue = jSONLexerBase.integerValue();
                        jSONLexerBase.nextToken(16);
                    } else if (i7 == 3) {
                        objIntegerValue = jSONLexerBase.decimalValue();
                        jSONLexerBase.nextToken(16);
                    } else if (i7 != 4) {
                        objIntegerValue = defaultJSONParser.parse();
                    } else {
                        objIntegerValue = jSONLexerBase.stringVal();
                        jSONLexerBase.nextToken(16);
                    }
                    if (context.eval) {
                        context.object = objIntegerValue;
                        return;
                    }
                    return;
                }
                return;
            }
            if ("*".equals(this.propertyName)) {
                return;
            }
            jSONLexerBase.nextToken();
            JSONArray jSONArray = this.deep ? (JSONArray) context.object : new JSONArray();
            while (true) {
                if (jSONLexerBase.token() == 12) {
                    if (jSONLexerBase.seekObjectToField(this.propertyNameHash, this.deep) == 3) {
                        int i8 = jSONLexerBase.token();
                        if (i8 == 2) {
                            objIntegerValue2 = jSONLexerBase.integerValue();
                            jSONLexerBase.nextToken();
                        } else if (i8 != 4) {
                            objIntegerValue2 = defaultJSONParser.parse();
                        } else {
                            objIntegerValue2 = jSONLexerBase.stringVal();
                            jSONLexerBase.nextToken();
                        }
                        jSONArray.add(objIntegerValue2);
                        if (jSONLexerBase.token() == 13) {
                            jSONLexerBase.nextToken();
                        } else {
                            jSONLexerBase.skipObject();
                        }
                    } else {
                        if (this.deep) {
                            throw new UnsupportedOperationException();
                        }
                        jSONLexerBase.skipObject();
                    }
                }
                if (jSONLexerBase.token() == 15) {
                    if (this.deep) {
                        return;
                    }
                    context.object = jSONArray;
                    return;
                } else {
                    if (jSONLexerBase.token() != 16) {
                        throw new JSONException("illegal json.");
                    }
                    jSONLexerBase.nextToken();
                }
            }
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removePropertyValue(obj, this.propertyName);
        }

        public void setValue(JSONPath jSONPath, Object obj, Object obj2) {
            if (this.deep) {
                jSONPath.deepSet(obj, this.propertyName, this.propertyNameHash, obj2);
            } else {
                jSONPath.setPropertyValue(obj, this.propertyName, this.propertyNameHash, obj2);
            }
        }
    }

    public static class RangeSegement implements Segement {
        private final int end;
        private final int start;
        private final int step;

        public RangeSegement(int i7, int i8, int i9) {
            this.start = i7;
            this.end = i8;
            this.step = i9;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            int iIntValue = SizeSegement.instance.eval(jSONPath, obj, obj2).intValue();
            int i7 = this.start;
            if (i7 < 0) {
                i7 += iIntValue;
            }
            int i8 = this.end;
            if (i8 < 0) {
                i8 += iIntValue;
            }
            int i9 = ((i8 - i7) / this.step) + 1;
            if (i9 == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i9);
            while (i7 <= i8 && i7 < iIntValue) {
                arrayList.add(jSONPath.getArrayItem(obj2, i7));
                i7 += this.step;
            }
            return arrayList;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    public static class RlikeSegement implements Filter {
        private final boolean not;
        private final Pattern pattern;
        private final String propertyName;
        private final long propertyNameHash;

        public RlikeSegement(String str, String str2, boolean z6) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.pattern = Pattern.compile(str2);
            this.not = z6;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            boolean zMatches = this.pattern.matcher(propertyValue.toString()).matches();
            return this.not ? !zMatches : zMatches;
        }
    }

    public interface Segement {
        Object eval(JSONPath jSONPath, Object obj, Object obj2);

        void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context);
    }

    public static class SizeSegement implements Segement {
        public static final SizeSegement instance = new SizeSegement();

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Integer eval(JSONPath jSONPath, Object obj, Object obj2) {
            return Integer.valueOf(jSONPath.evalSize(obj2));
        }
    }

    public static class StringInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final String[] values;

        public StringInSegement(String str, String[] strArr, boolean z6) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = strArr;
            this.not = z6;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            for (String str : this.values) {
                if (str == propertyValue) {
                    return !this.not;
                }
                if (str != null && str.equals(propertyValue)) {
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    public static class StringOpSegement implements Filter {

        /* renamed from: op */
        private final Operator f413op;
        private final String propertyName;
        private final long propertyNameHash;
        private final String value;

        public StringOpSegement(String str, String str2, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = str2;
            this.f413op = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            Operator operator = this.f413op;
            if (operator == Operator.EQ) {
                return this.value.equals(propertyValue);
            }
            if (operator == Operator.NE) {
                return !this.value.equals(propertyValue);
            }
            if (propertyValue == null) {
                return false;
            }
            int iCompareTo = this.value.compareTo(propertyValue.toString());
            Operator operator2 = this.f413op;
            return operator2 == Operator.GE ? iCompareTo <= 0 : operator2 == Operator.GT ? iCompareTo < 0 : operator2 == Operator.LE ? iCompareTo >= 0 : operator2 == Operator.LT && iCompareTo > 0;
        }
    }

    public static class ValueSegment implements Filter {

        /* renamed from: eq */
        private boolean f414eq;
        private final String propertyName;
        private final long propertyNameHash;
        private final Object value;

        public ValueSegment(String str, Object obj, boolean z6) {
            this.f414eq = true;
            if (obj == null) {
                throw new IllegalArgumentException("value is null");
            }
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = obj;
            this.f414eq = z6;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            boolean zEquals = this.value.equals(jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash));
            return !this.f414eq ? !zEquals : zEquals;
        }
    }

    public static class WildCardSegement implements Segement {
        public static WildCardSegement instance = new WildCardSegement();

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getPropertyValues(obj2);
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            if (context.eval) {
                Object obj = defaultJSONParser.parse();
                if (obj instanceof JSONObject) {
                    Collection<Object> collectionValues = ((JSONObject) obj).values();
                    JSONArray jSONArray = new JSONArray(collectionValues.size());
                    Iterator<Object> it = collectionValues.iterator();
                    while (it.hasNext()) {
                        jSONArray.add(it.next());
                    }
                    context.object = jSONArray;
                    return;
                }
                if (obj instanceof JSONArray) {
                    context.object = obj;
                    return;
                }
            }
            throw new JSONException("TODO");
        }
    }

    public JSONPath(String str) {
        this(str, SerializeConfig.getGlobalInstance(), ParserConfig.getGlobalInstance());
    }

    public static JSONPath compile(String str) {
        if (str == null) {
            throw new JSONPathException("jsonpath can not be null");
        }
        JSONPath jSONPath = pathCache.get(str);
        if (jSONPath != null) {
            return jSONPath;
        }
        JSONPath jSONPath2 = new JSONPath(str);
        if (pathCache.size() >= 1024) {
            return jSONPath2;
        }
        pathCache.putIfAbsent(str, jSONPath2);
        return pathCache.get(str);
    }

    /* renamed from: eq */
    public static boolean m337eq(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if (obj.getClass() == obj2.getClass()) {
            return obj.equals(obj2);
        }
        if (!(obj instanceof Number)) {
            return obj.equals(obj2);
        }
        if (obj2 instanceof Number) {
            return eqNotNull((Number) obj, (Number) obj2);
        }
        return false;
    }

    public static boolean eqNotNull(Number number, Number number2) {
        Class<?> cls = number.getClass();
        boolean zIsInt = isInt(cls);
        Class<?> cls2 = number2.getClass();
        boolean zIsInt2 = isInt(cls2);
        if (number instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) number;
            if (zIsInt2) {
                return bigDecimal.equals(BigDecimal.valueOf(TypeUtils.longExtractValue(number2)));
            }
        }
        if (zIsInt) {
            if (zIsInt2) {
                return number.longValue() == number2.longValue();
            }
            if (number2 instanceof BigInteger) {
                return BigInteger.valueOf(number.longValue()).equals((BigInteger) number);
            }
        }
        if (zIsInt2 && (number instanceof BigInteger)) {
            return ((BigInteger) number).equals(BigInteger.valueOf(TypeUtils.longExtractValue(number2)));
        }
        boolean zIsDouble = isDouble(cls);
        boolean zIsDouble2 = isDouble(cls2);
        return ((zIsDouble && zIsDouble2) || ((zIsDouble && zIsInt2) || (zIsDouble2 && zIsInt))) && number.doubleValue() == number2.doubleValue();
    }

    public static boolean isDouble(Class<?> cls) {
        return cls == Float.class || cls == Double.class;
    }

    public static boolean isInt(Class<?> cls) {
        return cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class;
    }

    public static Map<String, Object> paths(Object obj) {
        return paths(obj, SerializeConfig.globalInstance);
    }

    public static Object read(String str, String str2) {
        return compile(str2).eval(JSON.parse(str));
    }

    public void arrayAdd(Object obj, Object... objArr) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NegativeArraySizeException {
        if (objArr == null || objArr.length == 0 || obj == null) {
            return;
        }
        init();
        Object obj2 = null;
        int i7 = 0;
        int i8 = 0;
        Object objEval = obj;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i8 >= segementArr.length) {
                break;
            }
            if (i8 == segementArr.length - 1) {
                obj2 = objEval;
            }
            objEval = segementArr[i8].eval(this, obj, objEval);
            i8++;
        }
        if (objEval == null) {
            StringBuilder sbM112a = C0413b.m112a("value not found in path ");
            sbM112a.append(this.path);
            throw new JSONPathException(sbM112a.toString());
        }
        if (objEval instanceof Collection) {
            Collection collection = (Collection) objEval;
            int length = objArr.length;
            while (i7 < length) {
                collection.add(objArr[i7]);
                i7++;
            }
            return;
        }
        Class<?> cls = objEval.getClass();
        if (!cls.isArray()) {
            throw new JSONException("unsupported array put operation. " + cls);
        }
        int length2 = Array.getLength(objEval);
        Object objNewInstance = Array.newInstance(cls.getComponentType(), objArr.length + length2);
        System.arraycopy(objEval, 0, objNewInstance, 0, length2);
        while (i7 < objArr.length) {
            Array.set(objNewInstance, length2 + i7, objArr[i7]);
            i7++;
        }
        Segement segement = this.segments[r8.length - 1];
        if (segement instanceof PropertySegement) {
            ((PropertySegement) segement).setValue(this, obj2, objNewInstance);
        } else {
            if (!(segement instanceof ArrayAccessSegement)) {
                throw new UnsupportedOperationException();
            }
            ((ArrayAccessSegement) segement).setValue(this, obj2, objNewInstance);
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        init();
        Object objEval = obj;
        int i7 = 0;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i7 >= segementArr.length) {
                return true;
            }
            objEval = segementArr[i7].eval(this, obj, objEval);
            if (objEval == null) {
                return false;
            }
            i7++;
        }
    }

    public boolean containsValue(Object obj, Object obj2) {
        Object objEval = eval(obj);
        if (objEval == obj2) {
            return true;
        }
        if (objEval == null) {
            return false;
        }
        if (!(objEval instanceof Iterable)) {
            return m337eq(objEval, obj2);
        }
        Iterator it = ((Iterable) objEval).iterator();
        while (it.hasNext()) {
            if (m337eq(it.next(), obj2)) {
                return true;
            }
        }
        return false;
    }

    public void deepScan(Object obj, String str, List<Object> list) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.containsKey(str)) {
                list.add(map.get(str));
                return;
            }
            Iterator it = map.values().iterator();
            while (it.hasNext()) {
                deepScan(it.next(), str, list);
            }
            return;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer == null) {
            if (obj instanceof List) {
                List list2 = (List) obj;
                for (int i7 = 0; i7 < list2.size(); i7++) {
                    deepScan(list2.get(i7), str, list);
                }
                return;
            }
            return;
        }
        try {
            FieldSerializer fieldSerializer = javaBeanSerializer.getFieldSerializer(str);
            if (fieldSerializer == null) {
                Iterator<Object> it2 = javaBeanSerializer.getFieldValues(obj).iterator();
                while (it2.hasNext()) {
                    deepScan(it2.next(), str, list);
                }
                return;
            }
            try {
                try {
                    list.add(fieldSerializer.getPropertyValueDirect(obj));
                } catch (IllegalAccessException e7) {
                    throw new JSONException("getFieldValue error." + str, e7);
                }
            } catch (InvocationTargetException e8) {
                throw new JSONException("getFieldValue error." + str, e8);
            }
        } catch (Exception e9) {
            throw new JSONPathException(C0164a.m99a(C0413b.m112a("jsonpath error, path "), this.path, ", segement ", str), e9);
        }
    }

    public void deepSet(Object obj, String str, long j7, Object obj2) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.containsKey(str)) {
                map.get(str);
                map.put(str, obj2);
                return;
            } else {
                Iterator it = map.values().iterator();
                while (it.hasNext()) {
                    deepSet(it.next(), str, j7, obj2);
                }
                return;
            }
        }
        Class<?> cls = obj.getClass();
        JavaBeanDeserializer javaBeanDeserializer = getJavaBeanDeserializer(cls);
        if (javaBeanDeserializer == null) {
            if (obj instanceof List) {
                List list = (List) obj;
                for (int i7 = 0; i7 < list.size(); i7++) {
                    deepSet(list.get(i7), str, j7, obj2);
                }
                return;
            }
            return;
        }
        try {
            FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str);
            if (fieldDeserializer != null) {
                fieldDeserializer.setValue(obj, obj2);
                return;
            }
            Iterator<Object> it2 = getJavaBeanSerializer(cls).getObjectFieldValues(obj).iterator();
            while (it2.hasNext()) {
                deepSet(it2.next(), str, j7, obj2);
            }
        } catch (Exception e7) {
            throw new JSONPathException(C0164a.m99a(C0413b.m112a("jsonpath error, path "), this.path, ", segement ", str), e7);
        }
    }

    public Object eval(Object obj) {
        if (obj == null) {
            return null;
        }
        init();
        int i7 = 0;
        Object objEval = obj;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i7 >= segementArr.length) {
                return objEval;
            }
            objEval = segementArr[i7].eval(this, obj, objEval);
            i7++;
        }
    }

    public Set<?> evalKeySet(Object obj) {
        JavaBeanSerializer javaBeanSerializer;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return ((Map) obj).keySet();
        }
        if ((obj instanceof Collection) || (obj instanceof Object[]) || obj.getClass().isArray() || (javaBeanSerializer = getJavaBeanSerializer(obj.getClass())) == null) {
            return null;
        }
        try {
            return javaBeanSerializer.getFieldNames(obj);
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("evalKeySet error : ");
            sbM112a.append(this.path);
            throw new JSONPathException(sbM112a.toString(), e7);
        }
    }

    public int evalSize(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        }
        if (obj instanceof Map) {
            int i7 = 0;
            Iterator it = ((Map) obj).values().iterator();
            while (it.hasNext()) {
                if (it.next() != null) {
                    i7++;
                }
            }
            return i7;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer == null) {
            return -1;
        }
        try {
            return javaBeanSerializer.getSize(obj);
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("evalSize error : ");
            sbM112a.append(this.path);
            throw new JSONPathException(sbM112a.toString(), e7);
        }
    }

    public Object extract(DefaultJSONParser defaultJSONParser) {
        Object obj;
        if (defaultJSONParser == null) {
            return null;
        }
        init();
        Context context = null;
        int i7 = 0;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i7 >= segementArr.length) {
                return context.object;
            }
            Segement segement = segementArr[i7];
            boolean z6 = true;
            boolean z7 = i7 == segementArr.length - 1;
            if (context != null && (obj = context.object) != null) {
                return segement.eval(this, null, obj);
            }
            if (!z7) {
                Segement segement2 = segementArr[i7 + 1];
                if ((!(segement instanceof PropertySegement) || !((PropertySegement) segement).deep || (!(segement2 instanceof ArrayAccessSegement) && !(segement2 instanceof MultiIndexSegement))) && ((!(segement2 instanceof ArrayAccessSegement) || ((ArrayAccessSegement) segement2).index >= 0) && !(segement2 instanceof FilterSegement))) {
                    z6 = false;
                }
            }
            Context context2 = new Context(context, z6);
            segement.extract(this, defaultJSONParser, context2);
            i7++;
            context = context2;
        }
    }

    public Object getArrayItem(Object obj, int i7) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (i7 >= 0) {
                if (i7 < list.size()) {
                    return list.get(i7);
                }
                return null;
            }
            if (Math.abs(i7) <= list.size()) {
                return list.get(list.size() + i7);
            }
            return null;
        }
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            if (i7 >= 0) {
                if (i7 < length) {
                    return Array.get(obj, i7);
                }
                return null;
            }
            if (Math.abs(i7) <= length) {
                return Array.get(obj, length + i7);
            }
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(Integer.valueOf(i7));
            return obj2 == null ? map.get(Integer.toString(i7)) : obj2;
        }
        if (!(obj instanceof Collection)) {
            throw new UnsupportedOperationException();
        }
        int i8 = 0;
        for (Object obj3 : (Collection) obj) {
            if (i8 == i7) {
                return obj3;
            }
            i8++;
        }
        return null;
    }

    public JavaBeanDeserializer getJavaBeanDeserializer(Class<?> cls) {
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(cls);
        if (deserializer instanceof JavaBeanDeserializer) {
            return (JavaBeanDeserializer) deserializer;
        }
        return null;
    }

    public JavaBeanSerializer getJavaBeanSerializer(Class<?> cls) {
        ObjectSerializer objectWriter = this.serializeConfig.getObjectWriter(cls);
        if (objectWriter instanceof JavaBeanSerializer) {
            return (JavaBeanSerializer) objectWriter;
        }
        return null;
    }

    public String getPath() {
        return this.path;
    }

    public Object getPropertyValue(Object obj, String str, long j7) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(str);
            return obj2 == null ? (SIZE == j7 || LENGTH == j7) ? Integer.valueOf(map.size()) : obj2 : obj2;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                return javaBeanSerializer.getFieldValue(obj, str, j7, false);
            } catch (Exception e7) {
                throw new JSONPathException(C0164a.m99a(C0413b.m112a("jsonpath error, path "), this.path, ", segement ", str), e7);
            }
        }
        int i7 = 0;
        if (obj instanceof List) {
            List list = (List) obj;
            if (SIZE == j7 || LENGTH == j7) {
                return Integer.valueOf(list.size());
            }
            JSONArray jSONArray = new JSONArray(list.size());
            while (i7 < list.size()) {
                Object obj3 = list.get(i7);
                if (obj3 == list) {
                    jSONArray.add(obj3);
                } else {
                    Object propertyValue = getPropertyValue(obj3, str, j7);
                    if (propertyValue instanceof Collection) {
                        jSONArray.addAll((Collection) propertyValue);
                    } else if (propertyValue != null) {
                        jSONArray.add(propertyValue);
                    }
                }
                i7++;
            }
            return jSONArray;
        }
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (SIZE == j7 || LENGTH == j7) {
                return Integer.valueOf(objArr.length);
            }
            JSONArray jSONArray2 = new JSONArray(objArr.length);
            while (i7 < objArr.length) {
                Object[] objArr2 = objArr[i7];
                if (objArr2 == objArr) {
                    jSONArray2.add(objArr2);
                } else {
                    Object propertyValue2 = getPropertyValue(objArr2, str, j7);
                    if (propertyValue2 instanceof Collection) {
                        jSONArray2.addAll((Collection) propertyValue2);
                    } else if (propertyValue2 != null) {
                        jSONArray2.add(propertyValue2);
                    }
                }
                i7++;
            }
            return jSONArray2;
        }
        if (obj instanceof Enum) {
            Enum r32 = (Enum) obj;
            if (-4270347329889690746L == j7) {
                return r32.name();
            }
            if (-1014497654951707614L == j7) {
                return Integer.valueOf(r32.ordinal());
            }
        }
        if (obj instanceof Calendar) {
            Calendar calendar = (Calendar) obj;
            if (8963398325558730460L == j7) {
                return Integer.valueOf(calendar.get(1));
            }
            if (-811277319855450459L == j7) {
                return Integer.valueOf(calendar.get(2));
            }
            if (-3851359326990528739L == j7) {
                return Integer.valueOf(calendar.get(5));
            }
            if (4647432019745535567L == j7) {
                return Integer.valueOf(calendar.get(11));
            }
            if (6607618197526598121L == j7) {
                return Integer.valueOf(calendar.get(12));
            }
            if (-6586085717218287427L == j7) {
                return Integer.valueOf(calendar.get(13));
            }
        }
        return null;
    }

    public Collection<Object> getPropertyValues(Object obj) {
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer == null) {
            if (obj instanceof Map) {
                return ((Map) obj).values();
            }
            throw new UnsupportedOperationException();
        }
        try {
            return javaBeanSerializer.getFieldValues(obj);
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("jsonpath error, path ");
            sbM112a.append(this.path);
            throw new JSONPathException(sbM112a.toString(), e7);
        }
    }

    public void init() {
        if (this.segments != null) {
            return;
        }
        if ("*".equals(this.path)) {
            this.segments = new Segement[]{WildCardSegement.instance};
        } else {
            this.segments = new JSONPathParser(this.path).explain();
        }
    }

    public Set<?> keySet(Object obj) {
        if (obj == null) {
            return null;
        }
        init();
        int i7 = 0;
        Object objEval = obj;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i7 >= segementArr.length) {
                return evalKeySet(objEval);
            }
            objEval = segementArr[i7].eval(this, obj, objEval);
            i7++;
        }
    }

    public boolean remove(Object obj) {
        boolean z6 = false;
        if (obj == null) {
            return false;
        }
        init();
        Object obj2 = null;
        Object objEval = obj;
        int i7 = 0;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i7 >= segementArr.length) {
                break;
            }
            if (i7 == segementArr.length - 1) {
                obj2 = objEval;
                break;
            }
            objEval = segementArr[i7].eval(this, obj, objEval);
            if (objEval == null) {
                break;
            }
            i7++;
        }
        if (obj2 == null) {
            return false;
        }
        Segement[] segementArr2 = this.segments;
        Segement segement = segementArr2[segementArr2.length - 1];
        if (!(segement instanceof PropertySegement)) {
            if (segement instanceof ArrayAccessSegement) {
                return ((ArrayAccessSegement) segement).remove(this, obj2);
            }
            throw new UnsupportedOperationException();
        }
        PropertySegement propertySegement = (PropertySegement) segement;
        if ((obj2 instanceof Collection) && segementArr2.length > 1) {
            Segement segement2 = segementArr2[segementArr2.length - 2];
            if ((segement2 instanceof RangeSegement) || (segement2 instanceof MultiIndexSegement)) {
                Iterator it = ((Collection) obj2).iterator();
                while (it.hasNext()) {
                    if (propertySegement.remove(this, it.next())) {
                        z6 = true;
                    }
                }
                return z6;
            }
        }
        return propertySegement.remove(this, obj2);
    }

    public boolean removeArrayItem(JSONPath jSONPath, Object obj, int i7) {
        if (!(obj instanceof List)) {
            throw new JSONPathException("unsupported set operation." + obj.getClass());
        }
        List list = (List) obj;
        if (i7 >= 0) {
            if (i7 >= list.size()) {
                return false;
            }
            list.remove(i7);
            return true;
        }
        int size = list.size() + i7;
        if (size < 0) {
            return false;
        }
        list.remove(size);
        return true;
    }

    public boolean removePropertyValue(Object obj, String str) {
        if (obj instanceof Map) {
            return ((Map) obj).remove(str) != null;
        }
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(obj.getClass());
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (javaBeanDeserializer == null) {
            throw new UnsupportedOperationException();
        }
        FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str);
        if (fieldDeserializer == null) {
            return false;
        }
        fieldDeserializer.setValue(obj, (String) null);
        return true;
    }

    public boolean set(Object obj, Object obj2) {
        return set(obj, obj2, true);
    }

    public boolean setArrayItem(JSONPath jSONPath, Object obj, int i7, Object obj2) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (obj instanceof List) {
            List list = (List) obj;
            if (i7 >= 0) {
                list.set(i7, obj2);
            } else {
                list.set(list.size() + i7, obj2);
            }
            return true;
        }
        Class<?> cls = obj.getClass();
        if (!cls.isArray()) {
            throw new JSONPathException("unsupported set operation." + cls);
        }
        int length = Array.getLength(obj);
        if (i7 >= 0) {
            if (i7 < length) {
                Array.set(obj, i7, obj2);
            }
        } else if (Math.abs(i7) <= length) {
            Array.set(obj, length + i7, obj2);
        }
        return true;
    }

    public boolean setPropertyValue(Object obj, String str, long j7, Object obj2) {
        if (obj instanceof Map) {
            ((Map) obj).put(str, obj2);
            return true;
        }
        if (obj instanceof List) {
            for (Object obj3 : (List) obj) {
                if (obj3 != null) {
                    setPropertyValue(obj3, str, j7, obj2);
                }
            }
            return true;
        }
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(obj.getClass());
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (javaBeanDeserializer == null) {
            throw new UnsupportedOperationException();
        }
        FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(j7);
        if (fieldDeserializer == null) {
            return false;
        }
        fieldDeserializer.setValue(obj, obj2);
        return true;
    }

    public int size(Object obj) {
        if (obj == null) {
            return -1;
        }
        init();
        int i7 = 0;
        Object objEval = obj;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i7 >= segementArr.length) {
                return evalSize(objEval);
            }
            objEval = segementArr[i7].eval(this, obj, objEval);
            i7++;
        }
    }

    @Override // com.alibaba.fastjson.JSONAware
    public String toJSONString() {
        return JSON.toJSONString(this.path);
    }

    public JSONPath(String str, SerializeConfig serializeConfig, ParserConfig parserConfig) {
        if (str == null || str.length() == 0) {
            throw new JSONPathException("json-path can not be null or empty");
        }
        this.path = str;
        this.serializeConfig = serializeConfig;
        this.parserConfig = parserConfig;
    }

    public static Map<String, Object> paths(Object obj, SerializeConfig serializeConfig) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        HashMap map = new HashMap();
        paths(identityHashMap, map, ServiceReference.DELIMITER, obj, serializeConfig);
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean set(java.lang.Object r9, java.lang.Object r10, boolean r11) throws java.lang.IllegalAccessException, java.lang.InstantiationException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            r8 = this;
            r11 = 0
            if (r9 != 0) goto L4
            return r11
        L4:
            r8.init()
            r0 = 0
            r2 = r9
            r3 = r0
            r1 = 0
        Lb:
            com.alibaba.fastjson.JSONPath$Segement[] r4 = r8.segments
            int r5 = r4.length
            r6 = 1
            if (r1 >= r5) goto L86
            r3 = r4[r1]
            java.lang.Object r4 = r3.eval(r8, r9, r2)
            if (r4 != 0) goto L81
            com.alibaba.fastjson.JSONPath$Segement[] r4 = r8.segments
            int r5 = r4.length
            int r5 = r5 - r6
            if (r1 >= r5) goto L24
            int r5 = r1 + 1
            r4 = r4[r5]
            goto L25
        L24:
            r4 = r0
        L25:
            boolean r5 = r4 instanceof com.alibaba.fastjson.JSONPath.PropertySegement
            if (r5 == 0) goto L61
            boolean r4 = r3 instanceof com.alibaba.fastjson.JSONPath.PropertySegement
            if (r4 == 0) goto L4b
            r4 = r3
            com.alibaba.fastjson.JSONPath$PropertySegement r4 = (com.alibaba.fastjson.JSONPath.PropertySegement) r4
            java.lang.String r4 = com.alibaba.fastjson.JSONPath.PropertySegement.access$200(r4)
            java.lang.Class r5 = r2.getClass()
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r5 = r8.getJavaBeanDeserializer(r5)
            if (r5 == 0) goto L4b
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r4 = r5.getFieldDeserializer(r4)
            com.alibaba.fastjson.util.FieldInfo r4 = r4.fieldInfo
            java.lang.Class<?> r4 = r4.fieldClass
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r5 = r8.getJavaBeanDeserializer(r4)
            goto L4d
        L4b:
            r4 = r0
            r5 = r4
        L4d:
            if (r5 == 0) goto L5b
            com.alibaba.fastjson.util.JavaBeanInfo r7 = r5.beanInfo
            java.lang.reflect.Constructor<?> r7 = r7.defaultConstructor
            if (r7 == 0) goto L5a
            java.lang.Object r4 = r5.createInstance(r0, r4)
            goto L6c
        L5a:
            return r11
        L5b:
            com.alibaba.fastjson.JSONObject r4 = new com.alibaba.fastjson.JSONObject
            r4.<init>()
            goto L6c
        L61:
            boolean r4 = r4 instanceof com.alibaba.fastjson.JSONPath.ArrayAccessSegement
            if (r4 == 0) goto L6b
            com.alibaba.fastjson.JSONArray r4 = new com.alibaba.fastjson.JSONArray
            r4.<init>()
            goto L6c
        L6b:
            r4 = r0
        L6c:
            if (r4 == 0) goto L87
            boolean r5 = r3 instanceof com.alibaba.fastjson.JSONPath.PropertySegement
            if (r5 == 0) goto L78
            com.alibaba.fastjson.JSONPath$PropertySegement r3 = (com.alibaba.fastjson.JSONPath.PropertySegement) r3
            r3.setValue(r8, r2, r4)
            goto L81
        L78:
            boolean r5 = r3 instanceof com.alibaba.fastjson.JSONPath.ArrayAccessSegement
            if (r5 == 0) goto L87
            com.alibaba.fastjson.JSONPath$ArrayAccessSegement r3 = (com.alibaba.fastjson.JSONPath.ArrayAccessSegement) r3
            r3.setValue(r8, r2, r4)
        L81:
            int r1 = r1 + 1
            r3 = r2
            r2 = r4
            goto Lb
        L86:
            r2 = r3
        L87:
            if (r2 != 0) goto L8a
            return r11
        L8a:
            com.alibaba.fastjson.JSONPath$Segement[] r9 = r8.segments
            int r11 = r9.length
            int r11 = r11 - r6
            r9 = r9[r11]
            boolean r11 = r9 instanceof com.alibaba.fastjson.JSONPath.PropertySegement
            if (r11 == 0) goto L9a
            com.alibaba.fastjson.JSONPath$PropertySegement r9 = (com.alibaba.fastjson.JSONPath.PropertySegement) r9
            r9.setValue(r8, r2, r10)
            return r6
        L9a:
            boolean r11 = r9 instanceof com.alibaba.fastjson.JSONPath.ArrayAccessSegement
            if (r11 == 0) goto La5
            com.alibaba.fastjson.JSONPath$ArrayAccessSegement r9 = (com.alibaba.fastjson.JSONPath.ArrayAccessSegement) r9
            boolean r9 = r9.setValue(r8, r2, r10)
            return r9
        La5:
            java.lang.UnsupportedOperationException r9 = new java.lang.UnsupportedOperationException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.JSONPath.set(java.lang.Object, java.lang.Object, boolean):boolean");
    }

    public static boolean contains(Object obj, String str) {
        if (obj == null) {
            return false;
        }
        return compile(str).contains(obj);
    }

    public static Object eval(Object obj, String str) {
        return compile(str).eval(obj);
    }

    public static Set<?> keySet(Object obj, String str) {
        JSONPath jSONPathCompile = compile(str);
        return jSONPathCompile.evalKeySet(jSONPathCompile.eval(obj));
    }

    private static void paths(Map<Object, String> map, Map<String, Object> map2, String str, Object obj, SerializeConfig serializeConfig) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        StringBuilder sb;
        if (obj == null) {
            return;
        }
        int i7 = 0;
        if (map.put(obj, str) != null) {
            if (!((obj instanceof String) || (obj instanceof Number) || (obj instanceof Date) || (obj instanceof UUID))) {
                return;
            }
        }
        map2.put(str, obj);
        if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                if (key instanceof String) {
                    StringBuilder sb2 = str.equals(ServiceReference.DELIMITER) ? new StringBuilder() : C0413b.m112a(str);
                    sb2.append(ServiceReference.DELIMITER);
                    sb2.append(key);
                    paths(map, map2, sb2.toString(), entry.getValue(), serializeConfig);
                }
            }
            return;
        }
        if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                StringBuilder sb3 = str.equals(ServiceReference.DELIMITER) ? new StringBuilder() : C0413b.m112a(str);
                sb3.append(ServiceReference.DELIMITER);
                sb3.append(i7);
                paths(map, map2, sb3.toString(), obj2, serializeConfig);
                i7++;
            }
            return;
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            while (i7 < length) {
                Object obj3 = Array.get(obj, i7);
                StringBuilder sb4 = str.equals(ServiceReference.DELIMITER) ? new StringBuilder() : C0413b.m112a(str);
                sb4.append(ServiceReference.DELIMITER);
                sb4.append(i7);
                paths(map, map2, sb4.toString(), obj3, serializeConfig);
                i7++;
            }
            return;
        }
        if (ParserConfig.isPrimitive2(cls) || cls.isEnum()) {
            return;
        }
        ObjectSerializer objectWriter = serializeConfig.getObjectWriter(cls);
        if (objectWriter instanceof JavaBeanSerializer) {
            try {
                for (Map.Entry<String, Object> entry2 : ((JavaBeanSerializer) objectWriter).getFieldValuesMap(obj).entrySet()) {
                    String key2 = entry2.getKey();
                    if (key2 instanceof String) {
                        if (str.equals(ServiceReference.DELIMITER)) {
                            sb = new StringBuilder();
                            sb.append(ServiceReference.DELIMITER);
                        } else {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append(ServiceReference.DELIMITER);
                        }
                        sb.append(key2);
                        paths(map, map2, sb.toString(), entry2.getValue(), serializeConfig);
                    }
                }
            } catch (Exception e7) {
                throw new JSONException("toJSON error", e7);
            }
        }
    }

    public static int size(Object obj, String str) {
        JSONPath jSONPathCompile = compile(str);
        return jSONPathCompile.evalSize(jSONPathCompile.eval(obj));
    }

    public static boolean containsValue(Object obj, String str, Object obj2) {
        return compile(str).containsValue(obj, obj2);
    }

    public static Object extract(String str, String str2) {
        return extract(str, str2, ParserConfig.global, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
    }

    public static Object extract(String str, String str2, ParserConfig parserConfig, int i7, Feature... featureArr) {
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, parserConfig, i7 | Feature.OrderedField.mask);
        Object objExtract = compile(str2).extract(defaultJSONParser);
        defaultJSONParser.lexer.close();
        return objExtract;
    }

    public static boolean remove(Object obj, String str) {
        return compile(str).remove(obj);
    }

    public static void arrayAdd(Object obj, String str, Object... objArr) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NegativeArraySizeException {
        compile(str).arrayAdd(obj, objArr);
    }

    public static boolean set(Object obj, String str, Object obj2) {
        return compile(str).set(obj, obj2);
    }
}
