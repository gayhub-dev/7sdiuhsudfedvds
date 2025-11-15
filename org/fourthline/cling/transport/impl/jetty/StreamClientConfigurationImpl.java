package org.fourthline.cling.transport.impl.jetty;

import java.util.concurrent.ExecutorService;
import org.fourthline.cling.transport.spi.AbstractStreamClientConfiguration;

/* loaded from: classes.dex */
public class StreamClientConfigurationImpl extends AbstractStreamClientConfiguration {
    public StreamClientConfigurationImpl(ExecutorService executorService) {
        super(executorService);
    }

    public int getRequestRetryCount() {
        return 0;
    }

    public StreamClientConfigurationImpl(ExecutorService executorService, int i7) {
        super(executorService, i7);
    }
}
