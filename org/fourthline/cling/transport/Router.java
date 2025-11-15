package org.fourthline.cling.transport;

import java.net.InetAddress;
import java.util.List;
import org.fourthline.cling.UpnpServiceConfiguration;
import org.fourthline.cling.model.NetworkAddress;
import org.fourthline.cling.model.message.IncomingDatagramMessage;
import org.fourthline.cling.model.message.OutgoingDatagramMessage;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.protocol.ProtocolFactory;
import org.fourthline.cling.transport.spi.InitializationException;
import org.fourthline.cling.transport.spi.UpnpStream;

/* loaded from: classes.dex */
public interface Router {
    void broadcast(byte[] bArr);

    boolean disable();

    boolean enable();

    List<NetworkAddress> getActiveStreamServers(InetAddress inetAddress);

    UpnpServiceConfiguration getConfiguration();

    ProtocolFactory getProtocolFactory();

    void handleStartFailure(InitializationException initializationException);

    boolean isEnabled();

    void received(IncomingDatagramMessage incomingDatagramMessage);

    void received(UpnpStream upnpStream);

    StreamResponseMessage send(StreamRequestMessage streamRequestMessage);

    void send(OutgoingDatagramMessage outgoingDatagramMessage);

    void shutdown();
}
