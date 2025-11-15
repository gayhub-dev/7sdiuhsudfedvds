package okhttp3.internal.http2;

import android.support.v7.widget.ActivityChooserView;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import okio.Buffer;
import okio.BufferedSink;

/* loaded from: classes.dex */
final class Http2Writer implements Closeable {
    private static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final boolean client;
    private boolean closed;
    private final Buffer hpackBuffer;
    public final Hpack.Writer hpackWriter;
    private int maxFrameSize;
    private final BufferedSink sink;

    public Http2Writer(BufferedSink bufferedSink, boolean z6) {
        this.sink = bufferedSink;
        this.client = z6;
        Buffer buffer = new Buffer();
        this.hpackBuffer = buffer;
        this.hpackWriter = new Hpack.Writer(buffer);
        this.maxFrameSize = 16384;
    }

    private void writeContinuationFrames(int i7, long j7) {
        while (j7 > 0) {
            int iMin = (int) Math.min(this.maxFrameSize, j7);
            long j8 = iMin;
            j7 -= j8;
            frameHeader(i7, iMin, (byte) 9, j7 == 0 ? (byte) 4 : (byte) 0);
            this.sink.write(this.hpackBuffer, j8);
        }
    }

    private static void writeMedium(BufferedSink bufferedSink, int i7) {
        bufferedSink.writeByte((i7 >>> 16) & 255);
        bufferedSink.writeByte((i7 >>> 8) & 255);
        bufferedSink.writeByte(i7 & 255);
    }

    public synchronized void applyAndAckSettings(Settings settings) {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.maxFrameSize = settings.getMaxFrameSize(this.maxFrameSize);
        if (settings.getHeaderTableSize() != -1) {
            this.hpackWriter.setHeaderTableSizeSetting(settings.getHeaderTableSize());
        }
        frameHeader(0, 0, (byte) 4, (byte) 1);
        this.sink.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.closed = true;
        this.sink.close();
    }

    public synchronized void connectionPreface() {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (this.client) {
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.fine(Util.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.hex()));
            }
            this.sink.write(Http2.CONNECTION_PREFACE.toByteArray());
            this.sink.flush();
        }
    }

    public synchronized void data(boolean z6, int i7, Buffer buffer, int i8) {
        if (this.closed) {
            throw new IOException("closed");
        }
        dataFrame(i7, z6 ? (byte) 1 : (byte) 0, buffer, i8);
    }

    public void dataFrame(int i7, byte b7, Buffer buffer, int i8) {
        frameHeader(i7, i8, (byte) 0, b7);
        if (i8 > 0) {
            this.sink.write(buffer, i8);
        }
    }

    public synchronized void flush() {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.sink.flush();
    }

    public void frameHeader(int i7, int i8, byte b7, byte b8) {
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINE)) {
            logger2.fine(Http2.frameLog(false, i7, i8, b7, b8));
        }
        int i9 = this.maxFrameSize;
        if (i8 > i9) {
            throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i9), Integer.valueOf(i8));
        }
        if ((Integer.MIN_VALUE & i7) != 0) {
            throw Http2.illegalArgument("reserved bit set: %s", Integer.valueOf(i7));
        }
        writeMedium(this.sink, i8);
        this.sink.writeByte(b7 & 255);
        this.sink.writeByte(b8 & 255);
        this.sink.writeInt(i7 & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public synchronized void goAway(int i7, ErrorCode errorCode, byte[] bArr) {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
        }
        frameHeader(0, bArr.length + 8, (byte) 7, (byte) 0);
        this.sink.writeInt(i7);
        this.sink.writeInt(errorCode.httpCode);
        if (bArr.length > 0) {
            this.sink.write(bArr);
        }
        this.sink.flush();
    }

    public synchronized void headers(int i7, List<Header> list) {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(false, i7, list);
    }

    public int maxDataLength() {
        return this.maxFrameSize;
    }

    public synchronized void ping(boolean z6, int i7, int i8) {
        if (this.closed) {
            throw new IOException("closed");
        }
        frameHeader(0, 8, (byte) 6, z6 ? (byte) 1 : (byte) 0);
        this.sink.writeInt(i7);
        this.sink.writeInt(i8);
        this.sink.flush();
    }

    public synchronized void pushPromise(int i7, int i8, List<Header> list) {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(list);
        long size = this.hpackBuffer.size();
        int iMin = (int) Math.min(this.maxFrameSize - 4, size);
        long j7 = iMin;
        frameHeader(i7, iMin + 4, (byte) 5, size == j7 ? (byte) 4 : (byte) 0);
        this.sink.writeInt(i8 & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        this.sink.write(this.hpackBuffer, j7);
        if (size > j7) {
            writeContinuationFrames(i7, size - j7);
        }
    }

    public synchronized void rstStream(int i7, ErrorCode errorCode) {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        }
        frameHeader(i7, 4, (byte) 3, (byte) 0);
        this.sink.writeInt(errorCode.httpCode);
        this.sink.flush();
    }

    public synchronized void settings(Settings settings) {
        if (this.closed) {
            throw new IOException("closed");
        }
        int i7 = 0;
        frameHeader(0, settings.size() * 6, (byte) 4, (byte) 0);
        while (i7 < 10) {
            if (settings.isSet(i7)) {
                this.sink.writeShort(i7 == 4 ? 3 : i7 == 7 ? 4 : i7);
                this.sink.writeInt(settings.get(i7));
            }
            i7++;
        }
        this.sink.flush();
    }

    public synchronized void synReply(boolean z6, int i7, List<Header> list) {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(z6, i7, list);
    }

    public synchronized void synStream(boolean z6, int i7, int i8, List<Header> list) {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(z6, i7, list);
    }

    public synchronized void windowUpdate(int i7, long j7) {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (j7 == 0 || j7 > 2147483647L) {
            throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j7));
        }
        frameHeader(i7, 4, (byte) 8, (byte) 0);
        this.sink.writeInt((int) j7);
        this.sink.flush();
    }

    public void headers(boolean z6, int i7, List<Header> list) throws IOException {
        if (!this.closed) {
            this.hpackWriter.writeHeaders(list);
            long size = this.hpackBuffer.size();
            int iMin = (int) Math.min(this.maxFrameSize, size);
            long j7 = iMin;
            byte b7 = size == j7 ? (byte) 4 : (byte) 0;
            if (z6) {
                b7 = (byte) (b7 | 1);
            }
            frameHeader(i7, iMin, (byte) 1, b7);
            this.sink.write(this.hpackBuffer, j7);
            if (size > j7) {
                writeContinuationFrames(i7, size - j7);
                return;
            }
            return;
        }
        throw new IOException("closed");
    }
}
