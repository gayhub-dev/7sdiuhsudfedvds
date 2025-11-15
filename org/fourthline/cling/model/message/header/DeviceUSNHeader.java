package org.fourthline.cling.model.message.header;

import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.NamedDeviceType;
import org.fourthline.cling.model.types.UDN;
import p009b.C0413b;

/* loaded from: classes.dex */
public class DeviceUSNHeader extends UpnpHeader<NamedDeviceType> {
    public DeviceUSNHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(NamedDeviceType.valueOf(str));
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid device USN header value, ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public DeviceUSNHeader(UDN udn, DeviceType deviceType) {
        setValue(new NamedDeviceType(udn, deviceType));
    }

    public DeviceUSNHeader(NamedDeviceType namedDeviceType) {
        setValue(namedDeviceType);
    }
}
