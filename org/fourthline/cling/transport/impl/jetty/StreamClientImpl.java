package org.fourthline.cling.transport.impl.jetty;

import android.arch.lifecycle.C0063n;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpHeaders;
import org.fourthline.cling.model.message.UpnpMessage;
import org.fourthline.cling.model.message.UpnpRequest;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.message.header.ContentTypeHeader;
import org.fourthline.cling.model.message.header.UpnpHeader;
import org.fourthline.cling.transport.spi.AbstractStreamClient;
import org.fourthline.cling.transport.spi.InitializationException;
import org.fourthline.cling.transport.spi.StreamClient;
import p007a6.C0041a;
import p009b.C0413b;
import p041e5.C0957b;
import p041e5.C0961f;
import p041e5.C0962g;
import p041e5.C0963h;
import p065h5.C1101i;
import p065h5.C1110r;
import p073i5.C1155h;
import p073i5.C1158k;
import p098l6.C1448b;
import p186x2.C2074b;
import p196y5.C2133a;

/* loaded from: classes.dex */
public class StreamClientImpl extends AbstractStreamClient<StreamClientConfigurationImpl, HttpContentExchange> {
    private static final Logger log = Logger.getLogger(StreamClient.class.getName());
    public final C0962g client;
    public final StreamClientConfigurationImpl configuration;

    /* renamed from: org.fourthline.cling.transport.impl.jetty.StreamClientImpl$1 */
    public class C17201 extends C0041a {
        public C17201(ExecutorService executorService) {
            super(executorService);
        }

        @Override // p007a6.C0041a, p168u5.AbstractC1980a
        public void doStop() {
        }
    }

    /* renamed from: org.fourthline.cling.transport.impl.jetty.StreamClientImpl$2 */
    public class CallableC17212 implements Callable<StreamResponseMessage> {
        public final /* synthetic */ HttpContentExchange val$exchange;
        public final /* synthetic */ StreamRequestMessage val$requestMessage;

        public CallableC17212(StreamRequestMessage streamRequestMessage, HttpContentExchange httpContentExchange) {
            streamRequestMessage = streamRequestMessage;
            httpContentExchange = httpContentExchange;
        }

