package org.fourthline.cling.model.message;

import java.net.InetAddress;
import org.fourthline.cling.model.message.UpnpMessage;
import org.fourthline.cling.model.message.UpnpOperation;

/* loaded from: classes.dex */
public abstract class OutgoingDatagramMessage<O extends UpnpOperation> extends UpnpMessage<O> {
    private InetAddress destinationAddress;
    private int destinationPort;
    private UpnpHeaders headers;

    public OutgoingDatagramMessage(O o7, InetAddress inetAddress, int i7) {
        super(o7);
        this.headers = new UpnpHeaders(false);
        this.destinationAddress = inetAddress;
        this.destinationPort = i7;
    }

    public InetAddress getDestinationAddress() {
        return this.destinationAddress;
    }

    public int getDestinationPort() {
        return this.destinationPort;
    }

    @Override // org.fourthline.cling.model.message.UpnpMessage
    public UpnpHeaders getHeaders() {
        return this.headers;
    }

    public OutgoingDatagramMessage(O o7, UpnpMessage.BodyType bodyType, Object obj, InetAddress inetAddress, int i7) {
        super(o7, bodyType, obj);
        this.headers = new UpnpHeaders(false);
        this.destinationAddress = inetAddress;
        this.destinationPort = i7;
    }
}
