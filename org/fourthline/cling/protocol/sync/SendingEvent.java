package org.fourthline.cling.protocol.sync;

import java.net.URL;
import java.util.Iterator;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.gena.LocalGENASubscription;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.gena.OutgoingEventRequestMessage;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.protocol.SendingSync;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SendingEvent extends SendingSync<OutgoingEventRequestMessage, StreamResponseMessage> {
    private static final Logger log = Logger.getLogger(SendingEvent.class.getName());
    public final UnsignedIntegerFourBytes currentSequence;
    public final OutgoingEventRequestMessage[] requestMessages;
    public final String subscriptionId;

    public SendingEvent(UpnpService upnpService, LocalGENASubscription localGENASubscription) {
        super(upnpService, null);
        this.subscriptionId = localGENASubscription.getSubscriptionId();
        this.requestMessages = new OutgoingEventRequestMessage[localGENASubscription.getCallbackURLs().size()];
        Iterator<URL> it = localGENASubscription.getCallbackURLs().iterator();
        int i7 = 0;
        while (it.hasNext()) {
            this.requestMessages[i7] = new OutgoingEventRequestMessage(localGENASubscription, it.next());
            getUpnpService().getConfiguration().getGenaEventProcessor().writeBody(this.requestMessages[i7]);
            i7++;
        }
        this.currentSequence = localGENASubscription.getCurrentSequence();
        localGENASubscription.incrementSequence();
    }

    @Override // org.fourthline.cling.protocol.SendingSync
    public StreamResponseMessage executeSync() {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Sending event for subscription: ");
        sbM112a.append(this.subscriptionId);
        logger.fine(sbM112a.toString());
        StreamResponseMessage streamResponseMessageSend = null;
        for (OutgoingEventRequestMessage outgoingEventRequestMessage : this.requestMessages) {
            if (this.currentSequence.getValue().longValue() == 0) {
                Logger logger2 = log;
                StringBuilder sbM112a2 = C0413b.m112a("Sending initial event message to callback URL: ");
                sbM112a2.append(outgoingEventRequestMessage.getUri());
                logger2.fine(sbM112a2.toString());
            } else {
                Logger logger3 = log;
                StringBuilder sbM112a3 = C0413b.m112a("Sending event message '");
                sbM112a3.append(this.currentSequence);
                sbM112a3.append("' to callback URL: ");
                sbM112a3.append(outgoingEventRequestMessage.getUri());
                logger3.fine(sbM112a3.toString());
            }
            streamResponseMessageSend = getUpnpService().getRouter().send(outgoingEventRequestMessage);
            log.fine("Received event callback response: " + streamResponseMessageSend);
        }
        return streamResponseMessageSend;
    }
}
