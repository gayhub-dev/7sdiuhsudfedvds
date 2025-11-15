package org.fourthline.cling.transport.impl;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.Constants;
import org.fourthline.cling.transport.spi.InitializationException;
import org.fourthline.cling.transport.spi.NetworkAddressFactory;
import org.fourthline.cling.transport.spi.NoNetworkException;
import p009b.C0413b;
import p098l6.AbstractC1447a;

/* loaded from: classes.dex */
public class NetworkAddressFactoryImpl implements NetworkAddressFactory {
    public static final int DEFAULT_TCP_HTTP_LISTEN_PORT = 0;
    private static Logger log = Logger.getLogger(NetworkAddressFactoryImpl.class.getName());
    public final List<InetAddress> bindAddresses;
    public final List<NetworkInterface> networkInterfaces;
    public int streamListenPort;
    public final Set<String> useAddresses;
    public final Set<String> useInterfaces;

    public NetworkAddressFactoryImpl() {
        this(0);
    }

    public void discoverBindAddresses() {
        try {
            synchronized (this.networkInterfaces) {
                Iterator<NetworkInterface> it = this.networkInterfaces.iterator();
                while (it.hasNext()) {
                    NetworkInterface next = it.next();
                    log.finer("Discovering addresses of interface: " + next.getDisplayName());
                    int i7 = 0;
                    for (InetAddress inetAddress : getInetAddresses(next)) {
                        if (inetAddress == null) {
                            log.warning("Network has a null address: " + next.getDisplayName());
                        } else if (isUsableAddress(next, inetAddress)) {
                            log.fine("Discovered usable network interface address: " + inetAddress.getHostAddress());
                            i7++;
                            synchronized (this.bindAddresses) {
                                this.bindAddresses.add(inetAddress);
                            }
                        } else {
                            log.finer("Ignoring non-usable network interface address: " + inetAddress.getHostAddress());
                        }
                    }
                    if (i7 == 0) {
                        log.finer("Network interface has no usable addresses, removing: " + next.getDisplayName());
                        it.remove();
                    }
                }
            }
        } catch (Exception e7) {
            throw new InitializationException("Could not not analyze local network interfaces: " + e7, e7);
        }
    }

