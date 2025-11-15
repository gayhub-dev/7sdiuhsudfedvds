package com.alibaba.fastjson.serializer;

import android.support.constraint.motion.C0079a;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.RyuDouble;
import com.alibaba.fastjson.util.RyuFloat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;
import okhttp3.HttpUrl;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class SerializeWriter extends Writer {
    private static final ThreadLocal<char[]> bufLocal = new ThreadLocal<>();
    private static final ThreadLocal<byte[]> bytesBufLocal = new ThreadLocal<>();
    public static final int nonDirectFeatures = ((((((((SerializerFeature.UseSingleQuotes.mask | 0) | SerializerFeature.BrowserCompatible.mask) | SerializerFeature.PrettyFormat.mask) | SerializerFeature.WriteEnumUsingToString.mask) | SerializerFeature.WriteNonStringValueAsString.mask) | SerializerFeature.WriteSlashAsSpecial.mask) | SerializerFeature.IgnoreErrorGetter.mask) | SerializerFeature.WriteClassName.mask) | SerializerFeature.NotWriteDefaultValue.mask;
    public boolean beanToArray;
    public boolean browserSecure;
    public char[] buf;
    public int count;
    public boolean disableCircularReferenceDetect;
    public int features;
    public char keySeperator;
    public int maxBufSize;
    public boolean notWriteDefaultValue;
    public boolean quoteFieldNames;
    public long sepcialBits;
    public boolean sortField;
    public boolean useSingleQuotes;
    public boolean writeDirect;
    public boolean writeEnumUsingName;
    public boolean writeEnumUsingToString;
    public boolean writeNonStringValueAsString;
    private final Writer writer;

    public SerializeWriter() {
        this((Writer) null);
    }

    private int encodeToUTF8(OutputStream outputStream) throws IOException {
        int i7 = (int) (this.count * 3.0d);
        ThreadLocal<byte[]> threadLocal = bytesBufLocal;
        byte[] bArr = threadLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            threadLocal.set(bArr);
        }
        if (bArr.length < i7) {
            bArr = new byte[i7];
        }
        int iEncodeUTF8 = IOUtils.encodeUTF8(this.buf, 0, this.count, bArr);
        outputStream.write(bArr, 0, iEncodeUTF8);
        return iEncodeUTF8;
    }

    private byte[] encodeToUTF8Bytes() {
        int i7 = (int) (this.count * 3.0d);
        ThreadLocal<byte[]> threadLocal = bytesBufLocal;
        byte[] bArr = threadLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            threadLocal.set(bArr);
        }
        if (bArr.length < i7) {
            bArr = new byte[i7];
        }
        int iEncodeUTF8 = IOUtils.encodeUTF8(this.buf, 0, this.count, bArr);
        byte[] bArr2 = new byte[iEncodeUTF8];
        System.arraycopy(bArr, 0, bArr2, 0, iEncodeUTF8);
        return bArr2;
    }

    private void writeEnumFieldValue(char c7, String str, String str2) {
        if (this.useSingleQuotes) {
            writeFieldValue(c7, str, str2);
        } else {
            writeFieldValueStringWithDoubleQuote(c7, str, str2);
        }
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String str) {
        byte[] bArr = IOUtils.specicalFlags_singleQuotes;
        int length = str.length();
        boolean z6 = true;
        int i7 = this.count + length + 1;
        int i8 = 0;
        if (i7 > this.buf.length) {
            if (this.writer != null) {
                if (length == 0) {
                    write(39);
                    write(39);
                    write(58);
                    return;
                }
                int i9 = 0;
                while (true) {
                    if (i9 < length) {
                        char cCharAt = str.charAt(i9);
                        if (cCharAt < bArr.length && bArr[cCharAt] != 0) {
                            break;
                        } else {
                            i9++;
                        }
                    } else {
                        z6 = false;
                        break;
                    }
                }
                if (z6) {
                    write(39);
                }
                while (i8 < length) {
                    char cCharAt2 = str.charAt(i8);
                    if (cCharAt2 >= bArr.length || bArr[cCharAt2] == 0) {
                        write(cCharAt2);
                    } else {
                        write(92);
                        write(IOUtils.replaceChars[cCharAt2]);
                    }
                    i8++;
                }
                if (z6) {
                    write(39);
                }
                write(58);
                return;
            }
            expandCapacity(i7);
        }
        if (length == 0) {
            int i10 = this.count;
            if (i10 + 3 > this.buf.length) {
                expandCapacity(i10 + 3);
            }
            char[] cArr = this.buf;
            int i11 = this.count;
            int i12 = i11 + 1;
            this.count = i12;
            cArr[i11] = '\'';
            int i13 = i12 + 1;
            this.count = i13;
            cArr[i12] = '\'';
            this.count = i13 + 1;
            cArr[i13] = ':';
            return;
        }
        int i14 = this.count;
        int i15 = i14 + length;
        str.getChars(0, length, this.buf, i14);
        this.count = i7;
        int i16 = i14;
        boolean z7 = false;
        while (i16 < i15) {
            char[] cArr2 = this.buf;
            char c7 = cArr2[i16];
            if (c7 < bArr.length && bArr[c7] != 0) {
                if (z7) {
                    i7++;
                    if (i7 > cArr2.length) {
                        expandCapacity(i7);
                    }
                    this.count = i7;
                    char[] cArr3 = this.buf;
                    int i17 = i16 + 1;
                    System.arraycopy(cArr3, i17, cArr3, i16 + 2, i15 - i16);
                    char[] cArr4 = this.buf;
                    cArr4[i16] = '\\';
                    cArr4[i17] = IOUtils.replaceChars[c7];
                    i15++;
                    i16 = i17;
                } else {
                    i7 += 3;
                    if (i7 > cArr2.length) {
                        expandCapacity(i7);
                    }
                    this.count = i7;
                    char[] cArr5 = this.buf;
                    int i18 = i16 + 1;
                    System.arraycopy(cArr5, i18, cArr5, i16 + 3, (i15 - i16) - 1);
                    char[] cArr6 = this.buf;
                    System.arraycopy(cArr6, i8, cArr6, 1, i16);
                    char[] cArr7 = this.buf;
                    cArr7[i14] = '\'';
                    cArr7[i18] = '\\';
                    int i19 = i18 + 1;
                    cArr7[i19] = IOUtils.replaceChars[c7];
                    i15 += 2;
                    cArr7[this.count - 2] = '\'';
                    i16 = i19;
                    z7 = true;
                }
            }
            i16++;
            i8 = 0;
        }
        this.buf[i7 - 1] = ':';
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        char[] cArr = this.buf;
        if (cArr.length <= 131072) {
            bufLocal.set(cArr);
        }
        this.buf = null;
    }

    public void computeFeatures() {
        int i7 = this.features;
        boolean z6 = (SerializerFeature.QuoteFieldNames.mask & i7) != 0;
        this.quoteFieldNames = z6;
        boolean z7 = (SerializerFeature.UseSingleQuotes.mask & i7) != 0;
        this.useSingleQuotes = z7;
        this.sortField = (SerializerFeature.SortField.mask & i7) != 0;
        this.disableCircularReferenceDetect = (SerializerFeature.DisableCircularReferenceDetect.mask & i7) != 0;
        boolean z8 = (SerializerFeature.BeanToArray.mask & i7) != 0;
        this.beanToArray = z8;
        this.writeNonStringValueAsString = (SerializerFeature.WriteNonStringValueAsString.mask & i7) != 0;
        this.notWriteDefaultValue = (SerializerFeature.NotWriteDefaultValue.mask & i7) != 0;
        boolean z9 = (SerializerFeature.WriteEnumUsingName.mask & i7) != 0;
        this.writeEnumUsingName = z9;
        this.writeEnumUsingToString = (SerializerFeature.WriteEnumUsingToString.mask & i7) != 0;
        this.writeDirect = z6 && (nonDirectFeatures & i7) == 0 && (z8 || z9);
        this.keySeperator = z7 ? '\'' : '\"';
        boolean z10 = (SerializerFeature.BrowserSecure.mask & i7) != 0;
        this.browserSecure = z10;
        this.sepcialBits = z10 ? 5764610843043954687L : (i7 & SerializerFeature.WriteSlashAsSpecial.mask) != 0 ? 140758963191807L : 21474836479L;
    }

    public void config(SerializerFeature serializerFeature, boolean z6) {
        if (z6) {
            int mask = this.features | serializerFeature.getMask();
            this.features = mask;
            SerializerFeature serializerFeature2 = SerializerFeature.WriteEnumUsingToString;
            if (serializerFeature == serializerFeature2) {
                this.features = (~SerializerFeature.WriteEnumUsingName.getMask()) & mask;
            } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                this.features = (~serializerFeature2.getMask()) & mask;
            }
        } else {
            this.features = (~serializerFeature.getMask()) & this.features;
        }
        computeFeatures();
    }

    public void expandCapacity(int i7) {
        int i8 = this.maxBufSize;
        if (i8 != -1 && i7 >= i8) {
            StringBuilder sbM112a = C0413b.m112a("serialize exceeded MAX_OUTPUT_LENGTH=");
            sbM112a.append(this.maxBufSize);
            sbM112a.append(", minimumCapacity=");
            sbM112a.append(i7);
            throw new JSONException(sbM112a.toString());
        }
        char[] cArr = this.buf;
        int length = cArr.length + (cArr.length >> 1) + 1;
        if (length >= i7) {
            i7 = length;
        }
        char[] cArr2 = new char[i7];
        System.arraycopy(cArr, 0, cArr2, 0, this.count);
        this.buf = cArr2;
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        Writer writer = this.writer;
        if (writer == null) {
            return;
        }
        try {
            writer.write(this.buf, 0, this.count);
            this.writer.flush();
            this.count = 0;
        } catch (IOException e7) {
            throw new JSONException(e7.getMessage(), e7);
        }
    }

    public int getBufferLength() {
        return this.buf.length;
    }

    public int getMaxBufSize() {
        return this.maxBufSize;
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return (serializerFeature.mask & this.features) != 0;
    }

    public boolean isNotWriteDefaultValue() {
        return this.notWriteDefaultValue;
    }

    public boolean isSortField() {
        return this.sortField;
    }

    public void setMaxBufSize(int i7) {
        if (i7 >= this.buf.length) {
            this.maxBufSize = i7;
        } else {
            StringBuilder sbM112a = C0413b.m112a("must > ");
            sbM112a.append(this.buf.length);
            throw new JSONException(sbM112a.toString());
        }
    }

    public int size() {
        return this.count;
    }

    public byte[] toBytes(String str) {
        return toBytes((str == null || "UTF-8".equals(str)) ? IOUtils.UTF8 : Charset.forName(str));
    }

    public char[] toCharArray() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        int i7 = this.count;
        char[] cArr = new char[i7];
        System.arraycopy(this.buf, 0, cArr, 0, i7);
        return cArr;
    }

    public char[] toCharArrayForSpringWebSocket() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        int i7 = this.count;
        char[] cArr = new char[i7 - 2];
        System.arraycopy(this.buf, 1, cArr, 0, i7 - 2);
        return cArr;
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    @Override // java.io.Writer
    public void write(int i7) {
        int i8 = 1;
        int i9 = this.count + 1;
        if (i9 <= this.buf.length) {
            i8 = i9;
        } else if (this.writer == null) {
            expandCapacity(i9);
            i8 = i9;
        } else {
            flush();
        }
        this.buf[this.count] = (char) i7;
        this.count = i8;
    }

    public void writeByteArray(byte[] bArr) {
        if (isEnabled(SerializerFeature.WriteClassName.mask)) {
            writeHex(bArr);
            return;
        }
        int length = bArr.length;
        boolean z6 = this.useSingleQuotes;
        char c7 = z6 ? '\'' : '\"';
        if (length == 0) {
            write(z6 ? "''" : "\"\"");
            return;
        }
        char[] cArr = IOUtils.f423CA;
        int i7 = (length / 3) * 3;
        int i8 = length - 1;
        int i9 = this.count;
        int i10 = (((i8 / 3) + 1) << 2) + i9 + 2;
        if (i10 > this.buf.length) {
            if (this.writer != null) {
                write(c7);
                int i11 = 0;
                while (i11 < i7) {
                    int i12 = i11 + 1;
                    int i13 = i12 + 1;
                    int i14 = ((bArr[i11] & 255) << 16) | ((bArr[i12] & 255) << 8) | (bArr[i13] & 255);
                    write(cArr[(i14 >>> 18) & 63]);
                    write(cArr[(i14 >>> 12) & 63]);
                    write(cArr[(i14 >>> 6) & 63]);
                    write(cArr[i14 & 63]);
                    i11 = i13 + 1;
                }
                int i15 = length - i7;
                if (i15 > 0) {
                    int i16 = ((bArr[i7] & 255) << 10) | (i15 == 2 ? (bArr[i8] & 255) << 2 : 0);
                    write(cArr[i16 >> 12]);
                    write(cArr[(i16 >>> 6) & 63]);
                    write(i15 == 2 ? cArr[i16 & 63] : '=');
                    write(61);
                }
                write(c7);
                return;
            }
            expandCapacity(i10);
        }
        this.count = i10;
        int i17 = i9 + 1;
        this.buf[i9] = c7;
        int i18 = 0;
        while (i18 < i7) {
            int i19 = i18 + 1;
            int i20 = i19 + 1;
            int i21 = ((bArr[i18] & 255) << 16) | ((bArr[i19] & 255) << 8);
            int i22 = i20 + 1;
            int i23 = i21 | (bArr[i20] & 255);
            char[] cArr2 = this.buf;
            int i24 = i17 + 1;
            cArr2[i17] = cArr[(i23 >>> 18) & 63];
            int i25 = i24 + 1;
            cArr2[i24] = cArr[(i23 >>> 12) & 63];
            int i26 = i25 + 1;
            cArr2[i25] = cArr[(i23 >>> 6) & 63];
            i17 = i26 + 1;
            cArr2[i26] = cArr[i23 & 63];
            i18 = i22;
        }
        int i27 = length - i7;
        if (i27 > 0) {
            int i28 = ((bArr[i7] & 255) << 10) | (i27 == 2 ? (bArr[i8] & 255) << 2 : 0);
            char[] cArr3 = this.buf;
            cArr3[i10 - 5] = cArr[i28 >> 12];
            cArr3[i10 - 4] = cArr[(i28 >>> 6) & 63];
            cArr3[i10 - 3] = i27 == 2 ? cArr[i28 & 63] : '=';
            cArr3[i10 - 2] = '=';
        }
        this.buf[i10 - 1] = c7;
    }

    public void writeDouble(double d7, boolean z6) throws IOException {
        if (Double.isNaN(d7) || Double.isInfinite(d7)) {
            writeNull();
            return;
        }
        int i7 = this.count + 24;
        if (i7 > this.buf.length) {
            if (this.writer != null) {
                String string = RyuDouble.toString(d7);
                write(string, 0, string.length());
                if (z6 && isEnabled(SerializerFeature.WriteClassName)) {
                    write(68);
                    return;
                }
                return;
            }
            expandCapacity(i7);
        }
        this.count += RyuDouble.toString(d7, this.buf, this.count);
        if (z6 && isEnabled(SerializerFeature.WriteClassName)) {
            write(68);
        }
    }

    public void writeEnum(Enum<?> r32) throws IOException {
        if (r32 == null) {
            writeNull();
            return;
        }
        String string = null;
        if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            string = r32.name();
        } else if (this.writeEnumUsingToString) {
            string = r32.toString();
        }
        if (string == null) {
            writeInt(r32.ordinal());
            return;
        }
        int i7 = isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
        write(i7);
        write(string);
        write(i7);
    }

    public void writeFieldName(String str) {
        writeFieldName(str, false);
    }

    public void writeFieldNameDirect(String str) {
        int length = str.length();
        int i7 = this.count + length + 3;
        if (i7 > this.buf.length) {
            expandCapacity(i7);
        }
        int i8 = this.count;
        char[] cArr = this.buf;
        cArr[i8] = '\"';
        str.getChars(0, length, cArr, i8 + 1);
        this.count = i7;
        char[] cArr2 = this.buf;
        cArr2[i7 - 2] = '\"';
        cArr2[i7 - 1] = ':';
    }

    public void writeFieldValue(char c7, String str, char c8) {
        write(c7);
        writeFieldName(str);
        if (c8 == 0) {
            writeString("\u0000");
        } else {
            writeString(Character.toString(c8));
        }
    }

    public void writeFieldValueStringWithDoubleQuote(char c7, String str, String str2) {
        int length = str.length();
        int i7 = this.count;
        int length2 = str2.length();
        int i8 = length + length2 + 6 + i7;
        if (i8 > this.buf.length) {
            if (this.writer != null) {
                write(c7);
                writeStringWithDoubleQuote(str, ':');
                writeStringWithDoubleQuote(str2, (char) 0);
                return;
            }
            expandCapacity(i8);
        }
        char[] cArr = this.buf;
        int i9 = this.count;
        cArr[i9] = c7;
        int i10 = i9 + 2;
        int i11 = i10 + length;
        cArr[i9 + 1] = '\"';
        str.getChars(0, length, cArr, i10);
        this.count = i8;
        char[] cArr2 = this.buf;
        cArr2[i11] = '\"';
        int i12 = i11 + 1;
        int i13 = i12 + 1;
        cArr2[i12] = ':';
        cArr2[i13] = '\"';
        str2.getChars(0, length2, cArr2, i13 + 1);
        this.buf[this.count - 1] = '\"';
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a1 A[PHI: r1 r6 r10 r12
      0x00a1: PHI (r1v39 int) = (r1v29 int), (r1v3 int) binds: [B:55:0x00dc, B:30:0x009d] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r6v14 int) = (r6v12 int), (r6v16 int) binds: [B:55:0x00dc, B:30:0x009d] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r10v16 int) = (r10v14 int), (r10v18 int) binds: [B:55:0x00dc, B:30:0x009d] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r12v21 int) = (r12v3 int), (r12v23 int) binds: [B:55:0x00dc, B:30:0x009d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeFieldValueStringWithDoubleQuoteCheck(char r22, java.lang.String r23, java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 781
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.SerializeWriter.writeFieldValueStringWithDoubleQuoteCheck(char, java.lang.String, java.lang.String):void");
    }

    public void writeFloat(float f7, boolean z6) throws IOException {
        if (f7 != f7 || f7 == Float.POSITIVE_INFINITY || f7 == Float.NEGATIVE_INFINITY) {
            writeNull();
            return;
        }
        int i7 = this.count + 15;
        if (i7 > this.buf.length) {
            if (this.writer != null) {
                String string = RyuFloat.toString(f7);
                write(string, 0, string.length());
                if (z6 && isEnabled(SerializerFeature.WriteClassName)) {
                    write(70);
                    return;
                }
                return;
            }
            expandCapacity(i7);
        }
        this.count += RyuFloat.toString(f7, this.buf, this.count);
        if (z6 && isEnabled(SerializerFeature.WriteClassName)) {
            write(70);
        }
    }

    public void writeHex(byte[] bArr) {
        int i7 = 2;
        int length = (bArr.length * 2) + this.count + 3;
        int i8 = 0;
        if (length > this.buf.length) {
            if (this.writer != null) {
                char[] cArr = new char[bArr.length + 3];
                cArr[0] = 'x';
                cArr[1] = '\'';
                while (i8 < bArr.length) {
                    int i9 = bArr[i8] & 255;
                    int i10 = i9 >> 4;
                    int i11 = i9 & 15;
                    int i12 = i7 + 1;
                    cArr[i7] = (char) (i10 + (i10 < 10 ? 48 : 55));
                    i7 = i12 + 1;
                    cArr[i12] = (char) (i11 + (i11 < 10 ? 48 : 55));
                    i8++;
                }
                cArr[i7] = '\'';
                try {
                    this.writer.write(cArr);
                    return;
                } catch (IOException e7) {
                    throw new JSONException("writeBytes error.", e7);
                }
            }
            expandCapacity(length);
        }
        char[] cArr2 = this.buf;
        int i13 = this.count;
        int i14 = i13 + 1;
        this.count = i14;
        cArr2[i13] = 'x';
        this.count = i14 + 1;
        cArr2[i14] = '\'';
        while (i8 < bArr.length) {
            int i15 = bArr[i8] & 255;
            int i16 = i15 >> 4;
            int i17 = i15 & 15;
            char[] cArr3 = this.buf;
            int i18 = this.count;
            int i19 = i18 + 1;
            this.count = i19;
            cArr3[i18] = (char) (i16 + (i16 < 10 ? 48 : 55));
            this.count = i19 + 1;
            cArr3[i19] = (char) (i17 + (i17 < 10 ? 48 : 55));
            i8++;
        }
        char[] cArr4 = this.buf;
        int i20 = this.count;
        this.count = i20 + 1;
        cArr4[i20] = '\'';
    }

    public void writeInt(int i7) throws IOException {
        if (i7 == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int iStringSize = i7 < 0 ? IOUtils.stringSize(-i7) + 1 : IOUtils.stringSize(i7);
        int i8 = this.count + iStringSize;
        if (i8 > this.buf.length) {
            if (this.writer != null) {
                char[] cArr = new char[iStringSize];
                IOUtils.getChars(i7, iStringSize, cArr);
                write(cArr, 0, iStringSize);
                return;
            }
            expandCapacity(i8);
        }
        IOUtils.getChars(i7, i8, this.buf);
        this.count = i8;
    }

    public void writeLong(long j7) throws IOException {
        boolean z6 = isEnabled(SerializerFeature.BrowserCompatible) && !isEnabled(SerializerFeature.WriteClassName) && (j7 > 9007199254740991L || j7 < -9007199254740991L);
        if (j7 == Long.MIN_VALUE) {
            if (z6) {
                write("\"-9223372036854775808\"");
                return;
            } else {
                write("-9223372036854775808");
                return;
            }
        }
        int iStringSize = j7 < 0 ? IOUtils.stringSize(-j7) + 1 : IOUtils.stringSize(j7);
        int i7 = this.count + iStringSize;
        if (z6) {
            i7 += 2;
        }
        if (i7 > this.buf.length) {
            if (this.writer != null) {
                char[] cArr = new char[iStringSize];
                IOUtils.getChars(j7, iStringSize, cArr);
                if (!z6) {
                    write(cArr, 0, iStringSize);
                    return;
                }
                write(34);
                write(cArr, 0, iStringSize);
                write(34);
                return;
            }
            expandCapacity(i7);
        }
        if (z6) {
            char[] cArr2 = this.buf;
            cArr2[this.count] = '\"';
            int i8 = i7 - 1;
            IOUtils.getChars(j7, i8, cArr2);
            this.buf[i8] = '\"';
        } else {
            IOUtils.getChars(j7, i7, this.buf);
        }
        this.count = i7;
    }

    public void writeLongAndChar(long j7, char c7) throws IOException {
        writeLong(j7);
        write(c7);
    }

    public void writeNull() {
        write("null");
    }

    public void writeString(String str, char c7) {
        if (!this.useSingleQuotes) {
            writeStringWithDoubleQuote(str, c7);
        } else {
            writeStringWithSingleQuote(str);
            write(c7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:144:0x02b2 A[PHI: r4 r5 r8 r12
      0x02b2: PHI (r4v44 int) = (r4v42 int), (r4v46 int) binds: [B:168:0x02f0, B:143:0x02ae] A[DONT_GENERATE, DONT_INLINE]
      0x02b2: PHI (r5v35 int) = (r5v5 int), (r5v37 int) binds: [B:168:0x02f0, B:143:0x02ae] A[DONT_GENERATE, DONT_INLINE]
      0x02b2: PHI (r8v20 int) = (r8v10 int), (r8v3 int) binds: [B:168:0x02f0, B:143:0x02ae] A[DONT_GENERATE, DONT_INLINE]
      0x02b2: PHI (r12v13 int) = (r12v11 int), (r12v15 int) binds: [B:168:0x02f0, B:143:0x02ae] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x014f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeStringWithDoubleQuote(java.lang.String r23, char r24) {
        /*
            Method dump skipped, instructions count: 1338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.SerializeWriter.writeStringWithDoubleQuote(java.lang.String, char):void");
    }

    public void writeStringWithSingleQuote(String str) {
        int i7 = 0;
        if (str == null) {
            int i8 = this.count + 4;
            if (i8 > this.buf.length) {
                expandCapacity(i8);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i8;
            return;
        }
        int length = str.length();
        int i9 = this.count + length + 2;
        if (i9 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i7 < str.length()) {
                    char cCharAt = str.charAt(i7);
                    if (cCharAt <= '\r' || cCharAt == '\\' || cCharAt == '\'' || (cCharAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(IOUtils.replaceChars[cCharAt]);
                    } else {
                        write(cCharAt);
                    }
                    i7++;
                }
                write(39);
                return;
            }
            expandCapacity(i9);
        }
        int i10 = this.count;
        int i11 = i10 + 1;
        int i12 = i11 + length;
        char[] cArr = this.buf;
        cArr[i10] = '\'';
        str.getChars(0, length, cArr, i11);
        this.count = i9;
        int i13 = -1;
        char c7 = 0;
        for (int i14 = i11; i14 < i12; i14++) {
            char c8 = this.buf[i14];
            if (c8 <= '\r' || c8 == '\\' || c8 == '\'' || (c8 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i7++;
                i13 = i14;
                c7 = c8;
            }
        }
        int i15 = i9 + i7;
        if (i15 > this.buf.length) {
            expandCapacity(i15);
        }
        this.count = i15;
        if (i7 == 1) {
            char[] cArr2 = this.buf;
            int i16 = i13 + 1;
            System.arraycopy(cArr2, i16, cArr2, i13 + 2, (i12 - i13) - 1);
            char[] cArr3 = this.buf;
            cArr3[i13] = '\\';
            cArr3[i16] = IOUtils.replaceChars[c7];
        } else if (i7 > 1) {
            char[] cArr4 = this.buf;
            int i17 = i13 + 1;
            System.arraycopy(cArr4, i17, cArr4, i13 + 2, (i12 - i13) - 1);
            char[] cArr5 = this.buf;
            cArr5[i13] = '\\';
            cArr5[i17] = IOUtils.replaceChars[c7];
            int i18 = i12 + 1;
            for (int i19 = i17 - 2; i19 >= i11; i19--) {
                char c9 = this.buf[i19];
                if (c9 <= '\r' || c9 == '\\' || c9 == '\'' || (c9 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    char[] cArr6 = this.buf;
                    int i20 = i19 + 1;
                    System.arraycopy(cArr6, i20, cArr6, i19 + 2, (i18 - i19) - 1);
                    char[] cArr7 = this.buf;
                    cArr7[i19] = '\\';
                    cArr7[i20] = IOUtils.replaceChars[c9];
                    i18++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeTo(Writer writer) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        writer.write(this.buf, 0, this.count);
    }

    public int writeToEx(OutputStream outputStream, Charset charset) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charset == IOUtils.UTF8) {
            return encodeToUTF8(outputStream);
        }
        byte[] bytes = new String(this.buf, 0, this.count).getBytes(charset);
        outputStream.write(bytes);
        return bytes.length;
    }

    public SerializeWriter(Writer writer) {
        this(writer, JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
    }

    public boolean isEnabled(int i7) {
        return (i7 & this.features) != 0;
    }

    public void writeFieldName(String str, boolean z6) {
        if (str == null) {
            write("null:");
            return;
        }
        if (this.useSingleQuotes) {
            if (!this.quoteFieldNames) {
                writeKeyWithSingleQuoteIfHasSpecial(str);
                return;
            } else {
                writeStringWithSingleQuote(str);
                write(58);
                return;
            }
        }
        if (this.quoteFieldNames) {
            writeStringWithDoubleQuote(str, ':');
            return;
        }
        boolean z7 = true;
        boolean z8 = str.length() == 0;
        int i7 = 0;
        while (true) {
            if (i7 >= str.length()) {
                z7 = z8;
                break;
            }
            char cCharAt = str.charAt(i7);
            if ((cCharAt < '@' && (this.sepcialBits & (1 << cCharAt)) != 0) || cCharAt == '\\') {
                break;
            } else {
                i7++;
            }
        }
        if (z7) {
            writeStringWithDoubleQuote(str, ':');
        } else {
            write(str);
            write(58);
        }
    }

    public void writeNull(SerializerFeature serializerFeature) {
        writeNull(0, serializerFeature.mask);
    }

    public SerializeWriter(SerializerFeature... serializerFeatureArr) {
        this((Writer) null, serializerFeatureArr);
    }

    public void writeNull(int i7, int i8) {
        if ((i7 & i8) == 0 && (this.features & i8) == 0) {
            writeNull();
            return;
        }
        if (i8 == SerializerFeature.WriteNullListAsEmpty.mask) {
            write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        if (i8 == SerializerFeature.WriteNullStringAsEmpty.mask) {
            writeString("");
            return;
        }
        if (i8 == SerializerFeature.WriteNullBooleanAsFalse.mask) {
            write("false");
        } else if (i8 == SerializerFeature.WriteNullNumberAsZero.mask) {
            write(48);
        } else {
            writeNull();
        }
    }

    public SerializeWriter(Writer writer, SerializerFeature... serializerFeatureArr) {
        this(writer, 0, serializerFeatureArr);
    }

    public byte[] toBytes(Charset charset) {
        if (this.writer == null) {
            if (charset == IOUtils.UTF8) {
                return encodeToUTF8Bytes();
            }
            return new String(this.buf, 0, this.count).getBytes(charset);
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public void writeTo(OutputStream outputStream, String str) throws IOException {
        writeTo(outputStream, Charset.forName(str));
    }

    public SerializeWriter(Writer writer, int i7, SerializerFeature... serializerFeatureArr) {
        this.maxBufSize = -1;
        this.writer = writer;
        ThreadLocal<char[]> threadLocal = bufLocal;
        char[] cArr = threadLocal.get();
        this.buf = cArr;
        if (cArr != null) {
            threadLocal.set(null);
        } else {
            this.buf = new char[2048];
        }
        for (SerializerFeature serializerFeature : serializerFeatureArr) {
            i7 |= serializerFeature.getMask();
        }
        this.features = i7;
        computeFeatures();
    }

    public void writeFieldValue(char c7, String str, boolean z6) {
        if (!this.quoteFieldNames) {
            write(c7);
            writeFieldName(str);
            write(z6);
            return;
        }
        int i7 = z6 ? 4 : 5;
        int length = str.length();
        int i8 = this.count + length + 4 + i7;
        if (i8 > this.buf.length) {
            if (this.writer != null) {
                write(c7);
                writeString(str);
                write(58);
                write(z6);
                return;
            }
            expandCapacity(i8);
        }
        int i9 = this.count;
        this.count = i8;
        char[] cArr = this.buf;
        cArr[i9] = c7;
        int i10 = i9 + length + 1;
        cArr[i9 + 1] = this.keySeperator;
        str.getChars(0, length, cArr, i9 + 2);
        this.buf[i10 + 1] = this.keySeperator;
        if (z6) {
            System.arraycopy(":true".toCharArray(), 0, this.buf, i10 + 2, 5);
        } else {
            System.arraycopy(":false".toCharArray(), 0, this.buf, i10 + 2, 6);
        }
    }

    public void writeString(String str) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(str);
        } else {
            writeStringWithDoubleQuote(str, (char) 0);
        }
    }

    public void writeTo(OutputStream outputStream, Charset charset) throws IOException {
        writeToEx(outputStream, charset);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence charSequence) throws IOException {
        String string = charSequence == null ? "null" : charSequence.toString();
        write(string, 0, string.length());
        return this;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i7, int i8) throws IOException {
        int i9;
        if (i7 < 0 || i7 > cArr.length || i8 < 0 || (i9 = i7 + i8) > cArr.length || i9 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i8 == 0) {
            return;
        }
        int i10 = this.count + i8;
        if (i10 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i10);
            } else {
                do {
                    char[] cArr2 = this.buf;
                    int length = cArr2.length;
                    int i11 = this.count;
                    int i12 = length - i11;
                    System.arraycopy(cArr, i7, cArr2, i11, i12);
                    this.count = this.buf.length;
                    flush();
                    i8 -= i12;
                    i7 += i12;
                } while (i8 > this.buf.length);
                i10 = i8;
            }
        }
        System.arraycopy(cArr, i7, this.buf, this.count, i8);
        this.count = i10;
    }

    public void writeString(char[] cArr) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(cArr);
        } else {
            writeStringWithDoubleQuote(new String(cArr), (char) 0);
        }
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence charSequence, int i7, int i8) throws IOException {
        if (charSequence == null) {
            charSequence = "null";
        }
        String string = charSequence.subSequence(i7, i8).toString();
        write(string, 0, string.length());
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(char c7) {
        write(c7);
        return this;
    }

    public SerializeWriter(int i7) {
        this((Writer) null, i7);
    }

    public SerializeWriter(Writer writer, int i7) {
        this.maxBufSize = -1;
        this.writer = writer;
        if (i7 > 0) {
            this.buf = new char[i7];
            computeFeatures();
            return;
        }
        throw new IllegalArgumentException(C0079a.m93a("Negative initial size: ", i7));
    }

    @Override // java.io.Writer
    public void write(String str, int i7, int i8) throws IOException {
        int i9;
        int i10 = this.count + i8;
        if (i10 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i10);
            } else {
                while (true) {
                    char[] cArr = this.buf;
                    int length = cArr.length;
                    int i11 = this.count;
                    int i12 = length - i11;
                    i9 = i7 + i12;
                    str.getChars(i7, i9, cArr, i11);
                    this.count = this.buf.length;
                    flush();
                    i8 -= i12;
                    if (i8 <= this.buf.length) {
                        break;
                    } else {
                        i7 = i9;
                    }
                }
                i10 = i8;
                i7 = i9;
            }
        }
        str.getChars(i7, i8 + i7, this.buf, this.count);
        this.count = i10;
    }

    public void writeFieldValue(char c7, String str, int i7) throws IOException {
        if (i7 != Integer.MIN_VALUE && this.quoteFieldNames) {
            int iStringSize = i7 < 0 ? IOUtils.stringSize(-i7) + 1 : IOUtils.stringSize(i7);
            int length = str.length();
            int i8 = this.count + length + 4 + iStringSize;
            if (i8 > this.buf.length) {
                if (this.writer != null) {
                    write(c7);
                    writeFieldName(str);
                    writeInt(i7);
                    return;
                }
                expandCapacity(i8);
            }
            int i9 = this.count;
            this.count = i8;
            char[] cArr = this.buf;
            cArr[i9] = c7;
            int i10 = i9 + length + 1;
            cArr[i9 + 1] = this.keySeperator;
            str.getChars(0, length, cArr, i9 + 2);
            char[] cArr2 = this.buf;
            cArr2[i10 + 1] = this.keySeperator;
            cArr2[i10 + 2] = ':';
            IOUtils.getChars(i7, this.count, cArr2);
            return;
        }
        write(c7);
        writeFieldName(str);
        writeInt(i7);
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str == null) {
            writeNull();
        } else {
            write(str, 0, str.length());
        }
    }

    public void write(List<String> list) {
        boolean z6;
        int i7;
        if (list.isEmpty()) {
            write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        int i8 = this.count;
        int size = list.size();
        int i9 = i8;
        int i10 = 0;
        while (i10 < size) {
            String str = list.get(i10);
            if (str == null) {
                z6 = true;
            } else {
                int length = str.length();
                z6 = false;
                for (int i11 = 0; i11 < length; i11++) {
                    char cCharAt = str.charAt(i11);
                    z6 = cCharAt < ' ' || cCharAt > '~' || cCharAt == '\"' || cCharAt == '\\';
                    if (z6) {
                        break;
                    }
                }
            }
            if (z6) {
                this.count = i8;
                write(91);
                for (int i12 = 0; i12 < list.size(); i12++) {
                    String str2 = list.get(i12);
                    if (i12 != 0) {
                        write(44);
                    }
                    if (str2 == null) {
                        write("null");
                    } else {
                        writeStringWithDoubleQuote(str2, (char) 0);
                    }
                }
                write(93);
                return;
            }
            int length2 = str.length() + i9 + 3;
            if (i10 == list.size() - 1) {
                length2++;
            }
            if (length2 > this.buf.length) {
                this.count = i9;
                expandCapacity(length2);
            }
            if (i10 == 0) {
                i7 = i9 + 1;
                this.buf[i9] = '[';
            } else {
                i7 = i9 + 1;
                this.buf[i9] = ',';
            }
            int i13 = i7 + 1;
            this.buf[i7] = '\"';
            str.getChars(0, str.length(), this.buf, i13);
            int length3 = str.length() + i13;
            this.buf[length3] = '\"';
            i10++;
            i9 = length3 + 1;
        }
        this.buf[i9] = ']';
        this.count = i9 + 1;
    }

    public void writeStringWithSingleQuote(char[] cArr) {
        int i7 = 0;
        if (cArr == null) {
            int i8 = this.count + 4;
            if (i8 > this.buf.length) {
                expandCapacity(i8);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i8;
            return;
        }
        int length = cArr.length;
        int i9 = this.count + length + 2;
        if (i9 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i7 < cArr.length) {
                    char c7 = cArr[i7];
                    if (c7 > '\r' && c7 != '\\' && c7 != '\'' && (c7 != '/' || !isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(c7);
                    } else {
                        write(92);
                        write(IOUtils.replaceChars[c7]);
                    }
                    i7++;
                }
                write(39);
                return;
            }
            expandCapacity(i9);
        }
        int i10 = this.count;
        int i11 = i10 + 1;
        int i12 = length + i11;
        char[] cArr2 = this.buf;
        cArr2[i10] = '\'';
        System.arraycopy(cArr, 0, cArr2, i11, cArr.length);
        this.count = i9;
        int i13 = -1;
        char c8 = 0;
        for (int i14 = i11; i14 < i12; i14++) {
            char c9 = this.buf[i14];
            if (c9 <= '\r' || c9 == '\\' || c9 == '\'' || (c9 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i7++;
                i13 = i14;
                c8 = c9;
            }
        }
        int i15 = i9 + i7;
        if (i15 > this.buf.length) {
            expandCapacity(i15);
        }
        this.count = i15;
        if (i7 == 1) {
            char[] cArr3 = this.buf;
            int i16 = i13 + 1;
            System.arraycopy(cArr3, i16, cArr3, i13 + 2, (i12 - i13) - 1);
            char[] cArr4 = this.buf;
            cArr4[i13] = '\\';
            cArr4[i16] = IOUtils.replaceChars[c8];
        } else if (i7 > 1) {
            char[] cArr5 = this.buf;
            int i17 = i13 + 1;
            System.arraycopy(cArr5, i17, cArr5, i13 + 2, (i12 - i13) - 1);
            char[] cArr6 = this.buf;
            cArr6[i13] = '\\';
            cArr6[i17] = IOUtils.replaceChars[c8];
            int i18 = i12 + 1;
            for (int i19 = i17 - 2; i19 >= i11; i19--) {
                char c10 = this.buf[i19];
                if (c10 <= '\r' || c10 == '\\' || c10 == '\'' || (c10 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    char[] cArr7 = this.buf;
                    int i20 = i19 + 1;
                    System.arraycopy(cArr7, i20, cArr7, i19 + 2, (i18 - i19) - 1);
                    char[] cArr8 = this.buf;
                    cArr8[i19] = '\\';
                    cArr8[i20] = IOUtils.replaceChars[c10];
                    i18++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeFieldValue(char c7, String str, long j7) throws IOException {
        if (j7 != Long.MIN_VALUE && this.quoteFieldNames) {
            int iStringSize = j7 < 0 ? IOUtils.stringSize(-j7) + 1 : IOUtils.stringSize(j7);
            int length = str.length();
            int i7 = this.count + length + 4 + iStringSize;
            if (i7 > this.buf.length) {
                if (this.writer != null) {
                    write(c7);
                    writeFieldName(str);
                    writeLong(j7);
                    return;
                }
                expandCapacity(i7);
            }
            int i8 = this.count;
            this.count = i7;
            char[] cArr = this.buf;
            cArr[i8] = c7;
            int i9 = i8 + length + 1;
            cArr[i8 + 1] = this.keySeperator;
            str.getChars(0, length, cArr, i8 + 2);
            char[] cArr2 = this.buf;
            cArr2[i9 + 1] = this.keySeperator;
            cArr2[i9 + 2] = ':';
            IOUtils.getChars(j7, this.count, cArr2);
            return;
        }
        write(c7);
        writeFieldName(str);
        writeLong(j7);
    }

    public void write(boolean z6) {
        if (z6) {
            write("true");
        } else {
            write("false");
        }
    }

    public void writeFieldValue(char c7, String str, float f7) throws IOException {
        write(c7);
        writeFieldName(str);
        writeFloat(f7, false);
    }

    public void writeFieldValue(char c7, String str, double d7) throws IOException {
        write(c7);
        writeFieldName(str);
        writeDouble(d7, false);
    }

    public void writeFieldValue(char c7, String str, String str2) {
        if (this.quoteFieldNames) {
            if (this.useSingleQuotes) {
                write(c7);
                writeFieldName(str);
                if (str2 == null) {
                    writeNull();
                    return;
                } else {
                    writeString(str2);
                    return;
                }
            }
            if (isEnabled(SerializerFeature.BrowserCompatible)) {
                write(c7);
                writeStringWithDoubleQuote(str, ':');
                writeStringWithDoubleQuote(str2, (char) 0);
                return;
            }
            writeFieldValueStringWithDoubleQuoteCheck(c7, str, str2);
            return;
        }
        write(c7);
        writeFieldName(str);
        if (str2 == null) {
            writeNull();
        } else {
            writeString(str2);
        }
    }

    public void writeFieldValue(char c7, String str, Enum<?> r42) throws IOException {
        if (r42 == null) {
            write(c7);
            writeFieldName(str);
            writeNull();
        } else if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            writeEnumFieldValue(c7, str, r42.name());
        } else if (this.writeEnumUsingToString) {
            writeEnumFieldValue(c7, str, r42.toString());
        } else {
            writeFieldValue(c7, str, r42.ordinal());
        }
    }

    public void writeFieldValue(char c7, String str, BigDecimal bigDecimal) {
        String string;
        write(c7);
        writeFieldName(str);
        if (bigDecimal == null) {
            writeNull();
            return;
        }
        int iScale = bigDecimal.scale();
        if (isEnabled(SerializerFeature.WriteBigDecimalAsPlain) && iScale >= -100 && iScale < 100) {
            string = bigDecimal.toPlainString();
        } else {
            string = bigDecimal.toString();
        }
        write(string);
    }

    /* JADX WARN: Removed duplicated region for block: B:166:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeStringWithDoubleQuote(char[] r24, char r25) {
        /*
            Method dump skipped, instructions count: 1332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.SerializeWriter.writeStringWithDoubleQuote(char[], char):void");
    }
}
