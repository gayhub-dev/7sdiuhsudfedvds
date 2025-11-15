package org.fourthline.cling.transport.spi;

import java.util.logging.Logger;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.protocol.ProtocolCreationException;
import org.fourthline.cling.protocol.ProtocolFactory;
import org.fourthline.cling.protocol.ReceivingSync;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class UpnpStream implements Runnable {
    private static Logger log = Logger.getLogger(UpnpStream.class.getName());
    public final ProtocolFactory protocolFactory;
    public ReceivingSync syncProtocol;

    public UpnpStream(ProtocolFactory protocolFactory) {
        this.protocolFactory = protocolFactory;
    }

    public ProtocolFactory getProtocolFactory() {
        return this.protocolFactory;
    }

    public StreamResponseMessage process(StreamRequestMessage streamRequestMessage) {
        log.fine("Processing stream request message: " + streamRequestMessage);
        try {
            this.syncProtocol = getProtocolFactory().createReceivingSync(streamRequestMessage);
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Running protocol for synchronous message processing: ");
            sbM112a.append(this.syncProtocol);
            logger.fine(sbM112a.toString());
            this.syncProtocol.run();
            StreamResponseMessage outputMessage = this.syncProtocol.getOutputMessage();
            if (outputMessage == null) {
                log.finer("Protocol did not return any response message");
                return null;
            }
            log.finer("Protocol returned response: " + outputMessage);
            return outputMessage;
        } catch (ProtocolCreationException e7) {
            Logger logger2 = log;
            StringBuilder sbM112a2 = C0413b.m112a("Processing stream request failed - ");
            sbM112a2.append(C2074b.m2475O(e7).toString());
            logger2.warning(sbM112a2.toString());
            return new StreamResponseMessage(UpnpResponse.Status.NOT_IMPLEMENTED);
        }
    }

    public void responseException(Throwable th) {
        ReceivingSync receivingSync = this.syncProtocol;
        if (receivingSync != null) {
            receivingSync.responseException(th);
        }
    }

    public void responseSent(StreamResponseMessage streamResponseMessage) {
        ReceivingSync receivingSync = this.syncProtocol;
        if (receivingSync != null) {
            receivingSync.responseSent(streamResponseMessage);
        }
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(")");
        return sbM112a.toString();
    }
}