    public void discoverNetworkInterfaces() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                log.finer("Analyzing network interface: " + networkInterface.getDisplayName());
                if (isUsableNetworkInterface(networkInterface)) {
                    log.fine("Discovered usable network interface: " + networkInterface.getDisplayName());
                    synchronized (this.networkInterfaces) {
                        this.networkInterfaces.add(networkInterface);
                    }
                } else {
                    log.finer("Ignoring non-usable network interface: " + networkInterface.getDisplayName());
                }
            }
        } catch (Exception e7) {
            throw new InitializationException("Could not not analyze local network interfaces: " + e7, e7);
        }
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public Short getAddressNetworkPrefixLength(InetAddress inetAddress) {
        synchronized (this.networkInterfaces) {
            Iterator<NetworkInterface> it = this.networkInterfaces.iterator();
            while (it.hasNext()) {
                for (InterfaceAddress interfaceAddress : getInterfaceAddresses(it.next())) {
                    if (interfaceAddress != null && interfaceAddress.getAddress().equals(inetAddress)) {
                        short networkPrefixLength = interfaceAddress.getNetworkPrefixLength();
                        if (networkPrefixLength <= 0 || networkPrefixLength >= 32) {
                            return null;
                        }
                        return Short.valueOf(networkPrefixLength);
                    }
                }
            }
            return null;
        }
    }

    public InetAddress getBindAddressInSubnetOf(InetAddress inetAddress) {
        synchronized (this.networkInterfaces) {
            Iterator<NetworkInterface> it = this.networkInterfaces.iterator();
            while (it.hasNext()) {
                for (InterfaceAddress interfaceAddress : getInterfaceAddresses(it.next())) {
                    synchronized (this.bindAddresses) {
                        if (interfaceAddress != null) {
                            if (this.bindAddresses.contains(interfaceAddress.getAddress())) {
                                if (isInSubnet(inetAddress.getAddress(), interfaceAddress.getAddress().getAddress(), interfaceAddress.getNetworkPrefixLength())) {
                                    return interfaceAddress.getAddress();
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public Iterator<InetAddress> getBindAddresses() {
        return new AbstractC1447a<InetAddress>(this.bindAddresses) { // from class: org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl.2
            @Override // p098l6.AbstractC1447a
            public void synchronizedRemove(int i7) {
                synchronized (NetworkAddressFactoryImpl.this.bindAddresses) {
                    NetworkAddressFactoryImpl.this.bindAddresses.remove(i7);
                }
            }
        };
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public InetAddress getBroadcastAddress(InetAddress inetAddress) {
        synchronized (this.networkInterfaces) {
            Iterator<NetworkInterface> it = this.networkInterfaces.iterator();
            while (it.hasNext()) {
                for (InterfaceAddress interfaceAddress : getInterfaceAddresses(it.next())) {
                    if (interfaceAddress != null && interfaceAddress.getAddress().equals(inetAddress)) {
                        return interfaceAddress.getBroadcast();
                    }
                }
            }
            return null;
        }
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public byte[] getHardwareAddress(InetAddress inetAddress) {
        try {
            NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(inetAddress);
            if (byInetAddress != null) {
                return byInetAddress.getHardwareAddress();
            }
            return null;
        } catch (Throwable th) {
            log.log(Level.WARNING, "Cannot get hardware address for: " + inetAddress, th);
            return null;
        }
    }

    public List<InetAddress> getInetAddresses(NetworkInterface networkInterface) {
        return Collections.list(networkInterface.getInetAddresses());
    }

    public List<InterfaceAddress> getInterfaceAddresses(NetworkInterface networkInterface) {
        return networkInterface.getInterfaceAddresses();
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public InetAddress getLocalAddress(NetworkInterface networkInterface, boolean z6, InetAddress inetAddress) {
        InetAddress bindAddressInSubnetOf = getBindAddressInSubnetOf(inetAddress);
        if (bindAddressInSubnetOf != null) {
            return bindAddressInSubnetOf;
        }
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Could not find local bind address in same subnet as: ");
        sbM112a.append(inetAddress.getHostAddress());
        logger.finer(sbM112a.toString());
        for (InetAddress inetAddress2 : getInetAddresses(networkInterface)) {
            if (z6 && (inetAddress2 instanceof Inet6Address)) {
                return inetAddress2;
            }
            if (!z6 && (inetAddress2 instanceof Inet4Address)) {
                return inetAddress2;
            }
        }
        StringBuilder sbM112a2 = C0413b.m112a("Can't find any IPv4 or IPv6 address on interface: ");
        sbM112a2.append(networkInterface.getDisplayName());
        throw new IllegalStateException(sbM112a2.toString());
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public InetAddress getMulticastGroup() {
        try {
            return InetAddress.getByName(Constants.IPV4_UPNP_MULTICAST_GROUP);
        } catch (UnknownHostException e7) {
            throw new RuntimeException(e7);
        }
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public int getMulticastPort() {
        return Constants.UPNP_MULTICAST_PORT;
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public Iterator<NetworkInterface> getNetworkInterfaces() {
        return new AbstractC1447a<NetworkInterface>(this.networkInterfaces) { // from class: org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl.1
            @Override // p098l6.AbstractC1447a
            public void synchronizedRemove(int i7) {
                synchronized (NetworkAddressFactoryImpl.this.networkInterfaces) {
                    NetworkAddressFactoryImpl.this.networkInterfaces.remove(i7);
                }
            }
        };
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public int getStreamListenPort() {
        return this.streamListenPort;
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public boolean hasUsableNetwork() {
        return this.networkInterfaces.size() > 0 && this.bindAddresses.size() > 0;
    }

    public boolean isInSubnet(byte[] bArr, byte[] bArr2, short s6) {
        if (bArr.length != bArr2.length || s6 / 8 > bArr.length) {
            return false;
        }
        int i7 = 0;
        while (s6 >= 8 && i7 < bArr.length) {
            if (bArr[i7] != bArr2[i7]) {
                return false;
            }
            i7++;
            s6 = (short) (s6 - 8);
        }
        if (i7 == bArr.length) {
            return true;
        }
        byte b7 = (byte) (~((1 << (8 - s6)) - 1));
        return (bArr[i7] & b7) == (bArr2[i7] & b7);
    }

    public boolean isUsableAddress(NetworkInterface networkInterface, InetAddress inetAddress) {
        if (!(inetAddress instanceof Inet4Address)) {
            log.finer("Skipping unsupported non-IPv4 address: " + inetAddress);
            return false;
        }
        if (inetAddress.isLoopbackAddress()) {
            log.finer("Skipping loopback address: " + inetAddress);
            return false;
        }
        if (this.useAddresses.size() <= 0 || this.useAddresses.contains(inetAddress.getHostAddress())) {
            return true;
        }
        log.finer("Skipping unwanted address: " + inetAddress);
        return false;
    }

    public boolean isUsableNetworkInterface(NetworkInterface networkInterface) {
        if (!networkInterface.isUp()) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Skipping network interface (down): ");
            sbM112a.append(networkInterface.getDisplayName());
            logger.finer(sbM112a.toString());
            return false;
        }
        if (getInetAddresses(networkInterface).size() == 0) {
            Logger logger2 = log;
            StringBuilder sbM112a2 = C0413b.m112a("Skipping network interface without bound IP addresses: ");
            sbM112a2.append(networkInterface.getDisplayName());
            logger2.finer(sbM112a2.toString());
            return false;
        }
        String name = networkInterface.getName();
        Locale locale = Locale.ROOT;
        if (name.toLowerCase(locale).startsWith("vmnet") || (networkInterface.getDisplayName() != null && networkInterface.getDisplayName().toLowerCase(locale).contains("vmnet"))) {
            Logger logger3 = log;
            StringBuilder sbM112a3 = C0413b.m112a("Skipping network interface (VMWare): ");
            sbM112a3.append(networkInterface.getDisplayName());
            logger3.finer(sbM112a3.toString());
            return false;
        }
        if (networkInterface.getName().toLowerCase(locale).startsWith("vnic")) {
            Logger logger4 = log;
            StringBuilder sbM112a4 = C0413b.m112a("Skipping network interface (Parallels): ");
            sbM112a4.append(networkInterface.getDisplayName());
            logger4.finer(sbM112a4.toString());
            return false;
        }
        if (networkInterface.getName().toLowerCase(locale).startsWith("vboxnet")) {
            Logger logger5 = log;
            StringBuilder sbM112a5 = C0413b.m112a("Skipping network interface (Virtual Box): ");
            sbM112a5.append(networkInterface.getDisplayName());
            logger5.finer(sbM112a5.toString());
            return false;
        }
        if (networkInterface.getName().toLowerCase(locale).contains("virtual")) {
            Logger logger6 = log;
            StringBuilder sbM112a6 = C0413b.m112a("Skipping network interface (named '*virtual*'): ");
            sbM112a6.append(networkInterface.getDisplayName());
            logger6.finer(sbM112a6.toString());
            return false;
        }
        if (networkInterface.getName().toLowerCase(locale).startsWith("ppp")) {
            Logger logger7 = log;
            StringBuilder sbM112a7 = C0413b.m112a("Skipping network interface (PPP): ");
            sbM112a7.append(networkInterface.getDisplayName());
            logger7.finer(sbM112a7.toString());
            return false;
        }
        if (networkInterface.isLoopback()) {
            Logger logger8 = log;
            StringBuilder sbM112a8 = C0413b.m112a("Skipping network interface (ignoring loopback): ");
            sbM112a8.append(networkInterface.getDisplayName());
            logger8.finer(sbM112a8.toString());
            return false;
        }
        if (this.useInterfaces.size() > 0 && !this.useInterfaces.contains(networkInterface.getName())) {
            Logger logger9 = log;
            StringBuilder sbM112a9 = C0413b.m112a("Skipping unwanted network interface (-Dorg.fourthline.cling.network.useInterfaces): ");
            sbM112a9.append(networkInterface.getName());
            logger9.finer(sbM112a9.toString());
            return false;
        }
        if (networkInterface.supportsMulticast()) {
            return true;
        }
        Logger logger10 = log;
        StringBuilder sbM112a10 = C0413b.m112a("Network interface may not be multicast capable: ");
        sbM112a10.append(networkInterface.getDisplayName());
        logger10.warning(sbM112a10.toString());
        return true;
    }

    @Override // org.fourthline.cling.transport.spi.NetworkAddressFactory
    public void logInterfaceInformation() {
        synchronized (this.networkInterfaces) {
            if (this.networkInterfaces.isEmpty()) {
                log.info("No network interface to display!");
                return;
            }
            Iterator<NetworkInterface> it = this.networkInterfaces.iterator();
            while (it.hasNext()) {
                try {
                    logInterfaceInformation(it.next());
                } catch (SocketException e7) {
                    log.log(Level.WARNING, "Exception while logging network interface information", (Throwable) e7);
                }
            }
        }
    }

    public boolean requiresNetworkInterface() {
        return true;
    }

    public NetworkAddressFactoryImpl(int i7) {
        HashSet hashSet = new HashSet();
        this.useInterfaces = hashSet;
        HashSet hashSet2 = new HashSet();
        this.useAddresses = hashSet2;
        ArrayList arrayList = new ArrayList();
        this.networkInterfaces = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.bindAddresses = arrayList2;
        System.setProperty("java.net.preferIPv4Stack", "true");
        String property = System.getProperty(NetworkAddressFactory.SYSTEM_PROPERTY_NET_IFACES);
        if (property != null) {
            hashSet.addAll(Arrays.asList(property.split(",")));
        }
        String property2 = System.getProperty(NetworkAddressFactory.SYSTEM_PROPERTY_NET_ADDRESSES);
        if (property2 != null) {
            hashSet2.addAll(Arrays.asList(property2.split(",")));
        }
        discoverNetworkInterfaces();
        discoverBindAddresses();
        if (arrayList.size() == 0 || arrayList2.size() == 0) {
            log.warning("No usable network interface or addresses found");
            if (requiresNetworkInterface()) {
                throw new NoNetworkException("Could not discover any usable network interfaces and/or addresses");
            }
        }
        this.streamListenPort = i7;
    }

    public void logInterfaceInformation(NetworkInterface networkInterface) {
        log.info("---------------------------------------------------------------------------------");
        log.info(String.format("Interface display name: %s", networkInterface.getDisplayName()));
        if (networkInterface.getParent() != null) {
            log.info(String.format("Parent Info: %s", networkInterface.getParent()));
        }
        log.info(String.format("Name: %s", networkInterface.getName()));
        Iterator it = Collections.list(networkInterface.getInetAddresses()).iterator();
        while (it.hasNext()) {
            log.info(String.format("InetAddress: %s", (InetAddress) it.next()));
        }
        for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
            if (interfaceAddress == null) {
                log.warning("Skipping null InterfaceAddress!");
            } else {
                log.info(" Interface Address");
                Logger logger = log;
                StringBuilder sbM112a = C0413b.m112a("  Address: ");
                sbM112a.append(interfaceAddress.getAddress());
                logger.info(sbM112a.toString());
                Logger logger2 = log;
                StringBuilder sbM112a2 = C0413b.m112a("  Broadcast: ");
                sbM112a2.append(interfaceAddress.getBroadcast());
                logger2.info(sbM112a2.toString());
                Logger logger3 = log;
                StringBuilder sbM112a3 = C0413b.m112a("  Prefix length: ");
                sbM112a3.append((int) interfaceAddress.getNetworkPrefixLength());
                logger3.info(sbM112a3.toString());
            }
        }
        Iterator it2 = Collections.list(networkInterface.getSubInterfaces()).iterator();
        while (it2.hasNext()) {
            NetworkInterface networkInterface2 = (NetworkInterface) it2.next();
            if (networkInterface2 == null) {
                log.warning("Skipping null NetworkInterface sub-interface");
            } else {
                log.info(String.format("\tSub Interface Display name: %s", networkInterface2.getDisplayName()));
                log.info(String.format("\tSub Interface Name: %s", networkInterface2.getName()));
            }
        }
        log.info(String.format("Up? %s", Boolean.valueOf(networkInterface.isUp())));
        log.info(String.format("Loopback? %s", Boolean.valueOf(networkInterface.isLoopback())));
        log.info(String.format("PointToPoint? %s", Boolean.valueOf(networkInterface.isPointToPoint())));
        log.info(String.format("Supports multicast? %s", Boolean.valueOf(networkInterface.supportsMulticast())));
        log.info(String.format("Virtual? %s", Boolean.valueOf(networkInterface.isVirtual())));
        log.info(String.format("Hardware address: %s", Arrays.toString(networkInterface.getHardwareAddress())));
        log.info(String.format("MTU: %s", Integer.valueOf(networkInterface.getMTU())));
    }
}
