package com.alibaba.fastjson.parser;

import android.support.constraint.motion.C0079a;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class JSONScanner extends JSONLexerBase {
    private final int len;
    private final String text;

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public static boolean charArrayCompare(String str, int i7, char[] cArr) {
        int length = cArr.length;
        if (length + i7 > str.length()) {
            return false;
        }
        for (int i8 = 0; i8 < length; i8++) {
            if (cArr[i8] != str.charAt(i7 + i8)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDate(char c7, char c8, char c9, char c10, char c11, char c12, int i7, int i8) {
        if (c7 >= '0' && c7 <= '9' && c8 >= '0' && c8 <= '9' && c9 >= '0' && c9 <= '9' && c10 >= '0' && c10 <= '9') {
            if (c11 == '0') {
                if (c12 < '1' || c12 > '9') {
                    return false;
                }
            } else if (c11 != '1' || (c12 != '0' && c12 != '1' && c12 != '2')) {
                return false;
            }
            if (i7 == 48) {
                return i8 >= 49 && i8 <= 57;
            }
            if (i7 != 49 && i7 != 50) {
                return i7 == 51 && (i8 == 48 || i8 == 49);
            }
            if (i8 >= 48 && i8 <= 57) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTime(char c7, char c8, char c9, char c10, char c11, char c12) {
        if (c7 == '0') {
            if (c8 < '0' || c8 > '9') {
                return false;
            }
        } else {
            if (c7 != '1') {
                if (c7 == '2' && c8 >= '0' && c8 <= '4') {
                }
                return false;
            }
            if (c8 < '0' || c8 > '9') {
                return false;
            }
        }
        if (c9 < '0' || c9 > '5') {
            if (c9 != '6' || c10 != '0') {
                return false;
            }
        } else if (c10 < '0' || c10 > '9') {
            return false;
        }
        return (c11 < '0' || c11 > '5') ? c11 == '6' && c12 == '0' : c12 >= '0' && c12 <= '9';
    }

    private void setCalendar(char c7, char c8, char c9, char c10, char c11, char c12, char c13, char c14) {
        Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar = calendar;
        int i7 = c10 - '0';
        calendar.set(1, i7 + ((c9 - '0') * 10) + ((c8 - '0') * 100) + ((c7 - '0') * 1000));
        this.calendar.set(2, ((c12 - '0') + ((c11 - '0') * 10)) - 1);
        this.calendar.set(5, (c14 - '0') + ((c13 - '0') * 10));
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i7, int i8, int i9, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i7, i8, i9);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void arrayCopy(int i7, char[] cArr, int i8, int i9) {
        this.text.getChars(i7, i9 + i7, cArr, i8);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        if (this.token != 26) {
            return IOUtils.decodeBase64(this.text, this.f419np + 1, this.f420sp);
        }
        int i7 = this.f419np + 1;
        int i8 = this.f420sp;
        if (i8 % 2 != 0) {
            throw new JSONException(C0079a.m93a("illegal state. ", i8));
        }
        int i9 = i8 / 2;
        byte[] bArr = new byte[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            int i11 = (i10 * 2) + i7;
            char cCharAt = this.text.charAt(i11);
            char cCharAt2 = this.text.charAt(i11 + 1);
            char c7 = '0';
            int i12 = cCharAt - (cCharAt <= '9' ? '0' : '7');
            if (cCharAt2 > '9') {
                c7 = '7';
            }
            bArr[i10] = (byte) ((i12 << 4) | (cCharAt2 - c7));
        }
        return bArr;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i7) {
        return i7 >= this.len ? JSONLexer.EOI : this.text.charAt(i7);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void copyTo(int i7, int i8, char[] cArr) {
        this.text.getChars(i7, i8 + i7, cArr, 0);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final BigDecimal decimalValue() {
        char cCharAt = charAt((this.f419np + this.f420sp) - 1);
        int i7 = this.f420sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i7--;
        }
        int i8 = this.f419np;
        char[] cArr = this.sbuf;
        if (i7 < cArr.length) {
            this.text.getChars(i8, i8 + i7, cArr, 0);
            return new BigDecimal(this.sbuf, 0, i7);
        }
        char[] cArr2 = new char[i7];
        this.text.getChars(i8, i7 + i8, cArr2, 0);
        return new BigDecimal(cArr2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c7, int i7) {
        return this.text.indexOf(c7, i7);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        StringBuilder sb = new StringBuilder();
        int i7 = 0;
        int i8 = 1;
        int i9 = 1;
        while (i7 < this.f417bp) {
            if (this.text.charAt(i7) == '\n') {
                i8++;
                i9 = 1;
            }
            i7++;
            i9++;
        }
        sb.append("pos ");
        sb.append(this.f417bp);
        sb.append(", line ");
        sb.append(i8);
        sb.append(", column ");
        sb.append(i9);
        if (this.text.length() < 65535) {
            sb.append(this.text);
        } else {
            sb.append(this.text.substring(0, 65535));
        }
        return sb.toString();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        int i7 = this.f417bp;
        int i8 = this.len;
        if (i7 != i8) {
            return this.f418ch == 26 && i7 + 1 == i8;
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean matchField2(char[] cArr) {
        while (JSONLexerBase.isWhitespace(this.f418ch)) {
            next();
        }
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = this.f417bp + cArr.length;
        int i7 = length + 1;
        char cCharAt = this.text.charAt(length);
        while (JSONLexerBase.isWhitespace(cCharAt)) {
            cCharAt = this.text.charAt(i7);
            i7++;
        }
        if (cCharAt != ':') {
            this.matchStat = -2;
            return false;
        }
        this.f417bp = i7;
        this.f418ch = charAt(i7);
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Collection<String> newCollectionByType(Class<?> cls) {
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e7) {
            throw new JSONException(e7.getMessage(), e7);
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() {
        int i7 = this.f417bp + 1;
        this.f417bp = i7;
        char cCharAt = i7 >= this.len ? JSONLexer.EOI : this.text.charAt(i7);
        this.f418ch = cCharAt;
        return cCharAt;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() {
        char cCharAt = charAt((this.f419np + this.f420sp) - 1);
        int i7 = this.f420sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i7--;
        }
        return subString(this.f419np, i7);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanDate(char c7) {
        char cCharAt;
        long j7;
        Date date;
        int i7;
        boolean z6 = false;
        this.matchStat = 0;
        int i8 = this.f417bp;
        char c8 = this.f418ch;
        int i9 = i8 + 1;
        char cCharAt2 = charAt(i8);
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf('\"', i9);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.f417bp = i9;
            if (!scanISO8601DateIfMatch(false, iIndexOf - i9)) {
                this.f417bp = i8;
                this.f418ch = c8;
                this.matchStat = -1;
                return null;
            }
            date = this.calendar.getTime();
            cCharAt = charAt(iIndexOf + 1);
            this.f417bp = i8;
            while (cCharAt != ',' && cCharAt != ']') {
                if (!JSONLexerBase.isWhitespace(cCharAt)) {
                    this.f417bp = i8;
                    this.f418ch = c8;
                    this.matchStat = -1;
                    return null;
                }
                iIndexOf++;
                cCharAt = charAt(iIndexOf + 1);
            }
            this.f417bp = iIndexOf + 1;
            this.f418ch = cCharAt;
        } else {
            char c9 = '9';
            char c10 = '0';
            if (cCharAt2 != '-' && (cCharAt2 < '0' || cCharAt2 > '9')) {
                if (cCharAt2 == 'n') {
                    int i10 = i9 + 1;
                    if (charAt(i9) == 'u') {
                        int i11 = i10 + 1;
                        if (charAt(i10) == 'l') {
                            int i12 = i11 + 1;
                            if (charAt(i11) == 'l') {
                                cCharAt = charAt(i12);
                                this.f417bp = i12;
                                date = null;
                            }
                        }
                    }
                }
                this.f417bp = i8;
                this.f418ch = c8;
                this.matchStat = -1;
                return null;
            }
            if (cCharAt2 == '-') {
                cCharAt2 = charAt(i9);
                i9++;
                z6 = true;
            }
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                cCharAt = cCharAt2;
                j7 = 0;
            } else {
                j7 = cCharAt2 - '0';
                while (true) {
                    i7 = i9 + 1;
                    cCharAt = charAt(i9);
                    if (cCharAt < c10 || cCharAt > c9) {
                        break;
                    }
                    j7 = (j7 * 10) + (cCharAt - '0');
                    i9 = i7;
                    c9 = '9';
                    c10 = '0';
                }
                if (cCharAt == ',' || cCharAt == ']') {
                    this.f417bp = i7 - 1;
                }
            }
            if (j7 < 0) {
                this.f417bp = i8;
                this.f418ch = c8;
                this.matchStat = -1;
                return null;
            }
            if (z6) {
                j7 = -j7;
            }
            date = new Date(j7);
        }
        if (cCharAt == ',') {
            int i13 = this.f417bp + 1;
            this.f417bp = i13;
            this.f418ch = charAt(i13);
            this.matchStat = 3;
            return date;
        }
        int i14 = this.f417bp + 1;
        this.f417bp = i14;
        char cCharAt3 = charAt(i14);
        if (cCharAt3 == ',') {
            this.token = 16;
            int i15 = this.f417bp + 1;
            this.f417bp = i15;
            this.f418ch = charAt(i15);
        } else if (cCharAt3 == ']') {
            this.token = 15;
            int i16 = this.f417bp + 1;
            this.f417bp = i16;
            this.f418ch = charAt(i16);
        } else if (cCharAt3 == '}') {
            this.token = 13;
            int i17 = this.f417bp + 1;
            this.f417bp = i17;
            this.f418ch = charAt(i17);
        } else {
            if (cCharAt3 != 26) {
                this.f417bp = i8;
                this.f418ch = c8;
                this.matchStat = -1;
                return null;
            }
            this.f418ch = JSONLexer.EOI;
            this.token = 20;
        }
        this.matchStat = 4;
        return date;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00c0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00c4 -> B:52:0x00b4). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double scanDouble(char r22) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanDouble(char):double");
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x00f3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0104  */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean scanFieldBoolean(char[] r11) {
        /*
            Method dump skipped, instructions count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldBoolean(char[]):boolean");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanFieldDate(char[] cArr) {
        char cCharAt;
        long j7;
        char cCharAt2;
        Date date;
        int i7;
        boolean z6 = false;
        this.matchStat = 0;
        int i8 = this.f417bp;
        char c7 = this.f418ch;
        if (!charArrayCompare(this.text, i8, cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = this.f417bp + cArr.length;
        int i9 = length + 1;
        char cCharAt3 = charAt(length);
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf('\"', i9);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.f417bp = i9;
            if (!scanISO8601DateIfMatch(false, iIndexOf - i9)) {
                this.f417bp = i8;
                this.matchStat = -1;
                return null;
            }
            date = this.calendar.getTime();
            cCharAt2 = charAt(iIndexOf + 1);
            this.f417bp = i8;
            while (cCharAt2 != ',' && cCharAt2 != '}') {
                if (!JSONLexerBase.isWhitespace(cCharAt2)) {
                    this.matchStat = -1;
                    return null;
                }
                iIndexOf++;
                cCharAt2 = charAt(iIndexOf + 1);
            }
            this.f417bp = iIndexOf + 1;
            this.f418ch = cCharAt2;
        } else {
            char c8 = '9';
            char c9 = '0';
            if (cCharAt3 != '-' && (cCharAt3 < '0' || cCharAt3 > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (cCharAt3 == '-') {
                cCharAt3 = charAt(i9);
                i9++;
                z6 = true;
            }
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                cCharAt = cCharAt3;
                j7 = 0;
            } else {
                j7 = cCharAt3 - '0';
                while (true) {
                    i7 = i9 + 1;
                    cCharAt = charAt(i9);
                    if (cCharAt < c9 || cCharAt > c8) {
                        break;
                    }
                    j7 = (j7 * 10) + (cCharAt - '0');
                    i9 = i7;
                    c8 = '9';
                    c9 = '0';
                }
                if (cCharAt == ',' || cCharAt == '}') {
                    this.f417bp = i7 - 1;
                }
            }
            if (j7 < 0) {
                this.matchStat = -1;
                return null;
            }
            if (z6) {
                j7 = -j7;
            }
            cCharAt2 = cCharAt;
            date = new Date(j7);
        }
        if (cCharAt2 == ',') {
            int i10 = this.f417bp + 1;
            this.f417bp = i10;
            this.f418ch = charAt(i10);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        int i11 = this.f417bp + 1;
        this.f417bp = i11;
        char cCharAt4 = charAt(i11);
        if (cCharAt4 == ',') {
            this.token = 16;
            int i12 = this.f417bp + 1;
            this.f417bp = i12;
            this.f418ch = charAt(i12);
        } else if (cCharAt4 == ']') {
            this.token = 15;
            int i13 = this.f417bp + 1;
            this.f417bp = i13;
            this.f418ch = charAt(i13);
        } else if (cCharAt4 == '}') {
            this.token = 13;
            int i14 = this.f417bp + 1;
            this.f417bp = i14;
            this.f418ch = charAt(i14);
        } else {
            if (cCharAt4 != 26) {
                this.f417bp = i8;
                this.f418ch = c7;
                this.matchStat = -1;
                return null;
            }
            this.token = 20;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public int scanFieldInt(char[] cArr) {
        int i7;
        char cCharAt;
        this.matchStat = 0;
        int i8 = this.f417bp;
        char c7 = this.f418ch;
        if (!charArrayCompare(this.text, i8, cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = this.f417bp + cArr.length;
        int i9 = length + 1;
        char cCharAt2 = charAt(length);
        boolean z6 = cCharAt2 == '\"';
        if (z6) {
            cCharAt2 = charAt(i9);
            i9++;
        }
        boolean z7 = cCharAt2 == '-';
        if (z7) {
            cCharAt2 = charAt(i9);
            i9++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i10 = cCharAt2 - '0';
        while (true) {
            i7 = i9 + 1;
            cCharAt = charAt(i9);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i10 = (i10 * 10) + (cCharAt - '0');
            i9 = i7;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (i10 < 0) {
            this.matchStat = -1;
            return 0;
        }
        if (z6) {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0;
            }
            int i11 = i7 + 1;
            char cCharAt3 = charAt(i7);
            i7 = i11;
            cCharAt = cCharAt3;
        }
        while (cCharAt != ',' && cCharAt != '}') {
            if (!JSONLexerBase.isWhitespace(cCharAt)) {
                this.matchStat = -1;
                return 0;
            }
            int i12 = i7 + 1;
            char cCharAt4 = charAt(i7);
            i7 = i12;
            cCharAt = cCharAt4;
        }
        int i13 = i7 - 1;
        this.f417bp = i13;
        if (cCharAt == ',') {
            int i14 = i13 + 1;
            this.f417bp = i14;
            this.f418ch = charAt(i14);
            this.matchStat = 3;
            this.token = 16;
            return z7 ? -i10 : i10;
        }
        if (cCharAt == '}') {
            this.f417bp = i13;
            int i15 = i13 + 1;
            this.f417bp = i15;
            char cCharAt5 = charAt(i15);
            while (true) {
                if (cCharAt5 == ',') {
                    this.token = 16;
                    int i16 = this.f417bp + 1;
                    this.f417bp = i16;
                    this.f418ch = charAt(i16);
                    break;
                }
                if (cCharAt5 == ']') {
                    this.token = 15;
                    int i17 = this.f417bp + 1;
                    this.f417bp = i17;
                    this.f418ch = charAt(i17);
                    break;
                }
                if (cCharAt5 == '}') {
                    this.token = 13;
                    int i18 = this.f417bp + 1;
                    this.f417bp = i18;
                    this.f418ch = charAt(i18);
                    break;
                }
                if (cCharAt5 == 26) {
                    this.token = 20;
                    break;
                }
                if (!JSONLexerBase.isWhitespace(cCharAt5)) {
                    this.f417bp = i8;
                    this.f418ch = c7;
                    this.matchStat = -1;
                    return 0;
                }
                int i19 = this.f417bp + 1;
                this.f417bp = i19;
                cCharAt5 = charAt(i19);
            }
            this.matchStat = 4;
        }
        return z7 ? -i10 : i10;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldLong(char[] cArr) {
        boolean z6;
        int i7;
        char cCharAt;
        this.matchStat = 0;
        int i8 = this.f417bp;
        char c7 = this.f418ch;
        if (!charArrayCompare(this.text, i8, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.f417bp + cArr.length;
        int i9 = length + 1;
        char cCharAt2 = charAt(length);
        boolean z7 = cCharAt2 == '\"';
        if (z7) {
            cCharAt2 = charAt(i9);
            i9++;
        }
        if (cCharAt2 == '-') {
            z6 = true;
            cCharAt2 = charAt(i9);
            i9++;
        } else {
            z6 = false;
        }
        if (cCharAt2 >= '0') {
            char c8 = '9';
            if (cCharAt2 <= '9') {
                long j7 = cCharAt2 - '0';
                while (true) {
                    i7 = i9 + 1;
                    cCharAt = charAt(i9);
                    if (cCharAt < '0' || cCharAt > c8) {
                        break;
                    }
                    j7 = (j7 * 10) + (cCharAt - '0');
                    i9 = i7;
                    c8 = '9';
                }
                if (cCharAt == '.') {
                    this.matchStat = -1;
                    return 0L;
                }
                if (z7) {
                    if (cCharAt != '\"') {
                        this.matchStat = -1;
                        return 0L;
                    }
                    int i10 = i7 + 1;
                    char cCharAt3 = charAt(i7);
                    i7 = i10;
                    cCharAt = cCharAt3;
                }
                if (cCharAt == ',' || cCharAt == '}') {
                    this.f417bp = i7 - 1;
                }
                if (!(j7 >= 0 || (j7 == Long.MIN_VALUE && z6))) {
                    this.f417bp = i8;
                    this.f418ch = c7;
                    this.matchStat = -1;
                    return 0L;
                }
                while (cCharAt != ',') {
                    if (cCharAt == '}') {
                        int i11 = this.f417bp + 1;
                        this.f417bp = i11;
                        char cCharAt4 = charAt(i11);
                        while (true) {
                            if (cCharAt4 == ',') {
                                this.token = 16;
                                int i12 = this.f417bp + 1;
                                this.f417bp = i12;
                                this.f418ch = charAt(i12);
                                break;
                            }
                            if (cCharAt4 == ']') {
                                this.token = 15;
                                int i13 = this.f417bp + 1;
                                this.f417bp = i13;
                                this.f418ch = charAt(i13);
                                break;
                            }
                            if (cCharAt4 == '}') {
                                this.token = 13;
                                int i14 = this.f417bp + 1;
                                this.f417bp = i14;
                                this.f418ch = charAt(i14);
                                break;
                            }
                            if (cCharAt4 == 26) {
                                this.token = 20;
                                break;
                            }
                            if (!JSONLexerBase.isWhitespace(cCharAt4)) {
                                this.f417bp = i8;
                                this.f418ch = c7;
                                this.matchStat = -1;
                                return 0L;
                            }
                            int i15 = this.f417bp + 1;
                            this.f417bp = i15;
                            cCharAt4 = charAt(i15);
                        }
                        this.matchStat = 4;
                        return z6 ? -j7 : j7;
                    }
                    if (!JSONLexerBase.isWhitespace(cCharAt)) {
                        this.matchStat = -1;
                        return 0L;
                    }
                    this.f417bp = i7;
                    int i16 = i7 + 1;
                    char cCharAt5 = charAt(i7);
                    i7 = i16;
                    cCharAt = cCharAt5;
                }
                int i17 = this.f417bp + 1;
                this.f417bp = i17;
                this.f418ch = charAt(i17);
                this.matchStat = 3;
                this.token = 16;
                return z6 ? -j7 : j7;
            }
        }
        this.f417bp = i8;
        this.f418ch = c7;
        this.matchStat = -1;
        return 0L;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        int i7 = this.f417bp;
        char c7 = this.f418ch;
        while (!charArrayCompare(this.text, this.f417bp, cArr)) {
            if (!JSONLexerBase.isWhitespace(this.f418ch)) {
                this.matchStat = -2;
                return stringDefaultValue();
            }
            next();
        }
        int length = this.f417bp + cArr.length;
        int i8 = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int iIndexOf = indexOf('\"', i8);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        String strSubString = subString(i8, iIndexOf - i8);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i9 = 0;
                for (int i10 = iIndexOf - 1; i10 >= 0 && charAt(i10) == '\\'; i10--) {
                    i9++;
                }
                if (i9 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf('\"', iIndexOf + 1);
            }
            int i11 = this.f417bp;
            int length2 = iIndexOf - ((cArr.length + i11) + 1);
            strSubString = JSONLexerBase.readString(sub_chars(i11 + cArr.length + 1, length2), length2);
        }
        char cCharAt = charAt(iIndexOf + 1);
        while (cCharAt != ',' && cCharAt != '}') {
            if (!JSONLexerBase.isWhitespace(cCharAt)) {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            iIndexOf++;
            cCharAt = charAt(iIndexOf + 1);
        }
        int i12 = iIndexOf + 1;
        this.f417bp = i12;
        this.f418ch = cCharAt;
        if (cCharAt == ',') {
            int i13 = i12 + 1;
            this.f417bp = i13;
            this.f418ch = charAt(i13);
            this.matchStat = 3;
            return strSubString;
        }
        int i14 = i12 + 1;
        this.f417bp = i14;
        char cCharAt2 = charAt(i14);
        if (cCharAt2 == ',') {
            this.token = 16;
            int i15 = this.f417bp + 1;
            this.f417bp = i15;
            this.f418ch = charAt(i15);
        } else if (cCharAt2 == ']') {
            this.token = 15;
            int i16 = this.f417bp + 1;
            this.f417bp = i16;
            this.f418ch = charAt(i16);
        } else if (cCharAt2 == '}') {
            this.token = 13;
            int i17 = this.f417bp + 1;
            this.f417bp = i17;
            this.f418ch = charAt(i17);
        } else {
            if (cCharAt2 != 26) {
                this.f417bp = i7;
                this.f418ch = c7;
                this.matchStat = -1;
                return stringDefaultValue();
            }
            this.token = 20;
        }
        this.matchStat = 4;
        return strSubString;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00db, code lost:
    
        if (r9 != ']') goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00e1, code lost:
    
        if (r3.size() != 0) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e3, code lost:
    
        r2 = r1 + 1;
        r1 = charAt(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ed, code lost:
    
        r17.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f0, code lost:
    
        return null;
     */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Collection<java.lang.String> scanFieldStringArray(char[] r18, java.lang.Class<?> r19) {
        /*
            Method dump skipped, instructions count: 415
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldStringArray(char[], java.lang.Class):java.util.Collection");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.f417bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.f417bp + cArr.length;
        int i7 = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j7 = -3750763034362895579L;
        while (true) {
            int i8 = i7 + 1;
            char cCharAt = charAt(i7);
            if (cCharAt == '\"') {
                this.f417bp = i8;
                char cCharAt2 = charAt(i8);
                this.f418ch = cCharAt2;
                while (cCharAt2 != ',') {
                    if (cCharAt2 == '}') {
                        next();
                        skipWhitespace();
                        char current = getCurrent();
                        if (current == ',') {
                            this.token = 16;
                            int i9 = this.f417bp + 1;
                            this.f417bp = i9;
                            this.f418ch = charAt(i9);
                        } else if (current == ']') {
                            this.token = 15;
                            int i10 = this.f417bp + 1;
                            this.f417bp = i10;
                            this.f418ch = charAt(i10);
                        } else if (current == '}') {
                            this.token = 13;
                            int i11 = this.f417bp + 1;
                            this.f417bp = i11;
                            this.f418ch = charAt(i11);
                        } else {
                            if (current != 26) {
                                this.matchStat = -1;
                                return 0L;
                            }
                            this.token = 20;
                        }
                        this.matchStat = 4;
                        return j7;
                    }
                    if (!JSONLexerBase.isWhitespace(cCharAt2)) {
                        this.matchStat = -1;
                        return 0L;
                    }
                    int i12 = this.f417bp + 1;
                    this.f417bp = i12;
                    cCharAt2 = charAt(i12);
                }
                int i13 = this.f417bp + 1;
                this.f417bp = i13;
                this.f418ch = charAt(i13);
                this.matchStat = 3;
                return j7;
            }
            if (i8 > this.len) {
                this.matchStat = -1;
                return 0L;
            }
            j7 = (j7 ^ cCharAt) * 1099511628211L;
            i7 = i8;
        }
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final int scanInt(char c7) {
        int i7;
        char cCharAt;
        this.matchStat = 0;
        int i8 = this.f417bp;
        int i9 = i8 + 1;
        char cCharAt2 = charAt(i8);
        while (JSONLexerBase.isWhitespace(cCharAt2)) {
            int i10 = i9 + 1;
            char cCharAt3 = charAt(i9);
            i9 = i10;
            cCharAt2 = cCharAt3;
        }
        boolean z6 = cCharAt2 == '\"';
        if (z6) {
            int i11 = i9 + 1;
            char cCharAt4 = charAt(i9);
            i9 = i11;
            cCharAt2 = cCharAt4;
        }
        boolean z7 = cCharAt2 == '-';
        if (z7) {
            int i12 = i9 + 1;
            char cCharAt5 = charAt(i9);
            i9 = i12;
            cCharAt2 = cCharAt5;
        }
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            int i13 = cCharAt2 - '0';
            while (true) {
                i7 = i9 + 1;
                cCharAt = charAt(i9);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i13 = (i13 * 10) + (cCharAt - '0');
                i9 = i7;
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (z6) {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0;
                }
                char cCharAt6 = charAt(i7);
                i7++;
                cCharAt = cCharAt6;
            }
            if (i13 < 0) {
                this.matchStat = -1;
                return 0;
            }
            while (cCharAt != c7) {
                if (!JSONLexerBase.isWhitespace(cCharAt)) {
                    this.matchStat = -1;
                    return z7 ? -i13 : i13;
                }
                cCharAt = charAt(i7);
                i7++;
            }
            this.f417bp = i7;
            this.f418ch = charAt(i7);
            this.matchStat = 3;
            this.token = 16;
            return z7 ? -i13 : i13;
        }
        if (cCharAt2 == 'n') {
            int i14 = i9 + 1;
            if (charAt(i9) == 'u') {
                int i15 = i14 + 1;
                if (charAt(i14) == 'l') {
                    int i16 = i15 + 1;
                    if (charAt(i15) == 'l') {
                        this.matchStat = 5;
                        int i17 = i16 + 1;
                        char cCharAt7 = charAt(i16);
                        if (z6 && cCharAt7 == '\"') {
                            int i18 = i17 + 1;
                            char cCharAt8 = charAt(i17);
                            i17 = i18;
                            cCharAt7 = cCharAt8;
                        }
                        while (cCharAt7 != ',') {
                            if (cCharAt7 == ']') {
                                this.f417bp = i17;
                                this.f418ch = charAt(i17);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0;
                            }
                            if (!JSONLexerBase.isWhitespace(cCharAt7)) {
                                this.matchStat = -1;
                                return 0;
                            }
                            int i19 = i17 + 1;
                            char cCharAt9 = charAt(i17);
                            i17 = i19;
                            cCharAt7 = cCharAt9;
                        }
                        this.f417bp = i17;
                        this.f418ch = charAt(i17);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char c7) {
        int i7;
        char cCharAt;
        boolean z6 = false;
        this.matchStat = 0;
        int i8 = this.f417bp;
        int i9 = i8 + 1;
        char cCharAt2 = charAt(i8);
        boolean z7 = cCharAt2 == '\"';
        if (z7) {
            int i10 = i9 + 1;
            char cCharAt3 = charAt(i9);
            i9 = i10;
            cCharAt2 = cCharAt3;
        }
        boolean z8 = cCharAt2 == '-';
        if (z8) {
            int i11 = i9 + 1;
            char cCharAt4 = charAt(i9);
            i9 = i11;
            cCharAt2 = cCharAt4;
        }
        char c8 = '0';
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            long j7 = cCharAt2 - '0';
            while (true) {
                i7 = i9 + 1;
                cCharAt = charAt(i9);
                if (cCharAt < c8 || cCharAt > '9') {
                    break;
                }
                j7 = (j7 * 10) + (cCharAt - '0');
                i9 = i7;
                c8 = '0';
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0L;
            }
            if (z7) {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
                cCharAt = charAt(i7);
                i7++;
            }
            if (j7 >= 0 || (j7 == Long.MIN_VALUE && z8)) {
                z6 = true;
            }
            if (!z6) {
                this.matchStat = -1;
                return 0L;
            }
            while (cCharAt != c7) {
                if (!JSONLexerBase.isWhitespace(cCharAt)) {
                    this.matchStat = -1;
                    return j7;
                }
                cCharAt = charAt(i7);
                i7++;
            }
            this.f417bp = i7;
            this.f418ch = charAt(i7);
            this.matchStat = 3;
            this.token = 16;
            return z8 ? -j7 : j7;
        }
        if (cCharAt2 == 'n') {
            int i12 = i9 + 1;
            if (charAt(i9) == 'u') {
                int i13 = i12 + 1;
                if (charAt(i12) == 'l') {
                    int i14 = i13 + 1;
                    if (charAt(i13) == 'l') {
                        this.matchStat = 5;
                        int i15 = i14 + 1;
                        char cCharAt5 = charAt(i14);
                        if (z7 && cCharAt5 == '\"') {
                            int i16 = i15 + 1;
                            char cCharAt6 = charAt(i15);
                            i15 = i16;
                            cCharAt5 = cCharAt6;
                        }
                        while (cCharAt5 != ',') {
                            if (cCharAt5 == ']') {
                                this.f417bp = i15;
                                this.f418ch = charAt(i15);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0L;
                            }
                            if (!JSONLexerBase.isWhitespace(cCharAt5)) {
                                this.matchStat = -1;
                                return 0L;
                            }
                            int i17 = i15 + 1;
                            char cCharAt7 = charAt(i15);
                            i15 = i17;
                            cCharAt5 = cCharAt7;
                        }
                        this.f417bp = i15;
                        this.f418ch = charAt(i15);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0L;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0L;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean seekArrayToItem(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException(C0079a.m93a("index must > 0, but ", i7));
        }
        if (this.token != 14) {
            throw new UnsupportedOperationException();
        }
        int i8 = 0;
        while (true) {
            boolean z6 = true;
            if (i8 >= i7) {
                nextToken();
                return true;
            }
            skipWhitespace();
            char c7 = this.f418ch;
            if (c7 == '\"' || c7 == '\'') {
                skipString();
                char c8 = this.f418ch;
                if (c8 != ',') {
                    if (c8 != ']') {
                        throw new JSONException("illegal json.");
                    }
                    next();
                    nextToken(16);
                    return false;
                }
                next();
            } else {
                if (c7 == '{') {
                    next();
                    this.token = 12;
                    skipObject();
                } else if (c7 == '[') {
                    next();
                    this.token = 14;
                    skipArray();
                } else {
                    int i9 = this.f417bp + 1;
                    while (true) {
                        if (i9 >= this.text.length()) {
                            z6 = false;
                            break;
                        }
                        char cCharAt = this.text.charAt(i9);
                        if (cCharAt == ',') {
                            int i10 = i9 + 1;
                            this.f417bp = i10;
                            this.f418ch = charAt(i10);
                            break;
                        }
                        if (cCharAt == ']') {
                            int i11 = i9 + 1;
                            this.f417bp = i11;
                            this.f418ch = charAt(i11);
                            nextToken();
                            return false;
                        }
                        i9++;
                    }
                    if (!z6) {
                        throw new JSONException("illegal json.");
                    }
                }
                int i12 = this.token;
                if (i12 != 16) {
                    if (i12 == 15) {
                        return false;
                    }
                    throw new UnsupportedOperationException();
                }
            }
            i8++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public int seekObjectToField(long j7, boolean z6) {
        char c7;
        if (this.token != 12) {
            throw new UnsupportedOperationException();
        }
        while (true) {
            char c8 = this.f418ch;
            if (c8 == '}') {
                next();
                nextToken();
                return -1;
            }
            char cCharAt = JSONLexer.EOI;
            if (c8 == 26) {
                return -1;
            }
            if (c8 != '\"') {
                skipWhitespace();
            }
            if (this.f418ch != '\"') {
                throw new UnsupportedOperationException();
            }
            long j8 = -3750763034362895579L;
            int i7 = this.f417bp + 1;
            while (true) {
                if (i7 >= this.text.length()) {
                    break;
                }
                char cCharAt2 = this.text.charAt(i7);
                if (cCharAt2 == '\\') {
                    i7++;
                    if (i7 == this.text.length()) {
                        StringBuilder sbM112a = C0413b.m112a("unclosed str, ");
                        sbM112a.append(info());
                        throw new JSONException(sbM112a.toString());
                    }
                    cCharAt2 = this.text.charAt(i7);
                }
                if (cCharAt2 == '\"') {
                    int i8 = i7 + 1;
                    this.f417bp = i8;
                    this.f418ch = i8 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(this.f417bp);
                } else {
                    j8 = (j8 ^ cCharAt2) * 1099511628211L;
                    i7++;
                }
            }
            if (j8 == j7) {
                if (this.f418ch != ':') {
                    skipWhitespace();
                }
                if (this.f418ch != ':') {
                    return 3;
                }
                int i9 = this.f417bp + 1;
                this.f417bp = i9;
                char cCharAt3 = i9 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i9);
                this.f418ch = cCharAt3;
                if (cCharAt3 == ',') {
                    int i10 = this.f417bp + 1;
                    this.f417bp = i10;
                    if (i10 < this.text.length()) {
                        cCharAt = this.text.charAt(i10);
                    }
                    this.f418ch = cCharAt;
                    this.token = 16;
                    return 3;
                }
                if (cCharAt3 == ']') {
                    int i11 = this.f417bp + 1;
                    this.f417bp = i11;
                    if (i11 < this.text.length()) {
                        cCharAt = this.text.charAt(i11);
                    }
                    this.f418ch = cCharAt;
                    this.token = 15;
                    return 3;
                }
                if (cCharAt3 == '}') {
                    int i12 = this.f417bp + 1;
                    this.f417bp = i12;
                    if (i12 < this.text.length()) {
                        cCharAt = this.text.charAt(i12);
                    }
                    this.f418ch = cCharAt;
                    this.token = 13;
                    return 3;
                }
                if (cCharAt3 < '0' || cCharAt3 > '9') {
                    nextToken(2);
                    return 3;
                }
                this.f420sp = 0;
                this.pos = this.f417bp;
                scanNumber();
                return 3;
            }
            if (this.f418ch != ':') {
                skipWhitespace();
            }
            if (this.f418ch != ':') {
                StringBuilder sbM112a2 = C0413b.m112a("illegal json, ");
                sbM112a2.append(info());
                throw new JSONException(sbM112a2.toString());
            }
            int i13 = this.f417bp + 1;
            this.f417bp = i13;
            char cCharAt4 = i13 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i13);
            this.f418ch = cCharAt4;
            if (cCharAt4 != '\"' && cCharAt4 != '\'' && cCharAt4 != '{' && cCharAt4 != '[' && cCharAt4 != '0' && cCharAt4 != '1' && cCharAt4 != '2' && cCharAt4 != '3' && cCharAt4 != '4' && cCharAt4 != '5' && cCharAt4 != '6' && cCharAt4 != '7' && cCharAt4 != '8' && cCharAt4 != '9' && cCharAt4 != '+' && cCharAt4 != '-') {
                skipWhitespace();
            }
            char c9 = this.f418ch;
            if (c9 == '-' || c9 == '+' || (c9 >= '0' && c9 <= '9')) {
                next();
                while (true) {
                    c7 = this.f418ch;
                    if (c7 < '0' || c7 > '9') {
                        break;
                    }
                    next();
                }
                if (c7 == '.') {
                    next();
                    while (true) {
                        char c10 = this.f418ch;
                        if (c10 < '0' || c10 > '9') {
                            break;
                        }
                        next();
                    }
                }
                char c11 = this.f418ch;
                if (c11 == 'E' || c11 == 'e') {
                    next();
                    char c12 = this.f418ch;
                    if (c12 == '-' || c12 == '+') {
                        next();
                    }
                    while (true) {
                        char c13 = this.f418ch;
                        if (c13 < '0' || c13 > '9') {
                            break;
                        }
                        next();
                    }
                }
                if (this.f418ch != ',') {
                    skipWhitespace();
                }
                if (this.f418ch == ',') {
                    next();
                }
            } else if (c9 == '\"') {
                skipString();
                char c14 = this.f418ch;
                if (c14 != ',' && c14 != '}') {
                    skipWhitespace();
                }
                if (this.f418ch == ',') {
                    next();
                }
            } else if (c9 == '{') {
                int i14 = this.f417bp + 1;
                this.f417bp = i14;
                if (i14 < this.text.length()) {
                    cCharAt = this.text.charAt(i14);
                }
                this.f418ch = cCharAt;
                if (z6) {
                    this.token = 12;
                    return 1;
                }
                skipObject();
            } else {
                if (c9 != '[') {
                    throw new UnsupportedOperationException();
                }
                next();
                if (z6) {
                    this.token = 14;
                    return 2;
                }
                skipArray();
            }
        }
    }

    public void setTime(char c7, char c8, char c9, char c10, char c11, char c12) {
        this.calendar.set(11, (c8 - '0') + ((c7 - '0') * 10));
        this.calendar.set(12, (c10 - '0') + ((c9 - '0') * 10));
        this.calendar.set(13, (c12 - '0') + ((c11 - '0') * 10));
    }

    public void setTimeZone(char c7, char c8, char c9) {
        setTimeZone(c7, c8, c9, '0', '0');
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void skipArray() {
        int i7 = this.f417bp;
        boolean z6 = false;
        int i8 = 0;
        while (i7 < this.text.length()) {
            char cCharAt = this.text.charAt(i7);
            if (cCharAt == '\\') {
                if (i7 >= this.len - 1) {
                    this.f418ch = cCharAt;
                    this.f417bp = i7;
                    StringBuilder sbM112a = C0413b.m112a("illegal str, ");
                    sbM112a.append(info());
                    throw new JSONException(sbM112a.toString());
                }
                i7++;
            } else if (cCharAt == '\"') {
                z6 = !z6;
            } else if (cCharAt == '[') {
                if (!z6) {
                    i8++;
                }
            } else if (cCharAt == ']' && !z6 && i8 - 1 == -1) {
                int i9 = i7 + 1;
                this.f417bp = i9;
                this.f418ch = this.text.charAt(i9);
                nextToken(16);
                return;
            }
            i7++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void skipObject() {
        int i7 = this.f417bp;
        boolean z6 = false;
        int i8 = 0;
        while (i7 < this.text.length()) {
            char cCharAt = this.text.charAt(i7);
            if (cCharAt == '\\') {
                if (i7 >= this.len - 1) {
                    this.f418ch = cCharAt;
                    this.f417bp = i7;
                    StringBuilder sbM112a = C0413b.m112a("illegal str, ");
                    sbM112a.append(info());
                    throw new JSONException(sbM112a.toString());
                }
                i7++;
            } else if (cCharAt == '\"') {
                z6 = !z6;
            } else if (cCharAt == '{') {
                if (!z6) {
                    i8++;
                }
            } else if (cCharAt == '}' && !z6 && i8 - 1 == -1) {
                int i9 = i7 + 1;
                this.f417bp = i9;
                char cCharAt2 = this.text.charAt(i9);
                this.f418ch = cCharAt2;
                if (cCharAt2 == ',') {
                    this.token = 16;
                    int i10 = this.f417bp + 1;
                    this.f417bp = i10;
                    this.f418ch = i10 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i10);
                    return;
                }
                if (cCharAt2 == '}') {
                    this.token = 13;
                    next();
                    return;
                } else if (cCharAt2 != ']') {
                    nextToken(16);
                    return;
                } else {
                    this.token = 15;
                    next();
                    return;
                }
            }
            i7++;
        }
    }

    public final void skipString() {
        if (this.f418ch != '\"') {
            throw new UnsupportedOperationException();
        }
        int i7 = this.f417bp;
        while (true) {
            i7++;
            if (i7 >= this.text.length()) {
                throw new JSONException("unclosed str");
            }
            char cCharAt = this.text.charAt(i7);
            if (cCharAt == '\\') {
                if (i7 < this.len - 1) {
                    i7++;
                }
            } else if (cCharAt == '\"') {
                String str = this.text;
                int i8 = i7 + 1;
                this.f417bp = i8;
                this.f418ch = str.charAt(i8);
                return;
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        return !this.hasSpecial ? subString(this.f419np + 1, this.f420sp) : new String(this.sbuf, 0, this.f420sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i7, int i8) {
        if (!ASMUtils.IS_ANDROID) {
            return this.text.substring(i7, i8 + i7);
        }
        char[] cArr = this.sbuf;
        if (i8 < cArr.length) {
            this.text.getChars(i7, i7 + i8, cArr, 0);
            return new String(this.sbuf, 0, i8);
        }
        char[] cArr2 = new char[i8];
        this.text.getChars(i7, i8 + i7, cArr2, 0);
        return new String(cArr2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char[] sub_chars(int i7, int i8) {
        if (ASMUtils.IS_ANDROID) {
            char[] cArr = this.sbuf;
            if (i8 < cArr.length) {
                this.text.getChars(i7, i8 + i7, cArr, 0);
                return this.sbuf;
            }
        }
        char[] cArr2 = new char[i8];
        this.text.getChars(i7, i8 + i7, cArr2, 0);
        return cArr2;
    }

    public JSONScanner(String str, int i7) {
        super(i7);
        this.text = str;
        this.len = str.length();
        this.f417bp = -1;
        next();
        if (this.f418ch == 65279) {
            next();
        }
    }

    public boolean scanISO8601DateIfMatch(boolean z6) {
        return scanISO8601DateIfMatch(z6, this.len - this.f417bp);
    }

    public void setTimeZone(char c7, char c8, char c9, char c10, char c11) {
        int i7 = (((c11 - '0') + ((c10 - '0') * 10)) * 60 * 1000) + (((c9 - '0') + ((c8 - '0') * 10)) * 3600 * 1000);
        if (c7 == '-') {
            i7 = -i7;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i7) {
            String[] availableIDs = TimeZone.getAvailableIDs(i7);
            if (availableIDs.length > 0) {
                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x01ea A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean scanISO8601DateIfMatch(boolean r26, int r27) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 1686
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanISO8601DateIfMatch(boolean, int):boolean");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.f417bp, cArr);
    }

    public JSONScanner(char[] cArr, int i7) {
        this(cArr, i7, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i7, int i8) {
        this(new String(cArr, 0, i7), i8);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String[] scanFieldStringArray(char[] cArr, int i7, SymbolTable symbolTable) throws NumberFormatException {
        int i8;
        char cCharAt;
        int i9 = this.f417bp;
        char c7 = this.f418ch;
        while (JSONLexerBase.isWhitespace(this.f418ch)) {
            next();
        }
        if (cArr != null) {
            this.matchStat = 0;
            if (!charArrayCompare(cArr)) {
                this.matchStat = -2;
                return null;
            }
            int length = this.f417bp + cArr.length;
            int i10 = length + 1;
            char cCharAt2 = this.text.charAt(length);
            while (JSONLexerBase.isWhitespace(cCharAt2)) {
                cCharAt2 = this.text.charAt(i10);
                i10++;
            }
            if (cCharAt2 == ':') {
                i8 = i10 + 1;
                cCharAt = this.text.charAt(i10);
                while (JSONLexerBase.isWhitespace(cCharAt)) {
                    cCharAt = this.text.charAt(i8);
                    i8++;
                }
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            i8 = this.f417bp + 1;
            cCharAt = this.f418ch;
        }
        if (cCharAt == '[') {
            this.f417bp = i8;
            this.f418ch = this.text.charAt(i8);
            String[] strArr = i7 >= 0 ? new String[i7] : new String[4];
            int i11 = 0;
            while (true) {
                if (JSONLexerBase.isWhitespace(this.f418ch)) {
                    next();
                } else {
                    if (this.f418ch != '\"') {
                        this.f417bp = i9;
                        this.f418ch = c7;
                        this.matchStat = -1;
                        return null;
                    }
                    String strScanSymbol = scanSymbol(symbolTable, '\"');
                    if (i11 == strArr.length) {
                        String[] strArr2 = new String[strArr.length + (strArr.length >> 1) + 1];
                        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                        strArr = strArr2;
                    }
                    int i12 = i11 + 1;
                    strArr[i11] = strScanSymbol;
                    while (JSONLexerBase.isWhitespace(this.f418ch)) {
                        next();
                    }
                    if (this.f418ch == ',') {
                        next();
                        i11 = i12;
                    } else {
                        if (strArr.length != i12) {
                            String[] strArr3 = new String[i12];
                            System.arraycopy(strArr, 0, strArr3, 0, i12);
                            strArr = strArr3;
                        }
                        while (JSONLexerBase.isWhitespace(this.f418ch)) {
                            next();
                        }
                        if (this.f418ch == ']') {
                            next();
                            return strArr;
                        }
                        this.f417bp = i9;
                        this.f418ch = c7;
                        this.matchStat = -1;
                        return null;
                    }
                }
            }
        } else {
            if (cCharAt == 'n' && this.text.startsWith("ull", this.f417bp + 1)) {
                int i13 = this.f417bp + 4;
                this.f417bp = i13;
                this.f418ch = this.text.charAt(i13);
                return null;
            }
            this.matchStat = -1;
            return null;
        }
    }
}
