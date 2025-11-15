package okhttp3.internal.http2;

import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Header;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* loaded from: classes.dex */
public final class Http2Stream {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public long bytesLeftInWriteWindow;
    public final Http2Connection connection;
    public ErrorCode errorCode;
    private boolean hasResponseHeaders;
    private Header.Listener headersListener;
    private final Deque<Headers> headersQueue;

    /* renamed from: id */
    public final int f4850id;
    public final StreamTimeout readTimeout;
    public final FramingSink sink;
    private final FramingSource source;
    public long unacknowledgedBytesRead = 0;
    public final StreamTimeout writeTimeout;

    public final class FramingSink implements Sink {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long EMIT_BUFFER_SIZE = 16384;
        public boolean closed;
        public boolean finished;
        private final Buffer sendBuffer = new Buffer();

        public FramingSink() {
        }

        private void emitFrame(boolean z6) throws IOException {
            Http2Stream http2Stream;
            long jMin;
            Http2Stream http2Stream2;
            synchronized (Http2Stream.this) {
                Http2Stream.this.writeTimeout.enter();
                while (true) {
                    try {
                        http2Stream = Http2Stream.this;
                        if (http2Stream.bytesLeftInWriteWindow > 0 || this.finished || this.closed || http2Stream.errorCode != null) {
                            break;
                        } else {
                            http2Stream.waitForIo();
                        }
                    } finally {
                    }
                }
                http2Stream.writeTimeout.exitAndThrowIfTimedOut();
                Http2Stream.this.checkOutNotClosed();
                jMin = Math.min(Http2Stream.this.bytesLeftInWriteWindow, this.sendBuffer.size());
                http2Stream2 = Http2Stream.this;
                http2Stream2.bytesLeftInWriteWindow -= jMin;
            }
            http2Stream2.writeTimeout.enter();
            try {
                Http2Stream http2Stream3 = Http2Stream.this;
                http2Stream3.connection.writeData(http2Stream3.f4850id, z6 && jMin == this.sendBuffer.size(), this.sendBuffer, jMin);
            } finally {
            }
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Http2Stream.this) {
                if (this.closed) {
                    return;
                }
                if (!Http2Stream.this.sink.finished) {
                    if (this.sendBuffer.size() > 0) {
                        while (this.sendBuffer.size() > 0) {
                            emitFrame(true);
                        }
                    } else {
                        Http2Stream http2Stream = Http2Stream.this;
                        http2Stream.connection.writeData(http2Stream.f4850id, true, null, 0L);
                    }
                }
                synchronized (Http2Stream.this) {
                    this.closed = true;
                }
                Http2Stream.this.connection.flush();
                Http2Stream.this.cancelStreamIfNecessary();
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (Http2Stream.this) {
                Http2Stream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.size() > 0) {
                emitFrame(false);
                Http2Stream.this.connection.flush();
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return Http2Stream.this.writeTimeout;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j7) throws IOException {
            this.sendBuffer.write(buffer, j7);
            while (this.sendBuffer.size() >= 16384) {
                emitFrame(false);
            }
        }
    }

    public final class FramingSource implements Source {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public boolean closed;
        public boolean finished;
        private final long maxByteCount;
        private final Buffer receiveBuffer = new Buffer();
        private final Buffer readBuffer = new Buffer();

        public FramingSource(long j7) {
            this.maxByteCount = j7;
        }

