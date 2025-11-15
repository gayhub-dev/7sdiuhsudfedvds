package org.fourthline.cling.protocol;

import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class SendingSync<IN extends StreamRequestMessage, OUT extends StreamResponseMessage> extends SendingAsync {
    private final IN inputMessage;
    public OUT outputMessage;

    public SendingSync(UpnpService upnpService, IN in) {
        super(upnpService);
        this.inputMessage = in;
    }

    @Override // org.fourthline.cling.protocol.SendingAsync
    public final void execute() {
        this.outputMessage = (OUT) executeSync();
    }

    public abstract OUT executeSync();

    public IN getInputMessage() {
        return this.inputMessage;
    }

    public OUT getOutputMessage() {
        return this.outputMessage;
    }

    @Override // org.fourthline.cling.protocol.SendingAsync
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(")");
        return sbM112a.toString();
    }
}
