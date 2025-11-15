package org.fourthline.cling.model.profile;

import org.fourthline.cling.model.message.UpnpHeaders;
import org.fourthline.cling.model.message.header.UpnpHeader;
import org.fourthline.cling.model.message.header.UserAgentHeader;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ClientInfo {
    public final UpnpHeaders requestHeaders;

    public ClientInfo() {
        this(new UpnpHeaders());
    }

    public UpnpHeaders getRequestHeaders() {
        return this.requestHeaders;
    }

    public String getRequestUserAgent() {
        return getRequestHeaders().getFirstHeaderString(UpnpHeader.Type.USER_AGENT);
    }

    public void setRequestUserAgent(String str) {
        getRequestHeaders().add(UpnpHeader.Type.USER_AGENT, new UserAgentHeader(str));
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") User-Agent: ");
        sbM112a.append(getRequestUserAgent());
        return sbM112a.toString();
    }

    public ClientInfo(UpnpHeaders upnpHeaders) {
        this.requestHeaders = upnpHeaders;
    }
}
