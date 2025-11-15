package org.fourthline.cling.support.messagebox;

import org.fourthline.cling.controlpoint.ActionCallback;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.messagebox.model.Message;
import p098l6.C1448b;

/* loaded from: classes.dex */
public abstract class AddMessage extends ActionCallback {
    public final C1448b mimeType;

    public AddMessage(Service service, Message message) {
        super(new ActionInvocation(service.getAction("AddMessage")));
        C1448b c1448bM1633a = C1448b.m1633a("text/xml;charset=\"utf-8\"");
        this.mimeType = c1448bM1633a;
        getActionInvocation().setInput("MessageID", Integer.toString(message.getId()));
        getActionInvocation().setInput("MessageType", c1448bM1633a.toString());
        getActionInvocation().setInput("Message", message.toString());
    }
}
