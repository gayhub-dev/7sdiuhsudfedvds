package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.types.NotificationSubtype;

/* loaded from: classes.dex */
public class NTSHeader extends UpnpHeader<NotificationSubtype> {
    public NTSHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().getHeaderString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        NotificationSubtype[] notificationSubtypeArrValues = NotificationSubtype.values();
        int length = notificationSubtypeArrValues.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                break;
            }
            NotificationSubtype notificationSubtype = notificationSubtypeArrValues[i7];
            if (str.equals(notificationSubtype.getHeaderString())) {
                setValue(notificationSubtype);
                break;
            }
            i7++;
        }
        if (getValue() == null) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid NTS header value: ", str));
        }
    }

    public NTSHeader(NotificationSubtype notificationSubtype) {
        setValue(notificationSubtype);
    }
}
