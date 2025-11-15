package org.fourthline.cling.transport.impl;

import org.fourthline.cling.transport.spi.DatagramIOConfiguration;

/* loaded from: classes.dex */
public class DatagramIOConfigurationImpl implements DatagramIOConfiguration {
    private int maxDatagramBytes;
    private int timeToLive;

    public DatagramIOConfigurationImpl() {
        this.timeToLive = 4;
        this.maxDatagramBytes = 640;
    }

    @Override // org.fourthline.cling.transport.spi.DatagramIOConfiguration
    public int getMaxDatagramBytes() {
        return this.maxDatagramBytes;
    }

    @Override // org.fourthline.cling.transport.spi.DatagramIOConfiguration
    public int getTimeToLive() {
        return this.timeToLive;
    }

    public void setMaxDatagramBytes(int i7) {
        this.maxDatagramBytes = i7;
    }

    public void setTimeToLive(int i7) {
        this.timeToLive = i7;
    }

    public DatagramIOConfigurationImpl(int i7, int i8) {
        this.timeToLive = 4;
        this.maxDatagramBytes = 640;
        this.timeToLive = i7;
        this.maxDatagramBytes = i8;
    }
}
