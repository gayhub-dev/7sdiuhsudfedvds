package org.fourthline.cling.model.message.header;

import android.support.constraint.solver.widgets.analyzer.C0096a;
import org.fourthline.cling.model.types.UDN;

/* loaded from: classes.dex */
public class USNRootDeviceHeader extends UpnpHeader<UDN> {
    public static final String ROOT_DEVICE_SUFFIX = "::upnp:rootdevice";

    public USNRootDeviceHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString() + ROOT_DEVICE_SUFFIX;
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (!str.startsWith("uuid:") || !str.endsWith(ROOT_DEVICE_SUFFIX)) {
            throw new InvalidHeaderException(C0096a.m97a("Invalid root device USN header value, must start with 'uuid:' and end with '::upnp:rootdevice' but is '", str, "'"));
        }
        setValue(new UDN(str.substring(5, str.length() - 17)));
    }

    public USNRootDeviceHeader(UDN udn) {
        setValue(udn);
    }
}
