package org.fourthline.cling.protocol.async;

import java.util.Iterator;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.message.IncomingDatagramMessage;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.message.discovery.IncomingSearchResponse;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.model.meta.RemoteDeviceIdentity;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.protocol.ReceivingAsync;
import org.fourthline.cling.protocol.RetrieveRemoteDescriptors;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ReceivingSearchResponse extends ReceivingAsync<IncomingSearchResponse> {
    private static final Logger log = Logger.getLogger(ReceivingSearchResponse.class.getName());

    public ReceivingSearchResponse(UpnpService upnpService, IncomingDatagramMessage<UpnpResponse> incomingDatagramMessage) {
        super(upnpService, new IncomingSearchResponse(incomingDatagramMessage));
    }

    @Override // org.fourthline.cling.protocol.ReceivingAsync
    public void execute() {
        if (!getInputMessage().isSearchResponseMessage()) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Ignoring invalid search response message: ");
            sbM112a.append(getInputMessage());
            logger.fine(sbM112a.toString());
            return;
        }
        UDN rootDeviceUDN = getInputMessage().getRootDeviceUDN();
        if (rootDeviceUDN == null) {
            Logger logger2 = log;
            StringBuilder sbM112a2 = C0413b.m112a("Ignoring search response message without UDN: ");
            sbM112a2.append(getInputMessage());
            logger2.fine(sbM112a2.toString());
            return;
        }
        RemoteDeviceIdentity remoteDeviceIdentity = new RemoteDeviceIdentity(getInputMessage());
        Logger logger3 = log;
        logger3.fine("Received device search response: " + remoteDeviceIdentity);
        if (getUpnpService().getRegistry().update(remoteDeviceIdentity)) {
            logger3.fine("Remote device was already known: " + rootDeviceUDN);
            return;
        }
        try {
            RemoteDevice remoteDevice = new RemoteDevice(remoteDeviceIdentity);
            if (remoteDeviceIdentity.getDescriptorURL() == null) {
                StringBuilder sbM112a3 = C0413b.m112a("Ignoring message without location URL header: ");
                sbM112a3.append(getInputMessage());
                logger3.finer(sbM112a3.toString());
            } else {
                if (remoteDeviceIdentity.getMaxAgeSeconds() != null) {
                    getUpnpService().getConfiguration().getAsyncProtocolExecutor().execute(new RetrieveRemoteDescriptors(getUpnpService(), remoteDevice));
                    return;
                }
                StringBuilder sbM112a4 = C0413b.m112a("Ignoring message without max-age header: ");
                sbM112a4.append(getInputMessage());
                logger3.finer(sbM112a4.toString());
            }
        } catch (ValidationException e7) {
            log.warning("Validation errors of device during discovery: " + remoteDeviceIdentity);
            Iterator<ValidationError> it = e7.getErrors().iterator();
            while (it.hasNext()) {
                log.warning(it.next().toString());
            }
        }
    }
}
