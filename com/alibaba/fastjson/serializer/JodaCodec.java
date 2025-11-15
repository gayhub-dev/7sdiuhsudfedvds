package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import p016b6.AbstractC0475f;
import p016b6.AbstractC0478i;
import p016b6.C0470a;
import p016b6.C0476g;
import p016b6.C0481l;
import p016b6.C0482m;
import p016b6.C0483n;
import p016b6.C0484o;
import p016b6.C0487r;
import p016b6.InterfaceC0493x;
import p034d6.C0885l;
import p058g6.C1063a;
import p058g6.C1064b;
import p058g6.C1071i;
import p159t3.AbstractC1904c;
import p186x2.C2074b;
import p203z5.C2158b;

/* loaded from: classes.dex */
public class JodaCodec implements ObjectSerializer, ContextObjectSerializer, ObjectDeserializer {
    private static final String formatter_iso8601_pattern_23 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    private static final String formatter_iso8601_pattern_29 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";
    public static final JodaCodec instance = new JodaCodec();
    private static final String defaultPatttern = "yyyy-MM-dd HH:mm:ss";
    private static final C1064b defaultFormatter = C1063a.m1055a(defaultPatttern);
    private static final C1064b defaultFormatter_23 = C1063a.m1055a("yyyy-MM-dd HH:mm:ss.SSS");
    private static final C1064b formatter_dt19_tw = C1063a.m1055a("yyyy/MM/dd HH:mm:ss");
    private static final C1064b formatter_dt19_cn = C1063a.m1055a("yyyy年M月d日 HH:mm:ss");
    private static final C1064b formatter_dt19_cn_1 = C1063a.m1055a("yyyy年M月d日 H时m分s秒");
    private static final C1064b formatter_dt19_kr = C1063a.m1055a("yyyy년M월d일 HH:mm:ss");
    private static final C1064b formatter_dt19_us = C1063a.m1055a("MM/dd/yyyy HH:mm:ss");
    private static final C1064b formatter_dt19_eur = C1063a.m1055a("dd/MM/yyyy HH:mm:ss");
    private static final C1064b formatter_dt19_de = C1063a.m1055a("dd.MM.yyyy HH:mm:ss");
    private static final C1064b formatter_dt19_in = C1063a.m1055a("dd-MM-yyyy HH:mm:ss");
    private static final C1064b formatter_d8 = C1063a.m1055a("yyyyMMdd");
    private static final C1064b formatter_d10_tw = C1063a.m1055a("yyyy/MM/dd");
    private static final C1064b formatter_d10_cn = C1063a.m1055a("yyyy年M月d日");
    private static final C1064b formatter_d10_kr = C1063a.m1055a("yyyy년M월d일");
    private static final C1064b formatter_d10_us = C1063a.m1055a("MM/dd/yyyy");
    private static final C1064b formatter_d10_eur = C1063a.m1055a("dd/MM/yyyy");
    private static final C1064b formatter_d10_de = C1063a.m1055a("dd.MM.yyyy");
    private static final C1064b formatter_d10_in = C1063a.m1055a("dd-MM-yyyy");
    private static final C1064b ISO_FIXED_FORMAT = C1063a.m1055a(defaultPatttern).m1066i(AbstractC0475f.m235g());
    private static final String formatter_iso8601_pattern = "yyyy-MM-dd'T'HH:mm:ss";
    private static final C1064b formatter_iso8601 = C1063a.m1055a(formatter_iso8601_pattern);

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, null, 0);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 4;
    }

    public C0483n parseDateTime(String str, C1064b c1064b) {
        if (c1064b == null) {
            if (str.length() == 19) {
                char cCharAt = str.charAt(4);
                char cCharAt2 = str.charAt(7);
                char cCharAt3 = str.charAt(10);
                char cCharAt4 = str.charAt(13);
                char cCharAt5 = str.charAt(16);
                if (cCharAt4 == ':' && cCharAt5 == ':') {
                    if (cCharAt == '-' && cCharAt2 == '-') {
                        if (cCharAt3 == 'T') {
                            c1064b = formatter_iso8601;
                        } else if (cCharAt3 == ' ') {
                            c1064b = defaultFormatter;
                        }
                    } else if (cCharAt == '-' && cCharAt2 == '-') {
                        c1064b = defaultFormatter;
                    } else if (cCharAt == '/' && cCharAt2 == '/') {
                        c1064b = formatter_dt19_tw;
                    } else {
                        char cCharAt6 = str.charAt(0);
                        char cCharAt7 = str.charAt(1);
                        char cCharAt8 = str.charAt(2);
                        char cCharAt9 = str.charAt(3);
                        char cCharAt10 = str.charAt(5);
                        if (cCharAt8 == '/' && cCharAt10 == '/') {
                            int i7 = (cCharAt - '0') + ((cCharAt9 - '0') * 10);
                            if ((cCharAt7 - '0') + ((cCharAt6 - '0') * 10) > 12) {
                                c1064b = formatter_dt19_eur;
                            } else if (i7 > 12) {
                                c1064b = formatter_dt19_us;
                            } else {
                                String country = Locale.getDefault().getCountry();
                                if (country.equals("US")) {
                                    c1064b = formatter_dt19_us;
                                } else if (country.equals("BR") || country.equals("AU")) {
                                    c1064b = formatter_dt19_eur;
                                }
                            }
                        } else if (cCharAt8 == '.' && cCharAt10 == '.') {
                            c1064b = formatter_dt19_de;
                        } else if (cCharAt8 == '-' && cCharAt10 == '-') {
                            c1064b = formatter_dt19_in;
                        }
                    }
                }
            } else if (str.length() == 23) {
                char cCharAt11 = str.charAt(4);
                char cCharAt12 = str.charAt(7);
                char cCharAt13 = str.charAt(10);
                char cCharAt14 = str.charAt(13);
                char cCharAt15 = str.charAt(16);
                char cCharAt16 = str.charAt(19);
                if (cCharAt14 == ':' && cCharAt15 == ':' && cCharAt11 == '-' && cCharAt12 == '-' && cCharAt13 == ' ' && cCharAt16 == '.') {
                    c1064b = defaultFormatter_23;
                }
            }
            if (str.length() >= 17) {
                char cCharAt17 = str.charAt(4);
                if (cCharAt17 == 24180) {
                    c1064b = str.charAt(str.length() - 1) == 31186 ? formatter_dt19_cn_1 : formatter_dt19_cn;
                } else if (cCharAt17 == 45380) {
                    c1064b = formatter_dt19_kr;
                }
            }
        }
        return c1064b == null ? C0483n.m268c(str) : c1064b.m1060c(str);
    }

    public C0482m parseLocalDate(String str, String str2, C1064b c1064b) {
        C1064b c1064b2;
        if (c1064b == null) {
            if (str.length() == 8) {
                c1064b = formatter_d8;
            }
            if (str.length() == 10) {
                char cCharAt = str.charAt(4);
                char cCharAt2 = str.charAt(7);
                if (cCharAt == '/' && cCharAt2 == '/') {
                    c1064b = formatter_d10_tw;
                }
                char cCharAt3 = str.charAt(0);
                char cCharAt4 = str.charAt(1);
                char cCharAt5 = str.charAt(2);
                char cCharAt6 = str.charAt(3);
                char cCharAt7 = str.charAt(5);
                if (cCharAt5 == '/' && cCharAt7 == '/') {
                    int i7 = (cCharAt - '0') + ((cCharAt6 - '0') * 10);
                    if ((cCharAt4 - '0') + ((cCharAt3 - '0') * 10) > 12) {
                        c1064b2 = formatter_d10_eur;
                    } else if (i7 > 12) {
                        c1064b2 = formatter_d10_us;
                    } else {
                        String country = Locale.getDefault().getCountry();
                        if (country.equals("US")) {
                            c1064b2 = formatter_d10_us;
                        } else if (country.equals("BR") || country.equals("AU")) {
                            c1064b2 = formatter_d10_eur;
                        }
                    }
                    c1064b = c1064b2;
                } else {
                    if (cCharAt5 == '.' && cCharAt7 == '.') {
                        c1064b2 = formatter_d10_de;
                    } else if (cCharAt5 == '-' && cCharAt7 == '-') {
                        c1064b2 = formatter_d10_in;
                    }
                    c1064b = c1064b2;
                }
            }
            if (str.length() >= 9) {
                char cCharAt8 = str.charAt(4);
                if (cCharAt8 == 24180) {
                    c1064b = formatter_d10_cn;
                } else if (cCharAt8 == 45380) {
                    c1064b = formatter_d10_kr;
                }
            }
        }
        if (c1064b == null) {
            Set<AbstractC0478i> set = C0482m.f338h;
            return C1071i.f2107b0.m1060c(str).m269e();
        }
        Set<AbstractC0478i> set2 = C0482m.f338h;
        return c1064b.m1060c(str).m269e();
    }

    public C0470a parseZonedDateTime(String str, C1064b c1064b) {
        if (c1064b == null) {
            if (str.length() == 19) {
                char cCharAt = str.charAt(4);
                char cCharAt2 = str.charAt(7);
                char cCharAt3 = str.charAt(10);
                char cCharAt4 = str.charAt(13);
                char cCharAt5 = str.charAt(16);
                if (cCharAt4 == ':' && cCharAt5 == ':') {
                    if (cCharAt == '-' && cCharAt2 == '-') {
                        if (cCharAt3 == 'T') {
                            c1064b = formatter_iso8601;
                        } else if (cCharAt3 == ' ') {
                            c1064b = defaultFormatter;
                        }
                    } else if (cCharAt == '-' && cCharAt2 == '-') {
                        c1064b = defaultFormatter;
                    } else if (cCharAt == '/' && cCharAt2 == '/') {
                        c1064b = formatter_dt19_tw;
                    } else {
                        char cCharAt6 = str.charAt(0);
                        char cCharAt7 = str.charAt(1);
                        char cCharAt8 = str.charAt(2);
                        char cCharAt9 = str.charAt(3);
                        char cCharAt10 = str.charAt(5);
                        if (cCharAt8 == '/' && cCharAt10 == '/') {
                            int i7 = (cCharAt - '0') + ((cCharAt9 - '0') * 10);
                            if ((cCharAt7 - '0') + ((cCharAt6 - '0') * 10) > 12) {
                                c1064b = formatter_dt19_eur;
                            } else if (i7 > 12) {
                                c1064b = formatter_dt19_us;
                            } else {
                                String country = Locale.getDefault().getCountry();
                                if (country.equals("US")) {
                                    c1064b = formatter_dt19_us;
                                } else if (country.equals("BR") || country.equals("AU")) {
                                    c1064b = formatter_dt19_eur;
                                }
                            }
                        } else if (cCharAt8 == '.' && cCharAt10 == '.') {
                            c1064b = formatter_dt19_de;
                        } else if (cCharAt8 == '-' && cCharAt10 == '-') {
                            c1064b = formatter_dt19_in;
                        }
                    }
                }
            }
            if (str.length() >= 17) {
                char cCharAt11 = str.charAt(4);
                if (cCharAt11 == 24180) {
                    c1064b = str.charAt(str.length() - 1) == 31186 ? formatter_dt19_cn_1 : formatter_dt19_cn;
                } else if (cCharAt11 == 45380) {
                    c1064b = formatter_dt19_kr;
                }
            }
        }
        if (c1064b != null) {
            return c1064b.m1059b(str);
        }
        C1064b c1064b2 = C1071i.f2113e0;
        if (!c1064b2.f2014d) {
            c1064b2 = new C1064b(c1064b2.f2011a, c1064b2.f2012b, c1064b2.f2013c, true, c1064b2.f2015e, null, c1064b2.f2017g, c1064b2.f2018h);
        }
        return c1064b2.m1059b(str);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i7) {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (type == null) {
            type = obj.getClass();
        }
        if (type != C0483n.class) {
            serializeWriter.writeString(obj.toString());
            return;
        }
        SerializerFeature serializerFeature = SerializerFeature.UseISO8601DateFormat;
        int mask = serializerFeature.getMask();
        C0483n c0483n = (C0483n) obj;
        String dateFormatPattern = jSONSerializer.getDateFormatPattern();
        if (dateFormatPattern == null) {
            dateFormatPattern = ((i7 & mask) != 0 || jSONSerializer.isEnabled(serializerFeature)) ? formatter_iso8601_pattern : c0483n.f343f.mo714v().mo199b(c0483n.f342e) == 0 ? formatter_iso8601_pattern_23 : formatter_iso8601_pattern_29;
        }
        write(serializeWriter, c0483n, dateFormatPattern);
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [T, b6.n] */
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str, int i7) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (jSONLexer.token() != 4) {
            if (jSONLexer.token() != 2) {
                throw new UnsupportedOperationException();
            }
            long jLongValue = jSONLexer.longValue();
            jSONLexer.nextToken();
            if (type == C0470a.class) {
                return (T) new C0470a(jLongValue);
            }
            ?? r32 = (T) new C0483n(jLongValue, C0885l.m761S());
            if (type == C0483n.class) {
                return r32;
            }
            if (type == C0482m.class) {
                return (T) r32.m269e();
            }
            if (type == C0484o.class) {
                return (T) r32.m270f();
            }
            if (type == C0481l.class) {
                return (T) new C0481l(jLongValue);
            }
            throw new UnsupportedOperationException();
        }
        String strStringVal = jSONLexer.stringVal();
        jSONLexer.nextToken();
        C1064b c1064bM1055a = str != null ? defaultPatttern.equals(str) ? defaultFormatter : C1063a.m1055a(str) : null;
        if ("".equals(strStringVal)) {
            return null;
        }
        if (type == C0483n.class) {
            if (strStringVal.length() != 10 && strStringVal.length() != 8) {
                return (T) parseDateTime(strStringVal, c1064bM1055a);
            }
            C0482m localDate = parseLocalDate(strStringVal, str, c1064bM1055a);
            C0484o c0484o = C0484o.f344g;
            Objects.requireNonNull(localDate);
            if (c0484o == null) {
                throw new IllegalArgumentException("The time must not be null");
            }
            AbstractC1904c abstractC1904c = localDate.f340f;
            if (abstractC1904c == c0484o.f347f) {
                return (T) new C0483n(localDate.f339e + c0484o.f346e, abstractC1904c);
            }
            throw new IllegalArgumentException("The chronology of the time does not match");
        }
        if (type == C0482m.class) {
            return strStringVal.length() == 23 ? (T) C0483n.m268c(strStringVal).m269e() : (T) parseLocalDate(strStringVal, str, c1064bM1055a);
        }
        if (type == C0484o.class) {
            if (strStringVal.length() == 23) {
                return (T) C0483n.m268c(strStringVal).m270f();
            }
            C0484o c0484o2 = C0484o.f344g;
            return (T) C1071i.f2111d0.m1060c(strStringVal).m270f();
        }
        if (type == C0470a.class) {
            if (c1064bM1055a == defaultFormatter) {
                c1064bM1055a = ISO_FIXED_FORMAT;
            }
            return (T) parseZonedDateTime(strStringVal, c1064bM1055a);
        }
        if (type == AbstractC0475f.class) {
            return (T) AbstractC0475f.m232d(strStringVal);
        }
        if (type == C0487r.class) {
            int i8 = C0487r.f350g;
            C2158b c2158bM2467G = C2074b.m2467G();
            c2158bM2467G.m2599a();
            return (T) new C0487r(c2158bM2467G.m2602d(strStringVal));
        }
        if (type == C0476g.class) {
            return (T) new C0476g(strStringVal);
        }
        if (type == C0481l.class) {
            return (T) new C0481l(C1071i.f2113e0.m1059b(strStringVal).mo261g());
        }
        if (type == C1064b.class) {
            return (T) C1063a.m1055a(strStringVal);
        }
        return null;
    }

    @Override // com.alibaba.fastjson.serializer.ContextObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, BeanContext beanContext) {
        write(jSONSerializer.out, (InterfaceC0493x) obj, beanContext.getFormat());
    }

    private void write(SerializeWriter serializeWriter, InterfaceC0493x interfaceC0493x, String str) {
        C1064b c1064bM1055a;
        if (str == formatter_iso8601_pattern) {
            c1064bM1055a = formatter_iso8601;
        } else {
            c1064bM1055a = C1063a.m1055a(str);
        }
        serializeWriter.writeString(c1064bM1055a.m1062e(interfaceC0493x));
    }
}
