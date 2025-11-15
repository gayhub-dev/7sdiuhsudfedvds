package org.fourthline.cling.model.meta;

import java.net.InetAddress;
import java.net.URL;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.message.discovery.IncomingNotificationRequest;
import org.fourthline.cling.model.message.discovery.IncomingSearchResponse;
import org.fourthline.cling.model.types.UDN;
import p009b.C0413b;

/* loaded from: classes.dex */
public class RemoteDeviceIdentity extends DeviceIdentity {
    private final URL descriptorURL;
    private final InetAddress discoveredOnLocalAddress;
    private final byte[] interfaceMacAddress;

    public RemoteDeviceIdentity(UDN udn, RemoteDeviceIdentity remoteDeviceIdentity) {
        this(udn, remoteDeviceIdentity.getMaxAgeSeconds(), remoteDeviceIdentity.getDescriptorURL(), remoteDeviceIdentity.getInterfaceMacAddress(), remoteDeviceIdentity.getDiscoveredOnLocalAddress());
    }

    public URL getDescriptorURL() {
        return this.descriptorURL;
    }

    public InetAddress getDiscoveredOnLocalAddress() {
        return this.discoveredOnLocalAddress;
    }

    public byte[] getInterfaceMacAddress() {
        return this.interfaceMacAddress;
    }

    public byte[] getWakeOnLANBytes() {
        if (getInterfaceMacAddress() == null) {
            return null;
        }
        int length = 6;
        int length2 = (getInterfaceMacAddress().length * 16) + 6;
        byte[] bArr = new byte[length2];
        for (int i7 = 0; i7 < 6; i7++) {
            bArr[i7] = -1;
        }
        while (length < length2) {
            System.arraycopy(getInterfaceMacAddress(), 0, bArr, length, getInterfaceMacAddress().length);
            length += getInterfaceMacAddress().length;
        }
        return bArr;
    }

    @Override // org.fourthline.cling.model.meta.DeviceIdentity
    public String toString() {
        if (ModelUtil.ANDROID_RUNTIME) {
            StringBuilder sbM112a = C0413b.m112a("(RemoteDeviceIdentity) UDN: ");
            sbM112a.append(getUdn());
            sbM112a.append(", Descriptor: ");
            sbM112a.append(getDescriptorURL());
            return sbM112a.toString();
        }
        StringBuilder sbM112a2 = C0413b.m112a("(");
        sbM112a2.append(getClass().getSimpleName());
        sbM112a2.append(") UDN: ");
        sbM112a2.append(getUdn());
        sbM112a2.append(", Descriptor: ");
        sbM112a2.append(getDescriptorURL());
        return sbM112a2.toString();
    }

    public RemoteDeviceIdentity(UDN udn, Integer num, URL url, byte[] bArr, InetAddress inetAddress) {
        super(udn, num);
        this.descriptorURL = url;
        this.interfaceMacAddress = bArr;
        this.discoveredOnLocalAddress = inetAddress;
    }

    public RemoteDeviceIdentity(IncomingNotificationRequest incomingNotificationRequest) {
        this(incomingNotificationRequest.getUDN(), incomingNotificationRequest.getMaxAge(), incomingNotificationRequest.getLocationURL(), incomingNotificationRequest.getInterfaceMacHeader(), incomingNotificationRequest.getLocalAddress());
    }

    public RemoteDeviceIdentity(IncomingSearchResponse incomingSearchResponse) {
        this(incomingSearchResponse.getRootDeviceUDN(), incomingSearchResponse.getMaxAge(), incomingSearchResponse.getLocationURL(), incomingSearchResponse.getInterfaceMacHeader(), incomingSearchResponse.getLocalAddress());
    }
}
