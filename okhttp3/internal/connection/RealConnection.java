package okhttp3.internal.connection;

import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.p124ws.RealWebSocket;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class RealConnection extends Http2Connection.Listener implements Connection {
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    public long idleAtNanos = RecyclerView.FOREVER_NS;

    public RealConnection(ConnectionPool connectionPool, Route route) {
        this.connectionPool = connectionPool;
        this.route = route;
    }

    private void connectSocket(int i7, int i8, Call call, EventListener eventListener) throws IOException {
        Proxy proxy = this.route.proxy();
        this.rawSocket = (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) ? this.route.address().socketFactory().createSocket() : new Socket(proxy);
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        this.rawSocket.setSoTimeout(i8);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i7);
            try {
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            } catch (NullPointerException e7) {
                if (NPE_THROW_WITH_NULL.equals(e7.getMessage())) {
                    throw new IOException(e7);
                }
            }
        } catch (ConnectException e8) {
            StringBuilder sbM112a = C0413b.m112a("Failed to connect to ");
            sbM112a.append(this.route.socketAddress());
            ConnectException connectException = new ConnectException(sbM112a.toString());
            connectException.initCause(e8);
            throw connectException;
        }
    }

    private void connectTls(ConnectionSpecSelector connectionSpecSelector) throws Throwable {
        Address address = this.route.address();
        SSLSocket sSLSocket = null;
        try {
            try {
                SSLSocket sSLSocket2 = (SSLSocket) address.sslSocketFactory().createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
                try {
                    ConnectionSpec connectionSpecConfigureSecureSocket = connectionSpecSelector.configureSecureSocket(sSLSocket2);
                    if (connectionSpecConfigureSecureSocket.supportsTlsExtensions()) {
                        Platform.get().configureTlsExtensions(sSLSocket2, address.url().host(), address.protocols());
                    }
                    sSLSocket2.startHandshake();
                    SSLSession session = sSLSocket2.getSession();
                    Handshake handshake = Handshake.get(session);
                    if (address.hostnameVerifier().verify(address.url().host(), session)) {
                        address.certificatePinner().check(address.url().host(), handshake.peerCertificates());
                        String selectedProtocol = connectionSpecConfigureSecureSocket.supportsTlsExtensions() ? Platform.get().getSelectedProtocol(sSLSocket2) : null;
                        this.socket = sSLSocket2;
                        this.source = Okio.buffer(Okio.source(sSLSocket2));
                        this.sink = Okio.buffer(Okio.sink(this.socket));
                        this.handshake = handshake;
                        this.protocol = selectedProtocol != null ? Protocol.get(selectedProtocol) : Protocol.HTTP_1_1;
                        Platform.get().afterHandshake(sSLSocket2);
                        return;
                    }
                    List<Certificate> listPeerCertificates = handshake.peerCertificates();
                    if (listPeerCertificates.isEmpty()) {
                        throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified (no certificates)");
                    }
                    X509Certificate x509Certificate = (X509Certificate) listPeerCertificates.get(0);
                    throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified:\n    certificate: " + CertificatePinner.pin(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(x509Certificate));
                } catch (AssertionError e7) {
                    e = e7;
                    if (!Util.isAndroidGetsocknameError(e)) {
                        throw e;
                    }
                    throw new IOException(e);
                } catch (Throwable th) {
                    th = th;
                    sSLSocket = sSLSocket2;
                    if (sSLSocket != null) {
                        Platform.get().afterHandshake(sSLSocket);
                    }
                    Util.closeQuietly((Socket) sSLSocket);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (AssertionError e8) {
            e = e8;
        }
    }

    private void connectTunnel(int i7, int i8, int i9, Call call, EventListener eventListener) throws IOException {
        Request requestCreateTunnelRequest = createTunnelRequest();
        HttpUrl httpUrlUrl = requestCreateTunnelRequest.url();
        for (int i10 = 0; i10 < 21; i10++) {
            connectSocket(i7, i8, call, eventListener);
            requestCreateTunnelRequest = createTunnel(i8, i9, requestCreateTunnelRequest, httpUrlUrl);
            if (requestCreateTunnelRequest == null) {
                return;
            }
            Util.closeQuietly(this.rawSocket);
            this.rawSocket = null;
            this.sink = null;
            this.source = null;
            eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), null);
        }
    }

    private Request createTunnel(int i7, int i8, Request request, HttpUrl httpUrl) throws IOException {
        StringBuilder sbM112a = C0413b.m112a("CONNECT ");
        sbM112a.append(Util.hostHeader(httpUrl, true));
        sbM112a.append(" HTTP/1.1");
        String string = sbM112a.toString();
        while (true) {
            Http1Codec http1Codec = new Http1Codec(null, null, this.source, this.sink);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            this.source.timeout().timeout(i7, timeUnit);
            this.sink.timeout().timeout(i8, timeUnit);
            http1Codec.writeRequest(request.headers(), string);
            http1Codec.finishRequest();
            Response responseBuild = http1Codec.readResponseHeaders(false).request(request).build();
            long jContentLength = HttpHeaders.contentLength(responseBuild);
            if (jContentLength == -1) {
                jContentLength = 0;
            }
            Source sourceNewFixedLengthSource = http1Codec.newFixedLengthSource(jContentLength);
            Util.skipAll(sourceNewFixedLengthSource, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, timeUnit);
            sourceNewFixedLengthSource.close();
            int iCode = responseBuild.code();
            if (iCode == 200) {
                if (this.source.buffer().exhausted() && this.sink.buffer().exhausted()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (iCode != 407) {
                StringBuilder sbM112a2 = C0413b.m112a("Unexpected response code for CONNECT: ");
                sbM112a2.append(responseBuild.code());
                throw new IOException(sbM112a2.toString());
            }
            Request requestAuthenticate = this.route.address().proxyAuthenticator().authenticate(this.route, responseBuild);
            if (requestAuthenticate == null) {
                throw new IOException("Failed to authenticate with proxy");
            }
            if (com.ctvit.network.model.HttpHeaders.HEAD_VALUE_CONNECTION_CLOSE.equalsIgnoreCase(responseBuild.header(com.ctvit.network.model.HttpHeaders.HEAD_KEY_CONNECTION))) {
                return requestAuthenticate;
            }
            request = requestAuthenticate;
        }
    }

    private Request createTunnelRequest() {
        Request requestBuild = new Request.Builder().url(this.route.address().url()).method("CONNECT", null).header(com.alibaba.sdk.android.oss.common.utils.HttpHeaders.HOST, Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
        Request requestAuthenticate = this.route.address().proxyAuthenticator().authenticate(this.route, new Response.Builder().request(requestBuild).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header("Proxy-Authenticate", "OkHttp-Preemptive").build());
        return requestAuthenticate != null ? requestAuthenticate : requestBuild;
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int i7, Call call, EventListener eventListener) throws Throwable {
        if (this.route.address().sslSocketFactory() != null) {
            eventListener.secureConnectStart(call);
            connectTls(connectionSpecSelector);
            eventListener.secureConnectEnd(call, this.handshake);
            if (this.protocol == Protocol.HTTP_2) {
                startHttp2(i7);
                return;
            }
            return;
        }
        List<Protocol> listProtocols = this.route.address().protocols();
        Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        if (!listProtocols.contains(protocol)) {
            this.socket = this.rawSocket;
            this.protocol = Protocol.HTTP_1_1;
        } else {
            this.socket = this.rawSocket;
            this.protocol = protocol;
            startHttp2(i7);
        }
    }

    private void startHttp2(int i7) throws SocketException {
        this.socket.setSoTimeout(0);
        Http2Connection http2ConnectionBuild = new Http2Connection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).pingIntervalMillis(i7).build();
        this.http2Connection = http2ConnectionBuild;
        http2ConnectionBuild.start();
    }

    public static RealConnection testConnection(ConnectionPool connectionPool, Route route, Socket socket, long j7) {
        RealConnection realConnection = new RealConnection(connectionPool, route);
        realConnection.socket = socket;
        realConnection.idleAtNanos = j7;
        return realConnection;
    }

    public void cancel() throws IOException {
        Util.closeQuietly(this.rawSocket);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e6 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0135 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void connect(int r17, int r18, int r19, int r20, boolean r21, okhttp3.Call r22, okhttp3.EventListener r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connect(int, int, int, int, boolean, okhttp3.Call, okhttp3.EventListener):void");
    }

    @Override // okhttp3.Connection
    public Handshake handshake() {
        return this.handshake;
    }

    public boolean isEligible(Address address, @Nullable Route route) {
        if (this.allocations.size() >= this.allocationLimit || this.noNewStreams || !Internal.instance.equalsNonHost(this.route.address(), address)) {
            return false;
        }
        if (address.url().host().equals(route().address().url().host())) {
            return true;
        }
        if (this.http2Connection == null || route == null || route.proxy().type() != Proxy.Type.DIRECT || this.route.proxy().type() != Proxy.Type.DIRECT || !this.route.socketAddress().equals(route.socketAddress()) || route.address().hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
            return false;
        }
        try {
            address.certificatePinner().check(address.url().host(), handshake().peerCertificates());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean isHealthy(boolean z6) throws SocketException {
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        if (this.http2Connection != null) {
            return !r0.isShutdown();
        }
        if (z6) {
            try {
                int soTimeout = this.socket.getSoTimeout();
                try {
                    this.socket.setSoTimeout(1);
                    return !this.source.exhausted();
                } finally {
                    this.socket.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    public boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    public HttpCodec newCodec(OkHttpClient okHttpClient, Interceptor.Chain chain, StreamAllocation streamAllocation) throws SocketException {
        if (this.http2Connection != null) {
            return new Http2Codec(okHttpClient, chain, streamAllocation, this.http2Connection);
        }
        this.socket.setSoTimeout(chain.readTimeoutMillis());
        Timeout timeout = this.source.timeout();
        long timeoutMillis = chain.readTimeoutMillis();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        timeout.timeout(timeoutMillis, timeUnit);
        this.sink.timeout().timeout(chain.writeTimeoutMillis(), timeUnit);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }

    public RealWebSocket.Streams newWebSocketStreams(final StreamAllocation streamAllocation) {
        return new RealWebSocket.Streams(true, this.source, this.sink) { // from class: okhttp3.internal.connection.RealConnection.1
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                StreamAllocation streamAllocation2 = streamAllocation;
                streamAllocation2.streamFinished(true, streamAllocation2.codec(), -1L, null);
            }
        };
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onSettings(Http2Connection http2Connection) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection.maxConcurrentStreams();
        }
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onStream(Http2Stream http2Stream) {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    @Override // okhttp3.Connection
    public Protocol protocol() {
        return this.protocol;
    }

    @Override // okhttp3.Connection
    public Route route() {
        return this.route;
    }

    @Override // okhttp3.Connection
    public Socket socket() {
        return this.socket;
    }

    public boolean supportsUrl(HttpUrl httpUrl) {
        if (httpUrl.port() != this.route.address().url().port()) {
            return false;
        }
        if (httpUrl.host().equals(this.route.address().url().host())) {
            return true;
        }
        return this.handshake != null && OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) this.handshake.peerCertificates().get(0));
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Connection{");
        sbM112a.append(this.route.address().url().host());
        sbM112a.append(":");
        sbM112a.append(this.route.address().url().port());
        sbM112a.append(", proxy=");
        sbM112a.append(this.route.proxy());
        sbM112a.append(" hostAddress=");
        sbM112a.append(this.route.socketAddress());
        sbM112a.append(" cipherSuite=");
        Handshake handshake = this.handshake;
        sbM112a.append(handshake != null ? handshake.cipherSuite() : "none");
        sbM112a.append(" protocol=");
        sbM112a.append(this.protocol);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