        private void updateConnectionFlowControl(long j7) {
            Http2Stream.this.connection.updateConnectionFlowControl(j7);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            long size;
            Header.Listener listener;
            ArrayList arrayList;
            synchronized (Http2Stream.this) {
                this.closed = true;
                size = this.readBuffer.size();
                this.readBuffer.clear();
                listener = null;
                if (Http2Stream.this.headersQueue.isEmpty() || Http2Stream.this.headersListener == null) {
                    arrayList = null;
                } else {
                    ArrayList arrayList2 = new ArrayList(Http2Stream.this.headersQueue);
                    Http2Stream.this.headersQueue.clear();
                    listener = Http2Stream.this.headersListener;
                    arrayList = arrayList2;
                }
                Http2Stream.this.notifyAll();
            }
            if (size > 0) {
                updateConnectionFlowControl(size);
            }
            Http2Stream.this.cancelStreamIfNecessary();
            if (listener != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    listener.onHeaders((Headers) it.next());
                }
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j7) throws StreamResetException {
            ErrorCode errorCode;
            long j8;
            Headers headers;
            Header.Listener listener;
            Header.Listener listener2;
            if (j7 < 0) {
                throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
            }
            while (true) {
                synchronized (Http2Stream.this) {
                    Http2Stream.this.readTimeout.enter();
                    try {
                        Http2Stream http2Stream = Http2Stream.this;
                        errorCode = http2Stream.errorCode;
                        if (errorCode == null) {
                            errorCode = null;
                        }
                        if (!this.closed) {
                            if (!http2Stream.headersQueue.isEmpty() && Http2Stream.this.headersListener != null) {
                                headers = (Headers) Http2Stream.this.headersQueue.removeFirst();
                                listener2 = Http2Stream.this.headersListener;
                            } else if (this.readBuffer.size() > 0) {
                                Buffer buffer2 = this.readBuffer;
                                j8 = buffer2.read(buffer, Math.min(j7, buffer2.size()));
                                Http2Stream http2Stream2 = Http2Stream.this;
                                long j9 = http2Stream2.unacknowledgedBytesRead + j8;
                                http2Stream2.unacknowledgedBytesRead = j9;
                                if (errorCode == null && j9 >= http2Stream2.connection.okHttpSettings.getInitialWindowSize() / 2) {
                                    Http2Stream http2Stream3 = Http2Stream.this;
                                    http2Stream3.connection.writeWindowUpdateLater(http2Stream3.f4850id, http2Stream3.unacknowledgedBytesRead);
                                    Http2Stream.this.unacknowledgedBytesRead = 0L;
                                }
                                headers = null;
                                listener = null;
                                if (headers == null || listener == null) {
                                    break;
                                }
                                listener.onHeaders(headers);
                            } else if (this.finished || errorCode != null) {
                                headers = null;
                                listener2 = null;
                            } else {
                                Http2Stream.this.waitForIo();
                                Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                            }
                            listener = listener2;
                            j8 = -1;
                            if (headers == null) {
                                break;
                            }
                            break;
                            break;
                        }
                        throw new IOException("stream closed");
                    } finally {
                    }
                }
            }
            if (j8 != -1) {
                updateConnectionFlowControl(j8);
                return j8;
            }
            if (errorCode == null) {
                return -1L;
            }
            throw new StreamResetException(errorCode);
        }

        public void receive(BufferedSource bufferedSource, long j7) throws EOFException {
            boolean z6;
            boolean z7;
            boolean z8;
            while (j7 > 0) {
                synchronized (Http2Stream.this) {
                    z6 = this.finished;
                    z7 = true;
                    z8 = this.readBuffer.size() + j7 > this.maxByteCount;
                }
                if (z8) {
                    bufferedSource.skip(j7);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z6) {
                    bufferedSource.skip(j7);
                    return;
                }
                long j8 = bufferedSource.read(this.receiveBuffer, j7);
                if (j8 == -1) {
                    throw new EOFException();
                }
                j7 -= j8;
                synchronized (Http2Stream.this) {
                    if (this.readBuffer.size() != 0) {
                        z7 = false;
                    }
                    this.readBuffer.writeAll(this.receiveBuffer);
                    if (z7) {
                        Http2Stream.this.notifyAll();
                    }
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return Http2Stream.this.readTimeout;
        }
    }

    public class StreamTimeout extends AsyncTimeout {
        public StreamTimeout() {
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }

        @Override // okio.AsyncTimeout
        public IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // okio.AsyncTimeout
        public void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
        }
    }

