package com.alibaba.fastjson.parser;

import android.arch.lifecycle.C0063n;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import p009b.C0413b;
import p027d.C0861a;

/* loaded from: classes.dex */
public abstract class JSONLexerBase implements JSONLexer, Closeable {
    public static final int INT_MULTMIN_RADIX_TEN = -214748364;
    public static final long MULTMIN_RADIX_TEN = -922337203685477580L;
    private static final ThreadLocal<char[]> SBUF_LOCAL = new ThreadLocal<>();
    public static final int[] digits;
    public static final char[] typeFieldName;

    /* renamed from: bp */
    public int f417bp;

    /* renamed from: ch */
    public char f418ch;
    public int eofPos;
    public int features;
    public boolean hasSpecial;

    /* renamed from: np */
    public int f419np;
    public int pos;
    public char[] sbuf;

    /* renamed from: sp */
    public int f420sp;
    public String stringDefaultValue;
    public int token;
    public Calendar calendar = null;
    public TimeZone timeZone = JSON.defaultTimeZone;
    public Locale locale = JSON.defaultLocale;
    public int matchStat = 0;

    static {
        StringBuilder sbM112a = C0413b.m112a("\"");
        sbM112a.append(JSON.DEFAULT_TYPE_KEY);
        sbM112a.append("\":\"");
        typeFieldName = sbM112a.toString().toCharArray();
        digits = new int[103];
        for (int i7 = 48; i7 <= 57; i7++) {
            digits[i7] = i7 - 48;
        }
        for (int i8 = 97; i8 <= 102; i8++) {
            digits[i8] = (i8 - 97) + 10;
        }
        for (int i9 = 65; i9 <= 70; i9++) {
            digits[i9] = (i9 - 65) + 10;
        }
    }

