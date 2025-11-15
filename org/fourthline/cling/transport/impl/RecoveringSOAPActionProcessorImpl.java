package org.fourthline.cling.transport.impl;

import android.arch.lifecycle.C0063n;
import java.util.logging.Logger;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.control.ActionRequestMessage;
import org.fourthline.cling.model.message.control.ActionResponseMessage;
import org.fourthline.cling.transport.spi.SOAPActionProcessor;
import org.seamless.xml.C1729h;

/* loaded from: classes.dex */
public class RecoveringSOAPActionProcessorImpl extends PullSOAPActionProcessorImpl {
    private static Logger log = Logger.getLogger(SOAPActionProcessor.class.getName());

    public void handleInvalidMessage(ActionInvocation actionInvocation, UnsupportedDataException unsupportedDataException, UnsupportedDataException unsupportedDataException2) {
        throw unsupportedDataException;
    }

    @Override // org.fourthline.cling.transport.impl.PullSOAPActionProcessorImpl, org.fourthline.cling.transport.impl.SOAPActionProcessorImpl, org.fourthline.cling.transport.spi.SOAPActionProcessor
    public void readBody(ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation) {
        try {
            super.readBody(actionRequestMessage, actionInvocation);
        } catch (UnsupportedDataException e7) {
            if (!actionRequestMessage.isBodyNonEmptyString()) {
                throw e7;
            }
            log.warning("Trying to recover from invalid SOAP XML request: " + e7);
            try {
                actionRequestMessage.setBody(C1729h.m1873b(getMessageBody(actionRequestMessage)));
                super.readBody(actionRequestMessage, actionInvocation);
            } catch (UnsupportedDataException e8) {
                handleInvalidMessage(actionInvocation, e7, e8);
            }
        }
    }

    @Override // org.fourthline.cling.transport.impl.PullSOAPActionProcessorImpl, org.fourthline.cling.transport.impl.SOAPActionProcessorImpl, org.fourthline.cling.transport.spi.SOAPActionProcessor
    public void readBody(ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation) {
        try {
            super.readBody(actionResponseMessage, actionInvocation);
        } catch (UnsupportedDataException e7) {
            if (actionResponseMessage.isBodyNonEmptyString()) {
                log.warning("Trying to recover from invalid SOAP XML response: " + e7);
                String strM1873b = C1729h.m1873b(getMessageBody(actionResponseMessage));
                if (strM1873b.endsWith("</s:Envelop")) {
                    strM1873b = C0063n.m88a(strM1873b, "e>");
                }
                try {
                    actionResponseMessage.setBody(strM1873b);
                    super.readBody(actionResponseMessage, actionInvocation);
                    return;
                } catch (UnsupportedDataException e8) {
                    handleInvalidMessage(actionInvocation, e7, e8);
                    return;
                }
            }
            throw e7;
        }
    }
}
