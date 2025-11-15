package okhttp3.internal.p124ws;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

/* loaded from: classes.dex */
final class WebSocketWriter {
    public boolean activeWriter;
    public final Buffer buffer = new Buffer();
    public final FrameSink frameSink = new FrameSink();
    public final boolean isClient;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    public final Random random;
    public final BufferedSink sink;
    public final Buffer sinkBuffer;
    public boolean writerClosed;

    public final class FrameSink implements Sink {
        public boolean closed;
        public long contentLength;
        public int formatOpcode;
        public boolean isFirstFrame;

        public FrameSink() {
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter webSocketWriter = WebSocketWriter.this;
            webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size(), this.isFirstFrame, true);
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter webSocketWriter = WebSocketWriter.this;
            webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size(), this.isFirstFrame, false);
            this.isFirstFrame = false;
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j7) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(buffer, j7);
            boolean z6 = this.isFirstFrame && this.contentLength != -1 && WebSocketWriter.this.buffer.size() > this.contentLength - 8192;
            long jCompleteSegmentByteCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
            if (jCompleteSegmentByteCount <= 0 || z6) {
                return;
            }
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, jCompleteSegmentByteCount, this.isFirstFrame, false);
            this.isFirstFrame = false;
        }
    }

    public WebSocketWriter(boolean z6, BufferedSink bufferedSink, Random random) {
        Objects.requireNonNull(bufferedSink, "sink == null");
        Objects.requireNonNull(random, "random == null");
        this.isClient = z6;
        this.sink = bufferedSink;
        this.sinkBuffer = bufferedSink.buffer();
        this.random = random;
        this.maskKey = z6 ? new byte[4] : null;
        this.maskCursor = z6 ? new Buffer.UnsafeCursor() : null;
    }

    private void writeControlFrame(int i7, ByteString byteString) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int size = byteString.size();
        if (size > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.sinkBuffer.writeByte(i7 | 128);
        if (this.isClient) {
            this.sinkBuffer.writeByte(size | 128);
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (size > 0) {
                long size2 = this.sinkBuffer.size();
                this.sinkBuffer.write(byteString);
                this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(size2);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.writeByte(size);
            this.sinkBuffer.write(byteString);
        }
        this.sink.flush();
    }

    public Sink newMessageSink(int i7, long j7) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        FrameSink frameSink = this.frameSink;
        frameSink.formatOpcode = i7;
        frameSink.contentLength = j7;
        frameSink.isFirstFrame = true;
        frameSink.closed = false;
        return frameSink;
    }

    public void writeClose(int i7, ByteString byteString) {
        ByteString byteString2 = ByteString.EMPTY;
        if (i7 != 0 || byteString != null) {
            if (i7 != 0) {
                WebSocketProtocol.validateCloseCode(i7);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(i7);
            if (byteString != null) {
                buffer.write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        try {
            writeControlFrame(8, byteString2);
        } finally {
            this.writerClosed = true;
        }
    }

    public void writeMessageFrame(int i7, long j7, boolean z6, boolean z7) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        if (!z6) {
            i7 = 0;
        }
        if (z7) {
            i7 |= 128;
        }
        this.sinkBuffer.writeByte(i7);
        int i8 = this.isClient ? 128 : 0;
        if (j7 <= 125) {
            this.sinkBuffer.writeByte(((int) j7) | i8);
        } else if (j7 <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            this.sinkBuffer.writeByte(i8 | 126);
            this.sinkBuffer.writeShort((int) j7);
        } else {
            this.sinkBuffer.writeByte(i8 | 127);
            this.sinkBuffer.writeLong(j7);
        }
        if (this.isClient) {
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (j7 > 0) {
                long size = this.sinkBuffer.size();
                this.sinkBuffer.write(this.buffer, j7);
                this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(size);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.write(this.buffer, j7);
        }
        this.sink.emit();
    }

    public void writePing(ByteString byteString) throws IOException {
        writeControlFrame(9, byteString);
    }

    public void writePong(ByteString byteString) throws IOException {
        writeControlFrame(10, byteString);
    }
}
