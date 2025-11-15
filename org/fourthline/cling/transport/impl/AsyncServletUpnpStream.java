package org.fourthline.cling.transport.impl;

import android.arch.lifecycle.C0063n;
import android.text.TextUtils;
import java.io.IOException;
import java.net.URI;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import p006a5.AbstractC0030p;
import p006a5.C0016b;
import p006a5.InterfaceC0014a;
import p006a5.InterfaceC0017c;
import p009b.C0413b;
import p013b3.C0440a;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p106m6.C1500c;
import p113n5.C1542c;
import p113n5.C1550k;
import p113n5.C1554o;
import p118o2.C1581b;
import p186x2.C2073a;

/* loaded from: classes.dex */
public abstract class AsyncServletUpnpStream extends UpnpStream implements InterfaceC0017c {
    private static final Logger log = Logger.getLogger(UpnpStream.class.getName());
    public final InterfaceC0014a asyncContext;
    public final InterfaceC0458c request;
    public StreamResponseMessage responseMessage;

    public AsyncServletUpnpStream(ProtocolFactory protocolFactory, InterfaceC0014a interfaceC0014a, InterfaceC0458c interfaceC0458c) {
        super(protocolFactory);
        this.asyncContext = interfaceC0014a;
        this.request = interfaceC0458c;
        ((C1542c) interfaceC0014a).m1743a(this);
    }

    public void complete() {
        try {
            ((C1542c) this.asyncContext).m1745c();
        } catch (IllegalStateException e7) {
            log.info("Error calling servlet container's AsyncContext#complete() method: " + e7);
        }
    }

    public abstract Connection createConnection();

    public InterfaceC0458c getRequest() {
        return this.request;
    }

    public InterfaceC0460e getResponse() {
        C1542c c1542c = (C1542c) this.asyncContext;
        Objects.requireNonNull(c1542c);
        C1554o c1554o = c1542c.f4568a.f4552m;
        if (c1554o != null) {
            return c1554o;
        }
        throw new IllegalStateException("Couldn't get response from asynchronous context, already timed out");
    }

    @Override // p006a5.InterfaceC0017c
    public void onComplete(C0016b c0016b) {
        Logger logger = log;
        if (logger.isLoggable(Level.FINER)) {
            StringBuilder sbM112a = C0413b.m112a("Completed asynchronous processing of HTTP request: ");
            sbM112a.append(c0016b.f27a);
            logger.finer(sbM112a.toString());
        }
        responseSent(this.responseMessage);
    }

    @Override // p006a5.InterfaceC0017c
    public void onError(C0016b c0016b) {
        Logger logger = log;
        if (logger.isLoggable(Level.FINER)) {
            StringBuilder sbM112a = C0413b.m112a("Asynchronous processing of HTTP request error: ");
            sbM112a.append(c0016b.f29c);
            logger.finer(sbM112a.toString());
        }
        responseException(c0016b.f29c);
    }

    @Override // p006a5.InterfaceC0017c
    public void onStartAsync(C0016b c0016b) {
    }

    @Override // p006a5.InterfaceC0017c
    public void onTimeout(C0016b c0016b) {
        Logger logger = log;
        if (logger.isLoggable(Level.FINER)) {
            StringBuilder sbM112a = C0413b.m112a("Asynchronous processing of HTTP request timed out: ");
            sbM112a.append(c0016b.f27a);
            logger.finer(sbM112a.toString());
        }
        responseException(new Exception("Asynchronous request timed out"));
    }

