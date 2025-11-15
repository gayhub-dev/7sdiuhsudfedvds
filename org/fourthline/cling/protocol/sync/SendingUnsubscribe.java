package org.fourthline.cling.protocol.sync;

import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.RemoteGENASubscription;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.gena.OutgoingUnsubscribeRequestMessage;
import org.fourthline.cling.protocol.SendingSync;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SendingUnsubscribe extends SendingSync<OutgoingUnsubscribeRequestMessage, StreamResponseMessage> {
    private static final Logger log = Logger.getLogger(SendingUnsubscribe.class.getName());
    public final RemoteGENASubscription subscription;

    /* renamed from: org.fourthline.cling.protocol.sync.SendingUnsubscribe$1 */
    public class RunnableC16841 implements Runnable {
        public final /* synthetic */ StreamResponseMessage val$response;

        public RunnableC16841(StreamResponseMessage streamResponseMessage) {
            streamResponseMessage = streamResponseMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            StreamResponseMessage streamResponseMessage = streamResponseMessage;
            if (streamResponseMessage == null) {
                SendingUnsubscribe.log.fine("Unsubscribe failed, no response received");
                SendingUnsubscribe.this.subscription.end(CancelReason.UNSUBSCRIBE_FAILED, null);
                return;
            }
            if (streamResponseMessage.getOperation().isFailed()) {
                Logger logger = SendingUnsubscribe.log;
                StringBuilder sbM112a = C0413b.m112a("Unsubscribe failed, response was: ");
                sbM112a.append(streamResponseMessage);
                logger.fine(sbM112a.toString());
                SendingUnsubscribe.this.subscription.end(CancelReason.UNSUBSCRIBE_FAILED, streamResponseMessage.getOperation());
                return;
            }
            Logger logger2 = SendingUnsubscribe.log;
            StringBuilder sbM112a2 = C0413b.m112a("Unsubscribe successful, response was: ");
            sbM112a2.append(streamResponseMessage);
            logger2.fine(sbM112a2.toString());
            SendingUnsubscribe.this.subscription.end(null, streamResponseMessage.getOperation());
        }
    }

    public SendingUnsubscribe(UpnpService upnpService, RemoteGENASubscription remoteGENASubscription) {
        super(upnpService, new OutgoingUnsubscribeRequestMessage(remoteGENASubscription, upnpService.getConfiguration().getEventSubscriptionHeaders(remoteGENASubscription.getService())));
        this.subscription = remoteGENASubscription;
    }

    @Override // org.fourthline.cling.protocol.SendingSync
    public StreamResponseMessage executeSync() {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Sending unsubscribe request: ");
        sbM112a.append(getInputMessage());
        logger.fine(sbM112a.toString());
        try {
            StreamResponseMessage streamResponseMessageSend = getUpnpService().getRouter().send(getInputMessage());
            onUnsubscribe(streamResponseMessageSend);
            return streamResponseMessageSend;
        } catch (Throwable th) {
            onUnsubscribe(null);
            throw th;
        }
    }

    public void onUnsubscribe(StreamResponseMessage streamResponseMessage) {
        getUpnpService().getRegistry().removeRemoteSubscription(this.subscription);
        getUpnpService().getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.protocol.sync.SendingUnsubscribe.1
            public final /* synthetic */ StreamResponseMessage val$response;

            public RunnableC16841(StreamResponseMessage streamResponseMessage2) {
                streamResponseMessage = streamResponseMessage2;
            }

            @Override // java.lang.Runnable
            public void run() {
                StreamResponseMessage streamResponseMessage2 = streamResponseMessage;
                if (streamResponseMessage2 == null) {
                    SendingUnsubscribe.log.fine("Unsubscribe failed, no response received");
                    SendingUnsubscribe.this.subscription.end(CancelReason.UNSUBSCRIBE_FAILED, null);
                    return;
                }
                if (streamResponseMessage2.getOperation().isFailed()) {
                    Logger logger = SendingUnsubscribe.log;
                    StringBuilder sbM112a = C0413b.m112a("Unsubscribe failed, response was: ");
                    sbM112a.append(streamResponseMessage);
                    logger.fine(sbM112a.toString());
                    SendingUnsubscribe.this.subscription.end(CancelReason.UNSUBSCRIBE_FAILED, streamResponseMessage.getOperation());
                    return;
                }
                Logger logger2 = SendingUnsubscribe.log;
                StringBuilder sbM112a2 = C0413b.m112a("Unsubscribe successful, response was: ");
                sbM112a2.append(streamResponseMessage);
                logger2.fine(sbM112a2.toString());
                SendingUnsubscribe.this.subscription.end(null, streamResponseMessage.getOperation());
            }
        });
    }
}
