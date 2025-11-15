package org.fourthline.cling.transport.spi;

import org.fourthline.cling.model.message.gena.IncomingEventRequestMessage;
import org.fourthline.cling.model.message.gena.OutgoingEventRequestMessage;

/* loaded from: classes.dex */
public interface GENAEventProcessor {
    void readBody(IncomingEventRequestMessage incomingEventRequestMessage);

    void writeBody(OutgoingEventRequestMessage outgoingEventRequestMessage);
}