    public StreamRequestMessage readRequestMessage() throws IOException {
        String strMo165N = getRequest().mo165N();
        String strMo167U = getRequest().mo167U();
        Logger logger = log;
        if (logger.isLoggable(Level.FINER)) {
            logger.finer("Processing HTTP request: " + strMo165N + " " + strMo167U);
        }
        try {
            StreamRequestMessage streamRequestMessage = new StreamRequestMessage(UpnpRequest.Method.getByHttpName(strMo165N), URI.create(strMo167U));
            if (((UpnpRequest) streamRequestMessage.getOperation()).getMethod().equals(UpnpRequest.Method.UNKNOWN)) {
                throw new RuntimeException(C0063n.m88a("Method not supported: ", strMo165N));
            }
            streamRequestMessage.setConnection(createConnection());
            UpnpHeaders upnpHeaders = new UpnpHeaders();
            Enumeration<String> enumerationMo172w = getRequest().mo172w();
            while (enumerationMo172w.hasMoreElements()) {
                String strNextElement = enumerationMo172w.nextElement();
                Enumeration<String> enumerationMo164L = getRequest().mo164L(strNextElement);
                while (enumerationMo164L.hasMoreElements()) {
                    upnpHeaders.add(strNextElement, enumerationMo164L.nextElement());
                }
            }
            streamRequestMessage.setHeaders(upnpHeaders);
            C1550k c1550kMo28c = null;
            try {
                c1550kMo28c = getRequest().mo28c();
                byte[] bArrM1664a = C1500c.m1664a(c1550kMo28c);
                c1550kMo28c.close();
                Logger logger2 = log;
                Level level = Level.FINER;
                if (logger2.isLoggable(level)) {
                    StringBuilder sbM112a = C0413b.m112a("Reading request body bytes: ");
                    sbM112a.append(bArrM1664a.length);
                    logger2.finer(sbM112a.toString());
                }
                if (bArrM1664a.length > 0 && streamRequestMessage.isContentTypeMissingOrText()) {
                    if (logger2.isLoggable(level)) {
                        logger2.finer("Request contains textual entity body, converting then setting string on message");
                    }
                    streamRequestMessage.setBodyCharacters(bArrM1664a);
                } else if (bArrM1664a.length > 0) {
                    if (logger2.isLoggable(level)) {
                        logger2.finer("Request contains binary entity body, setting bytes on message");
                    }
                    streamRequestMessage.setBody(UpnpMessage.BodyType.BYTES, bArrM1664a);
                } else if (logger2.isLoggable(level)) {
                    logger2.finer("Request did not contain entity body");
                }
                return streamRequestMessage;
            } catch (Throwable th) {
                if (c1550kMo28c != null) {
                    c1550kMo28c.close();
                }
                throw th;
            }
        } catch (IllegalArgumentException e7) {
            throw new RuntimeException(C0063n.m88a("Invalid request URI: ", strMo167U), e7);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            StreamRequestMessage requestMessage = readRequestMessage();
            Logger logger = log;
            if (logger.isLoggable(Level.FINER)) {
                logger.finer("Processing new request message: " + requestMessage);
            }
            this.responseMessage = process(requestMessage);
            if (!TextUtils.isEmpty(requestMessage.getBodyString()) && ((requestMessage.getBodyString().contains("<CurrentURI></CurrentURI>") || requestMessage.getBodyString().contains("<CurrentURI>null</CurrentURI>")) && requestMessage.getBodyString().contains("ListenAudioVivid&quot;,&quot;value&quot;:&quot;on&quot;"))) {
                C2073a.m2459d("listenDelay sleep llll t:" + C0440a.m151b(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", "GMT+08"));
                C1581b.m1836e().f4755k = false;
                boolean z6 = true;
                while (z6) {
                    C2073a.m2459d("listenDelay xun llll t:" + C0440a.m151b(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", "GMT+08"));
                    if (C1581b.m1836e().f4755k) {
                        z6 = false;
                    }
                }
                C1581b.m1836e().f4755k = false;
                C2073a.m2459d("listenDelay wake up llll t:" + C0440a.m151b(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", "GMT+08"));
            }
            if (this.responseMessage != null) {
                Logger logger2 = log;
                if (logger2.isLoggable(Level.FINER)) {
                    logger2.finer("Preparing HTTP response message: " + this.responseMessage);
                }
                writeResponseMessage(this.responseMessage);
            } else {
                Logger logger3 = log;
                if (logger3.isLoggable(Level.FINER)) {
                    logger3.finer("Sending HTTP response status: 404");
                }
                getResponse().mo178S(404);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public void writeResponseMessage(StreamResponseMessage streamResponseMessage) throws IOException {
        Logger logger = log;
        if (logger.isLoggable(Level.FINER)) {
            StringBuilder sbM112a = C0413b.m112a("Sending HTTP response status: ");
            sbM112a.append(streamResponseMessage.getOperation().getStatusCode());
            logger.finer(sbM112a.toString());
        }
        getResponse().mo178S(streamResponseMessage.getOperation().getStatusCode());
        for (Map.Entry<String, List<String>> entry : streamResponseMessage.getHeaders().entrySet()) {
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                getResponse().mo182q(entry.getKey(), it.next());
            }
        }
        getResponse().mo181m("Date", System.currentTimeMillis());
        byte[] bodyBytes = streamResponseMessage.hasBody() ? streamResponseMessage.getBodyBytes() : null;
        int length = bodyBytes != null ? bodyBytes.length : -1;
        if (length > 0) {
            getResponse().mo42H(length);
            log.finer("Response message has body, writing bytes to stream...");
            AbstractC0030p abstractC0030pMo45i = getResponse().mo45i();
            int i7 = C1500c.f4301a;
            if (bodyBytes != null) {
                abstractC0030pMo45i.write(bodyBytes);
            }
        }
    }
}
