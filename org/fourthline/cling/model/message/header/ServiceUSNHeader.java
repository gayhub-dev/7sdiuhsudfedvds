package org.fourthline.cling.model.message.header;

import org.fourthline.cling.model.types.NamedServiceType;
import org.fourthline.cling.model.types.ServiceType;
import org.fourthline.cling.model.types.UDN;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ServiceUSNHeader extends UpnpHeader<NamedServiceType> {
    public ServiceUSNHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(NamedServiceType.valueOf(str));
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid service USN header value, ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public ServiceUSNHeader(UDN udn, ServiceType serviceType) {
        setValue(new NamedServiceType(udn, serviceType));
    }

    public ServiceUSNHeader(NamedServiceType namedServiceType) {
        setValue(namedServiceType);
    }
}
