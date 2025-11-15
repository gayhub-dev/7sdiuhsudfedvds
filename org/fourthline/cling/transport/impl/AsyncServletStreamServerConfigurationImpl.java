package org.fourthline.cling.transport.impl;

import org.fourthline.cling.transport.spi.ServletContainerAdapter;
import org.fourthline.cling.transport.spi.StreamServerConfiguration;

/* loaded from: classes.dex */
public class AsyncServletStreamServerConfigurationImpl implements StreamServerConfiguration {
    public int asyncTimeoutSeconds;
    public int listenPort;
    public ServletContainerAdapter servletContainerAdapter;

    public AsyncServletStreamServerConfigurationImpl(ServletContainerAdapter servletContainerAdapter) {
        this.listenPort = 0;
        this.asyncTimeoutSeconds = 60;
        this.servletContainerAdapter = servletContainerAdapter;
    }

    public int getAsyncTimeoutSeconds() {
        return this.asyncTimeoutSeconds;
    }

    @Override // org.fourthline.cling.transport.spi.StreamServerConfiguration
    public int getListenPort() {
        return this.listenPort;
    }

    public ServletContainerAdapter getServletContainerAdapter() {
        return this.servletContainerAdapter;
    }

    public void setAsyncTimeoutSeconds(int i7) {
        this.asyncTimeoutSeconds = i7;
    }

    public void setListenPort(int i7) {
        this.listenPort = i7;
    }

    public void setServletContainerAdapter(ServletContainerAdapter servletContainerAdapter) {
        this.servletContainerAdapter = servletContainerAdapter;
    }

    public AsyncServletStreamServerConfigurationImpl(ServletContainerAdapter servletContainerAdapter, int i7) {
        this.listenPort = 0;
        this.asyncTimeoutSeconds = 60;
        this.servletContainerAdapter = servletContainerAdapter;
        this.listenPort = i7;
    }

    public AsyncServletStreamServerConfigurationImpl(ServletContainerAdapter servletContainerAdapter, int i7, int i8) {
        this.listenPort = 0;
        this.asyncTimeoutSeconds = 60;
        this.servletContainerAdapter = servletContainerAdapter;
        this.listenPort = i7;
        this.asyncTimeoutSeconds = i8;
    }
}
