package com.alibaba.fastjson.util;

import android.support.v4.media.AudioAttributesCompat;
import android.support.v7.widget.ActivityChooserView;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.asm.Opcodes;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.MalformedInputException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Properties;
import p009b.C0413b;

/* loaded from: classes.dex */
public class IOUtils {
    public static final char[] ASCII_CHARS;

    /* renamed from: CA */
    public static final char[] f423CA;
    public static final char[] DigitOnes;
    public static final char[] DigitTens;
    public static final String FASTJSON_COMPATIBLEWITHFIELDNAME = "fastjson.compatibleWithFieldName";
    public static final String FASTJSON_COMPATIBLEWITHJAVABEAN = "fastjson.compatibleWithJavaBean";
    public static final String FASTJSON_PROPERTIES = "fastjson.properties";

    /* renamed from: IA */
    public static final int[] f424IA;
    public static final char[] digits;
    public static final char[] replaceChars;
    public static final int[] sizeTable;
    public static final byte[] specicalFlags_doubleQuotes;
    public static final boolean[] specicalFlags_doubleQuotesFlags;
    public static final byte[] specicalFlags_singleQuotes;
    public static final boolean[] specicalFlags_singleQuotesFlags;
    public static final Properties DEFAULT_PROPERTIES = new Properties();
    public static final Charset UTF8 = Charset.forName("UTF-8");
    public static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final boolean[] firstIdentifierFlags = new boolean[256];
    public static final boolean[] identifierFlags = new boolean[256];

