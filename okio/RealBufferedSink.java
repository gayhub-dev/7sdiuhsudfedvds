package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
import p009b.C0413b;

/* loaded from: classes.dex */
final class RealBufferedSink implements BufferedSink {
    public final Buffer buffer = new Buffer();
    public boolean closed;
    public final Sink sink;

    public RealBufferedSink(Sink sink) {
        Objects.requireNonNull(sink, "sink == null");
        this.sink = sink;
    }

    @Override // okio.BufferedSink
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.closed) {
            return;
        }
        Throwable th = null;
        try {
            Buffer buffer = this.buffer;
            long j7 = buffer.size;
            if (j7 > 0) {
                this.sink.write(buffer, j7);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.sink.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.closed = true;
        if (th != null) {
            Util.sneakyRethrow(th);
        }
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long size = this.buffer.size();
        if (size > 0) {
            this.sink.write(this.buffer, size);
        }
        return this;
    }

    @Override // okio.BufferedSink
    public BufferedSink emitCompleteSegments() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long jCompleteSegmentByteCount = this.buffer.completeSegmentByteCount();
        if (jCompleteSegmentByteCount > 0) {
            this.sink.write(this.buffer, jCompleteSegmentByteCount);
        }
        return this;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Buffer buffer = this.buffer;
        long j7 = buffer.size;
        if (j7 > 0) {
            this.sink.write(buffer, j7);
        }
        this.sink.flush();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.RealBufferedSink.1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws Throwable {
                RealBufferedSink.this.close();
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (realBufferedSink.closed) {
                    return;
                }
                realBufferedSink.flush();
            }

            public String toString() {
                return RealBufferedSink.this + ".outputStream()";
            }

            @Override // java.io.OutputStream
            public void write(int i7) throws IOException {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (realBufferedSink.closed) {
                    throw new IOException("closed");
                }
                realBufferedSink.buffer.writeByte((int) ((byte) i7));
                RealBufferedSink.this.emitCompleteSegments();
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i7, int i8) throws IOException {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (!realBufferedSink.closed) {
                    realBufferedSink.buffer.write(bArr, i7, i8);
                    RealBufferedSink.this.emitCompleteSegments();
                    return;
                }
                throw new IOException("closed");
            }
        };
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("buffer(");
        sbM112a.append(this.sink);
        sbM112a.append(")");
        return sbM112a.toString();
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(buffer, j7);
        emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j7 = 0;
        while (true) {
            long j8 = source.read(this.buffer, 8192L);
            if (j8 == -1) {
                return j7;
            }
            j7 += j8;
            emitCompleteSegments();
        }
    }

    @Override // okio.BufferedSink
    public BufferedSink writeByte(int i7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeByte(i7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeDecimalLong(long j7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeDecimalLong(j7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeHexadecimalUnsignedLong(long j7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeHexadecimalUnsignedLong(j7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeInt(int i7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeInt(i7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeIntLe(int i7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeIntLe(i7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeLong(long j7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeLong(j7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeLongLe(long j7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeLongLe(j7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeShort(int i7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeShort(i7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeShortLe(int i7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeShortLe(i7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeString(String str, Charset charset) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeString(str, charset);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String str) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeUtf8(str);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8CodePoint(int i7) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeUtf8CodePoint(i7);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink write(ByteString byteString) {
        if (!this.closed) {
            this.buffer.write(byteString);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeString(String str, int i7, int i8, Charset charset) {
        if (!this.closed) {
            this.buffer.writeString(str, i7, i8, charset);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String str, int i7, int i8) {
        if (!this.closed) {
            this.buffer.writeUtf8(str, i7, i8);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] bArr) {
        if (!this.closed) {
            this.buffer.write(bArr);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] bArr, int i7, int i8) {
        if (!this.closed) {
            this.buffer.write(bArr, i7, i8);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (!this.closed) {
            int iWrite = this.buffer.write(byteBuffer);
            emitCompleteSegments();
            return iWrite;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink write(Source source, long j7) throws EOFException {
        while (j7 > 0) {
            long j8 = source.read(this.buffer, j7);
            if (j8 != -1) {
                j7 -= j8;
                emitCompleteSegments();
            } else {
                throw new EOFException();
            }
        }
        return this;
    }
}
