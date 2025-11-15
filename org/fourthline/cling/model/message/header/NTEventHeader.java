package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;
import java.util.Locale;

/* loaded from: classes.dex */
public class NTEventHeader extends UpnpHeader<String> {
    public NTEventHeader() {
        setValue("upnp:event");
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (!str.toLowerCase(Locale.ROOT).equals(getValue())) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid event NT header value: ", str));
        }
    }
}
