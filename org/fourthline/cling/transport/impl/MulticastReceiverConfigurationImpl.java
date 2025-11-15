package org.fourthline.cling.transport.impl;

import java.net.InetAddress;
import org.fourthline.cling.transport.spi.MulticastReceiverConfiguration;

/* loaded from: classes.dex */
public class MulticastReceiverConfigurationImpl implements MulticastReceiverConfiguration {
    private InetAddress group;
    private int maxDatagramBytes;
    private int port;

    public MulticastReceiverConfigurationImpl(InetAddress inetAddress, int i7, int i8) {
        this.group = inetAddress;
        this.port = i7;
        this.maxDatagramBytes = i8;
    }

    @Override // org.fourthline.cling.transport.spi.MulticastReceiverConfiguration
    public InetAddress getGroup() {
        return this.group;
    }

    @Override // org.fourthline.cling.transport.spi.MulticastReceiverConfiguration
    public int getMaxDatagramBytes() {
        return this.maxDatagramBytes;
    }

    @Override // org.fourthline.cling.transport.spi.MulticastReceiverConfiguration
    public int getPort() {
        return this.port;
    }

    public void setGroup(InetAddress inetAddress) {
        this.group = inetAddress;
    }

    public void setMaxDatagramBytes(int i7) {
        this.maxDatagramBytes = i7;
    }

    public void setPort(int i7) {
        this.port = i7;
    }

    public MulticastReceiverConfigurationImpl(InetAddress inetAddress, int i7) {
        this(inetAddress, i7, 640);
    }

    public MulticastReceiverConfigurationImpl(String str, int i7, int i8) {
        this(InetAddress.getByName(str), i7, i8);
    }

    public MulticastReceiverConfigurationImpl(String str, int i7) {
        this(InetAddress.getByName(str), i7, 640);
    }
}
