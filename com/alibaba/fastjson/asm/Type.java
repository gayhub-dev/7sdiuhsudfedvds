package com.alibaba.fastjson.asm;

import okhttp3.HttpUrl;

/* loaded from: classes.dex */
public class Type {
    private final char[] buf;
    private final int len;
    private final int off;
    public final int sort;
    public static final Type VOID_TYPE = new Type(0, null, 1443168256, 1);
    public static final Type BOOLEAN_TYPE = new Type(1, null, 1509950721, 1);
    public static final Type CHAR_TYPE = new Type(2, null, 1124075009, 1);
    public static final Type BYTE_TYPE = new Type(3, null, 1107297537, 1);
    public static final Type SHORT_TYPE = new Type(4, null, 1392510721, 1);
    public static final Type INT_TYPE = new Type(5, null, 1224736769, 1);
    public static final Type FLOAT_TYPE = new Type(6, null, 1174536705, 1);
    public static final Type LONG_TYPE = new Type(7, null, 1241579778, 1);
    public static final Type DOUBLE_TYPE = new Type(8, null, 1141048066, 1);

    private Type(int i7, char[] cArr, int i8, int i9) {
        this.sort = i7;
        this.buf = cArr;
        this.off = i8;
        this.len = i9;
    }

    public static Type[] getArgumentTypes(String str) {
        char[] charArray = str.toCharArray();
        int i7 = 1;
        int i8 = 1;
        int i9 = 0;
        while (true) {
            int i10 = i8 + 1;
            char c7 = charArray[i8];
            if (c7 == ')') {
                break;
            }
            if (c7 == 'L') {
                while (true) {
                    i8 = i10 + 1;
                    if (charArray[i10] == ';') {
                        break;
                    }
                    i10 = i8;
                }
                i9++;
            } else {
                if (c7 != '[') {
                    i9++;
                }
                i8 = i10;
            }
        }
        Type[] typeArr = new Type[i9];
        int i11 = 0;
        while (charArray[i7] != ')') {
            typeArr[i11] = getType(charArray, i7);
            i7 += typeArr[i11].len + (typeArr[i11].sort == 10 ? 2 : 0);
            i11++;
        }
        return typeArr;
    }

    public static int getArgumentsAndReturnSizes(String str) {
        int i7;
        int i8 = 1;
        int i9 = 1;
        int i10 = 1;
        while (true) {
            i7 = i9 + 1;
            char cCharAt = str.charAt(i9);
            if (cCharAt == ')') {
                break;
            }
            if (cCharAt == 'L') {
                while (true) {
                    i9 = i7 + 1;
                    if (str.charAt(i7) == ';') {
                        break;
                    }
                    i7 = i9;
                }
                i10++;
            } else {
                i10 = (cCharAt == 'D' || cCharAt == 'J') ? i10 + 2 : i10 + 1;
                i9 = i7;
            }
        }
        char cCharAt2 = str.charAt(i7);
        int i11 = i10 << 2;
        if (cCharAt2 == 'V') {
            i8 = 0;
        } else if (cCharAt2 == 'D' || cCharAt2 == 'J') {
            i8 = 2;
        }
        return i11 | i8;
    }

    private int getDimensions() {
        int i7 = 1;
        while (this.buf[this.off + i7] == '[') {
            i7++;
        }
        return i7;
    }

    public static Type getType(String str) {
        return getType(str.toCharArray(), 0);
    }

    public String getClassName() {
        switch (this.sort) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "char";
            case 3:
                return "byte";
            case 4:
                return "short";
            case 5:
                return "int";
            case 6:
                return "float";
            case 7:
                return "long";
            case 8:
                return "double";
            case 9:
                StringBuilder sb = new StringBuilder(getType(this.buf, this.off + getDimensions()).getClassName());
                for (int dimensions = getDimensions(); dimensions > 0; dimensions--) {
                    sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                }
                return sb.toString();
            default:
                return new String(this.buf, this.off, this.len).replace('/', '.');
        }
    }

    public String getDescriptor() {
        return new String(this.buf, this.off, this.len);
    }

    public String getInternalName() {
        return new String(this.buf, this.off, this.len);
    }

    private static Type getType(char[] cArr, int i7) {
        int i8;
        char c7 = cArr[i7];
        if (c7 == 'F') {
            return FLOAT_TYPE;
        }
        if (c7 == 'S') {
            return SHORT_TYPE;
        }
        if (c7 == 'V') {
            return VOID_TYPE;
        }
        if (c7 == 'I') {
            return INT_TYPE;
        }
        if (c7 == 'J') {
            return LONG_TYPE;
        }
        if (c7 == 'Z') {
            return BOOLEAN_TYPE;
        }
        if (c7 != '[') {
            switch (c7) {
                case 'B':
                    return BYTE_TYPE;
                case 'C':
                    return CHAR_TYPE;
                case 'D':
                    return DOUBLE_TYPE;
                default:
                    int i9 = 1;
                    while (cArr[i7 + i9] != ';') {
                        i9++;
                    }
                    return new Type(10, cArr, i7 + 1, i9 - 1);
            }
        }
        int i10 = 1;
        while (true) {
            i8 = i7 + i10;
            if (cArr[i8] != '[') {
                break;
            }
            i10++;
        }
        if (cArr[i8] == 'L') {
            do {
                i10++;
            } while (cArr[i7 + i10] != ';');
        }
        return new Type(9, cArr, i7, i10 + 1);
    }
}
