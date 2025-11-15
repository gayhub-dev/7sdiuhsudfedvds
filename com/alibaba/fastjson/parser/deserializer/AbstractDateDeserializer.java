package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class AbstractDateDeserializer extends ContextObjectDeserializer {
    public abstract <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2);

    @Override // com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, null, 0);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str, int i7) throws Throwable {
        SimpleDateFormat simpleDateFormat;
        Date date;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        Object objValueOf = null;
        if (jSONLexer.token() == 2) {
            objValueOf = Long.valueOf(jSONLexer.longValue());
            jSONLexer.nextToken(16);
        } else if (jSONLexer.token() == 4) {
            String strStringVal = jSONLexer.stringVal();
            if (str != null) {
                try {
                    simpleDateFormat = new SimpleDateFormat(str, JSON.defaultLocale);
                } catch (IllegalArgumentException unused) {
                    if (str.equals("yyyy-MM-ddTHH:mm:ss.SSS")) {
                        str = "yyyy-MM-dd'T'HH:mm:ss.SSS";
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", JSON.defaultLocale);
                    } else if (str.equals("yyyy-MM-ddTHH:mm:ss")) {
                        str = "yyyy-MM-dd'T'HH:mm:ss";
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", JSON.defaultLocale);
                    } else {
                        simpleDateFormat = null;
                    }
                }
                TimeZone timeZone = JSON.defaultTimeZone;
                if (timeZone != null) {
                    simpleDateFormat.setTimeZone(timeZone);
                }
                try {
                    date = simpleDateFormat.parse(strStringVal);
                } catch (ParseException unused2) {
                    date = null;
                }
                if (date == null && JSON.defaultLocale == Locale.CHINA) {
                    try {
                        date = new SimpleDateFormat(str, Locale.US).parse(strStringVal);
                    } catch (ParseException unused3) {
                        date = null;
                    }
                }
                if (date != null) {
                    objValueOf = date;
                } else if (str.equals("yyyy-MM-dd'T'HH:mm:ss.SSS") && strStringVal.length() == 19) {
                    try {
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", JSON.defaultLocale);
                        simpleDateFormat2.setTimeZone(JSON.defaultTimeZone);
                        objValueOf = simpleDateFormat2.parse(strStringVal);
                    } catch (ParseException unused4) {
                    }
                }
            }
            if (objValueOf == null) {
                jSONLexer.nextToken(16);
                Object obj2 = strStringVal;
                if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                    JSONScanner jSONScanner = new JSONScanner(strStringVal);
                    Object time = strStringVal;
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        time = jSONScanner.getCalendar().getTime();
                    }
                    jSONScanner.close();
                    obj2 = time;
                }
                objValueOf = obj2;
            }
        } else if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
        } else if (jSONLexer.token() == 12) {
            jSONLexer.nextToken();
            if (jSONLexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            if (JSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal())) {
                jSONLexer.nextToken();
                defaultJSONParser.accept(17);
                Class<?> clsCheckAutoType = defaultJSONParser.getConfig().checkAutoType(jSONLexer.stringVal(), null, jSONLexer.getFeatures());
                if (clsCheckAutoType != null) {
                    type = clsCheckAutoType;
                }
                defaultJSONParser.accept(4);
                defaultJSONParser.accept(16);
            }
            jSONLexer.nextTokenWithColon(2);
            if (jSONLexer.token() != 2) {
                StringBuilder sbM112a = C0413b.m112a("syntax error : ");
                sbM112a.append(jSONLexer.tokenName());
                throw new JSONException(sbM112a.toString());
            }
            long jLongValue = jSONLexer.longValue();
            jSONLexer.nextToken();
            objValueOf = Long.valueOf(jLongValue);
            defaultJSONParser.accept(13);
        } else if (defaultJSONParser.getResolveStatus() == 2) {
            defaultJSONParser.setResolveStatus(0);
            defaultJSONParser.accept(16);
            if (jSONLexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            if (!"val".equals(jSONLexer.stringVal())) {
                throw new JSONException("syntax error");
            }
            jSONLexer.nextToken();
            defaultJSONParser.accept(17);
            objValueOf = defaultJSONParser.parse();
            defaultJSONParser.accept(13);
        } else {
            objValueOf = defaultJSONParser.parse();
        }
        return (T) cast(defaultJSONParser, type, obj, objValueOf);
    }
}
