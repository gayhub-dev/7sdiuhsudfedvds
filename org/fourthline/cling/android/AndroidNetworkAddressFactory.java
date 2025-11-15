package org.fourthline.cling.android;

import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl;
import p009b.C0413b;

/* loaded from: classes.dex */
public class AndroidNetworkAddressFactory extends NetworkAddressFactoryImpl {
    private static final Logger log = Logger.getLogger(AndroidUpnpServiceConfiguration.class.getName());

    public AndroidNetworkAddressFactory(int i7) {
        super(i7);
    }

    @Override // org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl
    public void discoverNetworkInterfaces() {
        try {
            super.discoverNetworkInterfaces();
        } catch (Exception e7) {
            log.warning("Exception while enumerating network interfaces, trying once more: " + e7);
            super.discoverNetworkInterfaces();
        }
    }

    @Override // org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl, org.fourthline.cling.transport.spi.NetworkAddressFactory
    public InetAddress getLocalAddress(NetworkInterface networkInterface, boolean z6, InetAddress inetAddress) {
        for (InetAddress inetAddress2 : getInetAddresses(networkInterface)) {
            if (z6 && (inetAddress2 instanceof Inet6Address)) {
                return inetAddress2;
            }
            if (!z6 && (inetAddress2 instanceof Inet4Address)) {
                return inetAddress2;
            }
        }
        StringBuilder sbM112a = C0413b.m112a("Can't find any IPv4 or IPv6 address on interface: ");
        sbM112a.append(networkInterface.getDisplayName());
        throw new IllegalStateException(sbM112a.toString());
    }

    @Override // org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl
    public boolean isUsableAddress(NetworkInterface networkInterface, InetAddress inetAddress) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Field declaredField;
        Object obj;
        boolean zIsUsableAddress = super.isUsableAddress(networkInterface, inetAddress);
        if (zIsUsableAddress) {
            String hostAddress = inetAddress.getHostAddress();
            try {
                try {
                    Field declaredField2 = InetAddress.class.getDeclaredField("holder");
                    declaredField2.setAccessible(true);
                    obj = declaredField2.get(inetAddress);
                    declaredField = obj.getClass().getDeclaredField("hostName");
                } catch (NoSuchFieldException unused) {
                    declaredField = InetAddress.class.getDeclaredField("hostName");
                    obj = inetAddress;
                }
                if (declaredField == null || hostAddress == null) {
                    return false;
                }
                declaredField.setAccessible(true);
                declaredField.set(obj, hostAddress);
            } catch (Exception e7) {
                log.log(Level.SEVERE, "Failed injecting hostName to work around Android InetAddress DNS bug: " + inetAddress, (Throwable) e7);
                return false;
            }
        }
        return zIsUsableAddress;
    }

    @Override // org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl
    public boolean requiresNetworkInterface() {
        return false;
    }
}
