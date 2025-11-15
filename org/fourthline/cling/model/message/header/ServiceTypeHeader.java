package org.fourthline.cling.model.message.header;

import java.net.URI;
import org.fourthline.cling.model.types.ServiceType;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ServiceTypeHeader extends UpnpHeader<ServiceType> {
    public ServiceTypeHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(ServiceType.valueOf(str));
        } catch (RuntimeException e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid service type header value, ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public ServiceTypeHeader(URI uri) {
        setString(uri.toString());
    }

    public ServiceTypeHeader(ServiceType serviceType) {
        setValue(serviceType);
    }
}
