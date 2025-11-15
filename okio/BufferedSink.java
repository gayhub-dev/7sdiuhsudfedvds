package okio;

import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public interface BufferedSink extends Sink, WritableByteChannel {
    Buffer buffer();

    BufferedSink emit();

    BufferedSink emitCompleteSegments();

    @Override // okio.Sink, java.io.Flushable
    void flush();

    OutputStream outputStream();

    BufferedSink write(ByteString byteString);

    BufferedSink write(Source source, long j7);

    BufferedSink write(byte[] bArr);

    BufferedSink write(byte[] bArr, int i7, int i8);

    long writeAll(Source source);

    BufferedSink writeByte(int i7);

    BufferedSink writeDecimalLong(long j7);

    BufferedSink writeHexadecimalUnsignedLong(long j7);

    BufferedSink writeInt(int i7);

    BufferedSink writeIntLe(int i7);

    BufferedSink writeLong(long j7);

    BufferedSink writeLongLe(long j7);

    BufferedSink writeShort(int i7);

    BufferedSink writeShortLe(int i7);

    BufferedSink writeString(String str, int i7, int i8, Charset charset);

    BufferedSink writeString(String str, Charset charset);

    BufferedSink writeUtf8(String str);

    BufferedSink writeUtf8(String str, int i7, int i8);

    BufferedSink writeUtf8CodePoint(int i7);
}