        @Override // java.util.concurrent.Callable
        public StreamResponseMessage call() throws UnknownHostException {
            C0963h c0963hPutIfAbsent;
            if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                Logger logger = StreamClientImpl.log;
                StringBuilder sbM112a = C0413b.m112a("Sending HTTP request: ");
                sbM112a.append(streamRequestMessage);
                logger.fine(sbM112a.toString());
            }
            C0962g c0962g = StreamClientImpl.this.client;
            HttpContentExchange httpContentExchange = httpContentExchange;
            Objects.requireNonNull(c0962g);
            boolean zMo1315B = ((C1158k) C1110r.f2363b).mo1315B(httpContentExchange.getScheme());
            C0957b address = httpContentExchange.getAddress();
            C2133a c2133a = c0962g.f1755v;
            if (address == null) {
                throw new UnknownHostException("Remote socket address cannot be null.");
            }
            C0963h c0963h = c0962g.f1746m.get(address);
            if (c0963h == null && (c0963hPutIfAbsent = c0962g.f1746m.putIfAbsent(address, (c0963h = new C0963h(c0962g, address, zMo1315B, c2133a)))) != null) {
                c0963h = c0963hPutIfAbsent;
            }
            c0963h.m905h(httpContentExchange);
            int iWaitForDone = httpContentExchange.waitForDone();
            if (iWaitForDone == 7) {
                try {
                    return httpContentExchange.createResponse();
                } catch (Throwable th) {
                    Logger logger2 = StreamClientImpl.log;
                    Level level = Level.WARNING;
                    StringBuilder sbM112a2 = C0413b.m112a("Error reading response: ");
                    sbM112a2.append(streamRequestMessage);
                    logger2.log(level, sbM112a2.toString(), C2074b.m2475O(th));
                    return null;
                }
            }
            if (iWaitForDone == 11 || iWaitForDone == 9) {
                return null;
            }
            StreamClientImpl.log.warning("Unhandled HTTP exchange status: " + iWaitForDone);
            return null;
        }
    }

    public static class HttpContentExchange extends C0961f {
        public final C0962g client;
        public final StreamClientConfigurationImpl configuration;
        public Throwable exception;
        public final StreamRequestMessage requestMessage;

        public HttpContentExchange(StreamClientConfigurationImpl streamClientConfigurationImpl, C0962g c0962g, StreamRequestMessage streamRequestMessage) {
            super(true);
            this.configuration = streamClientConfigurationImpl;
            this.client = c0962g;
            this.requestMessage = streamRequestMessage;
            applyRequestURLMethod();
            applyRequestHeaders();
            applyRequestBody();
        }

        public void applyRequestBody() {
            if (getRequestMessage().hasBody()) {
                if (getRequestMessage().getBodyType() == UpnpMessage.BodyType.STRING) {
                    if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                        Logger logger = StreamClientImpl.log;
                        StringBuilder sbM112a = C0413b.m112a("Writing textual request body: ");
                        sbM112a.append(getRequestMessage());
                        logger.fine(sbM112a.toString());
                    }
                    C1448b value = getRequestMessage().getContentTypeHeader() != null ? getRequestMessage().getContentTypeHeader().getValue() : ContentTypeHeader.DEFAULT_CONTENT_TYPE_UTF8;
                    String contentTypeCharset = getRequestMessage().getContentTypeCharset() != null ? getRequestMessage().getContentTypeCharset() : "UTF-8";
                    setRequestContentType(value.toString());
                    try {
                        C1158k c1158k = new C1158k(getRequestMessage().getBodyString(), contentTypeCharset);
                        setRequestHeader("Content-Length", String.valueOf(c1158k.length()));
                        setRequestContent(c1158k);
                        return;
                    } catch (UnsupportedEncodingException e7) {
                        throw new RuntimeException(C0063n.m88a("Unsupported character encoding: ", contentTypeCharset), e7);
                    }
                }
                if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                    Logger logger2 = StreamClientImpl.log;
                    StringBuilder sbM112a2 = C0413b.m112a("Writing binary request body: ");
                    sbM112a2.append(getRequestMessage());
                    logger2.fine(sbM112a2.toString());
                }
                if (getRequestMessage().getContentTypeHeader() == null) {
                    StringBuilder sbM112a3 = C0413b.m112a("Missing content type header in request message: ");
                    sbM112a3.append(this.requestMessage);
                    throw new RuntimeException(sbM112a3.toString());
                }
                setRequestContentType(getRequestMessage().getContentTypeHeader().getValue().toString());
                byte[] bodyBytes = getRequestMessage().getBodyBytes();
                C1158k c1158k2 = new C1158k(bodyBytes, 0, bodyBytes.length, 2);
                setRequestHeader("Content-Length", String.valueOf(c1158k2.length()));
                setRequestContent(c1158k2);
            }
        }

        public void applyRequestHeaders() {
            UpnpHeaders headers = getRequestMessage().getHeaders();
            if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                Logger logger = StreamClientImpl.log;
                StringBuilder sbM112a = C0413b.m112a("Writing headers on HttpContentExchange: ");
                sbM112a.append(headers.size());
                logger.fine(sbM112a.toString());
            }
            UpnpHeader.Type type = UpnpHeader.Type.USER_AGENT;
            if (!headers.containsKey(type)) {
                setRequestHeader(type.getHttpName(), getConfiguration().getUserAgentValue(getRequestMessage().getUdaMajorVersion(), getRequestMessage().getUdaMinorVersion()));
            }
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                for (String str : entry.getValue()) {
                    String key = entry.getKey();
                    if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                        StreamClientImpl.log.fine("Setting header '" + key + "': " + str);
                    }
                    addRequestHeader(key, str);
                }
            }
        }

        public void applyRequestURLMethod() {
            UpnpRequest operation = getRequestMessage().getOperation();
            if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                Logger logger = StreamClientImpl.log;
                StringBuilder sbM112a = C0413b.m112a("Preparing HTTP request message with method '");
                sbM112a.append(operation.getHttpMethodName());
                sbM112a.append("': ");
                sbM112a.append(getRequestMessage());
                logger.fine(sbM112a.toString());
            }
            setURL(operation.getURI().toString());
            setMethod(operation.getHttpMethodName());
        }

        public StreamResponseMessage createResponse() {
            ArrayList arrayList;
            UpnpResponse upnpResponse = new UpnpResponse(getResponseStatus(), UpnpResponse.Status.getByStatusCode(getResponseStatus()).getStatusMsg());
            if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                StreamClientImpl.log.fine("Received response: " + upnpResponse);
            }
            StreamResponseMessage streamResponseMessage = new StreamResponseMessage(upnpResponse);
            UpnpHeaders upnpHeaders = new UpnpHeaders();
            C1101i responseFields = getResponseFields();
            Objects.requireNonNull(responseFields);
            ArrayList arrayList2 = new ArrayList(responseFields.f2296a.size());
            Iterator<C1101i.e> it = responseFields.f2296a.iterator();
            while (it.hasNext()) {
                C1101i.e next = it.next();
                if (next != null) {
                    arrayList2.add(C1155h.m1365c(next.f2301a));
                }
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                String str = (String) it2.next();
                C1101i.e eVarM1226g = responseFields.m1226g(str);
                if (eVarM1226g == null) {
                    arrayList = null;
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    while (eVarM1226g != null) {
                        arrayList3.add(eVarM1226g.m1233a());
                        eVarM1226g = eVarM1226g.f2303c;
                    }
                    arrayList = arrayList3;
                }
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    upnpHeaders.add(str, (String) it3.next());
                }
            }
            streamResponseMessage.setHeaders(upnpHeaders);
            byte[] responseContentBytes = getResponseContentBytes();
            if (responseContentBytes != null && responseContentBytes.length > 0 && streamResponseMessage.isContentTypeMissingOrText()) {
                if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                    StreamClientImpl.log.fine("Response contains textual entity body, converting then setting string on message");
                }
                try {
                    streamResponseMessage.setBodyCharacters(responseContentBytes);
                } catch (UnsupportedEncodingException e7) {
                    throw new RuntimeException("Unsupported character encoding: " + e7, e7);
                }
            } else if (responseContentBytes != null && responseContentBytes.length > 0) {
                if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                    StreamClientImpl.log.fine("Response contains binary entity body, setting bytes on message");
                }
                streamResponseMessage.setBody(UpnpMessage.BodyType.BYTES, responseContentBytes);
            } else if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                StreamClientImpl.log.fine("Response did not contain entity body");
            }
            if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                StreamClientImpl.log.fine("Response message complete: " + streamResponseMessage);
            }
            return streamResponseMessage;
        }

        public StreamClientConfigurationImpl getConfiguration() {
            return this.configuration;
        }

        public StreamRequestMessage getRequestMessage() {
            return this.requestMessage;
        }

        @Override // p041e5.C0965j
        public void onConnectionFailed(Throwable th) {
            Logger logger = StreamClientImpl.log;
            Level level = Level.WARNING;
            StringBuilder sbM112a = C0413b.m112a("HTTP connection failed: ");
            sbM112a.append(this.requestMessage);
            logger.log(level, sbM112a.toString(), C2074b.m2475O(th));
        }

        @Override // p041e5.C0965j
        public void onException(Throwable th) {
            Logger logger = StreamClientImpl.log;
            Level level = Level.WARNING;
            StringBuilder sbM112a = C0413b.m112a("HTTP request failed: ");
            sbM112a.append(this.requestMessage);
            logger.log(level, sbM112a.toString(), C2074b.m2475O(th));
        }
    }

    public StreamClientImpl(StreamClientConfigurationImpl streamClientConfigurationImpl) {
        this.configuration = streamClientConfigurationImpl;
        log.info("Starting Jetty HttpClient...");
        C0962g c0962g = new C0962g();
        this.client = c0962g;
        C17201 c17201 = new C0041a(getConfiguration().getRequestExecutorService()) { // from class: org.fourthline.cling.transport.impl.jetty.StreamClientImpl.1
            public C17201(ExecutorService executorService) {
                super(executorService);
            }

            @Override // p007a6.C0041a, p168u5.AbstractC1980a
            public void doStop() {
            }
        };
        c0962g.mo1799L(c0962g.f1747n);
        c0962g.f1747n = c17201;
        c0962g.mo1798G(c17201);
        c0962g.f1750q = (streamClientConfigurationImpl.getTimeoutSeconds() + 5) * 1000;
        c0962g.f1751r = (streamClientConfigurationImpl.getTimeoutSeconds() + 5) * 1000;
        c0962g.f1754u = streamClientConfigurationImpl.getRequestRetryCount();
        try {
            c0962g.start();
        } catch (Exception e7) {
            throw new InitializationException("Could not start Jetty HTTP client: " + e7, e7);
        }
    }

    @Override // org.fourthline.cling.transport.spi.AbstractStreamClient
    public boolean logExecutionException(Throwable th) {
        return false;
    }

    @Override // org.fourthline.cling.transport.spi.StreamClient
    public void stop() {
        try {
            this.client.stop();
        } catch (Exception e7) {
            log.info("Error stopping HTTP client: " + e7);
        }
    }

    @Override // org.fourthline.cling.transport.spi.AbstractStreamClient
    public void abort(HttpContentExchange httpContentExchange) {
        httpContentExchange.cancel();
    }

    @Override // org.fourthline.cling.transport.spi.AbstractStreamClient
    public Callable<StreamResponseMessage> createCallable(StreamRequestMessage streamRequestMessage, HttpContentExchange httpContentExchange) {
        return new Callable<StreamResponseMessage>() { // from class: org.fourthline.cling.transport.impl.jetty.StreamClientImpl.2
            public final /* synthetic */ HttpContentExchange val$exchange;
            public final /* synthetic */ StreamRequestMessage val$requestMessage;

            public CallableC17212(StreamRequestMessage streamRequestMessage2, HttpContentExchange httpContentExchange2) {
                streamRequestMessage = streamRequestMessage2;
                httpContentExchange = httpContentExchange2;
            }

            @Override // java.util.concurrent.Callable
            public StreamResponseMessage call() throws UnknownHostException {
                C0963h c0963hPutIfAbsent;
                if (StreamClientImpl.log.isLoggable(Level.FINE)) {
                    Logger logger = StreamClientImpl.log;
                    StringBuilder sbM112a = C0413b.m112a("Sending HTTP request: ");
                    sbM112a.append(streamRequestMessage);
                    logger.fine(sbM112a.toString());
                }
                C0962g c0962g = StreamClientImpl.this.client;
                HttpContentExchange httpContentExchange2 = httpContentExchange;
                Objects.requireNonNull(c0962g);
                boolean zMo1315B = ((C1158k) C1110r.f2363b).mo1315B(httpContentExchange2.getScheme());
                C0957b address = httpContentExchange2.getAddress();
                C2133a c2133a = c0962g.f1755v;
                if (address == null) {
                    throw new UnknownHostException("Remote socket address cannot be null.");
                }
                C0963h c0963h = c0962g.f1746m.get(address);
                if (c0963h == null && (c0963hPutIfAbsent = c0962g.f1746m.putIfAbsent(address, (c0963h = new C0963h(c0962g, address, zMo1315B, c2133a)))) != null) {
                    c0963h = c0963hPutIfAbsent;
                }
                c0963h.m905h(httpContentExchange2);
                int iWaitForDone = httpContentExchange.waitForDone();
                if (iWaitForDone == 7) {
                    try {
                        return httpContentExchange.createResponse();
                    } catch (Throwable th) {
                        Logger logger2 = StreamClientImpl.log;
                        Level level = Level.WARNING;
                        StringBuilder sbM112a2 = C0413b.m112a("Error reading response: ");
                        sbM112a2.append(streamRequestMessage);
                        logger2.log(level, sbM112a2.toString(), C2074b.m2475O(th));
                        return null;
                    }
                }
                if (iWaitForDone == 11 || iWaitForDone == 9) {
                    return null;
                }
                StreamClientImpl.log.warning("Unhandled HTTP exchange status: " + iWaitForDone);
                return null;
            }
        };
    }

    @Override // org.fourthline.cling.transport.spi.AbstractStreamClient
    public HttpContentExchange createRequest(StreamRequestMessage streamRequestMessage) {
        return new HttpContentExchange(getConfiguration(), this.client, streamRequestMessage);
    }

    @Override // org.fourthline.cling.transport.spi.StreamClient
    public StreamClientConfigurationImpl getConfiguration() {
        return this.configuration;
    }
}
