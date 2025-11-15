package org.fourthline.cling.protocol;

import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.profile.RemoteClientInfo;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class ReceivingSync<IN extends StreamRequestMessage, OUT extends StreamResponseMessage> extends ReceivingAsync<IN> {
    private static final Logger log = Logger.getLogger(UpnpService.class.getName());
    public OUT outputMessage;
    public final RemoteClientInfo remoteClientInfo;

    public ReceivingSync(UpnpService upnpService, IN in) {
        super(upnpService, in);
        this.remoteClientInfo = new RemoteClientInfo(in);
    }

    @Override // org.fourthline.cling.protocol.ReceivingAsync
    public final void execute() {
        OUT out = (OUT) executeSync();
        this.outputMessage = out;
        if (out == null || getRemoteClientInfo().getExtraResponseHeaders().size() <= 0) {
            return;
        }
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Setting extra headers on response message: ");
        sbM112a.append(getRemoteClientInfo().getExtraResponseHeaders().size());
        logger.fine(sbM112a.toString());
        this.outputMessage.getHeaders().putAll(getRemoteClientInfo().getExtraResponseHeaders());
    }

    public abstract OUT executeSync();

    public OUT getOutputMessage() {
        return this.outputMessage;
    }

    public RemoteClientInfo getRemoteClientInfo() {
        return this.remoteClientInfo;
    }

    public void responseException(Throwable th) {
    }

    public void responseSent(StreamResponseMessage streamResponseMessage) {
    }

    @Override // org.fourthline.cling.protocol.ReceivingAsync
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(")");
        return sbM112a.toString();
    }
}
