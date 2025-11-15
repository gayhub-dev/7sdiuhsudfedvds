package okhttp3.internal.p124ws;

import android.support.constraint.solver.widgets.analyzer.C0096a;
import com.ctvit.network.model.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.p124ws.WebSocketReader;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    public final WebSocketListener listener;
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private int receivedCloseCode = -1;

    /* renamed from: okhttp3.internal.ws.RealWebSocket$1 */
    public class RunnableC16351 implements Runnable {
        public RunnableC16351() {
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            do {
                try {
                } catch (IOException e7) {
                    RealWebSocket.this.failWebSocket(e7, null);
                    return;
                }
            } while (RealWebSocket.this.writeOneFrame());
        }
    }

    /* renamed from: okhttp3.internal.ws.RealWebSocket$2 */
    public class C16362 implements Callback {
        public final /* synthetic */ Request val$request;

        public C16362(Request request) {
            request = request;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) throws IOException {
            RealWebSocket.this.failWebSocket(iOException, null);
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) throws IOException {
            try {
                RealWebSocket.this.checkResponse(response);
                StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                streamAllocation.noNewStreams();
                Streams streamsNewWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                try {
                    RealWebSocket realWebSocket = RealWebSocket.this;
                    realWebSocket.listener.onOpen(realWebSocket, response);
                    RealWebSocket.this.initReaderAndWriter("OkHttp WebSocket " + request.url().redact(), streamsNewWebSocketStreams);
                    streamAllocation.connection().socket().setSoTimeout(0);
                    RealWebSocket.this.loopReader();
                } catch (Exception e7) {
                    RealWebSocket.this.failWebSocket(e7, null);
                }
            } catch (ProtocolException e8) {
                RealWebSocket.this.failWebSocket(e8, response);
                Util.closeQuietly(response);
            }
        }
    }

    public final class CancelRunnable implements Runnable {
        public CancelRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RealWebSocket.this.cancel();
        }
    }

    public static final class Close {
        public final long cancelAfterCloseMillis;
        public final int code;
        public final ByteString reason;

        public Close(int i7, ByteString byteString, long j7) {
            this.code = i7;
            this.reason = byteString;
            this.cancelAfterCloseMillis = j7;
        }
    }

    public static final class Message {
        public final ByteString data;
        public final int formatOpcode;

        public Message(int i7, ByteString byteString) {
            this.formatOpcode = i7;
            this.data = byteString;
        }
    }

    public final class PingRunnable implements Runnable {
        public PingRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            RealWebSocket.this.writePingFrame();
        }
    }

    public static abstract class Streams implements Closeable {
        public final boolean client;
        public final BufferedSink sink;
        public final BufferedSource source;

        public Streams(boolean z6, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.client = z6;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }
    }

    public RealWebSocket(Request request, WebSocketListener webSocketListener, Random random, long j7) {
        if (!"GET".equals(request.method())) {
            StringBuilder sbM112a = C0413b.m112a("Request must be GET: ");
            sbM112a.append(request.method());
            throw new IllegalArgumentException(sbM112a.toString());
        }
        this.originalRequest = request;
        this.listener = webSocketListener;
        this.random = random;
        this.pingIntervalMillis = j7;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.key = ByteString.m1869of(bArr).base64();
        this.writerRunnable = new Runnable() { // from class: okhttp3.internal.ws.RealWebSocket.1
            public RunnableC16351() {
            }

            @Override // java.lang.Runnable
            public void run() throws IOException {
                do {
                    try {
                    } catch (IOException e7) {
                        RealWebSocket.this.failWebSocket(e7, null);
                        return;
                    }
                } while (RealWebSocket.this.writeOneFrame());
            }
        };
    }

    private void runWriter() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.execute(this.writerRunnable);
        }
    }

    public void awaitTermination(int i7, TimeUnit timeUnit) {
        this.executor.awaitTermination(i7, timeUnit);
    }

    @Override // okhttp3.WebSocket
    public void cancel() {
        this.call.cancel();
    }

    public void checkResponse(Response response) throws ProtocolException {
        if (response.code() != 101) {
            StringBuilder sbM112a = C0413b.m112a("Expected HTTP 101 response but was '");
            sbM112a.append(response.code());
            sbM112a.append(" ");
            sbM112a.append(response.message());
            sbM112a.append("'");
            throw new ProtocolException(sbM112a.toString());
        }
        String strHeader = response.header(HttpHeaders.HEAD_KEY_CONNECTION);
        if (!"Upgrade".equalsIgnoreCase(strHeader)) {
            throw new ProtocolException(C0096a.m97a("Expected 'Connection' header value 'Upgrade' but was '", strHeader, "'"));
        }
        String strHeader2 = response.header("Upgrade");
        if (!"websocket".equalsIgnoreCase(strHeader2)) {
            throw new ProtocolException(C0096a.m97a("Expected 'Upgrade' header value 'websocket' but was '", strHeader2, "'"));
        }
        String strHeader3 = response.header("Sec-WebSocket-Accept");
        String strBase64 = ByteString.encodeUtf8(this.key + WebSocketProtocol.ACCEPT_MAGIC).sha1().base64();
        if (strBase64.equals(strHeader3)) {
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + strBase64 + "' but was '" + strHeader3 + "'");
    }

    @Override // okhttp3.WebSocket
    public boolean close(int i7, String str) {
        return close(i7, str, CANCEL_AFTER_CLOSE_MILLIS);
    }

    public void connect(OkHttpClient okHttpClient) {
        OkHttpClient okHttpClientBuild = okHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        Request requestBuild = this.originalRequest.newBuilder().header("Upgrade", "websocket").header(HttpHeaders.HEAD_KEY_CONNECTION, "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build();
        Call callNewWebSocketCall = Internal.instance.newWebSocketCall(okHttpClientBuild, requestBuild);
        this.call = callNewWebSocketCall;
        callNewWebSocketCall.timeout().clearTimeout();
        this.call.enqueue(new Callback() { // from class: okhttp3.internal.ws.RealWebSocket.2
            public final /* synthetic */ Request val$request;

            public C16362(Request requestBuild2) {
                request = requestBuild2;
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) throws IOException {
                RealWebSocket.this.failWebSocket(iOException, null);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    RealWebSocket.this.checkResponse(response);
                    StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                    streamAllocation.noNewStreams();
                    Streams streamsNewWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                    try {
                        RealWebSocket realWebSocket = RealWebSocket.this;
                        realWebSocket.listener.onOpen(realWebSocket, response);
                        RealWebSocket.this.initReaderAndWriter("OkHttp WebSocket " + request.url().redact(), streamsNewWebSocketStreams);
                        streamAllocation.connection().socket().setSoTimeout(0);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e7) {
                        RealWebSocket.this.failWebSocket(e7, null);
                    }
                } catch (ProtocolException e8) {
                    RealWebSocket.this.failWebSocket(e8, response);
                    Util.closeQuietly(response);
                }
            }
        });
    }

    public void failWebSocket(Exception exc, @Nullable Response response) throws IOException {
        synchronized (this) {
            if (this.failed) {
                return;
            }
            this.failed = true;
            Streams streams = this.streams;
            this.streams = null;
            ScheduledFuture<?> scheduledFuture = this.cancelFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledExecutorService scheduledExecutorService = this.executor;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdown();
            }
            try {
                this.listener.onFailure(this, exc, response);
            } finally {
                Util.closeQuietly(streams);
            }
        }
    }

    public void initReaderAndWriter(String str, Streams streams) {
        synchronized (this) {
            this.streams = streams;
            this.writer = new WebSocketWriter(streams.client, streams.sink, this.random);
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(str, false));
            this.executor = scheduledThreadPoolExecutor;
            if (this.pingIntervalMillis != 0) {
                PingRunnable pingRunnable = new PingRunnable();
                long j7 = this.pingIntervalMillis;
                scheduledThreadPoolExecutor.scheduleAtFixedRate(pingRunnable, j7, j7, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams.client, streams.source, this);
    }

    public void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadClose(int i7, String str) throws IOException {
        Streams streams;
        if (i7 == -1) {
            throw new IllegalArgumentException();
        }
        synchronized (this) {
            if (this.receivedCloseCode != -1) {
                throw new IllegalStateException("already closed");
            }
            this.receivedCloseCode = i7;
            this.receivedCloseReason = str;
            streams = null;
            if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                Streams streams2 = this.streams;
                this.streams = null;
                ScheduledFuture<?> scheduledFuture = this.cancelFuture;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
                this.executor.shutdown();
                streams = streams2;
            }
        }
        try {
            this.listener.onClosing(this, i7, str);
            if (streams != null) {
                this.listener.onClosed(this, i7, str);
            }
        } finally {
            Util.closeQuietly(streams);
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(String str) {
        this.listener.onMessage(this, str);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPing(ByteString byteString) {
        if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
            this.pongQueue.add(byteString);
            runWriter();
            this.receivedPingCount++;
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPong(ByteString byteString) {
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    public synchronized boolean pong(ByteString byteString) {
        if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
            this.pongQueue.add(byteString);
            runWriter();
            return true;
        }
        return false;
    }

    public boolean processNextFrame() throws IOException {
        try {
            this.reader.processNextFrame();
            return this.receivedCloseCode == -1;
        } catch (Exception e7) {
            failWebSocket(e7, null);
            return false;
        }
    }

    @Override // okhttp3.WebSocket
    public synchronized long queueSize() {
        return this.queueSize;
    }

    public synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    public synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    @Override // okhttp3.WebSocket
    public Request request() {
        return this.originalRequest;
    }

    @Override // okhttp3.WebSocket
    public boolean send(String str) {
        Objects.requireNonNull(str, "text == null");
        return send(ByteString.encodeUtf8(str), 1);
    }

    public synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    public void tearDown() {
        ScheduledFuture<?> scheduledFuture = this.cancelFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10L, TimeUnit.SECONDS);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v9 */
    public boolean writeOneFrame() throws IOException {
        Streams streams;
        String str;
        synchronized (this) {
            if (this.failed) {
                return false;
            }
            WebSocketWriter webSocketWriter = this.writer;
            ByteString byteStringPoll = this.pongQueue.poll();
            int i7 = -1;
            Message message = 0;
            if (byteStringPoll == null) {
                Object objPoll = this.messageAndCloseQueue.poll();
                if (objPoll instanceof Close) {
                    int i8 = this.receivedCloseCode;
                    str = this.receivedCloseReason;
                    if (i8 != -1) {
                        Streams streams2 = this.streams;
                        this.streams = null;
                        this.executor.shutdown();
                        message = objPoll;
                        i7 = i8;
                        streams = streams2;
                    } else {
                        this.cancelFuture = this.executor.schedule(new CancelRunnable(), ((Close) objPoll).cancelAfterCloseMillis, TimeUnit.MILLISECONDS);
                        i7 = i8;
                        streams = null;
                    }
                } else {
                    if (objPoll == null) {
                        return false;
                    }
                    streams = null;
                    str = null;
                }
                message = objPoll;
            } else {
                streams = null;
                str = null;
            }
            try {
                if (byteStringPoll != null) {
                    webSocketWriter.writePong(byteStringPoll);
                } else if (message instanceof Message) {
                    ByteString byteString = message.data;
                    BufferedSink bufferedSinkBuffer = Okio.buffer(webSocketWriter.newMessageSink(message.formatOpcode, byteString.size()));
                    bufferedSinkBuffer.write(byteString);
                    bufferedSinkBuffer.close();
                    synchronized (this) {
                        this.queueSize -= byteString.size();
                    }
                } else {
                    if (!(message instanceof Close)) {
                        throw new AssertionError();
                    }
                    Close close = (Close) message;
                    webSocketWriter.writeClose(close.code, close.reason);
                    if (streams != null) {
                        this.listener.onClosed(this, i7, str);
                    }
                }
                return true;
            } finally {
                Util.closeQuietly(streams);
            }
        }
    }

    public void writePingFrame() throws IOException {
        synchronized (this) {
            if (this.failed) {
                return;
            }
            WebSocketWriter webSocketWriter = this.writer;
            int i7 = this.awaitingPong ? this.sentPingCount : -1;
            this.sentPingCount++;
            this.awaitingPong = true;
            if (i7 == -1) {
                try {
                    webSocketWriter.writePing(ByteString.EMPTY);
                    return;
                } catch (IOException e7) {
                    failWebSocket(e7, null);
                    return;
                }
            }
            StringBuilder sbM112a = C0413b.m112a("sent ping but didn't receive pong within ");
            sbM112a.append(this.pingIntervalMillis);
            sbM112a.append("ms (after ");
            sbM112a.append(i7 - 1);
            sbM112a.append(" successful ping/pongs)");
            failWebSocket(new SocketTimeoutException(sbM112a.toString()), null);
        }
    }

    public synchronized boolean close(int i7, String str, long j7) {
        WebSocketProtocol.validateCloseCode(i7);
        ByteString byteStringEncodeUtf8 = null;
        if (str != null) {
            byteStringEncodeUtf8 = ByteString.encodeUtf8(str);
            if (byteStringEncodeUtf8.size() > 123) {
                throw new IllegalArgumentException("reason.size() > 123: " + str);
            }
        }
        if (!this.failed && !this.enqueuedClose) {
            this.enqueuedClose = true;
            this.messageAndCloseQueue.add(new Close(i7, byteStringEncodeUtf8, j7));
            runWriter();
            return true;
        }
        return false;
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(ByteString byteString) {
        this.listener.onMessage(this, byteString);
    }

    @Override // okhttp3.WebSocket
    public boolean send(ByteString byteString) {
        Objects.requireNonNull(byteString, "bytes == null");
        return send(byteString, 2);
    }

    private synchronized boolean send(ByteString byteString, int i7) {
        if (!this.failed && !this.enqueuedClose) {
            if (this.queueSize + byteString.size() > MAX_QUEUE_SIZE) {
                close(1001, null);
                return false;
            }
            this.queueSize += byteString.size();
            this.messageAndCloseQueue.add(new Message(i7, byteString));
            runWriter();
            return true;
        }
        return false;
    }
}
