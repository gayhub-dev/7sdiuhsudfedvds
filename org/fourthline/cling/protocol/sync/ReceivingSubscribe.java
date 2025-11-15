package org.fourthline.cling.protocol.sync;

import java.net.URL;
import java.util.List;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.LocalGENASubscription;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.message.gena.IncomingSubscribeRequestMessage;
import org.fourthline.cling.model.message.gena.OutgoingSubscribeResponseMessage;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.resource.ServiceEventSubscriptionResource;
import org.fourthline.cling.protocol.ReceivingSync;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class ReceivingSubscribe extends ReceivingSync<StreamRequestMessage, OutgoingSubscribeResponseMessage> {
    private static final Logger log = Logger.getLogger(ReceivingSubscribe.class.getName());
    public LocalGENASubscription subscription;

    public ReceivingSubscribe(UpnpService upnpService, StreamRequestMessage streamRequestMessage) {
        super(upnpService, streamRequestMessage);
    }

    public OutgoingSubscribeResponseMessage processNewSubscription(LocalService localService, IncomingSubscribeRequestMessage incomingSubscribeRequestMessage) {
        List<URL> callbackURLs = incomingSubscribeRequestMessage.getCallbackURLs();
        if (callbackURLs == null || callbackURLs.size() == 0) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Missing or invalid Callback URLs in subscribe request: ");
            sbM112a.append(getInputMessage());
            logger.fine(sbM112a.toString());
            return new OutgoingSubscribeResponseMessage(UpnpResponse.Status.PRECONDITION_FAILED);
        }
        if (!incomingSubscribeRequestMessage.hasNotificationHeader()) {
            Logger logger2 = log;
            StringBuilder sbM112a2 = C0413b.m112a("Missing or invalid NT header in subscribe request: ");
            sbM112a2.append(getInputMessage());
            logger2.fine(sbM112a2.toString());
            return new OutgoingSubscribeResponseMessage(UpnpResponse.Status.PRECONDITION_FAILED);
        }
        try {
            this.subscription = new LocalGENASubscription(localService, getUpnpService().getConfiguration().isReceivedSubscriptionTimeoutIgnored() ? null : incomingSubscribeRequestMessage.getRequestedTimeoutSeconds(), callbackURLs) { // from class: org.fourthline.cling.protocol.sync.ReceivingSubscribe.1
                @Override // org.fourthline.cling.model.gena.LocalGENASubscription
                public void ended(CancelReason cancelReason) {
                }

                @Override // org.fourthline.cling.model.gena.GENASubscription
                public void established() {
                }

                @Override // org.fourthline.cling.model.gena.GENASubscription
                public void eventReceived() {
                    ReceivingSubscribe.this.getUpnpService().getConfiguration().getSyncProtocolExecutorService().execute(ReceivingSubscribe.this.getUpnpService().getProtocolFactory().createSendingEvent(this));
                }
            };
            Logger logger3 = log;
            StringBuilder sbM112a3 = C0413b.m112a("Adding subscription to registry: ");
            sbM112a3.append(this.subscription);
            logger3.fine(sbM112a3.toString());
            getUpnpService().getRegistry().addLocalSubscription(this.subscription);
            logger3.fine("Returning subscription response, waiting to send initial event");
            return new OutgoingSubscribeResponseMessage(this.subscription);
        } catch (Exception e7) {
            Logger logger4 = log;
            StringBuilder sbM112a4 = C0413b.m112a("Couldn't create local subscription to service: ");
            sbM112a4.append(C2074b.m2475O(e7));
            logger4.warning(sbM112a4.toString());
            return new OutgoingSubscribeResponseMessage(UpnpResponse.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public OutgoingSubscribeResponseMessage processRenewal(LocalService localService, IncomingSubscribeRequestMessage incomingSubscribeRequestMessage) {
        LocalGENASubscription localSubscription = getUpnpService().getRegistry().getLocalSubscription(incomingSubscribeRequestMessage.getSubscriptionId());
        this.subscription = localSubscription;
        if (localSubscription == null) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Invalid subscription ID for renewal request: ");
            sbM112a.append(getInputMessage());
            logger.fine(sbM112a.toString());
            return new OutgoingSubscribeResponseMessage(UpnpResponse.Status.PRECONDITION_FAILED);
        }
        Logger logger2 = log;
        StringBuilder sbM112a2 = C0413b.m112a("Renewing subscription: ");
        sbM112a2.append(this.subscription);
        logger2.fine(sbM112a2.toString());
        this.subscription.setSubscriptionDuration(incomingSubscribeRequestMessage.getRequestedTimeoutSeconds());
        if (getUpnpService().getRegistry().updateLocalSubscription(this.subscription)) {
            return new OutgoingSubscribeResponseMessage(this.subscription);
        }
        StringBuilder sbM112a3 = C0413b.m112a("Subscription went away before it could be renewed: ");
        sbM112a3.append(getInputMessage());
        logger2.fine(sbM112a3.toString());
        return new OutgoingSubscribeResponseMessage(UpnpResponse.Status.PRECONDITION_FAILED);
    }

    @Override // org.fourthline.cling.protocol.ReceivingSync
    public void responseException(Throwable th) {
        if (this.subscription == null) {
            return;
        }
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Response could not be send to subscriber, removing local GENA subscription: ");
        sbM112a.append(this.subscription);
        logger.fine(sbM112a.toString());
        getUpnpService().getRegistry().removeLocalSubscription(this.subscription);
    }

    @Override // org.fourthline.cling.protocol.ReceivingSync
    public void responseSent(StreamResponseMessage streamResponseMessage) {
        if (this.subscription == null) {
            return;
        }
        if (streamResponseMessage != null && !streamResponseMessage.getOperation().isFailed() && this.subscription.getCurrentSequence().getValue().longValue() == 0) {
            Logger logger = log;
            logger.fine("Establishing subscription");
            this.subscription.registerOnService();
            this.subscription.establish();
            logger.fine("Response to subscription sent successfully, now sending initial event asynchronously");
            getUpnpService().getConfiguration().getAsyncProtocolExecutor().execute(getUpnpService().getProtocolFactory().createSendingEvent(this.subscription));
            return;
        }
        if (this.subscription.getCurrentSequence().getValue().longValue() == 0) {
            Logger logger2 = log;
            logger2.fine("Subscription request's response aborted, not sending initial event");
            if (streamResponseMessage == null) {
                logger2.fine("Reason: No response at all from subscriber");
            } else {
                StringBuilder sbM112a = C0413b.m112a("Reason: ");
                sbM112a.append(streamResponseMessage.getOperation());
                logger2.fine(sbM112a.toString());
            }
            StringBuilder sbM112a2 = C0413b.m112a("Removing subscription from registry: ");
            sbM112a2.append(this.subscription);
            logger2.fine(sbM112a2.toString());
            getUpnpService().getRegistry().removeLocalSubscription(this.subscription);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.fourthline.cling.protocol.ReceivingSync
    public OutgoingSubscribeResponseMessage executeSync() {
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
        IncomingSubscribeRequestMessage incomingSubscribeRequestMessage = new IncomingSubscribeRequestMessage((StreamRequestMessage) getInputMessage(), serviceEventSubscriptionResource.getModel());
        if (incomingSubscribeRequestMessage.getSubscriptionId() != null && (incomingSubscribeRequestMessage.hasNotificationHeader() || incomingSubscribeRequestMessage.getCallbackURLs() != null)) {
            StringBuilder sbM112a3 = C0413b.m112a("Subscription ID and NT or Callback in subscribe request: ");
            sbM112a3.append(getInputMessage());
            logger2.fine(sbM112a3.toString());
            return new OutgoingSubscribeResponseMessage(UpnpResponse.Status.BAD_REQUEST);
        }
        if (incomingSubscribeRequestMessage.getSubscriptionId() != null) {
            return processRenewal(serviceEventSubscriptionResource.getModel(), incomingSubscribeRequestMessage);
        }
        if (incomingSubscribeRequestMessage.hasNotificationHeader() && incomingSubscribeRequestMessage.getCallbackURLs() != null) {
            return processNewSubscription(serviceEventSubscriptionResource.getModel(), incomingSubscribeRequestMessage);
        }
        StringBuilder sbM112a4 = C0413b.m112a("No subscription ID, no NT or Callback, neither subscription or renewal: ");
        sbM112a4.append(getInputMessage());
        logger2.fine(sbM112a4.toString());
        return new OutgoingSubscribeResponseMessage(UpnpResponse.Status.PRECONDITION_FAILED);
    }
}
