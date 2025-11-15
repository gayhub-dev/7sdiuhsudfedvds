package org.fourthline.cling.model.message.control;

import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpResponse;

/* loaded from: classes.dex */
public class IncomingActionResponseMessage extends StreamResponseMessage implements ActionResponseMessage {
    public IncomingActionResponseMessage(StreamResponseMessage streamResponseMessage) {
        super(streamResponseMessage);
    }

    @Override // org.fourthline.cling.model.message.control.ActionMessage
    public String getActionNamespace() {
        return null;
    }

    public boolean isFailedNonRecoverable() {
        int statusCode = getOperation().getStatusCode();
        return (!getOperation().isFailed() || statusCode == UpnpResponse.Status.METHOD_NOT_SUPPORTED.getStatusCode() || (statusCode == UpnpResponse.Status.INTERNAL_SERVER_ERROR.getStatusCode() && hasBody())) ? false : true;
    }

    public boolean isFailedRecoverable() {
        return hasBody() && getOperation().getStatusCode() == UpnpResponse.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    }

    public IncomingActionResponseMessage(UpnpResponse upnpResponse) {
        super(upnpResponse);
    }
}
