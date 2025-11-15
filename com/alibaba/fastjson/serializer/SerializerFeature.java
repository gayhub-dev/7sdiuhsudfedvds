package com.alibaba.fastjson.serializer;

/* loaded from: classes.dex */
public enum SerializerFeature {
    QuoteFieldNames,
    UseSingleQuotes,
    WriteMapNullValue,
    WriteEnumUsingToString,
    WriteEnumUsingName,
    UseISO8601DateFormat,
    WriteNullListAsEmpty,
    WriteNullStringAsEmpty,
    WriteNullNumberAsZero,
    WriteNullBooleanAsFalse,
    SkipTransientField,
    SortField,
    WriteTabAsSpecial,
    PrettyFormat,
    WriteClassName,
    DisableCircularReferenceDetect,
    WriteSlashAsSpecial,
    BrowserCompatible,
    WriteDateUseDateFormat,
    NotWriteRootClassName,
    DisableCheckSpecialChar,
    BeanToArray,
    WriteNonStringKeyAsString,
    NotWriteDefaultValue,
    BrowserSecure,
    IgnoreNonFieldGetter,
    WriteNonStringValueAsString,
    IgnoreErrorGetter,
    WriteBigDecimalAsPlain,
    MapSortField;

    public static final SerializerFeature[] EMPTY;
    public static final int WRITE_MAP_NULL_FEATURES;
    public final int mask = 1 << ordinal();

    static {
        SerializerFeature serializerFeature = WriteMapNullValue;
        SerializerFeature serializerFeature2 = WriteNullListAsEmpty;
        SerializerFeature serializerFeature3 = WriteNullStringAsEmpty;
        SerializerFeature serializerFeature4 = WriteNullNumberAsZero;
        SerializerFeature serializerFeature5 = WriteNullBooleanAsFalse;
        EMPTY = new SerializerFeature[0];
        WRITE_MAP_NULL_FEATURES = serializerFeature.getMask() | serializerFeature5.getMask() | serializerFeature2.getMask() | serializerFeature4.getMask() | serializerFeature3.getMask();
    }

    SerializerFeature() {
    }

    public static int config(int i7, SerializerFeature serializerFeature, boolean z6) {
        return z6 ? i7 | serializerFeature.mask : i7 & (~serializerFeature.mask);
    }

    public static boolean isEnabled(int i7, SerializerFeature serializerFeature) {
        return (i7 & serializerFeature.mask) != 0;
    }

    /* renamed from: of */
    public static int m342of(SerializerFeature[] serializerFeatureArr) {
        if (serializerFeatureArr == null) {
            return 0;
        }
        int i7 = 0;
        for (SerializerFeature serializerFeature : serializerFeatureArr) {
            i7 |= serializerFeature.mask;
        }
        return i7;
    }

    public final int getMask() {
        return this.mask;
    }

    public static boolean isEnabled(int i7, int i8, SerializerFeature serializerFeature) {
        int i9 = serializerFeature.mask;
        return ((i7 & i9) == 0 && (i8 & i9) == 0) ? false : true;
    }
}
