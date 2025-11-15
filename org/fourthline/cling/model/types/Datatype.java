package org.fourthline.cling.model.types;

import android.support.v4.app.NotificationCompat;
import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public interface Datatype<V> {

    public enum Builtin {
        UI1("ui1", new UnsignedIntegerOneByteDatatype()),
        UI2("ui2", new UnsignedIntegerTwoBytesDatatype()),
        UI4("ui4", new UnsignedIntegerFourBytesDatatype()),
        I1("i1", new IntegerDatatype(1)),
        I2("i2", new IntegerDatatype(2)),
        I2_SHORT("i2", new ShortDatatype()),
        I4("i4", new IntegerDatatype(4)),
        INT("int", new IntegerDatatype(4)),
        R4("r4", new FloatDatatype()),
        R8("r8", new DoubleDatatype()),
        NUMBER("number", new DoubleDatatype()),
        FIXED144("fixed.14.4", new DoubleDatatype()),
        FLOAT("float", new DoubleDatatype()),
        CHAR("char", new CharacterDatatype()),
        STRING("string", new StringDatatype()),
        DATE("date", new DateTimeDatatype(new String[]{"yyyy-MM-dd"}, "yyyy-MM-dd")),
        DATETIME("dateTime", new DateTimeDatatype(new String[]{"yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss"}, "yyyy-MM-dd'T'HH:mm:ss")),
        DATETIME_TZ("dateTime.tz", new DateTimeDatatype(new String[]{"yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ssZ"}, "yyyy-MM-dd'T'HH:mm:ssZ")),
        TIME("time", new DateTimeDatatype(new String[]{"HH:mm:ss"}, "HH:mm:ss")),
        TIME_TZ("time.tz", new DateTimeDatatype(new String[]{"HH:mm:ssZ", "HH:mm:ss"}, "HH:mm:ssZ")),
        BOOLEAN("boolean", new BooleanDatatype()),
        BIN_BASE64("bin.base64", new Base64Datatype()),
        BIN_HEX("bin.hex", new BinHexDatatype()),
        URI(NotificationCompat.MessagingStyle.Message.KEY_DATA_URI, new URIDatatype()),
        UUID("uuid", new StringDatatype());

        private static Map<String, Builtin> byName = new HashMap<String, Builtin>() { // from class: org.fourthline.cling.model.types.Datatype.Builtin.1
            {
                for (Builtin builtin : Builtin.values()) {
                    String descriptorName = builtin.getDescriptorName();
                    Locale locale = Locale.ROOT;
                    if (!containsKey(descriptorName.toLowerCase(locale))) {
                        put(builtin.getDescriptorName().toLowerCase(locale), builtin);
                    }
                }
            }
        };
        private Datatype datatype;
        private String descriptorName;

        Builtin(String str, AbstractDatatype abstractDatatype) {
            abstractDatatype.setBuiltin(this);
            this.descriptorName = str;
            this.datatype = abstractDatatype;
        }

        public static Builtin getByDescriptorName(String str) {
            if (str == null) {
                return null;
            }
            return byName.get(str.toLowerCase(Locale.ROOT));
        }

        public static boolean isNumeric(Builtin builtin) {
            return builtin != null && (builtin.equals(UI1) || builtin.equals(UI2) || builtin.equals(UI4) || builtin.equals(I1) || builtin.equals(I2) || builtin.equals(I4) || builtin.equals(INT));
        }

        public Datatype getDatatype() {
            return this.datatype;
        }

        public String getDescriptorName() {
            return this.descriptorName;
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'BOOLEAN' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class Default {
        private static final /* synthetic */ Default[] $VALUES;
        public static final Default BOOLEAN;
        public static final Default BOOLEAN_PRIMITIVE;
        public static final Default BYTES;
        public static final Default CALENDAR;
        public static final Default CHAR;
        public static final Default CHAR_PRIMITIVE;
        public static final Default DOUBLE;
        public static final Default DOUBLE_PRIMTIIVE;
        public static final Default FLOAT;
        public static final Default FLOAT_PRIMITIVE;
        public static final Default INTEGER;
        public static final Default INTEGER_PRIMITIVE;
        public static final Default SHORT;
        public static final Default SHORT_PRIMITIVE;
        public static final Default STRING;
        public static final Default UNSIGNED_INTEGER_FOUR_BYTES;
        public static final Default UNSIGNED_INTEGER_ONE_BYTE;
        public static final Default UNSIGNED_INTEGER_TWO_BYTES;
        public static final Default URI;
        private Builtin builtinType;
        private Class javaType;

        private static /* synthetic */ Default[] $values() {
            return new Default[]{BOOLEAN, BOOLEAN_PRIMITIVE, SHORT, SHORT_PRIMITIVE, INTEGER, INTEGER_PRIMITIVE, UNSIGNED_INTEGER_ONE_BYTE, UNSIGNED_INTEGER_TWO_BYTES, UNSIGNED_INTEGER_FOUR_BYTES, FLOAT, FLOAT_PRIMITIVE, DOUBLE, DOUBLE_PRIMTIIVE, CHAR, CHAR_PRIMITIVE, STRING, CALENDAR, BYTES, URI};
        }

        static {
            Builtin builtin = Builtin.BOOLEAN;
            BOOLEAN = new Default("BOOLEAN", 0, Boolean.class, builtin);
            BOOLEAN_PRIMITIVE = new Default("BOOLEAN_PRIMITIVE", 1, Boolean.TYPE, builtin);
            Builtin builtin2 = Builtin.I2_SHORT;
            SHORT = new Default("SHORT", 2, Short.class, builtin2);
            SHORT_PRIMITIVE = new Default("SHORT_PRIMITIVE", 3, Short.TYPE, builtin2);
            Builtin builtin3 = Builtin.I4;
            INTEGER = new Default("INTEGER", 4, Integer.class, builtin3);
            INTEGER_PRIMITIVE = new Default("INTEGER_PRIMITIVE", 5, Integer.TYPE, builtin3);
            UNSIGNED_INTEGER_ONE_BYTE = new Default("UNSIGNED_INTEGER_ONE_BYTE", 6, UnsignedIntegerOneByte.class, Builtin.UI1);
            UNSIGNED_INTEGER_TWO_BYTES = new Default("UNSIGNED_INTEGER_TWO_BYTES", 7, UnsignedIntegerTwoBytes.class, Builtin.UI2);
            UNSIGNED_INTEGER_FOUR_BYTES = new Default("UNSIGNED_INTEGER_FOUR_BYTES", 8, UnsignedIntegerFourBytes.class, Builtin.UI4);
            Builtin builtin4 = Builtin.R4;
            FLOAT = new Default("FLOAT", 9, Float.class, builtin4);
            FLOAT_PRIMITIVE = new Default("FLOAT_PRIMITIVE", 10, Float.TYPE, builtin4);
            Builtin builtin5 = Builtin.FLOAT;
            DOUBLE = new Default("DOUBLE", 11, Double.class, builtin5);
            DOUBLE_PRIMTIIVE = new Default("DOUBLE_PRIMTIIVE", 12, Double.TYPE, builtin5);
            Builtin builtin6 = Builtin.CHAR;
            CHAR = new Default("CHAR", 13, Character.class, builtin6);
            CHAR_PRIMITIVE = new Default("CHAR_PRIMITIVE", 14, Character.TYPE, builtin6);
            STRING = new Default("STRING", 15, String.class, Builtin.STRING);
            CALENDAR = new Default("CALENDAR", 16, Calendar.class, Builtin.DATETIME);
            BYTES = new Default("BYTES", 17, byte[].class, Builtin.BIN_BASE64);
            URI = new Default("URI", 18, URI.class, Builtin.URI);
            $VALUES = $values();
        }

        private Default(String str, int i7, Class cls, Builtin builtin) {
            this.javaType = cls;
            this.builtinType = builtin;
        }

        public static Default getByJavaType(Class cls) {
            for (Default r32 : values()) {
                if (r32.getJavaType().equals(cls)) {
                    return r32;
                }
            }
            return null;
        }

        public static Default valueOf(String str) {
            return (Default) Enum.valueOf(Default.class, str);
        }

        public static Default[] values() {
            return (Default[]) $VALUES.clone();
        }

        public Builtin getBuiltinType() {
            return this.builtinType;
        }

        public Class getJavaType() {
            return this.javaType;
        }

        @Override // java.lang.Enum
        public String toString() {
            return getJavaType() + " => " + getBuiltinType();
        }
    }

    Builtin getBuiltin();

    String getDisplayString();

    String getString(V v6);

    boolean isHandlingJavaType(Class cls);

    boolean isValid(V v6);

    V valueOf(String str);
}
