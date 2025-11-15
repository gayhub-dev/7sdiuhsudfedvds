package org.fourthline.cling.protocol.async;

import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.types.NotificationSubtype;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SendingNotificationByebye extends SendingNotification {
    private static final Logger log = Logger.getLogger(SendingNotification.class.getName());

    public SendingNotificationByebye(UpnpService upnpService, LocalDevice localDevice) {
        super(upnpService, localDevice);
    }

    @Override // org.fourthline.cling.protocol.async.SendingNotification, org.fourthline.cling.protocol.SendingAsync
    public void execute() {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Sending byebye messages (");
        sbM112a.append(getBulkRepeat());
        sbM112a.append(" times) for: ");
        sbM112a.append(getDevice());
        logger.fine(sbM112a.toString());
        super.execute();
    }

    @Override // org.fourthline.cling.protocol.async.SendingNotification
    public NotificationSubtype getNotificationSubtype() {
        return NotificationSubtype.BYEBYE;
    }
}
