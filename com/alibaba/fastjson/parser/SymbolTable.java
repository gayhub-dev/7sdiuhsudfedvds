package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;

/* loaded from: classes.dex */
public class SymbolTable {
    private final int indexMask;
    private final String[] symbols;

    public SymbolTable(int i7) {
        this.indexMask = i7 - 1;
        this.symbols = new String[i7];
        addSymbol("$ref", 0, 4, 1185263);
        String str = JSON.DEFAULT_TYPE_KEY;
        addSymbol(str, 0, str.length(), JSON.DEFAULT_TYPE_KEY.hashCode());
    }

    public static int hash(char[] cArr, int i7, int i8) {
        int i9 = 0;
        int i10 = 0;
        while (i9 < i8) {
            i10 = (i10 * 31) + cArr[i7];
            i9++;
            i7++;
        }
        return i10;
    }

    private static String subString(String str, int i7, int i8) {
        char[] cArr = new char[i8];
        str.getChars(i7, i8 + i7, cArr, 0);
        return new String(cArr);
    }

    public String addSymbol(char[] cArr, int i7, int i8) {
        return addSymbol(cArr, i7, i8, hash(cArr, i7, i8));
    }

    public String addSymbol(char[] cArr, int i7, int i8, int i9) {
        int i10 = this.indexMask & i9;
        String str = this.symbols[i10];
        if (str != null) {
            boolean z6 = false;
            if (i9 == str.hashCode() && i8 == str.length()) {
                int i11 = 0;
                while (true) {
                    if (i11 >= i8) {
                        z6 = true;
                        break;
                    }
                    if (cArr[i7 + i11] != str.charAt(i11)) {
                        break;
                    }
                    i11++;
                }
            }
            return z6 ? str : new String(cArr, i7, i8);
        }
        String strIntern = new String(cArr, i7, i8).intern();
        this.symbols[i10] = strIntern;
        return strIntern;
    }

    public String addSymbol(String str, int i7, int i8, int i9) {
        return addSymbol(str, i7, i8, i9, false);
    }

    public String addSymbol(String str, int i7, int i8, int i9, boolean z6) {
        int i10 = this.indexMask & i9;
        String str2 = this.symbols[i10];
        if (str2 != null) {
            if (i9 == str2.hashCode() && i8 == str2.length() && str.startsWith(str2, i7)) {
                return str2;
            }
            String strSubString = subString(str, i7, i8);
            if (z6) {
                this.symbols[i10] = strSubString;
            }
            return strSubString;
        }
        if (i8 != str.length()) {
            str = subString(str, i7, i8);
        }
        String strIntern = str.intern();
        this.symbols[i10] = strIntern;
        return strIntern;
    }
}
