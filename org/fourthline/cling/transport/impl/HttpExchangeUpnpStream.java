package org.fourthline.cling.transport.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.message.Connection;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpHeaders;
import org.fourthline.cling.model.message.UpnpMessage;
import org.fourthline.cling.model.message.UpnpRequest;
import org.fourthline.cling.protocol.ProtocolFactory;
import org.fourthline.cling.transport.spi.UpnpStream;
import p009b.C0413b;
import p106m6.C1500c;
import p159t3.AbstractC1905d;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class HttpExchangeUpnpStream extends UpnpStream {
    private static Logger log = Logger.getLogger(UpnpStream.class.getName());
    private AbstractC1905d httpExchange;

    public HttpExchangeUpnpStream(ProtocolFactory protocolFactory, AbstractC1905d abstractC1905d) {
        super(protocolFactory);
        this.httpExchange = abstractC1905d;
    }

    public abstract Connection createConnection();

    public AbstractC1905d getHttpExchange() {
        return this.httpExchange;
    }

    @Override // java.lang.Runnable
    public void run() {
        InputStream inputStreamMo1988d;
        try {
            log.fine("Processing HTTP request: " + getHttpExchange().mo1990f() + " " + getHttpExchange().mo1991g());
            StreamRequestMessage streamRequestMessage = new StreamRequestMessage(UpnpRequest.Method.getByHttpName(getHttpExchange().mo1990f()), getHttpExchange().mo1991g());
            if (((UpnpRequest) streamRequestMessage.getOperation()).getMethod().equals(UpnpRequest.Method.UNKNOWN)) {
                log.fine("Method not supported by UPnP stack: " + getHttpExchange().mo1990f());
                throw new RuntimeException("Method not supported: " + getHttpExchange().mo1990f());
            }
            ((UpnpRequest) streamRequestMessage.getOperation()).setHttpMinorVersion(getHttpExchange().mo1986b().toUpperCase(Locale.ROOT).equals("HTTP/1.1") ? 1 : 0);
            log.fine("Created new request message: " + streamRequestMessage);
            streamRequestMessage.setConnection(createConnection());
            streamRequestMessage.setHeaders(new UpnpHeaders(getHttpExchange().mo1989e()));
            InputStream inputStream = null;
            OutputStream outputStreamMo1992h = null;
            try {
                inputStreamMo1988d = getHttpExchange().mo1988d();
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bArrM1664a = C1500c.m1664a(inputStreamMo1988d);
                inputStreamMo1988d.close();
                log.fine("Reading request body bytes: " + bArrM1664a.length);
                if (bArrM1664a.length > 0 && streamRequestMessage.isContentTypeMissingOrText()) {
                    log.fine("Request contains textual entity body, converting then setting string on message");
                    streamRequestMessage.setBodyCharacters(bArrM1664a);
                } else if (bArrM1664a.length > 0) {
                    log.fine("Request contains binary entity body, setting bytes on message");
                    streamRequestMessage.setBody(UpnpMessage.BodyType.BYTES, bArrM1664a);
                } else {
                    log.fine("Request did not contain entity body");
                }
                StreamResponseMessage streamResponseMessageProcess = process(streamRequestMessage);
                if (streamResponseMessageProcess != null) {
                    log.fine("Preparing HTTP response message: " + streamResponseMessageProcess);
                    getHttpExchange().mo1993i().putAll(streamResponseMessageProcess.getHeaders());
                    byte[] bodyBytes = streamResponseMessageProcess.hasBody() ? streamResponseMessageProcess.getBodyBytes() : null;
                    int length = bodyBytes != null ? bodyBytes.length : -1;
                    log.fine("Sending HTTP response message: " + streamResponseMessageProcess + " with content length: " + length);
                    getHttpExchange().mo1994j(streamResponseMessageProcess.getOperation().getStatusCode(), (long) length);
                    if (length > 0) {
                        log.fine("Response message has body, writing bytes to stream...");
                        try {
                            outputStreamMo1992h = getHttpExchange().mo1992h();
                            if (bodyBytes != null) {
                                outputStreamMo1992h.write(bodyBytes);
                            }
                            outputStreamMo1992h.flush();
                            outputStreamMo1992h.close();
                        } catch (Throwable th2) {
                            if (outputStreamMo1992h != null) {
                                outputStreamMo1992h.close();
                            }
                            throw th2;
                        }
                    }
                } else {
                    log.fine("Sending HTTP response status: 404");
                    getHttpExchange().mo1994j(404, -1L);
                }
                responseSent(streamResponseMessageProcess);
            } catch (Throwable th3) {
                th = th3;
                inputStream = inputStreamMo1988d;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            log.fine("Exception occured during UPnP stream processing: " + th4);
            Logger logger = log;
            Level level = Level.FINE;
            if (logger.isLoggable(level)) {
                Logger logger2 = log;
                StringBuilder sbM112a = C0413b.m112a("Cause: ");
                sbM112a.append(C2074b.m2475O(th4));
                logger2.log(level, sbM112a.toString(), C2074b.m2475O(th4));
            }
            try {
                this.httpExchange.mo1994j(500, -1L);
            } catch (IOException e7) {
                log.warning("Couldn't send error response: " + e7);
            }
            responseException(th4);
        }
    }
}
