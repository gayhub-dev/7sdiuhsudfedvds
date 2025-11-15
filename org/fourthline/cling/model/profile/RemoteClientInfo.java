package org.fourthline.cling.model.profile;

import java.net.InetAddress;
import org.fourthline.cling.model.message.Connection;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.UpnpHeaders;
import org.fourthline.cling.model.message.header.UpnpHeader;
import org.fourthline.cling.model.message.header.UserAgentHeader;
import p009b.C0413b;
import p082j6.C1213b;

/* loaded from: classes.dex */
public class RemoteClientInfo extends ClientInfo {
    public final Connection connection;
    public final UpnpHeaders extraResponseHeaders;

    public RemoteClientInfo() {
        this(null);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public UpnpHeaders getExtraResponseHeaders() {
        return this.extraResponseHeaders;
    }

    public InetAddress getLocalAddress() {
        return getConnection().getLocalAddress();
    }

    public InetAddress getRemoteAddress() {
        return getConnection().getRemoteAddress();
    }

    public boolean isPS3Request() {
        String requestUserAgent = getRequestUserAgent();
        String firstHeaderString = getRequestHeaders().getFirstHeaderString(UpnpHeader.Type.EXT_AV_CLIENT_INFO);
        int i7 = C1213b.f2737a;
        return (requestUserAgent != null && requestUserAgent.contains("PLAYSTATION 3")) || (firstHeaderString != null && firstHeaderString.contains("PLAYSTATION 3"));
    }

    public boolean isRequestCancelled() {
        return !getConnection().isOpen();
    }

    public boolean isWMPRequest() {
        String requestUserAgent = getRequestUserAgent();
        int i7 = C1213b.f2737a;
        if (requestUserAgent == null || !requestUserAgent.contains("Windows-Media-Player")) {
            return false;
        }
        return !(requestUserAgent.contains("J-River") || requestUserAgent.contains("J. River"));
    }

    public boolean isXbox360Request() {
        String requestUserAgent = getRequestUserAgent();
        String firstHeaderString = getRequestHeaders().getFirstHeaderString(UpnpHeader.Type.SERVER);
        int i7 = C1213b.f2737a;
        return (requestUserAgent != null && (requestUserAgent.contains("Xbox") || requestUserAgent.contains("Xenon"))) || (firstHeaderString != null && firstHeaderString.contains("Xbox"));
    }

    public void setResponseUserAgent(String str) {
        setResponseUserAgent(new UserAgentHeader(str));
    }

    public void throwIfRequestCancelled() throws InterruptedException {
        if (isRequestCancelled()) {
            throw new InterruptedException("Client's request cancelled");
        }
    }

    @Override // org.fourthline.cling.model.profile.ClientInfo
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") Remote Address: ");
        sbM112a.append(getRemoteAddress());
        return sbM112a.toString();
    }

    public RemoteClientInfo(StreamRequestMessage streamRequestMessage) {
        this(streamRequestMessage != null ? streamRequestMessage.getConnection() : null, streamRequestMessage != null ? streamRequestMessage.getHeaders() : new UpnpHeaders());
    }

    public void setResponseUserAgent(UserAgentHeader userAgentHeader) {
        getExtraResponseHeaders().add(UpnpHeader.Type.USER_AGENT, userAgentHeader);
    }

    public RemoteClientInfo(Connection connection, UpnpHeaders upnpHeaders) {
        super(upnpHeaders);
        this.extraResponseHeaders = new UpnpHeaders();
        this.connection = connection;
    }
}
