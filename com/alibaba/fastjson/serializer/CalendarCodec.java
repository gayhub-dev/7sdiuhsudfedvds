package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/* loaded from: classes.dex */
public class CalendarCodec implements ObjectSerializer, ObjectDeserializer {
    public static final CalendarCodec instance = new CalendarCodec();
    private DatatypeFactory dateFactory;

    public XMLGregorianCalendar createXMLGregorianCalendar(Calendar calendar) {
        if (this.dateFactory == null) {
            try {
                this.dateFactory = DatatypeFactory.newInstance();
            } catch (DatatypeConfigurationException e7) {
                throw new IllegalStateException("Could not obtain an instance of DatatypeFactory.", e7);
            }
        }
        return this.dateFactory.newXMLGregorianCalendar((GregorianCalendar) calendar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, java.util.Calendar] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t6 = (T) DateCodec.instance.deserialze(defaultJSONParser, type, obj);
        if (t6 instanceof Calendar) {
            return t6;
        }
        Date date = (Date) t6;
        if (date == null) {
            return null;
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        ?? r22 = (T) Calendar.getInstance(jSONLexer.getTimeZone(), jSONLexer.getLocale());
        r22.setTime(date);
        return type == XMLGregorianCalendar.class ? (T) createXMLGregorianCalendar((GregorianCalendar) r22) : r22;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws IOException {
        char[] charArray;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        Calendar gregorianCalendar = obj instanceof XMLGregorianCalendar ? ((XMLGregorianCalendar) obj).toGregorianCalendar() : (Calendar) obj;
        if (!serializeWriter.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
            jSONSerializer.write(gregorianCalendar.getTime());
            return;
        }
        char c7 = serializeWriter.isEnabled(SerializerFeature.UseSingleQuotes) ? '\'' : '\"';
        serializeWriter.append(c7);
        int i8 = gregorianCalendar.get(1);
        int i9 = gregorianCalendar.get(2) + 1;
        int i10 = gregorianCalendar.get(5);
        int i11 = gregorianCalendar.get(11);
        int i12 = gregorianCalendar.get(12);
        int i13 = gregorianCalendar.get(13);
        int i14 = gregorianCalendar.get(14);
        if (i14 != 0) {
            charArray = "0000-00-00T00:00:00.000".toCharArray();
            IOUtils.getChars(i14, 23, charArray);
            IOUtils.getChars(i13, 19, charArray);
            IOUtils.getChars(i12, 16, charArray);
            IOUtils.getChars(i11, 13, charArray);
            IOUtils.getChars(i10, 10, charArray);
            IOUtils.getChars(i9, 7, charArray);
            IOUtils.getChars(i8, 4, charArray);
        } else if (i13 == 0 && i12 == 0 && i11 == 0) {
            charArray = "0000-00-00".toCharArray();
            IOUtils.getChars(i10, 10, charArray);
            IOUtils.getChars(i9, 7, charArray);
            IOUtils.getChars(i8, 4, charArray);
        } else {
            charArray = "0000-00-00T00:00:00".toCharArray();
            IOUtils.getChars(i13, 19, charArray);
            IOUtils.getChars(i12, 16, charArray);
            IOUtils.getChars(i11, 13, charArray);
            IOUtils.getChars(i10, 10, charArray);
            IOUtils.getChars(i9, 7, charArray);
            IOUtils.getChars(i8, 4, charArray);
        }
        serializeWriter.write(charArray);
        int offset = gregorianCalendar.getTimeZone().getOffset(gregorianCalendar.getTimeInMillis()) / 3600000;
        if (offset == 0) {
            serializeWriter.append((CharSequence) "Z");
        } else if (offset > 0) {
            serializeWriter.append((CharSequence) "+").append((CharSequence) String.format("%02d", Integer.valueOf(offset))).append((CharSequence) ":00");
        } else {
            serializeWriter.append((CharSequence) "-").append((CharSequence) String.format("%02d", Integer.valueOf(-offset))).append((CharSequence) ":00");
        }
        serializeWriter.append(c7);
    }
}
