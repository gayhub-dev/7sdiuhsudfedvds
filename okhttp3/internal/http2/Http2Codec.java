package okhttp3.internal.http2;

import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* loaded from: classes.dex */
public final class Http2Codec implements HttpCodec {
    private static final String KEEP_ALIVE = "keep-alive";
    private final Interceptor.Chain chain;
    private final Http2Connection connection;
    private final Protocol protocol;
    private Http2Stream stream;
    public final StreamAllocation streamAllocation;
    private static final String CONNECTION = "connection";
    private static final String HOST = "host";
    private static final String PROXY_CONNECTION = "proxy-connection";

    /* renamed from: TE */
    private static final String f4849TE = "te";
    private static final String TRANSFER_ENCODING = "transfer-encoding";
    private static final String ENCODING = "encoding";
    private static final String UPGRADE = "upgrade";
    private static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(CONNECTION, HOST, "keep-alive", PROXY_CONNECTION, f4849TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD_UTF8, Header.TARGET_PATH_UTF8, Header.TARGET_SCHEME_UTF8, Header.TARGET_AUTHORITY_UTF8);
    private static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(CONNECTION, HOST, "keep-alive", PROXY_CONNECTION, f4849TE, TRANSFER_ENCODING, ENCODING, UPGRADE);

    public class StreamFinishingSource extends ForwardingSource {
        public long bytesRead;
        public boolean completed;

        public StreamFinishingSource(Source source) {
            super(source);
            this.completed = false;
            this.bytesRead = 0L;
        }

        private void endOfInput(IOException iOException) {
            if (this.completed) {
                return;
            }
            this.completed = true;
            Http2Codec http2Codec = Http2Codec.this;
            http2Codec.streamAllocation.streamFinished(false, http2Codec, this.bytesRead, iOException);
        }

        @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            super.close();
            endOfInput(null);
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(Buffer buffer, long j7) throws IOException {
            try {
                long j8 = delegate().read(buffer, j7);
                if (j8 > 0) {
                    this.bytesRead += j8;
                }
                return j8;
            } catch (IOException e7) {
                endOfInput(e7);
                throw e7;
            }
        }
    }

    public Http2Codec(OkHttpClient okHttpClient, Interceptor.Chain chain, StreamAllocation streamAllocation, Http2Connection http2Connection) {
        this.chain = chain;
        this.streamAllocation = streamAllocation;
        this.connection = http2Connection;
        List<Protocol> listProtocols = okHttpClient.protocols();
        Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        this.protocol = listProtocols.contains(protocol) ? protocol : Protocol.HTTP_2;
    }

    public static List<Header> http2HeadersList(Request request) {
        Headers headers = request.headers();
        ArrayList arrayList = new ArrayList(headers.size() + 4);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String strHeader = request.header(HttpHeaders.HOST);
        if (strHeader != null) {
            arrayList.add(new Header(Header.TARGET_AUTHORITY, strHeader));
        }
        arrayList.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
        int size = headers.size();
        for (int i7 = 0; i7 < size; i7++) {
            ByteString byteStringEncodeUtf8 = ByteString.encodeUtf8(headers.name(i7).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(byteStringEncodeUtf8.utf8())) {
                arrayList.add(new Header(byteStringEncodeUtf8, headers.value(i7)));
            }
        }
        return arrayList;
    }

    public static Response.Builder readHttp2HeadersList(Headers headers, Protocol protocol) throws ProtocolException, NumberFormatException {
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        StatusLine statusLine = null;
        for (int i7 = 0; i7 < size; i7++) {
            String strName = headers.name(i7);
            String strValue = headers.value(i7);
            if (strName.equals(Header.RESPONSE_STATUS_UTF8)) {
                statusLine = StatusLine.parse("HTTP/1.1 " + strValue);
            } else if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(strName)) {
                Internal.instance.addLenient(builder, strName, strValue);
            }
        }
        if (statusLine != null) {
            return new Response.Builder().protocol(protocol).code(statusLine.code).message(statusLine.message).headers(builder.build());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() {
        Http2Stream http2Stream = this.stream;
        if (http2Stream != null) {
            http2Stream.closeLater(ErrorCode.CANCEL);
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Sink createRequestBody(Request request, long j7) {
        return this.stream.getSink();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void finishRequest() {
        this.stream.getSink().close();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void flushRequest() {
        this.connection.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody openResponseBody(Response response) {
        StreamAllocation streamAllocation = this.streamAllocation;
        streamAllocation.eventListener.responseBodyStart(streamAllocation.call);
        return new RealResponseBody(response.header("Content-Type"), okhttp3.internal.http.HttpHeaders.contentLength(response), Okio.buffer(new StreamFinishingSource(this.stream.getSource())));
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder readResponseHeaders(boolean z6) throws ProtocolException, NumberFormatException {
        Response.Builder http2HeadersList = readHttp2HeadersList(this.stream.takeHeaders(), this.protocol);
        if (z6 && Internal.instance.code(http2HeadersList) == 100) {
            return null;
        }
        return http2HeadersList;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void writeRequestHeaders(Request request) {
        if (this.stream != null) {
            return;
        }
        Http2Stream http2StreamNewStream = this.connection.newStream(http2HeadersList(request), request.body() != null);
        this.stream = http2StreamNewStream;
        Timeout timeout = http2StreamNewStream.readTimeout();
        long timeoutMillis = this.chain.readTimeoutMillis();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        timeout.timeout(timeoutMillis, timeUnit);
        this.stream.writeTimeout().timeout(this.chain.writeTimeoutMillis(), timeUnit);
    }
}
