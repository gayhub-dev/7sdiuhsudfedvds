package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public final class JSONReaderScanner extends JSONLexerBase {
    private static final ThreadLocal<char[]> BUF_LOCAL = new ThreadLocal<>();
    private char[] buf;
    private int bufLength;
    private Reader reader;

    public JSONReaderScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i7, int i8, int i9, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.buf, i7, i8, i9);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void arrayCopy(int i7, char[] cArr, int i8, int i9) {
        System.arraycopy(this.buf, i7, cArr, i8, i9);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        if (this.token != 26) {
            return IOUtils.decodeBase64(this.buf, this.f419np + 1, this.f420sp);
        }
        throw new JSONException("TODO");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final boolean charArrayCompare(char[] cArr) {
        for (int i7 = 0; i7 < cArr.length; i7++) {
            if (charAt(this.f417bp + i7) != cArr[i7]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i7) throws IOException {
        int i8 = this.bufLength;
        if (i7 >= i8) {
            if (i8 == -1) {
                return i7 < this.f420sp ? this.buf[i7] : JSONLexer.EOI;
            }
            int i9 = this.f417bp;
            if (i9 == 0) {
                char[] cArr = this.buf;
                int length = (cArr.length * 3) / 2;
                char[] cArr2 = new char[length];
                System.arraycopy(cArr, i9, cArr2, 0, i8);
                int i10 = this.bufLength;
                try {
                    this.bufLength += this.reader.read(cArr2, i10, length - i10);
                    this.buf = cArr2;
                } catch (IOException e7) {
                    throw new JSONException(e7.getMessage(), e7);
                }
            } else {
                int i11 = i8 - i9;
                if (i11 > 0) {
                    char[] cArr3 = this.buf;
                    System.arraycopy(cArr3, i9, cArr3, 0, i11);
                }
                try {
                    Reader reader = this.reader;
                    char[] cArr4 = this.buf;
                    int i12 = reader.read(cArr4, i11, cArr4.length - i11);
                    this.bufLength = i12;
                    if (i12 == 0) {
                        throw new JSONException("illegal state, textLength is zero");
                    }
                    if (i12 == -1) {
                        return JSONLexer.EOI;
                    }
                    this.bufLength = i12 + i11;
                    int i13 = this.f417bp;
                    i7 -= i13;
                    this.f419np -= i13;
                    this.f417bp = 0;
                } catch (IOException e8) {
                    throw new JSONException(e8.getMessage(), e8);
                }
            }
        }
        return this.buf[i7];
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        char[] cArr = this.buf;
        if (cArr.length <= 65536) {
            BUF_LOCAL.set(cArr);
        }
        this.buf = null;
        IOUtils.close(this.reader);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void copyTo(int i7, int i8, char[] cArr) {
        System.arraycopy(this.buf, i7, cArr, 0, i8);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final BigDecimal decimalValue() throws IOException {
        int i7 = this.f419np;
        if (i7 == -1) {
            i7 = 0;
        }
        char cCharAt = charAt((this.f420sp + i7) - 1);
        int i8 = this.f420sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i8--;
        }
        return new BigDecimal(this.buf, i7, i8);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c7, int i7) throws IOException {
        int i8 = i7 - this.f417bp;
        while (true) {
            char cCharAt = charAt(this.f417bp + i8);
            if (c7 == cCharAt) {
                return i8 + this.f417bp;
            }
            if (cCharAt == 26) {
                return -1;
            }
            i8++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final boolean isBlankInput() {
        int i7 = 0;
        while (true) {
            char c7 = this.buf[i7];
            if (c7 == 26) {
                this.token = 20;
                return true;
            }
            if (!JSONLexerBase.isWhitespace(c7)) {
                return false;
            }
            i7++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        if (this.bufLength == -1) {
            return true;
        }
        int i7 = this.f417bp;
        char[] cArr = this.buf;
        if (i7 != cArr.length) {
            return this.f418ch == 26 && i7 + 1 == cArr.length;
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() throws IOException {
        int i7 = this.f417bp + 1;
        this.f417bp = i7;
        int i8 = this.bufLength;
        if (i7 >= i8) {
            if (i8 == -1) {
                return JSONLexer.EOI;
            }
            int i9 = this.f420sp;
            if (i9 > 0) {
                int i10 = i8 - i9;
                if (this.f418ch == '\"' && i10 > 0) {
                    i10--;
                }
                char[] cArr = this.buf;
                System.arraycopy(cArr, i10, cArr, 0, i9);
            }
            this.f419np = -1;
            int i11 = this.f420sp;
            this.f417bp = i11;
            try {
                char[] cArr2 = this.buf;
                int length = cArr2.length - i11;
                if (length == 0) {
                    char[] cArr3 = new char[cArr2.length * 2];
                    System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
                    this.buf = cArr3;
                    length = cArr3.length - i11;
                }
                int i12 = this.reader.read(this.buf, this.f417bp, length);
                this.bufLength = i12;
                if (i12 == 0) {
                    throw new JSONException("illegal stat, textLength is zero");
                }
                if (i12 == -1) {
                    this.f418ch = JSONLexer.EOI;
                    return JSONLexer.EOI;
                }
                this.bufLength = i12 + this.f417bp;
                i7 = i11;
            } catch (IOException e7) {
                throw new JSONException(e7.getMessage(), e7);
            }
        }
        char c7 = this.buf[i7];
        this.f418ch = c7;
        return c7;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() throws IOException {
        int i7 = this.f419np;
        if (i7 == -1) {
            i7 = 0;
        }
        char cCharAt = charAt((this.f420sp + i7) - 1);
        int i8 = this.f420sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i8--;
        }
        return new String(this.buf, i7, i8);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        if (this.hasSpecial) {
            return new String(this.sbuf, 0, this.f420sp);
        }
        int i7 = this.f419np + 1;
        if (i7 < 0) {
            throw new IllegalStateException();
        }
        char[] cArr = this.buf;
        int length = cArr.length;
        int i8 = this.f420sp;
        if (i7 <= length - i8) {
            return new String(cArr, i7, i8);
        }
        throw new IllegalStateException();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i7, int i8) {
        if (i8 >= 0) {
            return new String(this.buf, i7, i8);
        }
        throw new StringIndexOutOfBoundsException(i8);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char[] sub_chars(int i7, int i8) {
        if (i8 < 0) {
            throw new StringIndexOutOfBoundsException(i8);
        }
        if (i7 == 0) {
            return this.buf;
        }
        char[] cArr = new char[i8];
        System.arraycopy(this.buf, i7, cArr, 0, i8);
        return cArr;
    }

    public JSONReaderScanner(String str, int i7) {
        this(new StringReader(str), i7);
    }

    public JSONReaderScanner(char[] cArr, int i7) {
        this(cArr, i7, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader) {
        this(reader, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader, int i7) throws IOException {
        super(i7);
        this.reader = reader;
        ThreadLocal<char[]> threadLocal = BUF_LOCAL;
        char[] cArr = threadLocal.get();
        this.buf = cArr;
        if (cArr != null) {
            threadLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[16384];
        }
        try {
            this.bufLength = reader.read(this.buf);
            this.f417bp = -1;
            next();
            if (this.f418ch == 65279) {
                next();
            }
        } catch (IOException e7) {
            throw new JSONException(e7.getMessage(), e7);
        }
    }

    public JSONReaderScanner(char[] cArr, int i7, int i8) {
        this(new CharArrayReader(cArr, 0, i7), i8);
    }
}