    static {
        char c7 = 0;
        while (true) {
            boolean[] zArr = firstIdentifierFlags;
            if (c7 >= zArr.length) {
                break;
            }
            if (c7 >= 'A' && c7 <= 'Z') {
                zArr[c7] = true;
            } else if (c7 >= 'a' && c7 <= 'z') {
                zArr[c7] = true;
            } else if (c7 == '_' || c7 == '$') {
                zArr[c7] = true;
            }
            c7 = (char) (c7 + 1);
        }
        char c8 = 0;
        while (true) {
            boolean[] zArr2 = identifierFlags;
            if (c8 < zArr2.length) {
                if (c8 >= 'A' && c8 <= 'Z') {
                    zArr2[c8] = true;
                } else if (c8 >= 'a' && c8 <= 'z') {
                    zArr2[c8] = true;
                } else if (c8 == '_') {
                    zArr2[c8] = true;
                } else if (c8 >= '0' && c8 <= '9') {
                    zArr2[c8] = true;
                }
                c8 = (char) (c8 + 1);
            } else {
                try {
                    break;
                } catch (Throwable unused) {
                }
            }
        }
        loadPropertiesFromFile();
        byte[] bArr = new byte[Opcodes.IF_ICMPLT];
        specicalFlags_doubleQuotes = bArr;
        byte[] bArr2 = new byte[Opcodes.IF_ICMPLT];
        specicalFlags_singleQuotes = bArr2;
        specicalFlags_doubleQuotesFlags = new boolean[Opcodes.IF_ICMPLT];
        specicalFlags_singleQuotesFlags = new boolean[Opcodes.IF_ICMPLT];
        replaceChars = new char[93];
        bArr[0] = 4;
        bArr[1] = 4;
        bArr[2] = 4;
        bArr[3] = 4;
        bArr[4] = 4;
        bArr[5] = 4;
        bArr[6] = 4;
        bArr[7] = 4;
        bArr[8] = 1;
        bArr[9] = 1;
        bArr[10] = 1;
        bArr[11] = 4;
        bArr[12] = 1;
        bArr[13] = 1;
        bArr[34] = 1;
        bArr[92] = 1;
        bArr2[0] = 4;
        bArr2[1] = 4;
        bArr2[2] = 4;
        bArr2[3] = 4;
        bArr2[4] = 4;
        bArr2[5] = 4;
        bArr2[6] = 4;
        bArr2[7] = 4;
        bArr2[8] = 1;
        bArr2[9] = 1;
        bArr2[10] = 1;
        bArr2[11] = 4;
        bArr2[12] = 1;
        bArr2[13] = 1;
        bArr2[92] = 1;
        bArr2[39] = 1;
        for (int i7 = 14; i7 <= 31; i7++) {
            specicalFlags_doubleQuotes[i7] = 4;
            specicalFlags_singleQuotes[i7] = 4;
        }
        for (int i8 = 127; i8 < 160; i8++) {
            specicalFlags_doubleQuotes[i8] = 4;
            specicalFlags_singleQuotes[i8] = 4;
        }
        for (int i9 = 0; i9 < 161; i9++) {
            specicalFlags_doubleQuotesFlags[i9] = specicalFlags_doubleQuotes[i9] != 0;
            specicalFlags_singleQuotesFlags[i9] = specicalFlags_singleQuotes[i9] != 0;
        }
        char[] cArr = replaceChars;
        cArr[0] = '0';
        cArr[1] = '1';
        cArr[2] = '2';
        cArr[3] = '3';
        cArr[4] = '4';
        cArr[5] = '5';
        cArr[6] = '6';
        cArr[7] = '7';
        cArr[8] = 'b';
        cArr[9] = 't';
        cArr[10] = 'n';
        cArr[11] = 'v';
        cArr[12] = 'f';
        cArr[13] = 'r';
        cArr[34] = '\"';
        cArr[39] = '\'';
        cArr[47] = '/';
        cArr[92] = '\\';
        ASCII_CHARS = new char[]{'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
        digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        DigitTens = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
        DigitOnes = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        sizeTable = new int[]{9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        f423CA = charArray;
        int[] iArr = new int[256];
        f424IA = iArr;
        Arrays.fill(iArr, -1);
        int length = charArray.length;
        for (int i10 = 0; i10 < length; i10++) {
            f424IA[f423CA[i10]] = i10;
        }
        f424IA[61] = 0;
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void decode(CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer) throws CharacterCodingException {
        try {
            CoderResult coderResultDecode = charsetDecoder.decode(byteBuffer, charBuffer, true);
            if (!coderResultDecode.isUnderflow()) {
                coderResultDecode.throwException();
            }
            CoderResult coderResultFlush = charsetDecoder.flush(charBuffer);
            if (coderResultFlush.isUnderflow()) {
                return;
            }
            coderResultFlush.throwException();
        } catch (CharacterCodingException e7) {
            StringBuilder sbM112a = C0413b.m112a("utf8 decode error, ");
            sbM112a.append(e7.getMessage());
            throw new JSONException(sbM112a.toString(), e7);
        }
    }

    public static byte[] decodeBase64(char[] cArr, int i7, int i8) {
        int i9;
        int i10 = 0;
        if (i8 == 0) {
            return new byte[0];
        }
        int i11 = (i7 + i8) - 1;
        while (i7 < i11 && f424IA[cArr[i7]] < 0) {
            i7++;
        }
        while (i11 > 0 && f424IA[cArr[i11]] < 0) {
            i11--;
        }
        int i12 = cArr[i11] == '=' ? cArr[i11 + (-1)] == '=' ? 2 : 1 : 0;
        int i13 = (i11 - i7) + 1;
        if (i8 > 76) {
            i9 = (cArr[76] == '\r' ? i13 / 78 : 0) << 1;
        } else {
            i9 = 0;
        }
        int i14 = (((i13 - i9) * 6) >> 3) - i12;
        byte[] bArr = new byte[i14];
        int i15 = (i14 / 3) * 3;
        int i16 = 0;
        int i17 = 0;
        while (i16 < i15) {
            int[] iArr = f424IA;
            int i18 = i7 + 1;
            int i19 = i18 + 1;
            int i20 = (iArr[cArr[i7]] << 18) | (iArr[cArr[i18]] << 12);
            int i21 = i19 + 1;
            int i22 = i20 | (iArr[cArr[i19]] << 6);
            int i23 = i21 + 1;
            int i24 = i22 | iArr[cArr[i21]];
            int i25 = i16 + 1;
            bArr[i16] = (byte) (i24 >> 16);
            int i26 = i25 + 1;
            bArr[i25] = (byte) (i24 >> 8);
            int i27 = i26 + 1;
            bArr[i26] = (byte) i24;
            if (i9 <= 0 || (i17 = i17 + 1) != 19) {
                i7 = i23;
            } else {
                i7 = i23 + 2;
                i17 = 0;
            }
            i16 = i27;
        }
        if (i16 < i14) {
            int i28 = 0;
            while (i7 <= i11 - i12) {
                i10 |= f424IA[cArr[i7]] << (18 - (i28 * 6));
                i28++;
                i7++;
            }
            int i29 = 16;
            while (i16 < i14) {
                bArr[i16] = (byte) (i10 >> i29);
                i29 -= 8;
                i16++;
            }
        }
        return bArr;
    }

    public static int decodeUTF8(byte[] bArr, int i7, int i8, char[] cArr) {
        int i9 = i7 + i8;
        int iMin = Math.min(i8, cArr.length);
        int i10 = 0;
        while (i10 < iMin && bArr[i7] >= 0) {
            cArr[i10] = (char) bArr[i7];
            i10++;
            i7++;
        }
        while (i7 < i9) {
            int i11 = i7 + 1;
            byte b7 = bArr[i7];
            if (b7 >= 0) {
                cArr[i10] = (char) b7;
                i7 = i11;
                i10++;
            } else {
                if ((b7 >> 5) != -2 || (b7 & 30) == 0) {
                    if ((b7 >> 4) == -2) {
                        int i12 = i11 + 1;
                        if (i12 < i9) {
                            byte b8 = bArr[i11];
                            int i13 = i12 + 1;
                            byte b9 = bArr[i12];
                            if ((b7 != -32 || (b8 & 224) != 128) && (b8 & 192) == 128 && (b9 & 192) == 128) {
                                char c7 = (char) (((b7 << 12) ^ (b8 << 6)) ^ ((-123008) ^ b9));
                                if (c7 >= 55296 && c7 < 57344) {
                                    return -1;
                                }
                                cArr[i10] = c7;
                                i10++;
                                i7 = i13;
                            }
                        }
                        return -1;
                    }
                    if ((b7 >> 3) == -2 && i11 + 2 < i9) {
                        int i14 = i11 + 1;
                        byte b10 = bArr[i11];
                        int i15 = i14 + 1;
                        byte b11 = bArr[i14];
                        int i16 = i15 + 1;
                        byte b12 = bArr[i15];
                        int i17 = (((b7 << 18) ^ (b10 << 12)) ^ (b11 << 6)) ^ (3678080 ^ b12);
                        if ((b10 & 192) == 128 && (b11 & 192) == 128 && (b12 & 192) == 128 && i17 >= 65536 && i17 < 1114112) {
                            int i18 = i10 + 1;
                            cArr[i10] = (char) ((i17 >>> 10) + 55232);
                            i10 = i18 + 1;
                            cArr[i18] = (char) ((i17 & AudioAttributesCompat.FLAG_ALL) + 56320);
                            i7 = i16;
                        }
                    }
                    return -1;
                }
                if (i11 >= i9) {
                    return -1;
                }
                int i19 = i11 + 1;
                byte b13 = bArr[i11];
                if ((b13 & 192) != 128) {
                    return -1;
                }
                cArr[i10] = (char) (((b7 << 6) ^ b13) ^ 3968);
                i7 = i19;
                i10++;
            }
        }
        return i10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v16, types: [int] */
    /* JADX WARN: Type inference failed for: r10v25, types: [int] */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v3, types: [char, int] */
    public static int encodeUTF8(char[] cArr, int i7, int i8, byte[] bArr) {
        int i9;
        int i10;
        int i11 = i7 + i8;
        int i12 = 0;
        int iMin = Math.min(i8, bArr.length) + 0;
        while (i12 < iMin && cArr[i7] < 128) {
            bArr[i12] = (byte) cArr[i7];
            i12++;
            i7++;
        }
        while (i7 < i11) {
            int i13 = i7 + 1;
            char c7 = cArr[i7];
            if (c7 < 128) {
                i9 = i12 + 1;
                bArr[i12] = (byte) c7;
            } else {
                if (c7 < 2048) {
                    int i14 = i12 + 1;
                    bArr[i12] = (byte) ((c7 >> 6) | Opcodes.CHECKCAST);
                    i12 = i14 + 1;
                    bArr[i14] = (byte) ((c7 & 63) | 128);
                } else if (c7 < 55296 || c7 >= 57344) {
                    int i15 = i12 + 1;
                    bArr[i12] = (byte) ((c7 >> 12) | 224);
                    int i16 = i15 + 1;
                    bArr[i15] = (byte) ((63 & (c7 >> 6)) | 128);
                    i9 = i16 + 1;
                    bArr[i16] = (byte) ((c7 & 63) | 128);
                } else {
                    int i17 = i13 - 1;
                    if (c7 < 55296 || c7 >= 56320) {
                        if (c7 >= 56320 && c7 < 57344) {
                            i10 = i12 + 1;
                            bArr[i12] = 63;
                        }
                        i12 = i10;
                    } else if (i11 - i17 < 2) {
                        c7 = -1;
                    } else {
                        char c8 = cArr[i17 + 1];
                        if (c8 < 56320 || c8 >= 57344) {
                            throw new JSONException("encodeUTF8 error", new MalformedInputException(1));
                        }
                        c7 = ((c7 << 10) + c8) - 56613888;
                    }
                    if (c7 < 0) {
                        i10 = i12 + 1;
                        bArr[i12] = 63;
                    } else {
                        int i18 = i12 + 1;
                        bArr[i12] = (byte) ((c7 >> 18) | 240);
                        int i19 = i18 + 1;
                        bArr[i18] = (byte) (((c7 >> 12) & 63) | 128);
                        int i20 = i19 + 1;
                        bArr[i19] = (byte) ((63 & (c7 >> 6)) | 128);
                        bArr[i20] = (byte) ((c7 & 63) | 128);
                        i13++;
                        i10 = i20 + 1;
                    }
                    i12 = i10;
                }
                i7 = i13;
            }
            i7 = i13;
            i12 = i9;
        }
        return i12;
    }

    public static boolean firstIdentifier(char c7) {
        boolean[] zArr = firstIdentifierFlags;
        return c7 < zArr.length && zArr[c7];
    }

    public static void getChars(long j7, int i7, char[] cArr) {
        char c7;
        if (j7 < 0) {
            c7 = '-';
            j7 = -j7;
        } else {
            c7 = 0;
        }
        while (j7 > 2147483647L) {
            long j8 = j7 / 100;
            int i8 = (int) (j7 - (((j8 << 6) + (j8 << 5)) + (j8 << 2)));
            int i9 = i7 - 1;
            cArr[i9] = DigitOnes[i8];
            i7 = i9 - 1;
            cArr[i7] = DigitTens[i8];
            j7 = j8;
        }
        int i10 = (int) j7;
        while (i10 >= 65536) {
            int i11 = i10 / 100;
            int i12 = i10 - (((i11 << 6) + (i11 << 5)) + (i11 << 2));
            int i13 = i7 - 1;
            cArr[i13] = DigitOnes[i12];
            i7 = i13 - 1;
            cArr[i7] = DigitTens[i12];
            i10 = i11;
        }
        while (true) {
            int i14 = (52429 * i10) >>> 19;
            i7--;
            cArr[i7] = digits[i10 - ((i14 << 3) + (i14 << 1))];
            if (i14 == 0) {
                break;
            } else {
                i10 = i14;
            }
        }
        if (c7 != 0) {
            cArr[i7 - 1] = c7;
        }
    }

    public static String getStringProperty(String str) {
        String property;
        try {
            property = System.getProperty(str);
        } catch (SecurityException unused) {
            property = null;
        }
        return property == null ? DEFAULT_PROPERTIES.getProperty(str) : property;
    }

    public static boolean isIdent(char c7) {
        boolean[] zArr = identifierFlags;
        return c7 < zArr.length && zArr[c7];
    }

    public static boolean isValidJsonpQueryParam(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt != '.' && !isIdent(cCharAt)) {
                return false;
            }
        }
        return true;
    }

    public static void loadPropertiesFromFile() throws IOException {
        InputStream inputStream = (InputStream) AccessController.doPrivileged(new PrivilegedAction<InputStream>() { // from class: com.alibaba.fastjson.util.IOUtils.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public InputStream run() {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                return contextClassLoader != null ? contextClassLoader.getResourceAsStream(IOUtils.FASTJSON_PROPERTIES) : ClassLoader.getSystemResourceAsStream(IOUtils.FASTJSON_PROPERTIES);
            }
        });
        if (inputStream != null) {
            try {
                DEFAULT_PROPERTIES.load(inputStream);
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static String readAll(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            char[] cArr = new char[2048];
            while (true) {
                int i7 = reader.read(cArr, 0, 2048);
                if (i7 < 0) {
                    return sb.toString();
                }
                sb.append(cArr, 0, i7);
            }
        } catch (Exception e7) {
            throw new JSONException("read string from reader error", e7);
        }
    }

    public static int stringSize(int i7) {
        int i8 = 0;
        while (i7 > sizeTable[i8]) {
            i8++;
        }
        return i8 + 1;
    }

    public static int stringSize(long j7) {
        long j8 = 10;
        for (int i7 = 1; i7 < 19; i7++) {
            if (j7 < j8) {
                return i7;
            }
            j8 *= 10;
        }
        return 19;
    }

    public static void getChars(int i7, int i8, char[] cArr) {
        char c7;
        if (i7 < 0) {
            c7 = '-';
            i7 = -i7;
        } else {
            c7 = 0;
        }
        while (i7 >= 65536) {
            int i9 = i7 / 100;
            int i10 = i7 - (((i9 << 6) + (i9 << 5)) + (i9 << 2));
            int i11 = i8 - 1;
            cArr[i11] = DigitOnes[i10];
            i8 = i11 - 1;
            cArr[i8] = DigitTens[i10];
            i7 = i9;
        }
        while (true) {
            int i12 = (52429 * i7) >>> 19;
            i8--;
            cArr[i8] = digits[i7 - ((i12 << 3) + (i12 << 1))];
            if (i12 == 0) {
                break;
            } else {
                i7 = i12;
            }
        }
        if (c7 != 0) {
            cArr[i8 - 1] = c7;
        }
    }

    public static byte[] decodeBase64(String str, int i7, int i8) {
        int i9;
        int i10 = 0;
        if (i8 == 0) {
            return new byte[0];
        }
        int i11 = (i7 + i8) - 1;
        while (i7 < i11 && f424IA[str.charAt(i7)] < 0) {
            i7++;
        }
        while (i11 > 0 && f424IA[str.charAt(i11)] < 0) {
            i11--;
        }
        int i12 = str.charAt(i11) == '=' ? str.charAt(i11 + (-1)) == '=' ? 2 : 1 : 0;
        int i13 = (i11 - i7) + 1;
        if (i8 > 76) {
            i9 = (str.charAt(76) == '\r' ? i13 / 78 : 0) << 1;
        } else {
            i9 = 0;
        }
        int i14 = (((i13 - i9) * 6) >> 3) - i12;
        byte[] bArr = new byte[i14];
        int i15 = (i14 / 3) * 3;
        int i16 = 0;
        int i17 = 0;
        while (i16 < i15) {
            int[] iArr = f424IA;
            int i18 = i7 + 1;
            int i19 = i18 + 1;
            int i20 = (iArr[str.charAt(i7)] << 18) | (iArr[str.charAt(i18)] << 12);
            int i21 = i19 + 1;
            int i22 = i20 | (iArr[str.charAt(i19)] << 6);
            int i23 = i21 + 1;
            int i24 = i22 | iArr[str.charAt(i21)];
            int i25 = i16 + 1;
            bArr[i16] = (byte) (i24 >> 16);
            int i26 = i25 + 1;
            bArr[i25] = (byte) (i24 >> 8);
            int i27 = i26 + 1;
            bArr[i26] = (byte) i24;
            if (i9 <= 0 || (i17 = i17 + 1) != 19) {
                i7 = i23;
            } else {
                i7 = i23 + 2;
                i17 = 0;
            }
            i16 = i27;
        }
        if (i16 < i14) {
            int i28 = 0;
            while (i7 <= i11 - i12) {
                i10 |= f424IA[str.charAt(i7)] << (18 - (i28 * 6));
                i28++;
                i7++;
            }
            int i29 = 16;
            while (i16 < i14) {
                bArr[i16] = (byte) (i10 >> i29);
                i29 -= 8;
                i16++;
            }
        }
        return bArr;
    }

    public static void getChars(byte b7, int i7, char[] cArr) {
        char c7;
        int i8;
        if (b7 < 0) {
            c7 = '-';
            i8 = -b7;
        } else {
            c7 = 0;
            i8 = b7;
        }
        while (true) {
            int i9 = (52429 * i8) >>> 19;
            i7--;
            cArr[i7] = digits[i8 - ((i9 << 3) + (i9 << 1))];
            if (i9 == 0) {
                break;
            } else {
                i8 = i9;
            }
        }
        if (c7 != 0) {
            cArr[i7 - 1] = c7;
        }
    }

    public static byte[] decodeBase64(String str) {
        int i7;
        int length = str.length();
        int i8 = 0;
        if (length == 0) {
            return new byte[0];
        }
        int i9 = length - 1;
        int i10 = 0;
        while (i10 < i9 && f424IA[str.charAt(i10) & 255] < 0) {
            i10++;
        }
        while (i9 > 0 && f424IA[str.charAt(i9) & 255] < 0) {
            i9--;
        }
        int i11 = str.charAt(i9) == '=' ? str.charAt(i9 + (-1)) == '=' ? 2 : 1 : 0;
        int i12 = (i9 - i10) + 1;
        if (length > 76) {
            i7 = (str.charAt(76) == '\r' ? i12 / 78 : 0) << 1;
        } else {
            i7 = 0;
        }
        int i13 = (((i12 - i7) * 6) >> 3) - i11;
        byte[] bArr = new byte[i13];
        int i14 = (i13 / 3) * 3;
        int i15 = 0;
        int i16 = 0;
        while (i15 < i14) {
            int[] iArr = f424IA;
            int i17 = i10 + 1;
            int i18 = i17 + 1;
            int i19 = (iArr[str.charAt(i10)] << 18) | (iArr[str.charAt(i17)] << 12);
            int i20 = i18 + 1;
            int i21 = i19 | (iArr[str.charAt(i18)] << 6);
            int i22 = i20 + 1;
            int i23 = i21 | iArr[str.charAt(i20)];
            int i24 = i15 + 1;
            bArr[i15] = (byte) (i23 >> 16);
            int i25 = i24 + 1;
            bArr[i24] = (byte) (i23 >> 8);
            int i26 = i25 + 1;
            bArr[i25] = (byte) i23;
            if (i7 <= 0 || (i16 = i16 + 1) != 19) {
                i10 = i22;
            } else {
                i10 = i22 + 2;
                i16 = 0;
            }
            i15 = i26;
        }
        if (i15 < i13) {
            int i27 = 0;
            while (i10 <= i9 - i11) {
                i8 |= f424IA[str.charAt(i10)] << (18 - (i27 * 6));
                i27++;
                i10++;
            }
            int i28 = 16;
            while (i15 < i13) {
                bArr[i15] = (byte) (i8 >> i28);
                i28 -= 8;
                i15++;
            }
        }
        return bArr;
    }
}
