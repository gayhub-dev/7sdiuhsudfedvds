package org.fourthline.cling.protocol.sync;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.action.ActionCancelledException;
import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.action.RemoteActionInvocation;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.message.control.IncomingActionRequestMessage;
import org.fourthline.cling.model.message.control.OutgoingActionResponseMessage;
import org.fourthline.cling.model.message.header.ContentTypeHeader;
import org.fourthline.cling.model.message.header.UpnpHeader;
import org.fourthline.cling.model.resource.ServiceControlResource;
import org.fourthline.cling.model.types.ErrorCode;
import org.fourthline.cling.protocol.ReceivingSync;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class ReceivingAction extends ReceivingSync<StreamRequestMessage, StreamResponseMessage> {
    private static final Logger log = Logger.getLogger(ReceivingAction.class.getName());

    public ReceivingAction(UpnpService upnpService, StreamRequestMessage streamRequestMessage) {
        super(upnpService, streamRequestMessage);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.fourthline.cling.protocol.ReceivingSync
    public StreamResponseMessage executeSync() {
        RemoteActionInvocation remoteActionInvocation;
        OutgoingActionResponseMessage outgoingActionResponseMessage;
        ContentTypeHeader contentTypeHeader = (ContentTypeHeader) ((StreamRequestMessage) getInputMessage()).getHeaders().getFirstHeader(UpnpHeader.Type.CONTENT_TYPE, ContentTypeHeader.class);
        if (contentTypeHeader != null && !contentTypeHeader.isUDACompliantXML()) {
            log.warning("Received invalid Content-Type '" + contentTypeHeader + "': " + getInputMessage());
            return new StreamResponseMessage(new UpnpResponse(UpnpResponse.Status.UNSUPPORTED_MEDIA_TYPE));
        }
        if (contentTypeHeader == null) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Received without Content-Type: ");
            sbM112a.append(getInputMessage());
            logger.warning(sbM112a.toString());
        }
        ServiceControlResource serviceControlResource = (ServiceControlResource) getUpnpService().getRegistry().getResource(ServiceControlResource.class, ((StreamRequestMessage) getInputMessage()).getUri());
        if (serviceControlResource == null) {
            Logger logger2 = log;
            StringBuilder sbM112a2 = C0413b.m112a("No local resource found: ");
            sbM112a2.append(getInputMessage());
            logger2.fine(sbM112a2.toString());
            return null;
        }
        Logger logger3 = log;
        StringBuilder sbM112a3 = C0413b.m112a("Found local action resource matching relative request URI: ");
        sbM112a3.append(((StreamRequestMessage) getInputMessage()).getUri());
        logger3.fine(sbM112a3.toString());
        try {
            IncomingActionRequestMessage incomingActionRequestMessage = new IncomingActionRequestMessage((StreamRequestMessage) getInputMessage(), serviceControlResource.getModel());
            logger3.finer("Created incoming action request message: " + incomingActionRequestMessage);
            remoteActionInvocation = new RemoteActionInvocation(incomingActionRequestMessage.getAction(), getRemoteClientInfo());
            logger3.fine("Reading body of request message");
            getUpnpService().getConfiguration().getSoapActionProcessor().readBody(incomingActionRequestMessage, remoteActionInvocation);
            logger3.fine("Executing on local service: " + remoteActionInvocation);
            serviceControlResource.getModel().getExecutor(remoteActionInvocation.getAction()).execute(remoteActionInvocation);
            if (remoteActionInvocation.getFailure() == null) {
                outgoingActionResponseMessage = new OutgoingActionResponseMessage(remoteActionInvocation.getAction());
            } else {
                if (remoteActionInvocation.getFailure() instanceof ActionCancelledException) {
                    logger3.fine("Action execution was cancelled, returning 404 to client");
                    return null;
                }
                outgoingActionResponseMessage = new OutgoingActionResponseMessage(UpnpResponse.Status.INTERNAL_SERVER_ERROR, remoteActionInvocation.getAction());
            }
        } catch (UnsupportedDataException e7) {
            Logger logger4 = log;
            Level level = Level.WARNING;
            StringBuilder sbM112a4 = C0413b.m112a("Error reading action request XML body: ");
            sbM112a4.append(e7.toString());
            logger4.log(level, sbM112a4.toString(), C2074b.m2475O(e7));
            remoteActionInvocation = new RemoteActionInvocation(C2074b.m2475O(e7) instanceof ActionException ? (ActionException) C2074b.m2475O(e7) : new ActionException(ErrorCode.ACTION_FAILED, e7.getMessage()), getRemoteClientInfo());
            outgoingActionResponseMessage = new OutgoingActionResponseMessage(UpnpResponse.Status.INTERNAL_SERVER_ERROR);
        } catch (ActionException e8) {
            log.finer("Error executing local action: " + e8);
            remoteActionInvocation = new RemoteActionInvocation(e8, getRemoteClientInfo());
            outgoingActionResponseMessage = new OutgoingActionResponseMessage(UpnpResponse.Status.INTERNAL_SERVER_ERROR);
        }
        try {
            Logger logger5 = log;
            logger5.fine("Writing body of response message");
            getUpnpService().getConfiguration().getSoapActionProcessor().writeBody(outgoingActionResponseMessage, remoteActionInvocation);
            logger5.fine("Returning finished response message: " + outgoingActionResponseMessage);
            return outgoingActionResponseMessage;
        } catch (UnsupportedDataException e9) {
            Logger logger6 = log;
            logger6.warning("Failure writing body of response message, sending '500 Internal Server Error' without body");
            logger6.log(Level.WARNING, "Exception root cause: ", C2074b.m2475O(e9));
            return new StreamResponseMessage(UpnpResponse.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
