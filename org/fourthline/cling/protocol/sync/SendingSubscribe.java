package org.fourthline.cling.protocol.sync;

import java.util.List;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.NetworkAddress;
import org.fourthline.cling.model.gena.RemoteGENASubscription;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.gena.IncomingSubscribeResponseMessage;
import org.fourthline.cling.model.message.gena.OutgoingSubscribeRequestMessage;
import org.fourthline.cling.protocol.SendingSync;
import org.fourthline.cling.transport.RouterException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SendingSubscribe extends SendingSync<OutgoingSubscribeRequestMessage, IncomingSubscribeResponseMessage> {
    private static final Logger log = Logger.getLogger(SendingSubscribe.class.getName());
    public final RemoteGENASubscription subscription;

    /* renamed from: org.fourthline.cling.protocol.sync.SendingSubscribe$1 */
    public class RunnableC16791 implements Runnable {
        public RunnableC16791() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SendingSubscribe.this.subscription.fail(null);
        }
    }

    /* renamed from: org.fourthline.cling.protocol.sync.SendingSubscribe$2 */
    public class RunnableC16802 implements Runnable {
        public final /* synthetic */ IncomingSubscribeResponseMessage val$responseMessage;

        public RunnableC16802(IncomingSubscribeResponseMessage incomingSubscribeResponseMessage) {
            incomingSubscribeResponseMessage = incomingSubscribeResponseMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            SendingSubscribe.this.subscription.fail(incomingSubscribeResponseMessage.getOperation());
        }
    }

    /* renamed from: org.fourthline.cling.protocol.sync.SendingSubscribe$3 */
    public class RunnableC16813 implements Runnable {
        public final /* synthetic */ IncomingSubscribeResponseMessage val$responseMessage;

        public RunnableC16813(IncomingSubscribeResponseMessage incomingSubscribeResponseMessage) {
            incomingSubscribeResponseMessage = incomingSubscribeResponseMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            SendingSubscribe.this.subscription.fail(incomingSubscribeResponseMessage.getOperation());
        }
    }

    /* renamed from: org.fourthline.cling.protocol.sync.SendingSubscribe$4 */
    public class RunnableC16824 implements Runnable {
        public RunnableC16824() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SendingSubscribe.this.subscription.establish();
        }
    }

    /* renamed from: org.fourthline.cling.protocol.sync.SendingSubscribe$5 */
    public class RunnableC16835 implements Runnable {
        public RunnableC16835() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SendingSubscribe.this.subscription.fail(null);
        }
    }

    public SendingSubscribe(UpnpService upnpService, RemoteGENASubscription remoteGENASubscription, List<NetworkAddress> list) {
        super(upnpService, new OutgoingSubscribeRequestMessage(remoteGENASubscription, remoteGENASubscription.getEventCallbackURLs(list, upnpService.getConfiguration().getNamespace()), upnpService.getConfiguration().getEventSubscriptionHeaders(remoteGENASubscription.getService())));
        this.subscription = remoteGENASubscription;
    }

    public void onSubscriptionFailure() {
        log.fine("Subscription failed");
        getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.SendingSubscribe.5
            public RunnableC16835() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SendingSubscribe.this.subscription.fail(null);
            }
        });
    }

    @Override // org.fourthline.cling.protocol.SendingSync
    public IncomingSubscribeResponseMessage executeSync() {
        if (!getInputMessage().hasCallbackURLs()) {
            log.fine("Subscription failed, no active local callback URLs available (network disabled?)");
            getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.SendingSubscribe.1
                public RunnableC16791() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    SendingSubscribe.this.subscription.fail(null);
                }
            });
            return null;
        }
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Sending subscription request: ");
        sbM112a.append(getInputMessage());
        logger.fine(sbM112a.toString());
        try {
            getUpnpService().getRegistry().registerPendingRemoteSubscription(this.subscription);
            StreamResponseMessage streamResponseMessageSend = getUpnpService().getRouter().send(getInputMessage());
            if (streamResponseMessageSend == null) {
                onSubscriptionFailure();
                return null;
            }
            IncomingSubscribeResponseMessage incomingSubscribeResponseMessage = new IncomingSubscribeResponseMessage(streamResponseMessageSend);
            if (streamResponseMessageSend.getOperation().isFailed()) {
                logger.fine("Subscription failed, response was: " + incomingSubscribeResponseMessage);
                getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.SendingSubscribe.2
                    public final /* synthetic */ IncomingSubscribeResponseMessage val$responseMessage;

                    public RunnableC16802(IncomingSubscribeResponseMessage incomingSubscribeResponseMessage2) {
                        incomingSubscribeResponseMessage = incomingSubscribeResponseMessage2;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        SendingSubscribe.this.subscription.fail(incomingSubscribeResponseMessage.getOperation());
                    }
                });
            } else if (incomingSubscribeResponseMessage2.isValidHeaders()) {
                logger.fine("Subscription established, adding to registry, response was: " + streamResponseMessageSend);
                this.subscription.setSubscriptionId(incomingSubscribeResponseMessage2.getSubscriptionId());
                this.subscription.setActualSubscriptionDurationSeconds(incomingSubscribeResponseMessage2.getSubscriptionDurationSeconds());
                getUpnpService().getRegistry().addRemoteSubscription(this.subscription);
                getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.SendingSubscribe.4
                    public RunnableC16824() {
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        SendingSubscribe.this.subscription.establish();
                    }
                });
            } else {
                logger.severe("Subscription failed, invalid or missing (SID, Timeout) response headers");
                getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.SendingSubscribe.3
                    public final /* synthetic */ IncomingSubscribeResponseMessage val$responseMessage;

                    public RunnableC16813(IncomingSubscribeResponseMessage incomingSubscribeResponseMessage2) {
                        incomingSubscribeResponseMessage = incomingSubscribeResponseMessage2;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        SendingSubscribe.this.subscription.fail(incomingSubscribeResponseMessage.getOperation());
                    }
                });
            }
            return incomingSubscribeResponseMessage2;
        } catch (RouterException unused) {
            onSubscriptionFailure();
            return null;
        } finally {
            getUpnpService().getRegistry().unregisterPendingRemoteSubscription(this.subscription);
        }
    }
}
