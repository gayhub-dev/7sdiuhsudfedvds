package org.fourthline.cling.model.message.header;

import android.support.constraint.motion.C0081c;
import org.fourthline.cling.model.Constants;
import org.fourthline.cling.model.types.HostPort;

/* loaded from: classes.dex */
public class HostHeader extends UpnpHeader<HostPort> {
    public int port = Constants.UPNP_MULTICAST_PORT;
    public String group = Constants.IPV4_UPNP_MULTICAST_GROUP;

    public HostHeader() {
        setValue(new HostPort(Constants.IPV4_UPNP_MULTICAST_GROUP, Constants.UPNP_MULTICAST_PORT));
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (!str.contains(":")) {
            this.group = str;
            setValue(new HostPort(str, this.port));
            return;
        }
        try {
            this.port = Integer.valueOf(str.substring(str.indexOf(":") + 1)).intValue();
            String strSubstring = str.substring(0, str.indexOf(":"));
            this.group = strSubstring;
            setValue(new HostPort(strSubstring, this.port));
        } catch (NumberFormatException e7) {
            StringBuilder sbM95a = C0081c.m95a("Invalid HOST header value, can't parse port: ", str, " - ");
            sbM95a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM95a.toString());
        }
    }

    public HostHeader(int i7) {
        setValue(new HostPort(Constants.IPV4_UPNP_MULTICAST_GROUP, i7));
    }

    public HostHeader(String str, int i7) {
        setValue(new HostPort(str, i7));
    }
}
