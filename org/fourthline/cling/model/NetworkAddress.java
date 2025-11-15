package org.fourthline.cling.model;

import java.net.InetAddress;
import java.util.Arrays;

/* loaded from: classes.dex */
public class NetworkAddress {
    public InetAddress address;
    public byte[] hardwareAddress;
    public int port;

    public NetworkAddress(InetAddress inetAddress, int i7) {
        this(inetAddress, i7, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NetworkAddress networkAddress = (NetworkAddress) obj;
        return this.port == networkAddress.port && this.address.equals(networkAddress.address) && Arrays.equals(this.hardwareAddress, networkAddress.hardwareAddress);
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public byte[] getHardwareAddress() {
        return this.hardwareAddress;
    }

    public int getPort() {
        return this.port;
    }

    public int hashCode() {
        int iHashCode = ((this.address.hashCode() * 31) + this.port) * 31;
        byte[] bArr = this.hardwareAddress;
        return iHashCode + (bArr != null ? Arrays.hashCode(bArr) : 0);
    }

    public NetworkAddress(InetAddress inetAddress, int i7, byte[] bArr) {
        this.address = inetAddress;
        this.port = i7;
        this.hardwareAddress = bArr;
    }
}
