package okio;

import android.support.v7.widget.RecyclerView;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
import javax.annotation.Nullable;
import p009b.C0413b;

/* loaded from: classes.dex */
final class RealBufferedSource implements BufferedSource {
    public final Buffer buffer = new Buffer();
    public boolean closed;
    public final Source source;

    public RealBufferedSource(Source source) {
        Objects.requireNonNull(source, "source == null");
        this.source = source;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.source.close();
        this.buffer.clear();
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.buffer.exhausted() && this.source.read(this.buffer, 8192L) == -1;
    }

    @Override // okio.BufferedSource
    public Buffer getBuffer() {
        return this.buffer;
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b7) {
        return indexOf(b7, 0L, RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.RealBufferedSource.1
            @Override // java.io.InputStream
            public int available() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (realBufferedSource.closed) {
                    throw new IOException("closed");
                }
                return (int) Math.min(realBufferedSource.buffer.size, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                RealBufferedSource.this.close();
            }

            @Override // java.io.InputStream
            public int read() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (realBufferedSource.closed) {
                    throw new IOException("closed");
                }
                Buffer buffer = realBufferedSource.buffer;
                if (buffer.size == 0 && realBufferedSource.source.read(buffer, 8192L) == -1) {
                    return -1;
                }
                return RealBufferedSource.this.buffer.readByte() & 255;
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i7, int i8) throws IOException {
                if (!RealBufferedSource.this.closed) {
                    Util.checkOffsetAndCount(bArr.length, i7, i8);
                    RealBufferedSource realBufferedSource = RealBufferedSource.this;
                    Buffer buffer = realBufferedSource.buffer;
                    if (buffer.size == 0 && realBufferedSource.source.read(buffer, 8192L) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.buffer.read(bArr, i7, i8);
                }
                throw new IOException("closed");
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j7, ByteString byteString) {
        return rangeEquals(j7, byteString, 0, byteString.size());
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j7) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j7 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Buffer buffer2 = this.buffer;
        if (buffer2.size == 0 && this.source.read(buffer2, 8192L) == -1) {
            return -1L;
        }
        return this.buffer.read(buffer, Math.min(j7, this.buffer.size));
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j7 = 0;
        while (this.source.read(this.buffer, 8192L) != -1) {
            long jCompleteSegmentByteCount = this.buffer.completeSegmentByteCount();
            if (jCompleteSegmentByteCount > 0) {
                j7 += jCompleteSegmentByteCount;
                sink.write(this.buffer, jCompleteSegmentByteCount);
            }
        }
        if (this.buffer.size() <= 0) {
            return j7;
        }
        long size = j7 + this.buffer.size();
        Buffer buffer = this.buffer;
        sink.write(buffer, buffer.size());
        return size;
    }

    @Override // okio.BufferedSource
    public byte readByte() throws EOFException {
        require(1L);
        return this.buffer.readByte();
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteString();
    }

    @Override // okio.BufferedSource
    public long readDecimalLong() throws EOFException {
        byte b7;
        require(1L);
        int i7 = 0;
        while (true) {
            int i8 = i7 + 1;
            if (!request(i8)) {
                break;
            }
            b7 = this.buffer.getByte(i7);
            if ((b7 < 48 || b7 > 57) && !(i7 == 0 && b7 == 45)) {
                break;
            }
            i7 = i8;
        }
        if (i7 == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(b7)));
        }
        return this.buffer.readDecimalLong();
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] bArr) throws EOFException {
        try {
            require(bArr.length);
            this.buffer.readFully(bArr);
        } catch (EOFException e7) {
            int i7 = 0;
            while (true) {
                Buffer buffer = this.buffer;
                long j7 = buffer.size;
                if (j7 <= 0) {
                    throw e7;
                }
                int i8 = buffer.read(bArr, i7, (int) j7);
                if (i8 == -1) {
                    throw new AssertionError();
                }
                i7 += i8;
            }
        }
    }

    @Override // okio.BufferedSource
    public long readHexadecimalUnsignedLong() throws EOFException {
        byte b7;
        require(1L);
        int i7 = 0;
        while (true) {
            int i8 = i7 + 1;
            if (!request(i8)) {
                break;
            }
            b7 = this.buffer.getByte(i7);
            if ((b7 < 48 || b7 > 57) && ((b7 < 97 || b7 > 102) && (b7 < 65 || b7 > 70))) {
                break;
            }
            i7 = i8;
        }
        if (i7 == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(b7)));
        }
        return this.buffer.readHexadecimalUnsignedLong();
    }

    @Override // okio.BufferedSource
    public int readInt() throws EOFException {
        require(4L);
        return this.buffer.readInt();
    }

    @Override // okio.BufferedSource
    public int readIntLe() throws EOFException {
        require(4L);
        return this.buffer.readIntLe();
    }

    @Override // okio.BufferedSource
    public long readLong() throws EOFException {
        require(8L);
        return this.buffer.readLong();
    }

    @Override // okio.BufferedSource
    public long readLongLe() throws EOFException {
        require(8L);
        return this.buffer.readLongLe();
    }

    @Override // okio.BufferedSource
    public short readShort() throws EOFException {
        require(2L);
        return this.buffer.readShort();
    }

    @Override // okio.BufferedSource
    public short readShortLe() throws EOFException {
        require(2L);
        return this.buffer.readShortLe();
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.buffer.writeAll(this.source);
        return this.buffer.readString(charset);
    }

    @Override // okio.BufferedSource
    public String readUtf8() {
        this.buffer.writeAll(this.source);
        return this.buffer.readUtf8();
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        require(1L);
        byte b7 = this.buffer.getByte(0L);
        if ((b7 & 224) == 192) {
            require(2L);
        } else if ((b7 & 240) == 224) {
            require(3L);
        } else if ((b7 & 248) == 240) {
            require(4L);
        }
        return this.buffer.readUtf8CodePoint();
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() {
        long jIndexOf = indexOf((byte) 10);
        if (jIndexOf != -1) {
            return this.buffer.readUtf8Line(jIndexOf);
        }
        long j7 = this.buffer.size;
        if (j7 != 0) {
            return readUtf8(j7);
        }
        return null;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() {
        return readUtf8LineStrict(RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public boolean request(long j7) {
        Buffer buffer;
        if (j7 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        do {
            buffer = this.buffer;
            if (buffer.size >= j7) {
                return true;
            }
        } while (this.source.read(buffer, 8192L) != -1);
        return false;
    }

    @Override // okio.BufferedSource
    public void require(long j7) throws EOFException {
        if (!request(j7)) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public int select(Options options) throws EOFException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        do {
            int iSelectPrefix = this.buffer.selectPrefix(options, true);
            if (iSelectPrefix == -1) {
                return -1;
            }
            if (iSelectPrefix != -2) {
                this.buffer.skip(options.byteStrings[iSelectPrefix].size());
                return iSelectPrefix;
            }
        } while (this.source.read(this.buffer, 8192L) != -1);
        return -1;
    }

    @Override // okio.BufferedSource
    public void skip(long j7) throws EOFException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (j7 > 0) {
            Buffer buffer = this.buffer;
            if (buffer.size == 0 && this.source.read(buffer, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j7, this.buffer.size());
            this.buffer.skip(jMin);
            j7 -= jMin;
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("buffer(");
        sbM112a.append(this.source);
        sbM112a.append(")");
        return sbM112a.toString();
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b7, long j7) {
        return indexOf(b7, j7, RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString, long j7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jIndexOfElement = this.buffer.indexOfElement(byteString, j7);
            if (jIndexOfElement != -1) {
                return jIndexOfElement;
            }
            Buffer buffer = this.buffer;
            long j8 = buffer.size;
            if (this.source.read(buffer, 8192L) == -1) {
                return -1L;
            }
            j7 = Math.max(j7, j8);
        }
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j7, ByteString byteString, int i7, int i8) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (j7 < 0 || i7 < 0 || i8 < 0 || byteString.size() - i7 < i8) {
            return false;
        }
        for (int i9 = 0; i9 < i8; i9++) {
            long j8 = i9 + j7;
            if (!request(1 + j8) || this.buffer.getByte(j8) != byteString.getByte(i7 + i9)) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long j7) throws EOFException {
        if (j7 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("limit < 0: ", j7));
        }
        long j8 = j7 == RecyclerView.FOREVER_NS ? Long.MAX_VALUE : j7 + 1;
        long jIndexOf = indexOf((byte) 10, 0L, j8);
        if (jIndexOf != -1) {
            return this.buffer.readUtf8Line(jIndexOf);
        }
        if (j8 < RecyclerView.FOREVER_NS && request(j8) && this.buffer.getByte(j8 - 1) == 13 && request(1 + j8) && this.buffer.getByte(j8) == 10) {
            return this.buffer.readUtf8Line(j8);
        }
        Buffer buffer = new Buffer();
        Buffer buffer2 = this.buffer;
        buffer2.copyTo(buffer, 0L, Math.min(32L, buffer2.size()));
        StringBuilder sbM112a = C0413b.m112a("\\n not found: limit=");
        sbM112a.append(Math.min(this.buffer.size(), j7));
        sbM112a.append(" content=");
        sbM112a.append(buffer.readByteString().hex());
        sbM112a.append((char) 8230);
        throw new EOFException(sbM112a.toString());
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b7, long j7, long j8) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (j7 < 0 || j8 < j7) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j7), Long.valueOf(j8)));
        }
        while (j7 < j8) {
            long jIndexOf = this.buffer.indexOf(b7, j7, j8);
            if (jIndexOf != -1) {
                return jIndexOf;
            }
            Buffer buffer = this.buffer;
            long j9 = buffer.size;
            if (j9 >= j8 || this.source.read(buffer, 8192L) == -1) {
                break;
            }
            j7 = Math.max(j7, j9);
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long j7) throws EOFException {
        require(j7);
        return this.buffer.readByteArray(j7);
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long j7) throws EOFException {
        require(j7);
        return this.buffer.readByteString(j7);
    }

    @Override // okio.BufferedSource
    public String readUtf8(long j7) throws EOFException {
        require(j7);
        return this.buffer.readUtf8(j7);
    }

    @Override // okio.BufferedSource
    public String readString(long j7, Charset charset) throws EOFException {
        require(j7);
        if (charset != null) {
            return this.buffer.readString(j7, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer buffer, long j7) throws EOFException {
        try {
            require(j7);
            this.buffer.readFully(buffer, j7);
        } catch (EOFException e7) {
            buffer.writeAll(this.buffer);
            throw e7;
        }
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr, int i7, int i8) {
        long j7 = i8;
        Util.checkOffsetAndCount(bArr.length, i7, j7);
        Buffer buffer = this.buffer;
        if (buffer.size == 0 && this.source.read(buffer, 8192L) == -1) {
            return -1;
        }
        return this.buffer.read(bArr, i7, (int) Math.min(j7, this.buffer.size));
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString) {
        return indexOf(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString, long j7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jIndexOf = this.buffer.indexOf(byteString, j7);
            if (jIndexOf != -1) {
                return jIndexOf;
            }
            Buffer buffer = this.buffer;
            long j8 = buffer.size;
            if (this.source.read(buffer, 8192L) == -1) {
                return -1L;
            }
            j7 = Math.max(j7, (j8 - byteString.size()) + 1);
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        Buffer buffer = this.buffer;
        if (buffer.size == 0 && this.source.read(buffer, 8192L) == -1) {
            return -1;
        }
        return this.buffer.read(byteBuffer);
    }
}
