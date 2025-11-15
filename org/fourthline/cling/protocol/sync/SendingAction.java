package org.fourthline.cling.protocol.sync;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.action.ActionCancelledException;
import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.message.control.IncomingActionResponseMessage;
import org.fourthline.cling.model.message.control.OutgoingActionRequestMessage;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.ErrorCode;
import org.fourthline.cling.protocol.SendingSync;
import org.fourthline.cling.transport.RouterException;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class SendingAction extends SendingSync<OutgoingActionRequestMessage, IncomingActionResponseMessage> {
    private static final Logger log = Logger.getLogger(SendingAction.class.getName());
    public final ActionInvocation actionInvocation;

    public SendingAction(UpnpService upnpService, ActionInvocation actionInvocation, URL url) {
        super(upnpService, new OutgoingActionRequestMessage(actionInvocation, url));
        this.actionInvocation = actionInvocation;
    }

    public void handleResponse(IncomingActionResponseMessage incomingActionResponseMessage) throws ActionException {
        try {
            log.fine("Received response for outgoing call, reading SOAP response body: " + incomingActionResponseMessage);
            getUpnpService().getConfiguration().getSoapActionProcessor().readBody(incomingActionResponseMessage, this.actionInvocation);
        } catch (UnsupportedDataException e7) {
            Logger logger = log;
            logger.fine("Error reading SOAP body: " + e7);
            logger.log(Level.FINE, "Exception root cause: ", C2074b.m2475O(e7));
            ErrorCode errorCode = ErrorCode.ACTION_FAILED;
            StringBuilder sbM112a = C0413b.m112a("Error reading SOAP response message. ");
            sbM112a.append(e7.getMessage());
            throw new ActionException(errorCode, sbM112a.toString(), false);
        }
    }

    public void handleResponseFailure(IncomingActionResponseMessage incomingActionResponseMessage) throws ActionException {
        try {
            log.fine("Received response with Internal Server Error, reading SOAP failure message");
            getUpnpService().getConfiguration().getSoapActionProcessor().readBody(incomingActionResponseMessage, this.actionInvocation);
        } catch (UnsupportedDataException e7) {
            Logger logger = log;
            logger.fine("Error reading SOAP body: " + e7);
            logger.log(Level.FINE, "Exception root cause: ", C2074b.m2475O(e7));
            ErrorCode errorCode = ErrorCode.ACTION_FAILED;
            StringBuilder sbM112a = C0413b.m112a("Error reading SOAP response failure message. ");
            sbM112a.append(e7.getMessage());
            throw new ActionException(errorCode, sbM112a.toString(), false);
        }
    }

    public IncomingActionResponseMessage invokeRemote(OutgoingActionRequestMessage outgoingActionRequestMessage) throws ActionException, RouterException {
        Device device = this.actionInvocation.getAction().getService().getDevice();
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Sending outgoing action call '");
        sbM112a.append(this.actionInvocation.getAction().getName());
        sbM112a.append("' to remote service of: ");
        sbM112a.append(device);
        logger.fine(sbM112a.toString());
        IncomingActionResponseMessage incomingActionResponseMessage = null;
        try {
            StreamResponseMessage streamResponseMessageSendRemoteRequest = sendRemoteRequest(outgoingActionRequestMessage);
            if (streamResponseMessageSendRemoteRequest == null) {
                logger.fine("No connection or no no response received, returning null");
                this.actionInvocation.setFailure(new ActionException(ErrorCode.ACTION_FAILED, "Connection error or no response received"));
                return null;
            }
            IncomingActionResponseMessage incomingActionResponseMessage2 = new IncomingActionResponseMessage(streamResponseMessageSendRemoteRequest);
            try {
                if (!incomingActionResponseMessage2.isFailedNonRecoverable()) {
                    if (incomingActionResponseMessage2.isFailedRecoverable()) {
                        handleResponseFailure(incomingActionResponseMessage2);
                    } else {
                        handleResponse(incomingActionResponseMessage2);
                    }
                    return incomingActionResponseMessage2;
                }
                logger.fine("Response was a non-recoverable failure: " + incomingActionResponseMessage2);
                throw new ActionException(ErrorCode.ACTION_FAILED, "Non-recoverable remote execution failure: " + incomingActionResponseMessage2.getOperation().getResponseDetails());
            } catch (ActionException e7) {
                e = e7;
                incomingActionResponseMessage = incomingActionResponseMessage2;
                Logger logger2 = log;
                StringBuilder sbM112a2 = C0413b.m112a("Remote action invocation failed, returning Internal Server Error message: ");
                sbM112a2.append(e.getMessage());
                logger2.fine(sbM112a2.toString());
                this.actionInvocation.setFailure(e);
                return (incomingActionResponseMessage == null || !incomingActionResponseMessage.getOperation().isFailed()) ? new IncomingActionResponseMessage(new UpnpResponse(UpnpResponse.Status.INTERNAL_SERVER_ERROR)) : incomingActionResponseMessage;
            }
        } catch (ActionException e8) {
            e = e8;
        }
    }

    public StreamResponseMessage sendRemoteRequest(OutgoingActionRequestMessage outgoingActionRequestMessage) throws ActionException, RouterException {
        try {
            Logger logger = log;
            logger.fine("Writing SOAP request body of: " + outgoingActionRequestMessage);
            getUpnpService().getConfiguration().getSoapActionProcessor().writeBody(outgoingActionRequestMessage, this.actionInvocation);
            logger.fine("Sending SOAP body of message as stream to remote device");
            return getUpnpService().getRouter().send(outgoingActionRequestMessage);
        } catch (UnsupportedDataException e7) {
            Logger logger2 = log;
            Level level = Level.FINE;
            if (logger2.isLoggable(level)) {
                logger2.fine("Error writing SOAP body: " + e7);
                logger2.log(level, "Exception root cause: ", C2074b.m2475O(e7));
            }
            ErrorCode errorCode = ErrorCode.ACTION_FAILED;
            StringBuilder sbM112a = C0413b.m112a("Error writing request message. ");
            sbM112a.append(e7.getMessage());
            throw new ActionException(errorCode, sbM112a.toString());
        } catch (RouterException e8) {
            Throwable thM2475O = C2074b.m2475O(e8);
            if (!(thM2475O instanceof InterruptedException)) {
                throw e8;
            }
            Logger logger3 = log;
            if (logger3.isLoggable(Level.FINE)) {
                logger3.fine("Sending action request message was interrupted: " + thM2475O);
            }
            throw new ActionCancelledException((InterruptedException) thM2475O);
        }
    }

    @Override // org.fourthline.cling.protocol.SendingSync
    public IncomingActionResponseMessage executeSync() {
        return invokeRemote(getInputMessage());
    }
}
