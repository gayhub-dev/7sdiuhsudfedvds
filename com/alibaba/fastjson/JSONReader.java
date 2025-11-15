package com.alibaba.fastjson;

import android.support.constraint.motion.C0079a;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONReaderScanner;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import p009b.C0413b;

/* loaded from: classes.dex */
public class JSONReader implements Closeable {
    private JSONStreamContext context;
    private final DefaultJSONParser parser;

    public JSONReader(Reader reader) {
        this(reader, new Feature[0]);
    }

    private void endStructure() {
        int i7;
        JSONStreamContext jSONStreamContext = this.context.parent;
        this.context = jSONStreamContext;
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

    private void readAfter() {
        JSONStreamContext jSONStreamContext = this.context;
        int i7 = jSONStreamContext.state;
        int i8 = 1002;
        switch (i7) {
            case 1001:
            case 1003:
                break;
            case 1002:
                i8 = 1003;
                break;
            case 1004:
                i8 = 1005;
                break;
            case 1005:
                i8 = -1;
                break;
            default:
                throw new JSONException(C0079a.m93a("illegal state : ", i7));
        }
        if (i8 != -1) {
            jSONStreamContext.state = i8;
        }
    }

    private void readBefore() {
        int i7 = this.context.state;
        switch (i7) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.parser.accept(17);
                return;
            case 1003:
                this.parser.accept(16, 18);
                return;
            case 1005:
                this.parser.accept(16);
                return;
            default:
                throw new JSONException(C0079a.m93a("illegal state : ", i7));
        }
    }

    private void startStructure() {
        switch (this.context.state) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.parser.accept(17);
                return;
            case 1003:
            case 1005:
                this.parser.accept(16);
                return;
            default:
                StringBuilder sbM112a = C0413b.m112a("illegal state : ");
                sbM112a.append(this.context.state);
                throw new JSONException(sbM112a.toString());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.parser.close();
    }

    public void config(Feature feature, boolean z6) {
        this.parser.config(feature, z6);
    }

    public void endArray() {
        this.parser.accept(15);
        endStructure();
    }

    public void endObject() {
        this.parser.accept(13);
        endStructure();
    }

    public Locale getLocal() {
        return this.parser.lexer.getLocale();
    }

    public TimeZone getTimzeZone() {
        return this.parser.lexer.getTimeZone();
    }

    public boolean hasNext() {
        if (this.context == null) {
            throw new JSONException("context is null");
        }
        int i7 = this.parser.lexer.token();
        int i8 = this.context.state;
        switch (i8) {
            case 1001:
            case 1003:
                return i7 != 13;
            case 1002:
            default:
                throw new JSONException(C0079a.m93a("illegal state : ", i8));
            case 1004:
            case 1005:
                return i7 != 15;
        }
    }

    public int peek() {
        return this.parser.lexer.token();
    }

    public Integer readInteger() {
        Object obj;
        if (this.context == null) {
            obj = this.parser.parse();
        } else {
            readBefore();
            obj = this.parser.parse();
            readAfter();
        }
        return TypeUtils.castToInt(obj);
    }

    public Long readLong() {
        Object obj;
        if (this.context == null) {
            obj = this.parser.parse();
        } else {
            readBefore();
            obj = this.parser.parse();
            readAfter();
        }
        return TypeUtils.castToLong(obj);
    }

    public <T> T readObject(TypeReference<T> typeReference) {
        return (T) readObject(typeReference.getType());
    }

    public String readString() {
        Object obj;
        if (this.context == null) {
            obj = this.parser.parse();
        } else {
            readBefore();
            JSONLexer jSONLexer = this.parser.lexer;
            if (this.context.state == 1001 && jSONLexer.token() == 18) {
                String strStringVal = jSONLexer.stringVal();
                jSONLexer.nextToken();
                obj = strStringVal;
            } else {
                obj = this.parser.parse();
            }
            readAfter();
        }
        return TypeUtils.castToString(obj);
    }

    public void setLocale(Locale locale) {
        this.parser.lexer.setLocale(locale);
    }

    public void setTimzeZone(TimeZone timeZone) {
        this.parser.lexer.setTimeZone(timeZone);
    }

    public void startArray() {
        if (this.context == null) {
            this.context = new JSONStreamContext(null, 1004);
        } else {
            startStructure();
            this.context = new JSONStreamContext(this.context, 1004);
        }
        this.parser.accept(14);
    }

    public void startObject() {
        if (this.context == null) {
            this.context = new JSONStreamContext(null, 1001);
        } else {
            startStructure();
            this.context = new JSONStreamContext(this.context, 1001);
        }
        this.parser.accept(12, 18);
    }

    public JSONReader(Reader reader, Feature... featureArr) {
        this(new JSONReaderScanner(reader));
        for (Feature feature : featureArr) {
            config(feature, true);
        }
    }

    public <T> T readObject(Type type) {
        if (this.context == null) {
            return (T) this.parser.parseObject(type);
        }
        readBefore();
        T t6 = (T) this.parser.parseObject(type);
        readAfter();
        return t6;
    }

    public JSONReader(JSONLexer jSONLexer) {
        this(new DefaultJSONParser(jSONLexer));
    }

    public JSONReader(DefaultJSONParser defaultJSONParser) {
        this.parser = defaultJSONParser;
    }

    public <T> T readObject(Class<T> cls) {
        if (this.context == null) {
            return (T) this.parser.parseObject((Class) cls);
        }
        readBefore();
        T t6 = (T) this.parser.parseObject((Class) cls);
        readAfter();
        return t6;
    }

    public void readObject(Object obj) {
        if (this.context == null) {
            this.parser.parseObject(obj);
            return;
        }
        readBefore();
        this.parser.parseObject(obj);
        readAfter();
    }

    public Object readObject() {
        Object key;
        if (this.context == null) {
            return this.parser.parse();
        }
        readBefore();
        int i7 = this.context.state;
        if (i7 != 1001 && i7 != 1003) {
            key = this.parser.parse();
        } else {
            key = this.parser.parseKey();
        }
        readAfter();
        return key;
    }

    public Object readObject(Map map) {
        if (this.context == null) {
            return this.parser.parseObject(map);
        }
        readBefore();
        Object object = this.parser.parseObject(map);
        readAfter();
        return object;
    }
}
