package org.fourthline.cling.protocol.sync;

import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.gena.LocalGENASubscription;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.message.gena.IncomingUnsubscribeRequestMessage;
import org.fourthline.cling.model.resource.ServiceEventSubscriptionResource;
import org.fourthline.cling.protocol.ReceivingSync;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ReceivingUnsubscribe extends ReceivingSync<StreamRequestMessage, StreamResponseMessage> {
    private static final Logger log = Logger.getLogger(ReceivingUnsubscribe.class.getName());

    public ReceivingUnsubscribe(UpnpService upnpService, StreamRequestMessage streamRequestMessage) {
        super(upnpService, streamRequestMessage);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.fourthline.cling.protocol.ReceivingSync
    public StreamResponseMessage executeSync() {
        ServiceEventSubscriptionResource serviceEventSubscriptionResource = (ServiceEventSubscriptionResource) getUpnpService().getRegistry().getResource(ServiceEventSubscriptionResource.class, ((StreamRequestMessage) getInputMessage()).getUri());
        if (serviceEventSubscriptionResource == null) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("No local resource found: ");
            sbM112a.append(getInputMessage());
            logger.fine(sbM112a.toString());
            return null;
        }
        Logger logger2 = log;
        StringBuilder sbM112a2 = C0413b.m112a("Found local event subscription matching relative request URI: ");
        sbM112a2.append(((StreamRequestMessage) getInputMessage()).getUri());
        logger2.fine(sbM112a2.toString());
        IncomingUnsubscribeRequestMessage incomingUnsubscribeRequestMessage = new IncomingUnsubscribeRequestMessage((StreamRequestMessage) getInputMessage(), serviceEventSubscriptionResource.getModel());
        if (incomingUnsubscribeRequestMessage.getSubscriptionId() != null && (incomingUnsubscribeRequestMessage.hasNotificationHeader() || incomingUnsubscribeRequestMessage.hasCallbackHeader())) {
            StringBuilder sbM112a3 = C0413b.m112a("Subscription ID and NT or Callback in unsubcribe request: ");
            sbM112a3.append(getInputMessage());
            logger2.fine(sbM112a3.toString());
            return new StreamResponseMessage(UpnpResponse.Status.BAD_REQUEST);
        }
        LocalGENASubscription localSubscription = getUpnpService().getRegistry().getLocalSubscription(incomingUnsubscribeRequestMessage.getSubscriptionId());
        if (localSubscription == null) {
            StringBuilder sbM112a4 = C0413b.m112a("Invalid subscription ID for unsubscribe request: ");
            sbM112a4.append(getInputMessage());
            logger2.fine(sbM112a4.toString());
            return new StreamResponseMessage(UpnpResponse.Status.PRECONDITION_FAILED);
        }
        logger2.fine("Unregistering subscription: " + localSubscription);
        if (getUpnpService().getRegistry().removeLocalSubscription(localSubscription)) {
            localSubscription.end(null);
        } else {
            logger2.fine("Subscription was already removed from registry");
        }
        return new StreamResponseMessage(UpnpResponse.Status.OK);
    }
}
