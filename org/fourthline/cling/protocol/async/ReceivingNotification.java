package org.fourthline.cling.protocol.async;

import java.util.Iterator;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.message.IncomingDatagramMessage;
import org.fourthline.cling.model.message.UpnpRequest;
import org.fourthline.cling.model.message.discovery.IncomingNotificationRequest;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.model.meta.RemoteDeviceIdentity;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.protocol.ReceivingAsync;
import org.fourthline.cling.protocol.RetrieveRemoteDescriptors;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ReceivingNotification extends ReceivingAsync<IncomingNotificationRequest> {
    private static final Logger log = Logger.getLogger(ReceivingNotification.class.getName());

    public ReceivingNotification(UpnpService upnpService, IncomingDatagramMessage<UpnpRequest> incomingDatagramMessage) {
        super(upnpService, new IncomingNotificationRequest(incomingDatagramMessage));
    }

    @Override // org.fourthline.cling.protocol.ReceivingAsync
    public void execute() {
        UDN udn = getInputMessage().getUDN();
        if (udn == null) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Ignoring notification message without UDN: ");
            sbM112a.append(getInputMessage());
            logger.fine(sbM112a.toString());
            return;
        }
        RemoteDeviceIdentity remoteDeviceIdentity = new RemoteDeviceIdentity(getInputMessage());
        Logger logger2 = log;
        logger2.fine("Received device notification: " + remoteDeviceIdentity);
        try {
            RemoteDevice remoteDevice = new RemoteDevice(remoteDeviceIdentity);
            if (!getInputMessage().isAliveMessage()) {
                if (!getInputMessage().isByeByeMessage()) {
                    StringBuilder sbM112a2 = C0413b.m112a("Ignoring unknown notification message: ");
                    sbM112a2.append(getInputMessage());
                    logger2.finer(sbM112a2.toString());
                    return;
                }
                logger2.fine("Received device BYEBYE advertisement");
                if (getUpnpService().getRegistry().removeDevice(remoteDevice)) {
                    logger2.fine("Removed remote device from registry: " + remoteDevice);
                    return;
                }
                return;
            }
            StringBuilder sbM112a3 = C0413b.m112a("Received device ALIVE advertisement, descriptor location is: ");
            sbM112a3.append(remoteDeviceIdentity.getDescriptorURL());
            logger2.fine(sbM112a3.toString());
            if (remoteDeviceIdentity.getDescriptorURL() == null) {
                StringBuilder sbM112a4 = C0413b.m112a("Ignoring message without location URL header: ");
                sbM112a4.append(getInputMessage());
                logger2.finer(sbM112a4.toString());
            } else if (remoteDeviceIdentity.getMaxAgeSeconds() == null) {
                StringBuilder sbM112a5 = C0413b.m112a("Ignoring message without max-age header: ");
                sbM112a5.append(getInputMessage());
                logger2.finer(sbM112a5.toString());
            } else {
                if (!getUpnpService().getRegistry().update(remoteDeviceIdentity)) {
                    getUpnpService().getConfiguration().getAsyncProtocolExecutor().execute(new RetrieveRemoteDescriptors(getUpnpService(), remoteDevice));
                    return;
                }
                logger2.finer("Remote device was already known: " + udn);
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
