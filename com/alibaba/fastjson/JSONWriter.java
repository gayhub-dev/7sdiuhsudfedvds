package com.alibaba.fastjson;

import android.support.constraint.motion.C0079a;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public class JSONWriter implements Closeable, Flushable {
    private JSONStreamContext context;
    private JSONSerializer serializer;
    private SerializeWriter writer;

    public JSONWriter(Writer writer) {
        SerializeWriter serializeWriter = new SerializeWriter(writer);
        this.writer = serializeWriter;
        this.serializer = new JSONSerializer(serializeWriter);
    }

    private void afterWriter() {
        int i7;
        JSONStreamContext jSONStreamContext = this.context;
        if (jSONStreamContext == null) {
            return;
        }
        switch (jSONStreamContext.state) {
            case 1001:
            case 1003:
                i7 = 1002;
                break;
            case 1002:
                i7 = 1003;
                break;
            case 1004:
                i7 = 1005;
                break;
            default:
                i7 = -1;
                break;
        }
        if (i7 != -1) {
            jSONStreamContext.state = i7;
        }
    }

    private void beforeWrite() {
        JSONStreamContext jSONStreamContext = this.context;
        if (jSONStreamContext == null) {
            return;
        }
        int i7 = jSONStreamContext.state;
        if (i7 == 1002) {
            this.writer.write(58);
        } else if (i7 == 1003) {
            this.writer.write(44);
        } else {
            if (i7 != 1005) {
                return;
            }
            this.writer.write(44);
        }
    }

    private void beginStructure() {
        int i7 = this.context.state;
        switch (i7) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.writer.write(58);
                return;
            case 1003:
            default:
                throw new JSONException(C0079a.m93a("illegal state : ", i7));
            case 1005:
                this.writer.write(44);
                return;
        }
    }

    private void endStructure() {
        JSONStreamContext jSONStreamContext = this.context.parent;
        this.context = jSONStreamContext;
        if (jSONStreamContext == null) {
            return;
        }
        int i7 = jSONStreamContext.state;
        int i8 = i7 != 1001 ? i7 != 1002 ? i7 != 1004 ? -1 : 1005 : 1003 : 1002;
        if (i8 != -1) {
            jSONStreamContext.state = i8;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.writer.close();
    }

    public void config(SerializerFeature serializerFeature, boolean z6) {
        this.writer.config(serializerFeature, z6);
    }

    public void endArray() {
        this.writer.write(93);
        endStructure();
    }

    public void endObject() {
        this.writer.write(125);
        endStructure();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.writer.flush();
    }

    public void startArray() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, 1004);
        this.writer.write(91);
    }

    public void startObject() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, 1001);
        this.writer.write(123);
    }

    @Deprecated
    public void writeEndArray() {
        endArray();
    }

    @Deprecated
    public void writeEndObject() {
        endObject();
    }

    public void writeKey(String str) {
        writeObject(str);
    }

    public void writeObject(String str) {
        beforeWrite();
        this.serializer.write(str);
        afterWriter();
    }

    @Deprecated
    public void writeStartArray() {
        startArray();
    }

    @Deprecated
    public void writeStartObject() {
        startObject();
    }

    public void writeValue(Object obj) {
        writeObject(obj);
    }

    public void writeObject(Object obj) {
        beforeWrite();
        this.serializer.write(obj);
        afterWriter();
    }
}
