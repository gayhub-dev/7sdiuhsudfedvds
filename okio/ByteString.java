package okio;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0079a;
import android.support.constraint.solver.C0084a;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {
    private static final long serialVersionUID = 1;
    public final byte[] data;
    public transient int hashCode;
    public transient String utf8;
    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final ByteString EMPTY = m1869of(new byte[0]);

    public ByteString(byte[] bArr) {
        this.data = bArr;
    }

    public static int codePointIndexToCharIndex(String str, int i7) {
        int length = str.length();
        int iCharCount = 0;
        int i8 = 0;
        while (iCharCount < length) {
            if (i8 == i7) {
                return iCharCount;
            }
            int iCodePointAt = str.codePointAt(iCharCount);
            if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                return -1;
            }
            i8++;
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.length();
    }

    @Nullable
    public static ByteString decodeBase64(String str) {
        if (str == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] bArrDecode = Base64.decode(str);
        if (bArrDecode != null) {
            return new ByteString(bArrDecode);
        }
        return null;
    }

    public static ByteString decodeHex(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException(C0063n.m88a("Unexpected hex string: ", str));
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 * 2;
            bArr[i7] = (byte) ((decodeHexDigit(str.charAt(i8)) << 4) + decodeHexDigit(str.charAt(i8 + 1)));
        }
        return m1869of(bArr);
    }

    private static int decodeHexDigit(char c7) {
        if (c7 >= '0' && c7 <= '9') {
            return c7 - '0';
        }
        char c8 = 'a';
        if (c7 < 'a' || c7 > 'f') {
            c8 = 'A';
            if (c7 < 'A' || c7 > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c7);
            }
        }
        return (c7 - c8) + 10;
    }

    private ByteString digest(String str) {
        try {
            return m1869of(MessageDigest.getInstance(str).digest(this.data));
        } catch (NoSuchAlgorithmException e7) {
            throw new AssertionError(e7);
        }
    }

    public static ByteString encodeString(String str, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        if (charset != null) {
            return new ByteString(str.getBytes(charset));
        }
        throw new IllegalArgumentException("charset == null");
    }

    public static ByteString encodeUtf8(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(str.getBytes(Util.UTF_8));
        byteString.utf8 = str;
        return byteString;
    }

    private ByteString hmac(String str, ByteString byteString) throws NoSuchAlgorithmException, InvalidKeyException {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            return m1869of(mac.doFinal(this.data));
        } catch (InvalidKeyException e7) {
            throw new IllegalArgumentException(e7);
        } catch (NoSuchAlgorithmException e8) {
            throw new AssertionError(e8);
        }
    }

    /* renamed from: of */
    public static ByteString m1869of(byte... bArr) {
        if (bArr != null) {
            return new ByteString((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static ByteString read(InputStream inputStream, int i7) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException(C0079a.m93a("byteCount < 0: ", i7));
        }
        byte[] bArr = new byte[i7];
        int i8 = 0;
        while (i8 < i7) {
            int i9 = inputStream.read(bArr, i8, i7 - i8);
            if (i9 == -1) {
                throw new EOFException();
            }
            i8 += i9;
        }
        return new ByteString(bArr);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, NoSuchFieldException, IOException, IllegalArgumentException {
        ByteString byteString = read(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = ByteString.class.getDeclaredField("data");
            declaredField.setAccessible(true);
            declaredField.set(this, byteString.data);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (NoSuchFieldException unused2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(this.data).asReadOnlyBuffer();
    }

    public String base64() {
        return Base64.encode(this.data);
    }

    public String base64Url() {
        return Base64.encodeUrl(this.data);
    }

    public final boolean endsWith(ByteString byteString) {
        return rangeEquals(size() - byteString.size(), byteString, 0, byteString.size());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            int size = byteString.size();
            byte[] bArr = this.data;
            if (size == bArr.length && byteString.rangeEquals(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public byte getByte(int i7) {
        return this.data[i7];
    }

    public int hashCode() {
        int i7 = this.hashCode;
        if (i7 != 0) {
            return i7;
        }
        int iHashCode = Arrays.hashCode(this.data);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    public String hex() {
        byte[] bArr = this.data;
        char[] cArr = new char[bArr.length * 2];
        int i7 = 0;
        for (byte b7 : bArr) {
            int i8 = i7 + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i7] = cArr2[(b7 >> 4) & 15];
            i7 = i8 + 1;
            cArr[i8] = cArr2[b7 & 15];
        }
        return new String(cArr);
    }

    public ByteString hmacSha1(ByteString byteString) {
        return hmac("HmacSHA1", byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return hmac("HmacSHA256", byteString);
    }

    public ByteString hmacSha512(ByteString byteString) {
        return hmac("HmacSHA512", byteString);
    }

    public final int indexOf(ByteString byteString) {
        return indexOf(byteString.internalArray(), 0);
    }

    public byte[] internalArray() {
        return this.data;
    }

    public final int lastIndexOf(ByteString byteString) {
        return lastIndexOf(byteString.internalArray(), size());
    }

    public ByteString md5() {
        return digest("MD5");
    }

    public boolean rangeEquals(int i7, ByteString byteString, int i8, int i9) {
        return byteString.rangeEquals(i8, this.data, i7, i9);
    }

    public ByteString sha1() {
        return digest("SHA-1");
    }

    public ByteString sha256() {
        return digest("SHA-256");
    }

    public ByteString sha512() {
        return digest("SHA-512");
    }

    public int size() {
        return this.data.length;
    }

    public final boolean startsWith(ByteString byteString) {
        return rangeEquals(0, byteString, 0, byteString.size());
    }

    public String string(Charset charset) {
        if (charset != null) {
            return new String(this.data, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    public ByteString substring(int i7) {
        return substring(i7, this.data.length);
    }

    public ByteString toAsciiLowercase() {
        int i7 = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i7 >= bArr.length) {
                return this;
            }
            byte b7 = bArr[i7];
            if (b7 >= 65 && b7 <= 90) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i7] = (byte) (b7 + 32);
                for (int i8 = i7 + 1; i8 < bArr2.length; i8++) {
                    byte b8 = bArr2[i8];
                    if (b8 >= 65 && b8 <= 90) {
                        bArr2[i8] = (byte) (b8 + 32);
                    }
                }
                return new ByteString(bArr2);
            }
            i7++;
        }
    }

    public ByteString toAsciiUppercase() {
        int i7 = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i7 >= bArr.length) {
                return this;
            }
            byte b7 = bArr[i7];
            if (b7 >= 97 && b7 <= 122) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i7] = (byte) (b7 - 32);
                for (int i8 = i7 + 1; i8 < bArr2.length; i8++) {
                    byte b8 = bArr2[i8];
                    if (b8 >= 97 && b8 <= 122) {
                        bArr2[i8] = (byte) (b8 - 32);
                    }
                }
                return new ByteString(bArr2);
            }
            i7++;
        }
    }

    public byte[] toByteArray() {
        return (byte[]) this.data.clone();
    }

    public String toString() {
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String strUtf8 = utf8();
        int iCodePointIndexToCharIndex = codePointIndexToCharIndex(strUtf8, 64);
        if (iCodePointIndexToCharIndex != -1) {
            String strReplace = strUtf8.substring(0, iCodePointIndexToCharIndex).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (iCodePointIndexToCharIndex >= strUtf8.length()) {
                return C0096a.m97a("[text=", strReplace, "]");
            }
            StringBuilder sbM112a = C0413b.m112a("[size=");
            sbM112a.append(this.data.length);
            sbM112a.append(" text=");
            sbM112a.append(strReplace);
            sbM112a.append("…]");
            return sbM112a.toString();
        }
        if (this.data.length <= 64) {
            StringBuilder sbM112a2 = C0413b.m112a("[hex=");
            sbM112a2.append(hex());
            sbM112a2.append("]");
            return sbM112a2.toString();
        }
        StringBuilder sbM112a3 = C0413b.m112a("[size=");
        sbM112a3.append(this.data.length);
        sbM112a3.append(" hex=");
        sbM112a3.append(substring(0, 64).hex());
        sbM112a3.append("…]");
        return sbM112a3.toString();
    }

    public String utf8() {
        String str = this.utf8;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, Util.UTF_8);
        this.utf8 = str2;
        return str2;
    }

    public void write(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.data);
    }

    @Override // java.lang.Comparable
    public int compareTo(ByteString byteString) {
        int size = size();
        int size2 = byteString.size();
        int iMin = Math.min(size, size2);
        for (int i7 = 0; i7 < iMin; i7++) {
            int i8 = getByte(i7) & 255;
            int i9 = byteString.getByte(i7) & 255;
            if (i8 != i9) {
                return i8 < i9 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public final boolean endsWith(byte[] bArr) {
        return rangeEquals(size() - bArr.length, bArr, 0, bArr.length);
    }

    public final int indexOf(ByteString byteString, int i7) {
        return indexOf(byteString.internalArray(), i7);
    }

    public final int lastIndexOf(ByteString byteString, int i7) {
        return lastIndexOf(byteString.internalArray(), i7);
    }

    public boolean rangeEquals(int i7, byte[] bArr, int i8, int i9) {
        if (i7 >= 0) {
            byte[] bArr2 = this.data;
            if (i7 <= bArr2.length - i9 && i8 >= 0 && i8 <= bArr.length - i9 && Util.arrayRangeEquals(bArr2, i7, bArr, i8, i9)) {
                return true;
            }
        }
        return false;
    }

    public final boolean startsWith(byte[] bArr) {
        return rangeEquals(0, bArr, 0, bArr.length);
    }

    public ByteString substring(int i7, int i8) {
        if (i7 < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.data;
        if (i8 > bArr.length) {
            throw new IllegalArgumentException(C0084a.m96a(C0413b.m112a("endIndex > length("), this.data.length, ")"));
        }
        int i9 = i8 - i7;
        if (i9 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i7 == 0 && i8 == bArr.length) {
            return this;
        }
        byte[] bArr2 = new byte[i9];
        System.arraycopy(bArr, i7, bArr2, 0, i9);
        return new ByteString(bArr2);
    }

    /* renamed from: of */
    public static ByteString m1870of(byte[] bArr, int i7, int i8) {
        if (bArr != null) {
            Util.checkOffsetAndCount(bArr.length, i7, i8);
            byte[] bArr2 = new byte[i8];
            System.arraycopy(bArr, i7, bArr2, 0, i8);
            return new ByteString(bArr2);
        }
        throw new IllegalArgumentException("data == null");
    }

    public final int indexOf(byte[] bArr) {
        return indexOf(bArr, 0);
    }

    public final int lastIndexOf(byte[] bArr) {
        return lastIndexOf(bArr, size());
    }

    public void write(Buffer buffer) {
        byte[] bArr = this.data;
        buffer.write(bArr, 0, bArr.length);
    }

    public int indexOf(byte[] bArr, int i7) {
        int length = this.data.length - bArr.length;
        for (int iMax = Math.max(i7, 0); iMax <= length; iMax++) {
            if (Util.arrayRangeEquals(this.data, iMax, bArr, 0, bArr.length)) {
                return iMax;
            }
        }
        return -1;
    }

    public int lastIndexOf(byte[] bArr, int i7) {
        for (int iMin = Math.min(i7, this.data.length - bArr.length); iMin >= 0; iMin--) {
            if (Util.arrayRangeEquals(this.data, iMin, bArr, 0, bArr.length)) {
                return iMin;
            }
        }
        return -1;
    }

    /* renamed from: of */
    public static ByteString m1868of(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return new ByteString(bArr);
        }
        throw new IllegalArgumentException("data == null");
    }
}
