package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class PrimitiveArraySerializer implements ObjectSerializer {
    public static PrimitiveArraySerializer instance = new PrimitiveArraySerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int i8 = 0;
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            serializeWriter.write(91);
            while (i8 < iArr.length) {
                if (i8 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeInt(iArr[i8]);
                i8++;
            }
            serializeWriter.write(93);
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            serializeWriter.write(91);
            while (i8 < sArr.length) {
                if (i8 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeInt(sArr[i8]);
                i8++;
            }
            serializeWriter.write(93);
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            serializeWriter.write(91);
            while (i8 < jArr.length) {
                if (i8 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeLong(jArr[i8]);
                i8++;
            }
            serializeWriter.write(93);
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            serializeWriter.write(91);
            while (i8 < zArr.length) {
                if (i8 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.write(zArr[i8]);
                i8++;
            }
            serializeWriter.write(93);
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            serializeWriter.write(91);
            while (i8 < fArr.length) {
                if (i8 != 0) {
                    serializeWriter.write(44);
                }
                float f7 = fArr[i8];
                if (Float.isNaN(f7)) {
                    serializeWriter.writeNull();
                } else {
                    serializeWriter.append((CharSequence) Float.toString(f7));
                }
                i8++;
            }
            serializeWriter.write(93);
            return;
        }
        if (!(obj instanceof double[])) {
            if (obj instanceof byte[]) {
                serializeWriter.writeByteArray((byte[]) obj);
                return;
            } else {
                serializeWriter.writeString((char[]) obj);
                return;
            }
        }
        double[] dArr = (double[]) obj;
        serializeWriter.write(91);
        while (i8 < dArr.length) {
            if (i8 != 0) {
                serializeWriter.write(44);
            }
            double d7 = dArr[i8];
            if (Double.isNaN(d7)) {
                serializeWriter.writeNull();
            } else {
                serializeWriter.append((CharSequence) Double.toString(d7));
            }
            i8++;
        }
        serializeWriter.write(93);
    }
}