    public JSONLexerBase(int i7) {
        this.stringDefaultValue = null;
        this.features = i7;
        if ((i7 & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
        char[] cArr = SBUF_LOCAL.get();
        this.sbuf = cArr;
        if (cArr == null) {
            this.sbuf = new char[512];
        }
    }

    public static boolean isWhitespace(char c7) {
        return c7 <= ' ' && (c7 == ' ' || c7 == '\n' || c7 == '\r' || c7 == '\t' || c7 == '\f' || c7 == '\b');
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readString(char[] r12, int r13) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.readString(char[], int):java.lang.String");
    }

    private void scanStringSingleQuote() {
        this.f419np = this.f417bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\'') {
                this.token = 4;
                next();
                return;
            }
            if (next == 26) {
                if (isEOF()) {
                    throw new JSONException("unclosed single-quote string");
                }
                putChar(JSONLexer.EOI);
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    int i7 = this.f420sp;
                    char[] cArr = this.sbuf;
                    if (i7 > cArr.length) {
                        char[] cArr2 = new char[i7 * 2];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.sbuf = cArr2;
                    }
                    copyTo(this.f419np + 1, this.f420sp, this.sbuf);
                }
                char next2 = next();
                if (next2 == '\"') {
                    putChar('\"');
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            putChar('\\');
                        } else if (next2 == 'b') {
                            putChar('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                putChar('\n');
                            } else if (next2 == 'r') {
                                putChar('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        putChar('/');
                                        break;
                                    case '0':
                                        putChar((char) 0);
                                        break;
                                    case '1':
                                        putChar((char) 1);
                                        break;
                                    case '2':
                                        putChar((char) 2);
                                        break;
                                    case '3':
                                        putChar((char) 3);
                                        break;
                                    case '4':
                                        putChar((char) 4);
                                        break;
                                    case '5':
                                        putChar((char) 5);
                                        break;
                                    case '6':
                                        putChar((char) 6);
                                        break;
                                    case '7':
                                        putChar((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case 't':
                                                putChar('\t');
                                                break;
                                            case 'u':
                                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                break;
                                            case 'v':
                                                putChar((char) 11);
                                                break;
                                            default:
                                                this.f418ch = next2;
                                                throw new JSONException("unclosed single-quote string");
                                        }
                                }
                            } else {
                                int[] iArr = digits;
                                putChar((char) ((iArr[next()] * 16) + iArr[next()]));
                            }
                        }
                    }
                    putChar('\f');
                } else {
                    putChar('\'');
                }
            } else if (this.hasSpecial) {
                int i8 = this.f420sp;
                char[] cArr3 = this.sbuf;
                if (i8 == cArr3.length) {
                    putChar(next);
                } else {
                    this.f420sp = i8 + 1;
                    cArr3[i8] = next;
                }
            } else {
                this.f420sp++;
            }
        }
    }

    public abstract String addSymbol(int i7, int i8, int i9, SymbolTable symbolTable);

    public abstract void arrayCopy(int i7, char[] cArr, int i8, int i9);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract byte[] bytesValue();

    public abstract boolean charArrayCompare(char[] cArr);

    public abstract char charAt(int i7);

    @Override // com.alibaba.fastjson.parser.JSONLexer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        char[] cArr = this.sbuf;
        if (cArr.length <= 8192) {
            SBUF_LOCAL.set(cArr);
        }
        this.sbuf = null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void config(Feature feature, boolean z6) {
        int iConfig = Feature.config(this.features, feature, z6);
        this.features = iConfig;
        if ((iConfig & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
    }

    public abstract void copyTo(int i7, int i8, char[] cArr);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final Number decimalValue(boolean z6) {
        char cCharAt = charAt((this.f419np + this.f420sp) - 1);
        try {
            return cCharAt == 'F' ? Float.valueOf(Float.parseFloat(numberString())) : cCharAt == 'D' ? Double.valueOf(Double.parseDouble(numberString())) : z6 ? decimalValue() : Double.valueOf(doubleValue());
        } catch (NumberFormatException e7) {
            throw new JSONException(e7.getMessage() + ", " + info());
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract BigDecimal decimalValue();

    public double doubleValue() {
        return Double.parseDouble(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public float floatValue() throws NumberFormatException {
        char cCharAt;
        String strNumberString = numberString();
        float f7 = Float.parseFloat(strNumberString);
        if ((f7 == 0.0f || f7 == Float.POSITIVE_INFINITY) && (cCharAt = strNumberString.charAt(0)) > '0' && cCharAt <= '9') {
            throw new JSONException(C0063n.m88a("float overflow : ", strNumberString));
        }
        return f7;
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final char getCurrent() {
        return this.f418ch;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public int getFeatures() {
        return this.features;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public Locale getLocale() {
        return this.locale;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public abstract int indexOf(char c7, int i7);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        return "";
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int intValue() {
        int i7;
        boolean z6;
        int i8 = 0;
        if (this.f419np == -1) {
            this.f419np = 0;
        }
        int i9 = this.f419np;
        int i10 = this.f420sp + i9;
        if (charAt(i9) == '-') {
            i7 = Integer.MIN_VALUE;
            i9++;
            z6 = true;
        } else {
            i7 = -2147483647;
            z6 = false;
        }
        if (i9 < i10) {
            i8 = -(charAt(i9) - '0');
            i9++;
        }
        while (i9 < i10) {
            int i11 = i9 + 1;
            char cCharAt = charAt(i9);
            if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B') {
                i9 = i11;
                break;
            }
            int i12 = cCharAt - '0';
            if (i8 < -214748364) {
                throw new NumberFormatException(numberString());
            }
            int i13 = i8 * 10;
            if (i13 < i7 + i12) {
                throw new NumberFormatException(numberString());
            }
            i8 = i13 - i12;
            i9 = i11;
        }
        if (!z6) {
            return -i8;
        }
        if (i9 > this.f419np + 1) {
            return i8;
        }
        throw new NumberFormatException(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final Number integerValue() {
        long j7;
        long j8;
        boolean z6 = false;
        if (this.f419np == -1) {
            this.f419np = 0;
        }
        int i7 = this.f419np;
        int i8 = this.f420sp + i7;
        char c7 = ' ';
        char cCharAt = charAt(i8 - 1);
        if (cCharAt == 'B') {
            i8--;
            c7 = 'B';
        } else if (cCharAt == 'L') {
            i8--;
            c7 = 'L';
        } else if (cCharAt == 'S') {
            i8--;
            c7 = 'S';
        }
        if (charAt(this.f419np) == '-') {
            j7 = Long.MIN_VALUE;
            i7++;
            z6 = true;
        } else {
            j7 = -9223372036854775807L;
        }
        long j9 = MULTMIN_RADIX_TEN;
        if (i7 < i8) {
            j8 = -(charAt(i7) - '0');
            i7++;
        } else {
            j8 = 0;
        }
        while (i7 < i8) {
            int i9 = i7 + 1;
            int iCharAt = charAt(i7) - '0';
            if (j8 < j9) {
                return new BigInteger(numberString());
            }
            long j10 = j8 * 10;
            long j11 = iCharAt;
            if (j10 < j7 + j11) {
                return new BigInteger(numberString());
            }
            j8 = j10 - j11;
            i7 = i9;
            j9 = MULTMIN_RADIX_TEN;
        }
        if (!z6) {
            long j12 = -j8;
            return (j12 > 2147483647L || c7 == 'L') ? Long.valueOf(j12) : c7 == 'S' ? Short.valueOf((short) j12) : c7 == 'B' ? Byte.valueOf((byte) j12) : Integer.valueOf((int) j12);
        }
        if (i7 > this.f419np + 1) {
            return (j8 < -2147483648L || c7 == 'L') ? Long.valueOf(j8) : c7 == 'S' ? Short.valueOf((short) j8) : c7 == 'B' ? Byte.valueOf((byte) j8) : Integer.valueOf((int) j8);
        }
        throw new NumberFormatException(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public boolean isBlankInput() {
        int i7 = 0;
        while (true) {
            char cCharAt = charAt(i7);
            if (cCharAt == 26) {
                this.token = 20;
                return true;
            }
            if (!isWhitespace(cCharAt)) {
                return false;
            }
            i7++;
        }
    }

    public abstract boolean isEOF();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isEnabled(Feature feature) {
        return isEnabled(feature.mask);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isRef() {
        return this.f420sp == 4 && charAt(this.f419np + 1) == '$' && charAt(this.f419np + 2) == 'r' && charAt(this.f419np + 3) == 'e' && charAt(this.f419np + 4) == 'f';
    }

    public void lexError(String str, Object... objArr) {
        this.token = 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0085  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x005c -> B:12:0x0032). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long longValue() {
        /*
            r15 = this;
            int r0 = r15.f419np
            r1 = 0
            r2 = -1
            if (r0 != r2) goto L8
            r15.f419np = r1
        L8:
            int r0 = r15.f419np
            int r2 = r15.f420sp
            int r2 = r2 + r0
            char r3 = r15.charAt(r0)
            r4 = 45
            r5 = 1
            if (r3 != r4) goto L1c
            r3 = -9223372036854775808
            int r0 = r0 + 1
            r1 = 1
            goto L21
        L1c:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L21:
            r6 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            if (r0 >= r2) goto L34
            int r8 = r0 + 1
            char r0 = r15.charAt(r0)
            int r0 = r0 + (-48)
            int r0 = -r0
            long r9 = (long) r0
        L32:
            r0 = r8
            goto L36
        L34:
            r9 = 0
        L36:
            if (r0 >= r2) goto L73
            int r8 = r0 + 1
            char r0 = r15.charAt(r0)
            r11 = 76
            if (r0 == r11) goto L72
            r11 = 83
            if (r0 == r11) goto L72
            r11 = 66
            if (r0 != r11) goto L4b
            goto L72
        L4b:
            int r0 = r0 + (-48)
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 < 0) goto L68
            r11 = 10
            long r9 = r9 * r11
            long r11 = (long) r0
            long r13 = r3 + r11
            int r0 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r0 < 0) goto L5e
            long r9 = r9 - r11
            goto L32
        L5e:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.numberString()
            r0.<init>(r1)
            throw r0
        L68:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.numberString()
            r0.<init>(r1)
            throw r0
        L72:
            r0 = r8
        L73:
            if (r1 == 0) goto L85
            int r1 = r15.f419np
            int r1 = r1 + r5
            if (r0 <= r1) goto L7b
            return r9
        L7b:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.numberString()
            r0.<init>(r1)
            throw r0
        L85:
            long r0 = -r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.longValue():long");
    }

    public final boolean matchField(char[] cArr) {
        while (!charArrayCompare(cArr)) {
            if (!isWhitespace(this.f418ch)) {
                return false;
            }
            next();
        }
        int length = this.f417bp + cArr.length;
        this.f417bp = length;
        char cCharAt = charAt(length);
        this.f418ch = cCharAt;
        if (cCharAt == '{') {
            next();
            this.token = 12;
        } else if (cCharAt == '[') {
            next();
            this.token = 14;
        } else if (cCharAt == 'S' && charAt(this.f417bp + 1) == 'e' && charAt(this.f417bp + 2) == 't' && charAt(this.f417bp + 3) == '[') {
            int i7 = this.f417bp + 3;
            this.f417bp = i7;
            this.f418ch = charAt(i7);
            this.token = 21;
        } else {
            nextToken();
        }
        return true;
    }

    public boolean matchField2(char[] cArr) {
        throw new UnsupportedOperationException();
    }

    public final int matchStat() {
        return this.matchStat;
    }

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

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract char next();

    public final void nextIdent() {
        while (isWhitespace(this.f418ch)) {
            next();
        }
        char c7 = this.f418ch;
        if (c7 == '_' || c7 == '$' || Character.isLetter(c7)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextToken() {
        this.f420sp = 0;
        while (true) {
            this.pos = this.f417bp;
            char c7 = this.f418ch;
            if (c7 == '/') {
                skipComment();
            } else {
                if (c7 == '\"') {
                    scanString();
                    return;
                }
                if (c7 == ',') {
                    next();
                    this.token = 16;
                    return;
                }
                if (c7 >= '0' && c7 <= '9') {
                    scanNumber();
                    return;
                }
                if (c7 != '-') {
                    switch (c7) {
                        case '\b':
                        case '\t':
                        case '\n':
                        case '\f':
                        case '\r':
                        case ' ':
                            next();
                            break;
                        case '\'':
                            if (!isEnabled(Feature.AllowSingleQuotes)) {
                                throw new JSONException("Feature.AllowSingleQuotes is false");
                            }
                            scanStringSingleQuote();
                            return;
                        case '(':
                            next();
                            this.token = 10;
                            return;
                        case ')':
                            next();
                            this.token = 11;
                            return;
                        case '+':
                            next();
                            scanNumber();
                            return;
                        case '.':
                            next();
                            this.token = 25;
                            return;
                        case ':':
                            next();
                            this.token = 17;
                            return;
                        case ';':
                            next();
                            this.token = 24;
                            return;
                        case 'N':
                        case 'S':
                        case 'T':
                        case 'u':
                            scanIdent();
                            return;
                        case '[':
                            next();
                            this.token = 14;
                            return;
                        case ']':
                            next();
                            this.token = 15;
                            return;
                        case 'f':
                            scanFalse();
                            return;
                        case 'n':
                            scanNullOrNew();
                            return;
                        case 't':
                            scanTrue();
                            return;
                        case 'x':
                            scanHex();
                            return;
                        case '{':
                            next();
                            this.token = 12;
                            return;
                        case '}':
                            next();
                            this.token = 13;
                            return;
                        default:
                            if (isEOF()) {
                                if (this.token == 20) {
                                    throw new JSONException("EOF error");
                                }
                                this.token = 20;
                                int i7 = this.eofPos;
                                this.f417bp = i7;
                                this.pos = i7;
                                return;
                            }
                            char c8 = this.f418ch;
                            if (c8 > 31 && c8 != 127) {
                                lexError("illegal.char", String.valueOf((int) c8));
                                next();
                                return;
                            } else {
                                next();
                                break;
                            }
                            break;
                    }
                } else {
                    scanNumber();
                    return;
                }
            }
        }
    }

    public final void nextTokenWithChar(char c7) {
        this.f420sp = 0;
        while (true) {
            char c8 = this.f418ch;
            if (c8 == c7) {
                next();
                nextToken();
                return;
            }
            if (c8 != ' ' && c8 != '\n' && c8 != '\r' && c8 != '\t' && c8 != '\f' && c8 != '\b') {
                throw new JSONException("not match " + c7 + " - " + this.f418ch + ", info : " + info());
            }
            next();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon() {
        nextTokenWithChar(':');
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String numberString();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int pos() {
        return this.pos;
    }

    public final void putChar(char c7) {
        int i7 = this.f420sp;
        char[] cArr = this.sbuf;
        if (i7 == cArr.length) {
            char[] cArr2 = new char[cArr.length * 2];
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
            this.sbuf = cArr2;
        }
        char[] cArr3 = this.sbuf;
        int i8 = this.f420sp;
        this.f420sp = i8 + 1;
        cArr3[i8] = c7;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void resetStringPosition() {
        this.f420sp = 0;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public boolean scanBoolean(char c7) {
        boolean z6 = false;
        this.matchStat = 0;
        char cCharAt = charAt(this.f417bp + 0);
        int i7 = 5;
        if (cCharAt == 't') {
            if (charAt(this.f417bp + 1) != 'r' || C0861a.m670a(this.f417bp, 1, 1, this) != 'u' || C0861a.m670a(this.f417bp, 1, 2, this) != 'e') {
                this.matchStat = -1;
                return false;
            }
            cCharAt = charAt(this.f417bp + 4);
            z6 = true;
        } else if (cCharAt != 'f') {
            if (cCharAt == '1') {
                cCharAt = charAt(this.f417bp + 1);
                z6 = true;
            } else if (cCharAt == '0') {
                cCharAt = charAt(this.f417bp + 1);
            } else {
                i7 = 1;
            }
            i7 = 2;
        } else {
            if (charAt(this.f417bp + 1) != 'a' || C0861a.m670a(this.f417bp, 1, 1, this) != 'l' || C0861a.m670a(this.f417bp, 1, 2, this) != 's' || C0861a.m670a(this.f417bp, 1, 3, this) != 'e') {
                this.matchStat = -1;
                return false;
            }
            cCharAt = charAt(this.f417bp + 5);
            i7 = 6;
        }
        while (cCharAt != c7) {
            if (!isWhitespace(cCharAt)) {
                this.matchStat = -1;
                return z6;
            }
            cCharAt = charAt(this.f417bp + i7);
            i7++;
        }
        int i8 = this.f417bp + i7;
        this.f417bp = i8;
        this.f418ch = charAt(i8);
        this.matchStat = 3;
        return z6;
    }

    public Date scanDate(char c7) {
        long j7;
        Date date;
        int i7;
        boolean z6 = false;
        this.matchStat = 0;
        char cCharAt = charAt(this.f417bp + 0);
        int i8 = 5;
        if (cCharAt == '\"') {
            int iIndexOf = indexOf('\"', this.f417bp + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int i9 = this.f417bp + 1;
            String strSubString = subString(i9, iIndexOf - i9);
            if (strSubString.indexOf(92) != -1) {
                while (true) {
                    int i10 = 0;
                    for (int i11 = iIndexOf - 1; i11 >= 0 && charAt(i11) == '\\'; i11--) {
                        i10++;
                    }
                    if (i10 % 2 == 0) {
                        break;
                    }
                    iIndexOf = indexOf('\"', iIndexOf + 1);
                }
                int i12 = this.f417bp;
                int i13 = iIndexOf - (i12 + 1);
                strSubString = readString(sub_chars(i12 + 1, i13), i13);
            }
            int i14 = this.f417bp;
            int i15 = (iIndexOf - (i14 + 1)) + 1 + 1;
            int i16 = i15 + 1;
            cCharAt = charAt(i14 + i15);
            JSONScanner jSONScanner = new JSONScanner(strSubString);
            try {
                if (!jSONScanner.scanISO8601DateIfMatch(false)) {
                    this.matchStat = -1;
                    return null;
                }
                date = jSONScanner.getCalendar().getTime();
                jSONScanner.close();
                i8 = i16;
            } finally {
                jSONScanner.close();
            }
        } else {
            char c8 = '9';
            int i17 = 2;
            if (cCharAt == '-' || (cCharAt >= '0' && cCharAt <= '9')) {
                if (cCharAt == '-') {
                    cCharAt = charAt(this.f417bp + 1);
                    z6 = true;
                } else {
                    i17 = 1;
                }
                if (cCharAt < '0' || cCharAt > '9') {
                    j7 = 0;
                } else {
                    j7 = cCharAt - '0';
                    while (true) {
                        i7 = i17 + 1;
                        cCharAt = charAt(this.f417bp + i17);
                        if (cCharAt < '0' || cCharAt > c8) {
                            break;
                        }
                        j7 = (j7 * 10) + (cCharAt - '0');
                        c8 = '9';
                        i17 = i7;
                    }
                    i17 = i7;
                }
                if (j7 < 0) {
                    this.matchStat = -1;
                    return null;
                }
                if (z6) {
                    j7 = -j7;
                }
                date = new Date(j7);
                i8 = i17;
            } else {
                if (cCharAt != 'n' || charAt(this.f417bp + 1) != 'u' || C0861a.m670a(this.f417bp, 1, 1, this) != 'l' || C0861a.m670a(this.f417bp, 1, 2, this) != 'l') {
                    this.matchStat = -1;
                    return null;
                }
                this.matchStat = 5;
                cCharAt = charAt(this.f417bp + 4);
                date = null;
            }
        }
        if (cCharAt == ',') {
            int i18 = this.f417bp + i8;
            this.f417bp = i18;
            this.f418ch = charAt(i18);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        if (cCharAt != ']') {
            this.matchStat = -1;
            return null;
        }
        int i19 = i8 + 1;
        char cCharAt2 = charAt(this.f417bp + i8);
        if (cCharAt2 == ',') {
            this.token = 16;
            int i20 = this.f417bp + i19;
            this.f417bp = i20;
            this.f418ch = charAt(i20);
        } else if (cCharAt2 == ']') {
            this.token = 15;
            int i21 = this.f417bp + i19;
            this.f417bp = i21;
            this.f418ch = charAt(i21);
        } else if (cCharAt2 == '}') {
            this.token = 13;
            int i22 = this.f417bp + i19;
            this.f417bp = i22;
            this.f418ch = charAt(i22);
        } else {
            if (cCharAt2 != 26) {
                this.matchStat = -1;
                return null;
            }
            this.token = 20;
            this.f417bp = (i19 - 1) + this.f417bp;
            this.f418ch = JSONLexer.EOI;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public BigDecimal scanDecimal(char c7) {
        int i7;
        int i8;
        char cCharAt;
        JSONLexerBase jSONLexerBase;
        int i9;
        int i10;
        char c8;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.f417bp + 0);
        boolean z6 = cCharAt2 == '\"';
        if (z6) {
            cCharAt2 = charAt(this.f417bp + 1);
            i7 = 2;
        } else {
            i7 = 1;
        }
        if (cCharAt2 == '-') {
            cCharAt2 = charAt(this.f417bp + i7);
            i7++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            if (cCharAt2 != 'n' || charAt(this.f417bp + i7) != 'u' || C0861a.m670a(this.f417bp, i7, 1, this) != 'l' || C0861a.m670a(this.f417bp, i7, 2, this) != 'l') {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 5;
            int i11 = i7 + 3;
            int i12 = i11 + 1;
            char cCharAt3 = charAt(this.f417bp + i11);
            if (z6 && cCharAt3 == '\"') {
                int i13 = i12 + 1;
                char cCharAt4 = charAt(this.f417bp + i12);
                i12 = i13;
                cCharAt3 = cCharAt4;
            }
            while (cCharAt3 != ',') {
                if (cCharAt3 == '}') {
                    int i14 = this.f417bp + i12;
                    this.f417bp = i14;
                    this.f418ch = charAt(i14);
                    this.matchStat = 5;
                    this.token = 13;
                    return null;
                }
                if (!isWhitespace(cCharAt3)) {
                    this.matchStat = -1;
                    return null;
                }
                int i15 = i12 + 1;
                char cCharAt5 = charAt(this.f417bp + i12);
                i12 = i15;
                cCharAt3 = cCharAt5;
            }
            int i16 = this.f417bp + i12;
            this.f417bp = i16;
            this.f418ch = charAt(i16);
            this.matchStat = 5;
            this.token = 16;
            return null;
        }
        while (true) {
            i8 = i7 + 1;
            cCharAt = charAt(this.f417bp + i7);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i7 = i8;
        }
        if (cCharAt == '.') {
            int i17 = i8 + 1;
            char cCharAt6 = charAt(this.f417bp + i8);
            if (cCharAt6 < '0' || cCharAt6 > '9') {
                this.matchStat = -1;
                return null;
            }
            while (true) {
                i8 = i17 + 1;
                cCharAt = charAt(this.f417bp + i17);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i17 = i8;
            }
        }
        if (cCharAt == 'e' || cCharAt == 'E') {
            int i18 = i8 + 1;
            cCharAt = charAt(this.f417bp + i8);
            if (cCharAt == '+' || cCharAt == '-') {
                int i19 = i18 + 1;
                cCharAt = charAt(this.f417bp + i18);
                jSONLexerBase = this;
                i8 = i19;
                c8 = '0';
            } else {
                i8 = i18;
                c8 = '0';
                jSONLexerBase = this;
            }
            while (cCharAt >= c8 && cCharAt <= '9') {
                cCharAt = jSONLexerBase.charAt(jSONLexerBase.f417bp + i8);
                i8++;
            }
        } else {
            jSONLexerBase = this;
        }
        if (!z6) {
            i9 = jSONLexerBase.f417bp;
            i10 = ((i9 + i8) - i9) - 1;
        } else {
            if (cCharAt != '\"') {
                jSONLexerBase.matchStat = -1;
                return null;
            }
            int i20 = i8 + 1;
            cCharAt = jSONLexerBase.charAt(jSONLexerBase.f417bp + i8);
            int i21 = jSONLexerBase.f417bp;
            i9 = i21 + 1;
            i10 = ((i21 + i20) - i9) - 2;
            i8 = i20;
        }
        BigDecimal bigDecimal = new BigDecimal(jSONLexerBase.sub_chars(i9, i10));
        if (cCharAt == ',') {
            int i22 = jSONLexerBase.f417bp + i8;
            jSONLexerBase.f417bp = i22;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i22);
            jSONLexerBase.matchStat = 3;
            jSONLexerBase.token = 16;
            return bigDecimal;
        }
        if (cCharAt != ']') {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        int i23 = i8 + 1;
        char cCharAt7 = jSONLexerBase.charAt(jSONLexerBase.f417bp + i8);
        if (cCharAt7 == ',') {
            jSONLexerBase.token = 16;
            int i24 = jSONLexerBase.f417bp + i23;
            jSONLexerBase.f417bp = i24;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i24);
        } else if (cCharAt7 == ']') {
            jSONLexerBase.token = 15;
            int i25 = jSONLexerBase.f417bp + i23;
            jSONLexerBase.f417bp = i25;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i25);
        } else if (cCharAt7 == '}') {
            jSONLexerBase.token = 13;
            int i26 = jSONLexerBase.f417bp + i23;
            jSONLexerBase.f417bp = i26;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i26);
        } else {
            if (cCharAt7 != 26) {
                jSONLexerBase.matchStat = -1;
                return null;
            }
            jSONLexerBase.token = 20;
            jSONLexerBase.f417bp = (i23 - 1) + jSONLexerBase.f417bp;
            jSONLexerBase.f418ch = JSONLexer.EOI;
        }
        jSONLexerBase.matchStat = 4;
        return bigDecimal;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public double scanDouble(char c7) throws NumberFormatException {
        int i7;
        int i8;
        char cCharAt;
        boolean z6;
        long j7;
        JSONLexerBase jSONLexerBase;
        double d7;
        int i9;
        long j8;
        boolean z7;
        boolean z8;
        char cCharAt2;
        char c8;
        int i10;
        int i11;
        double d8;
        int i12;
        this.matchStat = 0;
        char cCharAt3 = charAt(this.f417bp + 0);
        boolean z9 = cCharAt3 == '\"';
        if (z9) {
            cCharAt3 = charAt(this.f417bp + 1);
            i7 = 2;
        } else {
            i7 = 1;
        }
        boolean z10 = cCharAt3 == '-';
        if (z10) {
            cCharAt3 = charAt(this.f417bp + i7);
            i7++;
        }
        char c9 = '0';
        if (cCharAt3 >= '0') {
            char c10 = '9';
            if (cCharAt3 <= '9') {
                long j9 = cCharAt3 - '0';
                while (true) {
                    i8 = i7 + 1;
                    cCharAt = charAt(this.f417bp + i7);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    j9 = (j9 * 10) + (cCharAt - '0');
                    i7 = i8;
                }
                if (cCharAt == '.') {
                    int i13 = i8 + 1;
                    char cCharAt4 = charAt(this.f417bp + i8);
                    if (cCharAt4 < '0' || cCharAt4 > '9') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    z6 = z10;
                    j9 = (j9 * 10) + (cCharAt4 - '0');
                    j7 = 10;
                    while (true) {
                        i8 = i13 + 1;
                        cCharAt = charAt(this.f417bp + i13);
                        if (cCharAt < c9 || cCharAt > c10) {
                            break;
                        }
                        j9 = (j9 * 10) + (cCharAt - '0');
                        j7 *= 10;
                        i13 = i8;
                        c9 = '0';
                        c10 = '9';
                    }
                } else {
                    z6 = z10;
                    j7 = 1;
                }
                boolean z11 = cCharAt == 'e' || cCharAt == 'E';
                if (z11) {
                    int i14 = i8 + 1;
                    char cCharAt5 = charAt(this.f417bp + i8);
                    if (cCharAt5 == '+' || cCharAt5 == '-') {
                        cCharAt5 = charAt(this.f417bp + i14);
                        jSONLexerBase = this;
                        d7 = 0.0d;
                        i12 = i14 + 1;
                    } else {
                        jSONLexerBase = this;
                        d7 = 0.0d;
                        i12 = i14;
                    }
                    long j10 = j9;
                    i9 = -1;
                    j8 = j7;
                    z7 = z11;
                    z8 = z9;
                    cCharAt2 = cCharAt5;
                    c8 = c7;
                    while (cCharAt2 >= '0' && cCharAt2 <= '9') {
                        cCharAt2 = jSONLexerBase.charAt(jSONLexerBase.f417bp + i12);
                        i12++;
                    }
                    j9 = j10;
                    i8 = i12;
                } else {
                    jSONLexerBase = this;
                    d7 = 0.0d;
                    i9 = -1;
                    j8 = j7;
                    z7 = z11;
                    z8 = z9;
                    cCharAt2 = cCharAt;
                    c8 = c7;
                }
                if (!z8) {
                    i10 = jSONLexerBase.f417bp;
                    i11 = ((i10 + i8) - i10) - 1;
                } else {
                    if (cCharAt2 != '\"') {
                        jSONLexerBase.matchStat = i9;
                        return d7;
                    }
                    int i15 = i8 + 1;
                    cCharAt2 = jSONLexerBase.charAt(jSONLexerBase.f417bp + i8);
                    int i16 = jSONLexerBase.f417bp;
                    i10 = i16 + 1;
                    i11 = ((i16 + i15) - i10) - 2;
                    i8 = i15;
                }
                if (z7 || i11 >= 17) {
                    d8 = Double.parseDouble(jSONLexerBase.subString(i10, i11));
                } else {
                    d8 = j9 / j8;
                    if (z6) {
                        d8 = -d8;
                    }
                }
                if (cCharAt2 != c8) {
                    jSONLexerBase.matchStat = i9;
                    return d8;
                }
                int i17 = jSONLexerBase.f417bp + i8;
                jSONLexerBase.f417bp = i17;
                jSONLexerBase.f418ch = jSONLexerBase.charAt(i17);
                jSONLexerBase.matchStat = 3;
                jSONLexerBase.token = 16;
                return d8;
            }
        }
        if (cCharAt3 != 'n' || charAt(this.f417bp + i7) != 'u' || C0861a.m670a(this.f417bp, i7, 1, this) != 'l' || C0861a.m670a(this.f417bp, i7, 2, this) != 'l') {
            this.matchStat = -1;
            return 0.0d;
        }
        this.matchStat = 5;
        int i18 = i7 + 3;
        int i19 = i18 + 1;
        char cCharAt6 = charAt(this.f417bp + i18);
        if (z9 && cCharAt6 == '\"') {
            int i20 = i19 + 1;
            char cCharAt7 = charAt(this.f417bp + i19);
            i19 = i20;
            cCharAt6 = cCharAt7;
        }
        while (cCharAt6 != ',') {
            if (cCharAt6 == ']') {
                int i21 = this.f417bp + i19;
                this.f417bp = i21;
                this.f418ch = charAt(i21);
                this.matchStat = 5;
                this.token = 15;
                return 0.0d;
            }
            if (!isWhitespace(cCharAt6)) {
                this.matchStat = -1;
                return 0.0d;
            }
            int i22 = i19 + 1;
            char cCharAt8 = charAt(this.f417bp + i19);
            i19 = i22;
            cCharAt6 = cCharAt8;
        }
        int i23 = this.f417bp + i19;
        this.f417bp = i23;
        this.f418ch = charAt(i23);
        this.matchStat = 5;
        this.token = 16;
        return 0.0d;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public Enum<?> scanEnum(Class<?> cls, SymbolTable symbolTable, char c7) {
        String strScanSymbolWithSeperator = scanSymbolWithSeperator(symbolTable, c7);
        if (strScanSymbolWithSeperator == null) {
            return null;
        }
        return Enum.valueOf(cls, strScanSymbolWithSeperator);
    }

    public final void scanFalse() {
        if (this.f418ch != 'f') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.f418ch != 'a') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.f418ch != 'l') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.f418ch != 's') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.f418ch != 'e') {
            throw new JSONException("error parse false");
        }
        next();
        char c7 = this.f418ch;
        if (c7 != ' ' && c7 != ',' && c7 != '}' && c7 != ']' && c7 != '\n' && c7 != '\r' && c7 != '\t' && c7 != 26 && c7 != '\f' && c7 != '\b' && c7 != ':' && c7 != '/') {
            throw new JSONException("scan false error");
        }
        this.token = 7;
    }

    public BigInteger scanFieldBigInteger(char[] cArr) {
        int i7;
        char cCharAt;
        int length;
        int i8;
        BigInteger bigIntegerValueOf;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length2 = cArr.length;
        int i9 = length2 + 1;
        char cCharAt2 = charAt(this.f417bp + length2);
        boolean z6 = cCharAt2 == '\"';
        if (z6) {
            cCharAt2 = charAt(this.f417bp + i9);
            i9++;
        }
        boolean z7 = cCharAt2 == '-';
        if (z7) {
            cCharAt2 = charAt(this.f417bp + i9);
            i9++;
        }
        char c7 = '0';
        if (cCharAt2 >= '0') {
            char c8 = '9';
            if (cCharAt2 <= '9') {
                long j7 = cCharAt2 - '0';
                while (true) {
                    i7 = i9 + 1;
                    cCharAt = charAt(this.f417bp + i9);
                    if (cCharAt < c7 || cCharAt > c8) {
                        break;
                    }
                    j7 = (j7 * 10) + (cCharAt - '0');
                    i9 = i7;
                    c7 = '0';
                    c8 = '9';
                }
                if (!z6) {
                    int i10 = this.f417bp;
                    length = cArr.length + i10;
                    i8 = ((i10 + i7) - length) - 1;
                } else {
                    if (cCharAt != '\"') {
                        this.matchStat = -1;
                        return null;
                    }
                    int i11 = i7 + 1;
                    cCharAt = charAt(this.f417bp + i7);
                    int i12 = this.f417bp;
                    length = cArr.length + i12 + 1;
                    i8 = ((i12 + i11) - length) - 2;
                    i7 = i11;
                }
                if (i8 < 20 || (z7 && i8 < 21)) {
                    if (z7) {
                        j7 = -j7;
                    }
                    bigIntegerValueOf = BigInteger.valueOf(j7);
                } else {
                    bigIntegerValueOf = new BigInteger(subString(length, i8));
                }
                if (cCharAt == ',') {
                    int i13 = this.f417bp + i7;
                    this.f417bp = i13;
                    this.f418ch = charAt(i13);
                    this.matchStat = 3;
                    this.token = 16;
                    return bigIntegerValueOf;
                }
                if (cCharAt != '}') {
                    this.matchStat = -1;
                    return null;
                }
                int i14 = i7 + 1;
                char cCharAt3 = charAt(this.f417bp + i7);
                if (cCharAt3 == ',') {
                    this.token = 16;
                    int i15 = this.f417bp + i14;
                    this.f417bp = i15;
                    this.f418ch = charAt(i15);
                } else if (cCharAt3 == ']') {
                    this.token = 15;
                    int i16 = this.f417bp + i14;
                    this.f417bp = i16;
                    this.f418ch = charAt(i16);
                } else if (cCharAt3 == '}') {
                    this.token = 13;
                    int i17 = this.f417bp + i14;
                    this.f417bp = i17;
                    this.f418ch = charAt(i17);
                } else {
                    if (cCharAt3 != 26) {
                        this.matchStat = -1;
                        return null;
                    }
                    this.token = 20;
                    this.f417bp = (i14 - 1) + this.f417bp;
                    this.f418ch = JSONLexer.EOI;
                }
                this.matchStat = 4;
                return bigIntegerValueOf;
            }
        }
        if (cCharAt2 != 'n' || charAt(this.f417bp + i9) != 'u' || C0861a.m670a(this.f417bp, i9, 1, this) != 'l' || C0861a.m670a(this.f417bp, i9, 2, this) != 'l') {
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 5;
        int i18 = i9 + 3;
        int i19 = i18 + 1;
        char cCharAt4 = charAt(this.f417bp + i18);
        if (z6 && cCharAt4 == '\"') {
            cCharAt4 = charAt(this.f417bp + i19);
            i19++;
        }
        while (cCharAt4 != ',') {
            if (cCharAt4 == '}') {
                int i20 = this.f417bp + i19;
                this.f417bp = i20;
                this.f418ch = charAt(i20);
                this.matchStat = 5;
                this.token = 13;
                return null;
            }
            if (!isWhitespace(cCharAt4)) {
                this.matchStat = -1;
                return null;
            }
            cCharAt4 = charAt(this.f417bp + i19);
            i19++;
        }
        int i21 = this.f417bp + i19;
        this.f417bp = i21;
        this.f418ch = charAt(i21);
        this.matchStat = 5;
        this.token = 16;
        return null;
    }

    public boolean scanFieldBoolean(char[] cArr) {
        int i7;
        boolean z6;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = cArr.length;
        int i8 = length + 1;
        char cCharAt = charAt(this.f417bp + length);
        if (cCharAt == 't') {
            int i9 = i8 + 1;
            if (charAt(this.f417bp + i8) != 'r') {
                this.matchStat = -1;
                return false;
            }
            int i10 = i9 + 1;
            if (charAt(this.f417bp + i9) != 'u') {
                this.matchStat = -1;
                return false;
            }
            i7 = i10 + 1;
            if (charAt(this.f417bp + i10) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z6 = true;
        } else {
            if (cCharAt != 'f') {
                this.matchStat = -1;
                return false;
            }
            int i11 = i8 + 1;
            if (charAt(this.f417bp + i8) != 'a') {
                this.matchStat = -1;
                return false;
            }
            int i12 = i11 + 1;
            if (charAt(this.f417bp + i11) != 'l') {
                this.matchStat = -1;
                return false;
            }
            int i13 = i12 + 1;
            if (charAt(this.f417bp + i12) != 's') {
                this.matchStat = -1;
                return false;
            }
            int i14 = i13 + 1;
            if (charAt(this.f417bp + i13) != 'e') {
                this.matchStat = -1;
                return false;
            }
            i7 = i14;
            z6 = false;
        }
        int i15 = i7 + 1;
        char cCharAt2 = charAt(this.f417bp + i7);
        if (cCharAt2 == ',') {
            int i16 = this.f417bp + i15;
            this.f417bp = i16;
            this.f418ch = charAt(i16);
            this.matchStat = 3;
            this.token = 16;
            return z6;
        }
        if (cCharAt2 != '}') {
            this.matchStat = -1;
            return false;
        }
        int i17 = i15 + 1;
        char cCharAt3 = charAt(this.f417bp + i15);
        if (cCharAt3 == ',') {
            this.token = 16;
            int i18 = this.f417bp + i17;
            this.f417bp = i18;
            this.f418ch = charAt(i18);
        } else if (cCharAt3 == ']') {
            this.token = 15;
            int i19 = this.f417bp + i17;
            this.f417bp = i19;
            this.f418ch = charAt(i19);
        } else if (cCharAt3 == '}') {
            this.token = 13;
            int i20 = this.f417bp + i17;
            this.f417bp = i20;
            this.f418ch = charAt(i20);
        } else {
            if (cCharAt3 != 26) {
                this.matchStat = -1;
                return false;
            }
            this.token = 20;
            this.f417bp = (i17 - 1) + this.f417bp;
            this.f418ch = JSONLexer.EOI;
        }
        this.matchStat = 4;
        return z6;
    }

    public Date scanFieldDate(char[] cArr) {
        int i7;
        long j7;
        Date date;
        int i8;
        char cCharAt;
        boolean z6 = false;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i9 = length + 1;
        char cCharAt2 = charAt(this.f417bp + length);
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf('\"', this.f417bp + cArr.length + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int length2 = this.f417bp + cArr.length + 1;
            String strSubString = subString(length2, iIndexOf - length2);
            if (strSubString.indexOf(92) != -1) {
                while (true) {
                    int i10 = 0;
                    for (int i11 = iIndexOf - 1; i11 >= 0 && charAt(i11) == '\\'; i11--) {
                        i10++;
                    }
                    if (i10 % 2 == 0) {
                        break;
                    }
                    iIndexOf = indexOf('\"', iIndexOf + 1);
                }
                int i12 = this.f417bp;
                int length3 = iIndexOf - ((cArr.length + i12) + 1);
                strSubString = readString(sub_chars(i12 + cArr.length + 1, length3), length3);
            }
            int i13 = this.f417bp;
            int length4 = (iIndexOf - ((cArr.length + i13) + 1)) + 1 + i9;
            i7 = length4 + 1;
            cCharAt2 = charAt(i13 + length4);
            JSONScanner jSONScanner = new JSONScanner(strSubString);
            try {
                if (!jSONScanner.scanISO8601DateIfMatch(false)) {
                    this.matchStat = -1;
                    return null;
                }
                date = jSONScanner.getCalendar().getTime();
            } finally {
                jSONScanner.close();
            }
        } else {
            if (cCharAt2 != '-' && (cCharAt2 < '0' || cCharAt2 > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (cCharAt2 == '-') {
                cCharAt2 = charAt(this.f417bp + i9);
                i9++;
                z6 = true;
            }
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                i7 = i9;
                j7 = 0;
            } else {
                j7 = cCharAt2 - '0';
                while (true) {
                    i8 = i9 + 1;
                    cCharAt = charAt(this.f417bp + i9);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    j7 = (j7 * 10) + (cCharAt - '0');
                    i9 = i8;
                }
                cCharAt2 = cCharAt;
                i7 = i8;
            }
            if (j7 < 0) {
                this.matchStat = -1;
                return null;
            }
            if (z6) {
                j7 = -j7;
            }
            date = new Date(j7);
        }
        if (cCharAt2 == ',') {
            int i14 = this.f417bp + i7;
            this.f417bp = i14;
            this.f418ch = charAt(i14);
            this.matchStat = 3;
            return date;
        }
        if (cCharAt2 != '}') {
            this.matchStat = -1;
            return null;
        }
        int i15 = i7 + 1;
        char cCharAt3 = charAt(this.f417bp + i7);
        if (cCharAt3 == ',') {
            this.token = 16;
            int i16 = this.f417bp + i15;
            this.f417bp = i16;
            this.f418ch = charAt(i16);
        } else if (cCharAt3 == ']') {
            this.token = 15;
            int i17 = this.f417bp + i15;
            this.f417bp = i17;
            this.f418ch = charAt(i17);
        } else if (cCharAt3 == '}') {
            this.token = 13;
            int i18 = this.f417bp + i15;
            this.f417bp = i18;
            this.f418ch = charAt(i18);
        } else {
            if (cCharAt3 != 26) {
                this.matchStat = -1;
                return null;
            }
            this.token = 20;
            this.f417bp = (i15 - 1) + this.f417bp;
            this.f418ch = JSONLexer.EOI;
        }
        this.matchStat = 4;
        return date;
    }

    public BigDecimal scanFieldDecimal(char[] cArr) {
        int i7;
        char cCharAt;
        JSONLexerBase jSONLexerBase;
        int length;
        int i8;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length2 = cArr.length;
        int i9 = length2 + 1;
        char cCharAt2 = charAt(this.f417bp + length2);
        boolean z6 = cCharAt2 == '\"';
        if (z6) {
            cCharAt2 = charAt(this.f417bp + i9);
            i9++;
        }
        if (cCharAt2 == '-') {
            cCharAt2 = charAt(this.f417bp + i9);
            i9++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            if (cCharAt2 != 'n' || charAt(this.f417bp + i9) != 'u' || C0861a.m670a(this.f417bp, i9, 1, this) != 'l' || C0861a.m670a(this.f417bp, i9, 2, this) != 'l') {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 5;
            int i10 = i9 + 3;
            int i11 = i10 + 1;
            char cCharAt3 = charAt(this.f417bp + i10);
            if (z6 && cCharAt3 == '\"') {
                int i12 = i11 + 1;
                cCharAt3 = charAt(this.f417bp + i11);
                i11 = i12;
            }
            while (cCharAt3 != ',') {
                if (cCharAt3 == '}') {
                    int i13 = this.f417bp + i11;
                    this.f417bp = i13;
                    this.f418ch = charAt(i13);
                    this.matchStat = 5;
                    this.token = 13;
                    return null;
                }
                if (!isWhitespace(cCharAt3)) {
                    this.matchStat = -1;
                    return null;
                }
                int i14 = i11 + 1;
                cCharAt3 = charAt(this.f417bp + i11);
                i11 = i14;
            }
            int i15 = this.f417bp + i11;
            this.f417bp = i15;
            this.f418ch = charAt(i15);
            this.matchStat = 5;
            this.token = 16;
            return null;
        }
        while (true) {
            i7 = i9 + 1;
            cCharAt = charAt(this.f417bp + i9);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i9 = i7;
        }
        if (cCharAt == '.') {
            int i16 = i7 + 1;
            char cCharAt4 = charAt(this.f417bp + i7);
            if (cCharAt4 < '0' || cCharAt4 > '9') {
                this.matchStat = -1;
                return null;
            }
            while (true) {
                i7 = i16 + 1;
                cCharAt = charAt(this.f417bp + i16);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i16 = i7;
            }
        }
        if (cCharAt == 'e' || cCharAt == 'E') {
            int i17 = i7 + 1;
            cCharAt = charAt(this.f417bp + i7);
            if (cCharAt == '+' || cCharAt == '-') {
                int i18 = i17 + 1;
                cCharAt = charAt(this.f417bp + i17);
                jSONLexerBase = this;
                i7 = i18;
            } else {
                i7 = i17;
                jSONLexerBase = this;
            }
            while (cCharAt >= '0' && cCharAt <= '9') {
                int i19 = i7 + 1;
                cCharAt = jSONLexerBase.charAt(jSONLexerBase.f417bp + i7);
                i7 = i19;
            }
        } else {
            jSONLexerBase = this;
        }
        if (!z6) {
            int i20 = jSONLexerBase.f417bp;
            length = cArr.length + i20;
            i8 = ((i20 + i7) - length) - 1;
        } else {
            if (cCharAt != '\"') {
                jSONLexerBase.matchStat = -1;
                return null;
            }
            int i21 = i7 + 1;
            cCharAt = jSONLexerBase.charAt(jSONLexerBase.f417bp + i7);
            int i22 = jSONLexerBase.f417bp;
            length = cArr.length + i22 + 1;
            i8 = ((i22 + i21) - length) - 2;
            i7 = i21;
        }
        BigDecimal bigDecimal = new BigDecimal(jSONLexerBase.sub_chars(length, i8));
        if (cCharAt == ',') {
            int i23 = jSONLexerBase.f417bp + i7;
            jSONLexerBase.f417bp = i23;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i23);
            jSONLexerBase.matchStat = 3;
            jSONLexerBase.token = 16;
            return bigDecimal;
        }
        if (cCharAt != '}') {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        int i24 = i7 + 1;
        char cCharAt5 = jSONLexerBase.charAt(jSONLexerBase.f417bp + i7);
        if (cCharAt5 == ',') {
            jSONLexerBase.token = 16;
            int i25 = jSONLexerBase.f417bp + i24;
            jSONLexerBase.f417bp = i25;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i25);
        } else if (cCharAt5 == ']') {
            jSONLexerBase.token = 15;
            int i26 = jSONLexerBase.f417bp + i24;
            jSONLexerBase.f417bp = i26;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i26);
        } else if (cCharAt5 == '}') {
            jSONLexerBase.token = 13;
            int i27 = jSONLexerBase.f417bp + i24;
            jSONLexerBase.f417bp = i27;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i27);
        } else {
            if (cCharAt5 != 26) {
                jSONLexerBase.matchStat = -1;
                return null;
            }
            jSONLexerBase.token = 20;
            jSONLexerBase.f417bp = (i24 - 1) + jSONLexerBase.f417bp;
            jSONLexerBase.f418ch = JSONLexer.EOI;
        }
        jSONLexerBase.matchStat = 4;
        return bigDecimal;
    }

    public final double scanFieldDouble(char[] cArr) throws NumberFormatException {
        int i7;
        char cCharAt;
        JSONLexerBase jSONLexerBase;
        int length;
        int i8;
        double d7;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0.0d;
        }
        int length2 = cArr.length;
        int i9 = length2 + 1;
        char cCharAt2 = charAt(this.f417bp + length2);
        boolean z6 = cCharAt2 == '\"';
        if (z6) {
            cCharAt2 = charAt(this.f417bp + i9);
            i9++;
        }
        boolean z7 = cCharAt2 == '-';
        if (z7) {
            cCharAt2 = charAt(this.f417bp + i9);
            i9++;
        }
        char c7 = '0';
        if (cCharAt2 >= '0') {
            char c8 = '9';
            if (cCharAt2 <= '9') {
                long j7 = cCharAt2 - '0';
                while (true) {
                    i7 = i9 + 1;
                    cCharAt = charAt(this.f417bp + i9);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    j7 = (j7 * 10) + (cCharAt - '0');
                    i9 = i7;
                    z7 = z7;
                }
                boolean z8 = z7;
                long j8 = 1;
                if (cCharAt == '.') {
                    int i10 = i7 + 1;
                    char cCharAt3 = charAt(this.f417bp + i7);
                    if (cCharAt3 < '0' || cCharAt3 > '9') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    j7 = (j7 * 10) + (cCharAt3 - '0');
                    long j9 = 10;
                    while (true) {
                        i7 = i10 + 1;
                        cCharAt = charAt(this.f417bp + i10);
                        if (cCharAt < c7 || cCharAt > c8) {
                            break;
                        }
                        j7 = (j7 * 10) + (cCharAt - '0');
                        j9 *= 10;
                        i10 = i7;
                        c7 = '0';
                        c8 = '9';
                    }
                    j8 = j9;
                }
                boolean z9 = cCharAt == 'e' || cCharAt == 'E';
                if (z9) {
                    int i11 = i7 + 1;
                    cCharAt = charAt(this.f417bp + i7);
                    if (cCharAt == '+' || cCharAt == '-') {
                        cCharAt = charAt(this.f417bp + i11);
                        jSONLexerBase = this;
                        i7 = i11 + 1;
                    } else {
                        jSONLexerBase = this;
                        i7 = i11;
                    }
                    while (cCharAt >= '0' && cCharAt <= '9') {
                        cCharAt = jSONLexerBase.charAt(jSONLexerBase.f417bp + i7);
                        i7++;
                    }
                } else {
                    jSONLexerBase = this;
                }
                if (!z6) {
                    int i12 = jSONLexerBase.f417bp;
                    length = cArr.length + i12;
                    i8 = ((i12 + i7) - length) - 1;
                } else {
                    if (cCharAt != '\"') {
                        jSONLexerBase.matchStat = -1;
                        return 0.0d;
                    }
                    int i13 = i7 + 1;
                    cCharAt = jSONLexerBase.charAt(jSONLexerBase.f417bp + i7);
                    int i14 = jSONLexerBase.f417bp;
                    length = cArr.length + i14 + 1;
                    i8 = ((i14 + i13) - length) - 2;
                    i7 = i13;
                }
                if (z9 || i8 >= 17) {
                    d7 = Double.parseDouble(jSONLexerBase.subString(length, i8));
                } else {
                    d7 = j7 / j8;
                    if (z8) {
                        d7 = -d7;
                    }
                }
                if (cCharAt == ',') {
                    int i15 = jSONLexerBase.f417bp + i7;
                    jSONLexerBase.f417bp = i15;
                    jSONLexerBase.f418ch = jSONLexerBase.charAt(i15);
                    jSONLexerBase.matchStat = 3;
                    jSONLexerBase.token = 16;
                    return d7;
                }
                if (cCharAt != '}') {
                    jSONLexerBase.matchStat = -1;
                    return 0.0d;
                }
                int i16 = i7 + 1;
                char cCharAt4 = jSONLexerBase.charAt(jSONLexerBase.f417bp + i7);
                if (cCharAt4 == ',') {
                    jSONLexerBase.token = 16;
                    int i17 = jSONLexerBase.f417bp + i16;
                    jSONLexerBase.f417bp = i17;
                    jSONLexerBase.f418ch = jSONLexerBase.charAt(i17);
                } else if (cCharAt4 == ']') {
                    jSONLexerBase.token = 15;
                    int i18 = jSONLexerBase.f417bp + i16;
                    jSONLexerBase.f417bp = i18;
                    jSONLexerBase.f418ch = jSONLexerBase.charAt(i18);
                } else if (cCharAt4 == '}') {
                    jSONLexerBase.token = 13;
                    int i19 = jSONLexerBase.f417bp + i16;
                    jSONLexerBase.f417bp = i19;
                    jSONLexerBase.f418ch = jSONLexerBase.charAt(i19);
                } else {
                    if (cCharAt4 != 26) {
                        jSONLexerBase.matchStat = -1;
                        return 0.0d;
                    }
                    jSONLexerBase.token = 20;
                    jSONLexerBase.f417bp = (i16 - 1) + jSONLexerBase.f417bp;
                    jSONLexerBase.f418ch = JSONLexer.EOI;
                }
                jSONLexerBase.matchStat = 4;
                return d7;
            }
        }
        if (cCharAt2 != 'n' || charAt(this.f417bp + i9) != 'u' || C0861a.m670a(this.f417bp, i9, 1, this) != 'l' || C0861a.m670a(this.f417bp, i9, 2, this) != 'l') {
            this.matchStat = -1;
            return 0.0d;
        }
        this.matchStat = 5;
        int i20 = i9 + 3;
        int i21 = i20 + 1;
        char cCharAt5 = charAt(this.f417bp + i20);
        if (z6 && cCharAt5 == '\"') {
            cCharAt5 = charAt(this.f417bp + i21);
            i21++;
        }
        while (cCharAt5 != ',') {
            if (cCharAt5 == '}') {
                int i22 = this.f417bp + i21;
                this.f417bp = i22;
                this.f418ch = charAt(i22);
                this.matchStat = 5;
                this.token = 13;
                return 0.0d;
            }
            if (!isWhitespace(cCharAt5)) {
                this.matchStat = -1;
                return 0.0d;
            }
            cCharAt5 = charAt(this.f417bp + i21);
            i21++;
        }
        int i23 = this.f417bp + i21;
        this.f417bp = i23;
        this.f418ch = charAt(i23);
        this.matchStat = 5;
        this.token = 16;
        return 0.0d;
    }

    public final float scanFieldFloat(char[] cArr) throws NumberFormatException {
        int i7;
        char cCharAt;
        int i8;
        JSONLexerBase jSONLexerBase;
        boolean z6;
        int length;
        int i9;
        float f7;
        char cCharAt2;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0.0f;
        }
        int length2 = cArr.length;
        int i10 = length2 + 1;
        char cCharAt3 = charAt(this.f417bp + length2);
        boolean z7 = cCharAt3 == '\"';
        if (z7) {
            cCharAt3 = charAt(this.f417bp + i10);
            i10++;
        }
        boolean z8 = cCharAt3 == '-';
        if (z8) {
            cCharAt3 = charAt(this.f417bp + i10);
            i10++;
        }
        if (cCharAt3 < '0' || cCharAt3 > '9') {
            if (cCharAt3 != 'n' || charAt(this.f417bp + i10) != 'u' || C0861a.m670a(this.f417bp, i10, 1, this) != 'l' || C0861a.m670a(this.f417bp, i10, 2, this) != 'l') {
                this.matchStat = -1;
                return 0.0f;
            }
            this.matchStat = 5;
            int i11 = i10 + 3;
            int i12 = i11 + 1;
            char cCharAt4 = charAt(this.f417bp + i11);
            if (z7 && cCharAt4 == '\"') {
                int i13 = i12 + 1;
                cCharAt4 = charAt(this.f417bp + i12);
                i12 = i13;
            }
            while (cCharAt4 != ',') {
                if (cCharAt4 == '}') {
                    int i14 = this.f417bp + i12;
                    this.f417bp = i14;
                    this.f418ch = charAt(i14);
                    this.matchStat = 5;
                    this.token = 13;
                    return 0.0f;
                }
                if (!isWhitespace(cCharAt4)) {
                    this.matchStat = -1;
                    return 0.0f;
                }
                int i15 = i12 + 1;
                cCharAt4 = charAt(this.f417bp + i12);
                i12 = i15;
            }
            int i16 = this.f417bp + i12;
            this.f417bp = i16;
            this.f418ch = charAt(i16);
            this.matchStat = 5;
            this.token = 16;
            return 0.0f;
        }
        long j7 = cCharAt3 - '0';
        while (true) {
            i7 = i10 + 1;
            cCharAt = charAt(this.f417bp + i10);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j7 = (j7 * 10) + (cCharAt - '0');
            i10 = i7;
            z8 = z8;
        }
        boolean z9 = z8;
        if (cCharAt == '.') {
            int i17 = i7 + 1;
            char cCharAt5 = charAt(this.f417bp + i7);
            if (cCharAt5 < '0' || cCharAt5 > '9') {
                this.matchStat = -1;
                return 0.0f;
            }
            j7 = (j7 * 10) + (cCharAt5 - '0');
            int i18 = 10;
            while (true) {
                i7 = i17 + 1;
                cCharAt2 = charAt(this.f417bp + i17);
                if (cCharAt2 < '0' || cCharAt2 > '9') {
                    break;
                }
                j7 = (j7 * 10) + (cCharAt2 - '0');
                i18 *= 10;
                i17 = i7;
            }
            i8 = i18;
            cCharAt = cCharAt2;
        } else {
            i8 = 1;
        }
        boolean z10 = cCharAt == 'e' || cCharAt == 'E';
        if (z10) {
            int i19 = i7 + 1;
            cCharAt = charAt(this.f417bp + i7);
            if (cCharAt == '+' || cCharAt == '-') {
                i7 = i19 + 1;
                cCharAt = charAt(this.f417bp + i19);
                jSONLexerBase = this;
            } else {
                jSONLexerBase = this;
                i7 = i19;
            }
            z6 = z9;
            while (cCharAt >= '0' && cCharAt <= '9') {
                cCharAt = jSONLexerBase.charAt(jSONLexerBase.f417bp + i7);
                i7++;
            }
        } else {
            jSONLexerBase = this;
            z6 = z9;
        }
        if (!z7) {
            int i20 = jSONLexerBase.f417bp;
            length = cArr.length + i20;
            i9 = ((i20 + i7) - length) - 1;
        } else {
            if (cCharAt != '\"') {
                jSONLexerBase.matchStat = -1;
                return 0.0f;
            }
            int i21 = i7 + 1;
            cCharAt = jSONLexerBase.charAt(jSONLexerBase.f417bp + i7);
            int i22 = jSONLexerBase.f417bp;
            length = cArr.length + i22 + 1;
            i9 = ((i22 + i21) - length) - 2;
            i7 = i21;
        }
        if (z10 || i9 >= 17) {
            f7 = Float.parseFloat(jSONLexerBase.subString(length, i9));
        } else {
            f7 = (float) (j7 / i8);
            if (z6) {
                f7 = -f7;
            }
        }
        if (cCharAt == ',') {
            int i23 = jSONLexerBase.f417bp + i7;
            jSONLexerBase.f417bp = i23;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i23);
            jSONLexerBase.matchStat = 3;
            jSONLexerBase.token = 16;
            return f7;
        }
        if (cCharAt != '}') {
            jSONLexerBase.matchStat = -1;
            return 0.0f;
        }
        int i24 = i7 + 1;
        char cCharAt6 = jSONLexerBase.charAt(jSONLexerBase.f417bp + i7);
        if (cCharAt6 == ',') {
            jSONLexerBase.token = 16;
            int i25 = jSONLexerBase.f417bp + i24;
            jSONLexerBase.f417bp = i25;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i25);
        } else if (cCharAt6 == ']') {
            jSONLexerBase.token = 15;
            int i26 = jSONLexerBase.f417bp + i24;
            jSONLexerBase.f417bp = i26;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i26);
        } else if (cCharAt6 == '}') {
            jSONLexerBase.token = 13;
            int i27 = jSONLexerBase.f417bp + i24;
            jSONLexerBase.f417bp = i27;
            jSONLexerBase.f418ch = jSONLexerBase.charAt(i27);
        } else {
            if (cCharAt6 != 26) {
                jSONLexerBase.matchStat = -1;
                return 0.0f;
            }
            jSONLexerBase.f417bp = (i24 - 1) + jSONLexerBase.f417bp;
            jSONLexerBase.token = 20;
            jSONLexerBase.f418ch = JSONLexer.EOI;
        }
        jSONLexerBase.matchStat = 4;
        return f7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x01c2, code lost:
    
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01c4, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a5, code lost:
    
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a7, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[] scanFieldFloatArray(char[] r21) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 453
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloatArray(char[]):float[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x021f, code lost:
    
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0221, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b4, code lost:
    
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b6, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[][] scanFieldFloatArray2(char[] r21) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloatArray2(char[]):float[][]");
    }

    public int scanFieldInt(char[] cArr) {
        int i7;
        char cCharAt;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i8 = length + 1;
        char cCharAt2 = charAt(this.f417bp + length);
        boolean z6 = cCharAt2 == '-';
        if (z6) {
            cCharAt2 = charAt(this.f417bp + i8);
            i8++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i9 = cCharAt2 - '0';
        while (true) {
            i7 = i8 + 1;
            cCharAt = charAt(this.f417bp + i8);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i9 = (i9 * 10) + (cCharAt - '0');
            i8 = i7;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if ((i9 < 0 || i7 > cArr.length + 14) && !(i9 == Integer.MIN_VALUE && i7 == 17 && z6)) {
            this.matchStat = -1;
            return 0;
        }
        if (cCharAt == ',') {
            int i10 = this.f417bp + i7;
            this.f417bp = i10;
            this.f418ch = charAt(i10);
            this.matchStat = 3;
            this.token = 16;
            return z6 ? -i9 : i9;
        }
        if (cCharAt != '}') {
            this.matchStat = -1;
            return 0;
        }
        int i11 = i7 + 1;
        char cCharAt3 = charAt(this.f417bp + i7);
        if (cCharAt3 == ',') {
            this.token = 16;
            int i12 = this.f417bp + i11;
            this.f417bp = i12;
            this.f418ch = charAt(i12);
        } else if (cCharAt3 == ']') {
            this.token = 15;
            int i13 = this.f417bp + i11;
            this.f417bp = i13;
            this.f418ch = charAt(i13);
        } else if (cCharAt3 == '}') {
            this.token = 13;
            int i14 = this.f417bp + i11;
            this.f417bp = i14;
            this.f418ch = charAt(i14);
        } else {
            if (cCharAt3 != 26) {
                this.matchStat = -1;
                return 0;
            }
            this.token = 20;
            this.f417bp = (i11 - 1) + this.f417bp;
            this.f418ch = JSONLexer.EOI;
        }
        this.matchStat = 4;
        return z6 ? -i9 : i9;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x011f, code lost:
    
        r2 = r4;
        r17.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0122, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int[] scanFieldIntArray(char[] r18) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldIntArray(char[]):int[]");
    }

    public long scanFieldLong(char[] cArr) {
        boolean z6;
        int i7;
        char cCharAt;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = cArr.length;
        int i8 = length + 1;
        char cCharAt2 = charAt(this.f417bp + length);
        if (cCharAt2 == '-') {
            cCharAt2 = charAt(this.f417bp + i8);
            i8++;
            z6 = true;
        } else {
            z6 = false;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0L;
        }
        long j7 = cCharAt2 - '0';
        while (true) {
            i7 = i8 + 1;
            cCharAt = charAt(this.f417bp + i8);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j7 = (j7 * 10) + (cCharAt - '0');
            i8 = i7;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0L;
        }
        if (!(i7 - cArr.length < 21 && (j7 >= 0 || (j7 == Long.MIN_VALUE && z6)))) {
            this.matchStat = -1;
            return 0L;
        }
        if (cCharAt == ',') {
            int i9 = this.f417bp + i7;
            this.f417bp = i9;
            this.f418ch = charAt(i9);
            this.matchStat = 3;
            this.token = 16;
            return z6 ? -j7 : j7;
        }
        if (cCharAt != '}') {
            this.matchStat = -1;
            return 0L;
        }
        int i10 = i7 + 1;
        char cCharAt3 = charAt(this.f417bp + i7);
        if (cCharAt3 == ',') {
            this.token = 16;
            int i11 = this.f417bp + i10;
            this.f417bp = i11;
            this.f418ch = charAt(i11);
        } else if (cCharAt3 == ']') {
            this.token = 15;
            int i12 = this.f417bp + i10;
            this.f417bp = i12;
            this.f418ch = charAt(i12);
        } else if (cCharAt3 == '}') {
            this.token = 13;
            int i13 = this.f417bp + i10;
            this.f417bp = i13;
            this.f418ch = charAt(i13);
        } else {
            if (cCharAt3 != 26) {
                this.matchStat = -1;
                return 0L;
            }
            this.token = 20;
            this.f417bp = (i10 - 1) + this.f417bp;
            this.f418ch = JSONLexer.EOI;
        }
        this.matchStat = 4;
        return z6 ? -j7 : j7;
    }

    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return stringDefaultValue();
        }
        int length = cArr.length;
        int i7 = length + 1;
        if (charAt(this.f417bp + length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int iIndexOf = indexOf('\"', this.f417bp + cArr.length + 1);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        int length2 = this.f417bp + cArr.length + 1;
        String strSubString = subString(length2, iIndexOf - length2);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i8 = 0;
                for (int i9 = iIndexOf - 1; i9 >= 0 && charAt(i9) == '\\'; i9--) {
                    i8++;
                }
                if (i8 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf('\"', iIndexOf + 1);
            }
            int i10 = this.f417bp;
            int length3 = iIndexOf - ((cArr.length + i10) + 1);
            strSubString = readString(sub_chars(i10 + cArr.length + 1, length3), length3);
        }
        int i11 = this.f417bp;
        int length4 = (iIndexOf - ((cArr.length + i11) + 1)) + 1 + i7;
        int i12 = length4 + 1;
        char cCharAt = charAt(i11 + length4);
        if (cCharAt == ',') {
            int i13 = this.f417bp + i12;
            this.f417bp = i13;
            this.f418ch = charAt(i13);
            this.matchStat = 3;
            return strSubString;
        }
        if (cCharAt != '}') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int i14 = i12 + 1;
        char cCharAt2 = charAt(this.f417bp + i12);
        if (cCharAt2 == ',') {
            this.token = 16;
            int i15 = this.f417bp + i14;
            this.f417bp = i15;
            this.f418ch = charAt(i15);
        } else if (cCharAt2 == ']') {
            this.token = 15;
            int i16 = this.f417bp + i14;
            this.f417bp = i16;
            this.f418ch = charAt(i16);
        } else if (cCharAt2 == '}') {
            this.token = 13;
            int i17 = this.f417bp + i14;
            this.f417bp = i17;
            this.f418ch = charAt(i17);
        } else {
            if (cCharAt2 != 26) {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            this.token = 20;
            this.f417bp = (i14 - 1) + this.f417bp;
            this.f418ch = JSONLexer.EOI;
        }
        this.matchStat = 4;
        return strSubString;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e4, code lost:
    
        if (r11 != ']') goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ea, code lost:
    
        if (r12.size() != 0) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ec, code lost:
    
        r5 = r0 + 1;
        r11 = charAt(r10.f417bp + r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x016b, code lost:
    
        throw new com.alibaba.fastjson.JSONException("illega str");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Collection<java.lang.String> scanFieldStringArray(char[] r11, java.lang.Class<?> r12) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldStringArray(char[], java.lang.Class):java.util.Collection");
    }

    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = cArr.length;
        int i7 = length + 1;
        if (charAt(this.f417bp + length) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j7 = -3750763034362895579L;
        while (true) {
            int i8 = i7 + 1;
            char cCharAt = charAt(this.f417bp + i7);
            if (cCharAt == '\"') {
                int i9 = i8 + 1;
                char cCharAt2 = charAt(this.f417bp + i8);
                if (cCharAt2 == ',') {
                    int i10 = this.f417bp + i9;
                    this.f417bp = i10;
                    this.f418ch = charAt(i10);
                    this.matchStat = 3;
                    return j7;
                }
                if (cCharAt2 != '}') {
                    this.matchStat = -1;
                    return 0L;
                }
                int i11 = i9 + 1;
                char cCharAt3 = charAt(this.f417bp + i9);
                if (cCharAt3 == ',') {
                    this.token = 16;
                    int i12 = this.f417bp + i11;
                    this.f417bp = i12;
                    this.f418ch = charAt(i12);
                } else if (cCharAt3 == ']') {
                    this.token = 15;
                    int i13 = this.f417bp + i11;
                    this.f417bp = i13;
                    this.f418ch = charAt(i13);
                } else if (cCharAt3 == '}') {
                    this.token = 13;
                    int i14 = this.f417bp + i11;
                    this.f417bp = i14;
                    this.f418ch = charAt(i14);
                } else {
                    if (cCharAt3 != 26) {
                        this.matchStat = -1;
                        return 0L;
                    }
                    this.token = 20;
                    this.f417bp = (i11 - 1) + this.f417bp;
                    this.f418ch = JSONLexer.EOI;
                }
                this.matchStat = 4;
                return j7;
            }
            j7 = (j7 ^ cCharAt) * 1099511628211L;
            if (cCharAt == '\\') {
                this.matchStat = -1;
                return 0L;
            }
            i7 = i8;
        }
    }

    public UUID scanFieldUUID(char[] cArr) {
        char cCharAt;
        int i7;
        UUID uuid;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i21 = length + 1;
        char cCharAt2 = charAt(this.f417bp + length);
        char c7 = 4;
        if (cCharAt2 != '\"') {
            if (cCharAt2 == 'n') {
                int i22 = i21 + 1;
                if (charAt(this.f417bp + i21) == 'u') {
                    int i23 = i22 + 1;
                    if (charAt(this.f417bp + i22) == 'l') {
                        int i24 = i23 + 1;
                        if (charAt(this.f417bp + i23) == 'l') {
                            cCharAt = charAt(this.f417bp + i24);
                            i7 = i24 + 1;
                            uuid = null;
                        }
                    }
                }
            }
            this.matchStat = -1;
            return null;
        }
        int iIndexOf = indexOf('\"', this.f417bp + cArr.length + 1);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        int length2 = this.f417bp + cArr.length + 1;
        int i25 = iIndexOf - length2;
        char c8 = 'F';
        char c9 = 'f';
        char c10 = 'A';
        char c11 = '0';
        if (i25 == 36) {
            int i26 = 0;
            long j7 = 0;
            while (i26 < 8) {
                char cCharAt3 = charAt(length2 + i26);
                if (cCharAt3 < '0' || cCharAt3 > '9') {
                    if (cCharAt3 >= 'a' && cCharAt3 <= 'f') {
                        i19 = cCharAt3 - 'a';
                    } else {
                        if (cCharAt3 < 'A' || cCharAt3 > c8) {
                            this.matchStat = -2;
                            return null;
                        }
                        i19 = cCharAt3 - 'A';
                    }
                    i20 = i19 + 10;
                } else {
                    i20 = cCharAt3 - '0';
                }
                j7 = (j7 << 4) | i20;
                i26++;
                iIndexOf = iIndexOf;
                c8 = 'F';
            }
            int i27 = iIndexOf;
            int i28 = 9;
            int i29 = 13;
            while (i28 < i29) {
                char cCharAt4 = charAt(length2 + i28);
                if (cCharAt4 < '0' || cCharAt4 > '9') {
                    if (cCharAt4 >= 'a' && cCharAt4 <= 'f') {
                        i17 = cCharAt4 - 'a';
                    } else {
                        if (cCharAt4 < c10 || cCharAt4 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i17 = cCharAt4 - 'A';
                    }
                    i18 = i17 + 10;
                } else {
                    i18 = cCharAt4 - '0';
                }
                j7 = (j7 << c7) | i18;
                i28++;
                i29 = 13;
                c10 = 'A';
                c7 = 4;
            }
            long j8 = j7;
            for (int i30 = 14; i30 < 18; i30++) {
                char cCharAt5 = charAt(length2 + i30);
                if (cCharAt5 < '0' || cCharAt5 > '9') {
                    if (cCharAt5 >= 'a' && cCharAt5 <= 'f') {
                        i15 = cCharAt5 - 'a';
                    } else {
                        if (cCharAt5 < 'A' || cCharAt5 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i15 = cCharAt5 - 'A';
                    }
                    i16 = i15 + 10;
                } else {
                    i16 = cCharAt5 - '0';
                }
                j8 = (j8 << 4) | i16;
            }
            long j9 = 0;
            for (int i31 = 19; i31 < 23; i31++) {
                char cCharAt6 = charAt(length2 + i31);
                if (cCharAt6 < '0' || cCharAt6 > '9') {
                    if (cCharAt6 >= 'a' && cCharAt6 <= 'f') {
                        i13 = cCharAt6 - 'a';
                    } else {
                        if (cCharAt6 < 'A' || cCharAt6 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i13 = cCharAt6 - 'A';
                    }
                    i14 = i13 + 10;
                } else {
                    i14 = cCharAt6 - '0';
                }
                j9 = (j9 << 4) | i14;
            }
            int i32 = 24;
            long j10 = j9;
            int i33 = 36;
            while (i32 < i33) {
                char cCharAt7 = charAt(length2 + i32);
                if (cCharAt7 < c11 || cCharAt7 > '9') {
                    if (cCharAt7 >= 'a' && cCharAt7 <= c9) {
                        i11 = cCharAt7 - 'a';
                    } else {
                        if (cCharAt7 < 'A' || cCharAt7 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i11 = cCharAt7 - 'A';
                    }
                    i12 = i11 + 10;
                } else {
                    i12 = cCharAt7 - '0';
                }
                j10 = (j10 << 4) | i12;
                i32++;
                i21 = i21;
                i33 = 36;
                c11 = '0';
                c9 = 'f';
            }
            uuid = new UUID(j8, j10);
            int i34 = this.f417bp;
            int length3 = (i27 - ((cArr.length + i34) + 1)) + 1 + i21;
            i7 = length3 + 1;
            cCharAt = charAt(i34 + length3);
        } else {
            if (i25 != 32) {
                this.matchStat = -1;
                return null;
            }
            long j11 = 0;
            for (int i35 = 0; i35 < 16; i35++) {
                char cCharAt8 = charAt(length2 + i35);
                if (cCharAt8 < '0' || cCharAt8 > '9') {
                    if (cCharAt8 >= 'a' && cCharAt8 <= 'f') {
                        i9 = cCharAt8 - 'a';
                    } else {
                        if (cCharAt8 < 'A' || cCharAt8 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i9 = cCharAt8 - 'A';
                    }
                    i10 = i9 + 10;
                } else {
                    i10 = cCharAt8 - '0';
                }
                j11 = (j11 << 4) | i10;
            }
            int i36 = 16;
            long j12 = 0;
            for (int i37 = 32; i36 < i37; i37 = 32) {
                char cCharAt9 = charAt(length2 + i36);
                if (cCharAt9 >= '0' && cCharAt9 <= '9') {
                    i8 = cCharAt9 - '0';
                } else if (cCharAt9 >= 'a' && cCharAt9 <= 'f') {
                    i8 = (cCharAt9 - 'a') + 10;
                } else {
                    if (cCharAt9 < 'A' || cCharAt9 > 'F') {
                        this.matchStat = -2;
                        return null;
                    }
                    i8 = (cCharAt9 - 'A') + 10;
                    j12 = (j12 << 4) | i8;
                    i36++;
                }
                j12 = (j12 << 4) | i8;
                i36++;
            }
            uuid = new UUID(j11, j12);
            int i38 = this.f417bp;
            int length4 = (iIndexOf - ((cArr.length + i38) + 1)) + 1 + i21;
            i7 = length4 + 1;
            cCharAt = charAt(i38 + length4);
        }
        if (cCharAt == ',') {
            int i39 = this.f417bp + i7;
            this.f417bp = i39;
            this.f418ch = charAt(i39);
            this.matchStat = 3;
            return uuid;
        }
        if (cCharAt != '}') {
            this.matchStat = -1;
            return null;
        }
        int i40 = i7 + 1;
        char cCharAt10 = charAt(this.f417bp + i7);
        if (cCharAt10 == ',') {
            this.token = 16;
            int i41 = this.f417bp + i40;
            this.f417bp = i41;
            this.f418ch = charAt(i41);
        } else if (cCharAt10 == ']') {
            this.token = 15;
            int i42 = this.f417bp + i40;
            this.f417bp = i42;
            this.f418ch = charAt(i42);
        } else if (cCharAt10 == '}') {
            this.token = 13;
            int i43 = this.f417bp + i40;
            this.f417bp = i43;
            this.f418ch = charAt(i43);
        } else {
            if (cCharAt10 != 26) {
                this.matchStat = -1;
                return null;
            }
            this.token = 20;
            this.f417bp = (i40 - 1) + this.f417bp;
            this.f418ch = JSONLexer.EOI;
        }
        this.matchStat = 4;
        return uuid;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final float scanFloat(char c7) throws NumberFormatException {
        int i7;
        int i8;
        char cCharAt;
        boolean z6;
        JSONLexerBase jSONLexerBase;
        boolean z7;
        long j7;
        char c8;
        char c9;
        int i9;
        int i10;
        int i11;
        float f7;
        boolean z8;
        char c10;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.f417bp + 0);
        boolean z9 = cCharAt2 == '\"';
        if (z9) {
            cCharAt2 = charAt(this.f417bp + 1);
            i7 = 2;
        } else {
            i7 = 1;
        }
        boolean z10 = cCharAt2 == '-';
        if (z10) {
            cCharAt2 = charAt(this.f417bp + i7);
            i7++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            boolean z11 = z9;
            if (cCharAt2 != 'n' || charAt(this.f417bp + i7) != 'u' || C0861a.m670a(this.f417bp, i7, 1, this) != 'l' || C0861a.m670a(this.f417bp, i7, 2, this) != 'l') {
                this.matchStat = -1;
                return 0.0f;
            }
            this.matchStat = 5;
            int i12 = i7 + 3;
            int i13 = i12 + 1;
            char cCharAt3 = charAt(this.f417bp + i12);
            if (z11 && cCharAt3 == '\"') {
                int i14 = i13 + 1;
                cCharAt3 = charAt(this.f417bp + i13);
                i13 = i14;
            }
            while (cCharAt3 != ',') {
                if (cCharAt3 == ']') {
                    int i15 = this.f417bp + i13;
                    this.f417bp = i15;
                    this.f418ch = charAt(i15);
                    this.matchStat = 5;
                    this.token = 15;
                    return 0.0f;
                }
                if (!isWhitespace(cCharAt3)) {
                    this.matchStat = -1;
                    return 0.0f;
                }
                int i16 = i13 + 1;
                cCharAt3 = charAt(this.f417bp + i13);
                i13 = i16;
            }
            int i17 = this.f417bp + i13;
            this.f417bp = i17;
            this.f418ch = charAt(i17);
            this.matchStat = 5;
            this.token = 16;
            return 0.0f;
        }
        long j8 = cCharAt2 - '0';
        while (true) {
            i8 = i7 + 1;
            cCharAt = charAt(this.f417bp + i7);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j8 = (j8 * 10) + (cCharAt - '0');
            i7 = i8;
        }
        long j9 = 1;
        if (cCharAt == '.') {
            int i18 = i8 + 1;
            char cCharAt4 = charAt(this.f417bp + i8);
            if (cCharAt4 < '0' || cCharAt4 > '9') {
                this.matchStat = -1;
                return 0.0f;
            }
            z6 = z9;
            j8 = (j8 * 10) + (cCharAt4 - '0');
            j9 = 10;
            while (true) {
                i8 = i18 + 1;
                cCharAt = charAt(this.f417bp + i18);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                j8 = (j8 * 10) + (cCharAt - '0');
                j9 *= 10;
                i18 = i8;
            }
        } else {
            z6 = z9;
        }
        boolean z12 = cCharAt == 'e' || cCharAt == 'E';
        if (z12) {
            int i19 = i8 + 1;
            cCharAt = charAt(this.f417bp + i8);
            if (cCharAt == '+' || cCharAt == '-') {
                int i20 = i19 + 1;
                char cCharAt5 = charAt(this.f417bp + i19);
                jSONLexerBase = this;
                z8 = z12;
                c10 = '\"';
                i8 = i20;
                c9 = c7;
                cCharAt = cCharAt5;
            } else {
                jSONLexerBase = this;
                i8 = i19;
                c10 = '\"';
                z8 = z12;
                c9 = c7;
            }
            while (cCharAt >= '0' && cCharAt <= '9') {
                int i21 = i8 + 1;
                char cCharAt6 = jSONLexerBase.charAt(jSONLexerBase.f417bp + i8);
                i8 = i21;
                c9 = c9;
                cCharAt = cCharAt6;
            }
            z7 = z8;
            j7 = j9;
            c8 = c10;
        } else {
            jSONLexerBase = this;
            z7 = z12;
            j7 = j9;
            c8 = '\"';
            c9 = c7;
        }
        if (!z6) {
            int i22 = jSONLexerBase.f417bp;
            i9 = ((i22 + i8) - i22) - 1;
            int i23 = i8;
            i10 = i22;
            i11 = i23;
        } else {
            if (cCharAt != c8) {
                jSONLexerBase.matchStat = -1;
                return 0.0f;
            }
            i11 = i8 + 1;
            cCharAt = jSONLexerBase.charAt(jSONLexerBase.f417bp + i8);
            int i24 = jSONLexerBase.f417bp;
            i10 = i24 + 1;
            i9 = ((i24 + i11) - i10) - 2;
        }
        if (z7 || i9 >= 17) {
            f7 = Float.parseFloat(jSONLexerBase.subString(i10, i9));
        } else {
            f7 = (float) (j8 / j7);
            if (z10) {
                f7 = -f7;
            }
        }
        if (cCharAt != c9) {
            jSONLexerBase.matchStat = -1;
            return f7;
        }
        int i25 = jSONLexerBase.f417bp + i11;
        jSONLexerBase.f417bp = i25;
        jSONLexerBase.f418ch = jSONLexerBase.charAt(i25);
        jSONLexerBase.matchStat = 3;
        jSONLexerBase.token = 16;
        return f7;
    }

    public final void scanHex() {
        char next;
        if (this.f418ch != 'x') {
            StringBuilder sbM112a = C0413b.m112a("illegal state. ");
            sbM112a.append(this.f418ch);
            throw new JSONException(sbM112a.toString());
        }
        next();
        if (this.f418ch != '\'') {
            StringBuilder sbM112a2 = C0413b.m112a("illegal state. ");
            sbM112a2.append(this.f418ch);
            throw new JSONException(sbM112a2.toString());
        }
        this.f419np = this.f417bp;
        next();
        if (this.f418ch == '\'') {
            next();
            this.token = 26;
            return;
        }
        while (true) {
            next = next();
            if ((next < '0' || next > '9') && (next < 'A' || next > 'F')) {
                break;
            } else {
                this.f420sp++;
            }
        }
        if (next == '\'') {
            this.f420sp++;
            next();
            this.token = 26;
        } else {
            throw new JSONException("illegal state. " + next);
        }
    }

    public final void scanIdent() {
        this.f419np = this.f417bp - 1;
        this.hasSpecial = false;
        do {
            this.f420sp++;
            next();
        } while (Character.isLetterOrDigit(this.f418ch));
        String strStringVal = stringVal();
        if ("null".equalsIgnoreCase(strStringVal)) {
            this.token = 8;
            return;
        }
        if ("new".equals(strStringVal)) {
            this.token = 9;
            return;
        }
        if ("true".equals(strStringVal)) {
            this.token = 6;
            return;
        }
        if ("false".equals(strStringVal)) {
            this.token = 7;
            return;
        }
        if ("undefined".equals(strStringVal)) {
            this.token = 23;
            return;
        }
        if ("Set".equals(strStringVal)) {
            this.token = 21;
        } else if ("TreeSet".equals(strStringVal)) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public int scanInt(char c7) {
        int i7;
        int i8;
        char cCharAt;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.f417bp + 0);
        boolean z6 = cCharAt2 == '\"';
        if (z6) {
            cCharAt2 = charAt(this.f417bp + 1);
            i7 = 2;
        } else {
            i7 = 1;
        }
        boolean z7 = cCharAt2 == '-';
        if (z7) {
            cCharAt2 = charAt(this.f417bp + i7);
            i7++;
        }
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            int i9 = cCharAt2 - '0';
            while (true) {
                i8 = i7 + 1;
                cCharAt = charAt(this.f417bp + i7);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i9 = (i9 * 10) + (cCharAt - '0');
                i7 = i8;
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (i9 < 0) {
                this.matchStat = -1;
                return 0;
            }
            while (cCharAt != c7) {
                if (!isWhitespace(cCharAt)) {
                    this.matchStat = -1;
                    return z7 ? -i9 : i9;
                }
                char cCharAt3 = charAt(this.f417bp + i8);
                i8++;
                cCharAt = cCharAt3;
            }
            int i10 = this.f417bp + i8;
            this.f417bp = i10;
            this.f418ch = charAt(i10);
            this.matchStat = 3;
            this.token = 16;
            return z7 ? -i9 : i9;
        }
        if (cCharAt2 != 'n' || charAt(this.f417bp + i7) != 'u' || C0861a.m670a(this.f417bp, i7, 1, this) != 'l' || C0861a.m670a(this.f417bp, i7, 2, this) != 'l') {
            this.matchStat = -1;
            return 0;
        }
        this.matchStat = 5;
        int i11 = i7 + 3;
        int i12 = i11 + 1;
        char cCharAt4 = charAt(this.f417bp + i11);
        if (z6 && cCharAt4 == '\"') {
            cCharAt4 = charAt(this.f417bp + i12);
            i12++;
        }
        while (cCharAt4 != ',') {
            if (cCharAt4 == ']') {
                int i13 = this.f417bp + i12;
                this.f417bp = i13;
                this.f418ch = charAt(i13);
                this.matchStat = 5;
                this.token = 15;
                return 0;
            }
            if (!isWhitespace(cCharAt4)) {
                this.matchStat = -1;
                return 0;
            }
            cCharAt4 = charAt(this.f417bp + i12);
            i12++;
        }
        int i14 = this.f417bp + i12;
        this.f417bp = i14;
        this.f418ch = charAt(i14);
        this.matchStat = 5;
        this.token = 16;
        return 0;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char c7) {
        int i7;
        JSONLexerBase jSONLexerBase;
        int i8;
        int i9;
        char cCharAt;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.f417bp + 0);
        boolean z6 = cCharAt2 == '\"';
        if (z6) {
            cCharAt2 = charAt(this.f417bp + 1);
            i7 = 2;
        } else {
            i7 = 1;
        }
        boolean z7 = cCharAt2 == '-';
        if (z7) {
            cCharAt2 = charAt(this.f417bp + i7);
            i7++;
        }
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            long j7 = cCharAt2 - '0';
            while (true) {
                i9 = i7 + 1;
                cCharAt = charAt(this.f417bp + i7);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                j7 = (j7 * 10) + (cCharAt - '0');
                i7 = i9;
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0L;
            }
            if (!(j7 >= 0 || (j7 == Long.MIN_VALUE && z7))) {
                throw new NumberFormatException(subString(this.f417bp, i9 - 1));
            }
            if (z6) {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
                cCharAt = charAt(this.f417bp + i9);
                i9++;
            }
            while (cCharAt != c7) {
                if (!isWhitespace(cCharAt)) {
                    this.matchStat = -1;
                    return j7;
                }
                cCharAt = charAt(this.f417bp + i9);
                i9++;
            }
            int i10 = this.f417bp + i9;
            this.f417bp = i10;
            this.f418ch = charAt(i10);
            this.matchStat = 3;
            this.token = 16;
            return z7 ? -j7 : j7;
        }
        if (cCharAt2 != 'n' || charAt(this.f417bp + i7) != 'u' || C0861a.m670a(this.f417bp, i7, 1, this) != 'l' || C0861a.m670a(this.f417bp, i7, 2, this) != 'l') {
            this.matchStat = -1;
            return 0L;
        }
        this.matchStat = 5;
        int i11 = i7 + 3;
        int i12 = i11 + 1;
        char cCharAt3 = charAt(this.f417bp + i11);
        if (z6 && cCharAt3 == '\"') {
            char cCharAt4 = charAt(this.f417bp + i12);
            i12++;
            i8 = 16;
            cCharAt3 = cCharAt4;
            jSONLexerBase = this;
        } else {
            jSONLexerBase = this;
            i8 = 16;
        }
        while (cCharAt3 != ',') {
            if (cCharAt3 == ']') {
                int i13 = jSONLexerBase.f417bp + i12;
                jSONLexerBase.f417bp = i13;
                jSONLexerBase.f418ch = jSONLexerBase.charAt(i13);
                jSONLexerBase.matchStat = 5;
                jSONLexerBase.token = 15;
                return 0L;
            }
            if (!isWhitespace(cCharAt3)) {
                jSONLexerBase.matchStat = -1;
                return 0L;
            }
            cCharAt3 = jSONLexerBase.charAt(jSONLexerBase.f417bp + i12);
            i12++;
        }
        int i14 = jSONLexerBase.f417bp + i12;
        jSONLexerBase.f417bp = i14;
        jSONLexerBase.f418ch = jSONLexerBase.charAt(i14);
        jSONLexerBase.matchStat = 5;
        jSONLexerBase.token = i8;
        return 0L;
    }

    public final void scanNullOrNew() {
        if (this.f418ch != 'n') {
            throw new JSONException("error parse null or new");
        }
        next();
        char c7 = this.f418ch;
        if (c7 != 'u') {
            if (c7 != 'e') {
                throw new JSONException("error parse new");
            }
            next();
            if (this.f418ch != 'w') {
                throw new JSONException("error parse new");
            }
            next();
            char c8 = this.f418ch;
            if (c8 != ' ' && c8 != ',' && c8 != '}' && c8 != ']' && c8 != '\n' && c8 != '\r' && c8 != '\t' && c8 != 26 && c8 != '\f' && c8 != '\b') {
                throw new JSONException("scan new error");
            }
            this.token = 9;
            return;
        }
        next();
        if (this.f418ch != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        if (this.f418ch != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        char c9 = this.f418ch;
        if (c9 != ' ' && c9 != ',' && c9 != '}' && c9 != ']' && c9 != '\n' && c9 != '\r' && c9 != '\t' && c9 != 26 && c9 != '\f' && c9 != '\b') {
            throw new JSONException("scan null error");
        }
        this.token = 8;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ca  */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void scanNumber() {
        /*
            Method dump skipped, instructions count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanNumber():void");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void scanString() {
        this.f419np = this.f417bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\"') {
                this.token = 4;
                this.f418ch = next();
                return;
            }
            if (next == 26) {
                if (isEOF()) {
                    throw new JSONException("unclosed string : " + next);
                }
                putChar(JSONLexer.EOI);
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    int i7 = this.f420sp;
                    char[] cArr = this.sbuf;
                    if (i7 >= cArr.length) {
                        int length = cArr.length * 2;
                        if (i7 <= length) {
                            i7 = length;
                        }
                        char[] cArr2 = new char[i7];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.sbuf = cArr2;
                    }
                    copyTo(this.f419np + 1, this.f420sp, this.sbuf);
                }
                char next2 = next();
                if (next2 == '\"') {
                    putChar('\"');
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            putChar('\\');
                        } else if (next2 == 'b') {
                            putChar('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                putChar('\n');
                            } else if (next2 == 'r') {
                                putChar('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        putChar('/');
                                        break;
                                    case '0':
                                        putChar((char) 0);
                                        break;
                                    case '1':
                                        putChar((char) 1);
                                        break;
                                    case '2':
                                        putChar((char) 2);
                                        break;
                                    case '3':
                                        putChar((char) 3);
                                        break;
                                    case '4':
                                        putChar((char) 4);
                                        break;
                                    case '5':
                                        putChar((char) 5);
                                        break;
                                    case '6':
                                        putChar((char) 6);
                                        break;
                                    case '7':
                                        putChar((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case 't':
                                                putChar('\t');
                                                break;
                                            case 'u':
                                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                break;
                                            case 'v':
                                                putChar((char) 11);
                                                break;
                                            default:
                                                this.f418ch = next2;
                                                throw new JSONException("unclosed string : " + next2);
                                        }
                                }
                            } else {
                                char next3 = next();
                                char next4 = next();
                                int[] iArr = digits;
                                putChar((char) ((iArr[next3] * 16) + iArr[next4]));
                            }
                        }
                    }
                    putChar('\f');
                } else {
                    putChar('\'');
                }
            } else if (this.hasSpecial) {
                int i8 = this.f420sp;
                char[] cArr3 = this.sbuf;
                if (i8 == cArr3.length) {
                    putChar(next);
                } else {
                    this.f420sp = i8 + 1;
                    cArr3[i8] = next;
                }
            } else {
                this.f420sp++;
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void scanStringArray(Collection<String> collection, char c7) {
        int i7;
        char cCharAt;
        int i8;
        char cCharAt2;
        this.matchStat = 0;
        char cCharAt3 = charAt(this.f417bp + 0);
        char c8 = 'u';
        char c9 = 'n';
        if (cCharAt3 == 'n' && charAt(this.f417bp + 1) == 'u' && C0861a.m670a(this.f417bp, 1, 1, this) == 'l' && C0861a.m670a(this.f417bp, 1, 2, this) == 'l' && C0861a.m670a(this.f417bp, 1, 3, this) == c7) {
            int i9 = this.f417bp + 5;
            this.f417bp = i9;
            this.f418ch = charAt(i9);
            this.matchStat = 5;
            return;
        }
        if (cCharAt3 != '[') {
            this.matchStat = -1;
            return;
        }
        char cCharAt4 = charAt(this.f417bp + 1);
        int i10 = 2;
        while (true) {
            if (cCharAt4 == c9 && charAt(this.f417bp + i10) == c8 && C0861a.m670a(this.f417bp, i10, 1, this) == 'l' && C0861a.m670a(this.f417bp, i10, 2, this) == 'l') {
                int i11 = i10 + 3;
                i7 = i11 + 1;
                cCharAt = charAt(this.f417bp + i11);
                collection.add(null);
            } else {
                if (cCharAt4 == ']' && collection.size() == 0) {
                    i8 = i10 + 1;
                    cCharAt2 = charAt(this.f417bp + i10);
                    break;
                }
                if (cCharAt4 != '\"') {
                    this.matchStat = -1;
                    return;
                }
                int i12 = this.f417bp + i10;
                int iIndexOf = indexOf('\"', i12);
                if (iIndexOf == -1) {
                    throw new JSONException("unclosed str");
                }
                String strSubString = subString(this.f417bp + i10, iIndexOf - i12);
                if (strSubString.indexOf(92) != -1) {
                    while (true) {
                        int i13 = 0;
                        for (int i14 = iIndexOf - 1; i14 >= 0 && charAt(i14) == '\\'; i14--) {
                            i13++;
                        }
                        if (i13 % 2 == 0) {
                            break;
                        } else {
                            iIndexOf = indexOf('\"', iIndexOf + 1);
                        }
                    }
                    int i15 = iIndexOf - i12;
                    strSubString = readString(sub_chars(this.f417bp + i10, i15), i15);
                }
                int i16 = this.f417bp;
                int i17 = (iIndexOf - (i16 + i10)) + 1 + i10;
                i7 = i17 + 1;
                cCharAt = charAt(i16 + i17);
                collection.add(strSubString);
            }
            if (cCharAt == ',') {
                i10 = i7 + 1;
                cCharAt4 = charAt(this.f417bp + i7);
                c8 = 'u';
                c9 = 'n';
            } else if (cCharAt != ']') {
                this.matchStat = -1;
                return;
            } else {
                i8 = i7 + 1;
                cCharAt2 = charAt(this.f417bp + i7);
            }
        }
        if (cCharAt2 != c7) {
            this.matchStat = -1;
            return;
        }
        int i18 = this.f417bp + i8;
        this.f417bp = i18;
        this.f418ch = charAt(i18);
        this.matchStat = 3;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable) {
        skipWhitespace();
        char c7 = this.f418ch;
        if (c7 == '\"') {
            return scanSymbol(symbolTable, '\"');
        }
        if (c7 == '\'') {
            if (isEnabled(Feature.AllowSingleQuotes)) {
                return scanSymbol(symbolTable, '\'');
            }
            throw new JSONException("syntax error");
        }
        if (c7 == '}') {
            next();
            this.token = 13;
            return null;
        }
        if (c7 == ',') {
            next();
            this.token = 16;
            return null;
        }
        if (c7 == 26) {
            this.token = 20;
            return null;
        }
        if (isEnabled(Feature.AllowUnQuotedFieldNames)) {
            return scanSymbolUnQuoted(symbolTable);
        }
        throw new JSONException("syntax error");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        if (this.token == 1 && this.pos == 0 && this.f417bp == 1) {
            this.f417bp = 0;
        }
        boolean[] zArr = IOUtils.firstIdentifierFlags;
        int i7 = this.f418ch;
        if (!(i7 >= zArr.length || zArr[i7])) {
            StringBuilder sbM112a = C0413b.m112a("illegal identifier : ");
            sbM112a.append(this.f418ch);
            sbM112a.append(info());
            throw new JSONException(sbM112a.toString());
        }
        boolean[] zArr2 = IOUtils.identifierFlags;
        this.f419np = this.f417bp;
        this.f420sp = 1;
        while (true) {
            char next = next();
            if (next < zArr2.length && !zArr2[next]) {
                break;
            }
            i7 = (i7 * 31) + next;
            this.f420sp++;
        }
        this.f418ch = charAt(this.f417bp);
        this.token = 18;
        if (this.f420sp == 4 && i7 == 3392903 && charAt(this.f419np) == 'n' && charAt(this.f419np + 1) == 'u' && charAt(this.f419np + 2) == 'l' && charAt(this.f419np + 3) == 'l') {
            return null;
        }
        return symbolTable == null ? subString(this.f419np, this.f420sp) : addSymbol(this.f419np, this.f420sp, i7, symbolTable);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanSymbolWithSeperator(SymbolTable symbolTable, char c7) {
        int i7 = 0;
        this.matchStat = 0;
        char cCharAt = charAt(this.f417bp + 0);
        if (cCharAt == 'n') {
            if (charAt(this.f417bp + 1) != 'u' || C0861a.m670a(this.f417bp, 1, 1, this) != 'l' || C0861a.m670a(this.f417bp, 1, 2, this) != 'l') {
                this.matchStat = -1;
                return null;
            }
            if (charAt(this.f417bp + 4) != c7) {
                this.matchStat = -1;
                return null;
            }
            int i8 = this.f417bp + 5;
            this.f417bp = i8;
            this.f418ch = charAt(i8);
            this.matchStat = 3;
            return null;
        }
        if (cCharAt != '\"') {
            this.matchStat = -1;
            return null;
        }
        int i9 = 1;
        while (true) {
            int i10 = i9 + 1;
            char cCharAt2 = charAt(this.f417bp + i9);
            if (cCharAt2 == '\"') {
                int i11 = this.f417bp;
                int i12 = i11 + 0 + 1;
                String strAddSymbol = addSymbol(i12, ((i11 + i10) - i12) - 1, i7, symbolTable);
                int i13 = i10 + 1;
                char cCharAt3 = charAt(this.f417bp + i10);
                while (cCharAt3 != c7) {
                    if (!isWhitespace(cCharAt3)) {
                        this.matchStat = -1;
                        return strAddSymbol;
                    }
                    cCharAt3 = charAt(this.f417bp + i13);
                    i13++;
                }
                int i14 = this.f417bp + i13;
                this.f417bp = i14;
                this.f418ch = charAt(i14);
                this.matchStat = 3;
                return strAddSymbol;
            }
            i7 = (i7 * 31) + cCharAt2;
            if (cCharAt2 == '\\') {
                this.matchStat = -1;
                return null;
            }
            i9 = i10;
        }
    }

    public final void scanTrue() {
        if (this.f418ch != 't') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.f418ch != 'r') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.f418ch != 'u') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.f418ch != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        char c7 = this.f418ch;
        if (c7 != ' ' && c7 != ',' && c7 != '}' && c7 != ']' && c7 != '\n' && c7 != '\r' && c7 != '\t' && c7 != 26 && c7 != '\f' && c7 != '\b' && c7 != ':' && c7 != '/') {
            throw new JSONException("scan true error");
        }
        this.token = 6;
    }

    public final int scanType(String str) {
        this.matchStat = 0;
        char[] cArr = typeFieldName;
        if (!charArrayCompare(cArr)) {
            return -2;
        }
        int length = this.f417bp + cArr.length;
        int length2 = str.length();
        for (int i7 = 0; i7 < length2; i7++) {
            if (str.charAt(i7) != charAt(length + i7)) {
                return -1;
            }
        }
        int i8 = length + length2;
        if (charAt(i8) != '\"') {
            return -1;
        }
        int i9 = i8 + 1;
        char cCharAt = charAt(i9);
        this.f418ch = cCharAt;
        if (cCharAt == ',') {
            int i10 = i9 + 1;
            this.f418ch = charAt(i10);
            this.f417bp = i10;
            this.token = 16;
            return 3;
        }
        if (cCharAt == '}') {
            i9++;
            char cCharAt2 = charAt(i9);
            this.f418ch = cCharAt2;
            if (cCharAt2 == ',') {
                this.token = 16;
                i9++;
                this.f418ch = charAt(i9);
            } else if (cCharAt2 == ']') {
                this.token = 15;
                i9++;
                this.f418ch = charAt(i9);
            } else if (cCharAt2 == '}') {
                this.token = 13;
                i9++;
                this.f418ch = charAt(i9);
            } else {
                if (cCharAt2 != 26) {
                    return -1;
                }
                this.token = 20;
            }
            this.matchStat = 4;
        }
        this.f417bp = i9;
        return this.matchStat;
    }

    public UUID scanUUID(char c7) {
        int i7;
        char cCharAt;
        UUID uuid;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.f417bp + 0);
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf('\"', this.f417bp + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int i21 = this.f417bp + 1;
            int i22 = iIndexOf - i21;
            char c8 = 'F';
            char c9 = 'f';
            char c10 = '9';
            char c11 = 'A';
            char c12 = 'a';
            if (i22 == 36) {
                int i23 = 0;
                long j7 = 0;
                while (i23 < 8) {
                    char cCharAt3 = charAt(i21 + i23);
                    if (cCharAt3 < '0' || cCharAt3 > '9') {
                        if (cCharAt3 >= 'a' && cCharAt3 <= 'f') {
                            i19 = cCharAt3 - 'a';
                        } else {
                            if (cCharAt3 < c11 || cCharAt3 > c8) {
                                this.matchStat = -2;
                                return null;
                            }
                            i19 = cCharAt3 - 'A';
                        }
                        i20 = i19 + 10;
                    } else {
                        i20 = cCharAt3 - '0';
                    }
                    j7 = (j7 << 4) | i20;
                    i23++;
                    c11 = 'A';
                    c8 = 'F';
                }
                int i24 = 9;
                while (i24 < 13) {
                    char cCharAt4 = charAt(i21 + i24);
                    if (cCharAt4 < '0' || cCharAt4 > '9') {
                        if (cCharAt4 >= 'a' && cCharAt4 <= c9) {
                            i17 = cCharAt4 - 'a';
                        } else {
                            if (cCharAt4 < 'A' || cCharAt4 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i17 = cCharAt4 - 'A';
                        }
                        i18 = i17 + 10;
                    } else {
                        i18 = cCharAt4 - '0';
                    }
                    j7 = (j7 << 4) | i18;
                    i24++;
                    iIndexOf = iIndexOf;
                    c9 = 'f';
                }
                int i25 = iIndexOf;
                int i26 = 14;
                long j8 = j7;
                while (i26 < 18) {
                    char cCharAt5 = charAt(i21 + i26);
                    if (cCharAt5 < '0' || cCharAt5 > '9') {
                        if (cCharAt5 >= c12 && cCharAt5 <= 'f') {
                            i15 = cCharAt5 - 'a';
                        } else {
                            if (cCharAt5 < 'A' || cCharAt5 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i15 = cCharAt5 - 'A';
                        }
                        i16 = i15 + 10;
                    } else {
                        i16 = cCharAt5 - '0';
                    }
                    j8 = (j8 << 4) | i16;
                    i26++;
                    c12 = 'a';
                }
                int i27 = 19;
                long j9 = 0;
                while (i27 < 23) {
                    char cCharAt6 = charAt(i21 + i27);
                    if (cCharAt6 < '0' || cCharAt6 > c10) {
                        if (cCharAt6 >= 'a' && cCharAt6 <= 'f') {
                            i13 = cCharAt6 - 'a';
                        } else {
                            if (cCharAt6 < 'A' || cCharAt6 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i13 = cCharAt6 - 'A';
                        }
                        i14 = i13 + 10;
                    } else {
                        i14 = cCharAt6 - '0';
                    }
                    j9 = (j9 << 4) | i14;
                    i27++;
                    j8 = j8;
                    c10 = '9';
                }
                long j10 = j8;
                long j11 = j9;
                for (int i28 = 24; i28 < 36; i28++) {
                    char cCharAt7 = charAt(i21 + i28);
                    if (cCharAt7 < '0' || cCharAt7 > '9') {
                        if (cCharAt7 >= 'a' && cCharAt7 <= 'f') {
                            i11 = cCharAt7 - 'a';
                        } else {
                            if (cCharAt7 < 'A' || cCharAt7 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i11 = cCharAt7 - 'A';
                        }
                        i12 = i11 + 10;
                    } else {
                        i12 = cCharAt7 - '0';
                    }
                    j11 = (j11 << 4) | i12;
                }
                uuid = new UUID(j10, j11);
                int i29 = this.f417bp;
                int i30 = (i25 - (i29 + 1)) + 1 + 1;
                i7 = i30 + 1;
                cCharAt = charAt(i29 + i30);
            } else {
                if (i22 != 32) {
                    this.matchStat = -1;
                    return null;
                }
                long j12 = 0;
                for (int i31 = 0; i31 < 16; i31++) {
                    char cCharAt8 = charAt(i21 + i31);
                    if (cCharAt8 < '0' || cCharAt8 > '9') {
                        if (cCharAt8 >= 'a' && cCharAt8 <= 'f') {
                            i9 = cCharAt8 - 'a';
                        } else {
                            if (cCharAt8 < 'A' || cCharAt8 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i9 = cCharAt8 - 'A';
                        }
                        i10 = i9 + 10;
                    } else {
                        i10 = cCharAt8 - '0';
                    }
                    j12 = (j12 << 4) | i10;
                }
                int i32 = 16;
                long j13 = 0;
                for (int i33 = 32; i32 < i33; i33 = 32) {
                    char cCharAt9 = charAt(i21 + i32);
                    if (cCharAt9 >= '0' && cCharAt9 <= '9') {
                        i8 = cCharAt9 - '0';
                    } else if (cCharAt9 >= 'a' && cCharAt9 <= 'f') {
                        i8 = (cCharAt9 - 'a') + 10;
                    } else {
                        if (cCharAt9 < 'A' || cCharAt9 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i8 = (cCharAt9 - 'A') + 10;
                    }
                    j13 = (j13 << 4) | i8;
                    i32++;
                }
                uuid = new UUID(j12, j13);
                int i34 = this.f417bp;
                int i35 = (iIndexOf - (i34 + 1)) + 1 + 1;
                i7 = i35 + 1;
                cCharAt = charAt(i34 + i35);
            }
        } else {
            if (cCharAt2 != 'n' || charAt(this.f417bp + 1) != 'u' || charAt(this.f417bp + 2) != 'l' || charAt(this.f417bp + 3) != 'l') {
                this.matchStat = -1;
                return null;
            }
            i7 = 5;
            cCharAt = charAt(this.f417bp + 4);
            uuid = null;
        }
        if (cCharAt == ',') {
            int i36 = this.f417bp + i7;
            this.f417bp = i36;
            this.f418ch = charAt(i36);
            this.matchStat = 3;
            return uuid;
        }
        if (cCharAt != ']') {
            this.matchStat = -1;
            return null;
        }
        int i37 = i7 + 1;
        char cCharAt10 = charAt(this.f417bp + i7);
        if (cCharAt10 == ',') {
            this.token = 16;
            int i38 = this.f417bp + i37;
            this.f417bp = i38;
            this.f418ch = charAt(i38);
        } else if (cCharAt10 == ']') {
            this.token = 15;
            int i39 = this.f417bp + i37;
            this.f417bp = i39;
            this.f418ch = charAt(i39);
        } else if (cCharAt10 == '}') {
            this.token = 13;
            int i40 = this.f417bp + i37;
            this.f417bp = i40;
            this.f418ch = charAt(i40);
        } else {
            if (cCharAt10 != 26) {
                this.matchStat = -1;
                return null;
            }
            this.token = 20;
            this.f417bp = (i37 - 1) + this.f417bp;
            this.f418ch = JSONLexer.EOI;
        }
        this.matchStat = 4;
        return uuid;
    }

    public boolean seekArrayToItem(int i7) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToField(long j7, boolean z6) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToFieldDeepScan(long j7) {
        throw new UnsupportedOperationException();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void setToken(int i7) {
        this.token = i7;
    }

    public void skipArray() {
        throw new UnsupportedOperationException();
    }

    public void skipComment() {
        char c7;
        next();
        char c8 = this.f418ch;
        if (c8 == '/') {
            do {
                next();
                c7 = this.f418ch;
                if (c7 == '\n') {
                    next();
                    return;
                }
            } while (c7 != 26);
            return;
        }
        if (c8 != '*') {
            throw new JSONException("invalid comment");
        }
        next();
        while (true) {
            char c9 = this.f418ch;
            if (c9 == 26) {
                return;
            }
            if (c9 == '*') {
                next();
                if (this.f418ch == '/') {
                    next();
                    return;
                }
            } else {
                next();
            }
        }
    }

    public void skipObject() {
        throw new UnsupportedOperationException();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void skipWhitespace() {
        while (true) {
            char c7 = this.f418ch;
            if (c7 > '/') {
                return;
            }
            if (c7 == ' ' || c7 == '\r' || c7 == '\n' || c7 == '\t' || c7 == '\f' || c7 == '\b') {
                next();
            } else if (c7 != '/') {
                return;
            } else {
                skipComment();
            }
        }
    }

    public final String stringDefaultValue() {
        return this.stringDefaultValue;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String stringVal();

    public abstract String subString(int i7, int i8);

    public abstract char[] sub_chars(int i7, int i8);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int token() {
        return this.token;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String tokenName() {
        return JSONToken.name(this.token);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isEnabled(int i7) {
        return (i7 & this.features) != 0;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon(int i7) {
        nextTokenWithChar(':');
    }

    public final boolean isEnabled(int i7, int i8) {
        return ((this.features & i8) == 0 && (i7 & i8) == 0) ? false : true;
    }

    public int matchField(long j7) {
        throw new UnsupportedOperationException();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable, char c7) throws NumberFormatException {
        String strAddSymbol;
        this.f419np = this.f417bp;
        this.f420sp = 0;
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            char next = next();
            if (next == c7) {
                this.token = 4;
                if (!z6) {
                    int i8 = this.f419np;
                    strAddSymbol = addSymbol(i8 == -1 ? 0 : i8 + 1, this.f420sp, i7, symbolTable);
                } else {
                    strAddSymbol = symbolTable.addSymbol(this.sbuf, 0, this.f420sp, i7);
                }
                this.f420sp = 0;
                next();
                return strAddSymbol;
            }
            if (next == 26) {
                throw new JSONException("unclosed.str");
            }
            if (next == '\\') {
                if (!z6) {
                    int i9 = this.f420sp;
                    char[] cArr = this.sbuf;
                    if (i9 >= cArr.length) {
                        int length = cArr.length * 2;
                        if (i9 <= length) {
                            i9 = length;
                        }
                        char[] cArr2 = new char[i9];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.sbuf = cArr2;
                    }
                    arrayCopy(this.f419np + 1, this.sbuf, 0, this.f420sp);
                    z6 = true;
                }
                char next2 = next();
                if (next2 == '\"') {
                    i7 = (i7 * 31) + 34;
                    putChar('\"');
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            i7 = (i7 * 31) + 92;
                            putChar('\\');
                        } else if (next2 == 'b') {
                            i7 = (i7 * 31) + 8;
                            putChar('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                i7 = (i7 * 31) + 10;
                                putChar('\n');
                            } else if (next2 == 'r') {
                                i7 = (i7 * 31) + 13;
                                putChar('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        i7 = (i7 * 31) + 47;
                                        putChar('/');
                                        break;
                                    case '0':
                                        i7 = (i7 * 31) + next2;
                                        putChar((char) 0);
                                        break;
                                    case '1':
                                        i7 = (i7 * 31) + next2;
                                        putChar((char) 1);
                                        break;
                                    case '2':
                                        i7 = (i7 * 31) + next2;
                                        putChar((char) 2);
                                        break;
                                    case '3':
                                        i7 = (i7 * 31) + next2;
                                        putChar((char) 3);
                                        break;
                                    case '4':
                                        i7 = (i7 * 31) + next2;
                                        putChar((char) 4);
                                        break;
                                    case '5':
                                        i7 = (i7 * 31) + next2;
                                        putChar((char) 5);
                                        break;
                                    case '6':
                                        i7 = (i7 * 31) + next2;
                                        putChar((char) 6);
                                        break;
                                    case '7':
                                        i7 = (i7 * 31) + next2;
                                        putChar((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case 't':
                                                i7 = (i7 * 31) + 9;
                                                putChar('\t');
                                                break;
                                            case 'u':
                                                int i10 = Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16);
                                                i7 = (i7 * 31) + i10;
                                                putChar((char) i10);
                                                break;
                                            case 'v':
                                                i7 = (i7 * 31) + 11;
                                                putChar((char) 11);
                                                break;
                                            default:
                                                this.f418ch = next2;
                                                throw new JSONException("unclosed.str.lit");
                                        }
                                }
                            } else {
                                char next3 = next();
                                this.f418ch = next3;
                                char next4 = next();
                                this.f418ch = next4;
                                int[] iArr = digits;
                                char c8 = (char) ((iArr[next3] * 16) + iArr[next4]);
                                i7 = (i7 * 31) + c8;
                                putChar(c8);
                            }
                        }
                    }
                    i7 = (i7 * 31) + 12;
                    putChar('\f');
                } else {
                    i7 = (i7 * 31) + 39;
                    putChar('\'');
                }
            } else {
                i7 = (i7 * 31) + next;
                if (!z6) {
                    this.f420sp++;
                } else {
                    int i11 = this.f420sp;
                    char[] cArr3 = this.sbuf;
                    if (i11 == cArr3.length) {
                        putChar(next);
                    } else {
                        this.f420sp = i11 + 1;
                        cArr3[i11] = next;
                    }
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0073 A[SYNTHETIC] */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void nextToken(int r10) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.nextToken(int):void");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanString(char c7) {
        this.matchStat = 0;
        char cCharAt = charAt(this.f417bp + 0);
        if (cCharAt == 'n') {
            if (charAt(this.f417bp + 1) == 'u' && C0861a.m670a(this.f417bp, 1, 1, this) == 'l' && C0861a.m670a(this.f417bp, 1, 2, this) == 'l') {
                if (charAt(this.f417bp + 4) == c7) {
                    int i7 = this.f417bp + 5;
                    this.f417bp = i7;
                    this.f418ch = charAt(i7);
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        int i8 = 1;
        while (cCharAt != '\"') {
            if (isWhitespace(cCharAt)) {
                cCharAt = charAt(this.f417bp + i8);
                i8++;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        int i9 = this.f417bp + i8;
        int iIndexOf = indexOf('\"', i9);
        if (iIndexOf != -1) {
            String strSubString = subString(this.f417bp + i8, iIndexOf - i9);
            if (strSubString.indexOf(92) != -1) {
                while (true) {
                    int i10 = 0;
                    for (int i11 = iIndexOf - 1; i11 >= 0 && charAt(i11) == '\\'; i11--) {
                        i10++;
                    }
                    if (i10 % 2 == 0) {
                        break;
                    }
                    iIndexOf = indexOf('\"', iIndexOf + 1);
                }
                int i12 = iIndexOf - i9;
                strSubString = readString(sub_chars(this.f417bp + 1, i12), i12);
            }
            int i13 = (iIndexOf - i9) + 1 + i8;
            int i14 = i13 + 1;
            char cCharAt2 = charAt(this.f417bp + i13);
            while (cCharAt2 != c7) {
                if (isWhitespace(cCharAt2)) {
                    cCharAt2 = charAt(this.f417bp + i14);
                    i14++;
                } else {
                    this.matchStat = -1;
                    return strSubString;
                }
            }
            int i15 = this.f417bp + i14;
            this.f417bp = i15;
            this.f418ch = charAt(i15);
            this.matchStat = 3;
            return strSubString;
        }
        throw new JSONException("unclosed str");
    }

    public String[] scanFieldStringArray(char[] cArr, int i7, SymbolTable symbolTable) {
        throw new UnsupportedOperationException();
    }
}
