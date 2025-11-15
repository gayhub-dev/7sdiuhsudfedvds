package org.fourthline.cling.model.message.header;

import org.fourthline.cling.model.types.NotificationSubtype;

/* loaded from: classes.dex */
public class STAllHeader extends UpnpHeader<NotificationSubtype> {
    public STAllHeader() {
        setValue(NotificationSubtype.ALL);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().getHeaderString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        NotificationSubtype notificationSubtype = NotificationSubtype.ALL;
        if (str.equals(notificationSubtype.getHeaderString())) {
            return;
        }
        throw new InvalidHeaderException("Invalid ST header value (not " + notificationSubtype + "): " + str);
    }
}