    public Http2Stream(int i7, Http2Connection http2Connection, boolean z6, boolean z7, @Nullable Headers headers) {
        ArrayDeque arrayDeque = new ArrayDeque();
        this.headersQueue = arrayDeque;
        this.readTimeout = new StreamTimeout();
        this.writeTimeout = new StreamTimeout();
        this.errorCode = null;
        Objects.requireNonNull(http2Connection, "connection == null");
        this.f4850id = i7;
        this.connection = http2Connection;
        this.bytesLeftInWriteWindow = http2Connection.peerSettings.getInitialWindowSize();
        FramingSource framingSource = new FramingSource(http2Connection.okHttpSettings.getInitialWindowSize());
        this.source = framingSource;
        FramingSink framingSink = new FramingSink();
        this.sink = framingSink;
        framingSource.finished = z7;
        framingSink.finished = z6;
        if (headers != null) {
            arrayDeque.add(headers);
        }
        if (isLocallyInitiated() && headers != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (!isLocallyInitiated() && headers == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    private boolean closeInternal(ErrorCode errorCode) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode;
            notifyAll();
            this.connection.removeStream(this.f4850id);
            return true;
        }
    }

    public void addBytesToWriteWindow(long j7) {
        this.bytesLeftInWriteWindow += j7;
        if (j7 > 0) {
            notifyAll();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void cancelStreamIfNecessary() {
        /*
            r2 = this;
            monitor-enter(r2)
            okhttp3.internal.http2.Http2Stream$FramingSource r0 = r2.source     // Catch: java.lang.Throwable -> L2f
            boolean r1 = r0.finished     // Catch: java.lang.Throwable -> L2f
            if (r1 != 0) goto L17
            boolean r0 = r0.closed     // Catch: java.lang.Throwable -> L2f
            if (r0 == 0) goto L17
            okhttp3.internal.http2.Http2Stream$FramingSink r0 = r2.sink     // Catch: java.lang.Throwable -> L2f
            boolean r1 = r0.finished     // Catch: java.lang.Throwable -> L2f
            if (r1 != 0) goto L15
            boolean r0 = r0.closed     // Catch: java.lang.Throwable -> L2f
            if (r0 == 0) goto L17
        L15:
            r0 = 1
            goto L18
        L17:
            r0 = 0
        L18:
            boolean r1 = r2.isOpen()     // Catch: java.lang.Throwable -> L2f
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2f
            if (r0 == 0) goto L25
            okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.CANCEL
            r2.close(r0)
            goto L2e
        L25:
            if (r1 != 0) goto L2e
            okhttp3.internal.http2.Http2Connection r0 = r2.connection
            int r1 = r2.f4850id
            r0.removeStream(r1)
        L2e:
            return
        L2f:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2f
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.cancelStreamIfNecessary():void");
    }

    public void checkOutNotClosed() throws IOException {
        FramingSink framingSink = this.sink;
        if (framingSink.closed) {
            throw new IOException("stream closed");
        }
        if (framingSink.finished) {
            throw new IOException("stream finished");
        }
        if (this.errorCode != null) {
            throw new StreamResetException(this.errorCode);
        }
    }

    public void close(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynReset(this.f4850id, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynResetLater(this.f4850id, errorCode);
        }
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public int getId() {
        return this.f4850id;
    }

    public Sink getSink() {
        synchronized (this) {
            if (!this.hasResponseHeaders && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public Source getSource() {
        return this.source;
    }

    public boolean isLocallyInitiated() {
        return this.connection.client == ((this.f4850id & 1) == 1);
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        FramingSource framingSource = this.source;
        if (framingSource.finished || framingSource.closed) {
            FramingSink framingSink = this.sink;
            if (framingSink.finished || framingSink.closed) {
                if (this.hasResponseHeaders) {
                    return false;
                }
            }
        }
        return true;
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public void receiveData(BufferedSource bufferedSource, int i7) throws EOFException {
        this.source.receive(bufferedSource, i7);
    }

    public void receiveFin() {
        boolean zIsOpen;
        synchronized (this) {
            this.source.finished = true;
            zIsOpen = isOpen();
            notifyAll();
        }
        if (zIsOpen) {
            return;
        }
        this.connection.removeStream(this.f4850id);
    }

    public void receiveHeaders(List<Header> list) {
        boolean zIsOpen;
        synchronized (this) {
            this.hasResponseHeaders = true;
            this.headersQueue.add(Util.toHeaders(list));
            zIsOpen = isOpen();
            notifyAll();
        }
        if (zIsOpen) {
            return;
        }
        this.connection.removeStream(this.f4850id);
    }

    public synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    public synchronized void setHeadersListener(Header.Listener listener) {
        this.headersListener = listener;
        if (!this.headersQueue.isEmpty() && listener != null) {
            notifyAll();
        }
    }

    public synchronized Headers takeHeaders() {
        this.readTimeout.enter();
        while (this.headersQueue.isEmpty() && this.errorCode == null) {
            try {
                waitForIo();
            } catch (Throwable th) {
                this.readTimeout.exitAndThrowIfTimedOut();
                throw th;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (this.headersQueue.isEmpty()) {
            throw new StreamResetException(this.errorCode);
        }
        return this.headersQueue.removeFirst();
    }

    public void waitForIo() throws InterruptedException, InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public void writeHeaders(List<Header> list, boolean z6) {
        boolean z7;
        boolean z8;
        boolean z9;
        Objects.requireNonNull(list, "headers == null");
        synchronized (this) {
            z7 = true;
            this.hasResponseHeaders = true;
            if (z6) {
                z8 = false;
                z9 = false;
            } else {
                this.sink.finished = true;
                z8 = true;
                z9 = true;
            }
        }
        if (!z8) {
            synchronized (this.connection) {
                if (this.connection.bytesLeftInWriteWindow != 0) {
                    z7 = false;
                }
            }
            z8 = z7;
        }
        this.connection.writeSynReply(this.f4850id, z9, list);
        if (z8) {
            this.connection.flush();
        }
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }
}
