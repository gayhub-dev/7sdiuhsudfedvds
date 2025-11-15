package org.fourthline.cling.transport.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLStreamHandlerFactory;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpHeaders;
import org.fourthline.cling.model.message.UpnpMessage;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.message.header.UpnpHeader;
import org.fourthline.cling.transport.spi.InitializationException;
import org.fourthline.cling.transport.spi.StreamClient;
import p009b.C0413b;
import p082j6.C1212a;
import p106m6.C1500c;

/* loaded from: classes.dex */
public class StreamClientImpl implements StreamClient {
    public static final String HACK_STREAM_HANDLER_SYSTEM_PROPERTY = "hackStreamHandlerProperty";
    private static final Logger log = Logger.getLogger(StreamClient.class.getName());
    public final StreamClientConfigurationImpl configuration;

    public StreamClientImpl(StreamClientConfigurationImpl streamClientConfigurationImpl) {
        this.configuration = streamClientConfigurationImpl;
        if (ModelUtil.ANDROID_EMULATOR || ModelUtil.ANDROID_RUNTIME) {
            throw new InitializationException("This client does not work on Android. The design of HttpURLConnection is broken, we can not add additional 'permitted' HTTP methods. Read the Cling manual.");
        }
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Using persistent HTTP stream client connections: ");
        sbM112a.append(streamClientConfigurationImpl.isUsePersistentConnections());
        logger.fine(sbM112a.toString());
        System.setProperty("http.keepAlive", Boolean.toString(streamClientConfigurationImpl.isUsePersistentConnections()));
        if (System.getProperty(HACK_STREAM_HANDLER_SYSTEM_PROPERTY) == null) {
            logger.fine("Setting custom static URLStreamHandlerFactory to work around bad JDK defaults");
            URL.setURLStreamHandlerFactory((URLStreamHandlerFactory) Class.forName("org.fourthline.cling.transport.impl.FixedSunURLStreamHandler").newInstance());
            System.setProperty(HACK_STREAM_HANDLER_SYSTEM_PROPERTY, "alreadyWorkedAroundTheEvilJDK");
        }
    }

    public void applyHeaders(HttpURLConnection httpURLConnection, C1212a c1212a) {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Writing headers on HttpURLConnection: ");
        sbM112a.append(c1212a.size());
        logger.fine(sbM112a.toString());
        for (Map.Entry<String, List<String>> entry : c1212a.entrySet()) {
            for (String str : entry.getValue()) {
                String key = entry.getKey();
                log.fine("Setting header '" + key + "': " + str);
                httpURLConnection.setRequestProperty(key, str);
            }
        }
    }

    public void applyRequestBody(HttpURLConnection httpURLConnection, StreamRequestMessage streamRequestMessage) throws IOException {
        if (!streamRequestMessage.hasBody()) {
            httpURLConnection.setDoOutput(false);
            return;
        }
        httpURLConnection.setDoOutput(true);
        if (streamRequestMessage.getBodyType().equals(UpnpMessage.BodyType.STRING)) {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            String bodyString = streamRequestMessage.getBodyString();
            int i7 = C1500c.f4301a;
            if (bodyString != null) {
                outputStream.write(bodyString.getBytes("UTF-8"));
            }
        } else if (streamRequestMessage.getBodyType().equals(UpnpMessage.BodyType.BYTES)) {
            OutputStream outputStream2 = httpURLConnection.getOutputStream();
            byte[] bodyBytes = streamRequestMessage.getBodyBytes();
            int i8 = C1500c.f4301a;
            if (bodyBytes != null) {
                outputStream2.write(bodyBytes);
            }
        }
        httpURLConnection.getOutputStream().flush();
    }

    public void applyRequestProperties(HttpURLConnection httpURLConnection, StreamRequestMessage streamRequestMessage) {
        httpURLConnection.setInstanceFollowRedirects(false);
        UpnpHeaders headers = streamRequestMessage.getHeaders();
        UpnpHeader.Type type = UpnpHeader.Type.USER_AGENT;
        if (!headers.containsKey(type)) {
            httpURLConnection.setRequestProperty(type.getHttpName(), getConfiguration().getUserAgentValue(streamRequestMessage.getUdaMajorVersion(), streamRequestMessage.getUdaMinorVersion()));
        }
        applyHeaders(httpURLConnection, streamRequestMessage.getHeaders());
    }

    public StreamResponseMessage createResponse(HttpURLConnection httpURLConnection, InputStream inputStream) throws IOException {
        byte[] bArrM1664a = null;
        if (httpURLConnection.getResponseCode() == -1) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Received an invalid HTTP response: ");
            sbM112a.append(httpURLConnection.getURL());
            logger.warning(sbM112a.toString());
            logger.warning("Is your Cling-based server sending connection heartbeats with RemoteClientInfo#isRequestCancelled? This client can't handle heartbeats, read the manual.");
            return null;
        }
        UpnpResponse upnpResponse = new UpnpResponse(httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage());
        Logger logger2 = log;
        logger2.fine("Received response: " + upnpResponse);
        StreamResponseMessage streamResponseMessage = new StreamResponseMessage(upnpResponse);
        streamResponseMessage.setHeaders(new UpnpHeaders(httpURLConnection.getHeaderFields()));
        if (inputStream != null) {
            try {
                bArrM1664a = C1500c.m1664a(inputStream);
            } finally {
                inputStream.close();
            }
        }
        if (inputStream != null) {
        }
        if (bArrM1664a != null && bArrM1664a.length > 0 && streamResponseMessage.isContentTypeMissingOrText()) {
            logger2.fine("Response contains textual entity body, converting then setting string on message");
            streamResponseMessage.setBodyCharacters(bArrM1664a);
        } else if (bArrM1664a == null || bArrM1664a.length <= 0) {
            logger2.fine("Response did not contain entity body");
        } else {
            logger2.fine("Response contains binary entity body, setting bytes on message");
            streamResponseMessage.setBody(UpnpMessage.BodyType.BYTES, bArrM1664a);
        }
        logger2.fine("Response message complete: " + streamResponseMessage);
        return streamResponseMessage;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0175  */
    @Override // org.fourthline.cling.transport.spi.StreamClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.fourthline.cling.model.message.StreamResponseMessage sendRequest(org.fourthline.cling.model.message.StreamRequestMessage r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 384
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fourthline.cling.transport.impl.StreamClientImpl.sendRequest(org.fourthline.cling.model.message.StreamRequestMessage):org.fourthline.cling.model.message.StreamResponseMessage");
    }

    @Override // org.fourthline.cling.transport.spi.StreamClient
    public void stop() {
    }

    @Override // org.fourthline.cling.transport.spi.StreamClient
    public StreamClientConfigurationImpl getConfiguration() {
        return this.configuration;
    }
}
