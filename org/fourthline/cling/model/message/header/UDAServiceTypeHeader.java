package org.fourthline.cling.model.message.header;

import java.net.URI;
import org.fourthline.cling.model.types.UDAServiceType;
import p009b.C0413b;

/* loaded from: classes.dex */
public class UDAServiceTypeHeader extends ServiceTypeHeader {
    public UDAServiceTypeHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.ServiceTypeHeader, org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(UDAServiceType.valueOf(str));
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid UDA service type header value, ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public UDAServiceTypeHeader(URI uri) {
        super(uri);
    }

    public UDAServiceTypeHeader(UDAServiceType uDAServiceType) {
        super(uDAServiceType);
    }
}
