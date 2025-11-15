package org.fourthline.cling.model.message.header;

import java.net.URI;
import org.fourthline.cling.model.types.DeviceType;
import p009b.C0413b;

/* loaded from: classes.dex */
public class DeviceTypeHeader extends UpnpHeader<DeviceType> {
    public DeviceTypeHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(DeviceType.valueOf(str));
        } catch (RuntimeException e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid device type header value, ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public DeviceTypeHeader(URI uri) {
        setString(uri.toString());
    }

    public DeviceTypeHeader(DeviceType deviceType) {
        setValue(deviceType);
    }
}
