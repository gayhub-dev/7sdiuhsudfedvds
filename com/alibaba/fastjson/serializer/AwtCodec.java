package com.alibaba.fastjson.serializer;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.Key;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.lang.reflect.Type;
import p009b.C0413b;

/* loaded from: classes.dex */
public class AwtCodec implements ObjectSerializer, ObjectDeserializer {
    public static final AwtCodec instance = new AwtCodec();

    private Object parseRef(DefaultJSONParser defaultJSONParser, Object obj) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        lexer.nextTokenWithColon(4);
        String strStringVal = lexer.stringVal();
        defaultJSONParser.setContext(defaultJSONParser.getContext(), obj);
        defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(defaultJSONParser.getContext(), strStringVal));
        defaultJSONParser.popContext();
        defaultJSONParser.setResolveStatus(1);
        lexer.nextToken(13);
        defaultJSONParser.accept(13);
        return null;
    }

    public static boolean support(Class<?> cls) {
        return cls == Point.class || cls == Rectangle.class || cls == Font.class || cls == Color.class;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t6;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        if (jSONLexer.token() != 12 && jSONLexer.token() != 16) {
            throw new JSONException("syntax error");
        }
        jSONLexer.nextToken();
        if (type == Point.class) {
            t6 = (T) parsePoint(defaultJSONParser, obj);
        } else if (type == Rectangle.class) {
            t6 = (T) parseRectangle(defaultJSONParser);
        } else if (type == Color.class) {
            t6 = (T) parseColor(defaultJSONParser);
        } else {
            if (type != Font.class) {
                throw new JSONException("not support awt class : " + type);
            }
            t6 = (T) parseFont(defaultJSONParser);
        }
        ParseContext context = defaultJSONParser.getContext();
        defaultJSONParser.setContext(t6, obj);
        defaultJSONParser.setContext(context);
        return t6;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public Color parseColor(DefaultJSONParser defaultJSONParser) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            String strStringVal = jSONLexer.stringVal();
            jSONLexer.nextTokenWithColon(2);
            if (jSONLexer.token() != 2) {
                throw new JSONException("syntax error");
            }
            int iIntValue = jSONLexer.intValue();
            jSONLexer.nextToken();
            if (strStringVal.equalsIgnoreCase("r")) {
                i7 = iIntValue;
            } else if (strStringVal.equalsIgnoreCase("g")) {
                i8 = iIntValue;
            } else if (strStringVal.equalsIgnoreCase("b")) {
                i9 = iIntValue;
            } else {
                if (!strStringVal.equalsIgnoreCase(Key.ALPHA)) {
                    throw new JSONException(C0063n.m88a("syntax error, ", strStringVal));
                }
                i10 = iIntValue;
            }
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken(4);
            }
        }
        jSONLexer.nextToken();
        return new Color(i7, i8, i9, i10);
    }

    public Font parseFont(DefaultJSONParser defaultJSONParser) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int iIntValue = 0;
        String strStringVal = null;
        int iIntValue2 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            String strStringVal2 = jSONLexer.stringVal();
            jSONLexer.nextTokenWithColon(2);
            if (strStringVal2.equalsIgnoreCase("name")) {
                if (jSONLexer.token() != 4) {
                    throw new JSONException("syntax error");
                }
                strStringVal = jSONLexer.stringVal();
                jSONLexer.nextToken();
            } else if (strStringVal2.equalsIgnoreCase("style")) {
                if (jSONLexer.token() != 2) {
                    throw new JSONException("syntax error");
                }
                iIntValue = jSONLexer.intValue();
                jSONLexer.nextToken();
            } else {
                if (!strStringVal2.equalsIgnoreCase("size")) {
                    throw new JSONException(C0063n.m88a("syntax error, ", strStringVal2));
                }
                if (jSONLexer.token() != 2) {
                    throw new JSONException("syntax error");
                }
                iIntValue2 = jSONLexer.intValue();
                jSONLexer.nextToken();
            }
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken(4);
            }
        }
        jSONLexer.nextToken();
        return new Font(strStringVal, iIntValue, iIntValue2);
    }

    public Point parsePoint(DefaultJSONParser defaultJSONParser, Object obj) {
        int iFloatValue;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i7 = 0;
        int i8 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            String strStringVal = jSONLexer.stringVal();
            if (JSON.DEFAULT_TYPE_KEY.equals(strStringVal)) {
                defaultJSONParser.acceptType("java.awt.Point");
            } else {
                if ("$ref".equals(strStringVal)) {
                    return (Point) parseRef(defaultJSONParser, obj);
                }
                jSONLexer.nextTokenWithColon(2);
                int i9 = jSONLexer.token();
                if (i9 == 2) {
                    iFloatValue = jSONLexer.intValue();
                    jSONLexer.nextToken();
                } else {
                    if (i9 != 3) {
                        StringBuilder sbM112a = C0413b.m112a("syntax error : ");
                        sbM112a.append(jSONLexer.tokenName());
                        throw new JSONException(sbM112a.toString());
                    }
                    iFloatValue = (int) jSONLexer.floatValue();
                    jSONLexer.nextToken();
                }
                if (strStringVal.equalsIgnoreCase("x")) {
                    i7 = iFloatValue;
                } else {
                    if (!strStringVal.equalsIgnoreCase("y")) {
                        throw new JSONException(C0063n.m88a("syntax error, ", strStringVal));
                    }
                    i8 = iFloatValue;
                }
                if (jSONLexer.token() == 16) {
                    jSONLexer.nextToken(4);
                }
            }
        }
        jSONLexer.nextToken();
        return new Point(i7, i8);
    }

    public Rectangle parseRectangle(DefaultJSONParser defaultJSONParser) {
        int iFloatValue;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            String strStringVal = jSONLexer.stringVal();
            jSONLexer.nextTokenWithColon(2);
            int i11 = jSONLexer.token();
            if (i11 == 2) {
                iFloatValue = jSONLexer.intValue();
                jSONLexer.nextToken();
            } else {
                if (i11 != 3) {
                    throw new JSONException("syntax error");
                }
                iFloatValue = (int) jSONLexer.floatValue();
                jSONLexer.nextToken();
            }
            if (strStringVal.equalsIgnoreCase("x")) {
                i7 = iFloatValue;
            } else if (strStringVal.equalsIgnoreCase("y")) {
                i8 = iFloatValue;
            } else if (strStringVal.equalsIgnoreCase("width")) {
                i9 = iFloatValue;
            } else {
                if (!strStringVal.equalsIgnoreCase("height")) {
                    throw new JSONException(C0063n.m88a("syntax error, ", strStringVal));
                }
                i10 = iFloatValue;
            }
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken(4);
            }
        }
        jSONLexer.nextToken();
        return new Rectangle(i7, i8, i9, i10);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (obj instanceof Point) {
            Point point = (Point) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Point.class, '{'), "x", point.x);
            serializeWriter.writeFieldValue(',', "y", point.y);
        } else if (obj instanceof Font) {
            Font font = (Font) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Font.class, '{'), "name", font.getName());
            serializeWriter.writeFieldValue(',', "style", font.getStyle());
            serializeWriter.writeFieldValue(',', "size", font.getSize());
        } else if (obj instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Rectangle.class, '{'), "x", rectangle.x);
            serializeWriter.writeFieldValue(',', "y", rectangle.y);
            serializeWriter.writeFieldValue(',', "width", rectangle.width);
            serializeWriter.writeFieldValue(',', "height", rectangle.height);
        } else {
            if (!(obj instanceof Color)) {
                StringBuilder sbM112a = C0413b.m112a("not support awt class : ");
                sbM112a.append(obj.getClass().getName());
                throw new JSONException(sbM112a.toString());
            }
            Color color = (Color) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Color.class, '{'), "r", color.getRed());
            serializeWriter.writeFieldValue(',', "g", color.getGreen());
            serializeWriter.writeFieldValue(',', "b", color.getBlue());
            if (color.getAlpha() > 0) {
                serializeWriter.writeFieldValue(',', Key.ALPHA, color.getAlpha());
            }
        }
        serializeWriter.write(125);
    }

    public char writeClassName(SerializeWriter serializeWriter, Class<?> cls, char c7) {
        if (!serializeWriter.isEnabled(SerializerFeature.WriteClassName)) {
            return c7;
        }
        serializeWriter.write(123);
        serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
        serializeWriter.writeString(cls.getName());
        return ',';
    }
}
