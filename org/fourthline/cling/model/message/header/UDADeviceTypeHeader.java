package org.fourthline.cling.model.message.header;

import java.net.URI;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;
import p009b.C0413b;

/* loaded from: classes.dex */
public class UDADeviceTypeHeader extends DeviceTypeHeader {
    public UDADeviceTypeHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.DeviceTypeHeader, org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(UDADeviceType.valueOf(str));
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid UDA device type header value, ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public UDADeviceTypeHeader(URI uri) {
        super(uri);
    }

    public UDADeviceTypeHeader(DeviceType deviceType) {
        super(deviceType);
    }
}
