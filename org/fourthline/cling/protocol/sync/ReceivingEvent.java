package org.fourthline.cling.protocol.sync;

import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.gena.RemoteGENASubscription;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.message.gena.IncomingEventRequestMessage;
import org.fourthline.cling.model.message.gena.OutgoingEventResponseMessage;
import org.fourthline.cling.model.resource.ServiceEventCallbackResource;
import org.fourthline.cling.protocol.ReceivingSync;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ReceivingEvent extends ReceivingSync<StreamRequestMessage, OutgoingEventResponseMessage> {
    private static final Logger log = Logger.getLogger(ReceivingEvent.class.getName());

    public ReceivingEvent(UpnpService upnpService, StreamRequestMessage streamRequestMessage) {
        super(upnpService, streamRequestMessage);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.fourthline.cling.protocol.ReceivingSync
    public OutgoingEventResponseMessage executeSync() {
        if (!((StreamRequestMessage) getInputMessage()).isContentTypeTextUDA()) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Received without or with invalid Content-Type: ");
            sbM112a.append(getInputMessage());
            logger.warning(sbM112a.toString());
        }
        ServiceEventCallbackResource serviceEventCallbackResource = (ServiceEventCallbackResource) getUpnpService().getRegistry().getResource(ServiceEventCallbackResource.class, ((StreamRequestMessage) getInputMessage()).getUri());
        if (serviceEventCallbackResource == null) {
            Logger logger2 = log;
            StringBuilder sbM112a2 = C0413b.m112a("No local resource found: ");
            sbM112a2.append(getInputMessage());
            logger2.fine(sbM112a2.toString());
            return new OutgoingEventResponseMessage(new UpnpResponse(UpnpResponse.Status.NOT_FOUND));
        }
        final IncomingEventRequestMessage incomingEventRequestMessage = new IncomingEventRequestMessage((StreamRequestMessage) getInputMessage(), serviceEventCallbackResource.getModel());
        if (incomingEventRequestMessage.getSubscrptionId() == null) {
            Logger logger3 = log;
            StringBuilder sbM112a3 = C0413b.m112a("Subscription ID missing in event request: ");
            sbM112a3.append(getInputMessage());
            logger3.fine(sbM112a3.toString());
            return new OutgoingEventResponseMessage(new UpnpResponse(UpnpResponse.Status.PRECONDITION_FAILED));
        }
        if (!incomingEventRequestMessage.hasValidNotificationHeaders()) {
            Logger logger4 = log;
            StringBuilder sbM112a4 = C0413b.m112a("Missing NT and/or NTS headers in event request: ");
            sbM112a4.append(getInputMessage());
            logger4.fine(sbM112a4.toString());
            return new OutgoingEventResponseMessage(new UpnpResponse(UpnpResponse.Status.BAD_REQUEST));
        }
        if (!incomingEventRequestMessage.hasValidNotificationHeaders()) {
            Logger logger5 = log;
            StringBuilder sbM112a5 = C0413b.m112a("Invalid NT and/or NTS headers in event request: ");
            sbM112a5.append(getInputMessage());
            logger5.fine(sbM112a5.toString());
            return new OutgoingEventResponseMessage(new UpnpResponse(UpnpResponse.Status.PRECONDITION_FAILED));
        }
        if (incomingEventRequestMessage.getSequence() == null) {
            Logger logger6 = log;
            StringBuilder sbM112a6 = C0413b.m112a("Sequence missing in event request: ");
            sbM112a6.append(getInputMessage());
            logger6.fine(sbM112a6.toString());
            return new OutgoingEventResponseMessage(new UpnpResponse(UpnpResponse.Status.PRECONDITION_FAILED));
        }
        try {
            getUpnpService().getConfiguration().getGenaEventProcessor().readBody(incomingEventRequestMessage);
            final RemoteGENASubscription waitRemoteSubscription = getUpnpService().getRegistry().getWaitRemoteSubscription(incomingEventRequestMessage.getSubscrptionId());
            if (waitRemoteSubscription != null) {
                getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.ReceivingEvent.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ReceivingEvent.log.fine("Calling active subscription with event state variable values");
                        waitRemoteSubscription.receive(incomingEventRequestMessage.getSequence(), incomingEventRequestMessage.getStateVariableValues());
                    }
                });
                return new OutgoingEventResponseMessage();
            }
            log.severe("Invalid subscription ID, no active subscription: " + incomingEventRequestMessage);
            return new OutgoingEventResponseMessage(new UpnpResponse(UpnpResponse.Status.PRECONDITION_FAILED));
        } catch (UnsupportedDataException e7) {
            log.fine("Can't read event message request body, " + e7);
            final RemoteGENASubscription remoteSubscription = getUpnpService().getRegistry().getRemoteSubscription(incomingEventRequestMessage.getSubscrptionId());
            if (remoteSubscription != null) {
                getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.ReceivingEvent.1
                    @Override // java.lang.Runnable
                    public void run() {
                        remoteSubscription.invalidMessage(e7);
                    }
                });
            }
            return new OutgoingEventResponseMessage(new UpnpResponse(UpnpResponse.Status.INTERNAL_SERVER_ERROR));
        }
    }
}
