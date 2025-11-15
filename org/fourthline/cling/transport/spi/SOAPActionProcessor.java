package org.fourthline.cling.transport.spi;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.control.ActionRequestMessage;
import org.fourthline.cling.model.message.control.ActionResponseMessage;

/* loaded from: classes.dex */
public interface SOAPActionProcessor {
    void readBody(ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation);

    void readBody(ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation);

    void writeBody(ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation);

    void writeBody(ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation);
}
