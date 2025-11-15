package org.fourthline.cling.transport.impl;

import java.util.concurrent.ExecutorService;
import org.fourthline.cling.transport.spi.AbstractStreamClientConfiguration;

/* loaded from: classes.dex */
public class StreamClientConfigurationImpl extends AbstractStreamClientConfiguration {
    private boolean usePersistentConnections;

    public StreamClientConfigurationImpl(ExecutorService executorService) {
        super(executorService);
        this.usePersistentConnections = false;
    }

    public boolean isUsePersistentConnections() {
        return this.usePersistentConnections;
    }

    public void setUsePersistentConnections(boolean z6) {
        this.usePersistentConnections = z6;
    }

    public StreamClientConfigurationImpl(ExecutorService executorService, int i7) {
        super(executorService, i7);
        this.usePersistentConnections = false;
    }
}
