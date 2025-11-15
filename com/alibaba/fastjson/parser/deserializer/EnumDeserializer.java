package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;
import java.util.Arrays;

/* loaded from: classes.dex */
public class EnumDeserializer implements ObjectDeserializer {
    public final Class<?> enumClass;
    public long[] enumNameHashCodes;
    public final Enum[] enums;
    public final Enum[] ordinalEnums;

    /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public EnumDeserializer(java.lang.Class<?> r22) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.EnumDeserializer.<init>(java.lang.Class):void");
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        try {
            JSONLexer jSONLexer = defaultJSONParser.lexer;
            int i7 = jSONLexer.token();
            if (i7 == 2) {
                int iIntValue = jSONLexer.intValue();
                jSONLexer.nextToken(16);
                if (iIntValue >= 0) {
                    Object[] objArr = this.ordinalEnums;
                    if (iIntValue <= objArr.length) {
                        return (T) objArr[iIntValue];
                    }
                }
                throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + iIntValue);
            }
            if (i7 == 4) {
                String strStringVal = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                if (strStringVal.length() == 0) {
                    return null;
                }
                long jCharAt = -3750763034362895579L;
                for (int i8 = 0; i8 < strStringVal.length(); i8++) {
                    jCharAt = (jCharAt ^ strStringVal.charAt(i8)) * 1099511628211L;
                }
                return (T) getEnumByHashCode(jCharAt);
            }
            if (i7 == 8) {
                jSONLexer.nextToken(16);
                return null;
            }
            throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + defaultJSONParser.parse());
        } catch (JSONException e7) {
            throw e7;
        } catch (Exception e8) {
            throw new JSONException(e8.getMessage(), e8);
        }
    }

    public Enum getEnumByHashCode(long j7) {
        int iBinarySearch;
        if (this.enums != null && (iBinarySearch = Arrays.binarySearch(this.enumNameHashCodes, j7)) >= 0) {
            return this.enums[iBinarySearch];
        }
        return null;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    public Enum<?> valueOf(int i7) {
        return this.ordinalEnums[i7];
    }
}
