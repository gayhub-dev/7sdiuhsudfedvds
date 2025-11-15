package com.alibaba.fastjson.parser;

/* loaded from: classes.dex */
public enum Feature {
    AutoCloseSource,
    AllowComment,
    AllowUnQuotedFieldNames,
    AllowSingleQuotes,
    InternFieldNames,
    AllowISO8601DateFormat,
    AllowArbitraryCommas,
    UseBigDecimal,
    IgnoreNotMatch,
    SortFeidFastMatch,
    DisableASM,
    DisableCircularReferenceDetect,
    InitStringFieldAsEmpty,
    SupportArrayToBean,
    OrderedField,
    DisableSpecialKeyDetect,
    UseObjectArray,
    SupportNonPublicField,
    IgnoreAutoType,
    DisableFieldSmartMatch,
    SupportAutoType,
    NonStringKeyAsString,
    CustomMapDeserializer;

    public final int mask = 1 << ordinal();

    Feature() {
    }

    public static int config(int i7, Feature feature, boolean z6) {
        return z6 ? i7 | feature.mask : i7 & (~feature.mask);
    }

    public static boolean isEnabled(int i7, Feature feature) {
        return (i7 & feature.mask) != 0;
    }

    /* renamed from: of */
    public static int m339of(Feature[] featureArr) {
        if (featureArr == null) {
            return 0;
        }
        int i7 = 0;
        for (Feature feature : featureArr) {
            i7 |= feature.mask;
        }
        return i7;
    }

    public final int getMask() {
        return this.mask;
    }
}
