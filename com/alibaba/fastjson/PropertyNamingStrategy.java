package com.alibaba.fastjson;

/* loaded from: classes.dex */
public enum PropertyNamingStrategy {
    CamelCase,
    PascalCase,
    SnakeCase,
    KebabCase;

    /* renamed from: com.alibaba.fastjson.PropertyNamingStrategy$1 */
    public static /* synthetic */ class C05291 {
        public static final /* synthetic */ int[] $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy;

        static {
            int[] iArr = new int[PropertyNamingStrategy.values().length];
            $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy = iArr;
            try {
                iArr[PropertyNamingStrategy.SnakeCase.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy[PropertyNamingStrategy.KebabCase.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy[PropertyNamingStrategy.PascalCase.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy[PropertyNamingStrategy.CamelCase.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public String translate(String str) {
        char cCharAt;
        int i7 = C05291.$SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy[ordinal()];
        int i8 = 0;
        if (i7 == 1) {
            StringBuilder sb = new StringBuilder();
            while (i8 < str.length()) {
                char cCharAt2 = str.charAt(i8);
                if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                    sb.append(cCharAt2);
                } else {
                    char c7 = (char) (cCharAt2 + ' ');
                    if (i8 > 0) {
                        sb.append('_');
                    }
                    sb.append(c7);
                }
                i8++;
            }
            return sb.toString();
        }
        if (i7 == 2) {
            StringBuilder sb2 = new StringBuilder();
            while (i8 < str.length()) {
                char cCharAt3 = str.charAt(i8);
                if (cCharAt3 < 'A' || cCharAt3 > 'Z') {
                    sb2.append(cCharAt3);
                } else {
                    char c8 = (char) (cCharAt3 + ' ');
                    if (i8 > 0) {
                        sb2.append('-');
                    }
                    sb2.append(c8);
                }
                i8++;
            }
            return sb2.toString();
        }
        if (i7 != 3) {
            if (i7 != 4 || (cCharAt = str.charAt(0)) < 'A' || cCharAt > 'Z') {
                return str;
            }
            char[] charArray = str.toCharArray();
            charArray[0] = (char) (charArray[0] + ' ');
            return new String(charArray);
        }
        char cCharAt4 = str.charAt(0);
        if (cCharAt4 < 'a' || cCharAt4 > 'z') {
            return str;
        }
        char[] charArray2 = str.toCharArray();
        charArray2[0] = (char) (charArray2[0] - ' ');
        return new String(charArray2);
    }
}
