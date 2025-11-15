package org.fourthline.cling.protocol.sync;

import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.RemoteGENASubscription;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.gena.IncomingSubscribeResponseMessage;
import org.fourthline.cling.model.message.gena.OutgoingRenewalRequestMessage;
import org.fourthline.cling.protocol.SendingSync;
import org.fourthline.cling.transport.RouterException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SendingRenewal extends SendingSync<OutgoingRenewalRequestMessage, IncomingSubscribeResponseMessage> {
    private static final Logger log = Logger.getLogger(SendingRenewal.class.getName());
    public final RemoteGENASubscription subscription;

    /* renamed from: org.fourthline.cling.protocol.sync.SendingRenewal$1 */
    public class RunnableC16761 implements Runnable {
        public final /* synthetic */ IncomingSubscribeResponseMessage val$responseMessage;

        public RunnableC16761(IncomingSubscribeResponseMessage incomingSubscribeResponseMessage) {
            incomingSubscribeResponseMessage = incomingSubscribeResponseMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            SendingRenewal.this.subscription.end(CancelReason.RENEWAL_FAILED, incomingSubscribeResponseMessage.getOperation());
        }
    }

    /* renamed from: org.fourthline.cling.protocol.sync.SendingRenewal$2 */
    public class RunnableC16772 implements Runnable {
        public final /* synthetic */ IncomingSubscribeResponseMessage val$responseMessage;

        public RunnableC16772(IncomingSubscribeResponseMessage incomingSubscribeResponseMessage) {
            incomingSubscribeResponseMessage = incomingSubscribeResponseMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            SendingRenewal.this.subscription.end(CancelReason.RENEWAL_FAILED, incomingSubscribeResponseMessage.getOperation());
        }
    }

    /* renamed from: org.fourthline.cling.protocol.sync.SendingRenewal$3 */
    public class RunnableC16783 implements Runnable {
        public RunnableC16783() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SendingRenewal.this.subscription.end(CancelReason.RENEWAL_FAILED, null);
        }
    }

    public SendingRenewal(UpnpService upnpService, RemoteGENASubscription remoteGENASubscription) {
        super(upnpService, new OutgoingRenewalRequestMessage(remoteGENASubscription, upnpService.getConfiguration().getEventSubscriptionHeaders(remoteGENASubscription.getService())));
        this.subscription = remoteGENASubscription;
    }

    public void onRenewalFailure() {
        log.fine("Subscription renewal failed, removing subscription from registry");
        getUpnpService().getRegistry().removeRemoteSubscription(this.subscription);
        getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.SendingRenewal.3
            public RunnableC16783() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SendingRenewal.this.subscription.end(CancelReason.RENEWAL_FAILED, null);
            }
        });
    }

    @Override // org.fourthline.cling.protocol.SendingSync
    public IncomingSubscribeResponseMessage executeSync() throws RouterException {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Sending subscription renewal request: ");
        sbM112a.append(getInputMessage());
        logger.fine(sbM112a.toString());
        try {
            StreamResponseMessage streamResponseMessageSend = getUpnpService().getRouter().send(getInputMessage());
            if (streamResponseMessageSend == null) {
                onRenewalFailure();
                return null;
            }
            IncomingSubscribeResponseMessage incomingSubscribeResponseMessage = new IncomingSubscribeResponseMessage(streamResponseMessageSend);
            if (streamResponseMessageSend.getOperation().isFailed()) {
                logger.fine("Subscription renewal failed, response was: " + streamResponseMessageSend);
                getUpnpService().getRegistry().removeRemoteSubscription(this.subscription);
                getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.SendingRenewal.1
                    public final /* synthetic */ IncomingSubscribeResponseMessage val$responseMessage;

                    public RunnableC16761(IncomingSubscribeResponseMessage incomingSubscribeResponseMessage2) {
                        incomingSubscribeResponseMessage = incomingSubscribeResponseMessage2;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        SendingRenewal.this.subscription.end(CancelReason.RENEWAL_FAILED, incomingSubscribeResponseMessage.getOperation());
                    }
                });
            } else if (incomingSubscribeResponseMessage2.isValidHeaders()) {
                logger.fine("Subscription renewed, updating in registry, response was: " + streamResponseMessageSend);
                this.subscription.setActualSubscriptionDurationSeconds(incomingSubscribeResponseMessage2.getSubscriptionDurationSeconds());
                getUpnpService().getRegistry().updateRemoteSubscription(this.subscription);
            } else {
                logger.severe("Subscription renewal failed, invalid or missing (SID, Timeout) response headers");
                getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.SendingRenewal.2
                    public final /* synthetic */ IncomingSubscribeResponseMessage val$responseMessage;

                    public RunnableC16772(IncomingSubscribeResponseMessage incomingSubscribeResponseMessage2) {
                        incomingSubscribeResponseMessage = incomingSubscribeResponseMessage2;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        SendingRenewal.this.subscription.end(CancelReason.RENEWAL_FAILED, incomingSubscribeResponseMessage.getOperation());
                    }
                });
            }
            return incomingSubscribeResponseMessage2;
        } catch (RouterException e7) {
            onRenewalFailure();
            throw e7;
        }
    }
}
