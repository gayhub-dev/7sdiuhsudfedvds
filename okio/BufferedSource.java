package okio;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    @Deprecated
    Buffer buffer();

    boolean exhausted();

    Buffer getBuffer();

    long indexOf(byte b7);

    long indexOf(byte b7, long j7);

    long indexOf(byte b7, long j7, long j8);

    long indexOf(ByteString byteString);

    long indexOf(ByteString byteString, long j7);

    long indexOfElement(ByteString byteString);

    long indexOfElement(ByteString byteString, long j7);

    InputStream inputStream();

    BufferedSource peek();

    boolean rangeEquals(long j7, ByteString byteString);

    boolean rangeEquals(long j7, ByteString byteString, int i7, int i8);

    int read(byte[] bArr);

    int read(byte[] bArr, int i7, int i8);

    long readAll(Sink sink);

    byte readByte();

    byte[] readByteArray();

    byte[] readByteArray(long j7);

    ByteString readByteString();

    ByteString readByteString(long j7);

    long readDecimalLong();

    void readFully(Buffer buffer, long j7);

    void readFully(byte[] bArr);

    long readHexadecimalUnsignedLong();

    int readInt();

    int readIntLe();

    long readLong();

    long readLongLe();

    short readShort();

    short readShortLe();

    String readString(long j7, Charset charset);

    String readString(Charset charset);

    String readUtf8();

    String readUtf8(long j7);

    int readUtf8CodePoint();

    @Nullable
    String readUtf8Line();

    String readUtf8LineStrict();

    String readUtf8LineStrict(long j7);

    boolean request(long j7);

    void require(long j7);

    int select(Options options);

    void skip(long j7);
}
