package okhttp3.internal.http1;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class Http1Codec implements HttpCodec {
    private static final int HEADER_LIMIT = 262144;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    public final OkHttpClient client;
    public final BufferedSink sink;
    public final BufferedSource source;
    public final StreamAllocation streamAllocation;
    public int state = 0;
    private long headerLimit = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;

    public final class ChunkedSink implements Sink {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            Http1Codec.this.sink.writeUtf8("0\r\n\r\n");
            Http1Codec.this.detachTimeout(this.timeout);
            Http1Codec.this.state = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() {
            if (this.closed) {
                return;
            }
            Http1Codec.this.sink.flush();
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j7) {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (j7 == 0) {
                return;
            }
            Http1Codec.this.sink.writeHexadecimalUnsignedLong(j7);
            Http1Codec.this.sink.writeUtf8("\r\n");
            Http1Codec.this.sink.write(buffer, j7);
            Http1Codec.this.sink.writeUtf8("\r\n");
        }
    }

    public class ChunkedSource extends AbstractSource {
        private static final long NO_CHUNK_YET = -1;
        private long bytesRemainingInChunk;
        private boolean hasMoreChunks;
        private final HttpUrl url;

        public ChunkedSource(HttpUrl httpUrl) {
            super();
            this.bytesRemainingInChunk = -1L;
            this.hasMoreChunks = true;
            this.url = httpUrl;
        }

        private void readChunkSize() throws ProtocolException {
            if (this.bytesRemainingInChunk != -1) {
                Http1Codec.this.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = Http1Codec.this.source.readHexadecimalUnsignedLong();
                String strTrim = Http1Codec.this.source.readUtf8LineStrict().trim();
                if (this.bytesRemainingInChunk < 0 || !(strTrim.isEmpty() || strTrim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + strTrim + "\"");
                }
                if (this.bytesRemainingInChunk == 0) {
                    this.hasMoreChunks = false;
                    HttpHeaders.receiveHeaders(Http1Codec.this.client.cookieJar(), this.url, Http1Codec.this.readHeaders());
                    endOfInput(true, null);
                }
            } catch (NumberFormatException e7) {
                throw new ProtocolException(e7.getMessage());
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                endOfInput(false, null);
            }
            this.closed = true;
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source
        public long read(Buffer buffer, long j7) throws IOException {
            if (j7 < 0) {
                throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (!this.hasMoreChunks) {
                return -1L;
            }
            long j8 = this.bytesRemainingInChunk;
            if (j8 == 0 || j8 == -1) {
                readChunkSize();
                if (!this.hasMoreChunks) {
                    return -1L;
                }
            }
            long j9 = super.read(buffer, Math.min(j7, this.bytesRemainingInChunk));
            if (j9 != -1) {
                this.bytesRemainingInChunk -= j9;
                return j9;
            }
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            endOfInput(false, protocolException);
            throw protocolException;
        }
    }

    public final class FixedLengthSink implements Sink {
        private long bytesRemaining;
        private boolean closed;
        private final ForwardingTimeout timeout;

        public FixedLengthSink(long j7) {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
            this.bytesRemaining = j7;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws ProtocolException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.bytesRemaining > 0) {
                throw new ProtocolException("unexpected end of stream");
            }
            Http1Codec.this.detachTimeout(this.timeout);
            Http1Codec.this.state = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            if (this.closed) {
                return;
            }
            Http1Codec.this.sink.flush();
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j7) throws ProtocolException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(buffer.size(), 0L, j7);
            if (j7 <= this.bytesRemaining) {
                Http1Codec.this.sink.write(buffer, j7);
                this.bytesRemaining -= j7;
            } else {
                StringBuilder sbM112a = C0413b.m112a("expected ");
                sbM112a.append(this.bytesRemaining);
                sbM112a.append(" bytes but received ");
                sbM112a.append(j7);
                throw new ProtocolException(sbM112a.toString());
            }
        }
    }

    public class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;

        public FixedLengthSource(long j7) {
            super();
            this.bytesRemaining = j7;
            if (j7 == 0) {
                endOfInput(true, null);
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            if (this.bytesRemaining != 0 && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                endOfInput(false, null);
            }
            this.closed = true;
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source
        public long read(Buffer buffer, long j7) throws IOException {
            if (j7 < 0) {
                throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            long j8 = this.bytesRemaining;
            if (j8 == 0) {
                return -1L;
            }
            long j9 = super.read(buffer, Math.min(j8, j7));
            if (j9 == -1) {
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                endOfInput(false, protocolException);
                throw protocolException;
            }
            long j10 = this.bytesRemaining - j9;
            this.bytesRemaining = j10;
            if (j10 == 0) {
                endOfInput(true, null);
            }
            return j9;
        }
    }

    public class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;

        public UnknownLengthSource() {
            super();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            if (!this.inputExhausted) {
                endOfInput(false, null);
            }
            this.closed = true;
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source
        public long read(Buffer buffer, long j7) throws IOException {
            if (j7 < 0) {
                throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (this.inputExhausted) {
                return -1L;
            }
            long j8 = super.read(buffer, j7);
            if (j8 != -1) {
                return j8;
            }
            this.inputExhausted = true;
            endOfInput(true, null);
            return -1L;
        }
    }

    public Http1Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.client = okHttpClient;
        this.streamAllocation = streamAllocation;
        this.source = bufferedSource;
        this.sink = bufferedSink;
    }

    private String readHeaderLine() {
        String utf8LineStrict = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= utf8LineStrict.length();
        return utf8LineStrict;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() throws IOException {
        RealConnection realConnectionConnection = this.streamAllocation.connection();
        if (realConnectionConnection != null) {
            realConnectionConnection.cancel();
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Sink createRequestBody(Request request, long j7) {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return newChunkedSink();
        }
        if (j7 != -1) {
            return newFixedLengthSink(j7);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void detachTimeout(ForwardingTimeout forwardingTimeout) {
        Timeout timeoutDelegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        timeoutDelegate.clearDeadline();
        timeoutDelegate.clearTimeout();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void finishRequest() {
        this.sink.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void flushRequest() {
        this.sink.flush();
    }

    public boolean isClosed() {
        return this.state == 6;
    }

    public Sink newChunkedSink() {
        if (this.state == 1) {
            this.state = 2;
            return new ChunkedSink();
        }
        StringBuilder sbM112a = C0413b.m112a("state: ");
        sbM112a.append(this.state);
        throw new IllegalStateException(sbM112a.toString());
    }

    public Source newChunkedSource(HttpUrl httpUrl) {
        if (this.state == 4) {
            this.state = 5;
            return new ChunkedSource(httpUrl);
        }
        StringBuilder sbM112a = C0413b.m112a("state: ");
        sbM112a.append(this.state);
        throw new IllegalStateException(sbM112a.toString());
    }

    public Sink newFixedLengthSink(long j7) {
        if (this.state == 1) {
            this.state = 2;
            return new FixedLengthSink(j7);
        }
        StringBuilder sbM112a = C0413b.m112a("state: ");
        sbM112a.append(this.state);
        throw new IllegalStateException(sbM112a.toString());
    }

    public Source newFixedLengthSource(long j7) {
        if (this.state == 4) {
            this.state = 5;
            return new FixedLengthSource(j7);
        }
        StringBuilder sbM112a = C0413b.m112a("state: ");
        sbM112a.append(this.state);
        throw new IllegalStateException(sbM112a.toString());
    }

    public Source newUnknownLengthSource() throws IOException {
        if (this.state != 4) {
            StringBuilder sbM112a = C0413b.m112a("state: ");
            sbM112a.append(this.state);
            throw new IllegalStateException(sbM112a.toString());
        }
        StreamAllocation streamAllocation = this.streamAllocation;
        if (streamAllocation == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.state = 5;
        streamAllocation.noNewStreams();
        return new UnknownLengthSource();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody openResponseBody(Response response) {
        StreamAllocation streamAllocation = this.streamAllocation;
        streamAllocation.eventListener.responseBodyStart(streamAllocation.call);
        String strHeader = response.header("Content-Type");
        if (!HttpHeaders.hasBody(response)) {
            return new RealResponseBody(strHeader, 0L, Okio.buffer(newFixedLengthSource(0L)));
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return new RealResponseBody(strHeader, -1L, Okio.buffer(newChunkedSource(response.request().url())));
        }
        long jContentLength = HttpHeaders.contentLength(response);
        return jContentLength != -1 ? new RealResponseBody(strHeader, jContentLength, Okio.buffer(newFixedLengthSource(jContentLength))) : new RealResponseBody(strHeader, -1L, Okio.buffer(newUnknownLengthSource()));
    }

    public Headers readHeaders() {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String headerLine = readHeaderLine();
            if (headerLine.length() == 0) {
                return builder.build();
            }
            Internal.instance.addLenient(builder, headerLine);
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder readResponseHeaders(boolean z6) throws NumberFormatException, IOException {
        int i7 = this.state;
        if (i7 != 1 && i7 != 3) {
            StringBuilder sbM112a = C0413b.m112a("state: ");
            sbM112a.append(this.state);
            throw new IllegalStateException(sbM112a.toString());
        }
        try {
            StatusLine statusLine = StatusLine.parse(readHeaderLine());
            Response.Builder builderHeaders = new Response.Builder().protocol(statusLine.protocol).code(statusLine.code).message(statusLine.message).headers(readHeaders());
            if (z6 && statusLine.code == 100) {
                return null;
            }
            if (statusLine.code == 100) {
                this.state = 3;
                return builderHeaders;
            }
            this.state = 4;
            return builderHeaders;
        } catch (EOFException e7) {
            StringBuilder sbM112a2 = C0413b.m112a("unexpected end of stream on ");
            sbM112a2.append(this.streamAllocation);
            IOException iOException = new IOException(sbM112a2.toString());
            iOException.initCause(e7);
            throw iOException;
        }
    }

    public void writeRequest(Headers headers, String str) {
        if (this.state != 0) {
            StringBuilder sbM112a = C0413b.m112a("state: ");
            sbM112a.append(this.state);
            throw new IllegalStateException(sbM112a.toString());
        }
        this.sink.writeUtf8(str).writeUtf8("\r\n");
        int size = headers.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.sink.writeUtf8(headers.name(i7)).writeUtf8(": ").writeUtf8(headers.value(i7)).writeUtf8("\r\n");
        }
        this.sink.writeUtf8("\r\n");
        this.state = 1;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void writeRequestHeaders(Request request) {
        writeRequest(request.headers(), RequestLine.get(request, this.streamAllocation.connection().route().proxy().type()));
    }

    public abstract class AbstractSource implements Source {
        public long bytesRead;
        public boolean closed;
        public final ForwardingTimeout timeout;

        private AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.source.timeout());
            this.bytesRead = 0L;
        }

        public final void endOfInput(boolean z6, IOException iOException) {
            Http1Codec http1Codec = Http1Codec.this;
            int i7 = http1Codec.state;
            if (i7 == 6) {
                return;
            }
            if (i7 != 5) {
                StringBuilder sbM112a = C0413b.m112a("state: ");
                sbM112a.append(Http1Codec.this.state);
                throw new IllegalStateException(sbM112a.toString());
            }
            http1Codec.detachTimeout(this.timeout);
            Http1Codec http1Codec2 = Http1Codec.this;
            http1Codec2.state = 6;
            StreamAllocation streamAllocation = http1Codec2.streamAllocation;
            if (streamAllocation != null) {
                streamAllocation.streamFinished(!z6, http1Codec2, this.bytesRead, iOException);
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j7) throws IOException {
            try {
                long j8 = Http1Codec.this.source.read(buffer, j7);
                if (j8 > 0) {
                    this.bytesRead += j8;
                }
                return j8;
            } catch (IOException e7) {
                endOfInput(false, e7);
                throw e7;
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }

        public /* synthetic */ AbstractSource(Http1Codec http1Codec, C16221 c16221) {
            this();
        }
    }
}
