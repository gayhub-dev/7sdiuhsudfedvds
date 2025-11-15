package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* loaded from: classes.dex */
public class AtomicCodec implements ObjectSerializer, ObjectDeserializer {
    public static final AtomicCodec instance = new AtomicCodec();

    /* JADX WARN: Type inference failed for: r4v2, types: [T, java.util.concurrent.atomic.AtomicLongArray] */
    /* JADX WARN: Type inference failed for: r4v3, types: [T, java.util.concurrent.atomic.AtomicIntegerArray] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (defaultJSONParser.lexer.token() == 8) {
            defaultJSONParser.lexer.nextToken(16);
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        defaultJSONParser.parseArray(jSONArray);
        int i7 = 0;
        if (type == AtomicIntegerArray.class) {
            ?? r42 = (T) new AtomicIntegerArray(jSONArray.size());
            while (i7 < jSONArray.size()) {
                r42.set(i7, jSONArray.getInteger(i7).intValue());
                i7++;
            }
            return r42;
        }
        ?? r43 = (T) new AtomicLongArray(jSONArray.size());
        while (i7 < jSONArray.size()) {
            r43.set(i7, jSONArray.getLong(i7).longValue());
            i7++;
        }
        return r43;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 14;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj instanceof AtomicInteger) {
            serializeWriter.writeInt(((AtomicInteger) obj).get());
            return;
        }
        if (obj instanceof AtomicLong) {
            serializeWriter.writeLong(((AtomicLong) obj).get());
            return;
        }
        if (obj instanceof AtomicBoolean) {
            serializeWriter.append((CharSequence) (((AtomicBoolean) obj).get() ? "true" : "false"));
            return;
        }
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int i8 = 0;
        if (obj instanceof AtomicIntegerArray) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            int length = atomicIntegerArray.length();
            serializeWriter.write(91);
            while (i8 < length) {
                int i9 = atomicIntegerArray.get(i8);
                if (i8 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeInt(i9);
                i8++;
            }
            serializeWriter.write(93);
            return;
        }
        AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
        int length2 = atomicLongArray.length();
        serializeWriter.write(91);
        while (i8 < length2) {
            long j7 = atomicLongArray.get(i8);
            if (i8 != 0) {
                serializeWriter.write(44);
            }
            serializeWriter.writeLong(j7);
            i8++;
        }
        serializeWriter.write(93);
    }
}
