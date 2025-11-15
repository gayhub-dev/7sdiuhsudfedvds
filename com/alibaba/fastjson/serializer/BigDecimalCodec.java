package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.asm.C0532a;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public class BigDecimalCodec implements ObjectSerializer, ObjectDeserializer {
    public static final BigDecimalCodec instance = new BigDecimalCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        try {
            return (T) deserialze(defaultJSONParser);
        } catch (Exception e7) {
            throw new JSONException(C0532a.m338a("parseDecimal error, field : ", obj), e7);
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        int iScale = bigDecimal.scale();
        serializeWriter.write((!serializeWriter.isEnabled(SerializerFeature.WriteBigDecimalAsPlain) || iScale < -100 || iScale >= 100) ? bigDecimal.toString() : bigDecimal.toPlainString());
        if (serializeWriter.isEnabled(SerializerFeature.WriteClassName) && type != BigDecimal.class && bigDecimal.scale() == 0) {
            serializeWriter.write(46);
        }
    }

    public static <T> T deserialze(DefaultJSONParser defaultJSONParser) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 2) {
            T t6 = (T) jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            return t6;
        }
        if (jSONLexer.token() == 3) {
            T t7 = (T) jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            return t7;
        }
        Object obj = defaultJSONParser.parse();
        if (obj == null) {
            return null;
        }
        return (T) TypeUtils.castToBigDecimal(obj);
    }
}
