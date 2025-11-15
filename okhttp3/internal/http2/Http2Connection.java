package okhttp3.internal.http2;

import android.support.v7.widget.ActivityChooserView;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Http2Reader;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class Http2Connection implements Closeable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    private static final ExecutorService listenerExecutor = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Http2Connection", true));
    private boolean awaitingPong;
    public long bytesLeftInWriteWindow;
    public final boolean client;
    public final Set<Integer> currentPushRequests;
    public final String hostname;
    public int lastGoodStreamId;
    public final Listener listener;
    public int nextStreamId;
    public final Settings peerSettings;
    private final ExecutorService pushExecutor;
    public final PushObserver pushObserver;
    public final ReaderRunnable readerRunnable;
    public boolean receivedInitialPeerSettings;
    public boolean shutdown;
    public final Socket socket;
    public final Http2Writer writer;
    private final ScheduledExecutorService writerExecutor;
    public final Map<Integer, Http2Stream> streams = new LinkedHashMap();
    public long unacknowledgedBytesRead = 0;
    public Settings okHttpSettings = new Settings();

    public static abstract class Listener {
        public static final Listener REFUSE_INCOMING_STREAMS = new Listener() { // from class: okhttp3.internal.http2.Http2Connection.Listener.1
            @Override // okhttp3.internal.http2.Http2Connection.Listener
            public void onStream(Http2Stream http2Stream) {
                http2Stream.close(ErrorCode.REFUSED_STREAM);
            }
        };

        public void onSettings(Http2Connection http2Connection) {
        }

        public abstract void onStream(Http2Stream http2Stream);
    }

    public final class PingRunnable extends NamedRunnable {
        public final int payload1;
        public final int payload2;
        public final boolean reply;

        public PingRunnable(boolean z6, int i7, int i8) {
            super("OkHttp %s ping %08x%08x", Http2Connection.this.hostname, Integer.valueOf(i7), Integer.valueOf(i8));
            this.reply = z6;
            this.payload1 = i7;
            this.payload2 = i8;
        }

        @Override // okhttp3.internal.NamedRunnable
        public void execute() {
            Http2Connection.this.writePing(this.reply, this.payload1, this.payload2);
        }
    }

    public class ReaderRunnable extends NamedRunnable implements Http2Reader.Handler {
        public final Http2Reader reader;

        public ReaderRunnable(Http2Reader http2Reader) {
            super("OkHttp %s", Http2Connection.this.hostname);
            this.reader = http2Reader;
        }

        private void applyAndAckSettings(final Settings settings) {
            try {
                Http2Connection.this.writerExecutor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[]{Http2Connection.this.hostname}) { // from class: okhttp3.internal.http2.Http2Connection.ReaderRunnable.3
                    @Override // okhttp3.internal.NamedRunnable
                    public void execute() {
                        try {
                            Http2Connection.this.writer.applyAndAckSettings(settings);
                        } catch (IOException unused) {
                            Http2Connection.this.failConnection();
                        }
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ackSettings() {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void alternateService(int i7, String str, ByteString byteString, String str2, int i8, long j7) {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void data(boolean z6, int i7, BufferedSource bufferedSource, int i8) throws IOException {
            if (Http2Connection.this.pushedStream(i7)) {
                Http2Connection.this.pushDataLater(i7, bufferedSource, i8, z6);
                return;
            }
            Http2Stream stream = Http2Connection.this.getStream(i7);
            if (stream == null) {
                Http2Connection.this.writeSynResetLater(i7, ErrorCode.PROTOCOL_ERROR);
                long j7 = i8;
                Http2Connection.this.updateConnectionFlowControl(j7);
                bufferedSource.skip(j7);
                return;
            }
            stream.receiveData(bufferedSource, i8);
            if (z6) {
                stream.receiveFin();
            }
        }

        @Override // okhttp3.internal.NamedRunnable
        public void execute() throws Throwable {
            ErrorCode errorCode;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            try {
                try {
                    this.reader.readConnectionPreface(this);
                    while (this.reader.nextFrame(false, this)) {
                    }
                    errorCode = ErrorCode.NO_ERROR;
                } catch (IOException unused) {
                    errorCode = errorCode2;
                } catch (Throwable th) {
                    th = th;
                    errorCode = errorCode2;
                    try {
                        Http2Connection.this.close(errorCode, errorCode2);
                    } catch (IOException unused2) {
                    }
                    Util.closeQuietly(this.reader);
                    throw th;
                }
                try {
                    try {
                        Http2Connection.this.close(errorCode, ErrorCode.CANCEL);
                    } catch (Throwable th2) {
                        th = th2;
                        Http2Connection.this.close(errorCode, errorCode2);
                        Util.closeQuietly(this.reader);
                        throw th;
                    }
                } catch (IOException unused3) {
                    ErrorCode errorCode3 = ErrorCode.PROTOCOL_ERROR;
                    Http2Connection.this.close(errorCode3, errorCode3);
                    Util.closeQuietly(this.reader);
                }
            } catch (IOException unused4) {
            }
            Util.closeQuietly(this.reader);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void goAway(int i7, ErrorCode errorCode, ByteString byteString) {
            Http2Stream[] http2StreamArr;
            byteString.size();
            synchronized (Http2Connection.this) {
                http2StreamArr = (Http2Stream[]) Http2Connection.this.streams.values().toArray(new Http2Stream[Http2Connection.this.streams.size()]);
                Http2Connection.this.shutdown = true;
            }
            for (Http2Stream http2Stream : http2StreamArr) {
                if (http2Stream.getId() > i7 && http2Stream.isLocallyInitiated()) {
                    http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    Http2Connection.this.removeStream(http2Stream.getId());
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void headers(boolean z6, int i7, int i8, List<Header> list) {
            if (Http2Connection.this.pushedStream(i7)) {
                Http2Connection.this.pushHeadersLater(i7, list, z6);
                return;
            }
            synchronized (Http2Connection.this) {
                Http2Stream stream = Http2Connection.this.getStream(i7);
                if (stream != null) {
                    stream.receiveHeaders(list);
                    if (z6) {
                        stream.receiveFin();
                        return;
                    }
                    return;
                }
                Http2Connection http2Connection = Http2Connection.this;
                if (http2Connection.shutdown) {
                    return;
                }
                if (i7 <= http2Connection.lastGoodStreamId) {
                    return;
                }
                if (i7 % 2 == http2Connection.nextStreamId % 2) {
                    return;
                }
                final Http2Stream http2Stream = new Http2Stream(i7, Http2Connection.this, false, z6, Util.toHeaders(list));
                Http2Connection http2Connection2 = Http2Connection.this;
                http2Connection2.lastGoodStreamId = i7;
                http2Connection2.streams.put(Integer.valueOf(i7), http2Stream);
                Http2Connection.listenerExecutor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{Http2Connection.this.hostname, Integer.valueOf(i7)}) { // from class: okhttp3.internal.http2.Http2Connection.ReaderRunnable.1
                    @Override // okhttp3.internal.NamedRunnable
                    public void execute() {
                        try {
                            Http2Connection.this.listener.onStream(http2Stream);
                        } catch (IOException e7) {
                            Platform platform = Platform.get();
                            StringBuilder sbM112a = C0413b.m112a("Http2Connection.Listener failure for ");
                            sbM112a.append(Http2Connection.this.hostname);
                            platform.log(4, sbM112a.toString(), e7);
                            try {
                                http2Stream.close(ErrorCode.PROTOCOL_ERROR);
                            } catch (IOException unused) {
                            }
                        }
                    }
                });
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ping(boolean z6, int i7, int i8) {
            if (!z6) {
                try {
                    Http2Connection.this.writerExecutor.execute(Http2Connection.this.new PingRunnable(true, i7, i8));
                } catch (RejectedExecutionException unused) {
                }
            } else {
                synchronized (Http2Connection.this) {
                    Http2Connection.this.awaitingPong = false;
                    Http2Connection.this.notifyAll();
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void priority(int i7, int i8, int i9, boolean z6) {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void pushPromise(int i7, int i8, List<Header> list) {
            Http2Connection.this.pushRequestLater(i8, list);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void rstStream(int i7, ErrorCode errorCode) {
            if (Http2Connection.this.pushedStream(i7)) {
                Http2Connection.this.pushResetLater(i7, errorCode);
                return;
            }
            Http2Stream http2StreamRemoveStream = Http2Connection.this.removeStream(i7);
            if (http2StreamRemoveStream != null) {
                http2StreamRemoveStream.receiveRstStream(errorCode);
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void settings(boolean z6, Settings settings) {
            Http2Stream[] http2StreamArr;
            long j7;
            int i7;
            synchronized (Http2Connection.this) {
                int initialWindowSize = Http2Connection.this.peerSettings.getInitialWindowSize();
                if (z6) {
                    Http2Connection.this.peerSettings.clear();
                }
                Http2Connection.this.peerSettings.merge(settings);
                applyAndAckSettings(settings);
                int initialWindowSize2 = Http2Connection.this.peerSettings.getInitialWindowSize();
                http2StreamArr = null;
                if (initialWindowSize2 == -1 || initialWindowSize2 == initialWindowSize) {
                    j7 = 0;
                } else {
                    j7 = initialWindowSize2 - initialWindowSize;
                    Http2Connection http2Connection = Http2Connection.this;
                    if (!http2Connection.receivedInitialPeerSettings) {
                        http2Connection.receivedInitialPeerSettings = true;
                    }
                    if (!http2Connection.streams.isEmpty()) {
                        http2StreamArr = (Http2Stream[]) Http2Connection.this.streams.values().toArray(new Http2Stream[Http2Connection.this.streams.size()]);
                    }
                }
                Http2Connection.listenerExecutor.execute(new NamedRunnable("OkHttp %s settings", Http2Connection.this.hostname) { // from class: okhttp3.internal.http2.Http2Connection.ReaderRunnable.2
                    @Override // okhttp3.internal.NamedRunnable
                    public void execute() {
                        Http2Connection http2Connection2 = Http2Connection.this;
                        http2Connection2.listener.onSettings(http2Connection2);
                    }
                });
            }
            if (http2StreamArr == null || j7 == 0) {
                return;
            }
            for (Http2Stream http2Stream : http2StreamArr) {
                synchronized (http2Stream) {
                    http2Stream.addBytesToWriteWindow(j7);
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void windowUpdate(int i7, long j7) {
            if (i7 == 0) {
                synchronized (Http2Connection.this) {
                    Http2Connection http2Connection = Http2Connection.this;
                    http2Connection.bytesLeftInWriteWindow += j7;
                    http2Connection.notifyAll();
                }
                return;
            }
            Http2Stream stream = Http2Connection.this.getStream(i7);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j7);
                }
            }
        }
    }

    public Http2Connection(Builder builder) {
        Settings settings = new Settings();
        this.peerSettings = settings;
        this.receivedInitialPeerSettings = false;
        this.currentPushRequests = new LinkedHashSet();
        this.pushObserver = builder.pushObserver;
        boolean z6 = builder.client;
        this.client = z6;
        this.listener = builder.listener;
        int i7 = z6 ? 1 : 2;
        this.nextStreamId = i7;
        if (z6) {
            this.nextStreamId = i7 + 2;
        }
        if (z6) {
            this.okHttpSettings.set(7, 16777216);
        }
        String str = builder.hostname;
        this.hostname = str;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(Util.format("OkHttp %s Writer", str), false));
        this.writerExecutor = scheduledThreadPoolExecutor;
        if (builder.pingIntervalMillis != 0) {
            PingRunnable pingRunnable = new PingRunnable(false, 0, 0);
            int i8 = builder.pingIntervalMillis;
            scheduledThreadPoolExecutor.scheduleAtFixedRate(pingRunnable, i8, i8, TimeUnit.MILLISECONDS);
        }
        this.pushExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(Util.format("OkHttp %s Push Observer", str), true));
        settings.set(7, 65535);
        settings.set(5, 16384);
        this.bytesLeftInWriteWindow = settings.getInitialWindowSize();
        this.socket = builder.socket;
        this.writer = new Http2Writer(builder.sink, z6);
        this.readerRunnable = new ReaderRunnable(new Http2Reader(builder.source, z6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void failConnection() {
        try {
            ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
            close(errorCode, errorCode);
        } catch (IOException unused) {
        }
    }

    private synchronized void pushExecutorExecute(NamedRunnable namedRunnable) {
        if (!isShutdown()) {
            this.pushExecutor.execute(namedRunnable);
        }
    }

    public synchronized void awaitPong() {
        while (this.awaitingPong) {
            wait();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    public void flush() {
        this.writer.flush();
    }

    public Protocol getProtocol() {
        return Protocol.HTTP_2;
    }

    public synchronized Http2Stream getStream(int i7) {
        return this.streams.get(Integer.valueOf(i7));
    }

    public synchronized boolean isShutdown() {
        return this.shutdown;
    }

    public synchronized int maxConcurrentStreams() {
        return this.peerSettings.getMaxConcurrentStreams(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public Http2Stream newStream(List<Header> list, boolean z6) {
        return newStream(0, list, z6);
    }

    public synchronized int openStreamCount() {
        return this.streams.size();
    }

    public void pushDataLater(final int i7, BufferedSource bufferedSource, final int i8, final boolean z6) throws IOException {
        final Buffer buffer = new Buffer();
        long j7 = i8;
        bufferedSource.require(j7);
        bufferedSource.read(buffer, j7);
        if (buffer.size() == j7) {
            pushExecutorExecute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[]{this.hostname, Integer.valueOf(i7)}) { // from class: okhttp3.internal.http2.Http2Connection.5
                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    try {
                        boolean zOnData = Http2Connection.this.pushObserver.onData(i7, buffer, i8, z6);
                        if (zOnData) {
                            Http2Connection.this.writer.rstStream(i7, ErrorCode.CANCEL);
                        }
                        if (zOnData || z6) {
                            synchronized (Http2Connection.this) {
                                Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i7));
                            }
                        }
                    } catch (IOException unused) {
                    }
                }
            });
            return;
        }
        throw new IOException(buffer.size() + " != " + i8);
    }

    public void pushHeadersLater(final int i7, final List<Header> list, final boolean z6) {
        try {
            pushExecutorExecute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[]{this.hostname, Integer.valueOf(i7)}) { // from class: okhttp3.internal.http2.Http2Connection.4
                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    boolean zOnHeaders = Http2Connection.this.pushObserver.onHeaders(i7, list, z6);
                    if (zOnHeaders) {
                        try {
                            Http2Connection.this.writer.rstStream(i7, ErrorCode.CANCEL);
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    if (zOnHeaders || z6) {
                        synchronized (Http2Connection.this) {
                            Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i7));
                        }
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    public void pushRequestLater(final int i7, final List<Header> list) {
        synchronized (this) {
            if (this.currentPushRequests.contains(Integer.valueOf(i7))) {
                writeSynResetLater(i7, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(Integer.valueOf(i7));
            try {
                pushExecutorExecute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[]{this.hostname, Integer.valueOf(i7)}) { // from class: okhttp3.internal.http2.Http2Connection.3
                    @Override // okhttp3.internal.NamedRunnable
                    public void execute() {
                        if (Http2Connection.this.pushObserver.onRequest(i7, list)) {
                            try {
                                Http2Connection.this.writer.rstStream(i7, ErrorCode.CANCEL);
                                synchronized (Http2Connection.this) {
                                    Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i7));
                                }
                            } catch (IOException unused) {
                            }
                        }
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    public void pushResetLater(final int i7, final ErrorCode errorCode) {
        pushExecutorExecute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[]{this.hostname, Integer.valueOf(i7)}) { // from class: okhttp3.internal.http2.Http2Connection.6
            @Override // okhttp3.internal.NamedRunnable
            public void execute() {
                Http2Connection.this.pushObserver.onReset(i7, errorCode);
                synchronized (Http2Connection.this) {
                    Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i7));
                }
            }
        });
    }

    public Http2Stream pushStream(int i7, List<Header> list, boolean z6) {
        if (this.client) {
            throw new IllegalStateException("Client cannot push requests.");
        }
        return newStream(i7, list, z6);
    }

    public boolean pushedStream(int i7) {
        return i7 != 0 && (i7 & 1) == 0;
    }

    public synchronized Http2Stream removeStream(int i7) {
        Http2Stream http2StreamRemove;
        http2StreamRemove = this.streams.remove(Integer.valueOf(i7));
        notifyAll();
        return http2StreamRemove;
    }

    public void setSettings(Settings settings) {
        synchronized (this.writer) {
            synchronized (this) {
                if (this.shutdown) {
                    throw new ConnectionShutdownException();
                }
                this.okHttpSettings.merge(settings);
            }
            this.writer.settings(settings);
        }
    }

    public void shutdown(ErrorCode errorCode) {
        synchronized (this.writer) {
            synchronized (this) {
                if (this.shutdown) {
                    return;
                }
                this.shutdown = true;
                this.writer.goAway(this.lastGoodStreamId, errorCode, Util.EMPTY_BYTE_ARRAY);
            }
        }
    }

    public void start() {
        start(true);
    }

    public synchronized void updateConnectionFlowControl(long j7) {
        long j8 = this.unacknowledgedBytesRead + j7;
        this.unacknowledgedBytesRead = j8;
        if (j8 >= this.okHttpSettings.getInitialWindowSize() / 2) {
            writeWindowUpdateLater(0, this.unacknowledgedBytesRead);
            this.unacknowledgedBytesRead = 0L;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
    
        r3 = java.lang.Math.min((int) java.lang.Math.min(r12, r3), r8.writer.maxDataLength());
        r6 = r3;
        r8.bytesLeftInWriteWindow -= r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeData(int r9, boolean r10, okio.Buffer r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 != 0) goto Ld
            okhttp3.internal.http2.Http2Writer r12 = r8.writer
            r12.data(r10, r9, r11, r0)
            return
        Ld:
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 <= 0) goto L67
            monitor-enter(r8)
        L12:
            long r3 = r8.bytesLeftInWriteWindow     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r5 > 0) goto L30
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r3 = r8.streams     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            boolean r3 = r3.containsKey(r4)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            if (r3 == 0) goto L28
            r8.wait()     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            goto L12
        L28:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            throw r9     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
        L30:
            long r3 = java.lang.Math.min(r12, r3)     // Catch: java.lang.Throwable -> L56
            int r4 = (int) r3     // Catch: java.lang.Throwable -> L56
            okhttp3.internal.http2.Http2Writer r3 = r8.writer     // Catch: java.lang.Throwable -> L56
            int r3 = r3.maxDataLength()     // Catch: java.lang.Throwable -> L56
            int r3 = java.lang.Math.min(r4, r3)     // Catch: java.lang.Throwable -> L56
            long r4 = r8.bytesLeftInWriteWindow     // Catch: java.lang.Throwable -> L56
            long r6 = (long) r3     // Catch: java.lang.Throwable -> L56
            long r4 = r4 - r6
            r8.bytesLeftInWriteWindow = r4     // Catch: java.lang.Throwable -> L56
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            long r12 = r12 - r6
            okhttp3.internal.http2.Http2Writer r4 = r8.writer
            if (r10 == 0) goto L51
            int r5 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r5 != 0) goto L51
            r5 = 1
            goto L52
        L51:
            r5 = 0
        L52:
            r4.data(r5, r9, r11, r3)
            goto Ld
        L56:
            r9 = move-exception
            goto L65
        L58:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L56
            r9.interrupt()     // Catch: java.lang.Throwable -> L56
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L56
            r9.<init>()     // Catch: java.lang.Throwable -> L56
            throw r9     // Catch: java.lang.Throwable -> L56
        L65:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            throw r9
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.writeData(int, boolean, okio.Buffer, long):void");
    }

    public void writePing(boolean z6, int i7, int i8) {
        boolean z7;
        if (!z6) {
            synchronized (this) {
                z7 = this.awaitingPong;
                this.awaitingPong = true;
            }
            if (z7) {
                failConnection();
                return;
            }
        }
        try {
            this.writer.ping(z6, i7, i8);
        } catch (IOException unused) {
            failConnection();
        }
    }

    public void writePingAndAwaitPong() {
        writePing(false, 1330343787, -257978967);
        awaitPong();
    }

    public void writeSynReply(int i7, boolean z6, List<Header> list) {
        this.writer.synReply(z6, i7, list);
    }

    public void writeSynReset(int i7, ErrorCode errorCode) {
        this.writer.rstStream(i7, errorCode);
    }

    public void writeSynResetLater(final int i7, final ErrorCode errorCode) {
        try {
            this.writerExecutor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{this.hostname, Integer.valueOf(i7)}) { // from class: okhttp3.internal.http2.Http2Connection.1
                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    try {
                        Http2Connection.this.writeSynReset(i7, errorCode);
                    } catch (IOException unused) {
                        Http2Connection.this.failConnection();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    public void writeWindowUpdateLater(final int i7, final long j7) {
        try {
            this.writerExecutor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[]{this.hostname, Integer.valueOf(i7)}) { // from class: okhttp3.internal.http2.Http2Connection.2
                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    try {
                        Http2Connection.this.writer.windowUpdate(i7, j7);
                    } catch (IOException unused) {
                        Http2Connection.this.failConnection();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    private Http2Stream newStream(int i7, List<Header> list, boolean z6) {
        int i8;
        Http2Stream http2Stream;
        boolean z7;
        boolean z8 = !z6;
        synchronized (this.writer) {
            synchronized (this) {
                if (this.nextStreamId > 1073741823) {
                    shutdown(ErrorCode.REFUSED_STREAM);
                }
                if (this.shutdown) {
                    throw new ConnectionShutdownException();
                }
                i8 = this.nextStreamId;
                this.nextStreamId = i8 + 2;
                http2Stream = new Http2Stream(i8, this, z8, false, null);
                z7 = !z6 || this.bytesLeftInWriteWindow == 0 || http2Stream.bytesLeftInWriteWindow == 0;
                if (http2Stream.isOpen()) {
                    this.streams.put(Integer.valueOf(i8), http2Stream);
                }
            }
            if (i7 == 0) {
                this.writer.synStream(z8, i8, i7, list);
            } else {
                if (this.client) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                }
                this.writer.pushPromise(i7, i8, list);
            }
        }
        if (z7) {
            this.writer.flush();
        }
        return http2Stream;
    }

    public void close(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        Http2Stream[] http2StreamArr = null;
        try {
            shutdown(errorCode);
            e = null;
        } catch (IOException e7) {
            e = e7;
        }
        synchronized (this) {
            if (!this.streams.isEmpty()) {
                http2StreamArr = (Http2Stream[]) this.streams.values().toArray(new Http2Stream[this.streams.size()]);
                this.streams.clear();
            }
        }
        if (http2StreamArr != null) {
            for (Http2Stream http2Stream : http2StreamArr) {
                try {
                    http2Stream.close(errorCode2);
                } catch (IOException e8) {
                    if (e != null) {
                        e = e8;
                    }
                }
            }
        }
        try {
            this.writer.close();
        } catch (IOException e9) {
            if (e == null) {
                e = e9;
            }
        }
        try {
            this.socket.close();
        } catch (IOException e10) {
            e = e10;
        }
        this.writerExecutor.shutdown();
        this.pushExecutor.shutdown();
        if (e != null) {
            throw e;
        }
    }

    public void start(boolean z6) {
        if (z6) {
            this.writer.connectionPreface();
            this.writer.settings(this.okHttpSettings);
            if (this.okHttpSettings.getInitialWindowSize() != 65535) {
                this.writer.windowUpdate(0, r6 - 65535);
            }
        }
        new Thread(this.readerRunnable).start();
    }

    public static class Builder {
        public boolean client;
        public String hostname;
        public int pingIntervalMillis;
        public BufferedSink sink;
        public Socket socket;
        public BufferedSource source;
        public Listener listener = Listener.REFUSE_INCOMING_STREAMS;
        public PushObserver pushObserver = PushObserver.CANCEL;

        public Builder(boolean z6) {
            this.client = z6;
        }

        public Http2Connection build() {
            return new Http2Connection(this);
        }

        public Builder listener(Listener listener) {
            this.listener = listener;
            return this;
        }

        public Builder pingIntervalMillis(int i7) {
            this.pingIntervalMillis = i7;
            return this;
        }

        public Builder pushObserver(PushObserver pushObserver) {
            this.pushObserver = pushObserver;
            return this;
        }

        public Builder socket(Socket socket) {
            return socket(socket, ((InetSocketAddress) socket.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(socket)), Okio.buffer(Okio.sink(socket)));
        }

        public Builder socket(Socket socket, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.socket = socket;
            this.hostname = str;
            this.source = bufferedSource;
            this.sink = bufferedSink;
            return this;
        }
    }
}
