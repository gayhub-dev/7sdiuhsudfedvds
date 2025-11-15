package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.types.UDN;

/* loaded from: classes.dex */
public class UDNHeader extends UpnpHeader<UDN> {
    public UDNHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (!str.startsWith("uuid:")) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid UDA header value, must start with 'uuid:': ", str));
        }
        if (str.contains("::urn")) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid UDA header value, must not contain '::urn': ", str));
        }
        setValue(new UDN(str.substring(5)));
    }

    public UDNHeader(UDN udn) {
        setValue(udn);
    }
}
