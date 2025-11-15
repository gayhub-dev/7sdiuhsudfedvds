package org.fourthline.cling.transport.impl;

import org.fourthline.cling.transport.spi.StreamServerConfiguration;

/* loaded from: classes.dex */
public class StreamServerConfigurationImpl implements StreamServerConfiguration {
    private int listenPort;
    private int tcpConnectionBacklog;

    public StreamServerConfigurationImpl() {
    }

    @Override // org.fourthline.cling.transport.spi.StreamServerConfiguration
    public int getListenPort() {
        return this.listenPort;
    }

    public int getTcpConnectionBacklog() {
        return this.tcpConnectionBacklog;
    }

    public void setListenPort(int i7) {
        this.listenPort = i7;
    }

    public void setTcpConnectionBacklog(int i7) {
        this.tcpConnectionBacklog = i7;
    }

    public StreamServerConfigurationImpl(int i7) {
        this.listenPort = i7;
    }
}
